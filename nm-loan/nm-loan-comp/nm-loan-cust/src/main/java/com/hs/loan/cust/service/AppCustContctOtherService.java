package com.hs.loan.cust.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scala.annotation.meta.param;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.commons.constants.CommonConstant;
import com.hs.loan.cust.entity.AppCustContctInfo;
import com.hs.loan.cust.entity.AppCustContctOther;
import com.hs.loan.cust.mapper.AppCustContctOtherMapper;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;

/**
 * APP_客户其他联系人信息 业务处理
 * @author autocreate
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppCustContctOtherService{
	@Autowired
	private AppCustContctOtherMapper appCustContctOtherMapper;
	@Autowired
	private AppCustInfoService appCustInfoService;
	 
	/**
	 * 新增 APP_客户其他联系人信息
	 * @param appCustContctOther 新增对象
	 */
	@Transactional
	public void insert(AppCustContctOther appCustContctOther){
		appCustContctOtherMapper.insert(appCustContctOther);
	}

	/**
	 * 通过主键修改 APP_客户其他联系人信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustContctOtherMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_客户其他联系人信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appCustContctOtherMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_客户其他联系人信息 对象
	 * @param primaryKey 主键
	 * @return APP_客户其他联系人信息对象
	 */
	public AppCustContctOther getByPrimaryKey(String primaryKey){
		return appCustContctOtherMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户其他联系人信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppCustContctOther> queryForList(Map<String, Object> param){
		return appCustContctOtherMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户其他联系人信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppCustContctOther> queryForPage(Page<AppCustContctOther> page){
		appCustContctOtherMapper.queryForList(page.getPageParams());
		return (Page<AppCustContctOther>)page.getPageParams().get(Page.KEY);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 通过客户号 获取 其他联系人信息 list
	 * @param custNo
	 * @return
	 */
	public List<AppCustContctOther> getListByNo(String custNo){
		Map<String ,Object> param = new HashMap<>();
		param.put("custNo", custNo);
		return queryForList(param);
	}
	
	/**
	 * 保存 客户其他联系人信息
	 * @param custNo
	 * @param otherLst
	 */
	@Transactional
	public void save(String custNo,List<AppCustContctOther> otherLst){
		appCustInfoService.saveCustExtra(custNo, otherLst, appCustContctOtherMapper);
	}
	
	/**
	 * 删除 客户其他联系人信息
	 */
	@Transactional
	public void delete(String custNo,String... ids){
		appCustInfoService.deleteCustExtra(custNo, ids, appCustContctOtherMapper);
	}
	
	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	public AppCustContctOther getById(String id){
		return getByPrimaryKey(id);
	}
	
	/**
	 * 通过客户号和有效时间段 获取客户其他联系人信息
	 * @param availableDate
	 * @return
	 */
	public List<AppCustContctOther> getCustContctOtherLstByDate(String custNo,Date availableDate){
		if(StringUtils.isEmpty(custNo)){
			throw new AppException("custNo不可为空");
		}
		Map<String,Object> param = new HashMap<>();
		param.put("custNo", custNo);
		param.put("availableDate", availableDate);
		//return appCustContctOtherMapper.getCustContctOtherLstByDate(param);
		return appCustContctOtherMapper.getAvailableExtraInfoLst(param);
	}
	
	/**
	 * 获取客户全部的 客户其他联系人联系信息
	 * 
	 * @param custNo
	 * @return
	 */
	public List<AppCustContctOther> getCrtContctOtherLst(String custNo){
		return getCustContctOtherLstByDate(custNo, new Date());
	}
	
	/**
	 * 获取 客户其他联系人联系信息
	 * @param param
	 * @return
	 */
	public List<AppCustContctOther> getList(Map<String,Object> param){
		return queryForList(param);
	}
	
}