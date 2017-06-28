package com.hs.loan.sale.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.sale.mapper.AppLoanBankAcctMapper;
import com.hs.loan.sale.entity.AppLoanBankAcct;
import com.hs.loan.sale.entity.AppLoanBranch;
import com.hs.base.entity.Page;
import com.hs.commons.constants.CommonConstant;

/**
 * APP_分期与银行账户关系 业务处理
 * @author autocreate
 * @create 2015-10-27
 */
@Service
@Transactional(readOnly=true)
public class  AppLoanBankAcctService{
	@Autowired
	private AppLoanBankAcctMapper appLoanBankAcctMapper;
	
	/**
	 * 新增 APP_分期与银行账户关系
	 * @param appLoanBankAcct 新增对象
	 */
	@Transactional
	public void insert(AppLoanBankAcct appLoanBankAcct){
		appLoanBankAcctMapper.insert(appLoanBankAcct);
	}

	/**
	 * 通过主键修改 APP_分期与银行账户关系
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appLoanBankAcctMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_分期与银行账户关系
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appLoanBankAcctMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键删除 APP_分期与银行账户关系
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByLoanNo(String loanNo){
		appLoanBankAcctMapper.deleteByLoanNo(loanNo);
	}


	/**
	 * 通过主键取得 APP_分期与银行账户关系 对象
	 * @param primaryKey 主键
	 * @return APP_分期与银行账户关系对象
	 */
	private AppLoanBankAcct getByPrimaryKey(String primaryKey){
		return appLoanBankAcctMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_分期与银行账户关系 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppLoanBankAcct> queryForList(Map<String, Object> param){
		return appLoanBankAcctMapper.queryForList(param);
	}
	

	/**
	 * 查询可用银行账户表ID
	 * @param loanNo 分期编号
	 * @return 银行卡ID
	 */
	public String getEnableAcctByLoanNo(String loanNo){
		return this.getAcctByLoanNo(loanNo, CommonConstant.STAT_ENABLE);
	}
	
	/**
	 * 查询历史银行账户表ID
	 * @param loanNo 分期编号
	 * @return 银行卡ID
	 */
	public String getDisableAcctByLoanNo(String loanNo){
		return this.getAcctByLoanNo(loanNo, CommonConstant.STAT_DISABLE);
	}
		
		
	/**
	 * 查询银行账户表ID
	 * @param loanNo 分期编号
	 * @param stat   状态
	 * @return 银行卡ID
	 */
	private String getAcctByLoanNo(String loanNo,String stat){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("loanNo", loanNo);
		param.put("stat", stat);
		List<AppLoanBankAcct> list = this.queryForList(param);
		if(list != null && list.size() > 0){
			return list.get(0).getBankAcctId();
		}
		return null;
	}
	
	/**
	 * 变更分期还款账户
	 * @param loanNo 分期编号
	 * @param stat   状态
	 * @param 银行卡ID
	 */
	public void updateByLoanSelective(Map<String,Object> param){
		
		 appLoanBankAcctMapper.updateByLoanSelective(param);
	}
	
	
	
}