package com.nm.impl.mb;

import com.hs.base.entity.UserProfile;
import com.hs.commons.constants.CommonConstant;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.loan.approv.dto.LoanApprBookDto;
import com.hs.loan.approve.api.LoanApprBookApi;
import com.hs.loan.busi.dto.LoanAcctOutDto;
import com.hs.loan.busi.dto.LoanGoodsDto;
import com.hs.loan.busi.dto.LoanSalerDto;
import com.hs.loan.cust.api.*;
import com.hs.loan.cust.dto.*;
import com.hs.loan.prod.api.ProdApi;
import com.hs.loan.prod.api.ProdFeeApi;
import com.hs.loan.prod.dto.PubProdDto;
import com.hs.loan.prod.dto.PubProdFeeDto;
import com.hs.loan.sale.api.LoanAcctApi;
import com.hs.utils.BeanUtils;
import com.hs.utils.StringUtils;
import com.nm.base.framework.core.exception.ServiceException;
import com.nm.base.framework.core.validate.Validator;
import com.nm.mapper.login.SysCodInfoApiMapper;
import com.nm.mapper.mb.DmMapper;
import com.nm.model.*;
import com.nm.service.mb.MbQueryApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by lenovo on 2017/5/15.
 */
@Service
public class MbQueryApiServiceImpl implements MbQueryApiService {

    @Resource
    private CustWorkApi custworkservice;
    @Resource
    private CustStudyApi custStudyservice;
    @Resource
    private CustContctOtherApi custContctOtherservice;
    @Resource
    private CustContctInfoApi custContctInfoservice;
    @Resource
    private CustLiveInfoApi custliveinfoService;
    @Resource
    private CustBankAcctApi custBankAcctservice;
    @Resource
    private CustInfoApi custInfoServer;
    @Resource
    public LoanAcctApi loanAcctservice;
    @Resource
    private LoanApprBookApi loanApprBookservice;
    @Resource
    private ProdApi prodApiService;
    @Resource
    private SysCodInfoApiMapper sysCodInfoApiMapper;
    @Resource
    private DmMapper dmMapper;
    @Resource
    private ProdFeeApi prodFeeServer;

    private static final Logger log = LoggerFactory.getLogger(MbQueryApiServiceImpl.class);

    @Override
    public CustAllInfoModel getCustInfos(String loanNo, String custNo) {
        return queryCustAllInfo(custNo);
    }


    /**
     * 根据客户编号查询客户所有信息
     *
     * @param custNo
     * @return
     */
    private CustAllInfoModel queryCustAllInfo(String custNo) {
        Validator.init(custNo, "客户编号").required().end();
        CustAllInfoModel custallinfomodel = new CustAllInfoModel();
        //CustWorkInfoModel custworkinfomodel = queryCustWorkInfo(custNo);
        //CustStudyInfoModel custstudyinfomodel = queryCustStudyInfo(custNo);
        //CustContctInfoModel custcontctinfomodel = queryCustContctInfo(custNo);
        CustBankAcctModel custbankacctmodel = queryCustBankAcctInfo(custNo);
        CustLiveInfoModel custLiveInfoModel = queryCustLiveInfo(custNo);
        CustInfoModel custInfoModel = queryCustInfo(custNo);
        CustContctInfoDto custContctInfoDto = custContctInfoservice.getCrtByNo(custNo);
        //custallinfomodel.setCustcontctinfomodel(custcontctinfomodel);
        //custallinfomodel.setCuststudyinfomodel(custstudyinfomodel);
        //custallinfomodel.setCustworkinfomodel(custworkinfomodel);
        custallinfomodel.setCustbankacctmodel(custbankacctmodel);
        custallinfomodel.setCustContctInfoDto(custContctInfoDto);
        custallinfomodel.setCustliveinfomodel(custLiveInfoModel);
        custallinfomodel.setCustinfomodel(custInfoModel);
        custallinfomodel.setCustNo(custNo);
        return custallinfomodel;
    }


