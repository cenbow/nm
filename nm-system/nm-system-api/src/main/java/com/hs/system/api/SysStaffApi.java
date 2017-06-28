package com.hs.system.api;

import java.util.List;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.system.dto.SysStaffRoleBO;
import com.hs.system.entity.SysOrg;
import com.hs.system.entity.SysRole;
import com.hs.system.entity.SysStaff;
import com.hs.system.entity.SysStaffOrg;
import com.hs.system.entity.SysStaffRole;

/**
 * 员工信息 接口
 * @author autocreate
 * @create 2015-09-23
 */
public interface  SysStaffApi{

	
	/**
	 * @param page
	 * @return
	 * @
	 */
	public Page<SysStaff> queryForPage(Page<SysStaff> page) throws ServiceException,AppException;
	
	/**
	 * 新增 修改员工
	 * @param sysStaff
	 * @
	 */
	public void save(SysStaff sysStaff)  throws ServiceException,AppException;
	
	
	/**
	 * 根据员工编号获取员工信息
	 */
	
	public SysStaff getByStaffId(String id) throws ServiceException,AppException;
	
	
	/**
	 * 根据员工编号获取员工信息
	 */
	
	public SysStaff getByStaffNo(String staffNo) throws ServiceException,AppException;
	
	/**
	 * 保存员工机构信息
	 * @param sysUserOrg
	 * @
	 */
	public void saveStaffOrg(SysStaffOrg sysUserOrg)  throws ServiceException,AppException;
	
	
	 /**
	  * 新增
	  * @param sysUserRole
	  */
	public void saveStaffRoles(List<SysStaffRole> sysUserRole)  throws ServiceException,AppException;
	
	/**
	 * 移除
	 * @param primaryKey
	 * @
	 */
	public void removeStaffRoles(List<String> relIds)  throws ServiceException,AppException;
	
	 
	/**
	 * 更具客户号查询 和未分配角色
	 * @param page
	 * @return 
	 */
	public Page<SysRole> queryCustNotExistRoles(Page<SysRole> page) throws ServiceException,AppException;
	
	/**
	 * 查询客户存在的机构
	 */
	
	public SysOrg queryCustBelongOrg(String staffNo) throws ServiceException,AppException;
	
	/**
	 * 查询员工角色信息
	 * @param staffNo
	 * @return
	 */
	public List<SysStaffRoleBO> queryCustRoles(String staffNo) throws ServiceException,AppException;
	
	
	/**
	 * @param page
	 * @return
	 * @查询员工列表-公共页面
	 */
	public Page<SysStaff> queryStaffByRoleForPage(Page<SysStaff> page) throws ServiceException,AppException;
	/**
	 * @param page
	 * @return
	 * @查询员工列表-公共页面
	 */
	public Page<SysStaff> queryStaffByRoleAndOrgForPage(Page<SysStaff> page,UserProfile userProfile) throws ServiceException,AppException;
	/**
	 * @param staffId
	 * @return
	 * @修改员工密码
	 */
	public void changePassword(String staffNo,String opwd,String npwd) throws ServiceException,AppException;

	/**
	 * @param staffId
	 * @return
	 * @重置员工密码
	 */
	public void resetPassword(String staffNo) throws ServiceException,AppException;

}