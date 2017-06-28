package com.nm.service.login;

import com.nm.cmd.LoginV2Cmd;

import java.util.Map;

public interface LoginApiService {

    /**
     * 登陆(app登陆)
     *
     * @param bean 登陆信息实体
     * @return 访问令牌(accessToken)
     */
    Map<String, String> loginV2(LoginV2Cmd bean);


}
