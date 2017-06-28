package com.nm.impl.usercenter;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.commons.attach.Attachment;
import com.hs.commons.attach.AttachmentApi;
import com.hs.commons.attach.OssUtil;
import com.hs.commons.bo.SimpleCode;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.commons.service.ISimpleCodeService;
import com.hs.loan.busi.dto.LoanAttInDto;
import com.hs.loan.busi.dto.LoanSalerDto;
import com.hs.loan.contract.api.AppContractSignApi;
import com.hs.loan.finance.api.AccCapWithApi;
import com.hs.loan.finance.dto.SingleRepayDto;
import com.hs.loan.finance.withpay.dto.SingleDkResultDto;
import com.hs.loan.sale.api.LoanAcctApi;
import com.hs.loan.sale.api.LoanAttApi;
import com.hs.system.service.AttachmentService;
import com.hs.utils.*;
import com.nm.api.frame.auth.model.AppCustInfo;
import com.nm.base.framework.core.exception.ParameterException;
import com.nm.base.framework.core.exception.ServiceException;
import com.nm.base.framework.core.exception.SystemException;
import com.nm.base.framework.core.validate.Method;
import com.nm.base.framework.core.validate.Validator;
import com.nm.base.wechat.service.MediaService;
import com.nm.cmd.LoanCustFileCmd;
import com.nm.cmd.OCLoanAttInDto;
import com.nm.cmd.OCOssAttach;
import com.nm.cmd.RepayCmd;
import com.nm.core.comp.bean.NewAppUserInfo;
import com.nm.mapper.login.SysCodInfoApiMapper;
import com.nm.mapper.usercenter.UserAcctApiMapper;
import com.nm.service.usercenter.NOssFileTransfer;
import com.nm.service.usercenter.UserAcctApiService;
import com.nm.util.*;

import net.sf.json.JSONObject;
import scala.annotation.meta.param;

import org.apache.commons.io.FilenameUtils;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 
 * @author wangqin
 *
 * @date 2017年5月10日 下午9:50:45
 */
@Service
@SuppressWarnings({"unchecked", "unused", "rawtypes"})
public class UserAcctApiServiceImpl implements UserAcctApiService {

    @Resource
    private UserAcctApiMapper userAcctApiMapper;
    @Resource
    private AppContractSignApi loanContractSignService;
    @Resource
    private LoanAttApi loanAttservice;
    @Autowired
    private AccCapWithApi busiAccCapWithService;
    @Resource
    private AttachmentApi attachmentservice;
    @Resource
    private AttachmentService attachmentService;
    @Resource
    private MediaService mediaservice;
    @Resource
    private LoanAcctApi loanAcctservice;
    @Autowired
    private ISimpleCodeService simpleCodeService;
    @Autowired
    private NOssFileTransfer nOssFileTransfer;
    
    @Resource
    private SysCodInfoApiMapper sysCodInfoApiMapper;
    
    private static final Logger log = LoggerFactory.getLogger(UserAcctApiServiceImpl.class);

    @Override
    public List<Map<String, Object>> queryAcctInstv1(List<AppCustInfo> appCustInfo) {
        List<Map<String, Object>> unPayMap;
        List<Map<String, Object>> retMap = new ArrayList<>();
        Map<String, Object> loanMap;
        List list = new ArrayList();
       if (appCustInfo == null || appCustInfo.size() ==0) {
        	 return retMap;
        }
        for (int i = 0; i < appCustInfo.size(); i++) {
        	String  cust = appCustInfo.get(i).getCustNo();
        	list.add(cust);
        }
        if (list == null || list.size() ==0) {
       	 return retMap;
        }
        //1.查询客户的贷款
        List<Map<String, Object>> acctMap = userAcctApiMapper.queryAcctByCustNo(list);
        if (acctMap == null  ||acctMap.size() ==0) {
            acctMap = new ArrayList<>();
            return acctMap;
        }
        //2.循环acctMap,查询每笔贷款的还款信息
        for (int i = 0; i < acctMap.size(); i++) {
            loanMap = acctMap.get(i);
            if(loanMap==null){
               return retMap;
            }
            String loanNo = acctMap.get(i).get("loanNo").toString();
            //未还款
            unPayMap = userAcctApiMapper.queryNoInst(loanNo);
            if(unPayMap==null || unPayMap.size() == 0){
                unPayMap = new ArrayList<>();
                loanMap.put("isPayNull",true);
            }else{
                loanMap.put("isPayNull",false);
            }
            loanMap.put("unPay", unPayMap);
            loanMap.put("serverDate",System.currentTimeMillis());
            retMap.add(loanMap);
        }
        return retMap;
    }

    @Override
    public List<Map<String, Object>> queryAcctInstv2(List<AppCustInfo> appCustInfo) {
        List<Map<String, Object>> payMap;
        List<Map<String, Object>> retMap = new ArrayList<>();
        Map<String, Object> loanMap;
        List list = new ArrayList();
        if (appCustInfo == null || appCustInfo.size() ==0) {
       	 return retMap;
       }
       for (int i = 0; i < appCustInfo.size(); i++) {
       	String  cust = appCustInfo.get(i).getCustNo();
       	list.add(cust);
       }
       if (list == null || list.size() ==0) {
      	 return retMap;
        }
        //1.查询客户的贷款
        List<Map<String, Object>> acctMap = userAcctApiMapper.queryAcctByCustNo(list);
        if (acctMap == null ||acctMap.size() ==0) {
            acctMap = new ArrayList<>();
            return acctMap;
        }
        //2.循环acctMap,查询每笔贷款的还款信息
        for (int i = 0; i < acctMap.size(); i++) {
            loanMap = acctMap.get(i);
            if(loanMap==null){
                return retMap;
            }
            String loanNo = acctMap.get(i).get("loanNo").toString();
            //已还款
            payMap = userAcctApiMapper.queryYesInst(loanNo);
            if(payMap==null || payMap.size() == 0){
                payMap = new ArrayList<>();
                loanMap.put("isPayNull",true);
            }else{
                loanMap.put("isPayNull",false);
            }
            loanMap.put("pay", payMap);
            loanMap.put("serverDate",System.currentTimeMillis());
            retMap.add(loanMap);
        }
        return retMap;
    }

