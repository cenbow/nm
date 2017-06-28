package com.hs.loan.approv.dto;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

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
  	@NotBlank(message="案件ID不能为空")
  	@Size(max=40,message="案件ID超长")
  	private String apprId ; 
    
    /*** 分期编号 */
  	@NotBlank(message="分期编号不能为空")
  	@Size(max=40,message="分期编号超长")
  	private String loanNo ; 
    
    /*** 块ID */
  	@NotBlank(message="块ID不能为空")
  	@Size(max=40,message="块ID超长")
  	private String blockId ; 
    
    /*** 审批批注 */
  	//@NotBlank(message="审批批注不能为空")
  	@Size(max=400,message="审批批注超长")
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