package com.threeParties.ssqian.util;

import cn.bestsign.sdk.domain.vo.BaseVO;
import cn.bestsign.sdk.domain.vo.params.ReceiveUser;
import cn.bestsign.sdk.domain.vo.params.SendUser;
import cn.bestsign.sdk.integration.Constants;
import cn.bestsign.sdk.integration.exceptions.BizException;
import cn.bestsign.sdk.integration.utils.EncodeUtils;
import cn.bestsign.sdk.integration.utils.Utils;
import cn.bestsign.sdk.integration.utils.http.HttpUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 */
public class BestSignLibs
{	
	private String mid = "45cf0eb490874ecca32552fbc44dc7b9";
	private String pem = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMQU3VOzXIBSOCzc5j6NLoqddSIOMIolFW08p1XAQ0cgoOCS9ZsIlHu/tZgKBzmC3ZvROIXWZJ7PXRtjBZHVQpbBm8n68WKbW+wwgLcKKuWOir8Keat3E+sU/gMb4d6CczCgA5tXIWAdwFSZIO6uu+FPeR1wg0U7Mm1GuFTfRoOFAgMBAAECgYA04ROXIQXPLV0s7B3DvLtScohGOOFqP/n5TaQrAgCiy+/W4IsP1k6E2PyWFg5AukQdY10E6v6TvYR0gE3eOE8OdCuw4sII4rLXRsz22fheuGNh2wm2U1tLaYnyoXV4f98Tzj91aFhrh5IrNdh4CTQME33c5Pkr/eCC5519dpyLAQJBAOFLrcAzTpDnSnvcBLNHoe2F43igV8EGJxi3GlPUjVOftLPSeXaXUgzvzpndEarvpbLLD4S+EN6KbgzG1+f2CuECQQDeze/QNbJngiGyQ68jpxXYyHQdbah1kvRSpKaf5j0Hqn9Zyu8vGyJQsh+evqLZgG5fYdTAuKWdr0HEPHHPvhElAkEAv2A/ycJLfL9b//aXb4rrvA49edwKbwbA8zemf4tQObayEwY480n7As452210cpV7VXM0TXf+cGt6rBPEl1/jQQJBALt4vJiNFhhSPtgoa22sYY2O3WUFqAGGLV58fFd++0tAAvgi8S7Jvg34UvLXpV8t2bEYOFQRCgmsNcJQudL7MqECQBIH5ol/5/gLtOqDzShX+NrWADFUnbzhWRxrhn/l3YR4DnKenbRDWlmi1TGIZIZt99P6yNfeUwr2sb4SAkgsStI=";
	private String host = "https://www.bestsign.cn";
	
	public BestSignLibs(String mid, String pem, String host) {
		HttpUtils.setDefaultUserAgent(Constants.APP_NAME + "/" + Constants.VERSION);
		this.mid = mid;
		this.pem = pem;
		this.host = (host != null && host.length() > 0) ? host : Constants.DEFAULT_HOST;
	}

	public BestSignLibs() {
		HttpUtils.setDefaultUserAgent(Constants.APP_NAME + "/" + Constants.VERSION);
	}

	/**
	 * 获取签名串
	 * @param data
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 */
	public static String getRsaSign(String pem, String...args) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException {
		byte[] data;
		try {
			data = getSignData(args).getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			data = getSignData(args).getBytes();
		}
		return Base64.encodeBase64String(EncodeUtils.rsaSign(data, pem));
	}
	
	/**
	 * 合同发送（签署人数可以不确定）
	 * 
	 * @param filename 文件名
	 * @param userlist
	 * @param senduser
	 * @param reqcontent
	 * @return
	 * @throws BadTypeException 
	 * @throws IOException 
	 * @throws SignatureException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 * @throws InvalidKeyException 
	 * @throws BizException 
	 * 
	 */
	public Map<String, Object> sjdsendcontractdocUpload(String filename, ReceiveUser[] userlist, SendUser[] senduser, byte[] reqcontent) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, BizException {
		final String method = "sjdsendcontractdocUpload.json";
		String path = "/open/" + method;
		
		if (null == filename || filename.length() == 0) {
			filename = "contract.pdf";
		}
		
		if (filename.substring(filename.length() - 4).equalsIgnoreCase(".pdf") && !Utils.isPdf(reqcontent)) {
			throw new BizException(-1, "not pdf", reqcontent);
		}
		String jsonUserList = "";
		if(userlist != null) {
			jsonUserList = this.toJSONArray(userlist).toJSONString();
		}
		String jsonSendUser = this.toJSONArray(senduser).toJSONString();
		
		//post data
		byte[] postData = reqcontent;
		
		//sign data
		String signData = getSignData(method, mid, md5(postData), urlencode(filename), jsonUserList, jsonSendUser);
		if(userlist == null) {
			signData = getSignData(method, mid, md5(postData), urlencode(filename), jsonSendUser);
		}
		
		//header data
		Map<String, String> headerData = new HashMap<String, String>();
		headerData.put("filename", filename);
		if(userlist != null) {
			headerData.put("userlist", jsonUserList);
		}
		headerData.put("senduser", jsonSendUser);
		
		return this.execute("POST", path, postData, signData, headerData, true);
	}
	
