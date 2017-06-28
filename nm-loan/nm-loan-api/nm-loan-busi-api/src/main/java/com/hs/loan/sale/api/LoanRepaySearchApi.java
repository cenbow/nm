package com.hs.loan.sale.api;

import java.util.List;

import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.busi.dto.LoanActRepayDto;
import com.hs.loan.busi.dto.LoanRepayCollectDto;
import com.hs.loan.busi.dto.LoanRepayPlanDto;
import com.hs.loan.busi.dto.LoanRepayPlanOvduDto;

/**
 * 分期还款信息查询 接口
 * @author jqiu
 * @create 2015-10-30
 */
public interface  LoanRepaySearchApi{
	
	/**
	 * 分期还款汇总信息查询
	 * @param loanNo		分期编号
	 * @param userProfile 	用户信息
	 * @return LoanRepayCollectDto
	 */
	public LoanRepayCollectDto getLoanRepayCollectInfo(String loanNo,UserProfile userProfile) throws ServiceException,AppException;
	
	/**
	 * 查询分期还款明细 
	 * @param loanNo		分期编号
	 * @param userProfile 	用户信息
	 * @return List<LoanActRepayDto>
	 */
	public List<LoanActRepayDto> getLoanActRepayInfo(String loanNo,UserProfile userProfile) throws ServiceException,AppException;
	
	/**
	 * 查询分期还款计划
	 * @param loanNo		分期编号
	 * @param userProfile 	用户信息
	 * @return List<LoanRepayPlanDto>
	 */
	public List<LoanRepayPlanDto> getLoanRepayPlan(String loanNo,UserProfile userProfile) throws ServiceException,AppException;
	
	/**
	 * 查询分期逾期信息
	 * @param loanNo		分期编号
	 * @param userProfile 	用户信息
	 * @return List<LoanRepayPlanOvduDto>
	 */
	public List<LoanRepayPlanOvduDto> getLoanOverdue(String loanNo,UserProfile userProfile) throws ServiceException,AppException;
	
	
}