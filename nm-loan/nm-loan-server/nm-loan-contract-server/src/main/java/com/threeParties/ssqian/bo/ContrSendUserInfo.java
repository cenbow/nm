package com.threeParties.ssqian.bo;

/**
 * 合同签约发送用户信息
 * @author jqiu
 */
public class ContrSendUserInfo {
	/**
	 * 用户邮箱(可选，邮箱可以为空，当前邮箱为空时，系统将用所提供手机号码)
	 */
	private String email;
	/**
	 * 邮件消息主题
	 */
	private String emailtitle;
	/**
	 * 邮件消息内容
	 */
	private String emailcontent;
	/**
	 * 有效时间(天),（可选，默认合同有效期为三天）
	 */
	private String sxdays;
	/**
	 * 需不需要发件人自己签署"（0表示不要自己签署，1表示要自己签署）
	 */
	private String selfsign;
	/**
	 * 用户姓名
	 */
	private String name;
	/**
	 * 是否需要视频签约(1:是;0:否2：是双向视频)
	 */
	private String needvideo;
	/**
	 * 手机
	 */
	private String mobile;
	/**
	 * 用户类型（1表示个人用户、2表示企业用户）
	 */
	private String usertype;
	/**
	 * 是否默认生成签名图片"（0表示不默认生成签名图片，1表示默认生成签名图片）
	 */
	private String signimagetype;
	/**
	 * 用户使用文件类型，1表示本地文件上传、2、表示使用云文件上传发送合同
	 */
	private String userfileType;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNeedvideo() {
		return needvideo;
	}
	public void setNeedvideo(String needvideo) {
		this.needvideo = needvideo;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmailtitle() {
		return emailtitle;
	}
	public void setEmailtitle(String emailtitle) {
		this.emailtitle = emailtitle;
	}
	public String getEmailcontent() {
		return emailcontent;
	}
	public void setEmailcontent(String emailcontent) {
		this.emailcontent = emailcontent;
	}
	public String getSxdays() {
		return sxdays;
	}
	public void setSxdays(String sxdays) {
		this.sxdays = sxdays;
	}
	public String getSelfsign() {
		return selfsign;
	}
	public void setSelfsign(String selfsign) {
		this.selfsign = selfsign;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getSignimagetype() {
		return signimagetype;
	}
	public void setSignimagetype(String signimagetype) {
		this.signimagetype = signimagetype;
	}
	public String getUserfileType() {
		return userfileType;
	}
	public void setUserfileType(String userfileType) {
		this.userfileType = userfileType;
	}
}
