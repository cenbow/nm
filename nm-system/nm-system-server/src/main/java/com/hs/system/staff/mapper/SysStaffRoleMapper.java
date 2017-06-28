package com.hs.system.staff.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.system.entity.SysStaffRole;

/**
 * 用户角色 mapper
 * @author autocreate
 * @create 2015-09-23
 */
@MyBatisRepository
public interface  SysStaffRoleMapper extends BaseMapper<SysStaffRole>{
	
	/**
	 * 删除 用户所有角色
	 * @param staffId	用户ID
	 * @return 删除数量
	 */
	public int deleteByStaffId(String staffId);
}