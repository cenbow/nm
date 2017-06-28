package com.hs.loan.sale.bo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by lenovo on 2016/4/26.
 */
public class HandFundMatchBo {
    //贷款编号
    private String loanNo;
    //匹配人编号
    private String matchPsn;
    //匹配人姓名
    private String matchName;
    //渠道号
    private String chanNo;
    //渠道名称
    private String chanName;
    //贷款金额
    private BigDecimal loanAmt;
    //匹配id
    private String matchId;
    //进件时间
    private Date instDate;
    //匹配时间
    private Date matchDate;
    //客户编号
    private String custNo;
    //客户名称
    private String custName;

    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo;
    }

    public String getMatchPsn() {
        return matchPsn;
    }

    public void setMatchPsn(String matchPsn) {
        this.matchPsn = matchPsn;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
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

    public BigDecimal getLoanAmt() {
        return loanAmt;
    }

    public void setLoanAmt(BigDecimal loanAmt) {
        this.loanAmt = loanAmt;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public Date getInstDate() {
        return instDate;
    }

    public void setInstDate(Date instDate) {
        this.instDate = instDate;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
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
}
