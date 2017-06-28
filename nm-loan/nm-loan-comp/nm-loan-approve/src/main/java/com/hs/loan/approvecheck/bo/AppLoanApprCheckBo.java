package com.hs.loan.approvecheck.bo;


import java.util.Date;

import com.hs.loan.approvecheck.entity.AppLoanApprCheck;

/**
 * APP_分期案件复核 对象
 * @author autocreate
 * @create 2016-11-26
 */
public class AppLoanApprCheckBo extends AppLoanApprCheck
{
	private static final long serialVersionUID = 1L;
    
  	private Integer instNum;

  	private Date manuStartDate;
  	
  	private Date instDate;
  	
  	private String codeRmark;
  	
  	//构造函数
  	public AppLoanApprCheckBo(){}
  	
  	//getter和setter方法
    public Integer getInstNum() {
		return instNum;
	}

	public void setInstNum(Integer instNum) {
		this.instNum = instNum;
	}
    
	public Date getManuStartDate() {
		return manuStartDate;
	}

	public void setManuStartDate(Date manuStartDate) {
		this.manuStartDate = manuStartDate;
	}

	public Date getInstDate() {
		return instDate;
	}

	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}

	public String getCodeRmark() {
		return codeRmark;
	}

	public void setCodeRmark(String codeRmark) {
		this.codeRmark = codeRmark;
	}
}