package com.hs.system.entity;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

/**
 * SYS_系统操作日志 对象
 * @author autocreate
 * @create 2015-10-10
 */
public class SysOperaterLog implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /***  */
  	private String id ; 
    
    /*** 用户编号 */
  	@NotBlank(message="用户编号不能为空")
	@Size(max=32,message="用户编号超长")
  	private String staffId ; 
    
    /*** 用户名称 */
  	@NotBlank(message="用户名称不能为空")
	@Size(max=64,message="用户名称超长")
  	private String staffName ; 
    
    /*** 登录IP地址 */
  	@Size(max=32,message="登录IP地址超长")
  	private String logIp ; 
    
    /***服务器IP地址  */
  	@Size(max=32,message="服务器IP地址超长")
  	private String serverIp ; 
    
    /*** 业务类型 */
  	@NotBlank(message="业务类型不能为空")
	@Size(max=8,message="业务类型超长")
  	private String busiTyp ; 
    
    /*** 日志时间 */
  	@NotNull(message="日志时间不能为空")
	@Future(message="日志时间必须晚于当前时间")
  	@JsonSerialize(using = DateTimeJsonSerializer.class)
  	private Date logDate ; 
    
    /*** 操作类型 */
  	@NotBlank(message="操作类型不能为空")
	@Size(max=8,message="操作类型超长")
  	private String operateTyp ; 
    
    /*** 操作描述 */
	@Size(max=2000,message="操作描述超长")
  	private String operateInfo ; 
    
    /*** 子系统名字 */
  	@NotBlank(message="子系统名字不能为空")
	@Size(max=80,message="子系统名字超长")
  	private String sysName ; 
    
    /*** 渠道 */
	@Size(max=40,message="渠道超长")
  	private String channel ; 

    //构造函数
    public SysOperaterLog(){}

    //getter和setter方法
    
    /**
	 * 获取 
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getStaffId() {
		return staffId;
	}

	/**
	 * 设置 
	 * @param staffId
	 */
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * 设置 
	 * @param staffName
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getLogIp() {
		return logIp;
	}

	/**
	 * 设置 
	 * @param logIp
	 */
	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getServerIp() {
		return serverIp;
	}

	/**
	 * 设置 
	 * @param serverIp
	 */
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getBusiTyp() {
		return busiTyp;
	}

	/**
	 * 设置 
	 * @param busiTyp
	 */
	public void setBusiTyp(String busiTyp) {
		this.busiTyp = busiTyp;
	}

    
    /**
	 * 获取 
	 * @return Date
	 */
	public Date getLogDate() {
		return logDate;
	}

	/**
	 * 设置 
	 * @param logDate
	 */
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getOperateTyp() {
		return operateTyp;
	}

	/**
	 * 设置 
	 * @param operateTyp
	 */
	public void setOperateTyp(String operateTyp) {
		this.operateTyp = operateTyp;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getOperateInfo() {
		return operateInfo;
	}

	/**
	 * 设置 
	 * @param operateInfo
	 */
	public void setOperateInfo(String operateInfo) {
		this.operateInfo = operateInfo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getSysName() {
		return sysName;
	}

	/**
	 * 设置 
	 * @param sysName
	 */
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * 设置 
	 * @param channel
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

}