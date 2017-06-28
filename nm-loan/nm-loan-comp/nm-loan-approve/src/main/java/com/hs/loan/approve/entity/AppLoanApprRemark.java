package com.hs.loan.approve.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * APP_分期审批案件批注表 对象
 * @author autocreate
 * @create 2015-11-24
 */
public class AppLoanApprRemark implements Serializable{
	private static final long serialVersionUID = 1L;


    /*** ID */
  	private String id ;

    /*** 案件ID */
  	private String apprId ;

    /*** 分期编号 */
  	private String loanNo ;

    /*** 块ID */
  	private String blockId ;

    /*** 批注人NO */
  	private String operateNo ;

    /*** 批注人名字 */
  	private String operateName ;

    /*** 审批备注 */
  	private String apprRemark ;

    /*** 插入时间 */
  	private Date instDate ;

    //构造函数
    public AppLoanApprRemark(){}

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
	 * 获取 批注人NO
	 * @return String
	 */
	public String getOperateNo() {
		return operateNo;
	}

	/**
	 * 设置 批注人NO
	 * @param operateNo
	 */
	public void setOperateNo(String operateNo) {
		this.operateNo = operateNo;
	}

    
    /**
	 * 获取 批注人名字
	 * @return String
	 */
	public String getOperateName() {
		return operateName;
	}

	/**
	 * 设置 批注人名字
	 * @param operateName
	 */
	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}


	public String getApprRemark() {
		return apprRemark;
	}

	public void setApprRemark(String apprRemark) {
		this.apprRemark = apprRemark;
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