package com.hs.loan.outsource.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.outsource.mapper.PlLoanOutsourceDerateMapper;
import com.hs.loan.outsource.entity.PlLoanOutsourceDerate;
import com.hs.base.entity.Page;

/**
 * PL_委外减免表 业务处理
 * @author autocreate
 * @create 2015-12-02
 */
@Service
@Transactional(readOnly=true)
public class  PlLoanOutsourceDerateService{
	@Autowired
	private PlLoanOutsourceDerateMapper plLoanOutsourceDerateMapper;
	
	/**
	 * 新增 PL_委外减免表
	 * @param plLoanOutsourceDerate 新增对象
	 */
	@Transactional
	public void insert(PlLoanOutsourceDerate plLoanOutsourceDerate){
		plLoanOutsourceDerateMapper.insert(plLoanOutsourceDerate);
	}

	/**
	 * 通过主键修改 PL_委外减免表
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		plLoanOutsourceDerateMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PL_委外减免表
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		plLoanOutsourceDerateMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 PL_委外减免表 对象
	 * @param primaryKey 主键
	 * @return PL_委外减免表对象
	 */
	public PlLoanOutsourceDerate getByPrimaryKey(String primaryKey){
		return plLoanOutsourceDerateMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PL_委外减免表 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PlLoanOutsourceDerate> queryForList(Map<String, Object> param){
		return plLoanOutsourceDerateMapper.queryForList(param);
	}
	
	/**
	 * 查询 PL_委外减免表 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PlLoanOutsourceDerate> queryForPage(Page<PlLoanOutsourceDerate> page){
		plLoanOutsourceDerateMapper.queryForList(page.getPageParams());
		return (Page<PlLoanOutsourceDerate>)page.getPageParams().get(Page.KEY);
	}
}