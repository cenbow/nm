package com.hs.loan.produce.curator;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.hs.loan.produce.common.MqCache;
import com.hs.loan.produce.util.ScheduleUtil;

/**
 * <li>@ClassName: LeaderAction
 * <li>@Description: 初始化leader后 在这里处理
 * <li>@author zzy
 * <li>@date 2016年11月21日
 * <li>
 */
public class LeaderAction {

	private static Logger logger = Logger.getLogger(LeaderAction.class);

	// 当前为leader状态
	private volatile static Boolean isLeader = false;
	private final static Lock lock = new ReentrantLock();
	private static Condition isworking = lock.newCondition();

	// on leader 获得主权时 初始化leader
	public static void initLeader() {
		logger.info("ip 为" + ScheduleUtil.getLocalIP() + "获得leader 权限");
		System.out.println("ip 为" + ScheduleUtil.getLocalIP() + "获得leader 权限");
		lock.lock();
		try {
			
			MqCache.getInstance().initCache();
			isLeader = true;
			while(isLeader){
				isworking.await();
			}
		} catch (Exception e) {
			logger.error("初始化leader异常", e);
			e.printStackTrace();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		} finally {
			lock.unlock();
			isLeader = false;
			logger.info("leader 已经失去leader权限");
		}
	}
	public static boolean isLeader(){
		return isLeader.booleanValue();
	}
}
