package com.hs.loan.sale.mapper;

import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.sale.entity.AppGrapScore;

/**
 * APP_芝麻分抓取表 mapper
 * @author autocreate
 * @create 2017-03-28
 */
@MyBatisRepository
public interface  AppGrapScoreMapper extends BaseMapper<AppGrapScore>{
	
	public AppGrapScore getByCustNo(Map<String, Object> param);
}