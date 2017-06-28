package com.hs.loan.cust.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.cust.dto.CustStudyDto;


/**
 * APP_客户学校信息 接口
 * @author autocreate
 * @create 2015-10-26
 */
public interface  CustStudyApi{

	/**
	 * 保存或者更新 客户学校信息
	 * @param cust
	 * @param studyLst
	 */
	public void save(String custNo,CustStudyDto... studyLst) throws ServiceException,AppException;
	
	/**
	 * 删除 客户学校信息
	 * @param custNo
	 * @param ids
	 */
	public void delete(String custNo,String... ids) throws ServiceException,AppException;
	
	/**
	 * 通过id获取 客户学校信息
	 * 
	 * @param id
	 * @return
	 */
	public CustStudyDto getById(String id) throws ServiceException,AppException;
	
	/**
	 *获取 客户学校信息 list
	 * 
	 * @return
	 */
	public List<CustStudyDto> getList(Map<String,Object> param) throws ServiceException,AppException;
	
	/**
	 * 通过有效时间区间 获取这个区间的 客户学校信息 list
	 * 
	 * @param custNo
	 * @param availableDate
	 * @return
	 */
	public List<CustStudyDto> getCustStudyListByDate(String custNo,Date availableDate) throws ServiceException,AppException;
	
	/**
	 * 获取客户当前的 有效的 客户学校信息 list
	 * 
	 * @return
	 */
	public List<CustStudyDto> getCrtCustStudyList(String custNo) throws ServiceException,AppException;
	
	
}