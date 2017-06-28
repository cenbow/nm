package com.hs.loan.approve.api;

import java.util.List;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.approv.dto.LoanFundMatchDto;
import com.hs.loan.approv.dto.ProdFundInfoOutDto;

/**
 * APP_分期资金匹配 接口
 * @author autocreate
 * @create 2015-11-23
 */
public interface  LoanFundMatchApi{

	/**
	 * 分页查询分期资金匹配
	 * @param param
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public Page<LoanFundMatchDto> queryLoanFundMatch(Page<LoanFundMatchDto> param,UserProfile profile) throws ServiceException,AppException;
	
	/**
	 * 拾取一个任务 进行人工匹配
	 * 
	 * @param id
	 * @param userProfile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public LoanFundMatchDto choiceMatch(String matchId,UserProfile userProfile) throws ServiceException,AppException;
	
	/**
	 * 查询产品绑定的渠道信息 人工匹配
	 * @param loanNo
	 * @throws ServiceException
	 * @throws AppException
	 */
	public List<ProdFundInfoOutDto> queryFundLst(String loanNo,UserProfile userProfile)   throws ServiceException,AppException;
	
	/**
	 * 资金匹配
	 * @param fundNo
	 * @param userProfile
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void saveLoanFundMatch(LoanFundMatchDto matchDto,UserProfile userProfile)  throws ServiceException,AppException;
	/**
	 * 资金匹配
	 * @param fundNo
	 * @param userProfile
	 * @throws ServiceException
	 * @throws AppException
	 */
	public LoanFundMatchDto queryLoanFund(String loanNo)  throws ServiceException,AppException;
	
	/**
	 * 手工生成合同	
	 * @param loanNo
	 * @param profile
	 * @throws ServiceException
	 * @throws AppException
	 */
	public String handCreateContract(String loanNo,UserProfile profile) throws ServiceException,AppException;
}