    @Override
    public Map<String, Object> queryLoanAcct(String loanNo,String num) {
    	 Validator.init(loanNo,"贷款编号").required().end();
    	 Validator.init(num,"贷款期数").required().end();
        Map<String,Object> retMap=userAcctApiMapper.queryLoanDetails(loanNo);
        Map<String,Object> retMap1=userAcctApiMapper.queryLoanNumDetails(loanNo,num);
        Map<String,Object> retMap2=userAcctApiMapper.queryAcctByLoanNo(loanNo);
        retMap.putAll(retMap2);
        retMap.putAll(retMap1);
        return retMap;
    }
    
    
    @Override
    public Map<String, Object> queryLoanAcct2(String loanNo) {
    	
    	 Validator.init(loanNo,"贷款编号").required().end();
    	 
        Map<String,Object> retMap=userAcctApiMapper.queryLoanDetails2(loanNo);
        if(retMap == null){
        	 return retMap;
        }
       String  goodsTypeName   =  sysCodInfoApiMapper.getCodeNameByTypeAndNum("googsType", retMap.get("goodsType").toString());
       retMap.put("goodsTypeName", goodsTypeName);
        return retMap;
    }

    @Override
    public List<Map<String, String>> queryMyLoan(List<AppCustInfo> appCustInfo) {
    	List<Map<String,String>> retList=new ArrayList<>();
    	 if (appCustInfo == null || appCustInfo.size() ==0) {
        	 return retList;
        }
        List list = new ArrayList();
        
        for (int i = 0; i < appCustInfo.size(); i++) {
        	String  cust = appCustInfo.get(i).getCustNo();
        	list.add(cust);
        }
        if (list == null || list.size() ==0) {
       	 return retList;
       }
        List<Map<String,String>> retMap=userAcctApiMapper.queryMyLoan(list);
        
        if(retMap==null || retMap.isEmpty()){
            return retList;
        }
        for(int i=0;i<retMap.size();i++){
            Map<String,String> map=retMap.get(i);
            if(map.get("frtpaystat")==null){
                map.put("frtpaystat","0");
            }
            if(map.get("payAmt")==null){
                map.put("payAmt","0");
            }
          /*  BigDecimal bgval=new BigDecimal(0.00);
            //主动查询三方扣款状态
            if(!String.valueOf(map.get("payAmt")).equals("0.00") && !map.get("frtpaystat").equals("20109003")){
                Map<String,Object> map1=userAcctApiMapper.queryOptSn(map.get("loanNo"));
                if(map1!=null && map1.size()>0) {
                    String retStr = this.getOptSnPayStat(map1);
                    //1：支付成功 2：支付失败 3：支付中
                    if (retStr.equals("1")) {
                        map.put("frtpaystat","20109003");
                    } else if (retStr.equals("2")) {
                        map.put("frtpaystat","20109001");
                    } else {
                        map.put("frtpaystat","20109001");
                    }
                }
            }*/
            retList.add(map);
        }
        return retList;
    }

   
	@Override
    public Map<String, Object> buidContantSsq(UserProfile userProfile, Map<String, String> rqMap) {
        if (rqMap==null || rqMap.size()==0){
            throw new ServiceException("签约数据错误");
        }
        String loanNo = rqMap.containsKey("loanNo")?rqMap.get("loanNo"):"";
        if(Method.isBlank(loanNo)){
            throw new ParameterException("贷款编号为空！");
        }
        String terminal=rqMap.containsKey("terminal")?rqMap.get("terminal"):"";
        if(Method.isBlank(terminal)){
            throw new ParameterException("terminal为空！");
        }
        String custMobile=rqMap.containsKey("custMobile")?rqMap.get("custMobile"):"";

        String saleMobile=rqMap.containsKey("saleMobile")?rqMap.get("saleMobile"):"";

        String branchMobile=rqMap.containsKey("branchMobile")?rqMap.get("branchMobile"):"";

        String ssqReturnUrl=rqMap.containsKey("ssqReturnUrl")?rqMap.get("ssqReturnUrl"):"";

        String goodsType=userAcctApiMapper.queryGoodsType(loanNo);
        if(goodsType==null || goodsType.equals("")){
            throw new ServiceException("商品类型为空");
        }
        Map<String,Object> map=new HashMap<String,Object>();
        //旅游    医美   一方签 （用户）   无提货照
        if(goodsType.equals("20200005") || goodsType.equals("20200006") ||goodsType.equals("20200004")){
            /*if(goodsType.equals("20200005")){
                //判断是否上传提货照
                LoanAttInDto loanatt = loanAttservice.queryLoanAtt(loanNo, "40103407");
                if (loanatt.getAttNo() == null) throw new ServiceException("请先上传提货照");
            }*/
            if(Method.isBlank(custMobile)){
                throw new ParameterException("客户号码为空！");
            }
            if(!this.isPhoneNumber(custMobile)){
                throw new ParameterException("请输入正确的号码！");
            }
            //放入签约客户电话号码
            map.put("custMobile",custMobile);
        }
        //现金贷  销售和客户
        else if(goodsType.equals("20200007")){
            //判断是否上传提货照
            /*LoanAttInDto loanatt = loanAttservice.queryLoanAtt(loanNo, "40103407");
            if (loanatt.getAttNo() == null) throw new ServiceException("请先上传提货照");*/
            if(Method.isBlank(custMobile)){
                throw new ParameterException("客户号码为空！");
            }
            if(Method.isBlank(saleMobile)){
                throw new ParameterException("销售号码为空！");
            }
            if(!this.isPhoneNumber(custMobile) || !this.isPhoneNumber(saleMobile)){
                throw new ParameterException("请输入正确的号码！");
            }
            if(custMobile.equals(saleMobile)){
                throw new ParameterException("客户和销售号码不能相同！");
            }
            //放入签约电话号码
            map.put("custMobile",custMobile);
            map.put("saleMobile",saleMobile);
        }
        //3c产品 三方签 用户 商家 销售
        else if(goodsType.equals("20200001") || goodsType.equals("20200002") || goodsType.equals("20200003")){
            //判断是否上传提货照
            LoanAttInDto loanatt = loanAttservice.queryLoanAtt(loanNo, "40103407");
            if (loanatt.getAttNo() == null) throw new ServiceException("请先上传提货照");
            if(Method.isBlank(custMobile)){
                throw new ParameterException("客户号码为空！");
            }
            if(Method.isBlank(branchMobile)){
                throw new ParameterException("网点号码为空！");
            }
            if(Method.isBlank(saleMobile)){
                throw new ParameterException("销售号码为空！");
            }
            if(!this.isPhoneNumber(custMobile) || !this.isPhoneNumber(branchMobile) || !this.isPhoneNumber(saleMobile)){
                throw new ParameterException("请输入正确的号码！");
            }
            if(custMobile.equals(saleMobile)){
                throw new ParameterException("客户和销售号码不能相同！");
            }
            if(custMobile.equals(branchMobile)){
                throw new ParameterException("客户和网点号码不能相同！");
            }
            if(saleMobile.equals(branchMobile)){
                throw new ParameterException("销售和网点号码不能相同！");
            }
            //放入签约电话号码
            map.put("custMobile",custMobile);
            map.put("saleMobile",saleMobile);
            map.put("branchMobile",branchMobile);
        }else{
            throw new ServiceException("无该商品类型");
        }
        //封装签约其他信息
        map.put("loanNo",loanNo);
        LoanSalerDto loansalerdto = loanAcctservice.queryLoanSaler(loanNo);
        userProfile.setOrgNo(loansalerdto.getOrgNo());
        userProfile.setStaffNo(loansalerdto.getStaffNo());
        userProfile.setStaffName(loansalerdto.getStaffName());
        map.put("userProfile",userProfile);
        map.put("ssqReturnUrl",ssqReturnUrl);
        map.put("terminal",terminal);
        Map<String,Object> resultMap=new HashMap<String,Object>();
        try {
            resultMap=loanContractSignService.buidContantSsq(map);
        } catch (Exception e) {
        	throw new SystemException("签约失败[01]!", e);
        }

        if(resultMap == null || resultMap.isEmpty()){
            throw new ServiceException("签约失败[02]！");
        }
        
        Map retMap=new HashMap();
        //旅游 现金贷 医美整形  一方签  用户
        if(goodsType.equals("20200005") || goodsType.equals("20200006") || goodsType.equals("20200004")){
            retMap.put("isSignCust",resultMap.get("isSignCust"));
            retMap.put("ssqCustSignUrl",resultMap.containsKey("ssqCustSignUrl")?resultMap.get("ssqCustSignUrl"):"");
            retMap.put("getContractViewURL",resultMap.get("getContractViewURL"));
            retMap.put("isUpload",resultMap.get("isUpload"));
        }
        // 保证金 二方签 用户 商家
        else if(goodsType.equals("20200007")){
            retMap.put("isSignCust",resultMap.get("isSignCust"));
            retMap.put("ssqCustSignUrl",resultMap.containsKey("ssqCustSignUrl")?resultMap.get("ssqCustSignUrl"):"");
            retMap.put("isSignSale",resultMap.get("isSignSale"));
            retMap.put("ssqSaleSignUrl",resultMap.containsKey("ssqSaleSignUrl")?resultMap.get("ssqSaleSignUrl"):"");
            retMap.put("getContractViewURL",resultMap.get("getContractViewURL"));
            retMap.put("isUpload",resultMap.get("isUpload"));
        }
        //3c产品 三方签 用户 商家 销售
        else if(goodsType.equals("20200001") || goodsType.equals("20200002") || goodsType.equals("20200003")){
            retMap.put("isSignCust",resultMap.get("isSignCust"));
            retMap.put("ssqCustSignUrl",resultMap.containsKey("ssqCustSignUrl")?resultMap.get("ssqCustSignUrl"):"");
            retMap.put("isSignBranch",resultMap.get("isSignBranch"));
            retMap.put("ssqBranchSignUrl",resultMap.containsKey("ssqBranchSignUrl")?resultMap.get("ssqBranchSignUrl"):"");
            retMap.put("isSignSale",resultMap.get("isSignSale"));
            retMap.put("ssqSaleSignUrl",resultMap.containsKey("ssqSaleSignUrl")?resultMap.get("ssqSaleSignUrl"):"");
            retMap.put("getContractViewURL",resultMap.get("getContractViewURL"));
            retMap.put("isUpload",resultMap.get("isUpload"));
        }
        return retMap;
    }

