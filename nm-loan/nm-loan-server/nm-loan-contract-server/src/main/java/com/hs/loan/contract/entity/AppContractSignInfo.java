package com.hs.loan.contract.entity;


import java.io.Serializable;

/**
 *  对象
 * @author autocreate
 * @create 2015-12-25
 */
public class AppContractSignInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 申请单号 */
  	private String loanNo ; 
    
    /*** 合同编号 */
  	private String contractNo ; 
    
    /*** 柠檬签约状态 */
  	private String signStatus ; 
    
    /*** 合同URL */
  	private String contractUrl ; 
  	
  	/*** 客户签约状态 */
  	private String custSignStatus ;

	private String contractSignId;

	private String signType;
	//签名文档编号
	private String docid;

	//签约电话
	private String signPhone;

	public String getSignPhone() {
		return signPhone;
	}

	public void setSignPhone(String signPhone) {
		this.signPhone = signPhone;
	}

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public String getContractSignId() {
		return contractSignId;
	}

	public void setContractSignId(String contractSignId) {
		this.contractSignId = contractSignId;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	//构造函数
    public AppContractSignInfo(){}

    //getter和setter方法
    
    /**
	 * 获取 申请单号
	 * @return String
	 */
	public String getLoanNo() {
		return loanNo;
	}

	/**
	 * 设置 申请单号
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

    
    /**
	 * 获取 合同编号
	 * @return String
	 */
	public String getContractNo() {
		return contractNo;
	}

	/**
	 * 设置 合同编号
	 * @param contractNo
	 */
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

    
    /**
	 * 获取 签约状态
	 * @return String
	 */
	public String getSignStatus() {
		return signStatus;
	}

	/**
	 * 设置 签约状态
	 * @param signStatus
	 */
	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}

    
    /**
	 * 获取 合同URL
	 * @return String
	 */
	public String getContractUrl() {
		return contractUrl;
	}

	/**
	 * 设置 合同URL
	 * @param contractUrl
	 */
	public void setContractUrl(String contractUrl) {
		this.contractUrl = contractUrl;
	}

    /**
	 * 获取 客户签约URL
	 * @return String
	 */
	public String getCustSignStatus() {
		return custSignStatus;
	}

	/**
	 * 设置 客户签约URL
	 * @param contractUrl
	 */
	public void setCustSignStatus(String custSignStatus) {
		this.custSignStatus = custSignStatus;
	}
}