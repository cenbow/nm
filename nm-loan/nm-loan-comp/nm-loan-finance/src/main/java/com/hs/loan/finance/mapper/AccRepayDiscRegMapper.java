package com.hs.loan.finance.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.finance.entity.AccRepayDiscReg;

/**
 * ACC_还款登记（费用减免） mapper
 * 
 * @author autocreate
 * @create 2016-02-03
 */
@MyBatisRepository
public interface AccRepayDiscRegMapper extends BaseMapper<AccRepayDiscReg> {

	List queryForPageByInstDate(Map<String, Object> pageParams);

	Map<String, Object> getOutMap(String loanNo);

	Integer updateOutSource(Map<String, Object> pageParams);

}