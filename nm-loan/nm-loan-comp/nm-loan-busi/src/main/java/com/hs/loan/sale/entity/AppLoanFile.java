package com.hs.loan.sale.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_分期与合同的关系 对象
 * @author autocreate
 * @create 2015-11-02
 */
public class AppLoanFile implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 模版编号 */
  	private String fileId ; 
    
    /*** 模版名称 */
  	private String fileName ; 
    
    /*** 模版类型 */
  	private String fileTyp ; 
    
    /*** 资金渠道 */
  	private String chanNo ; 
    
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 状态 */
  	private String stat ; 
    
    /*** 新增时间 */
  	private Date instDate ; 
    
    /*** 修改时间 */
  	private Date updtDate ; 

    //构造函数
    public AppLoanFile(){}

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
	 * 获取 模版编号
	 * @return String
	 */
	public String getFileId() {
		return fileId;
	}

	/**
	 * 设置 模版编号
	 * @param fileId
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

    
    /**
	 * 获取 模版名称
	 * @return String
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * 设置 模版名称
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

    
    /**
	 * 获取 模版类型
	 * @return String
	 */
	public String getFileTyp() {
		return fileTyp;
	}

	/**
	 * 设置 模版类型
	 * @param fileTyp
	 */
	public void setFileTyp(String fileTyp) {
		this.fileTyp = fileTyp;
	}

    
    /**
	 * 获取 资金渠道
	 * @return String
	 */
	public String getChanNo() {
		return chanNo;
	}

	/**
	 * 设置 资金渠道
	 * @param chanNo
	 */
	public void setChanNo(String chanNo) {
		this.chanNo = chanNo;
	}

    
    /**
	 * 获取 分期编号
	 * @return String
	 */
	public String getLoanNo() {
		return loanNo;
	}

	/**
	 * 设置 分期编号
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

    
    /**
	 * 获取 状态
	 * @return String
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * 设置 状态
	 * @param stat
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}

    
    /**
	 * 获取 新增时间
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 新增时间
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

}