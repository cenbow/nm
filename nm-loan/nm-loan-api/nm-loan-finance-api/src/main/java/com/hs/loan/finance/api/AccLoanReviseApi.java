package com.hs.loan.finance.api;

import java.math.BigDecimal;

import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;

/**
 * 调账
 * 
 * @author zhangxiaoqiang
 *
 */
public interface AccLoanReviseApi {
	/**
	 * 修改客户银行卡开户行
	 * 
	 * @param loanNo
	 * @param openOrg
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public String updateOpenBankOrg(String loanNo, String openOrg, UserProfile profile)
			throws ServiceException, AppException;

	/**
	 * 业务日期不对称修改
	 * 
	 * @param loanNo
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public String updateBusnDate(String loanNo, UserProfile profile) throws ServiceException, AppException;

	/**
	 * 修改对公登记
	 * 
	 * @param dgid
	 * @param tranType
	 * @param tranAmt
	 * @param oldtranAmt
	 * @param profile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public String updateDgReg(String dgid, String tranType, BigDecimal tranAmt, UserProfile profile)
			throws ServiceException, AppException;
}
