package com.hs.loan.produce.dao;

import java.util.List;
import java.util.Map;

/** 
 * <li>ClassName:AppLoanApprDao <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年11月7日 <br/> 
 * <li>@author   zzy       
 */
public interface AppMqStatDao {
	
	public List<Map<String, Object>> getAll();
	
	public void batchInsertAppr(List<String> result);
	
	public void batchInsertAcct(List<Map<String,Object>> result);
	
	public void batchInsertAccCap(List<Map<String,Object>> result);
	
	//删除历史数据
	public void clearHistoryDate();
}
