package com.hs.loan.busi.server;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.CommonConstant;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.loan.acct.api.RepayFirstConfApi;
import com.hs.loan.acct.entity.AppCustRegInfo;
import com.hs.loan.acct.entity.AppLoanActAcct;
import com.hs.loan.acct.entity.PubLoanProdCalc;
import com.hs.loan.acct.service.AcctFeeCalService;
import com.hs.loan.acct.service.AppCustRegInfoService;
import com.hs.loan.acct.service.AppLoanActAcctService;
import com.hs.loan.busi.contant.BusiServerContant;
import com.hs.loan.busi.dto.AppCustRegInfoDto;
import com.hs.loan.busi.dto.AppGrapScoreDto;
import com.hs.loan.busi.dto.AppLoanBankAcctDto;
import com.hs.loan.busi.dto.AppLoanProdDto;
import com.hs.loan.busi.dto.LoanAcctInDto;
import com.hs.loan.busi.dto.LoanAcctOutDto;
import com.hs.loan.busi.dto.LoanBankAcctDto;
import com.hs.loan.busi.dto.LoanBranchDto;
import com.hs.loan.busi.dto.LoanFeeDto;
import com.hs.loan.busi.dto.LoanGoodsDto;
import com.hs.loan.busi.dto.LoanOutHistoryDto;
import com.hs.loan.busi.dto.LoanProdCalcDto;
import com.hs.loan.busi.dto.LoanSalerDto;
import com.hs.loan.cust.api.CustBankAcctApi;
import com.hs.loan.cust.api.CustInfoApi;
import com.hs.loan.cust.dto.CustBankAcctDto;
import com.hs.loan.cust.dto.CustInfoDto;
import com.hs.loan.market.api.BranchApi;
import com.hs.loan.market.dto.BranchDto;
import com.hs.loan.prod.api.ProdApi;
import com.hs.loan.prod.api.ProdFeeApi;
import com.hs.loan.prod.dto.ProdBaseInfoDto;
import com.hs.loan.prod.dto.ProdFeeCalDto;
import com.hs.loan.prod.dto.PubProdDto;
import com.hs.loan.prod.dto.PubProdFeeDto;
import com.hs.loan.prod.dto.PubRepayTypConfDto;
import com.hs.loan.pub.hand.service.AppLoanHandService;
import com.hs.loan.sale.api.LoanAcctApi;
import com.hs.loan.sale.bo.LoanListHistoryOutBo;
import com.hs.loan.sale.entity.AppGrapScore;
import com.hs.loan.sale.entity.AppLoanAcct;
import com.hs.loan.sale.entity.AppLoanBankAcct;
import com.hs.loan.sale.entity.AppLoanBranch;
import com.hs.loan.sale.entity.AppLoanFee;
import com.hs.loan.sale.entity.AppLoanFundChan;
import com.hs.loan.sale.entity.AppLoanGoods;
import com.hs.loan.sale.entity.AppLoanProd;
import com.hs.loan.sale.entity.AppLoanRepayTyp;
import com.hs.loan.sale.entity.AppLoanSaler;
import com.hs.loan.sale.service.AppGrapScoreService;
import com.hs.loan.sale.service.AppLoanAcctService;
import com.hs.loan.sale.service.AppLoanBankAcctService;
import com.hs.loan.sale.service.AppLoanBranchService;
import com.hs.loan.sale.service.AppLoanFeeService;
import com.hs.loan.sale.service.AppLoanFundChanService;
import com.hs.loan.sale.service.AppLoanGoodsService;
import com.hs.loan.sale.service.AppLoanProdService;
import com.hs.loan.sale.service.AppLoanRepayTypService;
import com.hs.loan.sale.service.AppLoanSalerService;
import com.hs.system.area.PubSysRegionalBelongService;
import com.hs.system.contant.SysPubContant;
import com.hs.system.entity.SysOrg;
import com.hs.system.entity.SysStaff;
import com.hs.system.index.service.PubIndexService;
import com.hs.system.org.PubSysOrgService;
import com.hs.system.sqe.SysSqeApi;
import com.hs.system.staff.PubSysStaffService;
import com.hs.utils.BeanUtils;
import com.hs.utils.DateUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.SimpleCodeUtils;
import com.hs.utils.StringUtils;

/**
 * 分期基本信息
 * 
 * @author jqiu
 * @create 2015-10-27
 */
@Service
@Transactional(readOnly = true)
public class LoanAcctServer implements LoanAcctApi {
	private static String LOAN_NO_PRE = "LOAN";
	@Autowired
	private AppLoanAcctService appLoanAcctService;
	@Autowired
	private PubIndexService pubIndexService;
	@Autowired
	private AppLoanHandService appLoanHandService;
	@Autowired
	private AcctFeeCalService acctFeeCalService;// 试算组件
	@Autowired
	private ProdApi busiProdService;
	@Autowired
	private RepayFirstConfApi busiRepayFirstConfService;
	@Autowired
	private AppLoanProdService appLoanProdService;
	@Autowired
	private AppLoanGoodsService appLoanGoodsService;
	@Autowired
	private AppLoanFeeService appLoanFeeService;
	@Autowired
	private AppLoanFundChanService appLoanFundChanService;
	@Autowired
	private AppLoanRepayTypService appLoanRepayTypService;
	@Autowired
	private AppLoanBranchService appLoanBranchService;
	@Autowired
	private AppLoanSalerService appLoanSalerService;
	@Autowired
	private ProdFeeApi busiProdFeeService;
	@Autowired
	private BranchApi busiBranchService;
	@Autowired
	private PubSysStaffService pubSysStaffService;
	@Autowired
	private PubSysOrgService pubSysOrgServices;
	@Autowired
	private AppLoanBankAcctService appLoanBankAcctService;
	@Autowired
	private SysSqeApi busiSqeService;
	@Autowired
	private PubSysRegionalBelongService regionalBelongService; // 区县
	@Autowired
	private CustInfoApi busiCustInfoService;
	@Autowired
	private AppLoanActAcctService appLoanActAcctService;
	@Autowired
	private AppGrapScoreService appGrapScoreService;
	@Autowired
	private AppCustRegInfoService appCustRegInfoService;
	
	/**
	 * 获取下一订单号
	 * 
	 * @param orgNo
	 *            销售所在机构编码
	 * @return 订单号
	 */
	private String generateLoanNo(String orgNo) {
		// String now = DateUtils.getCurDate();
		String next = busiSqeService.invokeGetNextSqe(orgNo, "");
		return orgNo + next;
	}

