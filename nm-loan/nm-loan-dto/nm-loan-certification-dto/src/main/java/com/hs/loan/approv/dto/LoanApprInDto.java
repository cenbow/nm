package com.hs.loan.approv.dto;


import java.io.Serializable;
import java.util.Date;

/**
 * APP_分期审批信息 对象
 * @author autocreate
 * @create 2015-11-23
 */
public class LoanApprInDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 审批案件ID */
  	private String apprId ; 
    
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /*** 客户名称 */
  	private String custName ; 
    
    /*** 销售编号 */
  	private String saleNo ; 
    
    /*** 销售名称 */
  	private String saleName ; 
    
    /*** 待自动审批、自动审批中、自动审批通过、自动审批不通过、待分配、人工审批中、人工审批通过、人工审批不通过、人工审批驳回 */
  	private String stat ; 
    
    /*** 审批员编号 */
  	private String apprNo ; 
    
    /*** 审批员姓名 */
  	private String apprName ; 
      
    /*** 审批说明 */
  	private String apprDesc ; 
    
    /*** 备注 */
  	private String remark ; 

    //构造函数
    public LoanApprInDto(){}

    //getter和setter方法
    
    /**
	 * 获取 审批案件ID
	 * @return String
	 */
	public String getApprId() {
		return apprId;
	}

	/**
	 * 设置 审批案件ID
	 * @param apprId
	 */
	public void setApprId(String apprId) {
		this.apprId = apprId;
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
	 * 获取 销售编号
	 * @return String
	 */
	public String getSaleNo() {
		return saleNo;
	}

	/**
	 * 设置 销售编号
	 * @param saleNo
	 */
	public void setSaleNo(String saleNo) {
		this.saleNo = saleNo;
	}

    
    /**
	 * 获取 销售名称
	 * @return String
	 */
	public String getSaleName() {
		return saleName;
	}

	/**
	 * 设置 销售名称
	 * @param saleName
	 */
	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}
    /**
	 * 获取 待自动审批、自动审批中、自动审批通过、自动审批不通过、待分配、人工审批中、人工审批通过、人工审批不通过、人工审批驳回
	 * @return String
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * 设置 待自动审批、自动审批中、自动审批通过、自动审批不通过、待分配、人工审批中、人工审批通过、人工审批不通过、人工审批驳回
	 * @param stat
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}

  

    
    /**
	 * 获取 审批员编号
	 * @return String
	 */
	public String getApprNo() {
		return apprNo;
	}

	/**
	 * 设置 审批员编号
	 * @param apprNo
	 */
	public void setApprNo(String apprNo) {
		this.apprNo = apprNo;
	}

    
    /**
	 * 获取 审批员姓名
	 * @return String
	 */
	public String getApprName() {
		return apprName;
	}

	/**
	 * 设置 审批员姓名
	 * @param apprName
	 */
	public void setApprName(String apprName) {
		this.apprName = apprName;
	}

    /**
	 * 获取 审批说明
	 * @return String
	 */
	public String getApprDesc() {
		return apprDesc;
	}

	/**
	 * 设置 审批说明
	 * @param apprDesc
	 */
	public void setApprDesc(String apprDesc) {
		this.apprDesc = apprDesc;
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

}