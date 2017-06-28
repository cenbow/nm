package com.hs.system.param.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.base.test.BaseTest;
import com.hs.system.entity.SysGroup;

public class SysGroupServiceTest extends BaseTest{
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	SysGroupService sysGroupService;

//	@Test
	public void testGetByPrimaryKey() {
		sysGroupService.getByPrimaryKey("");
	}

//	@Test
	public void testQueryForList() {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("codeType", "test");
		List<SysGroup> list = sysGroupService.queryForList(param);
		logger.info(""+list.size());
	}

}
