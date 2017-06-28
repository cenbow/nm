package com.hs.loan.acctplus;

import java.util.Date;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import com.alibaba.fastjson.JSON;
import com.hs.loan.acct.dto.AppLoanAcctDto;

/** 
 * <li>ClassName:TestJson <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2017年1月11日 <br/> 
 * <li>@author   zzy       
 */
public class TestJson {
	public static void main(String[] args) {
		
		AppLoanAcctDto dto = new AppLoanAcctDto();
		
		dto.setLoanNo("00000160303132854540");
		dto.setStat("30201002");
		dto.setFileNo("5");
		
		dto.setCustNo("custno");
		dto.setCustName("custname");
		dto.setStaffNo("staffNo");
		dto.setStaffName("staffName");
		dto.setApplyDate(new Date());
		
		String json = JSON.toJSONString(dto);
		System.out.println(json);
		AppLoanAcctDto acct = JSON.parseObject(json, AppLoanAcctDto.class);
		System.out.println(acct.toString());
		
		Jackson2JsonMessageConverter aa = new Jackson2JsonMessageConverter();
	}
}
