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
import com.hs.system.api.SysRoleApi;
import com.hs.system.dto.SysRoleStaffBO;
import com.hs.system.entity.SysRole;
import com.hs.system.entity.SysStaff;
import com.hs.system.role.mapper.SysRoleMapper;
import com.hs.system.staff.mapper.SysStaffMapper;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;

/**
 * 角色信息 业务处理
 * @author autocreate
 * @create 2015-09-23
 */
@Service
@Transactional(readOnly=true)
public class  SysRoleService implements SysRoleApi{
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysStaffMapper sysStaffMapper;
	
	/**
	 * 新增 角色信息
	 * @param vo
	 * @return
	 */
	@Transactional 
	public void insert(SysRole sysRole) {
		sysRoleMapper.insert(sysRole);
	}

	/**
	 * 通过主键修改 角色信息
	 * @param map
	 * @return
	 */
	@Transactional 
	public void updateByPrimaryKeySelective(Map<String, Object> map)  {
		sysRoleMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 角色信息
	 * @param primaryKey
	 * @return
	 */
	@Transactional 
	public void deleteByPrimaryKey(String primaryKey)  {
		sysRoleMapper.deleteByPrimaryKey(primaryKey);
	}
	
	/**
	 * 保存角色信息
	 * @param sysRole
	 * @return
	 */
	@Transactional 
	public SysRole save(SysRole sysRole){
		int cn = 0;
		if(null != sysRole){
			Date date = new Date();
			String roleNo = sysRole.getRoleNo();
			if(StringUtils.isEmpty(roleNo)){
				throw new ServiceException("维护角色失败");
			}
			Map<String,Object> map = new HashMap<>();
			map.put("roleNo", roleNo);
			if(StringUtils.isEmpty(sysRole.getId())){
				List<SysRole> lst = this.queryForList(map);
				if(lst ==null || lst.size() == 0){
					sysRole.setId(RandomUtil.getUUID());
					sysRole.setInstDate(date);
					sysRole.setUpdtDate(date);
					cn = sysRoleMapper.insert(sysRole);
				}else{
					throw new ServiceException("保存角色信息失败,角色编号:"+roleNo+"已存在！");
				}
				
			}else{
				Map<String, Object> bean2map = BeanUtils.bean2map(sysRole);
				bean2map.put("updtDate", date);
				cn = sysRoleMapper.updateByPrimaryKeySelective(bean2map);
			}
		}
		if(cn == 0){
			throw new ServiceException("保存角色信息失败");
		}
		return sysRole;
	}

	/**
	 * 通过主键取得 角色信息 对象
	 * @param id
	 * @return
	 */
	public SysRole getByPrimaryKey(String primaryKey){
		return sysRoleMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 角色信息 列表
	 * @param param
	 * @return List<T>
	 */
	public List<SysRole> queryForList(Map<String, Object> param){
		return sysRoleMapper.queryForList(param);
	}
	
	/**
	 * 查询 角色信息 分页列表
	 * @param param
	 * @return List<T>
	 */
	public Page<SysRole> queryForPage(Page<SysRole> page){
		sysRoleMapper.queryForList(page.getPageParams());
		return (Page<SysRole>)page.getPageParams().get(Page.KEY);
	}

	/**
	 * 根据角色查询员工
	 */
	@Override
	public Page<SysRoleStaffBO> queryRolesExistStaff(Page<SysRoleStaffBO> page) {
		List<SysRoleStaffBO> list = sysStaffMapper.queryRolesExistStaff(page.getPageParams());
		System.out.println(list.size());
		return (Page<SysRoleStaffBO>)page.getPageParams().get(Page.KEY);
	}

	@Override
	public Page<SysStaff> queryRolesNotExistStaff(Page<SysStaff> page) {
		List<SysStaff> list=sysStaffMapper.queryRolesNotExistStaff(page.getPageParams());
		return (Page<SysStaff>)page.getPageParams().get(Page.KEY);
	}
}