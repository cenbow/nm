package com.hs.loan.outsource.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.outsource.entity.PlLoanOutsourceAmount;

/**
 * PL_委外金额明细 mapper
 * @author autocreate
 * @create 2015-12-02
 */
@MyBatisRepository
public interface  PlLoanOutsourceAmountMapper extends BaseMapper<PlLoanOutsourceAmount>{
	
	List<PlLoanOutsourceAmount>   queryOutSourceFeeList(Map<String,Object> map);
	
}