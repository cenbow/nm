package com.hs.loan.market.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 员工dto
 * @author zwr
 *
 */
public class StaffDto implements Serializable{
	private static final long serialVersionUID = 6419986855171461845L;

	private String id ; 
	
	@NotBlank(message="员工登录ID不能为空")
  	@Size(max=80,message="员工登录ID超长")
  	private String loginNo ; 

	@NotBlank(message="员工号不能为空")
  	@Size(max=80,message="员工号超长")
  	private String staffNo ; 

	@NotBlank(message="分组名称不能为空")
  	@Size(max=80,message="分组名称超长")
  	private String staffName ; 

  	@Size(max=80,message="分组名称超长")
  	private String loginPwd ; 

  	@Size(max=80,message="分组名称超长")
  	private String belgOrgNo ; 

  	@NotNull(message="分组名称不能为空")
  	@Size(max=80,message="分组名称超长")
  	private Date lastLoginDate ; 

  	@Size(max=80,message="分组名称超长")
  	private String lastLog ; 

  	@Size(max=80,message="分组名称超长")
  	private String openId ; 

  	@Size(max=80,message="分组名称超长")
  	private String staffPosition ; 

  	@NotBlank(message="分组名称不能为空")
  	@Size(max=80,message="分组名称超长")
  	private String staffStat ; 

  	@Size(max=80,message="分组名称超长")
  	private String userTyp ; 

  	@Size(max=80,message="分组名称超长")
  	private String moblNo ; 

  	@Size(max=80,message="分组名称超长")
  	private String email ; 

  	@Future(message="创建日期必须晚于当前时间")
  	private Date createDate ; 

  	@Future(message="更新日期必须晚于当前时间")
  	private Date modifyDate ; 


    //构造函数
    public StaffDto(){}

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
	
}
