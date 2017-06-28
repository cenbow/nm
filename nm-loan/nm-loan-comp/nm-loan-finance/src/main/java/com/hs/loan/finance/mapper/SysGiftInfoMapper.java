package com.hs.loan.finance.mapper;

import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.finance.entity.SysGiftInfo;

/**
 *  mapper
 * @author autocreate
 * @create 2016-10-11
 */
@MyBatisRepository
public interface  SysGiftInfoMapper extends BaseMapper<SysGiftInfo>
{
	/**
	 * 根据giftNo统计次数
	 * @param map
	 * @return
	 */
	public Integer getCountByGiftNo(Map<String,Object> map);

}