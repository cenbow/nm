package com.hs.loan.approve.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_审批术语表 对象
 * @author autocreate
 * @create 2016-04-11
 */
public class AppLoanApprTerm implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 术语ID */
  	private String termId ; 
    
    /*** 术语内容 */
  	private String termDesc ; 
    
    /*** 状态 */
  	private String stat ; 
    
    /*** 职业类型 */
  	private String jobTyp ; 
    
    /*** 术语记录添加人 */
  	private String redAddMan ; 
    
    /*** 创建时间 */
  	private Date instDate ; 
    
    /*** 更新时间 */
  	private Date updtDate ; 

    //构造函数
    public AppLoanApprTerm(){}

    //getter和setter方法
    
    /**
	 * 获取 术语ID
	 * @return String
	 */
	public String getTermId() {
		return termId;
	}

	/**
	 * 设置 术语ID
	 * @param termId
	 */
	public void setTermId(String termId) {
		this.termId = termId;
	}

    
    /**
	 * 获取 术语内容
	 * @return String
	 */
	public String getTermDesc() {
		return termDesc;
	}

	/**
	 * 设置 术语内容
	 * @param termDesc
	 */
	public void setTermDesc(String termDesc) {
		this.termDesc = termDesc;
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
	 * 获取 职业类型
	 * @return String
	 */
	public String getJobTyp() {
		return jobTyp;
	}

	/**
	 * 设置 职业类型
	 * @param jobTyp
	 */
	public void setJobTyp(String jobTyp) {
		this.jobTyp = jobTyp;
	}

    
    /**
	 * 获取 术语记录添加人
	 * @return String
	 */
	public String getRedAddMan() {
		return redAddMan;
	}

	/**
	 * 设置 术语记录添加人
	 * @param redAddMan
	 */
	public void setRedAddMan(String redAddMan) {
		this.redAddMan = redAddMan;
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
	 * 获取 更新时间
	 * @return Date
	 */
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 更新时间
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

}