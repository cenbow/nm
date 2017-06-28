package com.hs.loan.acctplus.util;

import java.util.Date;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import com.hs.base.exception.ServiceException;
import com.hs.loan.acct.dto.AppLoanAcctDto;
import com.hs.loan.acctplus.model.AppLoanAcct;
import com.hs.loan.acctplus.model.AppLoanAppr;
import com.hs.loan.acctplus.model.AppLoanHand;
import com.hs.loan.acctplus.service.AppLoanAcctService;
import com.hs.loan.acctplus.service.AppLoanApprService;
import com.hs.loan.acctplus.service.AppLoanHandService;

/** 
 * <li>ClassName:AppLoanUtil <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2017年1月11日 <br/> 
 * <li>@author   zzy       
 */
public class AppLoanUtil {
	
	public static AppLoanAppr getAppLoanApprByDto(AppLoanAcctDto acct){
		AppLoanAppr ap = new AppLoanAppr();
		ap.setApprId(UUID.randomUUID().toString().replace("-", ""));
		ap.setLoanNo(acct.getLoanNo());
		ap.setCustNo(acct.getCustNo());
		ap.setCustName(acct.getCustName());
		ap.setSaleNo(acct.getStaffNo());
		ap.setSaleName(acct.getStaffName());
		ap.setInstDate(acct.getApplyDate());
		ap.setEntrDate(new Date());
		return ap;
	}
	
	public static AppLoanHand getAppLoanHandByDto(AppLoanAcctDto acct){
		
		AppLoanHand h = new AppLoanHand();
		h.setId(UUID.randomUUID().toString().replace("-", ""));
		h.setCustName(acct.getCustName());
		h.setCustNo(acct.getCustNo());
		h.setLoanNo(acct.getLoanNo());
		h.setHandDate(new Date());
		h.setInstDate(new Date());
		
		h.setTyp("30101001");//系统
		h.setHandPersonNo("AUTO");
		h.setHandPersonName("AUTO");
		h.setHandDetail("30102002");//审批
		return h;
	}
	
	public static AppLoanAppr refuseAppr(AppLoanAppr ap){
		ap.setInstNum(1);//进件数量
		ap.setStat("40002004");//审批拒绝
		ap.setManuFlag("10001002");//是否人工审批  否
		ap.setApprNo("AUTO");
		ap.setApprName("AUTO");
		return ap;
	}
	
//	public static void approval(AppLoanAcctDto acct,String acct_stat,String appr_stat,String hand_remark){
//		//修改app_loan_acct状态
//		AppLoanAcct ac = new AppLoanAcct();
//		ac.setLoanNo(acct.getLoanNo());
//		ac.setStat(acct_stat);
//		AppLoanAcctService appLoanAcctService = BeanFactory.getBean(AppLoanAcctService.class);
//		boolean flag = appLoanAcctService.updateSelectiveById(ac);
//		if(!flag){
//			throw new ServiceException("update acct stat return false !");
//		}
//		//插入贷款审批信息
//		AppLoanAppr ap = AppLoanUtil.getAppLoanApprByDto(acct);
//		ap.setInstNum(1);
//		ap.setStat(appr_stat);
//		ap.setManuFlag("10001002");
//		ap.setApprNo("AUTO");
//		ap.setApprName("AUTO");
//		AppLoanApprService appLoanApprService = BeanFactory.getBean(AppLoanApprService.class);
//		appLoanApprService.insert(ap);
//		insertAppLoanApprCache(ap);
//		
//		//插入贷款经办信息
//		AppLoanHand h = AppLoanUtil.getAppLoanHandByDto(acct);
//		h.setRemark(hand_remark);
//		AppLoanHandService appLoanHandService = BeanFactory.getBean(AppLoanHandService.class);
//		appLoanHandService.insert(h);
//	}
	
	public static void insertAppLoanApprCache(AppLoanAppr ap){
		
		
	}
}
