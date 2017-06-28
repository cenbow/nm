package com.hs.system.role.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.system.dto.SysRoleStaffBO;
import com.hs.system.dto.SysStaffRoleBO;
import com.hs.system.entity.SysRole;
import com.hs.system.entity.SysStaff;

/**
 * 角色信息 mapper
 * @author autocreate
 * @create 2015-09-23
 */
@MyBatisRepository
public interface  SysRoleMapper extends BaseMapper<SysRole>{
	
	
	public List<SysStaffRoleBO> queryCustExistRoles(String staffNo);
	
	public List<SysRole> queryCustNotExistRoles(Map<String,Object> map);
	
	
}