    @Override
    public Map isAddSsqPhone(String loanNo) {
        List<Map> retrunMap=new ArrayList<>();
        Map retMap=new HashMap();
        //LoanAttInDto loanatt = loanAttservice.queryLoanAtt(loanNo, "40103407");
        //if (loanatt.getAttNo() == null) throw new ServiceException("请先上传提货照");
        Validator.init(loanNo,"贷款编号").required().end();
        
        List<Map> listMap = loanContractSignService.getContractInfoDetail(loanNo);
        if(listMap==null || listMap.size()==0){
            return retMap;
        }
        Map reMap=new HashMap();
        for(int i=0;i<listMap.size();i++){
            reMap=listMap.get(i);
            if(reMap.get("sign_type").equals("cust")){
                retMap.put("custPhone",reMap.get("sign_phone"));
            }
            else if(reMap.get("sign_type").equals("branch")){
                retMap.put("branchPhone",reMap.get("sign_phone"));
            }
            else if(reMap.get("sign_type").equals("sale")){
                retMap.put("salePhone",reMap.get("sign_phone"));
            }
        }
        return retMap;
    }
    /*重签*/
    @Override
    public void reContantSsq(String loanNo) {
        int number=0;
        Validator.init(loanNo,"贷款编号").required().end();
        try {
            number = loanContractSignService.resetSsq(loanNo);
        } catch (Exception e) {
            throw new ServiceException("重签dubbo抛出异常"+e.getMessage());
        }
        if(number<1){
            throw new ServiceException("重签失败");
        }
    }
    /**
     * 扣款
     * @param rqMap
     * @return
     */
    @Override
    public List<RepayCmd> dkRepayLoan(List<Map> rqMap, NewAppUserInfo appCustInfo) {
    	
    	Validator.init(rqMap.get(0), "扣款信息").required().end()
    	       .get("loanNo", "贷款编号").required().end()
    	       .get("curInstNum", "当前期数").required().end();
    	
        if(rqMap==null || rqMap.size()<1){
            throw new ParameterException("数据错误");
        }
        if(rqMap.size()>1){
            throw new ParameterException("只支持单扣");
        }
        Map<String,String> repayDetail=null;
        RepayCmd repayCmd=null;
        SingleRepayDto singleRepayDto=null;
        SingleDkResultDto singleDkResultDto=null;
        UserProfile userProfile=new UserProfile();
        userProfile.setLoginNo(appCustInfo.getCustName());
        List<RepayCmd> repayDtos=new ArrayList<>();
        Map<String, Object> params=new HashMap<>();
        for (int i = 0; i < rqMap.size(); i++) {
            repayDetail=rqMap.get(i);

			/*封装扣款结果信息*/
            params.put("loanNo", repayDetail.get("loanNo"));
            params.put("repayNum", repayDetail.get("curInstNum"));
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            repayCmd=userAcctApiMapper.queryRepayByLoanNoAndCurInstNum(params);
            if(repayCmd==null){
                throw new ServiceException("请求数据错误!");
            }
            int count=userAcctApiMapper.selectOverdueInst(repayDetail.get("loanNo"));
            if(count>0){
                throw new ServiceException("存在逾期，不能扣款请联系客服");
            }
            repayCmd.setSubmitDate(simpleDateFormat.format(new Date()));
			/*获取扣款信息*/
            singleRepayDto=doGetSingleRepayDto(repayDetail);
            singleRepayDto.setTranType("ZJPAY");
            repayCmd.setStateCode("200");
			/*调用扣款接口*/
            try {
                singleDkResultDto=busiAccCapWithService.singleRepay(singleRepayDto, userProfile);
            } catch (Exception e) {
                repayCmd.setMessage(e.getMessage());
                repayCmd.setStateCode("405");
                repayDtos.add(repayCmd);
                throw new ServiceException(repayCmd.getMessage());
            }
            repayCmd.setMessage(singleDkResultDto.getRetItem().getErrMsg());
            if(!repayCmd.getStateCode().equals("200")){
                repayCmd.setStateCode("405");
                repayDtos.add(repayCmd);
                throw new ServiceException(repayCmd.getMessage());
            }
            repayDtos.add(repayCmd);
        }
        return repayDtos;
    }

