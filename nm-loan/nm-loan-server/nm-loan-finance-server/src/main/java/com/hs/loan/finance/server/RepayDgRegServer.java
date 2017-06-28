package com.hs.loan.finance.server;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.CommonConstant;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.loan.finance.api.RepayDgRegApi;
import com.hs.loan.finance.bo.AccLoanAcctInstBo;
import com.hs.loan.finance.contant.FinanceConstant;
import com.hs.loan.finance.dto.AccLoanAcctInstDgDto;
import com.hs.loan.finance.dto.AccRepayDgRegDto;
import com.hs.loan.finance.entity.AccRepayDgReg;
import com.hs.loan.finance.entity.AccRepayFlow;
import com.hs.loan.finance.service.AccCapWithService;
import com.hs.loan.finance.service.AccLoanAcctInstService;
import com.hs.loan.finance.service.AccRepayDgRegService;
import com.hs.loan.finance.service.AccRepayFlowService;
import com.hs.loan.finance.util.CompAmtUtil;
import com.hs.loan.finance.util.PayChanType;
import com.hs.loan.finance.util.RepayUtil;
import com.hs.loan.pub.hand.service.AppLoanHandService;
import com.hs.loan.sale.entity.AppLoanAcct;
import com.hs.loan.sale.service.AppLoanAcctService;
import com.hs.utils.BeanUtils;
import com.hs.utils.DateUtils;
import com.hs.utils.ParamUtils;
import com.hs.utils.RandomUtil;

/**
 * 对公还款
 * 
 * @author zym
 *
 */
@Service
@Transactional(readOnly = true)
public class RepayDgRegServer implements RepayDgRegApi {

	@Autowired
	private AccRepayDgRegService accRepayDgRegService;
	@Autowired
	private AppLoanAcctService appLoanAcctService;
	@Autowired
	private AccLoanAcctInstService accLoanAcctInstService;
	@Autowired
	private AppLoanHandService appLoanHandService;
	@Autowired
	private AccCapWithService accCapWithService;

