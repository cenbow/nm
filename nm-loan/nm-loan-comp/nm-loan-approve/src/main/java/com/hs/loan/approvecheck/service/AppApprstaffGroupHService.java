package com.hs.loan.approvecheck.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.approvecheck.mapper.AppApprstaffGroupHMapper;
import com.hs.loan.approvecheck.entity.AppApprstaffGroupH;
import com.hs.base.entity.Page;

/**
 * APP_审批内部组信息历史 业务处理
 * @author autocreate
 * @create 2016-11-17
 */
@Service
@Transactional(readOnly=true)
public class  AppApprstaffGroupHService{
	@Autowired
	private AppApprstaffGroupHMapper appApprstaffGroupHMapper;
	
	/**
	 * 新增 APP_审批内部组信息历史
	 * @param appApprstaffGroupH 新增对象
	 */
	@Transactional
	public void insert(AppApprstaffGroupH appApprstaffGroupH){
		appApprstaffGroupHMapper.insert(appApprstaffGroupH);
	}

	/**
	 * 批量新增 APP_审批内部组信息历史
	 * @param appApprstaffGroupH 新增对象
	 */
	@Transactional
	public void batchInsert(List<AppApprstaffGroupH> list)
	{
		appApprstaffGroupHMapper.batchInsert(list);
	}
	
	/**
	 * 批量更新 APP_审批内部组信息历史
	 * @param appApprstaffGroupH 新增对象
	 */
	@Transactional
	public void batchModifyByPrimaryKeySelective(Map<String, Object> params)
	{
		appApprstaffGroupHMapper.batchModifyByPrimaryKeySelective(params);
	}
	
	/**
	 * 通过主键修改 APP_审批内部组信息历史
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appApprstaffGroupHMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_审批内部组信息历史
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appApprstaffGroupHMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_审批内部组信息历史 对象
	 * @param primaryKey 主键
	 * @return APP_审批内部组信息历史对象
	 */
	public AppApprstaffGroupH getByPrimaryKey(String primaryKey){
		return appApprstaffGroupHMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_审批内部组信息历史 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppApprstaffGroupH> queryForList(Map<String, Object> param){
		return appApprstaffGroupHMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_审批内部组信息历史 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppApprstaffGroupH> queryForPage(Page<AppApprstaffGroupH> page){
		appApprstaffGroupHMapper.queryForList(page.getPageParams());
		return (Page<AppApprstaffGroupH>)page.getPageParams().get(Page.KEY);
	}
}