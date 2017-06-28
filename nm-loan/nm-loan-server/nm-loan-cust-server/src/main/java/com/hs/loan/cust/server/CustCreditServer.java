package com.hs.loan.cust.server;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.loan.cust.api.CustCreditApi;
import com.hs.loan.cust.dto.CustCreditDto;

/**
 * 客户授信额度 服务
 * @author zwr
 *
 */
@Service
@Transactional(readOnly=true)
public class CustCreditServer implements CustCreditApi {

	@Override
	public CustCreditDto getCustCredit(String custNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
