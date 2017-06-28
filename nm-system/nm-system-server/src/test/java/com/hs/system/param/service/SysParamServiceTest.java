package com.hs.system.param.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.base.test.BaseTest;
import com.hs.system.api.SysCodeInfoApi;
import com.hs.system.api.SysParamApi;
import com.hs.system.entity.SysCodInfo;
import com.hs.system.entity.SysParam;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;

public class SysParamServiceTest extends BaseTest{
	Logger logger = LoggerFactory.getLogger(getClass());
	
	String id = "402882365a3ba1e0015a3ba1e0800001";
	
	@Autowired
	private SysParamApi sysParamApi;
	
	@Autowired
	private SysCodeInfoApi sysCodeInfoApi;
	
	@Test
	public void testQueryGroupListByType() {
		
//		add();
//		update();
//		del();
//		query();
		get();
//		update();
		
		
	}
	
	public void add(){
		SysParam s = new SysParam();
		s.setId(id);
		s.setCod("qwqeqwess");
		s.setInstDate(new Date());
		s.setName("测试数据");
		s.setStat("10002001");
		s.setUpdtDate(new Date());
		s.setVal("testcode123");
		sysParamApi.insert(s);
		get();
	}
	public void update(){
		
		SysParam s = new SysParam();
		s.setId(id);
		s.setCod("1111111111");
		s.setName("测试数据");
		s.setStat("10002001");
		s.setVal("testcode123");
		Map<String,Object> mm = BeanUtils.bean2map(s);
		mm.put("instDate", new Date());
		mm.put("updtDate", new Date());
		sysParamApi.updateByPrimaryKeySelective(mm);
		get();
	}
	
	void get(){
		SysParam co = sysParamApi.getByPrimaryKey(id);
		if(co!=null){
			System.out.println("================"+co.toString());
		}
		
	}
	
	void del(){
		sysParamApi.deleteByPrimaryKey(id);
		get();
	}
	
	void query(){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("cod", "gz_template_batchDk_classpath");
		param.put("val", "gz/batchDk.ftl");
		List<SysParam> ll = sysParamApi.queryForList(param);
		for(SysParam s:ll){
			System.out.println("s.toString()="+s.toString());
		}
	}
}
