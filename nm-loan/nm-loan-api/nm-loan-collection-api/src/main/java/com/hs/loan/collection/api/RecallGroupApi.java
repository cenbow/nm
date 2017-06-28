package com.hs.loan.collection.api;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.collection.dto.AppRecallGroupDto;
import com.hs.loan.collection.dto.RecallStaffGroupDto;

/**
 * APP_催收组信息 接口
 * @author autocreate
 * @create 2015-11-23
 */
public interface  RecallGroupApi{

	/**
	 * 查询催收组信息
	 * @param page
	 * @param userProfile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public Page<AppRecallGroupDto> queryRecallGroupLst(Page<AppRecallGroupDto> page,UserProfile userProfile) throws ServiceException,AppException;
	/**
	 * 保存催收组信息
	 * @param RecallGroupDto
	 * @param userProfile
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void saveRecallGroup(AppRecallGroupDto recallGroupDto, UserProfile userProfile)
			throws ServiceException, AppException, IllegalAccessException, InvocationTargetException;
	
	/**
	 * 删除催收组信息
	 * @param id
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void removeRecallGroup(String groupNo)  throws ServiceException,AppException;
	/**
	 * 查询催收组员工列表
	 * @param page
	 * @param userProfile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public Page<RecallStaffGroupDto> queryRecallGroupStaffLst(Page<RecallStaffGroupDto> page,UserProfile userProfile) throws ServiceException,AppException;
	/**
	 * 保存催收组员工信息
	 * @param RecallStaffGroupDto
	 * @param userProfile
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void saveRecallGroupStaff(List<RecallStaffGroupDto> RecallStaffGroupList,String groupNo,UserProfile userProfile)
			throws ServiceException, AppException, IllegalAccessException, InvocationTargetException;
	
	/**
	 * 删除催收组员工信息
	 * @param id
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void removeRecallGroupStaff(String groupNo,String staffNo)  throws ServiceException,AppException;
}