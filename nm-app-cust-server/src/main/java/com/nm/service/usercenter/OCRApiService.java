package com.nm.service.usercenter;

import org.apache.http.entity.mime.MultipartEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by lenovo on 2017/5/19.
 */
public interface OCRApiService {
    void CertNoCheck(String cardNum,String cardName,String phone,MultipartFile file);
}
