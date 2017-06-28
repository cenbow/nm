package com.nm.impl.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.system.entity.SysCodInfo;
import com.nm.base.framework.core.validate.Validator;
import com.nm.cmd.CodeApiCmd;
import com.nm.mapper.login.SysCodInfoApiMapper;
import com.nm.mybatis.mapper.entity.Example;
import com.nm.service.login.CodeCacheApiService;

@Service
public class CodeCacheApiServiceImpl implements CodeCacheApiService {
	
	private static final String DEFUALT_GROUP_KEY = "__defaultGroup";
	
	private static final String LEVEL_KEY = "level"; //层级
	@Autowired private SysCodInfoApiMapper sysCodInfoApiMapper;
	
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> getCode(List<CodeApiCmd> codes) {
		Map<String,Object> re = new HashMap<String,Object>(); //返回结果
		Example example = new Example(SysCodInfo.class);
		
		List<String> types = new ArrayList<String>();
		StringBuffer typeSb = new StringBuffer();
		
		for ( CodeApiCmd code : codes ) {
			types.add(code.getType());
			typeSb.append(",").append(code.getType());
		}

		example.selectProperties("codTyp", "codVal", "codName","stat");
		example.createCriteria().andIn("codTyp", types);
		example.orderBy("codTyp").asc().orderBy("codVal").asc();
		List<SysCodInfo> sysCodInfos = sysCodInfoApiMapper.selectByExample(example);
		
		String type;
		Map<String, Object> map;
		List<Object> list;
		
		for (SysCodInfo sysCodInfo : sysCodInfos) {
			type = sysCodInfo.getCodTyp();
			if (!re.containsKey(type)) {
				 map = new HashMap<String, Object>();
				 map.put(LEVEL_KEY, 1);
				 map.put(DEFUALT_GROUP_KEY, new ArrayList<Object>());
				 re.put(type, map);
			}
			
			map = (Map<String, Object>) re.get(type);
			list = (List<Object>) map.get(DEFUALT_GROUP_KEY);
			list.add(sysCodInfo);
		}
		
		String group;
		List<Map<String, Object>> groupMaps = sysCodInfoApiMapper.selectCodeGroupInfo(types);
		for (Map<String, Object> groupMap : groupMaps) {
			type = (String) groupMap.get("codTyp");
			if (!re.containsKey(type)) continue;
			
			group = (String) groupMap.get("groupCod");
			map = (Map<String, Object>) re.get(type);
			
			if (!map.containsKey(group)) {
				map.put(group, new ArrayList<Object>());
			}
			
			list = (List<Object>) map.get(group);
			list.add(groupMap);
		}
		
		return re;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> getCodeV2(List<CodeApiCmd> codes) {
		Map<String,Object> re = new HashMap<String,Object>(); //返回结果
		Example example = new Example(SysCodInfo.class);

		List<String> types = new ArrayList<String>();
		StringBuffer typeSb = new StringBuffer();
		String type;
		Map<String, Object> map;
		for ( CodeApiCmd code : codes ) {
			types.clear();
			types.add(code.getType());
			type=code.getType();
			//List<Map<String, Object>> groupMaps = sysCodInfoApiMapper.selectCodeGroupInfo(types);
			example.selectProperties("codTyp", "codVal", "codName","stat");
			example.createCriteria().andIn("codTyp", types);
			example.orderBy("codTyp").asc().orderBy("codVal").asc();
			List<SysCodInfo> sysCodInfos = sysCodInfoApiMapper.selectByExample(example);
			map = new HashMap<String, Object>();
			map.put(LEVEL_KEY, 1);
			map.put("arryList",sysCodInfos);
			//map.put(type,sysCodInfos);
			re.put(type, map);
		}



		return re;
	}

	@Override
	public String getCodeByNameType(String type, String name) {
		Validator.init(type, "码值类型").required().end();
		Validator.init(name, "码值名称").required().end();
		return sysCodInfoApiMapper.selectCodeByNameType(type,name);
	}

	@Override
	public String getCodeNameByTypeAndNum(String type, String codValue) {
		return sysCodInfoApiMapper.getCodeNameByTypeAndNum(type,codValue);
	}

	@Override
	public String getCodeNo(String type, String name) {
		return null;
	}
}
