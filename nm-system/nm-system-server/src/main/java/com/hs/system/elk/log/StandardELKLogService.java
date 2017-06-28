//package com.hs.system.elk.log;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import com.hs.system.elk.util.JSONConvertUtils;
//import com.nm.elk.api.StandardELKLogApi;
//
///**
// * 
// * 提供日志接口能在(ElasticSearch+Logstash+Kibana)
// * 方便可视化查询显示
// *
// */
//@Service("standardELKLogService")
//public class StandardELKLogService implements StandardELKLogApi
//{
//	
//	/**
//	 * 
//	 * @param sourceSys 使用的源系统
//	 * @param use 主要功能
//	 * @param args  传入参数(对象,集合、数组、九类基础对象)
//	 * @param describe 描述
//	 * @param clazz 使用的类
//	 * @return true:日志保存成功
//	 *         false:日志保存失败
//	 * try{} catch{} 有可能log4j通讯链接Logstash报错，处理异常不影响正常的业务
//	 */
//	public boolean elklog(String sourceSys, String use, Object args, String describe, Class<?> clazz)
//	{
//		Logger logger = null;
//		try {
//			logger = LoggerFactory.getLogger(clazz);
//			StringBuffer sb = new StringBuffer();
//			sb.append("<sourceSys>").append(sourceSys).append("</sourceSys>");
//			sb.append("<use>").append(use).append("</use>");
//			sb.append("<args>").append(JSONConvertUtils.object2json(args)).append("</args>");
//			sb.append("<describe>").append(describe).append("</describe>");
//			logger.info(sb.toString());
//			return true;
//		} catch(Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//		return false;
//	}
//}