package com.hs.loan.cust.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.cust.dto.CustEstateInfoDto;


/**
 * APP_客户房产信息 接口
 * @author autocreate
 * @create 2015-10-26
 */
public interface  CustEstateInfoApi{

	/**
	 * 通过客户号 获取 客户房产信息
	 * @param custNo
	 * @return
	 */
	public List<CustEstateInfoDto> getListByNo(String custNo) throws ServiceException,AppException;
	
	/**
	 * 保存或者更新 客户的房产信息
	 * 
	 * @param custNo
	 * @param estateLst
	 */
	public void save(String custNo,CustEstateInfoDto... estateLst) throws ServiceException,AppException;
	
	/**
	 * 删除 客户的房产信息
	 * @param custNo
	 * @param ids
	 */
	public void delete(String custNo,String... ids) throws ServiceException,AppException;
	
	/**
	 * 根据id 获取客户房产信息
	 * 
	 * @param id
	 */
	public CustEstateInfoDto getById(String id) throws ServiceException,AppException;
	
	/**
	 * 获取 客户房产信息list
	 * 
	 * @param param
	 * @return
	 */
	public List<CustEstateInfoDto> getList(Map<String,Object> param) throws ServiceException,AppException;
	
	/**
	 * 获取有效时间段的 有效的 客户房产信息
	 * @param custNo
	 * @param availableDate
	 * @return
	 */
	public List<CustEstateInfoDto> getCustEstateInfoLstByDate(String custNo,Date availableDate) throws ServiceException,AppException;
	
	/**
	 * 获取当前有效的 房产信息
	 * 
	 * @param cust
	 * @return
	 */
	public List<CustEstateInfoDto> getCrtEstateInfoLst(String custNo) throws ServiceException,AppException;

	
	/**
	 * 获取刚刚保存的或者修改的 房产信息 throws ServiceException,AppException;
	 * 
	 * @param dto
	 * @return
	 */
	public CustEstateInfoDto getEditedEstateInfo(CustEstateInfoDto dto) throws ServiceException,AppException;
	
}