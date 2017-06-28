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
import com.hs.loan.produce.common.AuditingStat;
import com.hs.loan.produce.common.MqCache;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

public class MQApproveUtil extends MqFactory{
	//可以分离成三个类
	Logger log = Logger.getLogger(MQApproveUtil.class);
	
    public static List<String> sendAppr(List<Map<String, Object>> list){
    	
       //创建一个连接 
       Connection connection = null;
       Channel channel = null;
       List<String> result = new ArrayList<String>();
       if(list==null || list.size()==0){
    	   return result;
       }
       try {
	   		connection = factory.newConnection(); //打开连接
            channel = connection.createChannel(); //创建一个频道  
            channel.queueDeclare(APPROVE_QUEUE_NAME, durable, false, false, null);//创建一个队列
            
            for(Map<String, Object> m:list){
            	String key = sendApprDetail(channel, m);
            	result.add(key);
            	//加入缓存
            	MqCache.addSendedAppr(key,System.currentTimeMillis());
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
    private static String sendApprDetail(Channel channel,Map<String, Object> m) throws IOException{
    	
    	Map<String,Object> mm = new HashMap<String, Object>();
    	mm.put("orderId", m.get("LOAN_NO"));
    	mm.put("applyRes", getDetailStat(Integer.parseInt(m.get("STAT").toString())));
    	mm.put("remark", m.get("APPR_DESC")==null?"":m.get("APPR_DESC").toString());
//    	mm.put("dataType", AuditingStat.DATA_TYPE_APPROVE);
    	String msg = JSON.toJSONString(mm);
    	 //往队列中发出一条消息  
        channel.basicPublish("", APPROVE_QUEUE_NAME, MessageProperties.PERSISTENT_BASIC, msg.getBytes("UTF-8"));  
        return m.get("LOAN_NO").toString();
    }
    
    private static int getDetailStat(int stat){
    	switch(stat){
	    	case AuditingStat.DB_PASS_AUTO:
	    	case AuditingStat.DB_PASS_MAN:{
	    		return AuditingStat.AUDITING_PASS;//通过
	    	}
	    	case AuditingStat.DB_NOT_PASS_AUTO:
	    	case AuditingStat.DB_NOT_PASS_MAN:
	    	case AuditingStat.DB_NOT_PASS_REFUSE:{
	    		return AuditingStat.AUDITING_NOT_PASS;//拒绝
	    	}
	    	default:{
	    		//默认没找到就拒绝
	    		return AuditingStat.AUDITING_NOT_PASS;
	    	}
    	}
    }
}  
