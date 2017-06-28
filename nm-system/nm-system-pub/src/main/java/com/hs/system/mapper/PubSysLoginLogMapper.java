package com.hs.system.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.system.entity.SysLoginLog;

/**
 * 系统登录日志 mapper
 * @author autocreate
 * @create 2015-10-10
 */
@MyBatisRepository
public interface  PubSysLoginLogMapper extends BaseMapper<SysLoginLog>{
	
}