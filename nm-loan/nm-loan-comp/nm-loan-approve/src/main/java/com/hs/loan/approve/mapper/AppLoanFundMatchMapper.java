package com.hs.loan.approve.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.approve.bo.AppLoanFundMatchBo;
import com.hs.loan.approve.entity.AppLoanFundMatch;

/**
 * APP_分期资金匹配 mapper
 * @author autocreate
 * @create 2015-11-23
 */
@MyBatisRepository
public interface  AppLoanFundMatchMapper extends BaseMapper<AppLoanFundMatch>{

	List<AppLoanFundMatchBo> queryLoanFundMatch(Map<String, Object> pageParams);
	
	void loanFundMatch(Map<String,Object> map);
}