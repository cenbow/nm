package com.nm.impl.usercenter;


import com.nm.base.framework.core.exception.ParameterException;
import com.nm.base.framework.core.validate.Method;
import com.nm.mapper.usercenter.VersionToolMapper;
import com.nm.service.usercenter.VersionToolService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * 
 * @author wangqin
 *
 * @date 2017年5月10日 上午11:07:18
 */
@Service
public class VersionToolServiceImpl implements VersionToolService {
	
	@Resource
	private VersionToolMapper versionToolMapper;

	@Override
	public Map<String, String> queryVersionDetail(String verNum, String appType) {
		Map<String,String> mapRet=new HashMap<String,String>();
		if(Method.isBlank(verNum)){
			throw new ParameterException("版本号为空！");
		}
		if(Method.isBlank(appType)){
			throw new ParameterException("版本标志为空！");
		}
		Map<String,String> retMap=versionToolMapper.queryVersionDetail(appType);
		
		if(retMap ==null){
			throw new ParameterException("没有此版本标志！");
		}
		if(retMap.get("apkversion").toString().equals(verNum)){
			mapRet.put("isNew", "NO");
			mapRet.put("url","");
		}else{
			mapRet.put("isNew", "YES");
			if(appType.equals("ApkClient")){//上一版
				mapRet.put("url","http://mobile.lemonjinfu.com/mobileupdate/app_customer.apk");
			}
			if(appType.equals("NewApkClient")){//新版app
				mapRet.put("url","http://mobile.lemonjinfu.com/mobileupdate/new_app_customer.apk");
			}
			else{
				mapRet.put("url","");
			}
		}
		return mapRet;
	}

	@Override
	public Map<String, String> queryVersionDetailV2(String appType) {
		// TODO Auto-generated method stub
		return versionToolMapper.queryVersionDetail(appType);
	}

}
