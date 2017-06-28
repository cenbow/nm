package com.hs.loan.sale.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.sale.entity.AppLoanCallHand;

/**
 * APP_经办历史记录表 mapper
 * @author autocreate
 * @create 2017-05-02
 */
@MyBatisRepository
public interface  AppLoanCallHandMapper extends BaseMapper<AppLoanCallHand>{

	void updateLastHand(Map<String, Object> param);

	List<AppLoanCallHand> queryHandForList(Map<String, Object> param);
	
}