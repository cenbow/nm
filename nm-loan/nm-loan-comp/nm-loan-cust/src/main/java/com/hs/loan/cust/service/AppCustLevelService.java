package com.hs.loan.cust.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.cust.mapper.AppCustLevelMapper;
import com.hs.loan.cust.bo.CustStarLevelBo;
import com.hs.loan.cust.entity.AppCustLevel;
import com.hs.base.entity.Page;

/**
 * APP_客户评级 业务处理
 * @author autocreate
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppCustLevelService{
	@Autowired
	private AppCustLevelMapper appCustLevelMapper;
	
	/**
	 * 新增 APP_客户评级
	 * @param appCustLevel 新增对象
	 */
	@Transactional
	public void insert(AppCustLevel appCustLevel){
		appCustLevelMapper.insert(appCustLevel);
	}

	/**
	 * 通过主键修改 APP_客户评级
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustLevelMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_客户评级
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appCustLevelMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_客户评级 对象
	 * @param primaryKey 主键
	 * @return APP_客户评级对象
	 */
	public AppCustLevel getByPrimaryKey(String primaryKey){
		return appCustLevelMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户评级 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppCustLevel> queryForList(Map<String, Object> param){
		return appCustLevelMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户评级 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppCustLevel> queryForPage(Page<AppCustLevel> page){
		appCustLevelMapper.queryForList(page.getPageParams());
		return (Page<AppCustLevel>)page.getPageParams().get(Page.KEY);
	}

	/////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 获取客户 星级评估信息
	 * @param custNo
	 * @return
	 */
	public CustStarLevelBo getStarEvaluate(String custNo) {
		return appCustLevelMapper.getStarEvaluate(custNo);
	}
}