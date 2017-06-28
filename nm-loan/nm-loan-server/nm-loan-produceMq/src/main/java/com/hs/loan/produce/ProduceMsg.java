package com.hs.loan.produce;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/** 
 * <li>ClassName:ProduceMsg <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年11月7日 <br/> 
 * <li>@author   zzy       
 */
public class ProduceMsg {
	
	static Logger log = Logger.getLogger(ProduceMsg.class);
	
	public static void main(String[] args){
		
			ApplicationContext factory=new ClassPathXmlApplicationContext("classpath*:app-spring.xml");
//			ProcessCmd cmd = new ProcessCmd();
//			cmd.start();
			log.info("init ok！");
			
	}
}
