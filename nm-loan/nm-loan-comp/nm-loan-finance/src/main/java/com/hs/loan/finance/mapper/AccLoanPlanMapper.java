package com.hs.loan.finance.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.finance.bo.AccLoanPlanBo;
import com.hs.loan.finance.entity.AccLoanPlan;

/**
 * ACC_还款计划 mapper
 * @author autocreate
 * @create 2016-02-03
 */
@MyBatisRepository
public interface  AccLoanPlanMapper extends BaseMapper<AccLoanPlan>{

	List queryListByParam(Map<String, Object> planParam);

	List queryListOnNo(Map<String, Object> pageParams);

	AccLoanPlanBo  calcEealySumAmt(String loanNo);

	List queryOutSourceCaseListOnNo(Map<String, Object> pageParams);
	
}