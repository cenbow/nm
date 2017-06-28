package com.hs.loan.sale.api;

import java.util.List;

import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.busi.dto.LoanAttInDto;

/**
 * APP_分期附件表 接口
 * @author autocreate
 * @create 2015-11-11
 */
public interface  LoanAttApi{

	/**
	 *新增分期附件信息 
	 * @param inDto
	 * @param profile
	 * @return
	 */
	public void saveLoanAttInfo(LoanAttInDto inDto,UserProfile profile) throws ServiceException,AppException;


	/**
	 * 删除附件信息
	 * @param id
	 */
	public void deleteLoanAtt(String loanNo,String attType) throws ServiceException,AppException;

	/**
	 * 查询分期信息列表
	 * @param loanNo
	 * @return
	 */
	public List<LoanAttInDto> queryLoanAttInfo(String loanNo) throws ServiceException,AppException;
	/**
	 * 查询分期信息列表
	 * @param loanNo
	 * @return
	 */
	public LoanAttInDto queryLoanAtt(String loanNo,String attType) throws ServiceException,AppException;

	/**
	 * 根据附件码类中的码组查询分期附件信息列表
	 * @param loanNo
	 * @param codTyp 码类
	 * @param groupCodStrs 码组字符串数组
	 * @return
	 */
	public List<LoanAttInDto> queryLoanAttByGroupCod(String loanNo, String codTyp, String groupCodStrs)  throws ServiceException,AppException;
	
	
	/**
	 * 根据附件码类中的码组查询分期附件信息列表
	 * @param loanNo
	 * @param codTyp 码类
	 * @param groupCodStrs 码组字符串数组
	 * @return
	 */
	public List<LoanAttInDto> queryLoanAttByGroupCod(String loanNo, String groupCodStrs)  throws ServiceException,AppException;
	
	
	/**
	 * 查询过滤附件码类中的码组查询分期附件信息列表
	 * @param loanNo
	 * @param codTyp 码类
	 * @param groupCodStrs 码组字符串数组
	 * @return
	 */
	public List<LoanAttInDto> queryLoanAttByGroupCodFilter(String loanNo, String codTyp, String groupCodStrs)  throws ServiceException,AppException;
	
	
	/**
	 * 查询过滤附件码类中的码组查询分期附件信息列表
	 * @param loanNo
	 * @param codTyp 码类
	 * @param groupCodStrs 码组字符串数组
	 * @return
	 */
	public List<LoanAttInDto> queryLoanAttByGroupCodFilter(String loanNo, String groupCodStrs)  throws ServiceException,AppException;

}