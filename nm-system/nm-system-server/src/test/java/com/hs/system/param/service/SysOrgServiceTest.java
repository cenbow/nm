package com.hs.system.param.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.base.entity.UserProfile;
import com.hs.base.test.BaseTest;
import com.hs.cache.config.DB;
import com.hs.cache.utils.RedisCacheUtil;
import com.hs.system.api.SysOrgApi;
import com.hs.system.entity.SysMenu;
import com.hs.system.entity.SysOrg;
import com.hs.system.entity.SysStaff;
import com.hs.system.entity.SysStaffOrg;
import com.hs.system.menu.service.SysMenuService;
import com.hs.system.org.service.SysOrgService;
import com.hs.system.staff.service.SysStaffService;
import com.hs.utils.StringUtils;

public class SysOrgServiceTest extends BaseTest{
	Logger logger = LoggerFactory.getLogger(getClass());
	
	String id = "402882365a4abc50015a4abc50ba0000";
	
	@Autowired
	private SysOrgApi sysOrgApi;
	
	@Test
	public void testQueryGroupListByType() {
//		save();
//		get();
		del();
	}
	
	void save(){
		SysOrg o = new SysOrg();
		o.setId(id);
		o.setOrgName("测试4");
		o.setOrgNo("12345");
		o.setParOrgNo("654321");
		SysOrg s = sysOrgApi.save(o);
		System.out.println(s.toString());
	}
	
	void get(){
		Map<String,Object> del = new HashMap<String,Object>();
		del.put("orgName", "测试");
		sysOrgApi.queryForList(del);
//		
		sysOrgApi.getByOrgId("402882365a4ab41a015a4ab41a8a0000");
		sysOrgApi.getByOrgNo("8801020100");
		sysOrgApi.getByOrgId("402882365a4ab41a015a4ab41a8a0000");
		sysOrgApi.getByOrgNo("8801020100");
		
	}
	
	void del(){
		UserProfile a = new UserProfile();
		a.setOrgNo("12312");
		sysOrgApi.queryForList(a);
		sysOrgApi.queryOrgForList(a);
		
		sysOrgApi.deleteByOrgId("402882365a4ab41a015a4ab41a8a0000");
		sysOrgApi.getByOrgId("402882365a4ab41a015a4ab41a8a0000");
	}
	
}
