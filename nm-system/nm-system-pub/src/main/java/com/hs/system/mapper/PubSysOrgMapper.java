package com.hs.system.mapper;

import java.util.List;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.system.entity.SysOrg;

/**
 * 机构信息 mapper
 * @author autocreate
 * @create 2015-09-23
 */
@MyBatisRepository
public interface  PubSysOrgMapper extends BaseMapper<SysOrg>{
	
	public SysOrg queryStaffBelongOrg(String staffNo);
	
	public List<SysOrg> queryForListAuthr(String orgNo);
}