package org.nm.loan.produceMq.mq;

/** 
 * <li>ClassName:Recv <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年11月1日 <br/> 
 * <li>@author   zzy       
 */
import com.rabbitmq.client.ConnectionFactory;
  
public class Recv extends Thread{  
	
    @Override
    public void run() {
    	 //打开连接和创建频道，与发送端一样  
        ConnectionFactory factory = new ConnectionFactory();
        factory.setAutomaticRecoveryEnabled(true);
        factory.setHost("192.168.3.208");
        factory.setUsername("zzy");
        factory.setPassword("zzy@123");
        factory.setRequestedHeartbeat(10);
        
        
        RecvDetail re = new RecvDetail(factory);
        re.start();
        
       String name =  Thread.currentThread().getName();
       System.out.println("main Thread.name-"+name);
    }
    public static void main(String[] args) {
    	Recv r = new Recv();
//    	Recv r2 = new Recv();
    	r.start();
//    	r2.start();
	}
}  
