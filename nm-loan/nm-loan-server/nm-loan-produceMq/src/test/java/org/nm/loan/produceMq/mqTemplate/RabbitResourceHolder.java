package org.nm.loan.produceMq.mqTemplate;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/** 
 * <li>ClassName:RabbitResourceHolder <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年11月25日 <br/> 
 * <li>@author   zzy       
 */
public class RabbitResourceHolder {
	
	private Connection connection = null;
	
	private Channel channel = null;
	
	private volatile boolean isSyn = false;

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public boolean isSyn() {
		return isSyn;
	}

	public void setSyn(boolean isSyn) {
		this.isSyn = isSyn;
	}
	
	
}
