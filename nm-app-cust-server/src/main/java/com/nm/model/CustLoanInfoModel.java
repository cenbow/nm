package com.nm.model;

import com.hs.loan.busi.dto.LoanGoodsDto;
import com.hs.loan.prod.dto.PubProdDto;
import com.hs.loan.prod.dto.PubProdFeeDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/5/16.
 */
public class CustLoanInfoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String loanNo;
    private String custNo;
    private String prodNo;
    private BigDecimal loanAmt;
    private Integer instNum;
    private String isWilling;
    private String custSource;
    private String remark;
    private String fileNo ;
    private String loanRemark;
    private String mthRepayDate;
    private BigDecimal mthRepayAmt;
    private String branchName;
    private String branchNo;
    private String staffNo;
    private String staffName;
    private String custType;
    private BigDecimal fstPayAmt;
    private PubProdDto pubProdDto;
    private LoanGoodsDto goodsDto;
    private List feeList;
    private List<PubProdFeeDto> pubProdFeeList;
    private List<Map<String, Object>> allFeelList;

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public BigDecimal getFstPayAmt() {
        return fstPayAmt;
    }

    public void setFstPayAmt(BigDecimal fstPayAmt) {
        this.fstPayAmt = fstPayAmt;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public List<Map<String, Object>> getAllFeelList() {
        return allFeelList;
    }

    public void setAllFeelList(List<Map<String, Object>> allFeelList) {
        this.allFeelList = allFeelList;
    }

    public List<PubProdFeeDto> getPubProdFeeList() {
        return pubProdFeeList;
    }

    public void setPubProdFeeList(List<PubProdFeeDto> pubProdFeeList) {
        this.pubProdFeeList = pubProdFeeList;
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    private List<InstNumInfoModel> instNumInfoList;

    public List<InstNumInfoModel> getInstNumInfoList() {
        return instNumInfoList;
    }

    public void setInstNumInfoList(List<InstNumInfoModel> instNumInfoList) {
        this.instNumInfoList = instNumInfoList;
    }

    public List getFeeList() {
        return feeList;
    }

    public void setFeeList(List feeList) {
        this.feeList = feeList;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public BigDecimal getMthRepayAmt() {
        return mthRepayAmt;
    }

    public void setMthRepayAmt(BigDecimal mthRepayAmt) {
        this.mthRepayAmt = mthRepayAmt;
    }

    private List<LoanApprBookModel> loanApprBookModelList;

    public String getMthRepayDate() {
        return mthRepayDate;
    }

    public void setMthRepayDate(String mthRepayDate) {
        this.mthRepayDate = mthRepayDate;
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

    public String getProdNo() {
        return prodNo;
    }

    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }

    public BigDecimal getLoanAmt() {
        return loanAmt;
    }

    public void setLoanAmt(BigDecimal loanAmt) {
        this.loanAmt = loanAmt;
    }

    public Integer getInstNum() {
        return instNum;
    }

    public void setInstNum(Integer instNum) {
        this.instNum = instNum;
    }

    public String getIsWilling() {
        return isWilling;
    }

    public void setIsWilling(String isWilling) {
        this.isWilling = isWilling;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public PubProdDto getPubProdDto() {
        return pubProdDto;
    }

    public void setPubProdDto(PubProdDto pubProdDto) {
        this.pubProdDto = pubProdDto;
    }

    public LoanGoodsDto getGoodsDto() {
        return goodsDto;
    }

    public void setGoodsDto(LoanGoodsDto goodsDto) {
        this.goodsDto = goodsDto;
    }

    public List<LoanApprBookModel> getLoanApprBookModelList() {
        return loanApprBookModelList;
    }

    public void setLoanApprBookModelList(List<LoanApprBookModel> loanApprBookModelList) {
        this.loanApprBookModelList = loanApprBookModelList;
    }
}
