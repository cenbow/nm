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
import com.hs.loan.cust.entity.AppCustGroupInfo;
import com.hs.loan.cust.entity.AppCustInfo;
import com.hs.loan.cust.mapper.AppCustGroupInfoMapper;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;

/**
 * APP_客户分群 业务处理
 * @author autocreate
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppCustGroupInfoService{
	@Autowired
	private AppCustGroupInfoMapper appCustGroupInfoMapper;
	
	/**
	 * 新增 APP_客户分群
	 * @param appCustGroupInfo 新增对象
	 */
	@Transactional
	public void insert(AppCustGroupInfo appCustGroupInfo){
		appCustGroupInfoMapper.insert(appCustGroupInfo);
	}

	/**
	 * 通过主键修改 APP_客户分群
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustGroupInfoMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_客户分群
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appCustGroupInfoMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_客户分群 对象
	 * @param primaryKey 主键
	 * @return APP_客户分群对象
	 */
	public AppCustGroupInfo getByPrimaryKey(String primaryKey){
		return appCustGroupInfoMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户分群 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppCustGroupInfo> queryForList(Map<String, Object> param){
		return appCustGroupInfoMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户分群 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppCustGroupInfo> queryForPage(Page<AppCustGroupInfo> page){
		appCustGroupInfoMapper.queryForList(page.getPageParams());
		return (Page<AppCustGroupInfo>)page.getPageParams().get(Page.KEY);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 保存或者更新 客户群
	 * @param appCustGroupInfo
	 */
	@Transactional
	public void save(AppCustGroupInfo appCustGroupInfo){
		String no = appCustGroupInfo.getCustGroup();
		if(StringUtils.isEmpty(no)){
			appCustGroupInfo.setCustGroup(RandomUtil.getUUID());
			appCustGroupInfo.setInstDate(new Date());
			insert(appCustGroupInfo);
		}else{
			Map<String,Object> param = BeanUtils.bean2mapExclude(appCustGroupInfo,"instPerson,instDate");
			param.put("beginDate", appCustGroupInfo.getBeginDate());
			param.put("endDate", appCustGroupInfo.getEndDate());
			updateByPrimaryKeySelective(param);
		}
	}
	
	/**
	 * 通过群编号 获取客户群
	 * @param custGroup
	 * @return
	 */
	public AppCustGroupInfo getByNo(String custGroup){
		return getByPrimaryKey(custGroup);
	}
	
	/**
	 * 通过群编号删除
	 * @param custGroup
	 */
	@Transactional
	public void deleteByNo(List<String> custGroupLst){
		for(String custGroup : custGroupLst){
			deleteByPrimaryKey(custGroup);
		}
	}
	
	/**
	 * 获取 客户群list
	 * @param param
	 * @return
	 */
	public List<AppCustGroupInfo>getList(Map<String,Object> param){
		return queryForList(param);
	}
	
	/**
	 * 分页查询 客户群
	 * @return
	 */
	public Page<AppCustGroupInfo> queryCustGroup(Page<AppCustGroupInfo> page){
		return queryForPage(page);
	}
	
	/**
	 * 执行规则
	 * 必须的参数：custGroup
	 * 
	 * @param page
	 * @return
	 */
	public Page<AppCustInfo> executeRule(Page<AppCustInfo> page){
		String custGroup = (String) page.getParams().get("custGroup");
		if(StringUtils.isEmpty(custGroup)){
			throw new AppException("custGroup不可为空");
		}
		AppCustGroupInfo appCustGroupInfo = getByNo(custGroup);
		String rule = appCustGroupInfo.getCustCalc();
		String custName = (String) page.getParams().get("custName");
		if(!StringUtils.isEmpty(custName)){
			rule = "SELECT * FROM ( "+rule+" ) r WHERE r.CUST_NAME like '%"+custName+"%' ";
		}
		page.getParams().put("rule", rule);
		appCustGroupInfoMapper.executeRule(page.getPageParams());
		return (Page<AppCustInfo>) page.getPageParams().get(Page.KEY);
	}
	
	/**
	 * 校验规则是否可用
	 * 
	 * @param rule
	 * @return
	 */
	public boolean validRule(String rule){
		if(StringUtils.isEmpty(rule)) throw new AppException("规则不可为空");
		Map<String,Object> param = new HashMap<>();
		param.put("rule", rule);
		try {
			List<AppCustInfo> lst = appCustGroupInfoMapper.executeRule(param);
			if(lst!=null) return true;
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
}