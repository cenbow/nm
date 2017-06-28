package com.hs.loan.prod.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.prod.entity.PubProdOrg;
import com.hs.loan.prod.mapper.PubProdOrgMapper;

/**
 *  业务处理
 * @author autocreate
 * @create 2015-10-15
 */
@Service
@Transactional(readOnly=true)
public class  PubProdOrgService{
	@Autowired
	private PubProdOrgMapper pubProdOrgMapper;
	
	/**
	 * 新增 
	 * @param pubProdOrg
	 * @return
	 */
	@Transactional
	public void insert(PubProdOrg pubProdOrg){
		pubProdOrgMapper.insert(pubProdOrg);
	}

	/**
	 * 通过主键修改 
	 * @param map
	 * @return
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		pubProdOrgMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 
	 * @param primaryKey
	 * @return
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		pubProdOrgMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得  对象
	 * @param primaryKey
	 * @return
	 */
	public PubProdOrg getByPrimaryKey(String primaryKey){
		return pubProdOrgMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询  列表
	 * @param param
	 * @return List<T>
	 */
	public List<PubProdOrg> queryForList(Map<String, Object> param){
		return pubProdOrgMapper.queryForList(param);
	}
	
	/**
	 * 查询  分页列表
	 * @param page
	 * @return List<T>
	 */
	public Page<PubProdOrg> queryForPage(Page<PubProdOrg> page){
		pubProdOrgMapper.queryForList(page.getPageParams());
		return (Page<PubProdOrg>)page.getPageParams().get(Page.KEY);
	}

	public void deleteByProdNo(String prodNo) {
		 
		pubProdOrgMapper.deletePubProdOrgByprodNo(prodNo);
	}

}