    /**
     * 查询客户有效工作信息
     *
     * @param custNo
     * @return
     */
    private CustWorkInfoModel queryCustWorkInfo(String custNo) {
        Validator.init(custNo, "客户编号").required().end();
        List<CustWorkDto> custworkinfodtolist = custworkservice.getCrtCustWorkLst(custNo);
        CustWorkInfoModel custworkinfomodel = new CustWorkInfoModel();
        if (custworkinfodtolist != null && custworkinfodtolist.size() > 0) {
            CustWorkDto custworkdto = custworkinfodtolist.get(0);
            custworkinfomodel = (CustWorkInfoModel) BeanUtils.copyPropertiesNotNull(new CustWorkInfoModel(), custworkdto);
            custworkinfomodel.setIndustryName(sysCodInfoApiMapper.getCodeNameByTypeAndNum("industry", custworkinfomodel.getIndustry()));
            custworkinfomodel.setWorkJobName(sysCodInfoApiMapper.getCodeNameByTypeAndNum("duty", custworkinfomodel.getWorkJob()));
            return custworkinfomodel;
        }
        return custworkinfomodel;
    }

    /**
     * 查询客户学校信息
     *
     * @param custNo
     * @return
     */
    private CustStudyInfoModel queryCustStudyInfo(String custNo) {
        Validator.init(custNo, "客户编号").required().end();
        List<CustStudyDto> custstudydtoList = custStudyservice.getCrtCustStudyList(custNo);
        CustStudyInfoModel custstudyinfomodel = new CustStudyInfoModel();
        if (custstudydtoList != null && custstudydtoList.size() > 0) {
            CustStudyDto custStudyDto = custstudydtoList.get(0);
            custstudyinfomodel = (CustStudyInfoModel) BeanUtils.copyPropertiesNotNull(new CustStudyInfoModel(), custStudyDto);
            return custstudyinfomodel;
        }
        return custstudyinfomodel;
    }

    /**
     * 查询客户其它联系信息
     *
     * @param custNo
     * @return
     */
    private CustContctInfoModel queryCustContctInfo(String custNo) {
        Validator.init(custNo, "客户编号").required().end();
        CustContctInfoModel custcontctinfomodel = new CustContctInfoModel();
        CustContctModel custcontctmodel = new CustContctModel();
        List<CustContctOtherDto> custcontctotherdtoList = custContctOtherservice.getCrtContctOtherLst(custNo);
        List<CustOtherContctInfoModel> custOtherContctInfoLista = new ArrayList<>();
        List<CustOtherContctInfoModel> custOtherContctInfoList = BeanUtils.copyProperties(custcontctotherdtoList, CustOtherContctInfoModel.class);
        for (CustOtherContctInfoModel custOtherContctInfoModel : custOtherContctInfoList) {
            custOtherContctInfoModel.setContactRelName(sysCodInfoApiMapper.getCodeNameByTypeAndNum("relationType", custOtherContctInfoModel.getContactRel()));
            custOtherContctInfoLista.add(custOtherContctInfoModel);
        }
        CustContctInfoDto custContctInfoDto = custContctInfoservice.getCrtByNo(custNo);
        custcontctinfomodel.setCustOtherContctInfoList(custOtherContctInfoLista);
        if (custContctInfoDto != null) {
            custcontctmodel = (CustContctModel) BeanUtils.copyPropertiesNotNull(new CustContctModel(), custContctInfoDto);
        }
        custcontctinfomodel.setCustcontctmodel(custcontctmodel);
        return custcontctinfomodel;
    }


