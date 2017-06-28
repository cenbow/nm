package com.hs.loan.sale.api;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.busi.dto.LoanHandOutDto;

/**
 * APP_分期经办登记查询 接口
 * @author autocreate
 * @create 2015-10-26
 */
public interface  LoanHandApi{
	
	/**
	 * 手动分期经办登记新增（用于前台人员手动添加）
	 * @param loanNo	分期编号
	 * @param handDetailTyp	经办类型（码值）
	 * @param handPerson	经办人
	 * @param handDate		经办时间
	 * @param remark		说明
	 */
	public void addHandInfoManual(LoanHandOutDto handOutDto) throws ServiceException,AppException;
	
	/**
	 * 自动分期经办登记新增（用于后台功能调用）
	 * @param loanNo	分期编号
	 * @param handDetailTyp	经办类型（码值）
	 * @param handPerson	经办人
	 * @param remark		说明
	 */
	public void addHandInfoAuto(String loanNo,String handDetailTyp,String handPersonNo,String handPersonName,String remark) throws ServiceException,AppException;
	
	/**
	 * 手动分期经办登记删除（只能删除手动登记的）
	 * @param handId	记录id
	 * @param userId	操作人用户ID
	 */
	@Transactional
	public void deleteHandInfo(String handId,String userId) throws ServiceException,AppException;
	
	/**
	 * 通过分期编号查询经办信息
	 * @param loanNo	分期编号
	 */
	public Page<LoanHandOutDto> queryHandInfoByLoanNo(Page<LoanHandOutDto> page,String loanNo,UserProfile userProfile) throws ServiceException,AppException;
	
	/**
	 * 通过客户编号查询经办信息
	 * @param 时间段	 
	 */
	public Page<LoanHandOutDto> queryHandInfoByDate(Page<LoanHandOutDto> page,UserProfile userProfile) throws ServiceException,AppException;
	
	/**
	 *  根据贷款编号查询经办信息
	 */
	public LoanHandOutDto queryHandInfoByLoanNo(Map<String,Object> param) throws ServiceException,AppException;
	
	
	
	
}