package com.hs.loan.finance.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.finance.mapper.AccAcctJnlMapper;
import com.hs.loan.finance.entity.AccAcctJnl;
import com.hs.base.entity.Page;

/**
 * ACC_分录流水 业务处理
 * @author autocreate
 * @create 2016-02-03
 */
@Service
@Transactional(readOnly=true)
public class  AccAcctJnlService{
	@Autowired
	private AccAcctJnlMapper accAcctJnlMapper;
	
	/**
	 * 新增 ACC_分录流水
	 * @param accAcctJnl 新增对象
	 */
	@Transactional
	public void insert(AccAcctJnl accAcctJnl){
		accAcctJnlMapper.insert(accAcctJnl);
	}

	/**
	 * 通过主键修改 ACC_分录流水
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		accAcctJnlMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 ACC_分录流水
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		accAcctJnlMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 ACC_分录流水 对象
	 * @param primaryKey 主键
	 * @return ACC_分录流水对象
	 */
	public AccAcctJnl getByPrimaryKey(String primaryKey){
		return accAcctJnlMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 ACC_分录流水 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AccAcctJnl> queryForList(Map<String, Object> param){
		return accAcctJnlMapper.queryForList(param);
	}
	
	/**
	 * 查询 ACC_分录流水 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AccAcctJnl> queryForPage(Page<AccAcctJnl> page){
		accAcctJnlMapper.queryForList(page.getPageParams());
		return (Page<AccAcctJnl>)page.getPageParams().get(Page.KEY);
	}
}