package com.hs.loan.approve.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.approve.entity.AppLoanApprH;

import java.util.List;

/**
 * APP_分期审批信息 mapper
 * @author autocreate
 * @create 2015-11-23
 */
@MyBatisRepository
public interface  AppLoanApprHMapper extends BaseMapper<AppLoanApprH>{
    /**
     * 根据员工编号查询员工角色
     * @param staffNo
     * @return List<String>
     */
	public List<String> selectRoleByStaffNo(String staffNo);
}