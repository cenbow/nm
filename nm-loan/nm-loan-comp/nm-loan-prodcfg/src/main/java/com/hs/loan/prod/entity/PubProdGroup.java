package com.hs.loan.prod.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * PUB_产品与销售群关系 对象
 * @author autocreate
 * @create 2015-10-21
 */
public class PubProdGroup implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 产品编号 */
  	private String prodNo ; 
    
    /*** 销售群编号 */
  	private String groupNo ; 
    
    /*** 销售群名称 */
  	private String groupName ; 

    //构造函数
    public PubProdGroup(){}

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

	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}