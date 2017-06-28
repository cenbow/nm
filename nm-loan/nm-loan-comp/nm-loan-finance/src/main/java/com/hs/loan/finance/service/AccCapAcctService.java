package com.hs.loan.finance.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.finance.mapper.AccCapAcctMapper;
import com.hs.loan.finance.entity.AccCapAcct;
import com.hs.base.entity.Page;

/**
 * ACC_资方账户 业务处理
 * @author autocreate
 * @create 2016-02-03
 */
@Service
@Transactional(readOnly=true)
public class  AccCapAcctService{
	@Autowired
	private AccCapAcctMapper accCapAcctMapper;
	
	/**
	 * 新增 ACC_资方账户
	 * @param accCapAcct 新增对象
	 */
	@Transactional
	public void insert(AccCapAcct accCapAcct){
		accCapAcctMapper.insert(accCapAcct);
	}

	/**
	 * 通过主键修改 ACC_资方账户
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		accCapAcctMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 ACC_资方账户
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		accCapAcctMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 ACC_资方账户 对象
	 * @param primaryKey 主键
	 * @return ACC_资方账户对象
	 */
	public AccCapAcct getByPrimaryKey(String primaryKey){
		return accCapAcctMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 ACC_资方账户 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AccCapAcct> queryForList(Map<String, Object> param){
		return accCapAcctMapper.queryForList(param);
	}
	
	/**
	 * 查询 ACC_资方账户 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AccCapAcct> queryForPage(Page<AccCapAcct> page){
		accCapAcctMapper.queryForList(page.getPageParams());
		return (Page<AccCapAcct>)page.getPageParams().get(Page.KEY);
	}
}