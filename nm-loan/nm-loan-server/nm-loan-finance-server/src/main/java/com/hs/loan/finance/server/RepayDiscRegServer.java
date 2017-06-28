package com.hs.loan.finance.server;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.loan.finance.api.RepayDiscRegApi;
import com.hs.loan.finance.bo.AccLoanPlanBo;
import com.hs.loan.finance.bo.AccRepayDiscRegBo;
import com.hs.loan.finance.dto.AccLoanPlanDto;
import com.hs.loan.finance.dto.AccRepayDiscRegDto;
import com.hs.loan.finance.dto.AccRepayDiscRegInfoDto;
import com.hs.loan.finance.entity.AccLoanInst;
import com.hs.loan.finance.entity.AccRepayDiscReg;
import com.hs.loan.finance.service.AccLoanAcctInstService;
import com.hs.loan.finance.service.AccLoanInstService;
import com.hs.loan.finance.service.AccLoanPlanService;
import com.hs.loan.finance.service.AccRepayDgRegService;
import com.hs.loan.finance.service.AccRepayDiscRegService;
import com.hs.loan.finance.util.CompAmtUtil;
import com.hs.loan.pub.hand.service.AppLoanHandService;
import com.hs.loan.sale.entity.AppLoanAcct;
import com.hs.loan.sale.service.AppLoanAcctService;
import com.hs.utils.BeanUtils;
import com.hs.utils.DateUtils;
import com.hs.utils.RandomUtil;

/**
 * ACC_还款登记（费用减免）
 * 
 * @author zhangxiaoqiang
 *
 */
@Service
@Transactional(readOnly = true)
public class RepayDiscRegServer implements RepayDiscRegApi {
	@Autowired
	private AccRepayDiscRegService accRepayDiscRegService;
	@Autowired
	private AccLoanPlanService accLoanPlanService;
	@Autowired
	private AccLoanAcctInstService accLoanAcctInstService;
	@Autowired
	private AppLoanAcctService appLoanAcctService;
	@Autowired
	private AccRepayDgRegService accRepayDgRegService;
	@Autowired
	private AppLoanHandService appLoanHandService;
	@Autowired
	private AccLoanInstService accLoanInstService;

