package com.hs.loan.cust.server;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.base.test.BaseTest;
import com.hs.loan.cust.dto.CustInfoDto;
import com.hs.loan.cust.dto.CustStarLevelBoDto;

public class CustViewServerTest extends BaseTest{

	@Autowired
	private CustViewServer custViewServer;
	
	//@Test
	public void test() {
		CustStarLevelBoDto custStarLevelBoDto = custViewServer.getCustStarLevel("001");
		System.out.println(custStarLevelBoDto);
	}
	
	//@Test
	public void test1() {
		CustInfoDto dto = custViewServer.getCustInfoByNo("001");
		System.out.println("============================>>>>>>>>>>>>>>>"+dto);
	}

}
