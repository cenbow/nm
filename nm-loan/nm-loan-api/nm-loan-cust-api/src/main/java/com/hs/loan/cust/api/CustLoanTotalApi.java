package com.hs.loan.cust.api;

import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.cust.dto.CustLoanTotalDto;


/**
 * APP_客户分期信息汇总 接口
 * @author autocreate
 * @create 2015-10-26
 */
public interface  CustLoanTotalApi{

	/**
	 * 通过客户号 获取客户分期汇总信息
	 * @param custNo
	 * @return
	 */
	public CustLoanTotalDto getByNo(String custNo) throws ServiceException,AppException;
	
}