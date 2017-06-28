package com.hs.loan.produce.common;

import java.util.ArrayList;
import java.util.List;

/** 
 * <li>ClassName:AuditingStat <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年11月8日 <br/> 
 * <li>@author   zzy       
 */
public class AuditingStat {
	
	public static final List<String> apprStatelist = new ArrayList<String>();
	
	public static final List<String> acctStatelist = new ArrayList<String>();
	
	/**
	 * @Fields AUDITING_PASS : 审批通过
	 */
	public static final int AUDITING_PASS =1;
	/**
	 * @Fields AUDITING_NOT_PASS : 审批拒绝
	 */
	public static final int AUDITING_NOT_PASS =0;
	
	
	///////////////////////////////业务类型///////////////////////////////////////
	public static final String DATA_TYPE_APPROVE = "approve";//审批
	
	public static final String DATA_TYPE_PAYMENT = "payment";//放款
	
	public static final String DATA_TYPE_CUTPAYMENT = "cutpayment";//扣款
	
	
	//////////////////////////以下为审批表对应状态码表////////////////////
	
	public static String STAFF_NO = null;
	/**
	 * @Fields DB_PASS_AUTO : 自动审批通过
	 */
	public static final int DB_PASS_AUTO = 40002003;
	/**
	 * @Fields DB_PASS_MAN : 人工审批通过
	 */
	public static final int DB_PASS_MAN = 40002007;
	
	/**
	 * @Fields DB_NOT_PASS_AUTO : 自动审批不通过
	 */
	public static final int DB_NOT_PASS_AUTO = 40002004;
	/**
	 * @Fields DB_NOT_PASS_MAN : 人工审批不通过
	 */
	public static final int DB_NOT_PASS_MAN = 40002008;
	/**
	 * @Fields DB_NOT_PASS_REFUSE : 人工审核拒绝
	 */
	public static final int DB_NOT_PASS_REFUSE = 40002009;
	
	//////////////////////////////
	public static final int DB_acct_pass = 50305002;//放款
	public static final int DB_acct_pass_return = 50305003;//放款撤销
	
	static{
		
		apprStatelist.add(String.valueOf(DB_PASS_AUTO));
		apprStatelist.add(String.valueOf(DB_PASS_MAN));
		apprStatelist.add(String.valueOf(DB_NOT_PASS_AUTO));
		apprStatelist.add(String.valueOf(DB_NOT_PASS_MAN));
		apprStatelist.add(String.valueOf(DB_NOT_PASS_REFUSE));
		
		acctStatelist.add(String.valueOf(DB_acct_pass));
		acctStatelist.add(String.valueOf(DB_acct_pass_return));
		
		
	}
	public static void main(String[] args) {
		System.out.println(AuditingStat.apprStatelist.toString().replace("[", "").replace("]", ""));
	}
	
}
