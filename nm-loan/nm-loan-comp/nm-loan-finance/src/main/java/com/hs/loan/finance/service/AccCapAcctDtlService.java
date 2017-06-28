package com.hs.loan.finance.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.finance.mapper.AccCapAcctDtlMapper;
import com.hs.loan.finance.entity.AccCapAcctDtl;
import com.hs.base.entity.Page;

/**
 * ACC_资方账户明细 业务处理
 * @author autocreate
 * @create 2016-02-03
 */
@Service
@Transactional(readOnly=true)
public class  AccCapAcctDtlService{
	@Autowired
	private AccCapAcctDtlMapper accCapAcctDtlMapper;
	
	/**
	 * 新增 ACC_资方账户明细
	 * @param accCapAcctDtl 新增对象
	 */
	@Transactional
	public void insert(AccCapAcctDtl accCapAcctDtl){
		accCapAcctDtlMapper.insert(accCapAcctDtl);
	}

	/**
	 * 通过主键修改 ACC_资方账户明细
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		accCapAcctDtlMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 ACC_资方账户明细
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		accCapAcctDtlMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 ACC_资方账户明细 对象
	 * @param primaryKey 主键
	 * @return ACC_资方账户明细对象
	 */
	public AccCapAcctDtl getByPrimaryKey(String primaryKey){
		return accCapAcctDtlMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 ACC_资方账户明细 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AccCapAcctDtl> queryForList(Map<String, Object> param){
		return accCapAcctDtlMapper.queryForList(param);
	}
	
	/**
	 * 查询 ACC_资方账户明细 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AccCapAcctDtl> queryForPage(Page<AccCapAcctDtl> page){
		accCapAcctDtlMapper.queryForList(page.getPageParams());
		return (Page<AccCapAcctDtl>)page.getPageParams().get(Page.KEY);
	}
}