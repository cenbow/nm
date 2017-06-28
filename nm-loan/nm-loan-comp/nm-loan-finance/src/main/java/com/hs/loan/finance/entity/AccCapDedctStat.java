package com.hs.loan.finance.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * ACC_银联代扣统计 对象
 * @author autocreate
 * @create 2016-02-03
 */
public class AccCapDedctStat implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 业务日期 */
  	private String busnDate ; 
    
    /*** 代扣总数 */
  	private Integer dedctNum ; 
    
    /*** 代扣成功数 */
  	private Integer succCnt ; 
    
    /*** 代扣失败数 */
  	private Integer failCnt ; 
    
    /*** 代扣成功金额 */
  	private java.math.BigDecimal succAmt ; 
    
    /*** 插入时间 */
  	private Date instDate ; 
    
    /*** 更新时间 */
  	private Date updtDate ; 

    //构造函数
    public AccCapDedctStat(){}

    //getter和setter方法
    
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
	 * 获取 代扣总数
	 * @return Integer
	 */
	public Integer getDedctNum() {
		return dedctNum;
	}

	/**
	 * 设置 代扣总数
	 * @param dedctNum
	 */
	public void setDedctNum(Integer dedctNum) {
		this.dedctNum = dedctNum;
	}

    
    /**
	 * 获取 代扣成功数
	 * @return Integer
	 */
	public Integer getSuccCnt() {
		return succCnt;
	}

	/**
	 * 设置 代扣成功数
	 * @param succCnt
	 */
	public void setSuccCnt(Integer succCnt) {
		this.succCnt = succCnt;
	}

    
    /**
	 * 获取 代扣失败数
	 * @return Integer
	 */
	public Integer getFailCnt() {
		return failCnt;
	}

	/**
	 * 设置 代扣失败数
	 * @param failCnt
	 */
	public void setFailCnt(Integer failCnt) {
		this.failCnt = failCnt;
	}

    
    /**
	 * 获取 代扣成功金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getSuccAmt() {
		return succAmt;
	}

	/**
	 * 设置 代扣成功金额
	 * @param succAmt
	 */
	public void setSuccAmt(java.math.BigDecimal succAmt) {
		this.succAmt = succAmt;
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