package com.hs.loan.collection.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * PL_逾期案件经办信息 对象
 * @author autocreate
 * @create 2016-04-13
 */
public class PlLoanOvduHand implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 贷款编号 */
  	private String loanNo ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /*** 客户姓名 */
  	private String custName ; 
    
    /*** 类型 */
  	private String typ ; 
    
    /*** 处理日期 */
  	private Date handDate ; 
    
    /*** 处理人 */
  	private String handPsnNo ; 
    
    /*** 结果是否有效 */
  	private String restStat ; 
    
    /*** 联络结果代码 */
  	private String restCode ; 
    
    /*** 处理人名字 */
  	private String handPsnName ; 
    
    /*** 联系人关系 */
  	private String contRel ; 
    
    /*** 联系人 */
  	private String contName ; 
    
    /*** 联系电话 */
  	private String contTel ; 
    
    /*** 处理结果代码 */
  	private String handRestCode ; 
    
    /*** 逾期原因 */
  	private String handOvduResn ; 
    
    /*** 备注 */
  	private String remark ; 
    
    /*** 创建时间 */
  	private Date instDate ; 
    
    /*** 更新时间 */
  	private Date updtDate ; 

    //构造函数
    public PlLoanOvduHand(){}

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
	 * 获取 贷款编号
	 * @return String
	 */
	public String getLoanNo() {
		return loanNo;
	}

	/**
	 * 设置 贷款编号
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

    
    /**
	 * 获取 客户编号
	 * @return String
	 */
	public String getCustNo() {
		return custNo;
	}

	/**
	 * 设置 客户编号
	 * @param custNo
	 */
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

    
    /**
	 * 获取 客户姓名
	 * @return String
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * 设置 客户姓名
	 * @param custName
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

    
    /**
	 * 获取 类型
	 * @return String
	 */
	public String getTyp() {
		return typ;
	}

	/**
	 * 设置 类型
	 * @param typ
	 */
	public void setTyp(String typ) {
		this.typ = typ;
	}

    
    /**
	 * 获取 处理日期
	 * @return Date
	 */
	public Date getHandDate() {
		return handDate;
	}

	/**
	 * 设置 处理日期
	 * @param handDate
	 */
	public void setHandDate(Date handDate) {
		this.handDate = handDate;
	}

    
    /**
	 * 获取 处理人
	 * @return String
	 */
	public String getHandPsnNo() {
		return handPsnNo;
	}

	/**
	 * 设置 处理人
	 * @param handPsnNo
	 */
	public void setHandPsnNo(String handPsnNo) {
		this.handPsnNo = handPsnNo;
	}

    
    /**
	 * 获取 结果是否有效
	 * @return String
	 */
	public String getRestStat() {
		return restStat;
	}

	/**
	 * 设置 结果是否有效
	 * @param restStat
	 */
	public void setRestStat(String restStat) {
		this.restStat = restStat;
	}

    
    /**
	 * 获取 联络结果代码
	 * @return String
	 */
	public String getRestCode() {
		return restCode;
	}

	/**
	 * 设置 联络结果代码
	 * @param restCode
	 */
	public void setRestCode(String restCode) {
		this.restCode = restCode;
	}

    
    /**
	 * 获取 处理人名字
	 * @return String
	 */
	public String getHandPsnName() {
		return handPsnName;
	}

	/**
	 * 设置 处理人名字
	 * @param handPsnName
	 */
	public void setHandPsnName(String handPsnName) {
		this.handPsnName = handPsnName;
	}

    
    /**
	 * 获取 联系人关系
	 * @return String
	 */
	public String getContRel() {
		return contRel;
	}

	/**
	 * 设置 联系人关系
	 * @param contRel
	 */
	public void setContRel(String contRel) {
		this.contRel = contRel;
	}

    
    /**
	 * 获取 联系人
	 * @return String
	 */
	public String getContName() {
		return contName;
	}

	/**
	 * 设置 联系人
	 * @param contName
	 */
	public void setContName(String contName) {
		this.contName = contName;
	}

    
    /**
	 * 获取 联系电话
	 * @return String
	 */
	public String getContTel() {
		return contTel;
	}

	/**
	 * 设置 联系电话
	 * @param contTel
	 */
	public void setContTel(String contTel) {
		this.contTel = contTel;
	}

    
    /**
	 * 获取 处理结果代码
	 * @return String
	 */
	public String getHandRestCode() {
		return handRestCode;
	}

	/**
	 * 设置 处理结果代码
	 * @param handRestCode
	 */
	public void setHandRestCode(String handRestCode) {
		this.handRestCode = handRestCode;
	}

    
    /**
	 * 获取 逾期原因
	 * @return String
	 */
	public String getHandOvduResn() {
		return handOvduResn;
	}

	/**
	 * 设置 逾期原因
	 * @param handOvduResn
	 */
	public void setHandOvduResn(String handOvduResn) {
		this.handOvduResn = handOvduResn;
	}

    
    /**
	 * 获取 备注
	 * @return String
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置 备注
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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

}