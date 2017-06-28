package com.hs;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.base.entity.Page;
import com.hs.base.test.BaseTest;
import com.hs.loan.finance.dto.AccLoanAcctInstDgDto;
import com.hs.loan.finance.server.RepayDgRegServer;

public class RepayDgRegServerTest extends BaseTest {
	@Autowired
	private RepayDgRegServer repayDgRegServer;

	@Test
	public void test() {
		Page<AccLoanAcctInstDgDto> page = new Page<AccLoanAcctInstDgDto>();
		page.setPageNo(1);
		page.setPageSize(20);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loanNo", "001001108022016031400001");
		page.setParams(map);
//		page = repayDgRegServer.queryAccLoanAcctInstLst(page);
		System.out.println("===================" + page.getList().size());
	}
}