    private SingleRepayDto doGetSingleRepayDto(Map<String,String> repayDetail) {
        Page<SingleRepayDto> page=new Page<>();
        Map<String, Object> params=new HashMap<>();
        params.put("loanNo", repayDetail.get("loanNo"));
        params.put("repayNum", repayDetail.get("curInstNum"));
        page.setParams(params);
        SingleRepayDto singleRepayDto=null;
        page=busiAccCapWithService.querySingleRepayListForPage(page);
        if(page.getList()==null||page.getList().size()<1){
            singleRepayDto=userAcctApiMapper.querySingleRepay(params);
            singleRepayDto.setBankName(SimpleCodeUtils
                    .getNameByCode(singleRepayDto.getBankNo()));
            singleRepayDto.setWithStat("20109001");
            singleRepayDto.setId("0000");
        }else{
            singleRepayDto=page.getList().get(0);
        }
        return singleRepayDto;
    }

    /**
     * 查询附件
     * @return
     */
    @Override
    public LoanAttInDto queryAttachment(String loanNo){
    	
    	Validator.init(loanNo,"贷款编号").required().end();
    	  
        List<LoanAttInDto> list = loanAttservice.queryLoanAttInfo(loanNo);
        /*
		身份证正面	40103401
		身份证反面	40103402
		银行卡		40103403
		现场照		40103404
		学生证		40103405
		通话详单	40103406
		提货照		40103407
		其他1		40103410
		其他2		40103411
		其他3		40103412
		费用减免凭证	40103480
		合同		40103499
		工作证明	40103413
		社保卡		40103414
		工资卡及其流水	40103415
		居住证明	40103416
		户口本		40103417
		房产证		40103418
	*/
        LoanAttInDto reAttInDto=new LoanAttInDto();
        for (LoanAttInDto attInDto : list) {
            if (attInDto.getAttTyp().equals("40103407")) {
                reAttInDto.setAttTyp("40103407");
                reAttInDto.setAttTyp(attInDto.getAttTyp());
                reAttInDto.setAttFile(OssUtil.getPresignedUrl(attInDto.getAttNo()));
                reAttInDto.setAttName(attInDto.getAttName());
                reAttInDto.setAttNo(attInDto.getAttNo());
                reAttInDto.setCustNo(attInDto.getCustNo());
                reAttInDto.setLoanNo(attInDto.getLoanNo());
                break;
            }
        }
        return reAttInDto;
    }

