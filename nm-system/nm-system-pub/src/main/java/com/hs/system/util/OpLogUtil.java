package com.hs.system.util;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.hs.base.entity.UserProfile;
import com.hs.commons.auth.SessionUserUtil;
import com.hs.commons.constants.CommonConstant;
import com.hs.commons.tools.SpringContextHolder;
import com.hs.system.entity.SysOperaterLog;
import com.hs.system.log.OperaterLogSaveApi;
import com.hs.system.log.SysOperaterLogThread;
import com.hs.utils.DateUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;

public class OpLogUtil{
	
	private static Logger logger = LoggerFactory.getLogger(OpLogUtil.class);
	
	/**
	 *PC- 操作日志保存
	 * @param busiTyp		业务类型
	 * @param operateTyp	操作类型
	 * @param operateInfo	操作描述
	 * @param Object		业务对象(可为空，不为空时将对象转换为json串,主要为新增、修改时使用)
	 */
	public static void saveOperaterLog(String busiTyp,String operateTyp,String operateInfo,Object obj,String channel){
		saveOperaterLog(busiTyp, operateTyp, (operateInfo==null?"":operateInfo) + (obj==null?"":JSONObject.toJSON(obj)),channel);
	}
		
	/**
	 * 操作日志保存
	 * @param busiTyp		业务类型
	 * @param operateTyp	操作类型
	 * @param operateInfo	操作描述
	 * @param channel		操作渠道
	 */
	public static void saveOperaterLog(String busiTyp,String operateTyp, String operateInfo,String channel){
		try {			
			SysOperaterLog sysOperaterLog = new SysOperaterLog();
			UserProfile	currentUser = SessionUserUtil.getCurrentUser();
			sysOperaterLog.setId(RandomUtil.getUUID());
			sysOperaterLog.setStaffId(currentUser.getStaffNo());
			sysOperaterLog.setStaffName(currentUser.getStaffName());
			sysOperaterLog.setLogIp(currentUser.getLoginIp());
			sysOperaterLog.setServerIp(InetAddress.getLocalHost().getHostAddress());
			sysOperaterLog.setLogDate(DateUtils.getCurrentDate());
			sysOperaterLog.setOperateTyp(operateTyp);
			sysOperaterLog.setOperateInfo(operateInfo);
			if(StringUtils.isNotEmpty(busiTyp)){
				sysOperaterLog.setSysName(busiTyp.substring(0, 8));
				sysOperaterLog.setBusiTyp(busiTyp.substring(8, 13));
			}
			if(StringUtils.isEmpty(channel)){
				channel = CommonConstant.SALECHANL_XYD;
			}
			sysOperaterLog.setChannel(channel);
			
			OperaterLogSaveApi bean = SpringContextHolder.getBean("pubSysOperaterLogService");
			SysOperaterLogThread sysOperaterLogThread = new SysOperaterLogThread(sysOperaterLog,bean);
			sysOperaterLogThread.start();
		} catch (Exception e) {
			logger.error("操作日志写入错误",e);
		}
	}
}
