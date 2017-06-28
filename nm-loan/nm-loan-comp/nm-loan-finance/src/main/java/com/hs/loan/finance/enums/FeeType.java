package com.hs.loan.finance.enums;

/**
 * 费用类型-枚举
 * 本金
 * 利息
 * 服务费
 * 账户管理费
 * 保险手续费
 * 滞纳金
 * 手续费
 * 委外费用
 * 还款包服务费
 * 超额还款金
 */
public enum FeeType {
	/**
	 * 本息
	 */
	PTIN_INT,
	/**
	 * 本金
	 */
	PRIN_AMT,
	/**
	 * 利息
	 */
	INT_AMT,
	/**
	 * 服务费
	 */
	SVC_AMT,
	/**
	 * 滞纳金
	 */
	OVDU_AMT,
	/**
	 * 账户管理费
	 */
	ACCT_AMT,
	/**
	 * 保险手续费
	 */
	INSU_AMT,
	/**
	 * 手续费
	 */
	FEE_AMT,
	/**
	 * 违约金
	 */
	FUN_AMT,
	/**
	 * 还款包服务费
	 */
	PACK_AMT,
	/**
	 * 委外手续费
	 */
	OUT_AMT,
	/**
	 * 超额
	 */
	EXCESS_AMT,
	
}
