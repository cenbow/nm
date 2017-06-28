package com.hs.loan.acct.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.acct.entity.PubLoanProdCalc;
import com.hs.loan.acct.entity.PubProdFeeCalc;

@MyBatisRepository
public interface AcctFeeCalMapper{

	/**
	 * 产品试算
	 * @param param
	 * @return
	 */
	public List<PubProdFeeCalc> calFee(Map<String,Object> param);
	/**
	 * 分期
	 * @param param
	 * @return
	 */
	public List<PubLoanProdCalc> loanCalFee(Map<String,Object> param);
	
	/**
	 * 首次还款日
	 * @param param
	 */
	public void loanRepayDate(Map<String, String> param);
}
