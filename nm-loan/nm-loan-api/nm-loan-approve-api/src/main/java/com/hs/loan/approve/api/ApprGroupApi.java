package com.hs.loan.approve.api;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.approv.dto.ApprGroupDto;
import com.hs.loan.approv.dto.ApprStaffGroupDto;

/**
 * APP_审批组信息 接口
 * @author autocreate
 * @create 2015-11-23
 */
public interface  ApprGroupApi{

	/**
	 * 查询审批组信息
	 * @param page
	 * @param userProfile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public Page<ApprGroupDto> queryApprGroupLst(Page<ApprGroupDto> page,UserProfile userProfile) throws ServiceException,AppException;
	/**
	 * 保存审批组信息
	 * @param apprGroupDto
	 * @param userProfile
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void saveApprGroup(ApprGroupDto apprGroupDto, UserProfile userProfile)
			throws ServiceException, AppException, IllegalAccessException, InvocationTargetException;
	
	/**
	 * 删除审批组信息
	 * @param id
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void removeApprGroup(String groupNo)  throws ServiceException,AppException;
	/**
	 * 查询审批组员工列表
	 * @param page
	 * @param userProfile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public Page<ApprStaffGroupDto> queryApprGroupStaffLst(Page<ApprGroupDto> page,UserProfile userProfile) throws ServiceException,AppException;
	/**
	 * 保存审批组员工信息
	 * @param apprStaffGroupDto
	 * @param userProfile
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void saveApprGroupStaff(List<ApprStaffGroupDto> apprStaffGroupList,String groupNo,UserProfile userProfile)
			throws ServiceException, AppException, IllegalAccessException, InvocationTargetException;
	
	/**
	 * 删除审批组员工信息
	 * @param id
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void removeApprGroupStaff(String groupNo,String staffNo)  throws ServiceException,AppException;
}