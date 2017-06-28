package com.hs.loan.collection.api;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.collection.dto.LoanOutSourceDetailDto;
import com.hs.loan.collection.dto.LoanOutsourceDto;
import com.hs.loan.collection.dto.LoanOutsourceRelationDto;
import com.hs.loan.collection.dto.LoanOutsourceReturnDto;

/**
 * 委外案件处理
 * @author IT-009
 *
 */
public interface LoanOutSourceApi {
	/**
	 *案件回收
 	 * @param map{loanNo:贷款编号}
	 * @return Map{"result":"success"}(success为处理成功)
	 */
    public Map<String,Object> callBackCase(Map<String,Object> map);

    /**
     * 
 	 * 案件回收到催收
 	 * @param 
 	 * 
 	 */
    public void callCollection(String loanNoList);
     
	/**
	 * 查询销售中心
	 * @return Map{PROV_NO:省的编号,PROV_NAME:省份名称}
	 */
	public List<Map> getRegionalSale();

	/**
	 * 委外对公还款登记录入
	 * @param requestParamMap 请求参数
	 * @param userProfile 用户信息
	 */
	public void saveLoanRepayDg(HashMap<String,Object> requestParamMap,UserProfile userProfile)throws ServiceException,AppException;

	/**
	 * 分页查询委外扣款案件信息列表
	 * @param page
	 * @param profile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public Page<LoanOutsourceDto> queryOutSource(Page<LoanOutsourceDto> page,UserProfile profile) throws ServiceException ,AppException;
	
	/**
	 * 分页查询委外扣款案件信息列表导出
	 * @param page
	 * @param profile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public List<Map<String,Object>> queryOutSourceForExp(Map<String,Object> param,UserProfile profile) throws ServiceException ,AppException;
	
	/**
	 * 委外具体费用项明细
	 * @param loanNo
	 * @param id
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public LoanOutSourceDetailDto queryLoanOutsourceAmount(String loanNo,String id) throws ServiceException ,AppException;
   
	/**
	 * 委外案件分配
	 * @param loanNo
	 * @param staffNo
	 * @param profile
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void distruOutSource(List<HashMap<String,Object>> list,UserProfile profile) throws ServiceException ,AppException;

	/**
	 * 委外代扣
	 * @param loanNo
	 * @param amt
	 * @param profile
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void repayAmt(String loanNo,BigDecimal amt,UserProfile profile,String id,String kdType,String repayNum)  throws ServiceException ,AppException;
	
	/**
	 * 查看委外回收金额明细
	 * @param loanNo
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public Page<LoanOutsourceReturnDto> queryOutSourceRet(Page<LoanOutsourceReturnDto> page)  throws ServiceException ,AppException;
	/**
	 * 查看委外历史信息
	 * @param loanNo
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public Page<LoanOutsourceRelationDto> queryOutSourceHis(Page<LoanOutsourceRelationDto> page)  throws ServiceException ,AppException;
	
	/**
	 * 委外信息查询 /查看
	 * @param page
	 * @param profile
	 * @return
	 */
	public Page<LoanOutsourceDto> queryLoanSource(Page<LoanOutsourceDto> page,UserProfile profile)  throws ServiceException ,AppException;
	/**
	 * 委外案件处理 （调用经办信息）
	 * @param remark
	 * @param profile
	 * @throws ServiceException
	 * @throws AppException
	 **/
    public void detailOutLoanCase(String loanNo,String caseId,String remark,UserProfile profile) throws ServiceException,AppException;
	 
}
