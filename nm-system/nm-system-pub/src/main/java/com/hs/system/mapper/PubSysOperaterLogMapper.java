package com.hs.system.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.system.entity.SysOperaterLog;

/**
 * SYS_系统操作日志 mapper
 * @author autocreate
 * @create 2015-10-10
 */
@MyBatisRepository
public interface  PubSysOperaterLogMapper extends BaseMapper<SysOperaterLog>{
	
}