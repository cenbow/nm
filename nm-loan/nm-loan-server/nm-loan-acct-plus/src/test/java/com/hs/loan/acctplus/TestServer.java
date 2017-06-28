package com.hs.loan.acctplus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hs.loan.acct.api.AcctPlusService;
import com.hs.loan.acct.dto.AppLoanAcctDto;
import com.hs.loan.acctplus.model.AppLoanAcct;
import com.hs.loan.acctplus.model.AppLoanAppr;
import com.hs.loan.acctplus.model.AppLoanHand;
import com.hs.loan.acctplus.service.AppLoanAcctService;
import com.hs.loan.acctplus.service.AppLoanApprService;
import com.hs.loan.acctplus.service.AppLoanHandService;
import com.hs.loan.acctplus.util.BeanFactory;


public class TestServer {

	public static void main(String[] args) throws SQLException {
		
		
		System.setProperty("dubbo.application.name","nm-acct-plus-Server");
		System.setProperty("dubbo.consumer.timeout","60000");
		System.setProperty("dubbo.consumer.retries","0");
		System.setProperty("dubbo.registry.address","zookeeper://192.168.3.208:2181");
		System.setProperty("dubbo.protocol.port","30881");
		
		ApplicationContext factory=new ClassPathXmlApplicationContext("classpath*:app-spring.xml"); 
		System.out.println(">>>>>>2222222>正式地址 测试启动任务调度器");
		try {
			System.in.read();
		}catch (IOException e) {
			e.printStackTrace();
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void aa(final String... args) throws Exception {

		ConnectionFactory cf = new CachingConnectionFactory();
		
		// set up the queue, exchange, binding on the broker
		RabbitAdmin admin = new RabbitAdmin(cf);
	    Map<String, Object> aaa = new HashMap<String, Object>();
	    aaa.put("x-message-ttl", 5000);//消息过期时间5秒
		Queue queue = new Queue("123", true, false, false, aaa);
		admin.declareQueue(queue);
		TopicExchange exchange = new TopicExchange("myExchange");
		admin.declareExchange(exchange);
		admin.declareBinding(
			BindingBuilder.bind(queue).to(exchange).with("foo.*"));

		// set up the listener and container
		SimpleMessageListenerContainer container =
				new SimpleMessageListenerContainer(cf);
		Object listener = new Object() {
			public void handleMessage(String foo) {
				System.out.println(foo);
			}
		};
		MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
		container.setMessageListener(adapter);
		container.setQueueNames("myQueue");
		container.start();

		// send something
		RabbitTemplate template = new RabbitTemplate(cf);
		template.convertAndSend("myExchange", "foo.bar", "Hello, world!");
		Thread.sleep(1000);
		container.stop();
	}
}
