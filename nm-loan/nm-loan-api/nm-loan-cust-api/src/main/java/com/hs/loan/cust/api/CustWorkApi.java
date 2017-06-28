package com.hs.loan.cust.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.cust.dto.CustWorkDto;


/**
 * APP_客户工作信息 接口
 * @author autocreate
 * @create 2015-10-26
 */
public interface  CustWorkApi {

	/**
	 * 保存或更新 客户工作信息
	 * @param custNo
	 * @param workLst
	 */
	public void save(String custNo,CustWorkDto... workLst) throws ServiceException,AppException;

	/**
	 * 删除 客户工作信息
	 * @param custNo
	 * @param ids
	 */
	public void delete(String custNo , String... ids) throws ServiceException,AppException;
	
	/**
	 * 通过id 获取客户工作信息
	 * @param id
	 * @return
	 */
	public CustWorkDto getById(String id) throws ServiceException,AppException;
	
	/**
	 * 获取 客户工作信息 list
	 * 
	 * @param param
	 * @return
	 */
	public List<CustWorkDto> getList(Map<String,Object> param) throws ServiceException,AppException;
	
	/**
	 * 获取客户有效时间段里的 有效的 客户工作信息
	 * 
	 * @param custNo
	 * @param availableDate
	 * @return
	 */
	public List<CustWorkDto> getCustWorkLstByDate(String custNo,Date availableDate) throws ServiceException,AppException;
	
	/**
	 * 获取当前 有效的 客户工作信息
	 * @param custNo
	 * @return
	 */
	public List<CustWorkDto> getCrtCustWorkLst(String custNo) throws ServiceException,AppException;
	
}