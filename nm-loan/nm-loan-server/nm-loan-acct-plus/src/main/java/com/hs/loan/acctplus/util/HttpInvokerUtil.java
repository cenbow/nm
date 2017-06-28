package com.hs.loan.acctplus.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
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
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
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
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;



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
	
	public static String executeHttpPost(String url, List<NameValuePair> parameters)
		    throws Exception
		  {
		    BufferedReader in = null;
		    try
		    {
		      HttpClient client = new DefaultHttpClient();
		      HttpPost request = new HttpPost(url);
		      RequestConfig config = RequestConfig.custom()  
		    	        .setConnectTimeout(5000).setConnectionRequestTimeout(5000)  
		    	        .setSocketTimeout(5000).build();  
		      request.setConfig(config);
		      UrlEncodedFormEntity formEntiry = new UrlEncodedFormEntity(parameters, Charset.forName("utf-8"));
		      request.setEntity(formEntiry);
		      HttpResponse response = client.execute(request);

		      in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		      StringBuffer sb = new StringBuffer("");
		      String line = "";
		      String NL = System.getProperty("line.separator");
		      while ((line = in.readLine()) != null) {
		        sb.append(line + NL);
		      }
		      in.close();
		      String result = sb.toString();
		      System.out.println("result" + result);
		      return result;
		    }
		    finally {
		      if (in != null)
		        try {
		          in.close();
		        } catch (Exception e) {
		          e.printStackTrace();
		        }
		    }
		  }
}
