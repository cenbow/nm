package com.hs.loan.cust.api;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.cust.dto.CustRevisitInfoDto;


/**
 * APP_客户回访信息 接口
 * @author autocreate
 * @create 2016-06-21
 */
public interface  CustRevisitInfoApi{
	/**
	 * 新增 APP_客户回访信息
	 * @param appCustRevisitInfo 新增对象
	 */
	public void save(CustRevisitInfoDto custRevisitInfoDto,UserProfile userProfile) throws ServiceException,AppException;;
	
	
	public Page<CustRevisitInfoDto> queryForPage(Page<CustRevisitInfoDto> page) throws ServiceException,AppException;

	/**
	 * 手动分期回访信息删除（只能删除手动登记的）
	 * @param handId	记录id
	 * @param userId	操作人用户ID
	 */
	public void deleteRevisitInfo(String id,String staffId) throws ServiceException,AppException;

}