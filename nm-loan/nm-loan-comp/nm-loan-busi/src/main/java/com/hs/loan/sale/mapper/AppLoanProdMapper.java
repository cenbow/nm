package com.hs.loan.sale.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.sale.entity.AppLoanProd;

import java.util.Map;

/**
 * APP_分期与产品关系 mapper
 * @author autocreate
 * @create 2015-10-26
 */
@MyBatisRepository
public interface  AppLoanProdMapper extends BaseMapper<AppLoanProd>{
	public Map<String,Object> getPreFee(Map map);
	void deleteByLoanNo(String loanNo);
	
}