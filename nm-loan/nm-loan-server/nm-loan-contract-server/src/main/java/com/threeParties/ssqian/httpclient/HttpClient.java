package com.threeParties.ssqian.httpclient;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.client.ClientProtocolException;

public interface HttpClient {

	public void setUrl(String urlStr) throws MalformedURLException;

	public void setHttpVersion(String httpVersion);

	public void setHttpContentCharset(String encoding);

	public void setRequestTimeoutInMillis(int requestTimeoutInMillis);

	public void setHttpMethod(String httpMethod) throws HttpException;

	public void addHeader(String name, String value);

	public void addHeaderObj(Map<String, Object> header_data);

	public void setEntity(String content, String contentType, String charset) throws IOException;

	public void setEntity(String fileName) throws IOException;

	public void execute() throws ClientProtocolException, IOException;

	public String getStatusLine();

	public int getStatusCode();

	public String getContentType();

	public Header[] getAllHeaders();

	public String getContentStr() throws IOException;

	public void abortExecution();

	public void shutdown();

}
