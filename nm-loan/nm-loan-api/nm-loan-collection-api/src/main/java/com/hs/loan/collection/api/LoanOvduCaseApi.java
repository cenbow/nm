package com.hs.loan.collection.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.collection.dto.AppLoanCustInfoDto;
import com.hs.loan.collection.dto.LoanOvduCaseDto;
import com.hs.loan.collection.dto.LoanOvduCaseOutDto;
import com.hs.loan.collection.dto.LoanOveDualCaseDto;
import com.hs.loan.collection.dto.LoanOverdueDetailDto;
import com.hs.loan.collection.dto.LoanOverduePeriodDetileDto;
import com.hs.loan.collection.dto.PlLoanOvduCaseRetAndFlowDto;
import com.hs.loan.collection.dto.PlLoanOvduHandDto;
import com.hs.loan.collection.dto.PubMessageModelDto;

/**
 * 预期案件处理
 * 
 * @author IT-009
 *
 */
public interface LoanOvduCaseApi {
	/**
	 * 根据主键获取逾期案件信息
	 * 
	 * @param primaryKey
	 *            逾期案件id(主键)
	 * @return LoanOvduCaseOutDto pl_逾期案件对象dto
	 */
	public LoanOvduCaseOutDto getByPrimaryKey(String primaryKey);

	/**
	 * 查询预期案件列表分页查询(案件处理)
	 * 
	 * @param page
	 * @param profile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public Page<LoanOvduCaseDto> queryLoanOvduCase(Page<LoanOvduCaseDto> page, UserProfile profile)
			throws ServiceException, AppException;

	/**
	 * 查询预期案件详细信息-包含费用项,减免信息,预期信息
	 * 
	 * @param page
	 * @param profile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public LoanOverdueDetailDto queryLoanOvduCaseDetail(String loanNo, String caseId, UserProfile profile)
			throws ServiceException, AppException;

	/**
	 * 案件处理 （调用经办信息）
	 * 
	 * @param remark
	 * @param profile
	 * @throws ServiceException
	 * @throws AppException
	 **/
	public void detailLoanCase(Map<String, Object> param, UserProfile profile) throws ServiceException, AppException;

	/**
	 * 案件重新分配
	 * 
	 * @param caseId
	 * @param staffNo
	 * @param profile
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void distrbOveCse(List<String> caseIdList, String staffNo, String staffName, UserProfile profile)
			throws ServiceException, AppException;

	/**
	 * 案件标记为委外
	 * 
	 * @param caseId
	 * @param profile
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void remarkOutSource(String loanNo, String caseId, UserProfile profile)
			throws ServiceException, AppException;
	/**
	 * 案件标记为委外批量
	 * 
	 * @param caseId
	 * @param profile
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void remarkOutSourceList(List<Map<String, Object>> list, UserProfile profile)
			throws ServiceException, AppException;
	/**
	 * 取消案件标记为委外
	 * 
	 * @param caseId
	 * @param profile
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void cancleRemarkOutSource(String caseId, UserProfile profile) throws ServiceException, AppException;
	/**
	 * 取消案件标记为委外批量
	 * 
	 * @param caseId
	 * @param profile
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void cancleRemarkOutSourceList(List<Map<String, Object>> list, UserProfile profile) throws ServiceException, AppException;
	/**
	 * 逾期扣款
	 * 
	 * @param caseId
	 * @param loanNo
	 * @param type
	 * @param amt
	 * @param profile
	 */
	public void repayAmt(String caseId, String loanNo, String type, BigDecimal amt, UserProfile profile)
			throws ServiceException, AppException;

	/**
	 * 根据案件逾期期数信息
	 * 
	 * @param param
	 *            逾期案件编号/开始期数/结束期数
	 * @return LoanOvduCaseOutDto pl_逾期案件对象dto
	 */
	public List<LoanOverduePeriodDetileDto> getOverduePeriodList(Map<String, Object> param);

	/**
	 * 根据案件逾期期数信息
	 * 
	 * @param param
	 *            逾期案件编号/开始期数/结束期数
	 * @return LoanOvduCaseOutDto pl_逾期案件对象dto
	 */
	public LoanOveDualCaseDto getOverduePeriodInfo(Map<String, Object> param);

	/**
	 * 查询实时代扣金额
	 * 
	 * @param param
	 *            dvalRepayTyp 扣款类型 loanNo 分期编号 bgRepayNum 开始逾期期数 enRepayNum
	 *            结束逾期期数
	 * @return
	 * @throws Exception
	 */
	public String getOvduCaseDebit(Map<String, Object> param) throws ServiceException, AppException;;

	/**
	 * 代扣金额
	 * 
	 * @param param:
	 *            loanNo,caseId,transAmtTotal,dvalRepayTyp,bgRepayNum,enRepayNum
	 * @param userProFile
	 * @throws Exception
	 */
	public void ovduCaseRepayAmt(Map<String, Object> param, UserProfile userProFile)
			throws ServiceException, AppException;

	/**
	 * 查询预期案件列表分页查询（案件分配）
	 * 
	 * @param page
	 * @param profile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public Page<LoanOvduCaseDto> queryLoanOvduCaseAllot(Page<LoanOvduCaseDto> page, UserProfile profile)
			throws ServiceException, AppException;

	/**
	 * 查询预期案件列表分页查询（案件分配导出）
	 * 
	 * @param page
	 * @param profile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public List<Map<String, Object>> queryLoanOvduCaseAllotForList(Map<String, Object> param, UserProfile userProFile)
			throws ServiceException;

	/**
	 * 获取客户联系人列表
	 * 
	 * @param param
	 *            客户编号：custNo
	 * @return
	 */
	public List<AppLoanCustInfoDto> queryLoanCustInfoForList(Map<String, Object> param);

	/**
	 * 获取逾期案件结果登记信息
	 * 
	 * @param param
	 * @return
	 */
	public List<PlLoanOvduHandDto> queryLoanOvduHandForList(Map<String, Object> param);

	/**
	 * 经理查询对应的还款信息
	 * 
	 * @param page
	 * @param profile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public Page<PlLoanOvduCaseRetAndFlowDto> queryLoanOvduCaseFlowPage(Page<PlLoanOvduCaseRetAndFlowDto> page,
			UserProfile profile) throws ServiceException, AppException;

	/**
	 * 经理查询对应的还款信息导出
	 * 
	 * @param page
	 * @param profile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public List<Map<String, Object>> queryLoanOvduCaseFlowList(Map<String, Object> param, UserProfile userProFile)
			throws ServiceException;

	/**
	 * 短信发送
	 * 
	 * @param param
	 *            msg,mob,flag(取MgBz)
	 * @throws ServiceException
	 */
	public void sendMsg(Map<String, Object> param) throws ServiceException;

	/**
	 * 短信模版获取
	 * 
	 * @param param
	 *            caseId
	 * @return
	 * @throws ServiceException
	 */
	public List<PubMessageModelDto> getMsg(Map<String, Object> param) throws ServiceException;

	/**
	 * 手动获取回盘结果
	 * 
	 * @param loanNo
	 */
	public void updateInstStat(String loanNo) throws ServiceException, AppException ;
}
