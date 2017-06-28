package com.test.mediaService;

import com.alibaba.fastjson.JSON;
import com.hs.loan.busi.dto.AppGrapScoreDto;
import com.hs.loan.cust.dto.CustContctInfoDto;
import com.hs.loan.cust.dto.CustInfoDto;
import com.hs.loan.cust.dto.CustLiveInfoDto;
import com.nm.cmd.AddCustBaseCmd;
import com.nm.cmd.CustBankAcctCmd;
import com.nm.cmd.LoanAcctInCmd;
import com.nm.util.AESUtil;
import net.sf.json.JSONObject;
import scala.util.matching.Regex;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lenovo on 2017/5/10.
 */
public class test {

    public static void main(String[] args) {
        String str="{\"errorCode\":200,\"errorMsg\":\"请求成功\",\"uid\":\"20170622121936964uOBllsDxnsrOE\",\"data\":{\"authorized\":\"true\",\"result\":\"671\"}}";
        System.out.println(str);
        JSONObject parseObject=JSONObject.fromObject(str);
        System.out.println(parseObject);
        String errorCode = parseObject.getString("errorCode");
        String errorMsg = parseObject.getString("errorMsg");
        String uid = parseObject.getString("uid");
        //200证明授权成功,授权失败返回202给前台,201代表24小时内抓取成功
        if ("200".equals(errorCode)) {
            JSONObject data = parseObject.getJSONObject("data");
            Boolean authorized = data.getBoolean("authorized");
            if (false == authorized) {
                System.out.println("201");
            } else {
                System.out.println("200");
            }
        }
    }
}
