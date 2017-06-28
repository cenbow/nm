package com.hs.loan.busi.dto;


import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 资方信息 对象
 * @author jqiu
 * @create 2015-10-27
 */
public class LoanFundChanDto implements Serializable{
	private static final long serialVersionUID = 1L;
    
	/*** 渠道编号 */
  	private String chanNo ; 
    
    /*** 渠道名称 */
  	private String chanName ; 
    
    /*** 资方名称  */
  	private String compOrg ;

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

	public String getCompOrg() {
		return compOrg;
	}

	public void setCompOrg(String compOrg) {
		this.compOrg = compOrg;
	}
}