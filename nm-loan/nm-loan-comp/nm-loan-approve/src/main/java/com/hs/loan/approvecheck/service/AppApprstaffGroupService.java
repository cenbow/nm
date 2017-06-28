package com.hs.loan.approvecheck.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.approvecheck.mapper.AppApprstaffGroupMapper;
import com.hs.loan.approvecheck.entity.AppApprstaffGroup;
import com.hs.base.entity.Page;

/**
 * APP_审批内部组信息 业务处理
 * @author autocreate
 * @create 2016-11-17
 */
@Service
@Transactional(readOnly=true)
public class  AppApprstaffGroupService{
	@Autowired
	private AppApprstaffGroupMapper appApprstaffGroupMapper;
	
	/**
	 * 新增 APP_审批内部组信息
	 * @param appApprstaffGroup 新增对象
	 */
	@Transactional
	public void insert(AppApprstaffGroup appApprstaffGroup){
		appApprstaffGroupMapper.insert(appApprstaffGroup);
	}

	/**
	 * 通过主键修改 APP_审批内部组信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appApprstaffGroupMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_审批内部组信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appApprstaffGroupMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过批量删除 APP_审批内部组信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void batchDeleteByPrimaryKey(List<String> list){
		appApprstaffGroupMapper.batchDeleteByPrimaryKey(list);
	}

	/**
	 * 通过主键取得 APP_审批内部组信息 对象
	 * @param primaryKey 主键
	 * @return APP_审批内部组信息对象
	 */
	public AppApprstaffGroup getByPrimaryKey(String primaryKey){
		return appApprstaffGroupMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_审批内部组信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppApprstaffGroup> queryForList(Map<String, Object> param){
		return appApprstaffGroupMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_审批内部组信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppApprstaffGroup> queryForPage(Page<AppApprstaffGroup> page){
		appApprstaffGroupMapper.queryForList(page.getPageParams());
		return (Page<AppApprstaffGroup>)page.getPageParams().get(Page.KEY);
	}
}