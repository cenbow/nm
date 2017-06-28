package com.hs.loan.produce.server.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.loan.produce.common.MqCache;
import com.hs.loan.produce.dao.AppLoanApprDao;
import com.hs.loan.produce.dao.AppMqStatDao;
import com.hs.loan.produce.mq.MQApproveUtil;
import com.hs.loan.produce.mq.MQCutPaymentUtil;
import com.hs.loan.produce.mq.MQPaymentUtil;
import com.hs.loan.produce.server.AppLoanApprServer;
import com.hs.loan.produce.server.AppMqStatServer;

/** 
 * <li>ClassName:AppLoanApprServerImpl <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年11月7日 <br/> 
 * <li>@author   zzy       
 */
@Service
public class AppLoanApprServerImpl implements AppLoanApprServer{
	@Autowired
	private AppLoanApprDao appLoanApprDao;
	
	@Autowired AppMqStatServer appMqStatServer;
	
	Logger log = Logger.getLogger(AppLoanApprServerImpl.class);

	@Override
	public void getAndSendAppLoanAppr(){
		
		//获取数据
		List<Map<String, Object>> list = appLoanApprDao.getApprAll();
		log.info("all msg size="+list.size());
		MqCache.distinctApprMsg(list);
		log.info("need send msg size="+list.size());
		//发送
		List<String> result = MQApproveUtil.sendAppr(list);
		//记录已经发送的数据
		appMqStatServer.batchInsertAppr(result);
		log.info("update msg size="+result.size());
		//这里不通过事物来保证数据完整性
		
	}

	@Override
	public void getAndSendAppLoanAcct() {
		//获取数据
		List<Map<String, Object>> list = appLoanApprDao.getAcctAll();
		log.info("all acct msg size="+list.size());
		MqCache.distinctAcctMsg(list);
		log.info("need acct send msg size="+list.size());
		//发送
		List<Map<String, Object>> result = MQPaymentUtil.sendAcct(list);
		//记录已经发送的数据
		appMqStatServer.batchInsertAcct(result);
		log.info("update acct msg size="+result.size());
		//这里不通过事物来保证数据完整性
	}

	@Override
	public void getAndSendAccCapWith() {
		//获取数据
		List<Map<String, Object>> list = appLoanApprDao.getKouKuanAll();
		log.info("all acc_cap msg size="+list.size());
		MqCache.distinctAccCapMsg(list);
		log.info("need acc_cap send msg size="+list.size());
		//发送
		List<Map<String, Object>> result = MQCutPaymentUtil.sendAccCap(list);
		//记录已经发送的数据
		appMqStatServer.batchInsertAccCap(result);
		log.info("update acc_cap msg size="+result.size());
		
	}

	@Override
	public void getAndSendAppCapWith2() {
		// TODO Auto-generated method stub
		
	}
	
	
}
