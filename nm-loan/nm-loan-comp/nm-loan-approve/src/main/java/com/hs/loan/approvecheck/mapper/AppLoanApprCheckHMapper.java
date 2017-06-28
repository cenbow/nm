package com.hs.loan.approvecheck.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.approvecheck.bo.AppLoanApprCheckHBo;
import com.hs.loan.approvecheck.entity.AppLoanApprCheckH;

/**
 * APP_分期案件复核历史 mapper
 * @author autocreate
 * @create 2016-11-24
 */
@MyBatisRepository
public interface  AppLoanApprCheckHMapper extends BaseMapper<AppLoanApprCheckH>{
	
	/**
	 * 查询 APP_分期案件复核历史 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppLoanApprCheckHBo> queryForListTwo(Map<String, Object> map);
}