	/**
	 * 追加签署人
	 * @param signid
	 * @param userlist
	 * @return
	 * @throws BadTypeException 
	 * @throws IOException 
	 * @throws SignatureException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 * @throws InvalidKeyException 
	 */
	public Map<String, Object> sjdsendcontract(final String signid, final ReceiveUser[] userlist) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		final String method = "sjdsendcontract.json";
		String path = "/open/" + method;
		
		//post data
		@SuppressWarnings("serial")
		Map<String, Object> data = new HashMap<String, Object>() {{
			put("request", new HashMap<String, Object>() {{
				put("content", new HashMap<String, Object>() {{
					put("signid", signid); 
					put("userlist", toJSONArray(userlist));
				}});
			}});
		}};
		byte[] postData = JSONObject.toJSONString(data).getBytes("UTF-8");
		
		//sign data
		String signData = getSignData(method, mid, md5(postData));
		
		//header data
		Map<String, String> headerData = null;
		        
		return this.execute("POST", path, postData, signData, headerData, true);
	}
	
	/**
	 * 结束合同
	 * @param signId
	 * @return
	 * @throws IOException 
	 * @throws SignatureException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 * @throws InvalidKeyException 
	 */
	public Map<String, Object> endContract(final String signId) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		final String method = "endcontract.json";
		String path = "/open/" + method;
		
		//post data
		@SuppressWarnings("serial")
		Map<String, Object> data = new HashMap<String, Object>() {{
			put("request", new HashMap<String, Object>() {{
				put("content", new HashMap<String, Object>() {{
					put("signid", signId);
				}});
			}});
		}};
		byte[] postData = JSONObject.toJSONString(data).getBytes("UTF-8");
		
		//sign data
		String signData = getSignData(method, mid, md5(postData));
		
		//header data
		Map<String, String> headerData = null;
		return this.execute("POST", path, postData, signData, headerData, true);
	}
	
	/**
	 * 用户图片上传接口
	 * 
	 * @param useracount
	 * @param usermobile
	 * @param imgtype
	 * @param image
	 * @param imgName
	 * @param usertype
	 * @param username
	 * @return
	 * @throws BadTypeException 
	 * @throws IOException 
	 * @throws SignatureException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 * @throws InvalidKeyException 
	 */
	public Map<String, Object> uploaduserimage(final String useracount, final String usermobile, final String imgtype, final String image, final String imgName, final String sealname, final String updateflag, final int usertype, final String username) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		final String method = "uploaduserimage.json";
		String path = "/open/" + method;
		
		//post data
		@SuppressWarnings("serial")
		Map<String, Object> data = new HashMap<String, Object>() {{
			put("request", new HashMap<String, Object>() {{
				put("content", new HashMap<String, Object>() {{
					put("useracount", useracount == null ? "" : useracount); 
					put("usermobile", usermobile);
					put("imgtype", imgtype);
					put("image", image);
					put("imgName", imgName);
					put("sealname", sealname);
					put("updateflag", updateflag);
					put("usertype", Integer.toString(usertype));
					put("username", username);
				}});
			}});
		}};
		byte[] postData = JSONObject.toJSONString(data).getBytes("UTF-8");
		
		//sign data
		String signData = getSignData(method, mid, md5(postData));
		
		//header data
		Map<String, String> headerData = new HashMap<String, String>();
		headerData.put("Expect", "");
				        
		return this.execute("POST", path, postData, signData, headerData, true);
	}
	
	/**
	 * 用户图片查询
	 * 
	 * @param useracount
	 * @param sealname
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BadTypeException
	 */
	public Map<String, Object> queryuserimage(final String useracount, final String sealname) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		final String method = "queryuserimage.json";
		String path = "/open/" + method;
		
		//post data
		@SuppressWarnings("serial")
		Map<String, Object> data = new HashMap<String, Object>() {{
			put("request", new HashMap<String, Object>() {{
				put("content", new HashMap<String, Object>() {{
					put("useracount", useracount);
					put("sealname", sealname);
				}});
			}});
		}};
		byte[] postData = JSONObject.toJSONString(data).getBytes("UTF-8");
		
		//sign data
		String signData = getSignData(method, mid, md5(postData));
		
		//header data
		Map<String, String> headerData = null;
				        
		return this.execute("POST", path, postData, signData, headerData, true);
	}
	
	/**
	 * 手动签名接口. 返回页面url
	 * 合同签名（甲方指定位置，不生成默认签名，不允许乙方拖动）
	 * 
	 * @param fsid
	 * @param email
	 * @param pagenum
	 * @param signx
	 * @param signy
	 * @param returnurl
	 * @param typedevice
	 * @param openflagString
	 * @return
	 * @throws SignatureException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public String getSignPageSignimagePc(final String fsid, final String email, final int pagenum, final float signx, final float signy, final String returnurl, final int typedevice, final int openflagString, final String sealname, final String pushurl) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException {
		final String method = "getSignPageSignimagePc.json";
		String path = "/openpagec/" + method;
		
		//sign data
		String signData = null;
		if (sealname.length() > 0) {
			signData = getSignData(method, mid, fsid, email, Integer.toString(pagenum), Float.toString(signx), Float.toString(signy), returnurl, Integer.toString(typedevice), Integer.toString(openflagString), sealname);
		}
		else {
			signData = getSignData(method, mid, fsid, email, Integer.toString(pagenum), Float.toString(signx), Float.toString(signy), returnurl, Integer.toString(typedevice), Integer.toString(openflagString));	
		}
		
		//签名串
		String sign = getRsaSign(this.pem, signData);
		
		StringBuffer requestPath = new StringBuffer(path + "?");
		requestPath.append("mid=" + urlencode(mid) + "&");
		requestPath.append("sign=" + urlencode(sign) + "&");
		requestPath.append("fsid=" + urlencode(fsid) + "&");
		requestPath.append("email=" + urlencode(email) + "&");
		requestPath.append("pagenum=" + pagenum + "&");
		requestPath.append("signx=" + signx + "&");
		requestPath.append("signy=" + signy + "&");
		requestPath.append("returnurl=" + urlencode(returnurl) + "&");
		requestPath.append("typedevice=" + typedevice + "&");
		requestPath.append("openflagString=" + openflagString);
		if (sealname.length() > 0) {
			requestPath.append("&sealname=" + urlencode(sealname));
		}
		if (pushurl.length() > 0) {
			requestPath.append("&pushurl=" + urlencode(pushurl));
		}
		
		path = requestPath.toString();
						        
		return this.host + path;
	}
	
	/**
	 * 手动签名
	 * @param fsid
	 * @param email
	 * @param pagenum
	 * @param signx
	 * @param signy
	 * @param returnurl
	 * @param typedevice
	 * @param openflagString
	 * @param sealname
	 * @param pushurl
	 * @param pagetype
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 */
	public String getSignPageSignimagePc(final String fsid, final String email, final int pagenum, final float signx, final float signy, final String returnurl, final int typedevice, final int openflagString,final String sealname, final String pushurl, final int pagetype) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException {
		String method = "getSignPageSignimagePc.json";
		if (pagetype == 3 || pagetype == 4) {
			method = "getSignPagePc.json";
		} else if (pagetype == 5) {
			method = "getDragSignPageSignimagePc.json";
		} else if (pagetype == 6) {
			method = "getDragNoSignPagePc.json";
		}
		
		String path = "/openpagec/" + method;
		
		//sign data
		String signData = null;
		
		if (pagetype == 1 || pagetype == 4 || pagetype == 5 || pagetype == 6) {
			if (sealname.length() > 0) {
				signData = getSignData(method, mid, fsid, email, Integer.toString(pagenum), Float.toString(signx), Float.toString(signy), returnurl, Integer.toString(typedevice), Integer.toString(openflagString), sealname);
			}
			else {
				signData = getSignData(method, mid, fsid, email, Integer.toString(pagenum), Float.toString(signx), Float.toString(signy), returnurl, Integer.toString(typedevice), Integer.toString(openflagString));	
			}
		} else {
			if (sealname.length() > 0) {
				signData = getSignData(method, mid, fsid, email, returnurl, Integer.toString(typedevice), Integer.toString(openflagString), sealname);
			}
			else {
				signData = getSignData(method, mid, fsid, email, returnurl, Integer.toString(typedevice), Integer.toString(openflagString));	
			}
		}
		
		//签名串
		String sign = getRsaSign(this.pem, signData);
		
		StringBuffer requestPath = new StringBuffer(path + "?");
		requestPath.append("mid=" + urlencode(mid) + "&");
		requestPath.append("sign=" + urlencode(sign) + "&");
		requestPath.append("fsid=" + urlencode(fsid) + "&");
		requestPath.append("email=" + urlencode(email) + "&");
		if (pagetype == 1 || pagetype == 4 || pagetype == 5 || pagetype == 6) {
			requestPath.append("pagenum=" + pagenum + "&");
			requestPath.append("signx=" + signx + "&");
			requestPath.append("signy=" + signy + "&");
		}
		requestPath.append("returnurl=" + urlencode(returnurl) + "&");
		requestPath.append("typedevice=" + typedevice + "&");
		requestPath.append("openflagString=" + openflagString);
		if (sealname.length() > 0) {
			requestPath.append("&sealname=" + urlencode(sealname));
		}
		if (pushurl.length() > 0) {
			requestPath.append("&pushurl=" + urlencode(pushurl));
		}
		path = requestPath.toString();
		return this.host + path;
	}
	
	/**
	 * 自动签名接口
	 * 
	 * @param signid
	 * @param email
	 * @param pagenum
	 * @param signx
	 * @param signy
	 * @param openflag
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BadTypeException
	 */
	public Map<String, Object> AutoSignbyCA(final String signid, final String email, final int pagenum, final float signx, final float signy, final int openflag, final String sealname) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		final String method = "AutoSignbyCA.json";
		String path = "/open/" + method;
		
		//post data
		@SuppressWarnings("serial")
		Map<String, Object> data = new HashMap<String, Object>() {{
			put("request", new HashMap<String, Object>() {{
				put("content", new HashMap<String, Object>() {{
					put("email", email);
					put("signid", signid);
					put("pagenum", Integer.toString(pagenum));
					put("signx", Float.toString(signx));
					put("signy", Float.toString(signy));
					put("openflag", Integer.toString(openflag));
					put("sealname", sealname);
				}});
			}});
		}};
		byte[] postData = JSONObject.toJSONString(data).getBytes("UTF-8");
		
		//sign data
		String signData = getSignData(method, mid, md5(postData));
		
		//header data
		Map<String, String> headerData = null;
				        
		return this.execute("POST", path, postData, signData, headerData, true);
	}
	/**
	 * 查询合同详细信息
	 * @param signid
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 */
	public Map<String, Object> contractInfo(final String signid) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		final String method = "contractInfo.json";
		String path = "/open/" + method;
		
		//post data
		@SuppressWarnings("serial")
		Map<String, Object> data = new HashMap<String, Object>() {{
			put("request", new HashMap<String, Object>() {{
				put("content", new HashMap<String, Object>() {{
					put("fsdid", signid);
				}});
			}});
		}};
		byte[] postData = JSONObject.toJSONString(data).getBytes("UTF-8");
		
		//sign data
		String signData = getSignData(method, mid, md5(postData));
		
		//header data
		Map<String, String> headerData = null;
				        
		return this.execute("POST", path, postData, signData, headerData, true);
	}
	
	
	public Map<String, Object> contractList(final String status,final String email,final String starttime,final String endtime) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		final String method = "contractQuerybyEmail.json";
		String path = "/open/" + method;
		
		//post data
		@SuppressWarnings("serial")
		Map<String, Object> data = new HashMap<String, Object>() {{
			put("request", new HashMap<String, Object>() {{
				put("content", new HashMap<String, Object>() {{
					put("status", status);
					put("email", email);
					put("starttime", starttime);
					put("endtime", endtime);
				}});
			}});
		}};
		byte[] postData = JSONObject.toJSONString(data).getBytes("UTF-8");
		
		//sign data
		String signData = getSignData(method, mid, md5(postData));
		
		//header data
		Map<String, String> headerData = null;
				        
		return this.execute("POST", path, postData, signData, headerData, true);
	}
	/**
	 * CFCA证书申请
	 * 
	 * @param userType
	 * @param name
	 * @param password
	 * @param certificateType
	 * @param identType
	 * @param identNo
	 * @param duration
	 * @param address
	 * @param linkMobile
	 * @param email
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BadTypeException
	 */
	public Map<String, Object> cfcaCertificateApply(final int userType, final String name, final String password, final int certificateType, final String identType, final String identNo, final int duration, final String address, final String linkMobile, final String email) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		final String method = "cfcaCertificateApply.json";
		String path = "/open/" + method;
		
		//post data
		@SuppressWarnings("serial")
		Map<String, Object> data = new HashMap<String, Object>() {{
			put("request", new HashMap<String, Object>() {{
				put("content", new HashMap<String, Object>() {{
					put("userType", Integer.toString(userType));
					put("name", name);
					put("password", password);
					put("certificateType", Integer.toString(certificateType));
					put("identType", identType);
					put("identNo", identNo);
					put("duration", Integer.toString(duration));
					put("address", address);
					put("linkMobile", linkMobile);
					put("email", email);
				}});
			}});
		}};
		byte[] postData = JSONObject.toJSONString(data).getBytes("UTF-8");
		
		//sign data
		String signData = getSignData(method, mid, md5(postData));
		
		//header data
		Map<String, String> headerData = null;
		
		return this.execute("POST", path, postData, signData, headerData, true);
	}
	
	/**
	 * 浙江CA证书申请
	 * 
	 * @param userType
	 * @param name
	 * @param password
	 * @param linkIdCode
	 * @param icCode
	 * @param linkMan
	 * @param orgCode
	 * @param taxCode
	 * @param province
	 * @param city
	 * @param address
	 * @param linkMobile
	 * @param email
	 * @return
	 * @throws InvalidKeyException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws BadTypeException
	 */
	public Map<String, Object> zjcaCertificateApply(final int userType, final String name, final String password, final String linkIdCode, final String icCode, final String linkMan, final String orgCode, final String taxCode, final String province, final String city, final String address, final String linkMobile, final String email) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException {
		final String method = "zjcaCertificateApply.json";
		String path = "/open/" + method;
		
		//post data
		@SuppressWarnings("serial")
		Map<String, Object> data = new HashMap<String, Object>() {{
			put("request", new HashMap<String, Object>() {{
				put("content", new HashMap<String, Object>() {{
					put("userType", Integer.toString(userType));
					put("name", name);
					put("password", password);
					put("linkIdCode", linkIdCode);
					put("icCode", icCode);
					put("linkMan", linkMan);
					put("orgCode", orgCode);
					put("taxCode", taxCode);
					put("province", province);
					put("city", city);
					put("address", address);
					put("linkMobile", linkMobile);
					put("email", email);
				}});
			}});
		}};
		byte[] postData = JSONObject.toJSONString(data).getBytes("UTF-8");
		
		//sign data
		String signData = getSignData(method, mid, md5(postData));
		
		//header data
		Map<String, String> headerData = null;
		
		return this.execute("POST", path, postData, signData, headerData, true);
	}
	
	/**
	 * 合同下载地址
	 * @param fsid
	 * @return
	 * @throws SignatureException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public String getContractDownloadURL(String fsdid) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException {
		final String method = "contractDownload.page";
		String path = "/openpage/" + method;
		String status = "3";
		String signData = getSignData(method, mid, fsdid, status);
		// 签名串
		String sign = getRsaSign(this.pem, signData);

		StringBuffer requestPath = new StringBuffer(path + "?");
		requestPath.append("mid=" + urlencode(mid) + "&");
		requestPath.append("sign=" + urlencode(sign) + "&");
		requestPath.append("fsdid=" + urlencode(fsdid) + "&");
		requestPath.append("status=" + urlencode(status));
		path = requestPath.toString();
		return this.host + path;
	}
	public  String getContractDownloadURLPDF(String fsdid) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException {
		final String method = "contractDownloadMobile.page";
		String path = "/openpage/" + method;
		String status = "3";
		String signData = getSignData(method, mid, fsdid, status);
		// 签名串
		String sign = getRsaSign(this.pem, signData);

		StringBuffer requestPath = new StringBuffer(path + "?");
		requestPath.append("mid=" + urlencode(mid) + "&");
		requestPath.append("sign=" + urlencode(sign) + "&");
		requestPath.append("fsdid=" + urlencode(fsdid) + "&");
		requestPath.append("status=" + urlencode(status));
		path = requestPath.toString();
		return this.host + path;
	}
	/**
	 * 获取合同预览地址
	 * @param fsdid
	 * @param docid
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 */
	public String getContractViewURL(String fsdid,String docid) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException {
		final String method = "ViewContract.page";
		String path = "/openpage/" + method;
		String signData = getSignData(method, mid, fsdid, docid);
		// 签名串
		String sign = getRsaSign(this.pem, signData);

		StringBuffer requestPath = new StringBuffer(path + "?");
		requestPath.append("mid=" + urlencode(mid) + "&");
		requestPath.append("sign=" + urlencode(sign) + "&");
		requestPath.append("fsdid=" + urlencode(fsdid) + "&");
		requestPath.append("docid=" + urlencode(docid));
		path = requestPath.toString();
		return this.host + path;
	}
	
	/**
	 * 下载合同
	 * @param requestURL
	 * @param path
	 * @throws Exception
	 */
	public void contractDownload(String requestURL, String path) throws Exception {
		URL url = new URL(requestURL);
        SSLContext sslcontext = SSLContext.getInstance("TLS");
        sslcontext.init(null, new TrustManager[]{new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }}, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        //设置请求头
        connection.setRequestProperty("accept", "**/*//*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("Cookie", "");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:31.0) Gecko/20100101 Firefox/31.0");
        connection.setRequestMethod("GET");

        connection.connect();

        String responseCookie = connection.getHeaderField("Set-Cookie");// 取到所用的Cookie
        String sessionIdString = "";
        if (responseCookie != null) {
            sessionIdString = responseCookie.substring(0, responseCookie.indexOf(";"));
        }
        connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/octet-stream;charset=UTF-8");
        connection.setRequestProperty("Cookie", sessionIdString);// 给服务器送登录后的cookie
        connection.connect();

        InputStream in = connection.getInputStream();
        FileOutputStream out = new FileOutputStream(path);
        copy(in, out, 1024);
        out.close();
	}
	
	public Map<String, Object> execute(String method, String path, byte[] postData, String signData, Map<String, String> headerData, boolean autoRedirect) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, IOException, KeyManagementException {
		Map<String, Object> response = request(method, path, postData, signData, headerData, autoRedirect);
		String responseBody = (String) response.get("response");
		try {
			JSONObject result = JSONObject.parseObject(responseBody);
			response.put("result", result);
		}
		catch (JSONException e) {
			response.put("result", responseBody);
		}
		return response;
	}
	
	public Map<String, Object> request(String method, String path, byte[] postData, String signData, Map<String, String> headerData, boolean autoRedirect) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, KeyManagementException, IOException  {
		String url = this.host + path;
		
		//签名串
		String sign = getRsaSign(this.pem, signData);
		
		if (headerData == null) {
			headerData = new HashMap<String, String>();
		}
		
		headerData.put("mid", this.mid);
		headerData.put("sign", sign);
		
		//headers
		String[] headers = null;
		if (headerData == null || headerData.size() == 0) {
			headers = new String[1];
			headers[0] = "Content-Type: application/json; charset=UTF-8";
		}
		else {
			headers = new String[headerData.size() + 1];
			int i = 0;
			headers[i++] = "Content-Type: application/json; charset=UTF-8";
			for (String name: headerData.keySet()) {
				String value = headerData.get(name);
				String line = name + ": " + urlencode(value);
				headers[i++] = line;
			}
		}
		
		Map<String, Object> ret = null;
		if (method.equalsIgnoreCase("POST")) {
			ret = HttpUtils.post(url, postData, headers, autoRedirect);
		}
		else {
			ret = HttpUtils.get(url, headers, autoRedirect);
		}
		return ret;
	}
	
	private static String getSignData(String ...args) {
		StringBuffer buffer = new StringBuffer();
		int len = args.length;
		for (int i = 0; i < args.length; i++) {
			buffer.append(args[i]);
			if (i < len - 1) {
				buffer.append("\n");
			}
		}
		return buffer.toString();
	}
	
	private String md5(byte[] data) {
		return EncodeUtils.md5(data);
	}
	
	private String urlencode(String data) {
		try {
			return URLEncoder.encode(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return data;
		}
	}
	
	private JSONArray toJSONArray(BaseVO[] list) {
		JSONArray result = new JSONArray();
		for (int i = 0; i < list.length; i++) {
			result.add(list[i].toJSONObject());
		}
		return result;
	}
	
	private static void copy(InputStream input, OutputStream output, int bufferSize) throws IOException {
        byte[] buf = new byte[bufferSize];
        int n = input.read(buf);
        while (n >= 0) {
            output.write(buf, 0, n);
            n = input.read(buf);
        }
        output.flush();
    }
}
