package com.hs.loan.cust.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.cust.entity.AppCustCreditInfo;
import com.hs.loan.cust.itface.ICustExtraInfoMapper;

/**
 * APP_客户信用卡信息 mapper
 * @author autocreate
 * @create 2015-10-26
 */
@MyBatisRepository
public interface  AppCustCreditInfoMapper extends ICustExtraInfoMapper<AppCustCreditInfo>{

	//获取最新
	AppCustCreditInfo getLatestByNo(String custNo);

	//获取时间段内的 信用卡信息
	List<AppCustCreditInfo> getCustCreditLstByDate(Map<String,Object> param);

	//更新信用卡信息的endDate
	void updateCreditEndDate(Map<String, Object> p);

	//批量插入
	void batchInsert(List<AppCustCreditInfo> list);
	
}