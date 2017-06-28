package com.hs.loan.approvecheck.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.approvecheck.bo.AppLoanApprCheckBo;
import com.hs.loan.approvecheck.entity.AppLoanApprCheck;
import com.hs.loan.approvecheck.mapper.AppLoanApprCheckMapper;

/**
 * APP_分期案件复核 业务处理
 * @author autocreate
 * @create 2016-11-24
 */
@Service
@Transactional(readOnly=true)
public class  AppLoanApprCheckService{
	@Autowired
	private AppLoanApprCheckMapper appLoanApprCheckMapper;
	
	/**
	 * 新增 APP_分期案件复核
	 * @param appLoanApprCheck 新增对象
	 */
	@Transactional
	public void insert(AppLoanApprCheck appLoanApprCheck){
		appLoanApprCheckMapper.insert(appLoanApprCheck);
	}

	/**
	 * 通过主键修改 APP_分期案件复核
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appLoanApprCheckMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_分期案件复核
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appLoanApprCheckMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_分期案件复核 对象
	 * @param primaryKey 主键
	 * @return APP_分期案件复核对象
	 */
	public AppLoanApprCheck getByPrimaryKey(String primaryKey){
		return appLoanApprCheckMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_分期案件复核 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppLoanApprCheck> queryForList(Map<String, Object> param){
		return appLoanApprCheckMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_分期案件复核 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppLoanApprCheckBo> queryForPageTwo(Page<AppLoanApprCheckBo> page){
		appLoanApprCheckMapper.queryForListTwo(page.getPageParams());
		return (Page<AppLoanApprCheckBo>)page.getPageParams().get(Page.KEY);
	}
	
	/**
	 * 查询 APP_分期案件复核 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppLoanApprCheck> queryForPage(Page<AppLoanApprCheck> page){
		appLoanApprCheckMapper.queryForList(page.getPageParams());
		return (Page<AppLoanApprCheck>)page.getPageParams().get(Page.KEY);
	}
	
	/**
     * 根据审批编号查询主管编号
     *
     * @param staffNo 人员编号
     * @return
     */
	public Map<String,String> getManagerNoByStaffNo(String staffNo)
	{
		return appLoanApprCheckMapper.getManagerNoByStaffNo(staffNo);
	}
	
	 /**
     * 根据主管编号查询是否在线(上线、下线)状态
     *
     * @param staffNo 人员编号
     * @return
     */
	public String getGroupTypeByStaffNo(String staffNo)
	{
		return appLoanApprCheckMapper.getGroupTypeByStaffNo(staffNo);
	}
	
	/**
     * 根据主管编号查询当前审批状态
     *
     * @param staffNo 人员编号
     * @return
     */
	public int getCountByManagerNo(String staffNo)
	{
		return appLoanApprCheckMapper.getCountByManagerNo(staffNo);
	}
	
	/**
     * 查询非主管审批中单子数
     * @param staffNo
     * @return
     */
	public int getCountByStaffNo(String staffNo)
	{
		return appLoanApprCheckMapper.getCountByStaffNo(staffNo);
	}
	
	/**
     * 查询非主管审批中单子数
     * @param staffNo
     * @return
     */
	public int getCountByStaffNoTwo(String staffNo)
	{
		return appLoanApprCheckMapper.getCountByStaffNoTwo(staffNo);
	}
	 /**
     * 根据ID删除分期
     * @param apprId
     * @return
     */
	public Integer getCheckCntByApprId(String apprId)
	{
		return appLoanApprCheckMapper.getCheckCntByApprId(apprId);
	}
	
	/**
     * 根据ID查询出复核记录
     * @param apprId
     * @return
     */
	@Transactional
	public int deleteByApprId(String apprId)
	{
		return appLoanApprCheckMapper.deleteByApprId(apprId);
	}
	
	 /**
     * 更新app_loan_acct
     * @param map
     * @return
     */
	@Transactional
	public int updateAppLoanAcctByLoanNo(Map<String,Object> map)
	{
		return appLoanApprCheckMapper.updateAppLoanAcctByLoanNo(map);
	}
}