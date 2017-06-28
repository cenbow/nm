package com.hs.system.entity;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 系统参数 对象
 * @author autocreate
 * @create 2015-09-28
 */
public class SysParam implements Serializable{
	private static final long serialVersionUID = 1L;
	
  	private String id ; 

  	@NotBlank(message="参数编号不能为空")
	@Size(max=40,message="参数编号超长")
  	private String cod ; 

  	@NotBlank(message="参数名不能为空")
	@Size(max=40,message="参数名超长")
  	private String name ; 

  	@NotBlank(message="参数值不能为空")
	@Size(max=32,message="参数值超长")
  	private String val ; 

  	@NotBlank(message="参数状态不能为空")
	@Size(max=8,message="参数状态超长")
  	private String stat ; 

  	@Future(message="创建时间必须晚于当前时间")
  	private Date instDate ; 

  	@Future(message="修改时间必须晚于当前时间")
  	private Date updtDate ; 


    //构造函数
    public SysParam(){}

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
	public String getCod() {
		return cod;
	}

	/**
	 * 设置 
	 * @param cod
	 */
	public void setCod(String cod) {
		this.cod = cod;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getVal() {
		return val;
	}

	/**
	 * 设置 
	 * @param val
	 */
	public void setVal(String val) {
		this.val = val;
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