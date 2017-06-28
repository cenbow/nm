package com.hs.loan.sale.mapper;

import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.sale.entity.AppLoanBankAcct;

/**
 * APP_分期与银行账户关系 mapper
 * @author autocreate
 * @create 2015-10-27
 */
@MyBatisRepository
public interface  AppLoanBankAcctMapper extends BaseMapper<AppLoanBankAcct>{
	
	void updateByLoanSelective(Map<String,Object> param) ;

	void deleteByLoanNo(String loanNo);
}