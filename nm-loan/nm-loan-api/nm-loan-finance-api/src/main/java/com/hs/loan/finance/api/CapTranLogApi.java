package com.hs.loan.finance.api;

import java.util.List;
import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.exception.ServiceException;
import com.hs.loan.finance.dto.AccCapTranLogDto;

/**
 * ACC_银联交易日志（单笔） 接口 单笔交易发起日志
 * @author autocreate
 * @create 2016-02-03
 */
public interface  CapTranLogApi{
	/**
	 * 新增 
	 * @param capTranlog 新增对象
	 */
	public void insert(AccCapTranLogDto capTranlog) throws ServiceException;

	/**
	 * 通过主键修改 
	 * @param map 修改参数Map
	 */
	public void updateByPrimaryKeySelective(Map<String, Object> map) throws ServiceException;

	/**
	 * 通过主键删除 
	 * @param primaryKey 主键
	 */
	public void deleteByPrimaryKey(String primaryKey) throws ServiceException;

	

	/**
	 * 通过主键取得  对象
	 * @param primaryKey 主键
	 * @return 对象
	 */
	public AccCapTranLogDto getByPrimaryKey(String primaryKey) throws ServiceException;

	/**
	 * 查询  列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AccCapTranLogDto> queryForList(Map<String, Object> param) throws ServiceException;
	
	/**
	 * 查询  分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AccCapTranLogDto> queryForPage(Page<AccCapTranLogDto> page) throws ServiceException;
}