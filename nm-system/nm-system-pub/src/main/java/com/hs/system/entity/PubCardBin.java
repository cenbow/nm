package com.hs.system.entity;


import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 银行卡卡BIN 对象
 * @author autocreate
 * @create 2015-11-06
 */
public class PubCardBin implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** CARD_BIN */
  	private String cardBin ; 
    
    /*** 银行帐号 */
  	@NotBlank(message="银行帐号不能为空")
	@Size(max=40,message="银行帐号超长")
  	private String bankNo ; 
    
    /*** 银行名字 */
  	@NotBlank(message="银行名字不能为空")
	@Size(max=80,message="银行名字超长")
  	private String bankName ; 
    
    /*** 银行别名 */
  	@NotBlank(message="银行别名不能为空")
	@Size(max=40,message="银行别名超长")
  	private String bankAbbr ; 
    
    /*** 备注 */
  	@Size(max=120,message="银行别名超长")
  	private String remark ; 
    
    /*** 插入时间 */
  	@Future(message="插入时间必须晚于当前时间")
  	private Date instDate ; 
    
    /*** 更新时间 */
  	@Future(message="更新时间必须晚于当前时间")
  	private Date updtDate ; 

    //构造函数
    public PubCardBin(){}

    //getter和setter方法
    
    /**
	 * 获取 CARD_BIN
	 * @return String
	 */
	public String getCardBin() {
		return cardBin;
	}

	/**
	 * 设置 CARD_BIN
	 * @param cardBin
	 */
	public void setCardBin(String cardBin) {
		this.cardBin = cardBin;
	}

    
    /**
	 * 获取 银行帐号
	 * @return String
	 */
	public String getBankNo() {
		return bankNo;
	}

	/**
	 * 设置 银行帐号
	 * @param bankNo
	 */
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

    
    /**
	 * 获取 银行名字
	 * @return String
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * 设置 银行名字
	 * @param bankName
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

    
    /**
	 * 获取 银行别名
	 * @return String
	 */
	public String getBankAbbr() {
		return bankAbbr;
	}

	/**
	 * 设置 银行别名
	 * @param bankAbbr
	 */
	public void setBankAbbr(String bankAbbr) {
		this.bankAbbr = bankAbbr;
	}

    
    /**
	 * 获取 备注
	 * @return String
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置 备注
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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