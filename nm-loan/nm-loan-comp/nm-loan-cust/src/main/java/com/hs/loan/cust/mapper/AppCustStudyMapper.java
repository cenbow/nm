package com.hs.loan.cust.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.cust.entity.AppCustStudy;
import com.hs.loan.cust.itface.ICustExtraInfoMapper;

/**
 * APP_客户学校信息 mapper
 * @author autocreate
 * @create 2015-10-26
 */
@MyBatisRepository
public interface  AppCustStudyMapper extends ICustExtraInfoMapper<AppCustStudy>{

	List<AppCustStudy> getCustStudyListByDate(Map<String, Object> param);

	
}