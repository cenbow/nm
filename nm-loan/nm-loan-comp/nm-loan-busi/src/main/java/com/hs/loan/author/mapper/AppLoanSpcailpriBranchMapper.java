package com.hs.loan.author.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.author.entity.AppLoanSpcailpriBranch;

/**
 * APP_商户直通车权限 mapper
 * @author autocreate
 * @create 2016-10-27
 */
@MyBatisRepository
public interface  AppLoanSpcailpriBranchMapper extends BaseMapper<AppLoanSpcailpriBranch>
{
	public List<AppLoanSpcailpriBranch> queryNotSpcailpriBranchList(Map<String, Object> param);
	
	void batchInsert(List<AppLoanSpcailpriBranch> list);
}