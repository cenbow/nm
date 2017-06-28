package com.hs.loan.approve.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.approve.entity.AppLoanApprBook;

/**
 * APP_分期审批案件批注表 mapper
 * @author autocreate
 * @create 2015-11-24
 */
@MyBatisRepository
public interface  AppLoanApprBookMapper extends BaseMapper<AppLoanApprBook>{

	void deleteByLoanNo(String loanNo);
	
}