package com.hs.loan.acct.server;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.test.BaseTest;
import com.hs.loan.acct.dto.RepayTypConfDto;

public class RepayTypConfServerTest extends BaseTest{

	@Autowired
	private RepayTypConfServer repayTypConfServer;
	
	//@Test
	public void test() {
		RepayTypConfDto dto = new RepayTypConfDto();
		dto.setConfName("xxx");
		//dto.setConfNo("123136516");
		dto.setInstDate(new Date());
		dto.setRepayCalc("31156eruyer");
		repayTypConfServer.save(dto);
	}
	
	//@Test
	public void test2() {
		repayTypConfServer.deleteByNo("ff808081511ed3d601511eea9edd0031");
		System.out.println("--------------------");
	}

}
