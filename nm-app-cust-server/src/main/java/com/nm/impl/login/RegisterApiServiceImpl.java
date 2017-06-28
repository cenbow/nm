package com.nm.impl.login;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.utils.DateUtils;
import com.hs.utils.RandomUtil;
import com.nm.api.frame.auth.mapper.AppUserInfoMapper;
import com.nm.api.frame.auth.model.AppUserInfo;
import com.nm.api.frame.captcha.service.CaptchaApiService;
import com.nm.base.framework.core.exception.ParameterException;
import com.nm.base.framework.core.exception.ServiceException;
import com.nm.base.framework.core.util.MD5Utils;
import com.nm.base.framework.core.validate.Validator;
import com.nm.mapper.login.RegisterApiMapper;
import com.nm.mybatis.mapper.entity.Example;
import com.nm.service.login.EncryptSmgService;
import com.nm.service.login.RegisterApiService;

/**
 * @author hanfei
 * @describe 医美serviceImpl
 */
@Service
public class RegisterApiServiceImpl implements RegisterApiService {

    @Resource
    private CaptchaApiService captchaApiService;
    @Resource
    private EncryptSmgService encryptSmgService;
    @Resource
    private RegisterApiMapper registerApiMapper;
    
    @Autowired
    private AppUserInfoMapper appUserInfoMapperr;

    /**
     * 发送验证码
     *
     * @param mobileNo
     * @return
     */
    @Override
    public String send(String mobileNo, String type, String smsType) {
        String captchaType = "31411004";
        Validator.init(mobileNo, "电话号码").required().isPhone().end();
        if (StringUtils.isBlank(type)) {
            throw new ParameterException("验证码类型为空");
        }
        Integer flag = registerApiMapper.queryMobileNo(mobileNo);
        if (type.equals("insert")) {
            captchaType = "31411002";
            if (flag != null && flag > 0) {
                throw new ServiceException("号码已注册请登录");
            }
        }else{
            if (flag == null || flag < 1) {
                throw new ServiceException("号码未注册请注册");
            }
        }

        encryptSmgService.sendMoblieCapthca(mobileNo, captchaType, 900, smsType);
        return null;
    }

    /**
     * 新增用户
     * map 用户注册信息
     *
     * @return
     */
    @Override
    public String addUser(Map<String, Object> map) {
        String regFlag = "0";
        Validator.init(map, "注册信息").required().end()
                .get("phone", "手机号").required().isPhone().end()
                .get("checkCode", "验证码").required().isNumber().rangeLength(4, 4).end()
                .get("passWord", "密码").required().rangeLength(4, 20).end()
                .get("source", "来源").required().isBlank().rangeLength(0, 32);

        String mobileNo = (String) map.get("phone");
        String checkCode = (String) map.get("checkCode");
        String passWord = (String) map.get("passWord");
        //验证码是否一致
        String captchaType = "31411002";
        captchaApiService.validMobleCapthcha(mobileNo, captchaType, checkCode, true);
        String md5Password = MD5Utils.md5(passWord);
        map.put("passWord", md5Password);
        Integer flag = registerApiMapper.queryMobileNo(mobileNo);
        if (flag != null && flag > 0) {
            throw new ServiceException("该手机已注册");
        }
        map.put("regDate", new Date());
        map.put("insDate", DateUtils.getCurDate());
        int type = 0;
        /*查询是否存量客户*/
        /*String custNo = registerApiMapper.queryIsExist(mobileNo);
        if (StringUtils.isBlank(custNo)) {//非存量客户
            custNo = RandomUtil.getUUID();
            map.put("custNo", custNo);
            type = registerApiMapper.addUser(map);
            registerApiMapper.addCust(map);
        } else { //存量客户
            map.put("custNo", custNo);
            type = registerApiMapper.addUser(map);
        }*/
        String custNo = RandomUtil.getUUID();
        map.put("custNo", custNo);
        type = registerApiMapper.addUser(map);
        if (type < 1) {
            throw new ServiceException("注册失败");
        }
        regFlag = "1";
        return regFlag;
    }

    /**
     * 修改密码
     *
     * @param map
     * @return
     */
    @Override
    public String alterPwd(Map<String, Object> map) {

        Validator.init(map, "修改密码信息").required().end()
                .get("phone", "手机号").required().isNumber().rangeLength(11, 11).end()
                //.get("checkCode", "验证码").required().isNumber().rangeLength(4, 4).end()
                .get("passWord", "新密码").required().rangeLength(4, 16).end()
                .get("pwd", "确认新密码").required().rangeLength(4, 16).end();

        String mobileNo = (String) map.get("phone");
        //String checkCode = (String) map.get("checkCode");
        String passWord = (String) map.get("passWord");
        String pwd = (String) map.get("pwd");

        Integer flag = registerApiMapper.queryMobileNo(mobileNo);
        if (flag == null || flag == 0) {
            throw new ParameterException("该手机号码未注册");
        }

        if (!passWord.equals(pwd)) {
            throw new ParameterException("两次密码不一致");
        }

        String captchaType = "31411004";
        //captchaApiService.validMobleCapthcha(mobileNo, captchaType, checkCode, true);

        String md5Password = MD5Utils.md5(passWord);
        map.put("passWord", md5Password);
        int type = registerApiMapper.updatePassWord(map);
        if (type < 1) {
            throw new ServiceException("修改失败");
        }

        return "1";
    }

    @Override
    public void checkPhoneKey(String checkCode, String phoneNo) {
        String captchaType = "31411004";
        Validator.init(phoneNo, "手机号").required().isNumber().rangeLength(11, 11);
        Validator.init(checkCode, "验证码").required().isNumber().rangeLength(4, 4);
        //验证码是否一致
        captchaApiService.validMobleCapthcha(phoneNo, captchaType, checkCode, false);
    }
    
    
    @Override
	public String updatePassWord(Map<String, Object> map) {
		Validator.init(map, "修改密码信息").required().end()
        .get("phone", "手机号").required().isNumber().rangeLength(11, 11).end()
        .get("oldpwd", "旧密码").required().rangeLength(4, 16).end()
        .get("newpwd", "新密码").required().rangeLength(4, 16).end()
        .get("passpwd", "确认密码").required().rangeLength(4, 16).end();
		
		String mobileNo = (String) map.get("phone");
		String oldPwd = (String) map.get("oldpwd");
		String newPwd = (String) map.get("newpwd");
		String passPwd = (String) map.get("passpwd");
		
		 Integer flag = registerApiMapper.queryMobileNo(mobileNo);
	        if (flag == null || flag == 0) {
	            throw new ParameterException("该手机号码未注册");
	        }
	        Example example;

	        example = new Example(AppUserInfo.class);
	        example.createCriteria().andEqualTo("moblNo", mobileNo);

	        List<AppUserInfo> list = appUserInfoMapperr.selectByExample(example);
	        
	        AppUserInfo appUserInfo = list.get(0);


	        if (!appUserInfo.getLoginPwd().equalsIgnoreCase(MD5Utils.md5(oldPwd))) {
	            throw new ParameterException("原密码错误");
	        }
	        
	        
	       if (!newPwd.equals(passPwd)) {
	            throw new ParameterException("两次密码不一致");
	       }
	       
	       
	        map.put("passWord", MD5Utils.md5(newPwd));
	        int type = registerApiMapper.updatePassWord(map);
	        if (type < 1) {
	            throw new ServiceException("修改失败");
	        }
	        
		
		
		
		return "1";
	}
    

}
