package com.threeParties.ssqian.service;

import com.threeParties.ssqian.util.SignUtil;
import com.hs.base.exception.ServiceException;
import com.hs.loan.contract.contant.ContractContant;
import com.hs.utils.ParamUtils;

/**
 * 获取签名URL
 * @author jqiu
 */
public class GetSign {
	
	/**
	 * 获取签名URL
	 * @param fsid	签名文档编号
	 * @param email	待签名方邮箱地址
	 * @param pagenum	签名的页面数
	 * @param signx		签名的x坐标比例
	 * @param signy		签名的y坐标比例
	 */
	public static String excute(String fsid,String email, int pagenum, double signx, double signy){	//获取签署页面
		String typedevice = "1";	// 1表示返回PC端签名页面，2表示返回移动端签名页面
		String returnurl = ParamUtils.getParam("returnurl");//"120.26.231.31:9000/approve/ssqianCallback";
		String url = ContractContant.HOST + "openpage2/";
		return getsignurl(url, "getSignPageSignimagePc.json", fsid, email, pagenum, signx, signy,typedevice, returnurl);

	}
	public static String getSsqSignUrl(String fsid,String email, int pagenum, double signx, double signy,String terminal,String returnType){	//获取签署页面
		String typedevice = "2";	// 1表示返回PC端签名页面，2表示返回移动端签名页面
		if(null!=terminal&&"pc".equals(terminal)){
			typedevice = "1";
		}
		String returnurl = ParamUtils.getParam("custreturnurl");//"120.26.231.31:9000/approve/ssqianCallback";
		if(null!=returnType&&"cust".equals(returnType)){
			returnurl = ParamUtils.getParam("custreturnurl");
		}else if(null!=returnType&&"sale".equals(returnType)){
			returnurl = ParamUtils.getParam("salereturnurl");
		}else if(null!=returnType&&"branch".equals(returnType)){
			returnurl = ParamUtils.getParam("branchreturnurl");
		}
		String url = ContractContant.HOST + "openpage2/";
		return getsignurl(url, "getSignPageSignimagePc.json", fsid, email, pagenum, signx, signy,typedevice, returnurl);

	}

	private static String getsignurl(String url, String action, String fsid,
			String email, int pagenum, double signx, double signy,
			String typedevice, String returnurl) {// （甲方指定位置，不生成默认签名，不允许乙方拖动）
		url = url + action;
		StringBuilder signdata = new StringBuilder();
		signdata.append(action);
		signdata.append(ContractContant.SIGN_SPLITSTR + ParamUtils.getParam("MID"));//ParamUtils.getParam("MID")
		signdata.append(ContractContant.SIGN_SPLITSTR + fsid);
		signdata.append(ContractContant.SIGN_SPLITSTR + email);
		signdata.append(ContractContant.SIGN_SPLITSTR + pagenum);
		signdata.append(ContractContant.SIGN_SPLITSTR + signx);
		signdata.append(ContractContant.SIGN_SPLITSTR + signy);
		signdata.append(ContractContant.SIGN_SPLITSTR + returnurl);
		signdata.append(ContractContant.SIGN_SPLITSTR + typedevice);
		String sign = null;
		try {
			sign = SignUtil.sign(signdata.toString()).trim();
		} catch (Exception e) {
			throw new ServiceException("数据异常");
		}
		sign = java.net.URLEncoder.encode(sign);
		String geturl = url + "?fsid=" + fsid + "&pagenum=" + pagenum
				+ "&signx=" + signx + "&signy=" + signy + "&typedevice="
				+ typedevice + "&returnurl=" + returnurl + "&email=" + email
				+ "&mid=" + ParamUtils.getParam("MID") + "&sign=" + sign;//ParamUtils.getParam("MID")
		return geturl;
	}
	
}