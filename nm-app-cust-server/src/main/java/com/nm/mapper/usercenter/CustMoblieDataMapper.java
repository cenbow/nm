package com.nm.mapper.usercenter;

import com.nm.mybatis.annotation.MyBatisRepository;

import java.util.Map;

/**
 * Created by lenovo on 2017/5/24.
 */
@MyBatisRepository
public interface CustMoblieDataMapper {
    Integer addCustPhoneBook(Map map);
    Integer getCustPhoneBook(Map map);
    Integer updateCustPhoneBook(Map map);
}
