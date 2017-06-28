package com.hs.loan.operation.entity;


import java.util.Date;

import java.io.Serializable;

/**
 * PUB_合同模版 对象
 * @author autocreate
 * @create 2015-10-15
 */
public class PubLoanContractFile implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 模版编号 */
  	private String fileId ; 
    
    /*** 模版编码 */
  	private String fileCod ; 
    
    /*** 模版名称 */
  	private String fileName ; 
    
    /*** 文件类型 */
  	private String fileTyp ; 
    
    /*** 渠道 */
  	private String chanNo ; 
    
    /*** 创建时间 */
  	private Date instDate ; 
    
    /*** 更新时间 */
  	private Date updtDate ; 
  	
	/*** 签名x坐标 */
  	private String signX ; 
  	/*** 签名Y坐标*/
  	private String signY ; 
  	/*** 页码 */
  	private String signPage ; 
  	
  	/*** 签名x坐标 */
  	private String cSignX ; 
  	/*** 签名Y坐标*/
  	private String cSignY ; 
  	/*** 页码 */
  	private String cSignPage ;
	private String shopSignX;
	private String shopSignY;
	private  String shopSignPage;

	public String getShopSignX() {
		return shopSignX;
	}

	public void setShopSignX(String shopSignX) {
		this.shopSignX = shopSignX;
	}

	public String getShopSignY() {
		return shopSignY;
	}

	public void setShopSignY(String shopSignY) {
		this.shopSignY = shopSignY;
	}

	public String getShopSignPage() {
		return shopSignPage;
	}

	public void setShopSignPage(String shopSignPage) {
		this.shopSignPage = shopSignPage;
	}

	public String getSignX() {
		return signX;
	}

	public void setSignX(String signX) {
		this.signX = signX;
	}

	public String getSignY() {
		return signY;
	}

	public void setSignY(String signY) {
		this.signY = signY;
	}

	public String getSignPage() {
		return signPage;
	}

	public void setSignPage(String signPage) {
		this.signPage = signPage;
	}

	//构造函数
    public PubLoanContractFile(){}

    //getter和setter方法
    
    /**
	 * 获取 模版编号
	 * @return String
	 */
	public String getFileId() {
		return fileId;
	}

	/**
	 * 设置 模版编号
	 * @param fileId
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

    
    /**
	 * 获取 模版编码
	 * @return String
	 */
	public String getFileCod() {
		return fileCod;
	}

	/**
	 * 设置 模版编码
	 * @param fileCod
	 */
	public void setFileCod(String fileCod) {
		this.fileCod = fileCod;
	}

    
    /**
	 * 获取 模版名称
	 * @return String
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * 设置 模版名称
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

    
    /**
	 * 获取 文件类型
	 * @return String
	 */
	public String getFileTyp() {
		return fileTyp;
	}

	/**
	 * 设置 文件类型
	 * @param fileTyp
	 */
	public void setFileTyp(String fileTyp) {
		this.fileTyp = fileTyp;
	}

    
    /**
	 * 获取 渠道
	 * @return String
	 */
	public String getChanNo() {
		return chanNo;
	}

	/**
	 * 设置 渠道
	 * @param chanNo
	 */
	public void setChanNo(String chanNo) {
		this.chanNo = chanNo;
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
	 * 获取 更新时间
	 * @return Date
	 */
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 更新时间
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	public String getcSignX() {
		return cSignX;
	}

	public void setcSignX(String cSignX) {
		this.cSignX = cSignX;
	}

	public String getcSignY() {
		return cSignY;
	}

	public void setcSignY(String cSignY) {
		this.cSignY = cSignY;
	}

	public String getcSignPage() {
		return cSignPage;
	}

	public void setcSignPage(String cSignPage) {
		this.cSignPage = cSignPage;
	}

}