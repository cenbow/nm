package com.hs.system.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.cache.busi.service.RedisCacheApi;
import com.hs.cache.config.DB;
import com.hs.commons.bo.Menu;
import com.hs.commons.constants.CommonConstant;
import com.hs.commons.service.ISimpleMenuService;
import com.hs.commons.tools.SysEnv;
import com.hs.system.entity.SysCodInfo;
import com.hs.system.entity.SysOrg;
import com.hs.system.entity.SysRole;
import com.hs.system.entity.SysStaff;
import com.hs.system.mapper.LoginMapper;
import com.hs.system.shiro.IShiroService;
import com.hs.system.util.BeanUtil;
import com.nm.cache.busi.service.OperateCacheServiceApi;

/**
 * 登陆信息
 * @author jqiu
 * @create 2015-09-24
 */
@Service
@Transactional(readOnly=true)
public class  LoginService implements IShiroService,ISimpleMenuService{
	@Autowired
	private LoginMapper loginMapper;
	
	@Autowired
	private RedisCacheApi redisCacheApi;
	
	Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 根据用户登陆号获取用户信息
	 * @param loginNo 登陆号
	 * @return
	 */
	@Override
	public UserProfile getUserProfile(String loginNo){
		
		SysStaff staff = this.getStaffByLoginNo(loginNo);
		
		UserProfile profile= new UserProfile();
		profile.setLoginNo(staff.getLoginNo());
		profile.setStaffNo(staff.getStaffNo());
		profile.setStaffName(staff.getStaffName());
		profile.setStaffautName(staff.getStaffAutName());
		List<SysRole> rolesList= queryCustEnableRoles(staff.getStaffNo());
		
		Set<String> set = new HashSet<String>();
		StringBuffer roleNameSb = new StringBuffer();
		
		for (SysRole sysRole : rolesList) {
			set.add(sysRole.getRoleNo());
			roleNameSb.append(",").append(sysRole.getRoleName());
		}

		profile.setRoleNames(roleNameSb.length()>0?roleNameSb.substring(1):"");
		profile.setRoleNoSet(set);
	   
		profile.setOrgNo(staff.getBelgOrgNo());
		
		
		SysOrg sysOrg = this.getStaffOrg(staff.getStaffNo());
		if(sysOrg != null){
			profile.setOrgName(sysOrg.getOrgName());
			profile.setParentOrgNo(sysOrg.getParOrgNo());
			profile.setOrgPath(sysOrg.getOrgCodPath());
			profile.setDepName(sysOrg.getOrgName());
			profile.setDepPath(sysOrg.getOrgCodPath());
		}
	 
		profile.setSysCode(SysEnv.getProp(CommonConstant.CFG_SYSCODE));
		return profile;
		
	}
	

	/**
	 * 通过登陆号查询用户角色
	 * @param staffNo	用户编号
	 * @return
	 */
	@Override
	public Set<String> findRoles(String staffNo){
		List<SysRole> rolesList= queryCustEnableRoles(staffNo);
		
		Set<String> set = new HashSet<String>();
		
		for (SysRole sysRole : rolesList) {
			set.add(sysRole.getRoleNo());
		}
		return set;
	}
	
	/**
	 * 通过登陆号查询用户资源
	 * @param staffNo	用户编号
	 * @return
	 */
	@Override
	public Set<String> findPermissions(String staffNo){
		//按用户分别查询是为了解决缓存的问题
		List<SysRole> rolesList= queryCustEnableRoles(staffNo);
		Set<String> allUrlSet = new HashSet<String>();
		for (SysRole sysRole : rolesList) {
			Set<String> roleUrlSet = loginMapper.getRoleResource(sysRole.getId());
			if(roleUrlSet != null){
				allUrlSet.addAll(roleUrlSet);
			}
		}
		return allUrlSet;
	}
	
	/**
	 * 
	 * @param staffNo
	 * @return
	 */
	private SysStaff getStaffByLoginNo(String loginNo) {
		SysStaff vo = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("loginNo",loginNo);
		List<SysStaff> list = this.queryStaffByCache(map);
		if(list != null && list.size() > 0){
			vo = (SysStaff) list.get(0);
		}else{
			throw new AppException("员工不存在！");
		}
		return vo;
	}
	
	public List<SysStaff> queryStaffByCache(Map<String, Object> param){
		List<SysStaff> list = new ArrayList<SysStaff>();
		boolean f = false;
		try {
			List<Map<String,Object>> lst = redisCacheApi.queryCacheByCondition(DB.SYS_STAFF, param);
			list = BeanUtil.ListMap2JavaBean(lst, SysStaff.class);
		} catch (Exception e) {
			log.error("call remote exception!", e);
			f = true;
		}
		if(list.size()==0 || f){
			list = this.queryForListByDB(param);
		}
		return list;
	}
	
	/**
	 * 查询 员工信息 列表
	 * @param param
	 * @return List<T>
	 */
	public List<SysStaff> queryForListByDB(Map<String, Object> param){
		return loginMapper.queryForList(param);
	}

	/**
	 * 查询员工可用角色
	 * @param staffNo
	 * @return List<SysRole>
	 */
	public List<SysRole> queryCustEnableRoles(String staffNo) {
		List<SysRole> list = loginMapper.queryCustEnableRoles(staffNo);
		return list;
	}
	
	/**
	 * 查询员工归属机构
	 * @param staffNo
	 * @return SysOrg
	 */
	public SysOrg getStaffOrg(String staffNo) {
		return loginMapper.getStaffOrg(staffNo);
	}


	
	/**
	 * 获取用户所有菜单
	 * @param staffNo	人员编号
	 * @param sysCode	系统编号
	 * @return List<Menu>
	 */
	public List<Menu> queryMenu(String staffNo, String sysCode) {
		return loginMapper.queryMenu(staffNo, sysCode);
	}


	@Override
	public String getLoginNoByStaffNo(String staffNo) {
		String loginNo = null;
		SysStaff vo  = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("staffNo",staffNo);
		List<SysStaff> list = this.queryStaffByCache(map);
		if(list != null && list.size() > 0){
			vo = (SysStaff) list.get(0);
			loginNo = vo.getLoginNo();
		}else{
			throw new AppException("员工不存在！");
		}
		return loginNo;
	}
}