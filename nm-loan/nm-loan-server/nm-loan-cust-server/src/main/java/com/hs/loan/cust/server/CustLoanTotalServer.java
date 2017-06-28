package com.hs.loan.cust.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.loan.cust.api.CustLoanTotalApi;
import com.hs.loan.cust.dto.CustLoanTotalDto;
import com.hs.loan.cust.entity.AppCustLoanTotal;
import com.hs.loan.cust.service.AppCustLoanTotalService;
import com.hs.utils.BeanUtils;


/**
 * 客户分期信息汇总
 * @author zwr
 *
 */
@Service
@Transactional(readOnly=true)
public class CustLoanTotalServer implements CustLoanTotalApi {

	@Autowired
	private AppCustLoanTotalService appCustLoanTotalService;
	
	/**
	 * 通过客户号 获取客户分期汇总信息
	 * @param custNo
	 * @return
	 */
	@Override
	public CustLoanTotalDto getByNo(String custNo) {
		AppCustLoanTotal appCustLoanTotal = appCustLoanTotalService.getByNo(custNo);
		CustLoanTotalDto custLoanTotalDto = (CustLoanTotalDto) BeanUtils.copyPropertiesNotNull( new CustLoanTotalDto(), appCustLoanTotal);
		return custLoanTotalDto;
	}

}
