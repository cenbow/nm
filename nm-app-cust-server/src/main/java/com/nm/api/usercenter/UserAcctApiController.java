package com.nm.api.usercenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hs.base.entity.UserProfile;
import com.hs.loan.busi.dto.LoanAttInDto;
import com.hs.utils.StringUtils;
import com.nm.api.frame.auth.model.AppCustInfo;
import com.nm.base.framework.core.cmd.Command;
import com.nm.cmd.OCLoanAttInDto;
import com.nm.cmd.RepayCmd;
import com.nm.core.base.NewBaseClientApiController;
import com.nm.service.usercenter.UserAcctApiService;

/**
 * 
 * 
 * @author wangqin
 *
 * @date 2017年5月10日 下午9:11:54
 */
@Controller
@RequestMapping("/api/acct")
public class UserAcctApiController extends NewBaseClientApiController {

    @Resource
    private UserAcctApiService userAcctApiService;

    /**
     * 查询客户账单 未还款
     *
     * @param cmd
     * @return
     */
    @RequestMapping(value = "/queryUserAcct/v1", method = RequestMethod.POST)
    @ResponseBody
    public Command queryUserAcct(Command cmd) {
    	List<AppCustInfo> app=super.getAppCustInfo();
        List<Map<String, Object>> retMap = userAcctApiService.queryAcctInstv1(app);
        cmd.setOut(retMap);
        return cmd;
    }

    /**
     * 查询客户账单 已还款
     *
     * @param cmd
     * @return
     */
    @RequestMapping(value = "/queryUserAcct/v2", method = RequestMethod.POST)
    @ResponseBody
    public Command queryUserAcctV2(Command cmd) {
        List<Map<String, Object>> retMap = userAcctApiService.queryAcctInstv2(super.getAppCustInfo());
        cmd.setOut(retMap);
        return cmd;
    }

    /**
     * 查询账单详情---暂时不用
     *
     * @param cmd
     * @return
     */
    @RequestMapping(value = "/queryLoanDetail/v1", method = RequestMethod.POST)
    @ResponseBody
    public Command queryLoanDetail(Command cmd) {
        String loanNo = cmd.getInString("loanNo");
        String num=cmd.getInString("num");
        Map<String, Object> retMap = userAcctApiService.queryLoanAcct(loanNo,num);
        cmd.setOut(retMap);
        return cmd;
    }
    
    
    /**
     * 查询账单详情
     *
     * @param cmd
     * @return
     */
    @RequestMapping(value = "/queryLoanDetail/v2", method = RequestMethod.POST)
    @ResponseBody
    public Command queryLoanDetail2(Command cmd) {
        String loanNo = cmd.getInString("loanNo");
     
        Map<String, Object> retMap = userAcctApiService.queryLoanAcct2(loanNo);
        cmd.setOut(retMap);
        return cmd;
    }
    

    /**
     * 查询我的订单
     *
     * @param cmd
     * @return
     */
    @RequestMapping(value = "/queryMyLoan/v1", method = RequestMethod.POST)
    @ResponseBody
    public Command queryMyLoan(Command cmd) {
        List<Map<String, String>> retMap = userAcctApiService.queryMyLoan(super.getAppCustInfo());
        cmd.setOut(retMap);
        return cmd;
    }

    /**
     * 扣款 ---中金
     * @param cmd
     * @return
     */
    @RequestMapping(value = "/dkRepayLoan/v1", method = RequestMethod.POST)
    @ResponseBody
    public Command dkRepayLoan(Command cmd){
        @SuppressWarnings("rawtypes")
		List<Map> rqMap1=new ArrayList<>();
        Map<String,String> rqMap=cmd.getInMap("dkInfo");
        rqMap1.add(rqMap);
        List<RepayCmd> repayCmds=userAcctApiService.dkRepayLoan(rqMap1,super.getCurrentAppUserInfo());
        cmd.setOut(repayCmds);
        return cmd;
    }
    
   
    
    /**
     * 签约
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/buidContractSsq/v1", method = RequestMethod.POST)
    public Command buidContantSsqV1(Command cmd) {
        Map<String, String> rqMap = cmd.getInMap("contractInfo");
        
        UserProfile userProfile = new UserProfile();
        
        Map<String, Object> retMap = userAcctApiService.buidContantSsq(userProfile, rqMap);
        cmd.setOut(retMap);
        return cmd;
    }

    /**
     * 查询是否已填写签约电话
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/isAddSsqPhone/v1", method = RequestMethod.POST)
    public Command isAddSsqPhone(Command cmd) {
        String loanNo = cmd.getInString("loanNo");
        @SuppressWarnings("rawtypes")
		Map retMap = userAcctApiService.isAddSsqPhone(loanNo);
        cmd.setOut(retMap);
        return cmd;
    }
    /**
     * 重签
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping(value= "/reContantSsq/v1", method = RequestMethod.POST)
    public Command reContantSsq(Command cmd){
        String loanNo=cmd.getInString("loanNo");
        userAcctApiService.reContantSsq(loanNo);
        return cmd;
    }

  

    /**
     * 附件查询---提货照
     * @param cmd
     * @return
     */
    @RequestMapping(value = "/queryLoanAtt/v1", method = RequestMethod.POST)
    @ResponseBody
    public Command queryLoanAttInfo(Command cmd) {
        String loanNo=cmd.getInString("loanNo");
        LoanAttInDto retlist=userAcctApiService.queryAttachment(loanNo);
        cmd.setOut(retlist);
        return cmd;
    }
    /**
     * 删除附件
     * @param cmd
     * @return
     */
    @RequestMapping(value = "/deteleLoanAtt/v1", method = RequestMethod.POST)
    @ResponseBody
    public Command deteleLoanAttInfo(Command cmd) {
        String loanNo=cmd.getInString("loanNo");
        String attType=cmd.getInString("attType");
        String id=cmd.getInString("id");
        userAcctApiService.deleteAttachment(loanNo, attType, id);
        return cmd;
    }




