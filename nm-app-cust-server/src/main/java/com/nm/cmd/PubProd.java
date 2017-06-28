package com.nm.cmd;


import java.io.Serializable;
import java.util.Date;

/**
 * PUB_产品信息 对象
 * @author autocreate
 * @create 2015-10-20
 */
public class PubProd implements Serializable {
	private static final long serialVersionUID = 1L;
	
    
    /*** 产品编号 */
  	private String prodNo ;
    
    /*** 产品名称 */
  	private String prodName ;
    
    /*** 产品类型 */
  	private String prodTyp ;
    
    /*** 产品利率 */
  	private java.math.BigDecimal rat ; 
    
    /***  */
  	private String isPrepost ;
    
    /*** 前置业务利率 */
  	private java.math.BigDecimal prepostRat ; 
    
    /*** 首付比例类型 */
  	private String fstPayTyp ;
    
    /*** 首付比例 */
  	private java.math.BigDecimal fstPayVal ; 
    
    /*** 分期金额最低值 */
  	private java.math.BigDecimal minAmt ; 
    
    /*** 分期金额最高值 */
  	private java.math.BigDecimal maxAmt ; 
    
    /*** 还款方式 */
  	private String repayKind ;
  	/***还款方式no*/
  	private String repayNo;
    
    /*** 产品提成 */
  	private java.math.BigDecimal commAmt ; 
    
    /*** 产品说明 */
  	private String prodRemark ;
    
    /*** 产品销售渠道 */
  	private String saleChan ;
    
    /*** 有效时间段 */
  	private Date startDate ;
    
    /***  */
  	private Date endDate ;
    
    /*** 状态 */
  	private String prodStat ;
    
    /*** 创建时间 */
  	private Date instDate ;
    
    /*** 更新时间 */
  	private Date updtDate ;
  	 
  	/*客户类型*/
  	private String custType;
	/*//手续费率
	private String feeRat;

    private String feeNum;

	public String getFeeNum() {
		return feeNum;
	}

	public void setFeeNum(String feeNum) {
		this.feeNum = feeNum;
	}

	public String getFeeRat() {
		return feeRat;
	}

	public void setFeeRat(String feeRat) {
		this.feeRat = feeRat;
	}*/

	//构造函数
    public PubProd(){}

    //getter和setter方法
    
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
	 * 获取 产品名称
	 * @return String
	 */
	public String getProdName() {
		return prodName;
	}

	/**
	 * 设置 产品名称
	 * @param prodName
	 */
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

    
    /**
	 * 获取 产品类型
	 * @return String
	 */
	public String getProdTyp() {
		return prodTyp;
	}

	/**
	 * 设置 产品类型
	 * @param prodTyp
	 */
	public void setProdTyp(String prodTyp) {
		this.prodTyp = prodTyp;
	}

    
    /**
	 * 获取 产品利率
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getRat() {
		return rat;
	}

	/**
	 * 设置 产品利率
	 * @param rat
	 */
	public void setRat(java.math.BigDecimal rat) {
		this.rat = rat;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getIsPrepost() {
		return isPrepost;
	}

	/**
	 * 设置 
	 * @param isPrepost
	 */
	public void setIsPrepost(String isPrepost) {
		this.isPrepost = isPrepost;
	}

    
    /**
	 * 获取 前置业务利率
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getPrepostRat() {
		return prepostRat;
	}

	/**
	 * 设置 前置业务利率
	 * @param prepostRat
	 */
	public void setPrepostRat(java.math.BigDecimal prepostRat) {
		this.prepostRat = prepostRat;
	}

    
    /**
	 * 获取 首付比例类型
	 * @return String
	 */
	public String getFstPayTyp() {
		return fstPayTyp;
	}

	/**
	 * 设置 首付比例类型
	 * @param fstPayTyp
	 */
	public void setFstPayTyp(String fstPayTyp) {
		this.fstPayTyp = fstPayTyp;
	}

    
    /**
	 * 获取 首付比例
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getFstPayVal() {
		return fstPayVal;
	}

	/**
	 * 设置 首付比例
	 * @param fstPayVal
	 */
	public void setFstPayVal(java.math.BigDecimal fstPayVal) {
		this.fstPayVal = fstPayVal;
	}

    
    /**
	 * 获取 分期金额最低值
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getMinAmt() {
		return minAmt;
	}

	/**
	 * 设置 分期金额最低值
	 * @param minAmt
	 */
	public void setMinAmt(java.math.BigDecimal minAmt) {
		this.minAmt = minAmt;
	}

    
    /**
	 * 获取 分期金额最高值
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getMaxAmt() {
		return maxAmt;
	}

	/**
	 * 设置 分期金额最高值
	 * @param maxAmt
	 */
	public void setMaxAmt(java.math.BigDecimal maxAmt) {
		this.maxAmt = maxAmt;
	}

    
    /**
	 * 获取 还款方式
	 * @return String
	 */
	public String getRepayKind() {
		return repayKind;
	}

	/**
	 * 设置 还款方式
	 * @param repayKind
	 */
	public void setRepayKind(String repayKind) {
		this.repayKind = repayKind;
	}

    
    /**
	 * 获取 产品提成
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getCommAmt() {
		return commAmt;
	}

	/**
	 * 设置 产品提成
	 * @param commAmt
	 */
	public void setCommAmt(java.math.BigDecimal commAmt) {
		this.commAmt = commAmt;
	}

    
    /**
	 * 获取 产品说明
	 * @return String
	 */
	public String getProdRemark() {
		return prodRemark;
	}

	/**
	 * 设置 产品说明
	 * @param prodRemark
	 */
	public void setProdRemark(String prodRemark) {
		this.prodRemark = prodRemark;
	}

    
    /**
	 * 获取 产品销售渠道
	 * @return String
	 */
	public String getSaleChan() {
		return saleChan;
	}

	/**
	 * 设置 产品销售渠道
	 * @param saleChan
	 */
	public void setSaleChan(String saleChan) {
		this.saleChan = saleChan;
	}

    
    /**
	 * 获取 有效时间段
	 * @return Date
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * 设置 有效时间段
	 * @param startDate
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

    
    /**
	 * 获取 
	 * @return Date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置 
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

    
    /**
	 * 获取 状态
	 * @return String
	 */
	public String getProdStat() {
		return prodStat;
	}

	/**
	 * 设置 状态
	 * @param prodStat
	 */
	public void setProdStat(String prodStat) {
		this.prodStat = prodStat;
	}

    
    /**
	 * 获取 创建时间
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 创建时间
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

    
    /**
	 * 获取 更新时间
	 * @return Date
	 */
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 更新时间
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	public String getRepayNo() {
		return repayNo;
	}

	public void setRepayNo(String repayNo) {
		this.repayNo = repayNo;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

}