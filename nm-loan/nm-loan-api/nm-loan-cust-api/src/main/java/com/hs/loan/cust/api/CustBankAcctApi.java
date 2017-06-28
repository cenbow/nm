package com.hs.loan.cust.api;

import java.util.List;

import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.cust.dto.CardBinDto;
import com.hs.loan.cust.dto.CustBankAcctDto;

/**
 * 客户银行账户 接口
 * @author zwr
 *
 */
public interface CustBankAcctApi {

	/**
	 * 获取客户有效的银行账户信息 按时间倒序排序
	 * @param custNo
	 * @return
	 */
	public List<CustBankAcctDto> getListByNo(String custNo) throws ServiceException,AppException;
	
	/**
	 * 获取客户无效的银行账户信息 按时间倒序排序
	 * @param custNo
	 * @return
	 */
	public List<CustBankAcctDto> getInvalidListByNo(String custNo) throws ServiceException,AppException;
	
	/**
	 * 获取客户的银行账户信息，按时间倒序，包括有效的和无效的
	 * @param custNo
	 * @return
	 */
	public List<CustBankAcctDto> getListAll(String custNo) throws ServiceException,AppException;
	
	/**
	 * 通过主键id 获取 客户银行账户信息
	 * @param id
	 * @return
	 */
	public CustBankAcctDto getById (String id) throws ServiceException,AppException;
	
	/**
	 * 获取cardBin信息
	 * @param cardBin
	 * @return
	 */
	public CardBinDto getCardBinInfo(String cardBin) throws ServiceException,AppException;
	
	/**
	 * 保存（insert）银行卡信息,银行卡不可修改
	 * 
	 * @param appCustBankAcct
	 */
	public void save(CustBankAcctDto custBankAcctDto) throws ServiceException,AppException;
	
	/**
	 * 保存（insert）银行卡信息（运营使用）
	 * 
	 * @param appCustBankAcct
	 */
	public void save(CustBankAcctDto custBankAcctDto,UserProfile userProfile,String loanNo) throws ServiceException,AppException;
	
	/**
	 * 删除银行卡信息
	 * @param id
	 */
	public void removeById(String id) throws ServiceException,AppException;
	
	/**
	 * 获取客户的银行卡信息
	 * 
	 * @param custNo 客户号
	 * @param acctNo 银行账户号
	 */
	public CustBankAcctDto getCustBankCard(String custNo,String acctNo) throws ServiceException,AppException;
	
	
	/**
	 * 保存（insert）银行卡信息,银行卡不可修改
	 * 
	 * @param appCustBankAcct
	 */
	public void saveCustLoanBank(CustBankAcctDto custBankAcctDto,String loanNo) throws ServiceException,AppException;
}
