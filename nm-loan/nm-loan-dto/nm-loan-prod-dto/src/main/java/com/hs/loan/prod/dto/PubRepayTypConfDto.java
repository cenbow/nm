package com.hs.loan.prod.dto;


import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * PUB_还款类型配置信息 对象
 * @author autocreate
 * @create 2015-10-15
 */
public class PubRepayTypConfDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 配置编号 */
  	private String confNo ; 
    
    /*** 配置名称 */
  	@NotBlank(message="配置名称不能为空")
  	@Size(max=80,message="配置名称超长")
  	private String confName ; 
    
    /*** 还款条件 */
  	@NotBlank(message="还款条件不能为空")
  	@Size(max=100,message="还款条件超长")
  	private String repayCond ; 
    
    /*** 还款金额计算 */
  	@NotBlank(message="还款金额计算不能为空")
  	@Size(max=100,message="还款金额计算超长")
  	private String repayCalc ; 
    
    /***配置说明  */
  	@Size(max=300,message="配置说明超长")
  	private String confRemark ; 
    
    /*** 创建日期 */
  	private Date instDate ; 
    
    /*** 更新日期 */
  	private Date updtDate ; 

    //构造函数
    public PubRepayTypConfDto(){}

    //getter和setter方法
    
    /**
	 * 获取 配置编号
	 * @return String
	 */
	public String getConfNo() {
		return confNo;
	}

	/**
	 * 设置 配置编号
	 * @param confNo
	 */
	public void setConfNo(String confNo) {
		this.confNo = confNo;
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

    
    /**
	 * 获取 还款条件
	 * @return String
	 */
	public String getRepayCond() {
		return repayCond;
	}

	/**
	 * 设置 还款条件
	 * @param repayCond
	 */
	public void setRepayCond(String repayCond) {
		this.repayCond = repayCond;
	}

    
    /**
	 * 获取 还款金额计算
	 * @return String
	 */
	public String getRepayCalc() {
		return repayCalc;
	}

	/**
	 * 设置 还款金额计算
	 * @param repayCalc
	 */
	public void setRepayCalc(String repayCalc) {
		this.repayCalc = repayCalc;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getConfRemark() {
		return confRemark;
	}

	/**
	 * 设置 
	 * @param confRemark
	 */
	public void setConfRemark(String confRemark) {
		this.confRemark = confRemark;
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