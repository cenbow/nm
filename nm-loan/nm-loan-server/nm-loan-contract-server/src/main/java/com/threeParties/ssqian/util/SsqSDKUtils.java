package com.threeParties.ssqian.util;

import cn.bestsign.sdk.BestSignSDK;
import cn.bestsign.sdk.domain.vo.params.ReceiveUser;
import cn.bestsign.sdk.domain.vo.params.SendUser;
import cn.bestsign.sdk.domain.vo.result.AutoSignbyCAResult;
import cn.bestsign.sdk.domain.vo.result.Continfo;
import cn.bestsign.sdk.integration.Constants;
import cn.bestsign.sdk.integration.Logger;
import cn.bestsign.sdk.integration.exceptions.BizException;
import cn.bestsign.sdk.integration.exceptions.ExecuteException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by lenovo on 2016/6/16.
 */
public class SsqSDKUtils {
//    private static final String mid = "e39e96e249334545bb9bacab84c50e78";
//    private static final String pem ="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKFXFJu9a1cckPIjVPSY0RY/KBxrQLm7IMyiPUFymq/9i6XoPumEAKJ3H2R2TkBbJftT/bCLOLMpDaCGrOEly+D7QbBahJ4p1kP9x9XpnSBShZadvf6zkwskoBpJX5hH+A2R7Vi9Nhb77bwHO5ewgO4z/ifVuOE/CU0u+45VmgfzAgMBAAECgYAvEytuLZyONEWOgQSewZ/wL8lSUXD3QERXsNbSq8+6ne4LIHKKy/srbDp6o5I9cNrqNB2HT3fhvo69Uc5Ley27JPxs6V7f6ewgq1RLwXIF+gtxZ4/0PvcKlnjnwjF2YHFKMhS2nDzUN3cZKQc1B0w/WjimPxj+bLh8fWpmkJ42AQJBAPE8gUDrP1yg5HzAtn4l4spoboyiTJceaErqP39bVWOxhUm0r89kWMhK1vwL0mP7Q1fRaDRBdABuVnmg1kVDFYECQQCrNtO32EaBbvWIueoj3wea5CiyK8pUo9jjyOOOiDP6ozGQnCXuSDECx8GQhfyjEe9s+fMEJOlFsEmbQ17+vN9zAkEAvGlXQk76U+xNyKnzXp9UfGYIQcrZKwZSyoAeS+gXzx9tTN3UmpRzSyw4sO7CydgLBuHFE1szsMp8u85EzB/TAQJAWYecAoDHrZs2Lb76HrPbqDUyyqh3bvcWzVYZGElE1QVNP89HTmjVqByIrWhDCYqxZoz1dO9QRC7A7r22CQL+3QJBAI+Aj0ksepHYxXCdb3D7UwI7RZNXPbCidvEL0Njoirj5awepritm1P5sAzLCqzvr653nxLzqFriwWnoKE5Z4q/c=";
    //private static String host = "https://www.ssqsign.com";
    private static final String mid = "45cf0eb490874ecca32552fbc44dc7b9";
    private static final String pem ="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMQU3VOzXIBSOCzc5j6NLoqddSIOMIolFW08p1XAQ0cgoOCS9ZsIlHu/tZgKBzmC3ZvROIXWZJ7PXRtjBZHVQpbBm8n68WKbW+wwgLcKKuWOir8Keat3E+sU/gMb4d6CczCgA5tXIWAdwFSZIO6uu+FPeR1wg0U7Mm1GuFTfRoOFAgMBAAECgYA04ROXIQXPLV0s7B3DvLtScohGOOFqP/n5TaQrAgCiy+/W4IsP1k6E2PyWFg5AukQdY10E6v6TvYR0gE3eOE8OdCuw4sII4rLXRsz22fheuGNh2wm2U1tLaYnyoXV4f98Tzj91aFhrh5IrNdh4CTQME33c5Pkr/eCC5519dpyLAQJBAOFLrcAzTpDnSnvcBLNHoe2F43igV8EGJxi3GlPUjVOftLPSeXaXUgzvzpndEarvpbLLD4S+EN6KbgzG1+f2CuECQQDeze/QNbJngiGyQ68jpxXYyHQdbah1kvRSpKaf5j0Hqn9Zyu8vGyJQsh+evqLZgG5fYdTAuKWdr0HEPHHPvhElAkEAv2A/ycJLfL9b//aXb4rrvA49edwKbwbA8zemf4tQObayEwY480n7As452210cpV7VXM0TXf+cGt6rBPEl1/jQQJBALt4vJiNFhhSPtgoa22sYY2O3WUFqAGGLV58fFd++0tAAvgi8S7Jvg34UvLXpV8t2bEYOFQRCgmsNcJQudL7MqECQBIH5ol/5/gLtOqDzShX+NrWADFUnbzhWRxrhn/l3YR4DnKenbRDWlmi1TGIZIZt99P6yNfeUwr2sb4SAkgsStI=";
    private static String host = "https://www.bestsign.cn";
    private static String charset = "UTF-8";
    public static String getContractViewURL(String fsdid,String docid) throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        return  init().getContractViewURL(fsdid,docid);
    }
    private static BestSignSDK init(){
        BestSignSDK sdk = BestSignSDK.getInstance(mid, pem, host);
        sdk.setDebugLevel(Logger.DEBUG_LEVEL.INFO);
        sdk.setCharset(charset);
        return sdk;
    }
    public static Continfo[] createContract(byte[] fileData,ReceiveUser[] userlist,SendUser senduser,String fileName) throws Exception {
//        ReceiveUser[] userlist = {new ReceiveUser("1234567@qq.com", "Test1", "13812345678", Constants.USER_TYPE.PERSONAL, Constants.CONTRACT_NEEDVIDEO.NONE, false)};
//        SendUser senduser = new SendUser("22345678@163.com", "Test2", "13912345678", 3, false, Constants.USER_TYPE.PERSONAL, false, "title", "");
        Continfo continfo=null;
        return init().sjdsendcontractdocUpload(userlist, senduser, fileData,fileName);
    }
    //手动签名
    public static String getSignPageSignimage(String fsid,String email,int pagenum,float signx,float signy,String returnurl,Constants.DEVICE_TYPE typedevice) throws Exception {
//        String email = "1234567@qq.com";
//        int pagenum = 1;
//        float signx = 0.001f;
//        float signy = 0.001f;
//        String returnurl = "http://www.baidu.com/";
//        Constants.DEVICE_TYPE typedevice = Constants.DEVICE_TYPE.PC;
        boolean openflagString = true;
        return init().getSignPageSignimagePc(fsid, email, pagenum, signx, signy, returnurl, typedevice, openflagString);
    }
    public static String getContractDownloadURL(String fsid)throws Exception{
        return init().getContractDownloadURL(fsid);
    }
    public static java.util.Map<String,Object> contractInfo(String fsid) throws BizException, NoSuchAlgorithmException, KeyManagementException, IOException, ExecuteException, SignatureException, InvalidKeyException, InvalidKeySpecException {
        return init().contractInfo(fsid);
    }
    //自动签合同
    public static AutoSignbyCAResult autoSignbyCA(String signid, String email, int pagenum, float signx, float signy, boolean openflag) throws Exception{
        return init().AutoSignbyCA(signid, email, pagenum, signx, signy, openflag);
    }
}
