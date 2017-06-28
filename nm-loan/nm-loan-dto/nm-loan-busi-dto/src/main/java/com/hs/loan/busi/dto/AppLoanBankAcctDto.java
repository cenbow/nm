package com.hs.loan.busi.dto;


import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * APP_分期与银行账户关系 对象
 * @author autocreate
 * @create 2015-10-29
 */
public class AppLoanBankAcctDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 分期编号 */
  	@NotBlank(message="分期编号不能为空") 
	@Size(min = 1, max = 40,message="分期编号超长")
  	private String loanNo ; 
    
    /*** 银行帐号ID */
  	@NotBlank(message="银行帐号不能为空") 
	@Size(min = 1, max = 40,message="银行帐号超长")
  	private String bankAcctId ; 
    
    /*** 状态 */
  	@NotBlank(message="状态不能为空") 
	@Size(min = 8, max = 40,message="状态超长")
  	private String stat ; 
    
    /*** 新增时间 */
  	@Future(message="创建日期必须晚于当前时间")
  	private Date instDate ; 
    
    /*** 修改时间 */
  	@Future(message="更新日期必须晚于当前时间")
  	private Date updtDate ; 

    //构造函数
    public AppLoanBankAcctDto(){}

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
	 * 获取 银行帐号ID
	 * @return String
	 */
	public String getBankAcctId() {
		return bankAcctId;
	}

	/**
	 * 设置 银行帐号ID
	 * @param bankAcctId
	 */
	public void setBankAcctId(String bankAcctId) {
		this.bankAcctId = bankAcctId;
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

    
    /**
	 * 获取 修改时间
	 * @return Date
	 */
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 修改时间
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

}