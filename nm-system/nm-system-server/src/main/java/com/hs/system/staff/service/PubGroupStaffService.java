package com.hs.system.staff.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.system.api.PubGroupStaffApi;
import com.hs.system.entity.PubGroupStaff;
import com.hs.system.staff.mapper.PubGroupStaffMapper;

/**
 * PUB_销售组与用户关系 业务处理
 * @author autocreate
 * @create 2015-10-15
 */
@Service
@Transactional(readOnly=true)
public class  PubGroupStaffService implements PubGroupStaffApi{
	@Autowired
	private PubGroupStaffMapper pubGroupStaffMapper;
	
	/**
	 * 新增 PUB_销售组与用户关系
	 * @param pubGroupStaff 新增对象
	 */
	@Transactional
	public void insert(PubGroupStaff pubGroupStaff){
		pubGroupStaffMapper.insert(pubGroupStaff);
	}

	/**
	 * 通过主键修改 PUB_销售组与用户关系
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		pubGroupStaffMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PUB_销售组与用户关系
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		pubGroupStaffMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 PUB_销售组与用户关系 对象
	 * @param primaryKey 主键
	 * @return PUB_销售组与用户关系对象
	 */
	public PubGroupStaff getByPrimaryKey(String primaryKey){
		return pubGroupStaffMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PUB_销售组与用户关系 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PubGroupStaff> queryForList(Map<String, Object> param){
		return pubGroupStaffMapper.queryForList(param);
	}
	
	/**
	 * 查询 PUB_销售组与用户关系 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PubGroupStaff> queryForPage(Page<PubGroupStaff> page){
		pubGroupStaffMapper.queryForList(page.getPageParams());
		return (Page<PubGroupStaff>)page.getPageParams().get(Page.KEY);
	}
}