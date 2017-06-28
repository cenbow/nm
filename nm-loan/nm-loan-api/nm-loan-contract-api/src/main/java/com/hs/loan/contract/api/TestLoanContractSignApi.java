package com.hs.loan.contract.api;

import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;

public interface TestLoanContractSignApi {
	 
	public String  getsignUrlByLoanNo(String loanNo) throws  ServiceException,AppException;
	
	
}
