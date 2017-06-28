package com.nm.api.mb;

import com.hs.loan.busi.dto.LoanProdCalcDto;
import com.hs.utils.StringUtils;
import com.nm.base.framework.core.cmd.Command;
import com.nm.base.framework.core.exception.ParameterException;
import com.nm.cmd.AddCustBaseCmd;
import com.nm.cmd.CustOtherContctInfoCmd;
import com.nm.cmd.CustStudyInfoCmd;
import com.nm.cmd.CustWorkInfoCmd;
import com.nm.core.base.NewBaseClientApiController;
import com.nm.model.*;
import com.nm.service.mb.MbApiService;
import com.nm.service.mb.MbQueryApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author hanfei
 * @describe 医美api
 */
@Controller
@RequestMapping("/api/mb")
public class MbApiController extends NewBaseClientApiController {
    @Autowired
    private MbApiService mbService;
    @Autowired
    private MbQueryApiService mbQueryApiService;

    /**
     * 根据网点编号查询网点信息、网点产品信息、产品费用项信息
     *
     * @param cmd orgNo  网点编号  source 区分二维码还是输入  custType  客户类型  成人/学生
     * @return
     */
    @RequestMapping(value = "/getOrgAndProInfos/v1", method = RequestMethod.POST)
    @ResponseBody
    public Command getOrgAndProInfosByOrgNo(Command cmd) {
        String branchNo = cmd.getInString("branchNo");
        String source = cmd.getInString("source");
        String custType = cmd.getInString("custType");
        String staffNo = cmd.getInString("staffNo");
        Map<String, Object> reMap = mbService.getOrgAndProInfosByOrgNo(staffNo,branchNo, source, custType);
        cmd.setOut(reMap);
        return cmd;
    }

    /**
     * 分期试算
     *
     * @param cmd prodNo 产品编号 loanAmt 分期金额 othFees 费用项 instNum 分期期数
     * @return
     */
    @RequestMapping(value = "/getLoanTrial/v1", method = RequestMethod.POST)
    @ResponseBody
    public Command getLoanTrial(Command cmd) {
        String prodNo = cmd.getInString("prodNo");
        String loanAmt = cmd.getInString("loanAmt");
        String othFees = cmd.getInString("othFees");
        String instNum = cmd.getInString("instNum");
        if (StringUtils.isBlank(loanAmt)) throw new ParameterException("贷款金额不能为空");
        List<LoanProdCalcDto> loanProdCalcDtoList = mbService.getLoanTrial(prodNo, new BigDecimal(loanAmt), Integer.valueOf(instNum), othFees);
        cmd.setOutList(loanProdCalcDtoList);
        return cmd;
    }

    /**
     * 新增客户基本信息,会生成客户编号、贷款编号
     *
     * @param cmd addCustBaseCmd
     * @return 每笔贷款的loanNo和custNo
     */
    @RequestMapping(value = "/addCustBaseInfo/v1", method = RequestMethod.POST)
    @ResponseBody
    public Command addCustBaseInfo(Command cmd) {
        AddCustBaseCmd addCustBaseCmd = cmd.getIn("addCustBaseCmd", AddCustBaseCmd.class);
        String certNo = super.getCurrentCertNo();
        Map<String, Object> map = mbService.addCustBaseInfo(addCustBaseCmd, certNo);
        CustAllInfoModel custAllInfoModel = mbQueryApiService.getCustInfos(map.get("loanNo").toString(), map.get("custNo").toString());
        map.put("custAllInfoModel",custAllInfoModel);
        cmd.setOut(map);
        return cmd;
    }

