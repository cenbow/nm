package com.hs.loan.cust.api;

import java.util.List;

import com.hs.loan.cust.dto.CustOtherInfoDto;

/**
 * APP_客户其他信息 接口
 * @author autocreate
 * @create 2015-10-26
 */
public interface  CustOtherInfoApi{
	public List<CustOtherInfoDto> queryCustOtherInfoList(String custNo);
}