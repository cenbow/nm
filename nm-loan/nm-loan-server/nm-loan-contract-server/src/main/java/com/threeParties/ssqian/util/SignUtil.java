package com.threeParties.ssqian.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.hs.loan.contract.contant.ContractContant;
import com.hs.utils.ParamUtils;

public final class SignUtil {

	public static String sign(String content) throws Exception {
		try {
			
			String signtmp= AlipaySignature.rsaSign(content, ParamUtils.getParam("PRIVATEKEY"), ContractContant.CHARSET_ENCODING);//
			
//			boolean flag = AlipaySignature.rsaCheckContent(content, signtmp,
//					ContractContant.publickey, "UTF-8");
			
			return signtmp;
		} catch (AlipayApiException e) {
			
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	public static String decrypt(String content) throws Exception {

		try {
			return AlipaySignature.rsaDecrypt(content,ParamUtils.getParam("PRIVATEKEY"), ContractContant.CHARSET_ENCODING);
		} catch (AlipayApiException e) {
			throw new Exception(e.getMessage());
		}
	}

}
