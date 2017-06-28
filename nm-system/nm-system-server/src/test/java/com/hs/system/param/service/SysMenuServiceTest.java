package com.hs.system.param.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.base.test.BaseTest;
import com.hs.cache.utils.RedisCacheUtil;
import com.hs.system.entity.SysMenu;
import com.hs.system.entity.SysStaff;
import com.hs.system.entity.SysStaffOrg;
import com.hs.system.menu.service.SysMenuService;
import com.hs.system.staff.service.SysStaffService;
import com.hs.utils.StringUtils;

public class SysMenuServiceTest extends BaseTest{
	Logger logger = LoggerFactory.getLogger(getClass());
	
	String id = "402882365a49e408015a49e4089d0000";
	
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private RedisCacheUtil<Object> redisCache;
	
	@Test
	public void testQueryGroupListByType() {
//		save();
//		get();
		del();
	}
	
	void save(){
//		SysMenu ss = sysMenuService.getByPrimaryKey(id);
//		System.out.println(ss.toString());
		SysMenu m = new SysMenu();
		m.setId(id);
		m.setMenuName("测试2");
		m.setImg("fa fa-tv");
		SysMenu as = sysMenuService.save(m);
		System.out.println(as.toString());
		
	}
	
	void get(){
		Map<String,Object> param = new HashMap<String, Object>();
//		param.put("menuName", "测试2");
		param.put("parMenuId", "8a7cf67450797ef101507990e34e0003");
		List<SysMenu> ll = sysMenuService.queryForList(param);
		for(SysMenu s:ll){
			System.out.println(s.toString());
		}
	}
	
	void del(){
		sysMenuService.deleteByPrimaryKey(id);
		SysMenu ss = sysMenuService.getByPrimaryKey(id);
		System.out.println(ss.toString());
	}
	
}
