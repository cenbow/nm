package com.hs.system.param.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.system.entity.SysCodTyp;

/**
 * 编码类型 mapper
 * @author autocreate
 * @create 2015-09-24
 */
@MyBatisRepository
public interface  SysCodTypMapper extends BaseMapper<SysCodTyp>{
	
	/**
	 * 修改 编码类型
	 * @param sysCodTyp
	 */
	public void updateSysCodTyp(SysCodTyp sysCodTyp);
}