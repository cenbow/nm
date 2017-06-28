package com.hs.loan.cust.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.cust.dto.CustAssetInfoDto;


/**
 * APP_客户资产信息 接口
 * @author autocreate
 * @create 2015-10-26
 */
public interface  CustAssetInfoApi{

	/**
	 * 通过客户号 获取 客户资产信息
	 * @param custNo
	 * @return
	 */
	public List<CustAssetInfoDto> getListByNo(String custNo) throws ServiceException,AppException;
	
	/**
	 * 保存或更新 客户资产信息 
	 */
	public void save(String custNo,CustAssetInfoDto... assetLst) throws ServiceException,AppException;
	
	/**
	 * 通过id获取 客户资产信息
	 * @param id
	 */
	public CustAssetInfoDto getById(String id) throws ServiceException,AppException;
	
	/**
	 * 通过客户号 获取 客户资产信息
	 * @param custNo
	 * @return
	 */
	public List<CustAssetInfoDto> getList(Map<String,Object> param ) throws ServiceException,AppException;
	
	/**
	 * 删除
	 * @param custNo
	 * @param ids
	 */
	public void delete(String custNo,String... ids) throws ServiceException,AppException;
	
	/**
	 * 获取客户 有效时间段的有效的 资产信息
	 * @param custNo
	 * @param availableDate
	 * @return
	 */
	public List<CustAssetInfoDto> getCustAssetInfoLstByDate(String custNo,Date availableDate) throws ServiceException,AppException;
	
	/**
	 * 获取客户当前有效的资产信息
	 * @param custNo
	 * @return
	 */
	public List<CustAssetInfoDto> getCrtCustAssetInfoLst(String custNo) throws ServiceException,AppException;
	
}