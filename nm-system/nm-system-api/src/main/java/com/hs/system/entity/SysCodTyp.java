package com.hs.system.entity;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 编码类型 对象
 * @author autocreate
 * @create 2015-09-28
 */
public class SysCodTyp implements Serializable{
	private static final long serialVersionUID = 1L;
	
  	private String id ; 
  	@NotBlank(message="编码代码不能为空")
  	@Size(max=20,message="编码代码超长")
  	private String codTyp ; 

  	@NotBlank(message="编码名称不能为空")
  	@Size(max=80,message="编码名称超长")
  	private String codTypName ; 

  	@NotBlank(message="状态不能为空")
  	@Size(max=10,message="状态超长")
  	private String stat ; 

  	//@NotNull(message="创建日期不能为空")
  	@Future(message="创建日期必须晚于当前时间")
  	private Date instDate ; 

  	@Future(message="修改日期必须晚于当前时间")
  	private Date updtDate ; 


    //构造函数
    public SysCodTyp(){}

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
	public String getCodTyp() {
		return codTyp;
	}

	/**
	 * 设置 
	 * @param codTyp
	 */
	public void setCodTyp(String codTyp) {
		this.codTyp = codTyp;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getCodTypName() {
		return codTypName;
	}

	/**
	 * 设置 
	 * @param codTypName
	 */
	public void setCodTypName(String codTypName) {
		this.codTypName = codTypName;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * 设置 
	 * @param stat
	 */
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

}