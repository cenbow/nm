package org.nm.loan.produceMq.mq;

/** 
 * <li>ClassName:Recv <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年11月1日 <br/> 
 * <li>@author   zzy       
 */
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownListener;
import com.rabbitmq.client.ShutdownSignalException;
  
public class RecvDetail extends Thread{  
	
	 //队列名称  
    private final static String QUEUE_NAME = "test5";
    //消费者每次获取N条数据来消费，确认消费成功后才再去获取消息(消费流量控制)
    private final static int basic_qos = 100;
    //开启消息确认机制
    private final static boolean autoAck = false;
    //开启队列的持久化
    private final static boolean durable = true;
    //本次消息确认与否
    private final static int state = Action.ACCEPT;
   
	ConnectionFactory factory = null;
	
	public RecvDetail(ConnectionFactory factory) {
		System.out.println("new RecvDetail()");
		this.factory = factory;
	}
  
    @Override
    public void run() {
    	String name =  Thread.currentThread().getName();
	    System.out.println("new ---Thread.name-"+name);
        
        Connection connection = null;
        Channel channel = null;
    	try {
			connection = factory.newConnection();
			
			connection.addShutdownListener(new ShutdownListener() {
				
				@Override
				public void shutdownCompleted(ShutdownSignalException cause) {
					RecvDetail de = new RecvDetail(factory);
			        de.start();
				}
			});
			channel = connection.createChannel();
			
	        boolean exclusive = false;//restricted to this connection
	        boolean autoDelete = false;//server will delete it when no longer in use
	        channel.queueDeclare(QUEUE_NAME, durable, exclusive, autoDelete, null);
	        channel.basicQos(basic_qos);
	        System.out.println(" [m] Waiting for messages.");  
	       
	        //创建队列消费者  
	        QueueingConsumer consumer = new QueueingConsumer(channel);
	        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
//	        consumer.handleDelivery(consumerTag, envelope, properties, body);
	        while(true){
	        	
	        	
	        	QueueingConsumer.Delivery delivery = null;
	            try {
					delivery = consumer.nextDelivery();
					System.out.println("Recv Thread.name-"+name);
					System.out.println("sleep......");
					String mm = new String(delivery.getBody(),"utf-8");  
					System.out.println(" [n] Received '" + mm + "'");  
				} catch (Exception e) {
					e.printStackTrace();
				} finally{
					//每个消息必须做确认与否，不然后面收不到了
					
					switch(state){
					case Action.ACCEPT:{
						//确认消息，已经收到  
				        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
						break;
					}
					case Action.RETRY:{
						//消息确认失败，消息重回队列,这里马上就可以重新收到该消息
						channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, true);
						break;
					}
					case Action.REJECT:{
						//消息确认失败，直接丢掉该消息
						channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, false);
						break;
					}
					default:break;//消息不做任何确认情况下，断开链接再次连接上又可以获取该消息
					//每个消息都要确认，<basic_qos>占满了就会阻塞消息
				}
				}
	        }
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ShutdownSignalException e) {
			e.printStackTrace();
		} catch (ConsumerCancelledException e) {
			e.printStackTrace();
		}finally{
		      System.out.println("finally Thread.name-"+name);
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
       
}  
