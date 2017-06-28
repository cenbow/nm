package com.hs.loan.finance.service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.commons.attach.Attachment;
import com.hs.commons.attach.AttachmentApi;
import com.hs.commons.attach.OssUtil;
import com.hs.commons.attach.tansfer.IFileTransfer;
import com.hs.commons.constants.CommonConstant;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.commons.tools.SpringContextHolder;
import com.hs.loan.finance.bo.BatchDkBo;
import com.hs.loan.finance.bo.BatchDkResultBo;
import com.hs.loan.finance.bo.BatchRepayCallBackBo;
import com.hs.loan.finance.bo.MerInfo;
import com.hs.loan.finance.bo.QueryBo;
import com.hs.loan.finance.bo.QueryResultBo;
import com.hs.loan.finance.bo.QuerySum;
import com.hs.loan.finance.bo.RetItemBo;
import com.hs.loan.finance.bo.SingleDkDto;
import com.hs.loan.finance.bo.SingleDkResultBo;
import com.hs.loan.finance.bo.SingleOtherBusiBo;
import com.hs.loan.finance.bo.SingleRepayBo;
import com.hs.loan.finance.bo.TransItem;
import com.hs.loan.finance.bo.TransSum;
import com.hs.loan.finance.contant.FinanceConstant;
import com.hs.loan.finance.entity.AccCapTranDtl;
import com.hs.loan.finance.entity.AccCapWith;
import com.hs.loan.finance.entity.AccRepayFlow;
import com.hs.loan.finance.entity.AccWithholdReg;
import com.hs.loan.finance.mapper.AccCapWithMapper;
import com.hs.loan.finance.util.CompAmtUtil;
import com.hs.loan.finance.util.DateUtil;
import com.hs.loan.finance.util.EBPPUtil;
import com.hs.loan.finance.util.HttpInvokerUtil;
import com.hs.loan.finance.util.KftUtil;
import com.hs.loan.finance.util.PayChanType;
import com.hs.loan.finance.util.RepayUtil;
import com.hs.utils.AmountUtils;
import com.hs.utils.DateUtils;
import com.hs.utils.ParamUtils;
import com.hs.utils.RandomUtil;

/**
 * 业务处理
 * 
 * @author autocreate
 * @create 2016-02-03
 */
@Service
@Transactional(readOnly = true)
public class AccCapWithService {

	@Autowired
	private AccLoanAcctInstService accLoanAcctInstService;
	@Autowired
	private AccCapWithMapper accCapWithMapper;
	@Autowired
	private AccRepayFlowService accRepayFlowService;
	@Autowired
	private AccCapTranDtlService accCapTranDtlService;
	@Autowired
	private IFileTransfer fileTransfer;
	@Autowired
	private AccWithholdRegService accWithholdRegService;
	@Autowired
	private AccPayWithService accPayWithService;

	/**
	 * 根据贷款编号查询交易方
	 * 
	 * @param loanNo
	 * @return String
	 */
	public String selectTranObjByLoanNo(String loanNo) {
		return accCapWithMapper.selectTranObjByLoanNo(loanNo);
	}

	/**
	 * 上传附件
	 * 
	 * @param in
	 *            附件数据
	 * @param type
	 *            附件类型
	 */
	@Transactional
	public Attachment uploadContract(InputStream in, String fileName, String busiType) {
		try {
			Attachment attachment = new Attachment();
			attachment.setFileSize(new Long(in.available()));
			attachment.setContentType(fileName.split("\\.")[1]);
			attachment.setOriginalName(fileName);
			attachment.setPresentName(fileName);
			attachment.setPhysicalAddress(ParamUtils.getParam("basePhysicalAddressDir") + DateUtils.getCurDate() + "/"
					+ attachment.getPresentName());
			attachment.setNetworkAddress(ParamUtils.getParam("baseNetworkAddressDir") + attachment.getPresentName());
			attachment.setBusiType(busiType);
			fileTransfer.upload(in, attachment);
			in.close();
			AttachmentApi sysAttachServer = SpringContextHolder.getBean("sysAttachServer");
			attachment = sysAttachServer.save(attachment);
			return attachment;
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new AppException("上传文件失败" + e.getMessage());
		}
	}

	/**
	 * 获取批次编号
	 * 
	 * @param busiType
	 *            不同渠道的分开生成
	 * @return
	 */
	public String getBatchNo(String busiType) {
		String batchNo = accPayWithService.getBatchNo(busiType);
		return batchNo;
	}

