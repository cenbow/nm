package com.hs.system.staff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.ServiceException;
import com.hs.cache.busi.service.RedisCacheApi;
import com.hs.cache.config.DB;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.system.entity.SysStaff;
import com.hs.system.mapper.PubSysStaffMapper;
import com.hs.system.util.BeanUtil;
import com.hs.utils.StringUtils;

/**
 * 员工信息 业务处理
 * 
 * @author autocreate
 * @create 2015-09-23
 */
@Service
@Transactional(readOnly = true)
public class PubSysStaffService {
	@Autowired
	private PubSysStaffMapper sysStaffMapper;

	@Autowired
	private RedisCacheApi redisCacheApi;
	
	Logger log = Logger.getLogger(this.getClass());
	/**
	 * 查询 员工信息 列表
	 * 
	 * @param param
	 * @return List<T>
	 */
	public List<SysStaff> queryForList(Map<String, Object> param) {
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
			list = sysStaffMapper.queryForList(param);
		}
		return list;
	}

	/**
	 * 查询 员工信息 分页列表
	 * 
	 * @param param
	 * @return List<T>
	 */
	public Page<SysStaff> queryForPage(Page<SysStaff> page) {
		sysStaffMapper.queryForList(page.getPageParams());
		return (Page<SysStaff>) page.getPageParams().get(Page.KEY);
	}

	/**
	 * 查询 员工信息 分页列表
	 * 
	 * @param param
	 * @return List<T>
	 */
	public Page<SysStaff> queryStaffByRole(Page<SysStaff> page) {
		sysStaffMapper.queryStaffByRole(page.getPageParams());
		return (Page<SysStaff>) page.getPageParams().get(Page.KEY);
	}

	/**
	 * 查询 员工信息 分页列表
	 * 
	 * @param param
	 * @return List<T>
	 */
	public Page<SysStaff> queryStaffByRoleAndOrg(Page<SysStaff> page, UserProfile userProfile) {
		Map<String, Object> param = page.getPageParams();
		Set<String> roles = userProfile.getRoleNoSet();
		for (String role : roles) {
			if (PubBusinessConstant.ROLE_R_SYS_SUPER.equals(role)) {
				param.put("orgNo", "");
				break;
			} else if (PubBusinessConstant.ROLE_R_COLLEC_MGR.equals(role)) {
				param.put("orgNo", "");
				break;
			} else if (PubBusinessConstant.ROLE_R_COLLEC_STAFF.equals(role)) {
				param.put("orgNo", userProfile.getOrgNo());
				break;
			} else if ("r_collec_teamM1".equals(role)) {// 催收组长M1
				param.put("orgNo", userProfile.getOrgNo());
				break;
			} else if ("r_collec_teamM2".equals(role)) {// 催收组长M2
				param.put("orgNo", userProfile.getOrgNo());
				break;
			}

		}
		sysStaffMapper.queryStaffByRole(param);
		return (Page<SysStaff>) page.getPageParams().get(Page.KEY);
	}

	/**
	 * 通过主键取得 员工信息 对象
	 * 
	 * @param id
	 * @return
	 */

	public SysStaff getByStaffId(String id) {
		return sysStaffMapper.getByPrimaryKey(id);
	}

	/**
	 * 根据员工号获取员工信息
	 * 
	 * @param staffNo
	 * @return
	 */

	public SysStaff getByStaffNo(String staffNo) {
		SysStaff vo = null;
		Map map = new HashMap<>();
		map.put("staffNo", staffNo);
		List list = this.queryForList(map);
		if (list != null && list.size() > 0) {
			vo = (SysStaff) list.get(0);
		}
		return vo;
	}

	/**
	 * 校验员工密码是否正确
	 * 
	 * @param staffNo
	 * @return
	 */

	public boolean getByStaffNo(String staffNo, String pwd) {
		boolean flag = false;
		SysStaff staff = this.getByStaffNo(staffNo);
		if (staff == null || StringUtils.isEmpty(staff.getLoginPwd())) {
			throw new ServiceException("员工信息为空或密码为空");
		}
		if (staff.getLoginPwd().equals(pwd)) {
			flag = true;
		}
		return flag;

	}

	/**
	 * 根据员工号获取员工的区域经理
	 * 
	 * @param staffNo
	 * @return
	 */

	public Map<String, String> getStaffMarg(String staffNo) {
		Map<String, String> param = new HashMap<>();
		param.put("staffNo", staffNo);
		param.put("roleNo", PubBusinessConstant.ROLE_R_SALE_MGR_AREA);
		List<Map<String, String>> list = sysStaffMapper.queryStaffMagg(param);
		if (list == null || list.isEmpty()) {
			return new HashMap<>();
		}
		return list.get(0);
	}

	/**
	 * 获取包含的销售列表
	 * 
	 * @param pageParams
	 */
	public List<SysStaff> getIncludeStaffLst(Map<String, Object> param) {
		return sysStaffMapper.getIncludeStaffLst(param);
	}

	/**
	 * 获取 不包含的 销售列表
	 * 
	 * @param pageParams
	 */
	public List<SysStaff> getNotIncludeStaffLst(Map<String, Object> pageParams) {
		return sysStaffMapper.getNotIncludeStaffLst(pageParams);
	}

	/**
	 * 根据用户登陆号获取用户信息
	 * 
	 * @param loginNo
	 * @return
	 *//*
		 * @Transactional
		 * 
		 * public UserProfile queryUserProfile(String loginNo){
		 * 
		 * SysStaff staff = this.getByStaffNo(loginNo);
		 * 
		 * UserProfile profile= new UserProfile();
		 * profile.setLoginNo(staff.getLoginNo());
		 * profile.setStaffNo(staff.getLoginNo());
		 * profile.setStaffName(staff.getStaffName());
		 * 
		 * List<SysRole> rolesobj= queryCustRoles(loginNo); Set set = new
		 * HashSet(); for (int i = 0; i < rolesobj.size(); i++) { SysRole
		 * sysRole = rolesobj.get(i); if(i == 0){
		 * profile.setRoleNo(sysRole.getRoleNo());
		 * profile.setRoleName(sysRole.getRoleName()); } String roleinfo=
		 * sysRole.getRoleNo()+"|"+sysRole.getRoleName(); set.add(roleinfo); }
		 * profile.setRoleNos(set);
		 * 
		 * profile.setOrgNo(staff.getBelgOrgNo());
		 * 
		 * 
		 * SysOrg sysOrg = this.queryCustBelongOrg(loginNo);
		 * 
		 * profile.setOrgName(sysOrg.getOrgName());
		 * profile.setParentOrgNo(sysOrg.getParOrgNo());
		 * 
		 * return profile;
		 * 
		 * }
		 */

}