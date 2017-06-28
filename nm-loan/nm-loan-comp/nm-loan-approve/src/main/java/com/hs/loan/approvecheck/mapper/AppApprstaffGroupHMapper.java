package com.hs.loan.approvecheck.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.approvecheck.entity.AppApprstaffGroupH;

/**
 * APP_审批内部组信息历史 mapper
 * @author autocreate
 * @create 2016-11-17
 */
@MyBatisRepository
public interface  AppApprstaffGroupHMapper extends BaseMapper<AppApprstaffGroupH>
{
	/**
	 * 批量新增审批内部组人员历史
	 * @param list
	 */
	public void batchInsert(List<AppApprstaffGroupH> list);
	
	/**
	 * 批量更新审批内部组人员历史
	 * @param list
	 */
	public void batchModifyByPrimaryKeySelective(Map<String, Object> params);
}