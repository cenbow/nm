package com.hs.loan.sale.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.sale.mapper.AppLoanFileMapper;
import com.hs.loan.sale.entity.AppLoanFee;
import com.hs.loan.sale.entity.AppLoanFile;
import com.hs.base.entity.Page;
import com.hs.commons.constants.CommonConstant;

/**
 * APP_分期与合同的关系 业务处理
 * @author autocreate
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppLoanFileService{
	@Autowired
	private AppLoanFileMapper appLoanFileMapper;
	
	/**
	 * 新增 APP_分期与合同的关系
	 * @param appLoanFile 新增对象
	 */
	@Transactional
	public void insert(AppLoanFile appLoanFile){
		appLoanFileMapper.insert(appLoanFile);
	}

	/**
	 * 通过主键修改 APP_分期与合同的关系
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appLoanFileMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_分期与合同的关系
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appLoanFileMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_分期与合同的关系 对象
	 * @param primaryKey 主键
	 * @return APP_分期与合同的关系对象
	 */
	public AppLoanFile getByPrimaryKey(String primaryKey){
		return appLoanFileMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_分期与合同的关系 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	private List<AppLoanFile> queryForList(Map<String, Object> param){
		return appLoanFileMapper.queryForList(param);
	}
	
	/**
	 * 查询当前使用分期与合同
	 * @param loanNo 分期编号
	 * @return List<AppLoanFee>
	 */
	public List<AppLoanFile> queryEnableByLoanNo(String loanNo){
		return this.queryByLoanNo(loanNo, CommonConstant.STAT_ENABLE);
	}
	
	/**
	 * 查询历史分期与合同
	 * @param loanNo 分期编号
	 * @return List<AppLoanFee>
	 */
	public List<AppLoanFile> queryDisableByLoanNo(String loanNo){
		return this.queryByLoanNo(loanNo, CommonConstant.STAT_DISABLE);
	}
		
		
	/**
	 * 查询分期与合同
	 * @param loanNo 分期编号
	 * @param stat   状态
	 * @return List<AppLoanFee>
	 */
	private List<AppLoanFile> queryByLoanNo(String loanNo,String stat){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("loanNo", loanNo);
		param.put("stat", stat);
		List<AppLoanFile> list = this.queryForList(param);
		return list;
	}
}