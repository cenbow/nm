package com.hs.system.api;

import java.util.List;
import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.system.entity.SysParam;

/**
 * 系统参数 接口
 * @author autocreate
 * @create 2015-09-23
 */
public interface  SysParamApi{
	/**
	 * 新增 系统参数
	 * @param vo
	 * @return
	 */
	public int insert(SysParam sysParam) throws ServiceException,AppException;

	/**
	 * 通过主键修改 系统参数
	 * @param map
	 * @return
	 */
	public int updateByPrimaryKeySelective(Map<String, Object> map) throws ServiceException,AppException;

	/**
	 * 通过主键删除 系统参数
	 * @param primaryKey
	 * @return
	 */
	public void deleteByPrimaryKey(String primaryKey) throws ServiceException,AppException;

	/**
	 * 通过主键取得 系统参数 对象
	 * @param id
	 * @return
	 */
	public SysParam getByPrimaryKey(String primaryKey) throws ServiceException,AppException;

	/**
	 * 查询 系统参数 列表
	 * @param param
	 * @return List<T>
	 */
	public List<SysParam> queryForList(Map<String, Object> param) throws ServiceException,AppException;
	
	/**
	 * 查询 系统参数 分页列表
	 * @param param
	 * @return List<T>
	 */
	public Page<SysParam> queryForPage(Page<SysParam> page) throws ServiceException,AppException;
}