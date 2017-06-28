package com.hs.loan.sale.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.sale.bo.HandFundMatchBo;
import com.hs.loan.sale.bo.LoanListHistoryOutBo;
import com.hs.loan.sale.bo.LoanListOutBo;
import com.hs.loan.sale.entity.AppLoanAcct;
import com.hs.loan.sale.entity.SysPrivInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * APP_分期基本信息 mapper
 * @author autocreate
 * @create 2015-10-26
 */
@MyBatisRepository
public interface  AppLoanAcctMapper extends BaseMapper<AppLoanAcct>{
	public String existsAccDownPaymentInfo(String loanNo);
	public Integer insertAccDownPaymentInfo(Map map);
	public Map<String,Object> getContractCustPhone(java.util.Map map);

	/**
	 *@describe 根据贷款编号查询附件信息(附件的类型)
	 *@author txia
	 *@Date 2016/8/3 14:26
	 *@param {loanNo:贷款编号}
	 *@return List<Map>
	 */
	public List<Map> getAttachmentType(java.util.Map map);
	public Map getFeeAmtByProd(java.util.Map map);
	public List<HashMap<String,Object>> getCustInfo(java.util.Map map);
	public List<HashMap<String,Object>> getAddressList(java.util.Map map);
	public HashMap<String,Object> getAcct(java.util.Map map);
	public HashMap<String,Object> getLoanAcctByLoanNo(java.util.Map map);
	/**
	 * 随心还款历史
	 * @param map
	 * @return
	 */
	public HashMap<String,Object> flexiblePaymentHistory(java.util.Map map);
	/**
	 * 随心还款查询
	 * @param map
	 * @return
	 */
	public List<HashMap<String,Object>> flexiblePayment(java.util.Map map);
	/**
	 * 插入匹配资金日志表
	 * @param map
	 * @return int
	 */
	public int insertFundMatchLog(HashMap<String,Object> map);
	/**
	 * 更新渠道号
	 * @param HandFundMatchBo
	 * @return int
	 */
	public int updateChanNoByLoanNo(HandFundMatchBo b);
	public int insertFundMatch(HandFundMatchBo b);
	/**
	 * HashMap
	 * @param loanNo
	 * @return HashMap
	 */
	public HashMap<String,Object> selectCustByNo(String loanNo);
	/**
	 * 根据贷款编号变更匹配结果为匹配变更
	 * @param loan
	 * @return int
	 */
	public int updateMatchResultByLoan(String loan);
	/**
	 * 查询该贷款编号是否已经资方匹配过
	 * @param loanNo
	 * @return int
	 */
	public int selectCountByLoanNo(String loanNo);
	public int queryLoanList2Count();
	public List<LoanListOutBo> queryLoanList2(HashMap<String, Object> param);

	public List<Map> getPubProd(Map map);

	/**
	 * 查询分期列表
	 * @param param 参数
	 * @return List<LoanListOutBo>
	 */
	public List<LoanListOutBo> queryLoanList(Map<String, Object> param);
	
	/**
	 * 查询分期第二类列表
	 * @param param 参数
	 * @return List<LoanListOutBo>
	 */
	public List<LoanListOutBo> queryLoanListTwo(Map<String, Object> param);
	
	/**
	 * 查询分期列表
	 * @param param 参数
	 * @return List<LoanListOutBo>
	 */
	public List<AppLoanAcct> queryLoan(Map<String, Object> param);
	
	/**
	 * 查询分期列表
	 * @param param 参数
	 * @return List<LoanOutHistoryBo>
	 */
	public List<LoanListHistoryOutBo> queryLoanHistoryList(String certNo);

	/**
	 * 查询时间段内申请的客户号
	 * @param bt
	 * @param ed
	 * @return
	 */
	public List<String> queryLoanCustNo(Map<String,String> map);
	/**
	 * 查询指定用户的指定权限
	 */
	public Set<SysPrivInfo> appointInfo(String staffNo);
	/**
	 * 根据机构ID查询本级及下级机构ID
	 */
	public List<String> belongToOrgNo(String orgNo);

	public void loanFundMatch(Map<String, Object> map);
	
	/**
	 * 批量更新
	 * @param list
	 * @return
	 */
	public int batchModifyByLoanNoLst(Map<String,Object> map);
	public Map<String, Object> findCustSettleAmt(Map<String, Object> map);
	public List<HashMap<String, Object>> getCallList(Map<String, Object> param);
}