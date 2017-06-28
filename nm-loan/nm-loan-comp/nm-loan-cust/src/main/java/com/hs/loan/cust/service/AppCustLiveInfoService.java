package com.hs.loan.cust.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hs.base.entity.Page;
import com.hs.commons.constants.CommonConstant;
import com.hs.loan.cust.entity.AppCustLiveInfo;
import com.hs.loan.cust.mapper.AppCustLiveInfoMapper;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;

/**
 * APP_客户居住信息 业务处理
 * @author autocreate
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppCustLiveInfoService{
	@Autowired
	private AppCustLiveInfoMapper appCustLiveInfoMapper;
	@Autowired
	private AppCustInfoService appCustInfoService;
	
	
	/**
	 * 新增 APP_客户居住信息
	 * @param appCustLiveInfo 新增对象
	 */
	@Transactional
	public void insert(AppCustLiveInfo appCustLiveInfo){
		appCustLiveInfoMapper.insert(appCustLiveInfo);
	}

	/**
	 * 通过主键修改 APP_客户居住信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustLiveInfoMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_客户居住信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appCustLiveInfoMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 APP_客户居住信息 对象
	 * @param primaryKey 主键
	 * @return APP_客户居住信息对象
	 */
	public AppCustLiveInfo getByPrimaryKey(String primaryKey){
		return appCustLiveInfoMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户居住信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppCustLiveInfo> queryForList(Map<String, Object> param){
		return appCustLiveInfoMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户居住信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppCustLiveInfo> queryForPage(Page<AppCustLiveInfo> page){
		appCustLiveInfoMapper.queryForList(page.getPageParams());
		return (Page<AppCustLiveInfo>)page.getPageParams().get(Page.KEY);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 通过custNo 获取客户当前最新的客户居住信息
	 * @param custNo
	 * @return
	 */
	public AppCustLiveInfo  getCrtByNo(String custNo){
		return appCustLiveInfoMapper.getCurrentLiveInfoByCustNo(custNo);
	}
	
	/**
	 * 保存或者更新 客户居住信息
	 * 
	 * @param custNo
	 * @param liveLst
	 */
	@Transactional
	public void save(String custNo,List<AppCustLiveInfo> liveLst){
		appCustInfoService.saveCustExtra(custNo, liveLst, appCustLiveInfoMapper);
	}
	
	/**
	 * 删除
	 * 
	 * @param custNo
	 * @param ids
	 */
	@Transactional
	public void delete(String custNo,String... ids){
		appCustInfoService.deleteCustExtra(custNo, ids, appCustLiveInfoMapper);
	}
	
	/**
	 * 通过id 获取 客户居住信息
	 * @param id
	 * @return
	 */
	public AppCustLiveInfo getById(String id){
		return getById(id);
	}
	
	/**
	 * 获取 客户居住信息 list
	 * @param param
	 * @return
	 */
	public List<AppCustLiveInfo> getList(Map<String,Object> param){
		return queryForList(param);
	}
	
	/**
	 * 获取 有效时间段里的 有效的 客户居住信息
	 * @param custNo
	 * @param availableDate
	 * @return
	 */
	public List<AppCustLiveInfo> getCustLiveInfoLstByDate(String custNo,Date availableDate){
		Map<String,Object > param = new HashMap<String,Object>();
		param.put("custNo",custNo);
		param.put("availableDate", availableDate);
		
		return appCustLiveInfoMapper.getAvailableExtraInfoLst(param);
	} 
	
	/**
	 * 获取 当前 有效的 客户居住信息 
	 * @param custNo
	 * @return
	 */
	public List<AppCustLiveInfo> getCrtCustLiveInfoLst(String custNo){
		return getCustLiveInfoLstByDate(custNo, new Date());
	}
	
	
}