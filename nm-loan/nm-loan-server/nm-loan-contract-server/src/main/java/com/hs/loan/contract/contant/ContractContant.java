package com.hs.loan.contract.contant;

public class ContractContant {

public static final String GROUP_DEFAULT = "__defaultGroup";
	 	
	/** 客户类型——个人 **/
	public static String USERTYPE_PERSION = "1";
	/** 客户类型——公司 **/
	public static String USERTYPE_CP = "2";
	
	/** 上传文件试——1表示本地文件上传  **/
	public static String USERFILETYPE_LOCAL = "1";
	
	public static final String SIGN_SPLITSTR = "\n";
	
	public static final String LOAN_UN_SIGN = "0";//分期签约-未签约
	public static final String LOAN_SIGN_ED = "1";//分期签约-已签约
	
	public static final String CHARSET_ENCODING = "UTF-8";
	
	public static final String HTTPMETHOD_POST = "POST";

	public static final String HTTPMETHOD_GET = "GET";
	
	/*** 家庭成员关系 --配偶 */
	public static final String FAM_MEMBER_REL_MATE = "40103003";
	/*** 家庭成员关系 --父亲 */
	public static final String FAM_MEMBER_REL_FATHER = "40103101";
	/*** 家庭成员关系 --母亲 */
	public static final String FAM_MEMBER_REL_MATHER = "40103102";
	/**
	 * 接口地址
	 */
	public static final String HOST = "https://www.ssqsign.com/";
	public static final String OPENAPI = HOST + "open/";
	public static final String APIPATH = HOST + "api/";
	public static final String OPENPAGEAPI = HOST + "openpage/";
	
	public static String needvideo = "0";//不需要视频
	public static String emailtitle = "合同标题";
	public static String emailcontent = "合同内容";
	public static String imgTempFilepath = "imgTempFile";
	
	public static String signimagetype = "0";	//是否默认生成签名图片"（0表示不默认生成签名图片，1表示默认生成签名图片）
	public static String selfsign = "1";		//需不需要发件人自己签署"（0表示不要自己签署，1表示要自己签署）

	public static String HTTPVERSION = "1.1";
	public static int REQUESTTIMEOUTINMILLIS = 60000;


	public static final String CONTENTTYPE_JSON = "application/json";

	public static final String SUCCESS_CODE = "100000";

}
