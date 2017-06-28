package com.hs.loan.prod.dto;


import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 * PUB_产品与费用项关系 对象
 * @author autocreate
 * @create 2015-10-15
 */
public class PubProdFeeDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ;
    
    /*** 费用编号 */
  	@NotBlank(message="费用编号不能为空")
  	@Size(max=40,message="费用编号超长")
  	private String feeNo ; 
    
    /*** 产品编号 */
  	@NotBlank(message="产品编号不能为空")
  	@Size(max=40,message="产品编号超长")
  	private String prodNo ; 
    
    /*** 费用名称 */
  	@NotBlank(message="费用名称不能为空")
  	@Size(max=80,message="费用名称超长")
  	private String feeName ; 
    
    /*** 费用项值 */
  	@NotBlank(message="费用项值不能为空")
  	@Size(max=12,message="费用项值超长")
  	private String feeVal ; 
    
    /*** 是否客户选择 */
  	@NotBlank(message="是否客户选择不能为空")
  	@Size(max=10,message="是否客户选择超长")
  	private String isSel ; 
    
    /*** 结算优先级 */
  	@NotNull(message="结算优先级不能为空")
  	@Range(max=99999999,message="结算优先级超长")
  	private Integer setlPrior ; 
  	/*** 分期期数 */
  	@NotNull(message="分期期数不能为空")
  	@Range(max=99999,message="分期期数超长")
  	private Integer instNum; 
  	
  	public String feeMode;

	private String feeRat;
	private String rat;
    /*** 首付比例类型 */
  	private String fstPayTyp ; 
    
    /*** 首付比例 */
  	private BigDecimal fstPayVal ; 
	public String getFeeRat() {
		return feeRat;
	}

	public void setFeeRat(String feeRat) {
		this.feeRat = feeRat;
	}

	public String getRat() {
		return rat;
	}

	public void setRat(String rat) {
		this.rat = rat;
	}

	//构造函数
    public PubProdFeeDto(){}

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
	 * 获取 费用编号
	 * @return String
	 */
	public String getFeeNo() {
		return feeNo;
	}

	/**
	 * 设置 费用编号
	 * @param feeNo
	 */
	public void setFeeNo(String feeNo) {
		this.feeNo = feeNo;
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
	 * 获取 费用名称
	 * @return String
	 */
	public String getFeeName() {
		return feeName;
	}

	/**
	 * 设置 费用名称
	 * @param feeName
	 */
	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

    
    /**
	 * 获取 费用项值
	 * @return String
	 */
	public String getFeeVal() {
		return feeVal;
	}

	/**
	 * 设置 费用项值
	 * @param feeVal
	 */
	public void setFeeVal(String feeVal) {
		this.feeVal = feeVal;
	}

    
    /**
	 * 获取 是否客户选择
	 * @return String
	 */
	public String getIsSel() {
		return isSel;
	}

	/**
	 * 设置 是否客户选择
	 * @param isSel
	 */
	public void setIsSel(String isSel) {
		this.isSel = isSel;
	}

    
    /**
	 * 获取 结算优先级
	 * @return Integer
	 */
	public Integer getSetlPrior() {
		return setlPrior;
	}

	/**
	 * 设置 结算优先级
	 * @param setlPrior
	 */
	public void setSetlPrior(Integer setlPrior) {
		this.setlPrior = setlPrior;
	}

	public String getFeeMode() {
		return feeMode;
	}

	public void setFeeMode(String feeMode) {
		this.feeMode = feeMode;
	}

	public Integer getInstNum() {
		return instNum;
	}

	public void setInstNum(Integer instNum) {
		this.instNum = instNum;
	}

	public String getFstPayTyp() {
		return fstPayTyp;
	}

	public void setFstPayTyp(String fstPayTyp) {
		this.fstPayTyp = fstPayTyp;
	}

	public BigDecimal getFstPayVal() {
		return fstPayVal;
	}

	public void setFstPayVal(BigDecimal fstPayVal) {
		this.fstPayVal = fstPayVal;
	}

}