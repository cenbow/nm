package com.hs.loan.acct.server;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.base.test.BaseTest;

public class RepayKindConfServerTest extends BaseTest{

	@Autowired
	private RepayKindConfServer repayKindConfServer;
	
	//@Test
	public void test() {
		repayKindConfServer.deleteByNo("ff808081511ed3d601511edc1e7d0017");
		System.out.println("------------------");
	}

}
