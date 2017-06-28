package com.hs.system.staff.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.cache.busi.service.RedisCacheApi;
import com.hs.cache.config.DB;
import com.hs.commons.constants.CommonConstant;
import com.hs.system.api.SysStaffApi;
import com.hs.system.dto.SysStaffRoleBO;
import com.hs.system.entity.SysOrg;
import com.hs.system.entity.SysRole;
import com.hs.system.entity.SysStaff;
import com.hs.system.entity.SysStaffOrg;
import com.hs.system.entity.SysStaffRole;
import com.hs.system.mapper.PubSysOrgMapper;
import com.hs.system.role.mapper.SysRoleMapper;
import com.hs.system.staff.PubSysStaffService;
import com.hs.system.staff.mapper.SysStaffMapper;
import com.hs.system.staff.mapper.SysStaffOrgMapper;
import com.hs.system.staff.mapper.SysStaffRoleMapper;
import com.hs.system.util.BeanUtil;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;
import com.nm.cache.busi.service.OperateCacheServiceApi;

/**
 * 员工信息 业务处理
 * @author autocreate
 * @create 2015-09-23
 */
@Service
@Transactional(readOnly=true)
public class  SysStaffService implements SysStaffApi{
	@Autowired
	private SysStaffMapper sysStaffMapper;
	
	@Autowired
	private SysStaffOrgMapper sysUserOrgMapper;
	
	@Autowired
	private SysStaffRoleMapper sysUserRoleMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Autowired
	private PubSysOrgMapper sysOrgMapper;
	@Autowired
	private PubSysStaffService pubSysStaffService;
	
	@Autowired
	private RedisCacheApi redisCacheApi;
	
	@Autowired
	private OperateCacheServiceApi operateCacheServiceApi;
	
	/**
	 * 通过主键修改 员工信息
	 * @param map
	 * @return
	 */
	@Transactional 
	public void updateByPrimaryKeySelective(Map<String, Object> map) {
		String id = map.get(DB.ID).toString();
		Map<String,Object> del = new HashMap<String, Object>();
		del.put(DB.ID, id);
		int n = sysStaffMapper.updateByPrimaryKeySelective(map);
		if(n>0){
			SysStaff s = sysStaffMapper.getByPrimaryKey(id);
			Map<String,Object> data = BeanUtils.bean2map(s);
			operateCacheServiceApi.modifyCacheByCondition(DB.SYS_STAFF, del, data);
		}
	}

	/**
	 * 通过主键删除 员工信息
	 * @param primaryKey
	 * @return
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){

		int n = sysStaffMapper.deleteByPrimaryKey(primaryKey);
		if(n>0){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", primaryKey);
			operateCacheServiceApi.delCache(DB.SYS_STAFF, map);
		}
	}

 
	/**
	 * 查询 员工信息 列表
	 * @param param
	 * @return List<T>
	 */
	public List<SysStaff> queryForList(Map<String, Object> param){
		List<SysStaff> list = new ArrayList<SysStaff>();
		boolean f = false;
		try {
			List<Map<String,Object>> lst = redisCacheApi.queryCacheByCondition(DB.SYS_STAFF, param);
			list = BeanUtil.ListMap2JavaBean(lst, SysStaff.class);
		} catch (Exception e) {
			throw e;
//			f = true;
		}
		if(list.isEmpty() || f){
			list = sysStaffMapper.queryForList(param);
		}
		return list;
	}
	
	/**
	 * 查询 员工信息 分页列表
	 * @param param
	 * @return List<T>
	 */
	public Page<SysStaff> queryForPage(Page<SysStaff> page)  {
		sysStaffMapper.queryForList(page.getPageParams());
		return (Page<SysStaff>)page.getPageParams().get(Page.KEY);
	}

	
	/**
	 * 新增修改员工
	 * @param sysStaff
	 */
	
