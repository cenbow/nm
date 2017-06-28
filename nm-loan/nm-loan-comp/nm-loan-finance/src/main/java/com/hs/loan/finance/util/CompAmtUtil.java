package com.hs.loan.finance.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.commons.tools.SpringContextHolder;
import com.hs.loan.finance.contant.FinanceConstant;
import com.hs.loan.finance.service.FinanceCompService;
import com.hs.utils.StringUtils;

/**
 * 计算交易金额工具类
 * @author zym
 *
 */
public class CompAmtUtil {
	
	public static Boolean checkDKWithStat(String thisWithStat){
		//代扣成功｜代扣中，取消 
		final String WITH_STAT = "^("+FinanceConstant.WITHSTAT_DKING+"|"+FinanceConstant.WITHSTAT_DKSUCC+"|" + FinanceConstant.WITHSTAT_DKREV+")";	//不对匹配该状态的参数处理（正则）
		return thisWithStat.matches(WITH_STAT);
	}
	
/**
	 *  代扣金额计算
	 * @param loanNo	贷款编号
	 * @param repayNum	期数
	 * @param chanNo	渠道代码
	 * @param acctFlag	交易方：资方/平台
	 * @param pageSource	业务模块-页面来源:代扣/还款计划登记（提前结清）/对公/催收单笔还款/催收结清/委外
	 * @return
	 * @throws ServiceException
	 */
	public static BigDecimal getDkTransBal(String loanNo,Integer repayNum,String chanNo,String acctFlag,String pageSource) throws ServiceException{
		FinanceCompService iFinanceComputerService = SpringContextHolder.getBean(FinanceCompService.class);
		BigDecimal transBal = new BigDecimal(0);
		if(FinanceConstant.SINGLE_REPAY_JH.equals(pageSource) || FinanceConstant.SINGLE_REPAY_CS_JQ.equals(pageSource)){//结清
			List<Map<String,Object>> lst = iFinanceComputerService.getAcctInstAll(loanNo);
			for (Map map : lst) {
				String INST_STAT = (String) map.get("INST_STAT");
				if(!StringUtils.isEmpty(INST_STAT))
				{
					if(FinanceConstant.PRETREAT_STAT_LOCK.equals(INST_STAT)){
						throw new ServiceException("分期处理表状态为锁定,请稍后处理.");
					}
					if(FinanceConstant.PRETREAT_STAT_CLEARD.equals(INST_STAT)){
						throw new ServiceException("分期处理表状态为已结清,请稍后处理.");
					}
				}
			}
			//查询结清金额
			transBal = iFinanceComputerService.compCurrSetAmt(loanNo, repayNum);
		}else{			
			//如果是渠道一 需根据交易方来查交易金额
			if(FinanceConstant.CHL_XT.equals(chanNo)){
				if(FinanceConstant.ACCTFLAG_XT.equals(acctFlag)){//资方
					//查询资方交易金额
					transBal = iFinanceComputerService.compCurrFunAmt(loanNo, repayNum);
				}else{//平台
					//查询平台交易金额
					transBal = iFinanceComputerService.compCurrPloAmt(loanNo, repayNum);
				}
			}else{//其他渠道
				//当期时点应还金额
				transBal = iFinanceComputerService.compCurrAmt(loanNo, repayNum);
			}
		}
		
		//异常提示
		if(null == transBal){
			throw new ServiceException("金额计算失败");
		}else if(transBal.compareTo(BigDecimal.valueOf(-1)) == 0){
			throw new ServiceException("数据不存在");
		}else if(transBal.compareTo(BigDecimal.valueOf(-2)) == 0){
			throw new ServiceException("业务日期不对称");
		}else if(transBal.compareTo(BigDecimal.valueOf(-3)) == 0){
			throw new ServiceException("该笔贷款已锁定，请稍后再试");
		}else if(transBal.compareTo(BigDecimal.valueOf(-4)) == 0){
			throw new ServiceException("该笔贷款已结清");
		}
		return transBal;
	}
	
	/**
	 *  对公金额计算
	 * @param loanNo	贷款编号
	 * @param repayNum	期数
	 * @param chanNo	渠道代码
	 * @param acctFlag	交易方：资方/平台
	 * @param pageSource	业务模块-页面来源:代扣/还款计划登记（提前结清）/对公/催收单笔还款/催收结清/委外
	 * @return
	 * @throws ServiceException
	 */
	public static BigDecimal getDgTransBal(String loanNo, Integer repayNum,String dgType) throws ServiceException{
		BigDecimal transBal = BigDecimal.ZERO;
		FinanceCompService iFinanceComputerService = SpringContextHolder.getBean(FinanceCompService.class);
		if(PubBusinessConstant.PLAN_REPAY_TYPE_OVER.equals(dgType)){//提前结清
			List<Map<String,Object>> lst = iFinanceComputerService.getAcctInstAll(loanNo);
			for (Map map : lst) {
				String INST_STAT = (String) map.get("INST_STAT");
				if(!StringUtils.isEmpty(INST_STAT))
				{
					if(FinanceConstant.PRETREAT_STAT_LOCK.equals(INST_STAT)){
						throw new ServiceException("分期处理表状态为锁定,请稍后处理.");
					}
					if(FinanceConstant.PRETREAT_STAT_CLEARD.equals(INST_STAT)){
						throw new ServiceException("分期处理表状态为已结清,请稍后处理.");
					}
				}
			}
			transBal = iFinanceComputerService.compCurrSetAmt(loanNo, repayNum);
		} else if (FinanceConstant.ACCTFLAG_XT.equals(dgType)) { //资方扣款
			transBal = iFinanceComputerService.compCurrFunAmt(loanNo, repayNum);
		}else{//正常还款
			transBal = iFinanceComputerService.compCurrAmt(loanNo, repayNum);
		}
		//异常提示
		if(transBal.compareTo(BigDecimal.valueOf(-1)) == 0){
			throw new ServiceException("数据不存在");
		}else if(transBal.compareTo(BigDecimal.valueOf(-2)) == 0){
			throw new ServiceException("业务日期不对称");
		}else if(transBal.compareTo(BigDecimal.valueOf(-3)) == 0){
			throw new ServiceException("该笔贷款已锁定，请稍后再试");
		}else if(transBal.compareTo(BigDecimal.valueOf(-4)) == 0){
			throw new ServiceException("该笔贷款已结清");
		}
		return transBal;
	}
	
}
