package com.hs.system.api;

import java.util.List;
import java.util.Map;

import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.system.entity.SysGroup;


/**
 * 编码组 接口
 * @author autocreate
 * @create 2015-09-26
 */
public interface  SysGroupApi{
	
	/**
	 * 查询 编码组 列表 
	 * @param param {typId}
	 * 
	 * @return List<T>
	 */
	public List<SysGroup> queryByType(Map<String,Object> param) throws ServiceException,AppException;
	
	/**
	 * 保存或者更新码表组信息，包含的syscodeinfo也将一同跟新
	 * @param sysGroup
	 */
	public void saveOrUpdateCodGrp(SysGroup sysGroup) throws ServiceException,AppException;
	
	/**
	 * 删除码表组
	 * @param groupId
	 */
	public void deleteCodGrp(String groupId) throws ServiceException,AppException;
	
	/**
	 * 查询码表组的基本信息，不包含syscodeinfo信息
	 * @param groupId
	 * @return
	 */
	public SysGroup querySysGrpBaseInfo(String groupId) throws ServiceException,AppException;
	
	/**
	 * 通过主键取得 编码组 对象
	 * @param id
	 * @return
	 */
	public SysGroup getByPrimaryKey(String primaryKey) throws ServiceException,AppException;
	
	/**
	 * 查询码表组中的codeinfo列表,
	 * 结果列表中包含包含checked的和未checked的，
	 * checked表示这条codeinfo是属于这个组中的。
	 * 
	 * map的key有id,codeVal,codeName,codTyp,checked
	 * 
	 * @return
	 */
	public List<Map<String,Object>> queryGrpCodeInfoLst(String groupId) throws ServiceException,AppException;
	
}