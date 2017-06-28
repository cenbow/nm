package com.nm.api.login;

import com.nm.base.framework.core.cmd.Command;
import com.nm.cmd.LoginV2Cmd;
import com.nm.core.base.NewBaseClientApiController;
import com.nm.core.comp.bean.NewPrincipalClientBean;
import com.nm.service.login.LoginApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Controller
@RequestMapping(value = "/api/login", method = RequestMethod.POST)
public class LoginApiController extends NewBaseClientApiController {

    @Autowired
    private LoginApiService loginApiService;


    @ResponseBody
    @RequestMapping(value = "/getCurrentPrincipal", method = RequestMethod.POST)
    public Command getCurrentPrincipal(Command cmd) {

        NewPrincipalClientBean currentPrincipal = super.getCurrentPrincipal();
        cmd.setOut(currentPrincipal);
        return cmd;
    }

    /**
     * 登陆接口
     *
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping("/v1")
    public Command loginV1(Command cmd) {
        super.logout();
        LoginV2Cmd bean = cmd.populate(new LoginV2Cmd());
        bean.setRemoteApi(super.getRequest().getRequestURI());
        bean.setClientIp(getCurrentClientIp());
        bean.setClientUserAgent(getCurrentClientUserAgent());
        Map<String, String> accessToken = loginApiService.loginV2(bean);
        cmd.setOutMap(accessToken);
        return cmd;
    }


    /**
     * 登录退出
     */
    @ResponseBody
    @RequestMapping(value = "/logout/v1", method = RequestMethod.POST)
    public Command logout(Command cmd) {
        logout();
        return cmd;
    }


}
