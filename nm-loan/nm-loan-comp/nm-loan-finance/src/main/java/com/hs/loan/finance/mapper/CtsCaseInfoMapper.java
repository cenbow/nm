package com.hs.loan.finance.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.finance.entity.CtsCaseInfo;

/**
 *  mapper
 * @author autocreate
 * @create 2016-10-13
 */
@MyBatisRepository
public interface  CtsCaseInfoMapper extends BaseMapper<CtsCaseInfo>{

	Object queryParam(String loanNo);
	
}