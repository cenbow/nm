package com.hs.loan.produce.server;

import java.util.List;
import java.util.Map;

/** 
 * <li>ClassName:AppLoanApprServer <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年11月7日 <br/> 
 * <li>@author   zzy       
 */
public interface AppMqStatServer{
	
	public void batchInsertAppr(List<String> result);
	
	public void batchInsertAcct(List<Map<String,Object>> result);
	
	public void batchInsertAccCap(List<Map<String,Object>> result);
	
	//删除hour小时前的数据
	public void cleanHistoryData();
	
}
