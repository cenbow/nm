package com.hs.loan.approve.service;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.loan.approve.entity.AppLoanApprH;
import com.hs.loan.approve.mapper.AppLoanApprHMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * APP_分期审批信息 业务处理
 * @author autocreate
 * @create 2015-11-23
 */
@Service
@Transactional(readOnly=true)
public class  AppLoanApprHService{
	@Autowired
	private AppLoanApprHMapper appLoanApprHMapper;
	/**
	 * 根据员工编号查询员工角色
	 * @param staffNo
	 * @return List<String>
	 */
	public List<String> selectRoleByStaffNo(String staffNo){
		return  appLoanApprHMapper.selectRoleByStaffNo(staffNo);
	}
	
	/**
	 * 新增 APP_分期审批信息
	 * @param appLoanApprH 新增对象
	 */
	@Transactional
	private void insert(AppLoanApprH appLoanApprH){
		appLoanApprHMapper.insert(appLoanApprH);
	}

	/**
	 * 通过主键修改 APP_分期审批信息
	 * @param map 修改参数Map
	 */
	@Transactional
	private void updateByPrimaryKeySelective(Map<String, Object> map){
		appLoanApprHMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_分期审批信息
	 * @param primaryKey 主键
	 */
	@Transactional
	private void deleteByPrimaryKey(String primaryKey){
		appLoanApprHMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_分期审批信息 对象
	 * @param primaryKey 主键
	 * @return APP_分期审批信息对象
	 */
	private AppLoanApprH getByPrimaryKey(String primaryKey){
		return appLoanApprHMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_分期审批信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	private List<AppLoanApprH> queryForList(Map<String, Object> param){
		return appLoanApprHMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_分期审批信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	private Page<AppLoanApprH> queryForPage(Page<AppLoanApprH> page){
		appLoanApprHMapper.queryForList(page.getPageParams());
		return (Page<AppLoanApprH>)page.getPageParams().get(Page.KEY);
	}
	
	/**
	 * 查询 APP_分期审批信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppLoanApprH> queryLoanAprHis(Page<AppLoanApprH> page,UserProfile userProfile){
		page.getPageParams().put("staffNo", userProfile.getStaffNo());
		page.getPageParams().put("roleNos", userProfile.getRoleNoSet());
		page.getPageParams().put("orgNo", userProfile.getOrgNo());
		return  this.queryForPage(page);
	} 
}