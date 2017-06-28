package com.hs.system.param.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.cache.busi.service.RedisCacheApi;
import com.hs.cache.config.DB;
import com.hs.system.api.SysParamApi;
import com.hs.system.entity.SysParam;
import com.hs.system.param.mapper.SysParamMapper;
import com.hs.system.util.BeanUtil;
import com.hs.utils.BeanUtils;
import com.nm.cache.busi.service.OperateCacheServiceApi;

/**
 * 系统参数 业务处理
 * @author autocreate
 * @create 2015-09-23
 */
@Service
@Transactional(readOnly=true)
public class  SysParamService implements SysParamApi{
	@Autowired
	private SysParamMapper sysParamMapper;
	
	@Autowired
	private OperateCacheServiceApi operateCacheServiceApi;
	
	@Autowired
	private RedisCacheApi redisCacheApi;
	/**
	 * 新增 系统参数
	 * @param vo
	 * @return
	 */
	@Transactional
	public int insert(SysParam sysParam){
		int n =sysParamMapper.insert(sysParam);
		if(n>0){
			Map<String,Object> map = BeanUtils.bean2map(sysParam);
			operateCacheServiceApi.addCache(DB.SYS_PARAM, map);
		}
		return n;
	}

	/**
	 * 通过主键修改 系统参数
	 * @param map
	 * @return
	 */
	@Transactional
	public int updateByPrimaryKeySelective(Map<String, Object> map){
		Map<String,Object> del = new HashMap<String, Object>();
		String id = map.get(DB.ID).toString();
		del.put(DB.ID, id);
		int n = sysParamMapper.updateByPrimaryKeySelective(map);
		if(n>0){
			SysParam parm = sysParamMapper.getByPrimaryKey(id);
			Map<String, Object> mm = BeanUtils.bean2map(parm);
			operateCacheServiceApi.modifyCacheByCondition(DB.SYS_PARAM,del,mm);
		}
		return n;
	}

	/**
	 * 通过主键删除 系统参数
	 * @param primaryKey
	 * @return
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){

		int n = sysParamMapper.deleteByPrimaryKey(primaryKey);
		if(n>0){
			Map<String,Object> conditionMap = new HashMap<String,Object>();
			conditionMap.put("id", primaryKey);
			operateCacheServiceApi.delCache(DB.SYS_PARAM, conditionMap);
		}
	}

	/**
	 * 通过主键取得 系统参数 对象
	 * @param id
	 * @return
	 */
	public SysParam getByPrimaryKey(String primaryKey){
		SysParam sysParam  = null;
		boolean f = false;
		try {
			Map<String,Object> conditionMap = new HashMap<String,Object>();
			conditionMap.put("id", primaryKey);
			List<Map<String,Object>> lzt = redisCacheApi.queryCacheByCondition(DB.SYS_PARAM, conditionMap);
			List<SysParam> list = BeanUtil.ListMap2JavaBean(lzt, SysParam.class);
			sysParam = null;
			if(!list.isEmpty()){
				sysParam = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			f = true;
		}
		if(sysParam==null || f){
			sysParam = sysParamMapper.getByPrimaryKey(primaryKey);
		}
		return sysParam;
	}

	/**
	 * 查询 系统参数 列表
	 * @param param
	 * @return List<T>
	 */
	public List<SysParam> queryForList(Map<String, Object> param){
		List<SysParam> list = new ArrayList<SysParam>();
		boolean f = false;
		try {
			List<Map<String,Object>> lzt = redisCacheApi.queryCacheByCondition(DB.SYS_PARAM, param);
			list = BeanUtil.ListMap2JavaBean(lzt, SysParam.class);
		} catch (Exception e) {
			e.printStackTrace();
			f = true;
		}
		if(list.isEmpty() || f){
			list = sysParamMapper.queryForList(param);
		}
		return list;
	}
	
	/**
	 * 查询 系统参数 分页列表
	 * @param param
	 * @return List<T>
	 */
	public Page<SysParam> queryForPage(Page<SysParam> page){
		sysParamMapper.queryForList(page.getPageParams());
		return (Page<SysParam>)page.getPageParams().get(Page.KEY);
	}
}