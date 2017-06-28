package com.hs.loan.cust.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.cust.bo.CustInfoBo;
import com.hs.loan.cust.entity.AppCustInfo;

/**
 * APP_客户信息 mapper
 * @author autocreate
 * @create 2015-10-26
 */
@MyBatisRepository
public interface  AppCustInfoMapper extends BaseMapper<AppCustInfo>{

	//分页查询 客户基本信息
	public List<CustInfoBo> queryCustInfo(Map<String, Object> param);

	//获取客户信用评分
	public List<Integer> queryCustScore(String custNo);
	
}