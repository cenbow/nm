package com.hs.loan.cust.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.cust.entity.AppCustLiveInfo;
import com.hs.loan.cust.itface.ICustExtraInfoMapper;

/**
 * APP_客户居住信息 mapper
 * @author autocreate
 * @create 2015-10-26
 */
@MyBatisRepository
public interface  AppCustLiveInfoMapper extends ICustExtraInfoMapper<AppCustLiveInfo>{

	//通过客户号 获取客户当前最新的居住信息
	AppCustLiveInfo getCurrentLiveInfoByCustNo(String custNo);

}