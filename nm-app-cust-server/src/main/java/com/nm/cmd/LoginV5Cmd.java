package com.nm.cmd;

import java.io.Serializable;

/**
 * @author hemf
 *         一次性免登陆
 */
public class LoginV5Cmd extends BaseLoginClientCmd implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 一次性免登陆令牌
     */
    private String disposableFreeLoginToken;

    public String getDisposableFreeLoginToken() {
        return disposableFreeLoginToken;
    }

    public void setDisposableFreeLoginToken(String disposableFreeLoginToken) {
        this.disposableFreeLoginToken = disposableFreeLoginToken;
    }


}
