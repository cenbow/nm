package com.hs.loan.prod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.prod.entity.PubProdArea;
import com.hs.loan.prod.mapper.PubProdAreaMapper;

/**
 *  业务处理
 * @author autocreate
 * @create 2015-10-15
 */
@Service
@Transactional(readOnly=true)
public class  PubProdAreaService{
	@Autowired
	private PubProdAreaMapper pubProdAreaMapper;
	
	/**
	 * 新增 
	 * @param pubProdArea
	 * @return
	 */
	@Transactional
	public void insert(PubProdArea pubProdArea){
		pubProdAreaMapper.insert(pubProdArea);
	}

	/**
	 * 通过主键修改 
	 * @param map
	 * @return
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		pubProdAreaMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 
	 * @param primaryKey
	 * @return
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		pubProdAreaMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得  对象
	 * @param primaryKey
	 * @return
	 */
	public PubProdArea getByPrimaryKey(String primaryKey){
		return pubProdAreaMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询  列表
	 * @param param
	 * @return List<T>
	 */
	public List<PubProdArea> queryForList(Map<String, Object> param){
		return pubProdAreaMapper.queryForList(param);
	}
	
	/**
	 * 查询  分页列表
	 * @param page
	 * @return List<T>
	 */
	public Page<PubProdArea> queryForPage(Page<PubProdArea> page){
		pubProdAreaMapper.queryForList(page.getPageParams());
		return (Page<PubProdArea>)page.getPageParams().get(Page.KEY);
	}
	/**
	 * 保存产品区域信息
	 * @param page
	 * @return List<T>
	 */
	@Transactional
	public void savePubProdArea(PubProdArea pubProdArea){
		Map<String,Object> param = new HashMap<>();
		param.put("prodNo",	pubProdArea.getProdNo());		
		param.put("provNo",pubProdArea.getProvNo());
		param.put("cityNo",pubProdArea.getCityNo());
		param.put("cntyNo",pubProdArea.getCntyNo());
		this.deleteProdAreaByBusi(param);
		this.insert(pubProdArea);
	}
	
	/**
	 * 通过主键删除 
	 * @param primaryKey
	 * @return
	 */
	@Transactional
	public void deleteProdAreaByBusi(Map<String,Object> map){
		pubProdAreaMapper.deleteProdAreaByBusi(map);
	}

}