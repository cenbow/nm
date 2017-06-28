package com.hs.loan.collection.dto;


import java.util.Date;

import java.io.Serializable;

/**
 * PUB_消息模版 对象
 * @author autocreate
 * @create 2016-04-25
 */
public class PubMessageModelDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 消息模版编码 */
  	private String messageCode ; 
    
    /*** 消息模版名字 */
  	private String messageName ; 
    
    /*** 消息模版类型 */
  	private String messageTyp ; 
    
    /*** 消息模版状态 */
  	private String messageStat ; 
    
    /***  */
  	private String msg ; 
    
    /*** 创建时间 */
  	private Date instDate ; 
    
    /***  */
  	private String mgbz ; 

    //构造函数
    public PubMessageModelDto(){}

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
	 * 获取 消息模版编码
	 * @return String
	 */
	public String getMessageCode() {
		return messageCode;
	}

	/**
	 * 设置 消息模版编码
	 * @param messageCode
	 */
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

    
    /**
	 * 获取 消息模版名字
	 * @return String
	 */
	public String getMessageName() {
		return messageName;
	}

	/**
	 * 设置 消息模版名字
	 * @param messageName
	 */
	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}

    
    /**
	 * 获取 消息模版类型
	 * @return String
	 */
	public String getMessageTyp() {
		return messageTyp;
	}

	/**
	 * 设置 消息模版类型
	 * @param messageTyp
	 */
	public void setMessageTyp(String messageTyp) {
		this.messageTyp = messageTyp;
	}

    
    /**
	 * 获取 消息模版状态
	 * @return String
	 */
	public String getMessageStat() {
		return messageStat;
	}

	/**
	 * 设置 消息模版状态
	 * @param messageStat
	 */
	public void setMessageStat(String messageStat) {
		this.messageStat = messageStat;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * 设置 
	 * @param msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

    
    /**
	 * 获取 创建时间
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 创建时间
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getMgbz() {
		return mgbz;
	}

	/**
	 * 设置 
	 * @param mgbz
	 */
	public void setMgbz(String mgbz) {
		this.mgbz = mgbz;
	}

}