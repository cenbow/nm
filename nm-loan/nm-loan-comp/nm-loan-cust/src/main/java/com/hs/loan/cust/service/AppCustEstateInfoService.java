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
import com.hs.base.exception.AppException;
import com.hs.commons.constants.CommonConstant;
import com.hs.loan.cust.entity.AppCustEstateInfo;
import com.hs.loan.cust.mapper.AppCustEstateInfoMapper;
import com.hs.utils.BeanUtils;

/**
 * APP_客户房产信息 业务处理
 * @author autocreate
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppCustEstateInfoService{
	@Autowired
	private AppCustEstateInfoMapper appCustEstateInfoMapper;
	@Autowired
	private AppCustInfoService appCustInfoService;
	
	/**
	 * 新增 APP_客户房产信息
	 * @param appCustEstateInfo 新增对象
	 */
	@Transactional
	public void insert(AppCustEstateInfo appCustEstateInfo){
		appCustEstateInfoMapper.insert(appCustEstateInfo);
	}

	/**
	 * 通过主键修改 APP_客户房产信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustEstateInfoMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_客户房产信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appCustEstateInfoMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_客户房产信息 对象
	 * @param primaryKey 主键
	 * @return APP_客户房产信息对象
	 */
	public AppCustEstateInfo getByPrimaryKey(String primaryKey){
		return appCustEstateInfoMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户房产信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppCustEstateInfo> queryForList(Map<String, Object> param){
		return appCustEstateInfoMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户房产信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppCustEstateInfo> queryForPage(Page<AppCustEstateInfo> page){
		appCustEstateInfoMapper.queryForList(page.getPageParams());
		return (Page<AppCustEstateInfo>)page.getPageParams().get(Page.KEY);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 通过客户号 获取客户的房产信息 list
	 * @param custNo
	 * @return
	 */
	public List<AppCustEstateInfo> getListByNo(String custNo){
		Map<String,Object> param = new HashMap<>();
		param.put("custNo", custNo);
		return queryForList(param);
	}
	
	/**
	 * 保存或者更新 客户的房产信息
	 * 
	 * @param custNo
	 * @param estateLst
	 */
	@Transactional
	public void save(String custNo,List<AppCustEstateInfo> estateLst){
		appCustInfoService.saveCustExtra(custNo, estateLst, appCustEstateInfoMapper);
	}
	
	/**
	 * 删除 客户的房产信息
	 * @param custNo
	 * @param ids
	 */
	@Transactional
	public void delete(String custNo,String... ids){
		appCustInfoService.deleteCustExtra(custNo, ids, appCustEstateInfoMapper);
	}
	
	/**
	 * 根据id 获取客户房产信息
	 * 
	 * @param id
	 */
	public AppCustEstateInfo getById(String id){
		return getByPrimaryKey(id);
	}
	
	/**
	 * 获取 客户房产信息list
	 * 
	 * @param param
	 * @return
	 */
	public List<AppCustEstateInfo> getList(Map<String,Object> param){
		return queryForList(param);
	}
	
	/**
	 * 获取有效时间段的 有效的 客户房产信息
	 * @param custNo
	 * @param availableDate
	 * @return
	 */
	public List<AppCustEstateInfo> getCustEstateInfoLstByDate(String custNo,Date availableDate){
		if(StringUtils.isEmpty(custNo)){
			throw new AppException("custNo不可为空");
		}
		
		Map<String,Object> param = new HashMap<>();
		param.put("custNo", custNo);
		param.put("availableDate",availableDate);
		//return appCustEstateInfoMapper.getCustEstateInfoLstByDate(param);
		return appCustEstateInfoMapper.getAvailableExtraInfoLst(param);
	}
	
	/**
	 * 获取当前有效的 房产信息
	 * 
	 * @param cust
	 * @return
	 */
	public List<AppCustEstateInfo> getCrtEstateInfoLst(String custNo){
		return getCustEstateInfoLstByDate(custNo, new Date());
	}
	
	/**
	 * 获取刚刚编辑过的那条数据
	 * 
	 * @param appCustEstateInfo
	 * @return
	 */
	public AppCustEstateInfo getEditedEstateInfo (AppCustEstateInfo  appCustEstateInfo){
		String custNo = appCustEstateInfo.getCustNo();
		if(StringUtils.isEmpty(custNo)){
			throw new AppException("custNo 不可为空");
		}
		if(StringUtils.isEmpty(appCustEstateInfo.getId())){
			appCustEstateInfo.setBeginDate(appCustInfoService.getByNo(custNo).getLastApplyDate());
			appCustEstateInfo.setEndDate(CommonConstant.MAX_DATE);
		}
		
		Map<String,Object> param = BeanUtils.bean2map(appCustEstateInfo);
		param.put("beginDate",appCustEstateInfo.getBeginDate());
		param.put("endDate", appCustEstateInfo.getEndDate());
		param.put("availableDate",new Date());
		List<AppCustEstateInfo> lst = appCustEstateInfoMapper.getAvailableExtraInfoLst(param);
		return lst !=null && lst.size()>0 ? lst.get(0):null;
	}
	
}