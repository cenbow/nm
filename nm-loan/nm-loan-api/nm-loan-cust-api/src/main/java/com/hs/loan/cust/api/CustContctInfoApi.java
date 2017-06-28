package com.hs.loan.cust.api;

import java.util.Date;
import java.util.List;

import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.cust.dto.CustContctInfoDto;


/**
 * APP_客户联系信息 接口
 * @author autocreate
 * @create 2015-10-26
 */
public interface  CustContctInfoApi{

	/**
	 * 通过客户号 获取客户现联系信息
	 * @return
	 */
	public CustContctInfoDto getCrtByNo(String custNo) throws ServiceException,AppException;
	
	/**
	 * 保存或者更新 客户联系信息
	 * @param custNo
	 * @param contctLst
	 */
	public void save(String custNo,CustContctInfoDto... contctLst) throws ServiceException,AppException;
	
	/**
	 * 删除某个客户的 客户联系信息
	 * @param custNo
	 * @param ids
	 */
	public void delete(String custNo,String... ids) throws ServiceException,AppException;
	
	/**
	 * 通过id获取 客户联系信息
	 * @param id
	 * @return
	 */
	public CustContctInfoDto getById(String id) throws ServiceException,AppException;
	
	/**
	 * 根据客户号和有效时间段 获取 客户联系信息
	 * 
	 * @param custNo
	 * @param availableDate
	 * @return
	 */
	public List<CustContctInfoDto> getCustContctInfoListByDate(String custNo,Date availableDate) throws ServiceException,AppException;
	
	/**
	 * 获取最新的 有效的  客户联系信息
	 * 
	 * @return
	 */
	public List<CustContctInfoDto> getCrtContctInfoLst(String custNo) throws ServiceException,AppException;
	
	/**
	 * 获取客户全部的 客户联系信息
	 * @param custNo
	 * @return
	 */
	public List<CustContctInfoDto> getListByNo(String custNo) throws ServiceException,AppException;
	
}