package com.threeParties.ssqian.util;

import com.hs.base.exception.ServiceException;
import com.hs.loan.contract.contant.ContractContant;
import com.hs.utils.ParamUtils;

/**
 * 合同下载
 * @author jqiu
 *
 */
public class DownloadContract {
	/**
	 * 获取下载URL
	 * @param fsid	签名文档编号
	 */
	public static String excute(String fsid){						//获取签署页面
		String url = ContractContant.HOST + "openpage/";
		return geturl(url, "contractDownloadMobile.page", fsid);
	}


	private static String geturl(String url,String action,String fsid) {
		url = url + action;
		StringBuilder signdata = new StringBuilder();
		signdata.append(action);
		signdata.append(ContractContant.SIGN_SPLITSTR + ParamUtils.getParam("MID"));
		signdata.append(ContractContant.SIGN_SPLITSTR + fsid);
		signdata.append(ContractContant.SIGN_SPLITSTR + 3);
		String sign = null;
		try {
			sign = SignUtil.sign(signdata.toString()).trim();
		} catch (Exception e) {
			throw new ServiceException("数据异常");
		}
		sign = java.net.URLEncoder.encode(sign);
		String geturl = url + "?mid=" + ParamUtils.getParam("MID") + "&fsdid=" + fsid + "&status=3&sign=" + sign;
		return geturl;
	}
}
