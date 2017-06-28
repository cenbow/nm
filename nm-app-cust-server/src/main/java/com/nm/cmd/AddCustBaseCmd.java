package com.nm.cmd;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/5/10.
 */
public class AddCustBaseCmd implements Serializable{
    private static final long serialVersionUID = 1L;

    private CustInfoCmd custinfocmd;
    private LoanAcctInCmd loanacctincmd;
    private CustLiveInfoCmd custliveinfocmd;

    private CustContctCmd custContctCmd;
    private CustBankAcctCmd custBankAcctCmd;
    private String saleSource;
    private String staffNo;

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getSaleSource() {
        return saleSource;
    }

    public void setSaleSource(String saleSource) {
        this.saleSource = saleSource;
    }

    public CustInfoCmd getCustinfocmd() {
        return custinfocmd;
    }

    public void setCustinfocmd(CustInfoCmd custinfocmd) {
        this.custinfocmd = custinfocmd;
    }

    public LoanAcctInCmd getLoanacctincmd() {
        return loanacctincmd;
    }

    public void setLoanacctincmd(LoanAcctInCmd loanacctincmd) {
        this.loanacctincmd = loanacctincmd;
    }

    public CustLiveInfoCmd getCustliveinfocmd() {
        return custliveinfocmd;
    }

    public void setCustliveinfocmd(CustLiveInfoCmd custliveinfocmd) {
        this.custliveinfocmd = custliveinfocmd;
    }

    public CustContctCmd getCustContctCmd() {
        return custContctCmd;
    }

    public void setCustContctCmd(CustContctCmd custContctCmd) {
        this.custContctCmd = custContctCmd;
    }

    public CustBankAcctCmd getCustBankAcctCmd() {
        return custBankAcctCmd;
    }

    public void setCustBankAcctCmd(CustBankAcctCmd custBankAcctCmd) {
        this.custBankAcctCmd = custBankAcctCmd;
    }
}
