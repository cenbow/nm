package com.hs.loan.prod.constants;


/**
 * 产品服务常量
 * @author jqiu
 */
public class ProdConstant {
	//书写方式
//	/*** 状态 —— 正常 */
//	public static final String STAT_ENABLE = "10000002"; 
	/*** 产品状态 —— 待售 */
 	public static final String PRODSTAT_WAITING = "20101001"; 
 	/*** 产品状态 —— 销售中 */
 	public static final String PRODSTAT_SALEING = "20101002"; 
 	/*** 产品状态 —— 失效*/
 	public static final String PRODSTAT_DISABLE = "20101003"; 
 	/*** 首付类型 —— 按比例*/
 	public static final String FSTPAYTYPE_RATE = "20103001"; 
 	/*** 首付类型 —— 按金额*/
 	public static final String FSTPAYTYPE_AMT = "20103002"; 
}
