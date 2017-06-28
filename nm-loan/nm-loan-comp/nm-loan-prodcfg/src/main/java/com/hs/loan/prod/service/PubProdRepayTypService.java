package com.hs.loan.prod.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.prod.entity.PubProdRepayTyp;
import com.hs.loan.prod.mapper.PubProdRepayTypMapper;

/**
 * PUB_产品与还款类型的关系 业务处理
 * @author autocreate
 * @create 2015-10-16
 */
@Service
@Transactional(readOnly=true)
public class  PubProdRepayTypService{
	@Autowired
	private PubProdRepayTypMapper pubProdRepayTypMapper;
	
	/**
	 * 新增 PUB_产品与还款类型的关系
	 * @param pubProdRepayTyp 新增对象
	 */
	@Transactional
	public void insert(PubProdRepayTyp pubProdRepayTyp){
		pubProdRepayTypMapper.insert(pubProdRepayTyp);
	}

	/**
	 * 通过主键修改 PUB_产品与还款类型的关系
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		pubProdRepayTypMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PUB_产品与还款类型的关系
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		pubProdRepayTypMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 PUB_产品与还款类型的关系 对象
	 * @param primaryKey 主键
	 * @return PUB_产品与还款类型的关系对象
	 */
	public PubProdRepayTyp getByPrimaryKey(String primaryKey){
		return pubProdRepayTypMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PUB_产品与还款类型的关系 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PubProdRepayTyp> queryForList(Map<String, Object> param){
		return pubProdRepayTypMapper.queryForList(param);
	}
	
	/**
	 * 查询 PUB_产品与还款类型的关系 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PubProdRepayTyp> queryForPage(Page<PubProdRepayTyp> page){
		pubProdRepayTypMapper.queryForList(page.getPageParams());
		return (Page<PubProdRepayTyp>)page.getPageParams().get(Page.KEY);
	}

	/**
	 * 通过主键删除 PUB_产品与还款类型的关系
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByProdNo(String prodNo) {
		pubProdRepayTypMapper.deleteByProdNo(prodNo);
		
	}
}