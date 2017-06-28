package com.hs.loan.prod.server;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.base.test.BaseTest;
import com.hs.loan.prod.dto.PubProdDto;

public class ProdServerTest extends BaseTest{

	
	@Autowired
	ProdServer prodser;
	
	/*@Test
	public void testQueryProd() {
		
		Page<ProdBaseInfoDto> page = new Page();
		 page = prodser.queryProd(page);
		
		System.out.println(page.getList().get(0).getPubProd().getProdNo());
	
	}*/
	
	 @Test
	public void testtryCalProdFee() {
		
	//	ProFeeCalResultDto dd= prodser.tryCalProdFee("ff8080815147d0e0015147e80e240006", new BigDecimal("5000"),6);
		Map map = new  java.util.HashMap<>();
		map.put("branchNo", "23");
		map.put("orgNo", "1223");
		map.put("staffNo", "23");
		map.put("goodsType", "23");
		map.put("custType", "23");
		List<PubProdDto>  ss= prodser.queryProdForLoan(map);
	
		System.out.println("-----------------"+ss.size());
	}

}
