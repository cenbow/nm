package com.hs.loan.finance.api;

import java.util.List;
import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.finance.dto.CtsCaseInfoDto;

/**
 * 安全合规接口
 * 
 * @author autocreate
 * @create 2016-10-13
 */
public interface CaseInfoApi {

	/**
	 * 获取安全合规案件
	 * 
	 * @param page
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public Page<CtsCaseInfoDto> queryCaseInfoPage(Page<CtsCaseInfoDto> page) throws ServiceException, AppException;

	/**
	 * 上报案件
	 * 
	 * @param caseinfo
	 * @param user
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void insertCase(CtsCaseInfoDto caseinfo, UserProfile user) throws ServiceException, AppException;

	/**
	 * 标记案件
	 * 
	 * @param prame
	 * @param user
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void insertMarkCase(Map<String, Object> param, UserProfile user) throws ServiceException, AppException;

	/**
	 * 复核案件
	 * 
	 * @param id
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void updateCase(Map<String, Object> param) throws ServiceException, AppException;

	/**
	 * 批量复核
	 * 
	 * @param list
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void updateCaseList(List<Map<String, Object>> list) throws ServiceException, AppException;

}