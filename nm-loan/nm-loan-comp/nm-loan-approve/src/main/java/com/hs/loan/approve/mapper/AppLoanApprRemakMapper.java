package com.hs.loan.approve.mapper;

import org.apache.ibatis.annotations.Param;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.approve.entity.AppLoanApprRemark;

/**
 * APP_分期审批案件批注表 mapper
 * @author autocreate
 * @create 2015-11-24
 */
@MyBatisRepository
public interface AppLoanApprRemakMapper extends BaseMapper<AppLoanApprRemark>{

	void deleteByLoanNo(String loanNo);
	
	void deleteByBlockId(@Param("blockId") String blockId, @Param("loanNo")String loanNo);
	
}