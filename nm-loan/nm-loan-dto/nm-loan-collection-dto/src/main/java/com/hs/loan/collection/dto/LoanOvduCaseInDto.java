package com.hs.loan.collection.dto;


import java.util.Date;

import java.io.Serializable;

/**
 * PL_逾期案件 对象
 * @author autocreate
 * @create 2015-12-02
 */
public class LoanOvduCaseInDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 证件号码 */
  	private String certNo ; 
    
    /*** 客户名称 */
  	private String custName ; 
     
    /*** 逾期开始日期-起 */
  	private String bgnDateStart ; 
  	
  	 /*** 逾期开始日期 -至*/
  	private String bgnDateEnd ; 
    
    /*** 处理状态 */
  	private String dealStat ; 
    
    /*** 处理人姓名 */
  	private String dealUserName ; 
    
    /*** 处理机构 */
  	private String dealOrg ; 
    
    /*** 处理机构名称 */
  	private String dealOrgName ; 
    /***商户所在省 */
  	private String branchProvNo;
    /***商户所在市*/
  	private String branchCityNo;
	/**渠道****/
  	private String chanleNo;
  	/**渠道名称****/
  	private String chanleName;

    //构造函数
    public LoanOvduCaseInDto(){}


	public String getLoanNo() {
		return loanNo;
	}


	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}


	public String getCertNo() {
		return certNo;
	}


	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}


	public String getCustName() {
		return custName;
	}


	public void setCustName(String custName) {
		this.custName = custName;
	}


	public String getBgnDateStart() {
		return bgnDateStart;
	}


	public void setBgnDateStart(String bgnDateStart) {
		this.bgnDateStart = bgnDateStart;
	}


	public String getBgnDateEnd() {
		return bgnDateEnd;
	}


	public void setBgnDateEnd(String bgnDateEnd) {
		this.bgnDateEnd = bgnDateEnd;
	}


	public String getDealStat() {
		return dealStat;
	}


	public void setDealStat(String dealStat) {
		this.dealStat = dealStat;
	}


	public String getDealUserName() {
		return dealUserName;
	}


	public void setDealUserName(String dealUserName) {
		this.dealUserName = dealUserName;
	}


	public String getDealOrg() {
		return dealOrg;
	}


	public void setDealOrg(String dealOrg) {
		this.dealOrg = dealOrg;
	}


	public String getDealOrgName() {
		return dealOrgName;
	}


	public void setDealOrgName(String dealOrgName) {
		this.dealOrgName = dealOrgName;
	}


	 

	public String getChanleNo() {
		return chanleNo;
	}


	public void setChanleNo(String chanleNo) {
		this.chanleNo = chanleNo;
	}


	public String getChanleName() {
		return chanleName;
	}


	public void setChanleName(String chanleName) {
		this.chanleName = chanleName;
	}


	public String getBranchProvNo() {
		return branchProvNo;
	}


	public void setBranchProvNo(String branchProvNo) {
		this.branchProvNo = branchProvNo;
	}


	public String getBranchCityNo() {
		return branchCityNo;
	}


	public void setBranchCityNo(String branchCityNo) {
		this.branchCityNo = branchCityNo;
	}
 

}