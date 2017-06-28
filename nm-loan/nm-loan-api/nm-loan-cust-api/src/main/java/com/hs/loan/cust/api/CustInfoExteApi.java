package com.hs.loan.cust.api;

import java.util.List;
import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.cust.dto.AppCustInfoExteDto;

/**
 * APP_客户信息拓展表 接口
 * 
 * @author autocreate
 * @create 2016-06-21
 */
public interface CustInfoExteApi {
	/**
	 * 保存客户拓展信息
	 * 
	 * @param custInfoExte
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void save(AppCustInfoExteDto custInfoExte) throws ServiceException, AppException;

	/**
	 * 更新客户拓展信息
	 * 
	 * @param custInfoExte
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void update(AppCustInfoExteDto custInfoExte) throws ServiceException, AppException;

	/**
	 * 删除客户拓展信息
	 * 
	 * @param custNo
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void deleteInfoByNo(String custNo) throws ServiceException, AppException;

	/**
	 * 查询客户拓展信息列表
	 * 
	 * @param params
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public List<AppCustInfoExteDto> findAllByParam(Map<String, Object> params) throws ServiceException, AppException;

	/**
	 * 根据客户编号查询客户拓展信息
	 * 
	 * @param custNo
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public AppCustInfoExteDto getByCustNo(String custNo) throws ServiceException, AppException;

	/**
	 * 查询客户拓展信息分页
	 * 
	 * @param page
	 * @param profile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public Page<AppCustInfoExteDto> queryForPage(Page<AppCustInfoExteDto> page, UserProfile profile)
			throws ServiceException, AppException;
}