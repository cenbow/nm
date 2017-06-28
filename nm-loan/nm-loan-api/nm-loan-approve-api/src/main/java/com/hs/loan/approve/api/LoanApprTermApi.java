package com.hs.loan.approve.api;

import java.util.List;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.approv.dto.AppLoanApprTermDto;

/**
 * APP_审批术语表 接口
 * @author autocreate
 * @create 2016-04-11
 */
public interface  LoanApprTermApi{
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
}