package com.hs.loan.approve.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.loan.approve.entity.AppApprStaffGroup;
import com.hs.loan.approve.mapper.AppApprStaffGroupMapper;

/**
 * APP_审批人员与组关联信息 业务处理
 * @author autocreate
 * @create 2015-11-23
 */
@Service
@Transactional(readOnly=true)
public class  AppApprStaffGroupService{
	@Autowired
	private AppApprStaffGroupMapper appApprStaffGroupMapper;
	
	/**
	 * 新增 APP_审批人员与组关联信息
	 * @param appApprStaffGroup 新增对象
	 */
	@Transactional
	public void insert(AppApprStaffGroup appApprStaffGroup){
		appApprStaffGroupMapper.insert(appApprStaffGroup);
	}

	/**
	 * 通过主键修改 APP_审批人员与组关联信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appApprStaffGroupMapper.updateByPrimaryKeySelective(map);
	}
	
	/**
	 * 通过主键修改 APP_审批人员与组关联信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByApprStaffGrp(AppApprStaffGroup apprStaffGrp){
		appApprStaffGroupMapper.updateByApprStaffGrp(apprStaffGrp);
	}

	/**
	 * 通过主键删除 APP_审批人员与组关联信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appApprStaffGroupMapper.deleteByPrimaryKey(primaryKey);
	}
	
	/**
	 * 通过主键删除 APP_审批人员与组关联信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByCont(Map<String,Object> param){
		appApprStaffGroupMapper.deleteByCont(param);
	}

	/**
	 * 通过主键取得 APP_审批人员与组关联信息 对象
	 * @param primaryKey 主键
	 * @return APP_审批人员与组关联信息对象
	 */
	public AppApprStaffGroup getByPrimaryKey(String primaryKey){
		return appApprStaffGroupMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_审批人员与组关联信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppApprStaffGroup> queryForList(Map<String, Object> param){
		return appApprStaffGroupMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_审批人员与组关联信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppApprStaffGroup> queryForPage(Page<AppApprStaffGroup> page){
		appApprStaffGroupMapper.queryForList(page.getPageParams());
		return (Page<AppApprStaffGroup>)page.getPageParams().get(Page.KEY);
	}
	
	/**
	 * 查询 APP_审批人员与组关联信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppApprStaffGroup> queryApprStaffGrpForPage(Page<AppApprStaffGroup> page,UserProfile userProfile){
		//page.getPageParams().put("staffNo", userProfile.getStaffNo());
		page.getPageParams().put("roleNos", userProfile.getRoleNoSet());
		page.getPageParams().put("orgNo", userProfile.getOrgNo());
		return this.queryForPage(page);
	}
	
	/**
	 * 通过员工号修改员工审批状态
	 * @param primaryKey 主键
	 */
	@Transactional
	public void updateStaffApprStat(String staffNo,String apprStat){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("apprStat", apprStat);
		param.put("staffNo", staffNo);
		appApprStaffGroupMapper.updateApprstatByStaffNo(param);
	}
}