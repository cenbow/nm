package com.threeParties.ssqian.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.hs.base.exception.ServiceException;
import com.hs.utils.ParamUtils;

/**
 * 自动签名
 * @author jqiu
 */
public class AutoSign {
	
	private static Logger logger = LoggerFactory.getLogger(AutoSign.class);
	
	/**
	 * 我机构自动签名
	 * @param fsid	签名文档编号
	 * @param email	待签名方邮箱地址
	 * @param pagenum	签名的页面数
	 * @param signx		签名的x坐标比例
	 * @param signy		签名的y坐标比例
	 */
	public static void excute(String fsid,int pagenum,double signx,double signy) {
		excute(fsid, ParamUtils.getParam("send_email"), pagenum, signx, signy);
	}
	
	/**
	 * 自动签名
	 * @param fsid	签名文档编号
	 * @param email	待签名方邮箱地址
	 * @param pagenum	签名的页面数
	 * @param signx		签名的x坐标比例
	 * @param signy		签名的y坐标比例
	 */
	public static void excute(String fsid,String email,int pagenum,double signx,double signy) {
		String openflagString="1";	//1 表示合同签署结束，0表示合同签名没有结束
		
		Helper serviceHelper = new Helper("AutoSignbyCA.json");
		Map<String, Object> content = new HashMap<String, Object>();
		content.put("email", email);
		content.put("signid", fsid);
		content.put("pagenum", pagenum);
		content.put("signx", signx);
		content.put("signy", signy);
		content.put("openflag", openflagString);
		String ret = serviceHelper.doService(content);
		logger.info(fsid+","+email+"自动签名请求返回："+ret);
		
		try{
			JSONObject parseObject = JSONObject.parseObject(ret);
			JSONObject object = parseObject.getJSONObject("response");
			Integer code = (Integer)object.getJSONObject("info").get("code");
			if(100000 != code){
				throw new ServiceException("签名异常！错误代码："+code);
			}
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			throw new ServiceException("合同签名异常");
		}
	}

}