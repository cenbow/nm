package com.nm.cmd;

import java.io.Serializable;

public class BaseLoginClientCmd extends BaseLoginCmd implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String clientKey;

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }
}
