package com.hs.loan.outsource.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.outsource.bo.LoanOutsourceRelationBo;
import com.hs.loan.outsource.entity.PlLoanOutsourceRelation;
import com.hs.loan.outsource.mapper.PlLoanOutsourceRelationMapper;

/**
 * PL_委外单位与分期合同对应关系表 业务处理
 * @author autocreate
 * @create 2015-12-02
 */
@Service
@Transactional(readOnly=true)
public class  PlLoanOutsourceRelationService{
	@Autowired
	private PlLoanOutsourceRelationMapper plLoanOutsourceRelationMapper;
	
	/**
	 * 新增 PL_委外单位与分期合同对应关系表
	 * @param plLoanOutsourceRelation 新增对象
	 */
	@Transactional
	public void insert(PlLoanOutsourceRelation plLoanOutsourceRelation){
		plLoanOutsourceRelationMapper.insert(plLoanOutsourceRelation);
	}

	/**
	 * 通过主键修改 PL_委外单位与分期合同对应关系表
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		plLoanOutsourceRelationMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PL_委外单位与分期合同对应关系表
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		plLoanOutsourceRelationMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 PL_委外单位与分期合同对应关系表 对象
	 * @param primaryKey 主键
	 * @return PL_委外单位与分期合同对应关系表对象
	 */
	public PlLoanOutsourceRelation getByPrimaryKey(String primaryKey){
		return plLoanOutsourceRelationMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PL_委外单位与分期合同对应关系表 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PlLoanOutsourceRelation> queryForList(Map<String, Object> param){
		return plLoanOutsourceRelationMapper.queryForList(param);
	}
	
	/**
	 * 查询 PL_委外单位与分期合同对应关系表 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PlLoanOutsourceRelation> queryForPage(Page<PlLoanOutsourceRelation> page){
		plLoanOutsourceRelationMapper.queryForList(page.getPageParams());
		return (Page<PlLoanOutsourceRelation>)page.getPageParams().get(Page.KEY);
	}
	/**
	 * 查询 PL_委外单位与分期合同对应关系表 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<LoanOutsourceRelationBo> queryOutsourceRelationForPage(Page<LoanOutsourceRelationBo> page){
		plLoanOutsourceRelationMapper.queryOutsourceRelationForList(page.getPageParams());
		return (Page<LoanOutsourceRelationBo>)page.getPageParams().get(Page.KEY);
	}

	/**
	 * 更新委外状态
	 * @param map
	 */
	@Transactional
	public void updateBySelective(Map<String, Object> map) {
		plLoanOutsourceRelationMapper.updateBySelective(map);
	}
}