	/**
	 * 分期信息新增
	 * 
	 * @param loanAcctIn
	 *            分期基本信息维护对象
	 */
	@Transactional
	public String addLoanAcct(LoanAcctInDto loanAcctIn, UserProfile userProfile) {
		AppLoanAcct appLoanAcct = new AppLoanAcct();
		if (StringUtils.isEmpty(loanAcctIn.getProdNo())) {
			throw new ServiceException("产品编号为空,请选择分期产品");
		}
		if (StringUtils.isEmpty(loanAcctIn.getCustNo())) {
			throw new ServiceException("客户号为空,请填写客户信息");
		}
		String branchNo = loanAcctIn.getBranchNo();
		if (StringUtils.isEmpty(branchNo)) {
			throw new ServiceException("办理网点信息为空");
		}
		String marName = loanAcctIn.getMarName();
		if (userProfile == null || StringUtils.isEmpty(userProfile.getOrgNo())) {
			throw new ServiceException("登录用户信息为空");
		}

		ProdBaseInfoDto prodBaseInfoDto = busiProdService.getProdBaseInfo(loanAcctIn.getProdNo());
		PubProdDto prod = prodBaseInfoDto.getPubProd();
		if (prodBaseInfoDto == null || null == prod) {
			throw new ServiceException("通过产品编号未获到产品信息");
		}
		List<LoanGoodsDto> loanGoodsLst = loanAcctIn.getGoodsDto();
		if (loanGoodsLst == null || loanGoodsLst.size() == 0) {
			throw new ServiceException("商品信息为空");
		}
		BigDecimal pric = new BigDecimal("0");
		for (LoanGoodsDto loanGoodsDto : loanGoodsLst) {
			if (loanGoodsDto.getPric() == null || loanGoodsDto.getPric().compareTo(new BigDecimal(0)) <= 0) {
				throw new ServiceException("商品金额不能为为空");
			}
			pric = pric.add(loanGoodsDto.getPric());
		}
		this.validationLoanAmt(prod, pric, loanAcctIn.getFstPayAmt(), loanAcctIn.getLoanAmt(), loanAcctIn.getInstNum(),
				prodBaseInfoDto.getPubProdFeeDtos());// 校验分期金额

		String loanNo = "";
		boolean actFlag = false;
		if (StringUtils.isEmpty(loanAcctIn.getLoanNo())) {
			/*** 分期编号 */
			actFlag = true;
			loanNo = generateLoanNo(userProfile.getOrgNo());
			loanAcctIn.setLoanNo(loanNo);
		} else {
			actFlag = false;
			loanNo = loanAcctIn.getLoanNo();
			/** 设置贷款状态 */
			AppLoanAcct appLoanAcctOld = appLoanAcctService.getByLoanNo(loanNo);
			if (appLoanAcctOld != null) {
				appLoanAcct.setStat(appLoanAcctOld.getStat());
			}
			this.deleteLoanProd(loanNo);
			this.deleteLoanFee(loanNo);
			this.deleteLoanRepayType(loanNo);
			this.deleteLoanGoods(loanNo);
			this.deleteLoanBranch(loanNo);
			appLoanAcctService.deleteByLoanNo(loanNo);
			appLoanSalerService.deleteByPrimaryKey(loanNo);

			loanAcctIn.setFileNo(appLoanAcctOld.getFileNo());
			loanAcctIn.setLoanRemark(appLoanAcctOld.getLoanRemark());

		}
		this.saveLoanProd(prod, loanNo, loanAcctIn.getInstNum()); // 保存分期产品编号

		this.saveLoanFee(loanAcctIn.getProdNo(), loanAcctIn.getInstNum(), loanNo, loanAcctIn.getStrSeleFees()); // 保存费用项

		this.saveLoanRepayType(prodBaseInfoDto.getPubRepayTypConfs(), loanNo);// 还款类型

		this.saveLoanGoods(loanNo, loanAcctIn.getGoodsDto()); // 保存商品信息

		// 挂单商户联系人
		String marContctPerson = threeNull(loanAcctIn.getMarContctPerson());
		// 挂单商户联系人电话
		String marContctTel = threeNull(loanAcctIn.getMarContctTel());

		AppLoanBranch appLoanBranch = this.saveLoanBranch(loanNo, branchNo, marName, marContctPerson, marContctTel);// 保存网点信息
		appLoanAcct.setLoanNo(loanNo);
		/*** 客户编号 */
		appLoanAcct.setCustNo(loanAcctIn.getCustNo());
		/*** 客户名称 */
		CustInfoDto cust = busiCustInfoService.getByNo(loanAcctIn.getCustNo());
		if (cust == null)
			throw new ServiceException("客户信息表中不存在客户信息，客户号：" + loanAcctIn.getCustNo());
		appLoanAcct.setCustName(cust.getCustName());
		/*** 首付金额 */
		appLoanAcct.setFstPayAmt(loanAcctIn.getFstPayAmt());

		/*** 分期本金 */
		appLoanAcct.setLoanAmt(loanAcctIn.getLoanAmt());

		/*** 分期类型 */
		appLoanAcct.setLoanTyp(prod.getProdTyp());
		/*** 分期期数 */
		appLoanAcct.setInstNum(loanAcctIn.getInstNum());
		/*** 首次还款额 */
		String strSelFees = loanAcctIn.getStrSeleFees();

		List<LoanProdCalcDto> list = this.loanTryCalc(prod.getProdNo(), loanAcctIn.getLoanAmt(),
				loanAcctIn.getInstNum(), strSelFees);
		if (list != null && list.size() > 0) {
			appLoanAcct.setFstRepayAmt(list.get(0).getFeeAmt());
			/*** 月还款金额 */
			appLoanAcct.setMthRepayAmt(list.get(0).getFeeAmt());
		}
		/*** 首次还款日 */
		String repayDate = this.getRepayDate(prod.getProdNo(), DateUtils.getCurDate());
		appLoanAcct.setFstRepayDate(repayDate);
		if (StringUtils.isEmpty(repayDate)) {
			throw new ServiceException("获取首次还款日失败");
		}
		/*** 每月还款日 */
		appLoanAcct.setMthRepayDate(repayDate.substring(6, 8));
		/*** 申请日期 */
		appLoanAcct.setApplyDate(DateUtils.getCurrentDate());
		/*** 审批日期 */
		appLoanAcct.setAprvDate(null);
		/*** 注册日期 */
		appLoanAcct.setRegDate(null);
		/*** 放款日期 */
		appLoanAcct.setDistrDate(null);
		/*** 档案代码 */
		appLoanAcct.setFileNo(loanAcctIn.getFileNo());
		/*** 分期状态 */
		if (appLoanAcct.getStat() == null || appLoanAcct.getStat().equals("")) {
			appLoanAcct.setStat(PubBusinessConstant.LOANSTAT_UNCOMMIT);
		}

		/*** 贷后状态 */
		// private String afterStat ;
		/*** 五级分类 */
		appLoanAcct.setFivCls(BusiServerContant.LOANCLASSIFICATION_NORMAL);

		/*** 办理所在省 */
		appLoanAcct.setApplyProv(appLoanBranch.getBranchProv());

		/*** 办理所在区/县 */
		appLoanAcct.setApplyArea(appLoanBranch.getBranchCity());
		/*** 办理所在市 */
		appLoanAcct.setApplyCity(appLoanBranch.getBranchArea());
		appLoanAcct.setApplyAddr(loanAcctIn.getApplyAddr());

		// 计算手续费
		Map map = new HashedMap();
		map.put("prodNo", prod.getProdNo());
		map.put("instNum", loanAcctIn.getInstNum());
		Map feeAmtByProd = appLoanAcctService.getFeeAmtByProd(map);
		if (feeAmtByProd == null) {
			throw new ServiceException("获取产品费率信息失败");
		}
		Object fee_rat = feeAmtByProd.get("FEE_RAT");
		BigDecimal loanAmt = BigDecimal.ZERO;
		BigDecimal actAmt = BigDecimal.ZERO;
		if (null != fee_rat && (fee_rat instanceof Integer || fee_rat instanceof Double)
				&& !"".equals(fee_rat.toString().trim())) {
			Double feeRatDouble = Double.valueOf(fee_rat.toString());
			if (feeRatDouble > 0) {
				BigDecimal feeRatDecimal = BigDecimal.valueOf(feeRatDouble);
				loanAmt = loanAcctIn.getLoanAmt();
				// 手续费
				BigDecimal multiplyFeeRat = loanAmt.multiply(feeRatDecimal);
				appLoanAcct.setFeeRat(multiplyFeeRat);
				actAmt = multiplyFeeRat.multiply(new BigDecimal("0.01"));
			}
		} else {
			appLoanAcct.setFeeRat(new BigDecimal("0"));
		}
		// 加实际打款金额表
		if (actFlag) {
			Map<String, Object> actMap = new HashMap<>();
			actMap.put("loanNo", loanNo);
			actMap.put("loanAmt", loanAmt);
			actMap.put("actAmt", loanAmt.subtract(actAmt));
			appLoanActAcctService.updateByLoanNo(actMap);
		} else {
			AppLoanActAcct appLoanActAcct = new AppLoanActAcct();
			appLoanActAcct.setId(RandomUtil.getUUID());
			appLoanActAcct.setLoanAmt(loanAmt);
			appLoanActAcct.setActAmt(loanAmt.subtract(actAmt));
			appLoanActAcct.setLoanNo(loanNo);
			appLoanActAcctService.insert(appLoanActAcct );
		}
		/*** 利率 */
		appLoanAcct.setInterRate(feeAmtByProd.get("RAT") == null ? new BigDecimal("0")
				: new BigDecimal(feeAmtByProd.get("RAT").toString()));

		// 通讯地址
		appLoanAcct.setContactAddr(loanAcctIn.getContactAddr());
		appLoanAcct.setSaleChanl(loanAcctIn.getSaleChanl()); // 提单渠道
		appLoanAcct.setLoanState(PubBusinessConstant.LOANSTAT_WITHOUT);
		appLoanAcctService.insert(appLoanAcct);
		this.saveLoanSale(loanNo, loanAcctIn.getCustNo(), null, userProfile, loanAcctIn.getOrgNo(),
				loanAcctIn.getOrgName());
		String existsAccDownPaymentInfo = threeNull(
				appLoanAcctService.existsAccDownPaymentInfo(loanAcctIn.getLoanNo()));
		if (null == existsAccDownPaymentInfo)
			insertAccDownPaymentInfo(loanAcctIn);// 插入首付信息
		return loanNo;
	}

