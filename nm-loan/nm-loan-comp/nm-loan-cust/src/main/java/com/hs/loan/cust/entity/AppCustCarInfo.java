package com.hs.loan.cust.entity;


import java.util.Date;
import java.io.Serializable;

import com.hs.loan.cust.itface.ICustExtraInfo;

/**
 * APP_客户车辆信息 对象
 * @author autocreate
 * @create 2015-10-26
 */
public class AppCustCarInfo implements Serializable,ICustExtraInfo{
	private static final long serialVersionUID = 1L;
	
	/*** ID */
  	private String id ; 
    
    /*** 客户编号 */
  	private String custNo ; 
    
    /*** 车辆状态 */
  	private String carType ; 
    
    /*** 车牌 */
  	private String carPlate ; 
    
    /*** 车辆品牌 */
  	private String carBrand ; 
    
    /*** 车辆型号 */
  	private String carModel ; 
    
    /*** 车辆购买年限 */
  	private String carBuyYear ; 
    
    /*** 车辆每月还款金额 */
  	private String carMthAmt ; 
    
    /*** 车辆每月还款日 */
  	private String carMthDay ; 
    
    /*** 车辆剩余期限(月) */
  	private String carMonth ; 
    
    /*** 备注 */
  	private String remark ; 
    
    /*** 开始日期 */
  	private Date beginDate ; 
    
    /*** 结束日期 */
  	private Date endDate ; 

    //构造函数
    public AppCustCarInfo(){}

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