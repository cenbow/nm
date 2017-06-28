package com.hs.system.priv.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.system.api.SysPrivInfoApi;
import com.hs.system.contant.SysPubContant;
import com.hs.system.dto.SysPrivManagerBo;
import com.hs.system.entity.SysPrivInfo;
import com.hs.system.entity.SysStaff;
import com.hs.system.priv.mapper.SysPrivInfoMapper;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;

/**
 * 角色权限设置 业务处理
 * @author autocreate
 * @create 2015-09-23
 */
@Service("sysPrivInfoService")
@Transactional(readOnly=true)
public class  SysPrivInfoService implements SysPrivInfoApi{
	@Autowired
	private SysPrivInfoMapper sysPrivInfoMapper;
	
	/**
	 * 新增 角色权限设置
	 * @param vo
	 * @return
	 */
	@Transactional
	public void insert(SysPrivInfo sysPrivInfo){
		sysPrivInfoMapper.insert(sysPrivInfo);
	}

	/**
	 * 通过主键修改 角色权限设置
	 * @param map
	 * @return
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		sysPrivInfoMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 角色权限设置
	 * @param primaryKey
	 * @return
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		sysPrivInfoMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 角色权限设置 对象
	 * @param id
	 * @return
	 */
	public SysPrivInfo getByPrimaryKey(String primaryKey){
		return sysPrivInfoMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 角色权限设置 列表
	 * @param param
	 * @return List<T>
	 */
	public List<SysPrivInfo> queryForList(Map<String, Object> param){
		return sysPrivInfoMapper.queryForList(param);
	}
	
	/**
	 * 查询 角色权限设置 分页列表
	 * @param param
	 * @return List<T>
	 */
	public Page<SysPrivInfo> queryForPage(Page<SysPrivInfo> page){
		sysPrivInfoMapper.queryForList(page.getPageParams());
		return (Page<SysPrivInfo>)page.getPageParams().get(Page.KEY);
	}
	
	/**
	 * 保存或修改权限设置
	 * @param sysPrivInfo
	 */
	@Transactional
	public void saveOrUpdate(SysPrivInfo sysPrivInfo){
		String privType = sysPrivInfo.getPrivType();
		String autTypCod = sysPrivInfo.getAutTypCod();
		if(StringUtils.isEmpty(privType)){
			throw new AppException("类型不可为空");
		}
		if(StringUtils.isEmpty(autTypCod)){
			throw new AppException("权限类型不可为空");
		}
		
		if(privType.equals("0")){//角色类型的权限
			sysPrivInfo.setStaffNo(null);
		}
		if(privType.equals("1")){//员工类型的权限
			sysPrivInfo.setOrgPath(null);
			sysPrivInfo.setOrgPathName(null);
			sysPrivInfo.setOrgMatchTyp(null);
			sysPrivInfo.setRoleNo(null);
		}
		if(autTypCod.equals(SysPubContant.AUTTYPE_ZD)){//权限类型为指定
			/*sysPrivInfo.setAppointOrgNo(null);
			sysPrivInfo.setAppointOrgClass(null);
			sysPrivInfo.setAppointRoleNo(null);
			sysPrivInfo.setAppointStaffNo(null);
			sysPrivInfo.setAppointRoleName(null);
			sysPrivInfo.setAppointStaffName(null);
			sysPrivInfo.setAppointOrgName(null);*/
		}
		
		String id = sysPrivInfo.getId();
		//保存
		if(StringUtils.isEmpty(id)){
			sysPrivInfo.setId(RandomUtil.getUUID());
			sysPrivInfo.setInstDate(new Date());
			sysPrivInfo.setUpdtDate(null);
			insert(sysPrivInfo);
			return;
		}
		//更新
		sysPrivInfo.setUpdtDate(new Date());
		deleteByPrimaryKey(sysPrivInfo.getId());
		insert(sysPrivInfo);
		
	}

	/**
	 * 通过id 批量删除privinfo
	 */
	@Transactional
	@Override
	public void deletePrivInfos(List<String> param) {
		for(String id : param){
			deleteByPrimaryKey(id);
		}
	}
	
	/**
	 * 分页查询privmanage bo
	 * @param sysPrivInfo
	 * @return
	 */
	public Page<SysPrivManagerBo> queryPrivManagerBoPage(Page<SysPrivManagerBo> page){
		List<SysPrivManagerBo> lst = sysPrivInfoMapper.queryPrivMangerInfoLst(page.getPageParams());
		return (Page<SysPrivManagerBo>)page.getPageParams().get(Page.KEY);
	}
	
	
}