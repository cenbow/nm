package com.hs.loan.cust.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.loan.cust.entity.AppCustRevisitInfo;
import com.hs.loan.cust.mapper.AppCustRevisitInfoMapper;
import com.hs.utils.RandomUtil;

/**
 * APP_客户回访信息 业务处理
 * @author autocreate
 * @create 2016-06-21
 */
@Service
@Transactional(readOnly=true)
public class  AppCustRevisitInfoService{
	@Autowired
	private AppCustRevisitInfoMapper appCustRevisitInfoMapper;
	
	/**
	 * 新增 APP_客户回访信息
	 * @param appCustRevisitInfo 新增对象
	 */
	@Transactional
	public void insert(AppCustRevisitInfo appCustRevisitInfo){
			if(StringUtils.isEmpty(appCustRevisitInfo.getCustNo())){
				throw new AppException("客户编号不能为空");
			}
			if(StringUtils.isEmpty(appCustRevisitInfo.getLoanNo())){
				throw new AppException("贷款编号不能为空");
			}
		
		appCustRevisitInfo.setId(RandomUtil.getUUID());
		appCustRevisitInfoMapper.insert(appCustRevisitInfo);
	}

	/**
	 * 通过主键修改 APP_客户回访信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustRevisitInfoMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_客户回访信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appCustRevisitInfoMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_客户回访信息 对象
	 * @param primaryKey 主键
	 * @return APP_客户回访信息对象
	 */
	public AppCustRevisitInfo getByPrimaryKey(String primaryKey){
		return appCustRevisitInfoMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户回访信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppCustRevisitInfo> queryForList(Map<String, Object> param){
		return appCustRevisitInfoMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户回访信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppCustRevisitInfo> queryForPage(Page<AppCustRevisitInfo> page){
		appCustRevisitInfoMapper.queryForList(page.getPageParams());
		return (Page<AppCustRevisitInfo>)page.getPageParams().get(Page.KEY);
	}
}