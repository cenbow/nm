package org.nm.loan.produceMq.mqTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Connection;

/** 
 * <li>ClassName:MqFactory <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年11月9日 <br/> 
 * <li>@author   zzy       
 */
@Component
public class ConnectionFactory{
	
	Logger log = Logger.getLogger(this.getClass());
	
	private static Connection connectList = null;
	
	public ConnectionFactory() throws IOException {
//		connectList = MqFactory.factory.newConnection();
	}
	
	public static RabbitResourceHolder getRabbitResourceHolder() throws IOException{
		
		RabbitResourceHolder hh = new RabbitResourceHolder();
		hh.setConnection(connectList);
		hh.setChannel(connectList.createChannel());
		return hh;
	}
}
