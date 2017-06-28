package com.nm.core.comp.bean;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by lenovo on 2017/5/10.
 */
@Table(
        name = "app_user_info"
)
public class NewAppUserInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(
            name = "CERT_NO"
    )
    private String certNo;
    @Id
    @Column(
            name = "CUST_NO"
    )
    private String custNo;
    @Column(
            name = "USER_NAME"
    )
    private String userName;
    @Column(
            name = "MOBL_NO"
    )
    private String moblNo;
    @Column(
            name = "LOGIN_PWD"
    )
    private String loginPwd;
    @Column(
            name = "ACCT_PWD"
    )
    private String acctPwd;
    @Column(
            name = "MESG_PWD"
    )
    private String mesgPwd;
    @Column(
            name = "CUST_NAME"
    )
    private String custName;
    @Column(
            name = "CUST_SCORE"
    )
    private Integer custScore;
    @Column(
            name = "LOGIN_STAT"
    )
    private String loginStat;
    @Column(
            name = "VIP_FLAG"
    )
    private String vipFlag;
    @Column(
            name = "AUTH_FLAG"
    )
    private String authFlag;
    @Column(
            name = "REG_DATE"
    )
    private Date regDate;
    @Column(
            name = "LOGIN_FLAG"
    )
    private Date loginFlag;
    @Column(
            name = "LAST_LOGIN_FLAG"
    )
    private Date lastLoginFlag;
    @Column(
            name = "INST_DATE"
    )
    private String instDate;
    @Column(
            name = "UPDT_DATE"
    )
    private String updtDate;
    @Column(
            name = "DIFFERENCE"
    )
    private BigDecimal difference;
    @Column(
            name = "CREDIT_LIMIT"
    )
    private BigDecimal creditLimit;
    @Column(
            name = "OPEN_ID"
    )
    private String openId;
    @Column(
            name = "BUYERID"
    )
    private String buyerid;
    @Column(
            name = "SOURCE"
    )
    private String source;
    public NewAppUserInfo() {
    }


    public String getCustNo() {
        return this.custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMoblNo() {
        return this.moblNo;
    }

    public void setMoblNo(String moblNo) {
        this.moblNo = moblNo;
    }

    public String getLoginPwd() {
        return this.loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getAcctPwd() {
        return this.acctPwd;
    }

    public void setAcctPwd(String acctPwd) {
        this.acctPwd = acctPwd;
    }

    public String getMesgPwd() {
        return this.mesgPwd;
    }

    public void setMesgPwd(String mesgPwd) {
        this.mesgPwd = mesgPwd;
    }

    public String getCustName() {
        return this.custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public Integer getCustScore() {
        return this.custScore;
    }

    public void setCustScore(Integer custScore) {
        this.custScore = custScore;
    }

    public String getLoginStat() {
        return this.loginStat;
    }

    public void setLoginStat(String loginStat) {
        this.loginStat = loginStat;
    }

    public String getVipFlag() {
        return this.vipFlag;
    }

    public void setVipFlag(String vipFlag) {
        this.vipFlag = vipFlag;
    }

    public String getAuthFlag() {
        return this.authFlag;
    }

    public void setAuthFlag(String authFlag) {
        this.authFlag = authFlag;
    }

    public Date getRegDate() {
        return this.regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getLoginFlag() {
        return this.loginFlag;
    }

    public void setLoginFlag(Date loginFlag) {
        this.loginFlag = loginFlag;
    }

    public Date getLastLoginFlag() {
        return this.lastLoginFlag;
    }

    public void setLastLoginFlag(Date lastLoginFlag) {
        this.lastLoginFlag = lastLoginFlag;
    }

    public String getInstDate() {
        return this.instDate;
    }

    public void setInstDate(String instDate) {
        this.instDate = instDate;
    }

    public String getUpdtDate() {
        return this.updtDate;
    }

    public void setUpdtDate(String updtDate) {
        this.updtDate = updtDate;
    }

    public BigDecimal getDifference() {
        return this.difference;
    }

    public void setDifference(BigDecimal difference) {
        this.difference = difference;
    }

    public BigDecimal getCreditLimit() {
        return this.creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getOpenId() {
        return this.openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getBuyerid() {
        return this.buyerid;
    }

    public void setBuyerid(String buyerid) {
        this.buyerid = buyerid;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }
}
