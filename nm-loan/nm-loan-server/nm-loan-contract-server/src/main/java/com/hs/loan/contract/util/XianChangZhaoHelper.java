package com.hs.loan.contract.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * 从服务器获取现场照帮助类
 * @author zwr
 *
 */
public class XianChangZhaoHelper {
		
		/**
		 * 从文件服务器获取现场照输入流
		 * @param datas
		 * @return
		 */
		public static StringBuffer getXianChangZhaoFromFileServer(String serverAddr){
		 try {
			CloseableHttpClient httpclient = HttpClients.createDefault();  
	        HttpGet httpget = new HttpGet(serverAddr);  
	        HttpResponse response  = httpclient.execute(httpget);
	        System.out.println(response.getStatusLine());
	        StringBuffer rest = new StringBuffer();

	        HttpEntity entity = response.getEntity();  
	        InputStream in = entity.getContent();
	        
	      /*  File file = new File("C:/a.jpg");  
		       
            FileOutputStream fout = new FileOutputStream(file);  
            int l = -1;  
            byte[] tmp = new byte[1024];  
            while ((l = in.read(tmp)) != -1) {  
                fout.write(tmp, 0, l);  
                // 注意这里如果用OutputStream.write(buff)的话，图片会失真，大家可以试试  
            }  
            fout.flush();  
            fout.close();  */
	        //File file = new File(destFileName);  
	        
	        int i = 0;
			while ((i = in.read()) != -1) {
			    String str = i < 16 ? "0" + Integer.toHexString(i) : Integer.toHexString(i);
			    rest.append(str);
			}
			return rest;
			} catch (IOException e) {
				e.printStackTrace();
		
			}
		 	return null;  
	       
		}
	
	
}
