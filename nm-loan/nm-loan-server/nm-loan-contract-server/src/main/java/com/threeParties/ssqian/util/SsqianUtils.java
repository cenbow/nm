package com.threeParties.ssqian.util;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.threeParties.ssqian.bo.ContrSendUserInfo;
import com.threeParties.ssqian.bo.SignUpInfo;
import com.threeParties.ssqian.service.AutoSign;
import com.threeParties.ssqian.service.GetSign;
import com.threeParties.ssqian.service.Helper;
import com.threeParties.ssqian.service.UploadSignImg;
import com.hs.base.exception.ServiceException;
import com.hs.loan.contract.contant.ContractContant;
import com.hs.utils.ParamUtils;

/**
 * 上上签工具类
 * @author jqiu
 *
 */
public class SsqianUtils {

	/**
	 * 合同上传
	 * @param docPath		合同路径
	 * @param custName		名称
	 * @param custMobile	客户手机号
	 * @return 合同编号
	 */
	public static String uploadContract(String docPath,String custName, String custMobile) {
		return uploadContract(docPath, custName,custMobile, null,null);
	}
	
	
	/**
	 * 合同上传
	 * @param docPath	合同路径
	 * @param custName		名称
	 * @param custMobile	客户手机号
	 * @param custName		第三方企业名称
	 * @param custMobile	第三方企业手机号
	 * @return 合同编号
	 */
	public static String uploadContract(String docPath,String custName, String custMobile,String cpName, String cpMobile) {
		String action = "sjdsendcontractdocUpload.json";
		String uploadfile = docPath;
		File file = new File(docPath);
		String filename = file.getName();
		
		ContrSendUserInfo formUser = new ContrSendUserInfo();
		formUser.setEmail(ParamUtils.getParam("send_email"));//it_service@tksincerity.com
		formUser.setEmailcontent(ContractContant.emailcontent);
		formUser.setEmailtitle(ContractContant.emailtitle);
		formUser.setMobile(ParamUtils.getParam("send_mobile"));//ParamUtils.getParam("send_mobile")
		formUser.setName(ParamUtils.getParam("send_name"));//ParamUtils.getParam("send_name")
		formUser.setNeedvideo(ContractContant.needvideo);		//不需要视频。好像无用
		formUser.setSelfsign(ContractContant.selfsign);
		formUser.setSignimagetype(ContractContant.signimagetype);
		formUser.setUserfileType(ContractContant.USERFILETYPE_LOCAL);
		formUser.setSxdays(ParamUtils.getParam("sxdays"));			//有效天数 ParamUtils.getParam("sxdays")
		formUser.setUsertype(ContractContant.USERTYPE_CP);
		
		List<Map<String, Object>> contractlistSend = new ArrayList<Map<String, Object>>();
		Map<String, Object> contrinfoMapSend = new LinkedHashMap<String, Object>();
		contrinfoMapSend.put("email", formUser.getEmail());
		contrinfoMapSend.put("emailtitle", StringUtils.isEmpty(formUser.getEmailtitle())?"邮件标题":formUser.getEmailtitle());
		contrinfoMapSend.put("emailcontent", StringUtils.isEmpty(formUser.getEmailcontent())?"邮件内容":formUser.getEmailcontent());
		contrinfoMapSend.put("sxdays", formUser.getSxdays());
		contrinfoMapSend.put("selfsign", formUser.getSelfsign());
		contrinfoMapSend.put("name", formUser.getName());
		contrinfoMapSend.put("mobile", formUser.getMobile());
		contrinfoMapSend.put("usertype", formUser.getUsertype());
		contrinfoMapSend.put("Signimagetype", formUser.getSignimagetype());		
		contrinfoMapSend.put("UserfileType", formUser.getUserfileType());
		contractlistSend.add(contrinfoMapSend);

		
		List<Map<String, Object>> contractlistUser = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> contrinfoMapUser = new LinkedHashMap<String, Object>();
		contrinfoMapUser.put("email", "");
		contrinfoMapUser.put("name", custName);
		contrinfoMapUser.put("needvideo", ContractContant.needvideo);
		contrinfoMapUser.put("mobile", custMobile);
		contrinfoMapUser.put("usertype", ContractContant.USERTYPE_PERSION);
		contrinfoMapUser.put("Signimagetype", ContractContant.signimagetype);
		contractlistUser.add(contrinfoMapUser);
		
		if(!StringUtils.isEmpty(cpMobile) && !StringUtils.isEmpty(cpName)){
			Map<String, Object> contrinfoMapCpUser = new LinkedHashMap<String, Object>();
			contrinfoMapCpUser.put("email", "");
			contrinfoMapCpUser.put("name", cpName);
			contrinfoMapCpUser.put("needvideo", ContractContant.needvideo);
			contrinfoMapCpUser.put("mobile", cpMobile);
			contrinfoMapCpUser.put("usertype", ContractContant.USERTYPE_CP);
			contrinfoMapCpUser.put("Signimagetype", ContractContant.signimagetype);
			contractlistUser.add(contrinfoMapCpUser);
		}
	
		String userlist=JSONArray.toJSONString(contractlistUser);
		String senduser=JSONArray.toJSONString(contractlistSend);
		String subdata = filename + ContractContant.SIGN_SPLITSTR + userlist + ContractContant.SIGN_SPLITSTR + senduser;
		String tmpstring = doService(action,subdata,senduser,userlist,uploadfile,filename).toString();
		if(StringUtils.isEmpty(tmpstring)){
			throw new ServiceException("合同上传异常");
		}
		
		try{
			JSONObject json = toJson(tmpstring);
			JSONObject object = json.getJSONObject("response");
			Integer code = (Integer)object.getJSONObject("info").get("code");
			if(100000 != code){
				throw new ServiceException("上传异常！错误代码："+code);
			}
			JSONArray jsonArray = object.getJSONObject("content").getJSONArray("contlist");
			if(jsonArray.size() == 0){
				throw new ServiceException("返回数据为空！");
			}
			return jsonArray.getJSONObject(0).getJSONObject("continfo").getString("signid");
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			throw new ServiceException("合同上传异常");
		}
	}


