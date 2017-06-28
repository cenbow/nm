package com.hs;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.base.entity.Page;
import com.hs.base.test.BaseTest;
import com.hs.loan.finance.dto.AccCapTranDtlDto;
import com.hs.loan.finance.server.CapTranDtlServer;

public class CapTranDtlServerTest extends BaseTest{
	
	@Autowired
	private CapTranDtlServer capTranDtlServer;

	@Test
	public void test() {
		try{
		Page<AccCapTranDtlDto> page = new Page<AccCapTranDtlDto>();
		page.setPageNo(1);
		page.setPageSize(20);
		page.getParams().put("logId", "0E6AC1D7D66B4C4B9284CB9ED89C13C6");
		page = capTranDtlServer.queryForPage(page);
		System.out.println(page.getList().size());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
