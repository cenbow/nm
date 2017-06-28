package com.nm.util;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by MIfengHe on 2017/3/25.
 */
public class RemoteClientHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteClientHelper.class);

    protected static String invokePostException(String url, Map<String, String> params) {
        return invokePostException(url, null, params);
    }

    /**
     *
     * @param url
     * @param headers
     * @param params
     * @return
     */
    protected static String invokePostException(String url, Map<String, String> headers, Map<String, String> params) {
        String result;

        long startTime = new Date().getTime();
        if (params == null) params = new HashMap<String, String>();
        if (headers == null) headers = new HashMap<String, String>();

        List<String> infos = new ArrayList<String>();
        infos.add("# *************************************************************** #");
        infos.add("# 请求地址 > " + url);
        infos.add("# 请求头部 > " + JSONObject.toJSONString(headers));
        infos.add("# 请求参数 > " + JSONObject.toJSONString(params));

        try {
            HttpClient httpclient = new HttpClient();
            httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(150000);
            httpclient.getHttpConnectionManager().getParams().setSoTimeout(150000);

            PostMethod post = new PostMethod(url);
            try {
                post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf8");

                Set<String> keySet;
                String value;

                //头部
                keySet = headers.keySet();
                for(String key : keySet) {
                    value = params.get(key);
                    post.addRequestHeader(key, value);
                }

                //参数
                keySet = params.keySet();
                for(String key : keySet) {
                    value = params.get(key);
                    post.addParameter(new NameValuePair(key, value));
                }


                httpclient.executeMethod(post);
                //String info = new String(post.getResponseBody(), "utf8");
                InputStream inputStream = post.getResponseBodyAsStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer = new StringBuffer();
                String str= "";
                while((str = br.readLine()) != null){
                    stringBuffer .append(str );
                }
                result = stringBuffer.toString();
                infos.add("# 响应结果 > " + result);
                return result;
            }catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                post.isAborted();
                post.releaseConnection();
                httpclient.getHttpConnectionManager().closeIdleConnections(0);
            }
        } catch (Exception e) {
            LOGGER.error("# 远程连接异常：", e);
            infos.add("# 异常信息 > " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            infos.add("# 调用费时 > " + (new Date().getTime() - startTime) + "ms");
            infos.add("# *************************************************************** #");

            for (String info : infos) {
                LOGGER.info(info);
            }
        }
    }

    /**
     *
     * @param url
     * @param params
     * @return
     */
    public static String invokePost(String url, Map<String, String> params) {
        return invokePostException(url, params);
    }

    /**
     *
     * @param url
     * @param headers
     * @param params
     * @return
     */
    public static String invokePost(String url, Map<String, String> headers, Map<String, String> params) {
        return invokePostException(url, params);
    }

    /**
     *
     * @param url
     * @param params
     * @return
     */
    public static String invokePostIgnoreException(String url, Map<String, String> params) {
        try {
            return invokePostException(url, params);
        } catch (Exception e) {
            //忽略处理
        }
        return StringUtils.EMPTY;
    }

    /**
     *
     * @param url
     * @param headers
     * @param params
     * @return
     */
    public static String invokePostIgnoreException(String url, Map<String, String> headers, Map<String, String> params) {
        try {
            return invokePostException(url, headers, params);
        } catch (Exception e) {
            //忽略处理
        }
        return StringUtils.EMPTY;
    }
}
