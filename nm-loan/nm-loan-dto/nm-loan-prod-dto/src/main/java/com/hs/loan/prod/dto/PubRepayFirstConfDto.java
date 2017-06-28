package com.hs.loan.prod.dto;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * PUB_首次还款日规则 对象
 * @author autocreate
 * @create 2015-10-29
 */
public class PubRepayFirstConfDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 还款日规则编号 */
  	private String firstNo ; 
    
    /*** 还款日规则名字 */
  	@NotBlank(message="还款日规则名字不能为空")
  	@Size(max=80,message="还款日规则名字超长")
  	private String firstName ; 
    
    /*** 还款日规则 */
  	@NotBlank(message="还款日规则不能为空")
  	@Size(max=100,message="还款日规则超长")
  	private String firstCalc ; 
    
    /*** 配置说明 */
  	@Size(max=300,message="配置说明超长")
  	private String firstRemark ; 
    
    /*** 创建日期 */
  	private Date instDate ; 
    
    /*** 更新日期 */
  	private Date updtDate ; 

    //构造函数
    public PubRepayFirstConfDto(){}

    //getter和setter方法
    
    /**
	 * 获取 还款日规则编号
	 * @return String
	 */
	public String getFirstNo() {
		return firstNo;
	}

	/**
	 * 设置 还款日规则编号
	 * @param firstNo
	 */
	public void setFirstNo(String firstNo) {
		this.firstNo = firstNo;
	}

    
    /**
	 * 获取 还款日规则名字
	 * @return String
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 设置 还款日规则名字
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

    
    /**
	 * 获取 还款日规则
	 * @return String
	 */
	public String getFirstCalc() {
		return firstCalc;
	}

	/**
	 * 设置 还款日规则
	 * @param firstCalc
	 */
	public void setFirstCalc(String firstCalc) {
		this.firstCalc = firstCalc;
	}

    
    /**
	 * 获取 配置说明
	 * @return String
	 */
	public String getFirstRemark() {
		return firstRemark;
	}

	/**
	 * 设置 配置说明
	 * @param firstRemark
	 */
	public void setFirstRemark(String firstRemark) {
		this.firstRemark = firstRemark;
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