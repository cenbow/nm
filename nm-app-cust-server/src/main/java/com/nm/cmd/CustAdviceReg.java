package com.nm.cmd;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "app_cust_advice_reg")
public class CustAdviceReg implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "PHONE_NO")
    private String phoneNo;

    @Column(name = "ADVICE_CONTENT")
    private String adviceContent;

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

    @Column(name = "INST_DATE")
    private Date instDate;

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
     * @return PHONE_NO
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * @param phoneNo
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * @return ADVICE_CONTENT
     */
    public String getAdviceContent() {
        return adviceContent;
    }

    /**
     * @param adviceContent
     */
    public void setAdviceContent(String adviceContent) {
        this.adviceContent = adviceContent;
    }

    /**
     * @return EMAIL_ADDRESS
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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
}