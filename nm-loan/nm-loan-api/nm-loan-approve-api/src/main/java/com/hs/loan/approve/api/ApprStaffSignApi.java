package com.hs.loan.approve.api;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.approv.dto.ApprStaffSignDto;

/**
 * APP_审批人员签到明细 接口
 * @author autocreate
 * @create 2015-11-23
 */
public interface  ApprStaffSignApi{
 
	/**
	 * 查询审判员当前的状态
	 * @param profile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public String queryCurrentStat(UserProfile profile) throws ServiceException,AppException;
	/**
	 * 审判员上线
	 * @param profile
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void onLine (UserProfile profile) throws ServiceException,AppException;
	/**
	 * 审判员下线
	 * @param profile
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void offLine (UserProfile profile) throws ServiceException,AppException;
	/**
	 * 查询审判上下线记录
	 * @param page
	 * @param profile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public Page<ApprStaffSignDto> queryStaffSignRst (Page<ApprStaffSignDto> page,UserProfile profile) throws ServiceException,AppException;
	
	
}