    /**
     * 审批驳回后查看用户基本信息
     */
    @Override
    public List<LoanApprBookModel> queryCustLoanApprBook(String loanNo, String custNo) {
        Validator.init(loanNo, "贷款编号").required().end();
        Validator.init(custNo, "客户编号").required().end();
        UserProfile userprofile = new UserProfile();
        LoanSalerDto loansalerdto = loanAcctservice.queryLoanSaler(loanNo);
        userprofile = (UserProfile) BeanUtils.copyPropertiesNotNull(new UserProfile(),
                loansalerdto);
        LoanAcctOutDto loanacctoutdto = queryLoaninfo(loanNo, userprofile);
        List<LoanApprBookModel> loanapprbookmodellist = new ArrayList<LoanApprBookModel>();
        List<LoanApprBookModel> loanApprBookModelList = new ArrayList<LoanApprBookModel>();
        if (PubBusinessConstant.LOANSTAT_REJECTED.equals(loanacctoutdto.getStat())) {
            List<LoanApprBookDto> loanApprBooks = loanApprBookservice.queryLoanApprBookLst(loanNo, userprofile);
            loanapprbookmodellist = BeanUtils.copyProperties(loanApprBooks, LoanApprBookModel.class);
        }
        /*for(LoanApprBookModel loanapprbookmodel:loanapprbookmodellist){
            loanapprbookmodel.setBlockIdName(sysCodInfoApiMapper.getCodeNameByTypeAndNum("BLOCK",loanapprbookmodel.getBlockId()));
            loanApprBookModelList.add(loanapprbookmodel);
        }*/
        return loanapprbookmodellist;
    }

    @Override
    public CustLoanInfoModel getLoanInfoByLoanNo(String loanNo) {
        Validator.init(loanNo, "贷款编号").required().end();
        CustLoanInfoModel custLoanInfoModel = new CustLoanInfoModel();
        UserProfile userProfile = new UserProfile();
        LoanSalerDto loansalerdto = loanAcctservice.queryLoanSaler(loanNo);
        userProfile = (UserProfile) BeanUtils.copyPropertiesNotNull(new UserProfile(), loansalerdto);
        LoanAcctOutDto loanacctoutdto = queryLoaninfo(loanNo, userProfile);
        List<LoanApprBookModel> loanApprBookModelList = new ArrayList<LoanApprBookModel>();
        if (PubBusinessConstant.LOANSTAT_REJECTED.equals(loanacctoutdto.getStat())) {
            List<LoanApprBookDto> loanApprBooks = loanApprBookservice.queryLoanApprBookLst(loanNo, userProfile);
            loanApprBookModelList = BeanUtils.copyProperties(loanApprBooks, LoanApprBookModel.class);
        }
        List<InstNumInfoModel> instNumInfoList = queryProdFeeinStNums(loanacctoutdto.getProdNo());
        List<String> feeList = loanAcctservice.queryFeesByLoanNo(loanNo);
        //展示页面贷款信息
        custLoanInfoModel.setInstNumInfoList(instNumInfoList);
        custLoanInfoModel.setFeeList(feeList);
        custLoanInfoModel.setCustNo(loanacctoutdto.getCustNo());
        custLoanInfoModel.setLoanAmt(loanacctoutdto.getLoanAmt());
        custLoanInfoModel.setLoanApprBookModelList(loanApprBookModelList);
        custLoanInfoModel.setLoanNo(loanNo);
        custLoanInfoModel.setMthRepayDate(loanacctoutdto.getMthRepayDate());
        custLoanInfoModel.setProdNo(loanacctoutdto.getProdNo());
        custLoanInfoModel.setInstNum(loanacctoutdto.getInstNum());
        custLoanInfoModel.setLoanRemark(loanacctoutdto.getLoanRemark());
        custLoanInfoModel.setFileNo(loanacctoutdto.getFileNo());
        custLoanInfoModel.setMthRepayAmt(loanacctoutdto.getMthRepayAmt());
        /*LoanGoodsDto loanGoodsDto=loanacctoutdto.getGoodsDto().get(0);
        com.nm.model.LoanGoodsDto loanGoodsDto1=new com.nm.model.LoanGoodsDto();
        loanGoodsDto1.setBrand(loanGoodsDto.getBrand());
        loanGoodsDto1.setGoodsType(loanGoodsDto.getGoodsType());
        loanGoodsDto1.setGoodsTypeName(loanGoodsDto.getGoodsTypeName());
        loanGoodsDto1.setId(loanGoodsDto.getId());
        loanGoodsDto1.setLoanNo(loanGoodsDto.getLoanNo());
*/
        custLoanInfoModel.setGoodsDto(loanacctoutdto.getGoodsDto().get(0));
        custLoanInfoModel.setStaffNo(loansalerdto.getStaffNo());
        custLoanInfoModel.setStaffName(loansalerdto.getStaffName());
        custLoanInfoModel.setFstPayAmt(loanacctoutdto.getFstPayAmt());
        Map<String, String> map = dmMapper.getBranchNameByLoanNo(loanNo);
        custLoanInfoModel.setBranchName(map.get("branchName").toString());
        custLoanInfoModel.setBranchNo(map.get("branchNo").toString());
        PubProdDto pubProdDto = prodApiService.getProdBaseInfo(loanacctoutdto.getProdNo()).getPubProd();

        //查询产品所有费用项
        Map<String, Object> feemap = new HashMap<>();
        feemap.put("prodNo", pubProdDto.getProdNo());
        feemap.put("isSel", CommonConstant.COMMON_YES);
        List<PubProdFeeDto> pubProdFeeList = prodFeeServer.queryProdFee(feemap);//查询费用项
        List<Map<String, Object>> allFeeList1 = new ArrayList<>();
        //循环取费用项
        if (pubProdFeeList.size() < 1) throw new ServiceException("产品费用项出错");
        for (PubProdFeeDto pubProdFeeDto : pubProdFeeList) {
            Map<String, Object> feeMap = new HashMap<>();
            feeMap.put("feeName", pubProdFeeDto.getFeeName());
            feeMap.put("feeNo", pubProdFeeDto.getFeeNo());
            allFeeList1.add(feeMap);
        }
        HashSet h = new HashSet(allFeeList1);
        allFeeList1.clear();
        allFeeList1.addAll(h);
        //查询客户类型
        String custType = dmMapper.getCustType(custLoanInfoModel.getCustNo());
        custLoanInfoModel.setCustType(custType);
        custLoanInfoModel.setAllFeelList(allFeeList1);
        custLoanInfoModel.setPubProdDto(pubProdDto);
        return custLoanInfoModel;
    }

