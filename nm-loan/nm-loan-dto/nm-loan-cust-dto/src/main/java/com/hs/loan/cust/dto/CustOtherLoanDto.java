package com.hs.loan.cust.dto;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

/**
 * APP_客户其他分期信息 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class CustOtherLoanDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/*** ID */
//	@NotBlank (message="表主键不能为空")
//	@Size(max=40,message="表主键超长")
  	private String id ; 
    
    /*** 客户编号 */
	@NotBlank (message="客户编号不能为空")
	@Size(max=32,message="客户编号超长")
  	private String custNo ; 
    
    /*** 信贷办理机构 */
	@NotBlank (message="信贷办理机构不能为空")
	@Size(max=8,message="信贷办理机构超长")
  	private String openOrg ; 
    
    /*** 信贷分期金额 */
	@NotBlank (message="信贷分期金额不能为空")
	@Size(max=8,message="信贷分期金额超长")
  	private String loanAmt ; 
  	
  	/*** 每月还款额 */
	@NotNull (message="每月还款额不能为空")
	@Size(max=12,message="每月还款额超长")
  	private java.math.BigDecimal mthAmt ; 
    
    /*** 信贷每月还款日 */
	@NotBlank (message="信贷每月还款日不能为空")
	@Size(max=8,message="信贷每月还款日超长")
  	private String loanMthDay ; 
    
    /*** 信贷剩余期限(月) */
	@NotBlank (message="信贷剩余期限(月)不能为空")
	@Size(max=8,message="信贷剩余期限(月)超长")
  	private String loanMonth ; 
    
    /*** 备注 */
	@Size( max = 128,message="备注超长")
  	private String remark ; 
    
    /*** 开始日期 */
  	@NotNull (message="开始日期不能为空")
  	private Date beginDate ; 
    
    /*** 结束日期 */
  	@NotNull (message="结束日期不能为空")
  	private Date endDate ; 
  	
  	/*** 分期期数 */
  	private Integer instNum ; 
  	
    //构造函数
    public CustOtherLoanDto(){}

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
	 * 获取 信贷办理机构
	 * @return String
	 */
	public String getOpenOrg() {
		return openOrg;
	}

	/**
	 * 设置 信贷办理机构
	 * @param openOrg
	 */
	public void setOpenOrg(String openOrg) {
		this.openOrg = openOrg;
	}

    
    /**
	 * 获取 信贷分期金额
	 * @return String
	 */
	public String getLoanAmt() {
		return loanAmt;
	}

	/**
	 * 设置 信贷分期金额
	 * @param loanAmt
	 */
	public void setLoanAmt(String loanAmt) {
		this.loanAmt = loanAmt;
	}

    
    /**
	 * 获取 信贷每月还款日
	 * @return String
	 */
	public String getLoanMthDay() {
		return loanMthDay;
	}

	/**
	 * 设置 信贷每月还款日
	 * @param loanMthDay
	 */
	public void setLoanMthDay(String loanMthDay) {
		this.loanMthDay = loanMthDay;
	}

    
    /**
	 * 获取 信贷剩余期限(月)
	 * @return String
	 */
	public String getLoanMonth() {
		return loanMonth;
	}

	/**
	 * 设置 信贷剩余期限(月)
	 * @param loanMonth
	 */
	public void setLoanMonth(String loanMonth) {
		this.loanMonth = loanMonth;
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
	 * 获取 开始日期
	 * @return Date
	 */
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置 开始日期
	 * @param beginDate
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

    
    /**
	 * 获取 结束日期
	 * @return Date
	 */
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置 结束日期
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public java.math.BigDecimal getMthAmt() {
		return mthAmt;
	}

	public void setMthAmt(java.math.BigDecimal mthAmt) {
		this.mthAmt = mthAmt;
	}

	public Integer getInstNum() {
		return instNum;
	}

	public void setInstNum(Integer instNum) {
		this.instNum = instNum;
	}

}