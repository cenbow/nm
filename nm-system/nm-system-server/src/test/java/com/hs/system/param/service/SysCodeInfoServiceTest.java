package com.hs.system.param.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.hs.system.entity.SysCodInfo;

public class SysCodeInfoServiceTest extends BaseTest{
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SysCodeInfoApi sysCodeInfoApi;
	
	@Test
	public void testQueryGroupListByType() {
		
//		add();
//		get();
//		get();
//		update();
		get();
//		add();
//		del();
//		query();
		
	}
	
	public void add(){
		SysCodInfo s = new SysCodInfo();
		s.setCodName("测试添加");
		s.setCodVal("wef23fwefs");
		s.setCodTyp("capitalRepresenting");
		s.setStat("10002001");
		sysCodeInfoApi.save(s);
	}
	public void update(){
		SysCodInfo s = new SysCodInfo();
		s.setId("402882365a3ba1e0015a3ba1e0800001");
		s.setCodName("测试添加");
		s.setCodVal("5551");
		s.setCodTyp("capitalRepresenting");
		s.setStat("10002001");
		sysCodeInfoApi.save(s);
	}
	
	void get(){
		SysCodInfo co = sysCodeInfoApi.getByPrimaryKey("003");
		if(co!=null){
			System.out.println("================"+co.toString());
		}
		
	}
	
	void del(){
		get();
		sysCodeInfoApi.deleteByPrimaryKey("402882365a3fd72c015a3fd738ba0001");
		get();
	}
	
	void query(){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("codVal", "004");
		param.put("codTyp", "capitalRepresenting");
		List<SysCodInfo> ll = sysCodeInfoApi.queryForList(param);
		for(SysCodInfo s:ll){
			System.out.println("s.toString()="+s.toString());
		}
	}
}
