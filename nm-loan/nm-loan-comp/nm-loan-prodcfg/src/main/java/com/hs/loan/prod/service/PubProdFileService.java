package com.hs.loan.prod.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.prod.entity.PubProdFile;
import com.hs.loan.prod.mapper.PubProdFileMapper;

/**
 *  业务处理
 * @author autocreate
 * @create 2015-10-15
 */
@Service
@Transactional(readOnly=true)
public class  PubProdFileService{
	@Autowired
	private PubProdFileMapper pubProdFileMapper;
	
	/**
	 * 新增 
	 * @param pubProdFile
	 * @return
	 */
	@Transactional
	public void insert(PubProdFile pubProdFile){
		pubProdFileMapper.insert(pubProdFile);
	}

	/**
	 * 通过主键修改 
	 * @param map
	 * @return
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		pubProdFileMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 
	 * @param primaryKey
	 * @return
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		pubProdFileMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得  对象
	 * @param primaryKey
	 * @return
	 */
	public PubProdFile getByPrimaryKey(String primaryKey){
		return pubProdFileMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询  列表
	 * @param param
	 * @return List<T>
	 */
	public List<PubProdFile> queryForList(Map<String, Object> param){
		return pubProdFileMapper.queryForList(param);
	}
	
	/**
	 * 查询  分页列表
	 * @param page
	 * @return List<T>
	 */
	public Page<PubProdFile> queryForPage(Page<PubProdFile> page){
		pubProdFileMapper.queryForList(page.getPageParams());
		return (Page<PubProdFile>)page.getPageParams().get(Page.KEY);
	}
}