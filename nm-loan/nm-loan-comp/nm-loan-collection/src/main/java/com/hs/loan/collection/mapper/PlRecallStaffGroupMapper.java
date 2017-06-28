package com.hs.loan.collection.mapper;

import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.collection.entity.PlRecallStaffGroup;

/**
 * PL_催收人员与组关联信息 mapper
 * @author autocreate
 * @create 2015-12-02
 */
@MyBatisRepository
public interface  PlRecallStaffGroupMapper extends BaseMapper<PlRecallStaffGroup>{
	public void deleteByCont(Map<String,String> param);
}