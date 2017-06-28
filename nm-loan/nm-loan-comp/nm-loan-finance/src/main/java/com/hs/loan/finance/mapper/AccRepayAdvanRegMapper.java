package com.hs.loan.finance.mapper;

import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.finance.entity.AccRepayAdvanReg;

/**
 * ACC_还款登记（提前结清） mapper
 * @author autocreate
 * @create 2016-02-03
 */
@MyBatisRepository
public interface  AccRepayAdvanRegMapper extends BaseMapper<AccRepayAdvanReg>{

	/**
	 * 提前结清更新预期案件表状态
	 * @param loanNo
	 */
	void updateOveLoanStat(String loanNo);

	void updateBySelective(Map<String,Object> map);

	Map<String, Object> getAppLoanAcct(String loanNo);
	
}