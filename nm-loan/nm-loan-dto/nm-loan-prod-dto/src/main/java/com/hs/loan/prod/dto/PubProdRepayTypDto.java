package com.hs.loan.prod.dto;


import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * PUB_产品与还款类型的关系 对象
 * @author autocreate
 * @create 2015-10-16
 */
public class PubProdRepayTypDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 还款类型编号 */
  	@NotBlank(message="还款类型编号不能为空")
  	@Size(max=40,message="还款类型编号超长")
  	private String confNo ; 
  	/*** 配置名称 */
  	@NotBlank(message="配置名称不能为空")
  	@Size(max=80,message="配置名称超长")
  	private String confName ; 
    
    /*** 产品编号 */
  	@NotBlank(message="产品编号不能为空")
  	@Size(max=40,message="产品编号超长")
  	private String prodNo ; 

    //构造函数
    public PubProdRepayTypDto(){}

    //getter和setter方法
    
    /**
	 * 获取 ID
	 * @return String
	 */
	public String getId() {
		return id;
	}

	public String getConfName() {
		return confName;
	}

	public void setConfName(String confName) {
		this.confName = confName;
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