package com.hs.loan.approve.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.approve.entity.AppApprGroup;

/**
 * APP_审批组信息 mapper
 * @author autocreate
 * @create 2015-11-23
 */
@MyBatisRepository
public interface  AppApprGroupMapper extends BaseMapper<AppApprGroup>{
	public void updateGrpByGrpNo(AppApprGroup apprGrp);
}