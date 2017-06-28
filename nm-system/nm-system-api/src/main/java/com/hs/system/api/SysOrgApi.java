package com.hs.system.api;

import java.util.List;
import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.system.entity.SysOrg;

/**
 * 机构信息 接口
 * @author autocreate
 * @create 2015-09-23
 */
public interface  SysOrgApi{

	
	
	/**
	 *  获取机构列表翻页
	 * @param page
	 * @return
	 * @
	 */
	public Page<SysOrg> queryForPage(Page<SysOrg> page,UserProfile userProfile) throws ServiceException,AppException;
	/**
	 * 查询树顶级菜单
	 * @param userProfile
	 * @return
	 * @list<SysOrg>
	 */
	public List<SysOrg> queryForList(UserProfile userProfile)  throws ServiceException,AppException;
	
	/**
	 * 查询下级树列表
	 * @param parentId 父ID
	 * @param userProfile
	 * @return
	 */
	public List<SysOrg> queryForList(String parentId,UserProfile userProfile)  throws ServiceException,AppException;
	/**
	 *新增机构 修改
	 * @param sysOrg
	 * @
	 */	
	public SysOrg save(SysOrg sysOrg) throws ServiceException,AppException;
	 
	/**
	 * 删除机构
	 * @param primaryKey
	 * @
	 */
	public void deleteByOrgId(String orgId)  throws ServiceException,AppException;
	
	/**
	 * 获取单个机构
	 * @param primaryKey
	 * @return
	 * @
	 */
	public SysOrg getByOrgId(String orgId)  throws ServiceException,AppException;
	/**
	 * 获取单个机构
	 * @param primaryKey
	 * @return
	 * @
	 */
	public SysOrg getByOrgNo(String orgNo)  throws ServiceException,AppException;
	
	
	/**
	 * 获取机构列表
	 * @param param
	 * @return
	 * @
	 */
	public List<SysOrg> queryForList(Map<String, Object> param)  throws ServiceException,AppException;
	
	/**
	 * 查询本级及下级树列表
	 * @param parOrgNo 父ID
	 * @param userProfile
	 * @return
	 */
	public List<SysOrg> queryOrgForList(UserProfile userProfile)  throws ServiceException,AppException;

}