package com.nm.impl.mb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hs.app.applycash.dto.BdLoanData;
import com.hs.base.entity.UserProfile;
import com.hs.commons.constants.CommonConstant;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.loan.acct.entity.PubLoanProdCalc;
import com.hs.loan.acct.service.AcctFeeCalService;
import com.hs.loan.busi.dto.*;
import com.hs.loan.cust.api.*;
import com.hs.loan.cust.dto.*;
import com.hs.loan.market.api.BranchApi;
import com.hs.loan.market.dto.BranchDto;
import com.hs.loan.prod.api.ProdFeeApi;
import com.hs.loan.prod.dto.PubProdDto;
import com.hs.loan.prod.dto.PubProdFeeDto;
import com.hs.loan.sale.api.LoanAcctApi;
import com.hs.system.area.PubSysRegionalBelongService;
import com.hs.system.entity.SysRegionalBelong;
import com.hs.system.index.service.PubIndexService;
import com.hs.utils.BeanUtils;
import com.hs.utils.HttpsInvokerUtil;
import com.hs.utils.ParamUtils;
import com.hs.utils.RandomUtil;
import com.nm.api.frame.auth.mapper.AppCustInfoMapper;
import com.nm.api.frame.auth.model.AppCustInfo;
import com.nm.base.framework.core.exception.ParameterException;
import com.nm.base.framework.core.exception.ServiceException;
import com.nm.base.framework.core.validate.Validator;
import com.nm.cmd.*;
import com.nm.mapper.login.SysCodInfoApiMapper;
import com.nm.mapper.mb.DmMapper;
import com.nm.mapper.usercenter.UserAcctApiMapper;
import com.nm.model.BigDataHFivemodel;
import com.nm.model.CustOtherContctInfoModel;
import com.nm.model.InstNumInfoModel;
import com.nm.mybatis.mapper.entity.Example;
import com.nm.service.mb.MbApiService;
import com.nm.service.usercenter.BigDataApiService;
import com.nm.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hanfei
 * @describe 医美serviceImpl
 */
@Service
public class MbApiServiceImpl implements MbApiService {

    @Autowired
    private BranchApi branchServer;
    @Autowired
    private DmMapper dmMapper;
    @Resource
    private ProdFeeApi prodFeeServer;
    /*    @Autowired
        private ProdApi prodServer;*/
    @Autowired
    private AcctFeeCalService acctFeeCalService;
    @Resource
    private CustInfoApi custinfoService;
    @Resource
    private CustLiveInfoApi custliveinfoService;
    @Resource
    public LoanAcctApi loanAcctservice;
    @Resource
    private CustBankAcctApi custBankAcctservice;
    @Resource
    private CustContctInfoApi custContctInfoServer;
    @Autowired
    private AppCustInfoMapper appCustInfoMapper;
    @Resource
    private CustStudyApi custStudyservice;
    @Resource
    private CustWorkApi custworkservice;
    @Resource
    private CustContctOtherApi custContctOtherservice;
    @Resource
    private PubSysRegionalBelongService pubSysRegionalBelongService;
    @Resource
    public PubIndexService pubIndexService;
    @Resource
    private SysCodInfoApiMapper sysCodInfoApiMapper;
    @Autowired
    private UserAcctApiMapper userAcctApiMapper;
    @Autowired
    private BigDataApiService bigDataApiService;
    private static final Logger log = LoggerFactory.getLogger(MbApiServiceImpl.class);

    /*查询网点和产品信息*/
    @Override
    public Map<String, Object> getOrgAndProInfosByOrgNo(String staffNo, String branchNo, String soucre, String custType) {
        Validator.init(staffNo, "销售编号").required().end();
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> retList = new ArrayList<>();
        if (StringUtils.isBlank(branchNo)) throw new ParameterException("请输入医院编码/扫描医院二维码");
        if (StringUtils.isBlank(custType)) throw new ServiceException("custType is empty");
        if (StringUtils.isNotBlank(soucre) && soucre.equals("input")) {
            //如果手动输入网点编号进入
            branchNo = branchNo;
        } else if (StringUtils.isNotBlank(soucre) && soucre.equals("qrcode")) {
            //如果扫描二维码进入,则进行解密
            try {
                branchNo = AESUtil.decrypt(branchNo);
                JSONObject jsonObject = JSON.parseObject(branchNo);
                branchNo = jsonObject.getString("branchNo");
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException("请扫描正确的二维码");
            }
        } else {
            throw new ServiceException("请重新扫描二维码");
        }
        //查询网点信息
        BranchDto branchDto = branchServer.getByNo(branchNo);
        if (branchDto == null) throw new ServiceException("请输入正确编码/扫描正确医院二维码");
        //根据branchNo查询产品信息
        List<PubProdDto> pubProdDtoList = new ArrayList<PubProdDto>();
        map.put("branchNo", branchDto.getBranchNo());
        map.put("goodsType", "20200004");
        map.put("saleChan", CommonConstant.SALECHANL_ONLINE);
        map.put("prodTyp", "20104001");
        if (custType.equals("40101003")) {
            custType = "40101001";
        }
        map.put("custType", custType);


        Validator.init(staffNo, "销售编号").required().end();
        Map<String,Object> mapsaler=dmMapper.getStaffNo(staffNo);
        if(mapsaler==null || mapsaler.size()<1) throw  new ServiceException("请核对销售编号和网点编码");
        /*map.put("orgNo", mapsaler.get("orgno").toString());//写死的orgNo和销售
        map.put("staffNo", staffNo);*/

        map.put("orgNo", "33000001");//写死的orgNo和销售
        map.put("staffNo", "dm_saler");
        pubProdDtoList = this.queryProdForLoan(map);
        //pubProdDtoList = prodServer.queryProdForLoan(map);//查询产品
        if (pubProdDtoList.size() < 1) throw new ServiceException("该医院无产品请联系运营");
        for (PubProdDto pubProdDto : pubProdDtoList) {
            Map<String, Object> newMap = new HashMap<>();
            Map<String, Object> feemap = new HashMap<>();
            feemap.put("prodNo", pubProdDto.getProdNo());
            feemap.put("isSel", CommonConstant.COMMON_YES);
            List<Map<String, Object>> feeList = new ArrayList<>();
            List<PubProdFeeDto> pubProdFeeList = prodFeeServer.queryProdFee(feemap);//查询费用项
            //循环取费用项
            for (PubProdFeeDto pubProdFeeDto : pubProdFeeList) {
                Map<String, Object> feeMap = new HashMap<>();
                feeMap.put("feeName", pubProdFeeDto.getFeeName());
                feeMap.put("feeNo", pubProdFeeDto.getFeeNo());
                //feeMap.put("instNum", pubProdFeeDto.getInstNum());
                feeList.add(feeMap);
            }
            HashSet h = new HashSet(feeList);
            feeList.clear();
            feeList.addAll(h);
            //根据prodNo查询产品期数
            List<InstNumInfoModel> instNumInfoList = queryProdFeeinStNums(pubProdDto.getProdNo());
            newMap.put("pubProDto", pubProdDto);
            newMap.put("feeList", feeList);
            newMap.put("instNumList", instNumInfoList);
            retList.add(newMap);
        }
        map.clear();
        map.put("proAndFeeAndInstList", retList);
        map.put("branchName", branchDto.getBranchName());
        map.put("branchNo", branchDto.getBranchNo());
        return map;
    }

