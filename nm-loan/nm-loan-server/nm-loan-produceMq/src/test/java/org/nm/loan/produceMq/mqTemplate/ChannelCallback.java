package org.nm.loan.produceMq.mqTemplate;

import com.rabbitmq.client.Channel;

/** 
 * <li>ClassName:ChannelCallback <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年11月28日 <br/> 
 * <li>@author   zzy       
 */
public interface ChannelCallback<T> {
	
	T doInRabbit(Channel channel) throws Exception;
}
