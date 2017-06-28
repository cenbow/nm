package com.hs.loan.finance.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.finance.mapper.AccInnAcctMapper;
import com.hs.loan.finance.entity.AccInnAcct;
import com.hs.base.entity.Page;

/**
 * ACC_内部账户 业务处理
 * @author autocreate
 * @create 2016-02-03
 */
@Service
@Transactional(readOnly=true)
public class  AccInnAcctService{
	@Autowired
	private AccInnAcctMapper accInnAcctMapper;
	
	/**
	 * 新增 ACC_内部账户
	 * @param accInnAcct 新增对象
	 */
	@Transactional
	public void insert(AccInnAcct accInnAcct){
		accInnAcctMapper.insert(accInnAcct);
	}

	/**
	 * 通过主键修改 ACC_内部账户
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		accInnAcctMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 ACC_内部账户
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		accInnAcctMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 ACC_内部账户 对象
	 * @param primaryKey 主键
	 * @return ACC_内部账户对象
	 */
	public AccInnAcct getByPrimaryKey(String primaryKey){
		return accInnAcctMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 ACC_内部账户 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AccInnAcct> queryForList(Map<String, Object> param){
		return accInnAcctMapper.queryForList(param);
	}
	
	/**
	 * 查询 ACC_内部账户 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AccInnAcct> queryForPage(Page<AccInnAcct> page){
		accInnAcctMapper.queryForList(page.getPageParams());
		return (Page<AccInnAcct>)page.getPageParams().get(Page.KEY);
	}
}