    /**
     * 上传附件
     */
    @RequestMapping(value = "/saveLoanAtt/v3", method = RequestMethod.POST)
    @ResponseBody
    public Command saveLoanAttInfov3(HttpServletRequest request, Command cmd) {
        String loanNo=cmd.getInString("loanNo");
        String attType=cmd.getInString("attType");
        String custNo=cmd.getInString("custNo");
        OCLoanAttInDto loanAttInDto=new OCLoanAttInDto();
        if (request instanceof MultipartHttpServletRequest) {
            MultipartFile file =  ((MultipartHttpServletRequest) request).getFile("file");
            UserProfile userProfile = new UserProfile();
            loanAttInDto=userAcctApiService.uploadAttachmentTemp(file, custNo, loanNo, attType,userProfile);
        }else{
            String ret="非MultipartFile请求，请加上multipart/form-data";
            cmd.setOut(ret);
        }
        cmd.setOut(loanAttInDto);
        return cmd;
    }
    /**
     * 附件查询
     * @param cmd
     * @return
     */
    @RequestMapping(value = "/queryLoanAtt/v2", method = RequestMethod.POST)
    @ResponseBody
    public Command queryLoanAttInfov2(Command cmd) {
        String loanNo=cmd.getInString("loanNo");
        List<OCLoanAttInDto> retlist=userAcctApiService.queryAttachmentv2(loanNo);
        cmd.setOut(retlist);
        return cmd;
    }
    
    
    /**
     * 附件查询
     * @param cmd
     * @return
     */
    @RequestMapping(value = "/queryLoanAtt/v3", method = RequestMethod.POST)
    @ResponseBody
    public Command queryLoanAttInfov3(Command cmd) {
        String loanNo=cmd.getInString("loanNo");
        
        List<String> attype = cmd.getInArray("attype", String.class);
        List<OCLoanAttInDto> retlist=userAcctApiService.queryAttachmentv3(loanNo,attype);
        cmd.setOut(retlist);
        return cmd;
    }
    
    /**
     * 查询合同地址
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryContractUrl/v1", method = RequestMethod.POST)
    public Command queryContractUrl(Command cmd) {
        String loanNo=cmd.getInString("loanNo");
        String url = userAcctApiService.queryContractUrl(loanNo);
        cmd.setOut("contractUrl",url);
        return cmd;
    }


    /**
     * 封装扣款数据-------暂时不用 支付宝，微信
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/payMsg/v1", method = RequestMethod.POST)
    public Command payMsg(Command cmd) {
        String loanNo=cmd.getInString("loanNo");
        String payType=cmd.getInString("payType");
        String planId=cmd.getInString("planId");
        String repayNum=cmd.getInString("repayNum");
        String tranChan=cmd.getInString("tranChan");
        String buyerId=cmd.getInString("buyerId");
        String payFlag=cmd.getInString("payFlag");
        String openId=cmd.getInString("openId");
        if(StringUtils.isBlank(openId)){
            openId=super.getCurrentPrincipal().getAppUserInfo().getOpenId();
        }
        Map<String,String> map = userAcctApiService.payMsg(loanNo,payType,repayNum,tranChan,planId,buyerId,openId,payFlag);
        cmd.setOut(map);
        return cmd;
    }

    /**
     * 获取buyerI
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAliBuyerId/v1", method = RequestMethod.POST)
    public Command getBuyerId(Command cmd) {
        String url=cmd.getInString("rspUrl");
        String rspUrl = userAcctApiService.getBuyerId(url);
        return cmd;
    }

    /**
     * 获取buyerId
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getWxOpenid/v1", method = RequestMethod.POST)
    public Command getWxOpenid(Command cmd) {
        String url=cmd.getInString("rspUrl");
        String rspUrl = userAcctApiService.getOpenid(url);
        return cmd;
    }

    /**
     * 三方付款后的写入三方扣款信息表
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/threePay/v1", method = RequestMethod.POST)
    public Command threePay(Command cmd) {
        Map<String,Object> rqMap=cmd.getInMapObject("payInfo");
        String rspUrl = userAcctApiService.threePayInfo(rqMap);
        return cmd;
    }

    /**
     * 提交订单评价接口 --需要传custNo
     * @param cmd
     * @return
     */
   @ResponseBody
    @RequestMapping(value = "/addEvaluate/v1", method = RequestMethod.POST)
    public Command evaluate(Command cmd) {
        Map<String,Object> rqMap=cmd.getInMapObject("evaluateInfo");
        String rspUrl = userAcctApiService.evaluate(rqMap);
        return cmd;
    }

    /**
     * 查询订单评价接口
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getEvaluate/v1", method = RequestMethod.POST)
    public Command getEvaluate(Command cmd) {
        String loanNo=cmd.getInString("loanNo");
        Map<String,Object> map = userAcctApiService.getEvaluate(loanNo);
        cmd.setOut("retMap",map);
        return cmd;
    }


    /**
     * 查询订单评价状态
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getEvaluateStat/v1", method = RequestMethod.POST)
    public Command getEvaluateStat(Command cmd) {
        String loanNo=cmd.getInString("loanNo");
        Boolean stat=false;
        Map<String,Object> map = userAcctApiService.getEvaluate(loanNo);
        if(map!= null && map.size()>0){
            stat=true;
        }
        cmd.setOut("isEvaluate",stat.toString());
        return cmd;
    }
}
