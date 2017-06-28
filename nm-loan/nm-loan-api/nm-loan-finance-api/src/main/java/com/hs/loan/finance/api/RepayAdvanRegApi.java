package com.hs.loan.finance.api;

import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.finance.dto.AccLoanPlanDto;
import com.hs.loan.finance.dto.AccRepayAdvanRegDto;
import com.hs.loan.finance.dto.AccRepayDgRegDto;
/**
 * ACC_还款登记（提前结清） 接口
 * @author autocreate
 * @create 2016-02-03
 */
public interface  RepayAdvanRegApi{


	/**
	 * 查询提前结清列表
	 * @param map
	 * @return Page<PubProd>
	 */
	public Page<AccRepayAdvanRegDto> queryRepayAdvanRegDto(Page<AccRepayAdvanRegDto> map) throws ServiceException,AppException;

	/**
	 * 计算提前结清金额
	 */
	
	public AccLoanPlanDto comRepay(Map<String,Object> map)throws ServiceException,AppException;
	
	/**
	 * 保存提前结清 (对公/批量代扣 )
	 * @param repayDgVO
	 * @return 
	 * @throws ServiceException
	 */
	public void saveLoanRepayDg(AccRepayDgRegDto repayDgDto,UserProfile userProfile) throws ServiceException,AppException;
	
	/**
	 * 短信发送
	 * 
	 * @param param loanNO
	 * @throws ServiceException
	 */
	public void sendMsg(Map<String, Object> param) throws ServiceException;
	
}