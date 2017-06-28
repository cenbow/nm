package com.hs.system.staff.mapper;

import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.system.entity.PubGroupStaff;

/**
 * PUB_销售组与用户关系 mapper
 * @author autocreate
 * @create 2015-10-15
 */
@MyBatisRepository
public interface  PubGroupStaffMapper extends BaseMapper<PubGroupStaff>{

	/*删除销售组和销售的关系*/
	void deleteGrpSalerRel(Map<String, Object> param);

	
}