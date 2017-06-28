package com.hs.loan.outsource.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.outsource.bo.LoanOutsourceRelationBo;
import com.hs.loan.outsource.entity.PlLoanOutsourceRelation;

/**
 * PL_委外单位与分期合同对应关系表 mapper
 * @author autocreate
 * @create 2015-12-02
 */
@MyBatisRepository
public interface  PlLoanOutsourceRelationMapper extends BaseMapper<PlLoanOutsourceRelation>{

	void updateBySelective(Map<String, Object> map);

	List<LoanOutsourceRelationBo> queryOutsourceRelationForList(Map<String, Object> pageParams);
	
}