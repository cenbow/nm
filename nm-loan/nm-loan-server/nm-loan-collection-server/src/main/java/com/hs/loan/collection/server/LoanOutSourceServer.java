package com.hs.loan.collection.server;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.CommonConstant;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.loan.busi.dto.LoanAcctOutDto;
import com.hs.loan.collection.api.LoanOutSourceApi;
import com.hs.loan.collection.dto.LoanOutSourceDetailDto;
import com.hs.loan.collection.dto.LoanOutsourceDto;
import com.hs.loan.collection.dto.LoanOutsourceRelationDto;
import com.hs.loan.collection.dto.LoanOutsourceReturnDto;
import com.hs.loan.collection.dto.OverdueAmtDetailDto;
import com.hs.loan.collection.entity.AccCustAndBankBo;
import com.hs.loan.collection.service.PlLoanOvduCaseService;
import com.hs.loan.finance.bo.SingleDkResultBo;
import com.hs.loan.finance.bo.SingleOtherBusiBo;
import com.hs.loan.finance.contant.FinanceConstant;
import com.hs.loan.finance.entity.AccPayWith;
import com.hs.loan.finance.entity.AccRepayAdvanReg;
import com.hs.loan.finance.entity.AccRepayDgReg;
import com.hs.loan.finance.entity.AccRepayFlow;
import com.hs.loan.finance.entity.AccWithholdReg;
import com.hs.loan.finance.service.AccCapWithService;
import com.hs.loan.finance.service.AccLoanAcctInstService;
import com.hs.loan.finance.service.AccLoanInstService;
import com.hs.loan.finance.service.AccPayWithService;
import com.hs.loan.finance.service.AccRepayAdvanRegService;
import com.hs.loan.finance.service.AccRepayDgRegService;
import com.hs.loan.finance.service.AccRepayFlowService;
import com.hs.loan.finance.service.AccWithholdRegService;
import com.hs.loan.finance.util.PayChanType;
import com.hs.loan.outsource.bo.LoanOutsourceBo;
import com.hs.loan.outsource.bo.LoanOutsourceRelationBo;
import com.hs.loan.outsource.entity.PlLoanOutsource;
import com.hs.loan.outsource.entity.PlLoanOutsourceAmount;
import com.hs.loan.outsource.entity.PlLoanOutsourceRelation;
import com.hs.loan.outsource.entity.PlLoanOutsourceReturn;
import com.hs.loan.outsource.service.PlLoanOutsourceAmountService;
import com.hs.loan.outsource.service.PlLoanOutsourceRelationService;
import com.hs.loan.outsource.service.PlLoanOutsourceReturnService;
import com.hs.loan.outsource.service.PlLoanOutsourceService;
import com.hs.loan.pub.hand.service.AppLoanHandService;
import com.hs.loan.sale.api.LoanAcctApi;
import com.hs.utils.BeanUtils;
import com.hs.utils.DateUtils;
import com.hs.utils.ParamUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.SimpleCodeUtils;
import com.hs.utils.StringUtils;

/**
 * 预期案件server
 * 
 * @author IT-009
 *
 */
@Service
@Transactional(readOnly = true)
public class LoanOutSourceServer implements LoanOutSourceApi {

	@Autowired
	private AppLoanHandService appLoanHandService;
	// @Autowired
	// private AccLoanBreaksFlowService accLoanBreaksFlowService;
	@Autowired
	private PlLoanOutsourceService plLoanOutsourceService;
	@Autowired
	private PlLoanOutsourceReturnService lLoanOutsourceReturnService;
	@Autowired
	private PlLoanOutsourceRelationService plLoanOutsourceRelationService;
	@Autowired
	private PlLoanOutsourceAmountService plLoanOutsourceAmountService;
	@Autowired
	private LoanAcctApi colLoanAcctService;
	@Autowired
	private AccPayWithService accPayWithService;
	@Autowired
	private AccLoanInstService accLoanInstService;
	@Autowired
	private AccCapWithService accCapWithService;
	@Autowired
	private PlLoanOvduCaseService caseService;
	@Autowired
	private AccRepayFlowService accRepayFlowService;
	@Autowired
	private AccRepayDgRegService accRepayDgRegService;
	@Autowired
	private AccLoanAcctInstService accLoanAcctInstService;
	@Autowired
	private AccWithholdRegService accWithholdRegService;
	@Autowired
	private AccRepayAdvanRegService accRepayAdvanRegService;

	/**
	 *
	 * @param map{loanNo:贷款编号}
	 * @return Map<String,Object>
	 */
	@Transactional
	public Map<String, Object> callBackCase(Map<String, Object> map) {
		return plLoanOutsourceService.callBackCase(map);
	}

