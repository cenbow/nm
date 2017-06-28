package com.hs.system.param.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.base.test.BaseTest;
import com.hs.cache.utils.RedisCacheUtil;
import com.hs.system.entity.SysStaff;
import com.hs.system.entity.SysStaffOrg;
import com.hs.system.staff.service.SysStaffService;
import com.hs.utils.StringUtils;

public class SysStaffServiceTest extends BaseTest{
	Logger logger = LoggerFactory.getLogger(getClass());
	
	String id = "402882365a45d2b4015a45d2b4350000";
	
	@Autowired
	private SysStaffService sysStaffApi;
	@Autowired
	private RedisCacheUtil<Object> redisCache;
	
	public static String aa =  "SYS_STAFF*staffNo:zz-lipf*";
	
	@Test
	public void testQueryGroupListByType() {
		
//		reset();		
//		change();
//		saveStaffOrg();
		del();
	}
	
	public void add(){
		
		
//		SysStaff s2 = new SysStaff();
//		s2.setStaffNo("testcache22");
//		s2.setLoginPwd("123456");
//		s2.setId(null);
//		sysStaffApi.save(s2);
//		
//		SysStaff s = get("2D5C5D6B20031475E0502D0A14BF4191");
//		s.setStaffNo("testcache");
//		s.setId(null);
//		sysStaffApi.save(s);
	}
//	public void update(){
//		
//		SysParam s = new SysParam();
//		s.setId(id);
//		s.setCod("1111111111");
//		s.setName("测试数据");
//		s.setStat("10002001");
//		s.setVal("testcode123");
//		Map<String,Object> mm = BeanUtils.bean2map(s);
//		mm.put("instDate", new Date());
//		mm.put("updtDate", new Date());
//		sysStaffApi.updateByPrimaryKeySelective(mm);
//		get();
//	}
	
	SysStaff  get(String id){
		SysStaff co = sysStaffApi.getByStaffId(id);
		if(co!=null){
			System.out.println("================"+co.toString());
		}
		return co;
		
	}
	
	void del(){
		sysStaffApi.deleteByPrimaryKey(id);
		get(id);
	}
//	
//	void query(){
//		Map<String,Object> param = new HashMap<String, Object>();
//		param.put("cod", "gz_template_batchDk_classpath");
//		param.put("val", "gz/batchDk.ftl");
//		List<SysParam> ll = sysParamApi.queryForList(param);
//		for(SysParam s:ll){
//			System.out.println("s.toString()="+s.toString());
//		}
//	}
	
	void getStaff(){
		sysStaffApi.getByStaffNo("testcache");
	}
	
	void reset(){
		sysStaffApi.resetPassword("testcache");
		SysStaff sysStaff = sysStaffApi.getByStaffNo("testcache");
		System.out.println(sysStaff.toString());
	}
	
	void change(){
		String str = StringUtils.MD5Encode("123456").toLowerCase();
		String str2= StringUtils.MD5Encode("asdasdq").toLowerCase(); 
		sysStaffApi.changePassword("testcache", str, str2);
		SysStaff sysStaff = sysStaffApi.getByStaffNo("testcache");
		System.out.println(sysStaff.toString());
	}
	
	void saveStaffOrg(){
		SysStaffOrg sys = new SysStaffOrg();
		sys.setOrgId("396C7584FFFFBF395C0CDC743C270E31");
		sys.setStaffId("402882365a45d2b4015a45d2b4350000");
		sysStaffApi.saveStaffOrg(sys);	
		SysStaff st = sysStaffApi.getByStaffId("402882365a45d2b4015a45d2b4350000");
		System.out.println(st.toString());
	}
}
