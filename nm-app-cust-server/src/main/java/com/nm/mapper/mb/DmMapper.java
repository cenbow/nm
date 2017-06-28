package com.nm.mapper.mb;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hs.loan.busi.dto.LoanAcctInDto;
import com.nm.cmd.PubProd;
import com.nm.cmd.ReferrerInfoCmd;
import com.nm.cmd.WeichatOrderId;
import com.nm.model.InFirmaryInfo;
import com.nm.model.InstNumInfoModel;
import com.nm.model.SalerInfo;
import com.nm.mybatis.annotation.MyBatisRepository;

@MyBatisRepository
public interface DmMapper {

	void insertReferrerInfo(ReferrerInfoCmd referrerInfoCmd);

	void submitOrderIdInfo(WeichatOrderId weichatorderid);

	void deleteDmOrderInfo(String loanNo);

	List<PubProd> queryProdLisForLoanCal(Map<String, Object> map);

	WeichatOrderId querySucceed(Map<String, Object> map);

	List<InstNumInfoModel> queryProdFeeinStNums(String prodNo);

	List<InFirmaryInfo> queryInFirmaryInfo(String salerNo);

	SalerInfo querySaler(String salerNo);

	List<PubProd> salerqueryProdForLoan(Map<String, Object> map);

	Integer updateAppUserInfo(Map<String, Object> map);

	Map<String,Object> getAppCustInfoByLoanNo(String loanNo);

	LoanAcctInDto getLoanNoByCustNo(String custNo);

	Map<String,Object> getApplyAddr(@Param("custNo") String custNo);

	void updateApplyAddr(@Param("id") String id, @Param("applyAddr") String applyAddr);

	void insertApplyAddr(@Param("id") String id,@Param("custNo") String custNo, @Param("applyAddr") String applyAddr);

	Map<String,String> getBranchNameByLoanNo(@Param("loanNo")String loanNo);

	Integer getStudyByCustNo(@Param("custNo")String custNo);

	Integer getWorkByCustNo(@Param("custNo")String custNo);

	Integer getOtherContctByCustNo(@Param("custNo")String custNo);

	Integer getDianShang(Map<String, Object> map);

	String getCertNoByMobileNo(@Param("moblNo")String moblNo);

	Map<String,Object> getStaffNo(@Param("staffNo")String staffNo);

	String getCustType(@Param("custNo")String custNo);
}
