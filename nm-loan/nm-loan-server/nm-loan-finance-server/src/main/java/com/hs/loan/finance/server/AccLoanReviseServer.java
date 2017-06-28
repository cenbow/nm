package com.hs.loan.finance.server;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.loan.finance.api.AccLoanReviseApi;
import com.hs.loan.finance.service.AccRepayFlowService;
import com.hs.loan.pub.hand.service.AppLoanHandService;
import com.hs.utils.DateUtils;

/**
 * 调账功能
 * 
 * @author zhangxiaoqiang
 *
 */
@Service
@Transactional(readOnly = true)
public class AccLoanReviseServer implements AccLoanReviseApi {

	@Autowired
	private AccRepayFlowService accRepayFlowService;

	@Autowired
	private AppLoanHandService appLoanHandService;

	private void saveHand(UserProfile profile, String remark, String loanNo) {
		// 保存系统经办
		appLoanHandService.saveAppLoanHand(loanNo, "", "", PubBusinessConstant.LOANHANDTYPE_UPDATECUSTINFO,
				PubBusinessConstant.LOANHANDMODEL_SYS, profile.getStaffNo(), profile.getStaffName(),
				DateUtils.getCurrentDate(), remark, PubBusinessConstant.CUST_ZC);
	}

	/**
	 * 修改客户银行卡开户行
	 * 
	 * @param loanNo
	 * @param openOrg
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	@Transactional
	public String updateOpenBankOrg(String loanNo, String openOrg, UserProfile profile)
			throws ServiceException, AppException {
		// TODO Auto-generated method stub
		Map<String, Object> objMap = accRepayFlowService.findOpenBankOrg(loanNo);
		if (objMap == null)
			throw new ServiceException("未查询到该笔贷款对应的有效银行卡信息");

		if (openOrg.equals(objMap.get("OPEN_ORG")))
			throw new ServiceException("修改的银行卡开户行与记录一致");

		Map<String, Object> param = new HashMap<>();
		param.put("bankAcctId", objMap.get("BANK_ACCT_ID"));
		param.put("openOrg", openOrg);
		if (accRepayFlowService.updateOpenBankOrg(param) > 0) {
			saveHand(profile, "修改银行卡开户行：" + openOrg, loanNo);
			return "操作成功";

		}
		throw new ServiceException("操作失败 "); 
	}

	/**
	 * 业务日期不对称修改
	 * 
	 * @param loanNo
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	@Transactional
	public String updateBusnDate(String loanNo, UserProfile profile) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		Map<String, Object> objMap = accRepayFlowService.findInstMap(loanNo);
		if (objMap == null)
			throw new ServiceException("未查询到该笔贷款对应预处理表信息");
		String busDateCop = DateUtils.calendar(new Date(), -1, "yyyyMMdd");
		if (busDateCop.equals(objMap.get("BUSN_DATE")))
			throw new ServiceException("该笔贷款的业务日期正确无需修改");
		if ("20101002".equals(objMap.get("INST_STAT")))
			throw new ServiceException("该笔贷款已被锁定");
		if ("20101003".equals(objMap.get("INST_STAT")))
			throw new ServiceException("该笔贷款已结清");
		Map<String, Object> param = new HashMap<>();
		param.put("loanNo", loanNo);
		param.put("busDateCop", busDateCop);
		if (accRepayFlowService.updateInstBusnDate(param) > 0) {
			saveHand(profile, "修改业务日期", loanNo);
			return "操作成功";
		}
		throw new ServiceException("操作失败 "); 
	}

	/**
	 * 修改对公登记
	 * 
	 * @param dgid
	 * @param tranType
	 * @param tranAmt
	 * @param oldtranAmt
	 * @param profile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	@Transactional
	public String updateDgReg(String dgid, String tranType, BigDecimal tranAmt, UserProfile profile)
			throws ServiceException, AppException {
		// TODO Auto-generated method stub
		Map<String, Object> objMap = accRepayFlowService.findDgReg(dgid);
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyyMMdd");
		String busDateCop = tempDate.format(new Date());
		if (!busDateCop.equals(objMap.get("INST_DATE")))
			throw new ServiceException("只能修改当日未结算的对公记录");
		Map<String, Object> flowparam = new HashMap<>();
		flowparam.put("loanNo", objMap.get("LOAN_NO"));
		flowparam.put("repayDate", objMap.get("REPAY_DATE"));
		flowparam.put("tranDate", objMap.get("TRAN_DATE"));
		flowparam.put("tranChan", objMap.get("TRAN_TYPE"));
		flowparam.put("tranAmt", objMap.get("TRAN_AMT"));
		Map<String, Object> flowMap = accRepayFlowService.findRepayFlow(flowparam);
		StringBuilder sb = new StringBuilder();
		sb.append("修改对公登记表--金额:").append(tranAmt).append("(原金额:").append(objMap.get("TRAN_AMT")).append(") 渠道:")
				.append(tranType).append("(原渠道:").append(objMap.get("TRAN_TYPE")).append(")");
		Map<String, Object> param = new HashMap<>();
		param.put("dgId", dgid);
		param.put("flId", flowMap.get("ID"));
		param.put("tranAmt", tranAmt);
		param.put("tranType", tranType);
		if (accRepayFlowService.updateDgReg(param) > 0 && accRepayFlowService.updateRepayFlow(param) > 0) {
			saveHand(profile, sb.toString(), objMap.get("LOAN_NO").toString());
			return "操作成功";
		}
		throw new ServiceException("操作失败 "); 
	}

}
