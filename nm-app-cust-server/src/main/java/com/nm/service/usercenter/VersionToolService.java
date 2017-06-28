package com.nm.service.usercenter;

import java.util.Map;
/**
 * 
 * 
 * @author wangqin
 *
 * @date 2017年5月10日 上午11:07:28
 */
public interface VersionToolService {
	
	Map<String,String> queryVersionDetail(String verNum, String appType);
	
	Map<String,String> queryVersionDetailV2(String appType);

}
