package com.hs.loan.approve.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.approve.bo.AppLoanCustRepeatCheckBo;

/**
 * 客户重复信息检查
 * @author mac
 *
 */
@MyBatisRepository
public interface  AppLoanCustRepeatCheckMapper {
	public List<AppLoanCustRepeatCheckBo> queryForList(String custNo);
	
	public List<AppLoanCustRepeatCheckBo> queryCustNoCheckLst(Map<String, Object> map);
	
	public Map<String, String> queryCustInfo(String custNo);
	
}