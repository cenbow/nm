package com.hs.loan.approve.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_审批组与商品类型关系 对象
 * @author autocreate
 * @create 2017-01-13
 */
public class AppApprGroupGoods implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /***  */
  	private String id ; 
    
    /***  */
  	private String groupNo ; 
    
    /***  */
  	private String goodsType ; 
    
    /***  */
  	private Date instDate ; 

    //构造函数
    public AppApprGroupGoods(){}

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
	public String getGroupNo() {
		return groupNo;
	}

	/**
	 * 设置 
	 * @param groupNo
	 */
	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getGoodsType() {
		return goodsType;
	}

	/**
	 * 设置 
	 * @param goodsType
	 */
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
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

}