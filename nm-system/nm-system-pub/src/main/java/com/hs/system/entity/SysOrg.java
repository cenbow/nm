package com.hs.system.entity;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 * 机构信息 对象
 * @author autocreate
 * @create 2015-09-28
 */
public class SysOrg implements Serializable{
	private static final long serialVersionUID = 1L;
	
  	private String id ; 

  	@NotBlank(message="机构号不能为空")
	@Size(max=40,message="机构号超长")
  	private String orgNo ; 

  	@NotBlank(message="机构名称不能为空")
	@Size(max=40,message="机构名称超长")
  	private String orgName ; 

	//@Size(max=11,message="机构级别")
  	@Range(min=1,max=99999,message="机构级别超长")
  	private Integer orgLev ; 

	@Size(max=40,message="上级机构超长")
  	private String parOrgNo ; 

  	@NotBlank(message="机构路径不能为空")
	@Size(max=1000,message="机构路径超长")
  	private String orgCodPath ; 

  	@NotBlank(message="机构状态不能为空")
	@Size(max=10,message="机构状态超长")
  	private String stat ; 

  	@NotBlank(message="所属省不能为空")
	@Size(max=10,message="所属省超长")
  	private String provNo ; 

  	@NotBlank(message="所属市不能为空")
	@Size(max=10,message="所属市超长")
  	private String cityNo ; 

  	@NotBlank(message="所属县不能为空")
	@Size(max=10,message="所属县超长")
  	private String cntyNo ; 

	@Size(max=10,message="所属区域超长")
  	private String areaNo ; 

  	@NotBlank(message="机构属性不能为空")
	@Size(max=40,message="机构属性超长")
  	private String orgAttr ; 

  	@Future(message="创建时间必须晚于当前时间")
  	private Date instDate ; 

  	@Future(message="修改时间必须晚于当前时间")
  	private Date updtDate ; 


    //构造函数
    public SysOrg(){}

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
	public String getOrgNo() {
		return orgNo;
	}

	/**
	 * 设置 
	 * @param orgNo
	 */
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * 设置 
	 * @param orgName
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

    
    /**
	 * 获取 
	 * @return Integer
	 */
	public Integer getOrgLev() {
		return orgLev;
	}

	/**
	 * 设置 
	 * @param orgLev
	 */
	public void setOrgLev(Integer orgLev) {
		this.orgLev = orgLev;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getParOrgNo() {
		return parOrgNo;
	}

	/**
	 * 设置 
	 * @param parOrgNo
	 */
	public void setParOrgNo(String parOrgNo) {
		this.parOrgNo = parOrgNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getOrgCodPath() {
		return orgCodPath;
	}

	/**
	 * 设置 
	 * @param orgCodPath
	 */
	public void setOrgCodPath(String orgCodPath) {
		this.orgCodPath = orgCodPath;
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
	 * @return String
	 */
	public String getProvNo() {
		return provNo;
	}

	/**
	 * 设置 
	 * @param provNo
	 */
	public void setProvNo(String provNo) {
		this.provNo = provNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getCityNo() {
		return cityNo;
	}

	/**
	 * 设置 
	 * @param cityNo
	 */
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getCntyNo() {
		return cntyNo;
	}

	/**
	 * 设置 
	 * @param cntyNo
	 */
	public void setCntyNo(String cntyNo) {
		this.cntyNo = cntyNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getAreaNo() {
		return areaNo;
	}

	/**
	 * 设置 
	 * @param areaNo
	 */
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getOrgAttr() {
		return orgAttr;
	}

	/**
	 * 设置 
	 * @param orgAttr
	 */
	public void setOrgAttr(String orgAttr) {
		this.orgAttr = orgAttr;
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