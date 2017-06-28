package com.hs.loan.sale.api;

import java.util.List;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.ServiceException;
import com.hs.loan.busi.dto.AppLoanSpcailpriBranchDto;
import com.hs.loan.busi.dto.AppLoanSpcailpriInfoDto;
import com.hs.loan.busi.dto.AppLoanSpcailpriSalerDto;

public interface SalerAuthorApi 
{
	
	/**
	 * 商户直通车权限查询
	 * @param param
	 * @return
	 * @throws ServiceException
	 */
	public Page<AppLoanSpcailpriBranchDto> queryBranchAuthorList(Page<AppLoanSpcailpriBranchDto> param,UserProfile profile) throws ServiceException;
	
	/**
	 * 商户直通车权限新增
	 * @param inDto
	 * @param profile
	 * @throws ServiceException
	 */
	public void addBranchAuthor(List<AppLoanSpcailpriBranchDto> list, UserProfile profile) throws ServiceException;
	
	/**
	 * 商户直通车权限删除
	 * @param id
	 * @param profile
	 * @throws ServiceException
	 */
	public void  delBranchAuthor(String branchNo, UserProfile profile) throws ServiceException;
	
	/**
	 * 销售直通车权限查询
	 * @param param
	 * @return
	 * @throws ServiceException
	 */
	public Page<AppLoanSpcailpriSalerDto> querySaleAuthorLst(Page<AppLoanSpcailpriSalerDto> param,UserProfile profile) throws ServiceException;
	
	/**
	 * 销售直通车权限新增
	 * @param inDto
	 * @param profile
	 * @throws ServiceException
	 */
	public void  addSaleAuthor(List<AppLoanSpcailpriSalerDto> list,UserProfile profile) throws ServiceException;
	/**
	 * 销售直通车权限删除
	 * @param id
	 * @param profile
	 * @throws ServiceException
	 */
	public void  delSaleAuthor(String staffNo,UserProfile profile) throws ServiceException;
	
	/**
	 * 销售申请、复核直通车权限查询
	 * @param param
	 * @return
	 * @throws ServiceException
	 */
	public Page<AppLoanSpcailpriInfoDto> queryLoanSpcailpriInfoLst(Page<AppLoanSpcailpriInfoDto> param,UserProfile profile) throws ServiceException;
	
	/**
	 * 销售申请、复核直通车权限新增
	 * @param param
	 * @return
	 * @throws ServiceException
	 */
	public void  addLoanSpcailpriInfo(AppLoanSpcailpriInfoDto inDto,UserProfile profile) throws ServiceException;
	
	/**
	 * 销售申请、复核直通车权限修改
	 * @param param
	 * @return
	 * @throws ServiceException
	 */
	public void  updateLoanSpcailpriInfo(AppLoanSpcailpriInfoDto inDto,UserProfile profile) throws ServiceException;
	
	/**
	 * 
	 * 不在商户直通车的网点查询
	 * 
	 */
	public Page<AppLoanSpcailpriBranchDto> queryNotSpcailpriBranchForPage(Page<AppLoanSpcailpriBranchDto> page, UserProfile userProfile);
		
	
	/**
	 * 
	 * 查询不在商户直通车的销售
	 * 
	 */
    public Page<AppLoanSpcailpriSalerDto> queryNotSpcailpriSalerForPage(Page<AppLoanSpcailpriSalerDto> page, UserProfile userProfile);
}
