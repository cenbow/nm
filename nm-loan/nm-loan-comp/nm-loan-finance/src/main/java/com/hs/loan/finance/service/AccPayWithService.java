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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
import com.hs.loan.finance.bo.BatchRepayDKFileBo;
import com.hs.loan.finance.bo.RetItemBo;
import com.hs.loan.finance.contant.FinanceConstant;
import com.hs.loan.finance.entity.AccCapTranDtl;
import com.hs.loan.finance.entity.AccCapWith;
import com.hs.loan.finance.entity.AccLoanAcctInst;
import com.hs.loan.finance.entity.AccPayWith;
import com.hs.loan.finance.entity.AccRepayAdvanReg;
import com.hs.loan.finance.entity.AccRepayFlow;
import com.hs.loan.finance.mapper.AccPayWithMapper;
import com.hs.loan.finance.util.CompAmtUtil;
import com.hs.loan.finance.util.DateUtil;
import com.hs.loan.finance.util.EBPPUtil;
import com.hs.loan.finance.util.KftUtil;
import com.hs.loan.finance.util.PayChanType;
import com.hs.loan.finance.util.RepayUtil;
import com.hs.utils.AmountUtils;
import com.hs.utils.DateUtils;
import com.hs.utils.ParamUtils;
import com.hs.utils.RandomUtil;

/**
 * ACC_三方支付代扣信息 业务处理
 * 
 * @author autocreate
 * @create 2016-04-20
 */
@Service
@Transactional(readOnly = true)
public class AccPayWithService {

	@Autowired
	private AccCapWithService accCapWithService;

	@Autowired
	private AccPayWithMapper accPayWithMapper;
	@Autowired
	private IFileTransfer fileTransfer;

	@Autowired
	private AccLoanAcctInstService accLoanAcctInstService;

	@Autowired
	private AccRepayFlowService accRepayFlowService;

	@Autowired
	private AccCapTranDtlService accCapTranDtlService;

	@Autowired
	private AccRepayAdvanRegService accRepayAdvanRegService;
	
	@Autowired
	private AccLoanInstService accLoanInstService;

	public Integer accPayReg(AccPayWith vo) throws ServiceException {
		if (StringUtils.isEmpty(vo.getId())) {
			throw new ServiceException("登记主键不能为空");
		}
		if (StringUtils.isEmpty(vo.getStat())) {
			throw new ServiceException("处理状态不能为空");
		}
		if (!vo.getStat().equals(FinanceConstant.WITHSTAT_UNDK)) {
			throw new ServiceException("登记处理状态错误");
		}
		if (StringUtils.isEmpty(vo.getLoanNo())) {
			throw new ServiceException("贷款编号不能为空");
		}
		if (StringUtils.isEmpty(vo.getAcctName())) {
			throw new ServiceException("扣款账户名不能为空");
		}
		if (StringUtils.isEmpty(vo.getAcctNo())) {
			throw new ServiceException("扣款账号不能为空");
		}
		if (StringUtils.isEmpty(vo.getOpenBank())) {
			throw new ServiceException("扣款账号开户行不能为空");
		}
		if (vo.getBgnRepayNum().intValue() == 0) {
			throw new ServiceException("开始期数为0");
		}
		if (vo.getEndRepayNum().intValue() == 0) {
			throw new ServiceException("结束期数为0");
		}
		if (vo.getCurRcvAmt().compareTo(BigDecimal.ZERO) == 0) {
			throw new ServiceException("扣款金额为0");
		}
		if (vo.getWithAmt().compareTo(BigDecimal.ZERO) == 0) {
			throw new ServiceException("实际要还款为0");
		}
		if (org.apache.commons.lang3.StringUtils.isEmpty(vo.getRepayType())) {
			throw new ServiceException("还款业务类型不能为空");
		}
		Map<String, Object> _mapIns = new HashMap<>();
		_mapIns.put("loanNo", vo.getLoanNo());
		_mapIns.put("instStat", FinanceConstant.PRETREAT_STAT_LOCK);
		// 如果是结清 查询目前锁定的条数是否与结清的期数一致
		// 还款总期数
		int repayNumTotal = vo.getEndRepayNum().intValue() - vo.getBgnRepayNum().intValue() + 1;
		if (!(accLoanAcctInstService.queryForList(_mapIns).size() == repayNumTotal)) {
			throw new ServiceException("代扣登记前请先锁定相应预处理数据");
		}
		// 代扣表除代扣成功以外的代扣中纪录
		for (int i = vo.getBgnRepayNum().intValue(); i <= vo.getEndRepayNum().intValue(); i++) {
			Map<String, Object> _mapWith = new HashMap<>();
			_mapWith.put("loanNo", vo.getLoanNo());
			_mapWith.put("repayNum", i);
			List<AccCapWith> acwLst = accCapWithService.queryForList(_mapWith);
			if (acwLst.size() > 0) {
				for (AccCapWith accCapWith : acwLst) {
					if (!accCapWith.getWithStat().equals(FinanceConstant.WITHSTAT_DKING)) {
						throw new ServiceException("代扣登记前请先修改代扣表中相应期数的数据");
					}
				}
			}
		}

		return accPayWithMapper.insert(vo);
	}

