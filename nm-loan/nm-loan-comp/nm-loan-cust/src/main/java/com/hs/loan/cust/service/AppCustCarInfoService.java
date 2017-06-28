package com.hs.loan.cust.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.cust.mapper.AppCustCarInfoMapper;
import com.hs.loan.cust.entity.AppCustCarInfo;
import com.hs.utils.BeanUtils;
import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.commons.constants.CommonConstant;

/**
 * APP_客户车辆信息 业务处理
 * @author autocreate
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppCustCarInfoService{
	@Autowired
	private AppCustCarInfoMapper appCustCarInfoMapper;
	@Autowired
	private AppCustInfoService appCustInfoService;
	/**
	 * 新增 APP_客户车辆信息
	 * @param appCustCarInfo 新增对象
	 */
	@Transactional
	public void insert(AppCustCarInfo appCustCarInfo){
		appCustCarInfoMapper.insert(appCustCarInfo);
	}

	/**
	 * 通过主键修改 APP_客户车辆信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustCarInfoMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_客户车辆信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appCustCarInfoMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_客户车辆信息 对象
	 * @param primaryKey 主键
	 * @return APP_客户车辆信息对象
	 */
	public AppCustCarInfo getByPrimaryKey(String primaryKey){
		return appCustCarInfoMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户车辆信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppCustCarInfo> queryForList(Map<String, Object> param){
		return appCustCarInfoMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户车辆信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppCustCarInfo> queryForPage(Page<AppCustCarInfo> page){
		appCustCarInfoMapper.queryForList(page.getPageParams());
		return (Page<AppCustCarInfo>)page.getPageParams().get(Page.KEY);
	}
	
	////////////////////////////////////////////////////////////////////////////////
	/**
	 * 通过客户号获取客户车辆信息 list
	 * @param custNo
	 * @return
	 */
	public List<AppCustCarInfo> getListByNo(String custNo){
		Map<String,Object> param = new HashMap<>();
		param.put("custNo", custNo);
		return queryForList(param);
	}
	
	/**
	 * 保存或者更新 客户车辆信息
	 * @param custNo
	 * @param carLst
	 */
	@Transactional
	public void save(String custNo,List<AppCustCarInfo> carLst){
		appCustInfoService.saveCustExtra(custNo, carLst, appCustCarInfoMapper);
	}
	
	/**
	 * 删除
	 * @param custNo
	 * @param ids
	 */
	@Transactional
	public void delete(String custNo,String... ids){
		appCustInfoService.deleteCustExtra(custNo, ids, appCustCarInfoMapper);
	}
	
	/**
	 * 通过id获取 客户车辆信息
	 * 
	 * @param id
	 * @return
	 */
	public AppCustCarInfo getById(String id){
		return getByPrimaryKey(id);
	}
	
	/**
	 * 获取 客户车辆信息lst
	 * @param param
	 * @return
	 */
	public List<AppCustCarInfo> getList(Map<String,Object> param){
		return queryForList(param);
	}
	
	/**
	 * 获取有效时间段里的 有效的 客户车辆信息
	 * @param custNo
	 * @param availableDate
	 * @return
	 */
	public List<AppCustCarInfo> getCustCarInfoLstByDate(String custNo,Date availableDate){
		if(StringUtils.isEmpty(custNo)){
			throw new AppException("custNo不可为空");
		}
		Map<String,Object> param = new HashMap<>();
		param.put("custNo", custNo);
		param.put("availableDate", availableDate);
		
		//return appCustCarInfoMapper.getCustCarInfoLstByDate(param);
		return appCustCarInfoMapper.getAvailableExtraInfoLst(param);
	}
	
	/**
	 * 获取客户当前 有效的 客户车辆信息
	 * @param custNo
	 * @return
	 */
	public List<AppCustCarInfo> getCrtCustCarInfoLst(String custNo){
		return getCustCarInfoLstByDate(custNo, new Date());
	}
	
	/**
	 * 获取刚刚修改过的车辆信息
	 * 
	 * @param appCustCarInfo
	 * @return
	 */
	public AppCustCarInfo getEditedCarInfo(AppCustCarInfo appCustCarInfo){
		String custNo = appCustCarInfo.getCustNo();
		if(StringUtils.isEmpty(custNo)){
			throw new AppException("custNo不可为空");
		}
		
		if(StringUtils.isEmpty(appCustCarInfo.getId())){
			appCustCarInfo.setBeginDate(appCustInfoService.getByNo(custNo).getLastApplyDate());
			appCustCarInfo.setEndDate(CommonConstant.MAX_DATE);
		}
		
		Map<String,Object> param = BeanUtils.bean2map(appCustCarInfo);
		param.put("beginDate", appCustCarInfo.getBeginDate());
		param.put("endDate", appCustCarInfo.getEndDate());
		param.put("availableDate", new Date());
		List<AppCustCarInfo> lst = appCustCarInfoMapper.getAvailableExtraInfoLst(param);
		return lst!=null && lst.size()>0 ? lst.get(0):null;
	}
	
}