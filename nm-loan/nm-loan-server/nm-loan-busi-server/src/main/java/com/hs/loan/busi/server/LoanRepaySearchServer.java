package com.hs.loan.busi.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.UserProfile;
import com.hs.loan.acct.service.AccLoanActRepayService;
import com.hs.loan.acct.service.AccLoanRepayPlanOvduService;
import com.hs.loan.acct.service.AccLoanRepayPlanService;
import com.hs.loan.acct.service.AccRepaySumService;
import com.hs.loan.busi.dto.LoanActRepayDto;
import com.hs.loan.busi.dto.LoanRepayCollectDto;
import com.hs.loan.busi.dto.LoanRepayPlanDto;
import com.hs.loan.busi.dto.LoanRepayPlanOvduDto;
import com.hs.loan.sale.api.LoanRepaySearchApi;
import com.hs.utils.BeanUtils;

/**
 * 分期基本信息
 * @author jqiu
 * @create 2015-10-27
 */
@Service
@Transactional(readOnly=true)
public class  LoanRepaySearchServer implements LoanRepaySearchApi{
	@Autowired
	private AccRepaySumService accRepaySumService;
	@Autowired
	private AccLoanRepayPlanOvduService accLoanRepayPlanOvduService;
	@Autowired
	private AccLoanRepayPlanService accLoanRepayPlanService;
	@Autowired
	private AccLoanActRepayService accLoanActRepayService;
	
	/**
	 * 分期还款汇总信息查询
	 * @param loanNo		分期编号
	 * @param userProfile 	用户信息
	 * @return LoanRepayCollectDto
	 */
	@Override
	public LoanRepayCollectDto getLoanRepayCollectInfo(String loanNo,UserProfile userProfile){
		return BeanUtils.copyPropertiesNotForceByClz(LoanRepayCollectDto.class,accRepaySumService.getByLoanNo(loanNo));
	}
	
	/**
	 * 查询分期还款明细 
	 * @param loanNo		分期编号
	 * @param userProfile 	用户信息
	 * @return List<LoanActRepayDto>
	 */
	@Override
	public List<LoanActRepayDto> getLoanActRepayInfo(String loanNo,UserProfile userProfile){
		return BeanUtils.copyProperties(accLoanActRepayService.queryByLoanNo(loanNo),LoanActRepayDto.class);
	}
	
	/**
	 * 查询分期还款计划
	 * @param loanNo		分期编号
	 * @param userProfile 	用户信息
	 * @return List<LoanRepayPlanDto>
	 */
	@Override
	public List<LoanRepayPlanDto> getLoanRepayPlan(String loanNo,UserProfile userProfile){
		return BeanUtils.copyProperties(accLoanRepayPlanService.queryByLoanNo(loanNo),LoanRepayPlanDto.class);
	}
	
	/**
	 * 查询分期逾期信息
	 * @param loanNo		分期编号
	 * @param userProfile 	用户信息
	 * @return List<LoanRepayPlanOvduDto>
	 */
	@Override
	public List<LoanRepayPlanOvduDto> getLoanOverdue(String loanNo,UserProfile userProfile){
		return BeanUtils.copyProperties(accLoanRepayPlanOvduService.queryByLoanNo(loanNo),LoanRepayPlanOvduDto.class);
	}
	
	
}