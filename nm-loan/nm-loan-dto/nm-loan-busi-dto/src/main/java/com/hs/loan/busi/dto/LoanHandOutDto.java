package com.hs.loan.busi.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 分期经办登记 查询 对象
 * 
 * @author jqiu
 * @create 2015-10-27
 */
public class LoanHandOutDto implements Serializable {
	private static final long serialVersionUID = 1L;

	/***  */
	private String id;

	/*** 分期编号 */
	@NotBlank(message = "分期编号不能为空")
	@Size(max = 40, message = "分期编号超长")
	private String loanNo;

	/*** 客户编号 */
	@NotBlank(message = "客户编号不能为空")
	@Size(max = 40, message = "客户编号超长")
	private String custNo;
	/*** 客户名称 */
	private String custName;

	/*** 类型 */
	@NotBlank(message = "类型不能为空")
	@Size(max = 8, message = "类型超长")
	private String typ;

	/*** 类型名 */
	private String typName;

	/*** 处理日期 */
	@NotNull(message = "处理日期不能为空")
	@Future(message = "处理日期必须晚于当前时间")
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	private Date handDate;

	/*** 处理人 */
	@NotNull(message = "处理人不能为空")
	@Size(max = 10, message = "处理人超长")
	private String handPersonNo;

	/*** 处理人姓名 */
	@NotNull(message = "处理人姓名不能为空")
	@Size(max = 40, message = "处理人姓名超长")
	private String handPersonName;

	/*** 说明 */
	@Size(max = 400, message = "说明超长")
	private String remark;

	/*** 处理内容编码 */
	@NotNull(message = "处理内容编码不能为空")
	@Size(max = 8, message = "处理内容编码超长")
	private String handDetail;

	/*** 处理内容 */
	private String handDetailName;
	
	/*** 客户标识 */
	private String custIdentifier;

	// getter和setter方法

	/**
	 * 获取
	 * 
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 分期编号
	 * 
	 * @return String
	 */
	public String getLoanNo() {
		return loanNo;
	}

	/**
	 * 设置 分期编号
	 * 
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	/**
	 * 获取 客户编号
	 * 
	 * @return String
	 */
	public String getCustNo() {
		return custNo;
	}

	/**
	 * 设置 客户编号
	 * 
	 * @param custNo
	 */
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	/**
	 * 获取 类型
	 * 
	 * @return String
	 */
	public String getTyp() {
		return typ;
	}

	/**
	 * 设置 类型
	 * 
	 * @param typ
	 */
	public void setTyp(String typ) {
		this.typ = typ;
	}

	/**
	 * 获取 处理日期
	 * 
	 * @return Date
	 */
	public Date getHandDate() {
		return handDate;
	}

	/**
	 * 设置 处理日期
	 * 
	 * @param handDate
	 */
	public void setHandDate(Date handDate) {
		this.handDate = handDate;
	}

	/**
	 * 获取 说明
	 * 
	 * @return String
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置 说明
	 * 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取 处理内容
	 * 
	 * @return String
	 */
	public String getHandDetail() {
		return handDetail;
	}

	/**
	 * 设置 处理内容
	 * 
	 * @param handDetail
	 */
	public void setHandDetail(String handDetail) {
		this.handDetail = handDetail;
	}

	public String getTypName() {
		return typName;
	}

	public void setTypName(String typName) {
		this.typName = typName;
	}

	public String getHandPersonName() {
		return handPersonName;
	}

	public void setHandPersonName(String handPersonName) {
		this.handPersonName = handPersonName;
	}

	public String getHandDetailName() {
		return handDetailName;
	}

	public void setHandDetailName(String handDetailName) {
		this.handDetailName = handDetailName;
	}

	public String getHandPersonNo() {
		return handPersonNo;
	}

	public void setHandPersonNo(String handPersonNo) {
		this.handPersonNo = handPersonNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustIdentifier() {
		return custIdentifier;
	}

	public void setCustIdentifier(String custIdentifier) {
		this.custIdentifier = custIdentifier;
	}

}