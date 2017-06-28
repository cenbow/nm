package com.hs;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.base.entity.Page;
import com.hs.base.test.BaseTest;
import com.hs.loan.finance.dto.AccCapTranLogBatDto;
import com.hs.loan.finance.server.CapTranLogBatServer;

public class CapTranLogBatServerTest extends BaseTest{
	
	@Autowired
	private CapTranLogBatServer capTranLogBatServer;

	@Test
	public void test() {
		try{
		Page<AccCapTranLogBatDto> page = new Page<AccCapTranLogBatDto>();
		page.setPageNo(1);
		page.setPageSize(20);
		//page.getParams().put("id", "749dea41-eb48-11e5-b1b5-d89d672af1cc");
		page = capTranLogBatServer.queryForPage(page);
		AccCapTranLogBatDto d = page.getList().get(0);
		//System.err.println(new String(d.getRecvData(), "GB2312"));
		System.out.println(page.getList().size());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
