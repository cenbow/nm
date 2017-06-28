package com.hs.loan.busi.dto;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_分期与产品关系 对象
 * @author autocreate
 * @create 2015-10-29
 */
public class AppLoanProdDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
	 /*** ID */
  	private String id ; 
    
    /*** 分期编码 */
  	private String loanNo ; 
    
    /*** 产品编号 */
  	private String prodNo ; 
    
    /*** 产品名称 */
  	private String prodName ; 
    
    /*** 产品类型 */
  	private String prodTyp ; 
    
    /*** 产品利率 */
  	private java.math.BigDecimal rat ; 
    
    /*** 使用客户类型 */
  	private String custType ; 
    
    /*** 还款日规则编号 */
  	private String firstNo ; 
    
    /*** 是否前置收费 */
  	private String isPrepost ; 
    
    /*** 前置业务利率 */
  	private java.math.BigDecimal prepostRat ; 
    
    /*** 首付比例类型 */
  	private String fstPayTyp ; 
    
    /*** 首付值 */
  	private java.math.BigDecimal fstPayVal ; 
    
    /*** 分期金额最低值 */
  	private java.math.BigDecimal minAmt ; 
    
    /*** 分期金额最高值 */
  	private java.math.BigDecimal maxAmt ; 
    
    /*** 还款方式 */
  	private String repayKind ; 
    
    /*** 产品提成 */
  	private java.math.BigDecimal commAmt ; 
    
    /*** 产品说明 */
  	private String prodRemark ; 
    
    /*** 产品销售渠道 */
  	private String saleChan ; 
    
    /*** 有效开始时间 */
  	private Date startDate ; 
    
    /*** 有效结束时间 */
  	private Date endDate ; 
    
    /*** 状态 */
  	private String prodStat ; 
    
    /*** 创建时间 */
  	private Date instDate ; 
    
    /*** 更新时间 */
  	private Date updtDate ; 

    //构造函数
    public AppLoanProdDto(){}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getProdNo() {
		return prodNo;
	}

	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdTyp() {
		return prodTyp;
	}

	public void setProdTyp(String prodTyp) {
		this.prodTyp = prodTyp;
	}

	public java.math.BigDecimal getRat() {
		return rat;
	}

	public void setRat(java.math.BigDecimal rat) {
		this.rat = rat;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getFirstNo() {
		return firstNo;
	}

	public void setFirstNo(String firstNo) {
		this.firstNo = firstNo;
	}

	public String getIsPrepost() {
		return isPrepost;
	}

	public void setIsPrepost(String isPrepost) {
		this.isPrepost = isPrepost;
	}

	public java.math.BigDecimal getPrepostRat() {
		return prepostRat;
	}

	public void setPrepostRat(java.math.BigDecimal prepostRat) {
		this.prepostRat = prepostRat;
	}

	public String getFstPayTyp() {
		return fstPayTyp;
	}

	public void setFstPayTyp(String fstPayTyp) {
		this.fstPayTyp = fstPayTyp;
	}

	public java.math.BigDecimal getFstPayVal() {
		return fstPayVal;
	}

	public void setFstPayVal(java.math.BigDecimal fstPayVal) {
		this.fstPayVal = fstPayVal;
	}

	public java.math.BigDecimal getMinAmt() {
		return minAmt;
	}

	public void setMinAmt(java.math.BigDecimal minAmt) {
		this.minAmt = minAmt;
	}

	public java.math.BigDecimal getMaxAmt() {
		return maxAmt;
	}

	public void setMaxAmt(java.math.BigDecimal maxAmt) {
		this.maxAmt = maxAmt;
	}

	public String getRepayKind() {
		return repayKind;
	}

	public void setRepayKind(String repayKind) {
		this.repayKind = repayKind;
	}

	public java.math.BigDecimal getCommAmt() {
		return commAmt;
	}

	public void setCommAmt(java.math.BigDecimal commAmt) {
		this.commAmt = commAmt;
	}

	public String getProdRemark() {
		return prodRemark;
	}

	public void setProdRemark(String prodRemark) {
		this.prodRemark = prodRemark;
	}

	public String getSaleChan() {
		return saleChan;
	}

	public void setSaleChan(String saleChan) {
		this.saleChan = saleChan;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getProdStat() {
		return prodStat;
	}

	public void setProdStat(String prodStat) {
		this.prodStat = prodStat;
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