package com.hs.loan.contract.server;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hs.base.entity.UserProfile;
import com.hs.base.test.BaseTest;
import com.hs.commons.attach.OssUtil;
import com.threeParties.ssqian.util.SsqSDKUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

public class ContractBuidServerTest extends BaseTest{

	@Autowired	private ContractBuidServer contractBuidServer;
	@Autowired private AppContractBuildServer appContractBuildServer;
	@Test
	public void testContractBuidServer()throws  Exception{
		buidContantSsq();
		//getSsqSignUrl();
	}

	/**
	 * 老合同测试下载
	 * @throws Exception
	 */
	private void testOldContantDown()throws Exception{
		UserProfile userProfile = new UserProfile();
		userProfile.setStaffNo("gy-yuanyd");
		byte[] bytes = contractBuidServer.oldContantDown("880104010010121133469140006", userProfile);
		ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(bytes);
		FileOutputStream fileOutputStream=new FileOutputStream("C:\\Users\\lenovo\\Desktop\\www.doc");
		IOUtils.copyLarge(byteArrayInputStream,fileOutputStream);
		IOUtils.closeQuietly(byteArrayInputStream);
		IOUtils.closeQuietly(fileOutputStream);
	}

	private void buidContantOss() throws Exception {
		UserProfile userProfile = new UserProfile();
		userProfile.setStaffNo("sjz-yanglu");
		Map map = contractBuidServer.buidContantOss("002002001022016031600013", userProfile);
		ObjectMapper objectMapper=new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String writeValueAsString = objectMapper.writeValueAsString(map);
		System.out.println("=====================================================");
		System.out.println(writeValueAsString);
		System.out.println("=====================================================");
	}


	/**
	 *@describe
	 *@author txia
	 *datetime 2016/8/26 10:54
	 *params []
	 *return void
	 */
	private void getSsqSignUrl() throws Exception {
		Map signMap=new HashedMap();
		UserProfile userProfile = new UserProfile();
		userProfile.setStaffNo("sjz-yanglu");
		signMap.put("loanNo","88010101022016040400012");
		signMap.put("signType","branch");
		signMap.put("terminal","pc");
		signMap.put("userProfile",userProfile);
		signMap.put("mobile","13551846396");
		String ssqSignUrl = contractBuidServer.getSsqSignUrl(signMap);
		System.out.println(ssqSignUrl);
	}


	/**
	 *@describe 生成合同并上传至尚尚签
	 *@author
	 *datetime 2016/8/16 14:37
	 *params []
	 *return void
	 */
	private void buidContantSsq(){
		Map paramMap=new HashedMap();
		//88010101022016040400012 chanNo 005
		paramMap.put("loanNo","001001108012016032700002");
		paramMap.put("custMobile","18702876708");
//		paramMap.put("saleMobile","15881123891");
//		paramMap.put("branchMobile","13551846396");
		UserProfile user = new UserProfile();
		user.setStaffNo("sjz-yanglu");
		paramMap.put("userProfile",user);
		try {
			Map map = appContractBuildServer.buidContantSsq(paramMap);
			ObjectMapper objectMapper=new ObjectMapper();
			objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			String writeValueAsString = objectMapper.writeValueAsString(map);
			System.out.println("=====================================================");
			System.out.println(writeValueAsString);
			System.out.println("=====================================================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getContractViewURL(){
		String contractViewURL = appContractBuildServer.getContractViewURL("88010101022016073000004");
		System.out.println(contractViewURL);
	}
	@Test
	public void testAppBuildServer() throws Exception {
		UserProfile user = new UserProfile();
//		user.setStaffNo("sjz-yanglu");
//		ObjectMapper objectMapper=new ObjectMapper();
//		try {
//			Map app = appContractBuildServer.buidContant("0020010012016031100001", user, "app");
//			System.out.println(objectMapper.writeValueAsString(app));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		String ssqSignUrl = appContractBuildServer.getSsqSignUrl("0020010012016031100001", "cust", "pc", user);
		System.out.println(ssqSignUrl);
	}
	@Test
	public void test() {
//		UserProfile user = new UserProfile();
//		user.setStaffNo("sjz-yanglu");
//		try {
//			java.util.Map map = conser.buidContant("00100110501012016031100006", user, "app");
//			System.out.println(map.get("downUrl"));
//			String ssqSignUrl = conser.getSsqSignUrl("00100110501012016031100006", "cust", "pc", user);
//			System.out.println("签名url:"+ssqSignUrl);
//			String contractDownloadURL = SsqSDKUtils.getContractDownloadURL("1467097261692O1YD2");
//			System.out.println(contractDownloadURL);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		String presignedUrl = OssUtil.getPresignedUrl("8af4a136560100e401562684d2220f5c");
		System.out.println("=======================================================");
		System.out.println(presignedUrl);
	}

}
