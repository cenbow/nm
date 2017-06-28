package com.hs.loan.contract.util;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import sun.misc.BASE64Encoder;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by lenovo on 2016/10/9.
 */
public class VelocityTool {
    public static String imageBase64(byte[] data) throws Exception {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
    public static byte[] createRTF(String templetePath, String templeteName, Map<String,String> replaceMap) throws Exception {
        VelocityContext velocityContext = new VelocityContext();
        if(null!=replaceMap&&replaceMap.size()>0){
            for (Map.Entry<String, String> entry : replaceMap.entrySet()) {
                velocityContext.put(entry.getKey(),entry.getValue());
            }
        }
        Properties ps = new Properties();
        ps.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, templetePath);
        VelocityEngine ve = new VelocityEngine();
        ve.init(ps);
        ve.init();
        Template template = ve.getTemplate(templeteName, "utf-8");
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream, "utf-8"));
        template.merge(velocityContext, writer);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        writer.flush();
        writer.close();
        byteArrayOutputStream.close();
        return bytes;
    }
}
