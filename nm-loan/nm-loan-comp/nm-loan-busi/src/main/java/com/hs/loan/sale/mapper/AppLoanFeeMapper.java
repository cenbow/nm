package com.hs.loan.sale.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.sale.entity.AppLoanFee;

/**
 * APP_分期与费用项关系 mapper
 * @author autocreate
 * @create 2015-10-26
 */
@MyBatisRepository
public interface  AppLoanFeeMapper extends BaseMapper<AppLoanFee>{

	public Integer delAppLoanFee(java.util.Map map);
	void deleteByLoanNo(String loanNo);
	
}