    //查询期数
    private List<InstNumInfoModel> queryProdFeeinStNums(String prodNo) {
        List<InstNumInfoModel> instNumInfoList = new ArrayList<InstNumInfoModel>();
        instNumInfoList = dmMapper.queryProdFeeinStNums(prodNo);
        return instNumInfoList;
    }

    @Override
    public void deleteCustContctInfo(String id, String custNo) {
        Validator.init(id, "主键id").required().end();
        Validator.init(custNo, "客户编号").required().end();
        custContctOtherservice.delete(custNo, id);
    }

    @Override
    public CustContctInfoModel getCustOtherContct(String custNo) {
        Validator.init(custNo, "客户编号").required().end();
        CustContctInfoModel custcontctinfomodel = queryCustContctInfo(custNo);
        return custcontctinfomodel;
    }

    @Override
    public CustStudyInfoModel getStudyByCustNo(String custNo) {
        Validator.init(custNo, "客户编号").required().end();
        return queryCustStudyInfo(custNo);
    }

    @Override
    public CustWorkInfoModel getWorkByCustNo(String custNo) {
        Validator.init(custNo, "客户编号").required().end();
        return queryCustWorkInfo(custNo);
    }

    @Override
    public Map<String, Object> queryIsLock(String custNo, String loanNo) {
        Validator.init(custNo, "客户编号").required().end();
        Validator.init(loanNo, "贷款编号").required().end();
        Integer contct = dmMapper.getOtherContctByCustNo(custNo);
        Integer study = dmMapper.getStudyByCustNo(custNo);
        Integer work = dmMapper.getWorkByCustNo(custNo);
        String custType = dmMapper.getCustType(custNo);
        Map<String, Object> map = new HashMap<>();
        ArrayList list = new ArrayList();
        list.add("40105060");
        list.add("40105001");
        map.put("loanNo", loanNo);
        map.put("list", list);
        Integer dianshang = dmMapper.getDianShang(map);
        Map<String, Object> rtmap = new HashMap<>();
        if (work > 0 || study > 0 || custType.equals("40101003")) {
            rtmap.put("identity", true);
        } else {
            rtmap.put("identity", false);
        }
        if (contct > 0) {
            rtmap.put("contact", true);
        } else {
            rtmap.put("contact", false);
        }
        if (dianshang > 0) {
            rtmap.put("economics", true);
        } else {
            rtmap.put("economics", false);
        }
        return rtmap;
    }

