package com.hs.loan.cust.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.cust.dto.CustContctOtherDto;
import com.hs.loan.cust.dto.CustContctPersonDto;


/**
 * APP_客户其他联系人信息 接口
 * @author autocreate
 * @create 2015-10-26
 */
public interface  CustContctOtherApi{

	/**
	 * 通过客户号 获取 客户其他联系人信息
	 * @param custNo
	 * @return
	 */
	public List<CustContctOtherDto> getListByNo(String custNo) throws ServiceException,AppException;
	
	/**
	 * 保存 客户其他联系人信息
	 * @param custNo
	 * @param otherLst
	 */
	public void save(String custNo,CustContctOtherDto... otherLst) throws ServiceException,AppException;
	
	/**
	 * 删除 客户其他联系人信息
	 */
	public void delete(String custNo,String... ids) throws ServiceException,AppException;
	
	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	public CustContctOtherDto getById(String id) throws ServiceException,AppException;
	
	/**
	 * 通过客户号和有效时间段 获取客户其他联系人信息
	 * @param availableDate
	 * @return
	 */
	public List<CustContctOtherDto> getCustContctOtherLstByDate(String custNo,Date availableDate) throws ServiceException,AppException;
	
	/**
	 * 获取当前有效的 客户其他联系人联系信息
	 * 
	 * @param custNo
	 * @return
	 */
	public List<CustContctOtherDto> getCrtContctOtherLst(String custNo);
	
	/**
	 * 获取 客户其他联系人联系信息
	 * @param param
	 * @return
	 */
	public List<CustContctOtherDto> getList(Map<String,Object> param);
	
	/**
	 * 保存客户的 联系人信息和其他联系人信息
	 * 
	 */
	public void saveContctPerson(String custNo ,CustContctPersonDto dto);
	
}