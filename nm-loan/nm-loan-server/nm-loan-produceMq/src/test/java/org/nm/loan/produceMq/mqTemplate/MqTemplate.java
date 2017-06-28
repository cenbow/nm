package org.nm.loan.produceMq.mqTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.rabbit.core.ChannelCallback;

import com.hs.loan.produce.common.MqCache;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

/** 
 * <li>ClassName:MqTemplate <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年11月25日 <br/> 
 * <li>@author   zzy       
 */
public class MqTemplate {
	
//	private AmqpTemplate amqpTemplate;
//	
//	public  void tt(String[] args) {
//		
//		amqpTemplate.convertAndSend(arg0);
//	}
	private void doExecute(ChannelCallback back){
		
           RabbitResourceHolder holder = null;
		try {
			holder = ConnectionFactory.getRabbitResourceHolder();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
           Channel channel = holder.getChannel();
	       try {
	    	   back.doInRabbit(channel);
		   	} catch (IOException e) {
		   		e.printStackTrace();
		   	} catch (Exception e) {
				e.printStackTrace();
			}finally{
		   		releaseResources(holder);
		   	}
	} 
	private void releaseResources(RabbitResourceHolder holder){
		RabbitUtils.closeChannel(holder.getChannel());
		RabbitUtils.closeConnection(holder.getConnection());
	}
	
	//对外
	public void send(String msg){
//		try {
//			send("", MqFactory.APPROVE_QUEUE_NAME, MessageProperties.PERSISTENT_BASIC, msg.getBytes("UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
	}
	
	private void send(final String exchange, final String routingKey, final BasicProperties base, final byte[] msg){
		doExecute(new ChannelCallback<Object>() {
			
			@Override
			public Object doInRabbit(Channel channel) throws Exception {
				doSend(channel,exchange, routingKey, base, msg);
				return null;
			}
		});
	}
	
	private void doSend(Channel channel,String exchange, String queueName, BasicProperties base, byte[] msg) throws IOException{
		channel.basicPublish(exchange, queueName, base, msg);  
	}
	public static void main(String[] args) {
		MqTemplate t = new MqTemplate();
		t.send("1111");
	}
}
