package com.hs.loan.outsource.bo;


import java.util.Date;

import java.io.Serializable;

/**
 * PL_委外单位与分期合同对应关系表 对象
 * @author autocreate
 * @create 2015-12-02
 */
public class LoanOutsourceRelationBo implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 分期编码 */
  	private String loanNo ; 
    
    /*** 委外单位编码 */
  	private String unitNo ; 
    /*** 委外单位名称*/
  	private String unitName ; 
  	 /*** 回收金额*/
  	private String retAmt ; 
    
    /*** 状态 */
  	private String stat ; 
    
    /*** 插入时间 */
  	private Date instDate ; 

    //构造函数
    public LoanOutsourceRelationBo(){}

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
	 * 获取 分期编码
	 * @return String
	 */
	public String getLoanNo() {
		return loanNo;
	}

	/**
	 * 设置 分期编码
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

    
    /**
	 * 获取 委外单位编码
	 * @return String
	 */
	public String getUnitNo() {
		return unitNo;
	}

	/**
	 * 设置 委外单位编码
	 * @param unitNo
	 */
	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
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
	 * 获取 插入时间
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 插入时间
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getRetAmt() {
		return retAmt;
	}

	public void setRetAmt(String retAmt) {
		this.retAmt = retAmt;
	}

}