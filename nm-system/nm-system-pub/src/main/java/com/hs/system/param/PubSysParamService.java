package com.hs.system.param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.cache.busi.service.RedisCacheApi;
import com.hs.cache.config.DB;
import com.hs.commons.constants.CommonConstant;
import com.hs.commons.service.IParamService;
import com.hs.system.entity.SysParam;
import com.hs.system.mapper.PubSysParamMapper;
import com.hs.system.util.BeanUtil;
import com.nm.cache.busi.service.OperateCacheServiceApi;

/**
 * 系统参数 业务处理
 * @author autocreate
 * @create 2015-09-23
 */
@Service
@Transactional(readOnly=true)
public class  PubSysParamService implements IParamService{
	@Autowired
	private PubSysParamMapper sysParamMapper;
	
	@Autowired
	private RedisCacheApi redisCacheApi;
	
	Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 查询 系统参数 列表
	 * @param param
	 * @return List<T>
	 */
	public List<SysParam> queryForList(Map<String, Object> param){
		List<SysParam> list = new ArrayList<SysParam>();
		boolean f = false;
		try {
			List<Map<String, Object>> lst = redisCacheApi.queryCacheByCondition(DB.SYS_PARAM, param);
			list = BeanUtil.ListMap2JavaBean(lst, SysParam.class);
		} catch (Exception e) {
			log.error("call remote exception!", e);
			f = true;
		}
		if(list.size()==0 || f){
			list = sysParamMapper.queryForList(param);
		}
		return list;
	}

	
	@Override
	public String getParam(String key) {
		Map<String,Object> map = new HashMap<>();
		map.put("cod", key);
		map.put("stat", CommonConstant.STAT_ENABLE);
		List<SysParam> list = this.queryForList(map);
		if(list == null || list.size() == 0){
			return "";
		}
		SysParam param = list.get(0);
		if(param == null) return "";
		return param.getVal();
	}
	
 
}