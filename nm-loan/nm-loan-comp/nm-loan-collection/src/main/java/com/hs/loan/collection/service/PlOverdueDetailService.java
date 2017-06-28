package com.hs.loan.collection.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.loan.collection.entity.PlOverdueDetail;
import com.hs.loan.collection.mapper.PlOverdueDetailMapper;

/**
 * PL_逾期费用项明细 业务处理
 * @author autocreate
 * @create 2015-12-02
 */
@Service
@Transactional(readOnly=true)
public class  PlOverdueDetailService{
	@Autowired
	private PlOverdueDetailMapper plOverdueDetailMapper;
	
	/**
	 * 新增 PL_逾期费用项明细
	 * @param plOverdueDetail 新增对象
	 */
	@Transactional
	public void insert(PlOverdueDetail plOverdueDetail){
		plOverdueDetailMapper.insert(plOverdueDetail);
	}

	/**
	 * 通过主键修改 PL_逾期费用项明细
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		plOverdueDetailMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PL_逾期费用项明细
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		plOverdueDetailMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 PL_逾期费用项明细 对象
	 * @param primaryKey 主键
	 * @return PL_逾期费用项明细对象
	 */
	public PlOverdueDetail getByPrimaryKey(String primaryKey){
		return plOverdueDetailMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PL_逾期费用项明细 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PlOverdueDetail> queryForList(Map<String, Object> param){
		return plOverdueDetailMapper.queryForList(param);
	}
	
	/**
	 * 查询 PL_逾期费用项明细 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PlOverdueDetail> queryForPermissonList(Map<String, Object> param,UserProfile profile){
		param.put("staffNo", profile.getStaffNo());
		param.put("orgNo",profile.getOrgNo());
		param.put("roleNos",profile.getRoleNoSet());
		return this.queryForList(param);
	}
	
	/**
	 * 查询 PL_逾期费用项明细 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PlOverdueDetail> queryForPage(Page<PlOverdueDetail> page){
		plOverdueDetailMapper.queryForList(page.getPageParams());
		return (Page<PlOverdueDetail>)page.getPageParams().get(Page.KEY);
	}
	
	/**
	 * 查询 PL_逾期费用项明细 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PlOverdueDetail> queryForPermissionPage(Page<PlOverdueDetail> page,UserProfile profile){
		plOverdueDetailMapper.queryForList(page.getPageParams());
		return (Page<PlOverdueDetail>)page.getPageParams().get(Page.KEY);
	}
}