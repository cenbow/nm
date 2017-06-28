package com.hs.system.staff.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.system.entity.SysOrg;
import com.hs.system.entity.SysStaffOrg;

/**
 * 机构人员关系 mapper
 * @author autocreate
 * @create 2015-09-23
 */
@MyBatisRepository
public interface  SysStaffOrgMapper extends BaseMapper<SysStaffOrg>{
	
	public void deleteStaffOrgByStaffId(String staffId);
	 
}