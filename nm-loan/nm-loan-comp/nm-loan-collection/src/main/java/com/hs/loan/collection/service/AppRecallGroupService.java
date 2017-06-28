package com.hs.loan.collection.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.loan.collection.entity.AppRecallGroup;
import com.hs.loan.collection.mapper.AppRecallGroupMapper;

/**
 * PL_催收组信息 业务处理
 * @author autocreate
 * @create 2015-12-02
 */
@Service
@Transactional(readOnly=true)
public class  AppRecallGroupService{
	@Autowired
	private AppRecallGroupMapper appRecallGroupMapper;
	/**
	 * 根据催收组名称查询该催收组是否已经存在
	 * @param groupName 催收组名称
	 * @return Integer
	 */
	public Integer selectExistsByName(String groupName){
		return appRecallGroupMapper.selectExistsByName(groupName);
	}
	/**
	 * 新增 PL_催收组信息
	 * @param appRecallGroup 新增对象
	 */
	@Transactional
	public void insert(AppRecallGroup appRecallGroup){
		appRecallGroupMapper.insert(appRecallGroup);
	}

	/**
	 * 通过主键修改 PL_催收组信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appRecallGroupMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PL_催收组信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appRecallGroupMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 PL_催收组信息 对象
	 * @param primaryKey 主键
	 * @return PL_催收组信息对象
	 */
	public AppRecallGroup getByPrimaryKey(String primaryKey){
		return appRecallGroupMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PL_催收组信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppRecallGroup> queryForList(Map<String, Object> param){
		return appRecallGroupMapper.queryForList(param);
	}
	
	/**
	 * 查询 PL_催收组信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppRecallGroup> queryForPage(Page<AppRecallGroup> page){
		appRecallGroupMapper.queryForList(page.getPageParams());
		return (Page<AppRecallGroup>)page.getPageParams().get(Page.KEY);
	}
	
	/**
	 * 查询 PL_催收组信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppRecallGroup> queryForPermissionPage(Page<AppRecallGroup> page,UserProfile profile){
		return this.queryForPage(page);
	}
}