package com.hs.loan.finance.enums;
/**
 * 预处理
 * @author YXS
 *
 */
public enum AcctType {
	/**
	 * 期数
	 */
	REPAY_NUM,
	/**
	 * 当日应还金额
	 */
	DAY_RCV_AMT,
	/**
	 * 当前应还金额
	 */
	CUR_RCV_AMT,
	/**
	 * 当日资方应还金额
	 */
	FUND_DAY_RCV_AMT,
	/**
	 * 当前资方余额
	 */
	FUND_CUR_RCV_AMT,
	/**
	 * 提前结清金额
	 */
	SETL_AMT
}
