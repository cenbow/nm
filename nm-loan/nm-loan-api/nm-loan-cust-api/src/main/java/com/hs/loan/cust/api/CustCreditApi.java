package com.hs.loan.cust.api;

import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.cust.dto.CustCreditDto;


/**
 * APP_客户授信额度 接口
 * @author autocreate
 * @create 2015-10-26
 */
public interface  CustCreditApi{

	/**
	 * 通过客户号 获取 客户授信额度
	 * @param custNo
	 * @return
	 */
	public CustCreditDto getCustCredit(String custNo) throws ServiceException,AppException;
	
	
}