	/**
	 * 批量代扣登记文件回盘导入
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
		String sysId = ""; // syslib id
		boolean isDelDTLLog = false;
		int insertDtlLogCount = 0;
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
				boolean ryflg = false;
				if (attLst.size() > 0) {
					sysId = attLst.get(0).getId();
					if (attLst.get(0).getBusiType().equals(PayChanType.LYCHPAY.toString())) {
						exportType = PubBusinessConstant.KFT_BRANCH;
					} else if (attLst.get(0).getBusiType().equals(PayChanType.RYPAY.toString())) {
						exportType = PubBusinessConstant.RY_BRANCH;
						ryflg = true;
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
					actdLst.add(dt);
				}
				// 根据返回内容组装批量交易明细 end
				Map<String, Object> _param = new HashMap<String, Object>();
				_param.put("logId", sysId);

				// 回盘前先检查改批次是否已经全部回盘，防止重复写还款流水
				if (accCapTranDtlService.queryForList(_param).size() == actdLst.size()) {
					throw new ServiceException("该笔交易已全部上传［" + sysId + "］");
				}

				// 如果没有全部回盘说明上一次回盘失败 先清理一遍再插入 删除已保存的交易明细数据
				accCapTranDtlService.deleteByLogIdKey(_param);// 实时事务
				// 批量插入银联交易日志明细纪录 实时事务
				insertDtlLogCount = accCapTranDtlService.batckInsertAccCapTranDtl(actdLst);
				if (insertDtlLogCount != lst.size()) {
					throw new ServiceException("上传文件中明细笔数与纪录笔数不一致");
				}
				// 需要保存还款流水的数据集合（用于解决重复发起回盘查询 多次写入还款流水的问题）
				List<BatchRepayDKFileBo> list = accCapTranDtlService.queryBatchRepayDkFileForPay(sysId);// 该批次交易回盘总数据
				List<BatchRepayDKFileBo> flowlist = new ArrayList<BatchRepayDKFileBo>();// 交易成功的纪录
																						// 需要写流水
				BatchRepayDKFileBo _vo = null;
				for (int i = 0; i < list.size(); i++) {
					_vo = list.get(i);
					if (null == _vo) {
						continue;
					}
					/************************ 开始更新预处理 *******************************/
					// 获取所有未结清期数
					// int unClearInsTotal =
					// accLoanAcctInstService.queryUnClearTotalByLoanNo(_vo.getLoanNo());
					// 还款总期数
					if (FinanceConstant.KFT_TRAN_ST_SUCC.equals(_vo.getRetCode())) {
						// 如果是结清 查询目前锁定的条数是否与结清的期数一致
						if (_vo.getRepayType().equals(FinanceConstant.REPAY_TYPE_NOM_CLEAR)
								|| _vo.getRepayType().equals(FinanceConstant.REPAY_TYPE_OVER_CLEAR)) {
							if (_vo.getWithAmt().compareTo(_vo.getAmount()) == 0) {
								// 不走实时事务 结清只修改状态为结清不修改金额
								if (accLoanAcctInstService.updateAccLoanAcctStatNotREQUIRES_NEW(_vo.getLoanNo(), -1,
										FinanceConstant.PRETREAT_STAT_CLEARD).equals(FinanceConstant.FILED)) {
									throw new ServiceException("回盘数据中贷款编号：［" + _vo.getLoanNo() + "］ 更新预处理状态返回失败");
								}

								// 提前结清登记表
								AccRepayAdvanReg accRepayAdvanReg = new AccRepayAdvanReg();
								accRepayAdvanReg.setId(RandomUtil.getUUID());
								accRepayAdvanReg.setLoanNo(_vo.getLoanNo());
								accRepayAdvanReg.setRepayDate(_vo.getRepayDate());
								accRepayAdvanReg.setRepayNum(Integer.parseInt(_vo.getRepayNum()));
								accRepayAdvanReg.setTranDate(new Date());
								accRepayAdvanReg.setTranAmt(_vo.getAmount());
								accRepayAdvanReg.setCustName(_vo.getAcctName());
								accRepayAdvanReg.setConfNo(PubBusinessConstant.PLAN_REPAY_TYPE_OVER);
								accRepayAdvanReg.setCustAcct(_vo.getAcctNo());
								accRepayAdvanReg.setStat(PubBusinessConstant.REPAY_DG_STATUS_PAIED);
								accRepayAdvanReg.setTranStaff(user.getStaffNo());
								accRepayAdvanReg.setTranOrg(user.getOrgNo());
								accRepayAdvanReg.setInstDate(new Date());
								if (_vo.getRepayType().equals(FinanceConstant.REPAY_TYPE_OVER_CLEAR)) {
									accRepayAdvanReg.setRepayChan(PubBusinessConstant.KFT_BRANCH);
									accRepayAdvanReg.setSetlType(FinanceConstant.CLEARN_TYPE_YQ);
									accRepayAdvanReg.setTranDesc("提前结清-催收快付通扣款");
								} else if (ryflg) {
									accRepayAdvanReg.setRepayChan(PubBusinessConstant.RY_BRANCH);
									accRepayAdvanReg.setSetlType(FinanceConstant.CLEARN_TYPE_YQ);
									accRepayAdvanReg.setTranDesc("提前结清-瑞银结清扣款");
								} 
								accRepayAdvanRegService.insert(accRepayAdvanReg);
							} else {
								throw new ServiceException("回盘数据中贷款编号：［" + _vo.getLoanNo() + "］的 交易金额不正确");
							}
						} else {
							if (_vo.getRepayType().equals(PubBusinessConstant.REPAY_TYPE_DUE_ENTURST)) { //委外
								Map<String, Object> accAmtMap = new HashMap<String, Object>();
								accAmtMap.put("loanNo", _vo.getLoanNo());
								accAmtMap.put("updt", new Date());
								accAmtMap.put("setlAmt", _vo.getAmount());
								if (_vo.getWithAmt().compareTo(_vo.getAmount()) == 0) {// 足额还款
									if (accLoanAcctInstService.updateAccLoanAcctStatNotREQUIRES_NEW(_vo.getLoanNo(), -1,
											FinanceConstant.PRETREAT_STAT_CLEARD).equals(FinanceConstant.FILED)) {
										throw new ServiceException("回盘数据中贷款编号：［" + _vo.getLoanNo() + "］ 更新预处理状态返回失败");
									}	
									// 提前结清登记表
									AccRepayAdvanReg accRepayAdvanReg = new AccRepayAdvanReg();
									accRepayAdvanReg.setId(RandomUtil.getUUID());
									accRepayAdvanReg.setLoanNo(_vo.getLoanNo());
									accRepayAdvanReg.setRepayDate(_vo.getRepayDate());
									accRepayAdvanReg.setRepayNum(Integer.parseInt(_vo.getRepayNum()));
									accRepayAdvanReg.setTranDate(new Date());
									accRepayAdvanReg.setTranAmt(_vo.getAmount());
									accRepayAdvanReg.setCustName(_vo.getAcctName());
									accRepayAdvanReg.setConfNo(PubBusinessConstant.PLAN_REPAY_TYPE_OVER);
									accRepayAdvanReg.setCustAcct(_vo.getAcctNo());
									accRepayAdvanReg.setStat(PubBusinessConstant.REPAY_DG_STATUS_PAIED);
									accRepayAdvanReg.setTranStaff(user.getStaffNo());
									accRepayAdvanReg.setTranOrg(user.getOrgNo());
									accRepayAdvanReg.setInstDate(new Date());
									accRepayAdvanReg.setRepayChan(PubBusinessConstant.KFT_BRANCH);
									accRepayAdvanReg.setSetlType(FinanceConstant.CLEARN_TYPE_WW);
									accRepayAdvanReg.setTranDesc("提前结清-委外快付通扣款");
									accRepayAdvanRegService.insert(accRepayAdvanReg);
								}else {
									accLoanInstService.updateAccLoanInstSetlAmtAndUnLock(accAmtMap);
								}
							}else {
								// 非结清的类型
								BigDecimal forAmt = _vo.getAmount();
								Map<String, Object> instMap = null;
								for (int j = _vo.getBgnRepayNum().intValue(); j <= _vo.getEndRepayNum().intValue(); j++) {
									instMap = new HashMap<>();
									instMap.put("loanNo", _vo.getLoanNo());
									instMap.put("repayNum", j);
									List<AccLoanAcctInst> instList = accLoanAcctInstService.queryForList(instMap);
									BigDecimal inAmt = BigDecimal.ZERO;
									// 还款金额大于某一期的金额
									if (forAmt.subtract(instList.get(0).getCurRcvAmt()).compareTo(BigDecimal.ZERO) != -1) {
										forAmt = forAmt.subtract(instList.get(0).getCurRcvAmt());
										inAmt = instList.get(0).getCurRcvAmt();
									} else if (forAmt.compareTo(BigDecimal.ZERO) != -1) {
										inAmt = forAmt;
										forAmt = forAmt.subtract(instList.get(0).getCurRcvAmt());
									}
									Map<String, Object> _mapIns_0x = new HashMap<>();
									_mapIns_0x.put("updt", new Date());
									_mapIns_0x.put("loanNo", _vo.getLoanNo());
									// 解锁预处理
									_mapIns_0x.put("repayNum", j);
									_mapIns_0x.put("curRcvAmt", inAmt);
									// 不走实时事务 解锁同时更新j期当前应还金额 如果跑异常 回滚当前数据 不在继续处理
									if (accLoanAcctInstService
											.updateAccLoanInstCurRcvAmtAndUnLockNotREQUIRES_NEW(_mapIns_0x) <= 0) {
										throw new ServiceException(
												"回盘数据中贷款编号：［" + _vo.getLoanNo() + "］的第［" + j + "期］解锁预处理和更新当前要还金额失败返回失败");
									}
								}
							}
						}
					} else {
						// 扣款失败 只解锁
						// 如果是结清 查询目前锁定的条数是否与结清的期数一致
						if (_vo.getRepayType().equals(FinanceConstant.REPAY_TYPE_NOM_CLEAR)
								|| _vo.getRepayType().equals(FinanceConstant.REPAY_TYPE_OVER_CLEAR)) {
							for (int j = _vo.getBgnRepayNum().intValue(); j <= _vo.getEndRepayNum().intValue(); j++) {
								if (accLoanAcctInstService.updateAccLoanAcctStatNotREQUIRES_NEW(_vo.getLoanNo(), j,
										FinanceConstant.PRETREAT_STAT_UNLOCK).equals(FinanceConstant.FILED)) {
									throw new ServiceException(
											"回盘数据中贷款编号：［" + _vo.getLoanNo() + "］的第［" + j + "期］更新预处理状态返回失败");
								}
							}
						} else {
							// 非结清的类型
							for (int j = _vo.getBgnRepayNum().intValue(); j <= _vo.getEndRepayNum().intValue(); j++) {
								accLoanAcctInstService.updateAccLoanAcctStatusUnLock(_vo.getLoanNo(), j);
							}
						}
					}

