package com.hs.system.entity;


import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 编码信息 对象
 * @author autocreate
 * @create 2015-09-28
 */
public class SysCodInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
  	private String id ; 
  	
  	@NotBlank(message="码值不能为空")
	@Size(max=10,message="码值超长")
  	private String codVal ; 

  	@NotBlank(message="码值名称不能为空")
	@Size(max=80,message="码值名称超长")
  	private String codName ; 

  	@NotBlank(message="编码代码不能为空")
	@Size(max=40,message="编码代码超长")
  	private String codTyp ; 
  	
  	@NotBlank(message="码值状态不能为空")
	@Size(max=10,message="码值状态超长")
  	private String stat ; 

  	@Future(message="插入时间必须晚于当前时间")
  	private Date instDate ; 

  	@Future(message="更新时间必须晚于当前时间")
  	private Date updtDate ; 

  	/**
  	 * 类型名，查询时使用
  	 */
  	private String codTypName ; 

    //构造函数
    public SysCodInfo(){}

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
	 * 获取 
	 * @return String
	 */
	public String getCodVal() {
		return codVal;
	}

	/**
	 * 设置 
	 * @param codVal
	 */
	public void setCodVal(String codVal) {
		this.codVal = codVal;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getCodName() {
		return codName;
	}

	/**
	 * 设置 
	 * @param codName
	 */
	public void setCodName(String codName) {
		this.codName = codName;
	}

    
    public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	/**
	 * 获取 
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

    
    /**
	 * 获取 
	 * @return Date
	 */
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	
	
	public String getCodTyp() {
		return codTyp;
	}

	public void setCodTyp(String codTyp) {
		this.codTyp = codTyp;
	}

	public String getCodTypName() {
		return codTypName;
	}

	public void setCodTypName(String codTypName) {
		this.codTypName = codTypName;
	}

}