	/**
	 * 新增 ACC_还款登记（费用减免）
	 * 
	 * @throws Exception
	 */
	@Transactional
	public void insert(AccRepayDiscRegDto repayDiscRegDto, UserProfile user) throws ServiceException {
		// TODO Auto-generated method stub
		boolean succesFlg = false;
		if (repayDiscRegDto != null) {
			// 对应类型应收金额
			BigDecimal receivable = new BigDecimal("0");
			// 减免类型
			String discType = repayDiscRegDto.getDiscType();
			// 减免金额
			BigDecimal discAmt = repayDiscRegDto.getDiscAmt();
			if (discAmt.compareTo(receivable) == 0) {
				throw new ServiceException("减免金额不能为0");
			}
			Integer repayNum = 0;
			String loanNo = repayDiscRegDto.getLoanNo();
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("loanNo", loanNo);
			param.put("repayDate", repayDiscRegDto.getRepayDate());
			// 贷款分期信息
			List<AccLoanInst> listplan = accLoanInstService.queryForList(param);
			param.put("discType", discType);
			// 已减免列表
			List<AccRepayDiscReg> olddiscReg = accRepayDiscRegService.queryForList(param);
			Map<String, Object> outMap = accRepayDiscRegService.getOutMap(loanNo);
			int count = accRepayDgRegService.selectOutSourceCase(loanNo);
			boolean outflag = false;
			if (count > 0) { // 当前贷款已经进入委外
				outflag = true;
				repayNum = 1;
				if ("50003001".equals(discType)) { // 委外手续费
					receivable = new BigDecimal(outMap.get("OUT_AMT") == null ? "0" : outMap.get("OUT_AMT").toString());
				} else if ("50103002".equals(discType)) { // 服务费
					receivable = new BigDecimal(outMap.get("SVC_AMT") == null ? "0" : outMap.get("SVC_AMT").toString());
				} else if ("50103003".equals(discType)) { // 滞纳金
					receivable = new BigDecimal(outMap.get("OVERDUE_AMT") == null ? "0" : outMap.get("OVERDUE_AMT").toString());
				} else if ("50103004".equals(discType)) { // 账户管理费
					receivable = new BigDecimal(outMap.get("ACCT_AMT") == null ? "0" : outMap.get("ACCT_AMT").toString());
				} else if ("50103005".equals(discType)) { // 还款包服务费
					receivable = new BigDecimal(outMap.get("PACK_AMT") == null ? "0" : outMap.get("PACK_AMT").toString());
				} else if ("50103006".equals(discType)) { // 本金
					receivable = new BigDecimal(outMap.get("PRIN_AMT") == null ? "0" : outMap.get("PRIN_AMT").toString());
				} else if ("50103007".equals(discType)) { // 利息
					receivable = new BigDecimal(outMap.get("INTER_AMT") == null ? "0" : outMap.get("INTER_AMT").toString());
				}
			} else {
				if (listplan != null && !listplan.isEmpty()) {
					repayNum = listplan.get(0).getRepayNum();
					if ("50003001".equals(discType)) { // 委外手续费
						receivable = listplan.get(0).getRcvOutAmt();
					} else if ("50103002".equals(discType)) { // 服务费
						receivable = listplan.get(0).getRcvSvcAmt();
					} else if ("50103003".equals(discType)) { // 滞纳金
						receivable = listplan.get(0).getRcvOvduAmt();
					} else if ("50103004".equals(discType)) { // 账户管理费
						receivable = listplan.get(0).getRcvAcctAmt();
					} else if ("50103005".equals(discType)) { // 还款包服务费
						receivable = listplan.get(0).getRcvPackAmt();
					} else if ("50103006".equals(discType)) { // 本金
						receivable = listplan.get(0).getRcvPrinAmt();
					} else if ("50103007".equals(discType)) { // 利息
						receivable = listplan.get(0).getRcvIntAmt();
					}
				}
			}

			// 已减免数据汇总
			if (olddiscReg != null && !olddiscReg.isEmpty()) {
				for (AccRepayDiscReg breakFlowVo : olddiscReg) {
					if (repayDiscRegDto.getId() != null && repayDiscRegDto.getId().equals(breakFlowVo.getId())) {
						continue;
					}
					discAmt = discAmt
							.add(breakFlowVo.getDiscAmt() != null ? breakFlowVo.getDiscAmt() : BigDecimal.ZERO);
				}
			}
			if (discAmt.compareTo(receivable) > 0) {
				throw new ServiceException("多次减免总金额不能大于应收金额!");
			}

			if (outflag) {
				// 保存减免记录
				AccRepayDiscReg accRepayDiscReg = new AccRepayDiscReg();
				BeanUtils.copyProperties(repayDiscRegDto, accRepayDiscReg);
				if (repayDiscRegDto.getId() != null && !"".equals(repayDiscRegDto.getId())) {
					accRepayDiscReg.setInstDate(DateUtils.getCurrentDate());
					accRepayDiscReg.setDiscDate(DateUtils.getCurrentDate());
					accRepayDiscReg.setTranOrg(user.getOrgNo());
					accRepayDiscReg.setTranStaff(user.getStaffNo());
					accRepayDiscReg.setSetlFlag("50104001");
				} else {
					accRepayDiscReg.setId(RandomUtil.getUUID());
					accRepayDiscReg.setInstDate(DateUtils.getCurrentDate());
					accRepayDiscReg.setDiscDate(DateUtils.getCurrentDate());
					accRepayDiscReg.setTranOrg(user.getOrgNo());
					accRepayDiscReg.setTranStaff(user.getStaffNo());
					accRepayDiscReg.setSetlFlag("50104001");
				}

				// 新增经办登记
				String handDetailTyp = PubBusinessConstant.LOANHANDTYPE_DISBAL;
				String handPersonNo = user.getStaffNo();
				String handPersonName = user.getStaffName();
				Date handDate = DateUtils.getCurrentDate();
				appLoanHandService.saveAppLoanHand(loanNo, "N/A", "N/A", handDetailTyp,
						PubBusinessConstant.LOANHANDMODEL_SYS, handPersonNo, handPersonName, handDate, "费用减免",
						PubBusinessConstant.CUST_ZC);
				accRepayDiscRegService.insert(accRepayDiscReg);
				accRepayDiscRegService.updateOutSource(loanNo,repayDiscRegDto.getDiscAmt());
			}else{
				// 判断是否已经锁表，如果锁定直接抛出异常
				CompAmtUtil.getDgTransBal(loanNo, repayNum, PubBusinessConstant.PLAN_REPAY_TYPE_PRE);
				Map<String, Object> accAmtMap = new HashMap<String, Object>();
				try {
					// 锁表
					accLoanAcctInstService.updateAccLoanAcctStatusLock(loanNo, repayNum);
					// 保存
					// 更新预处理表
					accAmtMap.put("loanNo", loanNo);
					accAmtMap.put("repayNum", repayNum);
					accAmtMap.put("curRcvAmt", repayDiscRegDto.getDiscAmt());
					accAmtMap.put("updt", new Date());
					// 保存减免记录
					AccRepayDiscReg accRepayDiscReg = new AccRepayDiscReg();
					BeanUtils.copyProperties(repayDiscRegDto, accRepayDiscReg);
					if (repayDiscRegDto.getId() != null && !"".equals(repayDiscRegDto.getId())) {
						accRepayDiscReg.setInstDate(DateUtils.getCurrentDate());
						accRepayDiscReg.setDiscDate(DateUtils.getCurrentDate());
						accRepayDiscReg.setTranOrg(user.getOrgNo());
						accRepayDiscReg.setTranStaff(user.getStaffNo());
						accRepayDiscReg.setSetlFlag("50104001");
					} else {
						accRepayDiscReg.setId(RandomUtil.getUUID());
						accRepayDiscReg.setInstDate(DateUtils.getCurrentDate());
						accRepayDiscReg.setDiscDate(DateUtils.getCurrentDate());
						accRepayDiscReg.setTranOrg(user.getOrgNo());
						accRepayDiscReg.setTranStaff(user.getStaffNo());
						accRepayDiscReg.setSetlFlag("50104001");
					}

					// 新增经办登记
					String handDetailTyp = PubBusinessConstant.LOANHANDTYPE_DISBAL;
					String handPersonNo = user.getStaffNo();
					String handPersonName = user.getStaffName();
					Date handDate = DateUtils.getCurrentDate();
					appLoanHandService.saveAppLoanHand(loanNo, "N/A", "N/A", handDetailTyp,
							PubBusinessConstant.LOANHANDMODEL_SYS, handPersonNo, handPersonName, handDate, "费用减免",
							PubBusinessConstant.CUST_ZC);
					accRepayDiscRegService.insert(accRepayDiscReg);
					// 更新预处理表
					accLoanAcctInstService.updateAccLoanInstCurRcvAmtAndUnLock(accAmtMap);
					succesFlg = true;
				} catch (Exception e) {
					succesFlg = false;
					// TODO: handle exception
					throw new ServiceException("保存失败");
				} finally {
					if (!succesFlg) {
						// 处理完成 解锁
						accLoanAcctInstService.updateAccLoanAcctStatusUnLock(loanNo, repayNum);
					}
				}
			}
		}
	}