    /**
     * 新增客户学校信息
     *
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addCustStudyInfo/v1", method = RequestMethod.POST)
    public Command addCustStudyInfo(Command cmd) {
        CustStudyInfoCmd custStudyInfoCmd = cmd.getIn("custStudyInfoCmd", CustStudyInfoCmd.class);
        mbService.addCustStudyInfo(custStudyInfoCmd);
        CustStudyInfoModel custStudyInfoModel=mbQueryApiService.getStudyByCustNo(custStudyInfoCmd.getCustNo());
        cmd.setOut(custStudyInfoModel);
        return cmd;
    }

    /**
     * 新增客户工作信息
     *
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addCustWorkInfo/v1", method = RequestMethod.POST)
    public Command addCustWorkInfo(Command cmd) {
        CustWorkInfoCmd custWorkInfoCmd = cmd.getIn("custWorkInfoCmd", CustWorkInfoCmd.class);
        mbService.addCustWorkInfo(custWorkInfoCmd);
        CustWorkInfoModel custWorkInfoModel=mbQueryApiService.getWorkByCustNo(custWorkInfoCmd.getCustNo());
        cmd.setOut(custWorkInfoModel);
        return cmd;
    }


    /**
     * 新增客户其他联系人信息
     *
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addCustOtherContctInfo/v1", method = RequestMethod.POST)
    public Command addCustOtherContctInfo(Command cmd) {
        List<CustOtherContctInfoCmd> custOtherContctInfoCmdList = cmd.getInArray("custOtherContctInfoCmd", CustOtherContctInfoCmd.class);
        List<CustOtherContctInfoModel> custOtherContctInfoModel = mbService.addCustContctInfo(custOtherContctInfoCmdList);
        cmd.setOut(custOtherContctInfoModel);
        return cmd;
    }

    /**
     * 提交订单信息
     *
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addLoanFileNoInfo/v1", method = RequestMethod.POST)
    public Command addLoanFileNoInfo(Command cmd) {
        String loanNo = cmd.getInString("loanNo");
        String applyAddr = cmd.getInString("applyAddr");
        String custNo = cmd.getInString("custNo");
        mbService.addLoanFileNoInfo(loanNo, super.getAppCustInfoByLoanNo(loanNo), applyAddr,custNo);
        return cmd;
    }

    /**
     * 查询客户信息
     *
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCustInfos/v1", method = RequestMethod.POST)
    public Command getCustInfos(Command cmd) {
        String loanNo = cmd.getInString("loanNo");
        String custNo = cmd.getInString("custNo");
        CustAllInfoModel custAllInfoModel = mbQueryApiService.getCustInfos(loanNo, custNo);
        cmd.setOut(custAllInfoModel);
        return cmd;
    }

    /**
     * 查询客户联系信息
     *
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCustOtherContct/v1", method = RequestMethod.POST)
    public Command getCustOtherContct(Command cmd) {
        String custNo = cmd.getInString("custNo");
        CustContctInfoModel custContctInfoModel = mbQueryApiService.getCustOtherContct(custNo);
        cmd.setOut(custContctInfoModel);
        return cmd;
    }

    /**
     * 查询驳回信息
     *
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCustLoanApprBook/v1", method = RequestMethod.POST)
    public Command getCustLoanApprBook(Command cmd) {
        String loanNo = cmd.getInString("loanNo");
        String custNo = cmd.getInString("custNo");
        List<LoanApprBookModel> loanApprBookModelList = mbQueryApiService.queryCustLoanApprBook(loanNo, custNo);
        cmd.setOut(loanApprBookModelList);
        return cmd;
    }

    /**
     * 获取贷款信息
     *
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getLoanInfo/v1", method = RequestMethod.POST)
    public Command getLoanInfo(Command cmd) {
        String loanNo = cmd.getInString("loanNo");
        CustLoanInfoModel custLoanInfoModel = mbQueryApiService.getLoanInfoByLoanNo(loanNo);
        cmd.setOut(custLoanInfoModel);
        return cmd;
    }

    /**
     * 删除联系人信息
     *
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteCustContctInfo/v1", method = RequestMethod.POST)
    public Command deleteCustContctInfo(Command cmd) {
        String id = cmd.getInString("id");
        String custNo = cmd.getInString("custNo");
        mbQueryApiService.deleteCustContctInfo(id, custNo);
        return cmd;
    }

    /**
     * 查询客户学校信息
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getStudy/v1", method = RequestMethod.POST)
    public Command queryStudyByCustNo(Command cmd){
        String custNo = cmd.getInString("custNo");
        CustStudyInfoModel custStudyInfoModel=mbQueryApiService.getStudyByCustNo(custNo);
        cmd.setOut(custStudyInfoModel);
        return cmd;
    }

    /**
     * 查询客户工作信息
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getWork/v1", method = RequestMethod.POST)
    public Command queryWorkByCustNo(Command cmd){
        String custNo = cmd.getInString("custNo");
        CustWorkInfoModel custWorkInfoModel=mbQueryApiService.getWorkByCustNo(custNo);
        cmd.setOut(custWorkInfoModel);
        return cmd;
    }

    /**
     * 查询模块是否解锁
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getIsLock/v1", method = RequestMethod.POST)
    public Command queryIsLock(Command cmd){
        String custNo=cmd.getInString("custNo");
        String loanNo=cmd.getInString("loanNo");
        Map<String, Object> map=mbQueryApiService.queryIsLock(custNo,loanNo);
        cmd.setOut(map);
        return cmd;
    }
}
