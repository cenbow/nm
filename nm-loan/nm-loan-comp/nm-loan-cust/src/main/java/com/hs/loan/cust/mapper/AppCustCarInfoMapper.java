package com.hs.loan.cust.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.cust.entity.AppCustCarInfo;
import com.hs.loan.cust.itface.ICustExtraInfoMapper;

/**
 * APP_客户车辆信息 mapper
 * @author autocreate
 * @create 2015-10-26
 */
@MyBatisRepository
public interface  AppCustCarInfoMapper extends ICustExtraInfoMapper<AppCustCarInfo>{

	//获取有效时间段里的 有效的 客户车辆信息
	List<AppCustCarInfo> getCustCarInfoLstByDate(Map<String, Object> param);
	
}