    /**
     * 附件删除
     * @param loanNo
     * @param attType
     * @param id
     */
    @Override
    public void deleteAttachment(String loanNo,String attType,String id){
    	  Validator.init(loanNo,"贷款编号").required().end();
          Validator.init(attType,"附件类型").required().end();
          Validator.init(id,"附件编号").required().end();
    	
        attachmentservice.removeById(id);
        loanAttservice.deleteLoanAtt(loanNo,attType);
    }
 
    /**
     * 上传附件
     */
    @Override
    public OCLoanAttInDto uploadAttachmentTemp(MultipartFile multipartFile, String custNo, String loanNo, String attTyp, UserProfile userProfile) {
        if(StringUtils.isBlank(custNo)) throw new ParameterException("custNo is empty");
        if(StringUtils.isBlank(loanNo)) throw new ParameterException(" is empty");
        Validator.init(custNo,"客户编号").required().end();
        Validator.init(loanNo,"贷款编号").required().end();
        Validator.init(attTyp,"贷款类型").required().end();


        log.info(loanNo+"*******************附件上传参数*******************");
        log.info("loanNo=>"+loanNo);
        log.info("attTyp=>"+attTyp);
        log.info("size=>"+multipartFile.getSize());
        log.info("type=>"+multipartFile.getOriginalFilename());
        log.info("*******************附件上传参数*******************");

        String originalFilename = multipartFile.getOriginalFilename(); //文件名
        String extension = FilenameUtils.getExtension(originalFilename); //扩展名

        if (com.alibaba.dubbo.common.utils.StringUtils.isNotEmpty(extension)) extension = extension.toLowerCase();
        if (com.alibaba.dubbo.common.utils.StringUtils.isEmpty(loanNo)) throw new com.hs.base.exception.ServiceException("附件上传失败：贷款编号不能为空.");
        if (com.alibaba.dubbo.common.utils.StringUtils.isEmpty(attTyp)) throw new com.hs.base.exception.ServiceException("附件上传失败：请重试.");
        Attachment attachment = null;
        boolean canUpload=attachmentService.canUpload(multipartFile, extension); //验证是否能支持上传
        if(canUpload){
            attachment = attachmentService.uploadImage(multipartFile, extension);
        }
        /*获取销售信息*/
        LoanSalerDto loansalerdto = loanAcctservice.queryLoanSaler(loanNo);
        if(loansalerdto!=null){
            userProfile.setOrgNo(loansalerdto.getOrgNo());
            userProfile.setStaffNo(loansalerdto.getStaffNo());
            userProfile.setStaffName(loansalerdto.getStaffName());
        }
        //上传SSO更新分期附件信息表
        LoanAttInDto loanAttInDto = new LoanAttInDto();
        loanAttInDto.setLoanNo(loanNo);
        loanAttInDto.setAttTyp(attTyp);
        loanAttInDto.setAttFile(attachment.getNetworkAddress());
        loanAttInDto.setAttName(attachment.getOriginalName());
        loanAttInDto.setAttNo(attachment.getId());
        loanAttInDto.setCustNo(custNo);
        loanAttservice.saveLoanAttInfo(loanAttInDto, userProfile);

        OCLoanAttInDto ocLoanAttInDto = new OCLoanAttInDto();
        BeanUtils.copyProperties(loanAttInDto, ocLoanAttInDto);
        //OCOssAttach ocOsssAttach = nOssFileTransfer.getOCOsssAttach(ocLoanAttInDto.getAttNo());
        OCOssAttach ocOsssAttach = nOssFileTransfer.getOCOsssAttach(ocLoanAttInDto.getAttNo(),15);
        ocLoanAttInDto.setAttName(ocOsssAttach.getAttName());
        ocLoanAttInDto.setAttFile(ocOsssAttach.getAttFile());
        ocLoanAttInDto.setAttFileThumb(ocOsssAttach.getAttFileThumb());


        return ocLoanAttInDto;
    }
    /**
     * 查询附件
     *
     * @return
     */
    @Override
    public List<OCLoanAttInDto> queryAttachmentv2(String loanNo) {
    	
        Validator.init(loanNo,"贷款编号").required().end();
    
        List<LoanAttInDto> list = loanAttservice.queryLoanAttInfo(loanNo);
        List<OCLoanAttInDto> attInDtoResultlist = new ArrayList<OCLoanAttInDto>();
        for (LoanAttInDto attInDto : list) {
            /*attInDto.setAttTyp(attInDto.getAttTyp());
            attInDto.setAttFile(OssUtil.getPresignedUrl(attInDto.getAttNo()));
            attInDtoResultlist.add(attInDto);*/

            OCLoanAttInDto ocLoanAttInDto = new OCLoanAttInDto();
            BeanUtils.copyProperties(attInDto, ocLoanAttInDto);
            //OCOssAttach ocOsssAttach = nOssFileTransfer.getOCOsssAttach(ocLoanAttInDto.getAttNo());
            OCOssAttach ocOsssAttach = nOssFileTransfer.getOCOsssAttach(ocLoanAttInDto.getAttNo(),60);
            ocLoanAttInDto.setAttName(ocOsssAttach.getAttName());
            ocLoanAttInDto.setAttFile(ocOsssAttach.getAttFile());
            ocLoanAttInDto.setAttFileThumb(ocOsssAttach.getAttFileThumb());
            attInDtoResultlist.add(ocLoanAttInDto);
        }
        return attInDtoResultlist;
    }


