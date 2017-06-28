package com.hs.loan.finance.bo;


import java.util.Date;

import java.io.Serializable;

/**
 * ACC_还款登记（费用减免） 对象组装
 * @author autocreate
 * @create 2016-02-03
 */
public class AccRepayDiscRegBo implements Serializable{
	private static final long serialVersionUID = 1L;

	/*** 客户姓名 */
	private String custName;

	/*** 业务日期 （现已改为身份证号码）*/
	private String busnDate;
    
    /*** ID */
  	private String id ; 
    
    /*** 分期编码 */
  	private String loanNo ; 
    
    /*** 账单日 */
  	private String repayDate ; 
    
    /*** 减免类型 */
  	private String discType ; 
    
    /*** 减免金额 */
  	private java.math.BigDecimal discAmt ; 
    
    /*** 减免时间 */
  	private Date discDate ; 
    
    /***  */
  	private String tranDesc ; 
    
    /*** 减免人员编码 */
  	private String tranStaff ; 
    
    /*** 减免人员姓名 */
  	private String tranOrg ; 
    
    /*** 是否入账 */
  	private String setlFlag ; 
    
    /*** 创建时间 */
  	private Date instDate ; 
    
    /*** 更新时间 */
  	private Date updtDate ; 

    //构造函数
    public AccRepayDiscRegBo(){}

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
	 * 获取 账单日
	 * @return String
	 */
	public String getRepayDate() {
		return repayDate;
	}

	/**
	 * 设置 账单日
	 * @param repayDate
	 */
	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

    
    /**
	 * 获取 减免类型
	 * @return String
	 */
	public String getDiscType() {
		return discType;
	}

	/**
	 * 设置 减免类型
	 * @param discType
	 */
	public void setDiscType(String discType) {
		this.discType = discType;
	}

    
    /**
	 * 获取 减免金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getDiscAmt() {
		return discAmt;
	}

	/**
	 * 设置 减免金额
	 * @param discAmt
	 */
	public void setDiscAmt(java.math.BigDecimal discAmt) {
		this.discAmt = discAmt;
	}

    
    /**
	 * 获取 减免时间
	 * @return Date
	 */
	public Date getDiscDate() {
		return discDate;
	}

	/**
	 * 设置 减免时间
	 * @param discDate
	 */
	public void setDiscDate(Date discDate) {
		this.discDate = discDate;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getTranDesc() {
		return tranDesc;
	}

	/**
	 * 设置 
	 * @param tranDesc
	 */
	public void setTranDesc(String tranDesc) {
		this.tranDesc = tranDesc;
	}

    
    /**
	 * 获取 减免人员编码
	 * @return String
	 */
	public String getTranStaff() {
		return tranStaff;
	}

	/**
	 * 设置 减免人员编码
	 * @param tranStaff
	 */
	public void setTranStaff(String tranStaff) {
		this.tranStaff = tranStaff;
	}

    
    /**
	 * 获取 减免人员姓名
	 * @return String
	 */
	public String getTranOrg() {
		return tranOrg;
	}

	/**
	 * 设置 减免人员姓名
	 * @param tranOrg
	 */
	public void setTranOrg(String tranOrg) {
		this.tranOrg = tranOrg;
	}

    
    /**
	 * 获取 是否入账
	 * @return String
	 */
	public String getSetlFlag() {
		return setlFlag;
	}

	/**
	 * 设置 是否入账
	 * @param setlFlag
	 */
	public void setSetlFlag(String setlFlag) {
		this.setlFlag = setlFlag;
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

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getBusnDate() {
		return busnDate;
	}

	public void setBusnDate(String busnDate) {
		this.busnDate = busnDate;
	}

}