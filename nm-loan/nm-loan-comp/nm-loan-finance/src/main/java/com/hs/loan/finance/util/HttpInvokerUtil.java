/**
 * 
 */
package com.hs.loan.finance.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.UnsupportedCharsetException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.hs.loan.finance.bo.SingleDkDto;

/**
 * <p>decribing : HTTP请求调用</p>
 * <p>copyright : Copyright @ 2012 hansy</p>
 * <p>company   : hansy</p>
 * <p>time      : 2015-2-3</p>
 *
 * @author hmzhu
 * @version v1.0
 */
public class HttpInvokerUtil {
	
	/***设置连接超时时间 30s*/
	private final static  int REQ_TIMEOUT = 30*1000;
	/*** 设置响应超时时间 30s*/
	private final static  int RESP_TIMEOUT = 30*1000;
	
	/**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static List<String> sendGet(String url, String param) {
    	List<String> resultList = new ArrayList<String>();
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
            	resultList.add(line);
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return resultList;
    }
    
    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(URLEncoder.encode(param, "UTF-8"));
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @param charsetName 编码格式
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param, String charsetName) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url+"?"+param);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            //out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),charsetName));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
	/*public static String sendPost11(String url, String param, String charsetName) {
		  //3、发送报文信息
				HttpClient httpClient = new HttpClient();
				httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
				PostMethod postMethod = new PostMethod(url+"?"+param);

				try {
//					NameValuePair[] param1 = { new NameValuePair("appId", "2342343")};  
//					postMethod.setRequestBody(param1);   
					//执行getMethod
					int statusCode = httpClient.executeMethod(postMethod);
					//失败
					if (statusCode != HttpStatus.SC_OK) {
						//读取内容 
						byte[] responseBody = postMethod.getResponseBody();
						//处理内容
						String strResp = new String(responseBody, "UTF-8");
					}else {
						//读取内容 
						byte[] responseBody = postMethod.getResponseBody();
						
						String content = new String(responseBody, "UTF-8");
						//4、解密返回结果
						// 从密文报文中取数据
						return content;
					}
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				} catch (HttpException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					//释放连接
					postMethod.releaseConnection();
				}
return null;
	}*/
    public static CloseableHttpClient createSSLClientDefault(){
    	try {
    	             SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
    	                 //信任所有
    	                 public boolean isTrusted(X509Certificate[] chain,
    	                                 String authType) throws CertificateException {
    	                     return true;
    	                 }

						@Override
						public boolean isTrusted(
								java.security.cert.X509Certificate[] arg0,
								String arg1)
								throws java.security.cert.CertificateException {
							// TODO Auto-generated method stub
							return false;
						}
    	             }).build();
    	             SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
    	             return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    	         } catch (KeyManagementException e) {
    	             e.printStackTrace();
    	         } catch (NoSuchAlgorithmException e) {
    	             e.printStackTrace();
    	         } catch (KeyStoreException e) {
    	             e.printStackTrace();
    	         }
    	         return  HttpClients.createDefault();
    	}
    /**
	 * postJson返回json或普通字符串
	 * @param postUrl 请求的地址
	 * @param jsonStr 请求的json格式的报文
	 * @return String 返回接口响应的json字符串报文，失败返回null
	 */
	public static String postJson(String postUrl,String jsonStr){
		String rs = null;
		//CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpClient httpclient = HttpInvokerUtil.createSSLClientDefault();
		HttpPost post = new HttpPost(postUrl);
		try {
			StringEntity reqEntity= new StringEntity(jsonStr, ContentType.APPLICATION_JSON);
			post.setEntity(reqEntity);
			System.out.println("begin request"+DateUtil.getDayTime()+"===>>>executing request："+post.getURI());
			System.out.println("===>>>request param : " + jsonStr);
			CloseableHttpResponse resp = null;
			try {
				resp = httpclient.execute(post);
				StatusLine status = resp.getStatusLine();
				System.out.println("<<<=== respose status : "+status);
				if(status.getStatusCode()==200){
					HttpEntity entity = resp.getEntity();
					if(entity!=null){
						BufferedHttpEntity bufferedEntity = new BufferedHttpEntity(entity);
						rs = EntityUtils.toString(bufferedEntity, "UTF-8");
						System.out.println("respones "+DateUtil.getDayTime()+"<<<===response content:"+rs);
					}
				}
			} catch (ClientProtocolException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} finally {
				if(resp!=null){
					resp.close();
				}
			}
		} catch (UnsupportedCharsetException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				if(httpclient!=null)
					httpclient.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return rs;
	}
    
	
	public static String postForm(String postUrl ,Map<String,String> paramMap){
		String rs = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post =new HttpPost(postUrl);
		RequestConfig config = RequestConfig.custom()  
		        .setConnectTimeout(REQ_TIMEOUT)
		        .setConnectionRequestTimeout(3000)//设置从connect Manager获取Connection 超时时间 3s
		        .setSocketTimeout(RESP_TIMEOUT).build();
		post.setConfig(config);  
		CloseableHttpResponse resp = null;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		//参数组装
		for(Map.Entry<String, String> para : paramMap.entrySet()){
			params.add(new BasicNameValuePair(para.getKey(), para.getValue()));
		}
		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params,"UTF-8");
			post.setEntity(entity);
			System.out.println("executing request： "+post.getURI());
			resp = httpClient.execute(post);
			StatusLine status = resp.getStatusLine();
			System.out.println("respose status："+status);
			if(status.getStatusCode()==200){
				HttpEntity hEntity = resp.getEntity();
				if(hEntity!=null){
					BufferedHttpEntity bufferedEntity = new BufferedHttpEntity(hEntity);
					rs = EntityUtils.toString(bufferedEntity, "UTF-8");
				}
			}
		} catch (Exception e){
			System.out.println("post请求失败："+e.getMessage());
			e.printStackTrace();
			//throw new AppException(e);
		}finally{
			try {
				if(resp!=null)
					resp.close();
				if(httpClient!=null)
					httpClient.close();
			} catch (IOException e) {
				System.out.println("资源关闭失败："+e.getMessage());
				e.printStackTrace();
				//throw new AppException(e);
			}
		}
		return rs;
	}
	
	
	
    public static void main(String[] args) {
    	SingleDkDto s = new SingleDkDto();
    	//String url = "https://data.hualahuala.com/query";
    	//{"contact":[{"contact_name":"配偶","contact_tel":"13540298515","contact_type":3}],"feature_list":["mobile_name","is_real_name","is_in_juxinli_blacklist","mobile_reg_time","moblie_unused_time","call_count","call_passive_count","call_time","call_passive_time","call_first_date","call_last_date","intimate_contact","corp_contact","sms_count","home_offset","work_offset","getui_last_login_time","location_info"],"org_account":"szbyjr","user_id":"30250"}
    	//HttpInvokerUtil.postJson(url, "");
    	String url = "http://127.0.0.1:8080/tk-unionpay-server/dk/single";
//    	Map<String,String> param = new HashMap<String,String>();
//    	param.put("phones", "18683448261");
//    	param.put("contents", "哇咔咔哈喽");
//    	String rs = HttpInvokerUtil.postForm(url, param);
    	String rs = HttpInvokerUtil.postJson(url, JSONObject.toJSONString(s));
    	System.out.println(rs);
    	
	}

}
