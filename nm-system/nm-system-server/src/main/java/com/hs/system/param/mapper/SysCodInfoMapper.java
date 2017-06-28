package com.hs.system.param.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.system.entity.SysCodInfo;

/**
 * 编码信息 mapper
 * @author autocreate
 * @create 2015-09-24
 */
@MyBatisRepository
public interface  SysCodInfoMapper extends BaseMapper<SysCodInfo>{
	
	/**
	 * 修改编码信息
	 * @param sysCodInfo
	 */
	public void updateSysCodInfo(SysCodInfo sysCodInfo);
}