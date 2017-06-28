package com.hs.loan.sale.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_分期与还款类型的关系 对象
 * @author autocreate
 * @create 2015-10-30
 */
public class AppLoanRepayTyp implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 还款类型编号 */
  	private String confNo ; 
    
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 配置名称 */
  	private String confName ; 

    //构造函数
    public AppLoanRepayTyp(){}

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
	public String getLoanNo() {
		return loanNo;
	}

	/**
	 * 设置 产品编号
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

    
    /**
	 * 获取 配置名称
	 * @return String
	 */
	public String getConfName() {
		return confName;
	}

	/**
	 * 设置 配置名称
	 * @param confName
	 */
	public void setConfName(String confName) {
		this.confName = confName;
	}

}