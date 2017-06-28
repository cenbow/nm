
package com.nm.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import sun.misc.BASE64Decoder;


public class MessageUtil {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	static Random random = new Random();
	static AtomicLong serial = new AtomicLong(1);
	static final String ORDER_END_PFIX = "yyMMddHHmmssxxxxxx";
	static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");

	public static Map parserToMap(String s) {
		Map map = new HashMap();
		JSONObject json = JSONObject.parseObject(s);
		Iterator keys = (Iterator) json.keySet();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String value = json.get(key).toString();
			if (value.startsWith("{") && value.endsWith("}")) {
				map.put(key, parserToMap(value));
			} else {
				map.put(key, value);
			}

		}
		return map;
	}
	/**
	 * 构造支付请求消息
	 *
	 * @param dataMap
	 *            支付请求数据信息
	 * @return ReqMessage 支付请求消息
	 */
	// public static PayReqMsg createPayReqMsg(Map<String, String[]> dataMap)
	// throws Exception {
	//
	// // 构造支付请求消息
	// PayReqMsg reqMsg = new PayReqMsg();
	// // 构造支付请求消息头
	// ReqMsgHeader msgHeader = constructionReqMsgHeader(dataMap);
	//
	// reqMsg.setMsgHeader(msgHeader);
	//
	// // 构造支付请求消息体
	// try {
	// /** 交易流水号 */
	// String trade_sn = null;
	// if (dataMap.containsKey("trade_sn")) {
	// trade_sn = dataMap.get("trade_sn")[0];
	// }
	// /** 交易说明 */
	// String trade_desc = null;
	// if (dataMap.containsKey("trade_desc")) {
	// trade_desc = dataMap.get("trade_desc")[0];
	// }
	// /** 支付账号 */
	// String pay_account = null;
	// if (dataMap.containsKey("pay_account")) {
	// pay_account = dataMap.get("pay_account")[0];
	// }
	// /** 收款账号 */
	// String receive_account = null;
	// if (dataMap.containsKey("receive_account")) {
	// receive_account = dataMap.get("receive_account")[0];
	// }
	// /** 交易金额 */
	// BigDecimal trade_balance = null;
	// if (dataMap.containsKey("trade_balance")) {
	// trade_balance = new BigDecimal(dataMap.get("trade_balance")[0]);
	// }
	// /** 虚拟支付支付密码 */
	// String trade_psw = null;
	// if (dataMap.containsKey("trade_psw")) {
	// trade_psw = dataMap.get("trade_psw")[0];
	// }
	//
	// /** 处理状态 */
	// Integer pay_deal_status = 1;
	//
	// /** 同步返回地址URL */
	// String return_url = null;
	// if (dataMap.containsKey("return_url")) {
	// return_url = dataMap.get("return_url")[0];
	// }
	// /** 异步通知地址URL */
	// String notify_url = null;
	// if (dataMap.containsKey("notify_url")) {
	// notify_url = dataMap.get("notify_url")[0];
	// }
	//
	// /** 缺省银行（民生银行E支付请求参数） */
	// String default_bank = null;
	// if (dataMap.containsKey("default_bank")) {
	// default_bank = dataMap.get("default_bank")[0];
	// }
	// if (dataMap.containsKey("biz_back_params")) {
	// reqMsg.setBiz_back_params(dataMap.get("biz_back_params")[0]);
	// }
	// if (dataMap.containsKey("biz_extends_params")) {
	// reqMsg.setBiz_extends_params(dataMap.get("biz_extends_params")[0]);
	// }
	// if (dataMap.containsKey("auth_code")) {
	// reqMsg.setAuth_code(dataMap.get("auth_code")[0]);
	// }
	// if (dataMap.containsKey("openId")) {
	// reqMsg.setOpenId(dataMap.get("openId")[0]);
	// }
	// if (dataMap.containsKey("userId")) {
	// reqMsg.setUserId(dataMap.get("userId")[0]);
	// }
	// if (dataMap.containsKey("merchantId")) {
	// reqMsg.setMerchantId(dataMap.get("merchantId")[0]);
	// }
	// if (dataMap.containsKey("sub_merchant_no")){
	// reqMsg.setSon_merchant_no(dataMap.get("sub_merchant_no")[0]);
	// }
	// reqMsg.setReturn_url(return_url);
	// reqMsg.setNotify_url(notify_url);
	// reqMsg.setTrade_sn(trade_sn);
	// reqMsg.setTrade_desc(trade_desc);
	// reqMsg.setPay_account(pay_account);
	// reqMsg.setReceive_account(receive_account);
	// reqMsg.setTrade_balance(trade_balance);
	// reqMsg.setPay_deal_status(pay_deal_status);
	// reqMsg.setTrade_psw(trade_psw);
	// reqMsg.setDefault_bank(default_bank);
	//
	// return reqMsg;
	// } catch (Exception e) {
	// throw new Exception("Invalid message body!");
	// }
	// }

	/**
	 * 构造支付请求消息
	 *
	 * @param dataMap
	 *            支付请求数据信息
	 * @return ReqMessage 支付请求消息
	 */
	// public static ReqMessage createReqUPGGetOrg3BillsMsg(Map<String,
	// String[]> dataMap) throws Exception {
	//
	// // 构造支付请求消息
	// ReqMessage reqMsg = new ReqMessage();
	// // 构造支付请求消息头
	// ReqMsgHeader msgHeader = constructionReqMsgHeader(dataMap);
	//
	// reqMsg.setMsgHeader(msgHeader);
	//
	// // 构造下载对账单的请求消息体
	// try {
	//
	//
	// /** 交易流水号 */
	// int payWayId = -1;
	// if (dataMap.containsKey("payWayId")) {
	// payWayId = ValidationUtil.coverInt(dataMap.get("payWayId")[0]);
	// }
	// /** 交易说明 */
	// String begDate = null;
	// if (dataMap.containsKey("begDate")) {
	// begDate = dataMap.get("begDate")[0];
	// }
	//
	// /** 支付账号 */
	// String endDate = null;
	// if (dataMap.containsKey("endDate")) {
	// endDate = dataMap.get("endDate")[0];
	// }
	//
	//
	// reqMsg.setPayWayId(payWayId);
	// reqMsg.setBegDate(begDate);
	// reqMsg.setEndDate(endDate);
	//
	//
	// return reqMsg;
	// } catch (Exception e) {
	// throw new Exception("Invalid message body!");
	// }
	// }

	/**
	 * 构造支付响应消息
	 *
	 * @param dataStr
	 *            响应数据信息
	 * @return ReqMessage 响应消息
	 */
	// public static PayRspMsg createPayRspMsg(String dataStr) {
	//
	// // 按规定格式拆分协议包
	// String msg[] = dataStr.split("&");
	//
	// // 构造支付请求消息头
	// RspMsgHeader rspMsgHeader = constructionRspMsgHeader(msg[0]);
	//
	// // 构造支付请求消息
	// PayRspMsg rspMsg = new PayRspMsg();
	// rspMsg.setRspMsgHeader(rspMsgHeader);
	//
	// return rspMsg;
	// }

	/**
	 * 构造错误响应消息
	 *
	 * @param errorCode
	 *            响应数据信息
	 * @return ReqMessage 响应消息
	 */
	// public static RspMessage createErrorRspMsg(String errorCode) {
	//
	// RspMessage rspMsg = new RspMessage();
	// return rspMsg;
	// }

	/**
	 * 构造支付响应消息头
	 *
	 * @param dataStrHeader
	 *            消息头字符串
	 * @return RspMsgHeader 支付响应消息头
	 */
	// private static RspMsgHeader constructionRspMsgHeader(String
	// dataStrHeader) {
	// RspMsgHeader rspMsgHeader = new RspMsgHeader();
	// // 解析dataStrHeader操作
	// String payRspMsgHeaderParams[] = dataStrHeader.split("&");
	// rspMsgHeader.setClientId(100);
	// rspMsgHeader.setOpSn("sn10000");
	// rspMsgHeader.setServiceId(10000);
	// rspMsgHeader.setSignData(payRspMsgHeaderParams[3]);
	// return rspMsgHeader;
	// }

	/**
	 * 生成消息流水号 生成规则：服务标识(4位)+客户端ID(4位)+当前时间(17位)+流水号(6位)+随机号(1位)
	 */
	public static String createSN(String serviceId, String clientId) {
		// 时间
		String t = sdf.format(new Date());
		// 随机数
		// int max = 999999;
		// int min = 100000;
		// int i = random.nextInt(max) % (max - min + 1) + min;
		// String n = new Integer(i).toString();
		// 返回值
		String returnStr = null;
		// modify by CQ
		// returnStr = serviceId + clientId + t + n;
		returnStr = serviceId + clientId + new SimpleDateFormat("yyMMddHHmmss").format(new Date());
		for (int i = 0; i < 6; i++) {
			returnStr += random.nextInt(6);
		}
		// returnStr = serviceId + clientId + t +
		// UUID.randomUUID().toString().toUpperCase().replace("-", "");
		return returnStr;
	}

	/**
	 * 获取6位流水号，从1开始到999990
	 *
	 * @return @throws
	 */
	public static String getSerial() {
		long sn = serial.getAndIncrement();
		if (sn + 1 > 999990) {// 如果达到上限，则从0开始
			synchronized (serial) {// serial是AtomicLong类型
				if (sn + 1 > 999990) {
					serial.set(1);
				}
			}
			sn = serial.getAndIncrement();
		}
		return seiralFormat(sn, "######000000");
	}

	private static String seiralFormat(long sn, String format) {
		DecimalFormat df1 = new DecimalFormat(format);
		return df1.format(sn);
	}

	public static String nowDateFormat() {
		return sdf1.format(new Date());
	}

	/**
	 * 构造支付请求消息头
	 *
	 * @param dataMap
	 *            requestMap
	 * @return ReqMsgHeader 支付请求消息头
	 */
	// public static ReqMsgHeader constructionReqMsgHeader(Map<String, String[]>
	// dataMap) throws Exception {
	//
	// ReqMsgHeader reqMsgHeader = new ReqMsgHeader();
	// try {
	// /** 服务编号 */
	// Integer plan_id = null;
	// if (dataMap.containsKey("plan_id")) {
	// plan_id = new Integer(dataMap.get("plan_id")[0]);
	// }
	// /** 商户编号 */
	// String merchant_no = null;
	// if (dataMap.containsKey("merchant_no")) {
	// merchant_no = dataMap.get("merchant_no")[0];
	// }
	// /** 签名数据 */
	// String sign_data = null;
	// if (dataMap.containsKey("sign_data")) {
	// sign_data = dataMap.get("sign_data")[0];
	// }
	// /** 获取操作流水号 */
	// String opt_sn = null;
	// if (dataMap.containsKey("opt_sn")) {
	// opt_sn = dataMap.get("opt_sn")[0];
	// }
	// reqMsgHeader.setPlan_id(plan_id);
	// reqMsgHeader.setMerchant_no(merchant_no);
	// reqMsgHeader.setOpt_sn(opt_sn);
	// reqMsgHeader.setSign_data(sign_data);
	//
	// return reqMsgHeader;
	// } catch (Exception e) {
	// e.printStackTrace();
	// throw new Exception("Invalid message header!");
	// }
	// }

	/**
	 * 验证操作流水号的有效性
	 *
	 * @param serviceId
	 * @param merchantNo
	 * @param opsn
	 * @return
	 */
	public static boolean verifyOPSN(String serviceId, String merchantNo, String opsn) {
		// yyMMddHHmmssxxxxxx
		if (opsn.length() < 19) {
			System.out.println("订单号位数不满足要求");
			return false;
		}
		int _len = opsn.length();
		int _startPos = _len - ORDER_END_PFIX.length();
		String _validationStr = opsn.substring(_startPos, _len);
		// yyMMddHHmmss
		String timeStr = _validationStr.substring(0, "yyMMddHHmmss".length());
		String regX = "\\d{12}";
		return timeStr.matches(regX);
	}

	public static boolean verifyTime(String opsn) {
		return verifyOPSN(null, null, opsn);
	}

	public static String readFile(String filePath) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			br = new BufferedReader(new FileReader(filePath));
			String data = br.readLine();// 一次读入一行，直到读入null为文件结束
			while (data != null) {
				sb.append(data);
				data = br.readLine(); // 接着读下一行
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	// 服务ID+客户端ID+流水号+订单号+交易金额 顺序要准确
	public static String signFPayMessageRSA(String signOrgStr, String prikeyvalue) {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.getBytesBASE64(prikeyvalue));
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey myprikey = keyf.generatePrivate(priPKCS8);
			// 用私钥对信息生成数字签名
			Signature signet = Signature.getInstance("Md5WithRSA");
			signet.initSign(myprikey);
			signet.update(signOrgStr.getBytes("UTF-8"));
			byte[] signed = signet.sign(); // 对信息的数字签名
			String sign = new String(org.apache.commons.codec.binary.Base64.encodeBase64(signed));
			return sign;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public static String geneFpayMsg(Map<String, String> reqParam) {
		String signOrgStr = "";
		if (null != reqParam.get("merchant_no")
				&& !"".equals(ValidationUtil.removeNullString(reqParam.get("merchant_no")))) {
			signOrgStr += reqParam.get("merchant_no");
		}
		if (null != reqParam.get("opt_sn") && !"".equals(ValidationUtil.removeNullString(reqParam.get("opt_sn")))) {
			signOrgStr += reqParam.get("opt_sn");
		}
		if (null != reqParam.get("trade_sn") && !"".equals(ValidationUtil.removeNullString(reqParam.get("trade_sn")))) {
			signOrgStr += reqParam.get("trade_sn");
		}
		if (null != reqParam.get("trade_balance")
				&& !"".equals(ValidationUtil.removeNullString(reqParam.get("trade_balance")))) {
			signOrgStr += reqParam.get("trade_balance");
		}

		// Log.logger.debug("签名原始字符，signOrgStr="+signOrgStr);
		return signOrgStr;
	}

	/**
	 * 将请求信息转化为一个Map数据结构
	 *
	 * @param payReqMsg
	 *            请求消息
	 * @return Map Map数据集结构
	 */
	// public static Map<String, Object> payReqMsgBody2Map(PayReqMsg payReqMsg)
	// {
	// Map<String, Object> map = new HashMap<String, Object>();
	// map = payReqMsg.toMap();
	// return map;
	// }

	/**
	 * 将响应信息转化为一个Map数据结构
	 *
	 * @param rsqMessage
	 *            响应消息
	 * @return Map Map数据集结构
	 */
	// public static Map<String, Object> rspMessage2Map(RspMessage rsqMessage) {
	// Map<String, Object> map = new HashMap<String, Object>();
	//
	// return map;
	// }

	/**
	 * BCSUPG SIGN签名操作(示例方法)
	 *
	 * @param originalData
	 *            原始数据
	 * @return newData 签名后的数据
	 */
	public static String bcsupgSign(String originalData, String signData) {
		StringBuffer newData = new StringBuffer();
		newData.append("9283UYUXBCJNUYBUIUI");
		newData.append(originalData);
		for (int i = 10; i < 16; i++) {
			newData.append("I" + i + "U");
		}
		newData.append(signData);
		return newData.toString();
	}

	public static String coverObject2Jason(Object obj) {
		return "";
	}

	public static String coverObject2UrlParam(Object obj) {
		return "";
	}

	public static String coverObject2Json(Object object) {
		Gson gson = new Gson();
		String json = gson.toJson(object);
		return json;
	}

	public static Object coverJson2Object(String json, Class<?> classType) {
		Gson gson = new Gson();
		Object object = gson.fromJson(json, classType);
		return object;
	}

	/**
	 * 验证签名 服务ID+客户端ID+流水号+订单号+交易金额 顺序要准确
	 *
	 * @param signData
	 * @return false-失败,true-成功
	 */
	public static boolean verifyFPaySign(Map<String, String> reqParam, String signData, String pubKey) {
		// if(true)return true;
		String signOrgStr = geneFpayMsg(reqParam);
		System.out.println("原文：" + signOrgStr);
		// mp("要验证的报文\n" + signOrgStr);
		boolean result = false;
		try {
			// signData = signData.replaceAll("\r", "").replaceAll("\n",
			// "").replaceAll("\t", "");
			// MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCZ48Taqwh5/7wndySnw7z/AXmcS67mVc6QrRBFO7RDjYS5y73MvnII5GhBm5rkECTeQq+dU5bTtzJ54+4yt+/HwusONW/ODzRCj7LWGuT13U87vnCI7uyDRpyUwxsGSpPU5cXMDJugl5cRF0f2HpSSmp6rVhbeFm908Od5JPa0qwIDAQAB
			// MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCZ48Taqwh5/7wndySnw7z/AXmcS67mVc6QrRBFO7RDjYS5y73MvnII5GhBm5rkECTeQq+dU5bTtzJ54+4yt+/HwusONW/ODzRCj7LWGuT13U87vnCI7uyDRpyUwxsGSpPU5cXMDJugl5cRF0f2HpSSmp6rVhbeFm908Od5JPa0qwIDAQAB
			// PublicKey publicKey = RSAUtil.getPublicKey(clientPubKey);
			// result = verifyStr(signOrgStr, signData, "utf-8", publicKey);
			// String oid_str =
			// reqParam.get("opt_sn")+reqParam.get("trade_sn")+reqParam.get("total_blance");
			result = checksign(pubKey, signOrgStr, signData);
			// mp("公钥\n" + clientPubKey);
			// String pub_key=
			// "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDEwwqpi0l/R2YVcxsKU6dWXnzkIFJHyKbcXcksiXnnUVa8w+iGqxtK+elidxEHVcuU7unqYwcpvSChFFHQis6I21TAWdEgk2IHkiTCoS+m3g+B0No7xFyynQmTdPFrgSaGg0DRtNDyYnTzFGy5WjODjaVU8zDfe9gOGE0TCc6r8wIDAQAB";
			//
			// mp(pub_key);
		} catch (Exception e) {
			Logger.getLogger("\"验签异常,详细:\" + e.getMessage()");
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	/**
	 * 签名验证
	 *
	 * @param pubkeyvalue
	 *            ：公�?
	 * @param oid_str
	 *            ：源�?
	 * @param signed_str
	 *            ：签名结果串
	 * @return
	 */
	public static boolean checksign(String pubkeyvalue, String oid_str, String signed_str) {
		try {
			X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(Base64.getBytesBASE64(pubkeyvalue));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec);
			byte[] signed = Base64.getBytesBASE64(signed_str);// 这是SignatureData输出的数字签�?
			Signature signetcheck = Signature.getInstance("Md5WithRSA");
			signetcheck.initVerify(pubKey);
			signetcheck.update(oid_str.getBytes("UTF-8"));
			return signetcheck.verify(signed);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 验签使用public key
	 *
	 * @param dataStr
	 * @param signData
	 * @param charSet
	 * @param publicKey
	 * @return
	 */
	public static boolean verifyStr(String dataStr, String signData, String charSet, PublicKey publicKey) {
		BASE64Decoder decoder = new BASE64Decoder();
		boolean verifyResult = false;
		byte[] signByte;
		try {
			signByte = decoder.decodeBuffer(signData);

			Signature signetcheck = Signature.getInstance("Md5WithRSA");
			signetcheck.initVerify(publicKey);
			signetcheck.update(dataStr.getBytes(charSet));
			verifyResult = signetcheck.verify(signByte);

			return verifyResult;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 验签过程byte[]
	 *
	 * @param data
	 * @param sign
	 * @param certificatePath
	 * @return
	 * @throws Exception
	 */
	private static boolean verify(byte[] data, byte[] sign, String certificatePath, String keyPassword)
			throws Exception {
		// 获得证书
		Certificate certificate = (Certificate) getCertificate(certificatePath, keyPassword);
		// 由证书构建签名
		Signature signature = Signature.getInstance("Md5WithRSA");
		// 由证书初始化签名，实际上是使用了证书中的公钥
		signature.initVerify(certificate);
		signature.update(data);
		return signature.verify(sign);
	}

	/**
	 * 获取证书 通过P12得到
	 *
	 * @param certificatePath
	 * @return
	 * @throws Exception
	 */
	private static Certificate getCertificate(String certificatePath, String keypasswd) throws Exception {
		KeyStore ks = KeyStore.getInstance("PKCS12");
		FileInputStream fin = new FileInputStream(certificatePath);
		ks.load(fin, keypasswd.toCharArray());

		Enumeration enum1 = ks.aliases();
		String keyAlias = null;
		if (enum1.hasMoreElements()) {
			keyAlias = (String) enum1.nextElement();
		}
		Certificate cert = ks.getCertificate(keyAlias);
		return cert;
	}

	/**
	 * RSA加签名
	 *
	 * @param sign_src
	 * @param rsa_private
	 * @return
	 */
	public static String addSignRSA(String sign_src, String rsa_private) {
		Logger.getLogger("进入商户[" + sign_src + "]RSA加签名");
		if (sign_src == null) {
			return "";
		}
		// 生成待签名串
		// String sign_src = genSignData(reqObj);
		try {
			return sign2(rsa_private, sign_src);
		} catch (Exception e) {
			Logger.getLogger("商户[" + sign_src + "]RSA加签名异常" + e.getMessage());
			return "";
		}
	}

	/**
	 * 签名处理
	 *
	 * @param prikeyvalue
	 *            ：私钥
	 * @param sign_str
	 *            ：签名源内容
	 * @return
	 */
	public static String sign2(String prikeyvalue, String sign_str) {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.getBytesBASE64(prikeyvalue));
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey myprikey = keyf.generatePrivate(priPKCS8);
			// 用私钥对信息生成数字签名
			Signature signet = Signature.getInstance("Md5WithRSA");
			signet.initSign(myprikey);
			signet.update(sign_str.getBytes("UTF-8"));
			byte[] signed = signet.sign(); // 对信息的数字签名
			return new String(org.apache.commons.codec.binary.Base64.encodeBase64(signed));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(e.getMessage());
		}
		return null;
	}
}
