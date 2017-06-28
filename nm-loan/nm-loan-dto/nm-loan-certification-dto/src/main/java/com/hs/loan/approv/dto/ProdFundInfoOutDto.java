package com.hs.loan.approv.dto;


import java.io.Serializable;

/**
 * APP_分期资金匹配 对象
 * @author autocreate
 * @create 2015-11-23
 */
public class ProdFundInfoOutDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    /*** 渠道号 */
  	private String chanNo ; 
    
    /*** 渠道名称 */
  	private String chanName ; 

    //构造函数
    public ProdFundInfoOutDto(){}
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