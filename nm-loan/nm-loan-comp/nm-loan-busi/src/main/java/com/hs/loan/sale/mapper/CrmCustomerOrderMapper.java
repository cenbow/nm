package com.hs.loan.sale.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.sale.entity.CrmCustomerOrder;

/**
 * CRM_电销意向办理客户工单信息，将与业务系统数据交互 mapper
 * @author autocreate
 * @create 2017-04-24
 */
@MyBatisRepository
public interface  CrmCustomerOrderMapper extends BaseMapper<CrmCustomerOrder>{

	Map<String, Object> queryLoanStat(String loanNo);

	List<Map<String, String>> queryMgrStaff(Map<String, Object> param);

	void updateBackOrder(Map<String, Object> param);
	
}