package com.hs.loan.acct.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * PUB_还款方式配置表 对象
 * @author autocreate
 * @create 2015-10-15
 */
public class PubRepayKindConf implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 还款方式编号 */
  	private String repayNo ; 
    
    /*** 还款方式名称 */
  	private String repayName ; 
    
    /*** 还款方式描述 */
  	private String repayDesc ; 
    
    /*** 计算规则 */
  	private String ruleCalc ; 
    
    /*** 备注 */
  	private String repayRemark ; 
    
    /*** 创建时间 */
  	private Date instDate ; 
    
    /*** 更新时间 */
  	private Date updtDate ; 

    //构造函数
    public PubRepayKindConf(){}

    //getter和setter方法
    
    /**
	 * 获取 还款方式编号
	 * @return String
	 */
	public String getRepayNo() {
		return repayNo;
	}

	/**
	 * 设置 还款方式编号
	 * @param repayNo
	 */
	public void setRepayNo(String repayNo) {
		this.repayNo = repayNo;
	}

    
    /**
	 * 获取 还款方式名称
	 * @return String
	 */
	public String getRepayName() {
		return repayName;
	}

	/**
	 * 设置 还款方式名称
	 * @param repayName
	 */
	public void setRepayName(String repayName) {
		this.repayName = repayName;
	}

    
    /**
	 * 获取 还款方式描述
	 * @return String
	 */
	public String getRepayDesc() {
		return repayDesc;
	}

	/**
	 * 设置 还款方式描述
	 * @param repayDesc
	 */
	public void setRepayDesc(String repayDesc) {
		this.repayDesc = repayDesc;
	}

    
    /**
	 * 获取 计算规则
	 * @return String
	 */
	public String getRuleCalc() {
		return ruleCalc;
	}

	/**
	 * 设置 计算规则
	 * @param ruleCalc
	 */
	public void setRuleCalc(String ruleCalc) {
		this.ruleCalc = ruleCalc;
	}

    
    /**
	 * 获取 备注
	 * @return String
	 */
	public String getRepayRemark() {
		return repayRemark;
	}

	/**
	 * 设置 备注
	 * @param repayRemark
	 */
	public void setRepayRemark(String repayRemark) {
		this.repayRemark = repayRemark;
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