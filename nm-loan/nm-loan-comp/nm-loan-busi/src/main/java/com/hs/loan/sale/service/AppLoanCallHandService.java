package com.hs.loan.sale.service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.sale.mapper.AppLoanCallHandMapper;
import com.hs.utils.StringUtils;
import com.hs.loan.sale.entity.AppLoanCallHand;
import com.hs.base.entity.Page;

/**
 * APP_经办历史记录表 业务处理
 * @author autocreate
 * @create 2017-05-02
 */
@Service
@Transactional(readOnly=true)
public class  AppLoanCallHandService{
	@Autowired
	private AppLoanCallHandMapper appLoanCallHandMapper;
	
	/**
	 * 新增 APP_经办历史记录表
	 * @param appLoanCallHand 新增对象
	 */
	@Transactional
	public void insert(AppLoanCallHand appLoanCallHand){
		appLoanCallHandMapper.insert(appLoanCallHand);
	}

	/**
	 * 通过主键修改 APP_经办历史记录表
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appLoanCallHandMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_经办历史记录表
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appLoanCallHandMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_经办历史记录表 对象
	 * @param primaryKey 主键
	 * @return APP_经办历史记录表对象
	 */
	public AppLoanCallHand getByPrimaryKey(String primaryKey){
		return appLoanCallHandMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_经办历史记录表 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppLoanCallHand> queryForList(Map<String, Object> param){
		return appLoanCallHandMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_经办历史记录表 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppLoanCallHand> queryForPage(Page<AppLoanCallHand> page){
		appLoanCallHandMapper.queryForList(page.getPageParams());
		return (Page<AppLoanCallHand>)page.getPageParams().get(Page.KEY);
	}
	/**
	 * 更新上一步EndDate
	 * @param lastHandType
	 * @param string
	 */
	@Transactional
	public void updateLastHand(String lastHandType, String orderId) {
		if (StringUtils.isNotBlank(lastHandType)) {
			 Map<String, Object> param = new HashMap<String, Object>();
			 param.put("orderId", orderId);
			 param.put("handType", lastHandType);
			 param.put("endDate", new Date());
			 appLoanCallHandMapper.updateLastHand(param);
		}
	}

	public List<AppLoanCallHand> queryHandForList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return appLoanCallHandMapper.queryHandForList(param);
	}
}