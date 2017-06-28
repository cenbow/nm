package com.hs.loan.customer.server;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.loan.customer.api.AppCustomerCallRegisterApi;
import com.hs.loan.customer.dto.AppCustCallRegisterDto;
import com.hs.loan.customer.entity.AppCustCallRegister;
import com.hs.loan.customer.service.AppCustomerCallRegisterService;
import com.hs.loan.pub.hand.service.AppLoanHandService;
import com.hs.utils.BeanUtils;
import com.hs.utils.DateUtils;
import com.hs.utils.RandomUtil;

/**
 * 客户来电信息
 * @author zhangxiaoqiang
 *
 */
@Service
@Transactional(readOnly = true)
public class AppCustomerCallRegisterServer implements AppCustomerCallRegisterApi{
	@Autowired
	private AppCustomerCallRegisterService appCustCallRegisterService;
	@Autowired
	private AppLoanHandService appLoanHandService;
	
	@Transactional
	public void instCallRegister(AppCustCallRegisterDto custCallRegisterDto, UserProfile userProfile, String loanNo)
			throws ServiceException, AppException {
		if (custCallRegisterDto!=null) {
			custCallRegisterDto.setId(RandomUtil.getUUID());
			custCallRegisterDto.setBeginDate(new Date());
			custCallRegisterDto.setEndDate(new Date());
		}

		AppCustCallRegister appCustCallRegister = new AppCustCallRegister();
		BeanUtils.copyProperties(custCallRegisterDto, appCustCallRegister);
		appCustCallRegisterService.insert(appCustCallRegister);
	
		/* 添加经办信息 */
		appLoanHandService.saveAppLoanHand(loanNo, custCallRegisterDto.getCustNo(),
				custCallRegisterDto.getCustName(),
				PubBusinessConstant.LOANHANDTYPE_CUSTSERVICE,
				PubBusinessConstant.LOANHANDMODEL_HAND, userProfile.getStaffNo(),
				userProfile.getStaffName(), DateUtils.getCurrentTimestamp(),
				"添加来电记录 ："+custCallRegisterDto.getCallContent(),PubBusinessConstant.CUST_ZC);
	}

}
