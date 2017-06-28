package com.hs.loan.cust.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.cust.entity.AppCustWork;
import com.hs.loan.cust.itface.ICustExtraInfoMapper;

/**
 * APP_客户工作信息 mapper
 * @author autocreate
 * @create 2015-10-26
 */
@MyBatisRepository
public interface  AppCustWorkMapper extends ICustExtraInfoMapper<AppCustWork>{

	//获取客户有效时间段里的 有效的 客户工作信息
	List<AppCustWork> getCustWorkLstByDate(Map<String, Object> param);
	
}