package com.hs.loan.cust.api;

import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.cust.dto.AppCustSourceInfoDto;
import com.hs.loan.cust.dto.CustBaseInfoDto;
import com.hs.loan.cust.dto.CustInfoBoDto;
import com.hs.loan.cust.dto.CustInfoDto;


/**
 * APP_客户信息 接口
 * @author autocreate
 * @create 2015-10-26
 */
public interface  CustInfoApi{
	
	/**
	 * 分页查询 客户基本信息
	 * @param page
	 * @return
	 */
	public Page<CustInfoBoDto> queryCustInfo(Page<CustInfoBoDto> page) throws ServiceException,AppException;
	
	/**
	 * 通过客户号，获取客户基本信息（包含户籍信息）
	 * @param custNo
	 * @return
	 */
	public CustInfoDto getByNo(String custNo) throws ServiceException,AppException;
	
	/**
	 * 保存或者更新 客户基本信息
	 * 返回客户编号
	 * @param custInfoDto
	 */
	public String save(CustInfoDto custInfoDto) throws ServiceException,AppException;
	
	/**
	 * 更新 客户基本信息
	 * 返回客户编号
	 * @param custInfoDto
	 */
	public String update(String loanNo,CustInfoDto custInfoDto,UserProfile profile) throws ServiceException,AppException;
	/**
	 * 通过客户号 删除 客户基本信息
	 */
	public void deleteByNo(String custNo) throws ServiceException,AppException;
	
	/**
	 * 更新客户最后一次申请时间为当前时间
	 * @param custNo
	 */
	public void updateLastApplyDate(String custNo) throws ServiceException,AppException;
	
	/**
	 * 通过身份证号获取客户身份信息
	 * @param certNo
	 */
	public CustInfoDto getByCertNo(String certNo) throws ServiceException,AppException;
	
	/**
	 * 通过客户名和身份证号验证客户
	 * 验证成功返回该客户基本信息，否则返回null
	 * 
	 * @param custName
	 * @param certNo
	 * @return
	 */
	public CustBaseInfoDto validCust(String custName,String certNo) throws ServiceException,AppException;
	
	/**
	 * 保存或更新 客户的基本信息,联系信息，居住信息
	 * 若custNo不为空的时候，custInfoDto,custLiveInfoDto中的custNo以传入的custNo参数为准
	 * 
	 * 返回保存后的custNo
	 * 
	 * @param custInfoDto
	 * @param custLiveInfoDto
	 * @return
	 */
	public String saveCustBaseInfo(CustBaseInfoDto dto) throws ServiceException,AppException;
	
	/**
	 * 保存或更新 客户的基本信息,联系信息，居住信息(现金贷专用)
	 * 若custNo不为空的时候，custInfoDto,custLiveInfoDto中的custNo以传入的custNo参数为准
	 * 
	 * 返回保存后的custNo
	 * 
	 * @param custInfoDto
	 * @param custLiveInfoDto
	 * @return
	 */
	public String saveCustBaseInfoNew(CustBaseInfoDto dto) throws ServiceException,AppException;
	
	
	/**
	 * 获取客户信用评分
	 * 
	 * @param custNo
	 */
	public int queryCustScore(String custNo) throws ServiceException,AppException;
	
	/**
	 * 查询客户来源信息
	 * @param custNo
	 */
	public AppCustSourceInfoDto findShourceInfo(String custNo) throws ServiceException, AppException;
	/**
	 * 查询客户结清金额
	 * @param loanNo
	 * @param tranDate
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public Map<String, Object> findCustSettleAmt(String loanNo,String tranDate) throws ServiceException, AppException;
}