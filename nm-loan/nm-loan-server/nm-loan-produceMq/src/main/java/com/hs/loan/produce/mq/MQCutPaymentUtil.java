package com.hs.loan.produce.mq;

/** 
 * <li>ClassName:Send <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年11月1日 <br/> 
 * <li>@author   zzy       
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.hs.loan.produce.common.MqCache;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

public class MQCutPaymentUtil extends MqFactory{
	//可以分离成三个类
	Logger log = Logger.getLogger(MQCutPaymentUtil.class);
	
	 public static List<Map<String, Object>> sendAccCap(List<Map<String, Object>> list){
	    	
	        //创建一个连接  
	        Connection connection = null;
	        Channel channel = null;
	        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
	        if(list==null || list.size()==0){
	     	   return result;
	        }
	        try {
	 	   		connection = MqFactory.factory.newConnection(); //打开连接
	             channel = connection.createChannel(); //创建一个频道  
	             channel.queueDeclare(CUTPAYMENT_QUEUE_NAME, durable, false, false, null);//创建一个队列
	             
	             for(Map<String, Object> m:list){
	             	String key = sendAccCapDetail(channel, m);
	             	result.add(m);
	             	//加入缓存
	             	MqCache.addSendedAccCap(key,System.currentTimeMillis());
	             }
	 	   	} catch (IOException e) {
	 	   		e.printStackTrace();
	 	   	}finally{
	 	           try {
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
	    		return result;  
	     }
    
   
    //返回唯一id
    private static String sendAccCapDetail(Channel channel,Map<String, Object> m) throws IOException{
    	
    	Map<String,Object> mm = new HashMap<String, Object>();
    	mm.put("orderId", m.get("LOAN_NO"));
    	mm.put("repayDate",m.get("repayDate") );
    	mm.put("repayNumber", m.get("RCV_TOTL_AMT"));
    	mm.put("cutNumber", m.get("TRAN_AMT"));
    	mm.put("cutTime", m.get("cutTime"));
    	mm.put("cutRes", 1);//有数据就是成功
    	String msg = JSON.toJSONString(mm);
    	 //往队列中发出一条消息  
        channel.basicPublish("", CUTPAYMENT_QUEUE_NAME, MessageProperties.PERSISTENT_BASIC, msg.getBytes("UTF-8"));  
        return m.get("LOAN_NO").toString();
    }
}  
