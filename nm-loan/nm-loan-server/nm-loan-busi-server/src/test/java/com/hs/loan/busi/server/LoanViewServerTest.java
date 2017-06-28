package com.hs.loan.busi.server;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import scala.annotation.meta.param;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.test.BaseTest;
import com.hs.loan.busi.dto.CrmCustomerOrderDto;
import com.hs.loan.busi.dto.LoanListOutDto;

public class LoanViewServerTest extends BaseTest{
	@Autowired
	private LoanViewServer server;
	
	
	
//	@Test
	public void testQueryLoanList() {
		Page<LoanListOutDto> page = new Page<>();
		server.queryLoanList(page , new UserProfile());
		System.out.println();
	}
	
	@Test
	public void testQueryCrmOrderList() {
		Page<CrmCustomerOrderDto> page = new Page<>();
		UserProfile userProfile = new UserProfile();
		userProfile.setStaffNo("cd-xiongy");
		userProfile.setOrgNo("8800000000");
		Set<String> roleNoSet = new HashSet<>();
		roleNoSet.add("r_sale_mgr");
		//roleNoSet.add("r_sale_staff");
		userProfile.setRoleNoSet(roleNoSet);
		page = server.queryCrmOrderList(page, userProfile);
		List<CrmCustomerOrderDto> crmCustomerOrderDtos = page.getList();
		for (CrmCustomerOrderDto crmCustomerOrderDto : crmCustomerOrderDtos) {
			System.out.println(crmCustomerOrderDto.getCustName() + "--" + crmCustomerOrderDto.getOrderId());
		}
		//page.toPage(CrmCustomerOrderDto.class);
		
	}
	
	@Test
	public void testQueryAtt() {
		
	}

	
	@Test
	public void testUpdateCrmOrder() {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("handType", "70028006");
		paramMap.put("orderId", "33BRHPPAQTMSA71XDZNYMS1SA4U7SPTL");
		paramMap.put("loanNo","880101030105091513532420004");
		paramMap.put("remark","dfasfkakfajkfkjasfjkasjkfa");
		UserProfile userProfile = new UserProfile();
		userProfile.setStaffNo("cd-chenhb");
		userProfile.setOrgNo("8800000000");
		Set<String> roleNoSet = new HashSet<>();
		roleNoSet.add("r_sale_mgr_area");
		//roleNoSet.add("r_sale_staff");
		userProfile.setRoleNoSet(roleNoSet);
		server.updateCrmOrder(paramMap, userProfile);
	}
/*	@Test
	public void testGetLoanView() {
		server.getLoanView("001", new UserProfile());
		System.out.println();
	}*/
	@Test
	public void testGetLoanView() {
		server.getLoanView("00100110501012016031100007", new UserProfile());
		System.out.println();
	}

}
