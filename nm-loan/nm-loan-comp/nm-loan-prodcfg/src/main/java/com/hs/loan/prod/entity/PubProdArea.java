package com.hs.loan.prod.entity;


import java.io.Serializable;

/**
 * PUB_产品与区域的关系 对象
 * @author autocreate
 * @create 2015-10-19
 */
public class PubProdArea implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 省 */
  	private String provNo ; 
    
    /*** 省名 */
  	private String provName ; 
    
    /*** 市 */
  	private String cityNo ; 
    
    /*** 市名 */
  	private String cityName ; 
    
    /*** 县 */
  	private String cntyNo ; 
    
    /*** 县名 */
  	private String cntyName ; 
    
    /*** 产品编号 */
  	private String prodNo ; 

    //构造函数
    public PubProdArea(){}

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
	 * 获取 省
	 * @return String
	 */
	public String getProvNo() {
		return provNo;
	}

	/**
	 * 设置 省
	 * @param provNo
	 */
	public void setProvNo(String provNo) {
		this.provNo = provNo;
	}

    
    /**
	 * 获取 省名
	 * @return String
	 */
	public String getProvName() {
		return provName;
	}

	/**
	 * 设置 省名
	 * @param provName
	 */
	public void setProvName(String provName) {
		this.provName = provName;
	}

    
    /**
	 * 获取 市
	 * @return String
	 */
	public String getCityNo() {
		return cityNo;
	}

	/**
	 * 设置 市
	 * @param cityNo
	 */
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}

    
    /**
	 * 获取 市名
	 * @return String
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * 设置 市名
	 * @param cityName
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

    
    /**
	 * 获取 县
	 * @return String
	 */
	public String getCntyNo() {
		return cntyNo;
	}

	/**
	 * 设置 县
	 * @param cntyNo
	 */
	public void setCntyNo(String cntyNo) {
		this.cntyNo = cntyNo;
	}

    
    /**
	 * 获取 县名
	 * @return String
	 */
	public String getCntyName() {
		return cntyName;
	}

	/**
	 * 设置 县名
	 * @param cntyName
	 */
	public void setCntyName(String cntyName) {
		this.cntyName = cntyName;
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