package com.hs.loan.cust.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.hs.loan.cust.mapper.AppCustAssetInfoMapper;
import com.hs.loan.cust.entity.AppCustAssetInfo;
import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;

/**
 * APP_客户资产信息 业务处理
 * @author autocreate
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppCustAssetInfoService{
	@Autowired
	private AppCustAssetInfoMapper appCustAssetInfoMapper;
	@Autowired
	private AppCustInfoService appCustInfoService;
	
	/**
	 * 新增 APP_客户资产信息
	 * @param appCustAssetInfo 新增对象
	 */
	@Transactional
	public void insert(AppCustAssetInfo appCustAssetInfo){
		appCustAssetInfoMapper.insert(appCustAssetInfo);
	}

	/**
	 * 通过主键修改 APP_客户资产信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustAssetInfoMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_客户资产信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appCustAssetInfoMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_客户资产信息 对象
	 * @param primaryKey 主键
	 * @return APP_客户资产信息对象
	 */
	public AppCustAssetInfo getByPrimaryKey(String primaryKey){
		return appCustAssetInfoMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户资产信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppCustAssetInfo> queryForList(Map<String, Object> param){
		return appCustAssetInfoMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户资产信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppCustAssetInfo> queryForPage(Page<AppCustAssetInfo> page){
		appCustAssetInfoMapper.queryForList(page.getPageParams());
		return (Page<AppCustAssetInfo>)page.getPageParams().get(Page.KEY);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 保存或更新 客户资产信息 
	 */
	@Transactional
	public void save(String custNo,List<AppCustAssetInfo> assetLst){
		appCustInfoService.saveCustExtra(custNo, assetLst, appCustAssetInfoMapper);
	}
	
	/**
	 * 删除
	 * @param custNo
	 * @param ids
	 */
	@Transactional
	public void delete(String custNo,String... ids){
		appCustInfoService.deleteCustExtra(custNo, ids, appCustAssetInfoMapper);
	}
	
	/**
	 * 通过id获取 客户资产信息
	 * @param id
	 */
	public AppCustAssetInfo getById(String id){
		return getByPrimaryKey(id);
	}
	
	/**
	 * 通过客户号 获取 客户资产信息
	 * @param custNo
	 * @return
	 */
	public List<AppCustAssetInfo> getList(Map<String,Object> param ){
		return queryForList(param);
	}
	
	/**
	 * 通过客户号 获取 客户资产信息
	 * @param custNo
	 * @return
	 */
	public List<AppCustAssetInfo> getListByNo(String custNo){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("custNo", custNo);
		return queryForList(param);
	}
	
	/**
	 * 获取客户 有效时间段的有效的 资产信息
	 * @param custNo
	 * @param availableDate
	 * @return
	 */
	public List<AppCustAssetInfo> getCustAssetInfoLstByDate(String custNo,Date availableDate){
		if(StringUtils.isEmpty(custNo)){
			throw new AppException("custNo 不可为空");
		}
		Map<String,Object> param = new HashMap<>();
		param.put("custNo", custNo);
		param.put("availableDate", availableDate);
		//return appCustAssetInfoMapper.getCustAssetInfoLstByDate(param);
		return appCustAssetInfoMapper.getAvailableExtraInfoLst(param);
	}
	
	/**
	 * 获取客户当前有效的资产信息
	 * @param custNo
	 * @return
	 */
	public List<AppCustAssetInfo> getCrtCustAssetInfoLst(String custNo){
		return getCustAssetInfoLstByDate(custNo, new Date());
	}
	
}