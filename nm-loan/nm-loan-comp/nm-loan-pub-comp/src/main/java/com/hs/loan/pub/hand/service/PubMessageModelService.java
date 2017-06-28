package com.hs.loan.pub.hand.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.pub.hand.mapper.PubMessageModelMapper;
import com.hs.loan.pub.hand.entity.PubMessageModel;
import com.hs.base.entity.Page;

/**
 * PUB_消息模版 业务处理
 * @author autocreate
 * @create 2016-04-25
 */
@Service
@Transactional(readOnly=true)
public class  PubMessageModelService{
	@Autowired
	private PubMessageModelMapper pubMessageModelMapper;
	
	/**
	 * 新增 PUB_消息模版
	 * @param pubMessageModel 新增对象
	 */
	@Transactional
	public void insert(PubMessageModel pubMessageModel){
		pubMessageModelMapper.insert(pubMessageModel);
	}

	/**
	 * 通过主键修改 PUB_消息模版
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		pubMessageModelMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PUB_消息模版
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		pubMessageModelMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 PUB_消息模版 对象
	 * @param primaryKey 主键
	 * @return PUB_消息模版对象
	 */
	public PubMessageModel getByPrimaryKey(String primaryKey){
		return pubMessageModelMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PUB_消息模版 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PubMessageModel> queryForList(Map<String, Object> param){
		return pubMessageModelMapper.queryForList(param);
	}
	
	/**
	 * 查询 PUB_消息模版 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PubMessageModel> queryForPage(Page<PubMessageModel> page){
		pubMessageModelMapper.queryForList(page.getPageParams());
		return (Page<PubMessageModel>)page.getPageParams().get(Page.KEY);
	}
}