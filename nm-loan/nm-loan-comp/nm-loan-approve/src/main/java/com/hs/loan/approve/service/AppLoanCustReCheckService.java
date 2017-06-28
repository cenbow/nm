package com.hs.loan.approve.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.exception.ServiceException;
import com.hs.loan.approve.bo.AppLoanCustRepeatCheckBo;
import com.hs.loan.approve.mapper.AppLoanCustRepeatCheckMapper;

/**
 * 客户重复信息查询
 * @author mac
 *
 */
@Service
@Transactional(readOnly=true)
public class  AppLoanCustReCheckService{
	@Autowired
	private AppLoanCustRepeatCheckMapper appLoanCustCheckMapper;
	

	/**
	 * 查询 客户重复信息查询 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<AppLoanCustRepeatCheckBo> queryForList(String custNo) throws ServiceException{
		/*
		List<AppLoanCustRepeatCheckBo> list = new ArrayList<>();
		AppLoanCustRepeatCheckBo b = new AppLoanCustRepeatCheckBo();
		b.setReLoanNo("10231023021");
		b.setReName("电话号码");
		b.setReTxt("88288745");
		b.setCustAccountName("张三");
		b.setApplyDate(new Date());
		b.setAprovResult("通过");
		AppLoanCustRepeatCheckBo b1 = new AppLoanCustRepeatCheckBo();
		b1.setReLoanNo("50010223021");
		b1.setReName("电话号码");
		b1.setReTxt("18288745");
		b1.setCustAccountName("张三");
		b1.setApplyDate(new Date());
		b1.setAprovResult("通过");
		AppLoanCustRepeatCheckBo b2 = new AppLoanCustRepeatCheckBo();
		b2.setReLoanNo("50010223021");
		b2.setReName("电话号码");
		b2.setReTxt("133882887");
		b2.setCustAccountName("张三");
		b2.setApplyDate(new Date());
		b.setAprovResult("拒绝");
		list.add(b);
		list.add(b1);
		list.add(b2);
		return list;
		*/
		//return appLoanCustCheckMapper.queryForList(custNo);
		Map<String, String> params = appLoanCustCheckMapper.queryCustInfo(custNo);
		Map<String, Object> map = new java.util.HashMap<>();
		map.put("I_CUST_NO", custNo);
		map.put("I_CUST_INFO", params.get("OTHERS"));
		map.put("O_RET", params.get("custNo"));
		return appLoanCustCheckMapper.queryCustNoCheckLst(map);
	}
	
}