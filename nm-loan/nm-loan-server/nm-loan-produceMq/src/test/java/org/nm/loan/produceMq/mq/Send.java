package org.nm.loan.produceMq.mq;

/** 
 * <li>ClassName:Send <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年11月1日 <br/> 
 * <li>@author   zzy       
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
  
public class Send extends Thread{  
	private static String thread_name;
    //队列名称  
    private final static String QUEUE_NAME = "test5";
    //开启队列的持久化(还要开启消息的持久化)
    private final static boolean durable = true;
    
    public Send(String thread_name) {
		this.thread_name = thread_name;
	}
  
    @Override
    public void run() {
    	  ConnectionFactory factory = new ConnectionFactory();  
          factory.setHost("192.168.3.208");
          factory.setUsername("zzy");
          factory.setPassword("zzy@123");
          //创建一个连接  
        Connection connection = null;
        Channel channel = null;
		try {
			connection = factory.newConnection();
			 //创建一个频道  
             channel = connection.createChannel(); 
             boolean exclusive = false;//restricted to this connection
             boolean autoDelete = false;//server will delete it when no longer in use
             
             
             Map<String, Object> args = new HashMap<String, Object>();
//             args.put("x-message-ttl", 5000);//消息过期时间5秒
//             args.put("x-max-length", 10);//队列长度10
//             这里都不需要设置
             
//           channel.exchangeDeclare("myexchange", "direct"); //direct fanout topic  
           channel.queueDeclare(QUEUE_NAME, durable, exclusive, autoDelete, args);  
//           channel.queueBind(QUEUE_NAME, "myexchange", QUEUE_NAME);  
            
             int i=0;
             while(i<1000000){
             	 //发送的消息  
                 String message = thread_name+" hello world!"+i;  
                
                 
                 BasicProperties ee = MessageProperties.PERSISTENT_BASIC;//持久化消息
                 //往队列中发出一条消息  
                 channel.basicPublish("", QUEUE_NAME, ee, message.getBytes());  
//                 System.out.println(" [a] Sent '" + message + "'");  
             	i++;
             }
           
             
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
            try {
            	//关闭频道和连接  
            	if(channel!=null){
            		channel.close();
            	}
            	if(connection!=null){
            		connection.close();
            	}
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}  
         
          
            
    	
    }
    public static void main(String[] argv) throws java.io.IOException, TimeoutException  
    {  
      
        
        
    	Send th1 = new Send("th1");
    	Send th2 = new Send("th2");
    	Send th3 = new Send("th3");
    	Send th4 = new Send("th4");
        th1.start();
        th2.start();
        th3.start();
        th4.start();
     }  
    
}  
