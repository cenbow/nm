package com.nm.api.usercenter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.nm.base.framework.core.cmd.Command;
import com.nm.base.framework.core.exception.ParameterException;
import com.nm.core.base.NewBaseClientApiController;
import com.nm.service.usercenter.OCRApiService;

/**
 * Created by lenovo on 2017/5/19.
 */
@Controller
@RequestMapping("/api/ocr")
public class OCRApiController extends NewBaseClientApiController {


    @Autowired
    private OCRApiService ocrApiService;
    /**
     * orc身份一致性校验
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkOrc/v1")
    public Command CertNoCheck(Command cmd, HttpServletRequest request){
        String cardNum=cmd.getInString("cardNum");
        String cardName=cmd.getInString("cardName");
        String phone=cmd.getInString("phone");
        if (request instanceof MultipartHttpServletRequest) {
            MultipartFile file =  ((MultipartHttpServletRequest) request).getFile("file");
            ocrApiService.CertNoCheck(cardNum,cardName,phone,file);
        }else{
            throw new ParameterException("非MultipartFile请求，请加上multipart/form-data");
        }
        return cmd;
    }
}
