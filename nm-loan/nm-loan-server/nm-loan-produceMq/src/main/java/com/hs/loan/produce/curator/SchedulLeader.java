package com.hs.loan.produce.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.log4j.Logger;


/**
 * <li>@ClassName: SchedulLeader
 * <li>@Description: 获得leader权后该类被调用
 * <li>@author zzy
 * <li>@date 2016年11月21日
 * <li>
 */
public class SchedulLeader extends LeaderSelectorListenerAdapter{

	private Logger logger = Logger.getLogger(SchedulLeader.class);
	
	private  LeaderSelector leaderSelector;
	
	public SchedulLeader(CuratorFramework client, String path) {
	      leaderSelector = new LeaderSelector(client, path, this);
	      leaderSelector.autoRequeue();
	      leaderSelector.start();
	      logger.info("等待获取leader权限....");
	      System.out.println("等待获取leader权限....");
	}

	@Override
	public void takeLeadership(CuratorFramework client) throws Exception {
		logger.info("已经获得leader权限");
		System.out.println("已经获得leader权限");
		LeaderAction.initLeader();
	}
	 
}
