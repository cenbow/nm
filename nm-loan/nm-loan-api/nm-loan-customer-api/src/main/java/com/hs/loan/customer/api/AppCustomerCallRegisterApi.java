package com.hs.loan.customer.api;

import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.customer.dto.AppCustCallRegisterDto;

/**
 * APP_客户来电记录 接口
 * @author autocreate
 * @create 2016-07-07
 */
public interface  AppCustomerCallRegisterApi{
	/**
	 * 新增客户来电信息
	 * @param custCallRegisterDto
	 * @param userProfile
	 * @param loanNo
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void instCallRegister(AppCustCallRegisterDto custCallRegisterDto,UserProfile userProfile,String loanNo) throws ServiceException,AppException;
}