	/**
	 * 根据贷款编号更新扣款状态
	 * 
	 * @param map
	 *            loanNo(贷款编号) repayNum(支付期数)
	 * @return Integer
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Integer updateWithStat(Map<String, Object> map) {
		return accCapWithMapper.updateWithStat(map);
	}

	/**
	 * 单笔代扣交易 催收 提前结清
	 * 
	 * @param singleOtherBusiBo
	 * @param userProFile
	 * @return
	 * @throws ServiceException
	 */
	@Transactional
	public SingleDkResultBo singleRepayOtherBusi(SingleOtherBusiBo singleOtherBusiBo, UserProfile userProFile)
			throws ServiceException {
		if (null == singleOtherBusiBo) {
			throw new ServiceException("参数为空");
		}
		SingleDkResultBo singleDkResultBo = null;
		try {
			// 组装代扣交易信息数据
			SingleDkDto singleDkVo = RepayUtil.getSingleDkVo(singleOtherBusiBo);
			String result = "";
			// 单笔代扣请求
			if (PayChanType.ALLINPAY.toString().equals(singleOtherBusiBo.getTranType())) {// 通联
				result = HttpInvokerUtil.postJson(ParamUtils.getParam(FinanceConstant.tl_single_repay_url),
						JSONObject.toJSONString(singleDkVo));
			} else if (PayChanType.CHINAPAY.toString().equals(singleOtherBusiBo.getTranType())) { // 银联
				result = HttpInvokerUtil.postJson(ParamUtils.getParam(FinanceConstant.single_repay_url),
						JSONObject.toJSONString(singleDkVo));
			} else if (PayChanType.ZJPAY.toString().equals(singleOtherBusiBo.getTranType())) { // 中金
				if (capWithSize(1, singleOtherBusiBo.getLoanNo())) {
					throw new ServiceException("该笔贷款当日扣款失败次数超出限制");
				}
				result = HttpInvokerUtil.postJson(ParamUtils.getParam(FinanceConstant.zj_single_repay_url),
						JSONObject.toJSONString(singleDkVo));
				if (result != null && result.length() > 0) {
					JSONObject js = JSONObject.parseObject(result);
					result = js.get("singleResult").toString();
				}
			} else if (PayChanType.LIANLIANPAY.toString().equals(singleOtherBusiBo.getTranType())) { // 连连支付
				result = HttpInvokerUtil.postJson(ParamUtils.getParam(FinanceConstant.lianlian_single_repay_url),
						JSONObject.toJSONString(singleDkVo));
				if (result != null && result.length() > 0) {
					JSONObject js = JSONObject.parseObject(result);
					result = js.get("singleResult").toString();
				}
			} else {
				throw new ServiceException("不存在的扣款渠道");
			}
			singleDkResultBo = JSONObject.parseObject(result, SingleDkResultBo.class);
			if (singleDkResultBo.getRetItem() == null
					&& singleDkResultBo.getRetCode().equals(FinanceConstant.TRAN_ST_DEALING)) {
				RetItemBo re = new RetItemBo();
				re.setRetCode(FinanceConstant.TRAN_ST_DEALING);
				re.setErrMsg("代扣处理中......");
				singleDkResultBo.setRetItem(re);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return singleDkResultBo;
	}

	/**
	 * 单笔代扣交易
	 * 
	 * @param singleRepayVO
	 * @throws ServiceException
	 */
	@Transactional
	public SingleDkResultBo singleRepay(SingleRepayBo singleRepayBo, UserProfile userProFile) throws ServiceException {
		if (null == singleRepayBo) {
			throw new ServiceException("请选择一条代扣纪录");
		}
		if (com.hs.utils.StringUtils.isBlank(singleRepayBo.getWithStat())) {
			throw new ServiceException("单笔代扣［还款状态为空］");
		} else if (CompAmtUtil.checkDKWithStat(String.valueOf(singleRepayBo.getWithStat()))) {
			throw new ServiceException("单笔代扣［当前代扣状态不允许扣款］");
		}
		// 贷款编号 //期数
		String loanNo = singleRepayBo.getLoanNo();
		Integer repayNum = singleRepayBo.getRepayNum();
		Boolean isUnlock = false;// 异常后是否需要强制解锁预处理

		SingleDkResultBo singleDkResultBo = null;
		// 单笔代扣状态，默认代扣失败
		String withStat = FinanceConstant.WITHSTAT_DKFIELD;
		List<AccCapWith> listWith = this.queryWithNStat(loanNo, repayNum);
		if (listWith != null && listWith.size() > 0) {
			throw new ServiceException("该笔贷款[" + loanNo + "]在第[" + repayNum + "]期之前还有未还款的记录,请按还款日期依次还款");
		} else {
			if (singleRepayBo.getChanNo() != null && singleRepayBo.getChanNo().equals("006")) { // 信托扣款判断是否逾期
				String repayDate = singleRepayBo.getRepayDate();
				String nowDate = DateUtil.getDay("yyyyMMdd");
				if (!repayDate.equals(nowDate)) {
					throw new ServiceException("该笔贷款[" + loanNo + "],第[" + repayNum + "]期扣款日期不为今日,不允许资方扣款");
				}
			}
		}
		// 获取交易金额
		BigDecimal transBal = BigDecimal.ZERO;
		// 渠道代码
		String acctFlg = FinanceConstant.ACCTFLAG_PT;
		if (singleRepayBo.getChanNo() != null && singleRepayBo.getChanNo().equals("006")) {
			acctFlg = FinanceConstant.ACCTFLAG_XT;
			transBal = CompAmtUtil.getDkTransBal(loanNo, repayNum, "002", acctFlg, "");
		} else {
			transBal = CompAmtUtil.getDkTransBal(loanNo, repayNum, "", acctFlg, "");
		}
		if (transBal.compareTo(BigDecimal.ZERO) == 0) {
			throw new ServiceException("该笔贷款已扣款，请刷新页面查看");
		}
		// 锁表 锁预处理表 实时提交事务
		String returnStatus = accLoanAcctInstService.updateAccLoanAcctStatusLock(loanNo, repayNum);
		if (!returnStatus.equals(FinanceConstant.SUCC)) {
			throw new ServiceException("代扣数据［预处理信息ACC_LOAN_ACCT_INST］锁表失败");
		}
		String tranChan = "";
		try {
			// 组装代扣交易信息数据
			// 单笔代扣请求
			SingleDkDto singleDkVo = RepayUtil.getSingleDkVo(singleRepayBo, transBal);
			String result = "";
			if (PayChanType.ALLINPAY.toString().equals(singleRepayBo.getTranType())) {// 通联
				result = HttpInvokerUtil.postJson(ParamUtils.getParam(FinanceConstant.tl_single_repay_url),
						JSONObject.toJSONString(singleDkVo));
			} else if (PayChanType.CHINAPAY.toString().equals(singleRepayBo.getTranType())) { // 银联
				result = HttpInvokerUtil.postJson(ParamUtils.getParam(FinanceConstant.single_repay_url),
						JSONObject.toJSONString(singleDkVo));
			} else if (PayChanType.ZJPAY.toString().equals(singleRepayBo.getTranType())) { // 中金
				tranChan = PubBusinessConstant.ZJ_SIGLE;
				if (capWithSize(1, singleRepayBo.getLoanNo())) {
					throw new ServiceException("该笔贷款当日扣款失败次数超出限制");
				}
				if (singleRepayBo.getChanNo() != null && singleRepayBo.getChanNo().equals("006")) {
					result = HttpInvokerUtil.postJson(ParamUtils.getParam(FinanceConstant.zj_zf_single_repay_url),
							JSONObject.toJSONString(singleDkVo));
				} else {
					result = HttpInvokerUtil.postJson(ParamUtils.getParam(FinanceConstant.zj_single_repay_url),
							JSONObject.toJSONString(singleDkVo));
				}
				if (result != null && result.length() > 0) {
					JSONObject js = JSONObject.parseObject(result);
					result = js.get("singleResult").toString();
				}
			} else if (PayChanType.LIANLIANPAY.toString().equals(singleRepayBo.getTranType())) {
				tranChan = PubBusinessConstant.LL_SIGLE;
				result = HttpInvokerUtil.postJson(ParamUtils.getParam(FinanceConstant.lianlian_single_repay_url),
						JSONObject.toJSONString(singleDkVo));
				if (result != null && result.length() > 0) {
					JSONObject js = JSONObject.parseObject(result);
					result = js.get("singleResult").toString();
				}

			} else {
				throw new ServiceException("此扣款通道暂未开通");
			}

			singleDkResultBo = JSONObject.parseObject(result, SingleDkResultBo.class);
			if (null != singleDkResultBo) {
				AccWithholdReg reg = new AccWithholdReg();
				reg.setId(RandomUtil.getUUID());
				reg.setBgnNum(singleRepayBo.getRepayNum());
				reg.setEndNum(singleRepayBo.getRepayNum());
				reg.setStaffNo(userProFile.getStaffNo());
				reg.setStaffName(userProFile.getStaffName());
				reg.setOrgNo(userProFile.getOrgNo());
				reg.setBankNo(singleRepayBo.getBankNo());
				reg.setAcctNum(singleRepayBo.getAcctNo());
				reg.setAcctName(singleRepayBo.getAcctName());
				reg.setLoanNo(loanNo);
				reg.setTranAmt(singleRepayBo.getCurRcvAmt());
				reg.setRcvAmt(singleRepayBo.getCurRcvAmt());
				reg.setRepayDate(singleRepayBo.getRepayDate());
				reg.setTranBgnDate(new Date());
				reg.setTranTyp(PubBusinessConstant.REPAY_TYPE_NOM);
				reg.setTranChan(tranChan);
				String retCode = singleDkResultBo.getRetCode();
				// 处理成功的单笔代扣 0000
				if (FinanceConstant.TRAN_ST_SUCC.equals(retCode)) {
					if (singleDkResultBo.getRetItem() == null || (singleDkResultBo.getRetItem() != null
							&& !singleDkResultBo.getRetItem().getRetCode().equals(FinanceConstant.TRAN_ST_SUCC))) {
						// 如果交易成功，但扣款没成功，依然需要解锁
						isUnlock = true;
					} else {
						reg.setTxsn(singleDkResultBo.getReqSn());
						reg.setWithStat(FinanceConstant.WITHSTAT_DKSUCC);
						reg.setTranEndDate(new Date());
						reg.setSettleStat("10001002");
						accWithholdRegService.insertAccWithholdReg(reg);
						// 写入还款流水 处理结清 解锁表
						accRepayFlowService.saveRepayFlow(singleRepayBo, singleDkVo, singleDkResultBo, userProFile);
						// 单笔代扣后，业务处理
						if (singleRepayBo.getChanNo() != null && singleRepayBo.getChanNo().equals("006")) {
							withStat = FinanceConstant.WITHSTAT_DKXTSUCC;// 信托代扣成功
						} else if (singleRepayBo.getChanNo() != null && singleRepayBo.getChanNo().equals("999")) {
							withStat = FinanceConstant.WITHSTAT_DKPTSUCC;// 平台代扣成功
						} else {
							withStat = FinanceConstant.WITHSTAT_DKSUCC;// 正常代扣成功
						}
					}
					// 2000
				} else if (FinanceConstant.TRAN_ST_DEALING.equals(retCode)) {
					// 单笔代扣 处理中的情况不解锁，代扣中，由后台数据人员手工维护单笔代扣数据
					isUnlock = false;
					withStat = FinanceConstant.WITHSTAT_DKING;
					reg.setTxsn(singleDkResultBo.getReqSn());
					reg.setWithStat(FinanceConstant.WITHSTAT_DKING);
					reg.setSettleStat("10001002");
					accWithholdRegService.insertAccWithholdReg(reg);
				} else {
					reg.setTxsn(singleDkResultBo.getReqSn());
					reg.setWithStat(FinanceConstant.WITHSTAT_DKFIELD);
					reg.setTranEndDate(new Date());
					reg.setSettleStat("10001002");
					accWithholdRegService.insertAccWithholdReg(reg);
					// 其他情况扣款失败依然需要解锁
					throw new ServiceException(singleDkResultBo.getRetCode() + "|" + singleDkResultBo.getErrMsg());
				}
			} else {
				isUnlock = true;
			}
		} catch (Exception e) {
			isUnlock = true;// 如果没有异常则由写流水方法解锁 如果有异常则由finally解锁
			throw new ServiceException(e.getMessage());
		} finally {
			if (isUnlock)
				accLoanAcctInstService.updateAccLoanAcctStatusUnLock(loanNo, singleRepayBo.getRepayNum());

			// 交易完成更新代扣表 有代扣操作就更新代扣状态
			Map<String, Object> capWithRegMap = new HashMap<>();
			capWithRegMap.put("id", singleRepayBo.getId());
			capWithRegMap.put("updt", new Date());
			capWithRegMap.put("withStat", withStat);
			accCapWithMapper.updateByDkReturn(capWithRegMap);
		}
		return singleDkResultBo;
	}

	/**
	 * 判断该条贷款编号所扣期数 之前是否有未还款记录
	 * 
	 * @param loanNo
	 * @param repayNum
	 * @return
	 */
	public List<AccCapWith> queryWithNStat(String loanNo, Integer repayNum) {
		// TODO Auto-generated method stub
		Map<String, Object> withMap = new HashMap<>();
		withMap.put("loanNo", loanNo);
		withMap.put("repayNum", repayNum);
		return accCapWithMapper.queryWithNStat(withMap);
	}

	/**
	 * 根据代扣id 和状态更新代扣纪录 批量更新代扣状态方法已移动到预处理中两个方法合并执行解决事务问题 此方法废除
	 * 
	 * @param id
	 * @param stat
	 * @return
	 */
	@Deprecated
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Integer batchUpdateAccCapWithByBatckDk(Map<String, Object> params) {
		return accCapWithMapper.batchUpdateAccCapWithByBatckDk(params);
	}

	/**
	 * 实时批量代扣
	 * 
	 * @param params
	 * @param path
	 * @param operationType
	 *            操作类型：手工操作/调度平台
	 * @param acctFlag
	 *            交易方：资方/平台
	 * @return
	 * @throws ServiceException
	 */
	@Transactional
	public BatchDkResultBo executeBatchRepay(Map<String, Object> params) throws ServiceException {
		if (!params.containsKey("withStat")) {
			throw new ServiceException("批量代扣时还款状态必须选择还款状态");
		} else if (CompAmtUtil.checkDKWithStat(String.valueOf(params.get("withStat")))) {
			throw new ServiceException("批量代扣当前代扣状态不允许扣款");
		} else if (params.get("exportTxtType") == null || "".equals(params.get("exportTxtType"))) {
			throw new ServiceException("扣款渠道不允许为空");
		}
		// 扣款渠道代码
		String exportTxtType = params.get("exportTxtType").toString();
		BatchDkResultBo batchDkResultVo = null;
		// 取得可扣数据集合
		List<SingleRepayBo> repayDkDetailList = querySingleRepayBoList(params);
		if (repayDkDetailList.size() == 0) {
			throw new ServiceException("当前条件无数据可扣款");
		} else {
			if (repayDkDetailList.size() > 1000) {
				throw new ServiceException("中金批扣一次性最多只接受1000条扣款");
			}
			int succ = 0;
			int error = 0;
			for (SingleRepayBo bo : repayDkDetailList) {
				// List<AccCapWith> listWith =
				// this.queryWithNStat(bo.getLoanNo(),
				// bo.getRepayNum());
				// if (listWith != null && listWith.size() > 0) {
				// throw new ServiceException(
				// "该笔贷款[" + bo.getLoanNo() + "]在第[" + bo.getLoanNo() +
				// "]期之前还有未还款的记录,请按还款日期依次还款");
				// }
				if ("006".equals(params.get("chanNo")) && params.get("errorFlg") == null) {
					// 资方批扣和平台批扣判断是否逾期
					String repayDate = bo.getRepayDate();
					String nowDate = DateUtil.getDay("yyyyMMdd");
					if (!repayDate.equals(nowDate)) {
						error++;
					} else {
						succ++;
					}
				}
			}
			if (error != 0) {
				batchDkResultVo = new BatchDkResultBo();
				batchDkResultVo.setRetCode("4444");
				batchDkResultVo.setTrxCode("4444");
				batchDkResultVo.setErrMsg("该批次代扣记录中有[" + error + "]条逾期数据[" + succ + "]条正常扣款数据");
				return batchDkResultVo;
			}
		}
		Boolean isUnlock = false;
		try {
			// 变更预处理和代扣表状态 withStat instStat
			params.put("updt", new Date());
			params.put("instStat", FinanceConstant.PRETREAT_STAT_LOCK);
			params.put("updtDate", new Date());
			params.put("withStatForUpdate", FinanceConstant.WITHSTAT_DKING);
			Integer[] updateDKINSTCount = accLoanAcctInstService.batchUpdateAccLoanAcctInstByBatckDk(params);
			if (!(updateDKINSTCount[0].intValue() == updateDKINSTCount[1].intValue()
					&& repayDkDetailList.size() == updateDKINSTCount[1].intValue())) {
				throw new ServiceException("批量扣款［锁表预处理｜代扣表状态］失败,表更新条数与需要发起代扣的条数不一致");
			}
			String chalCode = params != null && params.get("chanNo") != null ? params.get("chanNo").toString() : "";

			// 获取批量代扣信息
			BatchDkBo batchDkVo = getBatchDkVo(repayDkDetailList, chalCode, exportTxtType);
			// 发起实时批量贷款
			String result = "";
			// 如果返回码为0000，则先修改代扣表数据状态
			String yys = "";
			if (PayChanType.ALLINPAY.toString().equals(exportTxtType)) {// 通联
				yys = "通联";
				result = HttpInvokerUtil.postJson(ParamUtils.getParam(FinanceConstant.tl_batch_repay_url),
						JSONObject.toJSONString(batchDkVo));
			} else if (PayChanType.CHINAPAY.toString().equals(exportTxtType)) { // 银联
				yys = "银联";
				result = HttpInvokerUtil.postJson(ParamUtils.getParam(FinanceConstant.batch_repay_url),
						JSONObject.toJSONString(batchDkVo));
			} else if (PayChanType.ZJPAY.toString().equals(exportTxtType)) { // 中金
				yys = "中金";
				if ("006".equals(params.get("batchChanNo"))) {
					result = HttpInvokerUtil.postJson(ParamUtils.getParam(FinanceConstant.zj_zf_batch_repay_url),
							JSONObject.toJSONString(batchDkVo));
				} else {
					result = HttpInvokerUtil.postJson(ParamUtils.getParam(FinanceConstant.zj_batch_repay_url),
							JSONObject.toJSONString(batchDkVo));
				}
				if (result != null && result.length() > 0) {
					JSONObject js = JSONObject.parseObject(result);
					result = js.get("batchDkResultVo").toString();
				}
			} else if (PayChanType.LIANLIANPAY.toString().equals(exportTxtType)) { // 连连支付
				yys = "连连支付";
				result = HttpInvokerUtil.postJson(ParamUtils.getParam(FinanceConstant.lianlian_batch_repay_url),
						JSONObject.toJSONString(batchDkVo));
				if (result != null && result.length() > 0) {
					JSONObject js = JSONObject.parseObject(result);
					result = js.get("batchDkResultVo").toString();
				}
			} else {
				throw new ServiceException("此扣款通道暂未开通");
			}
			batchDkResultVo = JSONObject.parseObject(result, BatchDkResultBo.class);
			String retCode = null;
			if (null != batchDkResultVo) {
				retCode = batchDkResultVo.getRetCode();
				if (!FinanceConstant.TRAN_ST_SUCC.equals(retCode))
					throw new ServiceException(batchDkResultVo.getRetCode() + "|" + batchDkResultVo.getErrMsg());
			} else {
				throw new ServiceException(yys + "服务端调用失败");
			}
		} catch (Exception e) {
			isUnlock = true;
			throw new ServiceException(e.getMessage());
		} finally {
			if (isUnlock) {
				params.put("withStat", FinanceConstant.WITHSTAT_DKING);
				params.put("updt", new Date());
				params.put("instStat", FinanceConstant.PRETREAT_STAT_UNLOCK);
				params.put("updtDate", new Date());
				params.put("withStatForUpdate", FinanceConstant.WITHSTAT_DKFIELD);
				Integer[] updateDKINSTCount = accLoanAcctInstService.batchUpdateAccLoanAcctInstByBatckDk(params);
				if (!(updateDKINSTCount[0].intValue() == updateDKINSTCount[1].intValue()
						&& repayDkDetailList.size() == updateDKINSTCount[1].intValue())) {
					throw new ServiceException("批量扣款回滚［解锁预处理｜代扣表状态更新］失败,表更新条数与需要回滚数据的条数不一致");
				}
			}
		}
		return batchDkResultVo;

	}

	/**
	 * 批量代扣文件导出
	 * 
	 * @param params
	 * @param path
	 * @param operationType
	 *            操作类型：手工操作/调度平台
	 * @param acctFlag
	 *            交易方：资方/平台
	 * @return
	 * @throws ServiceException
	 */
	@Transactional
	public String executeBatchDkFileExport(Map<String, Object> params) throws ServiceException {

		if (!params.containsKey("withStat")) {
			throw new ServiceException("必须选择还款状态");
		} else if (CompAmtUtil.checkDKWithStat(String.valueOf(params.get("withStat")))) {
			throw new ServiceException("当前代扣状态不允许扣款");
		}
		// 取得可扣数据集合
		List<SingleRepayBo> repayDkDetailList = querySingleRepayBoListOnKFT(params);
		if (repayDkDetailList.size() == 0) {
			throw new ServiceException("当前条件无数据可扣款");
		}

		Boolean isUnlock = false;
		String fileUrl = null;
		String kftBatchNo = "";
		try {
			// 变更预处理和代扣表状态 withStat instStat
			params.put("updt", new Date());
			params.put("instStat", FinanceConstant.PRETREAT_STAT_LOCK);
			params.put("updtDate", new Date());
			params.put("withStatForUpdate", FinanceConstant.WITHSTAT_DKING);
			Integer[] updateDKINSTCount = accLoanAcctInstService.batchUpdateAccLoanAcctInstByBatckDk(params);
			if (!(updateDKINSTCount[0].intValue() == updateDKINSTCount[1].intValue()
					&& repayDkDetailList.size() == updateDKINSTCount[1].intValue())) {
				throw new ServiceException("批量扣款［锁表预处理｜代扣表状态］失败,表更新条数与需要发起代扣的条数不一致");
			}
			// 渠道代码
			String chalCode = params != null && !StringUtils.isEmpty(params.get("channelType"))
					? params.get("channelType").toString() : "";

			Object[] obj = null;
			String exportTxtType = String.valueOf(params.get("exportTxtType"));
			if (com.hs.utils.StringUtils.isNotEmpty(exportTxtType)) {
				if (exportTxtType.equals(PayChanType.ALLINPAY.toString())) {
					throw new ServiceException("此渠道不支持间连扣款");
					// obj = EBPPUtil.expFileBeanByRepayDk(repayDkDetailList,
					// new
					// String[]{"#serialNum#","","#bankNo#","00","#acctNo#","#acctName#","","","","0","#curRcvAmt#","CNY","","","0","#certNo#","","","#id#","","#loanNo#"},
					// ",", getBatchNo(PayChanType.ALLINPAY.toString()));
				} else if (exportTxtType.equals(PayChanType.LYCHPAY.toString())) {
					kftBatchNo = getBatchNo(PayChanType.LYCHPAY.toString());
					obj = EBPPUtil.expKFTFileByRepayDk(kftBatchNo, repayDkDetailList);
				} else if (exportTxtType.equals(PayChanType.ZJPAY.toString())) {
					// 中金支付
					throw new ServiceException("此渠道不支持间连扣款");
				} else if (exportTxtType.equals(PayChanType.CHINAPAY.toString())) {
					// 银联
					throw new ServiceException("此渠道不支持间连扣款");
				} else if (exportTxtType.equals(PayChanType.RYPAY.toString())) {
					// 瑞银
					obj = EBPPUtil.expRyFileByRepayDk(getBatchNo(PayChanType.RYPAY.toString()), repayDkDetailList);
				} else {
					throw new ServiceException("不存在的扣款渠道");
				}

			} else {
				throw new ServiceException("扣款渠道为空");
			}
			// 生成代扣文件 上次服务器返回下载连接给前台，如果单批次超过1000 则分开生成后打包为一个文件
			Attachment m = this.uploadContract((InputStream) obj[1], String.valueOf(obj[0]), exportTxtType);
			fileUrl = OssUtil.getPresignedUrl(m.getId());
		} catch (Exception e) {
			isUnlock = true;
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} finally {
			if (isUnlock) {
				params.put("withStat", FinanceConstant.WITHSTAT_DKING);
				params.put("updt", new Date());
				params.put("instStat", FinanceConstant.PRETREAT_STAT_UNLOCK);
				params.put("updtDate", new Date());
				params.put("withStatForUpdate", FinanceConstant.WITHSTAT_DKFIELD);
				Integer[] updateDKINSTCount = accLoanAcctInstService.batchUpdateAccLoanAcctInstByBatckDk(params);
				if (!(updateDKINSTCount[0].intValue() == updateDKINSTCount[1].intValue()
						&& repayDkDetailList.size() == updateDKINSTCount[1].intValue())) {
					throw new ServiceException("批量扣款回滚［解锁预处理｜代扣表状态更新］失败,表更新条数与需要回滚数据的条数不一致");
				}
			}
		}
		return fileUrl;

	}

	/**
	 * 批量代扣回盘文件导入
	 * 
	 * @param params
	 * @param path
	 * @param operationType
	 *            操作类型：手工操作/调度平台
	 * @param acctFlag
	 *            交易方：资方/平台
	 * @return
	 * @throws ServiceException
	 */
	@Transactional
	public void executeBatchDkFileImport(String fileName, List<RetItemBo> lst, UserProfile user)
			throws ServiceException {
		// 是否需要删除本批次已插入的银联回盘明细纪录
		Boolean isDelTranLogDtl = false;
		Boolean isDelTranLogDtl_ = false;
		String sysId = ""; // syslib id
		try {
			if (lst.size() > 0) {
				// 查询是否有导出这个文件
				AttachmentApi sysAttachServer = SpringContextHolder.getBean("sysAttachServer");
				Map<String, Object> map = new HashMap<>();
				if (!com.hs.utils.StringUtils.isBlank(fileName)&&fileName.length()<20) {
					fileName = fileName.split("\\.")[0]+".txt";
				}
				map.put("originalName", fileName);// 上传的文件名
				List<Attachment> attLst = sysAttachServer.queryForList(map);
				String exportType = "";
				if (attLst.size() > 0) {
					sysId = attLst.get(0).getId();
					if (attLst.get(0).getBusiType().equals(PayChanType.LYCHPAY.toString())) {
						exportType = PubBusinessConstant.KFT_BRANCH;
					} else if (attLst.get(0).getBusiType().equals(PayChanType.RYPAY.toString())) {
						exportType = PubBusinessConstant.RY_BRANCH;
					}
				} else {
					throw new ServiceException("未查询到该批次导出文件");
				}
				// 根据返回内容组装批量交易明细
				List<AccCapTranDtl> actdLst = new ArrayList<AccCapTranDtl>();
				for (RetItemBo rd : lst) {
					if (org.apache.commons.lang3.StringUtils.isEmpty(rd.getReserve1())) {
						throw new ServiceException("返回文件缺失关键信息");
					}
					if (org.apache.commons.lang3.StringUtils.isEmpty(rd.getRetCode())) {
						throw new ServiceException("返回文件缺失代扣结果");
					}
					AccCapTranDtl dt = new AccCapTranDtl();
					dt.setAcctNo(rd.getAccountNo());
					dt.setAcctName(rd.getAccountName());
					dt.setWithDate(new Date());
					dt.setLogId(sysId);
					dt.setBatFlag("YES");
					dt.setRemark(rd.getRemark());
					dt.setRetStat(rd.getRetCode());
					dt.setRetMsg(rd.getErrMsg() == null ? KftUtil.getResultStr(rd.getRetCode()) : rd.getErrMsg());
					dt.setId(RandomUtil.getUUID());
					dt.setWithId(rd.getReserve1());
					dt.setShdWithAmt(new BigDecimal(rd.getAmount()));
					dt.setActWithAmt(new BigDecimal(rd.getAmount()));
					dt.setLoanNo(rd.getRemark());
					actdLst.add(dt);
				}
				// 根据返回内容组装批量交易明细 end
				Map<String, Object> _param = new HashMap<String, Object>();
				_param.put("logId", sysId);

				// 回盘前先检查改批次是否已经全部回盘，防止重复写还款流水
				if (accCapTranDtlService.queryForList(_param).size() == actdLst.size()) {
					throw new ServiceException("该笔交易已全部回盘［" + sysId + "］");
				} else {
					isDelTranLogDtl_ = true;
				}

				// 如果没有全部回盘说明上一次回盘失败 先清理一遍再插入 删除已保存的交易明细数据
				accCapTranDtlService.deleteByLogIdKey(_param);// 实时事务
				// 批量插入银联交易日志明细纪录 实时事务
				Integer _i = accCapTranDtlService.batckInsertAccCapTranDtl(actdLst);

				// 需要保存还款流水的数据集合（用于解决重复发起回盘查询 多次写入还款流水的问题）
				List<BatchRepayCallBackBo> list = accCapTranDtlService.queryBatchRepayDkFile(sysId);// 该批次交易回盘总数据
				List<BatchRepayCallBackBo> flowlist = new ArrayList<BatchRepayCallBackBo>();// 交易成功的纪录
																							// 需要写流水
				// 所有的dkid
				StringBuffer allDkIdStr = new StringBuffer();
				// 成功dkid
				StringBuffer succDkIdStr = new StringBuffer();
				// 失败dkid
				StringBuffer fieldDkIdStr = new StringBuffer();
				BatchRepayCallBackBo _vo = null;
				for (int i = 0; i < list.size(); i++) {
					_vo = list.get(i);
					if (null == _vo) {
						continue;
					}
					allDkIdStr.append("'" + _vo.getDkId() + "',");
					if (FinanceConstant.KFT_TRAN_ST_SUCC.equals(_vo.getRetCode())) {
						// 交易成功的情况需要纪录还款流水
						succDkIdStr.append("'" + _vo.getDkId() + "',");
						flowlist.add(_vo);
					} else {
						fieldDkIdStr.append("'" + _vo.getDkId() + "',");
					}
				}
				List<AccRepayFlow> listFlow = new ArrayList<AccRepayFlow>();
				for (BatchRepayCallBackBo vo : flowlist) {
					AccRepayFlow flowVO = new AccRepayFlow();
					flowVO.setId(RandomUtil.getUUID());
					flowVO.setTranOrg(user.getOrgNo());
					flowVO.setTranStaff(user.getStaffName());
					// 贷款编号
					flowVO.setLoanNo(vo.getLoanNo());
					// 账单日
					flowVO.setRepayDate(vo.getRepayDate());
					// 期数
					flowVO.setRepayNum(vo.getRepayNum());
					// 交易日期
					flowVO.setTranDate(DateUtil.formatDate(new Date(), "yyyyMMdd"));
					// 交易类型
					flowVO.setTranType(PubBusinessConstant.TRANDTYPE_ZC);
					// 交易金额
					flowVO.setTranAmt(new BigDecimal(vo.getAmount()));
					// 是否结算：否
					flowVO.setSetlFlag(CommonConstant.COMMON_NO);
					// 创建日期
					flowVO.setInstDate(new Date());
					// 还款账户
					flowVO.setAcctNo(vo.getAcctNo());
					// 还款户名
					flowVO.setAcctName(vo.getAcctName());
					// 交易类型
					flowVO.setTranChan(exportType);
					// 交易方
					flowVO.setTranObj(vo.getTranObj());
					// 交易状态：当前日期与账单日比较，如果
					flowVO.setLoanStat(RepayUtil.getLoanStat(vo.getRepayDate()));
					listFlow.add(flowVO);
				}
				// 批量插入流水
				if (listFlow.size() > 0) {
					accRepayFlowService.batchInsertRepayFlow(listFlow);
				}

				/* 批量解锁 */
				Map<String, Object> _params = new HashMap<String, Object>();
				_params.put("instStat", FinanceConstant.PRETREAT_STAT_UNLOCK);
				_params.put("updt", new Date());
				_params.put("withIds", org.apache.commons.lang3.StringUtils.isNotBlank(allDkIdStr)
						? allDkIdStr.toString().substring(0, allDkIdStr.toString().length() - 1) : "");
				Integer accAll = accLoanAcctInstService.batchUpdateALAIStatByBatckDk(_params);// 状态全部解锁
				/* 失败的批量更新 */
				Integer dkFAll = 0;
				if (org.apache.commons.lang3.StringUtils.isNotBlank(fieldDkIdStr)) {
					Map<String, Object> capWithRegMapField = new HashMap<String, Object>();
					capWithRegMapField.put("updt", new Date());
					capWithRegMapField.put("withStat", FinanceConstant.WITHSTAT_DKFIELD);
					capWithRegMapField.put("withIds", org.apache.commons.lang3.StringUtils.isNotBlank(fieldDkIdStr)
							? fieldDkIdStr.toString().substring(0, fieldDkIdStr.toString().length() - 1) : "");
					dkFAll = accCapWithMapper.updateByDkReturn(capWithRegMapField);
				}

				/* 成功的批量更新 */
				Integer dkSAll = 0;
				if (org.apache.commons.lang3.StringUtils.isNotBlank(succDkIdStr)) {
					Map<String, Object> capWithRegMapSucc = new HashMap<String, Object>();
					capWithRegMapSucc.put("updt", new Date());
					capWithRegMapSucc.put("withStat", FinanceConstant.WITHSTAT_DKSUCC);
					capWithRegMapSucc.put("withIds", org.apache.commons.lang3.StringUtils.isNotBlank(succDkIdStr)
							? succDkIdStr.toString().substring(0, succDkIdStr.toString().length() - 1) : "");
					dkSAll = accCapWithMapper.updateByDkReturn(capWithRegMapSucc);

					/* 批量并更新预处理的当前要还金额 */
					Map<String, Object> _params_ = new HashMap<String, Object>();
					_params_.put("updt", new Date());
					_params_.put("withIds", org.apache.commons.lang3.StringUtils.isNotBlank(succDkIdStr)
							? succDkIdStr.toString().substring(0, succDkIdStr.toString().length() - 1) : "");
					Integer accInsAll = accLoanAcctInstService.batchUpdateCurAmtByBatckDk(_params_);
				}
				/************************ 以上动作没有实时提交事务 *************************/
				if (accAll.intValue() != (dkFAll.intValue() + dkSAll.intValue())) {
					throw new ServiceException("批量更新预处理与代扣纪录失败［更新预处理与更新代扣纪录条数不一致］");
				}

			} else {
				throw new ServiceException("回盘纪录为空");
			}
		} catch (Exception e) {
			if (isDelTranLogDtl_) {
				isDelTranLogDtl = true;// 如果上面的方法出错，需要删除日志明细等待下次回盘查询从新写入
			}
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} finally {
			// 日志一定要回滚删除，不然无法手动回盘
			if (isDelTranLogDtl && org.apache.commons.lang3.StringUtils.isNotEmpty(sysId)) {
				Map<String, Object> _param = new HashMap<String, Object>();
				_param.put("logId", sysId);
				Integer _i_ = accCapTranDtlService.deleteByLogIdKey(_param);
			}
		}

	}

	/**
	 * 批量代扣获取代扣数据，锁表，更新代扣（代扣中） 返回列表
	 * 
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	@Deprecated
	@Transactional
	public List<SingleRepayBo> getRepayDkBoList(Map<String, Object> params) throws ServiceException {

		// 按照页面条件获取需要批扣的数据
		List<SingleRepayBo> list = querySingleRepayBoList(params);
		if (null == list || list.size() == 0) {
			throw new ServiceException("没有数据，请先查询");
		}
		List<SingleRepayBo> relist = new java.util.ArrayList<SingleRepayBo>();
		SingleRepayBo srb = null;
		for (int i = 0; i < list.size(); i++) {
			srb = list.get(i);
			if (null != srb) {
				String loanNo = srb.getLoanNo();
				Integer repayNum = srb.getRepayNum();
				// 获取交易金额
				BigDecimal transBal = CompAmtUtil.getDkTransBal(loanNo, repayNum, "", FinanceConstant.ACCTFLAG_PT, "");
				if (transBal.compareTo(BigDecimal.ZERO) == 0) {
					continue;
				}
				srb.setCurRcvAmt(transBal);
				String returnStatus = accLoanAcctInstService.updateAccLoanAcctStatusLock(loanNo, repayNum);
				if (!returnStatus.equals(FinanceConstant.SUCC)) {
					throw new ServiceException("代扣数据［预处理信息ACC_LOAN_ACCT_INST］锁表失败");
				}
				// 更新代扣表状态为代扣中 此时预处理表不解锁 等待银联处理结果后更新预处理状态
				// 交易完成更新代扣表
				Map<String, Object> capWithRegMap = new HashMap<>();
				capWithRegMap.put("id", srb.getId());
				capWithRegMap.put("updtDate", new Date());
				capWithRegMap.put("withStat", FinanceConstant.WITHSTAT_DKING);// 扣款中
				Integer count = updateAccCapWithStatById(capWithRegMap);
				if (count < 1) {
					throw new ServiceException("更新代扣数据失败［id:" + srb.getId() + "］");
				}
				relist.add(srb);
			}
		}
		if (null == relist || relist.size() == 0) {
			throw new ServiceException("没有数据，请先查询");
		}
		return relist;
	}

	/**
	 * 获取批量代扣信息
	 * 
	 * @return
	 * @throws ServiceException
	 */
	private BatchDkBo getBatchDkVo(List<SingleRepayBo> list, String chalCode, String exportTxtType)
			throws ServiceException {
		BatchDkBo batchDkVo = new BatchDkBo();
		// 设置商户银联信息
		MerInfo merInfo = RepayUtil.getMerInfo(exportTxtType);
		batchDkVo.setMerInfo(merInfo);
		// 交易总记录数
		int totalItem = 0;
		// 交易总金额
		BigDecimal totalSum = BigDecimal.ZERO;
		// 设置交易明细
		List<TransItem> transItemLst = new ArrayList<TransItem>();
		SingleRepayBo detailVO = null;
		for (int i = 0; i < list.size(); i++) {
			detailVO = list.get(i);
			if (null != detailVO) {
				BigDecimal transBal = detailVO.getCurRcvAmt();
				if ("006".equals(chalCode)) { // 信托批扣取资方金额
					String repayDate = detailVO.getRepayDate();
					String nowDate = DateUtil.getDay("yyyyMMdd");
					if (repayDate.equals(nowDate)) { // 逾期 的扣款数据排除
						transBal = detailVO.getFundCurRcvAmt();
						// 设置交易明细
						detailVO.setChanNo(chalCode);
						transItemLst.add(RepayUtil.getTransItem(transBal, detailVO));
						totalItem += 1;
						totalSum = totalSum.add(transBal);
					}
				} else {
					// 设置交易明细
					detailVO.setChanNo(chalCode);
					transItemLst.add(RepayUtil.getTransItem(transBal, detailVO));
					totalItem += 1;
					totalSum = totalSum.add(transBal);
				}
			}
		}
		batchDkVo.setTransItemLst(transItemLst);
		// 设置交易摘要
		TransSum transSum = RepayUtil.getTransSum(String.valueOf(totalItem), totalSum, chalCode);
		batchDkVo.setTransSum(transSum);
		// 设置批量代扣回盘回调信息
		// batchDkVo.setCallBackInfo(RepayUtil.getCallBackInfo());
		return batchDkVo;
	}

	/**
	 * 根据代扣id 和状态更新代扣纪录
	 * 
	 * @param id
	 * @param stat
	 * @return
	 */
	@Transactional
	public boolean updateAccCapWithByStatByIdAndStat(String id, String stat) {
		Map<String, Object> capWithRegMap = new HashMap<>();
		capWithRegMap.put("id", id);
		capWithRegMap.put("updt", new Date());
		capWithRegMap.put("withStat", stat);
		Integer i = accCapWithMapper.updateByDkReturn(capWithRegMap);
		return i != null && i > 0 ? true : false;
	}

	/**
	 * 实时批量代扣 回调
	 * 
	 * @param reqSN
	 *            批次流水号
	 * @return
	 * @throws ServiceException
	 */
	@Deprecated // 不再使用回调处理批量代扣
	@Transactional
	public void executeBatchRepayCallback(String reqSN) throws ServiceException {
		// 需要保存还款流水的数据集合（用于解决重复发起回盘查询 多次写入还款流水的问题）
		List<BatchRepayCallBackBo> flowlist = new ArrayList<BatchRepayCallBackBo>();
		// 查询实时批量代扣的回盘信息
		List<BatchRepayCallBackBo> list = accCapTranDtlService.queryBatchRepayCallBackInfo(reqSN);
		StringBuffer allDkIdStr = new StringBuffer();
		StringBuffer succDkIdStr = new StringBuffer();
		StringBuffer fieldDkIdStr = new StringBuffer();
		if (null != list && list.size() > 0) {
			BatchRepayCallBackBo _vo = null;
			for (int i = 0; i < list.size(); i++) {
				_vo = list.get(i);
				if (null == _vo) {
					continue;
				}
				allDkIdStr.append("'" + _vo.getDkId() + "',");
				if (FinanceConstant.TRAN_ST_SUCC.equals(_vo.getRetCode())) {// 交易成功的情况
					succDkIdStr.append("'" + _vo.getDkId() + "',");
					flowlist.add(_vo);// 存流水的时候才减掉当日应还
				} else {
					fieldDkIdStr.append("'" + _vo.getDkId() + "',");
				}
			}
			List<AccRepayFlow> listFlow = new ArrayList<>();
			for (BatchRepayCallBackBo vo : flowlist) {
				AccRepayFlow flowVO = new AccRepayFlow();
				flowVO.setId(RandomUtil.getUUID());
				// 贷款编号
				flowVO.setLoanNo(vo.getLoanNo());
				// 账单日
				flowVO.setRepayDate(vo.getRepayDate());
				// 期数
				flowVO.setRepayNum(vo.getRepayNum());
				// 交易日期
				flowVO.setTranDate(DateUtil.getDay("yyyyMMdd"));
				// 交易类型
				flowVO.setTranType(PubBusinessConstant.TRANDTYPE_ZC);
				// 交易金额
				flowVO.setTranAmt(new BigDecimal(vo.getAmount()));
				// 是否结算：否
				flowVO.setSetlFlag(CommonConstant.COMMON_NO);
				// 创建日期
				flowVO.setInstDate(new Date());
				// 还款账户
				flowVO.setAcctNo(vo.getAcctNo());
				// 还款户名
				flowVO.setAcctName(vo.getAcctName());
				// 交易类型
				flowVO.setTranChan(PubBusinessConstant.TRANCHAN_BRANCH);
				// 交易行号
				flowVO.setTranOrg(vo.getBankNo());

				flowVO.setTranStaff("ETL");
				// 交易状态：当前日期与账单日比较，如果
				flowVO.setLoanStat(RepayUtil.getLoanStat(vo.getRepayDate()));
				listFlow.add(flowVO);
			}
			// 批量插入流水
			Integer row = accRepayFlowService.batchInsertRepayFlow(listFlow);
			if (row.intValue() != listFlow.size()) {
				throw new ServiceException("回盘数据批量插入流水失败［插入条数与回盘扣款成功条数不一致］");
			}
			/* 批量解锁 并更新预处理的当前要还金额 */
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("instStat", FinanceConstant.PRETREAT_STAT_UNLOCK);
			params.put("updt", new Date());
			params.put("withIds", org.apache.commons.lang3.StringUtils.isNotBlank(allDkIdStr)
					? allDkIdStr.toString().substring(0, allDkIdStr.toString().length() - 1) : "");
			Integer accAll = accLoanAcctInstService.batchUpdateALAIStatAndCurAmtByBatckDk(params);
			/* 失败的批量更新 */
			Map<String, Object> capWithRegMapField = new HashMap<>();
			capWithRegMapField.put("updt", new Date());
			capWithRegMapField.put("withStat", FinanceConstant.WITHSTAT_DKFIELD);
			capWithRegMapField.put("withIds", org.apache.commons.lang3.StringUtils.isNotBlank(fieldDkIdStr)
					? fieldDkIdStr.toString().substring(0, fieldDkIdStr.toString().length() - 1) : "");
			Integer dkFAll = accCapWithMapper.updateByDkReturn(capWithRegMapField);
			/* 成功的批量更新 */
			Map<String, Object> capWithRegMapSucc = new HashMap<>();
			capWithRegMapSucc.put("updt", new Date());
			capWithRegMapSucc.put("withStat", FinanceConstant.WITHSTAT_DKSUCC);
			capWithRegMapSucc.put("withIds", org.apache.commons.lang3.StringUtils.isNotBlank(succDkIdStr)
					? succDkIdStr.toString().substring(0, succDkIdStr.toString().length() - 1) : "");
			Integer dkSAll = accCapWithMapper.updateByDkReturn(capWithRegMapSucc);
			if (accAll.intValue() != (dkFAll.intValue() + dkSAll.intValue())) {
				throw new ServiceException("批量更新预处理与代扣纪录失败［预处理与代扣纪录更新条数不一致］");
			}

		} else {
			throw new ServiceException("银联文件名：［" + reqSN + "]在交易明细中未查询到纪录");
		}
	}

	/**
	 * 保存还款流水
	 * 
	 * @param callBackVO
	 *            //实时批量代扣的回盘信息
	 * @throws ServiceException
	 */
	private String saveRepayFlow(BatchRepayCallBackBo callBackVO) throws ServiceException {
		try {
			AccRepayFlow flowVO = new AccRepayFlow();
			// 贷款编号
			flowVO.setLoanNo(callBackVO.getLoanNo());
			// 账单日
			flowVO.setRepayDate(callBackVO.getRepayDate());
			// 期数
			flowVO.setRepayNum(callBackVO.getRepayNum());
			// 交易日期
			flowVO.setTranDate(DateUtil.getDay("yyyyMMdd"));
			// 交易类型
			flowVO.setTranType(PubBusinessConstant.TRANDTYPE_ZC);
			// 交易金额
			flowVO.setTranAmt(new BigDecimal(callBackVO.getAmount()));
			// 是否结算：否
			flowVO.setSetlFlag(CommonConstant.COMMON_NO);
			// 创建日期
			flowVO.setInstDate(new Date());
			// 还款账户
			flowVO.setAcctNo(callBackVO.getAcctNo());
			// 还款户名
			flowVO.setAcctName(callBackVO.getAcctName());
			// 交易类型
			flowVO.setTranChan(PubBusinessConstant.TRANCHAN_BRANCH);
			// 交易行号
			flowVO.setTranOrg(callBackVO.getBankNo());

			flowVO.setTranStaff("ETL");
			// 交易状态：当前日期与账单日比较，如果
			flowVO.setLoanStat(RepayUtil.getLoanStat(callBackVO.getRepayDate()));
			// 交易是否成功
			boolean isSucc = false;
			if (FinanceConstant.TRAN_ST_SUCC.equals(callBackVO.getRetCode())) {
				isSucc = true;
			}
			String saveRepayFlow = accRepayFlowService.saveRepayFlow(flowVO, isSucc, false);
			if (!FinanceConstant.SUCC.equals(saveRepayFlow)) {
				throw new ServiceException(saveRepayFlow);
			}
			return saveRepayFlow;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统错误<br/>" + e.getMessage());
		}
	}

	/**
	 * 手动抓取批量代扣回盘信息 如果批量处理已处理完成 则由银联服务负责解锁，写流水表，更改代扣状态
	 * 
	 * @param reqSN
	 *            批次流水号
	 * @param chalCode
	 *            渠道代码
	 * @param acctFlag
	 *            交易方
	 * @param exportTxtType
	 *            扣款渠道
	 * @return
	 * @throws ServiceException
	 */

	public QueryResultBo queryBatchRepay(String reqSN, String chalCode) throws ServiceException {
		QueryBo queryVo = new QueryBo();
		// 取得商户信息
		MerInfo merInfo = RepayUtil.getMerInfo(chalCode);
		// 银联批扣查询摘要
		QuerySum querySum = RepayUtil.getQuerySum(reqSN, null, chalCode, null);
		// 批量代扣回盘回调信息
		// CallBackInfo callBackInfo = RepayUtil.getCallBackInfo();
		queryVo.setMerInfo(merInfo);
		queryVo.setQuerySum(querySum);
		// queryVo.setCallBackInfo(callBackInfo);
		// 发起实时批量贷款
		String result = HttpInvokerUtil.postJson(ParamUtils.getParam("query_repay_url"),
				JSONObject.toJSONString(queryVo));
		QueryResultBo resultVo = JSONObject.parseObject(result, QueryResultBo.class);
		// 如果返回码为0000，则先修改代扣表数据状态
		if (null != resultVo) {
			String retCode = resultVo.getRetCode();
			if (!FinanceConstant.TRAN_ST_SUCC.equals(retCode)) {
				throw new ServiceException("批量代扣结果查询发起失败！<br/>代码：" + retCode + "<br/>信息：" + resultVo.getErrMsg());
			}
		}
		return resultVo;
	}

	/**
	 * 新增
	 * 
	 * @param accCapWithReg
	 *            新增对象
	 */
	@Transactional
	public void insert(AccCapWith accCapWithReg) {
		accCapWithMapper.insert(accCapWithReg);
	}

	/**
	 * 通过主键修改
	 * 
	 * @param map
	 *            修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map) {
		accCapWithMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除
	 * 
	 * @param primaryKey
	 *            主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey) {
		accCapWithMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 对象
	 * 
	 * @param primaryKey
	 *            主键
	 * @return 对象
	 */
	public AccCapWith getByPrimaryKey(String primaryKey) {
		return accCapWithMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 列表
	 * 
	 * @param param
	 *            查询参数map
	 * @return List<T>列表
	 */
	public List<AccCapWith> queryForList(Map<String, Object> param) {
		return accCapWithMapper.queryForList(param);
	}

	@Transactional
	public Integer updateAccCapWithStatById(Map<String, Object> param) {
		return accCapWithMapper.updateAccCapWithStatById(param);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Integer updateAccCapWith(List<Map<String, Object>> list) {
		int i = 0;
		for (Map<String, Object> param : list) {
			accCapWithMapper.updateAccCapWithStatById(param);
			i++;
		}
		return i;
	}

	/**
	 * 查询 分页列表
	 * 
	 * @param page
	 *            查询参数page
	 * @return List<T>列表
	 */
	public Page<AccCapWith> queryForPage(Page<AccCapWith> page) {
		Map<String, Object> params = page.getPageParams();
		if ("1".equals(params.get("dkType"))) {
			params.put("ovduStatNo", "1");
			params.put("ovduStatYes", "");
		} else if ("2".equals(params.get("dkType"))) {
			params.put("ovduStatNo", "");
			params.put("ovduStatYes", "1");
		} else if ("3".equals(params.get("dkType"))) {
			params.put("ovduStatNo", "");
			params.put("ovduStatYes", "2");
		}
		if (PayChanType.LIANLIANPAY.toString().equals(params.get("exportTxtType"))) {
			params.put("lianlianpay", "1");
		}
		accCapWithMapper.queryForList(params);
		return (Page<AccCapWith>) page.getPageParams().get(Page.KEY);
	}

	/**
	 * 分页查询代扣纪录列表
	 * 
	 * @param params
	 * @return
	 */
	public Page<SingleRepayBo> querySingleRepayBoForList(Page<SingleRepayBo> page) {
		Map<String, Object> params = page.getPageParams();
		if ("1".equals(params.get("dkType"))) {
			params.put("ovduStatNo", "1");
			params.put("ovduStatYes", "");
		} else if ("2".equals(params.get("dkType"))) {
			params.put("ovduStatNo", "");
			params.put("ovduStatYes", "1");
		} else if ("3".equals(params.get("dkType"))) {
			params.put("ovduStatNo", "");
			params.put("ovduStatYes", "2");
		}
		if (PayChanType.LIANLIANPAY.toString().equals(params.get("exportTxtType"))) {
			params.put("lianlianpay", "1");
		}
		accCapWithMapper.querySingleRepayBoForList(params);
		return (Page<SingleRepayBo>) page.getPageParams().get(Page.KEY);
	}

	
	/**
	 * 批扣查询代扣纪录列表
	 * 
	 * @param params
	 * @return
	 */
	public List<SingleRepayBo> querySingleRepayBoListOnKFT(Map<String, Object> params) {
		if ("1".equals(params.get("dkType"))) {
			params.put("ovduStatNo", "1");
			params.put("ovduStatYes", "");
		} else if ("2".equals(params.get("dkType"))) {
			params.put("ovduStatNo", "");
			params.put("ovduStatYes", "1");
		} else if ("3".equals(params.get("dkType"))) {
			params.put("ovduStatNo", "");
			params.put("ovduStatYes", "2");
		}
		if (PayChanType.LIANLIANPAY.toString().equals(params.get("exportTxtType"))) {
			params.put("lianlianpay", "1");
		}
		return accCapWithMapper.querySingleRepayBoForListOnKFT(params);
	}
	
	/**
	 * 批扣查询代扣纪录列表
	 * 
	 * @param params
	 * @return
	 */
	public List<SingleRepayBo> querySingleRepayBoList(Map<String, Object> params) {
		if ("1".equals(params.get("dkType"))) {
			params.put("ovduStatNo", "1");
			params.put("ovduStatYes", "");
		} else if ("2".equals(params.get("dkType"))) {
			params.put("ovduStatNo", "");
			params.put("ovduStatYes", "1");
		} else if ("3".equals(params.get("dkType"))) {
			params.put("ovduStatNo", "");
			params.put("ovduStatYes", "2");
		}
		if (PayChanType.LIANLIANPAY.toString().equals(params.get("exportTxtType"))) {
			params.put("lianlianpay", "1");
		}
		return accCapWithMapper.querySingleRepayBoForList(params);
	}

	/**
	 * 贷款编号和期数更改代扣状态为扣款完成
	 * 
	 * @param loanNo
	 * @param repayNum
	 */
	@Transactional
	public void updateAccCapWithByStatByLoanNo(Map<String, Object> map) {
		accCapWithMapper.updateAccCapWithByStatByLoanNo(map);
	}

	public String getChanNoByMap(Map<String, Object> chanParam) {
		// TODO Auto-generated method stub
		return accCapWithMapper.getChanNoByMap(chanParam);
	}

	public SingleDkResultBo singleQueryStat(String tx) {
		SingleDkResultBo singleDkResultBo = null;
		try {
			StringBuilder sb = new StringBuilder("txSN=");
			sb.append(tx);
			String result = HttpInvokerUtil.sendPost(ParamUtils.getParam(FinanceConstant.zj_single_query_repay_url),
					sb.toString(), "UTF-8");
			if (result != null && result.length() > 0) {
				System.out.println("单扣轮询结果dankoulunxunjieguo===========" + result);
				JSONObject js = JSONObject.parseObject(result);
				result = js.get("singleResult").toString();
			}
			singleDkResultBo = JSONObject.parseObject(result, SingleDkResultBo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return singleDkResultBo;
	}

	public Map<String, Object> findTranLog(String loanNo, String repayDate) {
		Map<String, Object> map = new HashMap<>();
		map.put("loanNo", loanNo);
		map.put("repayDate", repayDate);
		return accCapWithMapper.findTranLog(map);
	}

	/**
	 * 验证扣款次数是否超限
	 * 
	 * @param capType
	 * @param loanNo
	 * @return
	 */
	public boolean capWithSize(int capType, String loanNo) {
		String deductionFailedMax = ParamUtils.getParam("deductionFailedMax");
		Integer deductionFailedNum = Integer.parseInt(deductionFailedMax) - 1;
		if (capType == 1) { // 中金单扣
			if (accCapWithMapper.queryCapSize(loanNo) > deductionFailedNum) {
				return true;
			}
		} else if (capType == 2) {// 快付通
			if (accCapWithMapper.queryPaySize(loanNo) > deductionFailedNum) {
				return true;
			}
		} else if (capType == 3) {
			if (accCapWithMapper.queryLianLianPayCapSize(loanNo) > deductionFailedNum) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 获取下一个快付通扣款编号
	 * @return
	 */
	public String getNextKftBatchNo(){
		return accCapWithMapper.getNextKftBatchNo();
	}
	/**
	 * 更新快付通扣款编号
	 * @param kftBatchNo
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=false)
	public void updateKftNo(String kftBatchNo){
		if (!com.hs.utils.StringUtils.isBlank(kftBatchNo)) {
			accCapWithMapper.updateKftNo(kftBatchNo);
		}
	}
}