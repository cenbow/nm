package com.nm.impl.login;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.system.entity.SysLoginLog;
import com.hs.utils.RandomUtil;
import com.nm.api.frame.auth.mapper.AppUserInfoMapper;
import com.nm.api.frame.auth.model.AppUserInfo;
import com.nm.base.framework.core.exception.ParameterException;
import com.nm.base.framework.core.prop.PropertyPlaceholderConfigurer;
import com.nm.base.framework.core.util.MD5Utils;
import com.nm.base.framework.core.validate.Validator;
import com.nm.base.framework.frame.model.SysPrincipalToken;
import com.nm.cmd.LoginV2Cmd;
import com.nm.core.comp.NewPrincipalClientApiComponent;
import com.nm.core.comp.bean.NewPrincipalClientBean;
import com.nm.mapper.login.SysLoginLogApiMapper;
import com.nm.mybatis.mapper.entity.Example;
import com.nm.service.login.LoginApiService;

@Service
public class LoginApiServiceImpl implements LoginApiService {

    @Autowired
    private NewPrincipalClientApiComponent principalClientApiComponent;
    @Autowired
    private SysLoginLogApiMapper sysLoginLogApiMapper;
    @Autowired
    private AppUserInfoMapper appUserInfoMapperr;


    @Override
    public Map<String, String> loginV2(LoginV2Cmd loginV2Cmd) {
        Validator.init(loginV2Cmd, "登录信息").required().end()
                .get("phone", "用户名").required().isNumber().rangeLength(11, 11).end()
                .get("password", "密码").required().rangeLength(4, 16).end();


        String clientKey2 = StringUtils.isBlank(loginV2Cmd.getPhoneKey()) ? RandomUtil.getUUID() : loginV2Cmd.getPhoneKey();
        clientKey2 = "clientKey" + clientKey2;
        loginV2Cmd.setClientKey(clientKey2);

        Example example;

        example = new Example(AppUserInfo.class);
        example.createCriteria().andEqualTo("moblNo", loginV2Cmd.getPhone());

        List<AppUserInfo> list = appUserInfoMapperr.selectByExample(example);

        if (null == list || list.size() == 0) {
            throw new ParameterException("用户名不存在");
        }

        AppUserInfo appUserInfo =  list.get(0);

        String md5Password = MD5Utils.md5(loginV2Cmd.getPassword());

        if (!appUserInfo.getLoginPwd().equalsIgnoreCase(md5Password)) {
            throw new ParameterException("密码错误");
        }
        String loginStat = appUserInfo.getLoginStat();
        if (!StringUtils.isEmpty(loginStat) && !appUserInfo.getLoginStat().equals("10002001"))
            throw new ParameterException("用户已禁用");

        String clientip = loginV2Cmd.getClientIp();
        String clientuseragent = loginV2Cmd.getClientUserAgent();
        String clientKey = loginV2Cmd.getClientKey();
        String remoteApi = loginV2Cmd.getRemoteApi();

        Integer validMins = PropertyPlaceholderConfigurer.getPropertyInteger("login.v2.validMins", 30);

        NewPrincipalClientBean principalClientBean = this.addsysPrincipalToken(validMins, clientKey, clientip, clientuseragent, remoteApi, appUserInfo);
        Map<String, String> map = new HashMap<String, String>();
        map.put("accessToken", principalClientBean.getAccessToken());
        map.put("clientKey", principalClientBean.getClientKey());
        return map;
    }


    private NewPrincipalClientBean addsysPrincipalToken(Integer validMins, String clientKey, String clientip, String clientuseragent, String remoteApi, AppUserInfo appUserInfo) {

        String mobileNo = appUserInfo.getMoblNo();
        SysPrincipalToken sysPrincipalToken = new SysPrincipalToken();
        sysPrincipalToken.setLoginNo(mobileNo);
        sysPrincipalToken.setClientKey(clientKey);
        sysPrincipalToken.setClientIp(clientip);
        sysPrincipalToken.setClientUserAgent(clientuseragent);
        sysPrincipalToken.setValidMins(validMins);
        sysPrincipalToken.setRemoteApi(remoteApi);
        NewPrincipalClientBean principalClientBean = principalClientApiComponent.addNewPrincipal(sysPrincipalToken);

        //系统日志
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setId(RandomUtil.getUUID());
        sysLoginLog.setLoginDate(new Date());
        sysLoginLog.setLoginIp(clientip);
        sysLoginLog.setStaffId(appUserInfo.getCustNo());
        sysLoginLog.setChannel("cust");
        sysLoginLog.setStaffName(appUserInfo.getCustName());
        /*sysLoginLog.setOrgId(userProfile.getOrgNo());
        sysLoginLog.setOrgName(userProfile.getOrgName());
		sysLoginLog.setStaffName(userProfile.getStaffName());*/
        sysLoginLogApiMapper.insertSelective(sysLoginLog);
        return principalClientBean;
    }


}
