package com.nm.mapper.login;

import com.nm.mybatis.annotation.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2016/11/24.
 */

@MyBatisRepository
public interface RegisterApiMapper {
    /*查询电话号码是否已经注册*/
    Integer queryMobileNo(@Param(value = "mobileNo") String mobileNo);

    /*查询是否是存量客户*/
    String queryIsExist(@Param("mobileNo") String mobileNo);

    /*新增用户*/
    Integer addUser(Map<String, Object> map);

    /*新增客户信息表*/
    Integer addCust(Map<String, Object> map);

    /*修改密码*/
    int updatePassWord(Map<String, Object> map);

    /*登陆日志记录登陆地址*/
    void loginToLogs(HashMap<String, String> map);

}
