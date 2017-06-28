package com.hs.loan.market.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.market.entity.PubSalerBranch;
import com.hs.loan.market.mapper.PubSalerBranchMapper;

/**
 * PUB_销售网点关联信息 业务处理
 * @author autocreate
 * @create 2015-10-15
 */
@Service
@Transactional(readOnly=true)
public class  PubSalerBranchService {
	@Autowired
	private PubSalerBranchMapper pubSalerBranchMapper;
	
	/**
	 * 新增 PUB_销售网点关联信息
	 * @param pubSalerBranch 新增对象
	 */
	@Transactional
	public void insert(PubSalerBranch pubSalerBranch){
		pubSalerBranchMapper.insert(pubSalerBranch);
	}

	/**
	 * 通过主键修改 PUB_销售网点关联信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		pubSalerBranchMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PUB_销售网点关联信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		pubSalerBranchMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 PUB_销售网点关联信息 对象
	 * @param primaryKey 主键
	 * @return PUB_销售网点关联信息对象
	 */
	public PubSalerBranch getByPrimaryKey(String primaryKey){
		return pubSalerBranchMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PUB_销售网点关联信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PubSalerBranch> queryForList(Map<String, Object> param){
		return pubSalerBranchMapper.queryForList(param);
	}
	
	/**
	 * 查询 PUB_销售网点关联信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PubSalerBranch> queryForPage(Page<PubSalerBranch> page){
		pubSalerBranchMapper.queryForList(page.getPageParams());
		return (Page<PubSalerBranch>)page.getPageParams().get(Page.KEY);
	}
}