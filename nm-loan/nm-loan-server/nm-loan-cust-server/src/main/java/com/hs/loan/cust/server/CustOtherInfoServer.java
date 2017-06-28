package com.hs.loan.cust.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.loan.cust.api.CustOtherInfoApi;
import com.hs.loan.cust.dto.CustOtherInfoDto;
import com.hs.loan.cust.service.AppCustOtherInfoService;
import com.hs.utils.BeanUtils;
/**
 * 客户其它信息 服务
 * @author xb
 *
 */
@Service
@Transactional(readOnly=true)
public class CustOtherInfoServer implements CustOtherInfoApi{
	@Autowired
	private AppCustOtherInfoService custOtherInfoService;
	
	public List<CustOtherInfoDto> queryCustOtherInfoList(String custNo){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("custNo", custNo);
		List<CustOtherInfoDto> otherInfoLst =  BeanUtils.copyProperties(custOtherInfoService.queryForList(param),CustOtherInfoDto.class);
		return otherInfoLst;
	}
}
