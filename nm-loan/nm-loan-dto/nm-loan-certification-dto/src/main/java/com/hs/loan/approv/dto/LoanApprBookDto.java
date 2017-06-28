package com.hs.loan.approv.dto;


import java.util.Date;

import java.io.Serializable;

/**
 * APP_分期审批案件批注表 对象
 * @author autocreate
 * @create 2015-11-24
 */
public class LoanApprBookDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 案件ID */
  	private String apprId ; 
    
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 块ID */
  	private String blockId ; 
    
    /*** 审批批注 */
  	private String apprBook ; 
    
    /*** 插入时间 */
  	private Date instDate ; 

    //构造函数
    public LoanApprBookDto(){}

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
	 * 获取 案件ID
	 * @return String
	 */
	public String getApprId() {
		return apprId;
	}

	/**
	 * 设置 案件ID
	 * @param apprId
	 */
	public void setApprId(String apprId) {
		this.apprId = apprId;
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
	 * 获取 块ID
	 * @return String
	 */
	public String getBlockId() {
		return blockId;
	}

	/**
	 * 设置 块ID
	 * @param blockId
	 */
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

    
    /**
	 * 获取 审批批注
	 * @return String
	 */
	public String getApprBook() {
		return apprBook;
	}

	/**
	 * 设置 审批批注
	 * @param apprBook
	 */
	public void setApprBook(String apprBook) {
		this.apprBook = apprBook;
	}

    
    /**
	 * 获取 插入时间
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 插入时间
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

}