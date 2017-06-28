package com.hs.loan.busi.server;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.busi.dto.AppWithdrawInfoDto;
import com.hs.loan.sale.api.LoanAppWithdrawApi;
import com.hs.loan.sale.entity.AppWithdrawInfo;
import com.hs.loan.sale.service.AppLoanWithdrawService;

/**
 * 分期基本信息
 * @author jqiu
 * @create 2015-10-27
 */
@Service
@Transactional(readOnly=true)
public class  LoanAppWithdrawServer implements LoanAppWithdrawApi{
	@Autowired
	private AppLoanWithdrawService appLoanWithdrawService;

	@Override
	public Page<AppWithdrawInfoDto> queryAppWithdrawInfo(
			Page<AppWithdrawInfoDto> page, UserProfile userProfile)
			throws ServiceException, AppException {
		Page<AppWithdrawInfo> pageRsl=appLoanWithdrawService.queryForList(page.toPage(AppWithdrawInfo.class));
		Page<AppWithdrawInfoDto> caseOutDtoPage = pageRsl.toPage(AppWithdrawInfoDto.class);
		return caseOutDtoPage;
	}

	@Override
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map)
			throws ServiceException, AppException {
		appLoanWithdrawService.updateByPrimaryKeySelective(map);
	}



}