	private Integer insertAccDownPaymentInfo(LoanAcctInDto loanAcctIn) {
		Date date = new Date();
		Map insertAccDownPaymentInfoMap = new HashMap();
		insertAccDownPaymentInfoMap.put("loanNo", loanAcctIn.getLoanNo());
		insertAccDownPaymentInfoMap.put("custNo", loanAcctIn.getCustNo());
		insertAccDownPaymentInfoMap.put("custName", loanAcctIn.getCustName());
		insertAccDownPaymentInfoMap.put("payAmt", threeBigDecimal(loanAcctIn.getFstPayAmt()));
		insertAccDownPaymentInfoMap.put("stat", "20109001");// 未扣款
		insertAccDownPaymentInfoMap.put("payChan", "20101342");// 支付渠道微信
		insertAccDownPaymentInfoMap.put("instDate", date);
		insertAccDownPaymentInfoMap.put("updtDate", date);
		return appLoanAcctService.insertAccDownPaymentInfo(insertAccDownPaymentInfoMap);

	}

	private BigDecimal threeBigDecimal(Object obj) {
		return (null == obj || "".equalsIgnoreCase(obj.toString().trim())) ? new BigDecimal("0")
				: new BigDecimal(obj.toString().trim());
	}

	private String threeNull(Object obj) {
		return (null == obj || "".equalsIgnoreCase(obj.toString().trim())) ? null : obj.toString().trim();
	}

	public Map getPubBranchByLoanNo(Map map) {
		return appLoanBranchService.getPubBranchByLoanNo(map);
	}

	/**
	 * 通过分期编号查询分期基本信息
	 * 
	 * @param loanNo
	 *            分期编号
	 * @param userProfile
	 *            用户信息（过滤权限）
	 * @return LoanAcctOutDto
	 */
	public LoanAcctOutDto getLoanAcct(String loanNo, UserProfile userProfile) {
		LoanAcctOutDto acctOutDto = new LoanAcctOutDto();

		AppLoanAcct appLoanAcct = appLoanAcctService.getByLoanNo(loanNo);
		AppLoanProd appLoanProd = appLoanProdService.getEnableAcctByLoanNo(loanNo);
		if (appLoanProd == null) {
			throw new ServiceException("分期绑定产品为空!");
		}

		BeanUtils.copyPropertiesNotForce(acctOutDto, appLoanAcct);
		acctOutDto.setStatName(SimpleCodeUtils.getNameByCode(acctOutDto.getStat()));
		List<AppLoanGoods> goods = appLoanGoodsService.queryByLoanNo(loanNo);
		List<LoanGoodsDto> dtos = new ArrayList<>();
		for (AppLoanGoods appLoanGoods : goods) {
			LoanGoodsDto dto = new LoanGoodsDto();
			BeanUtils.copyPropertiesNotForce(dto, appLoanGoods);
			dto.setGoodsTypeName(SimpleCodeUtils.getNameByCode(dto.getGoodsType()));
			dtos.add(dto);
		}

		AppLoanBranch appLoanBranch = appLoanBranchService.getByLoanNo(loanNo);
		if (appLoanBranch == null) {
			throw new ServiceException("分期办理机构为空!");
		}
		acctOutDto.setMarName(appLoanBranch.getMarName());
		acctOutDto.setMarContctPerson(appLoanBranch.getMarContctPerson());
		acctOutDto.setMarContctTel(appLoanBranch.getMarContctTel());
		acctOutDto.setProdNo(appLoanProd.getProdNo());
		acctOutDto.setCustType(appLoanProd.getCustType());

		acctOutDto.setBranchName(appLoanBranch.getBranchName());
		acctOutDto.setBranchNo(appLoanBranch.getBranchNo());

		/*
		 * acctOutDto.setApplyProv(appLoanBranch.getBranchProv());
		 * acctOutDto.setApplyCity(appLoanBranch.getBranchCity());
		 * acctOutDto.setApplyArea(appLoanBranch.getBranchArea());
		 */

		acctOutDto.setApplyProvName(StringUtils.isEmpty(acctOutDto.getApplyProv()) ? ""
				: regionalBelongService.getProvName(acctOutDto.getApplyProv()));

		// 数据库将市和区存反了，只能将错就错。取得时候取反
		acctOutDto.setApplyCityName(StringUtils.isEmpty(acctOutDto.getApplyCity()) ? ""
				: regionalBelongService.getCityName(acctOutDto.getApplyArea()));

		acctOutDto.setApplyAreaName(StringUtils.isEmpty(acctOutDto.getApplyArea()) ? ""
				: regionalBelongService.getCountName(acctOutDto.getApplyCity()));

		acctOutDto.setGoodsDto(dtos);
		List<AppLoanFee> appLoanFees = appLoanFeeService.querySelByLoanNo(loanNo);
		acctOutDto.setSelectFees(BeanUtils.copyProperties(appLoanFees, LoanFeeDto.class));
		// Map loanAcctByLoanNoMap=new HashedMap();
		// loanAcctByLoanNoMap.put("loanNo",loanNo);
		// HashMap<String, Object> loanAcctByLoanNo =
		// appLoanAcctService.getLoanAcctByLoanNo(loanAcctByLoanNoMap);
		// HashMap<String, Object> acct =
		// appLoanAcctService.getAcct(loanAcctByLoanNoMap);
		// Object prov_name=null;
		// Object city_name = null;
		// Object area_name =null;
		// Object apply_addr=null;
		// if(null!=loanAcctByLoanNo&&!loanAcctByLoanNo.isEmpty()){
		// prov_name = loanAcctByLoanNo.get("PROV_NAME");
		// city_name = loanAcctByLoanNo.get("CITY_NAME");
		// area_name = loanAcctByLoanNo.get("AREA_NAME");
		// apply_addr = acct.get("APPLY_ADDR");
		// }
		StringBuilder locationBuilder = new StringBuilder();
		if (null != acctOutDto.getApplyProvName()) {
			locationBuilder.append(acctOutDto.getApplyProvName());
		}
		if (null != acctOutDto.getApplyCityName()) {
			locationBuilder.append(acctOutDto.getApplyCityName());
		}
		if (null != acctOutDto.getApplyAreaName()) {
			locationBuilder.append(acctOutDto.getApplyAreaName());
		}
		 
		if(StringUtils.isEmpty(appLoanAcct.getApplyAddr())){
			
			locationBuilder.append(appLoanBranch.getBranchAdd());
		}else{
			locationBuilder.append(appLoanAcct.getApplyAddr());
		}
		 
		if (null != locationBuilder) {
			acctOutDto.setLocation(locationBuilder.toString());
		}
		return acctOutDto;
	}

