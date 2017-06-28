package com.hs.loan.cust.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.cust.mapper.AppCustWorkMapper;
import com.hs.loan.cust.entity.AppCustWork;
import com.hs.base.entity.Page;

/**
 * APP_客户工作信息 业务处理
 * @author autocreate
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppCustWorkService{
	@Autowired
	private AppCustWorkMapper appCustWorkMapper;
	@Autowired
	private AppCustInfoService appCustInfoService;
	
	/**
	 * 新增 APP_客户工作信息
	 * @param appCustWork 新增对象
	 */
	@Transactional
	public void insert(AppCustWork appCustWork){
		appCustWorkMapper.insert(appCustWork);
	}

	/**
	 * 通过主键修改 APP_客户工作信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustWorkMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_客户工作信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appCustWorkMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_客户工作信息 对象
	 * @param primaryKey 主键
	 * @return APP_客户工作信息对象
	 */
	public AppCustWork getByPrimaryKey(String primaryKey){
		return appCustWorkMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户工作信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppCustWork> queryForList(Map<String, Object> param){
		return appCustWorkMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户工作信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppCustWork> queryForPage(Page<AppCustWork> page){
		appCustWorkMapper.queryForList(page.getPageParams());
		return (Page<AppCustWork>)page.getPageParams().get(Page.KEY);
	}
	
	//////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 保存或更新 客户工作信息
	 * @param custNo
	 * @param workLst
	 */
	@Transactional
	public void save(String custNo,List<AppCustWork> workLst){
		appCustInfoService.saveCustExtra(custNo, workLst, appCustWorkMapper);
	}

	/**
	 * 删除 客户工作信息
	 * @param custNo
	 * @param ids
	 */
	@Transactional
	public void delete(String custNo , String... ids){
		appCustInfoService.deleteCustExtra(custNo, ids, appCustWorkMapper);
	}
	
	/**
	 * 通过id 获取客户工作信息
	 * @param id
	 * @return
	 */
	public AppCustWork getById(String id){
		return getById(id);
	}
	
	/**
	 * 获取 客户工作信息 list
	 * 
	 * @param param
	 * @return
	 */
	public List<AppCustWork> getList(Map<String,Object> param){
		return queryForList(param);
	}
	
	/**
	 * 获取客户有效时间段里的 有效的 客户工作信息
	 * 
	 * @param custNo
	 * @param availableDate
	 * @return
	 */
	public List<AppCustWork> getCustWorkLstByDate(String custNo,Date availableDate){
		Map<String,Object> param = new HashMap<>();
		param.put("custNo", custNo);
		param.put("availableDate", availableDate);
		//return appCustWorkMapper.getCustWorkLstByDate(param);
		return appCustWorkMapper.getAvailableExtraInfoLst(param);
	}
	
	/**
	 * 获取当前 有效的 客户工作信息
	 * @param custNo
	 * @return
	 */
	public List<AppCustWork> getCrtCustWorkLst(String custNo){
		return getCustWorkLstByDate(custNo, new Date());
	}
	
	
	
}