package com.hs.loan.finance.api;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.finance.dto.AccLoanAcctInstDgDto;
import com.hs.loan.finance.dto.AccRepayDgRegDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * ACC_还款登记（对公） 接口
 * @author zym
 * @create 2016-02-03
 */
public interface  RepayDgRegApi{
	/**
	 * 变更还款日期
	 * @param paramMap
	 * @param userProfile
	 * @return HashMap<String,Object>
	 * @throws ServiceException
	 * @throws AppException
	 */
	public HashMap<String,Object> changeFlexibleRepaymentDate(HashMap<String,Object> paramMap,UserProfile userProfile)throws ServiceException,AppException;
	/**
	 * 随心还款历史
	 * @param paramMap
	 * @return HashMap<String,Object>
	 */
	public HashMap<String,Object> flexiblePaymentHistory(HashMap<String,Object> paramMap)throws ServiceException,AppException;
	public Page<HashMap<String,Object>> flexibleNotYetDetail(Page<HashMap<String,Object>> page);
	/**
	 * 随心还款
	 * @param page
	 * @param userProfile
	 * @return  Page<HashMap<String,Object>>
	 */
	public Page<HashMap<String,Object>> flexiblePayment(Page<HashMap<String,Object>> page, UserProfile userProfile);
	public int selectCountExcel(HashMap<String,Object> map);
	
	/**
	 * 查询对公还款列表
	 * @param map
	 * @return Page<PubProd>
	 */
	public Page<AccRepayDgRegDto> queryLoanRepayDgLst(Page<AccRepayDgRegDto> map) throws ServiceException,AppException;
	
	
	/**
	 * 查询未还的代扣明细
	 * @param map
	 * @return Page<PubProd>
	 */
	public List<AccLoanAcctInstDgDto> queryAccLoanAcctInstLst(Map<String,Object> map) throws ServiceException,AppException;
	
	
	/**
	 * 加载对公信息
	 * @param singleRepayVO
	 * @return
	 * @throws ServiceException
	 */
	public AccRepayDgRegDto loadLoanRepayDgInfo(AccRepayDgRegDto repayDgDto) throws ServiceException,AppException;
	
	/**
	 * 保存对公信息
	 * @param repayDgVO
	 * @return 
	 * @throws ServiceException
	 */
	public void saveLoanRepayDg(AccRepayDgRegDto repayDgDto,UserProfile userProfile) throws ServiceException,AppException;

	/**
	 * 保存对公信息批量
	 * @param repayDgVO
	 * @return 
	 * @throws ServiceException
	 */
	public void saveLoanRepayDgList(List<AccRepayDgRegDto> repayDgDtoList,UserProfile userProfile) throws ServiceException,AppException;
	
}