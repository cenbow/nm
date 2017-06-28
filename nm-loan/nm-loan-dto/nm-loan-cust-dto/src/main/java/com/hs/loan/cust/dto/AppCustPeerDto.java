package com.hs.loan.cust.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lenovo on 2017/2/20.
 */
public class AppCustPeerDto implements Serializable{
    private String id;

    private String custNo;

    private String contactName;

    private String contactRel;

    private String contactTel;

    private Date instDate;

    private Date updtDate;

    private String status;

    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactRel() {
        return contactRel;
    }

    public void setContactRel(String contactRel) {
        this.contactRel = contactRel;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public Date getInstDate() {
        return instDate;
    }

    public void setInstDate(Date instDate) {
        this.instDate = instDate;
    }

    public Date getUpdtDate() {
        return updtDate;
    }

    public void setUpdtDate(Date updtDate) {
        this.updtDate = updtDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
