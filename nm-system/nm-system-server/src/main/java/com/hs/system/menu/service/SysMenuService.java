package com.hs.system.menu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.ServiceException;
import com.hs.cache.busi.service.RedisCacheApi;
import com.hs.cache.config.DB;
import com.hs.system.api.SysMenuApi;
import com.hs.system.entity.SysMenu;
import com.hs.system.menu.mapper.SysMenuMapper;
import com.hs.system.util.BeanUtil;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;
import com.nm.cache.busi.service.OperateCacheServiceApi;

/**
 * 菜单 业务处理
 * @author autocreate
 * @create 2015-09-23
 */
@Service
@Transactional(readOnly=true)
public class  SysMenuService implements SysMenuApi{
	@Autowired
	private SysMenuMapper sysMenuMapper;
	
	@Autowired
	private RedisCacheApi redisCacheApi;
	
	@Autowired
	private OperateCacheServiceApi operateCacheServiceApi;
	
	Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 新增 菜单
	 * @param vo
	 * @return
	 */
	@Transactional 
	@Override
	public int insert(SysMenu sysMenu) {
		int num = sysMenuMapper.insert(sysMenu);
		if(num>0){
			Map<String, Object> map = BeanUtils.bean2map(sysMenu);
			operateCacheServiceApi.addCache(DB.SYS_MENU, map);
		}
		return num;
	}

	/**
	 * 通过主键修改 菜单
	 * @param map
	 * @return
	 */
	@Transactional 
	@Override
	public int updateByPrimaryKeySelective(Map<String, Object> map) {
		String id = map.get(DB.ID).toString();
		int num = sysMenuMapper.updateByPrimaryKeySelective(map);
		if(num>0){
			 Map<String,Object> del = new HashMap<String,Object>();
			 del.put(DB.ID, id);
			 SysMenu m = sysMenuMapper.getByPrimaryKey(id);
			 Map<String,Object> news = BeanUtils.bean2map(m);
			 operateCacheServiceApi.modifyCacheByCondition(DB.SYS_MENU, del,news);
		}
		return num;
	}

	/**
	 * 通过主键删除 菜单
	 * @param primaryKey
	 * @return
	 */
	@Transactional
	@Override
	public void deleteByPrimaryKey(String primaryKey) {
		int num = sysMenuMapper.deleteByPrimaryKey(primaryKey);
		if(num>0){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", primaryKey);
			operateCacheServiceApi.delCache(DB.SYS_MENU, map);
		}
	}

	/**
	 * 通过主键取得 菜单 对象
	 * @param id
	 * @return
	 */
	public SysMenu getByPrimaryKey(String primaryKey) {
		SysMenu sysMenu = null;
		boolean f = false;
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", primaryKey);
			List<Map<String,Object>> lst = redisCacheApi.queryCacheByCondition(DB.SYS_MENU, map);
			List<SysMenu> list = BeanUtil.ListMap2JavaBean(lst, SysMenu.class);
			if(!list.isEmpty()) {
				sysMenu = list.get(0);
			}
		} catch (Exception e) {
			log.error("call remote exception!", e);
			f = true;
		}
		if(sysMenu == null || f){
			sysMenu = sysMenuMapper.getByPrimaryKey(primaryKey);
		}
		return sysMenu;
	}

	/**
	 * 查询 菜单 列表
	 * @param param
	 * @return List<T>
	 */
	public List<SysMenu> queryForList(Map<String, Object> param) {
	
		List<SysMenu> list = new ArrayList<SysMenu>();
		boolean f = false;
		try {
			List<Map<String,Object>> lst = redisCacheApi.queryCacheByCondition(DB.SYS_MENU, param);
			list = BeanUtil.ListMap2JavaBean(lst, SysMenu.class);
		} catch (Exception e) {
			log.error("call remote exception!", e);
			f = true;
		}
		if(list.isEmpty() || f){
			list = sysMenuMapper.queryForList(param);
		}
		return list;
	}
	
	/**
	 * 查询 菜单 分页列表
	 * @param param
	 * @return List<T>
	 */
	public Page<SysMenu> queryForPage(Page<SysMenu> page){
		sysMenuMapper.queryForList(page.getPageParams());
		return (Page<SysMenu>)page.getPageParams().get(Page.KEY);
	}
	
	/**
	 * 新增、修改 机构信息
	 * @param vo
	 * @return
	 */
	@Transactional 
	@Override
	public SysMenu save(SysMenu sysMenu) {
		int cn = 0;
		 if(sysMenu != null){
			if(StringUtils.isEmpty(sysMenu.getId())){
				sysMenu.setId(RandomUtil.getUUID());
				sysMenu.setInstDate(new Date());
				cn = this.insert(sysMenu);
			 }else{
				 Map<String,Object> map = BeanUtils.bean2map(sysMenu);
				 map.put("updtDate", new Date());
				 cn = updateByPrimaryKeySelective(map);
			 }
		 }
		 if(cn == 0){
			 throw new ServiceException("保存菜单信息失败");
		 }
		return getByPrimaryKey(sysMenu.getId());
	}
	
	/**
	 * 删除菜单，向下递归删除
	 * @param primaryKey
	 */
	@Transactional 
	@Override
	public void deleteMenu(String primaryKey){
		//删除当前菜单
		deleteByPrimaryKey(primaryKey);
		//查询当前菜单的子菜单列表
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("parMenuId", primaryKey);
		List<SysMenu> menuList = queryForList(param);
		for (SysMenu sysMenu : menuList) {
			deleteMenu(sysMenu.getId());
		}
	}
	
	
}