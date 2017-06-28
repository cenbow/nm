package com.nm.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
	private static String cKey = "5334557890327656";
    // 加密
    public static String encrypt(String sSrc) throws Exception {
        if (cKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (cKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = cKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
 
        return Base64.encode(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }
 
    // 解密
    public static String decrypt(String sSrc) throws Exception {
        try {
            // 判断Key是否正确
            if (cKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (cKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = cKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = Base64.decode(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
 
/*    public static void main(String[] args) throws Exception {
        
      
    	List<RepayDetailReqJson> repayDetailReqJsons=new ArrayList<>();
    	RepayDetailReqJson detailReqJson=new RepayDetailReqJson();
    	detailReqJson.setLoanNo("51010160309174239093");
    	detailReqJson.setCurInstNum(3);
    	repayDetailReqJsons.add(detailReqJson);
    	detailReqJson=new RepayDetailReqJson();
    	detailReqJson.setLoanNo("51010160309174239093");
    	detailReqJson.setCurInstNum(4);
    	repayDetailReqJsons.add(detailReqJson);
    	Gson gson=new Gson();
    	System.out.println(gson.toJson(repayDetailReqJsons));
        // 需要加密的字串
        String cSrc = "{\"loanNo\":\"88030200002016042500008\",\"type\":0}";
        System.out.println(cSrc);
        // 加密
        String enString = AESUtil.encrypt(cSrc);
        System.out.println("加密后的字串是：" + enString);

    }*/
}
 

