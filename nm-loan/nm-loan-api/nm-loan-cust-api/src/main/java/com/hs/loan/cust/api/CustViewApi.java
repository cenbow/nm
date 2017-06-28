package com.hs.loan.cust.api;

import java.util.List;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.cust.dto.CustAssetInfoDto;
import com.hs.loan.cust.dto.CustBankAcctDto;
import com.hs.loan.cust.dto.CustCallRegisterDto;
import com.hs.loan.cust.dto.CustCarInfoDto;
import com.hs.loan.cust.dto.CustContctInfoDto;
import com.hs.loan.cust.dto.CustContctOtherDto;
import com.hs.loan.cust.dto.CustCreditInfoDto;
import com.hs.loan.cust.dto.CustEstateInfoDto;
import com.hs.loan.cust.dto.CustInfoDto;
import com.hs.loan.cust.dto.CustLiveInfoDto;
import com.hs.loan.cust.dto.CustLoanTotalDto;
import com.hs.loan.cust.dto.CustOtherLoanDto;
import com.hs.loan.cust.dto.CustStarLevelBoDto;

/**
 * 客户视图 接口
 * @author zwr
 *
 */
public interface CustViewApi {

	/**
	 * 通过客户号，获取客户基本信息（包含户籍信息）
	 * @param custNo
	 * @return
	 */
	public CustInfoDto getCustInfoByNo(String custNo) throws ServiceException,AppException;
	
	/**
	 * 通过客户号 获取客户的星级评估信息
	 * @param custNo
	 * @return
	 */
	public CustStarLevelBoDto getCustStarLevel(String custNo) throws ServiceException,AppException;
	
	/**
	 * 通过客户号 获取 客户的现居住信息
	 * @param custNo
	 * @return
	 */
	public CustLiveInfoDto getCustCrtLiveInfoByNo(String custNo) throws ServiceException,AppException;
	
	/**
	 * 通过客户号 获取客户现联系信息
	 * @return
	 */
	public CustContctInfoDto getCustCrtContctInfoByNo(String custNo) throws ServiceException,AppException;
	
	/**
	 * 通过客户号 获取 客户其他联系人信息
	 * @param custNo
	 * @return
	 */
	public List<CustContctOtherDto> getCustContctOtherLst(String custNo) throws ServiceException,AppException;
	
	/**
	 * 通过客户号 获取 客户资产信息(收入来源)
	 * @param custNo
	 * @return
	 */
	public List<CustAssetInfoDto> getCustAssetInfoLst(String custNo) throws ServiceException,AppException;
	
	/**
	 * 通过客户号 获取 客户房产信息
	 * @param custNo
	 * @return
	 */
	public List<CustEstateInfoDto> getCustEstateInfoLst(String custNo) throws ServiceException,AppException;
	
	/**
	 * 通过客户号，获取 客户车辆信息
	 * @param custNo
	 * @return
	 */
	public List<CustCarInfoDto> getCustCarInfoLst(String custNo) throws ServiceException,AppException;
	
	/**
	 * 通过客户号 获取 客户信用卡信息
	 * @param custNo
	 * @return
	 */
	public List<CustCreditInfoDto> getCustCreditInfoLst(String custNo) throws ServiceException,AppException;
	
	/**
	 * 通过客户号 获取 客户其他分期信息
	 * @param custNo
	 * @return
	 */
	public List<CustOtherLoanDto> getCustOtherLoanLst(String custNo) throws ServiceException,AppException;
	
	/**
	 * 分页查询 客户分期信息 按时间倒序排
	 * 必须的参数 custNo
	 * @param page
	 * @return
	 */
	//public Page<CustLoanInfoDto> queryCustLoanInfo(Page<CustLoanInfoDto> page) throws ServiceException,AppException;
	
	/**
	 * 通过客户号 获取客户分期汇总信息
	 * @param custNo
	 * @return
	 */
	public CustLoanTotalDto getCustLoanTotal(String custNo) throws ServiceException,AppException;
	
	/**
	 * 分页查询 客户来电记录 按时间倒序排序
	 * 必须的参数 custNo
	 * @param page
	 * @return
	 */
	public Page<CustCallRegisterDto> queryCustCallRegister(Page<CustCallRegisterDto> page) throws ServiceException,AppException;
	
	/**
	 * 获取客户有效的银行账户信息
	 * @param custNo
	 * @return
	 */
	public List<CustBankAcctDto> getCustBankAcctLst(String custNo) throws ServiceException,AppException;

	/**
	 * 获取客户无效的银行账户信息
	 * @param custNo
	 * @return
	 */
	public List<CustBankAcctDto> getInvalidCustBankAcctLst(String custNo) throws ServiceException,AppException;
	
	/**
	 * 新增客户来电信息
	 * @param custCallRegisterDto
	 * @param userProfile
	 * @param loanNo
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void instCallRegister(CustCallRegisterDto custCallRegisterDto,UserProfile userProfile,String loanNo) throws ServiceException,AppException;
}