	public List<List<HashMap<String, Object>>> getAddressList(String custNo) throws ServiceException, AppException {
		if (null == custNo || custNo.isEmpty()) {
			throw new ServiceException("客户编号为空");
		}
		Map getAddressListMap = new HashedMap();
		getAddressListMap.put("custNo", custNo);
		List<HashMap<String, Object>> addressList = appLoanAcctService.getAddressList(getAddressListMap);
		if (null == addressList || addressList.isEmpty()) {
			return null;
		}
		List<List<HashMap<String, Object>>> returnList = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		for (HashMap<String, Object> s : addressList) {
			List<HashMap<String, Object>> list = new ArrayList<>();
			Object content = s.get("CONTENT");
			if (null != content) {
				try {
					JsonNode jsonNode = objectMapper.readTree(content.toString());
					if (jsonNode.isArray() && jsonNode.size() > 0) {
						for (int i = 0; i < jsonNode.size(); i++) {
							JsonNode name = jsonNode.get(i).path("name");
							JsonNode phone = jsonNode.get(i).path("phone");
							HashMap<String, Object> map = new HashMap<>();
							if (null != name && !"".equals(name.toString().trim())) {
								map.put("name", name.asText());
							}
							if (null != phone && !"".equals(phone.toString().trim())) {
								String asText = phone.asText();
								if (null != asText && !"".equals(asText.trim())) {
									if (asText.indexOf("|") != -1) {
										String[] phoneSplit = phone.asText().split("\\|");
										map.put("phone", phoneSplit);
									} else {
										map.put("phone", asText);
									}
								}
							}
							list.add(map);
						}
					}
				} catch (IOException e) {
					throw new ServiceException("通讯录不是json格式字符串");
				}
			}
			returnList.add(list);
		}
		HashMap<String, Object> custInfoParamMap = new HashMap<>();
		custInfoParamMap.put("custNo", custNo);
		// 查询客户联系人信息
		List<HashMap<String, Object>> custInfo = appLoanAcctService.getCustInfo(custInfoParamMap);
		// 对比电话本的联系人和客户保存的联系人
		if (null != custInfo && !custInfo.isEmpty() && null != returnList && !returnList.isEmpty()) {
			for (List<HashMap<String, Object>> list : returnList) {
				for (HashMap<String, Object> map : list) {
					for (HashMap<String, Object> hashMap : custInfo) {
						Object phone = map.get("phone");
						Object contact_tel = hashMap.get("CONTACT_TEL");
						if (null != phone && null != contact_tel
								&& phone.toString().trim().equalsIgnoreCase(contact_tel.toString().trim())) {
							map.put("isHighlight", true);
						}
					}
				}
			}
		}
		return returnList;
	}

	/**
	 * 分期试算
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<LoanProdCalcDto> loanTryCalc(String prodNo, BigDecimal loanAmt, int proies, String othFees) {
		List<PubLoanProdCalc> list = acctFeeCalService.loanCalc(prodNo, loanAmt, proies, othFees);
		return BeanUtils.copyProperties(list, LoanProdCalcDto.class);
	}

	/**
	 * 获取所有的分期试算
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<LoanProdCalcDto> loanTryCalcAll(String prodNo, BigDecimal loanAmt, String othFees) {
		// 查询产品与费用项关系信息期数(分期试算)
		ProdFeeCalDto prodFeeCalDto = busiProdFeeService.queryProdFeeForCal(prodNo);
		// 获取期数
		List<String> instNum = prodFeeCalDto.getInstNum();
		List<PubLoanProdCalc> resultlist = new ArrayList<>();
		if (null != instNum) {
			for (String s : instNum) {
				List<PubLoanProdCalc> list = acctFeeCalService.loanCalc(prodNo, loanAmt, Integer.valueOf(s), othFees);
				PubLoanProdCalc pubLoanProdCalc = list.get(0);
				pubLoanProdCalc.setInstNum(Integer.valueOf(s));
				resultlist.add(pubLoanProdCalc);
			}
		}
		return BeanUtils.copyProperties(resultlist, LoanProdCalcDto.class);
	}

	/**
	 * 计算还款日
	 * 
	 * @param prodNo
	 *            applyDate
	 * @return
	 */
	@Transactional
	private String getRepayDate(String prodNo, String applyDate) {
		String strs = busiRepayFirstConfService.getFirstRepayDate(prodNo, applyDate);
		return strs;
	}

	/**
	 * 保存分期和产品关系
	 * 
	 * @param loanNo
	 *            prod
	 * @return
	 */
	@Transactional
	private void saveLoanProd(PubProdDto prod, String loanNo, int instNum) {
		AppLoanProd appLoanProd = new AppLoanProd();
		BeanUtils.copyPropertiesNotForce(appLoanProd, prod);
		appLoanProd.setId(RandomUtil.getUUID());
		appLoanProd.setLoanNo(loanNo);
		appLoanProd.setProdStat(CommonConstant.STAT_ENABLE);
		Map<String, Object> map = new HashMap();
		map.put("prodNo", prod.getProdNo());
		map.put("feeNum", instNum);
		Map<String, Object> preFee = appLoanProdService.getPreFee(map);
		Object rat = preFee.get("RAT");
		if (null == rat || "".equals(rat.toString().trim()))
			throw new ServiceException("请检查产品费率信息");
		appLoanProd.setRat(new BigDecimal(rat.toString().trim()));
		appLoanProdService.insert(appLoanProd);
	}

	/**
	 * 删除分期和产品关系
	 * 
	 * @param loanNo
	 * @return
	 */
	@Transactional
	private void deleteLoanProd(String loanNo) {
		appLoanProdService.deleteByLoanNo(loanNo);
	}

