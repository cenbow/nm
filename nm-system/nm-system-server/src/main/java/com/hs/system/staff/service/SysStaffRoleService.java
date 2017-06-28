package com.hs.system.staff.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.system.api.SysStaffRoleApi;
import com.hs.system.entity.SysStaffRole;
import com.hs.system.staff.mapper.SysStaffRoleMapper;
import com.hs.utils.RandomUtil;

/**
 * 用户角色 业务处理
 * @author autocreate
 * @create 2015-09-23
 */
@Service
@Transactional(readOnly=true)
public class  SysStaffRoleService implements SysStaffRoleApi{
	@Autowired
	private SysStaffRoleMapper sysUserRoleMapper;
 

	/**
	 * 通过主键取得 用户角色 对象
	 * @param id
	 * @return
	 */
	public SysStaffRole getByPrimaryKey(String primaryKey){
		return sysUserRoleMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 用户角色 列表
	 * @param param
	 * @return List<T>
	 */
	public List<SysStaffRole> queryForList(Map<String, Object> param){
		return sysUserRoleMapper.queryForList(param);
	}
	
	/**
	 * 查询 用户角色 分页列表
	 * @param param
	 * @return List<T>
	 */
	public Page<SysStaffRole> queryForPage(Page<SysStaffRole> page){
		sysUserRoleMapper.queryForList(page.getPageParams());
		return (Page<SysStaffRole>)page.getPageParams().get(Page.KEY);
	}


	/**
	 * 新增 用户角色
	 * @param vo
	 * @return
	 */
	@Transactional 
	@Override
	public void saveStaffRoles(List<SysStaffRole> sysUserRoles)  {
		for (SysStaffRole sysUserRole : sysUserRoles) {
			sysUserRole.setId(RandomUtil.getUUID());
			sysUserRoleMapper.insert(sysUserRole);
		}
	}

	/**
	 * 通过主键删除 用户角色
	 * @param primaryKey
	 * @return
	 */
	@Override
	public void removeStaffRoles(List<String> relIds)  {
		for (String relId : relIds) {
			sysUserRoleMapper.deleteByPrimaryKey(relId);
		}
	}
}