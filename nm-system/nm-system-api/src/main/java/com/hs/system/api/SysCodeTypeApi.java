package com.hs.system.api;

import java.util.List;
import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.system.entity.SysCodTyp;

/**
 * 编码类型 接口
 * @author autocreate
 * @create 2015-09-24
 */
public interface  SysCodeTypeApi{

	/**
	 * 新增 编码类型
	 * @param vo
	 * @return
	 */
	public void insert(SysCodTyp sysCodTyp) throws ServiceException,AppException;
	
	/**
	 * 通过主键修改 编码类型
	 * @param map
	 * @return
	 */
	public void updateByPrimaryKeySelective(Map<String, Object> map) throws ServiceException,AppException;
	
	/**
	 * 通过主键删除 编码类型
	 * @param primaryKey
	 * @return
	 */
	public void deleteByPrimaryKey(String primaryKey) throws ServiceException,AppException;

	/**
	 * 通过主键取得 编码类型 对象
	 * @param id
	 * @return
	 */
	public SysCodTyp getByPrimaryKey(String primaryKey) throws ServiceException,AppException;

	/**
	 * 查询 编码类型 列表
	 * @param param
	 * @return List<T>
	 */
	public List<SysCodTyp> queryForList(Map<String, Object> param) throws ServiceException,AppException;
	
	/**
	 * 查询 编码类型 分页列表
	 * @param param
	 * @return List<T>
	 */
	public Page<SysCodTyp> queryForPage(Page<SysCodTyp> page) throws ServiceException,AppException;
	
	/**
	 * 保存  编码类型
	 * @param vo
	 * @return
	 */
	public SysCodTyp save(SysCodTyp sysCodTyp) throws ServiceException,AppException;
}