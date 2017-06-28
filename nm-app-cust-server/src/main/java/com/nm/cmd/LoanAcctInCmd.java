package com.nm.cmd;

import com.hs.loan.busi.dto.LoanGoodsDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LoanAcctInCmd implements Serializable {
	private static final long serialVersionUID = 1L;
	/*** 机构号 */
  	private String orgNo ;
  	
  	/*** 机构名称 */
  	private String orgName ;
  	
    /*** 分期编号 */
  	private String loanNo ;
    
    /*** 客户编号 */
  	private String custNo ;
  	/*** 客户编号 */
  	private String custName ;
  	
  	/*** 产品编号 */
  	private String prodNo ;
  	/*** 客户类型 */
  	private String custType ;
  	/*** 网点编号 */
  	private String branchNo ;
  	/*** 网点编号 */
  	private String branchName ;
    
    /*** 首付金额 */
  	private java.math.BigDecimal fstPayAmt ; 
    
    /*** 分期本金 */
  	private java.math.BigDecimal loanAmt ; 
    
    /*** 分期类型 */
  	private String loanTyp ;
    
    /*** 分期期数 */
  	private Integer instNum ;
    
    /*** 办理所在省 */
  	private String applyProv ;
    
    /*** 办理所在区/县 */
  	private String applyArea ;
    
    /*** 办理所在市 */
  	private String applyCity ;

  	 /*** 档案编号 */
  	private String fileNo ;
  	
	/*** 备注*/
  	private String loanRemark;
  	
	/*** 信托标志*/
  	private String entrFlag;
  
  	
  	/**客户选中的费用项**/
  	private String strSeleFees;
  	private List<LoanFeeCmd> selectFees;
  	
  	private List<LoanGoodsDto> goodsDto = new ArrayList<>();
  	private LoanGoodsDto goodDto;
  	private String saleChanl;

	
	public String getOrgNo() {
		return orgNo;
	}
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
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
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getProdNo() {
		return prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getBranchNo() {
		return branchNo;
	}
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public java.math.BigDecimal getFstPayAmt() {
		return fstPayAmt;
	}
	public void setFstPayAmt(java.math.BigDecimal fstPayAmt) {
		this.fstPayAmt = fstPayAmt;
	}
	public java.math.BigDecimal getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(java.math.BigDecimal loanAmt) {
		this.loanAmt = loanAmt;
	}
	public String getLoanTyp() {
		return loanTyp;
	}
	public void setLoanTyp(String loanTyp) {
		this.loanTyp = loanTyp;
	}
	public Integer getInstNum() {
		return instNum;
	}
	public void setInstNum(Integer instNum) {
		this.instNum = instNum;
	}
	public String getApplyProv() {
		return applyProv;
	}
	public void setApplyProv(String applyProv) {
		this.applyProv = applyProv;
	}
	public String getApplyArea() {
		return applyArea;
	}
	public void setApplyArea(String applyArea) {
		this.applyArea = applyArea;
	}
	public String getApplyCity() {
		return applyCity;
	}
	public void setApplyCity(String applyCity) {
		this.applyCity = applyCity;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public String getLoanRemark() {
		return loanRemark;
	}
	public void setLoanRemark(String loanRemark) {
		this.loanRemark = loanRemark;
	}
	public String getEntrFlag() {
		return entrFlag;
	}
	public void setEntrFlag(String entrFlag) {
		this.entrFlag = entrFlag;
	}
	public String getStrSeleFees() {
		return strSeleFees;
	}
	public void setStrSeleFees(String strSeleFees) {
		this.strSeleFees = strSeleFees;
	}
	public List<LoanFeeCmd> getSelectFees() {
		return selectFees;
	}
	public void setSelectFees(List<LoanFeeCmd> selectFees) {
		this.selectFees = selectFees;
	}
	public List<LoanGoodsDto> getGoodsDto() {
		goodsDto.add(goodDto);
		return goodsDto;
	}
	public void setGoodsDto(List<LoanGoodsDto> goodsDto) {
		this.goodsDto = goodsDto;
	}
	public LoanGoodsDto getGoodDto() {
		return goodDto;
	}
	public void setGoodDto(LoanGoodsDto goodDto) {
		this.goodDto = goodDto;
	}
	public String getSaleChanl() {
		return saleChanl;
	}
	public void setSaleChanl(String saleChanl) {
		this.saleChanl = saleChanl;
	}

}
