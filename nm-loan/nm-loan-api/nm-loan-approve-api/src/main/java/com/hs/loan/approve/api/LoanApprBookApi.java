package com.hs.loan.approve.api;

import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.approv.dto.LoanApprBookDto;
import com.hs.loan.approv.dto.LoanApprRemarkDto;

import java.util.List;

/**
 * APP_分期审批案件批注表 接口
 * @author autocreate
 * @create 2015-11-24
 */
public interface  LoanApprBookApi{
	public String svaeApprRemark(LoanApprRemarkDto loanApprRemarkDto, UserProfile userProfile)throws ServiceException, AppException;
	public List<LoanApprRemarkDto> queryLoanApprRemarkList(String loanNo, UserProfile userProfile);
	/**
	 * 新增批注
	 * @param apprBookDto
	 * @param userProfile
	 * @throws ServiceException
	 * @throws AppException
	 */
	public String saveApprBook(LoanApprBookDto apprBookDto,UserProfile userProfile) throws ServiceException,AppException;
	
	/**
	 * 查询批注
	 * @param loanNo
	 * @param userProfile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public List<LoanApprBookDto> queryLoanApprBookLst(String loanNo,UserProfile userProfile) throws ServiceException,AppException;
	
	/**
	 * 删除批注
	 * @param id
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void  removeApprBook(String id) throws ServiceException,AppException;
	/**
	 * 删除批注
	 * @param id
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void removeApprBookByLoan(String loanNo) throws ServiceException,AppException;
	
	
}