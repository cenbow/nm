package com.hs.loan.sale.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.sale.entity.CrmCustomerOrder;
import com.hs.loan.sale.mapper.CrmCustomerOrderMapper;

/**
 * CRM_电销意向办理客户工单信息，将与业务系统数据交互 业务处理
 * @author autocreate
 * @create 2017-04-24
 */
@Service
@Transactional(readOnly=true)
public class  CrmCustomerOrderService{
	@Autowired
	private CrmCustomerOrderMapper crmCustomerOrderMapper;
	
	/**
	 * 新增 CRM_电销意向办理客户工单信息，将与业务系统数据交互
	 * @param crmCustomerOrder 新增对象
	 */
	@Transactional
	public void insert(CrmCustomerOrder crmCustomerOrder){
		crmCustomerOrderMapper.insert(crmCustomerOrder);
	}

	/**
	 * 通过主键修改 CRM_电销意向办理客户工单信息，将与业务系统数据交互
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		crmCustomerOrderMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 CRM_电销意向办理客户工单信息，将与业务系统数据交互
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		crmCustomerOrderMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 CRM_电销意向办理客户工单信息，将与业务系统数据交互 对象
	 * @param primaryKey 主键
	 * @return CRM_电销意向办理客户工单信息，将与业务系统数据交互对象
	 */
	public CrmCustomerOrder getByPrimaryKey(String primaryKey){
		return crmCustomerOrderMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 CRM_电销意向办理客户工单信息，将与业务系统数据交互 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<CrmCustomerOrder> queryForList(Map<String, Object> param){
		return crmCustomerOrderMapper.queryForList(param);
	}
	
	/**
	 * 查询 CRM_电销意向办理客户工单信息，将与业务系统数据交互 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<CrmCustomerOrder> queryForPage(Page<CrmCustomerOrder> page){
		crmCustomerOrderMapper.queryForList(page.getPageParams());
		return (Page<CrmCustomerOrder>)page.getPageParams().get(Page.KEY);
	}
	/**
	 * 查询分期状态
	 * @param object
	 */
	public Map<String, Object> queryLoanStat(String loanNo) {
		// TODO Auto-generated method stub
		Map<String, Object> loanStatMap = crmCustomerOrderMapper.queryLoanStat(loanNo);
		return loanStatMap;
	}

	public List<Map<String, String>> queryMgrStaff(String _orgNo) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("_orgNo", _orgNo);
		return crmCustomerOrderMapper.queryMgrStaff(param);
	}
	/**
	 * 退回工单特殊处理
	 * @param param
	 */
	public void updateBackOrder(Map<String, Object> param) {
		// TODO Auto-generated method stub
		crmCustomerOrderMapper.updateBackOrder(param);
	}
}