					/************************ 更新预处理结束 *******************************/
					// 更新代扣登记和代扣纪录
					AccPayWith aw = accPayWithMapper.getByPrimaryKey(_vo.getDkId());
					Map<String, Object> accPayWithMap = new HashMap<>();
					accPayWithMap.put("id", aw.getId());
					accPayWithMap.put("updtDate", new Date());
					// 交易成功的情况需要纪录还款流水
					for (int j = _vo.getBgnRepayNum().intValue(); j <= _vo.getEndRepayNum().intValue(); j++) {
						Map<String, Object> _mapWith = new HashMap<String, Object>();
						_mapWith.put("loanNo", _vo.getLoanNo());
						_mapWith.put("repayNum", j);
						if (FinanceConstant.KFT_TRAN_ST_SUCC.equals(_vo.getRetCode())) {
							if (_vo.getWithAmt().compareTo(_vo.getAmount()) !=-1) {// 足额还款
								_mapWith.put("withStat", FinanceConstant.WITHSTAT_DKSUCC);
							} else { // 不足额 则 为初始状态
								_mapWith.put("withStat", FinanceConstant.WITHSTAT_UNDK);
							}
							accPayWithMap.put("stat", FinanceConstant.WITHSTAT_DKSUCC);
							// if
							// (!_vo.getRepayType().equals(FinanceConstant.REPAY_TYPE_NOM_CLEAR)
							// &&
							// !_vo.getRepayType().equals(FinanceConstant.REPAY_TYPE_OVER_CLEAR))
							// { //不为提前结清则加流水
							// flowlist.add(_vo);
							// }
						} else {
							_mapWith.put("withStat", FinanceConstant.WITHSTAT_DKFIELD);
							accPayWithMap.put("stat", FinanceConstant.WITHSTAT_DKFIELD);
						}
						accCapWithService.updateWithStat(_mapWith);// 更新代扣
					}
					accPayWithMapper.updateByPrimaryKeySelective(accPayWithMap);// 更新登记表
					if (FinanceConstant.KFT_TRAN_ST_SUCC.equals(_vo.getRetCode())) {
						// if
						// (_vo.getRepayType().equals(FinanceConstant.REPAY_TYPE_NOM_CLEAR)
						// ||
						// _vo.getRepayType().equals(FinanceConstant.REPAY_TYPE_OVER_CLEAR))
						// { // 提前结清加流水
						// flowlist.add(_vo);
						// }
						if (_vo.getRepayType().equals(PubBusinessConstant.REPAY_TYPE_DUE)
								|| _vo.getRepayType().equals(FinanceConstant.REPAY_TYPE_OVER_CLEAR)) {
							// 更新逾期的业务
							Map<String, Object> _mapOver = new HashMap<String, Object>();
							_mapOver.put("loanNo", _vo.getLoanNo());
							_mapOver.put("updt", new Date());
							if (_vo.getWithAmt().compareTo(_vo.getAmount()) !=-1) {// 足额还款
								_mapOver.put("dealStat", PubBusinessConstant.COLLECTION_YCL);
								_mapOver.put("withStat", PubBusinessConstant.REPAY_DG_STATUS_PAIED);
							} else {
								_mapOver.put("dealStat", PubBusinessConstant.COLLECTION_WCL);
								_mapOver.put("withStat", PubBusinessConstant.REPAY_DG_STATUS_PART);
							}
							accPayWithMapper.updateOverByLoanNo(_mapOver);
						} else if (_vo.getRepayType().equals(PubBusinessConstant.REPAY_TYPE_DUE_ENTURST)) {
							// 委外的业务
							Map<String, Object> outMap = new HashMap<>();
							outMap.put("id", RandomUtil.getUUID());
							outMap.put("loanNo", _vo.getLoanNo());
							outMap.put("payAmt", _vo.getAmount());
							outMap.put("payTyp", "20101201");
							outMap.put("stat", CommonConstant.STAT_ENABLE);
							Integer outInt = accPayWithMapper.updateOutSource(outMap);
							if (outInt == 0) {
								throw new com.hs.base.exception.ServiceException("获取委外案件分配信息失败");
							}
							Map<String, Object> sourceMap = new HashMap<>();
							sourceMap.put("loanNo", _vo.getLoanNo());
							if (_vo.getWithAmt().compareTo(_vo.getAmount()) !=-1) {// 足额还款
								sourceMap.put("isSettle", "1");
							}
							sourceMap.put("debtAmt", _vo.getAmount());
							accPayWithMapper.updateIsSettle(sourceMap);
						}
						flowlist.add(_vo);
					}
				}
				List<AccRepayFlow> listFlow = new ArrayList<AccRepayFlow>();
				for (BatchRepayDKFileBo vo : flowlist) {
					AccRepayFlow flowVO = new AccRepayFlow();
					flowVO.setId(RandomUtil.getUUID());
					flowVO.setTranOrg(user.getOrgNo());
					flowVO.setTranStaff(user.getStaffName());
					// 贷款编号
					flowVO.setLoanNo(vo.getLoanNo());
					// 账单日
					flowVO.setRepayDate(vo.getRepayDate());
					// 期数
					flowVO.setRepayNum(vo.getBgnRepayNum());
					// 交易日期
					flowVO.setTranDate(DateUtil.formatDate(new Date(), "yyyyMMdd"));
					// 交易类型
					flowVO.setTranType(PubBusinessConstant.TRANDTYPE_ZC);
					// 交易金额
					flowVO.setTranAmt(vo.getAmount());
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
					accRepayFlowService.batchInsertRepayFlow(listFlow);// 实时事务
				}
			} else {
				throw new ServiceException("回盘纪录为空");
			}
		} catch (Exception e) {
			isDelDTLLog = true;// 抛了异常后只需要删除已插入的交易明细即可，因为其他操作不是实时事务，不需要回滚
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} finally {
			if (isDelDTLLog) {
				Map<String, Object> _param = new HashMap<String, Object>();
				_param.put("logId", sysId);
				// 如果没有全部回盘说明上一次回盘失败 先清理一遍再插入 删除已保存的交易明细数据
				accCapTranDtlService.deleteByLogIdKey(_param);// 实时事务
			}
		}

	}

	@Transactional
	public String executeBatchDkFileExport(Map<String, Object> params) throws ServiceException {
		if (params.containsKey("repayType")) {
			throw new ServiceException("还款业务类型不能为空");
		}
		if (!params.containsKey("stat")) {
			throw new ServiceException("必须选择还款状态");
		} else if (CompAmtUtil.checkDKWithStat(String.valueOf(params.get("stat")))) {
			throw new ServiceException("当前代扣状态不允许扣款");
		}
		// 取得可扣数据集合
		List<AccPayWith> repayDkDetailList = accPayWithMapper.queryForList(params);
		if (repayDkDetailList.size() == 0) {
			throw new ServiceException("当前条件无数据可扣款");
		}

		String fileUrl = null;
		try {

			// 渠道代码
			String chalCode = params != null && !StringUtils.isEmpty(params.get("channelType"))
					? params.get("channelType").toString() : "";

			Object[] obj = null;
			String exportTxtType = String.valueOf(params.get("exportTxtType"));
			if (com.hs.utils.StringUtils.isNotEmpty(exportTxtType)) {
				if (exportTxtType.equals(PayChanType.ALLINPAY.toString())) {
					throw new ServiceException("此渠道不支持间连扣款");
					// obj = EBPPUtil.expFileBeanByRepayDk(repayDkDetailList,
					// new String[] { "#serialNum#", "", "#openBank#", "00",
					// "#acctNo#", "#acctName#", "", "", "",
					// "0", "#curRcvAmt#", "CNY", "", "", "0", "#certNo#", "",
					// "", "#id#", "",
					// "#loanNo#" },
					// ",", getBatchNo(PayChanType.ALLINPAY.toString()));
				} else if (exportTxtType.equals(PayChanType.LYCHPAY.toString())) {
					obj = EBPPUtil.expKFTFileByRepayDk(getBatchNo(PayChanType.LYCHPAY.toString()), repayDkDetailList);
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
			// 返回文件前修改代扣登记为扣款中
			Map<String, Object> accPayWithMap = null;
			for (AccPayWith accPayWith : repayDkDetailList) {
				accPayWithMap = new HashMap<>();
				accPayWithMap.put("id", accPayWith.getId());
				accPayWithMap.put("stat", FinanceConstant.WITHSTAT_DKING);
				if (accPayWithMapper.updateByPrimaryKeySelective(accPayWithMap) <= 0) {
					throw new ServiceException("代扣登记表更新失败");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} finally {

		}
		return fileUrl;

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
	public   String getBatchNo(String busiType) {
		String batchNo = "";
		if (busiType.equals(PayChanType.LYCHPAY.toString())) {
			String kftBatchNo = accCapWithService.getNextKftBatchNo();
			kftBatchNo = Integer.parseInt(kftBatchNo)+1+"";
			accCapWithService.updateKftNo(kftBatchNo);
			return  kftBatchNo;
		} else {
			AttachmentApi sysAttachServer = SpringContextHolder.getBean("sysAttachServer");
			Map<String, Object> map = new HashMap<>();
			map.put("instDate", DateUtil.getDay("yyyyMMdd"));
			map.put("busiType", busiType);
			List<Attachment> attLst = sysAttachServer.queryForList(map);
			int fileCount = attLst.size();
			fileCount = fileCount == 0 ? 1 : fileCount + 1;
			if (fileCount > 99) {
				throw new ServiceException("当天只允许生成99次代扣文件");
			}
			batchNo = fileCount + "";
		}
		return batchNo;
	}

	/**
	 * 新增 ACC_三方支付代扣信息
	 * 
	 * @param accPayWith
	 *            新增对象
	 */
	@Transactional
	public void insert(AccPayWith accPayWith) {
		accPayWithMapper.insert(accPayWith);
	}

	/**
	 * 通过主键修改 ACC_三方支付代扣信息
	 * 
	 * @param map
	 *            修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map) {
		accPayWithMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 ACC_三方支付代扣信息
	 * 
	 * @param primaryKey
	 *            主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey) {
		accPayWithMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 ACC_三方支付代扣信息 对象
	 * 
	 * @param primaryKey
	 *            主键
	 * @return ACC_三方支付代扣信息对象
	 */
	public AccPayWith getByPrimaryKey(String primaryKey) {
		return accPayWithMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 ACC_三方支付代扣信息 列表
	 * 
	 * @param param
	 *            查询参数map
	 * @return List<T>列表
	 */
	public List<AccPayWith> queryForList(Map<String, Object> param) {
		return accPayWithMapper.queryForList(param);
	}

	/**
	 * 查询 ACC_三方支付代扣信息 分页列表
	 * 
	 * @param page
	 *            查询参数page
	 * @return List<T>列表
	 */
	public Page<AccPayWith> queryForPage(Page<AccPayWith> page) {
		Map<String, Object> param = page.getPageParams();
		if (param.get("companyType") != null) {
			if ("0".equals(param.get("companyType").toString())) {
				param.put("tranChan", PayChanType.LYCHPAY);
			} else if ("1".equals(param.get("companyType").toString())) {
				param.put("tranChan", PayChanType.RYPAY);
			}
		}
		if ("".equals(param.get("stat"))||param.get("stat")==null) {
			param.put("stat","20109001");
		}
		accPayWithMapper.queryForList(param);
		return (Page<AccPayWith>) page.getPageParams().get(Page.KEY);
	}
}