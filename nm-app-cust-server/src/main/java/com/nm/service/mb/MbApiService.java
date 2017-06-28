package com.nm.service.mb;

import com.hs.loan.busi.dto.LoanAcctInDto;
import com.hs.loan.busi.dto.LoanProdCalcDto;
import com.nm.api.frame.auth.model.AppCustInfo;
import com.nm.cmd.AddCustBaseCmd;
import com.nm.cmd.CustOtherContctInfoCmd;
import com.nm.cmd.CustStudyInfoCmd;
import com.nm.cmd.CustWorkInfoCmd;
import com.nm.model.CustOtherContctInfoModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author hanfei
 * @describe 医美service interface
 */
public interface MbApiService {
    Map<String, Object> getOrgAndProInfosByOrgNo(String staffNo,String orgNo, String soucre, String custType);

    List<LoanProdCalcDto> getLoanTrial(String prodNo, BigDecimal loanAmt, int instNum, String othFees);

    Map<String, Object> addCustBaseInfo(AddCustBaseCmd addCustBaseCmd, String certNo);

    List<AppCustInfo> getAppCustInfo(String certNo);

    void addCustStudyInfo(CustStudyInfoCmd custStudyInfoCmd);

    void addCustWorkInfo(CustWorkInfoCmd custWorkInfoCmd);

    List<CustOtherContctInfoModel> addCustContctInfo(List<CustOtherContctInfoCmd> custOtherContctInfoCmdList);

    void addLoanFileNoInfo(String loanNo, AppCustInfo appCustInfo,String applyAddr,String custNo);

    AppCustInfo getAppCustInfoByLoanNo(String loanNo);

    LoanAcctInDto getLoanNoByCustNo(String custNo);

    String getCertNoByMobileNo(String mobileNo);
}
