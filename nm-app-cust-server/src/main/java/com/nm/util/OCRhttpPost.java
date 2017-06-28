package com.nm.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Created by lenovo on 2017/5/19.
 */
public class OCRhttpPost {

    public static final String api_id = "5b3c0b98bf71497a8a09ed506dbdc746";
    public static final String api_secret = "148ba864fc2d473283d1d676a33c4654";
    public static final String POST_URL = "https://cloudapi.linkface.cn/identity/selfie_idnumber_verification";


    public static String HttpClientPost(MultipartEntity entity) throws ClientProtocolException, IOException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost post = new HttpPost(POST_URL);
        StringBody id = new StringBody(api_id);
        StringBody secret = new StringBody(api_secret);
        /*StringBody name = new StringBody(username, Charset.forName("UTF-8"));
        StringBody number = new StringBody(id_number);
        FileBody fileBody = new FileBody(new File(filepath));*/

        entity.addPart("api_id", id);
        entity.addPart("api_secret", secret);
        /*entity.addPart("name", name);
        entity.addPart("id_number", number);
        entity.addPart("selfie_file", fileBody);*/
        post.setEntity(entity);
        String result="";
        String line="";
        HttpResponse response = httpclient.execute(post);
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entitys = response.getEntity();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(entitys.getContent()));
            line = reader.readLine();
            System.out.println(line);
        }else{
            HttpEntity r_entity = response.getEntity();
            String responseString = EntityUtils.toString(r_entity);
            System.out.println("错误码是："+response.getStatusLine().getStatusCode()+"  "+response.getStatusLine().getReasonPhrase());
            System.out.println("出错原因是："+responseString);
            //你需要根据出错的原因判断错误信息，并修改
        }
        result=response.toString();
        httpclient.getConnectionManager().shutdown();
        return line;
    }


    public void main(String[] args) throws ClientProtocolException, IOException {
        MultipartEntity entity=new MultipartEntity();
        StringBody name = new StringBody("韩飞",Charset.forName("UTF-8"));
        StringBody num = new StringBody("32032219841013071X");
        FileBody fileBody = new FileBody(new File("D:/C/1111.jpg"));
        entity.addPart("name",name );
        entity.addPart("id_number", num);
        entity.addPart("selfie_file", fileBody);
        HttpClientPost(entity);
    }
}