	/**
	 * 保存分期余额商品信息
	 */
	@Transactional
	private void saveLoanGoods(String loanNo, List<LoanGoodsDto> loanGoodsDtos) {
		if (loanGoodsDtos == null || loanGoodsDtos.size() == 0) {
			throw new ServiceException("商品信息不能为空!");
		}
		for (LoanGoodsDto loanGoodsDto : loanGoodsDtos) {
			AppLoanGoods appLoanGoods = new AppLoanGoods();
			appLoanGoods.setId(RandomUtil.getUUID());
			appLoanGoods.setLoanNo(loanNo);
			appLoanGoods.setBrand(loanGoodsDto.getBrand());
			appLoanGoods.setGoodsType(loanGoodsDto.getGoodsType());
			appLoanGoods.setMarques(loanGoodsDto.getMarques());
			appLoanGoods.setPric(loanGoodsDto.getPric());
			appLoanGoodsService.insert(appLoanGoods);
		}
	}

	/**
	 * 保存分期余额商品信息
	 */
	@Transactional
	private void deleteLoanGoods(String loanNo) {
		appLoanGoodsService.deleteByLoanNo(loanNo);
	}

	/**
	 * 保存分期与产品费用信息
	 */
	@Transactional
	public void saveLoanFee(String prodNo, Integer instNum, String loanNo, String strfees) {
		Map<String, Object> map = new HashMap<>();
		map.put("prodNo", prodNo);
		map.put("instNum", instNum);
		if (StringUtils.isEmpty(prodNo)) {
			throw new ServiceException("产品信息为空");
		}
		if (null == instNum) {
			throw new ServiceException("分期期数为空");
		}
		List<PubProdFeeDto> prodFeeDtos = busiProdFeeService.queryProdFee2(map);
		if (prodFeeDtos == null || prodFeeDtos.size() == 0) {
			throw new ServiceException("该产品[" + prodNo + "]未配置收费项目，请联系运营");
		}
		for (PubProdFeeDto pubProdFeeDto : prodFeeDtos) {
			AppLoanFee appLoanFee = new AppLoanFee();
			appLoanFee.setId(RandomUtil.getUUID());
			appLoanFee.setIsSel(pubProdFeeDto.getIsSel());
			appLoanFee.setLoanNo(loanNo);
			appLoanFee.setFeeName(pubProdFeeDto.getFeeName());
			appLoanFee.setFeeNo(pubProdFeeDto.getFeeNo());
			if (StringUtils.isEmpty(pubProdFeeDto.getFeeVal())) {
				throw new ServiceException("产品[" + prodNo + "]未配置收费项目费率，请联系运营");
			} else {
				appLoanFee.setFeeVal(pubProdFeeDto.getFeeVal());
			}
			appLoanFee.setInstDate(DateUtils.getCurrentDate());

			String[] fees = null;
			if (!StringUtils.isEmpty(strfees)) {
				fees = strfees.split("\\|");
			}

			if (CommonConstant.COMMON_YES.equals(pubProdFeeDto.getIsSel())) { // 可选费用项
				appLoanFee.setIsChoose(CommonConstant.COMMON_NO); // 否则不参与结算
				if (fees != null) {
					for (String fee : fees) {
						if (fee.equals(pubProdFeeDto.getFeeNo())) {
							appLoanFee.setIsChoose(CommonConstant.COMMON_YES);
							break;
						}
					}
				}
			} else {
				appLoanFee.setIsChoose(CommonConstant.COMMON_YES);// 不可选择的费用项都参与结算
			}
			appLoanFee.setSetlPrior(pubProdFeeDto.getSetlPrior().toString());
			appLoanFee.setStat(CommonConstant.STAT_ENABLE);
			appLoanFee.setInstDate(DateUtils.getCurrentDate());
			// Map delmap=new HashMap();
			// delmap.put("loanNo",loanNo);
			// delmap.put("feeNo",pubProdFeeDto.getFeeNo());
			// appLoanFeeService.delAppLoanFee(map);
			appLoanFeeService.insert(appLoanFee);
		}

	}

	/**
	 * 保存分期与产品费用信息
	 */
	@Transactional
	private void deleteLoanFee(String loanNo) {
		appLoanFeeService.deleteByloanNo(loanNo);
	}

	/**
	 * 保存分期与资金渠道
	 */
	@Transactional
	public void saveLoanFundChanl(String loanNo, String chanlNo, String chanName) {
		AppLoanFundChan appLoanFundChan = new AppLoanFundChan();
		appLoanFundChan.setChanNo(chanlNo);
		appLoanFundChan.setLoanNo(loanNo);
		appLoanFundChan.setId(RandomUtil.getUUID());
		appLoanFundChan.setInstDate(DateUtils.getCurrentDate());
		appLoanFundChan.setStat(CommonConstant.STAT_ENABLE);
		appLoanFundChan.setChanName(chanName);
		appLoanFundChanService.insert(appLoanFundChan);

	}

	/**
	 * 保存分期 与还款类型
	 */
	@Transactional
	private void saveLoanRepayType(List<PubRepayTypConfDto> confDtos, String loanNo) {

		for (PubRepayTypConfDto pubRepayTypConfDto : confDtos) {
			AppLoanRepayTyp appLoanRepayTyp = new AppLoanRepayTyp();
			appLoanRepayTyp.setId(RandomUtil.getUUID());
			appLoanRepayTyp.setLoanNo(loanNo);
			appLoanRepayTyp.setConfNo(pubRepayTypConfDto.getConfNo());
			appLoanRepayTyp.setConfName(pubRepayTypConfDto.getConfName());
			appLoanRepayTypService.insert(appLoanRepayTyp);
		}
	}

	/**
	 * 保存分期 与还款类型
	 */
	@Transactional
	private void deleteLoanRepayType(String loanNo) {
		appLoanRepayTypService.deleteByLoanNo(loanNo);

	}

	/**
	 * 保存分期 与网点关系
	 */
	@Transactional
	private AppLoanBranch saveLoanBranch(String loanNo, String branchNo, String marName, String marContctPerson,
			String marContctTel) {
		AppLoanBranch appLoanBranch = new AppLoanBranch();
		BranchDto branchDto = busiBranchService.getByNo(branchNo);
		if (branchDto == null) {
			throw new ServiceException("没有找到网点信息");
		}
		appLoanBranch.setId(RandomUtil.getUUID());
		appLoanBranch.setLoanNo(loanNo);
		appLoanBranch.setBranchNo(branchNo);
		appLoanBranch.setMarName(marName);
		appLoanBranch.setBranchName(branchDto.getBranchName());
		appLoanBranch.setContctPer(branchDto.getContctPer());
		appLoanBranch.setContctDuty(branchDto.getContctDuty());
		appLoanBranch.setContctTel(branchDto.getContctTel());
		appLoanBranch.setBranchProv(branchDto.getBranchProv());
		appLoanBranch.setBranchCity(branchDto.getBranchCity());
		appLoanBranch.setBranchArea(branchDto.getBranchArea());
		appLoanBranch.setBranchAdd(branchDto.getBranchAddr());
		appLoanBranch.setMarContctPerson(marContctPerson);
		appLoanBranch.setMarContctTel(marContctTel);
		appLoanBranchService.insert(appLoanBranch);
		return appLoanBranch;
	}

