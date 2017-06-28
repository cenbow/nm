package com.nm.cmd;

import java.io.Serializable;

public class LoginV2Cmd extends BaseLoginClientCmd implements Serializable {
    private static final long serialVersionUID = 1L;
    private String phone;
    private String password;
    private String phoneKey;//手机唯一编码
    private String phoneCode;//手机验证码

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneKey() {
        return phoneKey;
    }

    public void setPhoneKey(String phoneKey) {
        this.phoneKey = phoneKey;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }
}
