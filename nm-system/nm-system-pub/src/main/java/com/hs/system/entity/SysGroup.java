package com.hs.system.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 编码组 对象
 * @author autocreate
 * @create 2015-09-29
 */
public class SysGroup implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 编码代码 */
    @NotBlank(message="编码代码不能为空")
	@Size(max=40,message="编码代码超长")
  	private String typId ; 
    
    /*** 分组值 */
    @NotBlank(message="分组值不能为空")
	@Size(max=20,message="分组值超长")
  	private String groupCod ; 
    
    /*** 备注 */
  	@Size(max=120,message="备注超长")
  	private String remark ; 
    
    /*** 创建时间 */
  	@Future(message="创建时间必须晚于当前时间")
  	private Date instDate ; 
    
    /*** 更新时间 */
  	@Future(message="更新时间必须晚于当前时间")
  	private Date updtDate ; 
  	
  	/*** 对应code列表 */
  	private List<SysCodInfo> infoList = new ArrayList<SysCodInfo>(0);

    //构造函数
    public SysGroup(){}

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
	 * 获取 编码代码
	 * @return String
	 */
	public String getTypId() {
		return typId;
	}

	/**
	 * 设置 编码代码
	 * @param typId
	 */
	public void setTypId(String typId) {
		this.typId = typId;
	}

    
    /**
	 * 获取 分组值
	 * @return String
	 */
	public String getGroupCod() {
		return groupCod;
	}

	/**
	 * 设置 分组值
	 * @param groupCod
	 */
	public void setGroupCod(String groupCod) {
		this.groupCod = groupCod;
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

    
    /**
	 * 获取 创建时间
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 创建时间
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

    
    /**
	 * 获取 更新时间
	 * @return Date
	 */
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 更新时间
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	public List<SysCodInfo> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<SysCodInfo> infoList) {
		this.infoList = infoList;
	}

}