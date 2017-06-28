package com.hs.loan.contract.server;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.contract.api.TestLoanContractSignApi;
import com.hs.loan.contract.entity.AppContractSignInfo;
import com.hs.loan.contract.service.AppContractSignInfoService;
import com.hs.utils.StringUtils;
/**
 */
@Service
@Transactional(readOnly=true)
public class TestContractBuidServer implements TestLoanContractSignApi {
	@Autowired
	private AppContractSignInfoService contractSignInfoService;
	/**
	 * 更新客户签约状态
	 * @param contractNo
	 * @throws ServiceException
	 * @throws AppException
	 */
	@Transactional
	public String  getsignUrlByLoanNo(String loanNo) throws  ServiceException,AppException{
		AppContractSignInfo appContractSignInfo	=contractSignInfoService.getByPrimaryKey(loanNo);
		if(appContractSignInfo==null || StringUtils.isEmpty(appContractSignInfo.getContractUrl())){
			throw new ServiceException("生成合同失败");
		}
		return appContractSignInfo.getContractUrl();
	}

}
