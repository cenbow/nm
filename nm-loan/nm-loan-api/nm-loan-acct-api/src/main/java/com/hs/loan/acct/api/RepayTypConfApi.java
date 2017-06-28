package com.hs.loan.acct.api;

import java.util.List;
import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.acct.dto.RepayTypConfDto;


/**
 * PUB_还款类型配置信息 接口
 * @author autocreate
 * @create 2015-10-15
 */
public interface  RepayTypConfApi{

	/**
	 * 保存或者更新 还款类型配置信息
	 */
	public void save(RepayTypConfDto repayTypConfDto) throws ServiceException,AppException;
	
	/**
	 * 通过还款类型配置编号 删除 还款类型配置信息
	 * @param primaryKey 主键
	 */
	public void deleteByNo(String confNo) throws ServiceException,AppException;
	
	/**
	 * 通过还款类型配置编号 获取 还款类型配置信息
	 * @param primaryKey 主键
	 * @return PUB_还款类型配置信息对象
	 */
	public RepayTypConfDto getByNo(String confNo) throws ServiceException,AppException;
	
	/**
	 * 查询还款类型配置信息List
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<RepayTypConfDto> getList(Map<String, Object> param) throws ServiceException,AppException;
	
	/**
	 * 分页查询还款类型配置信息
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<RepayTypConfDto> queryRepayTypConf(Page<RepayTypConfDto> page) throws ServiceException,AppException;
	
}