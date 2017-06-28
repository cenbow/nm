package com.nm.cmd;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/5/19.
 */
public class Iidentity implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean validity;
    private String photo_id;
    private String reason;

    public boolean isValidity() {
        return validity;
    }

    public void setValidity(boolean validity) {
        this.validity = validity;
    }

    public String getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(String photo_id) {
        this.photo_id = photo_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
