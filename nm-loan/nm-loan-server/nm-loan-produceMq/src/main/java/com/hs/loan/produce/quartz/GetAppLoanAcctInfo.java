package com.hs.loan.produce.quartz;

import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.hs.loan.produce.curator.LeaderAction;
import com.hs.loan.produce.server.AppLoanApprServer;
import com.hs.loan.produce.util.BeanFactory;

/**
 * <li>@ClassName: GetTabDataJob
 * <li>@Description: 扫描放款数据
 * <li>@author zzy
 * <li>@date 2016年11月14日
 * <li>
 */
@DisallowConcurrentExecution
public class GetAppLoanAcctInfo implements Job{
	
	private Logger logger = Logger.getLogger(GetAppLoanAcctInfo.class);
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		if(LeaderAction.isLeader()){
			logger.info("GetAppLoanAcctInfo execute.............start...............");
			AppLoanApprServer server = BeanFactory.getBean(AppLoanApprServer.class);
			server.getAndSendAppLoanAcct();
			logger.info("GetAppLoanAcctInfo execute..............end..............");
		}
		
	}
}
