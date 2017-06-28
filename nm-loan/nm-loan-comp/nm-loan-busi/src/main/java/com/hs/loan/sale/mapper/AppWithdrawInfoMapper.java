package com.hs.loan.sale.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.sale.entity.AppWithdrawInfo;

/**
 * APP_分期基本信息 mapper
 * @author autocreate
 * @create 2015-10-26
 */
@MyBatisRepository
public interface  AppWithdrawInfoMapper extends BaseMapper<AppWithdrawInfo>{
	List<AppWithdrawInfo> queryForList(Map<String, Object> pageParams);
}