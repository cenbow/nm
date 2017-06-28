package com.hs.loan.sale.api;

import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.busi.dto.AppWithdrawInfoDto;

/**
 * 提现记录信息 接口
 * @author jqiu
 * @create 2015-10-27
 */
public interface  LoanAppWithdrawApi{
	
	/**
	 * 查询提现信息
	 * @param 时间段	 
	 */
	public Page<AppWithdrawInfoDto> queryAppWithdrawInfo(Page<AppWithdrawInfoDto> page,UserProfile userProfile) throws ServiceException,AppException;
	
	/**
	 * 更新提现记录状态
	 * @param loanNo
	 * @return int
	 */
	public void updateByPrimaryKeySelective(Map<String, Object> map) throws ServiceException, AppException;
}