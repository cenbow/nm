package com.hs.loan.cust.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.cust.dto.CustOtherLoanDto;


/**
 * APP_客户其他分期信息 接口
 * @author autocreate
 * @create 2015-10-26
 */
public interface  CustOtherLoanApi{

	/**
	 * 通过客户号 获取 客户其他分期信息
	 * @param custNo
	 * @return
	 */
	public List<CustOtherLoanDto> getListByNo(String custNo) throws ServiceException,AppException;
	
	/**
	 * 保存或者更新 客户其他分期信息
	 * 
	 * @param custNo
	 * @param loanLst
	 */
	public void save(String custNo, CustOtherLoanDto... loanLst) throws ServiceException,AppException;
	
	/**
	 * 删除
	 * 
	 * @param custNo
	 * @param ids
	 */
	public void delete(String custNo,String... ids) throws ServiceException,AppException;
	
	/**
	 * 通过id获取 客户其他分期信息
	 * @param id
	 */
	public CustOtherLoanDto getById(String id) throws ServiceException,AppException;
	
	/**
	 * 获取 客户其他分期信息 list
	 * 
	 * @param param
	 * @return
	 */
	public List<CustOtherLoanDto> getList(Map<String,Object> param) throws ServiceException,AppException;
	
	/**
	 * 获取有效时间段的有效的 客户其他分期信息
	 * @param custNo
	 * @param availableDate
	 * @return
	 */
	public List<CustOtherLoanDto> getCustOtherLoanLstByDate(String custNo,Date availableDate) throws ServiceException,AppException;
	
	/**
	 * 获取当前 有效的 客户其他分期信息
	 * 
	 * @param custNo
	 * @return
	 */
	public List<CustOtherLoanDto> getCrtCustOtherLoanLst(String custNo) throws ServiceException,AppException;
	
	/**
	 * 获取刚刚编辑的其他分期信息
	 * 
	 * @return
	 */
	public CustOtherLoanDto getEditedOtherLoan(CustOtherLoanDto custOtherLoanDto) throws ServiceException,AppException;
	
}