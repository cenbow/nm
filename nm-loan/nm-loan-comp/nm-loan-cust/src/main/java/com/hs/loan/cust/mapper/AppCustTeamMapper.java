package com.hs.loan.cust.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.cust.entity.AppCustInfo;
import com.hs.loan.cust.entity.AppCustTeam;

/**
 * APP_客户与客户组的关系 mapper
 * @author autocreate
 * @create 2015-10-26
 */
@MyBatisRepository
public interface  AppCustTeamMapper extends BaseMapper<AppCustTeam>{

	//删除组和客户的关系
	public void deleteGrpCustRel(Map<String, Object> param);

	//查询不在当前组中的客户信息
	public List<AppCustInfo> queryNotInGrpCust(Map<String, Object> pageParams);
	
}