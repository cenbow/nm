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
 * <li>@Description: 扫描扣款数据<同一笔单子每个月都会扣款，但是这里缓存每隔段时间就会清楚，所以不存在第二次扣款不发送的问题>
 * <li>@author zzy
 * <li>@date 2016年11月14日
 * <li>
 */
@DisallowConcurrentExecution
public class GetAccCapWithInfo implements Job{
	
	private Logger logger = Logger.getLogger(GetAccCapWithInfo.class);
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		if(LeaderAction.isLeader()){
			logger.info("GetAccCapWithInfo execute.............start...............");
			AppLoanApprServer server = BeanFactory.getBean(AppLoanApprServer.class);
			server.getAndSendAccCapWith();
			logger.info("GetAccCapWithInfo execute..............end..............");
		}
		
	}
}
