package com.hs.loan.cust.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.hs.loan.cust.mapper.AppCustContctInfoMapper;
import com.hs.loan.cust.entity.AppCustContctInfo;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;
import com.hs.base.entity.Page;
import com.hs.commons.constants.CommonConstant;

/**
 * APP_客户联系信息 业务处理
 * @author autocreate
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppCustContctInfoService{
	@Autowired
	private AppCustContctInfoMapper appCustContctInfoMapper;
	//客户基本信息组件
	@Autowired
	private AppCustInfoService appCustInfoService;
	
	/**
	 * 新增 APP_客户联系信息
	 * @param appCustContctInfo 新增对象
	 */
	@Transactional
	public void insert(AppCustContctInfo appCustContctInfo){
		appCustContctInfoMapper.insert(appCustContctInfo);
	}

	/**
	 * 通过主键修改 APP_客户联系信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustContctInfoMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_客户联系信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appCustContctInfoMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_客户联系信息 对象
	 * @param primaryKey 主键
	 * @return APP_客户联系信息对象
	 */
	public AppCustContctInfo getByPrimaryKey(String primaryKey){
		return appCustContctInfoMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户联系信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppCustContctInfo> queryForList(Map<String, Object> param){
		return appCustContctInfoMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户联系信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppCustContctInfo> queryForPage(Page<AppCustContctInfo> page){
		appCustContctInfoMapper.queryForList(page.getPageParams());
		return (Page<AppCustContctInfo>)page.getPageParams().get(Page.KEY);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 通过custNo 获取客户当前联系信息
	 * @param custNo
	 * @return
	 */
	public AppCustContctInfo getCrtByNo(String custNo){
		return appCustContctInfoMapper.getCustCurrentContactInfoByNo(custNo);
	}
	
	/**
	 * 保存或者更新 客户联系信息
	 * @param custNo
	 * @param contctLst
	 */
	@Transactional
	public void save(String custNo,List<AppCustContctInfo> contctLst){
		appCustInfoService.saveCustExtra(custNo, contctLst, appCustContctInfoMapper);
	}
	
	/**
	 * 删除某个客户的 客户联系信息
	 * @param custNo
	 * @param ids
	 */
	@Transactional
	public void delete(String custNo,String... ids){
		appCustInfoService.deleteCustExtra(custNo, ids, appCustContctInfoMapper);
		
	}
	
	/**
	 * 更新 客户联系信息-----此方法待定------------
	 * 
	 * @param appCustContctInfo
	 */
	@Transactional
	public void update(AppCustContctInfo appCustContctInfo){
		if(StringUtils.isEmpty(appCustContctInfo.getId())){
			return ;
		}
		Date now = new Date();
		Map<String,Object> param = BeanUtils.bean2map(appCustContctInfo);
		param.put("endDate", now);
		updateByPrimaryKeySelective(param);
		
		appCustContctInfo.setBeginDate(now);
		appCustContctInfo.setEndDate(CommonConstant.MAX_DATE);
		insert(appCustContctInfo);
	}
	
	/**
	 * 通过id获取 客户联系信息
	 * @param id
	 * @return
	 */
	public AppCustContctInfo getById(String id){
		return getByPrimaryKey(id);
	}
	
	/**
	 * 根据客户号和有效时间段 获取 客户联系信息
	 * 
	 * @param custNo
	 * @param availableDate
	 * @return
	 */
	public List<AppCustContctInfo> getCustContctInfoListByDate(String custNo,Date availableDate){
		Map<String,Object> param = new HashMap<>();
		param.put("custNo", custNo);
		param.put("availableDate", availableDate);
		
		return appCustContctInfoMapper.getAvailableExtraInfoLst(param);
	}
	
	/**
	 * 获取最新的 有效的  客户联系信息
	 * 
	 * @return
	 */
	public List<AppCustContctInfo> getCrtContctInfoLst(String custNo){
		return getCustContctInfoListByDate(custNo, new Date());
	}
	
	/**
	 * 获取客户全部的 客户联系信息
	 * @param custNo
	 * @return
	 */
	public List<AppCustContctInfo> getListByNo(String custNo){
		Map<String,Object> param = new HashMap<>();
		param.put("custNo", custNo);
		return queryForList(param);
	}
	
	
	
}