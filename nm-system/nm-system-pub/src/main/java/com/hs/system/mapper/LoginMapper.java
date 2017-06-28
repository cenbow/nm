package com.hs.system.mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.commons.bo.Menu;
import com.hs.system.entity.SysOrg;
import com.hs.system.entity.SysRole;
import com.hs.system.entity.SysStaff;

/**
 * 码表公用mapper
 * @author zym
 * @create 2015-09-24
 */
@MyBatisRepository
public interface  LoginMapper{
	
	/**
	 * 查询列表
	 * @param param
	 * @return List<T>
	 */
	public List<SysStaff> queryForList(Map<String, Object> param);
	
	/**
	 * 查询员工可用角色
	 * @param staffNo
	 * @return List<SysRole>
	 */
	public List<SysRole> queryCustEnableRoles(String staffNo);
	
	/**
	 * 查询员工归属机构
	 * @param staffNo
	 * @return SysOrg
	 */
	public SysOrg getStaffOrg(String staffNo);
	
	/**
	 * 通过角色ID获取 菜单资源信息
	 * @param roleId	
	 * @return Set<String> url
	 */
	public Set<String> getRoleResource(String roleId);
	
	/**
	 * 获取用户所有菜单
	 * @param staffNo	人员编号
	 * @param sysCode	系统编号
	 * @return List<Menu>
	 */
	public List<Menu> queryMenu(@Param("staffNo")String staffNo, @Param("sysCode") String sysCode);
	
}