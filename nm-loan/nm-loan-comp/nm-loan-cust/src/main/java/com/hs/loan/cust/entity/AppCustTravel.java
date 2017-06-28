package com.hs.loan.cust.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AppCustTravel implements Serializable{
    private String id;

    private String custNo;

    private String tourRoutName;

    private BigDecimal tourRoutPric;

    private String srcPlae;

    private String destPlae;

    private Integer travelDays;

    private Date instDate;

    private Date updtDate;

    private String status;

    private String ownIsTravel;

    private String travelprodNo;

    private  Integer travelNum;

    private Date travelDate;

    public Integer getTravelNum() {
        return travelNum;
    }

    public void setTravelNum(Integer travelNum) {
        this.travelNum = travelNum;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    public String getTravelprodNo() {
        return travelprodNo;
    }

    public void setTravelprodNo(String travelprodNo) {
        this.travelprodNo = travelprodNo;
    }

    public String getOwnIsTravel() {
        return ownIsTravel;
    }

    public void setOwnIsTravel(String ownIsTravel) {
        this.ownIsTravel = ownIsTravel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo == null ? null : custNo.trim();
    }

    public String getTourRoutName() {
        return tourRoutName;
    }

    public void setTourRoutName(String tourRoutName) {
        this.tourRoutName = tourRoutName == null ? null : tourRoutName.trim();
    }

    public BigDecimal getTourRoutPric() {
        return tourRoutPric;
    }

    public void setTourRoutPric(BigDecimal tourRoutPric) {
        this.tourRoutPric = tourRoutPric;
    }

    public String getSrcPlae() {
        return srcPlae;
    }

    public void setSrcPlae(String srcPlae) {
        this.srcPlae = srcPlae == null ? null : srcPlae.trim();
    }

    public String getDestPlae() {
        return destPlae;
    }

    public void setDestPlae(String destPlae) {
        this.destPlae = destPlae == null ? null : destPlae.trim();
    }

    public Integer getTravelDays() {
        return travelDays;
    }

    public void setTravelDays(Integer travelDays) {
        this.travelDays = travelDays;
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
        this.status = status == null ? null : status.trim();
    }
}