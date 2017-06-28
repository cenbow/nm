package com.hs;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.base.entity.UserProfile;
import com.hs.base.test.BaseTest;
import com.hs.loan.finance.dto.AccRepayDgRegDto;

public class AccRepayAdvanRegServerTest2 extends BaseTest{
	
	@Autowired
	//private AccRepayAdvanRegServer accRepayAdvanRegServer;

	@Test
	public void test() {
		 UserProfile userProfile = new UserProfile();
		 userProfile.setStaffName("123");
		 userProfile.setStaffNo("admin");
		 userProfile.setOrgNo("111");
		AccRepayDgRegDto dto = new AccRepayDgRegDto();
		dto.setLoanNo("002002001022016031100030");
		dto.setDgType("50201002");
		
		// accRepayAdvanRegServer.saveLoanRepayDg(dto, userProfile);
		
	}

}
