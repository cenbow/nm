package com.hs.system.cardbin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.system.entity.PubCardBin;
import com.hs.system.mapper.PubCardBinMapper;

/**
 * 银行卡卡BIN 业务处理
 * @author autocreate
 * @create 2015-11-06
 */
@Service
@Transactional(readOnly=true)
public class  PubCardBinService{
	@Autowired
	private PubCardBinMapper pubCardBinMapper;
	
	/**
	 * 新增 银行卡卡BIN
	 * @param pubCardBin 新增对象
	 */
	@Transactional
	public void insert(PubCardBin pubCardBin){
		pubCardBinMapper.insert(pubCardBin);
	}

	/**
	 * 通过主键修改 银行卡卡BIN
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		pubCardBinMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 银行卡卡BIN
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		pubCardBinMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 银行卡卡BIN 对象
	 * @param primaryKey 主键
	 * @return 银行卡卡BIN对象
	 */
	public PubCardBin getByPrimaryKey(String primaryKey){
		return pubCardBinMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 银行卡卡BIN 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PubCardBin> queryForList(Map<String, Object> param){
		return pubCardBinMapper.queryForList(param);
	}
	
	/**
	 * 查询 银行卡卡BIN 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PubCardBin> queryForPage(Page<PubCardBin> page){
		pubCardBinMapper.queryForList(page.getPageParams());
		return (Page<PubCardBin>)page.getPageParams().get(Page.KEY);
	}
}