package com.hs.loan.approve.api;

import java.util.List;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.ServiceException;
import com.hs.loan.approvcheck.dto.AppApprstaffGroupDto;
import com.hs.loan.approvcheck.dto.AppApprstaffGroupdetalDto;
import com.hs.loan.approvcheck.dto.AppLoanApprCheckDto;
import com.hs.loan.approvcheck.dto.AppLoanApprCheckHDto;

/**
 * 
 * 内部审核
 * @author lenovo
 * 
 */
public interface LoanApprCheckApi 
{
	/**
	 * 查询审核组
	 * @param page
	 * @param userProfile
	 * @return
	 * @throws ServiceException
	 */
	public Page<AppApprstaffGroupDto> queryApprStaffGroupList(Page<AppApprstaffGroupDto> page, UserProfile userProfile) throws ServiceException;
	
	/**
	 * 新增审核组
	 * @param appApprstaffGroupDto
	 * @param userProfile
	 * @throws ServiceException
	 */
	public void addApprStaffGroup(AppApprstaffGroupDto appApprstaffGroupDto, UserProfile userProfile) throws ServiceException;
	
	/**
	 * 批量删除审核组
	 * @param appApprstaffGroupDto
	 * @param userProfile
	 * @throws ServiceException
	 */
	public void batchDelApprStaffGroupByLstId(List<String> list, UserProfile userProfile) throws ServiceException;
	
	/**
	 * 修改审核组
	 * @param appApprstaffGroupDto
	 * @param userProfile
	 */
	public void modifyApprStaffGroupById(AppApprstaffGroupDto appApprstaffGroupDto, UserProfile userProfile) throws ServiceException;
	
	/**
	 * 查询审批内部组人员
	 * @param page
	 * @param userProfile
	 * @return
	 * @throws ServiceException
	 */
	public Page<AppApprstaffGroupdetalDto> queryApprstaffGroupdetalList(Page<AppApprstaffGroupdetalDto> page, UserProfile userProfile) throws ServiceException;
	
	/**
	 * 批量新增审批内部组人员
	 * @param apprstaffGroupDto
	 * @param apprstaffGroupdetalDtoList
	 * @param userProfile
	 */
	public void batchAddApprstaffGroupdetal(AppApprstaffGroupDto apprstaffGroupDto, List<AppApprstaffGroupdetalDto> apprstaffGroupdetalDtoList, UserProfile userProfile) throws ServiceException;
	
	/**
	 * 批量删除审批内部组人员
	 * @param list
	 * @param userProfile
	 * @throws ServiceException
	 */
	public void batchDelApprstaffGroupdetalByLstId(List<String> list, UserProfile userProfile) throws ServiceException;
	
	/**
	 * 查询不在审批内部组的人员
	 * @param page
	 * @param userProfile
	 * @return
	 */
	public Page<AppApprstaffGroupdetalDto> queryNotApprstaffGroupdetalForPage(Page<AppApprstaffGroupdetalDto> page, UserProfile userProfile) throws ServiceException;
	
	/**
	 * 查询所有的复核记录
	 * @param page
	 * @param userProfile
	 * @return
	 */
	public Page<AppLoanApprCheckDto> queryApprCheckList(Page<AppLoanApprCheckDto> page, UserProfile userProfile) throws ServiceException;
	
	/**
	 * 查询所有的复核历史
	 * @param page
	 * @param userProfile
	 * @return
	 * @throws ServiceException
	 */
	public Page<AppLoanApprCheckHDto> queryApprCheckHList(Page<AppLoanApprCheckHDto> page, UserProfile userProfile) throws ServiceException;
	

	/**
	 * 
	 * 查询主管是否在线
	 * 
	 */
	public String queryManagerStateByStaffNo(UserProfile userProfile) throws ServiceException;
}
