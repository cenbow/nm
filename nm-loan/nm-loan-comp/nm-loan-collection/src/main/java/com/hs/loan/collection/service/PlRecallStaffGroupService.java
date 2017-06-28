package com.hs.loan.collection.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.loan.collection.entity.PlRecallStaffGroup;
import com.hs.loan.collection.mapper.PlRecallStaffGroupMapper;

/**
 * PL_催收人员与组关联信息 业务处理
 * @author autocreate
 * @create 2015-12-02
 */
@Service
@Transactional(readOnly=true)
public class  PlRecallStaffGroupService{
	@Autowired
	private PlRecallStaffGroupMapper plRecallStaffGroupMapper;
	
	/**
	 * 新增 PL_催收人员与组关联信息
	 * @param plRecallStaffGroup 新增对象
	 */
	@Transactional
	public void insert(PlRecallStaffGroup plRecallStaffGroup){
		plRecallStaffGroupMapper.insert(plRecallStaffGroup);
	}

	/**
	 * 通过主键修改 PL_催收人员与组关联信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		plRecallStaffGroupMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PL_催收人员与组关联信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		plRecallStaffGroupMapper.deleteByPrimaryKey(primaryKey);
	}
	
	/**
	 * 通过主键删除 PL_催收人员与组关联信息
	 * @param
	 */
	@Transactional
	public void deleteByCont(String staffNo,String groupNo){
		Map<String,String> param = new HashMap<String,String>();
		param.put("staffNo", staffNo);
		param.put("groupNo", groupNo);
		plRecallStaffGroupMapper.deleteByCont(param);
	}

	

	/**
	 * 通过主键取得 PL_催收人员与组关联信息 对象
	 * @param primaryKey 主键
	 * @return PL_催收人员与组关联信息对象
	 */
	public PlRecallStaffGroup getByPrimaryKey(String primaryKey){
		return plRecallStaffGroupMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PL_催收人员与组关联信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PlRecallStaffGroup> queryForList(Map<String, Object> param){
		return plRecallStaffGroupMapper.queryForList(param);
	}
	
	/**
	 * 查询 PL_催收人员与组关联信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */

	public Page<PlRecallStaffGroup> queryForPage(Page<PlRecallStaffGroup> page){
		plRecallStaffGroupMapper.queryForList(page.getPageParams());
		return (Page<PlRecallStaffGroup>)page.getPageParams().get(Page.KEY);
	}
	/**
	 * 查询 PL_催收人员与组关联信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PlRecallStaffGroup> queryForPermissionPage(Page<PlRecallStaffGroup> page,UserProfile profile){
		return this.queryForPage(page);
	}

	/**
	 * 根据催收组编号查询催收组下所有催收人员信息
	 * @param groupNo 催收组编号
	 * @return PL_催收人员与组关联信息 对象 list
	 */
	public List<PlRecallStaffGroup> queryListBygroupNo(String groupNo){
		HashMap<String,Object> map=new HashMap<>();
		//催收组编号
		map.put("groupNo",groupNo);
		return plRecallStaffGroupMapper.queryForList(map);
	}
}