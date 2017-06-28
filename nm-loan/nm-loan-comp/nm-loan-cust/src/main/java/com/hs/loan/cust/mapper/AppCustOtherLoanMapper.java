package com.hs.loan.cust.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.cust.entity.AppCustOtherLoan;
import com.hs.loan.cust.itface.ICustExtraInfoMapper;

/**
 * APP_客户其他分期信息 mapper
 * @author autocreate
 * @create 2015-10-26
 */
@MyBatisRepository
public interface  AppCustOtherLoanMapper extends ICustExtraInfoMapper<AppCustOtherLoan>{

	//获取有效时间段里的有效的客户其他分期信息
	List<AppCustOtherLoan> getCustOtherLoanLstByDate(Map<String, Object> param);
	
}