package com.hs.loan.pub.hand.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.pub.hand.entity.AppLoanHand;
import com.hs.loan.pub.hand.mapper.AppLoanHandMapper;
import com.hs.utils.DateUtils;
import com.hs.utils.RandomUtil;

/**
 * APP_分期经办登记 业务处理
 * @author autocreate
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppLoanHandService{
	@Autowired
	private AppLoanHandMapper appLoanHandMapper;
	
	/**
	 * 新增 APP_分期经办登记
	 * @param appLoanHand 新增对象
	 */
	@Transactional
	public void insert(AppLoanHand appLoanHand){
		appLoanHandMapper.insert(appLoanHand);
	}

	/**
	 * 通过主键修改 APP_分期经办登记
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appLoanHandMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_分期经办登记
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appLoanHandMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_分期经办登记 对象
	 * @param primaryKey 主键
	 * @return APP_分期经办登记对象
	 */
	public AppLoanHand getByPrimaryKey(String primaryKey){
		return appLoanHandMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_分期经办登记 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppLoanHand> queryForList(Map<String, Object> param){
		return appLoanHandMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_分期经办登记 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppLoanHand> queryForPage(Page<AppLoanHand> page){
		appLoanHandMapper.queryForList(page.getPageParams());
		return (Page<AppLoanHand>)page.getPageParams().get(Page.KEY);
	}
	/**=======================================================***/
	/**
	 * 新增 APP_分期经办登记
	 * @param appLoanHand 新增对象
	 */
	@Transactional
	public void saveAppLoanHand(String loanNo,String custNo,String custName,String handDetailTyp,String type,String handPersonNo,String handPersonName,Date handDate,String remark,String custIdentifier){
		AppLoanHand appLoanHand = new AppLoanHand();
		appLoanHand.setId(RandomUtil.getUUID());
		appLoanHand.setLoanNo(loanNo);
		appLoanHand.setCustNo(custNo);
		appLoanHand.setCustName(custName);
		appLoanHand.setHandDate(handDate);
		appLoanHand.setRemark(remark);
		appLoanHand.setHandDetail(handDetailTyp);
		appLoanHand.setHandPersonNo(handPersonNo);
		appLoanHand.setHandPersonName(handPersonName);
		appLoanHand.setTyp(type);
		appLoanHand.setInstDate(DateUtils.getCurrentTimestamp());
		appLoanHand.setCustIdentifier(custIdentifier);
		this.insert(appLoanHand);
	}
}