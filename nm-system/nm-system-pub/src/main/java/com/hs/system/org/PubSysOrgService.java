package com.hs.system.org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.ServiceException;
import com.hs.cache.busi.service.RedisCacheApi;
import com.hs.cache.config.DB;
import com.hs.system.entity.SysOrg;
import com.hs.system.mapper.PubSysOrgMapper;
import com.hs.system.util.BeanUtil;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;
import com.nm.cache.busi.service.OperateCacheServiceApi;

/**
 * 机构信息 业务处理
 * @author autocreate
 * @create 2015-09-23
 */
@Service
@Transactional(readOnly=true)
public class  PubSysOrgService{
	@Autowired
	private PubSysOrgMapper sysOrgMapper;
	
	@Autowired
	private RedisCacheApi redisCacheApi;
	
	@Autowired
	private OperateCacheServiceApi operateCacheServiceApi;
	
	Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 查询 机构信息 列表
	 * @param param
	 * @return List<T>
	 */
	public List<SysOrg> queryForList(Map<String, Object> param) {
		List<SysOrg> list = new ArrayList<SysOrg>();
		boolean f = false;
		try {
			List<Map<String, Object>> lst = redisCacheApi.queryCacheByCondition(DB.SYS_ORG, param);
			list = BeanUtil.ListMap2JavaBean(lst, SysOrg.class);
		} catch (Exception e) {
			log.error("call remote exception!", e);
			f = true;
		}
		if(list.size()==0 || f){
			list = sysOrgMapper.queryForList(param);
		}
		return list;
	}
	
	/**
	 * 查询 机构信息 分页列表
	 * @param param
	 * @return List<T>
	 */
	public Page<SysOrg> queryForPage(Page<SysOrg> page,UserProfile userProfile) {
		sysOrgMapper.queryForList(page.getPageParams());
		return (Page<SysOrg>)page.getPageParams().get(Page.KEY);
	}

	/**
	 * 查询机构列表（顶级机构）
	 */
	public List<SysOrg> queryForList(UserProfile userProfile) {
		Map<String,Object> param = new HashMap<String, Object>();
		return this.queryForList(param);
//		return sysOrgMapper.queryForList(null);
	}
	
	/**
	 * 下级机构查询
	 */
	@Transactional
	
	public List<SysOrg> queryForList(String parOrgNo, UserProfile userProfile) {
		
		Map<String,Object> map=BeanUtils.bean2map(userProfile);
		map.put("parOrgNo", parOrgNo);
//		return sysOrgMapper.queryForList(map);
		return this.queryForList(map);
	}

	
	/**
	 * 新增、修改 机构信息
	 * @param vo
	 * @return
	 */
	@Transactional 
	public SysOrg save(SysOrg sysOrg) {
		int cn = 0;
		 if(sysOrg != null){
			if(StringUtils.isEmpty(sysOrg.getId())){
				sysOrg.setId(RandomUtil.getUUID());
				cn = sysOrgMapper.insert(sysOrg);
				if(cn>0){
					Map<String, Object> map = BeanUtils.bean2map(sysOrg);
					operateCacheServiceApi.addCache(DB.SYS_ORG, map);
				}
			 }else{
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
		 if(cn == 0){
			 throw new ServiceException("保存机构信息失败");
		 }
		return getByOrgId(sysOrg.getId());
	}

	/**
	 * 删除 机构信息
	 * @param vo
	 * @return
	 */
	@Transactional 
	public void deleteByOrgId(String orgId) {
		int cn = sysOrgMapper.deleteByPrimaryKey(orgId);
		if(cn !=1){
			 throw new ServiceException("删除机构信息失败");
		}
		if(cn>0){
			Map<String,Object> del = new HashMap<String,Object>();
			del.put("id", orgId);
			operateCacheServiceApi.delCache(DB.SYS_ORG, del);
		}
	}

	
	/**
	 * 根据Id获取机构信息
	 */
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
	public SysOrg getByOrgNo(String orgNo) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgNo", orgNo);
//		List<SysOrg> list= sysOrgMapper.queryForList(map);
		List<SysOrg> list = this.queryForList(map);
		SysOrg org = null;
		if(list != null && list.size() > 0){
			org = list.get(0);
		}
		return org;
	}
	/**
	 * 机构号查询jig
	 */
	public List<SysOrg> queryForListAuthr(String orgNo) {
		return sysOrgMapper.queryForListAuthr(orgNo);
	}
	
}