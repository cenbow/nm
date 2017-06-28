package com.hs.loan.cust.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.cust.entity.AppCustLoanTotal;

/**
 * APP_客户分期信息汇总 mapper
 * @author autocreate
 * @create 2015-10-26
 */
@MyBatisRepository
public interface  AppCustLoanTotalMapper extends BaseMapper<AppCustLoanTotal>{

	AppCustLoanTotal getByNo(String custNo);
	
}