package com.nm.mapper.usercenter;

import java.util.Map;

import com.nm.mybatis.annotation.MyBatisRepository;

@MyBatisRepository
public interface CustAdviceRegMapper {
	
	
	 /*新增意见反馈*/
    Integer addAdviceReg(Map<String, Object> map);
	
}