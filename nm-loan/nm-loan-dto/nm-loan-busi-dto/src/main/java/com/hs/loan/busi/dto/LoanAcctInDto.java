package com.hs.loan.busi.dto;


import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 分期基本信息新增 对象
 * @author jqiu
 * @create 2015-10-27
 */
public class LoanAcctInDto implements Serializable{
	private static final long serialVersionUID = 1L;
    
	/*** 机构号 */
  	private String orgNo ; 
  	
  	/*** 机构名称 */
  	private String orgName ; 
  	
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 客户编号 */
  	@NotBlank(message="客户编号不能为空")
  	@Size(max=40,message="客户编号超长")
  	private String custNo ; 
  	/*** 客户编号 */
  	@NotBlank(message="客户名称不能为空")
  	@Size(max=10,message="客户名称超长")
  	private String custName ; 
  	
  	/*** 产品编号 */
  	@NotBlank(message="产品编号不能为空")
  	@Size(max=40,message="产品编号超长")
  	private String prodNo ; 
  	/*** 客户类型 */
  	private String custType ; 
  	/*** 网点编号 */
  	@NotBlank(message="网点编号不能为空")
  	@Size(max=40,message="网点编号超长")
  	private String branchNo ; 
  	/*** 网点编号 */
  	private String branchName ; 
    
    /*** 首付金额 */
  	@NotNull(message="首付金额不能为空")
  	@Size(max=20,message="首付金额超长")
  	private java.math.BigDecimal fstPayAmt ; 
    
    /*** 分期本金 */
  	@NotNull(message="分期本金不能为空")
  	@Size(max=20,message="分期本金超长")
  	private java.math.BigDecimal loanAmt ; 
    
    /*** 分期类型 */
  	@NotBlank(message="分期本金不能为空")
  	@Size(max=8,message="分期本金超长")
  	private String loanTyp ; 
    
    /*** 分期期数 */
  	@NotNull(message="分期期数不能为空")
  	@Size(max=11,message="分期期数超长")
  	private Integer instNum ; 
    
    /*** 办理所在省 */
  	@NotBlank(message="办理所在省不能为空")
  	@Size(max=128,message="办理所在省超长")
  	private String applyProv ; 
    
    /*** 办理所在区/县 */
  	@NotBlank(message="办理所在区/县不能为空")
  	@Size(max=128,message="办理所在区/县超长")
  	private String applyArea ; 
    
    /*** 办理所在市 */
  	@NotBlank(message="办理所在市不能为空")
  	@Size(max=128,message="办理所在市超长")
  	private String applyCity ; 

  	 /*** 档案编号 */
  	@Size(max=8,message="档案编号超长")
  	private String fileNo ;
  	
	/*** 备注*/
  	private String loanRemark; 
  	
	/*** 信托标志*/
  	private String entrFlag; 
  	
  	/**客户选中的费用项**/
  	private String strSeleFees;
  	private List<LoanFeeDto> selectFees;
  	
  	private List<LoanGoodsDto> goodsDto;

	/*** 挂单商户名称*/
	@Size(max = 200,message = "挂单商户名称超长")
	private String marName;

	//挂单商户联系人
	private String marContctPerson;

	//挂单商户联系人电话
	private String marContctTel;

	private String applyAddr;
	//通讯地址
	private String contactAddr;
	//销售渠道
	private String saleChanl;

	public String getMarContctPerson() {
		return marContctPerson;
	}

	public void setMarContctPerson(String marContctPerson) {
		this.marContctPerson = marContctPerson;
	}

	public String getMarContctTel() {
		return marContctTel;
	}

	public void setMarContctTel(String marContctTel) {
		this.marContctTel = marContctTel;
	}

	public String getContactAddr() {
		return contactAddr;
	}

	public void setContactAddr(String contactAddr) {
		this.contactAddr = contactAddr;
	}

	public String getApplyAddr() {
		return applyAddr;
	}

	public void setApplyAddr(String applyAddr) {
		this.applyAddr = applyAddr;
	}
	//getter和setter方法
	/**
	 * 获取 挂单商户名称
	 * @return String
	 */
	public String getMarName() {
		return marName;
	}

	/**
	 * 设置 挂单商户名称
	 * @param marName
	 */

	public void setMarName(String marName) {
		this.marName = marName;
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
	 * 获取 首付金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getFstPayAmt() {
		return fstPayAmt;
	}

	/**
	 * 设置 首付金额
	 * @param fstPayAmt
	 */
	public void setFstPayAmt(java.math.BigDecimal fstPayAmt) {
		this.fstPayAmt = fstPayAmt;
	}

    

    
    /**
	 * 获取 分期本金
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getLoanAmt() {
		return loanAmt;
	}

	/**
	 * 设置 分期本金
	 * @param loanAmt
	 */
	public void setLoanAmt(java.math.BigDecimal loanAmt) {
		this.loanAmt = loanAmt;
	}

    
    /**
	 * 获取 分期类型
	 * @return String
	 */
	public String getLoanTyp() {
		return loanTyp;
	}

	/**
	 * 设置 分期类型
	 * @param loanTyp
	 */
	public void setLoanTyp(String loanTyp) {
		this.loanTyp = loanTyp;
	}

    
    /**
	 * 获取 分期期数
	 * @return Integer
	 */
	public Integer getInstNum() {
		return instNum;
	}

	/**
	 * 设置 分期期数
	 * @param instNum
	 */
	public void setInstNum(Integer instNum) {
		this.instNum = instNum;
	}

    
    /**
	 * 获取 办理所在省
	 * @return String
	 */
	public String getApplyProv() {
		return applyProv;
	}

	/**
	 * 设置 办理所在省
	 * @param applyProv
	 */
	public void setApplyProv(String applyProv) {
		this.applyProv = applyProv;
	}

    
    /**
	 * 获取 办理所在区/县
	 * @return String
	 */
	public String getApplyArea() {
		return applyArea;
	}

	/**
	 * 设置 办理所在区/县
	 * @param applyArea
	 */
	public void setApplyArea(String applyArea) {
		this.applyArea = applyArea;
	}

    
    /**
	 * 获取 办理所在市
	 * @return String
	 */
	public String getApplyCity() {
		return applyCity;
	}

	/**
	 * 设置 办理所在市
	 * @param applyCity
	 */
	public void setApplyCity(String applyCity) {
		this.applyCity = applyCity;
	}

	public String getProdNo() {
		return prodNo;
	}

	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getBranchNo() {
		return branchNo;
	}

	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}

	public List<LoanGoodsDto> getGoodsDto() {
		return goodsDto;
	}

	public void setGoodsDto(List<LoanGoodsDto> goodsDto) {
		this.goodsDto = goodsDto;
	}

	public List<LoanFeeDto> getSelectFees() {
		return selectFees;
	}

	public void setSelectFees(List<LoanFeeDto> selectFees) {
		this.selectFees = selectFees;
	}

	public String getStrSeleFees() {
		return strSeleFees;
	}

	public void setStrSeleFees(String strSeleFees) {
		this.strSeleFees = strSeleFees;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getLoanRemark() {
		return loanRemark;
	}

	public void setLoanRemark(String loanRemark) {
		this.loanRemark = loanRemark;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getEntrFlag() {
		return entrFlag;
	}

	public void setEntrFlag(String entrFlag) {
		this.entrFlag = entrFlag;
	}

	public String getSaleChanl() {
		return saleChanl;
	}

	public void setSaleChanl(String saleChanl) {
		this.saleChanl = saleChanl;
	}

 
}