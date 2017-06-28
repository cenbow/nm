package com.hs.loan.busi.dto;


import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

/**
 * CRM_电销意向办理客户工单信息，将与业务系统数据交互 对象
 * @author autocreate
 * @create 2017-04-24
 */
public class CrmCustomerOrderDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	   
    /*** 工单号-对应唯一的电访记录 */
  	private String orderId ; 
    
    /*** 电访记录号 */
  	private String telRecordId ; 
    
    /*** 客户姓名 */
  	private String custName ; 
    
    /*** 客户类型 */
  	private String custType ; 
    
    /***  */
  	private String sex ; 
    
    /*** 证件类型 */
  	private String certType ; 
    
    /*** 证件号码 */
  	private String certNo ; 
    
    /*** 手机号码 */
  	private String phoneNo ; 
    
    /*** 意向产品编号 */
  	private String intentProdNo ; 
    
    /*** 意向增值费用 */
  	private String intentFeeInfo ; 
    
    /*** 意向金额 */
  	private java.math.BigDecimal intentAmount ; 
    
    /*** 意向期数 */
  	private Integer intentInstNum ; 
    
    /*** 客户所在区域（线下办理区域）如：510000,510100,510101 */
  	private String saleAreaNo ; 
    
    /*** 客户所在详细地址 */
  	private String detailAddress ; 
    
    /*** 意向办理时间 */
  	@JsonSerialize(using = DateTimeJsonSerializer.class)
  	private Date intentDealDate ; 
    
    /*** 工单处理状态-标记系统间数据同步后后续处理-数据字典：OrderSendStat 待发送-70027001，已发送-70027002，未分配70027003，已分配70027004，未处理-70027005，已填单-70027006，已提交-70027007**/
  	private String orderDealStat ; 
    
    /*** 销售机构(被派单机构) */
  	private String saleOrganNo ; 
    
    /*** 工单分配人(销售区域经理) */
  	private String saleMgrStaffno ; 
    
    /*** 工单被分配人(销售员) */
  	private String saleStaffNo ; 
    
    /*** 实际产品编号 */
  	private String actualProdNo ; 
    
    /*** 实际分期金额 */
  	private java.math.BigDecimal actualAmount ; 
    
    /*** 实际分期期数 */
  	private Integer actualInstNum ; 
    
    /*** 实际办理时间 */
  	private Date actualDealTime ; 
    
    /*** 办理结果，数据字典码：70007100，未办理-70007101,签约成功-70007102,签约失败-70007103,工单撤销-70007104 */
  	private String dealResult ; 
    
    /*** 贷款编号 */
  	private String loanNo ; 
    
    /*** 业务系统返回时-记录更新时间 */
  	private Date updateTime ; 
    
    /*** CRM系统备注 */
  	private String crmRemark ; 
    
    /*** 业务系统备注 */
  	private String billRemark ; 
  	
  	/***意向产品名称*/
  	private String intentProdName;

	/***退回原因*/
  	private String returnReason;
  	
    //构造函数
    public CrmCustomerOrderDto(){}

    //getter和setter方法
    
    /**
	 * 获取 工单号-对应唯一的电访记录
	 * @return String
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * 设置 工单号-对应唯一的电访记录
	 * @param orderId
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

    
    /**
	 * 获取 电访记录号
	 * @return String
	 */
	public String getTelRecordId() {
		return telRecordId;
	}

	/**
	 * 设置 电访记录号
	 * @param telRecordId
	 */
	public void setTelRecordId(String telRecordId) {
		this.telRecordId = telRecordId;
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
	 * 获取 客户类型
	 * @return String
	 */
	public String getCustType() {
		return custType;
	}

	/**
	 * 设置 客户类型
	 * @param custType
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 设置 
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

    
    /**
	 * 获取 证件类型
	 * @return String
	 */
	public String getCertType() {
		return certType;
	}

	/**
	 * 设置 证件类型
	 * @param certType
	 */
	public void setCertType(String certType) {
		this.certType = certType;
	}

    
    /**
	 * 获取 证件号码
	 * @return String
	 */
	public String getCertNo() {
		return certNo;
	}

	/**
	 * 设置 证件号码
	 * @param certNo
	 */
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

    
    /**
	 * 获取 手机号码
	 * @return String
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * 设置 手机号码
	 * @param phoneNo
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

    
    /**
	 * 获取 意向产品编号
	 * @return String
	 */
	public String getIntentProdNo() {
		return intentProdNo;
	}

	/**
	 * 设置 意向产品编号
	 * @param intentProdNo
	 */
	public void setIntentProdNo(String intentProdNo) {
		this.intentProdNo = intentProdNo;
	}

    
    /**
	 * 获取 意向增值费用
	 * @return String
	 */
	public String getIntentFeeInfo() {
		return intentFeeInfo;
	}

	/**
	 * 设置 意向增值费用
	 * @param intentFeeInfo
	 */
	public void setIntentFeeInfo(String intentFeeInfo) {
		this.intentFeeInfo = intentFeeInfo;
	}

    
    /**
	 * 获取 意向金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getIntentAmount() {
		return intentAmount;
	}

	/**
	 * 设置 意向金额
	 * @param intentAmount
	 */
	public void setIntentAmount(java.math.BigDecimal intentAmount) {
		this.intentAmount = intentAmount;
	}

    
    /**
	 * 获取 意向期数
	 * @return Integer
	 */
	public Integer getIntentInstNum() {
		return intentInstNum;
	}

	/**
	 * 设置 意向期数
	 * @param intentInstNum
	 */
	public void setIntentInstNum(Integer intentInstNum) {
		this.intentInstNum = intentInstNum;
	}

    
    /**
	 * 获取 客户所在区域（线下办理区域）如：510000,510100,510101
	 * @return String
	 */
	public String getSaleAreaNo() {
		return saleAreaNo;
	}

	/**
	 * 设置 客户所在区域（线下办理区域）如：510000,510100,510101
	 * @param saleAreaNo
	 */
	public void setSaleAreaNo(String saleAreaNo) {
		this.saleAreaNo = saleAreaNo;
	}

    
    /**
	 * 获取 客户所在详细地址
	 * @return String
	 */
	public String getDetailAddress() {
		return detailAddress;
	}

	/**
	 * 设置 客户所在详细地址
	 * @param detailAddress
	 */
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

    
    /**
	 * 获取 意向办理时间
	 * @return Date
	 */
	public Date getIntentDealDate() {
		return intentDealDate;
	}

	/**
	 * 设置 意向办理时间
	 * @param intentDealDate
	 */
	public void setIntentDealDate(Date intentDealDate) {
		this.intentDealDate = intentDealDate;
	}

    
    /**
	 * 获取 工单处理状态-标记系统间数据同步后后续处理-数据字典：OrderSendStat 待发送-70027001，已发送-70027002，待处理-70027003，已处理-70027004
	 * @return String
	 */
	public String getOrderDealStat() {
		return orderDealStat;
	}

	/**
	 * 设置 工单处理状态-标记系统间数据同步后后续处理-数据字典：OrderSendStat 待发送-70027001，已发送-70027002，待处理-70027003，已处理-70027004
	 * @param orderDealStat
	 */
	public void setOrderDealStat(String orderDealStat) {
		this.orderDealStat = orderDealStat;
	}

    
    /**
	 * 获取 销售机构(被派单机构)
	 * @return String
	 */
	public String getSaleOrganNo() {
		return saleOrganNo;
	}

	/**
	 * 设置 销售机构(被派单机构)
	 * @param saleOrganNo
	 */
	public void setSaleOrganNo(String saleOrganNo) {
		this.saleOrganNo = saleOrganNo;
	}

    
    /**
	 * 获取 工单分配人(销售区域经理)
	 * @return String
	 */
	public String getSaleMgrStaffno() {
		return saleMgrStaffno;
	}

	/**
	 * 设置 工单分配人(销售区域经理)
	 * @param saleMgrStaffno
	 */
	public void setSaleMgrStaffno(String saleMgrStaffno) {
		this.saleMgrStaffno = saleMgrStaffno;
	}

    
    /**
	 * 获取 工单被分配人(销售员)
	 * @return String
	 */
	public String getSaleStaffNo() {
		return saleStaffNo;
	}

	/**
	 * 设置 工单被分配人(销售员)
	 * @param saleStaffNo
	 */
	public void setSaleStaffNo(String saleStaffNo) {
		this.saleStaffNo = saleStaffNo;
	}

    
    /**
	 * 获取 实际产品编号
	 * @return String
	 */
	public String getActualProdNo() {
		return actualProdNo;
	}

	/**
	 * 设置 实际产品编号
	 * @param actualProdNo
	 */
	public void setActualProdNo(String actualProdNo) {
		this.actualProdNo = actualProdNo;
	}

    
    /**
	 * 获取 实际分期金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getActualAmount() {
		return actualAmount;
	}

	/**
	 * 设置 实际分期金额
	 * @param actualAmount
	 */
	public void setActualAmount(java.math.BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}

    
    /**
	 * 获取 实际分期期数
	 * @return Integer
	 */
	public Integer getActualInstNum() {
		return actualInstNum;
	}

	/**
	 * 设置 实际分期期数
	 * @param actualInstNum
	 */
	public void setActualInstNum(Integer actualInstNum) {
		this.actualInstNum = actualInstNum;
	}

    
    /**
	 * 获取 实际办理时间
	 * @return Date
	 */
	public Date getActualDealTime() {
		return actualDealTime;
	}

	/**
	 * 设置 实际办理时间
	 * @param actualDealTime
	 */
	public void setActualDealTime(Date actualDealTime) {
		this.actualDealTime = actualDealTime;
	}

    
    /**
	 * 获取 办理结果，数据字典码：70007100，未办理-70007101,签约成功-70007102,签约失败-70007103,工单撤销-70007104
	 * @return String
	 */
	public String getDealResult() {
		return dealResult;
	}

	/**
	 * 设置 办理结果，数据字典码：70007100，未办理-70007101,签约成功-70007102,签约失败-70007103,工单撤销-70007104
	 * @param dealResult
	 */
	public void setDealResult(String dealResult) {
		this.dealResult = dealResult;
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
	 * 获取 业务系统返回时-记录更新时间
	 * @return Date
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置 业务系统返回时-记录更新时间
	 * @param updateTime
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

    
    /**
	 * 获取 CRM系统备注
	 * @return String
	 */
	public String getCrmRemark() {
		return crmRemark;
	}

	/**
	 * 设置 CRM系统备注
	 * @param crmRemark
	 */
	public void setCrmRemark(String crmRemark) {
		this.crmRemark = crmRemark;
	}

    
    /**
	 * 获取 业务系统备注
	 * @return String
	 */
	public String getBillRemark() {
		return billRemark;
	}

	/**
	 * 设置 业务系统备注
	 * @param billRemark
	 */
	public void setBillRemark(String billRemark) {
		this.billRemark = billRemark;
	}

	public String getIntentProdName() {
		return intentProdName;
	}

	public void setIntentProdName(String intentProdName) {
		this.intentProdName = intentProdName;
	}

	public String getReturnReason() {
		return returnReason;
	}

	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}

}