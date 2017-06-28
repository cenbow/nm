package com.hs.loan.produce.quartz;

import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CoreTrigger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Service;

/**
 * @Description: 定时任务管理类
 * 
 */
@Service
public class QuartzManager extends ApplicationObjectSupport implements InitializingBean{
	
	private Logger logger = Logger.getLogger(QuartzManager.class);
	
	//这里初始化时可以调整性能参数
	private static StdSchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
	
	private static String JOB_GROUP_NAME = "nm_task";
	private static String TRIGGER_GROUP_NAME = "nm_group";
	
	private static String express_appr = "0 0/2 * * * ?";//每2分钟执行一次
	private static String express_acct = "0 0/10 * * * ?";//每10分钟执行一次
	private static String express_cleandata = "0 0 1 * * ?";//每天凌晨执行
	private static String express_acc_cap = "0 0 0/12 * * ?";
	
	static QuartzManager quartzManager;
	
	
	public static QuartzManager getInstance(){
		return quartzManager;
	}
	
	public  synchronized Scheduler getScheduler() throws SchedulerException{
		return gSchedulerFactory.getScheduler();
	}
	
	public  void CreateWeChatJob(String exp,Class<? extends Job> cla,Map<String,Object> params) throws SchedulerException{
		String id = UUID.randomUUID().toString();
		String jobname = "jobName"+id;
		//设置参数
		JobDataMap job = new JobDataMap();
		if(params!=null){
		 	job.putAll(params);
		}
	 	
	 	
	   JobDetail jobDetail = JobBuilder.newJob(cla).withIdentity(jobname, JOB_GROUP_NAME).usingJobData(job).build();
	   CoreTrigger strigger = (CoreTrigger) TriggerBuilder.newTrigger().withIdentity("trigger_"+id,TRIGGER_GROUP_NAME)
       		.startNow().withSchedule(CronScheduleBuilder.cronSchedule(exp)).build();
       QuartzManager.getInstance().getScheduler().scheduleJob(jobDetail, strigger);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
		quartzManager = this;
		getScheduler().start();
		CreateWeChatJob(express_appr,GetAppLoanApprInfo.class,null);
		CreateWeChatJob(express_acct,GetAppLoanAcctInfo.class,null);
		CreateWeChatJob(express_cleandata,CleanMQdb.class,null);
		CreateWeChatJob(express_acc_cap,GetAccCapWithInfo.class,null);
	}
}