    @Override
    public String queryContractUrl(String loanNo) {
        if(loanNo==null ||loanNo.equals("")){
            throw new ParameterException("贷款编号为空");
        }
        String url=loanContractSignService.getContractViewURL(loanNo);
        return url;
    }
    //微信支付宝扣款
    @Override
    public Map<String, String> payMsg(String loanNo,String payType,String repayNum,String tranChan,String planId,String buyerId,String openId,String payFlag) {
        if(loanNo==null || loanNo.isEmpty()){
            throw new ParameterException("loanNo为空");
        }
        if(planId==null || planId.isEmpty()){
            throw new ParameterException("planId为空");
        }
        if(openId==null && buyerId==null){
            if(openId==null){
                throw new ParameterException("未绑定微信");
            }
            if(buyerId==null){
                throw new ParameterException("未绑定支付宝");
            }
        }
        if(openId.isEmpty() && buyerId.isEmpty()){
            if(openId.isEmpty()){
                throw new ParameterException("未绑定微信");
            }
            if(buyerId.isEmpty()){
                throw new ParameterException("未绑定支付宝");
            }
        }
        Map<String, String> retMap=new HashMap<>();
        Map<String, Object> optmap=new HashMap<>();
        //请求订单号
        String optSn="";
        //签名信息
        String signDate="";
        //交易金额
        String tradeBalance="";
        //交易账户
        String acctName="";
        //交易账户卡号
        String acctNo="";
        //回调参数
        String biz_back_params="";
        Map<String, Object> params=new HashMap<>();
        RepayCmd repayCmd=null;
        if(payFlag.equals("wxpay")){
            retMap.put("open_id",openId);
            retMap.put("trade_desc","微信支付");
            optmap.put("tradeDesc", "微信支付");
        }else{
            retMap.put("buyer_id",buyerId);
            retMap.put("trade_desc","支付宝支付");
            optmap.put("tradeDesc", "支付宝支付");
        }
        //判断是首付还是还款
        if(payType.equals("isfirstPay")) {
            //Map<String,Object> map11=userAcctApiMapper.queryOptSn(loanNo);
            //判断是否已有订单
            //if(map11==null || map11.size()<1){
                optmap.put("optType", "isfirstPay");
                optmap.put("optNum", null);
                String goodsType=userAcctApiMapper.queryGoodsType(loanNo);
                if(!goodsType.equals("20200006")){
                    throw new ServiceException("不需要首付（只有旅游保证金才需要首付）");
                }
                //查询首付信息
                Map<String, Object> map = userAcctApiMapper.queryFirstAmt(loanNo);
                if (map == null || map.isEmpty()) {
                    throw new ServiceException("没有首付信息");
                }
                //获取首付金额
                tradeBalance=map.get("payAmt").toString();
                if(tradeBalance==null || tradeBalance.isEmpty() || tradeBalance.equals("0") || tradeBalance.equals("0.00")){
                    throw new ServiceException("没有首付金额");
                }
                if(map.get("stat").toString().equals("20109002")){
                    throw new ServiceException("扣款中");
                }
                Map<String,String> bankMap=userAcctApiMapper.queryBankInfo(loanNo);
                try {
                    acctName=bankMap.get("acctName").toString();
                    acctNo=bankMap.get("acctNo").toString();
                } catch (Exception e) {
                    acctName ="n/a";
                    acctNo="0000";
                    e.printStackTrace();
                }
                /*acctName=map.get("custName").toString();
                acctNo=map.get("cardNo").toString();*/
                //回传参数
                retMap.put("notify_url",PayCommand.firstPayNotifyUrl);
                retMap.put("notify_url", ParamUtils.getParam("firstPayNotifyUrl"));
                Map<String, String> map1=new HashMap<>();
                map1.put("loanNo",loanNo);
                map1.put("acctName",acctName);
                map1.put("acctNo",acctNo);
                map1.put("tranChan",tranChan);
                JSONObject json = JSONObject.fromObject(map1);
                biz_back_params=json.toString();
                optSn=MessageUtil.createSN(planId, PayCommand.merchantNo);
                Map<String, String> reqParam=new HashMap<String, String>();
                reqParam.put("merchant_no",PayCommand.merchantNo);
                reqParam.put("opt_sn",optSn);
                reqParam.put("trade_balance",tradeBalance);
                //reqParam.put("trade_sn",tradeBalance);
                signDate=MessageUtil.addSignRSA(MessageUtil.geneFpayMsg(reqParam), SDKConstants.PRIVATE_KEY.value());
                //添加订单
                optmap.put("id", RandomUtil.getUUID());
                optmap.put("merchantNo", PayCommand.merchantNo);
                optmap.put("planId", planId);
                optmap.put("optSn", optSn);
                optmap.put("tradeBalance", tradeBalance);
                optmap.put("bizBackParams", biz_back_params);
                optmap.put("loanNo", loanNo);
                optmap.put("instDate", new Date());
                userAcctApiMapper.addPayDetail(optmap);
           // }else{
                //查询首付信息
               /* Map<String, Object> map = userAcctApiMapper.queryFirstAmt(loanNo);
                if (map == null || map.isEmpty()) {
                    throw new ServiceException("没有首付信息");
                }
                //获取首付金额
                tradeBalance=map.get("payAmt").toString();
                acctName=map.get("custName").toString();
                acctNo=map.get("cardNo").toString();
                //回传参数
                retMap.put("notify_url",PayCommand.firstPayNotifyUrl);
                Map<String, String> map1=new HashMap<>();
                map1.put("loanNo",loanNo);
                map1.put("acctName",acctName);
                map1.put("acctNo",acctNo);
                map1.put("tranChan",tranChan);
                JSONObject json = JSONObject.fromObject(map1);
                biz_back_params=json.toString();
                optSn=map11.get("optSn").toString();
                Map<String, String> reqParam=new HashMap<String, String>();
                reqParam.put("merchant_no",PayCommand.merchantNo);
                reqParam.put("opt_sn",optSn);
                reqParam.put("trade_balance",tradeBalance);
                //reqParam.put("trade_sn",tradeBalance);
                signDate=MessageUtil.addSignRSA(MessageUtil.geneFpayMsg(reqParam), SDKConstants.PRIVATE_KEY.value());*/
           // }
        }else{
            optmap.put("optType", "dkAmtPay");
            optmap.put("optNum", repayNum);
            int count=userAcctApiMapper.selectOverdueInst(loanNo);
            if(count>0){
                throw new ServiceException("存在逾期，不能扣款请联系客服");
            }
            if(repayNum==null || repayNum.isEmpty()){
                throw new ServiceException("还款期数为空");
            }
            //查询该期数是否有还款数据
            Map<String,Object> rePayMap=userAcctApiMapper.queryLoanNumDetails(loanNo,repayNum);
            if(rePayMap==null || rePayMap.isEmpty()){
                throw new ServiceException("没有该期还款数据");
            }
            //查询改期还款是否已经在扣款中或者已结清
            Map<String,Object> rePayMap1=userAcctApiMapper.queryIsPay(loanNo,repayNum);
            if(rePayMap1==null || rePayMap1.isEmpty()){
                throw new ServiceException("该期已在扣款中或者已结清");
            }
            //还款金额
            String repayDate= null;
            //Map<String,Object> map11=userAcctApiMapper.queryIsOptSn(loanNo,repayNum);
            //是否已存在订单
            //if(map11==null || map11.isEmpty()){
                try {
                    tradeBalance=rePayMap1.get("rcvTotlAmt").toString();
                    repayDate = rePayMap.get("repayDate").toString();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new ServiceException("扣款数据异常");
                }
                Map<String,String> bankMap=userAcctApiMapper.queryBankInfo(loanNo);
                try {
                    acctName=bankMap.get("acctName").toString();
                    acctNo=bankMap.get("acctNo").toString();
                } catch (Exception e) {
                    acctName ="n/a";
                    acctNo="0000";
                    e.printStackTrace();
                }
                //回调参数
                retMap.put("notify_url",PayCommand.notifyUrl);
                retMap.put("notify_url", ParamUtils.getParam("notifyUrl"));
                Map<String, String> map1=new HashMap<>();
                map1.put("loanNo",loanNo);
                map1.put("repayNum",repayNum);
                map1.put("repayDate",repayDate);
                map1.put("acctName",acctName);
                map1.put("acctNo",acctNo);
                map1.put("tranChan",tranChan);
                JSONObject json = JSONObject.fromObject(map1);
                biz_back_params=json.toString();
                optSn=MessageUtil.createSN(planId, PayCommand.merchantNo);
                Map<String, String> reqParam=new HashMap<String, String>();
                reqParam.put("merchant_no",PayCommand.merchantNo);
                reqParam.put("opt_sn",optSn);
                reqParam.put("trade_balance",tradeBalance);
                //reqParam.put("trade_sn",tradeBalance);
                signDate=MessageUtil.addSignRSA(MessageUtil.geneFpayMsg(reqParam), SDKConstants.PRIVATE_KEY.value());

                //添加订单
                optmap.put("id", RandomUtil.getUUID());
                optmap.put("merchantNo", PayCommand.merchantNo);
                optmap.put("planId", planId);
                optmap.put("optSn", optSn);
                optmap.put("tradeBalance", tradeBalance);
                optmap.put("bizBackParams", biz_back_params);
                optmap.put("loanNo", loanNo);
                optmap.put("instDate", new Date());
                userAcctApiMapper.addPayDetail(optmap);
           // }else{
                //Object instTime=map11.get("instDate");
               /* tradeBalance=tradeBalance=rePayMap1.get("rcvTotlAmt").toString();
                repayDate = rePayMap.get("repayDate").toString();
                optSn=map11.get("optSn").toString();
                Map<String, String> reqParam=new HashMap<String, String>();
                reqParam.put("merchant_no",PayCommand.merchantNo);
                reqParam.put("opt_sn",optSn);
                reqParam.put("trade_balance",tradeBalance);
                //reqParam.put("trade_sn",tradeBalance);
                signDate=MessageUtil.addSignRSA(MessageUtil.geneFpayMsg(reqParam), SDKConstants.PRIVATE_KEY.value());

                //回调参数
                retMap.put("notify_url",PayCommand.NotifyUrl);
                Map<String, String> map1=new HashMap<>();
                map1.put("loanNo",loanNo);
                map1.put("repayNum",repayNum);
                map1.put("repayDate",repayDate);
                map1.put("acctName",acctName);
                map1.put("acctNo",acctNo);
                map1.put("tranChan",tranChan);
                JSONObject json = JSONObject.fromObject(map1);
                biz_back_params=json.toString();*/
           // }
        }
         //封装返回参数
        retMap.put("merchant_no",PayCommand.merchantNo);
        retMap.put("opt_sn",optSn);
        /*System.out.println("============"+optSn);
        System.out.println("============"+biz_back_params);*/
        retMap.put("trade_balance",tradeBalance);
        retMap.put("biz_back_params",biz_back_params);
        retMap.put("sign_data",signDate);
        retMap.put("fromSubmitUrl", ParamUtils.getParam("fromSubmitUrl"));
        return retMap;
    }

