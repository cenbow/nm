package com.hs.loan.cust.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.cust.mapper.AppCustOtherLoanMapper;
import com.hs.loan.cust.entity.AppCustOtherLoan;
import com.hs.utils.BeanUtils;
import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.commons.constants.CommonConstant;

/**
 * APP_客户其他分期信息 业务处理
 * @author autocreate
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppCustOtherLoanService{
	@Autowired
	private AppCustOtherLoanMapper appCustOtherLoanMapper;
	@Autowired
	private AppCustInfoService appCustInfoService;
	
	/**
	 * 新增 APP_客户其他分期信息
	 * @param appCustOtherLoan 新增对象
	 */
	@Transactional
	public void insert(AppCustOtherLoan appCustOtherLoan){
		appCustOtherLoanMapper.insert(appCustOtherLoan);
	}

	/**
	 * 通过主键修改 APP_客户其他分期信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustOtherLoanMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_客户其他分期信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appCustOtherLoanMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_客户其他分期信息 对象
	 * @param primaryKey 主键
	 * @return APP_客户其他分期信息对象
	 */
	public AppCustOtherLoan getByPrimaryKey(String primaryKey){
		return appCustOtherLoanMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户其他分期信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppCustOtherLoan> queryForList(Map<String, Object> param){
		return appCustOtherLoanMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户其他分期信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppCustOtherLoan> queryForPage(Page<AppCustOtherLoan> page){
		appCustOtherLoanMapper.queryForList(page.getPageParams());
		return (Page<AppCustOtherLoan>)page.getPageParams().get(Page.KEY);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 通过custNo 获取 客户其他分期信息 list
	 * @param custNo
	 * @return
	 */
	public List<AppCustOtherLoan> getListByNo(String custNo){
		Map<String,Object> param = new HashMap<>();
		param.put("custNo", custNo);
		return queryForList(param);
	}
	
	/**
	 * 保存或者更新 客户其他分期信息
	 * 
	 * @param custNo
	 * @param loanLst
	 */
	@Transactional
	public void save(String custNo, List<AppCustOtherLoan> loanLst){
		appCustInfoService.saveCustExtra(custNo, loanLst, appCustOtherLoanMapper);
	}
	
	/**
	 * 删除
	 * 
	 * @param custNo
	 * @param ids
	 */
	@Transactional
	public void delete(String custNo,String... ids){
		appCustInfoService.deleteCustExtra(custNo, ids, appCustOtherLoanMapper);
	}
	
	/**
	 * 通过id获取 客户其他分期信息
	 * @param id
	 */
	public AppCustOtherLoan getById(String id){
		return getByPrimaryKey(id);
	}
	
	/**
	 * 获取 客户其他分期信息 list
	 * 
	 * @param param
	 * @return
	 */
	public List<AppCustOtherLoan> getList(Map<String,Object> param){
		return queryForList(param);
	}
	
	/**
	 * 获取有效时间段的有效的 客户其他分期信息
	 * @param custNo
	 * @param availableDate
	 * @return
	 */
	public List<AppCustOtherLoan> getCustOtherLoanLstByDate(String custNo,Date availableDate){
		if(StringUtils.isEmpty(custNo)){
			throw new AppException("客户号不可为空");
		}
		Map<String,Object> param = new HashMap<>();
		param.put("custNo", custNo);
		param.put("availableDate", availableDate);
		
		//return appCustOtherLoanMapper.getCustOtherLoanLstByDate(param);
		return appCustOtherLoanMapper.getAvailableExtraInfoLst(param);
	}
	
	/**
	 * 获取当前 有效的 客户其他分期信息
	 * 
	 * @param custNo
	 * @return
	 */
	public List<AppCustOtherLoan> getCrtCustOtherLoanLst(String custNo){
		return getCustOtherLoanLstByDate(custNo,new Date());
	}
	
	/**
	 * 获取刚刚编辑的其他分期信息
	 * 
	 * @return
	 */
	public AppCustOtherLoan getEditedOtherLoan(AppCustOtherLoan appCustOtherLoan){
		String custNo = appCustOtherLoan.getCustNo();
		if(StringUtils.isEmpty(custNo)){
			throw new AppException("custNo不可为空");
		}
		if(StringUtils.isEmpty(appCustOtherLoan.getId())){
			appCustOtherLoan.setBeginDate(appCustInfoService.getByNo(custNo).getLastApplyDate());
			appCustOtherLoan.setEndDate(CommonConstant.MAX_DATE);
		}
		Map<String,Object> param = BeanUtils.bean2map(appCustOtherLoan);
		param.put("beginDate", appCustOtherLoan.getBeginDate());
		param.put("endDate", appCustOtherLoan.getEndDate());
		param.put("availableDate", new Date());
		
		List<AppCustOtherLoan> lst = appCustOtherLoanMapper.getAvailableExtraInfoLst(param);
		return lst!=null && lst.size()>0?lst.get(0):null;
	}
	
	
	
	
}