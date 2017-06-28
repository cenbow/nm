package com.hs.system.entity;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 员工信息 对象
 * @author autocreate
 * @create 2015-09-28
 */
public class SysStaff implements Serializable{
	private static final long serialVersionUID = 1L;
	
  	private String id ; 

  	//@NotBlank(message="员工登录号不能为空")
	@Size(max=40,message="员工登录号超长")
  	private String loginNo ; 

  	//@NotBlank(message="员工号不能为空")
	@Size(max=40,message="员工号超长")
  	private String staffNo ; 

  	//@NotBlank(message="员工名称不能为空")
	@Size(max=40,message="员工名称超长")
  	private String staffName ; 

//  	@NotBlank(message="登录密码不能为空")
	@Size(max=40,message="登录密码超长")
  	private String loginPwd ; 

//  	@NotBlank(message="所属机构不能为空")
	@Size(max=40,message="所属机构超长")
  	private String belgOrgNo ; 

  	@Past(message="最近登录日期必须早于当前时间")
  	private Date lastLoginDate ; 

  	@Size(max=20,message="最近登录IP超长")
  	private String lastLog ; 

  	@Size(max=40,message="微信ID超长")
  	private String openId ; 

  	@Size(max=10,message="员工职位超长")
  	private String staffPosition ; 

  	//@NotBlank(message="员工状态不能为空")
  	@Size(max=10,message="员工状态超长")
  	private String staffStat ; 

  	@Size(max=10,message="人员类型超长")
  	private String userTyp ; 

  	//@Pattern(regexp = "^\\s+|((13[0-9]|14[0-9]|15[0-9]|18[0-9])\\d{8})$")
  	@Size(max=40,message="手机号超长")
  	private String moblNo ; 

  	//@Pattern(regexp = "^\\s+|(\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*)$")
  	@Size(max=20,message="Email超长")
  	private String email ; 

  	@Future(message="创建时间必须晚于当前时间")
  	private Date createDate ; 

  	@Future(message="修改时间必须晚于当前时间")
  	private Date modifyDate ; 

	@Size(max=40,message="分机号(认证名)超长")
  	private String staffAutName ; 
	@Size(max=40,message="认证密码超长")
  	private String staffAutPwd ; 

    //构造函数
    public SysStaff(){}

    //getter和setter方法
    
    /**
	 * 获取 
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getLoginNo() {
		return loginNo;
	}

	/**
	 * 设置 
	 * @param loginNo
	 */
	public void setLoginNo(String loginNo) {
		this.loginNo = loginNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getStaffNo() {
		return staffNo;
	}

	/**
	 * 设置 
	 * @param staffNo
	 */
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * 设置 
	 * @param staffName
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getLoginPwd() {
		return loginPwd;
	}

	/**
	 * 设置 
	 * @param loginPwd
	 */
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getBelgOrgNo() {
		return belgOrgNo;
	}

	/**
	 * 设置 
	 * @param belgOrgNo
	 */
	public void setBelgOrgNo(String belgOrgNo) {
		this.belgOrgNo = belgOrgNo;
	}

    
    /**
	 * 获取 
	 * @return Date
	 */
	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	/**
	 * 设置 
	 * @param lastLoginDate
	 */
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getLastLog() {
		return lastLog;
	}

	/**
	 * 设置 
	 * @param lastLog
	 */
	public void setLastLog(String lastLog) {
		this.lastLog = lastLog;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getOpenId() {
		return openId;
	}

	/**
	 * 设置 
	 * @param openId
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getStaffPosition() {
		return staffPosition;
	}

	/**
	 * 设置 
	 * @param staffPosition
	 */
	public void setStaffPosition(String staffPosition) {
		this.staffPosition = staffPosition;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getStaffStat() {
		return staffStat;
	}

	/**
	 * 设置 
	 * @param staffStat
	 */
	public void setStaffStat(String staffStat) {
		this.staffStat = staffStat;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getUserTyp() {
		return userTyp;
	}

	/**
	 * 设置 
	 * @param userTyp
	 */
	public void setUserTyp(String userTyp) {
		this.userTyp = userTyp;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getMoblNo() {
		return moblNo;
	}

	/**
	 * 设置 
	 * @param moblNo
	 */
	public void setMoblNo(String moblNo) {
		this.moblNo = moblNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

    
    /**
	 * 获取 
	 * @return Date
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 设置 
	 * @param createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

    
    /**
	 * 获取 
	 * @return Date
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * 设置 
	 * @param modifyDate
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getStaffAutName() {
		return staffAutName;
	}

	public void setStaffAutName(String staffAutName) {
		this.staffAutName = staffAutName;
	}

	public String getStaffAutPwd() {
		return staffAutPwd;
	}

	public void setStaffAutPwd(String staffAutPwd) {
		this.staffAutPwd = staffAutPwd;
	}

}