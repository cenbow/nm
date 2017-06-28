package com.nm.cmd;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/5/19.
 */
public class OCRretrunCmd implements Serializable {
    private static final long serialVersionUID = 1L;

    private String request_id;
    private String status;
    private String confidence;
    private Iidentity iidentity;
    private Selfie selfie;

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

    public Iidentity getIidentity() {
        return iidentity;
    }

    public void setIidentity(Iidentity iidentity) {
        this.iidentity = iidentity;
    }

    public Selfie getSelfie() {
        return selfie;
    }

    public void setSelfie(Selfie selfie) {
        this.selfie = selfie;
    }
}
