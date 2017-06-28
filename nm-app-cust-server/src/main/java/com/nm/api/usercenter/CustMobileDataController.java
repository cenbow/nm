package com.nm.api.usercenter;

import com.nm.base.framework.core.cmd.Command;
import com.nm.core.base.NewBaseClientApiController;
import com.nm.service.usercenter.CustMobileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lenovo on 2017/5/24.
 */
@Controller
@RequestMapping("/api/moblData")
public class CustMobileDataController extends NewBaseClientApiController{

    @Autowired
    private CustMobileDataService custMobileDataService;
    /**
     * 获取客户电话薄
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping("/phoneBook/v1")
    public Command getCustPhoneBook(Command cmd){
        String custNo=cmd.getInString("custNo");
        String phoneKey=cmd.getInString("phoneKey");
        String phoneBook=cmd.getInString("phoneBook");
        custMobileDataService.addCustPhoneBook(custNo,phoneKey,phoneBook);
        return cmd;
    }

}
