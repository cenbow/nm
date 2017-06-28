package com.hs.system.org.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.neo4j.cypher.internal.compiler.v2_1.functions.Has;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.cache.busi.service.RedisCacheApi;
import com.hs.cache.config.DB;
import com.hs.system.api.SysOrgApi;
import com.hs.system.entity.SysMenu;
import com.hs.system.entity.SysOrg;
import com.hs.system.org.mapper.SysOrgMapper;
import com.hs.system.util.BeanUtil;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;
import com.nm.cache.busi.service.OperateCacheServiceApi;

/**
 * 机构信息 业务处理
 * 
 * @author autocreate
 * @create 2015-09-23
 */
@Service
@Transactional(readOnly = true)
public class SysOrgService implements SysOrgApi {
	@Autowired
	private SysOrgMapper sysOrgMapper;

	@Autowired
	private OperateCacheServiceApi operateCacheServiceApi;
	
	@Autowired
	private RedisCacheApi redisCacheApi;
	
	Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 查询 机构信息 列表
	 * 
	 * @param param
	 * @return List<T>
	 */
	@Override
	public List<SysOrg> queryForList(Map<String, Object> param) {
//		return sysOrgMapper.queryForList(param);
		List<SysOrg> list = new ArrayList<SysOrg>();
		boolean f = false;
		try {
			List<Map<String,Object>> lst = redisCacheApi.queryCacheByCondition(DB.SYS_ORG, param);
			list = BeanUtil.ListMap2JavaBean(lst, SysOrg.class);
		} catch (Exception e) {
			log.error("call remote exception!", e);
			f = true;
		}
		if(list.isEmpty() || f){
			list = sysOrgMapper.queryForList(param);
		}
		return list;
	}

	/**
	 * 查询 机构信息 分页列表
	 * 
	 * @param param
	 * @return List<T>
	 */
	@Override
	public Page<SysOrg> queryForPage(Page<SysOrg> page, UserProfile userProfile) {
		sysOrgMapper.queryForList(page.getPageParams());
		return (Page<SysOrg>) page.getPageParams().get(Page.KEY);
	}

	/**
	 * 查询机构列表（顶级机构）
	 */
	@Transactional
	@Override
	public List<SysOrg> queryForList(UserProfile userProfile) {
//		return sysOrgMapper.queryForList(null);
		Map<String,Object> mm = new HashMap<String, Object>();
		return queryForList(mm);
	}

	/**
	 * 下级机构查询
	 */
	@Transactional
	@Override
	public List<SysOrg> queryForList(String parOrgNo, UserProfile userProfile) {

		Map<String, Object> map = BeanUtils.bean2map(userProfile);
		map.put("parOrgNo", parOrgNo);
//		return sysOrgMapper.queryForList(map);
		return queryForList(map);
	}

	/**
	 * 新增、修改 机构信息
	 * 
	 * @param vo
	 * @return
	 */
	@Transactional
	@Override
	public SysOrg save(SysOrg sysOrg) {
		int cn = 0;
		if (sysOrg != null) {
			if (StringUtils.isEmpty(sysOrg.getId())) {
				sysOrg.setId(RandomUtil.getUUID());
				cn = sysOrgMapper.insert(sysOrg);
				if(cn>0){
					Map<String, Object> map = BeanUtils.bean2map(sysOrg);
					operateCacheServiceApi.addCache(DB.SYS_ORG, map);
				}
			} else {
				cn = sysOrgMapper.updateByPrimaryKeySelective(BeanUtils.bean2map(sysOrg));
				if(cn>0){
					 Map<String,Object> del = new HashMap<String,Object>();
					 del.put(DB.ID, sysOrg.getId());
					 SysOrg m = sysOrgMapper.getByPrimaryKey(sysOrg.getId());
					 Map<String,Object> news = BeanUtils.bean2map(m);
					 operateCacheServiceApi.modifyCacheByCondition(DB.SYS_ORG, del,news);
				}
			}
		}
		if (cn == 0) {
			throw new ServiceException("保存机构信息失败");
		}
		return getByOrgId(sysOrg.getId());
	}

	/**
	 * 删除 机构信息
	 * 
	 * @param vo
	 * @return
	 */
	@Transactional
	@Override
	public void deleteByOrgId(String orgId) {

		SysOrg sysOrg = this.getByOrgId(orgId);
		Map<String, Object> map = new HashMap<>();
		map.put("parOrgNo", sysOrg.getOrgNo());
		List<SysOrg> list = this.queryForList(map);// 如果是父机构不能做删除，先删除子机构
		if (list == null || list.size() == 0) {
			int cn = sysOrgMapper.deleteByPrimaryKey(orgId);
			if (cn != 1) {
				throw new ServiceException("删除机构信息失败");
			}
			if(cn>0){
				Map<String,Object> del = new HashMap<String,Object>();
				del.put("id", orgId);
				operateCacheServiceApi.delCache(DB.SYS_ORG, del);
			}
		} else {
			throw new ServiceException("该机构为父级机构，请删除子机构，再做删除");
		}

	}

	/**
	 * 根据Id获取机构信息
	 */
	@Transactional
	@Override
	public SysOrg getByOrgId(String orgId) {
//		return sysOrgMapper.getByPrimaryKey(orgId);
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("id", orgId);
		List<SysOrg> l = queryForList(param);
		if(l!=null && !l.isEmpty()){
			return l.get(0);
		}
		return null;
	}

	/**
	 * 机构号查询jig
	 */
	@Transactional
	@Override
	public SysOrg getByOrgNo(String orgNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgNo", orgNo);
//		List<SysOrg> list = sysOrgMapper.queryForList(map);
		List<SysOrg> list = queryForList(map);
		SysOrg org = null;
		if (list != null && list.size() > 0) {
			org = list.get(0);
		}
		return org;
	}

	/**
	 * 本级及下级机构查询
	 */
	@Transactional
	@Override
	public List<SysOrg> queryOrgForList(UserProfile userProfile) throws ServiceException, AppException {
		Map<String, Object> map = new HashMap<>();
		map.put("orgNo", userProfile.getOrgNo());
		return sysOrgMapper.queryOrgForList(map);
		//return queryForList(map);
	}

}