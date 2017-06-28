package com.hs.loan.produce.server;

/** 
 * <li>ClassName:AppLoanApprServer <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年11月7日 <br/> 
 * <li>@author   zzy       
 */
public interface AppLoanApprServer{
	//审批
	public void getAndSendAppLoanAppr();
	//放款
	public void getAndSendAppLoanAcct();
	//扣款
	public void getAndSendAccCapWith();
	//逾期
	public void getAndSendAppCapWith2();
	
}
