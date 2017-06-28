package com.nm.service.login;


/**
 * Created by lenovo on 2017/5/3.
 */
public interface EncryptSmgService {
    void sendMoblieCapthca(String mobleNo, String captchaType, int validSeconds, int length, String type);

    void sendMoblieCapthca(String moblNo, String captchaType, int validSeconds, String type);

    void validMobleCapthcha(String mobleNo, String captchaType, String captchaVal, boolean isLogout);

    void validMobleCapthcha(String mobleNo, String captchaType, String captchaVal);
}
