/**
 * 
 */
package com.hs.loan.contract.util;

/**
 * 科目类型-枚举
 * 本金
 * 利息
 * 服务费
 * 账户管理费
 * 保险费
 * 滞纳金
 * 手续费
 * @author jfeng
 * @create 2014-6-24
 */
public enum SubjectType {
	/**
	 * 本息
	 */
	prin_inter,
	/**
	 * 本金
	 */
	principal,
	/**
	 * 利息
	 */
	interest,
	/**
	 * 滞纳金
	 */
	overdue,
	/**
	 * 违约金
	 */
	penalty,
	/**
	 * 服务费
	 */
	service_fee,
	/**
	 * 账户管理费
	 */
	account_manage_fee,
	/**
	 * 保险费
	 */
	insurance_fee,
	/**
	 * 手续费
	 */
	procedures_fee,
	/**
	 * 委外
	 
	other_fee,*/
	/**
	 * 超额
	 */
	excess_amt,
	/**
	 * 还款包服务费
	 */
	repay_package_fee
}