    /*分期试算*/
    @Override
    public List<LoanProdCalcDto> getLoanTrial(String prodNo, BigDecimal loanAmt, int instNum, String othFees) {
        if (StringUtils.isBlank(prodNo)) throw new ParameterException("prodNo is empty");
        if (loanAmt == null || loanAmt.compareTo(new BigDecimal("0.00")) == 0)  //BigDecimal  用compareTo比较时 只比较数值大小 不比较精度
            throw new ParameterException("loanAmt is empty");
        if (instNum < 1) throw new ParameterException("instNum is empty");
        //if (StringUtils.isBlank(othFees)) throw new ParameterException("othFees is empty");
        //试算 othFees   2001|2061   多个费用项用|隔开
        List<PubLoanProdCalc> lists = acctFeeCalService.loanCalc(prodNo, loanAmt, instNum, othFees);
        List<LoanProdCalcDto> list = BeanUtils.copyProperties(lists, LoanProdCalcDto.class);
        List<LoanProdCalcDto> LoanProdCalclist = new ArrayList<LoanProdCalcDto>();
        if (list != null && list.size() > 0) {
            Map<Integer, LoanProdCalcDto> loanProdCalcmap = new HashMap<>();
            //返回固定期数试算信息
            for (LoanProdCalcDto loanProdCalcDto : list) {
                loanProdCalcmap.put(instNum, loanProdCalcDto);
            }
            LoanProdCalclist.addAll(loanProdCalcmap.values());
        }
        return LoanProdCalclist;
    }

    /**
     * 添加客户基本信息
     *
     * @param
     * @return
     */
    @Override
    public Map<String, Object> addCustBaseInfo(AddCustBaseCmd addCustBaseCmd, String certNo) {
        String staffNo = addCustBaseCmd.getStaffNo();
        Validator.init(staffNo, "销售编号").required().end();
        Map<String, Object> mapsaler = dmMapper.getStaffNo(staffNo);
        if (mapsaler == null || mapsaler.size() < 1) throw new ServiceException("请核对销售编号和网点编码");
        //添加基本信息时 将身份证号码写入appuerinfo表中
        if (StringUtils.isBlank(certNo)) {
            //保存appuserinfo
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("phone", addCustBaseCmd.getCustinfocmd().getPhoneNo());
            userInfo.put("certNo", addCustBaseCmd.getCustinfocmd().getCertNo());
            Integer type = dmMapper.updateAppUserInfo(userInfo);
            if (type < 1) throw new ServiceException("保存基本信息失败");
        } else {
            Validator.init(addCustBaseCmd.getCustinfocmd().getCertNo(), "身份证号码").required().end();
            if (!certNo.equalsIgnoreCase(addCustBaseCmd.getCustinfocmd().getCertNo())) {
                throw new ParameterException("身份证号码不一致");
            }
        }
        //保存客户基本信息
        String custNo = this.addCustInfo(addCustBaseCmd.getCustinfocmd(), addCustBaseCmd.getCustinfocmd().getCustNo());
        //保存居住信息
        this.addCustLiveInfo(addCustBaseCmd.getCustliveinfocmd(), custNo);
        //保存联系人信息
        this.addCustselfContctInfo(addCustBaseCmd.getCustContctCmd(), custNo);
        //保存贷款信息
        addCustBaseCmd.getLoanacctincmd().setCustNo(custNo);
        addCustBaseCmd.getLoanacctincmd().setCustName(addCustBaseCmd.getCustinfocmd().getCustName());
        String loanNo = addCustBaseCmd.getLoanacctincmd().getLoanNo();
        //String saleSource = addCustBaseCmd.getSaleSource();
        //保存销售渠道
        addCustBaseCmd.getLoanacctincmd().setSaleChanl("50205008");
        UserProfile userprofile = new UserProfile();
        if (com.hs.utils.StringUtils.isBlank(loanNo)) {
            userprofile.setOrgNo(mapsaler.get("orgno").toString());
            userprofile.setStaffNo(mapsaler.get("staffno").toString());
            userprofile.setStaffName(mapsaler.get("staffname").toString());
            userprofile.setOrgName(mapsaler.get("orgname").toString());
        } else {
            LoanSalerDto loansalerdto = loanAcctservice.queryLoanSaler(loanNo);
            userprofile.setOrgNo(loansalerdto.getOrgNo());
            userprofile.setStaffNo(loansalerdto.getStaffNo());
            userprofile.setStaffName(loansalerdto.getStaffName());
        }
        loanNo = addLoanAcct(addCustBaseCmd.getLoanacctincmd(), userprofile);
        //保存客户银行卡信息
        addCustBaseCmd.getCustBankAcctCmd().setCustNo(custNo);
        addCustBaseCmd.getCustBankAcctCmd().setLoanNo(loanNo);
        this.addCustBankAcctInfo(addCustBaseCmd.getCustBankAcctCmd());
        Map<String, Object> map = new HashMap<>();
        map.put("custNo", custNo);
        map.put("loanNo", loanNo);
        return map;
    }

