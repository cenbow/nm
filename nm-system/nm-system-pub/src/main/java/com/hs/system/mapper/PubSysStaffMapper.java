package com.hs.system.mapper;

import java.util.List;
import java.util.Map;
import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.system.entity.SysStaff;

/**
 * 员工信息 mapper
 * @author autocreate
 * @create 2015-09-23
 */
@MyBatisRepository
public interface  PubSysStaffMapper extends BaseMapper<SysStaff>{

	//获取包含的销售列表
	List<SysStaff> getIncludeStaffLst(Map<String, Object> param);
	//获取不包含的 销售列表
	List<SysStaff> getNotIncludeStaffLst(Map<String, Object> pageParams);
	//获取不包含的 销售列表
	List<SysStaff> queryStaffByRole(Map<String, Object> pageParams);
	
	
	List<Map<String,String>> queryStaffMagg(Map<String,String> map);
}