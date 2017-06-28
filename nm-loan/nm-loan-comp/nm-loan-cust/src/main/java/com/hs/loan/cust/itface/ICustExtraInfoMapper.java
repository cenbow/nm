package com.hs.loan.cust.itface;

import java.util.List;
import java.util.Map;
import com.hs.base.mapper.BaseMapper;

/**
 * 客户的一些附加信息 的 mapper
 * @author zwr
 *
 */
public interface ICustExtraInfoMapper <T extends ICustExtraInfo> extends BaseMapper<T> {

	/**
	 * 获取客户当前有效时间段(availableDate)的 按beginDate 倒序排序的 额外信息的list
	 * 
	 * @param param
	 * @return
	 */
	public List<T> getAvailableExtraInfoLst(Map<String,Object> param);
}
