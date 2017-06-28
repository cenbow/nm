package com.hs.system.entity;


import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * PUB_销售群 对象
 * @author autocreate
 * @create 2015-10-15
 */
public class PubSaleCrowd implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
//	@NotBlank(message="销售群编号不能为空")
	@Size(max=40,message="销售群编号超长")
  	private String crowdNo ; 
    
    /*** 销售群名称 */
	@NotBlank(message="销售群名称不能为空")
	@Size(max=40,message="销售群名称超长")
  	private String crowdName ; 
    
    /*** 销售群规则 */
	@NotBlank(message="销售群规则不能为空")
  	private String crowdRule ; 
    
    /*** 销售群说明 */
	@Size(max=300,message="销售群说明超长")
  	private String crowdDesc ; 

    //构造函数
    public PubSaleCrowd(){}

	public String getCrowdNo() {
		return crowdNo;
	}

	public void setCrowdNo(String crowdNo) {
		this.crowdNo = crowdNo;
	}

	public String getCrowdName() {
		return crowdName;
	}

	public void setCrowdName(String crowdName) {
		this.crowdName = crowdName;
	}

	public String getCrowdRule() {
		return crowdRule;
	}

	public void setCrowdRule(String crowdRule) {
		this.crowdRule = crowdRule;
	}

	public String getCrowdDesc() {
		return crowdDesc;
	}

	public void setCrowdDesc(String crowdDesc) {
		this.crowdDesc = crowdDesc;
	}

    //getter和setter方法
    

}