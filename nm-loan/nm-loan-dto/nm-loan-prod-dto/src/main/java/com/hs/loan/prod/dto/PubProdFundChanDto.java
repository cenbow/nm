package com.hs.loan.prod.dto;


import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * PUB_产品与资金渠道的关系 对象
 * @author autocreate
 * @create 2015-10-16
 */
public class PubProdFundChanDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 渠道编号 */
  	@NotBlank(message="渠道编号不能为空")
  	@Size(max=40,message="渠道编号超长")
  	private String chanNo ; 
    
    /*** 渠道名称 */
  	@NotBlank(message="渠道名称不能为空")
  	@Size(max=40,message="渠道名称超长")
  	private String chanName ; 
    
    /*** 产品编号 */
  	@NotBlank(message="产品编号不能为空")
  	@Size(max=40,message="产品编号超长")
  	private String prodNo ; 
  	/*** 优先级 */
  	@NotBlank(message="优先级不能为空")
  	@Size(max=40,message="优先级超长")
  	private String setlPrior ; 

    //构造函数
    public PubProdFundChanDto(){}

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
	 * 获取 渠道编号
	 * @return String
	 */
	public String getChanNo() {
		return chanNo;
	}

	/**
	 * 设置 渠道编号
	 * @param chanNo
	 */
	public void setChanNo(String chanNo) {
		this.chanNo = chanNo;
	}

    
    /**
	 * 获取 渠道名称
	 * @return String
	 */
	public String getChanName() {
		return chanName;
	}

	/**
	 * 设置 渠道名称
	 * @param chanName
	 */
	public void setChanName(String chanName) {
		this.chanName = chanName;
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

	public String getSetlPrior() {
		return setlPrior;
	}

	public void setSetlPrior(String setlPrior) {
		this.setlPrior = setlPrior;
	}

}