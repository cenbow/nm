package com.hs.loan.finance.service;

import com.hs.base.entity.Page;
import com.hs.loan.finance.entity.AccRepayDgReg;
import com.hs.loan.finance.mapper.AccRepayDgRegMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ACC_还款登记（对公） 业务处理
 * @author autocreate
 * @create 2016-02-03
 */
@Service
@Transactional(readOnly=true)
public class  AccRepayDgRegService{
	@Autowired
	private AccRepayDgRegMapper accRepayDgRegMapper;

	public int selectCountExcel(HashMap<String,Object> map){
		return accRepayDgRegMapper.selectCountExcel(map);
	}
	
	/**
	 * 新增 ACC_还款登记（对公）
	 * @param accRepayDgReg 新增对象
	 */
	@Transactional
	public void insert(AccRepayDgReg accRepayDgReg){
		accRepayDgRegMapper.insert(accRepayDgReg);
	}

	/**
	 * 通过主键修改 ACC_还款登记（对公）
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		accRepayDgRegMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 ACC_还款登记（对公）
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		accRepayDgRegMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 ACC_还款登记（对公） 对象
	 * @param primaryKey 主键
	 * @return ACC_还款登记（对公）对象
	 */
	public AccRepayDgReg getByPrimaryKey(String primaryKey){
		return accRepayDgRegMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 ACC_还款登记（对公） 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AccRepayDgReg> queryForList(Map<String, Object> param){
		return accRepayDgRegMapper.queryForList(param);
	}
	
	/**
	 * 查询 ACC_还款登记（对公） 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AccRepayDgReg> queryForPage(Page<AccRepayDgReg> page){
		accRepayDgRegMapper.queryForList(page.getPageParams());
		return (Page<AccRepayDgReg>)page.getPageParams().get(Page.KEY);
	}
	/**
	 * 查询 ACC_还款登记（对公） 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AccRepayDgReg> queryForPageList(Page<AccRepayDgReg> page){
		accRepayDgRegMapper.queryForPageList(page.getPageParams());
		return (Page<AccRepayDgReg>)page.getPageParams().get(Page.KEY);
	}
	/**
	 * 查询 ACC_还款登记（对公） 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public int selectOutSourceCase(String loanNo){
		
		return accRepayDgRegMapper.selectOutSourceCase(loanNo);
	}
}