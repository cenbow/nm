package com.hs.loan.collection.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.collection.entity.AppRecallGroup;

/**
 * PL_催收组信息 mapper
 * @author autocreate
 * @create 2015-12-02
 */
@MyBatisRepository
public interface  AppRecallGroupMapper extends BaseMapper<AppRecallGroup>{
    /**
     * 根据催收组名称查询该催收组是否已经存在
     * @param groupName 催收组名称
     * @return Integer
     */
	public Integer selectExistsByName(String groupName);
}