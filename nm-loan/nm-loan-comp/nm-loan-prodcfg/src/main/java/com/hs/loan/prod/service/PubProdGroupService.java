package com.hs.loan.prod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.prod.bo.ProdGroupBO;
import com.hs.loan.prod.entity.PubProdFee;
import com.hs.loan.prod.entity.PubProdGroup;
import com.hs.loan.prod.mapper.PubProdGroupMapper;

/**
 * PUB_产品与销售群关系 业务处理
 * @author autocreate
 * @create 2015-10-21
 */
@Service
@Transactional(readOnly=true)
public class  PubProdGroupService{
	@Autowired
	private PubProdGroupMapper pubProdGroupMapper;
	
	/**
	 * 新增 PUB_产品与销售群关系
	 * @param pubProdGroup 新增对象
	 */
	@Transactional
	public void insert(PubProdGroup pubProdGroup){
		pubProdGroupMapper.insert(pubProdGroup);
	}

	/**
	 * 通过主键修改 PUB_产品与销售群关系
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		pubProdGroupMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PUB_产品与销售群关系
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		pubProdGroupMapper.deleteByPrimaryKey(primaryKey);
	}
	/**
	 * 通过销售组查询 PUB_产品与费用项关系
	 * 
	 * @param primaryKey
	 * @return 返回true可以删除 销售组 false则不可以删除 说明销售组和已有产品有关系
	 */
	public boolean queryExistRelaProdAndteam(String teamNo){
		Map<String, Object> param = new HashMap<>();
		param.put("crowdNo", teamNo);
		List<PubProdGroup> list= this.queryForList(param);
		if(list != null && list.size() > 0){
			return false;
		}
		return true;
	}

	/**
	 * 通过主键取得 PUB_产品与销售群关系 对象
	 * @param primaryKey 主键
	 * @return PUB_产品与销售群关系对象
	 */
	public PubProdGroup getByPrimaryKey(String primaryKey){
		return pubProdGroupMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PUB_产品与销售群关系 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PubProdGroup> queryForList(Map<String, Object> param){
		return pubProdGroupMapper.queryForList(param);
	}
	
	/**
	 * 查询 PUB_产品与销售群关系 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PubProdGroup> queryForPage(Page<PubProdGroup> page){
		pubProdGroupMapper.queryForList(page.getPageParams());
		return (Page<PubProdGroup>)page.getPageParams().get(Page.KEY);
	}
	/**
	 * 查询 产品和组群的关系
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<ProdGroupBO> queryForPageGroupTeam(Page<ProdGroupBO> page){
		List<ProdGroupBO> list= pubProdGroupMapper.queryForPageGroupCrowd(page.getPageParams());
		return (Page<ProdGroupBO>)page.getPageParams().get(Page.KEY);
	}
}