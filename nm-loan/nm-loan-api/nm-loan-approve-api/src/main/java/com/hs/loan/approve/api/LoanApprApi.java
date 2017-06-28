package com.hs.loan.approve.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.approv.dto.AppLoanApprTermDto;
import com.hs.loan.approv.dto.LoanApprDto;
import com.hs.loan.approv.dto.LoanApprInDto;
import com.hs.loan.approv.dto.LoanCustRepeatCheckDto;
import com.hs.loan.approvcheck.dto.AppLoanApprCheckDto;

/**
 * APP_分期审批信息 接口
 * @author autocreate
 * @create 2015-11-23
 */
public interface  LoanApprApi{

	/**
	 * 根据贷款编号查询审批信息列表
	 * (判断上一次状态是否为预通过 null!=getAppLoanAppr&&"40002010".equals(getAppLoanAppr().get(0).getStat()))
	 * @param loanNo
	 * @return List<AppLoanAppr>
	 */
	public List<LoanApprDto> getAppLoanAppr(String loanNo)throws ServiceException, AppException;

	/**
	 * 分期审批查询
	 * @param param
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public Page<LoanApprDto> queryLoanApprInfo(Page<LoanApprDto> param,UserProfile userProfile) throws ServiceException,AppException;
	
	/**
	 * 分期审批
	 * @param inDto
	 * @param userProfile
	 * @throws ServiceException
	 * @throws AppException
	 */
	public String loanAppr(LoanApprInDto inDto,UserProfile userProfile) throws ServiceException,AppException;
	
	/***
	 * @param loanNo
	 * @param profile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public String createContract(String loanNo,UserProfile profile) throws ServiceException,AppException;
	
	/**
	 * 查询重复列表
	 */
	public List<LoanCustRepeatCheckDto> getList(String custNo)  throws ServiceException,AppException;

	/**
	 * 获取审批任务
	 * @param profile 用户信息实体
	 */
	public Page<LoanApprDto> getAppoTask(UserProfile profile) throws ServiceException, AppException ;
	
    /**
     * 查询待审批的案件数量
     * @return
     * @throws ServiceException
     * @throws AppException
     */
    public int getWaitApprCnt(UserProfile profile) throws ServiceException,AppException;
    /**
	 * 查询审批术语信息
	 * 默认随机查询三条数据
	 */
    public List<AppLoanApprTermDto> getApprTermList(String jobtype)  throws ServiceException,AppException;
   /**
    * 新增修改审批术语信息
    */
    public void saveorupdateApprTermDto(AppLoanApprTermDto termdto, UserProfile userProFile)  throws ServiceException,AppException;
   /**
    * 启用停用审批术语信息
    */
    public void updateApprTermStatDto(AppLoanApprTermDto termdto, UserProfile userProFile)  throws ServiceException,AppException;
   /**
    * 分页查询审批术语信息
    */
    public Page<AppLoanApprTermDto> getApprTermPage(Page<AppLoanApprTermDto> page)  throws ServiceException,AppException;
    
    /**
	 * 分期初审
	 * @param inDto
	 * @param userProfile
	 * @return
	 * @throws ServiceException
	 */
	public Map<String, Object> loanApprFstCheck(LoanApprInDto inDto, UserProfile userProfile) throws ServiceException,AppException;
	
	/**
	 * 分期复核
	 * @param inDto
	 * @param userProfile
	 * @return
	 * @throws ServiceException
	 */
	public Map<String,Object> loanApprCheck(AppLoanApprCheckDto appLoanApprCheckDto,LoanApprInDto inDto,UserProfile userProfile) throws ServiceException,AppException;
	
	/**
	 * 获取审批任务
	 * @param profile 用户信息实体
	 */
	public Page<LoanApprDto> getGrabList(UserProfile profile) throws ServiceException;
	
	
	/**
	 * 
	 * 根据贷款编号，查询最后一次调额的金额
	 * @param loanNo
	 * @param userProfile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public BigDecimal queryChageCreditlimitAmt(String loanNo,UserProfile userProfile) throws ServiceException, AppException;
}