	/**
	 * 保存分期 与网点关系
	 */
	@Transactional
	private void deleteLoanBranch(String loanNo) {
		appLoanBranchService.deleteByLoanNo(loanNo);
	}

	/**
	 * 保存分期 与客户银行卡信息
	 */
	@Transactional
	public void saveLoanBanKCard(String loanNo, String bankCardNo) {

		Map<String, Object> param = new HashMap<>();
		param.put("loanNo", loanNo);
		param.put("bankAcctId", bankCardNo);
		param.put("stat", CommonConstant.STAT_DISABLE);
		param.put("updeDate", DateUtils.getCurDate());
		appLoanBankAcctService.updateByLoanSelective(param);

		AppLoanBankAcct appLoanBankAcct = new AppLoanBankAcct();
		appLoanBankAcct.setId(RandomUtil.getUUID());
		appLoanBankAcct.setLoanNo(loanNo);
		appLoanBankAcct.setBankAcctId(bankCardNo);
		appLoanBankAcct.setStat(CommonConstant.STAT_ENABLE);
		appLoanBankAcct.setInstDate(DateUtils.getCurrentDate());
		appLoanBankAcctService.insert(appLoanBankAcct);
	}

	/**
	 * 获取次分期选中的银行卡
	 */

	@Autowired
	private CustBankAcctApi busiCustBankAcctService;

	@Transactional
	public LoanBankAcctDto queryLoanBanKCard(String loanNo) {
		CustBankAcctDto bankAcct = new CustBankAcctDto();
		// 分期银行账户信息
		String bankAcctId = appLoanBankAcctService.getEnableAcctByLoanNo(loanNo);
		if (!StringUtils.isEmpty(bankAcctId)) {
			bankAcct = busiCustBankAcctService.getById(bankAcctId);
		}
		return BeanUtils.copyPropertiesNotForceByClz(LoanBankAcctDto.class, bankAcct);
	}

	/**
	 * 保存分期 与销售关系
	 */
	@Transactional
	private void saveLoanSale(String loanNo, String custNo, String fileNo, UserProfile userProfile, String orgNo,
			String orgName) {
		AppLoanSaler appLoanSaler = new AppLoanSaler();
		appLoanSaler.setLoanNo(loanNo);
		appLoanSaler.setStaffNo(userProfile.getStaffNo());
		appLoanSaler.setStaffName(userProfile.getStaffName());
		SysStaff sysStaff = pubSysStaffService.getByStaffNo(userProfile.getStaffNo());
		appLoanSaler.setMoblNo(sysStaff.getMoblNo());
		appLoanSaler.setOrgNo(userProfile.getOrgNo());
		appLoanSaler.setOrgName(userProfile.getOrgName());
		Map<String, String> map = pubSysStaffService.getStaffMarg(userProfile.getStaffNo());
		if (map != null) {
			appLoanSaler.setAreaMgerNo(map.get("mngNo"));
			appLoanSaler.setAreaMgerName(map.get("mngName"));
			appLoanSaler.setAreaStaffNo(map.get("mngId"));
		}
		SysOrg sysOrg = pubSysOrgServices.getByOrgNo(userProfile.getOrgNo());
		if (sysOrg != null) {
			appLoanSaler.setProvNo(sysOrg.getProvNo());
			appLoanSaler.setCityNo(sysOrg.getCityNo());
			appLoanSaler.setCntyNo(sysOrg.getCntyNo());
		}
		if (sysOrg == null) {
			throw new ServiceException("获取机构信息失败" + userProfile.getOrgNo());
		}
		String orgPath = sysOrg.getOrgCodPath();
		SysOrg daorg = this.getDqOrg(orgPath);
		if (daorg != null) {
			appLoanSaler.setAreaNo(daorg.getOrgNo());
			appLoanSaler.setAreaName(daorg.getOrgName());
		}
		/*** 判断是否是 区域经理角色 */
		Set<String> roles = userProfile.getRoleNoSet();
		boolean flg = false;
		for (String role : roles) {
			if (PubBusinessConstant.ROLE_R_SALE_MGR_AREA.equals(role)) {
				flg = true;
				break;
			}
		}
		if (flg) {
			if (StringUtils.isEmpty(orgNo)) {
				throw new ServiceException("当前用户为区域经理，请选择挂靠机构");
			}
			if (StringUtils.isEmpty(orgName)) {
				throw new ServiceException("当前用户为区域经理，请选择挂靠机构");
			}
			appLoanSaler.setOrgNo(orgNo);
			appLoanSaler.setOrgName(orgName);
		}

		appLoanSalerService.insert(appLoanSaler);
	}

	/**
	 * 最后一步提交分期信息 更新档案代码和分期状态
	 * 
	 * @param loanNo
	 * @param fileNo
	 * @param userProfile
	 */
	@Transactional
	public void sumitLoan(String loanNo, String fileNo, String loanRemark, UserProfile userProfile) {

		AppLoanSaler appLoanSaler = appLoanSalerService.getByLoanNo(loanNo);
		if (appLoanSaler == null) {
			throw new ServiceException("分期信息不存在");
		}
		if (!(userProfile.getStaffNo().equals(appLoanSaler.getStaffNo()))) {
			throw new ServiceException("操作用户信息不一致,无权限访问此分期申请");
		}
		AppLoanAcct appLoanAcct = appLoanAcctService.getByLoanNo(loanNo);
		if (appLoanAcct == null) {
			throw new ServiceException("分期信息不存在");
		}
		if (!PubBusinessConstant.LOANSTAT_UNCOMMIT.equals(appLoanAcct.getStat())
				&& !PubBusinessConstant.LOANSTAT_REJECTED.equals(appLoanAcct.getStat())
				&& !PubBusinessConstant.LOANSTAT_PREPASS.equals(appLoanAcct.getStat())) {
			throw new ServiceException("分期已提交");
		}

		Map<String, Object> param = new HashMap<>();
		param.put("loanNo", loanNo);
		param.put("fileNo", fileNo);
		param.put("applyDate", DateUtils.getCurrentDate());
		param.put("loanRemark", loanRemark);
		/*** 分期状态 */
		param.put("stat", PubBusinessConstant.LOANSTAT_WATEAPPROV);
		appLoanAcctService.updateByLoanNoSelective(param);
		CustInfoDto cust = busiCustInfoService.getByNo(appLoanAcct.getCustNo());
		if (cust == null)
			throw new ServiceException("客户信息表中不存在客户信息，客户号：" + appLoanAcct.getCustNo());
		appLoanHandService.saveAppLoanHand(loanNo, appLoanAcct.getCustNo(), cust.getCustName(),
				PubBusinessConstant.LOANHANDTYPE_APPL, PubBusinessConstant.LOANHANDMODEL_SYS, userProfile.getStaffNo(),
				userProfile.getStaffName(), DateUtils.getCurrentTimestamp(), "分期申请提单-系统写入",
				PubBusinessConstant.CUST_ZC);
	}

	/**
	 * 获取机构所在的大区机构
	 * 
	 * @param orgNos
	 * @return
	 */
	private SysOrg getDqOrg(String orgNos) {
		SysOrg sysOrg = new SysOrg();
		for (int i = 0; i < orgNos.split(",").length; i++) {
			sysOrg = pubSysOrgServices.getByOrgNo(orgNos.split(",")[i]);
			if (sysOrg != null && SysPubContant.ORGATTR_DQ.equals(sysOrg.getOrgAttr())) {
				break;
			}
		}
		return sysOrg;
	}

