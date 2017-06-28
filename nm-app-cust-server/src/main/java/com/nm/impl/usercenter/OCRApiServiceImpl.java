package com.nm.impl.usercenter;

import com.hs.utils.StringUtils;
import com.nm.base.framework.core.validate.Validator;
import com.nm.mapper.usercenter.BigDataApiMapper;
import com.nm.service.usercenter.OCRApiService;
import com.nm.util.OCRhttpPost;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;


/**
 * Created by lenovo on 2017/5/19.
 */
@Service
public class OCRApiServiceImpl implements OCRApiService{

    private static final Logger log= LoggerFactory.getLogger(OCRApiServiceImpl.class);

    @Autowired
    private BigDataApiMapper bigDataApiMapper;
    @Override
    public void CertNoCheck(String cardNum, String cardName,String phone, MultipartFile file){
        Validator.init(cardNum, "身份证号码").required().isidcard().end();
        Validator.init(cardName, "姓名").required().end();
        MultipartEntity entity=new MultipartEntity();
        String result="";
        try {
            StringBody name = new StringBody(cardName, Charset.forName("UTF-8"));
            StringBody num = new StringBody(cardNum);
            CommonsMultipartFile cf= (CommonsMultipartFile)file;
            DiskFileItem fi = (DiskFileItem)cf.getFileItem();
            File newfile = fi.getStoreLocation();
            FileBody fileBody = new FileBody(newfile);
            entity.addPart("name",name );
            entity.addPart("id_number", num);
            entity.addPart("selfie_file", fileBody);
            result= OCRhttpPost.HttpClientPost(entity);
        } catch (IOException e) {
            log.info("########################身份认证接口失败########################");
            log.info("请求参数:"+entity.toString());
            e.printStackTrace();
            log.info("########################身份认证接口失败########################");
        } finally {
            log.info("########################身份认证接口开始########################");
            log.info("请求参数:"+entity.toString());
            log.info("返回结果:"+result.toString());
            log.info("########################身份认证接口结束########################");
        }

        //处理数据
         JSONObject jsonObject=JSONObject.fromObject(result);
        String status=jsonObject.has("status")?jsonObject.get("status").toString():"";
        //如果认证不成功，存入认证失败
        if(!status.equals("OK")){
            log.info("########################用户"+phone+"OCR认证失败########################");
        }else{
            String identity=jsonObject.has("identity")?jsonObject.get("identity").toString():"";
            if(StringUtils.isNotBlank(identity)){
                JSONObject jsonIdentity=JSONObject.fromObject(identity);
                String reason=jsonIdentity.get("reason").toString();
                if(reason.equals("Gongan status OK")){
                    bigDataApiMapper.isAuthFlag(phone,jsonObject.get("confidence").toString());
                }
            }
        }

    }
}
