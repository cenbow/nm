package com.hs.loan.prod.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * PUB_产品与还款类型的关系 对象
 * @author autocreate
 * @create 2015-10-16
 */
public class PubProdRepayTyp implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 还款类型编号 */
  	private String confNo ; 
    
    /*** 产品编号 */
  	private String prodNo ; 

    //构造函数
    public PubProdRepayTyp(){}

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
	 * 获取 还款类型编号
	 * @return String
	 */
	public String getConfNo() {
		return confNo;
	}

	/**
	 * 设置 还款类型编号
	 * @param confNo
	 */
	public void setConfNo(String confNo) {
		this.confNo = confNo;
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