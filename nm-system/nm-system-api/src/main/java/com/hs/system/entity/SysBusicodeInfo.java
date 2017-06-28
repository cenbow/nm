package com.hs.system.entity;


import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 业务参数 对象
 * @author autocreate
 * @create 2015-09-23
 */
public class SysBusicodeInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	@NotBlank(message="类型编码不能为空")
	@Size(max=40,message="类型编码超长")
  	private String typeCode ; 

	@NotBlank(message="参数名称不能为空")
	@Size(max=64,message="参数名称超长")
  	private String codeName ; 

	@NotBlank(message="参数类型不能为空")
	@Size(max=8,message="参数类型超长")
  	private String codeType ; 

	@NotBlank(message="参数开始值不能为空")
	@Size(max=64,message="参数开始值超长")
  	private String codeValueStart ; 

	@NotBlank(message="参数开始运算符不能为空")
	@Size(max=8,message="参数开始运算符超长")
  	private String startSign ; 

	@NotBlank(message="参数结束值不能为空")
	@Size(max=64,message="参数结束值超长")
  	private String codeValueEnd ; 

	@NotBlank(message="参数结束值运算符不能为空")
	@Size(max=8,message="参数结束值运算符超长")
  	private String endSign ; 

	@NotBlank(message="参数精度不能为空")
	@Size(max=8,message="参数精度超长")
  	private String codeAccuracy ; 

	@NotBlank(message="数据来源不能为空")
	@Size(max=8,message="数据来源超长")
  	private String dateSrc ; 

	@NotBlank(message="生效日期不能为空")
	@Size(max=8,message="生效日期超长")
  	private String efftDt ; 

	@NotBlank(message="失效日期不能为空")
	@Size(max=8,message="失效日期超长")
  	private String invalDate ; 

	@Size(max=512,message="备注超长")
  	private String remark ; 

	@NotBlank(message="是否有效不能为空")
	@Size(max=8,message="是否有效超长")
  	private String isEfft ; 

	@NotBlank(message="码值不能为空")
	@Size(max=8,message="码值超长")
  	private String codeValue ; 

  	private Integer indexNo ; 

	@Size(max=40,message="父节点超长")
  	private String parTableKey ; 


    //构造函数
    public SysBusicodeInfo(){}

    //getter和setter方法
    
    /**
	 * 获取 
	 * @return String
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * 设置 
	 * @param typeCode
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getCodeName() {
		return codeName;
	}

	/**
	 * 设置 
	 * @param codeName
	 */
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getCodeType() {
		return codeType;
	}

	/**
	 * 设置 
	 * @param codeType
	 */
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getCodeValueStart() {
		return codeValueStart;
	}

	/**
	 * 设置 
	 * @param codeValueStart
	 */
	public void setCodeValueStart(String codeValueStart) {
		this.codeValueStart = codeValueStart;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getStartSign() {
		return startSign;
	}

	/**
	 * 设置 
	 * @param startSign
	 */
	public void setStartSign(String startSign) {
		this.startSign = startSign;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getCodeValueEnd() {
		return codeValueEnd;
	}

	/**
	 * 设置 
	 * @param codeValueEnd
	 */
	public void setCodeValueEnd(String codeValueEnd) {
		this.codeValueEnd = codeValueEnd;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getEndSign() {
		return endSign;
	}

	/**
	 * 设置 
	 * @param endSign
	 */
	public void setEndSign(String endSign) {
		this.endSign = endSign;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getCodeAccuracy() {
		return codeAccuracy;
	}

	/**
	 * 设置 
	 * @param codeAccuracy
	 */
	public void setCodeAccuracy(String codeAccuracy) {
		this.codeAccuracy = codeAccuracy;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getDateSrc() {
		return dateSrc;
	}

	/**
	 * 设置 
	 * @param dateSrc
	 */
	public void setDateSrc(String dateSrc) {
		this.dateSrc = dateSrc;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getEfftDt() {
		return efftDt;
	}

	/**
	 * 设置 
	 * @param efftDt
	 */
	public void setEfftDt(String efftDt) {
		this.efftDt = efftDt;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getInvalDate() {
		return invalDate;
	}

	/**
	 * 设置 
	 * @param invalDate
	 */
	public void setInvalDate(String invalDate) {
		this.invalDate = invalDate;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getIsEfft() {
		return isEfft;
	}

	/**
	 * 设置 
	 * @param isEfft
	 */
	public void setIsEfft(String isEfft) {
		this.isEfft = isEfft;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getCodeValue() {
		return codeValue;
	}

	/**
	 * 设置 
	 * @param codeValue
	 */
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

    
    /**
	 * 获取 
	 * @return Integer
	 */
	public Integer getIndexNo() {
		return indexNo;
	}

	/**
	 * 设置 
	 * @param indexNo
	 */
	public void setIndexNo(Integer indexNo) {
		this.indexNo = indexNo;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getParTableKey() {
		return parTableKey;
	}

	/**
	 * 设置 
	 * @param parTableKey
	 */
	public void setParTableKey(String parTableKey) {
		this.parTableKey = parTableKey;
	}

}