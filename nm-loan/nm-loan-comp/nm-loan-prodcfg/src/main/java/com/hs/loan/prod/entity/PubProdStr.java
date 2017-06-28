package com.hs.loan.prod.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * PUB_产品与网点的关系 对象
 * @author autocreate
 * @create 2015-10-15
 */
public class PubProdStr implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 产品编号 */
  	private String prodNo ; 
    
    /*** 网点编码 */
  	private String branchNo ; 

    //构造函数
    public PubProdStr(){}

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

}