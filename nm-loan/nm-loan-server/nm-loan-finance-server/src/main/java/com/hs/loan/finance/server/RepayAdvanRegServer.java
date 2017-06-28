package com.hs.loan.finance.server;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.CommonConstant;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.loan.finance.api.RepayAdvanRegApi;
import com.hs.loan.finance.bo.AccLoanPlanBo;
import com.hs.loan.finance.bo.SingleDkResultBo;
import com.hs.loan.finance.bo.SingleOtherBusiBo;
import com.hs.loan.finance.contant.FinanceConstant;
import com.hs.loan.finance.dto.AccLoanPlanDto;
import com.hs.loan.finance.dto.AccRepayAdvanRegDto;
import com.hs.loan.finance.dto.AccRepayDgRegDto;
import com.hs.loan.finance.entity.AccPayWith;
import com.hs.loan.finance.entity.AccRepayAdvanReg;
import com.hs.loan.finance.entity.AccRepayFlow;
import com.hs.loan.finance.entity.AccWithholdReg;
import com.hs.loan.finance.service.AccCapWithService;
import com.hs.loan.finance.service.AccLoanAcctInstService;
import com.hs.loan.finance.service.AccLoanPlanService;
import com.hs.loan.finance.service.AccPayWithService;
import com.hs.loan.finance.service.AccRepayAdvanRegService;
import com.hs.loan.finance.service.AccRepayFlowService;
import com.hs.loan.finance.service.AccWithholdRegService;
import com.hs.loan.finance.util.CompAmtUtil;
import com.hs.loan.finance.util.PayChanType;
import com.hs.loan.finance.util.RepayUtil;
import com.hs.loan.pub.hand.entity.PubMessageModel;
import com.hs.loan.pub.hand.service.AppLoanHandService;
import com.hs.loan.pub.hand.service.PubMessageModelService;
import com.hs.utils.BeanUtils;
import com.hs.utils.DateUtils;
import com.hs.utils.HttpsInvokerUtil;
import com.hs.utils.ParamUtils;
import com.hs.utils.RandomUtil;

/**
 * 提前结清
 * 
 * @author zym
 *
 */
@Service
@Transactional(readOnly = true)
public class RepayAdvanRegServer implements RepayAdvanRegApi {

	@Autowired
	private AccRepayAdvanRegService accRepayAdvanRegService;
	@Autowired
	private AccLoanPlanService accLoanPlanService;
	@Autowired
	private AccLoanAcctInstService accLoanAcctInstService;
	@Autowired
	private AccCapWithService accCapWithService;
	@Autowired
	private AppLoanHandService appLoanHandService;
	@Autowired
	private AccPayWithService accPayWithService;
	@Autowired
	private PubMessageModelService pubMessageModelService;
	@Autowired
	private AccWithholdRegService accWithholdRegService;

