package com.threeParties.ssqian.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.threeParties.ssqian.bo.SignUpInfo;
import com.hs.base.exception.ServiceException;
import com.hs.loan.contract.contant.ContractContant;

/**
 * 第三方企业上传签章
 * @author jqiu
 */
public class UploadSignImg {
	private static Logger logger = LoggerFactory.getLogger(UploadSignImg.class);
	
	public static void execute(SignUpInfo signUpInfo,String filePath) {// 非注册用户公章上传
		String action = "UploadSeal2.json";
		String uploadfile = filePath + signUpInfo.getImgName();
		String mobile = signUpInfo.getMobile();
		String companyName = signUpInfo.getCompanyName();	
		String imgType = signUpInfo.getImgType();	
		String imgName = signUpInfo.getImgName();	
		String email = "";	
		String subdata = mobile + ContractContant.SIGN_SPLITSTR + companyName
				+ ContractContant.SIGN_SPLITSTR + imgType
				+ ContractContant.SIGN_SPLITSTR + imgName
				+ ContractContant.SIGN_SPLITSTR + email;
		
		Helper serviceHelper = new Helper(action);
		
		String ret = serviceHelper.updoService(uploadfile,subdata,signUpInfo);
		
		logger.info(mobile+"上传签章返回："+ret);
		
		try{
			JSONObject parseObject = JSONObject.parseObject(ret);
			JSONObject object = parseObject.getJSONObject("response");
			Integer code = (Integer)object.getJSONObject("info").get("code");
			if(100000 != code){
				throw new ServiceException("上传异常！错误代码："+code);
			}
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			throw new ServiceException("合同上传异常");
		}
	}

}
