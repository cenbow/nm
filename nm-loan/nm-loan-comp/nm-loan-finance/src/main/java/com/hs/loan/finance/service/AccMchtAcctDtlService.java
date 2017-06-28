package com.hs.loan.finance.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.finance.mapper.AccMchtAcctDtlMapper;
import com.hs.loan.finance.entity.AccMchtAcctDtl;
import com.hs.base.entity.Page;

/**
 * ACC_商家账户明细 业务处理
 * @author autocreate
 * @create 2016-02-03
 */
@Service
@Transactional(readOnly=true)
public class  AccMchtAcctDtlService{
	@Autowired
	private AccMchtAcctDtlMapper accMchtAcctDtlMapper;
	
	/**
	 * 新增 ACC_商家账户明细
	 * @param accMchtAcctDtl 新增对象
	 */
	@Transactional
	public void insert(AccMchtAcctDtl accMchtAcctDtl){
		accMchtAcctDtlMapper.insert(accMchtAcctDtl);
	}

	/**
	 * 通过主键修改 ACC_商家账户明细
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		accMchtAcctDtlMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 ACC_商家账户明细
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		accMchtAcctDtlMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 ACC_商家账户明细 对象
	 * @param primaryKey 主键
	 * @return ACC_商家账户明细对象
	 */
	public AccMchtAcctDtl getByPrimaryKey(String primaryKey){
		return accMchtAcctDtlMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 ACC_商家账户明细 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AccMchtAcctDtl> queryForList(Map<String, Object> param){
		return accMchtAcctDtlMapper.queryForList(param);
	}
	
	/**
	 * 查询 ACC_商家账户明细 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AccMchtAcctDtl> queryForPage(Page<AccMchtAcctDtl> page){
		accMchtAcctDtlMapper.queryForList(page.getPageParams());
		return (Page<AccMchtAcctDtl>)page.getPageParams().get(Page.KEY);
	}
}