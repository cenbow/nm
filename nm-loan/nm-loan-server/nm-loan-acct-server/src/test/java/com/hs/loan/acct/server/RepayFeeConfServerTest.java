package com.hs.loan.acct.server;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.base.entity.Page;
import com.hs.base.test.BaseTest;
import com.hs.loan.acct.dto.RepayFeeConfDto;
import com.hs.loan.acct.entity.PubRepayFeeConf;
import com.hs.loan.acct.service.PubRepayFeeConfService;

public class RepayFeeConfServerTest extends BaseTest{
	@Autowired
	private RepayFeeConfServer server;
	@Autowired
	private PubRepayFeeConfService pubRepayFeeConfService;
	
	@Test
	public void testQueryFeeConf() {
		Page<RepayFeeConfDto> page = new Page<>();
		page = server.queryFeeConf(page);
		System.out.println(page.getList().get(0).getFeeName());
	}

	//@Test
	/*public void test() {
		String ids = "'8a7cf67450892e4b015089a58ddc0002','8a7cf67450892e4b015089a97e610003'";
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("feeNo", ids);
		List<PubRepayFeeConf> lst = pubRepayFeeConfService.queryProdUnUsedFeeConf(param);
		System.out.println(lst.size());
	}*/
	//@Test
	/*public void test() {
		String ids = "'8a7cf67450892e4b015089a58ddc0002','8a7cf67450892e4b015089a97e610003'";
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("feeNo", ids);
		List<PubRepayFeeConf> lst = pubRepayFeeConfService.queryProdUnUsedFeeConf(param);
		System.out.println(lst.size());
	}*/
	/*@Test
	public void test() {
		RepayFeeConfDto repayFeeConfDto = new  RepayFeeConfDto();
		repayFeeConfDto.setFeeName("1");
		repayFeeConfDto.setFeeCalc("123");
		repayFeeConfDto.setSubjNo("1108");
		repayFeeConfDto.setFeeMode("123");
		repayFeeConfDto.setFeeTyp("112");
		server.save(repayFeeConfDto);
		
	}*/
/*	@Test
	public void test() {
		
		server.deleteByNo("8a7cdec3513d502301513dd1f5e50035");
		
	}
*/	
	
	
	
	
}
