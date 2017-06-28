package com.hs.loan.approvecheck.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.approvecheck.mapper.AppApprstaffGroupdetalMapper;
import com.hs.loan.approvecheck.entity.AppApprstaffGroupdetal;
import com.hs.base.entity.Page;

/**
 * APP_审批内部组人员信息 业务处理
 * @author autocreate
 * @create 2016-11-17
 */
@Service
@Transactional(readOnly=true)
public class  AppApprstaffGroupdetalService{
	@Autowired
	private AppApprstaffGroupdetalMapper appApprstaffGroupdetalMapper;
	
	/**
	 * 新增 APP_审批内部组人员信息
	 * @param appApprstaffGroupdetal 新增对象
	 */
	@Transactional
	public void insert(AppApprstaffGroupdetal appApprstaffGroupdetal){
		appApprstaffGroupdetalMapper.insert(appApprstaffGroupdetal);
	}

	/**
	 * 批量新增 APP_审批内部组人员信息
	 * @param appApprstaffGroupdetal 新增对象
	 */
	@Transactional
	public void batchInsert(List<AppApprstaffGroupdetal> list){
		appApprstaffGroupdetalMapper.batchInsert(list);
	}
	
	/**
	 * 通过主键修改 APP_审批内部组人员信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appApprstaffGroupdetalMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_审批内部组人员信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appApprstaffGroupdetalMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键批量删除 APP_审批内部组人员信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void batchDeleteByPrimaryKey(List<String> list){
		appApprstaffGroupdetalMapper.batchDeleteByPrimaryKey(list);
	}

	/**
	 * 根据组ID删除审批内部组的人员
	 * @param groupId 审批内部组ID
	 */
	@Transactional
	public void batchDeleteByGroupId(List<String> list){
		appApprstaffGroupdetalMapper.batchDeleteByGroupId(list);
	}
	
	/**
	 * 通过主键取得 APP_审批内部组人员信息 对象
	 * @param primaryKey 主键
	 * @return APP_审批内部组人员信息对象
	 */
	public AppApprstaffGroupdetal getByPrimaryKey(String primaryKey){
		return appApprstaffGroupdetalMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_审批内部组人员信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppApprstaffGroupdetal> queryForList(Map<String, Object> param){
		return appApprstaffGroupdetalMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_审批内部组人员信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppApprstaffGroupdetal> queryForPage(Page<AppApprstaffGroupdetal> page){
		appApprstaffGroupdetalMapper.queryForList(page.getPageParams());
		return (Page<AppApprstaffGroupdetal>)page.getPageParams().get(Page.KEY);
	}
	
	/**
	 * 查询 APP_不在审批内部组人员信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppApprstaffGroupdetal> queryNotApprstaffGroupdetalList(Page<AppApprstaffGroupdetal> page){
		appApprstaffGroupdetalMapper.queryNotApprstaffGroupdetalList(page.getPageParams());
		return (Page<AppApprstaffGroupdetal>)page.getPageParams().get(Page.KEY);
	}

}