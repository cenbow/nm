package com.hs.loan.market.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class BranchGroupDto implements Serializable{

	private static final long serialVersionUID = 4690182893693856649L;

	
	/***  */
  	private String id ; 
    
    /*** 网点编码 */
  	@NotBlank(message="网点编码不能为空")
  	@Size(max=40,message="网点编码超长")
  	private String branchNo ; 
    
    /*** 网点组编号 */
  	@NotBlank(message="网点组编号不能为空")
  	@Size(max=40,message="网点组编号超长")
  	private String groupNo ; 

    //构造函数
    public BranchGroupDto(){}

    //getter和setter方法
    
    /**
	 * 获取 
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
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

    
    /**
	 * 获取 商户编号
	 * @return String
	 */
	public String getGroupNo() {
		return groupNo;
	}

	/**
	 * 设置 商户编号
	 * @param groupNo
	 */
	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}
	
	
	
}
