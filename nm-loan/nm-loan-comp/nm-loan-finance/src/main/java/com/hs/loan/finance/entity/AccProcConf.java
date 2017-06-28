package com.hs.loan.finance.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * ACC_交易配置 对象
 * @author autocreate
 * @create 2016-02-03
 */
public class AccProcConf implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 配置编号 */
  	private String txCod ; 
    
    /*** 交易编号 */
  	private String txTyp ; 
    
    /*** 交易名称 */
  	private String txTypName ; 
    
    /*** 对应账户 */
  	private String acctNo ; 
    
    /*** 对应账户名称 */
  	private String acctName ; 
    
    /*** 借贷方向 */
  	private String drCrFlag ; 
    
    /*** 存储过程名 */
  	private String procName ; 
    
    /*** 启用标志 */
  	private String useFlag ; 
    
    /*** 备注 */
  	private String remark ; 
    
    /*** 创建日期 */
  	private Date instDate ; 
    
    /*** 更新日期 */
  	private Date updtDate ; 

    //构造函数
    public AccProcConf(){}

    //getter和setter方法
    
    /**
	 * 获取 配置编号
	 * @return String
	 */
	public String getTxCod() {
		return txCod;
	}

	/**
	 * 设置 配置编号
	 * @param txCod
	 */
	public void setTxCod(String txCod) {
		this.txCod = txCod;
	}

    
    /**
	 * 获取 交易编号
	 * @return String
	 */
	public String getTxTyp() {
		return txTyp;
	}

	/**
	 * 设置 交易编号
	 * @param txTyp
	 */
	public void setTxTyp(String txTyp) {
		this.txTyp = txTyp;
	}

    
    /**
	 * 获取 交易名称
	 * @return String
	 */
	public String getTxTypName() {
		return txTypName;
	}

	/**
	 * 设置 交易名称
	 * @param txTypName
	 */
	public void setTxTypName(String txTypName) {
		this.txTypName = txTypName;
	}

    
    /**
	 * 获取 对应账户
	 * @return String
	 */
	public String getAcctNo() {
		return acctNo;
	}

	/**
	 * 设置 对应账户
	 * @param acctNo
	 */
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

    
    /**
	 * 获取 对应账户名称
	 * @return String
	 */
	public String getAcctName() {
		return acctName;
	}

	/**
	 * 设置 对应账户名称
	 * @param acctName
	 */
	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

    
    /**
	 * 获取 借贷方向
	 * @return String
	 */
	public String getDrCrFlag() {
		return drCrFlag;
	}

	/**
	 * 设置 借贷方向
	 * @param drCrFlag
	 */
	public void setDrCrFlag(String drCrFlag) {
		this.drCrFlag = drCrFlag;
	}

    
    /**
	 * 获取 存储过程名
	 * @return String
	 */
	public String getProcName() {
		return procName;
	}

	/**
	 * 设置 存储过程名
	 * @param procName
	 */
	public void setProcName(String procName) {
		this.procName = procName;
	}

    
    /**
	 * 获取 启用标志
	 * @return String
	 */
	public String getUseFlag() {
		return useFlag;
	}

	/**
	 * 设置 启用标志
	 * @param useFlag
	 */
	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
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
	 * 获取 创建日期
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 创建日期
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

    
    /**
	 * 获取 更新日期
	 * @return Date
	 */
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 更新日期
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

}