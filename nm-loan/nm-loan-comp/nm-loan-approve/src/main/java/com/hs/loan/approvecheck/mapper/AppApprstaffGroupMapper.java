package com.hs.loan.approvecheck.mapper;

import java.util.List;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.approvecheck.entity.AppApprstaffGroup;

/**
 * APP_审批内部组信息 mapper
 * @author autocreate
 * @create 2016-11-17
 */
@MyBatisRepository
public interface  AppApprstaffGroupMapper extends BaseMapper<AppApprstaffGroup>
{
	/**
	 * 批量删除审核组
	 * @param list
	 * @return
	 */
	public int batchDeleteByPrimaryKey(List<String> list);

}