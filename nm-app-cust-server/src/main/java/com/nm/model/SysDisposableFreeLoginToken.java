package com.nm.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "sys_disposable_free_login_token")
public class SysDisposableFreeLoginToken implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "LOGIN_TOKEN")
    private String loginToken;

    @Column(name = "STAFF_NO")
    private String staffNo;
    
    @Column(name = "CUST_NO")
    private String custNo;

    @Column(name = "CLIENT_KEY")
    private String clientKey;

    @Column(name = "ACCESS_TOKEN")
    private String accessToken;

    @Column(name = "CLIENT_IP")
    private String clientIp;

    @Column(name = "INST_DATE")
    private Date instDate;

    @Column(name = "UPDT_DATE")
    private Date updtDate;

    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return LOGIN_TOKEN
     */
    public String getLoginToken() {
        return loginToken;
    }

    /**
     * @param loginToken
     */
    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    /**
     * @return STAFF_NO
     */
    public String getStaffNo() {
        return staffNo;
    }

    /**
     * @param staffNo
     */
    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	/**
     * @return CLIENT_KEY
     */
    public String getClientKey() {
        return clientKey;
    }

    /**
     * @param clientKey
     */
    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    /**
     * @return ACCESS_TOKEN
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @param accessToken
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * @return CLIENT_IP
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * @param clientIp
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * @return INST_DATE
     */
    public Date getInstDate() {
        return instDate;
    }

    /**
     * @param instDate
     */
    public void setInstDate(Date instDate) {
        this.instDate = instDate;
    }

    /**
     * @return UPDT_DATE
     */
    public Date getUpdtDate() {
        return updtDate;
    }

    /**
     * @param updtDate
     */
    public void setUpdtDate(Date updtDate) {
        this.updtDate = updtDate;
    }
}