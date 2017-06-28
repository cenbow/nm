package com.hs.loan.sale.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_分期附件表 对象
 * @author autocreate
 * @create 2015-11-12
 */
public class AppLoanAtt implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 附件编号 */
  	private String attNo ; 
    
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 客户号 */
  	private String custNo ; 
    
    /*** 附件名称 */
  	private String attName ; 
    
    /*** 附件类型 */
  	private String attTyp ; 
    
    /*** 附件文件格式 */
  	private String attFile ; 
    
    /*** 新增人员 */
  	private String instUser ; 
    
    /*** 新增人员机构 */
  	private String instOrg ; 
    
    /*** 新增时间 */
  	private Date instDate ; 

    //构造函数
    public AppLoanAtt(){}

    //getter和setter方法
    
    /**
	 * 获取 附件编号
	 * @return String
	 */
	public String getAttNo() {
		return attNo;
	}

	/**
	 * 设置 附件编号
	 * @param attNo
	 */
	public void setAttNo(String attNo) {
		this.attNo = attNo;
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
	 * 获取 客户号
	 * @return String
	 */
	public String getCustNo() {
		return custNo;
	}

	/**
	 * 设置 客户号
	 * @param custNo
	 */
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

    
    /**
	 * 获取 附件名称
	 * @return String
	 */
	public String getAttName() {
		return attName;
	}

	/**
	 * 设置 附件名称
	 * @param attName
	 */
	public void setAttName(String attName) {
		this.attName = attName;
	}

    
    /**
	 * 获取 附件类型
	 * @return String
	 */
	public String getAttTyp() {
		return attTyp;
	}

	/**
	 * 设置 附件类型
	 * @param attTyp
	 */
	public void setAttTyp(String attTyp) {
		this.attTyp = attTyp;
	}

    
    /**
	 * 获取 附件文件格式
	 * @return String
	 */
	public String getAttFile() {
		return attFile;
	}

	/**
	 * 设置 附件文件格式
	 * @param attFile
	 */
	public void setAttFile(String attFile) {
		this.attFile = attFile;
	}

    
    /**
	 * 获取 新增人员
	 * @return String
	 */
	public String getInstUser() {
		return instUser;
	}

	/**
	 * 设置 新增人员
	 * @param instUser
	 */
	public void setInstUser(String instUser) {
		this.instUser = instUser;
	}

    
    /**
	 * 获取 新增人员机构
	 * @return String
	 */
	public String getInstOrg() {
		return instOrg;
	}

	/**
	 * 设置 新增人员机构
	 * @param instOrg
	 */
	public void setInstOrg(String instOrg) {
		this.instOrg = instOrg;
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

}