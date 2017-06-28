package com.nm.cmd;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 客户联系信息
 * @author lenovo
 *
 */
public class CustContctCmd implements Serializable {
	private static final long serialVersionUID = 1L;
    private String id ;
    
    /*** 客户编号 */
  	private String custNo ;
    
    /*** imei */
  	private String imei ;
    
    /*** 手机号码 */
  	private String phoneNo ;
    
    /*** 手机号码使用年限 */
  	private String phoneYears ;
    
    /*** 是否实名登记 */
  	private String isRealName ;
    
    /*** 每月平均消费话费 */
  	private String mthTelFee ;
    
    /*** 住宅电话 */
	@NotBlank (message="住宅电话不能为空")
	@Size(max=32,message="住宅电话超长") 
  	private String homeTel ;
    
    /*** 住宅电话登记人 */
  	private String homeTelOwner ;
    
    /*** 电子邮箱 */
  	private String email ;
    
    /*** QQ */
  	private String qq ;
    
    /*** 微信 */
  	private String wechat ;
    
    /*** 新浪 */
  	private String sina ;
    
    /*** 开始日期 */
  	private Date beginDate ;
    
    /*** 结束日期 */
  	private Date endDate ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPhoneYears() {
		return phoneYears;
	}

	public void setPhoneYears(String phoneYears) {
		this.phoneYears = phoneYears;
	}

	public String getIsRealName() {
		return isRealName;
	}

	public void setIsRealName(String isRealName) {
		this.isRealName = isRealName;
	}

	public String getMthTelFee() {
		return mthTelFee;
	}

	public void setMthTelFee(String mthTelFee) {
		this.mthTelFee = mthTelFee;
	}

	public String getHomeTel() {
		return homeTel;
	}

	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}

	public String getHomeTelOwner() {
		return homeTelOwner;
	}

	public void setHomeTelOwner(String homeTelOwner) {
		this.homeTelOwner = homeTelOwner;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getSina() {
		return sina;
	}

	public void setSina(String sina) {
		this.sina = sina;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	} 

	

}
