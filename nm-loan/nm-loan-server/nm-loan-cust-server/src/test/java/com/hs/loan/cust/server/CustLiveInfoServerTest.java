package com.hs.loan.cust.server;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.base.test.BaseTest;
import com.hs.loan.cust.dto.CustLiveInfoDto;

public class CustLiveInfoServerTest extends BaseTest {

	@Autowired
	private CustLiveInfoServer custLiveInfoServer;
	
	//@Test
	public void test() {
		List<CustLiveInfoDto> lst =  custLiveInfoServer.getCrtCustLiveInfoLst("001");
		System.out.println(lst.size());
	}

}
