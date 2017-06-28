package com.hs.loan.prod.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * PUB_产品与机构的关系 对象
 * @author autocreate
 * @create 2015-10-19
 */
public class PubProdOrg implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 机构号 */
  	private String orgNo ; 
    
    /*** 机构名称 */
  	private String orgName ; 
    
    /*** 所属省 */
  	private String provNo ; 
    
    /*** 所属市 */
  	private String cityNo ; 
    
    /*** 所属县 */
  	private String cntyNo ; 
    
    /*** 所属区域 */
  	private String areaNo ; 
    
    /*** 产品编号 */
  	private String prodNo ; 

    //构造函数
    public PubProdOrg(){}

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
	 * 获取 机构号
	 * @return String
	 */
	public String getOrgNo() {
		return orgNo;
	}

	/**
	 * 设置 机构号
	 * @param orgNo
	 */
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

    
    /**
	 * 获取 机构名称
	 * @return String
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * 设置 机构名称
	 * @param orgName
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

    
    /**
	 * 获取 所属省
	 * @return String
	 */
	public String getProvNo() {
		return provNo;
	}

	/**
	 * 设置 所属省
	 * @param provNo
	 */
	public void setProvNo(String provNo) {
		this.provNo = provNo;
	}

    
    /**
	 * 获取 所属市
	 * @return String
	 */
	public String getCityNo() {
		return cityNo;
	}

	/**
	 * 设置 所属市
	 * @param cityNo
	 */
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}

    
    /**
	 * 获取 所属县
	 * @return String
	 */
	public String getCntyNo() {
		return cntyNo;
	}

	/**
	 * 设置 所属县
	 * @param cntyNo
	 */
	public void setCntyNo(String cntyNo) {
		this.cntyNo = cntyNo;
	}

    
    /**
	 * 获取 所属区域
	 * @return String
	 */
	public String getAreaNo() {
		return areaNo;
	}

	/**
	 * 设置 所属区域
	 * @param areaNo
	 */
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

    
    /**
	 * 获取 产品编号
	 * @return String
	 */
	public String getProdNo() {
		return prodNo;
	}

	/**
	 * 设置 产品编号
	 * @param prodNo
	 */
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}

}