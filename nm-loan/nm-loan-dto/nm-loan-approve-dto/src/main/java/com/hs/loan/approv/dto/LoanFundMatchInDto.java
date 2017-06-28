package com.hs.loan.approv.dto;


import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * APP_分期资金匹配 对象
 * @author autocreate
 * @create 2015-11-23
 */
public class LoanFundMatchInDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    /*** 分期编号 */
	@NotBlank(message="分期编号不能为空")
	@Size(max=40,message="分期编号超长")
  	private String loanNo ; 
    
    /*** 客户编号 */
	@NotBlank(message="客户编号不能为空")
	@Size(max=40,message="客户编号超长")
  	private String custNo ;
  	
    /*** 渠道号 */
	@NotBlank(message="渠道号不能为空")
	@Size(max=10,message="渠道号超长")
  	private String chanNo ; 
    
    /*** 渠道名称 */
	@NotBlank(message="渠道名称不能为空")
	@Size(max=80,message="渠道名称超长")
  	private String chanName ; 

    //构造函数
    public LoanFundMatchInDto(){}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getChanNo() {
		return chanNo;
	}

	public void setChanNo(String chanNo) {
		this.chanNo = chanNo;
	}

	public String getChanName() {
		return chanName;
	}

	public void setChanName(String chanName) {
		this.chanName = chanName;
	}

}