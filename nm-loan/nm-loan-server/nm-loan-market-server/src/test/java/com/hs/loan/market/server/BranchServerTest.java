package com.hs.loan.market.server;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.base.entity.Page;
import com.hs.base.test.BaseTest;
import com.hs.loan.market.dto.BranchDto;

public class BranchServerTest extends BaseTest{

	@Autowired
	private BranchServer branchServer;
	
	//@Test
	public void test() {
//		Page<BranchDto> page = new Page<BranchDto>();
//		page.setPageNo(1);
//		page.setPageSize(10);
//		Page<BranchDto> rs = branchServer.queryBranch(page);
//		System.out.println(rs.getList().size());
		branchServer.rmvByNo("测试1");
	}

}
