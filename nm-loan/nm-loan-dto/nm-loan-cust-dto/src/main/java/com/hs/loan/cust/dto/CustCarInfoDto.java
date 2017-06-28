package com.hs.loan.cust.dto;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

/**
 * APP_客户车辆信息 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class CustCarInfoDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/*** ID */
//	@NotBlank (message="表主键不能为空")
//	@Size(max=40,message="表主键超长")
  	private String id ; 
    
    /*** 客户编号 */
  	@NotBlank (message="客户编号不能为空")
	@Size(max=32,message="客户编号超长")
  	private String custNo ; 
    
    /*** 车辆状态 */
  	@NotBlank (message="车辆状态不能为空")
	@Size(max=8,message="车辆状态超长")
  	private String carType ; 
    
    /*** 车牌 */
  	@NotBlank (message="车牌不能为空")
	@Size(max=32,message="车牌超长")
  	private String carPlate ; 
    
    /*** 车辆品牌 */
  	@NotBlank (message="车辆品牌不能为空")
	@Size(max=8,message="车辆品牌超长")
  	private String carBrand ; 
    
    /*** 车辆型号 */
  	@NotBlank (message="车辆型号不能为空")
	@Size(max=8,message="车辆型号超长")
  	private String carModel ; 
    
    /*** 车辆购买年限 */
  	@NotBlank (message="车辆购买年限不能为空")
	@Size(max=8,message="车辆购买年限超长")
  	private String carBuyYear ; 
    
    /*** 车辆每月还款金额 */
  	@NotBlank (message="车辆每月还款金额不能为空")
	@Size(max=8,message="车辆每月还款金额超长")
  	private String carMthAmt ; 
    
    /*** 车辆每月还款日 */
  	@NotBlank (message="车辆每月还款日不能为空")
	@Size(max=8,message="车辆每月还款日超长")
  	private String carMthDay ; 
    
    /*** 车辆剩余期限(月) */
  	@NotBlank (message="车辆剩余期限(月)不能为空")
	@Size(max=8,message="车辆剩余期限(月)超长")
  	private String carMonth ; 
    
    /*** 备注 */
  	@Size(max=128,message="备注超长")
  	private String remark ; 
    
    /*** 开始日期 */
  	private Date beginDate ; 
    
    /*** 结束日期 */
  	private Date endDate ; 

    //构造函数
    public CustCarInfoDto(){}

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
	 * 获取 车辆状态
	 * @return String
	 */
	public String getCarType() {
		return carType;
	}

	/**
	 * 设置 车辆状态
	 * @param carType
	 */
	public void setCarType(String carType) {
		this.carType = carType;
	}

    
    /**
	 * 获取 车牌
	 * @return String
	 */
	public String getCarPlate() {
		return carPlate;
	}

	/**
	 * 设置 车牌
	 * @param carPlate
	 */
	public void setCarPlate(String carPlate) {
		this.carPlate = carPlate;
	}

    
    /**
	 * 获取 车辆品牌
	 * @return String
	 */
	public String getCarBrand() {
		return carBrand;
	}

	/**
	 * 设置 车辆品牌
	 * @param carBrand
	 */
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

    
    /**
	 * 获取 车辆型号
	 * @return String
	 */
	public String getCarModel() {
		return carModel;
	}

	/**
	 * 设置 车辆型号
	 * @param carModel
	 */
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

    
    /**
	 * 获取 车辆购买年限
	 * @return String
	 */
	public String getCarBuyYear() {
		return carBuyYear;
	}

	/**
	 * 设置 车辆购买年限
	 * @param carBuyYear
	 */
	public void setCarBuyYear(String carBuyYear) {
		this.carBuyYear = carBuyYear;
	}

    
    /**
	 * 获取 车辆每月还款金额
	 * @return String
	 */
	public String getCarMthAmt() {
		return carMthAmt;
	}

	/**
	 * 设置 车辆每月还款金额
	 * @param carMthAmt
	 */
	public void setCarMthAmt(String carMthAmt) {
		this.carMthAmt = carMthAmt;
	}

    
    /**
	 * 获取 车辆每月还款日
	 * @return String
	 */
	public String getCarMthDay() {
		return carMthDay;
	}

	/**
	 * 设置 车辆每月还款日
	 * @param carMthDay
	 */
	public void setCarMthDay(String carMthDay) {
		this.carMthDay = carMthDay;
	}

    
    /**
	 * 获取 车辆剩余期限(月)
	 * @return String
	 */
	public String getCarMonth() {
		return carMonth;
	}

	/**
	 * 设置 车辆剩余期限(月)
	 * @param carMonth
	 */
	public void setCarMonth(String carMonth) {
		this.carMonth = carMonth;
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
	 * 获取 开始日期
	 * @return Date
	 */
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置 开始日期
	 * @param beginDate
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

    
    /**
	 * 获取 结束日期
	 * @return Date
	 */
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置 结束日期
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
    

}