	/**
	 * 通过主键修改 ACC_还款登记（费用减免）
	 * 
	 * @param map
	 *            修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map) {
		// TODO Auto-generated method stub
		accRepayDiscRegService.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 ACC_还款登记（费用减免）
	 * 
	 * @param primaryKey
	 *            主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey) {
		// TODO Auto-generated method stub
		accRepayDiscRegService.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 ACC_还款登记（费用减免） 对象
	 * 
	 * @param primaryKey
	 *            主键
	 * @return ACC_还款登记（费用减免）对象
	 */
	public AccRepayDiscRegDto getByPrimaryKey(String primaryKey) {
		// TODO Auto-generated method stub
		AccRepayDiscReg accRepayDiscReg = accRepayDiscRegService.getByPrimaryKey(primaryKey);
		AccRepayDiscRegDto accRepayDiscRegDto = new AccRepayDiscRegDto();
		BeanUtils.copyProperties(accRepayDiscReg, accRepayDiscRegDto);
		return accRepayDiscRegDto;
	}

	/**
	 * 查询 ACC_还款登记（费用减免） 列表
	 * 
	 * @param param
	 *            查询参数map
	 * @return List<T>列表
	 */
	public List<AccRepayDiscRegDto> queryForList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return BeanUtils.copyProperties(accRepayDiscRegService.queryForList(param), AccRepayDiscRegDto.class);
	}

	/**
	 * 查询 ACC_还款登记（费用减免） 分页列表
	 * 
	 * @param page
	 *            查询参数page
	 * @return List<T>列表
	 */
	public Page<AccRepayDiscRegDto> queryForPage(Page<AccRepayDiscRegDto> page) {
		// TODO Auto-generated method stub
		return accRepayDiscRegService.queryForPage(page.toPage(AccRepayDiscReg.class)).toPage(AccRepayDiscRegDto.class);
	}

	/**
	 * 根据 时间等查询 Acc_还款登记（费用减免）分页列表
	 * 
	 * @param page
	 * @return
	 */
	public Page<AccRepayDiscRegInfoDto> queryForListByInstDate(Page<AccRepayDiscRegInfoDto> page) {
		return accRepayDiscRegService.queryForPageByInstDate(page.toPage(AccRepayDiscRegBo.class))
				.toPage(AccRepayDiscRegInfoDto.class);
	}

	/**
	 * 查询计划
	 * 
	 * @param page
	 * @return
	 */
	public Page<AccLoanPlanDto> queryListOnNo(Page<AccLoanPlanDto> page) throws ServiceException {
		List<AppLoanAcct> loanLst = appLoanAcctService.queryLoan(page.getPageParams());
		AppLoanAcct loan = null;
		if (loanLst.size() > 0 && loanLst.size() != 1) {
			throw new ServiceException("当前客户有多笔贷款，请使用贷款编号查询");
		} else if (loanLst.size() > 0) {
			loan = loanLst.get(0);
		}
		if (loan != null) {
			int count = accRepayDgRegService.selectOutSourceCase(loan.getLoanNo());
			Page<AccLoanPlanDto> pages = null;
			if (count > 0) { // 当前贷款已经进入委外
				pages = accLoanPlanService.queryOutSourceCaseListOnNo(page.toPage(AccLoanPlanBo.class))
						.toPage(AccLoanPlanDto.class);
			} else {
				pages = accLoanPlanService.queryListOnNo(page.toPage(AccLoanPlanBo.class)).toPage(AccLoanPlanDto.class);
			}
			if (pages.getList() != null && pages.getList().size() > 0) {
				return pages;
			} else {
				throw new ServiceException("当前贷款的所有期数已结清或尚未跑借贷明细或正在扣款中，稍后再试");
			}
		} else {
			throw new ServiceException("贷款不存在，请核对。");
		}

	}

}
