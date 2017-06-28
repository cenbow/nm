package com.nm.mapper.usercenter;

import com.nm.mybatis.annotation.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

/**
 * Created by lenovo on 2017/5/12.
 */
@MyBatisRepository
public interface BigDataApiMapper {

    Integer deleteDmOrderInfo(String loanNo);

    void isAuthFlag(@Param("phone") String phone, @Param("authFlag")String authFlag);
}