    /*获取客户基本信息、根据certNo*/
    @Override
    public List<AppCustInfo> getAppCustInfo(String certNo) {
        Example exampleAppCustInfo = new Example(AppCustInfo.class);
        exampleAppCustInfo.createCriteria().andEqualTo("certNo", certNo);
        return this.appCustInfoMapper.selectByExample(exampleAppCustInfo);
    }

    /**
     * 新增客户学校信息
     *
     * @param custStudyInfoCmd
     */
    @Override
    public void addCustStudyInfo(CustStudyInfoCmd custStudyInfoCmd) {
        Validator.init(custStudyInfoCmd, "学校信息").required().end()
                .get("schoolName", "学校名称").required().end()
                .get("schoolProv", "学校所在省").required().end()
                .get("schoolCity", "学校所在城市").required().end()
                .get("schoolArea", "学校所在县区").required().end()
                .get("schoolAddr", "详细地址").required().end()
                .get("custNo", "客户编号").required().end();
        CustStudyDto custstudydto = new CustStudyDto();
        custstudydto = (CustStudyDto) BeanUtils.copyPropertiesNotNull(new CustStudyDto(), custStudyInfoCmd);
        custstudydto.setBeginDate(new Date());
        custStudyservice.save(custStudyInfoCmd.getCustNo(), custstudydto);
    }

    /**
     * 新增客户工作信息
     *
     * @param custworkinfocmd
     */
    @Transactional
    public void addCustWorkInfo(CustWorkInfoCmd custworkinfocmd) {
        Validator.init(custworkinfocmd, "客户工作信息").required().end()
                .get("workUnit", "单位名称").required().isMaxLength(64).end()
                .get("industry", "所属行业").required().end()
                .get("workJob", "职务").required().end()
                .get("unitProv", "工作所在省").required().isMaxLength(32).end()
                .get("unitCity", "工作所在市").required().isMaxLength(128).end()
                .get("unitAddr", "工作单位详细地址").required().isMaxLength(200).end()
                .get("custNo", "客户编号").required().end()
                .get("workTime", "工作时长").required().isNumber().end();
        CustWorkDto custWorkDto = new CustWorkDto();
        custWorkDto = (CustWorkDto) BeanUtils.copyPropertiesNotNull(new CustWorkDto(), custworkinfocmd);
        custWorkDto.setBeginDate(new Date());
        custworkservice.save(custworkinfocmd.getCustNo(), custWorkDto);
    }

    /* 新增客户其它联系人信息*/
    @Transactional
    public List<CustOtherContctInfoModel> addCustContctInfo(List<CustOtherContctInfoCmd> custOtherContctInfoCmdList) {
        //addCustselfContctInfo(custcontctcmd,custcontctcmd.getCustNo());
        if (custOtherContctInfoCmdList.size() < 1) throw new ParameterException("请输入联系人信息");
        HashSet phoneSet = new HashSet();
        for (CustOtherContctInfoCmd custOtherContctInfoCmd : custOtherContctInfoCmdList) {
            Validator.init(custOtherContctInfoCmd, "联系人信息").required().end()
                    .get("contactName", "联系人姓名").required().end()
                    .get("contactTel", "联系人电话").required().end();

            phoneSet.add(custOtherContctInfoCmd.getContactTel());

        }
        if (phoneSet.size() != custOtherContctInfoCmdList.size()) {
            throw new ParameterException("任意2个号码不能相同");
        }
        List<CustOtherContctInfoModel> lists = new ArrayList<>();
        addCustOtherContctInfo(custOtherContctInfoCmdList);
        List<CustOtherContctInfoModel> custOtherContctInfoModel = queryCustContctInfo(custOtherContctInfoCmdList.get(0).getCustNo());
        for (CustOtherContctInfoModel custOtherContctInfoModel1 : custOtherContctInfoModel) {
            custOtherContctInfoModel1.setContactRelName(sysCodInfoApiMapper.getCodeNameByTypeAndNum("relationType", custOtherContctInfoModel1.getContactRel()));
            lists.add(custOtherContctInfoModel1);
        }
        return lists;
    }