	/**
	 * 获取签名URL
	 * @param fsid	签名文档编号
	 * @param custMobile	客户电话号码
	 * @param templateId	模板编号
	 * @return 签名URL
	 */
	public static String getSignUrl(String fsid,String custMobile,int pageNum,float signX,float signY){
		int pagenum = pageNum;
		float signx = signX;
		float signy = signY;
		return GetSign.excute(fsid, custMobile, pagenum, signx, signy);
	}
	
	
	/**
	 * 下载合同
	 * @param fsid	签名文档编号
	 * @param custMobile	客户电话号码
	 * @param templateId	模板编号
	 * @return 签名URL
	 */
	public static String downloadContract(String fsid){
		return DownloadContract.excute(fsid);
	}

	/**
	 * 自动签名
	 * @param fsid	签名文档编号
	 * @param email	待签名方邮箱地址
	 * @param templateId	模板编号
	 */
	public static void autoSign(String fsid,String email,int pageNum,float signX,float signY){
			int pagenum = pageNum;
			float signx = signX;
			float signy = signY;
			AutoSign.excute(fsid, email, pagenum, signx, signy);
	}
	
	/**
	 * 上传签章
	 * @param fsid	签名文档编号
	 * @param email	待签名方邮箱地址
	 * @param templateId	模板编号
	 */
	public static void uploadSignImg(SignUpInfo signUpInfo){
		UploadSignImg.execute(signUpInfo,ContractContant.imgTempFilepath);//ContractContant.imgTempFilepath)
	}
	
	private static Object doService(String action,String subdata,String senduser,String userlist,String uploadfile,String filename) {
		Helper serviceHelper = new Helper(action);
		
		JSONObject jsonObject = toJson(serviceHelper.updoService(uploadfile,
				subdata,userlist,senduser,filename));
		return jsonObject;
	}
	private static JSONObject toJson(String content) {
		content = WebUtils.nullToStrTrim(content);
		if (WebUtils.isEmpty(content)) {
			return null;
		}
		JSONObject parseObject = JSONObject.parseObject(content);
		return parseObject;
	}
}
