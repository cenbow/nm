package com.hs.loan.finance.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.finance.bo.SaleScoreFlowBo;
import com.hs.loan.finance.entity.AccSalescoreFlow;

/**
 *  mapper
 * @author autocreate
 * @create 2016-10-11
 */
@MyBatisRepository
public interface  AccSalescoreFlowMapper extends BaseMapper<AccSalescoreFlow>{

	List<AccSalescoreFlow> queryListByStaff(Map<String, Object> param);
	
	/**
	 * 查询积分流水列表
	 * @param map
	 * @return
	 */
	public List<SaleScoreFlowBo> queryForListTwo(Map<String,Object> map);
	
}