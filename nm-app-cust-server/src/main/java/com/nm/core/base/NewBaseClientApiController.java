package com.nm.core.base;

import java.util.ArrayList;
import java.util.List;

import com.hs.loan.busi.dto.LoanAcctInDto;
import com.nm.base.framework.core.validate.Validator;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.utils.StringUtils;
import com.nm.api.core.base.BaseApiController;
import com.nm.api.frame.auth.model.AppCustInfo;
import com.nm.base.framework.core.exception.IllegalAccessException;
import com.nm.base.framework.frame.bean.TokenBean;
import com.nm.core.comp.NewPrincipalClientApiComponent;
import com.nm.core.comp.bean.NewAppUserInfo;
import com.nm.core.comp.bean.NewPrincipalClientBean;
import com.nm.service.mb.MbApiService;

/**
 * Created by lenovo on 2017/5/10.
 */
public class NewBaseClientApiController extends BaseApiController {
    @Autowired
    private NewPrincipalClientApiComponent principalClientApiComponent;
    @Autowired
    private MbApiService mbApiService;
    public NewPrincipalClientBean getCurrentPrincipal() {
        TokenBean tokenBean = this.getTokenBean();
        if (null == tokenBean) {
            throw new IllegalAccessException("用户未登录");
        } else {
            String clientIp = this.getCurrentClientIp();
            String clientUserAgent = this.getCurrentClientUserAgent();
            return (NewPrincipalClientBean) this.principalClientApiComponent.getPrincipal(tokenBean, clientIp, clientUserAgent);
        }
    }

    public NewAppUserInfo getCurrentAppUserInfo() {
        return this.getCurrentPrincipal().getAppUserInfo();
    }

    public String getCurrentCertNo() {
        return mbApiService.getCertNoByMobileNo(this.getCurrentAppUserInfo().getMoblNo());
    }

    public List<AppCustInfo> getAppCustInfo() {
        String certNo=getCurrentCertNo();
        if(StringUtils.isNotBlank(certNo)){
            return mbApiService.getAppCustInfo(certNo);
        }else{
            List<AppCustInfo> appCustInfo=new ArrayList<>();
            return appCustInfo;
        }
    }

    public AppCustInfo getAppCustInfoByLoanNo(String loanNo){
        Validator.init(loanNo, "贷款编号").required().end();
        return mbApiService.getAppCustInfoByLoanNo(loanNo);
    }
    /*该方法最好不用，因为在实行一个custNo对应一个loanNo之前，一个custNo可能对应多个loanNo*/
    public LoanAcctInDto getLoanNByCustNo(String custNo){
        return mbApiService.getLoanNoByCustNo(custNo);
    }
    /*public String getCurrentStaffNo() {
        return this.getCurrentUserProfile().getStaffNo();
    }*/

    public void logout() {
        this.principalClientApiComponent.logout(super.getTokenBean());
    }
}
