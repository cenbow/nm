package com.hs.loan.finance.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.finance.mapper.AccInnAcctDtlMapper;
import com.hs.loan.finance.entity.AccInnAcctDtl;
import com.hs.base.entity.Page;

/**
 * ACC_内部账户交易明细 业务处理
 * @author autocreate
 * @create 2016-02-03
 */
@Service
@Transactional(readOnly=true)
public class  AccInnAcctDtlService{
	@Autowired
	private AccInnAcctDtlMapper accInnAcctDtlMapper;
	
	/**
	 * 新增 ACC_内部账户交易明细
	 * @param accInnAcctDtl 新增对象
	 */
	@Transactional
	public void insert(AccInnAcctDtl accInnAcctDtl){
		accInnAcctDtlMapper.insert(accInnAcctDtl);
	}

	/**
	 * 通过主键修改 ACC_内部账户交易明细
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		accInnAcctDtlMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 ACC_内部账户交易明细
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		accInnAcctDtlMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 ACC_内部账户交易明细 对象
	 * @param primaryKey 主键
	 * @return ACC_内部账户交易明细对象
	 */
	public AccInnAcctDtl getByPrimaryKey(String primaryKey){
		return accInnAcctDtlMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 ACC_内部账户交易明细 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AccInnAcctDtl> queryForList(Map<String, Object> param){
		return accInnAcctDtlMapper.queryForList(param);
	}
	
	/**
	 * 查询 ACC_内部账户交易明细 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AccInnAcctDtl> queryForPage(Page<AccInnAcctDtl> page){
		accInnAcctDtlMapper.queryForList(page.getPageParams());
		return (Page<AccInnAcctDtl>)page.getPageParams().get(Page.KEY);
	}
}