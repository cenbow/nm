package com.hs.loan.cust.entity;


import java.util.Date;
import java.io.Serializable;

import com.hs.loan.cust.itface.ICustExtraInfo;

/**
 * APP_客户联系信息 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class AppCustContctInfo implements Serializable,ICustExtraInfo{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /***  */
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

    //构造函数
    public AppCustContctInfo(){}

    //getter和setter方法
    
    /**
	 * 获取 ID
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 ID
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

    
    /**
	 * 获取 客户编号
	 * @return String
	 */
	public String getCustNo() {
		return custNo;
	}

	/**
	 * 设置 客户编号
	 * @param custNo
	 */
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getImei() {
		return imei;
	}

	/**
	 * 设置 
	 * @param imei
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}

    
    /**
	 * 获取 手机号码
	 * @return String
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * 设置 手机号码
	 * @param phoneNo
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

    
    /**
	 * 获取 手机号码使用年限
	 * @return String
	 */
	public String getPhoneYears() {
		return phoneYears;
	}

	/**
	 * 设置 手机号码使用年限
	 * @param phoneYears
	 */
	public void setPhoneYears(String phoneYears) {
		this.phoneYears = phoneYears;
	}

    
    /**
	 * 获取 是否实名登记
	 * @return String
	 */
	public String getIsRealName() {
		return isRealName;
	}

	/**
	 * 设置 是否实名登记
	 * @param isRealName
	 */
	public void setIsRealName(String isRealName) {
		this.isRealName = isRealName;
	}

    
    /**
	 * 获取 每月平均消费话费
	 * @return String
	 */
	public String getMthTelFee() {
		return mthTelFee;
	}

	/**
	 * 设置 每月平均消费话费
	 * @param mthTelFee
	 */
	public void setMthTelFee(String mthTelFee) {
		this.mthTelFee = mthTelFee;
	}

    
    /**
	 * 获取 住宅电话
	 * @return String
	 */
	public String getHomeTel() {
		return homeTel;
	}

	/**
	 * 设置 住宅电话
	 * @param homeTel
	 */
	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}

    
    /**
	 * 获取 住宅电话登记人
	 * @return String
	 */
	public String getHomeTelOwner() {
		return homeTelOwner;
	}

	/**
	 * 设置 住宅电话登记人
	 * @param homeTelOwner
	 */
	public void setHomeTelOwner(String homeTelOwner) {
		this.homeTelOwner = homeTelOwner;
	}

    
    /**
	 * 获取 电子邮箱
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置 电子邮箱
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

    
    /**
	 * 获取 QQ
	 * @return String
	 */
	public String getQq() {
		return qq;
	}

	/**
	 * 设置 QQ
	 * @param qq
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}

    
    /**
	 * 获取 微信
	 * @return String
	 */
	public String getWechat() {
		return wechat;
	}

	/**
	 * 设置 微信
	 * @param wechat
	 */
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

    
    /**
	 * 获取 新浪
	 * @return String
	 */
	public String getSina() {
		return sina;
	}

	/**
	 * 设置 新浪
	 * @param sina
	 */
	public void setSina(String sina) {
		this.sina = sina;
	}

    
    /**
	 * 获取 开始日期
	 * @return Date
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置 开始日期
	 * @param beginDate
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

    
    /**
	 * 获取 结束日期
	 * @return Date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置 结束日期
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}