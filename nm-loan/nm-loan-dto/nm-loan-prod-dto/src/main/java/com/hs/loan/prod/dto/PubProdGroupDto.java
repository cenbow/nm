package com.hs.loan.prod.dto;


import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * PUB_产品与销售群关系 对象
 * @author autocreate
 * @create 2015-10-21
 */
public class PubProdGroupDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private String id;
    /*** 销售群/组编号 */
  	private String groupNo ; 
    
    /*** 产品编号 */
  	@NotBlank(message="产品编号不能为空")
  	@Size(max=40,message="产品编号超长")
  	private String prodNo ; 
  	/*** 销售群/组名称 */
  	@NotBlank(message="销售群/组名称不能为空")
  	@Size(max=40,message="销售群/组名称超长")
  	private String groupName ; 
  	
  	 /***属性  销售群/组 */
  	private String property ;

    //构造函数
    public PubProdGroupDto(){}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public String getProdNo() {
		return prodNo;
	}

	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}