	/** 根据客户银行卡信息检查是否有有效的分期跟次银行卡绑定 */
	@Override
	public List<AppLoanBankAcctDto> queryAppLoanBank(String bankCardId) {
		Map<String, Object> param = new HashMap<>();
		param.put("bankAcctId", bankCardId);
		param.put("stat", CommonConstant.STAT_ENABLE);
		List<AppLoanBankAcct> list = appLoanBankAcctService.queryForList(param);
		return BeanUtils.copyProperties(list, AppLoanBankAcctDto.class);
	}

	/**
	 * 商品信息，首付金额 商品价格 分期金额 分期金额校验
	 */
	private boolean validationLoanAmt(PubProdDto prodDto, BigDecimal pric, BigDecimal fstReapyAmt, BigDecimal loanAmt,
			Integer inNum, List<PubProdFeeDto> fees) {
		String fstRepayType = "";
		BigDecimal fstRepayVal = BigDecimal.ZERO;
		for (PubProdFeeDto fee : fees) {
			if (fee.getProdNo().equals(prodDto.getProdNo()) && fee.getInstNum() == inNum) {
				fstRepayType = fee.getFstPayTyp();
				fstRepayVal = fee.getFstPayVal();
			}
		}

		BigDecimal loanMaxAmt = prodDto.getMaxAmt();
		BigDecimal loanMinAmt = prodDto.getMinAmt();

		if (!prodDto.getProdStat().equals(PubBusinessConstant.PRODSTAT_SALEING)) {
			throw new ServiceException("产品不是销售状态,请联系运营");
		}

		Date statDt = prodDto.getStartDate();
		Date endDt = prodDto.getEndDate();
		Date curDt = DateUtils.getCurrentDate();

		if (!(statDt.before(curDt) && endDt.after(curDt))) {
			throw new ServiceException("产品已过期,请联系运营");
		}

		if (StringUtils.isEmpty(fstRepayType)) {
			throw new ServiceException("产品首付类型设置为空,请联系运营");
		}
		if (loanMaxAmt == null || loanMaxAmt.compareTo(new BigDecimal(0)) <= 0) {
			throw new ServiceException("产品最大分期金额设置错误,请联系运营");
		}
		if (loanMinAmt == null || loanMinAmt.compareTo(new BigDecimal(0)) < 0) {
			throw new ServiceException("产品最小分期金额设置错误,请联系运营");
		}

		if (pric == null || pric.compareTo(new BigDecimal(0)) <= 0) {
			throw new ServiceException("商品金额不正确");
		}

		// 按金额
		if (fstRepayType.equals(PubBusinessConstant.FSTPAYTYPE_AMT)) {
			if (fstReapyAmt.compareTo(fstRepayVal) < 0) {
				throw new ServiceException("首付金额不足，最小首付金额为：" + fstRepayVal);
			}

		} else if (fstRepayType.equals(PubBusinessConstant.FSTPAYTYPE_RATE)) {
			if (fstRepayVal == null || fstRepayVal.compareTo(new BigDecimal(100)) >= 0) {
				throw new ServiceException("产品首付值设置错误,请联系运营");
			}
			if (fstReapyAmt.compareTo(pric.multiply(fstRepayVal.divide(new BigDecimal("100")))) < 0) {
				throw new ServiceException(
						"首付金额不足，最小首付金额为：" + pric.multiply(fstRepayVal.divide(new BigDecimal("100"))));
			}
		}

		BigDecimal _loanAmt = pric.subtract(fstReapyAmt);
		if (_loanAmt.compareTo(loanAmt) != 0) {
			throw new ServiceException("分期金额错误,请重新填写商品金额信息.");
		}

		if (loanMaxAmt.compareTo(loanAmt) < 0) {
			throw new ServiceException("分期金额过大，最大分期金额为：" + loanMaxAmt);
		}
		if (loanAmt.compareTo(loanMinAmt) < 0) {
			throw new ServiceException("分期金额过小，最小分期金额为：" + loanMinAmt);
		}
		return true;
	}

	/**
	 * 修改分期信息（审批）
	 */
	@Override
	@Transactional
	public void updateLoanByLoanNo(Map<String, Object> map) throws ServiceException, AppException {
		if (StringUtils.isEmpty((String) map.get("loanNo"))) {
			throw new ServiceException("分期编号不能为空");
		}
		appLoanAcctService.updateByLoanNoSelective(map);
	}

	public LoanBranchDto queryLoanBranchByLoanNo(String loanNo) {
		LoanBranchDto dto = new LoanBranchDto();
		if (StringUtils.isEmpty(loanNo)) {
			return null;
		}
		// 网点信息
		AppLoanBranch appLoanBranch = appLoanBranchService.getByLoanNo(loanNo);
		BeanUtils.copyPropertiesNotForce(dto, appLoanBranch);
		return dto;
	}

	public List<LoanOutHistoryDto> queryLoanHistoryList(String certNo) {
		if (StringUtils.isEmpty(certNo)) {
			throw new ServiceException("身份证号不能为空");
		}
		List<LoanListHistoryOutBo> list = appLoanAcctService.queryLoanHistoryList(certNo);
		return BeanUtils.copyProperties(list, LoanOutHistoryDto.class);
	}

	@Override
	public LoanSalerDto queryLoanSaler(String loanNo) throws ServiceException, AppException {
		// 销售信息
		AppLoanSaler appLoanSaler = appLoanSalerService.getByLoanNo(loanNo);
		LoanSalerDto loanSaler = BeanUtils.copyPropertiesNotForceByClz(LoanSalerDto.class, appLoanSaler);
		return loanSaler;
	}

	@Override
	public AppLoanProdDto getLoanProd(String loanNo) throws ServiceException, AppException {
		AppLoanProd appLoanProd = appLoanProdService.getEnableAcctByLoanNo(loanNo);
		if (appLoanProd != null) {
			AppLoanProdDto loanProdDto = BeanUtils.copyPropertiesNotForceByClz(AppLoanProdDto.class, appLoanProd);
			return loanProdDto;
		}
		return null;
	}

