package com.threeParties.ssqian.bo;

/**
 * 签章上传接口
 * @author jqiu
 */
public class SignUpInfo {
	/**
	 * 公司名
	 */
	private String companyName;
	/**
	 * 手机
	 */
	private String mobile;
	/**
	 * 图片名称
	 */
	private String imgName;
	/**
	 * 图片类型
	 */
	private String imgType;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getImgType() {
		return imgType;
	}
	public void setImgType(String imgType) {
		this.imgType = imgType;
	}
}
