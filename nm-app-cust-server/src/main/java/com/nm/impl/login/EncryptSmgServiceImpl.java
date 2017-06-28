package com.nm.impl.login;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.utils.ParamUtils;
import com.hs.utils.RandomUtil;
import com.lemon.codec.AesEncrypt;
import com.nm.api.frame.captcha.mapper.SysMobileCaptchaMapper;
import com.nm.api.frame.captcha.model.SysMobileCaptcha;
import com.nm.base.framework.core.exception.ParameterException;
import com.nm.base.framework.core.exception.ServiceException;
import com.nm.base.framework.core.exception.SystemException;
import com.nm.base.framework.core.validate.Validator;
import com.nm.mybatis.mapper.entity.Example;
import com.nm.service.login.EncryptSmgService;
import com.nm.util.HttpClientTool;

import net.sf.json.JSONObject;

/**
 * Created by lenovo on 2017/5/3.
 */
@Service
public class EncryptSmgServiceImpl implements EncryptSmgService {

    private static final Logger logger = LoggerFactory.getLogger(EncryptSmgServiceImpl.class);
    @Autowired
    public SysMobileCaptchaMapper sysMobileCaptchaMapper;

    @Override
    public void sendMoblieCapthca(String mobleNo, String captchaType, int validSeconds, int length, String type) {
        Validator.init(mobleNo, "手机号码").required().isNumber().isMaxLength(11);
        Validator.init(captchaType, "验证码类型").required().isMaxLength(8);
        Validator.init(Integer.valueOf(length), "验证码长度").required().range(4.0D, 6.0D);
        Validator.init(Integer.valueOf(validSeconds), "有效秒数").required().min(60.0D);
        HashMap param = new HashMap();
        param.put("moblno", mobleNo);
        param.put("captchatype", captchaType);
        param.put("status", "10002001");
        Example example = new Example(SysMobileCaptcha.class);
        example.createCriteria().andEqualTo("moblno", mobleNo).andEqualTo("captchatype", captchaType);
        example.orderBy("instDate").desc();
        List list = this.sysMobileCaptchaMapper.selectByExample(example);
        SysMobileCaptcha sysMobileCaptcha = null;
        if (!list.isEmpty() && !type.equals("vms")) {
            sysMobileCaptcha = (SysMobileCaptcha) list.get(0);
            Date capthaval = sysMobileCaptcha.getInstDate();
            Date url = new Date();
            long parameters = (url.getTime() - capthaval.getTime()) / 1000L;
            if (parameters / 60L <= 5L) {
                double e2 = Math.floor((double) (parameters / 60L));
                if (e2 <= 0.0D) {
                    e2 = 1.0D;
                }

                throw new ServiceException(MessageFormat.format("验证码未失效，请查看{0}分钟之前发送的信息.", new Object[]{Double.valueOf(e2)}));
            }

            example = new Example(SysMobileCaptcha.class);
            example.createCriteria().andEqualTo("moblno", mobleNo).andEqualTo("captchatype", captchaType);
            this.sysMobileCaptchaMapper.deleteByExample(example);
        }

        String capthaval1 = (Math.random() + "").substring(2, 2 + length);
        sysMobileCaptcha = new SysMobileCaptcha();
        sysMobileCaptcha.setMoblno(mobleNo);
        sysMobileCaptcha.setCaptchatype(captchaType);
        sysMobileCaptcha.setStatus("10002001");
        sysMobileCaptcha.setId(RandomUtil.getUUID());
        sysMobileCaptcha.setCapthaval(capthaval1);
        sysMobileCaptcha.setValidseconds(Integer.valueOf(validSeconds));
        sysMobileCaptcha.setInstDate(new Date());
        sysMobileCaptcha.setUpdtDate(new Date());
        this.sysMobileCaptchaMapper.insertSysMobileCaptcha(sysMobileCaptcha);

        param.clear();
        param.put("mobile", mobleNo);
        param.put("content", MessageFormat.format("柠檬金服验证码：{0}，提示：{1}分钟内有效， {2}.", new Object[]{capthaval1, Double.valueOf(Math.floor((double) (validSeconds / 60))), (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(sysMobileCaptcha.getInstDate())}));
        String url1 = ParamUtils.getParam("encryptionSMSUrl");
        if (type.equals("vms")) {
            url1 = ParamUtils.getParam("encryptionVMSUrl");
            param.clear();
            param.put("phonenum", mobleNo);
            param.put("vfcode", capthaval1);

        }
        JSONObject json = JSONObject.fromObject(param);
        ArrayList parameters1 = new ArrayList();

        try {
            String data = AesEncrypt.encrypt(json.toString(), ParamUtils.getParam("smsAESpriKey"));
            param.clear();
            param.put("data", data);
            HttpClientTool httpClientUtil = new HttpClientTool();
            String e1 = httpClientUtil.sendHttpPost(url1, param);
            JSONObject jsonObject = JSONObject.fromObject(e1);
            String retMSg = jsonObject.has("error") ? jsonObject.get("error").toString() : "0";
            logger.debug("发送验证码返回值：" + e1);
            if (!retMSg.equals("0")) {
                throw new ServiceException("发送失败，请重试");
            }
        } catch (Exception var15) {
            throw new SystemException("发送短信通道错误", var15);
        }
    }

    @Override
    public void sendMoblieCapthca(String moblNo, String captchaType, int validSeconds, String type) {
        this.sendMoblieCapthca(moblNo, captchaType, validSeconds, 4, type);
    }

    public void validMobleCapthcha(String mobleNo, String captchaType, String captchaVal, boolean isLogout) {
        Validator.init(mobleNo, "手机号码").required().isNumber().isMaxLength(11);
        Validator.init(captchaType, "验证码类型").required().isMaxLength(8);
        Validator.init(captchaVal, "验证码").required().rangeLength(4, 6);
        Example example = new Example(SysMobileCaptcha.class);
        example.createCriteria().andEqualTo("moblno", mobleNo).andEqualTo("captchatype", captchaType);
        example.orderBy("instDate").desc();
        List list = this.sysMobileCaptchaMapper.selectByExample(example);
        if (list != null && !list.isEmpty()) {
            SysMobileCaptcha sysMobileCaptcha = (SysMobileCaptcha) list.get(0);
            int validSeconds = sysMobileCaptcha.getValidseconds().intValue();
            Date instDate = sysMobileCaptcha.getInstDate();
            Date currDate = new Date();
            long num = (currDate.getTime() - instDate.getTime()) / 1000L;
            if (num > (long) validSeconds) {
                throw new ParameterException("验证码已失效，请重新发送验证码.");
            } else if (!captchaVal.equals(sysMobileCaptcha.getCapthaval())) {
                logger.debug(MessageFormat.format("{0}:{1}", new Object[]{captchaType, sysMobileCaptcha.getCapthaval()}));
                throw new ParameterException("验证码错误，请重新输入验证码.");
            } else {
                if (isLogout) {
                    this.sysMobileCaptchaMapper.deleteByPrimaryKey(sysMobileCaptcha.getId());
                }

            }
        } else {
            throw new ParameterException("验证码无效，请重新发送验证码.");
        }
    }

    public void validMobleCapthcha(String mobleNo, String captchaType, String captchaVal) {
        this.validMobleCapthcha(mobleNo, captchaType, captchaVal, false);
    }

}
