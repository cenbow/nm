package com.hs.loan.approvecheck.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.approvecheck.entity.AppApprstaffGroupdetal;

/**
 * APP_审批内部组人员信息 mapper
 * @author autocreate
 * @create 2016-11-17
 */
@MyBatisRepository
public interface  AppApprstaffGroupdetalMapper extends BaseMapper<AppApprstaffGroupdetal>
{
	/**
	 * 批量新增审批内部组人员
	 * @param list
	 */
	public void batchInsert(List<AppApprstaffGroupdetal> list);
	
	/**
	 * 批量删除审批内部组人员
	 * @param list
	 * @return
	 */
	public int batchDeleteByPrimaryKey(List<String> list);
	
	/**
	 * 查询不在审批内部组人员
	 * @param param
	 * @return
	 */
	public List<AppApprstaffGroupdetal> queryNotApprstaffGroupdetalList(Map<String, Object> param);
	
	/**
	 * 批量删除审批内部组的人员
	 * @param groupId
	 * @return
	 */
	public int batchDeleteByGroupId(List<String> list);
}