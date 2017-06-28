package com.hs.loan.sale.entity;


import java.io.Serializable;

/**
 * APP_分期与商品关系 对象
 * @author autocreate
 * @create 2015-10-27
 */
public class AppLoanGoods implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 商品类型 */
  	private String goodsType ; 
    
    /*** 商品品牌 */
  	private String brand ; 
    
    /*** 商品型号 */
  	private String marques ; 
    
    /*** 价格 */
  	private java.math.BigDecimal pric ;

	/*** imei */
	private String imei;

    //构造函数
    public AppLoanGoods(){}

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
	 * 获取 分期编号
	 * @return String
	 */
	public String getLoanNo() {
		return loanNo;
	}

	/**
	 * 设置 分期编号
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

    
    /**
	 * 获取 商品类型
	 * @return String
	 */
	public String getGoodsType() {
		return goodsType;
	}

	/**
	 * 设置 商品类型
	 * @param goodsType
	 */
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

    
    /**
	 * 获取 商品品牌
	 * @return String
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * 设置 商品品牌
	 * @param brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

    
    /**
	 * 获取 商品型号
	 * @return String
	 */
	public String getMarques() {
		return marques;
	}

	/**
	 * 设置 商品型号
	 * @param marques
	 */
	public void setMarques(String marques) {
		this.marques = marques;
	}

    
    /**
	 * 获取 价格
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getPric() {
		return pric;
	}

	/**
	 * 设置 价格
	 * @param pric
	 */
	public void setPric(java.math.BigDecimal pric) {
		this.pric = pric;
	}

	/**
	 * 获取 imei
	 * @return String
	 */
	public String getImei() {
		return imei;
	}
	/**
	 * 设置 imei
	 * @param imei
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}
}