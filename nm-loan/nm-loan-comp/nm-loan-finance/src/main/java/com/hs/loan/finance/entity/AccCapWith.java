package com.hs.loan.finance.entity;


import java.util.Date;

import java.io.Serializable;

/**
 *  对象
 * @author autocreate
 * @create 2016-04-19
 */
public class AccCapWith implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 贷款编号 */
  	private String loanNo ; 
    
    /***  */
  	private Integer repayNum ; 
    
    /*** 账单日 */
  	private String repayDate ; 
    
    /*** 业务日期 */
  	private String busnDate ; 
    
    /*** 渠道号 */
  	private String chanNo ; 
    
    /*** 客户号 */
  	private String custNo ; 
    
    /*** 扣款账号 */
  	private String acctNo ; 
    
    /*** 扣款户名 */
  	private String acctName ; 
    
    /*** 开户银行 */
  	private String bankNo ; 
    
    /*** 开户银行名称 */
  	private String bankName ; 
    
    /*** 入账账号 */
  	private String inAcctNo ; 
    
    /*** 入账户名 */
  	private String inAcctName ; 
  	
  	/*** 交易方 */
  	private String tranObj ; 
  	
  	
    
    public String getTranObj() {
		return tranObj;
	}

	public void setTranObj(String tranObj) {
		this.tranObj = tranObj;
	}

	/*** 扣款状态:
              未扣款-20109001
              扣款中-20109002
              扣款成功-20109003
              扣款失败-20109004
              取消-20109005
              信托扣款成功-20109006
              平台扣款成功-20109007 */
  	private String withStat ; 
    
    /*** 创建时间 */
  	private Date instDate ; 
    
    /*** 更新时间 */
  	private Date updtDate ; 

    //构造函数
    public AccCapWith(){}

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
	 * 获取 贷款编号
	 * @return String
	 */
	public String getLoanNo() {
		return loanNo;
	}

	/**
	 * 设置 贷款编号
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
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
	 * 获取 业务日期
	 * @return String
	 */
	public String getBusnDate() {
		return busnDate;
	}

	/**
	 * 设置 业务日期
	 * @param busnDate
	 */
	public void setBusnDate(String busnDate) {
		this.busnDate = busnDate;
	}

    
    /**
	 * 获取 渠道号
	 * @return String
	 */
	public String getChanNo() {
		return chanNo;
	}

	/**
	 * 设置 渠道号
	 * @param chanNo
	 */
	public void setChanNo(String chanNo) {
		this.chanNo = chanNo;
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
	 * 获取 扣款账号
	 * @return String
	 */
	public String getAcctNo() {
		return acctNo;
	}

	/**
	 * 设置 扣款账号
	 * @param acctNo
	 */
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

    
    /**
	 * 获取 扣款户名
	 * @return String
	 */
	public String getAcctName() {
		return acctName;
	}

	/**
	 * 设置 扣款户名
	 * @param acctName
	 */
	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

    
    /**
	 * 获取 开户银行
	 * @return String
	 */
	public String getBankNo() {
		return bankNo;
	}

	/**
	 * 设置 开户银行
	 * @param bankNo
	 */
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

    
    /**
	 * 获取 开户银行名称
	 * @return String
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * 设置 开户银行名称
	 * @param bankName
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

    
    /**
	 * 获取 入账账号
	 * @return String
	 */
	public String getInAcctNo() {
		return inAcctNo;
	}

	/**
	 * 设置 入账账号
	 * @param inAcctNo
	 */
	public void setInAcctNo(String inAcctNo) {
		this.inAcctNo = inAcctNo;
	}

    
    /**
	 * 获取 入账户名
	 * @return String
	 */
	public String getInAcctName() {
		return inAcctName;
	}

	/**
	 * 设置 入账户名
	 * @param inAcctName
	 */
	public void setInAcctName(String inAcctName) {
		this.inAcctName = inAcctName;
	}

    
    /**
	 * 获取 扣款状态:
              未扣款-20109001
              扣款中-20109002
              扣款成功-20109003
              扣款失败-20109004
              取消-20109005
              信托扣款成功-20109006
              平台扣款成功-20109007
	 * @return String
	 */
	public String getWithStat() {
		return withStat;
	}

	/**
	 * 设置 扣款状态:
              未扣款-20109001
              扣款中-20109002
              扣款成功-20109003
              扣款失败-20109004
              取消-20109005
              信托扣款成功-20109006
              平台扣款成功-20109007
	 * @param withStat
	 */
	public void setWithStat(String withStat) {
		this.withStat = withStat;
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