    /**
     * 查询客户银行卡信息
     */
    private CustBankAcctModel queryCustBankAcctInfo(String custNo) {
        CustBankAcctModel custbankacctmodel = new CustBankAcctModel();
        List<CustBankAcctDto> custbankacctList = custBankAcctservice.getListByNo(custNo);
        if (custbankacctList != null && custbankacctList.size() > 0) {
            CustBankAcctDto custbankacctdto = custbankacctList.get(0);
            custbankacctmodel = (CustBankAcctModel) BeanUtils.copyPropertiesNotNull(new CustBankAcctModel(), custbankacctdto);
            custbankacctmodel.setOpenOrgName(sysCodInfoApiMapper.getCodeNameByTypeAndNum("chargeOpenOrg", custbankacctmodel.getOpenOrg()));
        }
        if (StringUtils.isBlank(custbankacctmodel.getBankName())) {
            custbankacctmodel.setBankName("");
        }
        return custbankacctmodel;

    }

    /**
     * 查询客户居住信息
     *
     * @param custNo
     * @return
     */
    private CustLiveInfoModel queryCustLiveInfo(String custNo) {
        Validator.init(custNo, "客户编号").required().end();
        CustLiveInfoModel custLiveInfoModel = new CustLiveInfoModel();
        CustLiveInfoDto custLiveInfoDto = custliveinfoService.getCrtByNo(custNo);
        custLiveInfoModel = (CustLiveInfoModel) BeanUtils.copyPropertiesNotNull(new CustLiveInfoModel(), custLiveInfoDto);
        return custLiveInfoModel;
    }

    /**
     * 查询客户基本信息
     *
     * @param custNo
     * @return
     */
    private CustInfoModel queryCustInfo(String custNo) {
        Validator.init(custNo, "客户编号").required().end();
        CustInfoModel custInfoModel = new CustInfoModel();
        CustInfoDto custInfoDto = custInfoServer.getByNo(custNo);
        custInfoModel = (CustInfoModel) BeanUtils.copyPropertiesNotNull(new CustInfoModel(), custInfoDto);
        custInfoModel.setEducName(sysCodInfoApiMapper.getCodeNameByTypeAndNum("education", custInfoModel.getEduc()));
        custInfoModel.setEthnicName(sysCodInfoApiMapper.getCodeNameByTypeAndNum("ethnic", custInfoModel.getEthnic()));
        custInfoModel.setMarriageName(sysCodInfoApiMapper.getCodeNameByTypeAndNum("marriage", custInfoModel.getMarriage()));
        return custInfoModel;
    }

    /**
     * 查询客户贷款基本信息
     */
    private LoanAcctOutDto queryLoaninfo(String loanNo, UserProfile userprofile) {
        Validator.init(loanNo, "贷款编号").required().end();
        LoanAcctOutDto loanacctoutdto = new LoanAcctOutDto();
        loanacctoutdto = loanAcctservice.getLoanAcct(loanNo, userprofile);
        return loanacctoutdto;

    }
}
