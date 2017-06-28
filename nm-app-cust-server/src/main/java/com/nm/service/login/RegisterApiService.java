package com.nm.service.login;

import java.util.Map;

/**
 * Created by lenovo on 2017/5/9.
 */
public interface RegisterApiService {

    String send(String mobileNo, String type, String smsType);

    String addUser(Map<String, Object> map);

    String alterPwd(Map<String, Object> map);

    void checkPhoneKey(String checkCode, String phoneNo);
    
    String updatePassWord(Map<String, Object> map);
}
