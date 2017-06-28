package com.hs.loan.sale.api;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.busi.dto.AppLoanCallHandDto;
import com.hs.loan.busi.dto.CrmCustomerOrderDto;
import com.hs.loan.busi.dto.HandFundMatchDto;
import com.hs.loan.busi.dto.LoanListOutDto;
import com.hs.loan.busi.dto.LoanOtherInfoDto;
import com.hs.loan.busi.dto.LoanViewDto;
import com.hs.loan.finance.dto.AccLoanInstDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分期视图 接口
 * @author jqiu
 * @create 2015-10-27
 */
public interface  LoanViewApi{

	/**
	 *@describe 新销售系统(查询还款计划)
	 *@author txia
	 *datetime 2016/8/25 10:09
	 *params{loanNo:贷款编号,certNo:身份证号,custName:客户名称,phoneNo:电话号码}
	 *return  HashMap<String,Object>{parentList,childrenList}
	 */
	public HashMap<String,Object> billQueryForList(java.util.Map map);

	/**
	 * 获取产品渠道
	 * @param map{loanNo:贷款编号}
	 * @return Map{SALE_CHAN:产品销售渠道}
	 */
	public Map getLoanProd(java.util.Map map)throws ServiceException, AppException;
	public List<HashMap<String, Object>> attDownLoad(String loanNo)throws ServiceException, AppException;
	/**
	 * 批量资金匹配
	 * @param list
	 * @param userProfile
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void handFundMatch(List<HandFundMatchDto> list,UserProfile userProfile)throws ServiceException, AppException;
	
	/**
	 * 判断IMEI是否存在
	 * @param imei
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void checkImeiCode(String imei,String loanNo)throws ServiceException, AppException;
	
	/**
	 * 查询该imei通过贷款编号
	 * @param loanNo
	 * @return String
	 */
	public String selectImeiByLoanNo(String loanNo)throws ServiceException, AppException;

	/**
	 * 根据贷款编号更新imei
	 * @param loanNo
	 * @return int
	 */
	public int updateImeiByLoanNo(String loanNo,String imei) throws ServiceException, AppException;


	/**
	 * 查询销售中的产品
	 * @return List<Map>
	 */
	public List<Map> getPubProd();

	/**
	 * 查询分期信息列表
	 * @param page
	 *      page.params 
	 * 		参数(custName,certNo,acctNo,salerName,areMangerName,orgNo,
	 * 			branchName,branchProv,branchCity,branchArea,
	 * 			applyDate[applyDateMin applyDateMax],regDate[regDateMin regDateMax],distrDate[distrDateMin distrDateMax],stat,aprvDate[审批时间 aprvDateMin aprvDateMax])
	 * @param userProfile 用户信息（过滤权限）
	 * @return List
	 */
	public Page<LoanListOutDto> queryLoanList(Page<LoanListOutDto> page,UserProfile userProfile) throws ServiceException,AppException;
	
	/**
	 * 通过客户编号查询分期信息列表
	 * @param custNo 客户编号
	 * @param userProfile 用户信息（过滤权限）
	 * @return List<LoanListOutDto>
	 */
	public List<LoanListOutDto> getLoanAcctByCustNo(String custNo,UserProfile userProfile) throws ServiceException,AppException;
	
	/**
	 * 获取分期视图
	 * @param loanNo 	      分期编号
	 * @param userProfile 用户信息（过滤权限）
	 * @return
	 */
	public LoanViewDto getLoanView(String loanNo,UserProfile userProfile) throws ServiceException,AppException;
	/**
	 * 获取分期视图 --分流 
	 * @param type 分流标志
	 * @param loanNo 	      分期编号
	 * @param userProfile 用户信息（过滤权限）
	 * @return
	 */
	public LoanViewDto getLoanViewStp(String loanNo,UserProfile userProfile,String type) throws ServiceException,AppException;
	
	/**
	 * 获取分期其他信息---分期审批使用
	 * @param loanNo 	      分期编号
	 * @param userProfile 用户信息（过滤权限）
	 * @return
	 */
	public LoanOtherInfoDto queryLoanOtherInfo(String loanNo,UserProfile userProfile) throws ServiceException,AppException;
	

	/**
	 * 分期签约
	 * @param loanLst
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public String registerLoan(List<LoanListOutDto> loanLst,UserProfile userProfile) throws ServiceException,AppException;
	
	/**
	 * 分期撤消
	 * @param loanNo
	 * @param handDetailTyp
	 * @param userProfile
	 * @param remark 撤消原因
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public String revokeLoan(String loanNo,UserProfile userProfile,String remark) throws ServiceException, AppException;
	
	/**
	 * 分期取消
	 * @param loadNo
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public String cancelLoan(String loanNo,UserProfile userProfile) throws ServiceException, AppException;
	
	
	/**
	 * 是否放款
	 * @param loadNo
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void modifyLoan(String loanNoList,String loanState, UserProfile userProfile) throws ServiceException, AppException;
	
	
	/**
	 * 查询客户的还款计划
	 * @param param
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public List<AccLoanInstDto> queryForList (Map<String,Object> param) throws ServiceException, AppException;
	
	/**
	 * 查询客服系统工单列表
	 * @param page
	 * @param userProfile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public Page<CrmCustomerOrderDto> queryCrmOrderList(Page<CrmCustomerOrderDto> page,UserProfile userProfile)throws ServiceException, AppException;
	/**
	 * 更新工单信息(分配，退回，提交结果，撤销)
	 * @param param
	 * @param userProfile
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void updateCrmOrder(Map<String, Object> param,UserProfile userProfile)throws ServiceException, AppException;
	/**
	 * 查询工单信息
	 * @param param
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public CrmCustomerOrderDto queryCrmOrder(Map<String, Object> param)throws ServiceException, AppException;
	/**
	 * 办单提交基本信息时更新工单中loanNo
	 * @param param
	 * @param userProfile
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void updateCrmOrderOnLoanNo(Map<String, Object> param,UserProfile userProfile)throws ServiceException, AppException;
	/**
	 * 批量分配,撤销,提交
	 * @param listParam
	 * @param userProfile
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void updateCrmOrderBatch(List<Map<String, Object>> listParam,String handType,UserProfile userProfile)throws ServiceException, AppException;
	/**
	 * 查询区域经理下的销售
	 * @param userProfile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public List<Map<String, String>> queryMgrStaff(UserProfile userProfile)throws ServiceException, AppException;
	/**
	 * 新增工单经办信息
	 * @param appLoanCallHandDto
	 */
	public void addCallHand(AppLoanCallHandDto appLoanCallHandDto ,UserProfile userProfile);
	/**
	 * 查询工单经办信息
	 * @param orderId
	 * @return
	 */
	public List<AppLoanCallHandDto> findCallHandList(String orderId,UserProfile userProfile);
}