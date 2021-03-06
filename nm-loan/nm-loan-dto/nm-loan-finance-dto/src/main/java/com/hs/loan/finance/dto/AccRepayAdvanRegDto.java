package com.hs.loan.finance.dto;


import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

import java.io.Serializable;

/**
 * ACC_还款登记（提前结清） 对象
 * @author autocreate
 * @create 2016-02-03
 */
public class AccRepayAdvanRegDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 账单日 */
  	private String repayDate ; 
    
    /***  */
  	private Integer repayNum ; 
    
    /*** 还款日期 */
	@JsonSerialize(using=DateTimeJsonSerializer.class)
  	private Date tranDate ; 
    
    /*** 还款金额 */
  	private java.math.BigDecimal tranAmt ; 
    
    /*** 还款客户名称 */
  	private String custName ; 
    
    /*** 还款账户 */
  	private String custAcct ; 
    
    /*** 还款状态 */
  	private String stat ; 
    
    /*** 经办备注 */
  	private String tranDesc ; 
    
    /*** 经办人 */
  	private String tranStaff ; 
    
    /*** 经办人姓名 */
  	private String tranOrg ; 
    
    /*** 创建日期 */
	@JsonSerialize(using=DateTimeJsonSerializer.class)
  	private Date instDate ; 

    //构造函数
    public AccRepayAdvanRegDto(){}

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
	 * 获取 
	 * @return Integer
	 */
	public Integer getRepayNum() {
		return repayNum;
	}

	/**
	 * 设置 
	 * @param repayNum
	 */
	public void setRepayNum(Integer repayNum) {
		this.repayNum = repayNum;
	}

    
    /**
	 * 获取 还款日期
	 * @return Date
	 */
	public Date getTranDate() {
		return tranDate;
	}

	/**
	 * 设置 还款日期
	 * @param tranDate
	 */
	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

    
    /**
	 * 获取 还款金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getTranAmt() {
		return tranAmt;
	}

	/**
	 * 设置 还款金额
	 * @param tranAmt
	 */
	public void setTranAmt(java.math.BigDecimal tranAmt) {
		this.tranAmt = tranAmt;
	}

    
    /**
	 * 获取 还款客户名称
	 * @return String
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * 设置 还款客户名称
	 * @param custName
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

    
    /**
	 * 获取 还款账户
	 * @return String
	 */
	public String getCustAcct() {
		return custAcct;
	}

	/**
	 * 设置 还款账户
	 * @param custAcct
	 */
	public void setCustAcct(String custAcct) {
		this.custAcct = custAcct;
	}

    
    /**
	 * 获取 还款状态
	 * @return String
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * 设置 还款状态
	 * @param stat
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}

    
    /**
	 * 获取 经办备注
	 * @return String
	 */
	public String getTranDesc() {
		return tranDesc;
	}

	/**
	 * 设置 经办备注
	 * @param tranDesc
	 */
	public void setTranDesc(String tranDesc) {
		this.tranDesc = tranDesc;
	}

    
    /**
	 * 获取 经办人
	 * @return String
	 */
	public String getTranStaff() {
		return tranStaff;
	}

	/**
	 * 设置 经办人
	 * @param tranStaff
	 */
	public void setTranStaff(String tranStaff) {
		this.tranStaff = tranStaff;
	}

    
    /**
	 * 获取 经办人姓名
	 * @return String
	 */
	public String getTranOrg() {
		return tranOrg;
	}

	/**
	 * 设置 经办人姓名
	 * @param tranOrg
	 */
	public void setTranOrg(String tranOrg) {
		this.tranOrg = tranOrg;
	}

    
    /**
	 * 获取 创建日期
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 创建日期
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

}