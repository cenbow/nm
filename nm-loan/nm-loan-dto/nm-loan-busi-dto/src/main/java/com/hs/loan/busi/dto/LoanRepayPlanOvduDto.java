package com.hs.loan.busi.dto;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 分期还款计划逾期 对象
 * @author jqiu
 * @create 2015-10-30
 */
public class LoanRepayPlanOvduDto implements Serializable{
	private static final long serialVersionUID = 1L;
    
	/*** 还款计划ID */
	@NotBlank(message="还款计划ID不能为空")
  	@Size(max=40,message="还款计划ID超长")
  	private String planId ; 
    
    /*** 分期编号 */
	@NotBlank(message="分期编号不能为空")
  	@Size(max=40,message="分期编号超长")
  	private String loanNo ; 
    
    /*** 身份证号码 */
	@NotBlank(message="身份证号码不能为空")
  	@Size(max=40,message="身份证号码超长")
  	private String certNo ; 
    
    /*** 客户编号 */
	@NotBlank(message="客户编号不能为空")
  	@Size(max=40,message="客户编号超长")
  	private String custNo ; 
    
    /*** 客户姓名 */
	@NotBlank(message="客户姓名不能为空")
  	@Size(max=80,message="客户姓名超长")
  	private String custName ; 
    
    /*** 当前日期 */
	@NotBlank(message="当前日期不能为空")
  	@Size(max=10,message="当前日期超长")
  	private String currDate ; 
    
    /*** 期数 */
	@NotNull(message="期数不能为空")
  	@Size(max=11,message="期数超长")
  	private Integer instNum ; 
    
    /*** 账单日 */
	@NotBlank(message="账单日不能为空")
  	@Size(max=10,message="账单日超长")
  	private String busnDate ; 
    
    /*** 超期天数 */
	@NotNull(message="超期天数为空")
  	@Size(max=11,message="超期天数超长")
  	private Integer ovduDays ; 
    
    /*** 应还款总额 */
	@NotNull(message="应还款总额为空")
  	@Size(max=20,message="应还款总额超长")
  	private java.math.BigDecimal shldPayAmt ; 
    
    /*** 应还本金加利息 */
	@NotNull(message="应还本金加利息为空")
  	@Size(max=20,message="应还本金加利息超长")
  	private java.math.BigDecimal prinIntAmt ; 
    
    /*** 应还本金 */
	@NotNull(message="应还本金为空")
  	@Size(max=20,message="应还本金超长")
  	private java.math.BigDecimal prinAmt ; 
    
    /*** 应还利息 */
	@NotNull(message="应还利息为空")
  	@Size(max=20,message="应还利息超长")
  	private java.math.BigDecimal intAmt ; 
    
    /*** 滞纳金 */
	@NotNull(message="滞纳金为空")
  	@Size(max=20,message="滞纳金超长")
  	private java.math.BigDecimal ovduAmt ; 
    
    /*** 服务费 */
	@NotNull(message="服务费为空")
  	@Size(max=20,message="服务费超长")
  	private java.math.BigDecimal servAmt ; 
    
    /*** 账户管理费 */
	@NotNull(message="账户管理费为空")
  	@Size(max=20,message="账户管理费超长")
  	private java.math.BigDecimal acctAmt ; 
    
    /*** 保险费 */
	@NotNull(message="保险费为空")
  	@Size(max=20,message="保险费超长")
  	private java.math.BigDecimal insrAmt ; 
    
    /*** 手续费 */
	@NotNull(message="手续费为空")
  	@Size(max=20,message="手续费超长")
  	private java.math.BigDecimal feeAmt ; 
    
    /*** 提前结清手续费 */
	@NotNull(message="提前结清手续费为空")
  	@Size(max=20,message="提前结清手续费超长")
  	private java.math.BigDecimal unexpFeeAmt ; 
    
    /*** 已还金额 */
	@NotNull(message="已还金额为空")
  	@Size(max=20,message="已还金额超长")
  	private java.math.BigDecimal actPayAmt ; 
    
    /*** 剩余金额 */
	@NotNull(message="剩余金额为空")
  	@Size(max=20,message="剩余金额超长")
  	private java.math.BigDecimal payBal ; 
    
    /*** 办理人 */
	@NotBlank(message="办理人为空")
  	@Size(max=40,message="办理人超长")
  	private String staffNo ; 
    
    /*** 创建日期 */
	@Future(message="创建日期必须晚于当前时间")
  	private Date instDate ; 
    
    /*** 更新日期 */
	@Future(message="更新日期必须晚于当前时间")
  	private Date updtDate ;

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCurrDate() {
		return currDate;
	}

	public void setCurrDate(String currDate) {
		this.currDate = currDate;
	}

	public Integer getInstNum() {
		return instNum;
	}

	public void setInstNum(Integer instNum) {
		this.instNum = instNum;
	}

	public String getBusnDate() {
		return busnDate;
	}

	public void setBusnDate(String busnDate) {
		this.busnDate = busnDate;
	}

	public Integer getOvduDays() {
		return ovduDays;
	}

	public void setOvduDays(Integer ovduDays) {
		this.ovduDays = ovduDays;
	}

	public java.math.BigDecimal getShldPayAmt() {
		return shldPayAmt;
	}

	public void setShldPayAmt(java.math.BigDecimal shldPayAmt) {
		this.shldPayAmt = shldPayAmt;
	}

	public java.math.BigDecimal getPrinIntAmt() {
		return prinIntAmt;
	}

	public void setPrinIntAmt(java.math.BigDecimal prinIntAmt) {
		this.prinIntAmt = prinIntAmt;
	}

	public java.math.BigDecimal getPrinAmt() {
		return prinAmt;
	}

	public void setPrinAmt(java.math.BigDecimal prinAmt) {
		this.prinAmt = prinAmt;
	}

	public java.math.BigDecimal getIntAmt() {
		return intAmt;
	}

	public void setIntAmt(java.math.BigDecimal intAmt) {
		this.intAmt = intAmt;
	}

	public java.math.BigDecimal getOvduAmt() {
		return ovduAmt;
	}

	public void setOvduAmt(java.math.BigDecimal ovduAmt) {
		this.ovduAmt = ovduAmt;
	}

	public java.math.BigDecimal getServAmt() {
		return servAmt;
	}

	public void setServAmt(java.math.BigDecimal servAmt) {
		this.servAmt = servAmt;
	}

	public java.math.BigDecimal getAcctAmt() {
		return acctAmt;
	}

	public void setAcctAmt(java.math.BigDecimal acctAmt) {
		this.acctAmt = acctAmt;
	}

	public java.math.BigDecimal getInsrAmt() {
		return insrAmt;
	}

	public void setInsrAmt(java.math.BigDecimal insrAmt) {
		this.insrAmt = insrAmt;
	}

	public java.math.BigDecimal getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(java.math.BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}

	public java.math.BigDecimal getUnexpFeeAmt() {
		return unexpFeeAmt;
	}

	public void setUnexpFeeAmt(java.math.BigDecimal unexpFeeAmt) {
		this.unexpFeeAmt = unexpFeeAmt;
	}

	public java.math.BigDecimal getActPayAmt() {
		return actPayAmt;
	}

	public void setActPayAmt(java.math.BigDecimal actPayAmt) {
		this.actPayAmt = actPayAmt;
	}

	public java.math.BigDecimal getPayBal() {
		return payBal;
	}

	public void setPayBal(java.math.BigDecimal payBal) {
		this.payBal = payBal;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public Date getInstDate() {
		return instDate;
	}

	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

	public Date getUpdtDate() {
		return updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	} 
  	
}