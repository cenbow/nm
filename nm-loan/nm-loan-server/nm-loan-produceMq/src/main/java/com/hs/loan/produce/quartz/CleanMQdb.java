package com.hs.loan.produce.quartz;

import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.hs.loan.produce.curator.LeaderAction;
import com.hs.loan.produce.server.AppLoanApprServer;
import com.hs.loan.produce.server.AppMqStatServer;
import com.hs.loan.produce.util.BeanFactory;

/**
 * <li>@ClassName: GetTabDataJob
 * <li>@Description: 清理数据
 * <li>@author zzy
 * <li>@date 2016年11月14日
 * <li>
 */
@DisallowConcurrentExecution
public class CleanMQdb implements Job{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		if(LeaderAction.isLeader()){
			System.out.println("CleanMQdb execute.............start...............");
			AppMqStatServer server = BeanFactory.getBean(AppMqStatServer.class);
			server.cleanHistoryData();
			System.out.println("CleanMQdb execute..............end..............");
		}
	}
}
