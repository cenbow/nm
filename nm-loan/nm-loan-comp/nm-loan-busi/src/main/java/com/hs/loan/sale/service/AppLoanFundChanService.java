package com.hs.loan.sale.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.commons.constants.CommonConstant;
import com.hs.loan.sale.entity.AppLoanFundChan;
import com.hs.loan.sale.mapper.AppLoanFundChanMapper;

/**
 * APP_分期与资方渠道关系 业务处理
 * @author autocreate
 * @create 2015-10-27
 */
@Service
@Transactional(readOnly=true)
public class  AppLoanFundChanService{
	@Autowired
	private AppLoanFundChanMapper appLoanFundChanMapper;
	
	/**
	 * 新增 APP_分期与资方渠道关系
	 * @param appLoanFundChan 新增对象
	 */
	@Transactional
	public void insert(AppLoanFundChan appLoanFundChan){
		appLoanFundChanMapper.insert(appLoanFundChan);
	}

	/**
	 * 通过主键修改 APP_分期与资方渠道关系
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appLoanFundChanMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_分期与资方渠道关系
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appLoanFundChanMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_分期与资方渠道关系 对象
	 * @param primaryKey 主键
	 * @return APP_分期与资方渠道关系对象
	 */
	private AppLoanFundChan getByPrimaryKey(String primaryKey){
		return appLoanFundChanMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_分期与资方渠道关系 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	private List<AppLoanFundChan> queryForList(Map<String, Object> param){
		return appLoanFundChanMapper.queryForList(param);
	}
	
	/**
	 * 查询当前分期渠道编号
	 * @param loanNo 分期编号
	 * @return 分期渠道编号
	 */
	public String getEnableChanNoByLoanNo(String loanNo){
		return this.getChanNoByLoanNo(loanNo, CommonConstant.STAT_ENABLE);
	}
	
	/**
	 * 查询历史分期渠道编号
	 * @param loanNo 分期编号
	 * @return 分期渠道编号
	 */
	public String getDisableChanNoByLoanNo(String loanNo){
		return this.getChanNoByLoanNo(loanNo, CommonConstant.STAT_DISABLE);
	}
		
		
	/**
	 * 查询分期渠道编号
	 * @param loanNo 分期编号
	 * @param stat   状态
	 * @return 分期渠道编号
	 */
	private String getChanNoByLoanNo(String loanNo,String stat){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("loanNo", loanNo);
		param.put("stat", stat);
		List<AppLoanFundChan> list = this.queryForList(param);
		if(list != null && list.size() > 0){
			return list.get(0).getChanNo();
		}
		return null;
	}
}