package com.hs.loan.produce.mq;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hs.loan.produce.common.AuditingStat;
import com.rabbitmq.client.ConnectionFactory;

/** 
 * <li>ClassName:MqFactory <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年11月9日 <br/> 
 * <li>@author   zzy       
 */
@Component
public class MqFactory {
	
	Logger log = Logger.getLogger(this.getClass());
	//审批队列名称  
	protected static String APPROVE_QUEUE_NAME = null;
	//放款队列名
	protected static String PAYMENT_QUEUE_NAME = null;
	//扣款队列名
	protected static String CUTPAYMENT_QUEUE_NAME = null;
    
    @Value("${mq.approve.queue.name}")
    public void setAPPROVE_QUEUE_NAME(String aPPROVE_QUEUE_NAME) {
  		APPROVE_QUEUE_NAME = aPPROVE_QUEUE_NAME;
  	}
    @Value("${mq.payment.queue.name}")
  	public void setPAYMENT_QUEUE_NAME(String pAYMENT_QUEUE_NAME) {
  		PAYMENT_QUEUE_NAME = pAYMENT_QUEUE_NAME;
  	}
    @Value("${mq.cutpayment.queue.name}")
  	public void setCUTPAYMENT_QUEUE_NAME(String cUTPAYMENT_QUEUE_NAME) {
  		CUTPAYMENT_QUEUE_NAME = cUTPAYMENT_QUEUE_NAME;
  	}
    //开启队列的持久化(还要开启消息的持久化)
    protected final static boolean durable = true;
    
	protected static ConnectionFactory factory = null;
    
    @Value("${mq.host}")  
    private String mqHost;  
    
    @Value("${mq.username}")  
    private String mqUserName;  
    
    @Value("${mq.password}")  
    private String mqPassWord;  
    
    @Value("${db.staffNo}")
    private void setStffNo(String staffNo){
    	AuditingStat.STAFF_NO = staffNo;
    }
    
    @PostConstruct
    private void init(){
    	 log.info("rabbitMq init start........................................");
	     log.info("mqHost="+mqHost);
	     log.info("mqUserName="+mqUserName);
	     log.info("mqPassWord="+mqPassWord);
	     log.info("mq.approve.queue.name="+APPROVE_QUEUE_NAME);
	     log.info("mq.payment.queue.namee="+PAYMENT_QUEUE_NAME);
	     log.info("mq.cutpayment.queue.name="+CUTPAYMENT_QUEUE_NAME);
	    
	     log.info("db.staffNo="+AuditingStat.STAFF_NO);
	     
	     
	     
	     System.out.println("rabbitMq init start........................................");
	     System.out.println("mqHost="+mqHost);
	     System.out.println("mqUserName="+mqUserName);
	     System.out.println("mqPassWord="+mqPassWord);
	     System.out.println("mq.approve.queue.name="+APPROVE_QUEUE_NAME);
	     System.out.println("mq.payment.queue.namee="+PAYMENT_QUEUE_NAME);
	     System.out.println("mq.cutpayment.queue.name="+CUTPAYMENT_QUEUE_NAME);
	    
	     System.out.println("db.staffNo="+AuditingStat.STAFF_NO);
	     
	     
	     
	     //////////////////////////////////
		 factory = new ConnectionFactory();  
		 //开启断线重连
		 factory.setAutomaticRecoveryEnabled(true);
	     factory.setHost(mqHost);
	     factory.setUsername(mqUserName);
	     factory.setPassword(mqPassWord);
	     log.info("rabbitMq init started.........................................");
	}
}
