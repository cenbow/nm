package com.hs.loan.produce.dao;

import java.util.List;
import java.util.Map;

/** 
 * <li>ClassName:AppLoanApprDao <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年11月7日 <br/> 
 * <li>@author   zzy       
 */
public interface AppLoanApprDao {
	
	public List<Map<String, Object>> getApprAll();
	
	public List<Map<String, Object>> getAcctAll();
	
	public List<Map<String, Object>> getKouKuanAll();
	
	public List<Map<String, Object>> getAccCapWithAll2();
}
