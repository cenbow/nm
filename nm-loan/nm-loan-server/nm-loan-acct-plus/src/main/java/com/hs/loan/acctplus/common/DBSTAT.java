package com.hs.loan.acctplus.common;

/** 
 * <li>ClassName:DBSTAT <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2017年1月11日 <br/> 
 * <li>@author   zzy       
 */
public class DBSTAT {

	/**
	 * @Fields APP_LOAN_ACCT_STAT_REFUSE : 审批拒绝
	 */
	public final static String APP_LOAN_ACCT_STAT_REFUSE = "30201004";
	/**
	 * @Fields APP_LOAN_ACCT_STAT_PASS : 审批通过
	 */
	public final static String APP_LOAN_ACCT_STAT_PASS = "30201005";
	
	/**
	 * @Fields APP_LOAN_ACCT_STAT_WAITING_APPROVAL : 等待审批
	 */
	public final static String APP_LOAN_ACCT_STAT_WAITING_APPROVAL = "30201002";
	
	
	
	/**
	 * @Fields APP_LOAN_APPR_STAT_REFUSE : 审批拒绝
	 */
	public final static String APP_LOAN_APPR_STAT_REFUSE = "40002004";
	
	/**
	 * @Fields APP_LOAN_APPR_STAT_PASS : 审批通过
	 */
	public final static String APP_LOAN_APPR_STAT_PASS = "40002003";
	
	/**
	 * @Fields ORG_NO_SHUZUN : 	数尊
	 */
	public final static String ORG_NO_SHUZUN = "11000001";
	
	/**
	 * @Fields ORG_NO_lvyou : 旅游
	 */
	public final static String ORG_NO_LVYOU = "22000001";
	
	/**
	 * @Fields FILE_REFUSE : 直接拒绝文件号
	 */
	public final static int FILE_REFUSE = 4;
	
	/**
	 * @Fields EXPIRED_TIME_MILLISECOND : 大数据获取超时时间 2分钟
	 */
	public final static long EXPIRED_TIME_MILLISECOND = 1000*60*2;
}
