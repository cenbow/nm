package com.hs.loan.cust.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.cust.entity.AppCustEstateInfo;
import com.hs.loan.cust.itface.ICustExtraInfoMapper;

/**
 * APP_客户房产信息 mapper
 * @author autocreate
 * @create 2015-10-26
 */
@MyBatisRepository
public interface  AppCustEstateInfoMapper extends ICustExtraInfoMapper<AppCustEstateInfo>{

	//获取有效时间段的 有效的 客户房产信息
	List<AppCustEstateInfo> getCustEstateInfoLstByDate(Map<String, Object> param);
	
}