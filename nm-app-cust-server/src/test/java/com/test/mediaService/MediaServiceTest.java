package com.test.mediaService;

import com.lemon.codec.AesEncrypt;
import com.nm.base.framework.core.test.SpringJunitTest;
import com.nm.base.wechat.service.MediaService;
import com.nm.cmd.AddCustBaseCmd;
import com.nm.util.HttpClientTool;
import net.coobird.thumbnailator.Thumbnails;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class MediaServiceTest extends SpringJunitTest {

    @Autowired
    private MediaService mediaService;


    @Test
    public void download() {

        String accessToken = "Ive62Slk75OdRfDfgFsvxKVGofKKF2rVny2AuWjUlLkFNXRbLV77svCOQuxXFVHD8k-F4R5rklOGCU2XxHM_8NXAjubP4uKp5GXWkOpY86yiahXRshiteUUeL47x1lSPCRTbAFAHWY";
        String mediaId = "zj3hZYhpvJ8127CRt6d2uAQlNd2RImveJLvQNr6qoqou-1kzAPZfhuDLA15RUXQI";
        MultipartFile mediaMultipartFilter = mediaService.getMediaMultipartFilter(accessToken, mediaId);
        System.out.println(mediaMultipartFilter.getSize());
    }

    @Test
    public void handle() throws IOException {
        File file = new File("D:\\images\\0094f5c5-8fdb-4e70-9e90-a93b1b8f39c9.jpg");
        BufferedImage thumbnail = Thumbnails.of(file)
                .scale(0.25f)
                .asBufferedImage();
        ImageIO.write(thumbnail, "jpg", new File("D:\\images\\slt.jpg"));
    }

    public static void main(String[] args) throws Exception {

        /*HttpClientTool httpClientUtil = new HttpClientTool();
        String url = "http://120.25.162.63:7077/lemonmsg/voice/voicemsg";
        HashMap rqmap = new HashMap();
        rqmap.put("phonenum", "18161293713");
        rqmap.put("vfcode", "5621");
        //rqmap.put("isEnc","true");
        JSONObject json = JSONObject.fromObject(rqmap);
        String data = AesEncrypt.encrypt(json.toString(), "0440764605F2D5C8096477D44AB9D953");
        System.out.println(json.toString());
        System.out.println(data);
        rqmap.clear();
        rqmap.put("data", data);
        String result = httpClientUtil.sendHttpPost(url, rqmap);
        System.out.println(result);*/
        String value="{'custBankAcctCmd':{'acctName':null,'acctNo':null,'bankName':null,'custNo':null,'id':null,'openCity':null,'openOrg':null,'openProv':null},'custContctCmd':{'custNo':null,'id':null,'qq':null},'custinfocmd':{'certIssuOrg':null,'certNo':null,'certValidDate':null,'custName':null,'custNo':null,'custType':'40101002','educ':null,'ethnic':null,'marriage':null,'phoneNo':null,'regAddr':null,'regArea':null,'regCity':null,'regProv':null},'custliveinfocmd':{'custNo':null,'id':null,'liveAddr':null,'liveArea':null,'liveCity':null,'liveProv':null},'loanacctincmd':{'branchNo':'dmbranch','custNo':null,'custType':'40101002','fstPayAmt':'0','goodDto':{'brand':'uhg','goodsType':'20200004','marques':null,'pric':'123'},'id':null,'instNum':'9','loanAmt':'3333','loanNo':null,'prodNo':'8af4a13a5921c3fb0159622078ce10dd','strSeleFees':'2601|2001'},'saleSource':'000'}";
        AddCustBaseCmd str=com.alibaba.fastjson.JSONObject.parseObject(value, AddCustBaseCmd.class);
        System.out.println(str.toString());


    }
}
