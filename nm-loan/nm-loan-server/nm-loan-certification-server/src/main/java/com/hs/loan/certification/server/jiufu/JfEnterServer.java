package com.hs.loan.certification.server.jiufu;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.loan.certification.api.jiufu.JfEnterApi;
import com.hs.loan.certification.server.jiufu.service.JfWebService;

@Service
public class JfEnterServer implements JfEnterApi{

	@Autowired
	private JfWebService  jfWebService;
	
	
	/**
	 * 玖富客户黑名单验证
	 * 
	 * @return true 表示在玖富黑名单中，false表示没在玖富黑名单中
	 * 
	 * @param certNo 证件号
	 * @param custName 客户姓名
	 */
	public boolean validateCoustmor(String certNo,String custName){
		Map<String,String> map = new HashMap<String, String>();
		map.put("certNo", certNo);
		map.put("custName", custName);
		Boolean flag = jfWebService.validateCoustmor(map);
		return flag;
	}
	
	
}
