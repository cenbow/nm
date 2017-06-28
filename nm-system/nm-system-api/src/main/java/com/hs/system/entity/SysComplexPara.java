package com.hs.system.entity;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * SYS_复杂参数表 对象
 * @author autocreate
 * @create 2015-10-30
 */
public class SysComplexPara implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 类别编码 */
  	@NotBlank(message="类型编码不能为空")
	@Size(max=40,message="类型编码超长")
  	private String typeNo ; 
    
    /*** 类别名称 */
  	@NotBlank(message="类别名称不能为空")
	@Size(max=120,message="类别名称超长")
  	private String typeName ; 
    
    /*** 参数编码 */
  	@NotBlank(message="参数编码不能为空")
	@Size(max=40,message="参数编码超长")
  	private String paraNo ; 
    
    /*** 参数名称 */
  	@NotBlank(message="参数名称不能为空")
	@Size(max=120,message="参数名称超长")
  	private String paraName ; 
    
    /*** 参数值 */
  	@NotBlank(message="参数值不能为空")
	@Size(max=40,message="参数值超长")
  	private String paraValue ; 
    
    /*** 参数开始值 */
  	@NotBlank(message="参数开始值不能为空")
	@Size(max=40,message="参数开始值超长")
  	private String paraStartValue ; 
    
    /*** 参数开始运算符 */
  	@NotBlank(message="参数开始运算符不能为空")
	@Size(max=40,message="参数开始运算符超长")
  	private String paraStartOpr ; 
    
    /*** 参数结束值 */
  	@NotBlank(message="参数结束值不能为空")
	@Size(max=40,message="参数结束值超长")
  	private String paraEndValue ; 
    
    /*** 参数结束运算符 */
  	@NotBlank(message="参数结束运算符不能为空")
	@Size(max=40,message="参数结束运算符超长")
  	private String paraEndOpr ; 
    
    /*** 参数精度 */
  	@NotBlank(message="参数精度不能为空")
	@Size(max=11,message="参数精度超长")
  	private Integer paraPrice ; 
    
    /*** 数据来源 */
  	@NotBlank(message="数据来源不能为空")
	@Size(max=40,message="数据来源超长")
  	private String dataSource ; 
    
    /*** 生效日期 */
	@Future(message="生效日期必须晚于当前时间")
  	private Date effectDate ; 
    
    /*** 失效日期 */
	@Future(message="失效日期必须晚于当前时间")
  	private Date loseDate ; 
    
    /*** 备注 */
  	@Size(max=400,message="备注超长")
  	private String remark ; 
    
    /*** 是否有效 */
  	@NotBlank(message="是否有效不能为空")
	@Size(max=8,message="是否有效超长")
  	private String stat ; 
    
    /*** 序号 */
  	private String orderNo ; 
    
    /*** 父节点 */
  	@Size(max=40,message="父节点超长")
  	private String pNode ; 

    //构造函数
    public SysComplexPara(){}

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
	 * 获取 类别编码
	 * @return String
	 */
	public String getTypeNo() {
		return typeNo;
	}

	/**
	 * 设置 类别编码
	 * @param typeNo
	 */
	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}

    
    /**
	 * 获取 类别名称
	 * @return String
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * 设置 类别名称
	 * @param typeName
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

    
    /**
	 * 获取 参数编码
	 * @return String
	 */
	public String getParaNo() {
		return paraNo;
	}

	/**
	 * 设置 参数编码
	 * @param paraNo
	 */
	public void setParaNo(String paraNo) {
		this.paraNo = paraNo;
	}

    
    /**
	 * 获取 参数名称
	 * @return String
	 */
	public String getParaName() {
		return paraName;
	}

	/**
	 * 设置 参数名称
	 * @param paraName
	 */
	public void setParaName(String paraName) {
		this.paraName = paraName;
	}

    
    /**
	 * 获取 参数值
	 * @return String
	 */
	public String getParaValue() {
		return paraValue;
	}

	/**
	 * 设置 参数值
	 * @param paraValue
	 */
	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}

    
    /**
	 * 获取 参数开始值
	 * @return String
	 */
	public String getParaStartValue() {
		return paraStartValue;
	}

	/**
	 * 设置 参数开始值
	 * @param paraStartValue
	 */
	public void setParaStartValue(String paraStartValue) {
		this.paraStartValue = paraStartValue;
	}

    
    /**
	 * 获取 参数开始运算符
	 * @return String
	 */
	public String getParaStartOpr() {
		return paraStartOpr;
	}

	/**
	 * 设置 参数开始运算符
	 * @param paraStartOpr
	 */
	public void setParaStartOpr(String paraStartOpr) {
		this.paraStartOpr = paraStartOpr;
	}

    
    /**
	 * 获取 参数结束值
	 * @return String
	 */
	public String getParaEndValue() {
		return paraEndValue;
	}

	/**
	 * 设置 参数结束值
	 * @param paraEndValue
	 */
	public void setParaEndValue(String paraEndValue) {
		this.paraEndValue = paraEndValue;
	}

    
    /**
	 * 获取 参数结束运算符
	 * @return String
	 */
	public String getParaEndOpr() {
		return paraEndOpr;
	}

	/**
	 * 设置 参数结束运算符
	 * @param paraEndOpr
	 */
	public void setParaEndOpr(String paraEndOpr) {
		this.paraEndOpr = paraEndOpr;
	}

    
    /**
	 * 获取 参数精度
	 * @return Integer
	 */
	public Integer getParaPrice() {
		return paraPrice;
	}

	/**
	 * 设置 参数精度
	 * @param paraPrice
	 */
	public void setParaPrice(Integer paraPrice) {
		this.paraPrice = paraPrice;
	}

    
    /**
	 * 获取 数据来源
	 * @return String
	 */
	public String getDataSource() {
		return dataSource;
	}

	/**
	 * 设置 数据来源
	 * @param dataSource
	 */
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

    
    /**
	 * 获取 生效日期
	 * @return Date
	 */
	public Date getEffectDate() {
		return effectDate;
	}

	/**
	 * 设置 生效日期
	 * @param effectDate
	 */
	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}

    
    /**
	 * 获取 失效日期
	 * @return Date
	 */
	public Date getLoseDate() {
		return loseDate;
	}

	/**
	 * 设置 失效日期
	 * @param loseDate
	 */
	public void setLoseDate(Date loseDate) {
		this.loseDate = loseDate;
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
	 * 获取 是否有效
	 * @return String
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * 设置 是否有效
	 * @param stat
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}

    
    /**
	 * 获取 序号
	 * @return String
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * 设置 序号
	 * @param orderNo
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

    
    /**
	 * 获取 父节点
	 * @return String
	 */
	public String getpNode() {
		return pNode;
	}

	/**
	 * 设置 父节点
	 * @param parentNode
	 */
	public void setpNode(String pNode) {
		this.pNode = pNode;
	}

}