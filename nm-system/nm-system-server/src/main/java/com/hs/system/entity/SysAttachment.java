package com.hs.system.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * SYS_附件表 对象
 * @author autocreate
 * @create 2015-11-10
 */
public class SysAttachment implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 原附件名 */
  	private String originalName ; 
    
    /*** 现附件名 */
  	private String presentName ; 
    
    /*** 附件大小 */
  	private Long fileSize ; 
    
    /*** 附件类型 */
  	private String contentType ; 
    
    /*** 物理地址 */
  	private String physicalAddress ; 
    
    /*** 网络地址 */
  	private String networkAddress ; 
    
    /*** 扩展名 */
  	private String extName ; 
    
    /*** 业务类型 */
  	private String busiType ; 
    
    /*** 业务主键 */
  	private String busiKey ; 
    
    /*** 业务说明 */
  	private String busiRemark ; 
    
    /*** 创建时间 */
  	private Date instDate ; 
    
    /*** 修改时间 */
  	private Date updtDate ; 
    
    /*** 登记人 */
  	private String instPerson ; 
  	
  	/*** 状态 */
  	private String stat ; 

    //构造函数
    public SysAttachment(){}

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
	 * 获取 原附件名
	 * @return String
	 */
	public String getOriginalName() {
		return originalName;
	}

	/**
	 * 设置 原附件名
	 * @param originalName
	 */
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

    
    /**
	 * 获取 现附件名
	 * @return String
	 */
	public String getPresentName() {
		return presentName;
	}

	/**
	 * 设置 现附件名
	 * @param presentName
	 */
	public void setPresentName(String presentName) {
		this.presentName = presentName;
	}

    
    /**
	 * 获取 附件大小
	 * @return Integer
	 */
	public Long getFileSize() {
		return fileSize;
	}

	/**
	 * 设置 附件大小
	 * @param fileSize
	 */
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

    
    /**
	 * 获取 附件类型
	 * @return String
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * 设置 附件类型
	 * @param contentType
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

    
    /**
	 * 获取 物理地址
	 * @return String
	 */
	public String getPhysicalAddress() {
		return physicalAddress;
	}

	/**
	 * 设置 物理地址
	 * @param physicalAddress
	 */
	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}

    
    /**
	 * 获取 网络地址
	 * @return String
	 */
	public String getNetworkAddress() {
		return networkAddress;
	}

	/**
	 * 设置 网络地址
	 * @param networkAddress
	 */
	public void setNetworkAddress(String networkAddress) {
		this.networkAddress = networkAddress;
	}

    
    /**
	 * 获取 扩展名
	 * @return String
	 */
	public String getExtName() {
		return extName;
	}

	/**
	 * 设置 扩展名
	 * @param extName
	 */
	public void setExtName(String extName) {
		this.extName = extName;
	}

    
    /**
	 * 获取 业务类型
	 * @return String
	 */
	public String getBusiType() {
		return busiType;
	}

	/**
	 * 设置 业务类型
	 * @param busiType
	 */
	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

    
    /**
	 * 获取 业务主键
	 * @return String
	 */
	public String getBusiKey() {
		return busiKey;
	}

	/**
	 * 设置 业务主键
	 * @param busiKey
	 */
	public void setBusiKey(String busiKey) {
		this.busiKey = busiKey;
	}

    
    /**
	 * 获取 业务说明
	 * @return String
	 */
	public String getBusiRemark() {
		return busiRemark;
	}

	/**
	 * 设置 业务说明
	 * @param busiRemark
	 */
	public void setBusiRemark(String busiRemark) {
		this.busiRemark = busiRemark;
	}

    
    /**
	 * 获取 创建时间
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 创建时间
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

    
    /**
	 * 获取 修改时间
	 * @return Date
	 */
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 修改时间
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

    
    /**
	 * 获取 登记人
	 * @return String
	 */
	public String getInstPerson() {
		return instPerson;
	}

	/**
	 * 设置 登记人
	 * @param instPerson
	 */
	public void setInstPerson(String instPerson) {
		this.instPerson = instPerson;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

}