	/**
	 * 变更还款日期
	 * 
	 * @param paramMap
	 * @param userProfile
	 * @return HashMap<String,Object>
	 * @throws ServiceException
	 * @throws AppException
	 */
	@Transactional
	public HashMap<String, Object> changeFlexibleRepaymentDate(HashMap<String, Object> paramMap,
			UserProfile userProfile) throws ServiceException, AppException {
		// 贷款编号
		String loanNo = (null == paramMap.get("loanNo") || "".equals(paramMap.get("loanNo").toString().trim())) ? null
				: paramMap.get("loanNo").toString().trim();
		if (null == loanNo) {
			throw new ServiceException("贷款编号不能为空");
		}
		// 改变的日期
		String changeDate = (null == paramMap.get("changeDate")
				|| "".equals(paramMap.get("changeDate").toString().trim())) ? null
						: paramMap.get("changeDate").toString().trim();
		if (null == changeDate) {
			throw new ServiceException("变更日期不能为空");
		}
		boolean numeric = StringUtils.isNumeric(changeDate);
		if (!numeric) {
			throw new ServiceException("变更日期不是一个数字");
		}
		Integer integer = Integer.valueOf(changeDate);
		HashMap<String, Object> map = flexiblePaymentHistory(paramMap);
		Integer mthRepayDate = Integer.valueOf(map.get("mthRepayDate").toString().trim());
		if (integer < mthRepayDate || integer > 28) {
			throw new ServiceException("变更日期不能小于当前还款日或者大于28");
		}
		Page<HashMap<String, Object>> page = new Page<>();
		page.getPageParams().put("loanNo", loanNo);
		Page<HashMap<String, Object>> hashMapPage = flexibleNotYetDetail(page);
		// 最小的一期
		Integer minRepayNum = (null == hashMapPage.getList().get(0).get("repayNum")
				|| "".equals(hashMapPage.getList().get(0).get("repayNum").toString().trim())) ? null
						: Integer.valueOf(hashMapPage.getList().get(0).get("repayNum").toString().trim());
		Date lateDate = null;
		try {
			lateDate = org.apache.commons.lang.time.DateUtils.parseDate(
					hashMapPage.getList().get(0).get("repayDate").toString().trim(), new String[] { "yyyyMMdd" });
			Date date = org.apache.commons.lang.time.DateUtils.addDays(lateDate, -15);
			if (date.getTime() <= new Date().getTime()) {
				throw new ServiceException("申请日在还款日之前15天");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int over = accLoanAcctInstService.isOver(paramMap);
		if (over > 0) {
			throw new ServiceException("已经逾期不能变更");
		}
		HashMap<String, Object> changeCountMap = accLoanAcctInstService.changeCount(paramMap);
		Integer changeCount = (null == changeCountMap.get("changeCount")
				|| "".equals(changeCountMap.get("changeCount").toString().trim())) ? null
						: Integer.valueOf(changeCountMap.get("changeCount").toString().trim());
		if (null != changeCountMap && null != changeCount && changeCount.intValue() > 0) {
			throw new ServiceException("已经变更过一次不能再次变更");
		}
		int lock = accLoanAcctInstService.isLock(paramMap);
		if (lock > 0) {
			throw new ServiceException("该笔贷款已经锁表或者还清");
		}
		HashMap<String, Object> appFeeAdjuRegMap = new HashMap<>();
		appFeeAdjuRegMap.put("id", RandomUtil.getUUID());
		appFeeAdjuRegMap.put("loanNo", loanNo);
		appFeeAdjuRegMap.put("adjuRepayDate", changeDate);
		appFeeAdjuRegMap.put("repayNum", minRepayNum);
		appFeeAdjuRegMap.put("adjuType", "30411002");
		appFeeAdjuRegMap.put("tranStaff", userProfile.getStaffName());
		appFeeAdjuRegMap.put("stat", "10002001");
		int insertAppFeeAdjuReg = accLoanAcctInstService.insertAppFeeAdjuReg(appFeeAdjuRegMap);
		HashMap<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", insertAppFeeAdjuReg);
		return resultMap;
	}

	/**
	 * 随心还款历史
	 * 
	 * @param paramMap
	 * @return HashMap<String,Object>
	 */
	public HashMap<String, Object> flexiblePaymentHistory(HashMap<String, Object> paramMap)
			throws ServiceException, AppException {
		if (null == paramMap.get("loanNo")) {
			throw new ServiceException("贷款编号不能为空");
		}
		return appLoanAcctService.flexiblePaymentHistory(paramMap);
	}

	/**
	 * 随心还款
	 * 
	 * @param page
	 * @param userProfile
	 * @return Page<HashMap<String,Object>>
	 */
	public Page<HashMap<String, Object>> flexiblePayment(Page<HashMap<String, Object>> page, UserProfile userProfile) {
		Map<String, Object> pageParams = page.getParams();
		String fstReapyDate = null;
		try {
			fstReapyDate = (null == pageParams.get("fstRepayDate")
					|| "".equals(pageParams.get("fstRepayDate").toString().trim()))
							? null
							: org.apache.commons.lang.time.DateFormatUtils.format(
									org.apache.commons.lang.time.DateUtils.parseDate(
											pageParams.get("fstRepayDate").toString(), new String[] { "yyyy-MM-dd" }),
									"yyyyMMdd");
			pageParams.put("fstRepayDate", fstReapyDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return appLoanAcctService.flexiblePayment(page, userProfile);
	}

	public Page<HashMap<String, Object>> flexibleNotYetDetail(Page<HashMap<String, Object>> page) {
		return accLoanAcctInstService.flexibleNotYetDetail(page);
	}

	public int selectCountExcel(HashMap<String, Object> map) {
		return accRepayDgRegService.selectCountExcel(map);
	}

	/**
	 * 对公还款登记列表查询
	 */
	@Override
	public Page<AccRepayDgRegDto> queryLoanRepayDgLst(Page<AccRepayDgRegDto> page)
			throws ServiceException, AppException {
		page = accRepayDgRegService.queryForPageList(page.toPage(AccRepayDgReg.class)).toPage(AccRepayDgRegDto.class);
		return page;
	}

	@Override
	public List<AccLoanAcctInstDgDto> queryAccLoanAcctInstLst(Map<String, Object> params)
			throws ServiceException, AppException {
		// 查询贷款月还款日
		params.put("stat", PubBusinessConstant.LOANSTAT_SIGNING);
		List<AppLoanAcct> loanLst = appLoanAcctService.queryLoan(params);
		AppLoanAcct loan = null;
		if (loanLst.size() > 0 && loanLst.size() != 1) {
			throw new ServiceException("当前客户有多笔贷款，请使用贷款编号查询");
		} else if (loanLst.size() > 0) {
			loan = loanLst.get(0);
		}
		if (loan != null) {
			int count = accRepayDgRegService.selectOutSourceCase(loan.getLoanNo());
			if (count > 0) {
				throw new ServiceException("当前贷款已经进入委外，不能再进行对公还款登记");
			}

			params.put("instStat", FinanceConstant.PRETREAT_STAT_UNLOCK);
			List<AccLoanAcctInstBo> list = accLoanAcctInstService.queryAccLoanAcctInstDg(params);

			if (list == null || list.size() == 0) {
				throw new ServiceException("当前贷款的所有期数已结清或尚未跑借贷明细或正在扣款中，稍后再试");
			}
			return BeanUtils.copyProperties(list, AccLoanAcctInstDgDto.class);
		} else {
			throw new ServiceException("贷款不存在，请核对。");
		}

	}

	@Override
	public AccRepayDgRegDto loadLoanRepayDgInfo(AccRepayDgRegDto repayDgDto) throws ServiceException {
		if (null == repayDgDto) {
			throw new ServiceException("数据信息错误，请关闭页面重新操作！");
		}
		// 还款金额计算
		BigDecimal dgTransBal = CompAmtUtil.getDgTransBal(repayDgDto.getLoanNo(), repayDgDto.getRepayNum(),
				repayDgDto.getDgType());
		repayDgDto.setTotlAmt(dgTransBal);
		return repayDgDto;
	}

	/**
	 * 保存对公信息
	 * @param repayDgDto
	 * @return
	 * @throws ServiceException
	 */
	@Transactional
	public void saveLoanRepayDg(AccRepayDgRegDto repayDgDto, UserProfile userProfile) throws ServiceException {
		boolean succFlag = false;
		String loanNo = repayDgDto.getLoanNo();
		Integer repayNum = repayDgDto.getRepayNum();
		Map<String, Object> accAmtMap = new HashMap<String, Object>();
		accAmtMap.put("loanNo", loanNo);
		accAmtMap.put("repayNum", repayNum);
		accAmtMap.put("curRcvAmt", repayDgDto.getTranAmt());
		accAmtMap.put("updt", new Date());
		try {
			// 还款金额计算
			BigDecimal dgTransBal = CompAmtUtil.getDgTransBal(loanNo, repayNum, PubBusinessConstant.REPAY_TYPE_NOM);
			// 应还金额
			repayDgDto.setTotlAmt(dgTransBal);
			/*
			 * // 判断是否已经锁表，如果锁定直接抛出异常 BigDecimal status =
			 * accLoanAcctInstService.queryAccLoanAcctStatus(loanNo, repayNum);
			 * if (status == null || status.compareTo(BigDecimal.ZERO) != 0) {
			 * throw new ServiceException("当前不可进行此操作"); }
			 */

			if (repayDgDto.getTranAmt() == null || new BigDecimal(0).compareTo(repayDgDto.getTranAmt()) == 0
					|| new BigDecimal(0).compareTo(repayDgDto.getTranAmt()) == 1) {
				throw new ServiceException("交易金额不正确");
			}

			// 锁表
			accLoanAcctInstService.updateAccLoanAcctStatusLock(loanNo, repayNum);

			// 主键
			repayDgDto.setId(RandomUtil.getUUID());
			// 插入时间
			repayDgDto.setInstDate(DateUtils.getCurrentTimestamp());

			// 插入用户ID
			repayDgDto.setTranStaff(userProfile.getStaffName());
			repayDgDto.setTranOrg(userProfile.getOrgNo());

			// 如果状态为已还，则将修改记录时间，并保存还款流水
			String dgstatus = repayDgDto.getStat();

			if (dgstatus.equals(PubBusinessConstant.REPAY_DG_STATUS_PAIED)) {
				// 保存还款流水
				saveRepayFlow(repayDgDto, userProfile);
			}
			AccRepayDgReg accRepayDgReg = new AccRepayDgReg();
			BeanUtils.copyPropertiesNotForce(accRepayDgReg, repayDgDto);
			if (repayDgDto.getTranDate()==null) {
				throw new ServiceException("到账时间为空");
			}
			accRepayDgReg.setTranDate(repayDgDto.getTranDate());
			accRepayDgRegService.insert(accRepayDgReg);
			// 更新预处理表
			accLoanAcctInstService.updateAccLoanInstCurRcvAmtAndUnLock(accAmtMap);

			if (dgTransBal.compareTo(repayDgDto.getTranAmt()) == 0
					|| dgTransBal.compareTo(repayDgDto.getTranAmt()) == -1) {
				// 更新代扣信息
				accAmtMap.put("loanNo", loanNo);
				accAmtMap.put("repayNum", repayNum);
				accAmtMap.put("withStat", FinanceConstant.WITHSTAT_DKSUCC);
				accAmtMap.put("updtDate", DateUtils.getCurDateTime());
				accAmtMap.put("_desc", "对公还款");
				accCapWithService.updateAccCapWithByStatByLoanNo(accAmtMap);
			}

			// 新增经办信息
			appLoanHandService.saveAppLoanHand(loanNo, "-", repayDgDto.getAcctName(),
					PubBusinessConstant.LOANHANDTYPE_DGREPAY, PubBusinessConstant.LOANHANDMODEL_SYS,
					userProfile.getStaffNo(), userProfile.getStaffName(), DateUtils.getCurrentTimestamp(),
					"分期对公支付宝还款,:-" + repayDgDto.getTranDesc(), PubBusinessConstant.CUST_ZC);

			succFlag = true;
		} catch (ServiceException e) {
			succFlag = false;
			throw new ServiceException("对公还款失败:" + e.getMessage());
		} finally {
			if (!succFlag) {
				// 解锁
				accLoanAcctInstService.updateAccLoanAcctStatusUnLock(loanNo, repayNum);
			}
		}
	}

	/**
	 * 保存还款流水
	 * 
	 * @param flowVO
	 *            //预处理VO
	 * @param merInfo
	 *            //银联商户信息
	 * @param transSum
	 *            //交易摘要
	 * @param transItem
	 *            //交易明细
	 * @param retCode
	 *            //返回码
	 * @throws ServiceException
	 */
	@Autowired
	AccRepayFlowService accRepayFlowService;

	private String saveRepayFlow(AccRepayDgRegDto repayDgDto, UserProfile userProfile) throws ServiceException {
		AccRepayFlow flowVO = new AccRepayFlow();
		if (PayChanType.ALLINPAY.toString().equals(repayDgDto.getTranType())) { // 通联
			// 交易渠道
			flowVO.setTranChan(PubBusinessConstant.ALINPAY_SIGLE);
		} else if (PayChanType.CHINAPAY.toString().equals(repayDgDto.getTranType())) { // 银联
			// 交易渠道
			flowVO.setTranChan(PubBusinessConstant.TRANCHAN_SIGNTYPE);
		} else if (PayChanType.LYCHPAY.toString().equals(repayDgDto.getTranType())) { // 快付通
			// 交易渠道
			flowVO.setTranChan(PubBusinessConstant.KFT_BRANCH);
		} else if (PayChanType.ZJPAY.toString().equals(repayDgDto.getTranType())) { // 中金
			// 交易渠道
			flowVO.setTranChan(PubBusinessConstant.ZJ_SIGLE);
		}else {
			flowVO.setTranChan(repayDgDto.getTranType());
		}
		flowVO.setAcctName(repayDgDto.getAcctName());
		// 贷款编号
		flowVO.setLoanNo(repayDgDto.getLoanNo());
		// 账单日
		flowVO.setRepayDate(repayDgDto.getRepayDate());
		// 期数
		flowVO.setRepayNum(repayDgDto.getRepayNum());
		// 交易日期
		flowVO.setTranDate(DateUtils.getCurDate());
		// 交易类型
		flowVO.setTranType(PubBusinessConstant.TRANDTYPE_ZC);
		// 还款类型
		// flowVO.setTranChan(repayDgDto.getTranType());
		// 交易金额
		flowVO.setTranAmt(repayDgDto.getTranAmt());
		// 是否结算：否
		flowVO.setSetlFlag(CommonConstant.COMMON_NO);
		// 创建日期
		flowVO.setInstDate(DateUtils.getCurrentTimestamp());
		// 还款账户
		flowVO.setAcctNo(repayDgDto.getCustAcct());
		// 还款户名
		flowVO.setAcctName(repayDgDto.getAcctName());
		// 交易状态：当前日期与账单日比较，如果
		flowVO.setLoanStat(RepayUtil.getLoanStat(flowVO.getRepayDate()));

		// 支付宝
		if (PubBusinessConstant.TRANCHAN_ZHIFUBO.equals(repayDgDto.getTranType())) {
			// 转入账号
			flowVO.setCntAcctNo(ParamUtils.getParam("compZfb"));
			// 转入户名
			flowVO.setCntAcctName("深圳柠檬金服支付宝账户");
		} else { // 现金还款，对公还款
			// 转入账号
			flowVO.setCntAcctNo(ParamUtils.getParam("compAcctNo"));
			// 转入户名
			flowVO.setCntAcctName("深圳柠檬金服对公账户");
		}

		// 发起机构
		String orgId = userProfile.getOrgNo();
		flowVO.setTranOrg(orgId);
		// 经办人
		flowVO.setTranStaff(userProfile.getStaffName());

		// 是否提前结清
		String dgType = repayDgDto.getDgType();
		Boolean isCleard = false;
		if (PubBusinessConstant.PLAN_REPAY_TYPE_OVER.equals(dgType)) {// 结清
			isCleard = true;
		}
		String saveRepayFlow = accRepayFlowService.saveRepay(flowVO);
		if (!FinanceConstant.SUCC.equals(saveRepayFlow)) {
			throw new ServiceException(saveRepayFlow);
		}
		return saveRepayFlow;
	}

	/**
	 * 保存对公信息批量
	 * 
	 * @param repayDgVO
	 * @return
	 * @throws ServiceException
	 */
	@Transactional
	public void saveLoanRepayDgList(List<AccRepayDgRegDto> repayDgDtoList, UserProfile userProfile)
			throws ServiceException, AppException {
		boolean succFlag = false;
		for (AccRepayDgRegDto repayDgDto : repayDgDtoList) {
			String loanNo = repayDgDto.getLoanNo();
			Integer repayNum = repayDgDto.getRepayNum();
			Map<String, Object> accAmtMap = new HashMap<String, Object>();
			accAmtMap.put("loanNo", loanNo);
			accAmtMap.put("repayNum", repayNum);
			accAmtMap.put("curRcvAmt", repayDgDto.getTranAmt());
			accAmtMap.put("updt", new Date());
			CompAmtUtil.getDgTransBal(loanNo, repayNum, PubBusinessConstant.REPAY_TYPE_NOM);
		}
		// 还款金额计算
		try {
			for (AccRepayDgRegDto repayDgDto : repayDgDtoList) {
				String loanNo = repayDgDto.getLoanNo();
				Integer repayNum = repayDgDto.getRepayNum();
				Map<String, Object> accAmtMap = new HashMap<String, Object>();
				accAmtMap.put("loanNo", loanNo);
				accAmtMap.put("repayNum", repayNum);
				accAmtMap.put("curRcvAmt", repayDgDto.getTranAmt());
				accAmtMap.put("updt", new Date());
				BigDecimal dgTransBal = CompAmtUtil.getDgTransBal(loanNo, repayNum, PubBusinessConstant.REPAY_TYPE_NOM);
				// 应还金额
				repayDgDto.setTotlAmt(dgTransBal);
				/*
				 * // 判断是否已经锁表，如果锁定直接抛出异常 BigDecimal status =
				 * accLoanAcctInstService.queryAccLoanAcctStatus(loanNo,
				 * repayNum); if (status == null ||
				 * status.compareTo(BigDecimal.ZERO) != 0) { throw new
				 * ServiceException("当前不可进行此操作"); }
				 */

				if (repayDgDto.getTranAmt() == null || new BigDecimal(0).compareTo(repayDgDto.getTranAmt()) == 0
						|| new BigDecimal(0).compareTo(repayDgDto.getTranAmt()) == 1) {
					throw new ServiceException("分期编号：[" + repayDgDto.getLoanNo() + "]交易金额不正确");
				}
				// 锁表
				accLoanAcctInstService.updateAccLoanAcctStatusLock(loanNo, repayNum);
				// 主键
				repayDgDto.setId(RandomUtil.getUUID());
				// 插入时间
				repayDgDto.setInstDate(DateUtils.getCurrentTimestamp());

				// 插入用户ID
				repayDgDto.setTranStaff(userProfile.getStaffName());
				repayDgDto.setTranOrg(userProfile.getOrgNo());

				// 如果状态为已还，则将修改记录时间，并保存还款流水
				String dgstatus = repayDgDto.getStat();

				if (dgstatus.equals(PubBusinessConstant.REPAY_DG_STATUS_PAIED)) {
					// 保存还款流水
					saveRepayFlow(repayDgDto, userProfile);
				}
				AccRepayDgReg accRepayDgReg = new AccRepayDgReg();
				BeanUtils.copyPropertiesNotForce(accRepayDgReg, repayDgDto);
				if (repayDgDto.getTranDate()==null) {
					throw new ServiceException("到账时间为空");
				}
				accRepayDgReg.setTranDate(repayDgDto.getTranDate());
				accRepayDgRegService.insert(accRepayDgReg);
				// 更新预处理表
				accLoanAcctInstService.updateAccLoanInstCurRcvAmtAndUnLock(accAmtMap);

				if (dgTransBal.compareTo(repayDgDto.getTranAmt()) == 0
						|| dgTransBal.compareTo(repayDgDto.getTranAmt()) == -1) {
					// 更新代扣信息
					accAmtMap.put("loanNo", loanNo);
					accAmtMap.put("repayNum", repayNum);
					accAmtMap.put("withStat", FinanceConstant.WITHSTAT_DKSUCC);
					accAmtMap.put("updtDate", DateUtils.getCurDateTime());
					accAmtMap.put("_desc", "对公还款");
					accCapWithService.updateAccCapWithByStatByLoanNo(accAmtMap);
				}

				// 新增经办信息
				appLoanHandService.saveAppLoanHand(loanNo, "-", repayDgDto.getAcctName(),
						PubBusinessConstant.LOANHANDTYPE_DGREPAY, PubBusinessConstant.LOANHANDMODEL_SYS,
						userProfile.getStaffNo(), userProfile.getStaffName(), DateUtils.getCurrentTimestamp(),
						"分期对公还款,:-" + repayDgDto.getTranDesc(), PubBusinessConstant.CUST_ZC);

			}
			succFlag = true;
		} catch (ServiceException e) {
			succFlag = false;
			throw new ServiceException("对公还款失败:" + e.getMessage());
		} finally {
			if (!succFlag) {
				// 解锁
				for (AccRepayDgRegDto repayDgDto : repayDgDtoList) {
					accLoanAcctInstService.updateAccLoanAcctStatusUnLock(repayDgDto.getLoanNo(),
							repayDgDto.getRepayNum());
				}
			}
		}
	}

}
