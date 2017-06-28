package com.hs.loan.sale.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.sale.entity.AppLoanRepayTyp;

/**
 * APP_分期与还款类型的关系 mapper
 * @author autocreate
 * @create 2015-10-30
 */
@MyBatisRepository
public interface  AppLoanRepayTypMapper extends BaseMapper<AppLoanRepayTyp>{

	void deleteByLoanNo(String loanNo);
	
}