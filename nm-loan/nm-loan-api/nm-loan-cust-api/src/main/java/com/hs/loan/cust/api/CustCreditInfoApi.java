package com.hs.loan.cust.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.cust.dto.CustCreditInfoDto;


/**
 * APP_客户信用卡信息 接口
 * @author autocreate
 * @create 2015-10-26
 */
public interface  CustCreditInfoApi{

	/**
	 * 通过客户号 获取 客户信用卡信息
	 * @param custNo
	 * @return
	 */
	public List<CustCreditInfoDto> getListByNo(String custNo) throws ServiceException,AppException;
	
	/**
	 * 保存或者更新 客户信用卡信息
	 * 必须的参数，custNo
	 * 
	 * @param appCustCreditInfo
	 */
	public void save(String custNo,CustCreditInfoDto... creditLst) throws ServiceException,AppException;
	
	/**
	 * 获取 客户 在有效时间段里的 信用卡信息 列表
	 * 
	 * @param availableDate 有效时间
	 * @return
	 */
	public List<CustCreditInfoDto> getCustCreditLstByDate(String custNo,Date availableDate) throws ServiceException,AppException;
	
	
	/**
	 * 获取客户当前 有效的 信用卡信息 列表
	 * 
	 * @return
	 */
	public  List<CustCreditInfoDto> getCrtCustCreditInfoLst(String custNo) throws ServiceException,AppException;
	
	/**
	 * 通过id 获取 客户信用卡信息
	 */
	public CustCreditInfoDto getById(String id) throws ServiceException,AppException;
	
	/**
	 * 删除 客户信用卡信息
	 * @param ids
	 */
	public void delete(String custNo,String... ids) throws ServiceException,AppException;
	
	/**
	 * 获取 客户信用卡信息 list
	 * @param param
	 * @return
	 */
	public List<CustCreditInfoDto> getList(Map<String,Object> param) throws ServiceException,AppException;
	
	/**
	 * 获取编辑过的 信用卡信息
	 * 
	 * @param appCustCreditInfo
	 * @return
	 */
	public CustCreditInfoDto getEditedCreditInfo(CustCreditInfoDto custCreditInfoDto) throws ServiceException,AppException;
	
}