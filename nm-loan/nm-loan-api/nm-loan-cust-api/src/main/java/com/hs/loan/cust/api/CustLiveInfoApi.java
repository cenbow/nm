package com.hs.loan.cust.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.cust.dto.CustLiveInfoDto;


/**
 * APP_客户居住信息 接口
 * @author autocreate
 * @create 2015-10-26
 */
public interface  CustLiveInfoApi{

	/**
	 * 通过客户号 获取 客户的现居住信息
	 * @param custNo
	 * @return
	 */
	public CustLiveInfoDto getCrtByNo(String custNo) throws ServiceException,AppException;
	
	/**
	 * 保存或者更新 客户居住信息
	 * 
	 * @param custNo
	 * @param liveLst
	 */
	public void save(String custNo,CustLiveInfoDto... liveLst) throws ServiceException,AppException;
	
	/**
	 * 删除
	 * 
	 * @param custNo
	 * @param ids
	 */
	public void delete(String custNo,String... ids) throws ServiceException,AppException;
	
	/**
	 * 通过id 获取 客户居住信息
	 * @param id
	 * @return
	 */
	public CustLiveInfoDto getById(String id) throws ServiceException,AppException;
	
	/**
	 * 获取 客户居住信息 list
	 * @param param
	 * @return
	 */
	public List<CustLiveInfoDto> getList(Map<String,Object> param) throws ServiceException,AppException;
	
	/**
	 * 获取 有效时间段里的 有效的 客户居住信息
	 * @param custNo
	 * @param availableDate
	 * @return
	 */
	public List<CustLiveInfoDto> getCustLiveInfoLstByDate(String custNo,Date availableDate) throws ServiceException,AppException;
	
	/**
	 * 获取 当前 有效的 客户居住信息 
	 * @param custNo
	 * @return
	 */
	public List<CustLiveInfoDto> getCrtCustLiveInfoLst(String custNo) throws ServiceException,AppException;
	
}