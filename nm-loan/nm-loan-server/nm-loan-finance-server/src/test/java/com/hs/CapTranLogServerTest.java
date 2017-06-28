package com.hs;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.base.entity.Page;
import com.hs.base.test.BaseTest;
import com.hs.loan.finance.dto.AccCapTranLogDto;
import com.hs.loan.finance.server.CapTranLogServer;

public class CapTranLogServerTest extends BaseTest{
	
	@Autowired
	private CapTranLogServer capTranLogServer;

	@Test
	public void test() {
		try{
		Page<AccCapTranLogDto> page = new Page<AccCapTranLogDto>();
		page.setPageNo(1);
		page.setPageSize(20);
		//page.getParams().put("id", "749dea41-eb48-11e5-b1b5-d89d672af1cc");
		page = capTranLogServer.queryForPage(page);
		System.out.println(page.getList().size());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
