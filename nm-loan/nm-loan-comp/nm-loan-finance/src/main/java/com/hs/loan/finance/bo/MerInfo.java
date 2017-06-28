package com.hs.loan.finance.bo;

import java.io.Serializable;

/**
 * 商户基本信息
 * @author zwr
 *
 */
public class MerInfo implements Serializable{
	private static final long serialVersionUID = 1387719962271703437L;
	
	/*** 商户号 非空*/
	private String merchantId;
	/*** 用户名 非空*/
	private String userName;
	/*** 密码 非空*/
	private String userPass;
	/*** 商户私钥证书 非空*/
	private String merKeyPath;
	
	/*** 商户公钥证书 非空*/
	private String pubKeyPath;
	
	/*** 银联接口地址 非空*/
	private String unionPayUrl;
	/*** 银联 报文模版 single 非空*/
	private String unionPayTmpSingle;
	/*** 银联 报文模版 batch  非空*/
	private String unionPayTmpBatch;
	/*** 银联 报文模版 query  非空*/
	private String unionPayTmpQuery;
	/**备用字段*/
	private String remark1;
	private String remark2;
	private String remark3;
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getMerKeyPath() {
		return merKeyPath;
	}
	public void setMerKeyPath(String merKeyPath) {
		this.merKeyPath = merKeyPath;
	}
	public String getPubKeyPath() {
		return pubKeyPath;
	}
	public void setPubKeyPath(String pubKeyPath) {
		this.pubKeyPath = pubKeyPath;
	}
	public String getUnionPayUrl() {
		return unionPayUrl;
	}
	public void setUnionPayUrl(String unionPayUrl) {
		this.unionPayUrl = unionPayUrl;
	}
	public String getUnionPayTmpSingle() {
		return unionPayTmpSingle;
	}
	public void setUnionPayTmpSingle(String unionPayTmpSingle) {
		this.unionPayTmpSingle = unionPayTmpSingle;
	}
	public String getUnionPayTmpBatch() {
		return unionPayTmpBatch;
	}
	public void setUnionPayTmpBatch(String unionPayTmpBatch) {
		this.unionPayTmpBatch = unionPayTmpBatch;
	}
	public String getUnionPayTmpQuery() {
		return unionPayTmpQuery;
	}
	public void setUnionPayTmpQuery(String unionPayTmpQuery) {
		this.unionPayTmpQuery = unionPayTmpQuery;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public String getRemark2() {
		return remark2;
	}
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	public String getRemark3() {
		return remark3;
	}
	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}
	
	
}
