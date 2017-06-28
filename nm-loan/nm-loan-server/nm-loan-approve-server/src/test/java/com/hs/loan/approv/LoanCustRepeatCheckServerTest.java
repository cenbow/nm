package com.hs.loan.approv;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.base.test.BaseTest;
import com.hs.loan.approv.dto.LoanCustRepeatCheckDto;
import com.hs.loan.approve.server.LoanApprServer;

public class LoanCustRepeatCheckServerTest extends BaseTest{

	@Autowired	private LoanApprServer checkSer;
	
	@Test
	public void test() {
		try{
		List<LoanCustRepeatCheckDto> lst = checkSer.getList("8aaf071153e56a260153e585a1ad001d");
		System.err.println("123123123");
		System.err.println(lst.size());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
