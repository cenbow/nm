package com.hs.loan.busi.server;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.base.entity.UserProfile;
import com.hs.base.test.BaseTest;
import com.hs.loan.busi.dto.LoanAcctInDto;

public class LoanAcctServerTest extends BaseTest{
	
	@Autowired
	private LoanAcctServer loanAcctServer;
	
	@Test
	public void testsaveLoanFee() {

		LoanAcctInDto dto = new LoanAcctInDto();
		
			dto.setSaleChanl("123213");
			 
			//loanAcctServer.addLoanAcct(dto, new UserProfile());
		
		//loanAcctServer.loanTryCalc("1", new  BigDecimal("1"), 3, "1,2");
//		loanAcctServer.queryLoanHistoryList("510107199301014037");
	}

}
