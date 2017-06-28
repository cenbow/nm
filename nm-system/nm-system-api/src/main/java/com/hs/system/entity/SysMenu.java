package com.hs.system.entity;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 * 菜单 对象
 * @author autocreate
 * @create 2015-09-28
 */
public class SysMenu implements Serializable{
	private static final long serialVersionUID = 1L;
	
  	private String id ; 

  	@NotBlank(message="菜单名称不能为空")
	@Size(max=40,message="菜单名称超长")
  	private String menuName ; 

	@Size(max=40,message="上级菜单编号超长")
  	private String parMenuId ; 

  	@NotBlank(message="菜单类型不能为空")
	@Size(max=10,message="菜单类型超长")
  	private String menuTyp ; 

  	@NotBlank(message="菜单链接不能为空")
	@Size(max=100,message="菜单链接超长")
  	private String url ; 

	@Size(max=100,message="图片超长")
  	private String img ; 

//  	@NotBlank(message="菜单目标不能为空")
	@Size(max=10,message="菜单目标超长")
  	private String menuTargt ; 

//  	@NotBlank(message="类型编码不能为空")
//	@Size(max=11,message="菜单层超长")
  	private Integer menuLayer ; 

	@Range(min=1,max=99999,message="菜单排序超长")
  	@NotNull(message="菜单排序不能为空")
//	@Size(max=11,message="菜单排序超长")
  	private Integer menuOrder ; 

//  	@NotBlank(message="菜单状态不能为空")
	@Size(max=10,message="菜单状态超长")
  	private String menuStat ; 

	@Size(max=40,message="子系统编号超长")
  	private String sysId ; 

  	@Future(message="创建时间必须晚于当前时间")
  	private Date instDate ; 

  	@Future(message="修改时间必须晚于当前时间")
  	private Date updtDate ; 


    //构造函数
    public SysMenu(){}

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
	public String getMenuName() {
		return menuName;
	}

	/**
	 * 设置 
	 * @param menuName
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getParMenuId() {
		return parMenuId;
	}

	/**
	 * 设置 
	 * @param parMenuId
	 */
	public void setParMenuId(String parMenuId) {
		this.parMenuId = parMenuId;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getMenuTyp() {
		return menuTyp;
	}

	/**
	 * 设置 
	 * @param menuTyp
	 */
	public void setMenuTyp(String menuTyp) {
		this.menuTyp = menuTyp;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置 
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getImg() {
		return img;
	}

	/**
	 * 设置 
	 * @param img
	 */
	public void setImg(String img) {
		this.img = img;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getMenuTargt() {
		return menuTargt;
	}

	/**
	 * 设置 
	 * @param menuTargt
	 */
	public void setMenuTargt(String menuTargt) {
		this.menuTargt = menuTargt;
	}

    
    /**
	 * 获取 
	 * @return Integer
	 */
	public Integer getMenuLayer() {
		return menuLayer;
	}

	/**
	 * 设置 
	 * @param menuLayer
	 */
	public void setMenuLayer(Integer menuLayer) {
		this.menuLayer = menuLayer;
	}

    
    /**
	 * 获取 
	 * @return Integer
	 */
	public Integer getMenuOrder() {
		return menuOrder;
	}

	/**
	 * 设置 
	 * @param menuOrder
	 */
	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getMenuStat() {
		return menuStat;
	}

	/**
	 * 设置 
	 * @param menuStat
	 */
	public void setMenuStat(String menuStat) {
		this.menuStat = menuStat;
	}

    
    /**
	 * 获取 
	 * @return String
	 */
	public String getSysId() {
		return sysId;
	}

	/**
	 * 设置 
	 * @param sysId
	 */
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

    
    /**
	 * 获取 
	 * @return Date
	 */
	public Date getInstDate() {
		return instDate;
	}

	/**
	 * 设置 
	 * @param instDate
	 */
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

    
    /**
	 * 获取 
	 * @return Date
	 */
	public Date getUpdtDate() {
		return updtDate;
	}

	/**
	 * 设置 
	 * @param updtDate
	 */
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

}