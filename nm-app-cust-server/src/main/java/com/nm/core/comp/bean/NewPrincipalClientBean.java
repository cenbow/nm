package com.nm.core.comp.bean;

import com.nm.api.frame.auth.model.AppCustInfo;
import com.nm.base.framework.frame.bean.PrincipalBean;

import java.util.List;

/**
 * Created by lenovo on 2017/5/10.
 */
public class NewPrincipalClientBean extends PrincipalBean {
    /*public AppUserInfo getAppUserInfo() {
        return appUserInfo;
    }

    public void setAppUserInfo(AppUserInfo appUserInfo) {
        this.appUserInfo = appUserInfo;
    }*/

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private NewAppUserInfo appUserInfo;

    private List<AppCustInfo> appCustInfo;

    public List<AppCustInfo> getAppCustInfo() {
        return appCustInfo;
    }

    public void setAppCustInfo(List<AppCustInfo> appCustInfo) {
        this.appCustInfo = appCustInfo;
    }

    public NewAppUserInfo getAppUserInfo() {
        return appUserInfo;
    }

    public void setAppUserInfo(NewAppUserInfo appUserInfo) {
        this.appUserInfo = appUserInfo;
    }
}
