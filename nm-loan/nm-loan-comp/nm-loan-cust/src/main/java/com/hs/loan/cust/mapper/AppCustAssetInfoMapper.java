package com.hs.loan.cust.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.cust.entity.AppCustAssetInfo;
import com.hs.loan.cust.itface.ICustExtraInfoMapper;

/**
 * APP_客户资产信息 mapper
 * @author autocreate
 * @create 2015-10-26
 */
@MyBatisRepository
public interface  AppCustAssetInfoMapper extends ICustExtraInfoMapper<AppCustAssetInfo>{

	//获取客户 有效时间段的有效的 资产信息
	List<AppCustAssetInfo> getCustAssetInfoLstByDate(Map<String, Object> param);
	
}