	/**
	 * 直通车服务接口
	 */
	@Override
	@Transactional
	public void updateSpacailPrl(String loanNo, UserProfile profile) throws ServiceException, AppException {
		// 修改贷款状态，新增经办信息，资方匹配 大数据拒绝的不能操作
		if (profile == null)
			throw new AppException("登陆信息为空，请重新登陆");
		if (StringUtils.isEmpty(loanNo))
			throw new AppException("操作的分期编号为空");
		String dmReslt = pubIndexService.getDmResult(loanNo);
		if ("2".equals(dmReslt)) {
			throw new ServiceException("此状态不能操作");
		}
		AppLoanAcct appLoanAcct = appLoanAcctService.getByLoanNo(loanNo);
		if (appLoanAcct == null)
			throw new ServiceException("没有找到相应的分期信息");
		if (!PubBusinessConstant.LOANSTAT_REFUSED.equals(appLoanAcct.getStat()))
			throw new ServiceException("只能对审批拒绝的分期做操作");

		Map<String, Object> map = new HashMap<>();
		map.put("loanNo", loanNo);
		map.put("stat", PubBusinessConstant.LOANSTAT_PASS);

		appLoanHandService.saveAppLoanHand(loanNo, appLoanAcct.getCustNo(), appLoanAcct.getCustName(),
				PubBusinessConstant.LOANHANDTYPE_CHANGESTAT, PubBusinessConstant.LOANHANDMODEL_SYS,
				profile.getStaffNo(), profile.getStaffName(), DateUtils.getCurrentTimestamp(), "分期直通车状态修改-销售操作写入",
				PubBusinessConstant.CUST_ZC);

		String matchStat = appLoanAcctService.loanFundMatch(loanNo);

		if ("00".equalsIgnoreCase(matchStat)) {
			try {
				// 生成合同
				// approveLoanContractSignService.buidContant(inDto.getLoanNo(),
				// userProfile);
				appLoanHandService.saveAppLoanHand(loanNo, appLoanAcct.getCustNo(), appLoanAcct.getCustName(),
						PubBusinessConstant.LOANHANDTYPE_APPOV, PubBusinessConstant.LOANHANDMODEL_SYS,
						profile.getStaffNo(), profile.getStaffName(), DateUtils.getCurrentTimestamp(),
						"分期资金匹配-(直通车)系统写入", PubBusinessConstant.CUST_ZC);
			} catch (Exception e) {
				e.printStackTrace();
				/*
				 * retMsg="02"; //审批通过资金匹配成功合同生成失败 return retMsg;
				 */
				throw new ServiceException("操作失败,请重新操作!");
			}
			int cn = appLoanAcctService.updateByLoanNoSelective(map);
			if (cn != 1) {
				throw new ServiceException("更新分期状态失败");
			}
		} else {
			throw new ServiceException("更新分期状态,资金匹配失败");
		}
	}

	/**
	 * 判断挂单商户是否是平台已失效的商户
	 * 
	 * @param branchName
	 * @return true 是已经失效的 false 不为已失效的
	 * @throws ServiceException
	 * @throws AppException
	 */
	@Override
	public List<Map<String, Object>> judgeBranchStatusByFaild(String branchName) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		return busiBranchService.judgeBranchStatusByFaild(branchName);
	}
	
	/**
	 * 根据分期编号查询参与结算的费用项编号
	 * @param loanNo
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	@Override
	public List<String> queryFeesByLoanNo(String loanNo)  throws ServiceException, AppException{
		List<String> lst = new ArrayList<>();
		List<AppLoanFee> appLoanFees =appLoanFeeService.querySelByLoanNo(loanNo);
		for (AppLoanFee appLoanFee : appLoanFees) {
			if(CommonConstant.COMMON_YES.equals(appLoanFee.getIsChoose())){
				lst.add(appLoanFee.getFeeNo());
			}
		}
		return lst;
	}

	/**
	 * 新增客户芝麻分
	 * @param appGrapScoreDto
	 * @return 
	 * @throws ServiceException
	 * @throws AppException
	 */
	@Transactional
	public void saveGrapScore(AppGrapScoreDto appGrapScoreDto) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		AppGrapScore score =new AppGrapScore();
		BeanUtils.copyPropertiesNotForce(score, appGrapScoreDto);
		Map<String, Object> param = new HashMap<>();
		param.put("custNo", appGrapScoreDto.getCustNo());
		param.put("loanNo", appGrapScoreDto.getLoanNo());
		param.put("grapChan", appGrapScoreDto.getGrapChan());
		AppGrapScore scoreOld =appGrapScoreService.getByCustNo(param);
		if (scoreOld!=null) { //存在历史数据
			Map<String, Object> map = new HashMap<>();
			map.put("id", scoreOld.getId());
			map.put("updtDate", DateUtils.getCurrentDate());
			map.put("grapSesaSeed", score.getGrapSesaSeed());
			map.put("grapDate", DateUtils.getCurrentDate());
			appGrapScoreService.updateByPrimaryKeySelective(map);
		}else {
			score.setId(RandomUtil.getUUID());
			score.setInstDate(DateUtils.getCurrentDate());
			score.setUpdtDate(DateUtils.getCurrentDate());
			score.setGrapDate(DateUtils.getCurrentDate());
			appGrapScoreService.insert(score);
		}
		
	}

	/**
	 * 根据CustNo获取芝麻分
	 * @param custNo
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public AppGrapScoreDto getAppGrapScoreByCustNoAndGrapChan(String custNo,String loanNo,String grapChan) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		AppGrapScoreDto scoreDto = new AppGrapScoreDto();
		Map<String, Object> param = new HashMap<>();
		param.put("custNo", custNo);
		param.put("loanNo", loanNo);
		param.put("grapChan", grapChan);
		AppGrapScore score=	appGrapScoreService.getByCustNo(param );
		BeanUtils.copyPropertiesNotForce(scoreDto, score);
		return scoreDto;
	}
	/**
	 * 获取分数
	 */
	@Override
	public List<AppGrapScoreDto> getAppGrapScoreByLoanNoOrCustNo(String custNo, String loanNo)
			throws ServiceException, AppException {
		Map<String, Object> param = new HashMap<>();
		param.put("custNo", custNo);
		param.put("loanNo", loanNo);
		List<AppGrapScore>	list =appGrapScoreService.queryForList(param);
		return BeanUtils.copyProperties(list, AppGrapScoreDto.class);
	}

	/**
	 * 获取客户定位信息
	 * @param custNo
	 * @return
	 */
	public List<AppCustRegInfoDto> getAppCustRegInfoList(Map<String, Object> param ) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		if (StringUtils.isBlank(param.get("custNo")+"")) {
			throw new ServiceException("custNo不能为空");
		}
		List<AppCustRegInfo> list = appCustRegInfoService.queryForList(param);
		return  BeanUtils.copyProperties(list, AppCustRegInfoDto.class);
	}
	/**
	 * 获取客户通话记录
	 */
	public  List<HashMap<String, Object>> getCallList(String custNo)  throws ServiceException, AppException {
		if (null == custNo || custNo.isEmpty()) {
			throw new ServiceException("客户编号为空");
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("custNo", custNo);
		List<HashMap<String, Object>> addressList = appLoanAcctService.getCallList(param);
		if (null == addressList || addressList.isEmpty()) {
			return null;
		}
		ObjectMapper objectMapper = new ObjectMapper();
		List<HashMap<String, Object>> returnList = new ArrayList<>();
		for (HashMap<String, Object> s : addressList) {
			Object content = s.get("CONTENT");
			if (null != content) {
				try {
					JsonNode jsonNode = objectMapper.readTree(content.toString());
					if (jsonNode.isArray() && jsonNode.size() > 0) {
						for (int i = 0; i < jsonNode.size(); i++) {
							JsonNode name = jsonNode.get(i).path("name");
							JsonNode phone = jsonNode.get(i).path("phone");
							JsonNode callDate = jsonNode.get(i).path("callDate");
							JsonNode callDuration = jsonNode.get(i).path("callDuration");
							JsonNode type = jsonNode.get(i).path("type");
							HashMap<String, Object> map = new HashMap<>();
							if (null != name && !"".equals(name.toString().trim())) {
								map.put("name", name.asText());
							}else{
								map.put("name", "陌生人");
							}
							map.put("callDate", callDate.asText());
							map.put("callDuration", callDuration.asText());
							map.put("phone", phone.asText());
							map.put("type", type.asText());
							returnList.add(map);
						}
					}
				} catch (IOException e) {
					throw new ServiceException("通讯录不是json格式字符串");
				}
			}
		}
		return returnList;
	}
}