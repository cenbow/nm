package com.hs.system.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.system.entity.SysCodInfo;
import com.hs.system.entity.SysGroup;

/**
 * 码表公用mapper
 * @author jqiu
 * @create 2015-09-24
 */
@MyBatisRepository
public interface  CodMapper{
	
	/**
	 * 查询列表
	 * @param param
	 * @return List<SysCodInfo>
	 */
	public List<SysCodInfo> queryForList(Map<String, Object> param);
	
	/**
	 * 通过码值类型查询group列表
	 * @param typeCod
	 * @return
	 */
	public List<SysGroup> queryGroupListByType(String typeCod);
}