    @Override
    public String getBuyerId(String url) {
        if(url==null ||url.isEmpty()){
            throw new ParameterException("url为空");
        }
        String reqUrl="https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id=2016090701861722&scope=auth_base&redirect_uri=http%3a%2f%2f115.159.235.109%3a7208%2fqthd-pay-web-gateway%2fscanPay%2faliFixPayXEx%3fredi_url%3d";
        url=reqUrl+url;
        HttpGet httpGet=new HttpGet(url);
        return null;
    }

    @Override
    public String getOpenid(String url) {
        if(url==null ||url.isEmpty()){
            throw new ParameterException("url为空");
        }
        String reqUrl="http://crm.aishua.cn/memberPlus/getXyOpenId.c?reqUrl=";
        url=reqUrl+url;
        HttpGet httpGet=new HttpGet(url);
        return null;
    }


    public String getOptSnPayStat(Map<String,Object> map) {
        String reqUrl=ParamUtils.getParam("thrPayQueryUrl");
        if(reqUrl==null || reqUrl.isEmpty()){
            reqUrl="http://222.211.94.217:8180/UPG/query/queryorder/service.do";
        }
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("plan_id", map.get("planId").toString());
        paramMap.put("merchant_no", PayCommand.merchantNo);
        paramMap.put("opt_sn", map.get("optSn").toString());
        paramMap.put("trade_sn", "null");
        paramMap.put("trade_balance", "null");
        String signData = MessageUtil.signFPayMessageRSA(MessageUtil.geneFpayMsg(paramMap), SDKConstants.PRIVATE_KEY.value());
        String param1="merchant_no="+PayCommand.merchantNo+"&plan_id="+map.get("planId").toString()+"&opt_sn="+map.get("optSn").toString()+"&sign_data="+signData;
        String sr1= null;
        try {
            sr1 = HttpRequest.sendPost(reqUrl, param1);
            byte[] sr2 = sr1.getBytes();
            sr1=new String(sr2,"utf-8");
            //System.out.println(sr1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("网络异常，请稍候再试！");
        }
        JSONObject json = null;
        try {
            json = JSONObject.fromObject(sr1);
        } catch (Exception e) {
            throw new ServiceException("查询失败 请重试");
        }
        String retStr="";
        if(json.containsKey("errorMsg")){
            retStr="2";
        }
        else if(json.containsKey("pay_result")){
            retStr=json.get("pay_result").toString();
        }
        else{
            retStr="2";
        }
        return retStr;
    }
    @Override
    public String threePayInfo(Map<String, Object> map) {
        map.put("id", RandomUtil.getUUID());
        userAcctApiMapper.addPayDetail(map);
        return null;
    }
    /*订单评价*/
    @Override
    public String evaluate(Map<String, Object> map) {
        if(map==null || map.isEmpty()){
            throw new ParameterException("evaluateInfo 为空");
        }
        map.put("id",RandomUtil.getUUID());
        map.put("instDate",new Date());
        userAcctApiMapper.addEvaluateInfo(map);
        return null;
    }
    /*查询订单评价*/
    @Override
    public Map<String, Object> getEvaluate(String loanNo) {
        if(StringUtils.isBlank(loanNo)){
            throw new ParameterException("loanNo 为空");
        }
        return userAcctApiMapper.getEvaluateInfo(loanNo);
    }

