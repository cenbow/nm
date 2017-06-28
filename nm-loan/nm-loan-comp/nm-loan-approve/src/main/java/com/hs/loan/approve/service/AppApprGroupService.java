package com.hs.loan.approve.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.cache.busi.service.RedisCacheApi;
import com.hs.cache.config.DB;
import com.hs.loan.approve.entity.AppApprGroup;
import com.hs.loan.approve.mapper.AppApprGroupMapper;
import com.hs.system.util.BeanUtil;
import com.hs.utils.BeanUtils;
import com.nm.cache.busi.service.OperateCacheServiceApi;

/**
 * APP_审批组信息 业务处理
 * @author autocreate
 * @create 2015-11-23
 */
@Service
@Transactional(readOnly=true)
public class  AppApprGroupService{
	@Autowired
	private AppApprGroupMapper appApprGroupMapper;
	
	@Autowired
	private RedisCacheApi redisCacheApi;
	
	@Autowired
	private OperateCacheServiceApi operateCacheService;
	
	Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 新增 APP_审批组信息
	 * @param appApprGroup 新增对象
	 */
	@Transactional
	public void insert(AppApprGroup appApprGroup){
//		appApprGroupMapper.insert(appApprGroup);
		int num = appApprGroupMapper.insert(appApprGroup);
		if(num>0){
			Map<String, Object> map = BeanUtils.bean2map(appApprGroup);
			operateCacheService.addCache(DB.APP_APPR_GROUP, map);
		}
	}

	/**
	 * 通过主键修改 APP_审批组信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
//		appApprGroupMapper.updateByPrimaryKeySelective(map);
		String id = map.get("groupNo").toString();
		int num = appApprGroupMapper.updateByPrimaryKeySelective(map);
		if(num>0){
			 Map<String,Object> del = new HashMap<String,Object>();
			 del.put("groupNo", id);
			 AppApprGroup m = appApprGroupMapper.getByPrimaryKey(id);
			 Map<String,Object> news = BeanUtils.bean2map(m);
			 operateCacheService.modifyCacheByCondition(DB.APP_APPR_GROUP, del,news);
		}
	}
	
	/**
	 * 通过主键修改 APP_审批组信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateGrpByGrpNo(AppApprGroup appApprGroup){
//		appApprGroupMapper.updateGrpByGrpNo(appApprGroup);
		String id = appApprGroup.getGroupNo();
		appApprGroupMapper.updateGrpByGrpNo(appApprGroup);
		Map<String,Object> del = new HashMap<String,Object>();
		del.put("groupNo", id);
		AppApprGroup m = appApprGroupMapper.getByPrimaryKey(id);
		Map<String,Object> news = BeanUtils.bean2map(m);
		operateCacheService.modifyCacheByCondition(DB.APP_APPR_GROUP, del,news);
	}

	/**
	 * 通过主键删除 APP_审批组信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
//		appApprGroupMapper.deleteByPrimaryKey(primaryKey);
		int num = appApprGroupMapper.deleteByPrimaryKey(primaryKey);
		if(num>0){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("groupNo", primaryKey);
			operateCacheService.delCache(DB.APP_APPR_GROUP, map);
		}
	}

	

	/**
	 * 通过主键取得 APP_审批组信息 对象
	 * @param primaryKey 主键
	 * @return APP_审批组信息对象
	 */
	public AppApprGroup getByPrimaryKey(String id){
//		return appApprGroupMapper.getByPrimaryKey(id);
		AppApprGroup g = null;
		boolean f = false;
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("groupNo", id);
			List<Map<String,Object>> lst = redisCacheApi.queryCacheByCondition(DB.APP_APPR_GROUP, map);
			List<AppApprGroup> list = BeanUtil.ListMap2JavaBean(lst, AppApprGroup.class);
			if(!list.isEmpty()) {
				g = list.get(0);
			}
		} catch (Exception e) {
			log.error("call remote exception!", e);
			f = true;
		}
		if(g == null || f){
			g = appApprGroupMapper.getByPrimaryKey(id);
		}
		return g;
	}

	/**
	 * 查询 APP_审批组信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppApprGroup> queryForList(Map<String, Object> param){
//		return appApprGroupMapper.queryForList(param);
		List<AppApprGroup> list = new ArrayList<AppApprGroup>();
		boolean f = false;
		try {
			List<Map<String,Object>> lst = redisCacheApi.queryCacheByCondition(DB.APP_APPR_GROUP, param);
			list = BeanUtil.ListMap2JavaBean(lst, AppApprGroup.class);
		} catch (Exception e) {
			log.error("call remote exception!", e);
			f = true;
		}
		if(list.isEmpty() || f){
			list = appApprGroupMapper.queryForList(param);
		}
		return list;
	}
	
	/**
	 * 查询 APP_审批组信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppApprGroup> queryForPage(Page<AppApprGroup> page){
		appApprGroupMapper.queryForList(page.getPageParams());
		return (Page<AppApprGroup>)page.getPageParams().get(Page.KEY);
	}
	
	/**
	 * 查询 APP_审批组信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppApprGroup> queryApprGrpForPage(Page<AppApprGroup> page,UserProfile userProfile){
		page.getPageParams().put("staffNo", userProfile.getStaffNo());
		page.getPageParams().put("roleNos", userProfile.getRoleNoSet());
		page.getPageParams().put("orgNo", userProfile.getOrgNo());
		return this.queryForPage(page);
	}
}