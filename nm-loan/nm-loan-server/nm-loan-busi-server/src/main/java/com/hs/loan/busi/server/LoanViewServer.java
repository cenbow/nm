package com.hs.loan.busi.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.commons.attach.OssUtil;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.loan.acct.entity.AccRepaySum;
import com.hs.loan.acct.service.AccRepaySumService;
import com.hs.loan.busi.dto.AppLoanCallHandDto;
import com.hs.loan.busi.dto.CrmCustomerOrderDto;
import com.hs.loan.busi.dto.HandFundMatchDto;
import com.hs.loan.busi.dto.LoanAcctOutDto;
import com.hs.loan.busi.dto.LoanBankAcctDto;
import com.hs.loan.busi.dto.LoanBranchDto;
import com.hs.loan.busi.dto.LoanCustBaseInfoDto;
import com.hs.loan.busi.dto.LoanFeeDto;
import com.hs.loan.busi.dto.LoanFundChanDto;
import com.hs.loan.busi.dto.LoanGoodsDto;
import com.hs.loan.busi.dto.LoanListOutDto;
import com.hs.loan.busi.dto.LoanOtherInfoDto;
import com.hs.loan.busi.dto.LoanProdDto;
import com.hs.loan.busi.dto.LoanRepayCollectDto;
import com.hs.loan.busi.dto.LoanSalerDto;
import com.hs.loan.busi.dto.LoanViewDto;
import com.hs.loan.cust.api.CustBankAcctApi;
import com.hs.loan.cust.api.CustInfoApi;
import com.hs.loan.cust.dto.CustBankAcctDto;
import com.hs.loan.cust.dto.CustInfoDto;
import com.hs.loan.finance.dto.AccLoanInstDto;
import com.hs.loan.finance.entity.AccLoanInst;
import com.hs.loan.finance.service.AccLoanInstService;
import com.hs.loan.finance.service.PubFundChanInfoService;
import com.hs.loan.finance.util.HttpInvokerUtil;
import com.hs.loan.market.api.BranchApi;
import com.hs.loan.market.dto.BranchDto;
import com.hs.loan.sale.api.LoanHandApi;
import com.hs.loan.sale.api.LoanViewApi;
import com.hs.loan.sale.bo.HandFundMatchBo;
import com.hs.loan.sale.bo.LoanListOutBo;
import com.hs.loan.sale.entity.AppChanImport;
import com.hs.loan.sale.entity.AppLoanAcct;
import com.hs.loan.sale.entity.AppLoanAtt;
import com.hs.loan.sale.entity.AppLoanBranch;
import com.hs.loan.sale.entity.AppLoanCallHand;
import com.hs.loan.sale.entity.AppLoanFee;
import com.hs.loan.sale.entity.AppLoanGoods;
import com.hs.loan.sale.entity.AppLoanProd;
import com.hs.loan.sale.entity.AppLoanRepayTyp;
import com.hs.loan.sale.entity.AppLoanSaler;
import com.hs.loan.sale.entity.CrmCustomerOrder;
import com.hs.loan.sale.service.AppChanImportService;
import com.hs.loan.sale.service.AppLoanAcctService;
import com.hs.loan.sale.service.AppLoanAttService;
import com.hs.loan.sale.service.AppLoanBankAcctService;
import com.hs.loan.sale.service.AppLoanBranchService;
import com.hs.loan.sale.service.AppLoanCallHandService;
import com.hs.loan.sale.service.AppLoanFeeService;
import com.hs.loan.sale.service.AppLoanFundChanService;
import com.hs.loan.sale.service.AppLoanGoodsService;
import com.hs.loan.sale.service.AppLoanProdService;
import com.hs.loan.sale.service.AppLoanRepayTypService;
import com.hs.loan.sale.service.AppLoanSalerService;
import com.hs.loan.sale.service.CrmCustomerOrderService;
import com.hs.system.area.PubSysRegionalBelongService;
import com.hs.system.entity.SysOrg;
import com.hs.system.org.PubSysOrgService;
import com.hs.utils.BeanUtils;
import com.hs.utils.DateUtils;
import com.hs.utils.ParamUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.SimpleCodeUtils;

/**
 * 分期视图服务
 * 
 * @author jqiu
 * @create 2015-10-27
 */
@Service
@Transactional(readOnly = true)
public class LoanViewServer implements LoanViewApi {
	@Autowired
	private AppLoanAcctService appLoanAcctService;
	@Autowired
	private CustInfoApi busiCustInfoService;
	@Autowired
	private AppLoanGoodsService appLoanGoodsService;
	@Autowired
	private AppLoanSalerService appLoanSalerService;
	@Autowired
	private AppLoanProdService appLoanProdService;
	@Autowired
	private AppLoanBranchService appLoanBranchService;
	@Autowired
	private AppLoanBankAcctService appLoanBankAcctService;
	@Autowired
	private AppLoanFundChanService appLoanFundChanService;
	@Autowired
	private AccLoanInstService accLoanInstService;
	@Autowired
	private AppLoanAttService appLoanAttService;
	@Autowired
	private AppLoanFeeService appLoanFeeService;
	@Autowired
	private PubFundChanInfoService pubFundChanInfoService;
	@Autowired
	private CustBankAcctApi busiCustBankAcctService;
	@Autowired
	private AppLoanRepayTypService appLoanRepayTypService;
	@Autowired
	private AccRepaySumService accRepaySumService;
	@Autowired
	private PubSysRegionalBelongService regionalBelongService; // 区县
	@Autowired
	private PubSysOrgService sysOrgService; // 机构
	@Autowired
	private BranchApi busiBranchService; // 网点服务
	@Autowired
	private LoanHandApi loanHandApi; // 分期经办
	@Autowired
	private AppChanImportService appChanImportService;
	@Autowired
	private CrmCustomerOrderService crmCustomerOrderService;
	@Autowired
	private AppLoanCallHandService appLoanCallHandService;

	/**
	 * 获取产品渠道
	 * 
	 * @param map{loanNo:贷款编号}
	 * @return Map{SALE_CHAN:产品销售渠道}
	 */
	public Map getLoanProd(java.util.Map map) throws ServiceException, AppException {
		if (null == map.get("loanNo")) {
			throw new ServiceException("贷款编号不能为空");
		}
		return appLoanAttService.getLoanProd(map);
	}

	/**
	 * 分期查询的附件下载
	 * 
	 * @param loanNo
	 * @param
	 */
	public List<HashMap<String, Object>> attDownLoad(String loanNo) throws ServiceException, AppException {
		if (null == loanNo || "".equals(loanNo.trim())) {
			throw new ServiceException("贷款编号不能为空");
		}
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("loanNo", loanNo);
		List<HashMap<String, Object>> attByLoanNo = appLoanAttService.getAttByLoanNo(paramMap);
		return attByLoanNo;
	}

	public static void inputstream2file(InputStream ins, File file) throws Exception {
		OutputStream os = new FileOutputStream(file);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();
		ins.close();
	}

