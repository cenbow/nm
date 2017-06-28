package com.hs.loan.collection.dto;

import java.io.Serializable;

public class LoanOveDualCaseBaseDto implements Serializable{
	private static final long serialVersionUID = 1L;
	 /*** 案件ID */
  	private String caseId ; 
    
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 证件号码 */
  	private String certNo ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /*** 客户名称 */
  	private String custName ; 
    
    /*** 业务日期 */
  	private String busnDate ; 
    
    /*** 逾期开始期数 */
  	private Integer bgnNum ; 
    
    /*** 逾期结束期数 */
  	private Integer endNum ; 
    
    /*** 逾期开始日期 */
  	private String bgnDate ; 
    
    /*** 逾期结束日期 */
  	private String endDate ; 
  	/*** 逾期金额 */
  	private java.math.BigDecimal ovduLoanAmt ; 
    
    /*** 逾期阶段 */
  	private Integer ovduLev ; 
    /*** 提前结清 */
  	private java.math.BigDecimal setlAmt ; 
    
    /*** 本息金额 */
  	private java.math.BigDecimal prinIntAmt ; 
    
    /*** 已还金额 */
  	private java.math.BigDecimal paidAmt ; 
    
    /*** 剩余金额 */
  	private java.math.BigDecimal remnAmt ; 
    
    /*** 实还金额 */
  	private java.math.BigDecimal totlAmt ; 
    
    /*** 还款方式 */
  	private String repayTyp ; 
    /*** 处理状态 */
  	private String dealStat ; 
  	/**
	 * 获取 案件ID
	 * @return String
	 */
	public String getCaseId() {
		return caseId;
	}

	/**
	 * 设置 案件ID
	 * @param caseId
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	 /**
		 * 获取 处理状态
		 * @return String
		 */
		public String getDealStat() {
			return dealStat;
		}

		/**
		 * 设置 处理状态
		 * @param dealStat
		 */
		public void setDealStat(String dealStat) {
			this.dealStat = dealStat;
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
	 * 获取 证件号码
	 * @return String
	 */
	public String getCertNo() {
		return certNo;
	}

	/**
	 * 设置 证件号码
	 * @param certNo
	 */
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

    
    /**
	 * 获取 客户编号
	 * @return String
	 */
	public String getCustNo() {
		return custNo;
	}

	/**
	 * 设置 客户编号
	 * @param custNo
	 */
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

    
    /**
	 * 获取 客户名称
	 * @return String
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * 设置 客户名称
	 * @param custName
	 */
	public void setCustName(String custName) {
		this.custName = custName;
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
	 * 获取 逾期开始期数
	 * @return Integer
	 */
	public Integer getBgnNum() {
		return bgnNum;
	}

	/**
	 * 设置 逾期开始期数
	 * @param bgnNum
	 */
	public void setBgnNum(Integer bgnNum) {
		this.bgnNum = bgnNum;
	}

    
    /**
	 * 获取 逾期结束期数
	 * @return Integer
	 */
	public Integer getEndNum() {
		return endNum;
	}

	/**
	 * 设置 逾期结束期数
	 * @param endNum
	 */
	public void setEndNum(Integer endNum) {
		this.endNum = endNum;
	}

    
    /**
	 * 获取 逾期开始日期
	 * @return String
	 */
	public String getBgnDate() {
		return bgnDate;
	}

	/**
	 * 设置 逾期开始日期
	 * @param bgnDate
	 */
	public void setBgnDate(String bgnDate) {
		this.bgnDate = bgnDate;
	}

    
    /**
	 * 获取 逾期结束日期
	 * @return String
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * 设置 逾期结束日期
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

    
    /**
	 * 获取 逾期阶段
	 * @return Integer
	 */
	public Integer getOvduLev() {
		return ovduLev;
	}

	/**
	 * 设置 逾期阶段
	 * @param ovduLev
	 */
	public void setOvduLev(Integer ovduLev) {
		this.ovduLev = ovduLev;
	}

    
    /**
	 * 获取 逾期金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getOvduLoanAmt() {
		return ovduLoanAmt;
	}

	/**
	 * 设置 逾期金额
	 * @param ovduLoanAmt
	 */
	public void setOvduLoanAmt(java.math.BigDecimal ovduLoanAmt) {
		this.ovduLoanAmt = ovduLoanAmt;
	}

    
    /**
	 * 获取 本息金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getPrinIntAmt() {
		return prinIntAmt;
	}

	/**
	 * 设置 本息金额
	 * @param prinIntAmt
	 */
	public void setPrinIntAmt(java.math.BigDecimal prinIntAmt) {
		this.prinIntAmt = prinIntAmt;
	}

    
    /**
	 * 获取 已还金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getPaidAmt() {
		return paidAmt;
	}

	/**
	 * 设置 已还金额
	 * @param paidAmt
	 */
	public void setPaidAmt(java.math.BigDecimal paidAmt) {
		this.paidAmt = paidAmt;
	}

    
    /**
	 * 获取 剩余金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getRemnAmt() {
		return remnAmt;
	}

	/**
	 * 设置 剩余金额
	 * @param remnAmt
	 */
	public void setRemnAmt(java.math.BigDecimal remnAmt) {
		this.remnAmt = remnAmt;
	}

    
    /**
	 * 获取 实还金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getTotlAmt() {
		return totlAmt;
	}

	/**
	 * 设置 实还金额
	 * @param totlAmt
	 */
	public void setTotlAmt(java.math.BigDecimal totlAmt) {
		this.totlAmt = totlAmt;
	}

    
    /**
	 * 获取 还款方式
	 * @return String
	 */
	public String getRepayTyp() {
		return repayTyp;
	}

	/**
	 * 设置 还款方式
	 * @param repayTyp
	 */
	public void setRepayTyp(String repayTyp) {
		this.repayTyp = repayTyp;
	}

	public java.math.BigDecimal getSetlAmt() {
		return setlAmt;
	}

	public void setSetlAmt(java.math.BigDecimal setlAmt) {
		this.setlAmt = setlAmt;
	}

}
