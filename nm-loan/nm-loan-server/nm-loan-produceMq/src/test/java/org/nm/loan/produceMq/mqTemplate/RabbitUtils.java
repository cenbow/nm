package org.nm.loan.produceMq.mqTemplate;

import java.io.IOException;



import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/** 
 * <li>ClassName:RabbitUtils <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年11月25日 <br/> 
 * <li>@author   zzy       
 */
public class RabbitUtils {
	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Close the given RabbitMQ Channel and ignore any thrown exception. This is useful for typical <code>finally</code>
	 * blocks in manual RabbitMQ code.
	 * @param channel the RabbitMQ Channel to close (may be <code>null</code>)
	 */
	public static void closeChannel(Channel channel) {
		if (channel != null && channel.isOpen()) {
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
