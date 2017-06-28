package com.hs.loan.finance.dto;


import java.util.Date;

import java.io.Serializable;

/**
 *  安全合规对象
 * @author autocreate
 * @create 2016-10-13
 */
public class CtsCaseInfoDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 案件来源 */
  	private String caseSource ; 
    
    /*** 案件编号 */
  	private String caseNo ; 
    
    /*** 案件性质 */
  	private String caseType ; 
    
    /*** 案发日期 */
  	private Date incidentDate ; 
    
    /*** 案件描述 */
  	private String caseDesc ; 
    
    /*** 客户姓名 */
  	private String custName ; 
    
    /*** 客户身份证号 */
  	private String custCard ; 
    
    /*** 客户电话 */
  	private String custTel ; 
    
    /*** 案发城市 */
  	private String city ; 
    
    /*** 网点编码 */
  	private String posNo ; 
    
    /*** 问题类型 */
  	private String prTyp ; 
    
    /*** 投诉人姓名 */
  	private String comName ; 
    
    /*** 投诉日期 */
  	private Date comDate ; 
    
    /*** 投诉人电话 */
  	private String comPhone ; 
    
    /*** 投诉对象 */
  	private String comObj ; 
    
    /*** 存档备注 */
  	private String archNote ; 
    
    /*** 录入时间 */
  	private Date instDate ; 
    
    /*** 更新时间 */
  	private Date updtDate ; 
    
    /*** 操作人 */
  	private String staffNo ; 
    
    /*** 操作人姓名 */
  	private String staffName ; 
    
    /*** 立案状态 */
  	private String dealStat ; 
    
    /*** 复核状态 */
  	private String caseStat ; 

    //构造函数
    public CtsCaseInfoDto(){}

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
	 * 获取 案件来源
	 * @return String
	 */
	public String getCaseSource() {
		return caseSource;
	}

	/**
	 * 设置 案件来源
	 * @param caseSource
	 */
	public void setCaseSource(String caseSource) {
		this.caseSource = caseSource;
	}

    
    /**
	 * 获取 案件编号
	 * @return String
	 */
	public String getCaseNo() {
		return caseNo;
	}

	/**
	 * 设置 案件编号
	 * @param caseNo
	 */
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

    
    /**
	 * 获取 案件性质
	 * @return String
	 */
	public String getCaseType() {
		return caseType;
	}

	/**
	 * 设置 案件性质
	 * @param caseType
	 */
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

    
    /**
	 * 获取 案发日期
	 * @return Date
	 */
	public Date getIncidentDate() {
		return incidentDate;
	}

	/**
	 * 设置 案发日期
	 * @param incidentDate
	 */
	public void setIncidentDate(Date incidentDate) {
		this.incidentDate = incidentDate;
	}

    
    /**
	 * 获取 案件描述
	 * @return String
	 */
	public String getCaseDesc() {
		return caseDesc;
	}

	/**
	 * 设置 案件描述
	 * @param caseDesc
	 */
	public void setCaseDesc(String caseDesc) {
		this.caseDesc = caseDesc;
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
	 * 获取 客户身份证号
	 * @return String
	 */
	public String getCustCard() {
		return custCard;
	}

	/**
	 * 设置 客户身份证号
	 * @param custCard
	 */
	public void setCustCard(String custCard) {
		this.custCard = custCard;
	}

    
    /**
	 * 获取 客户电话
	 * @return String
	 */
	public String getCustTel() {
		return custTel;
	}

	/**
	 * 设置 客户电话
	 * @param custTel
	 */
	public void setCustTel(String custTel) {
		this.custTel = custTel;
	}

    
    /**
	 * 获取 案发城市
	 * @return String
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 设置 案发城市
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

    
    /**
	 * 获取 网点编码
	 * @return String
	 */
	public String getPosNo() {
		return posNo;
	}

	/**
	 * 设置 网点编码
	 * @param posNo
	 */
	public void setPosNo(String posNo) {
		this.posNo = posNo;
	}

    
    /**
	 * 获取 问题类型
	 * @return String
	 */
	public String getPrTyp() {
		return prTyp;
	}

	/**
	 * 设置 问题类型
	 * @param prTyp
	 */
	public void setPrTyp(String prTyp) {
		this.prTyp = prTyp;
	}

    
    /**
	 * 获取 投诉人姓名
	 * @return String
	 */
	public String getComName() {
		return comName;
	}

	/**
	 * 设置 投诉人姓名
	 * @param comName
	 */
	public void setComName(String comName) {
		this.comName = comName;
	}

    
    /**
	 * 获取 投诉日期
	 * @return Date
	 */
	public Date getComDate() {
		return comDate;
	}

	/**
	 * 设置 投诉日期
	 * @param comDate
	 */
	public void setComDate(Date comDate) {
		this.comDate = comDate;
	}

    
    /**
	 * 获取 投诉人电话
	 * @return String
	 */
	public String getComPhone() {
		return comPhone;
	}

	/**
	 * 设置 投诉人电话
	 * @param comPhone
	 */
	public void setComPhone(String comPhone) {
		this.comPhone = comPhone;
	}

    
    /**
	 * 获取 投诉对象
	 * @return String
	 */
	public String getComObj() {
		return comObj;
	}

	/**
	 * 设置 投诉对象
	 * @param comObj
	 */
	public void setComObj(String comObj) {
		this.comObj = comObj;
	}

    
    /**
	 * 获取 存档备注
	 * @return String
	 */
	public String getArchNote() {
		return archNote;
	}

	/**
	 * 设置 存档备注
	 * @param archNote
	 */
	public void setArchNote(String archNote) {
		this.archNote = archNote;
	}

    
    /**
	 * 获取 录入时间
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 录入时间
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

    
    /**
	 * 获取 操作人
	 * @return String
	 */
	public String getStaffNo() {
		return staffNo;
	}

	/**
	 * 设置 操作人
	 * @param staffNo
	 */
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

    
    /**
	 * 获取 操作人姓名
	 * @return String
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * 设置 操作人姓名
	 * @param staffName
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

    
    /**
	 * 获取 立案状态
	 * @return String
	 */
	public String getDealStat() {
		return dealStat;
	}

	/**
	 * 设置 立案状态
	 * @param dealStat
	 */
	public void setDealStat(String dealStat) {
		this.dealStat = dealStat;
	}

    
    /**
	 * 获取 复核状态
	 * @return String
	 */
	public String getCaseStat() {
		return caseStat;
	}

	/**
	 * 设置 复核状态
	 * @param caseStat
	 */
	public void setCaseStat(String caseStat) {
		this.caseStat = caseStat;
	}

}