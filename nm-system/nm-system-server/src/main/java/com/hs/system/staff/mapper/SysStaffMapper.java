package com.hs.system.staff.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.system.dto.SysRoleStaffBO;
import com.hs.system.entity.SysStaff;

/**
 * 员工信息 mapper
 * @author autocreate
 * @create 2015-09-23
 */
@MyBatisRepository
public interface  SysStaffMapper extends BaseMapper<SysStaff>{
	
	public List<SysRoleStaffBO> queryRolesExistStaff(Map<String,Object> map);
	
	public List<SysStaff> queryRolesNotExistStaff(Map<String,Object> map);

	//查询没在销售组下的销售
	public List<SysStaff> queryNotInGrpSalerList(Map<String, Object> map);

	public void changePassword(Map<String, Object> map);

}