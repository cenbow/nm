package com.nm.impl.usercenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hs.loan.busi.dto.AppGrapScoreDto;
import com.hs.loan.sale.api.LoanAcctApi;
import com.hs.system.index.service.PubIndexService;
import com.hs.utils.ParamUtils;
import com.nm.base.framework.core.util.HelperUtils;
import com.nm.base.framework.core.validate.Validator;
import com.nm.cmd.BigDataHFiveCmd;
import com.nm.mapper.usercenter.BigDataApiMapper;
import com.nm.model.BigDataHFivemodel;
import com.nm.service.usercenter.BigDataApiService;
import com.nm.util.EncodingUtil;
import com.nm.util.RemoteClientHelper;
import org.apache.commons.httpclient.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class BigDataApiServiceImp implements BigDataApiService {

    private static final Logger log = LoggerFactory.getLogger(BigDataApiServiceImp.class);

    @Resource
    public PubIndexService pubIndexService;
    @Autowired
    private BigDataApiMapper bigDataApiMapper;
    @Resource
    public LoanAcctApi loanAcctservice;

    @Override
    public BigDataHFivemodel bigDataFromHFive(BigDataHFiveCmd bigDataHFiveCmd) {
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        BigDataHFivemodel bigDataHFivemodel = new BigDataHFivemodel();
        Validator.init(bigDataHFiveCmd, "调用H5接口信息").required().end()
                .get("loanNo", "贷款编号").required().end()
                .get("phoneNo", "手机号码").required().end()
                .get("certNo", "身份证号码").required().end()
                .get("redirectURL", "回调地址").required().end()
                .get("custName", "客户姓名").required().end();
        bigDataApiMapper.deleteDmOrderInfo(bigDataHFiveCmd.getLoanNo());
        parameters.add(new NameValuePair("userName", bigDataHFiveCmd.getCustName()));
        parameters.add(new NameValuePair("userIdCard", bigDataHFiveCmd.getCertNo()));
        parameters.add(new NameValuePair("userCellPhone", bigDataHFiveCmd.getPhoneNo()));
        parameters.add(new NameValuePair("redirectURL", bigDataHFiveCmd.getRedirectURL()));
        parameters.add(new NameValuePair("appKey", "lemon"));
        String url = ParamUtils.getParam("BIG_DATA_CRAWLER_API_URL");
        //"http://123.57.7.55:8080/mobileCrawler";
        String results = "";
        JSONObject parseObject = null;
        JSONObject data = null;
        try {
            results = HelperUtils.executeHttpPost(url, parameters);
            results=EncodingUtil.transcoding(results,"UTF-8");
        } catch (Exception e) {
            log.error(MessageFormat.format(bigDataHFiveCmd.getLoanNo() + "大数据H5接口调用失败({0})", bigDataHFiveCmd.getLoanNo()), e);
        } finally {
            log.info(bigDataHFiveCmd.getLoanNo() + "# ****************** 调用H5接口开始 ****************** #");
            log.info("# 调用H5  URL #" + url);
            log.info("# 调用H5 入参 #" + parameters.toString());
            log.info("# 调用A5 返回 #" + results.toString());
            log.info("# ****************** 调用A5接口结束 ****************** #");
        }
        parseObject = JSON.parseObject(results);
        String errorCode = "";
        String errorMsg = "";
        if (parseObject != null) {
            errorCode = parseObject.getString("errorCode");
            errorMsg = parseObject.getString("errorMsg");
            //200证明授权成功,授权失败返回202给前台,201代表24小时内抓取成功
            if ("200".equals(errorCode)) {
                String orderId = parseObject.getString("uid");
                data = parseObject.getJSONObject("data");
                String status = data.getString("status");
                pubIndexService.saveDmOrderInfo(orderId, bigDataHFiveCmd.getLoanNo());
                if ("0".equals(status)) {
                    bigDataHFivemodel.setErrorCode("200");
                    bigDataHFivemodel.setCrawlerURL(data.getString("crawlerURL"));
                    return bigDataHFivemodel;
                } else if ("1".equals(status)) {
                    bigDataHFivemodel.setErrorCode("202");
                    bigDataHFivemodel.setCrawlerURL(null);
                    bigDataHFivemodel.setErrorMsg("获取通话详单失败，请手动上传附件" + errorMsg);
                    return bigDataHFivemodel;
                } else if ("2".equals(status)) {
                    bigDataHFivemodel.setErrorCode("201");
                    bigDataHFivemodel.setCrawlerURL(null);
                    return bigDataHFivemodel;
                }
            } else {
                bigDataHFivemodel.setErrorCode("202");
                bigDataHFivemodel.setCrawlerURL(null);
                bigDataHFivemodel.setErrorMsg("获取通话详单失败，请手动上传附件" + errorMsg);
                return bigDataHFivemodel;
            }
        } else {
            bigDataHFivemodel.setErrorCode("202");
            bigDataHFivemodel.setCrawlerURL(null);
            bigDataHFivemodel.setErrorMsg("获取通话详单失败，请再次尝试！");
            return bigDataHFivemodel;
        }
        return bigDataHFivemodel;
    }

    @Override
    public BigDataHFivemodel bigDataFromzhima(BigDataHFiveCmd bigDataHFiveCmd) {
        BigDataHFivemodel bigDataHFivemodel = new BigDataHFivemodel();
        Validator.init(bigDataHFiveCmd, "调用芝麻接口信息").required().end()
                .get("phoneNo", "手机号码").required().end()
                .get("certNo", "身份证号码").required().end()
                .get("redirectURL", "回调地址").required().end()
                .get("custName", "客户姓名").required().end()
                .get("loanNo", "贷款编号").required().end()
                .get("custNo", "客户编号").required().end();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", bigDataHFiveCmd.getCustName());
        parameters.put("idCard", bigDataHFiveCmd.getCertNo());
        parameters.put("phone", bigDataHFiveCmd.getPhoneNo());
        parameters.put("redirectURL", bigDataHFiveCmd.getRedirectURL());
        parameters.put("channel_type", "2");
        parameters.put("appKey", "lemon");
        String url = ParamUtils.getParam("BIG_DATA_FETCH_ZHIMAFEN_API_URL");
        log.info("# 芝麻  URL #" + url);
        Map<String, String> headers = new HashMap<>();
        headers.put("appKey", "lemon");
        JSONObject parseObject = null;
        JSONObject data = null;

        String results = null;
        try {
            results = RemoteClientHelper.invokePostIgnoreException(url, headers, parameters);
            results=EncodingUtil.transcoding(results,"UTF-8");
        } catch (Exception e) {
            log.error(MessageFormat.format(bigDataHFiveCmd.getLoanNo() + "大数据芝麻分接口调用失败({0})", bigDataHFiveCmd.getLoanNo()), e);
        } finally {
            log.info(bigDataHFiveCmd.getLoanNo() + "# ****************** 调用芝麻接口开始 ****************** #");
            log.info("# 芝麻  URL #" + url);
            log.info("# 芝麻 head #" + headers.toString());
            log.info("# 芝麻 入参 #" + parameters.toString());
            log.info("# 芝麻 返回 #" + results.toString());
            log.info("# ****************** 调用芝麻接口结束 ****************** #");
        }
        parseObject = JSON.parseObject(results);
        String errorCode = "";
        String errorMsg = "";
        String uid="";
        if (parseObject != null) {
            errorCode = parseObject.getString("errorCode");
            errorMsg = parseObject.getString("errorMsg");
            uid = parseObject.getString("uid");
            log.info("# 芝麻 流水号 #" + uid);
            //200证明授权成功,授权失败返回202给前台,201代表24小时内抓取成功
            if ("200".equals(errorCode)) {
                data = parseObject.getJSONObject("data");
                Boolean authorized = data.getBoolean("authorized");
                if (false == authorized) {
                    bigDataHFivemodel.setErrorCode("201");
                    bigDataHFivemodel.setCrawlerURL(data.getString("auth_url"));
                    return bigDataHFivemodel;
                } else {
                    log.info("=============芝麻分保存开始=============");
                    bigDataHFivemodel.setErrorCode("200");
                    bigDataHFivemodel.setErrorMsg("成功获取芝麻分");
                    AppGrapScoreDto appGrapScoreDto = new AppGrapScoreDto();
                    appGrapScoreDto.setCustNo(bigDataHFiveCmd.getCustNo());
                    appGrapScoreDto.setGrapChan("31511001");
                    appGrapScoreDto.setGrapSesaSeed(Integer.valueOf(data.getString("result")));
                    appGrapScoreDto.setLoanNo(bigDataHFiveCmd.getLoanNo());
                    log.info("=============芝麻分保存客户============="+bigDataHFiveCmd.getCustNo());
                    //loanAcctservice.saveGrapScore(appGrapScoreDto);
                    log.info("=============芝麻分保存结束=============");
                    return bigDataHFivemodel;
                }
            } else {
                bigDataHFivemodel.setErrorCode("202");
                bigDataHFivemodel.setCrawlerURL(null);
                bigDataHFivemodel.setErrorMsg("获取芝麻分失败！" + errorMsg);
                return bigDataHFivemodel;
            }
        } else {
            bigDataHFivemodel.setErrorCode("202");
            bigDataHFivemodel.setCrawlerURL(null);
            bigDataHFivemodel.setErrorMsg("获取芝麻分失败！");
            return bigDataHFivemodel;
        }
    }

}
