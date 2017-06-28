package com.hs.loan.approvecheck.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.approvecheck.bo.AppLoanApprCheckHBo;
import com.hs.loan.approvecheck.entity.AppLoanApprCheckH;
import com.hs.loan.approvecheck.mapper.AppLoanApprCheckHMapper;

/**
 * APP_分期案件复核历史 业务处理
 * @author autocreate
 * @create 2016-11-24
 */
@Service
@Transactional(readOnly=true)
public class  AppLoanApprCheckHService{
	@Autowired
	private AppLoanApprCheckHMapper appLoanApprCheckHMapper;
	
	/**
	 * 新增 APP_分期案件复核历史
	 * @param appLoanApprCheckH 新增对象
	 */
	@Transactional
	public void insert(AppLoanApprCheckH appLoanApprCheckH){
		appLoanApprCheckHMapper.insert(appLoanApprCheckH);
	}

	/**
	 * 通过主键修改 APP_分期案件复核历史
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appLoanApprCheckHMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_分期案件复核历史
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appLoanApprCheckHMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_分期案件复核历史 对象
	 * @param primaryKey 主键
	 * @return APP_分期案件复核历史对象
	 */
	public AppLoanApprCheckH getByPrimaryKey(String primaryKey){
		return appLoanApprCheckHMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_分期案件复核历史 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppLoanApprCheckH> queryForList(Map<String, Object> param){
		return appLoanApprCheckHMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_分期案件复核历史 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public Page<AppLoanApprCheckHBo> queryForPageTwo(Page<AppLoanApprCheckHBo> page){
		appLoanApprCheckHMapper.queryForListTwo(page.getPageParams());
		return (Page<AppLoanApprCheckHBo>)page.getPageParams().get(Page.KEY);
	}
	
	/**
	 * 查询 APP_分期案件复核历史 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppLoanApprCheckH> queryForPage(Page<AppLoanApprCheckH> page){
		appLoanApprCheckHMapper.queryForList(page.getPageParams());
		return (Page<AppLoanApprCheckH>)page.getPageParams().get(Page.KEY);
	}
}