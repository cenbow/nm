package com.hs.loan.cust.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.hs.loan.cust.mapper.AppCustStudyMapper;
import com.hs.loan.cust.entity.AppCustStudy;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;
import com.hs.base.entity.Page;
import com.hs.commons.constants.CommonConstant;

/**
 * APP_客户学校信息 业务处理
 * @author autocreate
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppCustStudyService{
	@Autowired
	private AppCustStudyMapper appCustStudyMapper;
	@Autowired
	private AppCustInfoService appCustInfoService;
	/**
	 * 新增 APP_客户学校信息
	 * @param appCustStudy 新增对象
	 */
	@Transactional
	public void insert(AppCustStudy appCustStudy){
		appCustStudyMapper.insert(appCustStudy);
	}

	/**
	 * 通过主键修改 APP_客户学校信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustStudyMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_客户学校信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appCustStudyMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_客户学校信息 对象
	 * @param primaryKey 主键
	 * @return APP_客户学校信息对象
	 */
	public AppCustStudy getByPrimaryKey(String primaryKey){
		return appCustStudyMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户学校信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppCustStudy> queryForList(Map<String, Object> param){
		return appCustStudyMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户学校信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppCustStudy> queryForPage(Page<AppCustStudy> page){
		appCustStudyMapper.queryForList(page.getPageParams());
		return (Page<AppCustStudy>)page.getPageParams().get(Page.KEY);
	}
	
	//////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 保存或者更新 客户学校信息
	 * @param cust
	 * @param studyLst
	 */
	@Transactional
	public void save(String custNo,List<AppCustStudy> studyLst){
		appCustInfoService.saveCustExtra(custNo, studyLst, appCustStudyMapper);
	}
	
	/**
	 * 删除 客户学校信息
	 * @param custNo
	 * @param ids
	 */
	@Transactional
	public void delete(String custNo,String... ids){
		appCustInfoService.deleteCustExtra(custNo, ids, appCustStudyMapper);
	}
	
	/**
	 * 通过id获取 客户学校信息
	 * 
	 * @param id
	 * @return
	 */
	public AppCustStudy getById(String id){
		return getByPrimaryKey(id);
	}
	
	/**
	 *获取 客户学校信息 list
	 * 
	 * @return
	 */
	public List<AppCustStudy> getList(Map<String,Object> param){
		return queryForList(param);
	}
	
	/**
	 * 通过有效时间区间 获取这个区间的 客户学校信息 list
	 * 
	 * @param custNo
	 * @param availableDate
	 * @return
	 */
	public List<AppCustStudy> getCustStudyListByDate(String custNo,Date availableDate){
		Map<String ,Object> param = new HashMap<>();
		param.put("custNo", custNo);
		param.put("availableDate", availableDate);
		
		//return appCustStudyMapper.getCustStudyListByDate(param);
		return appCustStudyMapper.getAvailableExtraInfoLst(param);
	}
	
	/**
	 * 获取客户当前的 有效的 客户学校信息 list
	 * 
	 * @return
	 */
	public List<AppCustStudy> getCrtCustStudyList(String custNo){
		return getCustStudyListByDate(custNo, new Date());
	}
	
	
}