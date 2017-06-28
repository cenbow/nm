package com.hs.loan.finance.mapper;

import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.finance.entity.AccPayWith;

/**
 * ACC_三方支付代扣信息 mapper
 * @author autocreate
 * @create 2016-04-20
 */
@MyBatisRepository
public interface  AccPayWithMapper extends BaseMapper<AccPayWith>{
	
	public Integer updateOverByLoanNo(Map<String,Object> params);

	public Integer updateOutSource(Map<String, Object> outMap);

	public Integer updateIsSettle(Map<String, Object> sourceMap);
}