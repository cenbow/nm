package com.nm.service.mb;

import com.nm.model.*;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/5/15.
 */
public interface MbQueryApiService {
    CustAllInfoModel getCustInfos(String loanNo, String custNo);
    List<LoanApprBookModel> queryCustLoanApprBook(String loanNo, String custNo);
    CustLoanInfoModel getLoanInfoByLoanNo(String loanNo);
    void deleteCustContctInfo(String id,String custNo);
    CustContctInfoModel getCustOtherContct(String custNo);
    CustStudyInfoModel getStudyByCustNo(String custNo);
    CustWorkInfoModel getWorkByCustNo(String custNo);
    Map<String,Object> queryIsLock(String custNo,String loanNo);
}
