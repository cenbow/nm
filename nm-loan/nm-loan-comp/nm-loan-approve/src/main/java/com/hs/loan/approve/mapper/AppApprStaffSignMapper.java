package com.hs.loan.approve.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.approve.entity.AppApprStaffSign;

/**
 * APP_审批人员签到明细 mapper
 * @author autocreate
 * @create 2015-11-23
 */
@MyBatisRepository
public interface  AppApprStaffSignMapper extends BaseMapper<AppApprStaffSign>{
	public String getStaffStat(String staffNo);
}