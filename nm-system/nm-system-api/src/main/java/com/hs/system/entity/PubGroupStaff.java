package com.hs.system.entity;


import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * PUB_销售组与用户关系 对象
 * @author autocreate
 * @create 2015-10-15
 */
public class PubGroupStaff implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 销售组编号 */
  	@NotBlank(message="销售组编号不允许为空")
  	@Size(max=40,message="销售组编号超长")
  	private String groupNo ; 
    
    /*** 员工编号 */
  	@NotBlank(message="员工编号不允许为空")
  	@Size(max=40,message="员工编号超长")
  	private String staffNo ; 

    //构造函数
    public PubGroupStaff(){}

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
	 * 获取 销售组编号
	 * @return String
	 */
	public String getGroupNo() {
		return groupNo;
	}

	/**
	 * 设置 销售组编号
	 * @param groupNo
	 */
	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

}