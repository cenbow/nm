package com.hs.loan.market.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.market.entity.PubBranchGroup;
import com.hs.loan.market.mapper.PubBranchGroupMapper;

/**
 * PUB_网点分组关系 业务处理
 * @author autocreate
 * @create 2015-10-15
 */
@Service
@Transactional(readOnly=true)
public class  PubBranchGroupService {
	@Autowired
	private PubBranchGroupMapper pubBranchGroupMapper;
	
	/**
	 * 新增 PUB_网点分组关系
	 * @param pubBranchGroup 新增对象
	 */
	@Transactional
	public void insert(PubBranchGroup pubBranchGroup){
		pubBranchGroupMapper.insert(pubBranchGroup);
	}

	/**
	 * 通过主键修改 PUB_网点分组关系
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		pubBranchGroupMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PUB_网点分组关系
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		pubBranchGroupMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 PUB_网点分组关系 对象
	 * @param primaryKey 主键
	 * @return PUB_网点分组关系对象
	 */
	public PubBranchGroup getByPrimaryKey(String primaryKey){
		return pubBranchGroupMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PUB_网点分组关系 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PubBranchGroup> queryForList(Map<String, Object> param){
		return pubBranchGroupMapper.queryForList(param);
	}
	
	/**
	 * 查询 PUB_网点分组关系 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PubBranchGroup> queryForPage(Page<PubBranchGroup> page){
		pubBranchGroupMapper.queryForList(page.getPageParams());
		return (Page<PubBranchGroup>)page.getPageParams().get(Page.KEY);
	}
	
	
}