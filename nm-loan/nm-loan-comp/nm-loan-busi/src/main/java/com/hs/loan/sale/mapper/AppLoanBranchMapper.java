package com.hs.loan.sale.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.sale.entity.AppLoanBranch;

import java.util.Map;

/**
 * APP_分期与网点关系 mapper
 * @author autocreate
 * @create 2015-10-27
 */
@MyBatisRepository
public interface  AppLoanBranchMapper extends BaseMapper<AppLoanBranch>{

	public Map getPubBranchByLoanNo(Map map);

	void deleteByLoanNo(String loanNo);
	
}