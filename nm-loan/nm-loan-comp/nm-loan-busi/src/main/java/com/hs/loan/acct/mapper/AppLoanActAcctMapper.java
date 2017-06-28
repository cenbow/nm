package com.hs.loan.acct.mapper;

import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.acct.entity.AppLoanActAcct;

/**
 * APP_贷款实际打款情况 mapper
 * 
 * @author autocreate
 * @create 2017-03-21
 */
@MyBatisRepository
public interface AppLoanActAcctMapper extends BaseMapper<AppLoanActAcct> {
	public Integer updateByLoanNo(Map<String, Object> param);
}