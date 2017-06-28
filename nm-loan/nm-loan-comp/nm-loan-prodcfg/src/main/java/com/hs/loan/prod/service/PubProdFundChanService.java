package com.hs.loan.prod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.prod.entity.PubProdFee;
import com.hs.loan.prod.entity.PubProdFundChan;
import com.hs.loan.prod.mapper.PubProdFundChanMapper;

/**
 *  业务处理
 * @author autocreate
 * @create 2015-10-15
 */
@Service
@Transactional(readOnly=true)
public class  PubProdFundChanService{
	@Autowired
	private PubProdFundChanMapper pubProdFundChanMapper;
	
	/**
	 * 新增 
	 * @param pubProdFundChan
	 * @return
	 */
	@Transactional
	public void insert(PubProdFundChan pubProdFundChan){
		pubProdFundChanMapper.insert(pubProdFundChan);
	}

	/**
	 * 通过主键修改 
	 * @param map
	 * @return
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		pubProdFundChanMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 
	 * @param primaryKey
	 * @return
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		pubProdFundChanMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得  对象
	 * @param primaryKey
	 * @return
	 */
	public PubProdFundChan getByPrimaryKey(String primaryKey){
		return pubProdFundChanMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询  列表
	 * @param param
	 * @return List<T>
	 */
	public List<PubProdFundChan> queryForList(Map<String, Object> param){
		return pubProdFundChanMapper.queryForList(param);
	}
	
	/**
	 * 查询  分页列表
	 * @param page
	 * @return List<T>
	 */
	public Page<PubProdFundChan> queryForPage(Page<PubProdFundChan> page){
		pubProdFundChanMapper.queryForList(page.getPageParams());
		return (Page<PubProdFundChan>)page.getPageParams().get(Page.KEY);
	}


	/**
	 * 通过产品编号删除 PUB_产品与资金渠道关系
	 * @param primaryKey
	 * @return
	 */
	@Transactional
	public void deleteByProdNo(String prodNo){
		pubProdFundChanMapper.deletePubPrdoFunChanByprodNo(prodNo);
	}
	/**
	 * 通过资金渠道号查询 PUB_产品与资金渠道关系
	 * @param primaryKey
	 * @return返回true可以删除 资金渠道 false则不可以删除 说明渠道项和已有产品有关系
	 */
	public boolean queryExistRelaProdAndChan(String chanNo){
		Map<String, Object> param = new HashMap<>();
		param.put("chanNo", chanNo);
		List<PubProdFundChan> list= this.queryForList(param);
		if(list != null && list.size() > 0){
			return false;
		}
		return true;
	}
}