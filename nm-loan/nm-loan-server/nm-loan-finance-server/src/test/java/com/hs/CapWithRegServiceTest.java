package com.hs;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.test.BaseTest;
import com.hs.loan.finance.bo.SingleDkDto;
import com.hs.loan.finance.contant.FinanceConstant;
import com.hs.loan.finance.dto.SingleRepayDto;
import com.hs.loan.finance.server.AccCapWithServer;
import com.hs.loan.finance.util.PayChanType;
import com.hs.loan.finance.withpay.dto.LoanRepayWithDto;

public class CapWithRegServiceTest extends BaseTest{
	
	@Autowired
	private AccCapWithServer accCapWithServer;

	@Test
	public void test() {
		try{
			UserProfile user = new UserProfile();
			user.setLoginNo("123");
//		Page<SingleRepayDto> page = new Page<SingleRepayDto>();
//		page.setPageNo(1);
//		page.setPageSize(20);
//		page.getParams().put("loanNo", "00100110501032016031100003");
//		page.getParams().put("repayNum", "1");
		//page.getParams().put("acctName", "贺蜜");//扣款帐户名
		//分页查询代扣纪录
//		page =  accCapWithServer.querySingleRepayListForPage(page);
//		System.out.println(page.getList().size());
//		accCapWithServer.singleRepay(page.getList().get(0),user);
//		accCapWithServer.queryBatchRepay("1459827018301", "");// 1459827139656
//		accCapWithServer.executeBatchRepayCallback("1459429596866");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("withStat", FinanceConstant.WITHSTAT_UNDK);
		params.put("exportTxtType", PayChanType.ALLINPAY);
//		params.put("repayNum", "1");
//		params.put("bankNo", "105");
		params.put("loanNo", "00100110501012016031100010");
//		params.put("acctName", "卜涛");
		//params.put("chanNo", "001");
		//accCapWithServer.batchRepay(params);
		System.err.println(accCapWithServer.batchDkFileExport(params));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
//	@Test
//	public void test2() {
//		Page<LoanRepayWithDto> page = new Page<LoanRepayWithDto>();
//		page.setPageNo(1);
//		page.setPageSize(20);
//		page.getParams().put("loanNo", "8a7cf674508f65e401508f65e4600000");
//		page.getParams().put("acctName", "贺蜜");//扣款帐户名
//		//分页查询代扣纪录
//		page =  appFinanceCapWithRegServer.queryLoanPayWithInfo(page);
//		System.out.println(page.getList().size());
//	}

}
