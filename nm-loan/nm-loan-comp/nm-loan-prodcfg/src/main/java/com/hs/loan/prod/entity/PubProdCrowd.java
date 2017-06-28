package com.hs.loan.prod.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * PUB_产品与销售组关系 对象
 * @author autocreate
 * @create 2015-10-21
 */
public class PubProdCrowd implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 产品编号 */
  	private String prodNo ; 
    
    /*** 销售组编号 */
  	private String crowdNo ; 
    
    /*** 销售组名称 */
  	private String crowdName ; 

    //构造函数
    public PubProdCrowd(){}

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

	public String getCrowdNo() {
		return crowdNo;
	}

	public void setCrowdNo(String crowdNo) {
		this.crowdNo = crowdNo;
	}

	public String getCrowdName() {
		return crowdName;
	}

	public void setCrowdName(String crowdName) {
		this.crowdName = crowdName;
	}

    
}