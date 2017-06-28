package com.hs.system.role.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.system.dto.SysRoleMenuBo;
import com.hs.system.entity.SysMenu;
import com.hs.system.entity.SysRoleMenu;

/**
 * 角色菜单关系 mapper
 * @author autocreate
 * @create 2015-09-23
 */
@MyBatisRepository
public interface  SysRoleMenuMapper extends BaseMapper<SysRoleMenu>{
	
	public List<SysRoleMenuBo> querySysRoleMenuList(Map<String, Object> param);
	
	public List<SysRoleMenuBo> querySysRoleMenuCheckedList(Map<String, Object> param);
	
	public void deleteByRoleId(Map<String,Object> param);

	//查询改角色在子系统下菜单中的菜单，
	public List<String> querySubSysRoleHadMenu(Map<String, Object> param);
}