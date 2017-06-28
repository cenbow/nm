package com.hs.loan.cust.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.cust.dto.CustCarInfoDto;


/**
 * APP_客户车辆信息 接口
 * @author autocreate
 * @create 2015-10-26
 */
public interface  CustCarInfoApi{

	/**
	 * 通过客户号，获取 客户车辆信息
	 * @param custNo
	 * @return
	 */
	public List<CustCarInfoDto> getListByNo(String custNo) throws ServiceException,AppException;
	
	/**
	 * 保存或者更新 客户车辆信息
	 * @param custNo
	 * @param carLst
	 */
	public void save(String custNo,CustCarInfoDto... carLst) throws ServiceException,AppException;
	
	/**
	 * 删除
	 * @param custNo
	 * @param ids
	 */
	public void delete(String custNo,String... ids) throws ServiceException,AppException;
	
	/**
	 * 通过id获取 客户车辆信息
	 * 
	 * @param id
	 * @return
	 */
	public CustCarInfoDto getById(String id) throws ServiceException,AppException;
	
	/**
	 * 获取 客户车辆信息lst
	 * @param param
	 * @return
	 */
	public List<CustCarInfoDto> getList(Map<String,Object> param) throws ServiceException,AppException;
	
	/**
	 * 获取有效时间段里的 有效的 客户车辆信息
	 * @param custNo
	 * @param availableDate
	 * @return
	 */
	public List<CustCarInfoDto> getCustCarInfoLstByDate(String custNo,Date availableDate) throws ServiceException,AppException;
	
	/**
	 * 获取客户当前 有效的 客户车辆信息
	 * @param custNo
	 * @return
	 */
	public List<CustCarInfoDto> getCrtCustCarInfoLst(String custNo) throws ServiceException,AppException;
	
	/**
	 * 获取刚刚修改过的车辆信息
	 * 
	 * @param appCustCarInfo
	 * @return
	 */
	public CustCarInfoDto getEditedCarInfo(CustCarInfoDto custCarInfoDto) throws ServiceException,AppException;
	
}