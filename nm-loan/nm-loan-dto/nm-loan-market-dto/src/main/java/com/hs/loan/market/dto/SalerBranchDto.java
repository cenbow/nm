package com.hs.loan.market.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class SalerBranchDto implements Serializable{
	private static final long serialVersionUID = -6119842100143097188L;
	
	/*** ID */
  	private String id ; 
    
    /*** 销售编号 */
  	@NotBlank(message="销售编号不能为空")
  	@Size(max=40,message="销售编号超长")
  	private String staffNo ; 
    
    /*** 网点编码 */
  	@NotBlank(message="网点编码不能为空")
  	@Size(max=40,message="网点编码超长")
  	private String branchNo ; 

    //构造函数
    public SalerBranchDto(){}

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
