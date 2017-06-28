package com.hs.loan.prod.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * PUB_产品与商品类型的关系 对象
 * @author autocreate
 * @create 2015-10-20
 */
public class PubProdGoods implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 商品编码 */
  	private String goodsId ; 
    
    /*** 产品编号 */
  	private String prodNo ; 
    
    /***  */
  	private String goodsName ; 

    //构造函数
    public PubProdGoods(){}

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
	 * 获取 商品编码
	 * @return String
	 */
	public String getGoodsId() {
		return goodsId;
	}

	/**
	 * 设置 商品编码
	 * @param goodsId
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
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
	 * 获取 
	 * @return String
	 */
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * 设置 
	 * @param goodsName
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

}