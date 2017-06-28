package com.hs.system.shiro;

import java.util.Set;

import com.hs.base.entity.UserProfile;

/**
 * shiro查询接口
 * @author jqiu
 */
public interface IShiroService {
	
	/**
	 * 通过登陆号查询用户信息
	 * @param loginNo	登陆号
	 * @return
	 */
	public UserProfile getUserProfile(String loginNo);
	
	/**
	 * 通过登陆号查询用户角色
	 * @param staffNo	用户编号
	 * @return
	 */
	public Set<String> findRoles(String staffNo);
	
	/**
	 * 通过登陆号查询用户资源
	 * @param staffNo	用户编号
	 * @return
	 */
	public Set<String> findPermissions(String staffNo);
	
	/**
	 * 通过员工工号获取登录账号
	 * @param staffNo 用户工号【用户编号】
	 * @return 登录账号
	 */
	String getLoginNoByStaffNo(String staffNo);

}
