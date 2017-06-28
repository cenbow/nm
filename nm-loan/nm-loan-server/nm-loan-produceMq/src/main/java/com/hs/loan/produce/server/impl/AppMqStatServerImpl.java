package com.hs.loan.produce.server.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.loan.produce.common.MqCache;
import com.hs.loan.produce.dao.AppMqStatDao;
import com.hs.loan.produce.server.AppMqStatServer;

/** 
 * <li>ClassName:AppLoanApprServerImpl <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年11月7日 <br/> 
 * <li>@author   zzy       
 */
@Service
public class AppMqStatServerImpl implements AppMqStatServer{
	
	@Autowired
	private AppMqStatDao appMqStatDao;
	
	@Override
	public void batchInsertAppr(List<String> result) {
		
		appMqStatDao.batchInsertAppr(result);
	}

	@Override
	public void batchInsertAcct(List<Map<String, Object>> result) {
		
		appMqStatDao.batchInsertAcct(result);
	}

	@Override
	public void cleanHistoryData() {
		
		appMqStatDao.clearHistoryDate();
		//清空历史缓存
		MqCache.cleanHistoryCache();
	}

	@Override
	public void batchInsertAccCap(List<Map<String, Object>> result) {
		
		appMqStatDao.batchInsertAccCap(result);
	}
}