    /**
     * 提交分期信息
     */
    @Transactional
    public void addLoanFileNoInfo(String loanNo, AppCustInfo appcustinfo, String applyAddr, String custNo) {
        UserProfile userProfile = new UserProfile();
        Validator.init(loanNo, "贷款编号").required().end();
        Validator.init(custNo, "客户编号").required().end();
        LoanSalerDto loansalerdto = loanAcctservice.queryLoanSaler(loanNo);
        userProfile.setOrgNo(loansalerdto.getOrgNo());
        userProfile.setStaffNo(loansalerdto.getStaffNo());
        userProfile.setStaffName(loansalerdto.getStaffName());
        LoanAcctOutDto loanacctoutdto = loanAcctservice.getLoanAcct(loanNo, userProfile);
        String custType = dmMapper.getCustType(appcustinfo.getCustNo());
        //40101001 成人  40101002 学生 有工作大于20000必须上传财力证明； 无工作财力证明必须； 学生大于10000财力必须上传
        if (custType.equals("40101001")) {
            BigDecimal pric = loanacctoutdto.getLoanAmt().add(loanacctoutdto.getFstPayAmt());
            BigDecimal str2 = new BigDecimal(ParamUtils.getParam("cLpick2"));
            if (pric.compareTo(str2) == 1 || pric.compareTo(str2) == 0) {
                Map map = new HashMap();
                List<String> attType = new ArrayList<>();
                int filekey=40105021;
                attType.add("40105021");
                //attType.add("40105040");
                for(int i=0 ; i<19;i++){
                    filekey=filekey+1;
                    attType.add(String.valueOf(filekey));
                }
                map.put("loanNo", loanNo);
                map.put("attType", attType);
                List<Map<String, Object>> list2 = userAcctApiMapper.queryLoanAttInfo(map);
                if (list2.size() < 1) {
                    throw new ServiceException("请上传财力证明");
                }
            }
        } else if (custType.equals("40101002")) {
            //
            BigDecimal pric = loanacctoutdto.getLoanAmt().add(loanacctoutdto.getFstPayAmt());
            BigDecimal str1 = new BigDecimal(ParamUtils.getParam("cLpick1"));
            if (pric.compareTo(str1) == 1 || pric.compareTo(str1) == 0) {
                Map map = new HashMap();
                List<String> attType = new ArrayList<>();
                int filekey=40105001;
                attType.add("40105001");
                //attType.add("40105020");
                for(int i=0 ; i<19;i++){
                    filekey=filekey+1;
                    attType.add(String.valueOf(filekey));
                }
                map.put("loanNo", loanNo);
                map.put("attType", attType);
                List<Map<String, Object>> list2 = userAcctApiMapper.queryLoanAttInfo(map);
                if (list2.size() < 1) {
                    throw new ServiceException("请上传财力证明");
                }
            }

        } else {
            Map map = new HashMap();
            List<String> attType = new ArrayList<>();
            int filekey=40105041;
            attType.add("40105041");
            //attType.add("40105060");
            for(int i=0 ; i<19;i++){
                filekey=filekey+1;
                attType.add(String.valueOf(filekey));
            }
            map.put("loanNo", loanNo);
            map.put("attType", attType);
            map.put("loanNo", loanNo);
            map.put("attType", attType);
            List<Map<String, Object>> list2 = userAcctApiMapper.queryLoanAttInfo(map);
            if (list2.size() < 1) {
                throw new ServiceException("请上传财力证明");
            }
        }
        UserReportInfo userReportInfo = new UserReportInfo();
        userReportInfo.setCustNo(appcustinfo.getCustNo());
        userReportInfo.setFileNo("3");
        userReportInfo.setLoanNo(loanNo);
        String stat = loanacctoutdto.getStat();
        String fileNo = userReportInfo.getFileNo();
        custNo = userReportInfo.getCustNo();
        String loanRemark = userReportInfo.getLoanRemark();
        try {
            loanAcctservice.sumitLoan(loanNo, fileNo, loanRemark, userProfile);
        } catch (Exception e) {
            log.error("提交申请失败", e);
            throw new ServiceException(e.getMessage());
        } finally {
            log.info(loanNo + "# ****************** sumitLoan提交申请接口开始 ****************** #");
            log.info("# loanNo > " + loanNo);
            log.info("# custNo > " + custNo);
            log.info("# userProfile > ", JSONObject.toJSONString(userProfile));
            log.info("# userreportinfo > ", JSONObject.toJSONString(userReportInfo));
            log.info(loanNo + "# ****************** sumitLoan提交申请接口结束 ****************** #");
        }


        //保存定位信息
        if (StringUtils.isNotBlank(applyAddr)) {

            Map<String, Object> map = dmMapper.getApplyAddr(custNo);

            if (map != null && map.size() > 1) {
                dmMapper.updateApplyAddr(map.get("id").toString(), applyAddr);
            } else {
                dmMapper.insertApplyAddr(RandomUtil.getUUID(), custNo, applyAddr);
            }
        }

        //再次抓取芝麻分

        BdLoanData bdLoanData = this.createBdLoanData(fileNo, loanNo, custNo, stat, userProfile);
        String url = ParamUtils.getParam("submitApplicationData");
        //this.bankCardCheck(loanNo, custNo);
        List<org.apache.http.NameValuePair> parameters = new ArrayList<org.apache.http.NameValuePair>();
        parameters.add(new BasicNameValuePair("loanDataJson", JSON.toJSONString(bdLoanData)));
        parameters.add(new BasicNameValuePair("ruleId", ParamUtils.getParam("dmRuleId")));
        parameters.add(new BasicNameValuePair("scoreId", ParamUtils.getParam("dmScoreId")));
        String results = null;
        try {
            results = HttpsInvokerUtil.executeHttpPost(url, parameters);

        } catch (Exception e) {
            //解决大数据抛出异常，但是订单状态已经改变,就不抛出异常信息
            log.error(MessageFormat.format(loanNo + "大数据上传申请信息失败({0})", loanNo), e);
            JSONObject parseObject = JSON.parseObject(results);
            String errorCode = "";
            String errorMsg = "";
            if (parseObject.containsKey("errorCode") && !parseObject.get("errorCode").toString().equals("200")) {
                throw new ServiceException(parseObject.get("errorMsg").toString());
            }
            //throw new ServiceException("大数据上传申请信息失败(" + loanNo + ")" , e);
        } finally {
            log.info(loanNo + "# ****************** 调用A5接口开始 ****************** #");

            if (bdLoanData == null) {
                log.error(loanNo + "# bdLoanData > 大数据拼装数据失败");
            } else {
                log.info(loanNo + "# bdLoanData > " + JSON.toJSONString(bdLoanData));
            }

            if (com.hs.utils.StringUtils.isNotBlank(results)) {
                log.info(loanNo + "# result >" + results);
            } else log.info(loanNo + "# result > 返回值为空或调用失败");
            log.info(loanNo + "# ****************** 调用A5接口结束 ****************** #");
        }
        //验证芝麻分
        try {
            BigDataHFiveCmd bigDataHFiveCmd=new BigDataHFiveCmd();
            bigDataHFiveCmd.setLoanNo(loanNo);
            bigDataHFiveCmd.setPhoneNo(bdLoanData.getMobliePhone());
            bigDataHFiveCmd.setCertNo(bdLoanData.getIdCard());
            bigDataHFiveCmd.setRedirectURL("www.callback.com");
            bigDataHFiveCmd.setCustName(bdLoanData.getName());
            bigDataHFiveCmd.setCustNo(custNo);
            this.againZhiMaAuth(bigDataHFiveCmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //保存客户基本信息
    private String addCustInfo(CustInfoCmd custinfocmd, String custNo) {
        Validator.init(custinfocmd, "客户基本信息").required().end()
                .get("custName", "客户姓名").required().isMaxLength(20).end()
                .get("certNo", "身份证").isidcard().end()
                .get("custType", "客户类型").required().end()
                .get("phoneNo", "手机号").required().isNumber().rangeLength(11, 11).end()
                //.get("regProv", "户籍所在省").required().end()
                //.get("regCity", "户籍地址所在市").required().end()
                //.get("regArea", "户籍区县").required().end()
                .get("certValidDate", "证件有效期").required().end()
                .get("certIssuOrg", "签发机关").required().end()
                .get("educ", "学历").required().end()
                .get("regAddr", "户籍详细地址").required().isMaxLength(200).end()
                .get("ethnic", "民族").required().end()
                .get("marriage", "婚姻状况").required().end();

        String sex = custinfocmd.getCertNo().substring(16, 17);
        if (Integer.parseInt(sex) % 2 == 0) {
            sex = "40102102";
        } else {
            sex = "40102101";
        }
        Map<String, Object> param = new HashMap<>();
        param.put("areaNo", custinfocmd.getCertNo().substring(0, 6));
        List<SysRegionalBelong> sysRegionalBelongs = null;
        try {
            sysRegionalBelongs = pubSysRegionalBelongService.queryForList(param);
        } catch (Exception e) {
            e.printStackTrace();
            sysRegionalBelongs = null;
        }
        if (sysRegionalBelongs != null && sysRegionalBelongs.size() == 1) {
            SysRegionalBelong sysRegionalBelong = sysRegionalBelongs.get(0);
            custinfocmd.setRegProv(sysRegionalBelong.getProvNo());
            custinfocmd.setRegCity(sysRegionalBelong.getCityNo());
            custinfocmd.setRegArea(sysRegionalBelong.getAreaNo());
        } else {
            log.info("============身份证计算省市县失败============》" + custinfocmd.getCustName());
            log.info("============身份证号码============》" + custinfocmd.getCertNo());
            log.info("============客户电话号码============》" + custinfocmd.getPhoneNo());
            custinfocmd.setRegProv("510000");
            custinfocmd.setRegCity("519900");
            custinfocmd.setRegArea("519999");
        }
        String regProvName=pubSysRegionalBelongService.getProvName(custinfocmd.getRegProv());
        String regCityName=pubSysRegionalBelongService.getCityName(custinfocmd.getRegCity());
        String regAreaName=pubSysRegionalBelongService.getCountName(custinfocmd.getRegArea());
        String regAddrName=custinfocmd.getRegAddr();
        if(com.hs.utils.StringUtils.isNotBlank(regProvName) && regAddrName.contains(regProvName)){
            regAddrName=regAddrName.replace(regProvName,"");
        }
        if(com.hs.utils.StringUtils.isNotBlank(regCityName) && regAddrName.contains(regCityName)){
            regAddrName=regAddrName.replace(regCityName,"");
        }
        if(com.hs.utils.StringUtils.isNotBlank(regAreaName) && regAddrName.contains(regAreaName)){
            regAddrName=regAddrName.replace(regAreaName,"");
        }
        custinfocmd.setRegAddr(regAddrName);
        custinfocmd.setSex(sex);
        custinfocmd.setCertType("40102301");
        custinfocmd.setCustNo(custNo);
        custinfocmd.setCertValidDate(custinfocmd.getCertValidDate().replace("-", ""));
        CustInfoDto custinfodto = (CustInfoDto) BeanUtils.copyPropertiesNotNull(new CustInfoDto(), custinfocmd);
        String custNos = custinfoService.save(custinfodto);
        return custNos;
    }

    /**
     * 根据贷款编号获取客户基本信息
     *
     * @param loanNo
     * @return
     */
    public AppCustInfo getAppCustInfoByLoanNo(String loanNo) {
        AppCustInfo appCustInfo = new AppCustInfo();
        Map<String, Object> map = dmMapper.getAppCustInfoByLoanNo(loanNo);
        BeanToMapTool.transMap2Bean(map, appCustInfo);
        return appCustInfo;
    }

    @Override
    public LoanAcctInDto getLoanNoByCustNo(String custNo) {
        return dmMapper.getLoanNoByCustNo(custNo);
    }

    @Override
    public String getCertNoByMobileNo(String mobileNo) {
        String certNo="";
        certNo=dmMapper.getCertNoByMobileNo(mobileNo);
        if(com.hs.utils.StringUtils.isBlank(certNo)){
            certNo="";
        }
        return certNo;
    }

    /**
     * 新增客户居住信息
     *
     * @param custliveinfocmd
     * @param custNo
     */
    @Transactional
    public void addCustLiveInfo(CustLiveInfoCmd custliveinfocmd, String custNo) {
        Validator.init(custliveinfocmd, "客户居住信息").required().end()
                .get("liveProv", "居住所在省").required().end()
                .get("liveCity", "居住地址所在市").required().end()
                .get("liveArea", "居住所在区县").required().end()
                .get("liveAddr", "居住详细地址").required().isMaxLength(200).end();
        custliveinfocmd.setCustNo(custNo);
        custliveinfocmd.setBeginDate(new Date());
        CustLiveInfoDto[] custliveinfodto = new CustLiveInfoDto[1];
        custliveinfodto[0] = (CustLiveInfoDto) BeanUtils.copyPropertiesNotNull(new CustLiveInfoDto(), custliveinfocmd);
        custliveinfoService.save(custNo, custliveinfodto);
    }

    /**
     * 贷款基本信息
     *
     * @param loanAcctInCmd
     * @param userprofile
     * @return
     */
    private String addLoanAcct(LoanAcctInCmd loanAcctInCmd, UserProfile userprofile) {
        Validator.init(loanAcctInCmd, "贷款信息").required().end();
        loanAcctInCmd.getGoodDto().setGoodsType("20200004");
        LoanAcctInDto loanacctindto = (LoanAcctInDto) BeanUtils.copyPropertiesNotNull(new LoanAcctInDto(), loanAcctInCmd);
        String loanNo = loanAcctservice.addLoanAcct(loanacctindto, userprofile);
        return loanNo;
    }

    /**
     * 新增客户银行卡信息
     *
     * @param custbankacctcmd
     * @param
     */
    @Transactional
    public void addCustBankAcctInfo(CustBankAcctCmd custbankacctcmd) {
        Validator.init(custbankacctcmd, "银行信息").required().end();
        CustBankAcctDto custBankAcctDto = new CustBankAcctDto();
        if (com.hs.utils.StringUtils.isBlank(custbankacctcmd.getOpenCity())) {
            String prov = custbankacctcmd.getOpenCity();
            prov = com.hs.utils.StringUtils.isBlank(prov) && prov.length() < 2 ? "51" : prov.substring(0, 2);
            custbankacctcmd.setOpenCity(prov + "9900");
        }
        custBankAcctDto = (CustBankAcctDto) BeanUtils.copyPropertiesNotNull(new CustBankAcctDto(), custbankacctcmd);
        custBankAcctservice.saveCustLoanBank(custBankAcctDto, custbankacctcmd.getLoanNo());
    }

    /**
     * 新增客户联系信息
     */
    @Transactional
    public void addCustselfContctInfo(CustContctCmd custcontctcmd, String custNo) {
        Validator.init(custcontctcmd, "客户联系信息").required().end()
                .get("qq", "QQ").isNumber().isMaxLength(15).end();
        custcontctcmd.setCustNo(custNo);
        /*custcontctcmd.setWechat("N/A");*/
        CustContctInfoDto custContctInfoDto = new CustContctInfoDto();
        custContctInfoDto = (CustContctInfoDto) BeanUtils.copyPropertiesNotNull(new CustContctInfoDto(), custcontctcmd);
        custContctInfoDto.setBeginDate(new Date());
        Date time = null;
        try {
            time = DateTool.convertStringToDate("yyyy-MM-dd HH:mm:ss", "9999-12-31 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        custContctInfoDto.setEndDate(CommonConstant.MAX_DATE);
        custContctInfoServer.save(custNo, custContctInfoDto);
    }


    /**
     * 新增客户其它联系人信息
     */
    @Transactional
    public void addCustOtherContctInfo(List<CustOtherContctInfoCmd> custOtherContctInfoList) {
        List<CustContctOtherDto> custcontctotherdtoList = new ArrayList<CustContctOtherDto>();
        String custNo = "";
        for (CustOtherContctInfoCmd dto : custOtherContctInfoList) {
            Validator.init(dto, "客户其它联系人信息").required().end()
                    .get("contactName", "其他联系人姓名").required().isMaxLength(16).end()
                    .get("contactRel", "关系").required().isNumber().isMaxLength(32).end()
                    .get("contactTel", "电话号码").required().end()
                    .get("custNo", "客户编号").required().end();

            CustContctOtherDto custcontctotherdto = (CustContctOtherDto) BeanUtils.copyPropertiesNotNull(new CustContctOtherDto(), dto);
            //处理电话号码中存在的非数字和86开头
            String phonenum=custcontctotherdto.getContactTel().trim();
            String regEx = "[^0-9]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(phonenum);
            phonenum=m.replaceAll("").trim();
            if(phonenum.startsWith("86")){
                phonenum=phonenum.substring(2);
            }

            custcontctotherdto.setBeginDate(new Date());
            custcontctotherdto.setContactTel(phonenum);
            custcontctotherdto.setContactName(EmojiHandle.resolveToByteFromEmoji(custcontctotherdto.getContactName()));
            custcontctotherdtoList.add(custcontctotherdto);
            custNo = dto.getCustNo();
        }
        CustContctOtherDto[] CustContctOtherDtoArr = new CustContctOtherDto[]{};
        CustContctOtherDtoArr = custcontctotherdtoList.toArray(CustContctOtherDtoArr);
        custContctOtherservice.save(custNo, CustContctOtherDtoArr);
    }


    /**
     * 查询客户其它联系信息
     *
     * @param custNo
     * @return
     */
    private List<CustOtherContctInfoModel> queryCustContctInfo(String custNo) {
        List<CustContctOtherDto> custcontctotherdtoList = custContctOtherservice.getCrtContctOtherLst(custNo);
        List<CustOtherContctInfoModel> custOtherContctInfoList = BeanUtils.copyProperties(custcontctotherdtoList, CustOtherContctInfoModel.class);
        return custOtherContctInfoList;
    }

    //查询期数
    private List<InstNumInfoModel> queryProdFeeinStNums(String prodNo) {
        List<InstNumInfoModel> instNumInfoList = new ArrayList<InstNumInfoModel>();
        instNumInfoList = dmMapper.queryProdFeeinStNums(prodNo);
        return instNumInfoList;
    }

    private BdLoanData createBdLoanData(String fileNo, String loanNo, String custNo, String stat, UserProfile userProfile) {
        /**驳回时更新orderid*/
        String orderId = null;
        /**驳回时更新orderid*/
        if (PubBusinessConstant.LOANSTAT_REJECTED.equals(stat) || "30201092".equals(stat)) {
            orderId = pubIndexService.updateDmOrderId(loanNo);
        } else {
            orderId = pubIndexService.getDmOrderId(loanNo);
        }
        if (com.hs.utils.StringUtils.isBlank(orderId)) {
            orderId = RandomUtil.getUUID();
            pubIndexService.saveDmOrderInfo(orderId, loanNo);
        }
        //分期试算
        LoanAcctOutDto loanTrial = loanAcctservice.getLoanAcct(loanNo, userProfile);
        //基本信息
        CustInfoDto custInfoDto = custinfoService.getByNo(custNo);
        //工作信息
        List<CustWorkDto> crtCustWorkList = custworkservice.getCrtCustWorkLst(custNo);
        CustWorkDto crtCustWork = crtCustWorkList.size() > 0 ? crtCustWorkList.get(0) : new CustWorkDto();
        //居住信息
        List<CustLiveInfoDto> custLiveList = custliveinfoService.getCrtCustLiveInfoLst(custNo);
        CustLiveInfoDto liveInfo = custLiveList.size() > 0 ? custLiveList.get(0) : new CustLiveInfoDto();
        //其他联系人
        List<CustContctOtherDto> custContctOtherDtoList = custContctOtherservice.getCrtContctOtherLst(custNo);
        //银行卡信息
        LoanBankAcctDto loanBankAcct = loanAcctservice.queryLoanBanKCard(loanNo);

        BdLoanData bd = new BdLoanData();
        //    	bd.setLoanNo(loanNo);
        bd.setOrderId(orderId);
        bd.setAppKey("lemon");
        bd.setIdCard(custInfoDto.getCertNo());

        bd.setDocumentNo(Integer.valueOf(fileNo) % 4);
        //是否保险 需要改 根据是否有其他费用项 有:1 没有:2
        List<LoanFeeDto> SelectFees = loanTrial.getSelectFees();
        List<String> feeNos = new ArrayList<String>();
        for (int i = 0; i < SelectFees.size(); i++) {
            feeNos.add(SelectFees.get(i).getFeeNo());
        }
        if (feeNos.size() > 0 && feeNos.contains("2001")) {
            bd.setIsInsurance(1);
        } else {
            bd.setIsInsurance(2);
        }

        bd.setName(custInfoDto.getCustName());
        bd.setMobliePhone(custInfoDto.getPhoneNo());
        bd.setBankCard(loanBankAcct.getAcctNo());
        if (PubBusinessConstant.SEX_FEMALE.endsWith(custInfoDto.getSex())) {
            bd.setSex(2);
        } else {
            bd.setSex(1);
        }

        //bd.setMarry(Integer.valueOf(custInfoDto.getMarriage() == "40102401" ? "2" : "1"));
        if (custInfoDto.getMarriage().equals("40102401")) {
            bd.setMarry(2);
        } else if (custInfoDto.getMarriage().equals("40102402")) {
            bd.setMarry(4);
        } else if (custInfoDto.getMarriage().equals("40102403")) {
            bd.setMarry(1);
        } else if (custInfoDto.getMarriage().equals("40102404")) {
            bd.setMarry(5);
        } else if (custInfoDto.getMarriage().equals("40102405")) {
            bd.setMarry(6);
        } else {
            bd.setMarry(3);
        }

        bd.setCustType(Integer.valueOf("40101001".equals(custInfoDto.getCustType()) ? "1" : "2"));
        bd.setCommodityType(loanTrial.getGoodsDto().get(0).getBrand());

        bd.setApplyDate(loanTrial.getApplyDate().getTime());

        bd.setLoanAmt(loanTrial.getLoanAmt().doubleValue());
        Double price = 0.0;
        for (int i = 0; i < loanTrial.getGoodsDto().size(); i++) {
            price += loanTrial.getGoodsDto().get(i).getPric().doubleValue();
        }
        bd.setPrice(price);
        bd.setNum(loanTrial.getInstNum());
        bd.setMonthPayAmt(loanTrial.getMthRepayAmt().doubleValue());
        bd.setHouseProvince(custInfoDto.getRegProvName());
        bd.setHouseCity(custInfoDto.getRegCityName());
        //户籍详细地址

        bd.setHouseholdRegisterAddress(pubSysRegionalBelongService.getProvName(custInfoDto.getRegProv())
                + pubSysRegionalBelongService.getCityName(custInfoDto.getRegCity())
                + pubSysRegionalBelongService.getCountName(custInfoDto.getRegArea())
                + custInfoDto.getRegAddr());

        bd.setLiveProvince(liveInfo.getLiveProvName());
        bd.setLiveCity(liveInfo.getLiveCityName());
        //居住详细地址

        bd.setLivingAddress(pubSysRegionalBelongService.getProvName(liveInfo.getLiveProv())
                + pubSysRegionalBelongService.getCityName(liveInfo.getLiveCity())
                + pubSysRegionalBelongService.getCountName(liveInfo.getLiveArea())
                + liveInfo.getLiveAddr());

        String buildType = liveInfo.getLiveBuildType();
        if (com.hs.utils.StringUtils.isEmpty(ParamUtils.getParam(buildType))) {
            bd.setResidentialProperty(0);
        } else {
            bd.setResidentialProperty(Integer.valueOf(ParamUtils.getParam(buildType)));
        }

        if (com.hs.utils.StringUtils.isEmpty(crtCustWork.getWorkTime())) {
            bd.setWorkTime(0);
        } else {
            bd.setWorkTime(Integer.valueOf(crtCustWork.getWorkTime()));
        }
        if (com.hs.utils.StringUtils.isEmpty(crtCustWork.getUnitType()) || com.hs.utils.StringUtils.isEmpty(ParamUtils.getParam(crtCustWork.getUnitType()))) {
            bd.setUnitNature(0);
        } else {
            bd.setUnitNature(Integer.valueOf(ParamUtils.getParam(crtCustWork.getUnitType())));
        }
        if (com.hs.utils.StringUtils.isEmpty(crtCustWork.getUnitScale()) || com.hs.utils.StringUtils.isEmpty(ParamUtils.getParam(crtCustWork.getUnitScale()))) {
            bd.setUnitScale(0);
        } else {
            bd.setUnitScale(Integer.valueOf(ParamUtils.getParam(crtCustWork.getUnitScale())));
        }

        bd.setUnitName(crtCustWork.getWorkUnit());

        bd.setUnitProvince(crtCustWork.getUnitProvName());
        bd.setUnitCity(crtCustWork.getUnitCityName());
        //工作详细地址

        bd.setWorkAddress(pubSysRegionalBelongService.getProvName(crtCustWork.getUnitProv())
                + pubSysRegionalBelongService.getCityName(crtCustWork.getUnitCity())
                + pubSysRegionalBelongService.getCountName(crtCustWork.getUnitArea())
                + crtCustWork.getUnitAddr());
        /*bd.setWorkPersonName(crtCustWork.getWorkUnit());
        bd.setWorkPersonPhoneNum(crtCustWork.getUnitTel());*/
        for (int i = custContctOtherDtoList.size(); i > 0; i--) {
            CustContctOtherDto contact = custContctOtherDtoList.get(i - 1);
            if ("40103101".equals(contact.getContactRel())) {//父亲
                bd.setFatherName(contact.getContactName());
                bd.setFatherPhoneNum(contact.getContactTel());
                custContctOtherDtoList.remove(i - 1);
            } else if ("40103102".equals(contact.getContactRel())) {//母亲
                bd.setMotherName(contact.getContactName());
                bd.setMotherPhoneNum(contact.getContactTel());
                custContctOtherDtoList.remove(i - 1);
            } else if ("40103103".equals(contact.getContactRel())) {//兄弟姐妹
                bd.setPerson1Name(contact.getContactName());
                bd.setPerson1Phone(contact.getContactTel());
                custContctOtherDtoList.remove(i - 1);
            } else if ("40103104".equals(contact.getContactRel())) {//配偶
                bd.setSpouseName(contact.getContactName());
                bd.setSpousePhoneNum(contact.getContactTel());
                custContctOtherDtoList.remove(i - 1);
            } else if ("40103105".equals(contact.getContactRel())) {//亲戚
                bd.setPerson2Name(contact.getContactName());
                bd.setPerson2Phone(contact.getContactTel());
                custContctOtherDtoList.remove(i - 1);
            } else if ("40103107".equals(contact.getContactRel())) {//朋友
                bd.setPerson3Name(contact.getContactName());
                bd.setPerson3Phone(contact.getContactTel());
                custContctOtherDtoList.remove(i - 1);
            } else if ("40103108".equals(contact.getContactRel())) {//同事
                bd.setColleagueName(contact.getContactName());
                bd.setColleaguePhoneNum(contact.getContactTel());
                custContctOtherDtoList.remove(i - 1);
            } else if ("40103109".equals(contact.getContactRel()) || "40103110".equals(contact.getContactRel())) {
                if ("40101001".equals(custInfoDto.getCustType())) {//单位联系人
                    bd.setWorkPersonName(contact.getContactName());
                    bd.setWorkPersonPhoneNum(contact.getContactTel());
                    custContctOtherDtoList.remove(i - 1);
                } else if ("40101002".equals(custInfoDto.getCustType())) {//辅导员
                    bd.setWorkPersonName(contact.getContactName());
                    bd.setWorkPersonPhoneNum(contact.getContactTel());
                    custContctOtherDtoList.remove(i - 1);
                }
            }
        }
        /*if (custContctOtherDtoList.size() > 0) {
            bd.setPerson1Name(custContctOtherDtoList.get(0).getContactName());
            bd.setPerson1Phone(custContctOtherDtoList.get(0).getContactTel());
            custContctOtherDtoList.remove(0);
        }
        if (custContctOtherDtoList.size() > 0) {
            bd.setPerson2Name(custContctOtherDtoList.get(0).getContactName());
            bd.setPerson2Phone(custContctOtherDtoList.get(0).getContactTel());
            custContctOtherDtoList.remove(0);
        }
        if (custContctOtherDtoList.size() > 0) {
            bd.setPerson3Name(custContctOtherDtoList.get(0).getContactName());
            bd.setPerson3Phone(custContctOtherDtoList.get(0).getContactTel());
            custContctOtherDtoList.remove(0);
        }*/
        return bd;
    }

    private List<PubProdDto> queryProdForLoan(Map<String, Object> map) {
        //销售渠道，线上，线下，APP
        if (null == map.get("saleChan") || "".equals(map.get("saleChan"))) {
            map.put("I_SALE_CHAN", CommonConstant.SALECHANL_XYD);
        } else {
            map.put("I_SALE_CHAN", map.get("saleChan"));
        }
        //产品类型 消费贷，现金贷
        if (null == map.get("prodTyp") || "".equals(map.get("prodTyp"))) {
            map.put("I_PROD_TYP", CommonConstant.PRODTYP_XFD);
        } else {
            map.put("I_PROD_TYP", map.get("prodTyp"));
        }

        if (null == map.get("orgNo")) {
            throw new ServiceException("机构不能为空");
        } else {
            map.put("I_ORG_NO", map.get("orgNo"));
        }
        if (null == map.get("branchNo")) {
            throw new ServiceException("销售网点空,请重新登录或联系运营");
        } else {
            map.put("I_BRANCH_NO", map.get("branchNo"));
        }
        if (null == map.get("staffNo")) {
            throw new ServiceException("工号为空");
        } else {
            map.put("I_STAFF_NO", map.get("staffNo").toString());
        }
        if (null == map.get("custType")) {
            throw new ServiceException("客户类型为空");
        } else {
            map.put("I_CUST_TYP", map.get("custType").toString());
        }
        if (null == map.get("goodsType")) {
            throw new ServiceException("商品类型为空");
        } else {
            map.put("I_GOODS_ID", map.get("goodsType").toString());
        }
        List<PubProd> list = new ArrayList<>();
        try {
            list = dmMapper.queryProdLisForLoanCal(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null == map.get("O_RET")) {
            throw new ServiceException("获取产品信息失败");
        }
        return BeanUtils.copyProperties(list, PubProdDto.class);
    }


    public void againZhiMaAuth(BigDataHFiveCmd bigDataHFiveCmd){
        BigDataHFivemodel bigDataHFivemodel = new BigDataHFivemodel();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", bigDataHFiveCmd.getCustName());
        parameters.put("idCard", bigDataHFiveCmd.getCertNo());
        parameters.put("phone", bigDataHFiveCmd.getPhoneNo());
        parameters.put("redirectURL", bigDataHFiveCmd.getRedirectURL());
        parameters.put("channel_type", "2");
        parameters.put("appKey", "lemon");
        String url = ParamUtils.getParam("BIG_DATA_FETCH_ZHIMAFEN_API_URL");
        Map<String, String> headers = new HashMap<>();
        headers.put("appKey", "lemon");
        JSONObject parseObject = null;
        JSONObject data = null;

        String results = null;
        try {
            results = RemoteClientHelper.invokePostIgnoreException(url, headers, parameters);
            results=EncodingUtil.transcoding(results,"UTF-8");
        } catch (Exception e) {
            log.error(MessageFormat.format(bigDataHFiveCmd.getLoanNo() + "大数据芝麻分接口调用失败({0})", bigDataHFiveCmd.getLoanNo()), e);
        }
        parseObject = JSON.parseObject(results);
        String errorCode = "";
        if (parseObject != null) {
            errorCode = parseObject.getString("errorCode");
            //200证明授权成功,授权失败返回202给前台,201代表24小时内抓取成功
            if ("200".equals(errorCode)) {
                data = parseObject.getJSONObject("data");
                String authorized = data.getString("authorized");
                if ("true".equals(authorized)) {
                    bigDataHFivemodel.setErrorCode("200");
                    bigDataHFivemodel.setErrorMsg("成功获取芝麻分");
                    AppGrapScoreDto appGrapScoreDto = new AppGrapScoreDto();
                    appGrapScoreDto.setCustNo(bigDataHFiveCmd.getCustNo());
                    appGrapScoreDto.setGrapChan("31511001");
                    appGrapScoreDto.setGrapSesaSeed(Integer.valueOf(data.getString("result")));
                    appGrapScoreDto.setLoanNo(bigDataHFiveCmd.getLoanNo());
                    loanAcctservice.saveGrapScore(appGrapScoreDto);
                }
            }
        }
    }
}
