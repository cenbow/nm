package com.hs.loan.approve.api;

import java.util.List;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.approv.dto.LoanFundMatchLogDto;

/**
 * APP_分期资金匹配记录 接口
 * @author autocreate
 * @create 2015-11-23
 */
public interface  LoanFundMatchLogApi{

	/**
	 * 根据分期编号查询该笔分期匹配记录
	 * @param loanNo
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public List<LoanFundMatchLogDto> queryLoanFundMathcLog(String loanNo) throws ServiceException,AppException;
	
	/**
	 * 查询分期匹配记录
	 */

	public Page<LoanFundMatchLogDto> queryLoanFundMathcLogForPage(Page<LoanFundMatchLogDto> page,UserProfile profile)  throws ServiceException,AppException;
}