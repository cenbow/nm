package com.hs.loan.contract.server;

import cn.bestsign.sdk.domain.vo.params.ReceiveUser;
import cn.bestsign.sdk.domain.vo.params.SendUser;
import cn.bestsign.sdk.domain.vo.result.AutoSignbyCAResult;
import cn.bestsign.sdk.domain.vo.result.Continfo;
import cn.bestsign.sdk.integration.Constants;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.commons.attach.Attachment;
import com.hs.commons.attach.AttachmentApi;
import com.hs.commons.attach.OssUtil;
import com.hs.commons.attach.tansfer.IFileTransfer;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.commons.tools.SpringContextHolder;
import com.hs.loan.approv.dto.LoanFundMatchDto;
import com.hs.loan.approve.api.LoanFundMatchApi;
import com.hs.loan.busi.dto.*;
import com.hs.loan.contract.api.AppContractSignApi;
import com.hs.loan.contract.contant.ContractContant;
import com.hs.loan.contract.entity.AppContractSignInfo;
import com.hs.loan.contract.service.AppContractSignInfoService;
import com.hs.loan.contract.util.*;
import com.hs.loan.cust.api.*;
import com.hs.loan.cust.dto.*;
import com.hs.loan.market.api.BranchApi;
import com.hs.loan.market.dto.BranchDto;
import com.hs.loan.operation.entity.PubLoanContractFile;
import com.hs.loan.operation.service.PubLoanContractFileService;
import com.hs.loan.sale.api.LoanAcctApi;
import com.hs.loan.sale.api.LoanAttApi;
import com.hs.loan.sale.api.LoanViewApi;
import com.hs.utils.*;
import com.hs.utils.StringUtils;
import com.threeParties.ssqian.util.SsqSDKUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.math.BigDecimal;
import java.rmi.ServerException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;

/**
 * Created by lenovo on 2016/7/5.
 */
@Service
@Transactional(readOnly=true)
public class AppContractBuildServer implements AppContractSignApi {
    @Autowired
    private IFileTransfer fileTransfer;

    @Autowired
    private LoanAcctApi contractLoanAcctService;
    @Autowired
    private CustInfoApi contractCustInfoService;
    @Autowired
    private CustLiveInfoApi custLiveInfoService;
    @Autowired
    private LoanAttApi contractLoanAttService;
    @Autowired
    private CustContctInfoApi contractCustContctInfoService;
    @Autowired
    private CustWorkApi custWorkService;
    @Autowired
    private CustViewApi contractCustViewService;
    @Autowired
    private LoanViewApi contractLoanViewService;
    //	@Autowired
//	private LoanContractApi loanContractService;
    @Autowired
    private PubLoanContractFileService pubLoanContractFileService;
    @Autowired
    private AppContractSignInfoService contractSignInfoService;
    @Autowired
    private CustOtherInfoApi custOtherInfoService;
    @Autowired
    private BranchApi branchService;
    @Autowired
    private LoanFundMatchApi loanFundMatchService;


    protected Logger logger = LoggerFactory.getLogger(ContractBuidServer.class);

    /**
     *@describe 电子合同重置
     *@author txia
     *datetime 2016/9/5 10:47
     *params {loanNo:贷款编号}
     *return int 大于0重置成功
     */
    @Transactional
    public int resetSsq(String loanNo){
        if(org.apache.commons.lang.StringUtils.isBlank(loanNo)){
            throw new ServiceException("贷款编号不能为空");
        }
        Map paramMap=new HashMap<String,Object>();
        paramMap.put("loanNo",loanNo);
        return contractSignInfoService.deleteContractByLoanNo(paramMap);
    }

    /**
     *@describe 合同详细信息
     *@author txia
     *datetime 2016/8/30 16:40
     *params {loanNo:贷款编号}
     *return java.util.List<java.util.Map>
     */
    public List<Map> getContractInfoDetail(String signId){
        if(org.apache.commons.lang.StringUtils.isBlank(signId)){
            throw new ServiceException("合同编号不能为空");
        }
        HashMap<String,Object> paramMap=new HashMap<>();
        paramMap.put("loanNo",signId);
        //paramMap.put("contract_no",signId);
        return contractSignInfoService.fileIsUploadSsq(paramMap);
    }

