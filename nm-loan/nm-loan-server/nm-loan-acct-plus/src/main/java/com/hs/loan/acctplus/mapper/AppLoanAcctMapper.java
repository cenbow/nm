package com.hs.loan.acctplus.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hs.loan.acctplus.model.AppLoanAcct;
import com.baomidou.mybatisplus.mapper.CommonMapper;

/**
 *
 * AppLoanAcct 表数据库控制层接口
 *
 */
@Repository
public interface AppLoanAcctMapper extends CommonMapper<AppLoanAcct> {

	//资金匹配存储过程
	public void procFundMatch(Map<String,String> param);
	
	//多次进件
	public void multipleEnter(Map<String,String> param);
	
	//查询商户黑名单
	public int isBlacklistBranchNo(Map<String,String> param);
}