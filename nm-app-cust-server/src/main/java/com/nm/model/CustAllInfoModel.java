package com.nm.model;

import com.hs.loan.cust.dto.CustContctInfoDto;

import java.io.Serializable;

public class CustAllInfoModel implements Serializable {
    private static final long serialVersionUID = 1L;
    /*private CustContctInfoModel custcontctinfomodel;
    private CustStudyInfoModel custstudyinfomodel;
    private CustWorkInfoModel custworkinfomodel;*/
    private CustBankAcctModel custbankacctmodel;
    private CustInfoModel custinfomodel;
    private CustLiveInfoModel custliveinfomodel;
    private CustContctInfoDto custContctInfoDto;
    //private LoanAcctOutDto loanacctoutdto;
    private String custNo;

    public CustContctInfoDto getCustContctInfoDto() {
        return custContctInfoDto;
    }

    public void setCustContctInfoDto(CustContctInfoDto custContctInfoDto) {
        this.custContctInfoDto = custContctInfoDto;
    }
    /*public LoanAcctOutDto getLoanacctoutdto() {
        return loanacctoutdto;
    }

    public void setLoanacctoutdto(LoanAcctOutDto loanacctoutdto) {
        this.loanacctoutdto = loanacctoutdto;
    }*/

   /* public CustContctInfoModel getCustcontctinfomodel() {
        return custcontctinfomodel;
    }

    public void setCustcontctinfomodel(CustContctInfoModel custcontctinfomodel) {
        this.custcontctinfomodel = custcontctinfomodel;
    }

    public CustStudyInfoModel getCuststudyinfomodel() {
        return custstudyinfomodel;
    }

    public void setCuststudyinfomodel(CustStudyInfoModel custstudyinfomodel) {
        this.custstudyinfomodel = custstudyinfomodel;
    }

    public CustWorkInfoModel getCustworkinfomodel() {
        return custworkinfomodel;
    }

    public void setCustworkinfomodel(CustWorkInfoModel custworkinfomodel) {
        this.custworkinfomodel = custworkinfomodel;
    }*/

    /*public CustPropertyModel getCustpropertymodel() {
        return custpropertymodel;
    }
    public void setCustpropertymodel(CustPropertyModel custpropertymodel) {
        this.custpropertymodel = custpropertymodel;
    }
    */
    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public CustBankAcctModel getCustbankacctmodel() {
        return custbankacctmodel;
    }

    public void setCustbankacctmodel(CustBankAcctModel custbankacctmodel) {
        this.custbankacctmodel = custbankacctmodel;
    }

    public CustInfoModel getCustinfomodel() {
        return custinfomodel;
    }

    public void setCustinfomodel(CustInfoModel custinfomodel) {
        this.custinfomodel = custinfomodel;
    }

    public CustLiveInfoModel getCustliveinfomodel() {
        return custliveinfomodel;
    }

    public void setCustliveinfomodel(CustLiveInfoModel custliveinfomodel) {
        this.custliveinfomodel = custliveinfomodel;
    }

}
