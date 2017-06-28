package com.nm.core.comp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nm.base.framework.frame.component.AbstractPrincipalApiComponent;
import com.nm.base.framework.frame.model.SysPrincipalToken;
import com.nm.core.comp.bean.NewAppUserInfo;
import com.nm.core.comp.bean.NewPrincipalClientBean;
import com.nm.mapper.login.NewAppUserInfoMapper;
import com.nm.mybatis.mapper.entity.Example;

/**
 * Created by lenovo on 2017/5/10.
 */
@Component
public class NewPrincipalClientApiComponent extends AbstractPrincipalApiComponent<NewPrincipalClientBean> {
    @Autowired
    private NewAppUserInfoMapper newAppUserInfoMapper;

    @Override
    public NewPrincipalClientBean getPrincipal(SysPrincipalToken sysPrincipalToken) {
        String loginNo = sysPrincipalToken.getLoginNo();
        String code = "";
        NewAppUserInfo appUserInfo = null;
        Example exampleAppUserInfo = new Example(NewAppUserInfo.class);
        exampleAppUserInfo.createCriteria().andEqualTo("moblNo", loginNo);
        List list = this.newAppUserInfoMapper.selectByExample(exampleAppUserInfo);
        if (list != null && list.size() == 1) {
            appUserInfo = (NewAppUserInfo) list.get(0);
        }
        NewPrincipalClientBean principalClientBean = new NewPrincipalClientBean();
        principalClientBean.setAppUserInfo(appUserInfo);
        principalClientBean.setAccessToken(sysPrincipalToken.getAccessToken());
        principalClientBean.setClientKey(sysPrincipalToken.getClientKey());
        principalClientBean.setClientIp(sysPrincipalToken.getClientIp());
        principalClientBean.setClientUserAgent(sysPrincipalToken.getClientUserAgent());
        return principalClientBean;
    }
}
