/**
 * 
 */
package com.hs.loan.contract.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 等额本息工具类
 * @author jfeng
 * @create 2014-6-28
 */
public class PmtUtil {
	/**
	 * 等额本息算法，根据分期金额，期数，分期月利率 - 计算每月还款额
	 * @param loanAmt
	 * 		分期金额
	 * @param installNum
	 * 		分期期数
	 * @param rateMonth
	 * 		月利率
	 * @return
	 */
	public static double PMT(double loanAmt, int installNum,
			double rateMonth){
		double mthFee = ArithUtil.div(rateMonth, 100, 10);
		double amt1 = ArithUtil.mul(loanAmt, mthFee);
		double amt2 = ArithUtil.add(1, mthFee);
		double amt8 = Math.pow(amt2, installNum);
		double amt9 = ArithUtil.mul(amt1, amt8);
		double amt5 = Math.pow(amt2, installNum);
		double amt6 = ArithUtil.sub(amt5, 1);
		//月还款金额
		double mthRepayAmt = ArithUtil.div(amt9, amt6);
		return mthRepayAmt;
	}
	
	/**
	 * 等额本息算法，根据分期金额，期数，当期期数，分期月利率 - 计算当期还款本息拆分数据
	 * @param loanAmt
	 * 		分期金额
	 * @param installNum
	 * 		分期期数
	 * @param rateMonth
	 * 		月利率
	 * @return
	 */
	public static Map<SubjectType, Double> getMthRepayDtMap(double loanAmt, int installNum, int repayNum,
			double rateMonth){
		if( rateMonth == 0){
			double mthFee = 0 ;
			double amt1 = ArithUtil.mul(loanAmt, mthFee);
			double amt2 = ArithUtil.add(1, mthFee);
			double amt3 = ArithUtil.sub(repayNum, 1);
			double amt4 = Math.pow(amt2, amt3);
			double amt8 = Math.pow(amt2, installNum);
			double amt7 = ArithUtil.mul(amt1, amt4);
			double amt9 = ArithUtil.mul(amt1, amt8);
			double amt5 = Math.pow(amt2, installNum);
			double amt6 = ArithUtil.sub(amt5, 1);
			//月还款本金
			double repayPrial = ArithUtil.div(loanAmt, installNum);
			//月还款金额
			double mthRepayAmt = ArithUtil.div(loanAmt, installNum);
			//月还款利息
			double repayIntst = 0;
			Map<SubjectType, Double> repayDt = new HashMap<SubjectType, Double>();
			repayDt.put(SubjectType.prin_inter, mthRepayAmt);
			repayDt.put(SubjectType.principal, repayPrial);
			repayDt.put(SubjectType.interest, repayIntst);
			return repayDt;
		}else{
			double mthFee = ArithUtil.div(rateMonth, 100, 10);
			double amt1 = ArithUtil.mul(loanAmt, mthFee);
			double amt2 = ArithUtil.add(1, mthFee);
			double amt3 = ArithUtil.sub(repayNum, 1);
			double amt4 = Math.pow(amt2, amt3);
			double amt8 = Math.pow(amt2, installNum);
			double amt7 = ArithUtil.mul(amt1, amt4);
			double amt9 = ArithUtil.mul(amt1, amt8);
			double amt5 = Math.pow(amt2, installNum);
			double amt6 = ArithUtil.sub(amt5, 1);
			//月还款本金
			double repayPrial = ArithUtil.div(amt7, amt6);
			//月还款金额
			double mthRepayAmt = ArithUtil.div(amt9, amt6);
			//月还款利息
			double repayIntst = ArithUtil.sub(ArithUtil.div(amt9, amt6),ArithUtil.div(amt7, amt6));
			Map<SubjectType, Double> repayDt = new HashMap<SubjectType, Double>();
			repayDt.put(SubjectType.prin_inter, mthRepayAmt);
			repayDt.put(SubjectType.principal, repayPrial);
			repayDt.put(SubjectType.interest, repayIntst);
			return repayDt;
		}
	}
}
