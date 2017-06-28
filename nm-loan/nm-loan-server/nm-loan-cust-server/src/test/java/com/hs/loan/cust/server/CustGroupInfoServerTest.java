package com.hs.loan.cust.server;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.base.test.BaseTest;
import com.hs.loan.cust.dto.CustGroupInfoDto;
import com.hs.utils.DateUtils;

public class CustGroupInfoServerTest extends BaseTest{

	@Autowired
	private CustGroupInfoServer custGroupInfoServer;
	
	@Test
	public void test() {
		CustGroupInfoDto custGroupInfoDto = new CustGroupInfoDto();
	//	custGroupInfoDto.setCustGroup("12345");
		custGroupInfoDto.setCustGroupName("yyyyy");
		custGroupInfoDto.setInstPerson("123");
		custGroupInfoDto.setBeginDate(DateUtils.getCurrentDate());
		custGroupInfoDto.setEndDate(DateUtils.getCurrentDate());
		custGroupInfoServer.save(custGroupInfoDto);
	}

}
