package com.nm.api.usercenter;

import com.nm.base.framework.core.cmd.Command;
import com.nm.cmd.BigDataHFiveCmd;
import com.nm.core.base.NewBaseClientApiController;
import com.nm.model.BigDataHFivemodel;
import com.nm.service.usercenter.BigDataApiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/api/bigdata")
public class BigDataApiController extends NewBaseClientApiController {
    @Resource
    private BigDataApiService bigDataApiService;

    /**
     * 调用H5接口
     *
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/bigdatafromh5/v1", method = RequestMethod.POST)
    public Command bigDataFromH5(Command cmd) {
        BigDataHFiveCmd bigDataHFiveCmd = cmd.getIn("bigDataHFiveCmd", BigDataHFiveCmd.class);
        if(bigDataHFiveCmd.getCertNo()==null || bigDataHFiveCmd.getCertNo().equals("")){
            bigDataHFiveCmd.setCertNo(super.getCurrentCertNo());
        }
        if(bigDataHFiveCmd.getCustName()==null || bigDataHFiveCmd.getCustName().equals("")){
            bigDataHFiveCmd.setCustName(super.getAppCustInfo().get(0).getCustName());
        }
        BigDataHFivemodel bigDataH5model = bigDataApiService.bigDataFromHFive(bigDataHFiveCmd);
        cmd.setOut(bigDataH5model);
        return cmd;
    }

    /**
     * 调用芝麻接口
     *
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/bigDataFromzhima/v1", method = RequestMethod.POST)
    public Command bigDataFromzhima(Command cmd) {
        BigDataHFiveCmd bigDataHFiveCmd = cmd.getIn("bigDataHFiveCmd", BigDataHFiveCmd.class);
        if(bigDataHFiveCmd.getCertNo()==null || bigDataHFiveCmd.getCertNo().equals("")){
            bigDataHFiveCmd.setCertNo(super.getCurrentCertNo());
        }
        if(bigDataHFiveCmd.getCustName()==null || bigDataHFiveCmd.getCustName().equals("")){
            bigDataHFiveCmd.setCustName(super.getAppCustInfo().get(0).getCustName());
        }
        BigDataHFivemodel bigDataH5model = bigDataApiService.bigDataFromzhima(bigDataHFiveCmd);
        cmd.setOut(bigDataH5model);
        return cmd;
    }


}
