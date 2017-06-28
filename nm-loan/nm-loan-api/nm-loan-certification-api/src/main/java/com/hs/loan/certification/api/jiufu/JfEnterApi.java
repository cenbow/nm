package com.hs.loan.certification.api.jiufu;

import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;

public interface JfEnterApi {

	/**
	 * 玖富黑名单验证
	 * 
	 * @return true表示在玖富黑名单中，false表示没在玖富黑名单中
	 * 
	 * @throws ServiceException
	 * @throws AppException
	 */
	public boolean validateCoustmor(String certNo,String custName)throws ServiceException,AppException;
	
	
	
}
