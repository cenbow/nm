package com.hs.loan.finance.dto;


import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateJsonSerializer;

/**
 * ACC_银联交易日志（单笔） 对象
 * @author autocreate
 * @create 2016-02-03
 */
public class AccCapTranLogDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 账单日 */
  	private String repayDate ; 
    
    /*** 交易码 */
  	private String tranCode ; 
    
    /*** 发送时间 */
  	@JsonSerialize(using = DateJsonSerializer.class)
  	private Date sendDate ; 
    
    /*** 返回时间 */
  	@JsonSerialize(using = DateJsonSerializer.class)
  	private Date recvDate ; 
    
    /*** 文件名 */
  	private String fileName ; 
    
    /*** 发送报文 */
  	private String sendData ; 
    
    /*** 返回报文 */
  	private String recvData ; 
    
    /*** 渠道编号 */
  	private String chanNo ; 
    
    /*** 备注 */
  	private String remark ; 

    //构造函数
    public AccCapTranLogDto(){}

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
	 * 获取 账单日
	 * @return String
	 */
	public String getRepayDate() {
		return repayDate;
	}

	/**
	 * 设置 账单日
	 * @param repayDate
	 */
	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

    
    /**
	 * 获取 交易码
	 * @return String
	 */
	public String getTranCode() {
		return tranCode;
	}

	/**
	 * 设置 交易码
	 * @param tranCode
	 */
	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}

    
    /**
	 * 获取 发送时间
	 * @return Date
	 */
	public Date getSendDate() {
		return sendDate;
	}

	/**
	 * 设置 发送时间
	 * @param sendDate
	 */
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

    
    /**
	 * 获取 返回时间
	 * @return Date
	 */
	public Date getRecvDate() {
		return recvDate;
	}

	/**
	 * 设置 返回时间
	 * @param recvDate
	 */
	public void setRecvDate(Date recvDate) {
		this.recvDate = recvDate;
	}

    
    /**
	 * 获取 文件名
	 * @return String
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * 设置 文件名
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

    
    public String getSendData() {
		return sendData;
	}

	public void setSendData(String sendData) {
		this.sendData = sendData;
	}

	public String getRecvData() {
		return recvData;
	}

	public void setRecvData(String recvData) {
		this.recvData = recvData;
	}

	/**
	 * 获取 渠道编号
	 * @return String
	 */
	public String getChanNo() {
		return chanNo;
	}

	/**
	 * 设置 渠道编号
	 * @param chanNo
	 */
	public void setChanNo(String chanNo) {
		this.chanNo = chanNo;
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

}