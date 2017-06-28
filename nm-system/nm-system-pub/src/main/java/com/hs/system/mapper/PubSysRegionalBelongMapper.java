package com.hs.system.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.system.entity.SysRegionalBelong;

/**
 * SYS_地域归属表 mapper
 * @author autocreate
 * @create 2015-10-30
 */
@MyBatisRepository
public interface  PubSysRegionalBelongMapper extends BaseMapper<SysRegionalBelong>{

	String getCountName(String count);

	String getCityName(String cityNo);

	String getProvName(String provNo);
	Map<String,Long> checkType(String areaNo);
	List<SysRegionalBelong> queryProvs();
	List<SysRegionalBelong> queryCity(String provNo);
	List<SysRegionalBelong> queryTowns(String cityNo);

}