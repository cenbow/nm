package com.hs.system.api;

import java.util.List;
import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.system.entity.SysCodInfo;

/**
 * 编码类型 接口
 * @author autocreate
 * @create 2015-09-24
 */
public interface  SysCodeInfoApi{

	/**
	 * 新增 编码信息
	 * @param vo
	 * @return
	 */
	public int insert(SysCodInfo sysCodInfo) throws ServiceException,AppException;

	/**
	 * 通过主键修改 编码信息
	 * @param map
	 * @return
	 */
	public int updateByPrimaryKeySelective(Map<String, Object> map) throws ServiceException,AppException;

	/**
	 * 通过主键删除 编码信息
	 * @param primaryKey
	 * @return
	 */
	public void deleteByPrimaryKey(String primaryKey) throws ServiceException,AppException;

	/**
	 * 通过主键取得 编码信息 对象
	 * @param id
	 * @return
	 */
	public SysCodInfo getByPrimaryKey(String primaryKey) throws ServiceException,AppException;

	/**
	 * 查询 编码信息 列表
	 * @param param
	 * @return List<T>
	 */
	public List<SysCodInfo> queryForList(Map<String, Object> param) throws ServiceException,AppException;
	
	/**
	 * 查询 编码类型 分页列表
	 * @param param
	 * @return List<T>
	 */
	public Page<SysCodInfo> queryForPage(Page<SysCodInfo> page) throws ServiceException,AppException;
	
	/**
	 * 保存 编码信息
	 * @param vo
	 * @return
	 */
	public SysCodInfo save(SysCodInfo sysCodInfo) throws ServiceException,AppException;
}