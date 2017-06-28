package com.nm.api.login;

import com.nm.base.framework.core.cmd.Command;
import com.nm.cmd.LoginV2Cmd;
import com.nm.core.base.NewBaseClientApiController;
import com.nm.service.login.LoginApiService;
import com.nm.service.login.RegisterApiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hanfei
 * @describe 注册api
 */
@Controller
@RequestMapping("/api/register")
public class RegisterApiController extends NewBaseClientApiController {

    @Resource
    private RegisterApiService registerApiService;
    @Resource
    private LoginApiService loginApiService;

    /**
     * 用户注册接口
     *
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addUser/v1", method = RequestMethod.POST)
    public Command getCurrentPrincipal(Command cmd) {
        Map<String, Object> reqMap = cmd.getInMapObject("userInfo");
        String password=reqMap.get("passWord").toString();
        registerApiService.addUser(reqMap);


        Map<String,String> reqmap=new HashMap<>();
        reqmap.put("phone",reqMap.get("phone").toString());
        reqmap.put("password",password);
        cmd.setIn(reqmap);
        LoginV2Cmd bean = cmd.populate(new LoginV2Cmd());
        bean.setRemoteApi(super.getRequest().getRequestURI());
        bean.setClientIp(getCurrentClientIp());
        bean.setClientUserAgent(getCurrentClientUserAgent());

        Map<String, String> accessToken = loginApiService.loginV2(bean);
        cmd.setOut(accessToken);
        return cmd;
    }

    /**
     * 获取用户验证码
     */
    @RequestMapping(value = "/createcode/v1", method = RequestMethod.POST)
    @ResponseBody
    public Command CreatCode(Command cmd, HttpServletRequest request) {
        String moblNo = cmd.getInString("phone");
        String type = cmd.getInString("type");
        registerApiService.send(moblNo, type, "sms");
        return cmd;
    }

    /**
     * 获取用户语音验证码
     */
    @RequestMapping(value = "/cVoiceCode/v1", method = RequestMethod.POST)
    @ResponseBody
    public Command cVoiceCode(Command cmd, HttpServletRequest request) {
        String moblNo = cmd.getInString("phone");
        String type = cmd.getInString("type");
        registerApiService.send(moblNo, type, "vms");
        return cmd;
    }

    /**
     * 验证验证码是否正确
     */
    @RequestMapping(value = "/checkCode/v1", method = RequestMethod.POST)
    @ResponseBody
    public Command checkCode(Command cmd) {
        String checkCode = cmd.getInString("checkCode");
        String phoneNo = cmd.getInString("phone");
        registerApiService.checkPhoneKey(checkCode, phoneNo);
        return cmd;
    }


    /**
     * 修改密码
     */
    @RequestMapping(value = "/alterPwd/v1", method = RequestMethod.POST)
    @ResponseBody
    public Command alterPwd(Command cmd, HttpServletRequest request) {
        Map<String, Object> reqMap = cmd.getInMapObject("userInfo");
        registerApiService.alterPwd(reqMap);
        return cmd;
    }

    /**
     * 登录退出
     */
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Command logout(Command cmd) {
        logout();
        return cmd;
    }
}
