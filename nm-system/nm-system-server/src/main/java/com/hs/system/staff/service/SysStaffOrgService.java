package com.hs.system.staff.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.ServiceException;
import com.hs.cache.busi.service.RedisCacheApi;
import com.hs.system.api.SysStaffOrgApi;
import com.hs.system.entity.SysStaffOrg;
import com.hs.system.staff.mapper.SysStaffOrgMapper;
import com.hs.utils.RandomUtil;
import com.nm.cache.busi.service.OperateCacheServiceApi;

/**
 * 机构人员关系 业务处理
 * @author autocreate
 * @create 2015-09-23
 */
@Service
@Transactional(readOnly=true)
public class  SysStaffOrgService implements SysStaffOrgApi{
	@Autowired
	private SysStaffOrgMapper sysUserOrgMapper;
	
	@Autowired
	private RedisCacheApi redisCacheApi;
	
	@Autowired
	private OperateCacheServiceApi operateCacheServiceApi;
	
	Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 新增 机构人员关系
	 * @param vo
	 * @return
	 */
	@Transactional 
	public void saveStaffOrg(SysStaffOrg sysUserOrg) {
		sysUserOrgMapper.deleteStaffOrgByStaffId(sysUserOrg.getStaffId());
		sysUserOrg.setId(RandomUtil.getUUID());
		sysUserOrgMapper.insert(sysUserOrg);
	}

	/**
	 * 通过主键修改 机构人员关系
	 * @param map
	 * @return
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		sysUserOrgMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 机构人员关系
	 * @param primaryKey
	 * @return
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		sysUserOrgMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 机构人员关系 对象
	 * @param id
	 * @return
	 */
	public SysStaffOrg getByPrimaryKey(String primaryKey){
		return sysUserOrgMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 机构人员关系 列表
	 * @param param
	 * @return List<T>
	 */
	public List<SysStaffOrg> queryForList(Map<String, Object> param){
		return sysUserOrgMapper.queryForList(param);
	}
	
	/**
	 * 查询 机构人员关系 分页列表
	 * @param param
	 * @return List<T>
	 */
	public Page<SysStaffOrg> queryForPage(Page<SysStaffOrg> page){
		sysUserOrgMapper.queryForList(page.getPageParams());
		return (Page<SysStaffOrg>)page.getPageParams().get(Page.KEY);
	}
}