package com.hs.loan.market.mapper;

import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.market.entity.PubBranchGroup;

/**
 * PUB_网点分组关系 mapper
 * @author autocreate
 * @create 2015-10-15
 */
@MyBatisRepository
public interface  PubBranchGroupMapper extends BaseMapper<PubBranchGroup>{

	//删除 网点分组和网点的关系
	void rmvBrhGrpRel(Map<String, Object> param);

	
	
}