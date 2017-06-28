package com.hs.loan.produce.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <li>@ClassName: TaskZKService
 * <li>@Description: 初始化zookeeper并争取leader权限
 * <li>@author zzy
 * <li>@date 2016年11月21日
 * <li>
 */
@Service
public class TaskZKService implements InitializingBean{
	
	Logger log = Logger.getLogger(this.getClass());

	// leader目录
	public static String LEADER_NODE = "/nm_mq_leader_node";
	
	@Value("${zk.path}")  
    private String zk_path; 
	
	@Value("${zk.sessTimeOut}")  
    private int sessTimeOut; 
	
	@Value("${zk.connTimeOut}")  
    private int connTimeOut; 
	
	

	private static SchedulLeader leader = null;

	@Override
	public void afterPropertiesSet() throws Exception {
		
		log.info("zk_path="+zk_path);
		log.info("sessTimeOut="+sessTimeOut);
		log.info("connTimeOut="+connTimeOut);
		
		System.out.println("zk_path="+zk_path);
		System.out.println("sessTimeOut="+sessTimeOut);
		System.out.println("connTimeOut="+connTimeOut);
		
		
		ClientFactory clientFactory=new ClientFactory(zk_path, sessTimeOut, connTimeOut, "mqServer");
		CuratorFramework framework=clientFactory.newClient();	
		if (leader == null) {
			leader = new SchedulLeader(framework, LEADER_NODE);
		}
		
	}
}
