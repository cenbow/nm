package com.hs.system.priv.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.system.dto.SysPrivManagerBo;
import com.hs.system.entity.SysPrivInfo;

/**
 * 角色权限设置 mapper
 * @author autocreate
 * @create 2015-09-23
 */
@MyBatisRepository
public interface  SysPrivInfoMapper extends BaseMapper<SysPrivInfo>{

	/**
	 * 查询权限设置信息lst，用于页面显示
	 * @param params
	 * @return
	 */
	List<SysPrivManagerBo> queryPrivMangerInfoLst(Map<String, Object> params);
	
}