    /**
     * 查询尚尚签是否已经签名
     * @param loanNo 贷款编号
     * @param signType 签名类型(cust sale branch)
     * @param userProfile 用户信息
     * @return boolean(true已经签名 false未签名)
     * @throws ServiceException
     * @throws AppException
     */
    @Transactional
    public boolean isSign(String loanNo,String signType,UserProfile userProfile)throws ServiceException,AppException{
        boolean result=false;
        if(null==signType){
            throw new ServiceException("签名类型不能为空!");
        }
        Map map = this.getContractMap(loanNo);
        String fsdid=map.get("contract_no").toString();
        String mobile=null;
        if(null!=signType&&"cust".equals(signType)){
            LoanAcctOutDto loanAcctOutDto = contractLoanAcctService.getLoanAcct(loanNo, userProfile);
            CustInfoDto custInfoDto = contractCustInfoService.getByNo(loanAcctOutDto.getCustNo());
            mobile=custInfoDto.getPhoneNo();
        }else if(null!=signType&&"sale".equals(signType)){
            java.util.Map staffMoblMap=new HashMap<String,Object>();
            staffMoblMap.put("staffNo",userProfile.getStaffNo());
            //销售人员电话号码
            Map staffMoblResultMap = contractSignInfoService.getStaffMobl(staffMoblMap);
            //销售人员电话号码
            mobile=(null==staffMoblResultMap.get("MOBL_NO"))?null:staffMoblResultMap.get("MOBL_NO").toString();
        }else if(null!=signType&&"branch".equals(signType)){
            java.util.Map branchMoblMap=new HashMap<String,Object>();
            branchMoblMap.put("loanNo",loanNo);
            //商户电话号码
            Map branchMoblResultMap = contractSignInfoService.getBranchMobl(branchMoblMap);
            mobile = (null==branchMoblResultMap.get("CONTCT_TEL"))?null:branchMoblResultMap.get("CONTCT_TEL").toString();
        }
        if(null==mobile){
            throw new ServiceException("电话号码不能为空");
        }
        try {
            ObjectMapper objectMapper=new ObjectMapper();
            Map<String, Object> ssqContractInfoMap = SsqSDKUtils.contractInfo(fsdid);
           /* Map<String, Object> ssqContractInfoMap = null;*/
            String response = ssqContractInfoMap.get("response").toString();
            if(null==response){
                throw new ServiceException("查询合同接口为空!");
            }
            ObjectNode jsonNodes = objectMapper.readValue(response, ObjectNode.class);
            JsonNode path = jsonNodes.path("response").path("content").path("userlist");
            if(path.isArray()){
                for (JsonNode jsonNode : path) {
                    JsonNode sendMobile = jsonNode.path("userinfo").path("mobile");
                    JsonNode status = jsonNode.path("userinfo").path("status");
                    if(mobile.equals(sendMobile.asText().trim())){
                        //1表示未签 2表示已签
                        if("2".equals(status.asText().trim())){
                            result=true;
                            this.dealSsqReturn(signType,"100000",fsdid);
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查询合同接口异常");
        }
        return result;
    }
    public String getContractViewURL(String loanNo)throws ServiceException{
        Map map = this.getContractMap(loanNo);
        String fsdid=map.get("contract_no").toString();
        String docid=map.get("docid").toString();
        try {
            return SsqSDKUtils.getContractViewURL(fsdid,docid);
        } catch (Exception e) {
            throw new ServiceException("获取签名预览界面失败");
        }
    }

    private  Map getContractMap(String loanNo) {
        if(null==loanNo||"".equals(loanNo.trim())){
            throw new ServiceException("贷款编号不能为空!");
        }
        Map contractMap=new HashMap<String,Object>();
        contractMap.put("loanNo", loanNo);
        Map map = contractSignInfoService.getContractByLoanNo(contractMap);
        if(null==map.get("contract_no")||null==map.get("docid")){
            throw new ServiceException("合同编号或文档编号为空!");
        }
        return map;
    }
    @Transactional
    public int dealSsqReturn(String signType,String code,String signID){
        java.util.Map updateSignStatusMap=new HashMap<String,Object>();
        updateSignStatusMap.put("contractNo", signID);
        updateSignStatusMap.put("signType", signType);
        updateSignStatusMap.put("status", "1");
        List<Map> maps = contractSignInfoService.selectcontractNo(updateSignStatusMap);
        if(null!=code&&"100000".equals(code)&&null!=maps&&!maps.isEmpty()){
            return contractSignInfoService.updateSignStatus(updateSignStatusMap);
            //return contractSignInfoService.updateAcctStat(updateSignStatusMap);
        }else{
            return 0;
        }
    }
    public String getSsqSignUrl(String loanNo,String signType,String terminal,UserProfile userProfile) throws Exception {
        if(null==loanNo){
            throw new ServiceException("贷款编号不能为空");
        }
        java.util.Map contractMap=new HashMap<String,Object>();
        contractMap.put("loanNo", loanNo);
        String contractNoByLoanNo = contractSignInfoService.getContractNoByLoanNo(contractMap);
        if(null==contractNoByLoanNo){
            throw new ServiceException("合同还未生成");
        }
        String mobile=null;
        int pagenum=0;
        float x=0;
        float y=0;
        String returnUrl=null;
        Map xyPageMap = getXYPage(loanNo);
        if(null!=signType&&"cust".equals(signType)){
            LoanAcctOutDto loanAcctOutDto = contractLoanAcctService.getLoanAcct(loanNo, userProfile);
            CustInfoDto custInfoDto = contractCustInfoService.getByNo(loanAcctOutDto.getCustNo());
            mobile=custInfoDto.getPhoneNo();

            Object c_sign_x = xyPageMap.get("C_SIGN_X");
            if(null==c_sign_x){
                throw new ServerException("用户签名的x坐标为空");
            }

            Object c_sign_y = xyPageMap.get("C_SIGN_Y");
            if(null==c_sign_y){
                throw new ServerException("用户签名的y坐标为空");
            }

            Object c_sign_page = xyPageMap.get("C_SIGN_PAGE");
            if(null==c_sign_page){
                throw new ServerException("用户签名的页数为空");
            }

            pagenum=Integer.valueOf(c_sign_page.toString()).intValue();
            x=Float.valueOf(c_sign_x.toString()).floatValue();
            y=Float.valueOf(c_sign_y.toString()).floatValue();
            returnUrl= ParamUtils.getParam("custreturnurl");

        }else if(null!=signType&&"sale".equals(signType)){
            java.util.Map staffMoblMap=new HashMap<String,Object>();
            staffMoblMap.put("staffNo",userProfile.getStaffNo());
            //销售人员电话号码
            Map staffMoblResultMap = contractSignInfoService.getStaffMobl(staffMoblMap);
            //销售人员电话号码
            mobile=(null==staffMoblResultMap.get("MOBL_NO"))?null:staffMoblResultMap.get("MOBL_NO").toString();
            pagenum=Integer.valueOf(xyPageMap.get("SIGN_PAGE").toString()).intValue();
            x=(float)Double.valueOf(xyPageMap.get("SIGN_X").toString()).doubleValue();
            y=(float)Double.valueOf(xyPageMap.get("SIGN_Y").toString()).doubleValue();
            returnUrl=ParamUtils.getParam("salereturnurl");
        }else if(null!=signType&&"branch".equals(signType)){
            java.util.Map branchMoblMap=new HashMap<String,Object>();
            branchMoblMap.put("loanNo",loanNo);
            //商户电话号码
            Map branchMoblResultMap = contractSignInfoService.getBranchMobl(branchMoblMap);
            mobile = (null==branchMoblResultMap.get("CONTCT_TEL"))?null:branchMoblResultMap.get("CONTCT_TEL").toString();
            pagenum=Integer.valueOf(xyPageMap.get("SHOP_SIGN_PAGE").toString()).intValue();
            x=(float)Double.valueOf(xyPageMap.get("SHOP_SIGN_X").toString()).doubleValue();
            y=(float)Double.valueOf(xyPageMap.get("SHOP_SIGN_Y").toString()).doubleValue();
            returnUrl=ParamUtils.getParam("branchreturnurl");
        }
        if(null==mobile){
            throw new ServiceException("电话号码不能为空");
        }
        Constants.DEVICE_TYPE deviceType = Constants.DEVICE_TYPE.MOBILE;
        if(null!=terminal&&"pc".equals(terminal)){
            deviceType=Constants.DEVICE_TYPE.PC;
        }
        //mobile="18702876708";
        String signPageSignimage = SsqSDKUtils.getSignPageSignimage(contractNoByLoanNo,mobile, pagenum, x, y, returnUrl,deviceType);
        return signPageSignimage;
    }
    public String getSsqSignUrl(String loanNo,String contractNo,String signType,String terminal,UserProfile userProfile) throws Exception {
        if(null==loanNo){
            throw new ServiceException("贷款编号不能为空");
        }
        java.util.Map contractMap=new HashMap<String,Object>();
        contractMap.put("loanNo", loanNo);
        String mobile=null;
        int pagenum=0;
        float x=0;
        float y=0;
        String returnUrl=null;
        //分期资金匹配 对象
        Map xyPageMap = getXYPage(loanNo);
        if(null!=signType&&"cust".equals(signType)){
            LoanAcctOutDto loanAcctOutDto = contractLoanAcctService.getLoanAcct(loanNo, userProfile);
            CustInfoDto custInfoDto = contractCustInfoService.getByNo(loanAcctOutDto.getCustNo());
            mobile=custInfoDto.getPhoneNo();
            pagenum=Integer.valueOf(xyPageMap.get("C_SIGN_PAGE").toString()).intValue();
            x=(float)Double.valueOf(xyPageMap.get("C_SIGN_X").toString()).doubleValue();
            y=(float)Double.valueOf(xyPageMap.get("C_SIGN_Y").toString()).doubleValue();
            returnUrl=ParamUtils.getParam("custreturnurl");
        }else if(null!=signType&&"sale".equals(signType)){
            java.util.Map staffMoblMap=new HashMap<String,Object>();
            staffMoblMap.put("staffNo",userProfile.getStaffNo());
            //销售人员电话号码
            Map staffMoblResultMap = contractSignInfoService.getStaffMobl(staffMoblMap);
            //销售人员电话号码
            mobile=(null==staffMoblResultMap.get("MOBL_NO"))?null:staffMoblResultMap.get("MOBL_NO").toString();
            pagenum=Integer.valueOf(xyPageMap.get("SIGN_PAGE").toString()).intValue();
            x=(float)Double.valueOf(xyPageMap.get("SIGN_X").toString()).doubleValue();
            y=(float)Double.valueOf(xyPageMap.get("SIGN_Y").toString()).doubleValue();
            returnUrl=ParamUtils.getParam("salereturnurl");
        }else if(null!=signType&&"branch".equals(signType)){
            java.util.Map branchMoblMap=new HashMap<String,Object>();
            branchMoblMap.put("loanNo",loanNo);
            //商户电话号码
            Map branchMoblResultMap = contractSignInfoService.getBranchMobl(branchMoblMap);
            mobile = (null==branchMoblResultMap.get("CONTCT_TEL"))?null:branchMoblResultMap.get("CONTCT_TEL").toString();
            pagenum=Integer.valueOf(xyPageMap.get("SHOP_SIGN_PAGE").toString()).intValue();
            x=(float)Double.valueOf(xyPageMap.get("SHOP_SIGN_X").toString()).doubleValue();
            y=(float)Double.valueOf(xyPageMap.get("SHOP_SIGN_Y").toString()).doubleValue();
            returnUrl=ParamUtils.getParam("branchreturnurl");
        }
        if(null==mobile){
            throw new ServiceException("电话号码不能为空");
        }
        Constants.DEVICE_TYPE deviceType = Constants.DEVICE_TYPE.MOBILE;
        if(null!=terminal&&"pc".equals(terminal)){
            deviceType=Constants.DEVICE_TYPE.PC;
        }
        String signPageSignimage = SsqSDKUtils.getSignPageSignimage(contractNo,mobile, pagenum, x, y, returnUrl,deviceType);
        return signPageSignimage;
    }

    /**
     * 根据贷款编号获取签名的位置xy page
     * @param loanNo
     * @return Map
     */
    private Map getXYPage(String loanNo) {
        LoanFundMatchDto loanFundMathc = loanFundMatchService.queryLoanFund(loanNo);
        Map<String,Object> map = new HashMap<>();
        //map.put("fileTyp", PubBusinessConstant.CONTRACTTYPE_CR);
        map.put("chanNo", loanFundMathc.getChanNo());
        return contractSignInfoService.getXYPage(map);
    }

    public String getSsqSignUrl(java.util.Map paramMap) throws Exception {
        //String loanNo, String signType, String terminal, UserProfile userProfile
        String loanNo=threeNull(paramMap.get("loanNo"));
        if (null == loanNo) {
            throw new ServiceException("贷款编号不能为空");
        }
        String signType=threeNull(paramMap.get("signType"));
        if(null==signType){
            throw new ServiceException("签约类型不能为空");
        }
        String terminal=threeNull(paramMap.get("terminal"));
        String ssqReturnUrl=threeNull(paramMap.get("ssqReturnUrl"));
        if(null==terminal){
            throw new ServiceException("签约平台不能为空");
        }
        Object userProfileObj=paramMap.get("userProfile");
        if(null==userProfileObj){
            throw new ServiceException("用户信息不能为空");
        }
        UserProfile userProfile= (UserProfile) userProfileObj;

        java.util.Map contractMap = new HashMap<String, Object>();
        contractMap.put("loanNo", loanNo);
        String contractNoByLoanNo = contractSignInfoService.getContractNoByLoanNo(contractMap);
        if (null == contractNoByLoanNo) {
            throw new ServiceException("合同还未生成");
        }
        String mobile = null;
        int pagenum = 0;
        float x = 0;
        float y = 0;
        String returnUrl = null;
        java.util.Map xyPageParmMap=new HashedMap();
        //分期资金匹配 对象
        LoanFundMatchDto loanFundMathc = loanFundMatchService.queryLoanFund(loanNo);
        if (loanFundMathc == null) {
            throw new ServiceException("资金匹配失败");
        }
        xyPageParmMap.put("chanNo",loanFundMathc.getChanNo());
        Map xyPageMap = contractSignInfoService.getXYPage(xyPageParmMap);
        if (null != signType && "cust".equals(signType)) {
            LoanAcctOutDto loanAcctOutDto = contractLoanAcctService.getLoanAcct(loanNo, userProfile);
            CustInfoDto custInfoDto = contractCustInfoService.getByNo(loanAcctOutDto.getCustNo());
            mobile = custInfoDto.getPhoneNo();
            pagenum = Integer.valueOf(xyPageMap.get("C_SIGN_PAGE").toString()).intValue();
            x = (float) Double.valueOf(xyPageMap.get("C_SIGN_X").toString()).doubleValue();
            y = (float) Double.valueOf(xyPageMap.get("C_SIGN_Y").toString()).doubleValue();
            returnUrl = ParamUtils.getParam("custreturnurl");
        } else if (null != signType && "sale".equals(signType)) {
            java.util.Map staffMoblMap = new HashMap<String, Object>();
            staffMoblMap.put("staffNo", userProfile.getStaffNo());
            //销售人员电话号码
            Map staffMoblResultMap = contractSignInfoService.getStaffMobl(staffMoblMap);
            //销售人员电话号码
            mobile = (null == staffMoblResultMap.get("MOBL_NO")) ? null : staffMoblResultMap.get("MOBL_NO").toString();
            pagenum = Integer.valueOf(xyPageMap.get("SIGN_PAGE").toString()).intValue();
            x = (float) Double.valueOf(xyPageMap.get("SIGN_X").toString()).doubleValue();
            y = (float) Double.valueOf(xyPageMap.get("SIGN_Y").toString()).doubleValue();
            returnUrl = ParamUtils.getParam("salereturnurl");
        } else if (null != signType && "branch".equals(signType)) {
            java.util.Map branchMoblMap = new HashMap<String, Object>();
            branchMoblMap.put("loanNo", loanNo);
            //商户电话号码
            Map branchMoblResultMap = contractSignInfoService.getBranchMobl(branchMoblMap);
            mobile = (null == branchMoblResultMap.get("CONTCT_TEL")) ? null : branchMoblResultMap.get("CONTCT_TEL").toString();
            pagenum = Integer.valueOf(xyPageMap.get("SHOP_SIGN_PAGE").toString()).intValue();
            x = (float) Double.valueOf(xyPageMap.get("SHOP_SIGN_X").toString()).doubleValue();
            y = (float) Double.valueOf(xyPageMap.get("SHOP_SIGN_Y").toString()).doubleValue();
            returnUrl = ParamUtils.getParam("branchreturnurl");
        }

        Constants.DEVICE_TYPE deviceType = Constants.DEVICE_TYPE.MOBILE;
        if (null != terminal && "pc".equals(terminal)) {
            deviceType = Constants.DEVICE_TYPE.PC;
        }

        //改为传入电话号码
        mobile=threeNull(paramMap.get("mobile"));
        if (null == mobile) {
            throw new ServiceException("电话号码不能为空");
        }
        if(null!=ssqReturnUrl){
          returnUrl=ssqReturnUrl;
        }
        String signPageSignimage = SsqSDKUtils.getSignPageSignimage(contractNoByLoanNo, mobile, pagenum, x, y, returnUrl, deviceType);
        return signPageSignimage;
    }

    private String threeNull(Object obj) {
        return (null == obj || "".equalsIgnoreCase(obj.toString().trim())) ? null : obj.toString().trim();
    }
    private String threeNu(Object obj) {
        return (null == obj || "".equalsIgnoreCase(obj.toString().trim())) ? "" : obj.toString().trim();
    }
    /**
     * 获取阿里云上传附件地址
     *
     * @param loanNo 贷款编号
     * @return
     */
    public String getUrlOss(String loanNo) throws ServiceException, AppException {
        if (null == loanNo || loanNo.isEmpty()) {
            return null;
        }
        LoanAttInDto loanAttInDto = contractLoanAttService.queryLoanAtt(loanNo, "40103499");
        String attNo = loanAttInDto.getAttNo();
        if (null == attNo) {
            return null;
        }
        String url = OssUtil.getPresignedUrl(attNo);
        return url;
    }


    /**
     * 查询尚尚签是否已经签名
     * @param loanNo 贷款编号
     * @param signType 签名类型(cust sale branch)
     * @param userProfile 用户信息
     * @return boolean(true已经签名 false未签名)
     * @throws ServiceException
     * @throws AppException
     */
    @Transactional
    public boolean isSign(String loanNo,String signType,UserProfile userProfile,String mobile)throws ServiceException,AppException{
        boolean result=false;
        if(null==signType){
            throw new ServiceException("签名类型不能为空!");
        }
        Map map = this.getContractMap(loanNo);
        String fsdid=map.get("contract_no").toString();
        if(null==mobile){
            throw new ServiceException("电话号码不能为空");
        }
        try {
            ObjectMapper objectMapper=new ObjectMapper();
            Map<String, Object> ssqContractInfoMap = SsqSDKUtils.contractInfo(fsdid);
           /* Map<String, Object> ssqContractInfoMap = null;*/
            String response = ssqContractInfoMap.get("response").toString();
            if(null==response){
                throw new ServiceException("查询合同接口为空!");
            }
            ObjectNode jsonNodes = objectMapper.readValue(response, ObjectNode.class);
            JsonNode path = jsonNodes.path("response").path("content").path("userlist");
            if(path.isArray()){
                for (JsonNode jsonNode : path) {
                    JsonNode sendMobile = jsonNode.path("userinfo").path("mobile");
                    JsonNode status = jsonNode.path("userinfo").path("status");
                    if(mobile.equals(sendMobile.asText().trim())){
                        //1表示未签 2表示已签
                        if("2".equals(status.asText().trim())){
                            result=true;
                            this.dealSsqReturn(signType,"100000",fsdid);
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查询合同接口异常");
        }
        return result;
    }

    /**
     * @describe 合同生成上传阿里oss
     * @author txia
     * datetime 2016/8/12 10:00
     * params [loanNo:贷款编号, userProfile:用户信息]
     * return java.util.Map{downUrl:oss文件下载url}
     */
    @Transactional
    public java.util.Map buidContantOss(final String loanNo,final UserProfile userProfile) throws Exception {
        if(null==threeNull(loanNo)){
            throw new ServerException("贷款编号为空");
        }
        if(null==userProfile){
            throw new ServerException("用户信息为空");
        }
        //如果pc端oss生成合同直接返回
        String urlOss = this.getUrlOss(loanNo);
        if (null != urlOss && !urlOss.isEmpty()) {
            return null;
        }
//        //①初始化
//        Map initMap = init(loanNo, userProfile);
//        //②生成分期申请书
//        Map buildApplyWordComp = buildApplyWordComp(initMap);
//        //③文件上传至阿里oss
//        Map word2FileServerOssComp = word2FileServerOssComp(buildApplyWordComp);
//        return word2FileServerOssComp;
        //异步线程
        AsyncTaskExecutor executor = new SimpleAsyncTaskExecutor(loanNo);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    //①初始化
                    Map initMap = init(loanNo, userProfile);
                    //②生成分期申请书
                    Map buildApplyWordComp = buildApplyWordComp(initMap);
                    //③文件上传至阿里oss
                    Map word2FileServerOssComp = word2FileServerOssComp(buildApplyWordComp);
                }catch (Exception e){
                    throw new  RuntimeException(e);
                }
            }
        });
        return null;
    }


    /**
     *@describe 查询合同是否上传至尚尚签
     *@author txia
     *datetime 2016/8/15 11:51
     *params {userProfile:用户信息,loanNo:贷款编号}
     *return java.util.Map{isUpload:是否上传(boolean),ossUrl:阿里云oss文件下载地址,getContractViewURL:尚尚签合同查看url,ssqCustSignUrl:客户签约url,ssqSaleSignUrl:销售签约url,ssqBranchSignUrl:商户签约url}
     */
    @Transactional
    public java.util.Map fileIsUploadSsq(java.util.Map paramMap) throws Exception {
        Object userProfileObj = paramMap.get("userProfile");
        if (null == userProfileObj) {
            throw new ServerException("用户信息为空");
        }
        UserProfile userProfile = (UserProfile) userProfileObj;
        String loanNo = threeNull(paramMap.get("loanNo"));
        if (null == loanNo) {
            throw new ServerException("贷款编号为空");
        }
        String terminal=threeNull(paramMap.get("terminal"));
        String ssqReturnUrl=threeNull(paramMap.get("ssqReturnUrl"));
        java.util.Map ssqSignUrlMap=new HashedMap();
        ssqSignUrlMap.put("loanNo",loanNo);
        if(null!=terminal){
            ssqSignUrlMap.put("terminal","app");
        }else{
            ssqSignUrlMap.put("terminal",terminal);
        }
        ssqSignUrlMap.put("userProfile",userProfile);
        ssqSignUrlMap.put("ssqReturnUrl",ssqReturnUrl);

        java.util.Map returnMap=new HashedMap();
        returnMap.put("isSignCust",false);
        returnMap.put("isSignSale",false);
        returnMap.put("isSignBranch",false);
        returnMap.put("isUpload",false);
        returnMap.put("ossUrl",this.getUrlOss(loanNo));
        java.util.Map seachMap=new HashedMap();
        seachMap.put("loanNo",loanNo);
        //查询电话号码是否已经填写
        List<Map> fileIsUploadSsq = contractSignInfoService.fileIsUploadSsq(seachMap);
        List<String> updateStatusList=new ArrayList<>();
        if(null!=fileIsUploadSsq&&0<fileIsUploadSsq.size()){
            returnMap.put("isUpload",true);
            returnMap.put("getContractViewURL",this.getContractViewURL(loanNo));
            for (Map map : fileIsUploadSsq) {
                String sign_type=threeNull(map.get("sign_type"));
                String sign_phone = threeNull(map.get("sign_phone"));
                if("cust".equalsIgnoreCase(sign_type)){
                    returnMap.put("custMobile",sign_phone);
                    ssqSignUrlMap.put("signType","cust");
                    ssqSignUrlMap.put("mobile",sign_phone);
                    returnMap.put("isSignCust",true);
                    boolean isCust = this.isSign(loanNo, "cust", userProfile,sign_phone);
                    if(isCust)updateStatusList.add("true");
                    if(!isCust){
                        updateStatusList.add("false");
                        returnMap.put("isSignCust",false);
                        returnMap.put("ssqCustSignUrl",this.getSsqSignUrl(ssqSignUrlMap));
                    }
                }
                if("sale".equalsIgnoreCase(sign_type)){
                    returnMap.put("saleMobile",sign_phone);
                    ssqSignUrlMap.put("signType","sale");
                    ssqSignUrlMap.put("mobile",sign_phone);
                    returnMap.put("isSignSale",true);
                    boolean isSale = this.isSign(loanNo, "sale", userProfile,sign_phone);
                    if(isSale)updateStatusList.add("true");
                    if(!isSale){
                        updateStatusList.add("false");
                        returnMap.put("isSignSale",false);
                        returnMap.put("ssqSaleSignUrl",this.getSsqSignUrl(ssqSignUrlMap));
                    }
                }
                if("branch".equalsIgnoreCase(sign_type)){
                    returnMap.put("branchMobile",sign_phone);
                    ssqSignUrlMap.put("signType","branch");
                    ssqSignUrlMap.put("mobile",sign_phone);
                    returnMap.put("isSignBranch",true);
                    boolean isBranch = this.isSign(loanNo, "sale", userProfile,sign_phone);
                    if(isBranch)updateStatusList.add("true");
                    if(!isBranch){
                        updateStatusList.add("false");
                        returnMap.put("isSignBranch",false);
                        returnMap.put("ssqBranchSignUrl",this.getSsqSignUrl(ssqSignUrlMap));
                    }
                }
            }

            //如果三方全部签订完毕,改变分期状态为已经签名
            if(!updateStatusList.contains("false")&&updateStatusList.contains("true")){
                Map updateAcct=new HashedMap();
                updateAcct.put("loanNo",loanNo);
                contractSignInfoService.updateStateLoanAcct(updateAcct);
            }

        }
        return returnMap;
    }
    /**
     * @describe 合同生成上传尚尚签
     * @author txia
     * datetime 2016/8/12 10:00
     * params {loanNo:贷款编号, userProfile:用户信息,custMobile:客户电话,saleMobile:销售电话,branchMobile:商户电话}
     * return java.util.Map{downUrl:合同下载url,ssqCustSignUrl:尚尚签客户签名url,ssqSaleSignUrl:尚尚签销售签名url,ssqBranchSignUrl:尚尚签商户签名url,getContractViewURL:查看合同视图}
     */
    @Transactional
    public java.util.Map buidContantSsq(java.util.Map paramMap) throws Exception {
        String loanNo = threeNull(paramMap.get("loanNo"));
        if (null == loanNo) {
            throw new ServerException("贷款编号为空");
        }
        String custMobile = threeNull(paramMap.get("custMobile"));
        String saleMobile = threeNull(paramMap.get("saleMobile"));
        String branchMobile = threeNull(paramMap.get("branchMobile"));
        Object userProfileObj = paramMap.get("userProfile");
        if (null == userProfileObj) {
            throw new ServerException("用户信息为空");
        }
        String terminal=threeNull(paramMap.get("terminal"));
        String ssqReturnUrl=threeNull(paramMap.get("ssqReturnUrl"));
        UserProfile userProfile = (UserProfile) userProfileObj;

        Map contractSignComp=null;
        //判断是否已经在尚尚签生成合同
        java.util.Map ssqParamMap = new HashMap<String, Object>();
        ssqParamMap.put("loanNo", loanNo);
        String contractNoByLoanNo = contractSignInfoService.getContractNoByLoanNo(ssqParamMap);
        //如果app端在尚尚签生成合同直接返回尚尚签的合同下载
        if (null != contractNoByLoanNo) {
            return this.fileIsUploadSsq(paramMap);
        }
        try {
            //①初始化
            Map initMap = init(loanNo, userProfile);
            //②生成分期申请书
            Map buildApplyWordComp = buildApplyWordComp(initMap);
            //③文件上传至尚尚签
            Map word2FileServerSsqComp = word2FileServerSsqComp(buildApplyWordComp);
            word2FileServerSsqComp.put("custMobile", custMobile);
            word2FileServerSsqComp.put("saleMobile", saleMobile);
            word2FileServerSsqComp.put("branchMobile", branchMobile);
            word2FileServerSsqComp.put("terminal", terminal);
            word2FileServerSsqComp.put("ssqReturnUrl", ssqReturnUrl);
            //④获取尚尚签的签名和下载
            contractSignComp = contractSignComp(word2FileServerSsqComp);

            contractSignComp.put("getContractViewURL",this.getContractViewURL(loanNo));
            contractSignComp.put("isSignCust",false);
            contractSignComp.put("isSignSale",false);
            contractSignComp.put("isSignBranch",false);
            contractSignComp.put("isUpload",false);
        }catch (Exception e){
            e.printStackTrace();
        }
        return contractSignComp;
    }
    @Transactional
    private java.util.Map init(String loanNo, UserProfile userProfile) throws ParseException, WriterException {
        java.util.Map returnMap = new HashedMap();
        if (StringUtils.isEmpty(loanNo)) {
            throw new ServiceException("系统异常，生成合同分期编号不能为空");
        }
        //查询客户分期账户信息
        LoanAcctOutDto loanAcctOutDto = contractLoanAcctService.getLoanAcct(loanNo, userProfile);

        if (loanAcctOutDto == null) {
            throw new ServiceException("获取分期信息异常");
        }

        //生成还款单和确认单
        Map<String, String> map = new HashMap<String, String>();
        //客户号
        String custNo = loanAcctOutDto.getCustNo();

        //分期利率
        map.put("rateYear", loanAcctOutDto.getInterRate() == null ? "0" : loanAcctOutDto.getInterRate().toString());
        //计算本金+利息
        Map<SubjectType, Double> dtMap = PmtUtil.getMthRepayDtMap(loanAcctOutDto.getLoanAmt() == null ? 0d : loanAcctOutDto.getLoanAmt().doubleValue(),
                Integer.valueOf(loanAcctOutDto.getInstNum()), 1, loanAcctOutDto.getInterRate() == null ? 0d : loanAcctOutDto.getInterRate().doubleValue());
        Double mthPrinInter = dtMap.get(SubjectType.prin_inter);
        mthPrinInter = ArithUtil.round(mthPrinInter, 2);

        //甲方每期偿还本息数额
        map.put("mthPrinInter", mthPrinInter.toString());
        //装填数据
        map.putAll(BeanRefUtil.getFieldValueMap(loanAcctOutDto));
        //分期信息
        double serviceCash = 0d;
        double isInsuuance = 0d;
        double ppRate = 0d;
        double loanAmt = loanAcctOutDto.getLoanAmt() == null ? 0 : loanAcctOutDto.getLoanAmt().doubleValue();
        if (loanAcctOutDto.getInterRate() != null) {
            ppRate = loanAcctOutDto.getInterRate() == null ? 0 : loanAcctOutDto.getInterRate().doubleValue() / 100;
            serviceCash = Math.round(loanAmt * ppRate);
        }
        map.put("ppRate", loanAcctOutDto.getInterRate() == null ? "0" : loanAcctOutDto.getInterRate().doubleValue() + "%");//月客户服务费率
        map.put("amRateMonth", Double.toString(serviceCash));//月账户管理费率
        map.put("insFeeRate", "");//月保险手续费（元）
        map.put("isRepayPackage", "");//是否购买灵活还款服务包
        map.put("repayPackageFee", "");//灵活还款服务包费

        map.put("firFee", loanAcctOutDto.getFstRepayAmt().toString());//手续费（元）
        map.put("custName", loanAcctOutDto.getCustName());//客户名称
        map.put("custNo", loanAcctOutDto.getCustNo());//客户号

        //工作信息
        //查询工作信息列表
        List<CustWorkDto> custWorkDtos = custWorkService.getCrtCustWorkLst(custNo);
        map.put("workUnit", "");//单位名称
        map.put("workDept", CollectionUtils.isEmpty(custWorkDtos) ? "" : custWorkDtos.get(0).getWorkDept());//任职部门
        map.put("workJob", CollectionUtils.isEmpty(custWorkDtos) ? "" : custWorkDtos.get(0).getWorkJob());//职位
        map.put("industry", CollectionUtils.isEmpty(custWorkDtos) ? "" : SimpleCodeUtils.getNameByCode(custWorkDtos.get(0).getIndustry()));//所属行业
        map.put("unitType", CollectionUtils.isEmpty(custWorkDtos) ? "" : SimpleCodeUtils.getNameByCode(custWorkDtos.get(0).getUnitType()));//单位性质
        map.put("workTimeSpan", CollectionUtils.isEmpty(custWorkDtos) ? "" : custWorkDtos.get(0).getWorkTime());//就职时间（月）
        map.put("linkMan", CollectionUtils.isEmpty(custWorkDtos) ? "" : custWorkDtos.get(0).getLinkMan());//单位联系人姓名
        map.put("unitTel", CollectionUtils.isEmpty(custWorkDtos) ? "" : custWorkDtos.get(0).getUnitTel());//单位联系人电话
        map.put("unitProv", CollectionUtils.isEmpty(custWorkDtos) ? "" : custWorkDtos.get(0).getUnitProvName());//工作省/直辖市
        map.put("unitCity", CollectionUtils.isEmpty(custWorkDtos) ? "" : custWorkDtos.get(0).getUnitCityName());//工作市
        map.put("unitArea", CollectionUtils.isEmpty(custWorkDtos) ? "" : custWorkDtos.get(0).getUnitAreaName());//工作区/县
        map.put("unitTown", "");//工作镇
        map.put("unitStreet", CollectionUtils.isEmpty(custWorkDtos) ? "" : custWorkDtos.get(0).getUnitAddr());//工作街道/路/村
        map.put("unitVillage", "");//工作小区/楼盘
        map.put("unitUnit", "");//工作栋/单元/房号

        //其它信息
        //查询其他信息
        List<CustOtherInfoDto> custOtherInfoDtos = custOtherInfoService.queryCustOtherInfoList(custNo);
        if (CollectionUtils.isNotEmpty(custOtherInfoDtos)) {
            map.put("education", custOtherInfoDtos.get(0).getEdu());//受教育程度
            map.put("isLoaned", custOtherInfoDtos.get(0).getIsLoaned());//是否办理过相关业务
            map.put("income", custOtherInfoDtos.get(0).getIncome() == null ? "0" : "" + custOtherInfoDtos.get(0).getIncome().doubleValue());//月收入（元）
            map.put("mthRepay", custOtherInfoDtos.get(0).getMthRepay() == null ? "0" : "" + custOtherInfoDtos.get(0).getMthRepay().doubleValue());//月还款额
        } else {
            map.put("education", "");
            map.put("isLoaned", "");
            map.put("income", "");
            map.put("mthRepay", "");
        }

        LoanFundMatchDto loanFundMathc = loanFundMatchService.queryLoanFund(loanNo);
        String chanNo = loanFundMathc.getChanNo();

        List<LoanGoodsDto> loanGoodsLst = loanAcctOutDto.getGoodsDto();
        //商品
        //获取取商品列表
        if (loanGoodsLst != null && loanGoodsLst.size() > 0) {
            for (int i = 0; i < loanGoodsLst.size(); i++) {
                LoanGoodsDto goodsVO = (LoanGoodsDto) loanGoodsLst.get(i);
                map.put("goodsT" + i, SimpleCodeUtils.getNameByCode(goodsVO.getGoodsType()));//商品类型Ⅰ
                map.put("brandT" + i, goodsVO.getBrand());//商品品牌Ⅰ
                String marques = threeNull(goodsVO.getMarques());
                //现金贷商品品牌为空加 /
                if("012".equals(chanNo)){
                    marques=(null==marques)?"/":marques;
                }
                map.put("marquesT" + i,marques);//商品型号Ⅰ
//                map.put("priceT" + i, goodsVO.getPric().toString());//商品单价(元)Ⅰ
//                map.put("totalPrice", goodsVO.getPric().toString());//商品总价
                map.put("priceT"+i, threeNu(goodsVO.getPric().setScale(0,BigDecimal.ROUND_CEILING)));//商品单价(元)Ⅰ
                map.put("totalPrice", threeNu(goodsVO.getPric().setScale(0,BigDecimal.ROUND_CEILING)));//商品总价
            }
            //如果商品个数小于3,就空置一个商品行
            for (int i = loanGoodsLst.size(); i < 3; i++) {
                map.put("goodsT" + i, "");
                map.put("brandT" + i, "");
                map.put("marquesT" + i, "");
                map.put("priceT" + i, "");
            }
        } else {
            //如果没有商品就空置3个商品行
            for (int i = 0; i < 3; i++) {
                map.put("goodsT" + i, "");
                map.put("brandT" + i, "");
                map.put("marquesT" + i, "");
                map.put("priceT" + i, "");
            }
        }
        //家庭信息
        //查询关键联系人信息
        List<CustContctOtherDto> custOtherContactLst = contractCustViewService.getCustContctOtherLst(custNo);
        String famName = "";//家庭成员姓名
        String famRel = "";//关系
        String famTel = "";//家庭成员联系电话
        String mateName = "";//配偶姓名
        String mateTel = "";//配偶联系电话
        //多个联系人
        if (custOtherContactLst != null && custOtherContactLst.size() > 0) {
            for (int i = 0; i < custOtherContactLst.size(); i++) {
                CustContctOtherDto coc = (CustContctOtherDto) custOtherContactLst.get(i);
                map.put("contactName" + i, coc.getContactName());
                if (ContractContant.FAM_MEMBER_REL_MATE.equals(coc.getContactRel())) {//如果联系人是夫妻关系
                    mateName = coc.getContactName();//
                    mateTel = coc.getContactTel();
                }
                if (null == famRel && ContractContant.FAM_MEMBER_REL_FATHER.equals(coc.getContactRel())) {//如果联系人是父亲
                    famRel = ContractContant.FAM_MEMBER_REL_FATHER;
                    famName = coc.getContactName();
                    famTel = coc.getContactTel();
                }
                if (null == famRel && ContractContant.FAM_MEMBER_REL_MATHER.equals(coc.getContactRel())) {//如果联系人是父亲
                    famRel = ContractContant.FAM_MEMBER_REL_MATHER;
                    famName = coc.getContactName();
                    famTel = coc.getContactTel();
                }
            }
        }
        map.put("mateName", mateName);
        map.put("famName", famName);
        map.put("famRel", SimpleCodeUtils.getNameByCode(famRel));
        map.put("famTel", famTel);
        map.put("mateTel", mateTel);
        map.put("isRegAdd", "");
        map.put("famAdd", "");
        //合同签署年，月，日
        Date applyDate = loanAcctOutDto.getApplyDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(applyDate);
        map.put("applyYear", cal.get(Calendar.YEAR) + "");
        map.put("applyMonth", cal.get(Calendar.MONTH) + 1 + "");
        map.put("applyDay", cal.get(Calendar.DATE) + "");
//			map.put("applyHour", cal.get(Calendar.HOUR_OF_DAY)+"");
//			map.put("applyMinute", cal.get(Calendar.MINUTE)+"");
//			map.put("applySecond", cal.get(Calendar.SECOND)+"");

        //合同签订地：门店所在省+城市
        String actyProv = loanAcctOutDto.getApplyProvName();
        String actyCity = loanAcctOutDto.getApplyCityName();
        map.put("actyProv", actyProv);
        map.put("actyCity", actyCity);

        map.put("firRepayDate", loanAcctOutDto.getFstRepayDate());//首次还款日
        //计划还款时间段
        //从
        String fstRepayDatestr = loanAcctOutDto.getFstRepayDate();

        map.put("fYear", fstRepayDatestr.substring(0, 4) + "");//年
        map.put("fMonth", fstRepayDatestr.substring(4, 6) + "");//月
        map.put("fDay", fstRepayDatestr.substring(6, 8) + "");//日
        Date fstRepayDate = DateUtils.parseDate(fstRepayDatestr, "yyyyMMdd");
        Date tarDate = DateUtils.getDate(fstRepayDate, loanAcctOutDto.getInstNum() - 1);
        String tarDateStr = DateUtils.formatDate(tarDate, "yyyyMMdd");
        map.put("sysDate", DateUtils.getCurDate());
        //到
        map.put("loanEndDate", tarDateStr);//分期理论结束日期
        map.put("lYear", tarDateStr.substring(0, 4));//年
        map.put("lMonth", tarDateStr.substring(4, 6));//月
        map.put("lDay", tarDateStr.substring(6, 8));//日

        //生成二维码
        //生成 个人消费信贷确认单时需传入条码图片
        int width = 300;
        int height = 300;
        String format = "jpeg";//二维码的图片格式
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "utf8");////内容所使用编码
        hints.put(EncodeHintType.MARGIN, 0);//边距
        BitMatrix bitMatrix = new MultiFormatWriter().encode(loanNo, BarcodeFormat.QR_CODE, width, height, hints);
        String codeFilePath = ParamUtils.getParam("codeFilePath");//从参数表里获取文件保存路径
        //QRCodeUtil.writeToFile(bitMatrix, format, codeFilePath, codeFileName);
        //添加二维码标记必须写成ewm
        //map.put("ewm", RTFToWordUtil.replaceStringToImage(codeFilePath,codeFileName,null,null).toString());
        logger.debug("*****************************************************************现场照");
        String codeFileName = loanNo + ".jpeg";
        OneBarcodeUtil.createCodeAndSaveToFile(codeFilePath, codeFileName, "jpeg", loanNo);
        //添加条码 标记必须写成image
       // map.put("image", RTFToWordUtil.replaceStringToImage(codeFilePath, codeFileName, null, "").toString());

        //添加客户照片
        LoanAttInDto attDto = contractLoanAttService.queryLoanAtt(loanNo, PubBusinessConstant.ATTTYPE_XCZ);
        String fileUrl = OssUtil.getPresignedUrl(attDto.getAttNo());
        //map.put("twoIMG", RTFToWordUtil.replaceStringToImage(attDto.getAttFile(), attDto.getAttName(), PubBusinessConstant.ATTTYPE_XCZ, fileUrl).toString());

        //月还款金额
        //map.put("mothRepayAmt", String.valueOf(loanAcctOutDto.getMthRepayAmt().doubleValue()));
        map.put("mothRepayAmt",threeNu(loanAcctOutDto.getMthRepayAmt().setScale(0,BigDecimal.ROUND_CEILING)));

        //月还款额转为大写
        if(org.apache.commons.lang.StringUtils.isNotBlank(loanAcctOutDto.getMthRepayDate())){
            map.put("mothRepayAmtUpper",rmbUpper(loanAcctOutDto.getMthRepayAmt().toString()));
        }

        //月还款额
        map.put("mothRepayDate", String.valueOf(loanAcctOutDto.getMthRepayDate()));



        //还款期数
        map.put("installNum", String.valueOf(loanAcctOutDto.getInstNum()));
        map.put("dwnPayUpper", rmbUpper(String.valueOf(loanAcctOutDto.getFstPayAmt().doubleValue())));
        //首付金额
        //map.put("downPay", String.valueOf(loanAcctOutDto.getFstPayAmt().doubleValue()));
        map.put("downPay",threeNu(loanAcctOutDto.getFstPayAmt().setScale(0,BigDecimal.ROUND_CEILING)));
        //分期本金
        //map.put("loanAmt", String.valueOf(loanAcctOutDto.getLoanAmt().doubleValue()));
        map.put("loanAmt", threeNu(loanAcctOutDto.getLoanAmt().setScale(0,BigDecimal.ROUND_CEILING)));



        Map getFeeRatByLoanNoMap=new HashMap();
        getFeeRatByLoanNoMap.put("feeNum",loanAcctOutDto.getInstNum());
        getFeeRatByLoanNoMap.put("loanNo",loanNo);


        //分期本金转为大写
        if(null!=loanAcctOutDto.getLoanAmt()){
            map.put("loanAmtUpper",rmbUpper(loanAcctOutDto.getLoanAmt().toString()));
        }
        BigDecimal goodPrice = loanAcctOutDto.getFstPayAmt().add(loanAcctOutDto.getLoanAmt());
        //map.put("goodPrice", String.valueOf(goodPrice.doubleValue()));
        map.put("goodPrice",threeNu(goodPrice.setScale(0,BigDecimal.ROUND_CEILING)));
        map.put("goodPriceUpper", rmbUpper(String.valueOf(goodPrice.doubleValue())));
        Map getPubBranchByLoanNoMap=new HashedMap();
        getPubBranchByLoanNoMap.put("loanNo",loanNo);
        Map pubBranchByLoanNo = contractLoanAcctService.getPubBranchByLoanNo(getPubBranchByLoanNoMap);
        //商户账户号
        String branchAcctNo=threeNull(pubBranchByLoanNo.get("ACCT_NO"));
        //商户户名
        String branchAcctName=threeNull(pubBranchByLoanNo.get("ACCT_NAME"));
        //商户开户行
        String branchBankNo=threeNull(pubBranchByLoanNo.get("BANK_NO"));

        map.put("branchAcctNo",branchAcctNo);
        map.put("branchAcctName",branchAcctName);
        map.put("branchBankNo",branchBankNo);

        //手续费
        map.put("firFee", loanAcctOutDto.getStrSeleFees());
        //审批日期
        map.put("aprovDate", DateUtils.getCurDate());

        //商户指定账户信息  add by hmzhou 20151029
        //商户信息
        LoanViewDto loanViewDto = contractLoanViewService.getLoanView(loanNo, userProfile);
        map.put("salerTel", "");//分期顾问电话
        if (null != loanViewDto.getBranch()) {
            map.put("marBankNo", loanViewDto.getBranch().getBranchNo());////甲方消费商户指定账户
            BranchDto branchDto = branchService.getByNo(loanViewDto.getBranch().getBranchNo());
            if (null != branchDto) {
                map.put("marBankName", branchDto.getBankName());//甲方消费商户指定户名
                map.put("marName", branchDto.getBranchName());//合作商户
                map.put("marAddr", branchDto.getBranchAddr());//商户地址
                map.put("strAddr", branchDto.getBranchAddr());//门店地址
                map.put("strName", branchDto.getBranchName());//门店名称
                map.put("strNo", branchDto.getBranchNo());//商户编号
                map.put("contractAddr", branchDto.getBranchAddr());//合同签订地
            } else {
                map.put("marBankName", "");
                map.put("marName", "");
                map.put("marAddr", "");
                map.put("strAddr", "");
                map.put("strName", "");
                map.put("strNo", "");
                map.put("contractAddr", "");//合同签订地
            }
        } else {
            map.put("marBankNo", "");
            map.put("marBankName", "");
            map.put("marName", "");
            map.put("marAddr", "");
            map.put("strAddr", "");
            map.put("strName", "");
            map.put("strNo", "");
            map.put("contractAddr", "");//合同签订地
        }
        logger.debug("**************************************************");
        //还款信息
        if (null != loanViewDto.getBankAcct()) {
            map.put("custOpenOrg", StringUtils.isNotEmpty(loanViewDto.getBankAcct().getOpenOrg()) ? SimpleCodeUtils.getNameByCode(loanViewDto.getBankAcct().getOpenOrg()) : "");//开户行
            map.put("custAccount", StringUtils.isNotEmpty(loanViewDto.getBankAcct().getAcctNo()) ? loanViewDto.getBankAcct().getAcctNo() : "");//客户个人账户
            map.put("bankAccount", StringUtils.isNotEmpty(loanViewDto.getBankAcct().getAcctNo()) ? loanViewDto.getBankAcct().getAcctNo() : "");//客户个人账户
            map.put("aliasCAName", StringUtils.isNotEmpty(loanViewDto.getBankAcct().getAcctName()) ? loanViewDto.getBankAcct().getAcctName() : "");//户名
            //map.put("Name", loanViewDto.getBankAcct().getAcctName());
            map.put("marBankOrg", StringUtils.isNotEmpty(loanViewDto.getBankAcct().getOpenOrgName()) ? loanViewDto.getBankAcct().getOpenOrgName() : "");//开户行
        } else {
            map.put("custOpenOrg", "");
            map.put("custAccount", "");
            map.put("bankAccount", "");
            map.put("aliasCAName", "");
            //map.put("Name", "");
            map.put("marBankOrg", "");
        }
        //还款随心包
        List<LoanFeeDto> loanFees = loanViewDto.getFees();
        map.put("isAttach", "否");
        map.put("insFeeAmt", "0");
        map.put("isInsuuance", "否");
        map.put("attachAmt", "0");
        if (null != loanFees && loanFees.size() > 0) {
            for (LoanFeeDto loanFeeDto : loanFees) {
                if ("2601".equals(loanFeeDto.getFeeNo()) || "2001".equals(loanFeeDto.getFeeNo())) {
                    if ("2601".equals(loanFeeDto.getFeeNo())) {
                        map.put("isAttach", "是");
                        map.put("attachAmt", loanFeeDto.getFeeVal());
                    } else {
                        map.put("isInsuuance", "是");
                        isInsuuance = Math.round(loanAmt * 0.0069);
                        //map.put("insFeeAmt", Double.toString(isInsuuance));//申请参加保险
                        map.put("insFeeAmt",threeNu(new BigDecimal(isInsuuance).setScale(0,BigDecimal.ROUND_CEILING)));//申请参加保险
                    }
                } else {
                    map.put("isAttach", "否");
                    map.put("attachAmt", "0");
                    map.put("isInsuuance", "否");
                    map.put("insFeeAmt", "0");//申请参加保险
                }
            }
        }
        map.put("insFeeAmtUpper",rmbUpper(map.get("insFeeAmt")));
        if (null != loanViewDto.getSaler()) {
            map.put("salerName", loanViewDto.getSaler().getStaffName());//分期顾问姓名
        } else {
            map.put("salerName", "");
        }

        BigDecimal fstRepayAmt = loanAcctOutDto.getFstPayAmt();
        //旅游A产品
        if(null!=fstRepayAmt&&0!=fstRepayAmt.compareTo(BigDecimal.ZERO)){
            //map.put("lvoanAmt", String.valueOf(loanAcctOutDto.getLoanAmt().doubleValue()));
            map.put("lvoanAmt", threeNu(loanAcctOutDto.getLoanAmt().setScale(0,BigDecimal.ROUND_CEILING)));
            map.put("lvoaAmtUpper", rmbUpper(String.valueOf(loanAcctOutDto.getLoanAmt().doubleValue())));
            map.put("kvnowPay","0");
            map.put("kvnoPayUpper","零元整");
            BigDecimal divide = loanAcctOutDto.getLoanAmt().divide(new BigDecimal(loanAcctOutDto.getInstNum().toString().trim()),2,BigDecimal.ROUND_CEILING);
            String nh="0.2";
            if(9==loanAcctOutDto.getInstNum())nh="0.18";
            if(12==loanAcctOutDto.getInstNum())nh="0.16";
            BigDecimal multipl=new BigDecimal(nh).multiply(loanAcctOutDto.getLoanAmt()).divide(new BigDecimal("12"),2,BigDecimal.ROUND_CEILING);
            String isAttach = map.get("isAttach");
            BigDecimal isAttachBigDecimal=new BigDecimal("0");
            if("是".equals(isAttach)){
                isAttachBigDecimal=new BigDecimal("15");
            }
            BigDecimal insFeeAmt = divide.add(multipl).add(isAttachBigDecimal).add(new BigDecimal(map.get("insFeeAmt").toString()));
            BigDecimal bigDecimal = insFeeAmt.setScale(0, BigDecimal.ROUND_CEILING);
            //和系统算法一致，如果保险费不为0就加1
            if(new BigDecimal(map.get("insFeeAmt").toString()).compareTo(BigDecimal.ZERO)!=0)bigDecimal=bigDecimal.add(new BigDecimal(1));
            map.put("mvothRepayAmt",bigDecimal.toString());
            map.put("mvotRepayAmtUpper",rmbUpper(bigDecimal.toString()));
        }
        //旅游B产品
        if(null==fstRepayAmt||0==fstRepayAmt.compareTo(BigDecimal.ZERO)){
            map.put("lvoanAmt", threeNu(loanAcctOutDto.getFstPayAmt().add(loanAcctOutDto.getLoanAmt()).setScale(0,BigDecimal.ROUND_CEILING)));
            map.put("lvoaAmtUpper", rmbUpper(threeNu(loanAcctOutDto.getFstPayAmt().add(loanAcctOutDto.getLoanAmt()))));
            Map feeRatByLoanNo = contractSignInfoService.getFeeRatByLoanNo(getFeeRatByLoanNoMap);
            String feeRat=threeNull(feeRatByLoanNo.get("fee_rat"));
            if(null==feeRat)feeRat="0";
            BigDecimal multiply = loanAcctOutDto.getLoanAmt().multiply(new BigDecimal(feeRat).divide(new BigDecimal("100")));
            BigDecimal bigDecimal1 = multiply.setScale(0, BigDecimal.ROUND_CEILING);
            String knowPay=threeNu(bigDecimal1);
            map.put("kvnowPay",knowPay);
            map.put("kvnoPayUpper",rmbUpper(knowPay));
            //BigDecimal divide = loanAcctOutDto.getLoanAmt().divide(new BigDecimal(loanAcctOutDto.getInstNum().toString()));
            BigDecimal isAttachBigDecimal=new BigDecimal("0");
            String isAttach = map.get("isAttach");
            if("是".equals(isAttach)){
                isAttachBigDecimal=new BigDecimal("15");
            }
            BigDecimal ydivide = loanAcctOutDto.getLoanAmt().divide(new BigDecimal(loanAcctOutDto.getInstNum().toString()), 0, BigDecimal.ROUND_CEILING);
            BigDecimal insFeeAmt = ydivide.add(isAttachBigDecimal).add(new BigDecimal(map.get("insFeeAmt").toString()));
            BigDecimal bigDecimal = insFeeAmt.setScale(0, BigDecimal.ROUND_CEILING);
            //和系统算法一致，如果保险费不为0就加1
            if(new BigDecimal(map.get("insFeeAmt").toString()).compareTo(BigDecimal.ZERO)!=0)bigDecimal=bigDecimal.add(new BigDecimal(1));
            map.put("mvothRepayAmt",bigDecimal.toString());
            map.put("mvotRepayAmtUpper",rmbUpper(bigDecimal.toString()));
        }



        //查询客户信息
        CustInfoDto custInfoDto = contractCustInfoService.getByNo(custNo);
        if (custInfoDto == null) {
            throw new ServiceException("获取客户信息异常");
        }
        //查询客户居住信息
        CustLiveInfoDto custLiveInfo = custLiveInfoService.getCrtByNo(custNo);
        //查询联系信息
        List<CustContctInfoDto> custContctInfoDtos = contractCustContctInfoService.getCrtContctInfoLst(custNo);
        CustContctInfoDto custContactDto = null;
        if (!custContctInfoDtos.isEmpty()) {
            custContactDto = custContctInfoDtos.get(0);
        }
        returnMap.put("map", map);
        returnMap.put("loanNo", loanNo);
        returnMap.put("custInfoDto", custInfoDto);
        returnMap.put("custLiveInfo", custLiveInfo);
        returnMap.put("custContactDto", custContactDto);
        returnMap.put("userProfile", userProfile);
        return returnMap;
    }


    /**
     *@describe 人民币转成大写
     *@author txia
     *datetime 2016/8/25 14:43
     *params [num]
     *return java.lang.String
     */
    public  String rmbUpper(String num){
        if(null==num||"".equals(num.trim()))return "";
        if(0==new BigDecimal(num).compareTo(BigDecimal.ZERO))return "零元整";
        String fraction[] = {"角", "分"};
        String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        String unit1[] = {"", "拾", "佰", "仟"};//把钱数分成段,每四个一段,实际上得到的是一个二维数组
        String unit2[] = {"元", "万", "亿","万亿"}; //把钱数分成段,每四个一段,实际上得到的是一个二维数组
        BigDecimal bigDecimal =  new BigDecimal(num);
        bigDecimal=bigDecimal.multiply(new BigDecimal(100));
        String strVal = String.valueOf(bigDecimal.toBigInteger());
        String head = strVal.substring(0,strVal.length()-2);         //整数部分
        String end = strVal.substring(strVal.length()-2);              //小数部分
        String endMoney="";
        String headMoney = "";
        if("00".equals(end)){
            endMoney = "整";
        }else{
            if(!end.substring(0,1).equals("0")){
                endMoney+=digit[Integer.valueOf(end.substring(0,1))]+"角";
            }else if(end.substring(0,1).equals("0") && !end.substring(1,2).equals("0")){
                endMoney+= "零";
            }
            if(!end.substring(1,2).equals("0")){
                endMoney+=digit[Integer.valueOf(end.substring(1,2))]+"分";
            }
        }
        char[] chars = head.toCharArray();
        Map<String,Boolean> map = new HashMap<String,Boolean>();//段位置是否已出现zero
        boolean zeroKeepFlag = false;//0连续出现标志
        int vidxtemp = 0;
        for(int i=0;i<chars.length;i++){
            int idx = (chars.length-1-i)%4;//段内位置  unit1
            int vidx = (chars.length-1-i)/4;//段位置 unit2
            String s = digit[Integer.valueOf(String.valueOf(chars[i]))];
            if(!"零".equals(s)){
                headMoney += s +unit1[idx]+unit2[vidx];
                zeroKeepFlag = false;
            }else if(i==chars.length-1 || map.get("zero"+vidx)!=null){
                headMoney += "" ;
            }else{
                headMoney += s;
                zeroKeepFlag = true;
                map.put("zero"+vidx,true);//该段位已经出现0；
            }
            if(vidxtemp!=vidx || i==chars.length-1){
                headMoney = headMoney.replaceAll(unit2[vidx],"");
                headMoney+=unit2[vidx];
            }
            if(zeroKeepFlag && (chars.length-1-i)%4==0){
                headMoney = headMoney.replaceAll("零","");
            }
        }
        return headMoney+endMoney;
    }

    /**
     * @describe 生成分期申请书
     * @author txia
     * datetime 2016/8/12 9:57
     * params [m]
     * return java.util.Map
     */
    @Transactional
    private java.util.Map buildApplyWordComp(java.util.Map m) throws ServiceException, UnsupportedEncodingException {
        //Map<String,String> paraMap,String loanNo,CustInfoDto custInfoDto,CustLiveInfoDto custLiveInfo,CustContctInfoDto custContactDto,UserProfile userProfile,String terminal
        java.util.Map returnMap = new HashedMap();
        Map<String, String> paraMap = (Map<String, String>) m.get("map");
        String loanNo = m.get("loanNo").toString();
        CustInfoDto custInfoDto = (CustInfoDto) m.get("custInfoDto");
        CustLiveInfoDto custLiveInfo = (CustLiveInfoDto) m.get("custLiveInfo");
        CustContctInfoDto custContactDto = (CustContctInfoDto) m.get("custContactDto");
        UserProfile userProfile = (UserProfile) m.get("userProfile");

        //客户基本信息
        paraMap.put("custNo", custInfoDto.getCustNo());
        paraMap.put("sex", custInfoDto == null ? "" : SimpleCodeUtils.getNameByCode(custInfoDto.getSex()));//性别
        paraMap.put("certType", custInfoDto == null ? "" : SimpleCodeUtils.getNameByCode(custInfoDto.getCertType()));//证件类型
        paraMap.put("custName", custInfoDto == null ? "" : custInfoDto.getCustName());//客户名称
        paraMap.put("custMobile", custInfoDto == null ? "" : custInfoDto.getPhoneNo());//客户手机号
        paraMap.put("certNo", custInfoDto == null ? "" : custInfoDto.getCertNo());//证件号
        paraMap.put("certIssuOrg", custInfoDto == null ? "" : custInfoDto.getCertIssuOrg());//发证机关
        paraMap.put("certValidDate", custInfoDto == null ? "" : custInfoDto.getCertValidDate());//身份证有效期
        paraMap.put("regProv", custInfoDto == null ? "" : custInfoDto.getRegProvName());//户籍省/直辖市
        paraMap.put("regCity", custInfoDto == null ? "" : custInfoDto.getRegCityName());//户籍市
        paraMap.put("regArea", custInfoDto == null ? "" : custInfoDto.getRegAreaName());//户籍区/县
        paraMap.put("regTown", "");//户籍镇
        paraMap.put("regUnit", "");//户籍栋/单元/房号
        paraMap.put("regVillage", "");//户籍小区/楼盘
        paraMap.put("regStreet", custInfoDto == null ? "" : custInfoDto.getRegAddr());//户籍街道/路/村
        //现居住地
        paraMap.put("isRegLive", custLiveInfo == null ? "" : SimpleCodeUtils.getNameByCode(custLiveInfo.getIsRegLive()));//与户籍地址相同
        paraMap.put("liveBuildType", custLiveInfo == null ? "" : SimpleCodeUtils.getNameByCode(custLiveInfo.getLiveBuildType()));//住房类型
        paraMap.put("mthAmtHouse", custLiveInfo == null ? "0" : (custLiveInfo.getMthAmt() == null ? "0" : ("" + custLiveInfo.getMthAmt().doubleValue())));//月均支出（元）
        paraMap.put("liveTimeSpan", custLiveInfo == null ? "" : custLiveInfo.getLiveTimeSpan());//现住址居住时间(月)
        paraMap.put("liveProv", custLiveInfo == null ? "" : custLiveInfo.getLiveProvName());//现居住省/直辖市
        paraMap.put("liveCity", custLiveInfo == null ? "" : custLiveInfo.getLiveCityName());//现居住
        paraMap.put("liveArea", custLiveInfo == null ? "" : custLiveInfo.getLiveAreaName());//现居住
        paraMap.put("liveTown", "");//现居住
        paraMap.put("liveStreet", custLiveInfo == null ? "" : custLiveInfo.getLiveAddr());//现居住
        paraMap.put("custAddr", custLiveInfo == null ? "" : custLiveInfo.getLiveAddr());//现居住
        paraMap.put("liveVillage", "");//现居住
        paraMap.put("liveUnit", "");//现居住栋/单元/房号
        //联系方式
        paraMap.put("mobileTel", custInfoDto == null ? "" : custInfoDto.getPhoneNo());//手机号码
        paraMap.put("mthTelBill", custContactDto == null ? "" : custContactDto.getMthTelFee());//月均话费
        paraMap.put("mobileTimeSpan", custContactDto == null ? "" : custContactDto.getPhoneYears());//使用年限（月）
        paraMap.put("qq", custContactDto == null ? "" : custContactDto.getQq());//QQ号码
        paraMap.put("email", custContactDto == null ? "" : custContactDto.getEmail());//电子邮箱
        paraMap.put("marStatus", custContactDto == null ? "" : SimpleCodeUtils.getNameByCode(custInfoDto.getMarriage()));//婚姻状况
        paraMap.put("homeTel", custContactDto == null ? "" : custContactDto.getHomeTel());//住宅电话
        paraMap.put("homeTelOwner", custContactDto == null ? "" : custContactDto.getHomeTelOwner());//住宅电话登记人
        paraMap.put("custType", custInfoDto == null ? "" : SimpleCodeUtils.getNameByCode(custInfoDto.getCustType()));//客户代码

        //分期资金匹配 对象
        LoanFundMatchDto loanFundMathc = loanFundMatchService.queryLoanFund(loanNo);
        if (loanFundMathc == null) {
            throw new ServiceException("资金匹配失败");
        }
        paraMap.put("chalCode", loanFundMathc.getChanNo());//渠道代码
        paraMap.put("proNo", "");//促销员代码
        paraMap.put("actyCode", "");//活动代码

        //模板下载路径
        paraMap.put("savePath", ParamUtils.getParam("codeFilePath"));

        //根据渠道码和申请合同类型查询合同文件列表
        String fileTyp = PubBusinessConstant.CONTRACTTYPE_XS;//申请单学生合同

        /*List<LoanContractFileDto> contractFileLst = loanContractService.queryContractListByType(fileTyp,loanFundMathc.getChanNo());
        if(CollectionUtils.isEmpty(contractFileLst)){
			throw new ServiceException("没有找到合同模版!");
		} */
        if (StringUtils.isEmpty(loanFundMathc.getChanNo())) {
            throw new ServiceException("渠道信息为空");
        }
        if (StringUtils.isEmpty(fileTyp)) {
            throw new ServiceException("附件类型为空");
        }
        Map<String, Object> map = new HashMap<>();
        //map.put("fileTyp", fileTyp);
        map.put("chanNo", loanFundMathc.getChanNo());
        List<PubLoanContractFile> contractFileLst = pubLoanContractFileService.queryForList(map);
        if (contractFileLst == null || contractFileLst.size() == 0) {
            throw new ServiceException("获取渠道信息失败,请联系运营.");
        }
        logger.debug("*****************************************************************合同模板");
        PubLoanContractFile contractModel = contractFileLst.get(0);
        //this.word2FileServer(paraMap,contractModel,userProfile,terminal);
        returnMap.put("paraMap", paraMap);
        returnMap.put("contractModel", contractModel);
        returnMap.put("userProfile", userProfile);
        return returnMap;
    }

    /**
     * @describe 文件上传阿里oss
     * @author txia
     * datetime 2016/8/12 9:55
     * params [m]
     * return java.util.Map{downUrl:oss文件下载地址}
     */
    @Transactional
    private java.util.Map word2FileServerOssComp(Map m) throws UnsupportedEncodingException {
        //Map<String, String> map,PubLoanContractFile paramObj,UserProfile userProfile,String terminal
        java.util.Map returnMap = new HashedMap();
        Map<String, String> map = (Map<String, String>) m.get("paraMap");
        PubLoanContractFile paramObj = (PubLoanContractFile) m.get("contractModel");
        UserProfile userProfile = (UserProfile) m.get("userProfile");

        //生成放款通知书
        String loanNo = (String) map.get("loanNo");
        String path = (String) map.get("savePath");
        String downloadPath = ParamUtils.getParam("codeFilePath") + DateUtils.getCurDate() + "/";
        java.util.Date date = new java.util.Date();
        String saveName = paramObj.getFileName();
        if (paramObj.getFileName().indexOf("rtf") > 0) {
            saveName = paramObj.getFileName().replaceAll("rtf", "doc");
        }
        String fileName = loanNo + date.getTime() + saveName;
        //增加日期文件夹
        logger.debug("*****************************************************************生成合同" + downloadPath);

        Integer signPage = Integer.valueOf(paramObj.getSignPage());//柠檬签名页
        float signX = Float.valueOf(paramObj.getSignX());//柠檬签名x坐标
        float signY = Float.valueOf(paramObj.getSignY());//柠檬签名y坐标
        Integer csignPage = Integer.valueOf(paramObj.getcSignPage());//客户签名页
        float csignX = Float.valueOf(paramObj.getcSignX());//客户签名x坐标
        float csignY = Float.valueOf(paramObj.getcSignY());//客户签名y坐标
        //WordFileUtil.buildFile(map, fileName, path+paramObj.getFileName(), downloadPath); //将生成的word文档上传到文件服务器  ,返回BufferedInputStream
        RTFToWordUtil.rgModel(map, fileName, path + paramObj.getFileName(), downloadPath); //将生成的word文档上传到文件服务器  ,返回BufferedInputStream
        /**  上传合同文件到阿里云oos start by hwen 20160303*/
        Attachment attachment = null;

        try {
            attachment = this.uploadContract(new FileInputStream(new File(downloadPath + fileName)), fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new ServiceException("检查合同失败");
        }
        //上传SSO更新分期附件信息表
        LoanAttInDto loanAttInDto = new LoanAttInDto();
        loanAttInDto.setCustNo((String) map.get("custNo"));
        loanAttInDto.setLoanNo(loanNo);
        loanAttInDto.setAttTyp(PubBusinessConstant.ATTTYPE_HT);

        String attFileName = attachment.getNetworkAddress();
        loanAttInDto.setAttFile(attFileName);

        loanAttInDto.setAttName(attachment.getOriginalName());
        loanAttInDto.setAttNo(attachment.getId());
        contractLoanAttService.saveLoanAttInfo(loanAttInDto, userProfile);
        if (!StringUtils.isEmpty(attFileName)) {
            returnMap.put("downUrl", OssUtil.getPresignedUrl(loanAttInDto.getAttNo()));
        }
//        System.out.println("=====================================================");
//        System.out.println(OssUtil.getPresignedUrl(loanAttInDto.getAttNo()));
//        System.out.println("=====================================================");
        return returnMap;
    }

    /**
     * @describe 文件上传尚尚签
     * @author txia
     * datetime 2016/8/12 9:55
     * params [m]
     * return java.util.Map{downUrl:ssq文件下载地址}
     */
    @Transactional
    private java.util.Map word2FileServerSsqComp(Map m) throws UnsupportedEncodingException {
        //Map<String, String> map,PubLoanContractFile paramObj,UserProfile userProfile,String terminal
        java.util.Map returnMap = new HashedMap();
        Map<String, String> map = (Map<String, String>) m.get("paraMap");
        PubLoanContractFile paramObj = (PubLoanContractFile) m.get("contractModel");
        UserProfile userProfile = (UserProfile) m.get("userProfile");

        //生成放款通知书
        String loanNo = (String) map.get("loanNo");
        String path = (String) map.get("savePath");
        String downloadPath = ParamUtils.getParam("codeFilePath") + DateUtils.getCurDate() + "/";
        java.util.Date date = new java.util.Date();
        String saveName = paramObj.getFileName();
        if (paramObj.getFileName().indexOf("rtf") > 0) {
            saveName = paramObj.getFileName().replaceAll("rtf", "doc");
        }
        String fileName = loanNo + date.getTime() + saveName;
        //增加日期文件夹
        logger.debug("*****************************************************************生成合同" + downloadPath);

        Integer signPage = Integer.valueOf(paramObj.getSignPage());//柠檬签名页
        float signX = Float.valueOf(paramObj.getSignX());//柠檬签名x坐标
        float signY = Float.valueOf(paramObj.getSignY());//柠檬签名y坐标
        Integer csignPage = Integer.valueOf(paramObj.getcSignPage());//客户签名页
        float csignX = Float.valueOf(paramObj.getcSignX());//客户签名x坐标
        float csignY = Float.valueOf(paramObj.getcSignY());//客户签名y坐标
        //WordFileUtil.buildFile(map, fileName, path+paramObj.getFileName(), downloadPath); //将生成的word文档上传到文件服务器  ,返回BufferedInputStream
        RTFToWordUtil.rgModel(map, fileName, path + paramObj.getFileName(), downloadPath); //将生成的word文档上传到文件服务器  ,返回BufferedInputStream
        // this.contractSign(loanNo, downloadPath + fileName, signPage, signX, signY, csignPage, csignX, csignY, map.get("custName"), map.get("custMobile"), userProfile);
        returnMap.put("loanNo", loanNo);
        returnMap.put("filePath", downloadPath + fileName);
        returnMap.put("custName", map.get("custName"));
        returnMap.put("userProfile", userProfile);
        return returnMap;
    }
    /**
     *@describe 文件上传尚尚返回尚尚签信息
     *@author txia
     *datetime 2016/8/12 11:30
     *params java.util.Map{loanNo:贷款编号,filePath:文件地址,userProfile:用户信息,custName:用户姓名,custMobile:用户签名电话号码,saleMobile:销售电话号码,branchMobile:商户电话号码}
     *return java.util.Map{downUrl:合同下载url,ssqCustSignUrl:尚尚签客户签名url,ssqSaleSignUrl:尚尚签销售签名url,ssqBranchSignUrl:尚尚签商户签名url}
     */
    @Transactional
    private java.util.Map contractSignComp(java.util.Map m) throws Exception {
        //String loanNo,String filePath,int signPage,float signX,float signY,int csignPage,float csignX,float csignY,String custName,String custMobile,UserProfile userProfile
        String loanNo = m.get("loanNo").toString();
        String filePath = m.get("filePath").toString();
        UserProfile userProfile = (UserProfile) m.get("userProfile");
        String custName = m.get("custName").toString();
        String custMobile = threeNull(m.get("custMobile"));
        String staffMobl = threeNull(m.get("saleMobile"));
        String branchMobl = threeNull(m.get("branchMobile"));
        //根据合同号查找合同文件
        //上传并获取合同编号
        java.util.Map prodTypeMap = new HashMap<String, Object>();
        prodTypeMap.put("loanNo", loanNo);
        String prodType = contractSignInfoService.getProdType(prodTypeMap);
        logger.debug("*****************************************************************上传合同");
        List<Map<String, Object>> contractlistUser = new ArrayList<Map<String, Object>>();
        List<ReceiveUser> receiveList = new ArrayList<>();
        java.util.Map staffMoblMap = new HashMap<String, Object>();
        staffMoblMap.put("staffNo", userProfile.getStaffNo());
        Map staffMoblResultMap = contractSignInfoService.getStaffMobl(staffMoblMap);
        String staffName = (null == staffMoblResultMap.get("STAFF_NAME")||"".equalsIgnoreCase(staffMoblResultMap.get("STAFF_NAME").toString().trim())) ? "销售" : staffMoblResultMap.get("STAFF_NAME").toString();

        java.util.Map branchMoblMap = new HashMap<String, Object>();
        branchMoblMap.put("loanNo", loanNo);
        //商户电话号码
        Map branchMoblResultMap = contractSignInfoService.getBranchMobl(branchMoblMap);
        String branchName = (null == branchMoblResultMap.get("CONTCT_DUTY")||"".equalsIgnoreCase(branchMoblResultMap.get("CONTCT_DUTY").toString().toString().trim())) ? "商户" : branchMoblResultMap.get("CONTCT_DUTY").toString();


        ReceiveUser receiveUser = new ReceiveUser(custMobile, custName, custMobile, Constants.USER_TYPE.PERSONAL, Constants.CONTRACT_NEEDVIDEO.NONE, false);
        ReceiveUser receiveSale = new ReceiveUser(staffMobl, staffName, staffMobl, Constants.USER_TYPE.PERSONAL, Constants.CONTRACT_NEEDVIDEO.NONE, false);
        ReceiveUser receiveBranch = new ReceiveUser(branchMobl, branchName, branchMobl, Constants.USER_TYPE.PERSONAL, Constants.CONTRACT_NEEDVIDEO.NONE, false);
        //合同签署方
        HashMap<String, Object> switchSignMap = switchSign(custMobile,staffMobl,branchMobl);

        if(switchSignMap.containsKey("cust")){
            if (null == custMobile) {
                throw new ServiceException("客户电话号码不能为空");
            }
            receiveList.add(receiveUser);
        }
        if(switchSignMap.containsKey("sale")){
            if (null == staffMobl) {
                throw new ServiceException("销售电话号码不能为空");
            }
            receiveList.add(receiveSale);
        }
        if(switchSignMap.containsKey("branch")){
            if (null == branchMobl) {
                throw new ServiceException("商户电话号码不能为空");
            }
            receiveList.add(receiveBranch);
        }

        File file = new File(filePath);
        InputStream inpustStream = new FileInputStream(file);
        byte[] fileDate = IOUtils.toByteArray(inpustStream);
        //合同签订用户list
        ReceiveUser[] userlist = (ReceiveUser[]) receiveList.toArray(new ReceiveUser[receiveList.size()]);
        SendUser senduser = new SendUser(ParamUtils.getParam("compZfb"), ParamUtils.getParam("send_name"), ParamUtils.getParam("send_mobile"), 300, false, Constants.USER_TYPE.ENTERPRISE, false, custName,custName);
        Continfo[] confinfo = SsqSDKUtils.createContract(fileDate, userlist, senduser, file.getName());
        //得到合同编号
        String contractNo = confinfo[0].getSignid();
        String docid=confinfo[0].getDocid();
        IOUtils.closeQuietly(inpustStream);
        logger.debug("contractNo:" + contractNo);
        logger.debug("*****************************************************************");

        //记录生成的合同号 保存申请单、合同ID、签约状态、合同URL
        Map<String, Object> signInfoMap = new HashMap<String, Object>();
        signInfoMap.put("loanNo", loanNo);
        List<AppContractSignInfo> signInfoList = contractSignInfoService.queryForList(signInfoMap);
        if (null != signInfoList || !signInfoList.isEmpty()) {
            int deleteContractByLoanNo = contractSignInfoService.deleteContractByLoanNo(signInfoMap);
        }
        signInfoMap.put("contractNo", contractNo);
//        String custurl = this.getSsqSignUrl(loanNo, contractNo, "cust", "app", userProfile);
//        String saleurl = this.getSsqSignUrl(loanNo, contractNo, "sale", "app", userProfile);
//        String branchurl = this.getSsqSignUrl(loanNo, contractNo, "branch", "app", userProfile);
        List<AppContractSignInfo> list = new ArrayList<>();
        AppContractSignInfo cust = new AppContractSignInfo();
        cust.setLoanNo(loanNo);
        cust.setSignStatus(ContractContant.LOAN_SIGN_ED);
        cust.setContractNo(contractNo);
        cust.setContractUrl("");
        cust.setContractSignId(RandomUtil.getUUID());
        cust.setSignType("cust");
        cust.setDocid(docid);
        cust.setSignPhone(custMobile);
        AppContractSignInfo sale = new AppContractSignInfo();
        sale.setLoanNo(loanNo);
        sale.setSignStatus(ContractContant.LOAN_SIGN_ED);
        sale.setContractNo(contractNo);
        sale.setContractUrl("");
        sale.setContractSignId(RandomUtil.getUUID());
        sale.setSignType("sale");
        sale.setDocid(docid);
        sale.setSignPhone(staffMobl);
        AppContractSignInfo branch = new AppContractSignInfo();
        branch.setLoanNo(loanNo);
        branch.setSignStatus(ContractContant.LOAN_SIGN_ED);
        branch.setContractNo(contractNo);
        branch.setContractUrl("");
        branch.setContractSignId(RandomUtil.getUUID());
        branch.setSignType("branch");
        branch.setDocid(docid);
        branch.setSignPhone(branchMobl);
        //客户签名标志
        if(switchSignMap.containsKey("cust")){
            list.add(cust);
        }
        //销售签名标志
        if(switchSignMap.containsKey("sale")){
            list.add(sale);
        }
        //商户签名标志
        if(switchSignMap.containsKey("branch")){
            list.add(branch);
        }

        java.util.Map insertBatchMap = new HashMap<String, Object>();
        insertBatchMap.put("list", list);
        Integer insertBatch = contractSignInfoService.insertBatch(insertBatchMap);
        String downloadContract = SsqSDKUtils.getContractDownloadURL(contractNo);

        java.util.Map returnMap = new HashMap();
        java.util.Map paramMap = new HashMap<String, Object>();
        paramMap.put("loanNo", loanNo);
        List<Map> contract = contractSignInfoService.getContract(paramMap);
        if (null == contract || !contract.isEmpty()) {
            returnMap.put("cust", 0);
            returnMap.put("sale", 0);
            returnMap.put("branch", 0);
        } else {
            for (Map map : contract) {
                if (null != map.get("sign_type") && "cust".equals(map.get("sign_type").toString())) {
                    returnMap.put("cust", (null == map.get("sign_status")) ? 0 : map.get("sign_status").toString());
                }
                if (null != map.get("sign_type") && "sale".equals(map.get("sign_type").toString())) {
                    returnMap.put("sale", (null == map.get("sign_status")) ? 0 : map.get("sign_status").toString());
                }
                if (null != map.get("sign_type") && "branch".equals(map.get("sign_type").toString())) {
                    returnMap.put("branch", (null == map.get("sign_status")) ? 0 : map.get("sign_status").toString());
                }
            }
        }
        //合同下载url
        returnMap.put("downUrl", downloadContract);
        //合同编号
        returnMap.put("contractNo",contractNo);
        String terminal = threeNull(m.get("terminal"));
        String ssqReturnUrl = threeNull(m.get("ssqReturnUrl"));
        java.util.Map signMap=new HashedMap();
        signMap.put("loanNo",loanNo);
        signMap.put("signType","cust");
        if(null==terminal){
            signMap.put("terminal","app");
        }else{
            signMap.put("terminal",terminal);
        }
        signMap.put("ssqReturnUrl",ssqReturnUrl);
        signMap.put("userProfile",userProfile);
        signMap.put("mobile",custMobile);

        if(switchSignMap.containsKey("cust")){
            String ssqCustSignUrl = this.getSsqSignUrl(signMap);
            //尚尚签客户签名url
            returnMap.put("ssqCustSignUrl",ssqCustSignUrl);
        }

        signMap.put("signType","sale");
        signMap.put("mobile",staffMobl);

        if(switchSignMap.containsKey("sale")){
            String ssqSaleSignUrl = this.getSsqSignUrl(signMap);
            //尚尚签销售签名url
            returnMap.put("ssqSaleSignUrl",ssqSaleSignUrl);
        }

        signMap.put("signType","branch");
        signMap.put("mobile",branchMobl);
        if(switchSignMap.containsKey("branch")){
            String ssqBranchSignUrl = this.getSsqSignUrl(signMap);
            //尚尚商户售签名url
            returnMap.put("ssqBranchSignUrl",ssqBranchSignUrl);
        }

        return returnMap;
    }
    /**
     *@describe 决定由哪些方签订合同控制
     *@author txia
     *datetime 2016/8/12 14:20
     *params []
     *return java.util.HashMap<java.lang.String,java.lang.Object>
     */
    private HashMap<String,Object> switchSign(String custMobile,String staffMobl,String branchMobl){
        HashMap<String,Object> signMap=new HashMap<>();
        //客户签名标志
        String custSign = ParamUtils.getParam("cust_sign");
        if (null != custSign && !custSign.isEmpty()&&null!=custMobile) {
            signMap.put("cust","cust");
        }
        //销售签名标志
        String saleSign = ParamUtils.getParam("sale_sign");
        if (null != saleSign && !saleSign.isEmpty()&&null!=staffMobl) {
            signMap.put("sale","sale");
        }
        //商户签名标志
        String branchSign = ParamUtils.getParam("branch_sign");
        if (null != branchSign && !branchSign.isEmpty()&&null!=branchMobl) {
            signMap.put("branch","branch");
        }
        return signMap;
    }

    /**
     * 生成合同
     * @throws WriterException
     * @throws IOException
     */
    @Transactional
    public java.util.Map buidContant(String loanNo,UserProfile userProfile,String terminal) throws Exception {
        java.util.Map returnMap=new HashMap();
        if(StringUtils.isEmpty(loanNo)){
            throw new ServiceException("系统异常，生成合同分期编号不能为空");
        }
        LoanAcctOutDto loanAcctOutDto = contractLoanAcctService.getLoanAcct(loanNo, userProfile);//查询客户分期账户信息
        if(loanAcctOutDto == null){
            throw new ServiceException("获取分期信息异常");
        }
        //判断是否已经在尚尚签生成合同
        java.util.Map paramMap=new HashMap<String,Object>();
        paramMap.put("loanNo",loanNo);
        String contractNoByLoanNo = contractSignInfoService.getContractNoByLoanNo(paramMap);
        //判断是否已经上传至尚尚签，如果上传直接获取签名的url
        if(null!=contractNoByLoanNo){
            returnMap.put("signUrl",this.getSsqSignUrl(loanNo,"cust","app",userProfile));
            return returnMap;
        }
        List<Map> contract = contractSignInfoService.getContract(paramMap);
        if(null==contract||!contract.isEmpty()){
            returnMap.put("cust",0);
            returnMap.put("sale",0);
            returnMap.put("branch",0);
        }else{
            for (Map map : contract) {
                if(null!=map.get("sign_type")&&"cust".equals(map.get("sign_type").toString())){
                    returnMap.put("cust",(null==map.get("sign_status"))?0:map.get("sign_status").toString());
                }
                if(null!=map.get("sign_type")&&"sale".equals(map.get("sign_type").toString())){
                    returnMap.put("sale",(null==map.get("sign_status"))?0:map.get("sign_status").toString());
                }
                if(null!=map.get("sign_type")&&"branch".equals(map.get("sign_type").toString())){
                    returnMap.put("branch",(null==map.get("sign_status"))?0:map.get("sign_status").toString());
                }
            }
        }

        try{
            //生成还款单和确认单
            Map<String, String> map = new HashMap<String, String>();
            //客户号
            String custNo = loanAcctOutDto.getCustNo();
            //分期利率
            map.put("rateYear", loanAcctOutDto.getInterRate()==null?"0":loanAcctOutDto.getInterRate().toString());
            //计算本金+利息
            Map<SubjectType, Double> dtMap = PmtUtil.getMthRepayDtMap(loanAcctOutDto.getLoanAmt()==null?0d:loanAcctOutDto.getLoanAmt().doubleValue(),
                    Integer.valueOf(loanAcctOutDto.getInstNum()), 1, loanAcctOutDto.getInterRate()==null?0d:loanAcctOutDto.getInterRate().doubleValue());
            Double mthPrinInter = dtMap.get(SubjectType.prin_inter);
            mthPrinInter = ArithUtil.round(mthPrinInter, 2);
            map.put("mthPrinInter",mthPrinInter.toString());//甲方每期偿还本息数额
            //装填数据
            map.putAll(BeanRefUtil.getFieldValueMap(loanAcctOutDto));

            //分期信息
            double serviceCash = 0d;
            double isInsuuance = 0d;
            double ppRate = 0d;
            double loanAmt = loanAcctOutDto.getLoanAmt() == null ? 0 : loanAcctOutDto.getLoanAmt().doubleValue();
            if (loanAcctOutDto.getInterRate() != null) {
                ppRate = loanAcctOutDto.getInterRate()==null?0:loanAcctOutDto.getInterRate().doubleValue() / 100;
                serviceCash = Math.round(loanAmt*ppRate);
            }
            map.put("ppRate",loanAcctOutDto.getInterRate()==null?"0":loanAcctOutDto.getInterRate().doubleValue() + "%");//月客户服务费率
            map.put("amRateMonth",Double.toString(serviceCash));//月账户管理费率
            map.put("insFeeRate","");//月保险手续费（元）
            map.put("isRepayPackage","");//是否购买灵活还款服务包
            map.put("repayPackageFee","");//灵活还款服务包费

            map.put("firFee",loanAcctOutDto.getFstRepayAmt().toString());//手续费（元）
            map.put("custName", loanAcctOutDto.getCustName());//客户名称
            map.put("custNo", loanAcctOutDto.getCustNo());//客户号

            //工作信息
            //查询工作信息列表
            List<CustWorkDto> custWorkDtos = custWorkService.getCrtCustWorkLst(custNo);
            map.put("workUnit", "");//单位名称
            map.put("workDept", CollectionUtils.isEmpty(custWorkDtos)?"":custWorkDtos.get(0).getWorkDept());//任职部门
            map.put("workJob", CollectionUtils.isEmpty(custWorkDtos)?"":custWorkDtos.get(0).getWorkJob());//职位
            map.put("industry", CollectionUtils.isEmpty(custWorkDtos)?"": SimpleCodeUtils.getNameByCode(custWorkDtos.get(0).getIndustry()));//所属行业
            map.put("unitType", CollectionUtils.isEmpty(custWorkDtos)?"":SimpleCodeUtils.getNameByCode(custWorkDtos.get(0).getUnitType()));//单位性质
            map.put("workTimeSpan", CollectionUtils.isEmpty(custWorkDtos)?"":custWorkDtos.get(0).getWorkTime());//就职时间（月）
            map.put("linkMan", CollectionUtils.isEmpty(custWorkDtos)?"":custWorkDtos.get(0).getLinkMan());//单位联系人姓名
            map.put("unitTel", CollectionUtils.isEmpty(custWorkDtos)?"":custWorkDtos.get(0).getUnitTel());//单位联系人电话
            map.put("unitProv", CollectionUtils.isEmpty(custWorkDtos)?"":custWorkDtos.get(0).getUnitProvName());//工作省/直辖市
            map.put("unitCity", CollectionUtils.isEmpty(custWorkDtos)?"":custWorkDtos.get(0).getUnitCityName());//工作市
            map.put("unitArea", CollectionUtils.isEmpty(custWorkDtos)?"":custWorkDtos.get(0).getUnitAreaName());//工作区/县
            map.put("unitTown", "");//工作镇
            map.put("unitStreet", CollectionUtils.isEmpty(custWorkDtos)?"":custWorkDtos.get(0).getUnitAddr());//工作街道/路/村
            map.put("unitVillage", "");//工作小区/楼盘
            map.put("unitUnit", "");//工作栋/单元/房号

            //其它信息
            //查询其他信息
            List<CustOtherInfoDto> custOtherInfoDtos = custOtherInfoService.queryCustOtherInfoList(custNo);
            if(CollectionUtils.isNotEmpty(custOtherInfoDtos)){
                map.put("education", custOtherInfoDtos.get(0).getEdu());//受教育程度
                map.put("isLoaned", custOtherInfoDtos.get(0).getIsLoaned());//是否办理过相关业务
                map.put("income", custOtherInfoDtos.get(0).getIncome()==null?"0":""+custOtherInfoDtos.get(0).getIncome().doubleValue());//月收入（元）
                map.put("mthRepay", custOtherInfoDtos.get(0).getMthRepay()==null?"0":""+custOtherInfoDtos.get(0).getMthRepay().doubleValue());//月还款额
            }else{
                map.put("education", "");
                map.put("isLoaned", "");
                map.put("income", "");
                map.put("mthRepay", "");
            }

            //商品
            //获取取商品列表
            List<LoanGoodsDto> loanGoodsLst =loanAcctOutDto.getGoodsDto();
            if (loanGoodsLst != null && loanGoodsLst.size() > 0) {
                for (int i = 0; i < loanGoodsLst.size(); i++) {
                    LoanGoodsDto goodsVO = (LoanGoodsDto)loanGoodsLst.get(i);
                    map.put("goodsT"+i, SimpleCodeUtils.getNameByCode(goodsVO.getGoodsType()));//商品类型Ⅰ
                    map.put("brandT"+i, goodsVO.getBrand());//商品品牌Ⅰ
                    map.put("marquesT"+i, goodsVO.getMarques());//商品型号Ⅰ
//                    map.put("priceT"+i, goodsVO.getPric().toString());//商品单价(元)Ⅰ
//                    map.put("totalPrice", goodsVO.getPric().toString());//商品总价
                    map.put("priceT"+i, threeNu(goodsVO.getPric().setScale(0,BigDecimal.ROUND_CEILING)));//商品单价(元)Ⅰ
                    map.put("totalPrice", threeNu(goodsVO.getPric().setScale(0,BigDecimal.ROUND_CEILING)));//商品总价
                }
                //如果商品个数小于3,就空置一个商品行
                for (int i = loanGoodsLst.size(); i < 3; i++) {
                    map.put("goodsT"+i, "");
                    map.put("brandT"+i, "");
                    map.put("marquesT"+i, "");
                    map.put("priceT"+i, "");
                }
            }else{
                //如果没有商品就空置3个商品行
                for (int i = 0; i < 3; i++) {
                    map.put("goodsT"+i, "");
                    map.put("brandT"+i, "");
                    map.put("marquesT"+i, "");
                    map.put("priceT"+i, "");
                }
            }
            //家庭信息
            //查询关键联系人信息
            List<CustContctOtherDto> 	custOtherContactLst = contractCustViewService.getCustContctOtherLst(custNo);
            String famName = "";//家庭成员姓名
            String famRel = "";//关系
            String famTel = "";//家庭成员联系电话
            String mateName = "";//配偶姓名
            String mateTel = "";//配偶联系电话
            //多个联系人
            if (custOtherContactLst != null && custOtherContactLst.size() > 0) {
                for (int i = 0; i < custOtherContactLst.size(); i++) {
                    CustContctOtherDto coc = (CustContctOtherDto)custOtherContactLst.get(i);
                    map.put("contactName"+i, coc.getContactName());
                    if(ContractContant.FAM_MEMBER_REL_MATE.equals(coc.getContactRel())){//如果联系人是夫妻关系
                        mateName = coc.getContactName();//
                        mateTel = coc.getContactTel();
                    }
                    if(null==famRel && ContractContant.FAM_MEMBER_REL_FATHER.equals(coc.getContactRel())){//如果联系人是父亲
                        famRel = ContractContant.FAM_MEMBER_REL_FATHER;
                        famName = coc.getContactName();
                        famTel = coc.getContactTel();
                    }
                    if(null==famRel && ContractContant.FAM_MEMBER_REL_MATHER.equals(coc.getContactRel())){//如果联系人是父亲
                        famRel = ContractContant.FAM_MEMBER_REL_MATHER;
                        famName = coc.getContactName();
                        famTel = coc.getContactTel();
                    }
                }
            }
            map.put("mateName", mateName);
            map.put("famName", famName);
            map.put("famRel", SimpleCodeUtils.getNameByCode(famRel));
            map.put("famTel", famTel);
            map.put("mateTel", mateTel);
            map.put("isRegAdd", "");
            map.put("famAdd", "");
            //合同签署年，月，日
            java.util.Date applyDate = loanAcctOutDto.getApplyDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(applyDate);
            map.put("applyYear", cal.get(Calendar.YEAR)+"");
            map.put("applyMonth", cal.get(Calendar.MONTH)+1+"");
            map.put("applyDay", cal.get(Calendar.DATE)+"");
//			map.put("applyHour", cal.get(Calendar.HOUR_OF_DAY)+"");
//			map.put("applyMinute", cal.get(Calendar.MINUTE)+"");
//			map.put("applySecond", cal.get(Calendar.SECOND)+"");

            //合同签订地：门店所在省+城市
            String actyProv = loanAcctOutDto.getApplyProvName();
            String actyCity = loanAcctOutDto.getApplyCityName();
            map.put("actyProv", actyProv);
            map.put("actyCity", actyCity);

            map.put("firRepayDate", loanAcctOutDto.getFstRepayDate());//首次还款日
            //计划还款时间段
            //从
            String fstRepayDatestr = loanAcctOutDto.getFstRepayDate();

            map.put("fYear", fstRepayDatestr.substring(0, 4)+"");//年
            map.put("fMonth", fstRepayDatestr.substring(4,6)+"");//月
            map.put("fDay", fstRepayDatestr.substring(6,8)+"");//日
            Date fstRepayDate = DateUtils.parseDate(fstRepayDatestr,"yyyyMMdd");
            Date tarDate = DateUtils.getDate(fstRepayDate,loanAcctOutDto.getInstNum()-1);
            String tarDateStr = DateUtils.formatDate(tarDate, "yyyyMMdd");
            map.put("sysDate", DateUtils.getCurDate());
            //到
            map.put("loanEndDate", tarDateStr);//分期理论结束日期
            map.put("lYear", tarDateStr.substring(0, 4));//年
            map.put("lMonth", tarDateStr.substring(4,6));//月
            map.put("lDay", tarDateStr.substring(6,8));//日

            //生成二维码
            //生成 个人消费信贷确认单时需传入条码图片
            int width = 300;
            int height = 300;
            String format = "jpeg";//二维码的图片格式
            Hashtable hints = new Hashtable();
            hints.put(EncodeHintType.CHARACTER_SET, "utf8");////内容所使用编码
            hints.put(EncodeHintType.MARGIN, 0);//边距
            BitMatrix bitMatrix = new MultiFormatWriter().encode(loanNo,  BarcodeFormat.QR_CODE, width, height, hints);
            String codeFilePath = ParamUtils.getParam("codeFilePath");//从参数表里获取文件保存路径
            //QRCodeUtil.writeToFile(bitMatrix, format, codeFilePath, codeFileName);
            //添加二维码标记必须写成ewm
            //map.put("ewm", RTFToWordUtil.replaceStringToImage(codeFilePath,codeFileName,null,null).toString());
            logger.debug("*****************************************************************现场照");
            String codeFileName = loanNo+".jpeg";
            OneBarcodeUtil.createCodeAndSaveToFile(codeFilePath, codeFileName, "jpeg", loanNo);
            //添加条码 标记必须写成image
            map.put("image", RTFToWordUtil.replaceStringToImage(codeFilePath,codeFileName,null,"").toString());

            //添加客户照片
            //LoanAttInDto attDto = contractLoanAttService.queryLoanAtt(loanNo, PubBusinessConstant.ATTTYPE_XCZ);
//            String fileUrl =  OssUtil.getPresignedUrl(attDto.getAttNo());
//            map.put("twoIMG", RTFToWordUtil.replaceStringToImage(attDto.getAttFile(),attDto.getAttName(),PubBusinessConstant.ATTTYPE_XCZ,fileUrl).toString());

            //月还款金额
            //map.put("mothRepayAmt",String.valueOf(loanAcctOutDto.getMthRepayAmt().doubleValue()));
            map.put("mothRepayAmt",threeNu(loanAcctOutDto.getMthRepayAmt().setScale(0,BigDecimal.ROUND_CEILING)));
            //月还款额
            map.put("mothRepayDate", String.valueOf(loanAcctOutDto.getMthRepayDate()));

            //还款期数
            map.put("installNum", String.valueOf(loanAcctOutDto.getInstNum()));
            Map getFeeRatByLoanNoMap=new HashMap();
            getFeeRatByLoanNoMap.put("feeNum",loanAcctOutDto.getInstNum());
            getFeeRatByLoanNoMap.put("loanNo",loanNo);


            //首付金额
            map.put("dwnPayUpper",rmbUpper(String.valueOf(loanAcctOutDto.getFstPayAmt().doubleValue())));
            //map.put("downPay",String.valueOf(loanAcctOutDto.getFstPayAmt().doubleValue()));
            map.put("downPay",threeNu(loanAcctOutDto.getFstPayAmt().setScale(0,BigDecimal.ROUND_CEILING)));
            //分期本金
            //map.put("loanAmt", String.valueOf(loanAcctOutDto.getLoanAmt().doubleValue()));
            map.put("loanAmt", threeNu(loanAcctOutDto.getLoanAmt().setScale(0,BigDecimal.ROUND_CEILING)));
            BigDecimal goodPrice = loanAcctOutDto.getFstPayAmt().add(loanAcctOutDto.getLoanAmt());
            //map.put("goodPrice", String.valueOf(goodPrice.doubleValue()));
            //map.put("goodPrice", String.valueOf(goodPrice.doubleValue()));
            map.put("goodPrice",threeNu(goodPrice.setScale(0,BigDecimal.ROUND_CEILING)));
            map.put("goodPriceUpper", rmbUpper(String.valueOf(goodPrice.doubleValue())));
            //手续费
            map.put("firFee", loanAcctOutDto.getStrSeleFees());
            //审批日期
            map.put("aprovDate", DateUtils.getCurDate());

            //商户指定账户信息  add by hmzhou 20151029
            //商户信息
            LoanViewDto loanViewDto = contractLoanViewService.getLoanView(loanNo, userProfile);
            map.put("salerTel","");//分期顾问电话
            if(null != loanViewDto.getBranch()){
                map.put("marBankNo",loanViewDto.getBranch().getBranchNo());////甲方消费商户指定账户
                BranchDto branchDto = branchService.getByNo(loanViewDto.getBranch().getBranchNo());
                if(null!=branchDto){
                    map.put("marBankName",branchDto.getBankName());//甲方消费商户指定户名
                    map.put("marName",branchDto.getBranchName());//合作商户
                    map.put("marAddr",branchDto.getBranchAddr());//商户地址
                    map.put("strAddr",branchDto.getBranchAddr());//门店地址
                    map.put("strName",branchDto.getBranchName());//门店名称
                    map.put("strNo",branchDto.getBranchNo());//商户编号
                    map.put("contractAddr",branchDto.getBranchAddr());//合同签订地
                }else{
                    map.put("marBankName","");
                    map.put("marName","");
                    map.put("marAddr","");
                    map.put("strAddr","");
                    map.put("strName","");
                    map.put("strNo","");
                    map.put("contractAddr","");//合同签订地
                }
            }else{
                map.put("marBankNo","");
                map.put("marBankName","");
                map.put("marName","");
                map.put("marAddr","");
                map.put("strAddr","");
                map.put("strName","");
                map.put("strNo","");
                map.put("contractAddr","");//合同签订地
            }
            logger.debug("**************************************************");
            //还款信息
            if(null != loanViewDto.getBankAcct()){
                map.put("custOpenOrg", StringUtils.isNotEmpty(loanViewDto.getBankAcct().getOpenOrg())?SimpleCodeUtils.getNameByCode(loanViewDto.getBankAcct().getOpenOrg()):"");//开户行
                map.put("custAccount", StringUtils.isNotEmpty(loanViewDto.getBankAcct().getAcctNo())?loanViewDto.getBankAcct().getAcctNo():"");//客户个人账户
                map.put("bankAccount", StringUtils.isNotEmpty(loanViewDto.getBankAcct().getAcctNo())?loanViewDto.getBankAcct().getAcctNo():"");//客户个人账户
                map.put("aliasCAName", StringUtils.isNotEmpty(loanViewDto.getBankAcct().getAcctName())?loanViewDto.getBankAcct().getAcctName():"");//户名
                //map.put("Name", loanViewDto.getBankAcct().getAcctName());
                map.put("marBankOrg", StringUtils.isNotEmpty(loanViewDto.getBankAcct().getOpenOrgName())?loanViewDto.getBankAcct().getOpenOrgName():"");//开户行
            }else{
                map.put("custOpenOrg","");
                map.put("custAccount", "");
                map.put("bankAccount", "");
                map.put("aliasCAName", "");
                //map.put("Name", "");
                map.put("marBankOrg","");
            }
            //还款随心包
            List<LoanFeeDto> loanFees = loanViewDto.getFees();
            map.put("isAttach", "否");
            map.put("insFeeAmt", "0");
            map.put("isInsuuance", "否");
            map.put("attachAmt", "0");
            if(null != loanFees && loanFees.size() > 0){
                for (LoanFeeDto loanFeeDto : loanFees) {
                    if("2601".equals(loanFeeDto.getFeeNo()) || "2001".equals(loanFeeDto.getFeeNo())){
                        if("2601".equals(loanFeeDto.getFeeNo())){
                            map.put("isAttach", "是");
                            map.put("attachAmt", loanFeeDto.getFeeVal());
                        }else{
                            map.put("isInsuuance", "是");
                            isInsuuance = Math.round(loanAmt*0.0069);
                            //map.put("insFeeAmt",Double.toString(isInsuuance));//申请参加保险
                            map.put("insFeeAmt",threeNu(new BigDecimal(isInsuuance).setScale(0,BigDecimal.ROUND_CEILING)));//申请参加保险
                        }
                    }else{
                        map.put("isAttach", "否");
                        map.put("attachAmt", "0");
                        map.put("isInsuuance", "否");
                        map.put("insFeeAmt","0");//申请参加保险
                    }
                }
            }
            if(null != loanViewDto.getSaler()){
                map.put("salerName",loanViewDto.getSaler().getStaffName());//分期顾问姓名
            }else{
                map.put("salerName","");
            }
            //查询客户信息
            CustInfoDto custInfoDto = contractCustInfoService.getByNo(custNo);
            if(custInfoDto == null){
                throw new ServiceException("获取客户信息异常");
            }
            //查询客户居住信息
            CustLiveInfoDto custLiveInfo = custLiveInfoService.getCrtByNo(custNo);
            //查询联系信息
            List<CustContctInfoDto> custContctInfoDtos = contractCustContctInfoService.getCrtContctInfoLst(custNo);
            CustContctInfoDto custContactDto = null;
            if(!custContctInfoDtos.isEmpty()){
                custContactDto = custContctInfoDtos.get(0);
            }
            logger.debug("**************************************************2");
            return this.buildApplyWord(map,loanNo,custInfoDto,custLiveInfo,custContactDto,userProfile, terminal);
        }catch(IOException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }

        //审批通过时生成分期申请书
        //生成分期申请书
        return null;

    }

    /**
     * 生成分期申请书
     * @param paraMap
     * @param loanNo
     * @param custInfoDto
     * @param custLiveInfo
     * @param custContactDto
     * @return
     * @throws ServiceException
     * @throws UnsupportedEncodingException
     * @throws ServerException
     */
    private java.util.Map buildApplyWord(Map<String,String> paraMap,String loanNo,CustInfoDto custInfoDto,CustLiveInfoDto custLiveInfo,CustContctInfoDto custContactDto
            ,UserProfile userProfile,String terminal) throws ServiceException, UnsupportedEncodingException{
        logger.debug("**************************************************3");
        //客户基本信息
        paraMap.put("custNo", custInfoDto.getCustNo());
        paraMap.put("sex",custInfoDto==null?"":SimpleCodeUtils.getNameByCode(custInfoDto.getSex()));//性别
        paraMap.put("certType", custInfoDto==null?"":SimpleCodeUtils.getNameByCode(custInfoDto.getCertType()));//证件类型
        paraMap.put("custName", custInfoDto==null?"":custInfoDto.getCustName());//客户名称
        paraMap.put("custMobile", custInfoDto==null?"":custInfoDto.getPhoneNo());//客户手机号
        paraMap.put("certNo", custInfoDto==null?"":custInfoDto.getCertNo());//证件号
        paraMap.put("certIssuOrg", custInfoDto==null?"":custInfoDto.getCertIssuOrg());//发证机关
        paraMap.put("certValidDate", custInfoDto==null?"":custInfoDto.getCertValidDate());//身份证有效期
        paraMap.put("regProv", custInfoDto==null?"":custInfoDto.getRegProvName());//户籍省/直辖市
        paraMap.put("regCity", custInfoDto==null?"":custInfoDto.getRegCityName());//户籍市
        paraMap.put("regArea", custInfoDto==null?"":custInfoDto.getRegAreaName());//户籍区/县
        paraMap.put("regTown", "");//户籍镇
        paraMap.put("regUnit", "");//户籍栋/单元/房号
        paraMap.put("regVillage", "");//户籍小区/楼盘
        paraMap.put("regStreet", custInfoDto==null?"":custInfoDto.getRegAddr());//户籍街道/路/村
        //现居住地
        paraMap.put("isRegLive", custLiveInfo==null?"":SimpleCodeUtils.getNameByCode(custLiveInfo.getIsRegLive()));//与户籍地址相同
        paraMap.put("liveBuildType", custLiveInfo==null?"":SimpleCodeUtils.getNameByCode(custLiveInfo.getLiveBuildType()));//住房类型
        paraMap.put("mthAmtHouse", custLiveInfo==null?"0":(custLiveInfo.getMthAmt() ==null ?"0": (""+custLiveInfo.getMthAmt().doubleValue())));//月均支出（元）
        paraMap.put("liveTimeSpan", custLiveInfo==null?"":custLiveInfo.getLiveTimeSpan());//现住址居住时间(月)
        paraMap.put("liveProv", custLiveInfo==null?"":custLiveInfo.getLiveProvName());//现居住省/直辖市
        paraMap.put("liveCity", custLiveInfo==null?"":custLiveInfo.getLiveCityName());//现居住
        paraMap.put("liveArea", custLiveInfo==null?"":custLiveInfo.getLiveAreaName());//现居住
        paraMap.put("liveTown", "");//现居住
        paraMap.put("liveStreet", custLiveInfo==null?"":custLiveInfo.getLiveAddr());//现居住
        paraMap.put("custAddr", custLiveInfo==null?"":custLiveInfo.getLiveAddr());//现居住
        paraMap.put("liveVillage", "");//现居住
        paraMap.put("liveUnit", "");//现居住栋/单元/房号
        //联系方式
        paraMap.put("mobileTel", custInfoDto==null?"":custInfoDto.getPhoneNo());//手机号码
        paraMap.put("mthTelBill", custContactDto==null?"":custContactDto.getMthTelFee());//月均话费
        paraMap.put("mobileTimeSpan", custContactDto==null?"":custContactDto.getPhoneYears());//使用年限（月）
        paraMap.put("qq", custContactDto==null?"":custContactDto.getQq());//QQ号码
        paraMap.put("email", custContactDto==null?"":custContactDto.getEmail());//电子邮箱
        paraMap.put("marStatus", custContactDto==null?"":SimpleCodeUtils.getNameByCode(custInfoDto.getMarriage()));//婚姻状况
        paraMap.put("homeTel",custContactDto==null?"": custContactDto.getHomeTel());//住宅电话
        paraMap.put("homeTelOwner", custContactDto==null?"":custContactDto.getHomeTelOwner());//住宅电话登记人
        paraMap.put("custType", custInfoDto==null?"":SimpleCodeUtils.getNameByCode(custInfoDto.getCustType()));//客户代码

        //分期资金匹配 对象
        LoanFundMatchDto loanFundMathc = loanFundMatchService.queryLoanFund(loanNo);
        if(loanFundMathc == null){
            throw new ServiceException("资金匹配失败");
        }
        paraMap.put("chalCode", loanFundMathc.getChanNo());//渠道代码
        paraMap.put("proNo", "");//促销员代码
        paraMap.put("actyCode", "");//活动代码

        //模板下载路径
        paraMap.put("savePath", ParamUtils.getParam("codeFilePath"));

        //根据渠道码和申请合同类型查询合同文件列表
        String fileTyp = null;
        if(custInfoDto!=null){
            fileTyp = PubBusinessConstant.CONTRACTTYPE_CR;//申请单个人合同
//			if(PubBusinessConstant.CUSTTYPE_CR.equals(custInfoDto.getCustType())){
//			}else{
//				fileTyp = PubBusinessConstant.CONTRACTTYPE_XS;//申请单学生合同
//			}
        }
		/*List<LoanContractFileDto> contractFileLst = loanContractService.queryContractListByType(fileTyp,loanFundMathc.getChanNo());
		if(CollectionUtils.isEmpty(contractFileLst)){
			throw new ServiceException("没有找到合同模版!");
		} */
        if(StringUtils.isEmpty(loanFundMathc.getChanNo())){
            throw new ServiceException("渠道信息为空");
        }
        if(StringUtils.isEmpty(fileTyp)){
            throw new ServiceException("附件类型为空");
        }
        Map<String,Object> map = new HashMap<>();
        //map.put("fileTyp", fileTyp);
        map.put("chanNo", loanFundMathc.getChanNo());
        List<PubLoanContractFile> contractFileLst = pubLoanContractFileService.queryForList(map);
        if(contractFileLst == null  || contractFileLst.size() ==0){
            throw new ServiceException("获取渠道信息失败,请联系运营.");
        }
        logger.debug("*****************************************************************合同模板");
        PubLoanContractFile contractModel =  /* new PubLoanContractFile(); */ contractFileLst.get(0);
		/* contractModel.setFileCod("templatesc.rtf");
		contractModel.setFileName("templatesc.rtf");
		contractModel.setSignX("0.2");
		contractModel.setSignY("0.2");
		contractModel.setcSignPage("5");
		contractModel.setSignPage("5");
		contractModel.setcSignX("0.2");
		contractModel.setcSignY("0.6"); */
        return this.word2FileServer(paraMap,contractModel,userProfile,terminal);
    }

    private java.util.Map word2FileServer(Map<String, String> map,PubLoanContractFile paramObj,UserProfile userProfile,String terminal) throws UnsupportedEncodingException{
        //生成放款通知书
        String loanNo = (String) map.get("loanNo");
        String path = (String) map.get("savePath");
        String downloadPath = ParamUtils.getParam("codeFilePath")+DateUtils.getCurDate()+"/";
        java.util.Date date = new java.util.Date();
        String saveName = paramObj.getFileName();
        if (paramObj.getFileName().indexOf("rtf") > 0) {
            saveName = paramObj.getFileName().replaceAll("rtf", "doc");
        }
        String fileName = loanNo+date.getTime()+saveName;
        //增加日期文件夹


        logger.debug("*****************************************************************生成合同"+downloadPath);

        Integer signPage = Integer.valueOf(paramObj.getSignPage());//柠檬签名页
        float signX = Float.valueOf(paramObj.getSignX());//柠檬签名x坐标
        float signY = Float.valueOf(paramObj.getSignY());//柠檬签名y坐标
        Integer csignPage = Integer.valueOf(paramObj.getcSignPage());//客户签名页
        float csignX = Float.valueOf(paramObj.getcSignX());//客户签名x坐标
        float csignY = Float.valueOf(paramObj.getcSignY());//客户签名y坐标
        //WordFileUtil.buildFile(map, fileName, path+paramObj.getFileName(), downloadPath); //将生成的word文档上传到文件服务器  ,返回BufferedInputStream
        RTFToWordUtil.rgModel(map, fileName, path+paramObj.getFileName(), downloadPath); //将生成的word文档上传到文件服务器  ,返回BufferedInputStream
        if("pc".equals(terminal)){
            /**  上传合同文件到阿里云oos start by hwen 20160303*/
            Attachment attachment = null;

            try {
                attachment = this.uploadContract(new FileInputStream(new File(downloadPath+fileName)), fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new ServiceException("检查合同失败");
            }
            //上传SSO更新分期附件信息表
            LoanAttInDto loanAttInDto =new LoanAttInDto();
            loanAttInDto.setCustNo((String) map.get("custNo"));
            loanAttInDto.setLoanNo(loanNo);
            loanAttInDto.setAttTyp(PubBusinessConstant.ATTTYPE_HT);

            String attFileName=attachment.getNetworkAddress();
            loanAttInDto.setAttFile(attFileName);

            loanAttInDto.setAttName(attachment.getOriginalName());
            loanAttInDto.setAttNo(attachment.getId());
            contractLoanAttService.saveLoanAttInfo(loanAttInDto, userProfile);

            if(!StringUtils.isEmpty(attFileName)){
                java.util.Map returnmap=new HashMap();
                returnmap.put("downUrl",OssUtil.getPresignedUrl(loanAttInDto.getAttNo()));
                return returnmap;
            }else{
                return null;
            }

//			/**  上传合同文件到阿里云oos end */
///		暂时不用上上签//return attFileName;
        }else {
            return this.contractSign(loanNo, downloadPath+fileName, signPage, signX, signY, csignPage,csignX,csignY,map.get("custName"), map.get("custMobile"),userProfile);
        }
    }

    /**
     * 上传附件
     *
     * @param in 附件数据
     * @param fileName 附件类型
     */
    @Transactional
    public Attachment uploadContract(InputStream in,String fileName){
        try {
            Attachment attachment = new Attachment();
            attachment.setFileSize(new Long(in.available()));
            attachment.setContentType("word");
            attachment.setOriginalName(fileName);
            attachment.setPresentName(fileName);
            attachment.setPhysicalAddress(ParamUtils.getParam("basePhysicalAddressDir") + DateUtils.getCurDate() + "/" + attachment.getPresentName());
            attachment.setNetworkAddress(ParamUtils.getParam("baseNetworkAddressDir")+ attachment.getPresentName());

            fileTransfer.upload(in, attachment);
            in.close();

            AttachmentApi sysAttachServer = SpringContextHolder.getBean("sysAttachServer");
            attachment = sysAttachServer.save(attachment);
            return attachment;
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            logger.error("上传文件失败：" + e.getMessage(), e);
            throw new AppException("上传文件失败");
        }
    }

    /**
     * 金额格式化
     * @param price
     * @return
     */
    private String priceFormat(Object price){
        if(null != price && !StringUtils.isEmpty(price.toString())){
            BigDecimal decimal = new BigDecimal(price.toString());
            return new DecimalFormat("###,###").format(decimal);
        }
        return "";
    }



    /**
     * 生成合同签名url
     * @param loanNo 合同号
     * @param filePath 合同路径
     * @param signPage
     * @param signX
     * @param signY
     * @param csignPage
     * @param csignX
     * @param csignY
     * @param custName 客户名
     * @param custMobile 客户电话
     * @return
     * @throws UnsupportedEncodingException
     */
    @Transactional
    private java.util.Map contractSign(String loanNo,String filePath,int signPage,float signX,float signY,int csignPage,float csignX,float csignY,String custName,String custMobile,UserProfile userProfile) throws UnsupportedEncodingException {
        try{//根据合同号查找合同文件
            //上传并获取合同编号
            java.util.Map prodTypeMap=new HashMap<String,Object>();
            prodTypeMap.put("loanNo",loanNo);
            String prodType = contractSignInfoService.getProdType(prodTypeMap);
            logger.debug("*****************************************************************上传合同");
            List<Map<String, Object>> contractlistUser = new ArrayList<Map<String, Object>>();
            if(null==custMobile){
                throw new ServiceException("客户电话号码不能为空");
            }
            List<ReceiveUser> receiveList=new ArrayList<>();
            if(null!=prodType&&("20104002".equals(prodType)||"20104001".equals(prodType))){
                Map<String, Object> contrinfoMapUser = new LinkedHashMap<String, Object>();
                contrinfoMapUser.put("email", "");
                contrinfoMapUser.put("name", custName);
                contrinfoMapUser.put("needvideo", ContractContant.needvideo);
                contrinfoMapUser.put("mobile", custMobile);
                contrinfoMapUser.put("usertype", ContractContant.USERTYPE_PERSION);
                contrinfoMapUser.put("Signimagetype", ContractContant.signimagetype);
                contractlistUser.add(contrinfoMapUser);
                //custMobile="18702876708";
                //用户签名
                ReceiveUser receiveUser=new ReceiveUser(custMobile,custName,custMobile, Constants.USER_TYPE.PERSONAL, Constants.CONTRACT_NEEDVIDEO.NONE, false);
                //企业签名
                ReceiveUser receiveEnter=new ReceiveUser("cw@lemonjinfu.com","柠檬","18181990358", Constants.USER_TYPE.ENTERPRISE, Constants.CONTRACT_NEEDVIDEO.NONE, false);
                receiveList.add(receiveUser);
                receiveList.add(receiveEnter);
            }else{
                java.util.Map staffMoblMap=new HashMap<String,Object>();
                staffMoblMap.put("staffNo",userProfile.getStaffNo());
                Map staffMoblResultMap = contractSignInfoService.getStaffMobl(staffMoblMap);
                //销售人员电话号码
                String staffMobl=(null==staffMoblResultMap.get("MOBL_NO"))?null:staffMoblResultMap.get("MOBL_NO").toString();
                String staffName=(null==staffMoblResultMap.get("STAFF_NAME"))?null:staffMoblResultMap.get("STAFF_NAME").toString();
                if(null==staffMobl){
                    throw new ServiceException("销售电话号码不能为空");
                }
                java.util.Map branchMoblMap=new HashMap<String,Object>();
                branchMoblMap.put("loanNo",loanNo);
                //商户电话号码
                Map branchMoblResultMap = contractSignInfoService.getBranchMobl(branchMoblMap);
                String branchMobl = (null==branchMoblResultMap.get("CONTCT_TEL"))?null:branchMoblResultMap.get("CONTCT_TEL").toString();
                String branchName = (null==branchMoblResultMap.get("CONTCT_DUTY"))?null:branchMoblResultMap.get("CONTCT_DUTY").toString();
                if(null==branchMobl){
                    throw new ServiceException("商户电话号码不能为空");
                }
                ReceiveUser receiveUser=new ReceiveUser(custMobile,custName,custMobile, Constants.USER_TYPE.PERSONAL, Constants.CONTRACT_NEEDVIDEO.NONE, false);
                ReceiveUser receiveSale=new ReceiveUser(staffMobl,staffName,staffMobl, Constants.USER_TYPE.PERSONAL, Constants.CONTRACT_NEEDVIDEO.NONE, false);
                ReceiveUser receiveBranch=new ReceiveUser(branchMobl,branchName,branchMobl, Constants.USER_TYPE.PERSONAL, Constants.CONTRACT_NEEDVIDEO.NONE, false);
                //客户签名标志
                String custSign = ParamUtils.getParam("cust_sign");
                if(null!=custSign&&!custSign.isEmpty()){
                    receiveList.add(receiveUser);
                }
                //客户签名标志
                String saleSign = ParamUtils.getParam("sale_sign");
                if(null!=saleSign&&!saleSign.isEmpty()){
                    receiveList.add(receiveSale);
                }
                //商户签名标志
                String branchSign = ParamUtils.getParam("branch_sign");
                if(null!=branchSign&&!branchSign.isEmpty()){
                    receiveList.add(receiveBranch);
                }
            }
            File file=new File(filePath);
            InputStream inpustStream=new FileInputStream(file);
            byte[] fileDate= IOUtils.toByteArray(inpustStream);
            //ReceiveUser[] u = {new ReceiveUser("1234567@qq.com", "Test1", "13812345678", Constants.USER_TYPE.PERSONAL, Constants.CONTRACT_NEEDVIDEO.NONE, false)};
            ReceiveUser[] userlist= (ReceiveUser[]) receiveList.toArray(new ReceiveUser[receiveList.size()]);
            ObjectMapper objectMapper=new ObjectMapper();
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
             System.out.println("userlist:"+objectMapper.writeValueAsString(receiveList));
            SendUser senduser = new SendUser(ParamUtils.getParam("send_email"),ParamUtils.getParam("send_name"), ParamUtils.getParam("send_mobile"), 300, false, Constants.USER_TYPE.ENTERPRISE, false, "签名", "签名");
            Continfo[] confinfo = SsqSDKUtils.createContract(fileDate, userlist, senduser,file.getName());
            System.out.println("==========================ssq result===============================");
            System.out.println("ssq result:"+com.alibaba.fastjson.JSON.toJSONString(confinfo));
            logger.info("ssq result:"+com.alibaba.fastjson.JSON.toJSONString(confinfo));
            //合同编号
            String contractNo=confinfo[0].getSignid();
            //文档编号
            String docid = confinfo[0].getDocid();
            //企业自动签名公章
            autoSign(loanNo, contractNo);
            //String contractNo = SsqianUtils.uploadContract(new String(filePath.getBytes(),"GBK"),contractlistUser);
            IOUtils.closeQuietly(inpustStream);
            logger.debug("contractNo:"+contractNo);
            logger.debug("*****************************************************************");
            //记录生成的合同号
            //保存申请单、合同ID、签约状态、合同URL
            Map<String,Object> signInfoMap = new HashMap<String,Object>();
            signInfoMap.put("loanNo", loanNo);
            List<AppContractSignInfo> signInfoList = contractSignInfoService.queryForList(signInfoMap);
            if(null!=signInfoList||!signInfoList.isEmpty()){
                int deleteContractByLoanNo = contractSignInfoService.deleteContractByLoanNo(signInfoMap);
            }
            signInfoMap.put("contractNo", contractNo);
            //现金贷只有用户签名
            if(null!=prodType&&("20104002".equals(prodType)||"20104001".equals(prodType))){
                //柠檬签名
                //SsqianUtils.autoSign(contractNo, ParamUtils.getParam("send_email"), signPage, signX, signY);//"it_service@tksincerity.com"
                //记录签约状态到分期签约信息表
                signInfoMap.put("status", ContractContant.LOAN_SIGN_ED);
                //获取签名URL
                //String url = SsqianUtils.getSignUrl(contractNo,custMobile, csignPage,csignX,csignY);
                //System.out.println("尚尚签的签名URL：" + url);
                //将用户签名合同生成的url记录到分期签约信息表
                String url=null;
                signInfoMap.put("url",url);
                List<AppContractSignInfo> list=new ArrayList<>();
                AppContractSignInfo signInfoObj = new AppContractSignInfo();
                signInfoObj.setLoanNo(loanNo);
                signInfoObj.setSignStatus(ContractContant.LOAN_UN_SIGN);
                signInfoObj.setContractNo(contractNo);
                signInfoObj.setContractUrl(url);
                signInfoObj.setContractSignId(RandomUtil.getUUID());
                signInfoObj.setSignType("cust");
                signInfoObj.setDocid(docid);
                list.add(signInfoObj);
                java.util.Map insertBatchMap=new HashMap<String,Object>();
                insertBatchMap.put("list",list);
                Integer insertBatch = contractSignInfoService.insertBatch(insertBatchMap);
            }else{
                java.util.Map staffMoblMap=new HashMap<String,Object>();
                staffMoblMap.put("staffNo",userProfile.getStaffNo());
                Map staffMoblResultMap = contractSignInfoService.getStaffMobl(staffMoblMap);
                //销售人员电话号码
                String staffMobl=(null==staffMoblResultMap.get("MOBL_NO"))?null:staffMoblResultMap.get("MOBL_NO").toString();
                java.util.Map branchMoblMap=new HashMap<String,Object>();
                branchMoblMap.put("loanNo",loanNo);
                //商户电话号码
                Map branchMoblResultMap = contractSignInfoService.getBranchMobl(branchMoblMap);
                String branchMobl = (null==branchMoblResultMap.get("CONTCT_TEL"))?null:branchMoblResultMap.get("CONTCT_TEL").toString();
                String custurl =this.getSsqSignUrl(loanNo,contractNo,"cust","app",userProfile);
                String saleurl =this.getSsqSignUrl(loanNo,contractNo,"sale","app",userProfile);
                String branchurl = this.getSsqSignUrl(loanNo,contractNo,"branch","app",userProfile);
                List<AppContractSignInfo> list=new ArrayList<>();
                AppContractSignInfo cust = new AppContractSignInfo();
                cust.setLoanNo(loanNo);
                cust.setSignStatus(ContractContant.LOAN_UN_SIGN);
                cust.setContractNo(contractNo);
                cust.setContractUrl(custurl);
                cust.setContractSignId(RandomUtil.getUUID());
                cust.setSignType("cust");
                cust.setDocid(docid);
                AppContractSignInfo sale = new AppContractSignInfo();
                sale.setLoanNo(loanNo);
                sale.setSignStatus(ContractContant.LOAN_UN_SIGN);
                sale.setContractNo(contractNo);
                sale.setContractUrl(saleurl);
                sale.setContractSignId(RandomUtil.getUUID());
                sale.setSignType("sale");
                sale.setDocid(docid);
                AppContractSignInfo branch = new AppContractSignInfo();
                branch.setLoanNo(loanNo);
                branch.setSignStatus(ContractContant.LOAN_UN_SIGN);
                branch.setContractNo(contractNo);
                branch.setContractUrl(branchurl);
                branch.setContractSignId(RandomUtil.getUUID());
                branch.setSignType("branch");
                branch.setDocid(docid);
                //客户签名标志
                String custSign = ParamUtils.getParam("cust_sign");
                if(null!=custSign&&!custSign.isEmpty()){
                    list.add(cust);
                }
                //客户签名标志
                String saleSign = ParamUtils.getParam("sale_sign");
                if(null!=saleSign&&!saleSign.isEmpty()){
                    list.add(sale);
                }
                //商户签名标志
                String branchSign = ParamUtils.getParam("branch_sign");
                if(null!=branchSign&&!branchSign.isEmpty()){
                    list.add(branch);
                }
                java.util.Map insertBatchMap=new HashMap<String,Object>();
                insertBatchMap.put("list", list);
                Integer insertBatch = contractSignInfoService.insertBatch(insertBatchMap);
            }
            String downloadContract = SsqSDKUtils.getContractDownloadURL(contractNo);
            java.util.Map returnMap =new HashMap();
            java.util.Map paramMap=new HashMap<String,Object>();
            paramMap.put("loanNo",loanNo);
            List<Map> contract = contractSignInfoService.getContract(paramMap);
            if(null==contract||!contract.isEmpty()){
                returnMap.put("cust",0);
                returnMap.put("sale",0);
                returnMap.put("branch",0);
            }else{
                for (Map map : contract) {
                    if(null!=map.get("sign_type")&&"cust".equals(map.get("sign_type").toString())){
                        returnMap.put("cust",(null==map.get("sign_status"))?0:map.get("sign_status").toString());
                    }
                    if(null!=map.get("sign_type")&&"sale".equals(map.get("sign_type").toString())){
                        returnMap.put("sale",(null==map.get("sign_status"))?0:map.get("sign_status").toString());
                    }
                    if(null!=map.get("sign_type")&&"branch".equals(map.get("sign_type").toString())){
                        returnMap.put("branch",(null==map.get("sign_status"))?0:map.get("sign_status").toString());
                    }
                }
            }
            returnMap.put("downUrl",downloadContract);
            returnMap.put("signUrl",this.getSsqSignUrl(loanNo,"cust","app",userProfile));
            return returnMap;
            //return url;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 企业自动签署公章
     * @param loanNo 贷款编号
     * @param contractNo 合同编号
     * @throws Exception
     */
    private void autoSign(String loanNo, String contractNo) throws Exception {
        if(null==loanNo){
            throw new ServerException("贷款编号为空");
        }
        if(null==contractNo){
            throw new ServerException("合同编号为空");
        }
            Map xyPageMap = getXYPage(loanNo);
            Object sign_x = xyPageMap.get("SIGN_X");
            if(null==sign_x){
                throw new ServerException("企业签名的X坐标为空");
            }
            Object sign_y = xyPageMap.get("SIGN_Y");
            if(null==sign_y){
                throw new ServerException("企业签名的Y坐标为空");
            }
            Object sign_page = xyPageMap.get("SIGN_PAGE");
            if(null==sign_page){
                throw new ServerException("企业签名的页数为空");
            }
            //自动签名
        AutoSignbyCAResult autoSignbyCAResult=null;
            try {
                autoSignbyCAResult = SsqSDKUtils.autoSignbyCA(contractNo,"cw@lemonjinfu.com", Integer.valueOf(sign_page.toString()).intValue(), Float.valueOf(sign_x.toString()).floatValue(), Float.valueOf(sign_y.toString()).floatValue(), false);
            }catch (Exception e){

            }
        System.out.println(loanNo+"==>自动签名+"+ JSON.toJSONString(autoSignbyCAResult));
    }

    /**
     * 更新客户签约状态
     * @param param
     * @throws ServiceException
     * @throws AppException
     */
    @Transactional
    public void updateCustSignStatus(Map<String,Object> param) throws ServiceException, AppException {
        param.put("custSignStatus", ContractContant.LOAN_SIGN_ED);
        contractSignInfoService.updateByContractNo(param);
    }
    /**
     * 更新客户签约状态
     * @param loanNo
     * @throws ServiceException
     * @throws AppException
     */
    @Transactional
    public String  getsignUrlByLoanNo(String loanNo) throws  ServiceException,AppException{
        AppContractSignInfo appContractSignInfo	=contractSignInfoService.getByPrimaryKey(loanNo);
        if(appContractSignInfo==null || StringUtils.isEmpty(appContractSignInfo.getContractUrl())){
            throw new ServiceException("生成合同失败");
        }
        return appContractSignInfo.getContractUrl();
    }
}
