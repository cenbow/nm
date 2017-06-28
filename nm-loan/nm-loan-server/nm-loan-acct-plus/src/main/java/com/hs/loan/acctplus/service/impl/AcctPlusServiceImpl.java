package com.hs.loan.acctplus.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hs.base.exception.ServiceException;
import com.hs.loan.acct.api.AcctPlusService;
import com.hs.loan.acct.dto.AppLoanAcctDto;
import com.hs.loan.acctplus.common.DBSTAT;
import com.hs.loan.acctplus.service.AppLoanAcctService;
import com.hs.loan.acctplus.service.AppLoanApprService;
import com.hs.loan.acctplus.service.AppLoanHandService;
import com.hs.loan.acctplus.util.BeanFactory;


/** 
 * <li>ClassName:AcctPlusServiceImpl <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2017年1月4日 <br/> 
 * <li>@author   zzy       
 */
@Service
public class AcctPlusServiceImpl implements AcctPlusService {

	@Autowired 
	private AppLoanAcctService appLoanAcctService;
	@Autowired 
	private AppLoanApprService appLoanApprService;
	@Autowired 
	private AppLoanHandService appLoanHandService;
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
//	@Transactional(rollbackFor=Exception.class)
	public void sendAcctHandle(AppLoanAcctDto acct)
			throws ServiceException,AmqpException {
		System.out.println("new interface");
		//校验数据
		check(acct);
		
		//发送到mq,避免写数据库,也方便部署多机
		acct.setEnterMqTime(System.currentTimeMillis());
		String json = JSON.toJSONString(acct);
		amqpTemplate.convertAndSend(json);
		//避免客户端直接植入mq代码，所以先dubbo接口调用
	}

	
	
	public void check(AppLoanAcctDto acct)throws ServiceException{
		if(acct==null){
			throw new ServiceException("param is error:acctDto is null!");
		}
		if(acct.getEnterType()!=null && !"".equals(acct.getEnterType()) && !"mult".equals(acct.getEnterType())){
			throw new ServiceException("param is error:acct.getEnterType():"+acct.getEnterType());
		}
		if(acct.getStat()==null ||"".equals(acct.getStat())){
			throw new ServiceException("param is error:acct.getStat():"+acct.getStat());
		}
		if(!DBSTAT.APP_LOAN_ACCT_STAT_WAITING_APPROVAL.equals(acct.getStat())){
			throw new ServiceException("param is error:acct.getStat():"+acct.getStat()+" should be "+DBSTAT.APP_LOAN_ACCT_STAT_WAITING_APPROVAL);
		}
		try {
			Integer.parseInt(acct.getFileNo());
		}catch (NumberFormatException e) {
			throw new ServiceException("param is error:acct.getFileNo():"+acct.getFileNo()+" should be int");
		}
		if(acct.getLoanNo()==null || "".equals(acct.getLoanNo())){
			throw new ServiceException("param is error:acct.getLoanNo():"+acct.getLoanNo());
		}
		if(acct.getCustNo()==null || "".equals(acct.getCustNo())){
			throw new ServiceException("param is error:acct.getCustNo():"+acct.getCustNo());
		}
		if(acct.getCustName()==null || "".equals(acct.getCustName())){
			throw new ServiceException("param is error:acct.getCustName():"+acct.getCustName());
		}
		if(acct.getStaffNo()==null || "".equals(acct.getStaffNo())){
			throw new ServiceException("param is error:acct.getStaffNo():"+acct.getStaffNo());	
		}
		if(acct.getStaffName()==null || "".equals(acct.getStaffName())){
			throw new ServiceException("param is error:acct.getStaffName():"+acct.getStaffName());
		}
		if(acct.getApplyDate()==null || "".equals(acct.getApplyDate())){
			throw new ServiceException("param is error:acct.getApplyDate():"+acct.getApplyDate());
		}
		if(acct.getOrgNo()==null || "".equals(acct.getOrgNo())){
			throw new ServiceException("param is error:acct.getOrgNo():"+acct.getOrgNo());
		}
		if(acct.getBranchNo()==null || "".equals(acct.getBranchNo())){
			throw new ServiceException("param is error:acct.getBranchNo():"+acct.getBranchNo());
		}
	}
}