	/**
	 *
	 * @param map{loanNo:贷款编号}
	 * @return Map<String,Object>
	 */
	@Transactional
	public void callCollection(String loanNoList) {
		if (org.apache.commons.lang.StringUtils.isBlank(loanNoList)) {
			throw new ServiceException("请选择要处理贷款编号");
		}
		JSONArray js = JSONArray.parseArray(loanNoList);
		if (js == null || js.isEmpty()) {
			throw new ServiceException("请选择要处理贷款编号");
		}
		JSONObject jsobj = null;
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < js.size(); i++) {
			jsobj = js.getJSONObject(i);
			list.add((String) jsobj.get("loanNo"));
		}
		plLoanOutsourceService.batchDeleteByLoanNoLst(list);
		plLoanOutsourceService.batchModifyByLoanNoLst(list);
	}

	/**
	 * 查询销售中心
	 * 
	 * @return Map{PROV_NO:省的编号,PROV_NAME:省份名称}
	 */
	@Override
	public List<Map> getRegionalSale() {
		return plLoanOutsourceService.getRegionalSale();
	}

	/**
	 * 分页查询委外扣款案件信息列表
	 * 
	 * @param page
	 * @param profile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	@Override
	public Page<LoanOutsourceDto> queryOutSource(Page<LoanOutsourceDto> page, UserProfile profile)
			throws ServiceException, AppException {
		String staffNo = profile.getStaffNo();
		if (org.apache.commons.lang3.StringUtils.isBlank(staffNo)) {
			throw new com.hs.base.exception.ServiceException("员工编号为空");
		}
		// 员工角色集合
		List<String> roleList = plLoanOutsourceService.selectRoleByStaffNo(staffNo);
		boolean isAuth = false;
		for (String s : roleList) {
			// admin 委外专员 委外公司
			if (org.apache.commons.lang3.StringUtils.equals(s, "r_sys_super")
					|| org.apache.commons.lang3.StringUtils.equals(s, "r_collec_out_comp")
					|| org.apache.commons.lang3.StringUtils.equals(s, "r_collec_out_staff")
					|| s.indexOf("r_collec_out_staff") != -1) {
				isAuth = true;
			}
			// 委外公司只能查看自己公司的
			if (org.apache.commons.lang3.StringUtils.equals(s, "r_collec_out_comp")) {
				page.getParams().put("outComp", profile.getStaffNo());
			}
		}
		if (!isAuth) {
			throw new com.hs.base.exception.ServiceException("没有权限查看");
		}
		// 委外信息权限判断
		if (org.apache.commons.lang3.StringUtils.isNotBlank(String.valueOf(page.getPageParams().get("flag")))
				&& org.apache.commons.lang3.StringUtils.equals(String.valueOf(page.getPageParams().get("flag")),
						"outsourceinfo")) {

		}
		Page<LoanOutsourceBo> retpage = plLoanOutsourceService
				.queryOutSourceForPage(page.toPage(LoanOutsourceBo.class));
		return retpage.toPage(LoanOutsourceDto.class);
	}

	/**
	 * 委外具体费用项明细
	 * 
	 * @param loanNo
	 * @param id
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	@Override
	public LoanOutSourceDetailDto queryLoanOutsourceAmount(String loanNo, String id) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("loanNo", loanNo);
		map.put("flag", "outsourcepayment");
		LoanOutSourceDetailDto detailDto = new LoanOutSourceDetailDto();
		List<LoanOutsourceBo> list = plLoanOutsourceService.queryOutSourceForLst(map);
		if (list == null || list.size() == 0) {
			throw new com.hs.base.exception.ServiceException("获取委外案件信息失败");
		}
		LoanOutsourceBo bo = list.get(0);
		LoanOutsourceDto loanOutsourceDto = new LoanOutsourceDto();
		BeanUtils.copyPropertiesNotForce(loanOutsourceDto, bo);
		detailDto.setLoanOutsourceDto(loanOutsourceDto);
		map.remove("id");
		List<PlLoanOutsourceAmount> amounts = plLoanOutsourceAmountService.queryOutSourceFeeList(map);
		detailDto.setOverdueAmtDetailDtos(BeanUtils.copyProperties(amounts, OverdueAmtDetailDto.class));

		// 根据贷款编号查询还款计划的费用和
		HashMap<String, Object> loanPlanSumByLoanNoMap = plLoanOutsourceService.getLoanPlanSumByLoanNo(loanNo);
		loanPlanSumByLoanNoMap.put("totalAmt", loanPlanSumByLoanNoMap.get("CUR_RCV_AMT"));
		detailDto.setLoanPlanSumByLoanNoMap(loanPlanSumByLoanNoMap);
		return detailDto;
	}

	/**
	 * 委外分配，分配案件
	 * 
	 * @param id
	 * @param loanNo
	 * @param unitNo
	 * @param unitName
	 * @param profile
	 */
	@Transactional
	@Override
	public void distruOutSource(List<HashMap<String, Object>> list, UserProfile profile)
			throws ServiceException, AppException {
		if (null == list || 0 > list.size()) {
			throw new com.hs.base.exception.ServiceException("没有选择分配的案件");
		}
		for (HashMap<String, Object> m : list) {
			String loanNo = (null == m.get("loanNo")) ? null : m.get("loanNo").toString();
			String unitNo = (null == m.get("unitNo")) ? null : m.get("unitNo").toString();
			if (null == loanNo || null == unitNo) {
				throw new com.hs.base.exception.ServiceException("提交集合信息字段为空");
			}
			Map<String, Object> staffMap = new HashMap<>();
			staffMap.put("staffNo", unitNo);
			Map<String, Object> staff = plLoanOutsourceService.getStaff(staffMap);
			if (!"10002001".equals(staff.get("STAFF_STAT"))) {
				throw new com.hs.base.exception.ServiceException("委外公司状态为失效不能分配！");
			}
			Map<String, Object> map = new HashMap<>();
			map.put("loanNo", loanNo);
			List<Map<String, Object>> outsourceCur = plLoanOutsourceService.outsourceCur(map);
			Map<String, Object> outsourceOver = plLoanOutsourceService.outsourceOver(map);
			if (null == outsourceCur && null != outsourceOver && 0 < outsourceOver.size()) {
				String overLoanNo = (null == outsourceOver.get("LOAN_NO")) ? null
						: outsourceOver.get("LOAN_NO").toString();
				String overUnitNo = (null == outsourceOver.get("UNIT_NO")) ? null
						: outsourceOver.get("UNIT_NO").toString();
				if (loanNo.equalsIgnoreCase(overLoanNo) && unitNo.equalsIgnoreCase(overUnitNo)) {
					throw new com.hs.base.exception.ServiceException("案件回收，不能分配给最后一次分配的委外公司");
				}
			}
			if (null != outsourceCur && 0 < outsourceCur.size()) {
				for (Map<String, Object> stringObjectMap : outsourceCur) {
					String overLoanNo = (null == stringObjectMap.get("LOAN_NO")) ? null
							: stringObjectMap.get("LOAN_NO").toString();
					String overUnitNo = (null == stringObjectMap.get("UNIT_NO")) ? null
							: stringObjectMap.get("UNIT_NO").toString();
					if (loanNo.equalsIgnoreCase(overLoanNo) && unitNo.equalsIgnoreCase(overUnitNo)) {
						throw new com.hs.base.exception.ServiceException("不能分配给最后一次分配的委外公司");
					}
				}
			}
		}
		for (HashMap<String, Object> m : list) {
			String id = (null == m.get("id")) ? null : m.get("id").toString();
			String loanNo = (null == m.get("loanNo")) ? null : m.get("loanNo").toString();
			String unitNo = (null == m.get("unitNo")) ? null : m.get("unitNo").toString();
			String unitName = (null == m.get("unitName")) ? null : m.get("unitName").toString();
			if (null == id || null == loanNo || null == unitNo || null == unitName) {
				throw new com.hs.base.exception.ServiceException("提交集合信息字段为空");
			}
			PlLoanOutsource out = plLoanOutsourceService.getByPrimaryKey(id);
			if (out.getIsSettle() != null && "10001001".equals(out.getIsSettle())) {
				throw new com.hs.base.exception.ServiceException(out.getLoanNo() + "案件已结清");
			}
			Map<String, Object> map = new HashMap<>();
			map.put("loanNo", loanNo);
			map.put("stat", CommonConstant.STAT_DISABLE);
			plLoanOutsourceRelationService.updateBySelective(map);
			PlLoanOutsourceRelation plLoanOutsourceRelation = new PlLoanOutsourceRelation();
			plLoanOutsourceRelation.setId(RandomUtil.getUUID());
			plLoanOutsourceRelation.setLoanNo(loanNo);
			plLoanOutsourceRelation.setInstDate(DateUtils.getCurrentDate());
			plLoanOutsourceRelation.setUnitNo(unitNo);
			plLoanOutsourceRelation.setUnitName(unitName);
			plLoanOutsourceRelation.setStat(CommonConstant.STAT_ENABLE);
			plLoanOutsourceRelationService.insert(plLoanOutsourceRelation);
			map.put("id", id);
			map.put("companyName", unitName);
			plLoanOutsourceService.updateByPrimaryKeySelective(map);
			LoanAcctOutDto dto = colLoanAcctService.getLoanAcct(loanNo, profile);
			if (dto == null) {
				throw new com.hs.base.exception.ServiceException("获取分期信息失败");
			}
			// 经办信息
			appLoanHandService.saveAppLoanHand(loanNo, dto.getCustNo(), dto.getCustName(),
					PubBusinessConstant.LOANHANDTYPE_OUTSOURCE, PubBusinessConstant.LOANHANDMODEL_SYS,
					profile.getStaffNo(), profile.getStaffName(), DateUtils.getCurrentTimestamp(),
					"分期委外,委外公司为:" + unitName, PubBusinessConstant.CUST_ZC);
		}
	}

	/**
	 * 委外对公还款登记录入
	 * 
	 * @param requestParamMap
	 *            请求参数
	 * @param userProfile
	 *            用户信息
	 */
	@Transactional
	public void saveLoanRepayDg(HashMap<String, Object> requestParamMap, UserProfile userProfile)
			throws ServiceException, AppException {

		// 贷款编号
		String loanNo = (null == requestParamMap.get("loanNo")) ? null : requestParamMap.get("loanNo").toString();
		if (org.apache.commons.lang.StringUtils.isBlank(loanNo)) {
			throw new ServiceException("贷款编号为空");
		}
		// 通过贷款编号查询是否存在锁表
		Integer countByLoanNoInstStat = accLoanInstService.getCountByLoanNoInstStat(loanNo);
		if (null != countByLoanNoInstStat && 0 < countByLoanNoInstStat.intValue()) {
			throw new ServiceException("当前不可进行此操作");
		}
		// 通过贷款编号查询最近的一期
		Map<String, Object> amtMap = new HashMap<>();
		amtMap.put("loanNo", loanNo);
		HashMap<String, Object> map = plLoanOutsourceService.getcurRvcAmtByLoanNo(amtMap);
		BigDecimal dgTransBal = (null == map.get("CUR_RCV_AMT")) ? null
				: BigDecimal.valueOf(Double.valueOf(map.get("CUR_RCV_AMT").toString().trim()));
		BigDecimal setlAmt = (null == map.get("SETL_AMT")) ? null
				: BigDecimal.valueOf(Double.valueOf(map.get("SETL_AMT").toString().trim()));
		if (null == dgTransBal || dgTransBal.intValue() <= 0) {
			throw new ServiceException("已经结清");
		}
		String repayDate = map.get("REPAY_DATE").toString();
		Integer repayNum = Integer.valueOf(map.get("REPAY_NUM").toString());
		requestParamMap.put("repayNum", repayNum);

		// 还款金额
		BigDecimal tranAmt = BigDecimal.valueOf(Double.valueOf(String.valueOf(requestParamMap.get("tranAmt"))));
		requestParamMap.put("totlAmt", dgTransBal);
		// 预处理表锁表(当前期)
		HashMap<String, Object> paramMap = new HashMap<>();
		// 贷款编号
		paramMap.put("loanNo", loanNo);
		int overStat = 0;

		AccRepayAdvanReg accRepayAdvanReg = null;
		try {
			if (tranAmt.compareTo(dgTransBal) == 0) {
				// 还完了
				overStat = 2;
			} else {
				overStat = 1;
			}
			// 锁表 锁预处理表
			String returnStatus = accLoanAcctInstService.updateAccLoanAcctStatusLock(loanNo, repayNum);
			if (!returnStatus.equals(FinanceConstant.SUCC)) {
				throw new ServiceException("代扣数据［预处理信息ACC_LOAN_ACCT_INST］锁表失败");
			}
			requestParamMap.put("id", RandomUtil.getUUID());
			// 插入时间
			requestParamMap.put("instDate", DateUtils.getCurrentTimestamp());
			// 插入用户ID
			requestParamMap.put("tranStaff", userProfile.getStaffName());
			requestParamMap.put("tranOrg", userProfile.getOrgNo());
			// 账单日
			requestParamMap.put("repayDate", repayDate);

			// 根据贷款编号查询交易方
			String tranObjByLoanNo = accCapWithService.selectTranObjByLoanNo(loanNo.trim());
			if (org.apache.commons.lang3.StringUtils.isNotBlank(tranObjByLoanNo)) {
				requestParamMap.put("tranObj", tranObjByLoanNo);
			}
			// 插入还款流水
			saveRepayFlow(requestParamMap, userProfile);
			AccRepayDgReg accRepayDgReg = new AccRepayDgReg();
			accRepayDgReg = JSON.parseObject(JSON.toJSONString(requestParamMap), AccRepayDgReg.class);
			AccCustAndBankBo cb = caseService.getCustAndBank(String.valueOf(requestParamMap.get("loanNo")));
			accRepayDgReg.setCustAcct(cb.getAcctNo());
			accRepayDgReg.setAcctName(cb.getAcctName());
			accRepayDgRegService.insert(accRepayDgReg);
			// 提前结清登记表
			accRepayAdvanReg = new AccRepayAdvanReg();
			accRepayAdvanReg.setId(RandomUtil.getUUID());
			accRepayAdvanReg.setLoanNo(loanNo);
			accRepayAdvanReg.setRepayDate(accRepayDgReg.getRepayDate());
			accRepayAdvanReg.setRepayNum(accRepayDgReg.getRepayNum());
			accRepayAdvanReg.setTranDate(new Date());
			accRepayAdvanReg.setTranAmt(setlAmt);
			accRepayAdvanReg.setCustName(cb.getAcctName());
			accRepayAdvanReg.setRepayChan(PubBusinessConstant.ZJ_SIGLE);
			accRepayAdvanReg.setConfNo(PubBusinessConstant.PLAN_REPAY_TYPE_OVER);
			accRepayAdvanReg.setCustAcct(cb.getAcctNo());
			accRepayAdvanReg.setStat(PubBusinessConstant.REPAY_DG_STATUS_PAIED);
			accRepayAdvanReg.setTranDesc("委外结清");
			accRepayAdvanReg.setTranStaff(userProfile.getStaffNo());
			accRepayAdvanReg.setTranOrg(userProfile.getOrgNo());
			accRepayAdvanReg.setInstDate(new Date());
			accRepayAdvanReg.setSetlType(FinanceConstant.CLEARN_TYPE_WW);
		} catch (Exception e) {
			e.printStackTrace();
			overStat = 0;
			throw new ServiceException("登记对公还款失败," + e.getMessage());
		} finally {
			if (overStat == 0) { // 还款失败
				paramMap.put("instStat", 20101001);
				paramMap.put("withStat", 20109004);
				paramMap.put("repayNum", repayNum);
				accLoanInstService.updateInstStat(paramMap);
				accCapWithService.updateWithStat(paramMap);
			} else if (overStat == 1) { // 部分还款成功
				Map<String, Object> accAmtMap = new HashMap<String, Object>();
				accAmtMap.put("loanNo", loanNo);
				accAmtMap.put("repayNum", repayNum);
				accAmtMap.put("updt", new Date());
				accAmtMap.put("setlAmt", 0);
				paramMap.put("withStat", 20109003);
				paramMap.put("repayNum", repayNum);
				// 扣除预处理提前结清金额
				accLoanInstService.updateAccLoanInstSetlAmtAndUnLock(accAmtMap);
				accCapWithService.updateWithStat(paramMap);
				Map<String, Object> outMap = new HashMap<>();
				outMap.put("loanNo", loanNo);
				outMap.put("debtAmt", tranAmt);
				plLoanOutsourceService.updateIsSettle(outMap);
			} else { // 完全还款
				try {
					accLoanAcctInstService.updateAccLoanAcctStat(loanNo, -1, FinanceConstant.PRETREAT_STAT_CLEARD);
					paramMap.put("withStat", 20109003);
					paramMap.put("repayNum", repayNum);
					accCapWithService.updateWithStat(paramMap);
					Map<String, Object> outMap = new HashMap<>();
					outMap.put("loanNo", loanNo);
					outMap.put("isSettle", "1");
					outMap.put("debtAmt", tranAmt);
					plLoanOutsourceService.updateIsSettle(outMap);
					if (accRepayAdvanReg != null) {
						accRepayAdvanRegService.insert(accRepayAdvanReg);
					}
				} catch (Exception e2) {
					// TODO: handle exception
					throw new ServiceException(e2.getMessage());
				}
			}
		}
		PlLoanOutsourceReturn plLoanOutsourceReturn = new PlLoanOutsourceReturn();
		plLoanOutsourceReturn.setId(RandomUtil.getUUID());
		plLoanOutsourceReturn.setInstDate(DateUtils.getCurrentDate());
		plLoanOutsourceReturn.setLoanNo(loanNo);
		plLoanOutsourceReturn.setPayAmt(tranAmt);
		plLoanOutsourceReturn.setPayTyp("20101201");
		Map<String, Object> outmap = new HashMap<>();
		outmap.put("loanNo", loanNo);
		outmap.put("stat", CommonConstant.STAT_ENABLE);
		List<PlLoanOutsourceRelation> list = plLoanOutsourceRelationService.queryForList(outmap);
		if (list == null || list.size() == 0) {
			throw new com.hs.base.exception.ServiceException("获取委外案件分配信息失败");
		}
		plLoanOutsourceReturn.setUnitName(list.get(0).getUnitName());
		plLoanOutsourceReturn.setUnitNo(list.get(0).getUnitNo());
		plLoanOutsourceReturn.setRelId(list.get(0).getId());
		lLoanOutsourceReturnService.insert(plLoanOutsourceReturn);
	}

	@Transactional
	private void saveRepayFlow(HashMap<String, Object> m, UserProfile userProfile) throws Exception {
		AccCustAndBankBo cb = caseService.getCustAndBank(String.valueOf(m.get("loanNo")));
		AccRepayFlow flowVO = new AccRepayFlow();
		// 设置交易方
		String tranObj = (null == m.get("tranObj")) ? null : m.get("tranObj").toString().trim();
		if (null != tranObj) {
			flowVO.setTranObj(tranObj);
		}
		flowVO.setId(RandomUtil.getUUID());
		// 贷款编号
		// flowVO.setLoanNo(repayDgDto.getLoanNo());
		flowVO.setLoanNo(m.get("loanNo").toString());
		// 账单日
		// flowVO.setRepayDate(repayDgDto.getRepayDate());
		flowVO.setRepayDate(m.get("repayDate").toString());
		// 期数
		// flowVO.setRepayNum(repayDgDto.getRepayNum());
		flowVO.setRepayNum(Integer.valueOf(m.get("repayNum").toString()));
		// 交易日期
		flowVO.setTranDate(DateUtils.getCurDate());
		// 交易类型
		flowVO.setTranType(PubBusinessConstant.TRANDTYPE_ZC);
		// 交易金额
		// flowVO.setTranAmt(repayDgDto.getTranAmt());
		flowVO.setTranAmt(BigDecimal.valueOf(Double.valueOf(m.get("tranAmt").toString())));
		// 是否结算：否
		flowVO.setSetlFlag(CommonConstant.COMMON_NO);
		// 创建日期
		flowVO.setInstDate(DateUtils.getCurrentTimestamp());
		// 交易渠道
		// flowVO.setTranChan(repayDgDto.getTranType());
		flowVO.setTranChan(m.get("tranType").toString());
		// 还款账户
		flowVO.setAcctNo(cb.getAcctNo());
		// 还款户名
		flowVO.setAcctName(cb.getAcctName());
		// 交易状态：当前日期与账单日比较，如果
		flowVO.setLoanStat("20101430");
		// 发起机构
		String orgId = userProfile.getOrgNo();
		flowVO.setTranOrg(orgId);
		// 经办人
		flowVO.setTranStaff(userProfile.getStaffName());
		// 支付宝
		if (PubBusinessConstant.TRANCHAN_ZHIFUBO.equals(String.valueOf(m.get("tranType")))) {
			// 转入账号
			flowVO.setCntAcctNo(ParamUtils.getParam("compZfb"));
			// 转入户名
			flowVO.setCntAcctName("深圳柠檬金服支付宝账户");
		} else { // 现金还款，对公还款
			// 转入账号
			flowVO.setCntAcctNo(ParamUtils.getParam("compAcctNo"));
			// 转入户名
			flowVO.setCntAcctName("深圳柠檬金融信息服务有限公司");
		}
		accRepayFlowService.insert(flowVO);
	}

	/**
	 * 委外分配代扣
	 * 
	 * @param loanNo
	 * @param amt
	 * @param profile
	 */
	@Transactional
	public void repayAmt(String loanNo, BigDecimal amt, UserProfile userProFile, String id, String kdType,
			String repayNum) throws ServiceException, AppException {
		if (kdType.equals(PayChanType.ZJPAY.toString())) {
			if (accCapWithService.capWithSize(1, loanNo)) {
				throw new ServiceException("该笔贷款当日扣款失败次数超出限制");
			}
			zjpay(loanNo, amt, userProFile, id, repayNum,kdType);
		}if (kdType.equals(PayChanType.LIANLIANPAY.toString())) {
			if (accCapWithService.capWithSize(3, loanNo)) {
				throw new ServiceException("该笔贷款当日扣款失败次数超出限制");
			}
			zjpay(loanNo, amt, userProFile, id, repayNum,kdType);
		} else if (kdType.equals(PayChanType.LYCHPAY.toString())) {
			if (accCapWithService.capWithSize(2, loanNo)) {
				throw new ServiceException("该笔贷款当日扣款失败次数超出限制");
			}
			kftpay(loanNo, amt, userProFile, id, repayNum);
		}

	}

	/**
	 * 快付通
	 * 
	 * @param loanNo
	 * @param amt
	 * @param userProFile
	 * @param id
	 * @param repayNum
	 */
	@Transactional
	private void kftpay(String loanNo, BigDecimal amt, UserProfile userProFile, String id, String repayNum)
			throws ServiceException, AppException {

		try {
			Map<String, Object> amtMap = new HashMap<>();
			amtMap.put("loanNo", loanNo);
			amtMap.put("repayNum", repayNum);
			HashMap<String, Object> map = plLoanOutsourceService.getcurRvcAmtByLoanNo(amtMap);
			BigDecimal rs = (null == map.get("CUR_RCV_AMT")) ? null
					: BigDecimal.valueOf(Double.valueOf(map.get("CUR_RCV_AMT").toString().trim()));
			AccCustAndBankBo cb = caseService.getCustAndBank(loanNo);
			// 锁表 锁预处理表
			String returnStatus = accLoanAcctInstService.updateAccLoanAcctStatusLock(loanNo,
					Integer.parseInt(repayNum));
			if (!returnStatus.equals(FinanceConstant.SUCC)) {
				throw new ServiceException("代扣数据［预处理信息ACC_LOAN_ACCT_INST］锁表失败");
			}
			AccPayWith accPayWith = new AccPayWith();
			accPayWith.setId(RandomUtil.getUUID());
			accPayWith.setLoanNo(loanNo);
			accPayWith.setAcctNo(cb.getAcctNo());
			accPayWith.setAcctName(cb.getAcctName());
			accPayWith.setOpenBank(cb.getBankNo());
			accPayWith.setCertNo(cb.getCertNo());
			accPayWith.setCurRcvAmt(amt);
			accPayWith.setBgnRepayNum(Integer.parseInt(repayNum));
			accPayWith.setEndRepayNum(Integer.parseInt(repayNum));
			accPayWith.setWithAmt(rs);
			accPayWith.setStat(FinanceConstant.WITHSTAT_UNDK);
			accPayWith.setRepayType(PubBusinessConstant.REPAY_TYPE_DUE_ENTURST);
			accPayWith.setInstDate(new Date());
			accPayWith.setCompanyType("0");
			accPayWithService.accPayReg(accPayWith);
			// 代扣表状态扣款中
			HashMap<String, Object> paramMap = new HashMap<>();
			paramMap.put("loanNo", loanNo);
			paramMap.put("withStat", 20109002);
			paramMap.put("repayNum", repayNum);
			accCapWithService.updateWithStat(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("扣款失败");
		}

	}

	/**
	 * 中金
	 * 
	 * @param loanNo
	 * @param amt
	 * @param userProFile
	 * @param id
	 * @param repayNum
	 */
	@Transactional
	private void zjpay(String loanNo, BigDecimal amt, UserProfile userProFile, String id, String repayNum,String kdType)
			throws ServiceException, AppException {
		// 基本信息判断
		if (org.apache.commons.lang.StringUtils.isBlank(loanNo)) {
			throw new ServiceException("贷款编号为空");
		}
		if (0 >= amt.intValue()) {
			throw new ServiceException("本次还款金额必须大于0");
		}
		// 通过贷款编号查询是否存在锁表
		Integer countByLoanNoInstStat = accLoanInstService.getCountByLoanNoInstStat(loanNo);
		if (null != countByLoanNoInstStat && 0 < countByLoanNoInstStat.intValue()) {
			throw new ServiceException("当前正在扣款中");
		}
		Map<String, Object> amtMap = new HashMap<>();
		amtMap.put("loanNo", loanNo);
		HashMap<String, Object> map = plLoanOutsourceService.getcurRvcAmtByLoanNo(amtMap);
		BigDecimal rsAmt = (null == map.get("CUR_RCV_AMT")) ? null
				: BigDecimal.valueOf(Double.valueOf(map.get("CUR_RCV_AMT").toString().trim()));
		BigDecimal setlAmt = (null == map.get("SETL_AMT")) ? null
				: BigDecimal.valueOf(Double.valueOf(map.get("SETL_AMT").toString().trim()));
		if (null == rsAmt || rsAmt.intValue() <= 0) {
			throw new ServiceException("已经结清");
		}
		repayNum = map.get("REPAY_NUM").toString();
		String repayDate = map.get("REPAY_DATE").toString();

		HashMap<String, Object> paramMap = new HashMap<>();
		// 贷款编号
		paramMap.put("loanNo", loanNo);
		// 锁表状态
		paramMap.put("instStat", PubBusinessConstant.PRODSTAT_SALEING);
		// 处理表中的状态扣款中
		paramMap.put("withStat", 20109002);
		paramMap.put("repayNum", repayNum);
		HashMap<String, Object> chanNoMap = new HashMap<>();
		chanNoMap.put("loanNo", loanNo);
		String chanNo = accCapWithService.getChanNoByMap(chanNoMap);
		int overStat = 0;
		AccRepayAdvanReg accRepayAdvanReg = null;
		String tranChan ="";
		if (kdType.equals(PayChanType.ZJPAY.toString())) {
			tranChan = PubBusinessConstant.ZJ_SIGLE;
		}else if (kdType.equals(PayChanType.LIANLIANPAY.toString())) {
			tranChan = PubBusinessConstant.LL_SIGLE;
		}
		try {
			// 锁表 锁预处理表
			String returnStatus = accLoanAcctInstService.updateAccLoanAcctStatusLock(loanNo,
					Integer.parseInt(repayNum));
			if (!returnStatus.equals(FinanceConstant.SUCC)) {
				throw new ServiceException("代扣数据［预处理信息ACC_LOAN_ACCT_INST］锁表失败");
			}
			// 代扣表中的状态(扣款中)
			Integer updateWithStat = accCapWithService.updateWithStat(paramMap);
			if (null == updateWithStat) {
				throw new ServiceException("更新扣款中状态失败");
			}
			AccWithholdReg reg = new AccWithholdReg();
			AccCustAndBankBo cb = caseService.getCustAndBank(loanNo);
			// 代扣组装数据
			SingleOtherBusiBo singleBo = new SingleOtherBusiBo();
			// 贷款编号
			singleBo.setLoanNo(loanNo);
			singleBo.setCertNo(cb.getCertNo());
			singleBo.setChanNo(chanNo);
			singleBo.setTranType(kdType);
			singleBo.setId(id);
			singleBo.setBankNo(cb.getBankNo());
			singleBo.setBankName(cb.getBankName());
			singleBo.setAcctName(cb.getAcctName());
			singleBo.setAcctNo(cb.getAcctNo());
			singleBo.setRemark("委外代扣");
			singleBo.setTransAmtTotal(amt);
			singleBo.setRepayNum(Integer.parseInt(repayNum));
			reg.setId(RandomUtil.getUUID());
			reg.setBgnNum(Integer.parseInt(repayNum));
			reg.setEndNum(Integer.parseInt(repayNum));
			reg.setStaffNo(userProFile.getStaffNo());
			reg.setStaffName(userProFile.getStaffName());
			reg.setOrgNo(userProFile.getOrgNo());
			reg.setBankNo(cb.getBankNo());
			reg.setAcctNum(cb.getAcctNo());
			reg.setAcctName(cb.getAcctName());
			reg.setLoanNo(loanNo);
			reg.setTranAmt(amt);
			reg.setRcvAmt(rsAmt);
			reg.setRepayDate(repayDate);
			reg.setTranBgnDate(new Date());
			reg.setTranTyp(PubBusinessConstant.REPAY_TYPE_DUE_ENTURST);
			reg.setTranChan(tranChan);
			SingleDkResultBo result = accCapWithService.singleRepayOtherBusi(singleBo, userProFile);
			// 扣款成功
			if (null != result.getRetItem().getRetCode()
					&& result.getRetItem().getRetCode().equals(FinanceConstant.TRAN_ST_SUCC)) {
				reg.setTxsn(result.getReqSn());
				reg.setWithStat(FinanceConstant.WITHSTAT_DKSUCC);
				reg.setTranEndDate(new Date());
				reg.setSettleStat("10001002");
				accWithholdRegService.insertAccWithholdReg(reg);
				AccRepayFlow accRepayFlow = new AccRepayFlow();
				accRepayFlow.setId(RandomUtil.getUUID());
				accRepayFlow.setLoanNo(loanNo);
				accRepayFlow.setRepayDate(repayDate);
				accRepayFlow.setTranType(PubBusinessConstant.TRANDTYPE_ZC);
				accRepayFlow.setTranChan(tranChan);
				accRepayFlow.setTranDate(DateUtils.getYear() + DateUtils.getMonth() + DateUtils.getDay());
				accRepayFlow.setTranAmt(amt);
				accRepayFlow.setAcctNo(cb.getAcctNo());
				accRepayFlow.setAcctName(cb.getAcctName());
				accRepayFlow.setLoanStat("20101430");
				accRepayFlow.setSetlFlag(CommonConstant.COMMON_NO);
				accRepayFlow.setInstDate(DateUtils.getCurrentTimestamp());
				accRepayFlow.setRepayNum(Integer.parseInt(repayNum));
				accRepayFlow.setTranOrg(userProFile.getOrgNo());
				accRepayFlow.setTranStaff(userProFile.getStaffName());
				accRepayFlow.setCntAcctNo(ParamUtils.getParam("compAcctNo"));
				accRepayFlow.setCntAcctName("深圳柠檬金融信息服务有限公司");
				accRepayFlowService.insert(accRepayFlow);
				// 提前结清登记表
				accRepayAdvanReg = new AccRepayAdvanReg();
				accRepayAdvanReg.setId(RandomUtil.getUUID());
				accRepayAdvanReg.setLoanNo(loanNo);
				accRepayAdvanReg.setRepayDate(repayDate);
				accRepayAdvanReg.setRepayNum(Integer.parseInt(repayNum));
				accRepayAdvanReg.setTranDate(new Date());
				accRepayAdvanReg.setTranAmt(setlAmt);
				accRepayAdvanReg.setCustName(cb.getAcctName());
				accRepayAdvanReg.setRepayChan(tranChan);
				accRepayAdvanReg.setConfNo(PubBusinessConstant.PLAN_REPAY_TYPE_OVER);
				accRepayAdvanReg.setCustAcct(cb.getAcctNo());
				accRepayAdvanReg.setStat(PubBusinessConstant.REPAY_DG_STATUS_PAIED);
				accRepayAdvanReg.setTranDesc("委外结清");
				accRepayAdvanReg.setTranStaff(userProFile.getStaffNo());
				accRepayAdvanReg.setTranOrg(userProFile.getOrgNo());
				accRepayAdvanReg.setInstDate(new Date());
				accRepayAdvanReg.setSetlType(FinanceConstant.CLEARN_TYPE_WW);
				if (rsAmt.compareTo(amt) == 0) {
					// 还完了
					overStat = 2;
				} else {
					overStat = 1;
				}
				// 新增经办登记
				String handDetailTyp = PubBusinessConstant.LOANHANDTYPE_OUTSOURCE;
				String handPersonNo = userProFile.getStaffNo();
				String handPersonName = userProFile.getStaffName();
				Date handDate = DateUtils.getCurrentDate();
				LoanAcctOutDto dto = colLoanAcctService.getLoanAcct(loanNo, userProFile);
				appLoanHandService.saveAppLoanHand(loanNo, dto.getCustNo(), dto.getCustName(), handDetailTyp,
						PubBusinessConstant.LOANHANDMODEL_SYS, handPersonNo, handPersonName, handDate, "委外案件还款",
						PubBusinessConstant.CUST_ZC);

				PlLoanOutsourceReturn plLoanOutsourceReturn = new PlLoanOutsourceReturn();
				plLoanOutsourceReturn.setId(RandomUtil.getUUID());
				plLoanOutsourceReturn.setInstDate(DateUtils.getCurrentDate());
				plLoanOutsourceReturn.setLoanNo(loanNo);
				plLoanOutsourceReturn.setPayAmt(amt);
				plLoanOutsourceReturn.setPayTyp("20101201");
				Map<String, Object> outmap = new HashMap<>();
				outmap.put("loanNo", loanNo);
				outmap.put("stat", CommonConstant.STAT_ENABLE);
				List<PlLoanOutsourceRelation> list = plLoanOutsourceRelationService.queryForList(outmap);
				if (list == null || list.size() == 0) {
					throw new com.hs.base.exception.ServiceException("获取委外案件分配信息失败");
				}
				plLoanOutsourceReturn.setUnitName(list.get(0).getUnitName());
				plLoanOutsourceReturn.setUnitNo(list.get(0).getUnitNo());
				plLoanOutsourceReturn.setRelId(list.get(0).getId());
				lLoanOutsourceReturnService.insert(plLoanOutsourceReturn);

			} else if (result.getRetItem() != null && result.getRetItem().getRetCode() != null
					&& result.getRetItem().getRetCode().equals(FinanceConstant.TRAN_ST_DEALING)) {
				reg.setTxsn(result.getReqSn());
				reg.setWithStat(FinanceConstant.WITHSTAT_DKING);
				reg.setSettleStat("10001002");
				accWithholdRegService.insertAccWithholdReg(reg);
				// 开启轮询
				// updateFallStat(loanNo, result.getReqSn(), userProFile, cb,
				// repayNum, amt, paramMap, repayDate, rsAmt);
				throw new ServiceException("正在扣款中...." + result.getRetItem().getErrMsg());
			} else {
				overStat = 0;
				reg.setTxsn(result.getReqSn());
				reg.setWithStat(FinanceConstant.WITHSTAT_DKFIELD);
				reg.setTranEndDate(new Date());
				reg.setSettleStat("10001002");
				accWithholdRegService.insertAccWithholdReg(reg);
				throw new ServiceException("扣款失败，" + result.getRetItem().getErrMsg());
			}

		} catch (Exception e) {
			e.printStackTrace();
			overStat = 0;
			throw new ServiceException(e.getMessage());
		} finally {
			if (overStat == 0) { // 还款失败
				paramMap.put("instStat", 20101001);
				paramMap.put("withStat", 20109004);
				paramMap.put("repayNum", repayNum);
				accLoanInstService.updateInstStat(paramMap);
				accCapWithService.updateWithStat(paramMap);
			} else if (overStat == 1) { // 部分还款成功
				Map<String, Object> accAmtMap = new HashMap<String, Object>();
				accAmtMap.put("loanNo", loanNo);
				accAmtMap.put("repayNum", repayNum);
				accAmtMap.put("updt", new Date());
				accAmtMap.put("setlAmt", 0);
				paramMap.put("withStat", 20109003);
				paramMap.put("repayNum", repayNum);
				// 扣除预处理提前结清金额
				accLoanInstService.updateAccLoanInstSetlAmtAndUnLock(accAmtMap);
				accCapWithService.updateWithStat(paramMap);
				Map<String, Object> outMap = new HashMap<>();
				outMap.put("loanNo", loanNo);
				outMap.put("debtAmt", amt);
				plLoanOutsourceService.updateIsSettle(outMap);
			} else { // 完全还款
				try {
					accLoanAcctInstService.updateAccLoanAcctStat(loanNo, -1, FinanceConstant.PRETREAT_STAT_CLEARD);
					paramMap.put("withStat", 20109003);
					paramMap.put("repayNum", repayNum);
					accCapWithService.updateWithStat(paramMap);
					Map<String, Object> outMap = new HashMap<>();
					outMap.put("loanNo", loanNo);
					outMap.put("isSettle", "1");
					outMap.put("debtAmt", amt);
					plLoanOutsourceService.updateIsSettle(outMap);
					if (accRepayAdvanReg != null) {
						accRepayAdvanRegService.insert(accRepayAdvanReg);
					} 
				} catch (Exception e2) {
					// TODO: handle exception
					throw new ServiceException(e2.getMessage());
				}
			}
		}
	}

	/**
	 * 轮询
	 * 
	 * @param loanNo
	 * @param reqSn
	 * @param userProFile
	 * @param cb
	 * @param repayNum
	 * @param amt
	 * @param paramMap
	 * @param repayDate
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void updateFallStat(final String loanNo, final String reqSn, final UserProfile userProFile,
			final AccCustAndBankBo cb, final String repayNum, final BigDecimal amt, final Map<String, Object> paramMap,
			final String repayDate, final BigDecimal rsAmt) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				int overStat = 0;
				if (rsAmt.compareTo(amt) == 0) {
					// 还完了
					overStat = 2;
				} else {
					overStat = 1;
				}
				try {
					// 轮询结果
					SingleDkResultBo result = accCapWithService.singleQueryStat(reqSn);
					if ((result == null) || (result.getRetItem() != null && result.getRetItem().getRetCode() != null
							&& result.getRetItem().getRetCode().equals(FinanceConstant.TRAN_ST_DEALING))) {
						// 依然还在代扣中 不做任何处理 继续轮询

					} else if (result != null
							&& result.getRetItem().getRetCode().equals(FinanceConstant.TRAN_ST_SUCC)) { // 代扣成功
						boolean successFlg = false;
						Map<String, Object> accAmtMap = new HashMap<String, Object>();
						try {
							// 成功后修改预处理表金额 和加流水
							AccRepayFlow accRepayFlow = new AccRepayFlow();
							accRepayFlow.setId(RandomUtil.getUUID());
							accRepayFlow.setLoanNo(loanNo);
							accRepayFlow.setRepayDate(repayDate);
							accRepayFlow.setTranType(PubBusinessConstant.TRANDTYPE_ZC);
							accRepayFlow.setTranChan(PubBusinessConstant.ZJ_SIGLE);
							accRepayFlow.setTranDate(DateUtils.getYear() + DateUtils.getMonth() + DateUtils.getDay());
							accRepayFlow.setAcctNo(cb.getAcctNo());
							accRepayFlow.setAcctName(cb.getAcctName());
							accRepayFlow.setLoanStat("20101430");
							accRepayFlow.setSetlFlag(CommonConstant.COMMON_NO);
							accRepayFlow.setInstDate(DateUtils.getCurrentTimestamp());
							accRepayFlow.setRepayNum(Integer.parseInt(repayNum));
							accRepayFlow.setCntAcctNo(ParamUtils.getParam("compAcctNo"));
							accRepayFlow.setCntAcctName("深圳柠檬金融信息服务有限公司");
							accRepayFlow.setTranOrg(userProFile.getOrgNo());
							accRepayFlow.setTranStaff(userProFile.getStaffName());
							accRepayFlow.setTranAmt(amt);
							accRepayFlowService.insert(accRepayFlow);
							// 更新预处理表
							accAmtMap.put("loanNo", loanNo);
							accAmtMap.put("repayNum", repayNum);
							accAmtMap.put("updt", new Date());
							accAmtMap.put("setlAmt", amt);

							// 新增经办登记
							String handDetailTyp = PubBusinessConstant.LOANHANDTYPE_OUTSOURCE;
							String handPersonNo = userProFile.getStaffNo();
							String handPersonName = userProFile.getStaffName();
							Date handDate = DateUtils.getCurrentDate();
							LoanAcctOutDto dto = colLoanAcctService.getLoanAcct(loanNo, userProFile);
							appLoanHandService.saveAppLoanHand(loanNo, dto.getCustNo(), dto.getCustName(),
									handDetailTyp, PubBusinessConstant.LOANHANDMODEL_SYS, handPersonNo, handPersonName,
									handDate, "委外案件还款", PubBusinessConstant.CUST_ZC);

							PlLoanOutsourceReturn plLoanOutsourceReturn = new PlLoanOutsourceReturn();
							plLoanOutsourceReturn.setId(RandomUtil.getUUID());
							plLoanOutsourceReturn.setInstDate(DateUtils.getCurrentDate());
							plLoanOutsourceReturn.setLoanNo(loanNo);
							plLoanOutsourceReturn.setPayAmt(amt);
							plLoanOutsourceReturn.setPayTyp("20101310");
							Map<String, Object> outmap = new HashMap<>();
							outmap.put("loanNo", loanNo);
							outmap.put("stat", CommonConstant.STAT_ENABLE);
							List<PlLoanOutsourceRelation> list = plLoanOutsourceRelationService.queryForList(outmap);
							if (list == null || list.size() == 0) {
								throw new com.hs.base.exception.ServiceException("获取委外案件分配信息失败");
							}
							plLoanOutsourceReturn.setUnitName(list.get(0).getUnitName());
							plLoanOutsourceReturn.setUnitNo(list.get(0).getUnitNo());
							plLoanOutsourceReturn.setRelId(list.get(0).getId());
							lLoanOutsourceReturnService.insert(plLoanOutsourceReturn);
							successFlg = true;
							this.cancel();
						} catch (Exception e) {
							e.printStackTrace();
							successFlg = false;
						} finally {
							if (successFlg) {
								if (overStat == 1) {
									// 扣除预处理提前结清金额
									accLoanInstService.updateAccLoanInstSetlAmtAndUnLock(accAmtMap);
									Map<String, Object> outMap = new HashMap<>();
									outMap.put("loanNo", loanNo);
									outMap.put("debtAmt", amt);
									plLoanOutsourceService.updateIsSettle(outMap);
								} else if (overStat == 2) {
									// 更新预处理表
									accLoanAcctInstService.updateAccLoanAcctStat(loanNo, -1,
											FinanceConstant.PRETREAT_STAT_CLEARD);
									Map<String, Object> outMap = new HashMap<>();
									outMap.put("loanNo", loanNo);
									outMap.put("isSettle", "1");
									outMap.put("debtAmt", amt);
									plLoanOutsourceService.updateIsSettle(outMap);
								}
								paramMap.put("withStat", 20109003);
								accCapWithService.updateWithStat(paramMap);
							} else {
								// 解表状态
								paramMap.put("instStat", 20101001);
								// 状态扣款失败
								paramMap.put("withStat", 20109004);
								accLoanInstService.updateInstStat(paramMap);
								accCapWithService.updateWithStat(paramMap);
							}
						}
						this.cancel();
					} else {
						// 解表状态
						paramMap.put("instStat", 20101001);
						// 状态扣款失败
						paramMap.put("withStat", 20109004);
						accLoanInstService.updateInstStat(paramMap);
						accCapWithService.updateWithStat(paramMap);
						this.cancel();
					}
				} catch (Exception e) {
					// 解表状态
					paramMap.put("instStat", 20101001);
					// 状态扣款失败
					paramMap.put("withStat", 20109004);
					accLoanInstService.updateInstStat(paramMap);
					accCapWithService.updateWithStat(paramMap);
					this.cancel();
					e.printStackTrace();
				}
			}
		}, 3 * 60 * 1000, 3 * 60 * 1000);
	}

	/**
	 * 委外代扣还款明细
	 * 
	 * @param Page<LoanOutsourceReturnDto>
	 *            page
	 * @return Page<LoanOutsourceReturnDto>
	 */
	@Override
	public Page<LoanOutsourceReturnDto> queryOutSourceRet(Page<LoanOutsourceReturnDto> page) {
		Page<PlLoanOutsourceReturn> page1 = lLoanOutsourceReturnService
				.queryForPage(page.toPage(PlLoanOutsourceReturn.class));
		return page1.toPage(LoanOutsourceReturnDto.class);
	}

	@Override
	public Page<LoanOutsourceRelationDto> queryOutSourceHis(Page<LoanOutsourceRelationDto> page) {

		String loanNo = (String) page.getPageParams().get("loanNo");
		if (StringUtils.isEmpty(loanNo)) {
			throw new com.hs.base.exception.ServiceException("分期编号为空");
		}
		Page<LoanOutsourceRelationBo> page1 = plLoanOutsourceRelationService
				.queryOutsourceRelationForPage(page.toPage(LoanOutsourceRelationBo.class));
		return page1.toPage(LoanOutsourceRelationDto.class);
	}

	/**
	 * 分页查询委外信息
	 * 
	 * @param page
	 * @param profile
	 * @return
	 */
	@Override
	public Page<LoanOutsourceDto> queryLoanSource(Page<LoanOutsourceDto> page, UserProfile profile) {
		Page<LoanOutsourceBo> retpage = plLoanOutsourceService
				.queryOutSourceForPage(page.toPage(LoanOutsourceBo.class));
		return retpage.toPage(LoanOutsourceDto.class);
	}

	/**
	 * 委外案件处理 （调用经办信息）
	 * 
	 * @param remark
	 * @param profile
	 * @throws ServiceException
	 * @throws AppException
	 **/
	@Override
	@Transactional
	public void detailOutLoanCase(String loanNo, String caseId, String remark, UserProfile profile) {

		LoanAcctOutDto dto = colLoanAcctService.getLoanAcct(loanNo, profile);
		if (dto == null) {
			throw new com.hs.base.exception.ServiceException("获取分期信息失败");
		}
		// 经办信息
		appLoanHandService.saveAppLoanHand(loanNo, dto.getCustNo(), dto.getCustName(),
				PubBusinessConstant.LOANHANDTYPE_OUTSOURCE, PubBusinessConstant.LOANHANDMODEL_HAND,
				profile.getStaffNo(), profile.getStaffName(), DateUtils.getCurrentTimestamp(), remark,
				PubBusinessConstant.CUST_ZC);

	}

	/**
	 * 委外分配分页查询
	 * 
	 * @param page
	 * @param profile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	@Override
	public List<Map<String, Object>> queryOutSourceForExp(Map<String, Object> param, UserProfile profile) {
		String staffNo = profile.getStaffNo();
		if (org.apache.commons.lang3.StringUtils.isBlank(staffNo)) {
			throw new com.hs.base.exception.ServiceException("员工编号为空");
		}
		// 员工角色集合
		List<String> roleList = plLoanOutsourceService.selectRoleByStaffNo(staffNo);
		boolean isAuth = false;
		for (String s : roleList) {
			// admin 委外专员 委外公司
			if (org.apache.commons.lang3.StringUtils.equals(s, "r_sys_super")
					|| org.apache.commons.lang3.StringUtils.equals(s, "r_collec_out_comp")
					|| org.apache.commons.lang3.StringUtils.equals(s, "r_collec_out_staff")) {
				isAuth = true;
			}
			// 委外公司只能查看自己公司的
			if (org.apache.commons.lang3.StringUtils.equals(s, "r_collec_out_comp")) {
				param.put("outComp", profile.getStaffNo());
			}
		}
		if (!isAuth) {
			throw new com.hs.base.exception.ServiceException("没有权限导出");
		}
		List<LoanOutsourceBo> lst = plLoanOutsourceService.queryOutSourceForList(param);
		List<Map<String, Object>> retlst = new ArrayList<>();
		for (LoanOutsourceBo bo : lst) {
			bo.setSex(SimpleCodeUtils.getNameByCode("sex"));
			retlst.add(BeanUtils.bean2map(bo));
		}
		return retlst;
	}

}
