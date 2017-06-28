package com.hs.loan.finance.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.finance.mapper.AccMchtAcctMapper;
import com.hs.loan.finance.entity.AccMchtAcct;
import com.hs.base.entity.Page;

/**
 * ACC_商家账户 业务处理
 * @author autocreate
 * @create 2016-02-03
 */
@Service
@Transactional(readOnly=true)
public class  AccMchtAcctService{
	@Autowired
	private AccMchtAcctMapper accMchtAcctMapper;
	
	/**
	 * 新增 ACC_商家账户
	 * @param accMchtAcct 新增对象
	 */
	@Transactional
	public void insert(AccMchtAcct accMchtAcct){
		accMchtAcctMapper.insert(accMchtAcct);
	}

	/**
	 * 通过主键修改 ACC_商家账户
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		accMchtAcctMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 ACC_商家账户
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		accMchtAcctMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 ACC_商家账户 对象
	 * @param primaryKey 主键
	 * @return ACC_商家账户对象
	 */
	public AccMchtAcct getByPrimaryKey(String primaryKey){
		return accMchtAcctMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 ACC_商家账户 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AccMchtAcct> queryForList(Map<String, Object> param){
		return accMchtAcctMapper.queryForList(param);
	}
	
	/**
	 * 查询 ACC_商家账户 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AccMchtAcct> queryForPage(Page<AccMchtAcct> page){
		accMchtAcctMapper.queryForList(page.getPageParams());
		return (Page<AccMchtAcct>)page.getPageParams().get(Page.KEY);
	}
}