package com.hs.loan.busi.dto;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 分期还款计划 对象
 * @author jqiu
 * @create 2015-10-30
 */
public class LoanRepayPlanDto implements Serializable{
	private static final long serialVersionUID = 1L;
    
	/*** 分期编号 */
	@NotBlank(message="分期编号不能为空")
  	@Size(max=40,message="分期编号超长")
  	private String loanNo ; 
    
    /*** 应还账单日 */
	@NotBlank(message="应还账单日不能为空")
  	@Size(max=10,message="应还账单日超长")
  	private String repayDate ; 
    
    /*** 总期数 */
	@NotNull(message="总期数不能为空")
  	@Size(max=11,message="总期数超长")
  	private Integer num ; 
    
    /*** 当前期数 */
	@NotNull(message="当前期数不能为空")
  	@Size(max=11,message="当前期数超长")
  	private Integer repayNum ; 
    
    /*** 还款金额 */
	@NotNull(message="还款金额不能为空")
  	@Size(max=20,message="还款金额超长")
  	private java.math.BigDecimal repayAmt ; 
    
    /*** 提前结清金额 */
	@NotNull(message="提前结清金额不能为空")
  	@Size(max=20,message="提前结清金额超长")
  	private java.math.BigDecimal advAmt ; 
    
    /*** 本金加利息 */
	@NotNull(message="本金加利息不能为空")
  	@Size(max=20,message="本金加利息超长")
  	private java.math.BigDecimal prinInterAmt ; 
    
    /*** 本金金额 */
	@NotNull(message="本金金额不能为空")
  	@Size(max=20,message="本金金额超长")
  	private java.math.BigDecimal prinAmt ; 
    
    /*** 利息金额 */
	@NotNull(message="利息金额不能为空")
  	@Size(max=20,message="利息金额超长")
  	private java.math.BigDecimal interAmt ; 
    
    /*** 滞纳金金额 */
	@NotNull(message="滞纳金金额不能为空")
  	@Size(max=20,message="滞纳金金额超长")
  	private java.math.BigDecimal overdueAmt ; 
    
    /*** 服务费 */
	@NotNull(message="服务费不能为空")
  	@Size(max=20,message="服务费超长")
  	private java.math.BigDecimal serviceAmt ; 
    
    /*** 账户管理费 */
	@NotNull(message="账户管理费不能为空")
  	@Size(max=20,message="账户管理费超长")
  	private java.math.BigDecimal acctMngAmt ; 
    
    /*** 保险费 */
	@NotNull(message="保险费不能为空")
  	@Size(max=20,message="保险费超长")
  	private java.math.BigDecimal insuAmt ; 
    
    /*** 手续费 */
	@NotNull(message="手续费不能为空")
  	@Size(max=20,message="手续费超长")
  	private java.math.BigDecimal feeAmt ; 
    
    /*** 提前还款违约金 */
	@NotNull(message="提前还款违约金不能为空")
  	@Size(max=20,message="提前还款违约金超长")
  	private java.math.BigDecimal unexpPayAmt ; 
    
    /*** 灵活还款包费 */
	@NotNull(message="灵活还款包费不能为空")
  	@Size(max=20,message="灵活还款包费超长")
  	private java.math.BigDecimal agilityAmt ; 
    
    /*** 创建时间 */
	@Future(message="创建时间必须晚于当前时间")
  	private Date instDate ; 
    
    /*** 更新时间 */
	@Future(message="更新时间必须晚于当前时间")
  	private Date updtDate ;

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getRepayNum() {
		return repayNum;
	}

	public void setRepayNum(Integer repayNum) {
		this.repayNum = repayNum;
	}

	public java.math.BigDecimal getRepayAmt() {
		return repayAmt;
	}

	public void setRepayAmt(java.math.BigDecimal repayAmt) {
		this.repayAmt = repayAmt;
	}

	public java.math.BigDecimal getAdvAmt() {
		return advAmt;
	}

	public void setAdvAmt(java.math.BigDecimal advAmt) {
		this.advAmt = advAmt;
	}

	public java.math.BigDecimal getPrinInterAmt() {
		return prinInterAmt;
	}

	public void setPrinInterAmt(java.math.BigDecimal prinInterAmt) {
		this.prinInterAmt = prinInterAmt;
	}

	public java.math.BigDecimal getPrinAmt() {
		return prinAmt;
	}

	public void setPrinAmt(java.math.BigDecimal prinAmt) {
		this.prinAmt = prinAmt;
	}

	public java.math.BigDecimal getInterAmt() {
		return interAmt;
	}

	public void setInterAmt(java.math.BigDecimal interAmt) {
		this.interAmt = interAmt;
	}

	public java.math.BigDecimal getOverdueAmt() {
		return overdueAmt;
	}

	public void setOverdueAmt(java.math.BigDecimal overdueAmt) {
		this.overdueAmt = overdueAmt;
	}

	public java.math.BigDecimal getServiceAmt() {
		return serviceAmt;
	}

	public void setServiceAmt(java.math.BigDecimal serviceAmt) {
		this.serviceAmt = serviceAmt;
	}

	public java.math.BigDecimal getAcctMngAmt() {
		return acctMngAmt;
	}

	public void setAcctMngAmt(java.math.BigDecimal acctMngAmt) {
		this.acctMngAmt = acctMngAmt;
	}

	public java.math.BigDecimal getInsuAmt() {
		return insuAmt;
	}

	public void setInsuAmt(java.math.BigDecimal insuAmt) {
		this.insuAmt = insuAmt;
	}

	public java.math.BigDecimal getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(java.math.BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}

	public java.math.BigDecimal getUnexpPayAmt() {
		return unexpPayAmt;
	}

	public void setUnexpPayAmt(java.math.BigDecimal unexpPayAmt) {
		this.unexpPayAmt = unexpPayAmt;
	}

	public java.math.BigDecimal getAgilityAmt() {
		return agilityAmt;
	}

	public void setAgilityAmt(java.math.BigDecimal agilityAmt) {
		this.agilityAmt = agilityAmt;
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