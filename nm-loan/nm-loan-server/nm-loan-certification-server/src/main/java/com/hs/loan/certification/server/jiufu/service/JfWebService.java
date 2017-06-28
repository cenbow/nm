package com.hs.loan.certification.server.jiufu.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.macrosky.webservice.biz.TransBody;
import com.macrosky.webservice.biz.TransHead;
import com.macrosky.webservice.biz.TransModel;
import com.macrosky.webservice.biz.ValidateMap.Map.Entry;
import com.macrosky.webservice.biz.ValidateMap;
import com.hs.loan.certification.server.jiufu.common.CommonInterFaceService;
import com.hs.loan.certification.server.jiufu.constant.JFConstants;

@Service
public class JfWebService {

	public static final Logger log  =  LoggerFactory.getLogger(JfWebService. class);
	
	@Autowired
	private CommonInterFaceService commonInterFaceService;
	
	
	/**
	 * 验证客户是否在玖富黑名单中
	 * 
	 * @param map
	 * @return
	 */
	public Boolean validateCoustmor(Map<String, String> map) {
		Boolean flag = false;
		//1、组装包体对象
		ValidateMap validateMap = new ValidateMap();
		validateMap.setCertId(map.get("certNo"));
		validateMap.setCoustomerName(map.get("custName"));
		ValidateMap.Map aa= new ValidateMap.Map(); 
		List<Entry> entry = aa.getEntry();
		Entry entry2 = new Entry();
		entry2.setKey("isBlack");
		entry2.setValue("1");
		entry.add(entry2);
		validateMap.setMap(aa);
		String validateMapStr = JSON.toJSONString(validateMap);
		log.info("sendBodyData=" + validateMapStr);
		//2、生成包头对象
		TransHead transHead = commonInterFaceService.genTransHead(JFConstants.SOURCE_CLIENT,JFConstants.TRANS_TYPE);
		String transHeadStr = JSON.toJSONString(transHead);
		log.info("sendHeadData=" + transHeadStr);
		//3、组装接口日志对象
//		TJfLogInfoVO logInfo = new TJfLogInfoVO();
//		logInfo.setServiceName(JFConstants.VALIDATE_COUSTMOR_SERVICE);
//		logInfo.setSendHeadData(transHeadStr);
//		logInfo.setSendBodyData(validateMapStr);
//		logInfo.setSendTime(new Date());
		
		//4、调用webservice服务
		TransModel transModel = commonInterFaceService.getLoanService().validateCoustmor(transHead, validateMap);
		String transModelStr = JSON.toJSONString(transModel);
		log.info("respDate=" + transModelStr);
		//5、保存日志
//		logInfo.setRespTime(new Date());
//		logInfo.setRespDate(transModelStr);
//		logInfo.setCreateTime(new Date());
//		commonInterFaceService.saveJfLogInfo(logInfo);
		//6、处理返回结果
		TransBody transBody = transModel.getTransBody();
		List<Object> entity = transBody.getEntity();
		ValidateMap object =(ValidateMap) entity.get(0);
		ValidateMap.Map vaMap = object.getMap();
		List<Entry> entry3 = vaMap.getEntry();
		Entry entry4 = entry3.get(3);//验证黑名单
		Object value = entry4.getValue();
		//isBlack（黑名单）：0(不存在，允许进件)1（存在，不允许进件）2（未进行此类校验）
		if(value != null && value.toString().equals("1"))
			flag = true;
		return flag;
	}

}
