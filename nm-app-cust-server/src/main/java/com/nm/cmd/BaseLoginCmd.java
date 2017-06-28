package com.nm.cmd;

import java.io.Serializable;

public class BaseLoginCmd implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String remoteApi;
    protected String clientIp;
    protected String clientUserAgent;

    public String getRemoteApi() {
        return remoteApi;
    }

    public void setRemoteApi(String remoteApi) {
        this.remoteApi = remoteApi;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getClientUserAgent() {
        return clientUserAgent;
    }

    public void setClientUserAgent(String clientUserAgent) {
        this.clientUserAgent = clientUserAgent;
    }


}
