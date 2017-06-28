package com.hs.loan.cust.api;

import java.util.List;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.cust.dto.CustCallRegisterDto;


/**
 * APP_客户来电记录 接口
 * @author autocreate
 * @create 2015-10-26
 */
public interface  CustCallRegisterApi{

	/**
	 * 通过客户号 获取 客户来电记录
	 * @param custNo
	 * @return
	 */
	public List<CustCallRegisterDto> getCustCallRegisterLst(String custNo) throws ServiceException,AppException;
	
	/**
	 * 分页查询 客户来电记录 按时间倒序排序
	 * 必须的参数 custNo
	 * @param page
	 * @return
	 */
	public Page<CustCallRegisterDto> queryCustCallRegister(Page<CustCallRegisterDto> page) throws ServiceException,AppException;
	
}