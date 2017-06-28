package com.hs.loan.market.server;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.base.entity.Page;
import com.hs.base.test.BaseTest;
import com.hs.loan.market.dto.BranchDto;

public class BranchGroupInfoServerTest extends BaseTest{

	@Autowired
	private BranchGroupInfoServer branchGroupInfoServer;
	
	//@Test
	public void test() {
		String value = "8a7cf674508edf0101508edf01c70000";
		Page<BranchDto> page = new Page<BranchDto>();
		page.getParams().put("groupNo", value);
		
		page = branchGroupInfoServer.queryBranchPage(page);
		System.out.println(page.getList().size());
	}

}