	/**
	 * 批量资金匹配
	 * 
	 * @param list
	 * @param userProfile
	 * @throws ServiceException
	 * @throws AppException
	 */
	@Transactional
	public void handFundMatch(List<HandFundMatchDto> list, UserProfile userProfile)
			throws ServiceException, AppException {
		// 基本数据判断
		if (null == list) {
			throw new ServiceException("请选择匹配资方");
		}
		// 时间
		Date date = new Date();
		for (HandFundMatchDto h : list) {
			// 基本字段判断
			if (org.apache.commons.lang.StringUtils.isBlank(h.getLoanNo())) {
				throw new ServiceException("贷款编号为空");
			}
			if (org.apache.commons.lang.StringUtils.isBlank(h.getChanNo())) {
				throw new ServiceException("渠道号为空");
			}
			if (org.apache.commons.lang.StringUtils.isBlank(h.getChanName())) {
				throw new ServiceException("渠道名称为空");
			}
			if (null == h.getLoanAmt()) {
				throw new ServiceException("贷款金额为空");
			}
			if (org.apache.commons.lang.StringUtils.isBlank(userProfile.getStaffNo())) {
				throw new ServiceException("匹配人编号为空");
			} else {
				// 设置匹配人编号
				h.setMatchPsn(userProfile.getStaffNo());
			}
			if (org.apache.commons.lang.StringUtils.isBlank(userProfile.getStaffName())) {
				throw new ServiceException("匹配人姓名为空");
			} else {
				// 设置匹配人姓名
				h.setMatchName(userProfile.getStaffName());
			}
			h.setInstDate(date);
			h.setMatchDate(date);
		}

		for (HandFundMatchDto f : list) {

			// 查询该贷款编号是否已经资方匹配过
			int selectCountByLoanNo = appLoanAcctService.selectCountByLoanNo(f.getLoanNo());
			// custMap
			HashMap<String, Object> custMap = appLoanAcctService.selectCustByNo(f.getLoanNo());
			if (null == custMap) {
				throw new ServiceException("客户编号或者客户姓名为空");
			}
			// 客户编号
			String custNo = (null == custMap.get("CUST_NO")) ? null : custMap.get("CUST_NO").toString().trim();
			String custName = (null == custMap.get("CUST_NAME")) ? null : custMap.get("CUST_NAME").toString().trim();
			if (null == custNo || null == custName) {
				throw new ServiceException("客户编号或者客户姓名为空");
			}
			f.setCustNo(custNo);
			// 客户名字
			f.setCustName(custName);
			// uuid
			f.setMatchId(RandomUtil.getUUID());
			// 存在
			if (selectCountByLoanNo > 0) {
				// 根据贷款编号变更匹配结果为匹配变更
				appLoanAcctService.updateMatchResultByLoan(f.getLoanNo());

			}
			HandFundMatchBo bo = new HandFundMatchBo();
			org.springframework.beans.BeanUtils.copyProperties(f, bo);
			// 插入匹配资金
			appLoanAcctService.insertFundMatch(bo);
			HashMap<String, Object> fundMatchLogMap = new HashMap<>();
			fundMatchLogMap = JSON.parseObject(JSON.toJSONString(f), HashMap.class);
			fundMatchLogMap.put("id", RandomUtil.getUUID());
			fundMatchLogMap.put("instDate", DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));
			fundMatchLogMap.put("matchDate", DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));
			// 插入资金匹配日志表
			appLoanAcctService.insertFundMatchLog(fundMatchLogMap);
			// 根据贷款编号更新渠道编号
			appLoanAcctService.updateChanNoByLoanNo(bo);
			// 资金渠道匹配时加入匹配结果-------郑希鹏&朱凯俊
			try {
				AppChanImport appChanImport = null;
				if (f.getChanNo().equals("001") || f.getChanNo().equals("008")) {
					AppLoanAcct appLoanAcct = appLoanAcctService.getByLoanNo(f.getLoanNo());
					appChanImport = new AppChanImport();
					appChanImport.setLoanNo(f.getLoanNo());
					appChanImport.setChanNo(f.getChanNo());
					appChanImport.setInstNum(appLoanAcct.getInstNum());
					if (appLoanAcct.getInstNum() > 12) {
						appChanImport.setRat(
								"001".equals(f.getChanNo()) ? BigDecimal.valueOf(0.15) : BigDecimal.valueOf(0.125));
					} else {
						appChanImport.setRat(
								"001".equals(f.getChanNo()) ? BigDecimal.valueOf(0.15) : BigDecimal.valueOf(0.12));
					}
					appChanImport.setInstDate(new Date());
					appChanImportService.deleteAndInsert(appChanImport);
				} else if (f.getChanNo().equals("000")) {
					appChanImport = new AppChanImport();
					appChanImport.setLoanNo(f.getLoanNo());
					appChanImport.setChanNo(f.getChanNo());
					appChanImport.setInstDate(new Date());
					appChanImportService.insert(appChanImport);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * 查询销售中的产品
	 * 
	 * @return List<Map>
	 */
	public List<Map> getPubProd() {
		Map map = new HashMap();
		map.put("prodStat", "20101002");
		return appLoanAcctService.getPubProd(map);
	}

	/**
	 * 查询分期信息列表
	 * 
	 * @param page
	 *            page.params
	 *            参数(custName,certNo,acctNo,salerName,areMangerName,orgNo,
	 *            branchName,branchProv,branchCity,branchArea,
	 *            applyDate,regDate,distrDate,stat,aprvDate[审批时间]))
	 * @param userProfile
	 *            用户信息（过滤权限）
	 * @return List
	 */
	public Page<LoanListOutDto> queryLoanList(Page<LoanListOutDto> page, UserProfile userProfile) {
		Page<LoanListOutBo> pageDto = page.toPage(LoanListOutBo.class);

		return appLoanAcctService.queryLoanList(pageDto, userProfile).toPage(LoanListOutDto.class);

	}

	/**
	 * 通过客户编号查询分期信息列表
	 * 
	 * @param custNo
	 *            客户编号
	 * @param userProfile
	 *            用户信息（过滤权限）
	 * @return List<LoanListOutDto>
	 */
	public List<LoanListOutDto> getLoanAcctByCustNo(String custNo, UserProfile userProfile) {
		List<LoanListOutBo> loanList = appLoanAcctService.queryLoanByCustNo(custNo, userProfile);
		return BeanUtils.copyProperties(loanList, LoanListOutDto.class);
	}

	/**
	 * 获取分期视图
	 * 
	 * @param loanNo
	 *            分期编号
	 * @param userProfile
	 *            用户信息（过滤权限）
	 * @return
	 */
	public LoanViewDto getLoanView(String loanNo, UserProfile userProfile) {
		return getLoanViewStp(loanNo, userProfile, "default");
	}

	/**
	 * 获取分期视图
	 * 
	 * @param loanNo
	 *            分期编号
	 * @param userProfile
	 *            用户信息（过滤权限）
	 * @return
	 */
	public LoanViewDto getLoanViewStp(String loanNo, UserProfile userProfile, String type) {
		LoanViewDto loanView = new LoanViewDto();
		if (StringUtils.isEmpty(type)) {
			type = "default";
		}

		// 基本信息
		AppLoanAcct appLoanAcct = appLoanAcctService.getByLoanNo(loanNo);
		LoanAcctOutDto baseInfo = BeanUtils.copyPropertiesNotForceByClz(LoanAcctOutDto.class, appLoanAcct);
		if (baseInfo == null) {
			throw new AppException("分期信息不存在！");
		}
		baseInfo.setStatName(SimpleCodeUtils.getNameByCode(baseInfo.getStat()));
		loanView.setBaseInfo(baseInfo);

		if ("default".equals(type) || "step1".equals(type)) {

			// 客户信息
			CustInfoDto sourceCustInfo = busiCustInfoService.getByNo(baseInfo.getCustNo());
			if (sourceCustInfo != null) {
				LoanCustBaseInfoDto custInfo = BeanUtils.copyPropertiesNotForceByClz(LoanCustBaseInfoDto.class,
						sourceCustInfo);
				custInfo.setCertTypeName(SimpleCodeUtils.getNameByCode(custInfo.getCertType()));
				custInfo.setCustTypeName(SimpleCodeUtils.getNameByCode(custInfo.getCustType()));
				loanView.setCustBaseInfo(custInfo);
			}
			// 商品信息
			List<AppLoanGoods> appGoodsList = appLoanGoodsService.queryByLoanNo(baseInfo.getLoanNo());
			List<LoanGoodsDto> goodsList = BeanUtils.copyProperties(appGoodsList, LoanGoodsDto.class);
			for (LoanGoodsDto loanGoodsDto : goodsList) {
				loanGoodsDto.setGoodsTypeName(SimpleCodeUtils.getNameByCode(loanGoodsDto.getGoodsType()));
			}
			loanView.setGoods(goodsList);

			// 销售信息
			AppLoanSaler appLoanSaler = appLoanSalerService.getByLoanNo(baseInfo.getLoanNo());
			LoanSalerDto loanSaler = BeanUtils.copyPropertiesNotForceByClz(LoanSalerDto.class, appLoanSaler);
			loanView.setSaler(loanSaler);

			// 网点信息
			AppLoanBranch appLoanBranch = appLoanBranchService.getByLoanNo(baseInfo.getLoanNo());
			LoanBranchDto loanBranch = BeanUtils.copyPropertiesNotForceByClz(LoanBranchDto.class, appLoanBranch);
			if (loanBranch != null) {
				BranchDto branchDto = busiBranchService.getByNo(loanBranch.getBranchNo());
				loanBranch.setBranchLevel(branchDto.getBranchLevel());
				loanBranch.setBranchScore(branchDto.getBranchScore());

				loanBranch.setBranchProvName(StringUtils.isEmpty(loanBranch.getBranchProv()) ? ""
						: regionalBelongService.getProvName(loanBranch.getBranchProv()));
				loanBranch.setBranchCityName(StringUtils.isEmpty(loanBranch.getBranchCity()) ? ""
						: regionalBelongService.getCityName(loanBranch.getBranchCity()));
				if (!StringUtils.isEmpty(loanBranch.getBranchArea())) {
					SysOrg sysOrg = sysOrgService.getByOrgNo(loanBranch.getBranchArea());
					if (sysOrg != null) {
						loanBranch.setBranchAreaName(sysOrg.getOrgName());
					}
				}
			}
			loanView.setBranch(loanBranch);

			if ("step1".equals(type)) {
				return loanView;
			}
		}
		if ("default".equals(type) || "step2".equals(type)) {
			// 产品信息
			AppLoanProd appLoanProd = appLoanProdService.getEnableAcctByLoanNo(baseInfo.getLoanNo());
			LoanProdDto loanProd = BeanUtils.copyPropertiesNotForceByClz(LoanProdDto.class, appLoanProd);
			if (loanProd != null) {
				loanProd.setProdTypName(SimpleCodeUtils.getNameByCode(loanProd.getProdTyp()));
				loanProd.setRepayKindName(SimpleCodeUtils.getNameByCode(loanProd.getRepayKind()));
			}
			loanView.setProd(loanProd);

			// 分期银行账户信息
			String bankAcctId = appLoanBankAcctService.getEnableAcctByLoanNo(baseInfo.getLoanNo());
			if (!StringUtils.isEmpty(bankAcctId)) {
				CustBankAcctDto bankAcct = busiCustBankAcctService.getById(bankAcctId);
				loanView.setBankAcct(BeanUtils.copyPropertiesNotForceByClz(LoanBankAcctDto.class, bankAcct));
			}
			if ("step2".equals(type)) {
				return loanView;
			}
		}
		if ("default".equals(type) || "step3".equals(type)) {
			// 渠道资方信息
			String chanNo = appLoanFundChanService.getEnableChanNoByLoanNo(baseInfo.getLoanNo());
			if (!StringUtils.isEmpty(chanNo)) {
				LoanFundChanDto loanFund = BeanUtils.copyPropertiesNotForceByClz(LoanFundChanDto.class,
						pubFundChanInfoService.getByNo(chanNo));
				loanView.setFundChan(loanFund);
			}

			/*
			 * //合同列表(合同附件ID，合同名) List<AppLoanFile> fileList =
			 * appLoanFileService.queryEnableByLoanNo(baseInfo.getLoanNo());
			 * 
			 * Map<String,String> contracts = new HashMap<>(); for (AppLoanFile
			 * appLoanFile : fileList) { contracts.put(appLoanFile.getId(),
			 * appLoanFile.getFileName()); } loanView.setContracts(contracts);
			 */

			// 分期费用项列表
			List<AppLoanFee> appLoanFeeList = appLoanFeeService.querySelByLoanNo(baseInfo.getLoanNo());
			List<LoanFeeDto> loanFeeList = BeanUtils.copyProperties(appLoanFeeList, LoanFeeDto.class);
			loanView.setFees(loanFeeList);

			List<AppLoanRepayTyp> appLoanRepayTypList = appLoanRepayTypService.queryByLoanNo(loanNo);
			Map<String, String> repayTyps = new HashMap<>();
			;
			for (AppLoanRepayTyp appLoanRepayTyp : appLoanRepayTypList) {
				repayTyps.put(appLoanRepayTyp.getConfNo(), appLoanRepayTyp.getConfName());
			}
			loanView.setRepayTyps(repayTyps);

			AccRepaySum repaySum = accRepaySumService.getByLoanNo(loanNo);
			loanView.setRepayCollect(BeanUtils.copyPropertiesNotForceByClz(LoanRepayCollectDto.class, repaySum));

			if ("step3".equals(type)) {
				return loanView;
			}
		}
		if ("default".equals(type) || "step4".equals(type)) {
			// 合同列表(合同附件ID，合同名)
			Map<String, Object> map = new HashMap<>();
			map.put("loanNo", baseInfo.getLoanNo());
			List<AppLoanAtt> fileList = appLoanAttService.queryForList(map);
			Map<String, String> contracts = new HashMap<>();
			for (AppLoanAtt appLoanFile : fileList) {
				contracts.put(appLoanFile.getAttTyp(), OssUtil.getPresignedUrl(appLoanFile.getAttNo()));
			}
			if ("stpe4".equals(type)) {
				return loanView;
			}
		}
		return loanView;
	}

	@Override
	public LoanOtherInfoDto queryLoanOtherInfo(String loanNo, UserProfile userProfile)
			throws ServiceException, AppException {

		// 基本信息
		AppLoanAcct appLoanAcct = appLoanAcctService.getByLoanNo(loanNo);
		LoanAcctOutDto baseInfo = BeanUtils.copyPropertiesNotForceByClz(LoanAcctOutDto.class, appLoanAcct);
		if (baseInfo == null) {
			throw new AppException("分期信息不存在！");
		}
		LoanOtherInfoDto loanOtherInfoDto = new LoanOtherInfoDto();
		// 销售信息
		AppLoanSaler appLoanSaler = appLoanSalerService.getByLoanNo(baseInfo.getLoanNo());
		LoanSalerDto loanSaler = BeanUtils.copyPropertiesNotForceByClz(LoanSalerDto.class, appLoanSaler);
		loanOtherInfoDto.setSaler(loanSaler);

		// 产品信息
		AppLoanProd appLoanProd = appLoanProdService.getEnableAcctByLoanNo(baseInfo.getLoanNo());
		LoanProdDto loanProd = BeanUtils.copyPropertiesNotForceByClz(LoanProdDto.class, appLoanProd);
		if (loanProd != null) {
			loanProd.setProdTypName(SimpleCodeUtils.getNameByCode(loanProd.getProdTyp()));
			loanProd.setRepayKindName(SimpleCodeUtils.getNameByCode(loanProd.getRepayKind()));
		}
		loanOtherInfoDto.setProd(loanProd);

		// 网点信息
		AppLoanBranch appLoanBranch = appLoanBranchService.getByLoanNo(baseInfo.getLoanNo());
		LoanBranchDto loanBranch = BeanUtils.copyPropertiesNotForceByClz(LoanBranchDto.class, appLoanBranch);
		if (loanBranch != null) {
			BranchDto branchDto = busiBranchService.getByNo(loanBranch.getBranchNo());
			if (branchDto != null) {
				loanBranch.setBranchLevel(branchDto.getBranchLevel());
				loanBranch.setBranchScore(branchDto.getBranchScore());
			}

			loanBranch.setBranchProv(StringUtils.isEmpty(loanBranch.getBranchProv()) ? ""
					: regionalBelongService.getProvName(loanBranch.getBranchProv()));
			loanBranch.setBranchCityName(StringUtils.isEmpty(loanBranch.getBranchCity()) ? ""
					: regionalBelongService.getCityName(loanBranch.getBranchCity()));
			if (!StringUtils.isEmpty(loanBranch.getBranchArea())) {
				SysOrg sysOrg = sysOrgService.getByOrgNo(loanBranch.getBranchArea());
				if (sysOrg != null) {
					loanBranch.setBranchAreaName(sysOrg.getOrgName());
				}
			}
		}
		loanOtherInfoDto.setBranch(loanBranch);
		return loanOtherInfoDto;
	}

	/**
	 * 判断是否在签约时间范围内
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return boolean
	 */
	private boolean isRang(int startTime, int endTime) {
		if (startTime > 0 && startTime < 24 && endTime > 0 && endTime < 24) {
			Date date = new Date();
			date = org.apache.commons.lang.time.DateUtils.setHours(date, startTime);
			long st = date.getTime();
			date = org.apache.commons.lang.time.DateUtils.setHours(date, endTime);
			long et = date.getTime();
			long currentTimeMillis = System.currentTimeMillis();
			if (currentTimeMillis > st && currentTimeMillis < et) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 查询该imei通过贷款编号
	 * 
	 * @param loanNo
	 * @return String
	 */
	@Override
	public String selectImeiByLoanNo(String loanNo) throws ServiceException, AppException {
		// 基本数据判断
		if (org.apache.commons.lang.StringUtils.isBlank(loanNo)) {
			throw new ServiceException("贷款编号不能为空");
		}
		return appLoanGoodsService.selectImeiByLoanNo(loanNo);
	}

	/**
	 * 根据贷款编号更新imei
	 * 
	 * @param loanNo
	 * @return int
	 */
	@Override
	@Transactional
	public int updateImeiByLoanNo(String loanNo, String imei) throws ServiceException, AppException {
		// 基本数据判断
		if (org.apache.commons.lang.StringUtils.isBlank(loanNo)) {
			throw new ServiceException("贷款编号不能为空");
		}
		if (org.apache.commons.lang.StringUtils.isBlank(imei)) {
			throw new ServiceException("IMEI号不能为空");
		}
		// 根据贷款编号更新imei
		HashMap<String, Object> map = new HashMap<>();
		map.put("loanNo", loanNo);
		map.put("imei", imei);
		int retcn = appLoanGoodsService.updateImeiByLoanNo(map);

		return retcn;
	}

	public void checkImeiCode(String imei, String loanNo) throws ServiceException, AppException {
		// 查询该imei号是否存在
		Map<String, Object> map = new HashMap<>();
		map.put("imei", imei);
		map.put("loanNo", loanNo);
		Integer imeiExists = appLoanGoodsService.getImeiByLoanNo(map);
		if (null != imeiExists && imeiExists.intValue() > 0) {
			throw new AppException("该IMEI号已经存在");
		}

	}

	/** 分期签约 */
	@Override
	@Transactional
	public String registerLoan(List<LoanListOutDto> loanLst, UserProfile userProfile)
			throws ServiceException, AppException {
		for (LoanListOutDto loan : loanLst) {
			// 防止前端验证失效 3.10
			// 判断审批是否通过
			if (loan.getStat() == null || !PubBusinessConstant.LOANSTAT_PASS.equalsIgnoreCase(loan.getStat())) {
				throw new AppException("审批不是通过的分期不能签约");
			}
			// 获取系统参数签约时间范围value值(json字符串)
			String bnstop = ParamUtils.getParam("signTimeRang");
			// 判断是否为空
			if (org.apache.commons.lang.StringUtils.isNotBlank(bnstop)) {
				// json字符串转为hashmap
				HashMap hashMap = JSON.parseObject(bnstop, HashMap.class);
				// 获取签约开始时间
				int startTime = (int) hashMap.get("startTime");
				// 获取签约结束时间
				int endTime = (int) hashMap.get("endTime");
				// 判断是否在签约时间范围内
				if (!isRang(startTime, endTime)) {
					throw new AppException("系统已结算,请与明日做签约操作");
				}
			}

			// 判断附件表里的类型是否包含需要的类型
			// isOkAttType(loan.getLoanNo());

			Map<String, Object> paramMap = new HashedMap();
			paramMap.put("loanNo", loan.getLoanNo());

			List<AppLoanAtt> list = appLoanAttService.queryForList(paramMap);

			if (list == null || list.size() == 0) {
				throw new AppException("附件信息为空");
			}
			/*
			 * boolean a=false; boolean b=false; boolean c=false; boolean
			 * d=false; for (AppLoanAtt appLoanAtt : list) {
			 * if("40103420".equals(appLoanAtt.getAttTyp())){ a = true; }
			 * if("40103421".equals(appLoanAtt.getAttTyp())){ b = true; }
			 * if("40103422".equals(appLoanAtt.getAttTyp())){ c = true; }
			 * if("40103423".equals(appLoanAtt.getAttTyp())){ d = true; } }
			 * 
			 * if(!a) throw new AppException("请上传个人消费借款申请表"); if(!b) throw new
			 * AppException("请上传个人借款服务合同条款与条件"); if(!c) throw new
			 * AppException("请上传授权委托书"); if(!d) throw new
			 * AppException("请上银行划扣授权书");
			 */

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("loanNo", loan.getLoanNo());
			param.put("stat", PubBusinessConstant.LOANSTAT_SIGNING);
			param.put("regDate", new Date());
			// 根据贷款编号查询imei是否为空
			String imei = this.selectImeiByLoanNo(loan.getLoanNo());
			if (!StringUtils.isEmpty(imei)) {
				param.put("imei", imei);
				Integer imeiByLoanNo = appLoanGoodsService.getImeiByLoanNo(param);
				if (imeiByLoanNo != null && imeiByLoanNo.intValue() > 0) {
					throw new AppException("本次购买商品的IMEI已存在，请检查");
				}
			}
			appLoanAcctService.updateByLoanNoSelective(param);

			loanHandApi.addHandInfoAuto(loan.getLoanNo(), PubBusinessConstant.LOANHANDTYPE_SIGN,
					userProfile.getStaffNo(), userProfile.getStaffName(), "分期签约");

			// 发送还款短信
			sendRepaymentMsg(loan.getLoanNo());
			// 签约时添加渠道信息
			String chanNo = appLoanFundChanService.getEnableChanNoByLoanNo(loan.getLoanNo());
			if (!StringUtils.isEmpty(chanNo) && chanNo.equals("000")) {
				AppChanImport appChanImport = new AppChanImport();
				appChanImport.setLoanNo(loan.getLoanNo());
				appChanImport.setChanNo(chanNo);
				appChanImport.setInstDate(new Date());
				appChanImportService.insert(appChanImport);
			}
		}
		return "success";
	}

	/**
	 * @describe 分期签约发送还款短信
	 * @author txia datetime 2016/9/6 16:43 params {loanNo:贷款编号} return void
	 */
	private void sendRepaymentMsg(String loanNo) {
		Map appLoanAcctServiceMap = new HashMap();
		appLoanAcctServiceMap.put("loanNo", loanNo);
		List<AppLoanAcct> appLoanAccts = appLoanAcctService.queryForList(appLoanAcctServiceMap);
		Map<String, Object> contractCustPhone = appLoanAcctService.getContractCustPhone(appLoanAcctServiceMap);
		if (null == contractCustPhone || contractCustPhone.size() <= 0) {
			return;
		}
		Map<String, String> httpMap = new HashMap<>();
		httpMap.put("reqChanl", "201608240906394194567996681");
		httpMap.put("mob", contractCustPhone.get("sign_phone").toString());
		StringBuilder msgBuilder = new StringBuilder();
		String custName = threeNull(appLoanAccts.get(0).getCustName());
		String instNum = threeNull(appLoanAccts.get(0).getInstNum());
		String mthRepayAmt = threeNull(appLoanAccts.get(0).getMthRepayAmt());
		String mthRepayDate = threeNull(appLoanAccts.get(0).getMthRepayDate());
		String fstRepayDate = threeNull(appLoanAccts.get(0).getFstRepayDate());
		if (null == custName || null == instNum || null == mthRepayAmt || null == mthRepayDate
				|| null == fstRepayDate) {
			return;
		}
		msgBuilder.append("尊敬的：").append(custName)
				.append("您好，非常感谢您选择柠檬分期，希望我们的服务能提高您的生活品质,在您使用柠檬金服产品之际，请按时按协议还款,每月还款日:").append(mthRepayDate)
				.append("，借款期数:").append(instNum).append("，月还款金额（元）:").append(mthRepayAmt).append("，首次还款日:")
				.append(fstRepayDate)
				.append("；若您有任何建议和疑问，欢迎致电客服热线：4006-028-011，客服热线接听时间为每日9：00-21：00，再次感谢您选择我们的服务，祝您身体健康、生活愉快！");
		// 尊敬的：xxx，您好，非常感谢您选择柠檬分期，希望我们的服务能提高您的生活品质,在您使用柠檬金服产品之际，请按时按协议还款,每月还款日:
		// mothRep，借款期数: installNum，月还款金额（元）: mothRepayAmt，首次还款日:
		// firRepayDate；若您有任何建议和疑问，欢迎致电客服热线：4006-028-011，客服热线接听时间为每日9：00-18：00，再次感谢您选择我们的服务，祝您身体健康、生活愉快！
		httpMap.put("msg", msgBuilder.toString());
		if (null != contractCustPhone.get("sign_phone")
				&& !"".equalsIgnoreCase(contractCustPhone.get("sign_phone").toString().trim())) {
			// 发送短信
			HttpInvokerUtil.postForm(ParamUtils.getParam("msgSendUrl"), httpMap);
		}
	}

	/** 分期撤消 */
	@Override
	@Transactional
	public String revokeLoan(String loanNo, UserProfile userProfile, String remark)
			throws ServiceException, AppException {
		String handDetailTyp = PubBusinessConstant.LOANSTAT_UNDO;
		AppLoanAcct appLoanAcct = appLoanAcctService.getByLoanNo(loanNo);
		if (!PubBusinessConstant.LOANSTAT_PASS.equals(appLoanAcct.getStat())
				&& !PubBusinessConstant.LOANSTAT_SIGNING.equals(appLoanAcct.getStat())) {
			throw new ServiceException("只能撤消审批通过/已签约7天内的分期！");
		}
		String validaDate = DateUtils.getDateBefore(DateUtils.getCurDate(), -7);
		if (null != appLoanAcct.getRegDate()) {
			if (DateUtils.convert2Date(validaDate, "yyyyMMdd").after(
					DateUtils.convert2Date(DateUtils.formatDate(appLoanAcct.getRegDate(), "yyyyMMdd"), "yyyyMMdd"))) {
				throw new ServiceException("只能撤销7日以内的分期信息");
			}
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loanNo", loanNo);
		param.put("stat", handDetailTyp);
		appLoanAcctService.updateByLoanNoSelective(param);
		loanHandApi.addHandInfoAuto(loanNo, PubBusinessConstant.LOANHANDTYPE_CHANC, userProfile.getStaffNo(),
				userProfile.getStaffName(), "分期撤销,撤消原因:" + remark);
		return "success";
	}

	/** 分期取消 */
	@Override
	@Transactional
	public String cancelLoan(String loanNo, UserProfile userProfile) throws ServiceException, AppException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loanNo", loanNo);
		param.put("stat", PubBusinessConstant.LOANSTAT_CANCEL);
		AppLoanAcct appLoanAcct = appLoanAcctService.getByLoanNo(loanNo);
		if (!PubBusinessConstant.LOANSTAT_REJECTED.equals(appLoanAcct.getStat())
				&& !PubBusinessConstant.LOANSTAT_UNCOMMIT.equals(appLoanAcct.getStat())) {
			throw new ServiceException("只能取消未提交/审批驳回的分期！");
		}
		appLoanAcctService.updateByLoanNoSelective(param);
		loanHandApi.addHandInfoAuto(loanNo, PubBusinessConstant.LOANHANDTYPE_UNDO, userProfile.getStaffNo(),
				userProfile.getStaffName(), "分期取消/系统写入");
		return "success";
	}

	/**
	 * 查询客户还款计划
	 */
	@Override
	public List<AccLoanInstDto> queryForList(Map<String, Object> param) throws ServiceException, AppException {
		List<AccLoanInst> lst = accLoanInstService.queryForList(param);
		return BeanUtils.copyProperties(lst, AccLoanInstDto.class);
	}

	/**
	 * @describe 新销售系统(查询还款计划)
	 * @author datetime 2016/8/25 10:09
	 *         params{loanNo:贷款编号,certNo:身份证号,custName:客户名称,phoneNo:电话号码} return
	 *         HashMap<String,Object>{parentList,childrenList}
	 */
	public HashMap<String, Object> billQueryForList(java.util.Map map) {
		HashMap<String, Object> returnMap = new HashMap();
		if (null == map || map.size() <= 0) {
			map.put("busiDate", org.apache.commons.lang.time.DateFormatUtils.format(new Date(), "yyyyMMdd"));
		}
		List<Map> maps = accLoanInstService.billQueryForList(map);
		if (null == maps || maps.size() <= 0) {
			return returnMap;
		}
		Set<HashMap<String, Object>> parentList = new HashSet<>();
		for (Map m : maps) {
			HashMap<String, Object> ma = new HashMap<>();
			ma.put("phoneNo", threeNull(m.get("PHONE_NO")));
			ma.put("loanNo", threeNull(m.get("LOAN_NO")));
			ma.put("certNo", threeNull(m.get("CERT_NO")));
			ma.put("custName", threeNull(m.get("CUST_NAME")));
			parentList.add(ma);
		}
		for (HashMap<String, Object> hashMap : parentList) {
			List<Map<String, Object>> childrenList = new ArrayList<>();
			for (Map map1 : maps) {
				if (threeNull(hashMap.get("loanNo")).equals(threeNull(map1.get("LOAN_NO")))) {
					childrenList.add(map1);
				}
			}
			hashMap.put("childrenList", childrenList);
		}
		returnMap.put("parentList", parentList);
		return returnMap;
	}

	private String threeNull(Object obj) {
		return (null == obj || "".equals(obj.toString().trim())) ? null : obj.toString().trim();
	}

	/**
	 * 
	 * 是否放款
	 * 
	 */
	@Override
	@Transactional
	public void modifyLoan(String loanNoList, String loanState, UserProfile userProfile)
			throws ServiceException, AppException {
		if (org.apache.commons.lang.StringUtils.isBlank(loanNoList)) {
			throw new ServiceException("请选择要处理贷款编号");
		}
		JSONArray js = JSONArray.parseArray(loanNoList);
		if (js == null || js.isEmpty()) {
			throw new ServiceException("请选择要处理贷款编号");
		}
		JSONObject jsobj = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		for (int i = 0; i < js.size(); i++) {
			jsobj = js.getJSONObject(i);
			map = new HashMap<String, Object>();
			map.put("loanNo", jsobj.get("loanNo"));
			list.add(map);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loanState", loanState);
		params.put("list", list);
		appLoanAcctService.batchModifyByLoanNoLst(params);

	}

	/**
	 * 查询客服系统工单列表
	 * 
	 * @param page
	 * @param userProfile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public Page<CrmCustomerOrderDto> queryCrmOrderList(Page<CrmCustomerOrderDto> page, UserProfile userProfile)
			throws ServiceException, AppException {
		// TODO Auto-generated method stub
		Page<CrmCustomerOrder> pageDto = page.toPage(CrmCustomerOrder.class);
		Map<String, Object> pageParams = pageDto.getPageParams();
		SysOrg userOrg = sysOrgService.getByOrgNo(userProfile.getOrgNo());
		String userOrgNo = "";
		Set<String> setRoles = userProfile.getRoleNoSet();
		boolean flag = true;
		for (String role : setRoles) {
			if (PubBusinessConstant.ROLE_R_SALE_MGR.equals(role) || PubBusinessConstant.ROLE_R_SYS_SUPER.equals(role)) { // 销售经理
																															// 看所有
				userOrgNo = "";
				flag = false;
				break;
			} else if (PubBusinessConstant.ROLE_R_SALE_MGR_REGION.equals(role)) { // 大区经理
																					// 看本大区内
				userOrgNo = userOrg.getOrgNo().substring(0, 4);
				flag = false;
				break;
			} else if (PubBusinessConstant.ROLE_R_SALE_MGR_AREA.equals(role)) { // 区域经理
																				// 看本区域内
				userOrgNo = userOrg.getOrgNo();
				flag = false;
				break;
			}
		}
		if (flag) {
			pageParams.put("saleStaffNo", userProfile.getStaffNo());
		}
		pageParams.put("saleOrganNo", userOrgNo);
		if (pageParams.get("allotStat")!=null&&!"".equals(pageParams.get("allotStat"))) {
			if ("10001001".equals(pageParams.get("allotStat"))) {
				pageParams.put("allotStatYes", pageParams.get("allotStat"));
			}else {
				pageParams.put("allotStatNo", pageParams.get("allotStat"));
			}
		}
		return crmCustomerOrderService.queryForPage(pageDto).toPage(CrmCustomerOrderDto.class);
	}

	/**
	 * 工单分配
	 */
	@Transactional
	public void updateCrmOrder(Map<String, Object> param, UserProfile userProfile)
			throws ServiceException, AppException {
		if (param.get("handType") != null && !"".equals(param.get("handType"))) {
			String handType = param.get("handType").toString();
			String remark = "";
			AppLoanCallHand appLoanCallHand = new AppLoanCallHand();
			appLoanCallHand.setOrderId(param.get("orderId").toString());
			appLoanCallHand.setHandNo(userProfile.getStaffNo());
			appLoanCallHand.setHandPerson(userProfile.getStaffName());
			appLoanCallHand.setHandType(handType);
			appLoanCallHand.setId(RandomUtil.getUUID());
			appLoanCallHand.setStartDate(DateUtils.getCurrentDate());
			if (param.get("remark") != null) {
				remark = param.get("remark").toString();
			}
			Set<String> setRoles = userProfile.getRoleNoSet();
			CrmCustomerOrder crmCustomerOrder = crmCustomerOrderService.getByPrimaryKey(param.get("orderId").toString());
			if (crmCustomerOrder==null) {
				throw new ServiceException("操作失败，未查询到该笔工单...");
			}
			param.put("updateTime", new Date());
			if ("70028003".equals(handType)) { // 经理分配
				if (!crmCustomerOrder.getOrderDealStat().equals("70027003")) {
					throw new ServiceException("该笔工单状态不能分配...");
				}
				param.put("saleMgrStaffno", userProfile.getStaffNo());
				param.put("orderDealStat", "70027004");
				if (com.hs.utils.StringUtils.isNoneBlank(remark)) {
					appLoanCallHand.setRemark(remark);
				} else {
					appLoanCallHand.setRemark("经理分配给销售==" + param.get("saleStaffNo"));
				}
			} else if ("70028006".equals(handType)) { // 销售提交结果
				param.put("orderDealStat", "70027007");
			} else if ("70028007".equals(handType)) { // 经理撤销
				param.put("dealResult", "70007104");
				appLoanCallHand.setRemark(remark);
				param.put("billRemark", remark);
				param.put("saleStaffNo",null);
			} else if ("70028005".equals(handType)) { // 销售确认受理
				if (!crmCustomerOrder.getOrderDealStat().equals("70027004")) {
					throw new ServiceException("该笔工单状态不能受理...");
				}
				param.put("orderDealStat", "70027005");
				appLoanCallHand.setRemark("销售确认受理==" + userProfile.getStaffNo());
			}else if("70028008".equals(handType)){
				// 经理提交
				if (mgrFlag(setRoles)) {
					if (!crmCustomerOrder.getOrderDealStat().equals("70027007")) {
						throw new ServiceException("该笔工单状态不能提交结果...");
					}
					if (param.get("loanNo") != null && !"".equals(param.get("loanNo"))) {
						param.put("billRemark", remark);
						// 判断贷款编号状态
						Map<String, Object> loanStatMap = crmCustomerOrderService.queryLoanStat(param.get("loanNo").toString());
						if (loanStatMap!=null) {
							param.put("actualProdNo", loanStatMap.get("PROD_NO"));
							param.put("actualAmount", loanStatMap.get("LOAN_AMT"));
							param.put("actualInstNum", loanStatMap.get("INST_NUM"));
							if (PubBusinessConstant.LOANSTAT_SIGNING.equals(loanStatMap.get("STAT"))) { // 办理成功
								param.put("dealResult", "70007102");
							} else if (PubBusinessConstant.LOANSTAT_CANCEL.equals(loanStatMap.get("STAT"))
									|| PubBusinessConstant.LOANSTAT_UNDO.equals(loanStatMap.get("STAT"))
									|| PubBusinessConstant.LOANSTAT_REFUSED.equals(loanStatMap.get("STAT"))) { // 办理失败
								param.put("dealResult", "70007103");
							} else {
								throw new ServiceException("该笔工单正在办理中...");
							}
							param.put("returnReason",loanStatMap.get("STAT"));
						}
					} else {
						throw new ServiceException("该笔工单没有关联贷款...");
					}
				}
			}
			if ("70028004".equals(handType)) { // 销售退回
				param.put("orderDealStat", "70027003");
				if ("".equals(param.get("returnReason"))||param.get("returnReason")==null) {
					throw new ServiceException("工单退回时必须选择退回原因");
				}
				appLoanCallHand.setRemark("退回原因："+param.get("returnReasonName")+remark);
				crmCustomerOrderService.updateBackOrder(param);
			} else {
				crmCustomerOrderService.updateByPrimaryKeySelective(param);
			}
			appLoanCallHandService.updateLastHand(lastHandType(handType,mgrFlag(setRoles)), param.get("orderId").toString());
			appLoanCallHandService.insert(appLoanCallHand);
		} else {
			throw new ServiceException("请选择要操作的类型");
		}
	}

	private boolean mgrFlag(Set<String> setRoles) {
		for (String role : setRoles) {
			if (PubBusinessConstant.ROLE_R_SALE_MGR.equals(role)) { // 销售经理
				return true;
			} else if (PubBusinessConstant.ROLE_R_SALE_MGR_AREA.equals(role)) { // 区域经理
				return true;
			}
		}
		return false;
	}

	@Override
	public CrmCustomerOrderDto queryCrmOrder(Map<String, Object> param) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		List<CrmCustomerOrder> list = crmCustomerOrderService.queryForList(param);
		CrmCustomerOrderDto dto = new CrmCustomerOrderDto();
		if (list.size() > 0) {
			BeanUtils.copyProperties(list.get(0), dto);
		}
		return dto;
	}

	/**
	 * 工单更新loanNo
	 */
	@Transactional
	public void updateCrmOrderOnLoanNo(Map<String, Object> param, UserProfile userProfile)
			throws ServiceException, AppException {
		// TODO Auto-generated method stub
		if (param.get("loanNo") != null && param.get("orderId") != null) {
			param.put("orderDealStat", "70027006");
			param.put("actualDealTime", new Date());
			crmCustomerOrderService.updateByPrimaryKeySelective(param);
		} else {
			throw new ServiceException("loanNo或crmOrderId为空");
		}
	}

	/**
	 * 查询下辖销售
	 */
	public List<Map<String, String>> queryMgrStaff(UserProfile userProfile) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		return crmCustomerOrderService.queryMgrStaff(userProfile.getOrgNo().substring(0, userProfile.getOrgNo().length()-2));
	}

	/**
	 * 批量操作工单
	 */
	@Transactional
	public void updateCrmOrderBatch(List<Map<String, Object>> listParam, String handType, UserProfile userProfile)
			throws ServiceException, AppException {
		// TODO Auto-generated method stub
		if (com.hs.utils.StringUtils.isNotBlank(handType)) {
			for (Map<String, Object> param : listParam) {
				String remark = "";
				AppLoanCallHand appLoanCallHand = new AppLoanCallHand();
				appLoanCallHand.setOrderId(param.get("orderId").toString());
				appLoanCallHand.setHandNo(userProfile.getStaffNo());
				appLoanCallHand.setHandPerson(userProfile.getStaffName());
				appLoanCallHand.setHandType(handType);
				appLoanCallHand.setId(RandomUtil.getUUID());
				appLoanCallHand.setStartDate(DateUtils.getCurrentDate());
				if (param.get("remark") != null) {
					remark = param.get("remark").toString();
				}
				Set<String> setRoles = userProfile.getRoleNoSet();
				CrmCustomerOrder crmCustomerOrder = crmCustomerOrderService.getByPrimaryKey(param.get("orderId").toString());
				if (crmCustomerOrder==null) {
					throw new ServiceException("操作失败，未查询到该笔工单...");
				}
				param.put("updateTime", new Date());
				if ("70028003".equals(handType)) { // 经理分配
					if (!crmCustomerOrder.getOrderDealStat().equals("70027003")) {
						throw new ServiceException("该笔工单状态不能分配...");
					}
					param.put("saleMgrStaffno", userProfile.getStaffNo());
					param.put("orderDealStat", "70027004");
					if (com.hs.utils.StringUtils.isNoneBlank(remark)) {
						appLoanCallHand.setRemark(remark);
					} else {
						appLoanCallHand.setRemark("经理分配给销售==" + param.get("saleStaffNo"));
					}
				} else if ("70028006".equals(handType)) { // 销售提交结果
					appLoanCallHand.setEndDate(new Date());
					param.put("orderDealStat", "70027007");
				} else if ("70028007".equals(handType)) { // 经理撤销
					param.put("dealResult", "70007104");
					appLoanCallHand.setEndDate(new Date());
					appLoanCallHand.setRemark(remark);
					param.put("billRemark", remark);
					param.put("saleStaffNo",null);
				} else if ("70028005".equals(handType)) { // 销售确认受理
					if (!crmCustomerOrder.getOrderDealStat().equals("70027004")) {
						throw new ServiceException("该笔工单状态不能受理...");
					}
					param.put("orderDealStat", "70027005");
					appLoanCallHand.setRemark("销售确认受理==" + userProfile.getStaffNo());
				}else if("70028008".equals(handType)){
					// 经理提交
					if (mgrFlag(setRoles)) {
						appLoanCallHand.setEndDate(new Date());
						if (!crmCustomerOrder.getOrderDealStat().equals("70027007")) {
							throw new ServiceException("该笔工单状态不能提交结果...");
						}
						if (param.get("loanNo") != null && !"".equals(param.get("loanNo"))) {
							param.put("billRemark", remark);
							// 判断贷款编号状态
							Map<String, Object> loanStatMap = crmCustomerOrderService.queryLoanStat(param.get("loanNo").toString());
							if (loanStatMap!=null) {
								param.put("actualProdNo", loanStatMap.get("PROD_NO"));
								param.put("actualAmount", loanStatMap.get("LOAN_AMT"));
								param.put("actualInstNum", loanStatMap.get("INST_NUM"));
								if (PubBusinessConstant.LOANSTAT_SIGNING.equals(loanStatMap.get("STAT"))) { // 办理成功
									param.put("dealResult", "70007102");
								} else if (PubBusinessConstant.LOANSTAT_CANCEL.equals(loanStatMap.get("STAT"))
										|| PubBusinessConstant.LOANSTAT_UNDO.equals(loanStatMap.get("STAT"))
										|| PubBusinessConstant.LOANSTAT_REFUSED.equals(loanStatMap.get("STAT"))) { // 办理失败
									param.put("dealResult", "70007103");
								} else {
									throw new ServiceException("该笔工单正在办理中...");
								}
								param.put("returnReason",loanStatMap.get("STAT"));
							}
						} else {
							throw new ServiceException("该笔工单没有关联贷款...");
						}
					}
				}
				if ("70028004".equals(handType)) { // 销售退回
					param.put("orderDealStat", "70027003");
					if ("".equals(param.get("returnReason"))||param.get("returnReason")==null) {
						throw new ServiceException("工单退回时必须选择退回原因");
					}
					appLoanCallHand.setRemark("退回原因："+param.get("returnReasonName")+remark);
					appLoanCallHand.setEndDate(new Date());
					crmCustomerOrderService.updateBackOrder(param);
				} else {
					crmCustomerOrderService.updateByPrimaryKeySelective(param);
				}
				appLoanCallHandService.updateLastHand(lastHandType(handType,mgrFlag(setRoles)), param.get("orderId").toString());
				appLoanCallHandService.insert(appLoanCallHand);
			}
		} else {
			throw new ServiceException("请选择要操作的类型");
		}

	}

	private String lastHandType(String handType,boolean mgrFlag) {
		if ("70028003".equals(handType)) {
			return "70028002";
		} else if ("70028004".equals(handType)) {
			return "70028003";
		} else if ("70028005".equals(handType)) {
			return "70028003";
		} else if ("70028006".equals(handType)) {
			return "70028005";
		}  else if ("70028008".equals(handType)) {
			return "70028006";
		}
		return "";
	}
	
	/**
	 * 新增工单经办信息
	 */
	@Transactional
	public void addCallHand(AppLoanCallHandDto appLoanCallHandDto,UserProfile userProfile) {
		// TODO Auto-generated method stub
		AppLoanCallHand appLoanCallHand = new AppLoanCallHand();
		appLoanCallHand.setId(RandomUtil.getUUID());
		appLoanCallHand.setHandType("70028009");
		appLoanCallHand.setHandNo(userProfile.getStaffNo());
		appLoanCallHand.setHandPerson(userProfile.getStaffName());
		appLoanCallHand.setOrderId(appLoanCallHandDto.getOrderId());
		appLoanCallHand.setStartDate(DateUtils.getCurrentDate());
		appLoanCallHand.setEndDate(DateUtils.getCurrentDate());
		appLoanCallHand.setRemark(appLoanCallHandDto.getRemark());
		appLoanCallHandService.insert(appLoanCallHand);
	}

	@Override
	public List<AppLoanCallHandDto> findCallHandList(String orderId, UserProfile userProfile) {
		Map<String, Object> param = new HashMap<>();
		param.put("orderId", orderId);
		Set<String> setRoles = userProfile.getRoleNoSet();
		boolean flag = true;
		for (String role : setRoles) {
			if (PubBusinessConstant.ROLE_R_SALE_MGR.equals(role) || PubBusinessConstant.ROLE_R_SYS_SUPER.equals(role)) { // 销售经理
				flag = false;
				break;
			} else if (PubBusinessConstant.ROLE_R_SALE_MGR_REGION.equals(role)) { // 大区经理
				flag = false;
				break;
			} else if (PubBusinessConstant.ROLE_R_SALE_MGR_AREA.equals(role)) { // 区域经理
				flag = false;
				break;
			}
		}
		if (flag) {
			param.put("handTypeIn", "aaaaaaaa");
		}
		List<AppLoanCallHandDto> listDto = new ArrayList<AppLoanCallHandDto>();
		listDto=BeanUtils.copyProperties(appLoanCallHandService.queryHandForList(param), AppLoanCallHandDto.class);
		return listDto;
	}
}