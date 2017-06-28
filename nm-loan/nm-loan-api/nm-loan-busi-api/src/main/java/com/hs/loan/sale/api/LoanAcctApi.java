package com.hs.loan.sale.api;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.busi.dto.AppCustRegInfoDto;
import com.hs.loan.busi.dto.AppGrapScoreDto;
import com.hs.loan.busi.dto.AppLoanBankAcctDto;
import com.hs.loan.busi.dto.AppLoanProdDto;
import com.hs.loan.busi.dto.LoanAcctInDto;
import com.hs.loan.busi.dto.LoanAcctOutDto;
import com.hs.loan.busi.dto.LoanBankAcctDto;
import com.hs.loan.busi.dto.LoanBranchDto;
import com.hs.loan.busi.dto.LoanOutHistoryDto;
import com.hs.loan.busi.dto.LoanProdCalcDto;
import com.hs.loan.busi.dto.LoanSalerDto;

/**
 * 分期基本信息 接口
 * @author jqiu
 * @create 2015-10-27
 */
public interface  LoanAcctApi{
	public List<List<HashMap<String,Object>>> getAddressList(String custNo)throws ServiceException,AppException;
	/**
	 * 分期信息新增
	 * @param loanAcctIn 分期基本信息维护对象
	 */
	public String addLoanAcct(LoanAcctInDto loanAcctIn,UserProfile userProfile) throws ServiceException,AppException;

	public Map getPubBranchByLoanNo(Map map);

	/**
	 * 通过分期编号查询分期基本信息
	 * @param loanNo 分期编号
	 * @param userProfile 用户信息（过滤权限）
	 * @return LoanAcctOutDto
	 */
	public LoanAcctOutDto getLoanAcct(String loanNo,UserProfile userProfile) throws ServiceException,AppException;

	
	/**
	 * 通过分期编号查询分期产品信息
	 * @param loanNo 分期编号
	 * @return AppLoanProdDto
	 */
	public AppLoanProdDto getLoanProd(String loanNo) throws ServiceException,AppException;

	
	
	/**
	 * 分期试算
	 * @param loanNo
	 * @param prodNo
	 * @param loanAmt
	 * @param num
	 * @param String 费用已选则的费用项
	 * @return
	 */
	public List<LoanProdCalcDto> loanTryCalc(String prodNo,BigDecimal loanAmt,int num,String othFees) throws ServiceException,AppException;

	/**
	 * 获取所有的分期试算
	 * @param loanNo
	 * @param prodNo
	 * @param loanAmt
	 * @param num
	 * @param String 费用已选则的费用项
	 * @return
	 */
	public List<LoanProdCalcDto> loanTryCalcAll(String prodNo,BigDecimal loanAmt,String othFees) throws ServiceException,AppException;
	
	/**
	 * 保存分期银行卡信息
	 * @param loanNo
	 * @param bankCardNo
	 */
	public  void saveLoanBanKCard(String loanNo,String bankCardNo) throws ServiceException,AppException;
	/**
	 *  获取次分期选中的银行卡
	 * @param loanNo
	 * return bankCardID
	 */
	public LoanBankAcctDto  queryLoanBanKCard(String loanNo) throws ServiceException,AppException;
	/**
	 *  获取次分期销售
	 * @param loanNo
	 * return bankCardID
	 */
	public LoanSalerDto  queryLoanSaler(String loanNo) throws ServiceException,AppException;
	
	/**
	 * 客户银行卡查询分期信息
	 */
	public List<AppLoanBankAcctDto> queryAppLoanBank(String bankCardId) throws ServiceException,AppException;
	/**
	 *  提价分期信息
	 */
	public void sumitLoan(String loanNo,String fileNo,String loanRemark,UserProfile userProfile) throws ServiceException,AppException;
	
	
	/**
	 * 新增分期渠道信息
	 */
	public void saveLoanFundChanl(String loanNo,String chanlNo,String chanName) throws ServiceException,AppException;
	
	/**
	 * 修改分期审批状态
	 */
	
	public void updateLoanByLoanNo(Map<String,Object> map)  throws ServiceException,AppException;
	
	/**
	 * 通过分期编号获取分期办理网店
	 */
	public LoanBranchDto queryLoanBranchByLoanNo(String loanNo)  throws ServiceException,AppException;
	
	
	/**
	 * 根据身份证号码查询办单历史
	 * 
	 * @param page
	 * @return List
	 */
	public List<LoanOutHistoryDto> queryLoanHistoryList(String certNo) throws ServiceException,AppException;
	
	/**
	 * 销售直通车修改贷款状态
	 * 
	 * @param page
	 * @return List
	 */
	public void updateSpacailPrl(String loanNo,UserProfile profile) throws ServiceException,AppException;
	/**
	 * 判断挂单商户是否是平台已失效的商户
	 * @param branchName
	 * @return true 是已经失效的  false 不为已失效的
	 * @throws ServiceException
	 * @throws AppException
	 */
	public List<Map<String, Object>> judgeBranchStatusByFaild(String branchName)throws ServiceException,AppException;
	/**
	 * 根据分期编号查询参与结算的费用项
	 * @param loanNo
	 * @throws ServiceException
	 * @throws AppException
	 */
	public List<String> queryFeesByLoanNo(String loanNo)  throws ServiceException, AppException;
	/**
	 * 新增客户芝麻分
	 * @param appGrapScoreDto
	 * @return 
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void saveGrapScore(AppGrapScoreDto appGrapScoreDto) throws ServiceException, AppException;
	/**
	 * 根据CustNo获取芝麻分
	 * @param custNo
	 * @param loanNo
	 * @param grapChan
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public AppGrapScoreDto getAppGrapScoreByCustNoAndGrapChan(String custNo,String loanNo,String grapChan) throws ServiceException, AppException;
	/**
	 * 根据loanNo或custNo获取分数
	 * @param custNo
	 * @param loanNo
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public List<AppGrapScoreDto> getAppGrapScoreByLoanNoOrCustNo(String custNo,String loanNo)throws ServiceException, AppException;
	/**
	 * 获取客户定位信息
	 * @param custNo
	 * @return
	 */
	public List<AppCustRegInfoDto> getAppCustRegInfoList(Map<String, Object> param )throws ServiceException, AppException ;
	/**
	 * 获取通话记录
	 * @param custNo
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public  List<HashMap<String,Object>>  getCallList(String custNo)throws ServiceException,AppException;
}