	@Transactional 
	@Override
	public void save(SysStaff sysStaff) {
		if(null == sysStaff){
			throw new ServiceException("新增员工失败");
		}
		if(!StringUtils.isEmpty(sysStaff.getId())){
			updateByPrimaryKeySelective(BeanUtils.bean2map(sysStaff));
		}else{
			Map<String,Object> param = new HashMap<>();
			param.put("staffNo", sysStaff.getStaffNo());
			System.out.println("sysStaff.getStaffNo()=="+sysStaff.getStaffNo()+"===");
			List<SysStaff> list = this.queryForList(param);
			if(list == null || list.size() == 0){
				sysStaff.setId(RandomUtil.getUUID());
				String logpwd = sysStaff.getLoginPwd();
				sysStaff.setLoginPwd(StringUtils.MD5Encode(logpwd).toLowerCase());
				int n = sysStaffMapper.insert(sysStaff);
				if(n>0){
					Map<String,Object> map = BeanUtils.bean2map(sysStaff);
					operateCacheServiceApi.addCache(DB.SYS_STAFF, map);
				}
			}else{
				throw new ServiceException("员工已经存在");
			}
		}
		
		
	}
	
	/**
	 * 通过主键取得 员工信息 对象
	 * @param id
	 * @return
	 */
	@Override
	public SysStaff getByStaffId(String id) {
		List<SysStaff> list = new ArrayList<SysStaff>();
		SysStaff sysStaff = null;
		boolean f = false;
		try {
			Map<String,Object> param = new HashMap<String, Object>();
			param.put(DB.ID, id);
			List<Map<String,Object>> lst = redisCacheApi.queryCacheByCondition(DB.SYS_STAFF, param);
			list = BeanUtil.ListMap2JavaBean(lst, SysStaff.class);
			if(!list.isEmpty()){
				sysStaff = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			f = true;
		}
		if(sysStaff==null || f){
			sysStaff = sysStaffMapper.getByPrimaryKey(id);
		}
		return sysStaff;
	}

	/**
	 * 根据员工号获取员工信息
	 * @param staffNo
	 * @return
	 */
	@Override
	public SysStaff getByStaffNo(String staffNo) {
		SysStaff vo = null;
		Map map = new HashMap<>();
		map.put("staffNo",staffNo);
		List list = this.queryForList(map);
		if(list != null && list.size() > 0){
			vo = (SysStaff) list.get(0);
		}
		return vo;
	}

	/**
	 * 新增员工机构信息	
	 */
	@Transactional 
	@Override
	public void saveStaffOrg(SysStaffOrg sysUserOrg) {

		sysUserOrgMapper.deleteStaffOrgByStaffId(sysUserOrg.getStaffId());
		sysUserOrg.setId(RandomUtil.getUUID());
		sysUserOrgMapper.insert(sysUserOrg);
		
		SysOrg sysOrg= sysOrgMapper.getByPrimaryKey(sysUserOrg.getOrgId());
		Map map = new HashMap<>();
		map.put("id", sysUserOrg.getStaffId());
		map.put("belgOrgNo", sysOrg.getOrgNo());
		updateByPrimaryKeySelective(map);
	}


	/**
	 * 保存 用户角色
	 * @param vo
	 * @return
	 */
	@Transactional 
	@Override
	public void saveStaffRoles(List<SysStaffRole> sysUserRoles)  {
		 
		for (SysStaffRole sysUserRole : sysUserRoles) {
			sysUserRole.setId(RandomUtil.getUUID());
			sysUserRoleMapper.insert(sysUserRole);
		}
	}

	/**
	 * 通过主键删除 用户角色
	 * @param primaryKey
	 * @return
	 */
	@Transactional
	@Override
	public void removeStaffRoles(List<String> relIds)  {
		for (String relId : relIds) {
			sysUserRoleMapper.deleteByPrimaryKey(relId);
		}
	}

	
	@Override
	public Page<SysRole> queryCustNotExistRoles(Page<SysRole> page) {
		sysRoleMapper.queryCustNotExistRoles(page.getPageParams());
		return (Page<SysRole>)page.getPageParams().get(Page.KEY);
	}
	
	
	@Override
	public SysOrg queryCustBelongOrg(String staffNo) {
		return sysOrgMapper.queryStaffBelongOrg(staffNo);
	}
	
	
	@Override
	public List<SysStaffRoleBO> queryCustRoles(String staffNo) {
		List<SysStaffRoleBO> list = sysRoleMapper.queryCustExistRoles(staffNo);
		return list;
	}

	/**
	 * 查询员工列表 
	 */
	@Override
	public Page<SysStaff> queryStaffByRoleForPage(Page<SysStaff> page) throws ServiceException, AppException {
		
		return pubSysStaffService.queryStaffByRole(page);
	}
	/**
	 * 查询员工列表 
	 */
	@Override
	public Page<SysStaff> queryStaffByRoleAndOrgForPage(Page<SysStaff> page,UserProfile userProfile) throws ServiceException, AppException {
		
		return pubSysStaffService.queryStaffByRoleAndOrg(page,userProfile);
	}
	
	/**
	 * 修改密码
	 */
	@Transactional 
	@Override
	public void changePassword(String staffNo,String opwd,String npwd) throws ServiceException, AppException {
		
		SysStaff sysStaff = this.getByStaffNo(staffNo);
		if(sysStaff == null){
			throw new ServiceException("员工信息为空,请联系管理员");
		}
		if(StringUtils.isEmpty(sysStaff.getLoginPwd())){
			throw new ServiceException("员工初始化密码为空,请联系管理员");
		}
		if(!opwd.equals(sysStaff.getLoginPwd())){
			throw new ServiceException("原密码错误");
		}
		Map<String,Object> map = new HashMap<>();
		map.put("loginPwd", npwd);
		map.put("staffNo", staffNo);
		map.put("staffStat", CommonConstant.STAT_ENABLE);
		sysStaffMapper.changePassword(map);
		map.put(DB.ID, sysStaff.getId());
		updateByPrimaryKeySelective(map);
		
	}
	
	
	/**
	 * 根据用户登陆号获取用户信息
	 * @param loginNo
	 * @return
	 *//*
	@Transactional
	@Override
	public UserProfile queryUserProfile(String loginNo){
		
		SysStaff staff = this.getByStaffNo(loginNo);
		
		UserProfile profile= new UserProfile();
		profile.setLoginNo(staff.getLoginNo());
		profile.setStaffNo(staff.getLoginNo());
		profile.setStaffName(staff.getStaffName());
		
		List<SysRole> rolesobj= queryCustRoles(loginNo);
		Set set = new HashSet();
		for (int i = 0; i < rolesobj.size(); i++) {
			SysRole sysRole = rolesobj.get(i);
			if(i == 0){
				profile.setRoleNo(sysRole.getRoleNo());
				profile.setRoleName(sysRole.getRoleName());
			}
			String roleinfo=	sysRole.getRoleNo()+"|"+sysRole.getRoleName();
			set.add(roleinfo);
		}
		profile.setRoleNos(set);
	   
		profile.setOrgNo(staff.getBelgOrgNo());
		
		
		SysOrg sysOrg = this.queryCustBelongOrg(loginNo);
		
		profile.setOrgName(sysOrg.getOrgName());
		profile.setParentOrgNo(sysOrg.getParOrgNo());
	 
		return profile;
		
	}*/
	/**
	 * 重置密码
	 */
	@Transactional 
	@Override
	public void resetPassword(String staffNo) throws ServiceException, AppException {
		SysStaff sysStaff = this.getByStaffNo(staffNo);
		if(sysStaff == null){
			throw new ServiceException("员工信息为空,请联系管理员");
		}
		Map<String,Object> map = new HashMap<>();
		map.put("loginPwd", StringUtils.MD5Encode("123456").toLowerCase());
		map.put("staffNo", staffNo);
		map.put("staffStat", CommonConstant.STAT_ENABLE);
	
		sysStaffMapper.changePassword(map);
		
		Map<String,Object> del = new HashMap<String,Object>();
		del.put("id", sysStaff.getId());
		
		sysStaff.setLoginPwd(map.get("loginPwd").toString());
		Map<String,Object> mm = BeanUtils.bean2map(sysStaff);
		operateCacheServiceApi.modifyCacheByCondition(DB.SYS_STAFF, del,mm);
	}
}