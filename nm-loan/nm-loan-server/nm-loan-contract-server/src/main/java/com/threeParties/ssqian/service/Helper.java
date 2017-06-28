package com.threeParties.ssqian.service;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.threeParties.ssqian.bo.SignUpInfo;
import com.threeParties.ssqian.httpclient.HttpClient;
import com.threeParties.ssqian.httpclient.HttpClientImpl;
import com.threeParties.ssqian.util.MD5Utils;
import com.threeParties.ssqian.util.SignUtil;
import com.threeParties.ssqian.util.WebUtils;
import com.hs.loan.contract.contant.ContractContant;
import com.hs.utils.ParamUtils;

public class Helper {
	private static Logger logger = LoggerFactory.getLogger(AutoSign.class);
	
	private String action = "";

	public Helper(String action) {
		this.action = action;
	}

	public String updoService(String uploadfile, 
			String subdata,String userlist, String senduser,String filename) {

		if (WebUtils.isEmpty(uploadfile)) {
			logger.error("uploadfile is null");
			return null;
		}

		if (!new File(uploadfile).exists()) {
			logger.error("uploadfile [" + uploadfile + "] is not exists");
			return null;
		}

		return doService(ContractContant.HTTPMETHOD_POST, null, uploadfile,
				subdata, userlist, senduser,filename,null);
	}
	
	public String updoService(String uploadfile,String subdata,SignUpInfo signUpInfo) {
		String filename = signUpInfo.getImgName();
		
		if (WebUtils.isEmpty(uploadfile)) {
			logger.error("uploadfile is null");
			return null;
		}
		
		if (!new File(uploadfile).exists()) {
			logger.error("uploadfile [" + uploadfile + "] is not exists");
			return null;
		}
		
		return doService(ContractContant.HTTPMETHOD_POST, null, uploadfile,
				subdata, null, null,filename,signUpInfo);
	}
	
	
	public String doService(Map<String, Object> content) {

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (content != null) {
			map.put("content", content);
		}

		String reqcontent = "";

		try {
			Map newMap = new HashMap<String,String>();
			newMap.put("request", map);
			reqcontent = JSONObject.toJSONString(newMap);
			reqcontent = reqcontent.replace(" ", "");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		return doService(ContractContant.HTTPMETHOD_POST, reqcontent, null, null,
				null, null, null,null);

	}
	
	
	private String doService(String httpMethod, String reqcontent,
			String uploadfile, String subdata,
			String userlist, String senduser,String filename,SignUpInfo signUpInfo) {

		HttpClient httpClient = null;

		try {
			httpClient = new HttpClientImpl(ContractContant.OPENAPI);
			httpClient.setUrl(ContractContant.OPENAPI + action);
			httpClient.setHttpVersion(ContractContant.HTTPVERSION);
			httpClient.setHttpContentCharset(ContractContant.CHARSET_ENCODING);
			httpClient
					.setRequestTimeoutInMillis(ContractContant.REQUESTTIMEOUTINMILLIS);

			httpClient.setHttpMethod(httpMethod);

			String reqmd5 = "";
			if (WebUtils.isNotEmpty(reqcontent)) {
				reqmd5 = MD5Utils.md5(reqcontent).toLowerCase();
			} else {
				reqmd5 = MD5Utils.md5File(uploadfile);
			}

			httpClient = setAPIHeader(httpClient, reqmd5, subdata,
					 userlist, senduser, filename,signUpInfo);
			logger.info("ssq request subdata==========:"+subdata);
			logger.info("ssq request userlist==========:"+userlist);
			logger.info("ssq request senduser==========:"+senduser);
			
			if (WebUtils.isNotEmpty(reqcontent)) {
				httpClient.setEntity(reqcontent,
						ContractContant.CONTENTTYPE_JSON,
						ContractContant.CHARSET_ENCODING);
			} else {
				httpClient.setEntity(uploadfile);
			}

			logger.info("ssq request==========:"+reqcontent);
			httpClient.execute();

			String contentStr = new String(httpClient.getContentStr());
			logger.info("ssq resonse=========:"+contentStr);
			return contentStr;
		} catch (Exception e) {
			if (httpClient != null) {
				httpClient.abortExecution();
			}
		} finally {
			if (httpClient != null) {
				httpClient.shutdown();
			}
		}

		return null;
	}
	
	
	
	private HttpClient setAPIHeader(HttpClient httpClient, String reqmd5,
			 String subdata, 
			String userlist, String senduser,String filename,SignUpInfo signUpInfo) throws Exception {

		httpClient.addHeader("mid", ParamUtils.getParam("MID"));//"E0000000000000000147"
		
		Map<String, Object> upcontent = new LinkedHashMap<String, Object>();
		if (upcontent != null) {
			httpClient.addHeaderObj(upcontent);
		}

		StringBuilder signdata = new StringBuilder();
		signdata.append(action);
		signdata.append(ContractContant.SIGN_SPLITSTR + ParamUtils.getParam("MID"));//ParamUtils.getParam("MID")
		signdata.append(ContractContant.SIGN_SPLITSTR + reqmd5);

		if (WebUtils.isNotEmpty(subdata)) {
			signdata.append(ContractContant.SIGN_SPLITSTR + subdata);
		}

		String sign = SignUtil.sign(signdata.toString()).trim();

		if("sjdsendcontractdocUpload.json".equalsIgnoreCase(action)){		//上传
			httpClient.addHeader("filename", filename);
			httpClient.addHeader("userlist", userlist);
			httpClient.addHeader("senduser", senduser);
			httpClient.addHeader("sign", sign);
		}else if("AutoSignbyCA.json".equalsIgnoreCase(action)){	//自动签名
			httpClient.addHeader("sign", sign);
		}else if("UploadSeal2.json".equalsIgnoreCase(action)){				//签章上传
			httpClient.addHeader("sign", sign);
			httpClient.addHeader("mobile", signUpInfo.getMobile());
			httpClient.addHeader("imgName", signUpInfo.getImgName());
			httpClient.addHeader("imgType", signUpInfo.getImgType());
			httpClient.addHeader("companyName", signUpInfo.getCompanyName());
			httpClient.addHeader("email", "");
		}

		return httpClient;
	}
}
