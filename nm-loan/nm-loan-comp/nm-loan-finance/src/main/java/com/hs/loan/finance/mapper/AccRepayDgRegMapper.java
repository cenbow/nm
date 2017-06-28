package com.hs.loan.finance.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.finance.entity.AccRepayDgReg;

import java.util.HashMap;
import java.util.List;

/**
 * ACC_还款登记（对公） mapper
 * @author autocreate
 * @create 2016-02-03
 */
@MyBatisRepository
public interface  AccRepayDgRegMapper extends BaseMapper<AccRepayDgReg>{

	public int selectCountExcel(HashMap<String,Object> map);

	public  List<AccRepayDgReg> queryForPageList(java.util.Map<String, Object> map);

	/**
	 * 查询贷款是不是委外
	 * @param loanNo
	 * @return
	 */
	public int selectOutSourceCase(String loanNo);
	
}