package com.hs.system.staff.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.system.entity.PubSaleCrowd;
import com.hs.system.entity.SysStaff;
	
/**
 * PUB_销售群 mapper
 * @author autocreate
 * @create 2015-10-15
 */
@MyBatisRepository
public interface  PubSaleCrowdMapper extends BaseMapper<PubSaleCrowd>{
	//执行规则
	List<SysStaff> executeRule(Map<String, Object> param);
	
}