	/**
	 * 提前结清列表
	 */
	@Override
	public Page<AccRepayAdvanRegDto> queryRepayAdvanRegDto(Page<AccRepayAdvanRegDto> page)
			throws ServiceException, AppException {

		Page<AccRepayAdvanReg> page1 = null;
		try {
			page1 = accRepayAdvanRegService.queryForPage(page.toPage(AccRepayAdvanReg.class));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return page1.toPage(AccRepayAdvanRegDto.class);
	}

	/**
	 * 获取提前结清的金额
	 */
	@Override
	public AccLoanPlanDto comRepay(Map<String, Object> map) throws ServiceException, AppException {
		AccLoanPlanDto accLoanPlanDto = new AccLoanPlanDto();
		if (map == null || map.size() == 0) {
			throw new ServiceException("请求参数为空.");
		}
		String loanNo = (String) map.get("loanNo");
		String repayNum = (String) map.get("repayNum");
		if (StringUtils.isEmpty(loanNo) || StringUtils.isEmpty(repayNum)) {
			throw new ServiceException("请求参数为空.");
		}

		AccLoanPlanBo accLoanPlanbo = accLoanPlanService.calcEealySumAmt(loanNo);
		BeanUtils.copyPropertiesNotForce(accLoanPlanDto, accLoanPlanbo);
		BigDecimal salAmt = CompAmtUtil.getDgTransBal(loanNo, new Integer(repayNum),
				PubBusinessConstant.PLAN_REPAY_TYPE_OVER);

		accLoanPlanDto.setSetlAmt(salAmt);

		return accLoanPlanDto;
	}

	/**
	 * 银联提前结清
	 */
	@Override
	@Transactional
	public void saveLoanRepayDg(AccRepayDgRegDto repayDgDto, UserProfile userProfile) throws ServiceException {
		boolean flag = true;
		String loanNo = repayDgDto.getLoanNo();
		Integer repayNum = repayDgDto.getRepayNum();
		try {
			// 还款金额计算
			BigDecimal dgTransBal = CompAmtUtil.getDgTransBal(loanNo, repayNum,
					PubBusinessConstant.PLAN_REPAY_TYPE_OVER);
			// 锁表
			accLoanAcctInstService.updateAccLoanAcctStat(loanNo, -1, FinanceConstant.PRETREAT_STAT_LOCK);
			repayDgDto.setTotlAmt(dgTransBal);
			repayDgDto.setId(RandomUtil.getUUID());
			repayDgDto.setInstDate(DateUtils.getCurrentTimestamp());
			repayDgDto.setTranStaff(userProfile.getStaffName());
			repayDgDto.setTranOrg(userProfile.getOrgNo());
			AccRepayAdvanReg accRepayAdvanReg = new AccRepayAdvanReg();
			BeanUtils.copyPropertiesNotForce(accRepayAdvanReg, repayDgDto);
			accRepayAdvanReg.setCustName(repayDgDto.getAcctName());
//			accRepayAdvanReg.setTranDate(DateUtils.getCurrentDate());
			String tranChan = "";
			String tranChanMsg = "";
			if (PayChanType.ALLINPAY.toString().equals(repayDgDto.getTranType())) { // 通联
				throw new ServiceException("此扣款通道已关闭");
			} else if (PayChanType.CHINAPAY.toString().equals(repayDgDto.getTranType())) { // 银联
				throw new ServiceException("此扣款通道已关闭");
//				accRepayAdvanReg.setRepayChan(PubBusinessConstant.TRANCHAN_SIGNTYPE);
			} else if (PayChanType.LYCHPAY.toString().equals(repayDgDto.getTranType())) { // 快付通
				if (accCapWithService.capWithSize(2, repayDgDto.getLoanNo())) {
					throw new ServiceException("该笔贷款当日快付通扣款失败次数超出限制");
				}
				tranChan = PubBusinessConstant.KFT_BRANCH;
				accRepayAdvanReg.setRepayChan(tranChan);
				accRepayAdvanReg.setTranDate(DateUtils.getCurrentDate());
			} else if (PayChanType.ZJPAY.toString().equals(repayDgDto.getTranType())) { // 中金
				if (accCapWithService.capWithSize(1, repayDgDto.getLoanNo())) {
					throw new ServiceException("该笔贷款当日中金扣款失败次数超出限制");
				}
				tranChan = PubBusinessConstant.ZJ_SIGLE;
				tranChanMsg = "中金代扣";
				accRepayAdvanReg.setRepayChan(tranChan);
				accRepayAdvanReg.setTranDate(DateUtils.getCurrentDate());
			} else if (PayChanType.LIANLIANPAY.toString().equals(repayDgDto.getTranType())) { // 连连支付
				if (accCapWithService.capWithSize(3, repayDgDto.getLoanNo())) {
					throw new ServiceException("该笔贷款当日连连支付扣款失败次数超出限制");
				}
				tranChan = PubBusinessConstant.LL_SIGLE;
				tranChanMsg="连连支付";
				accRepayAdvanReg.setRepayChan(tranChan);
				accRepayAdvanReg.setTranDate(DateUtils.getCurrentDate());
			} else {
				accRepayAdvanReg.setRepayChan(repayDgDto.getTranType());
				if (repayDgDto.getTranAmtTwo() != null && repayDgDto.getTranDateTwo() != null
						&& repayDgDto.getTranTypeTwo() != null) {
					accRepayAdvanReg.setTranAmt(repayDgDto.getTranAmt().add(repayDgDto.getTranAmtTwo()));
				}
			}
			accRepayAdvanReg.setConfNo(PubBusinessConstant.PLAN_REPAY_TYPE_OVER);
			accRepayAdvanReg.setCustAcct(repayDgDto.getBankNo());
			accRepayAdvanReg.setStat(PubBusinessConstant.REPAY_DG_STATUS_PAIED);
			 if (repayDgDto.getTranType().equals(PayChanType.RYPAY.toString())) {
				// 瑞银-银联
				AccPayWith accPayWith = new AccPayWith();
				accPayWith.setId(RandomUtil.getUUID());
				accPayWith.setLoanNo(loanNo);
				accPayWith.setAcctNo(repayDgDto.getBankNo());
				accPayWith.setAcctName(repayDgDto.getAcctName());
				accPayWith.setOpenBank(repayDgDto.getOpenOrg());
				accPayWith.setCertNo(repayDgDto.getCertNo());
				accPayWith.setCurRcvAmt(dgTransBal);
				accPayWith.setBgnRepayNum(repayDgDto.getRepayNum());
				accPayWith.setEndRepayNum(repayDgDto.getRepayNum());
				accPayWith.setWithAmt(dgTransBal);
				accPayWith.setStat(FinanceConstant.WITHSTAT_UNDK);
				accPayWith.setRepayType(FinanceConstant.REPAY_TYPE_NOM_CLEAR);
				accPayWith.setInstDate(new Date());
				accPayWith.setCompanyType(repayDgDto.getTranType().equals(PayChanType.LYCHPAY.toString()) ? "0" : "1");
				accPayWithService.accPayReg(accPayWith);
			} else if (repayDgDto.getTranType().equals(PayChanType.LYCHPAY.toString())) {
				// 快付通
				accRepayAdvanReg.setStat(PubBusinessConstant.REPAY_DG_STATUS_HANDLING);
				AccPayWith accPayWith = new AccPayWith();
				accPayWith.setId(RandomUtil.getUUID());
				accPayWith.setLoanNo(loanNo);
				accPayWith.setAcctNo(repayDgDto.getBankNo());
				accPayWith.setAcctName(repayDgDto.getAcctName());
				accPayWith.setOpenBank(repayDgDto.getOpenOrg());
				accPayWith.setCertNo(repayDgDto.getCertNo());
				accPayWith.setCurRcvAmt(dgTransBal);
				accPayWith.setBgnRepayNum(repayDgDto.getRepayNum());
				accPayWith.setEndRepayNum(repayDgDto.getRepayNum());
				accPayWith.setWithAmt(dgTransBal);
				accPayWith.setStat(FinanceConstant.WITHSTAT_UNDK);
				accPayWith.setRepayType(FinanceConstant.REPAY_TYPE_NOM_CLEAR);
				accPayWith.setInstDate(new Date());
				accPayWith.setCompanyType(repayDgDto.getTranType().equals(PayChanType.LYCHPAY.toString()) ? "0" : "1");
				accPayWithService.accPayReg(accPayWith);
			} else if (repayDgDto.getTranType().equals(PayChanType.ZJPAY.toString())||repayDgDto.getTranType().equals(PayChanType.LIANLIANPAY.toString())) {
				// 中金和连连支付
				accRepayAdvanReg.setStat(PubBusinessConstant.REPAY_DG_STATUS_HANDLING);
				SingleOtherBusiBo singleBo = new SingleOtherBusiBo();
				singleBo.setLoanNo(repayDgDto.getLoanNo());
				singleBo.setId(repayDgDto.getId());
				singleBo.setCertNo(repayDgDto.getCertNo());
				singleBo.setAcctNo(repayDgDto.getBankNo());
				singleBo.setAcctName(repayDgDto.getAcctName());
				singleBo.setBankNo(repayDgDto.getOpenOrg());
				singleBo.setTransAmtTotal(dgTransBal);
				singleBo.setRepayNum(repayDgDto.getRepayNum());
				singleBo.setTranType(repayDgDto.getTranType());
				singleBo.setChanNo("001");
				accRepayAdvanReg.setStat(PubBusinessConstant.REPAY_DG_STATUS_PAIED);
				accRepayAdvanReg.setTranAmt(repayDgDto.getTotlAmt());
				accRepayAdvanReg.setTranDate(DateUtils.getCurrentDate());
				accRepayAdvanReg.setTranDesc("提前结清-单笔贷款");
				SingleDkResultBo result = accCapWithService.singleRepayOtherBusi(singleBo, userProfile);
				AccWithholdReg reg = new AccWithholdReg();
				reg.setId(RandomUtil.getUUID());
				reg.setBgnNum(singleBo.getRepayNum());
				reg.setEndNum(singleBo.getRepayNum());
				reg.setStaffNo(userProfile.getStaffNo());
				reg.setStaffName(userProfile.getStaffName());
				reg.setOrgNo(userProfile.getOrgNo());
				reg.setBankNo(singleBo.getBankNo());
				reg.setAcctNum(singleBo.getAcctNo());
				reg.setAcctName(singleBo.getAcctName());
				reg.setLoanNo(loanNo);
				reg.setTranAmt(dgTransBal);
				reg.setRcvAmt(dgTransBal);
				reg.setRepayDate(repayDgDto.getRepayDate());
				reg.setTranBgnDate(new Date());
				reg.setTranTyp(PubBusinessConstant.PLAN_REPAY_TYPE_OVER);
				reg.setTranChan(tranChan);
				if (result.getRetItem() != null && result.getRetItem().getRetCode() != null
						&& result.getRetItem().getRetCode().equals(FinanceConstant.TRAN_ST_SUCC)) {
					reg.setTxsn(result.getReqSn());
					reg.setWithStat(FinanceConstant.WITHSTAT_DKSUCC);
					reg.setTranEndDate(new Date());
					reg.setSettleStat("10001002");
					accWithholdRegService.insertAccWithholdReg(reg);
				} else if (result.getRetItem() != null && result.getRetItem().getRetCode() != null
						&& result.getRetItem().getRetCode().equals(FinanceConstant.TRAN_ST_DEALING)) {
					reg.setTxsn(result.getReqSn());
					reg.setWithStat(FinanceConstant.WITHSTAT_DKING);
					reg.setSettleStat("10001002");
					accWithholdRegService.insertAccWithholdReg(reg);
					flag = false;
					throw new ServiceException(tranChanMsg+"扣款正在处理中," + result.getErrMsg());
				} else {
					reg.setTxsn(result.getReqSn());
					reg.setWithStat(FinanceConstant.WITHSTAT_DKFIELD);
					reg.setTranEndDate(new Date());
					reg.setSettleStat("10001002");
					accWithholdRegService.insertAccWithholdReg(reg);
					throw new ServiceException(tranChanMsg+"扣款失败," + result.getRetItem().getErrMsg());
				}
				accLoanAcctInstService.updateAccLoanAcctStat(loanNo, -1, FinanceConstant.PRETREAT_STAT_CLEARD);
			} else {
				accLoanAcctInstService.updateAccLoanAcctStat(loanNo, -1, FinanceConstant.PRETREAT_STAT_CLEARD);
			}
			if (!repayDgDto.getTranType().equals(PayChanType.RYPAY.toString())
					&& !repayDgDto.getTranType().equals(PayChanType.LYCHPAY.toString())) {
				accRepayAdvanReg.setSetlType(FinanceConstant.CLEARN_TYPE_ZC);
				accRepayAdvanRegService.insert(accRepayAdvanReg);
			}
			String dgStatus = accRepayAdvanReg.getStat();
			if (dgStatus.equals(PubBusinessConstant.REPAY_DG_STATUS_PAIED)) {
				// 保存还款流水
//				if (!"".equals(tranChan)) {
//					repayDgDto.setTranType(tranChan);
//				}
				saveRepayFlow(repayDgDto, userProfile);

				if (repayDgDto.getTranAmtTwo() != null && repayDgDto.getTranDateTwo() != null
						&& repayDgDto.getTranTypeTwo() != null) {
					repayDgDto.setTranAmt(repayDgDto.getTranAmtTwo());
					repayDgDto.setTranDate(repayDgDto.getTranDateTwo());
					repayDgDto.setTranType(repayDgDto.getTranTypeTwo());
					saveRepayFlow(repayDgDto, userProfile);
				}
				accRepayAdvanRegService.updateOveLoanStat(loanNo);
				Map<String, Object> accAmtMap = new HashMap<String, Object>();
				// 更新代扣信息
				accAmtMap.put("loanNo", loanNo);
				accAmtMap.put("withStat", FinanceConstant.WITHSTAT_DKSUCC);
				accAmtMap.put("updtDate", DateUtils.getCurDateTime());
				accAmtMap.put("_desc", "结清");
				accCapWithService.updateAccCapWithByStatByLoanNo(accAmtMap);

				appLoanHandService.saveAppLoanHand(loanNo, "-", repayDgDto.getAcctName(),
						PubBusinessConstant.LOANHANDTYPE_FINSH, PubBusinessConstant.LOANHANDMODEL_SYS,
						userProfile.getStaffNo(), userProfile.getStaffName(), DateUtils.getCurrentTimestamp(),
						"分期提前结清,:-" + accRepayAdvanReg.getTranDesc(), PubBusinessConstant.CUST_ZC);
			}
			flag = false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("提前结清失败" + e.getMessage());
		} finally {
			if (flag) {
				accLoanAcctInstService.updateAccLoanAcctStat(loanNo, -1, FinanceConstant.PRETREAT_STAT_UNLOCK);
			}
		}
	}

	/**
	 * 保存还款流水
	 * 
	 * @param flowVO
	 *            //预处理VO
	 * @param merInfo
	 *            //银联商户信息
	 * @param transSum
	 *            //交易摘要
	 * @param transItem
	 *            //交易明细
	 * @param retCode
	 *            //返回码
	 * @throws ServiceException
	 */

	@Autowired
	AccRepayFlowService accRepayFlowService;

	private String saveRepayFlow(AccRepayDgRegDto repayDgDto, UserProfile userProfile) throws ServiceException {
		AccRepayFlow flowVO = new AccRepayFlow();
		// 交易金额
		flowVO.setTranAmt(repayDgDto.getTotlAmt());
		// 交易日期
		flowVO.setTranDate(DateUtils.getCurDate());
		// 还款类型
		if (PayChanType.LYCHPAY.toString().equals(repayDgDto.getTranType())) { // 快付通
			// 交易渠道
			flowVO.setTranChan(PubBusinessConstant.KFT_BRANCH);
		} else if (PayChanType.ZJPAY.toString().equals(repayDgDto.getTranType())) { // 中金
			// 交易渠道
			flowVO.setTranChan(PubBusinessConstant.ZJ_SIGLE);
		} else if (PayChanType.LIANLIANPAY.toString().equals(repayDgDto.getTranType())) { // 连连支付
			// 交易渠道
			flowVO.setTranChan(PubBusinessConstant.LL_SIGLE);
		} else {
			flowVO.setTranDate(DateUtils.formatDate(repayDgDto.getTranDate(), "yyyyMMdd"));
			flowVO.setTranChan(repayDgDto.getTranType());
			flowVO.setTranAmt(repayDgDto.getTranAmt());
		}
		// 贷款编号
		flowVO.setLoanNo(repayDgDto.getLoanNo());
		// 账单日
		flowVO.setRepayDate(repayDgDto.getRepayDate());
		// 期数
		flowVO.setRepayNum(repayDgDto.getRepayNum());

		// 交易类型
		flowVO.setTranType(PubBusinessConstant.TRANDTYPE_ZC);

		// 支付宝
		if (PubBusinessConstant.TRANCHAN_ZHIFUBO.equals(repayDgDto.getTranType())) {
			// 转入账号
			flowVO.setCntAcctNo(ParamUtils.getParam("compZfb"));
			// 转入户名
			flowVO.setCntAcctName("深圳柠檬金服支付宝账户");
		} else { // 现金还款，对公还款
			// 转入账号
			flowVO.setCntAcctNo(ParamUtils.getParam("compAcctNo"));
			// 转入户名
			flowVO.setCntAcctName("深圳柠檬金服对公账户");
		}
		// 是否结算：否
		flowVO.setSetlFlag(CommonConstant.COMMON_NO);
		// 创建日期
		flowVO.setInstDate(DateUtils.getCurrentTimestamp());
		// 还款账户
		flowVO.setAcctNo(repayDgDto.getCustAcct());
		// 还款户名
		flowVO.setAcctName(repayDgDto.getAcctName());
		// 交易状态：当前日期与账单日比较，如果
		flowVO.setLoanStat(RepayUtil.getLoanStat(flowVO.getRepayDate()));
		// 转入账号
		flowVO.setCntAcctNo("");
		// 转入户名
		flowVO.setCntAcctName("");
		// 发起机构
		String orgId = userProfile.getOrgNo();
		flowVO.setTranOrg(orgId);
		// 经办人
		flowVO.setTranStaff(userProfile.getStaffName());

		// 是否提前结清
		String saveRepayFlow = accRepayFlowService.saveRepay(flowVO);
		if (!FinanceConstant.SUCC.equals(saveRepayFlow)) {
			throw new ServiceException(saveRepayFlow);
		}
		return saveRepayFlow;
	}

	/**
	 * 短信发送
	 * 
	 * @param param
	 *            loanNO
	 * @throws ServiceException
	 */
	public void sendMsg(Map<String, Object> param) throws ServiceException {
		String loanNo = param.get("loanNo").toString();
		Map<String, Object> modelparam = new HashMap<>();
		modelparam.put("msgTyp", "operate");
		modelparam.put("messageStat", "1");
		List<PubMessageModel> listModel = pubMessageModelService.queryForList(modelparam);
		if (listModel != null && listModel.size() > 0) {
			Map<String, Object> acct = accRepayAdvanRegService.getAppLoanAcct(loanNo);
			PubMessageModel model = listModel.get(0);
			String msg = model.getMsg();
			msg = msg.replace("{name}", acct.get("custName").toString())
					.replace("{applyDate}", acct.get("applyDate").toString()).replace("{loanNo}", loanNo)
					.replace("{todayDate}", acct.get("todayDate").toString());
			String url = ParamUtils.getParam("msgSendUrl");
			param.put("mob", acct.get("phoneNo"));
			param.put("msg", msg);
			List<NameValuePair> parameters = new ArrayList<>();
			Set<String> keys = param.keySet();
			for (String key : keys) {
				parameters.add(new BasicNameValuePair(key, param.get(key).toString()));
			}
			try {
				HttpsInvokerUtil.executeHttpPost(url, parameters);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException("发送短信通道错误");
			}
		} else {
			throw new ServiceException("未获取到短信模版");
		}
	}

}