    //判断，返回布尔值
    private boolean isPhoneNumber(String input){
        boolean flag = false;
        try{
            Pattern regex = Pattern.compile("^(((1([3-9])[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\\\d{8})|(0\\d{3}-\\d{7})$");
            Matcher matcher = regex.matcher(input);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }

	
	
    /**
     * 查询附件
     *
     * @return
     */
    @Override
    public List<OCLoanAttInDto> queryAttachmentv3(String loanNo,List<String> attType) {
    	
        Validator.init(loanNo,"贷款编号").required().end();
        
        if(attType == null ||attType.size() == 0){
        	throw new ParameterException("attType为空");
        }
        Map map=new HashMap();
        map.put("loanNo",loanNo);
        map.put("attType",attType);
        List<Map<String,Object>> list2= userAcctApiMapper.queryLoanAttInfo(map);
        List<LoanAttInDto> list=new ArrayList<>();
        for(int i=0;i<list2.size();i++){
            LoanAttInDto loanAttInDto=new LoanAttInDto();
            BeanToMapTool.transMap2Bean(list2.get(i),loanAttInDto);
            list.add(loanAttInDto);
        }
        List<OCLoanAttInDto> attInDtoResultlist = new ArrayList<OCLoanAttInDto>();
        for (LoanAttInDto attInDto : list) {
            /*attInDto.setAttTyp(attInDto.getAttTyp());
            attInDto.setAttFile(OssUtil.getPresignedUrl(attInDto.getAttNo()));
            attInDtoResultlist.add(attInDto);*/

            OCLoanAttInDto ocLoanAttInDto = new OCLoanAttInDto();
            BeanUtils.copyProperties(attInDto, ocLoanAttInDto);
            //OCOssAttach ocOsssAttach = nOssFileTransfer.getOCOsssAttach(ocLoanAttInDto.getAttNo());
            OCOssAttach ocOsssAttach = nOssFileTransfer.getOCOsssAttach(ocLoanAttInDto.getAttNo(),60);
            ocLoanAttInDto.setAttName(ocOsssAttach.getAttName());
            ocLoanAttInDto.setAttFile(ocOsssAttach.getAttFile());
            ocLoanAttInDto.setAttFileThumb(ocOsssAttach.getAttFileThumb());
            attInDtoResultlist.add(ocLoanAttInDto);
        }
        return attInDtoResultlist;
    }
    
    
}
