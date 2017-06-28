package com.hs.system.role.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.ServiceException;
import com.hs.system.api.SysRoleMenuApi;
import com.hs.system.dto.SysRoleMenuBo;
import com.hs.system.entity.SysMenu;
import com.hs.system.entity.SysRoleMenu;
import com.hs.system.role.mapper.SysRoleMenuMapper;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;

/**
 * 角色菜单关系 业务处理
 * @author autocreate
 * @create 2015-09-23
 */
@Service
@Transactional(readOnly=true)
public class  SysRoleMenuService implements SysRoleMenuApi{
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	
	/**
	 * 新增 角色菜单关系
	 * @param vo
	 * @return
	 */
	@Transactional
	public void insert(SysRoleMenu sysRoleMenu){
		sysRoleMenuMapper.insert(sysRoleMenu);
	}

	/**
	 * 通过主键修改 角色菜单关系
	 * @param map
	 * @return
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		sysRoleMenuMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 角色菜单关系
	 * @param primaryKey
	 * @return
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		sysRoleMenuMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 角色菜单关系 对象
	 * @param id
	 * @return
	 */
	public SysRoleMenu getByPrimaryKey(String primaryKey){
		return sysRoleMenuMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 角色菜单关系 列表
	 * @param param
	 * @return List<T>
	 */
	public List<SysRoleMenu> queryForList(Map<String, Object> param){
		return sysRoleMenuMapper.queryForList(param);
	}
	
	/**
	 * 查询 角色菜单关系 分页列表
	 * @param param
	 * @return List<T>
	 */
	public Page<SysRoleMenu> queryForPage(Page<SysRoleMenu> page){
		sysRoleMenuMapper.queryForList(page.getPageParams());
		return (Page<SysRoleMenu>)page.getPageParams().get(Page.KEY);
	}
	
	/**
	 * 查询 菜单 树
	 * @param param
	 * @return List<T>
	 */
	public List<SysRoleMenuBo> querySysRoleMenuList(Map<String, Object> param){
		return sysRoleMenuMapper.querySysRoleMenuList(param);
	}
	
	/**
	 * 查询 角色菜单(选中的)
	 * @param param
	 * @return List<T>
	 */
	public List<SysRoleMenuBo> querySysRoleMenuCheckedList(Map<String, Object> param){
		return sysRoleMenuMapper.querySysRoleMenuCheckedList(param);
	}
	
	/**
	 * 保存 角色菜单关联
	 * @param roleId 	角色ID
	 * @param list		选中的菜单ID集合
	 */
	@Transactional
	public void save(String sysId,String roleId, List<String> list){
		
		if(StringUtils.isNotEmpty(roleId)){
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("sysId",sysId);
			param.put("roleId", roleId);
			sysRoleMenuMapper.deleteByRoleId(param);
		}
		if(null != list && list.size() > 0){
			String menuId = null;
			SysRoleMenu sysRoleMenu = null;
			for (int i = 0; i < list.size(); i++) {
				menuId = list.get(i);
				if(StringUtils.isEmpty(menuId)){
					continue;
				}
				//已勾选
				sysRoleMenu = new SysRoleMenu();
				sysRoleMenu.setId(RandomUtil.getUUID());
				sysRoleMenu.setMenuId(menuId);
				sysRoleMenu.setRoleId(roleId);
				Date date = new Date();
				sysRoleMenu.setInstDate(date);
				sysRoleMenu.setUpdtDate(date);
				sysRoleMenuMapper.insert(sysRoleMenu);
			}
		}
	}

	/**
	 * 查询改角色在子系统下菜单中的菜单，
	 */
	@Override
	public List<String> querySubSysRoleHadMenu(String roleId, String sysId) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("roleId", roleId);
		param.put("sysId", sysId);
		return sysRoleMenuMapper.querySubSysRoleHadMenu(param);
	}

}