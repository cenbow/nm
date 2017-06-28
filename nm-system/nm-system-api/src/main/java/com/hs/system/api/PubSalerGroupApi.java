package com.hs.system.api;

import java.util.List;
import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.system.entity.PubSalerGroup;
import com.hs.system.entity.SysStaff;


/**
 * PUB_销售组信息 接口
 * @author autocreate
 * @create 2015-10-15
 */
public interface  PubSalerGroupApi{

	/**
	 * 保存或者更新 销售组基本信息
	 * @param pubSalerGroup
	 */
	public void save(PubSalerGroup pubSalerGroup) throws ServiceException,AppException;
	
	/**
	 * 通过组编号 删除 销售组，同时会删除其与销售的关系
	 * @param groupNo
	 */
	public void deleteByNo(String groupNo) throws ServiceException,AppException;
	
	/**
	 * 通过组编号 获取 销售组的基本信息
	 * @param groupNo
	 * @return
	 */
	public PubSalerGroup getByNo(String groupNo) throws ServiceException,AppException;
	
	/**
	 * 获取 销售组基本信息 list
	 * @param param
	 * @return
	 */
	public List<PubSalerGroup> getList(Map<String,Object> param) throws ServiceException,AppException;
	
	/**
	 * 分页查询 销售组基本信息
	 * @param page
	 * @return
	 */
	public Page<PubSalerGroup> querySalerGroup(Page<PubSalerGroup> page ) throws ServiceException,AppException;
	
	/**
	 * 获取销售组下的 销售List
	 * 必须的参数 groupNo
	 * @param param
	 * @return
	 */
	public List<SysStaff> getSalerList(Map<String,Object> param) throws ServiceException,AppException;
	
	/**
	 * 分页查询销售组下的 销售
	 * 必须的参数 groupNo
	 * @param page
	 * @return
	 */
	public Page<SysStaff> querySaler(Page<SysStaff> page) throws ServiceException,AppException;
	
	/**
	 * 保存或更新 销售组 与销售的关系
	 */
	public void saveGrpSalerRel(String groupNo,List<String> staffNos) throws ServiceException,AppException;
	
	/**
	 * 保存或者更新 销售组 与 销售关系
	 * @param pubSalerGroup
	 * @param staffNos
	 */
	public void saveGrpSaler(PubSalerGroup pubSalerGroup,List<String> staffNos) throws ServiceException,AppException;
	
	/**
	 * 删除销售组下的 销售
	 * @param groupNo
	 */
	public void deleteSaler(String groupNo) throws ServiceException,AppException;
	
	/**
	 * 删除销售组下  的  销售
	 * @param groupNo
	 * @param staffNoLst
	 */
	public void deleteGrpSalerRel(String groupNo,List<String> staffNoLst) throws ServiceException,AppException;
	
	
	/**
	 * 分页查询 没在当前销售组下的 销售
	 * 必须的参数 groupNo
	 * @param page
	 * @return
	 */
	public Page<SysStaff> queryNotInGrpSaler(Page<SysStaff> page) throws ServiceException,AppException;
	
}