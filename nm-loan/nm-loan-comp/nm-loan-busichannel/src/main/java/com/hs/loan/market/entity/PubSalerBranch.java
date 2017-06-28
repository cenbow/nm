package com.hs.loan.market.entity;

import java.io.Serializable;

/**
 * PUB_销售网点关联信息 对象
 * @author autocreate
 * @create 2015-10-15
 */
public class PubSalerBranch implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 销售编号 */
  	private String staffNo ; 
    
    /*** 网点编码 */
  	private String branchNo ; 

    //构造函数
    public PubSalerBranch(){}

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

    public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	/**
	 * 获取 网点编码
	 * @return String
	 */
	public String getBranchNo() {
		return branchNo;
	}

	/**
	 * 设置 网点编码
	 * @param branchNo
	 */
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}

}