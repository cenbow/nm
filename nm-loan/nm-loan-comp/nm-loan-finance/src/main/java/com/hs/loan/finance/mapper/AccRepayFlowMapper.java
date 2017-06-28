package com.hs.loan.finance.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.finance.entity.AccRepayFlow;

/**
 * ACC_还款流水 mapper
 * @author autocreate
 * @create 2016-02-03
 */
@MyBatisRepository
public interface  AccRepayFlowMapper extends BaseMapper<AccRepayFlow>{
	
	/**
	 * 批量插入还款流水
	 * @param list
	 * @return
	 */
	public Integer batchInsertRepayFlow(List<AccRepayFlow> list);

	public void deleteByparam(Map<String, Object> map);

	public Map<String, Object> findOpenBankOrg(String loanNo);

	public Integer updateOpenBankOrg(Map<String, Object> param);

	public Map<String, Object> findInstMap(String loanNo);

	public Integer updateInstBusnDate(Map<String, Object> param);

	public Map<String, Object> findDgReg(String dgid);

	public Map<String, Object> findRepayFlow(Map<String, Object> param);

	public Integer updateDgReg(Map<String, Object> param);

	public Integer updateRepayFlow(Map<String, Object> param);
}