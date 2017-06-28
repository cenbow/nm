package com.hs.loan.approvecheck.bo;


import com.hs.loan.approvecheck.entity.AppLoanApprCheckH;

/**
 * APP_分期案件复核历史 对象
 * @author autocreate
 * @create 2016-11-26
 */
public class AppLoanApprCheckHBo extends AppLoanApprCheckH
{
	private static final long serialVersionUID = 1L;
	
  	private Integer instNum;
  	
  	private String codeRmark;

	//构造函数
    public AppLoanApprCheckHBo(){}

    //getter和setter方法
    public Integer getInstNum() {
		return instNum;
	}

	public void setInstNum(Integer instNum) {
		this.instNum = instNum;
	}

	public String getCodeRmark() {
		return codeRmark;
	}

	public void setCodeRmark(String codeRmark) {
		this.codeRmark = codeRmark;
	}
}