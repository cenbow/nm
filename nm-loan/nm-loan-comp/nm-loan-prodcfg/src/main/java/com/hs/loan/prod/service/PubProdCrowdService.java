package com.hs.loan.prod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.prod.entity.PubProdFee;
import com.hs.loan.prod.entity.PubProdCrowd;
import com.hs.loan.prod.mapper.PubProdCrowdMapper;

/**
 * PUB_产品与销售组关系 业务处理
 * @author autocreate
 * @create 2015-10-21
 */
@Service
@Transactional(readOnly=true)
public class  PubProdCrowdService{
	@Autowired
	private PubProdCrowdMapper pubProdCrwodMapper;
	
	/**
	 * 新增 PUB_产品与销售组关系
	 * @param pubProdTeam 新增对象
	 */
	@Transactional
	public void insert(PubProdCrowd pubProdTeam){
		pubProdCrwodMapper.insert(pubProdTeam);
	}

	/**
	 * 通过主键修改 PUB_产品与销售组关系
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		pubProdCrwodMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PUB_产品与销售组关系
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		pubProdCrwodMapper.deleteByPrimaryKey(primaryKey);
	}
	
	/**
	 * 通过群编号查询 PUB_产品与费用项关系
	 * @param primaryKey
	 * @return 返回true可以删除 群 false则不可以删除 说明群和已有产品有关系
	 */
	public boolean queryExistRelaProdAndgroupNo(String groupNo){
		Map<String, Object> param = new HashMap<>();
		param.put("groupNo", groupNo);
		List<PubProdCrowd> list= this.queryForList(param);
		if(list != null && list.size() > 0){
			return false;
		}
		return true;
	}

	/**
	 * 通过主键取得 PUB_产品与销售组关系 对象
	 * @param primaryKey 主键
	 * @return PUB_产品与销售组关系对象
	 */
	public PubProdCrowd getByPrimaryKey(String primaryKey){
		return pubProdCrwodMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PUB_产品与销售组关系 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PubProdCrowd> queryForList(Map<String, Object> param){
		return pubProdCrwodMapper.queryForList(param);
	}
	
	/**
	 * 查询 PUB_产品与销售组关系 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PubProdCrowd> queryForPage(Page<PubProdCrowd> page){
		pubProdCrwodMapper.queryForList(page.getPageParams());
		return (Page<PubProdCrowd>)page.getPageParams().get(Page.KEY);
	}
}