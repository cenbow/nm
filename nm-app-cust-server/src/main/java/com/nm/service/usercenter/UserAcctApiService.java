package com.nm.service.usercenter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hs.base.entity.UserProfile;
import com.hs.loan.busi.dto.LoanAttInDto;
import com.nm.api.frame.auth.model.AppCustInfo;
import com.nm.cmd.OCLoanAttInDto;
import com.nm.cmd.RepayCmd;
import com.nm.core.comp.bean.NewAppUserInfo;

/**
 * 
 * 
 * @author wangqin
 *
 * @date 2017年5月10日 下午9:50:32
 */
@Service
@SuppressWarnings("rawtypes")
public interface UserAcctApiService {
    List<Map<String,Object>> queryAcctInstv1(List<AppCustInfo> appCustInfo);

    List<Map<String,Object>> queryAcctInstv2(List<AppCustInfo> appCustInfo);

    Map<String,Object> queryLoanAcct(String loanNo,String num);
    Map<String,Object> queryLoanAcct2(String loanNo);

    List<Map<String,String>> queryMyLoan(List<AppCustInfo> appCustInfo);

    Map<String,Object> buidContantSsq(UserProfile userProfile, Map<String,String> rqMap);

	Map isAddSsqPhone(String loanNo);

    void reContantSsq(String loanNo);

    List<RepayCmd> dkRepayLoan(List<Map> rqMap, NewAppUserInfo appCustInfo);
    

    LoanAttInDto queryAttachment(String loanNo);

    void deleteAttachment(String loanNo,String attType,String id);


    OCLoanAttInDto uploadAttachmentTemp(MultipartFile multipartFile, String custNo, String loanNo, String attTyp, UserProfile userProfile);

    List<OCLoanAttInDto> queryAttachmentv2(String loanNo);
    
    List<OCLoanAttInDto> queryAttachmentv3(String loanNo,List<String> attType);


    String queryContractUrl(String loanNo);

    Map<String,String> payMsg(String loanNo,String payType,String repayNum,String tranChan,String planId,String buyerId,String openId,String payFlag);

    String getBuyerId(String url);

    String getOpenid(String url);

    String threePayInfo(Map<String,Object> map);

    String evaluate(Map<String,Object> map);

    Map<String,Object> getEvaluate(String loanNo);
}
