package com.hs.loan.collection.server;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.CommonConstant;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.loan.collection.api.LoanOvduCaseApi;
import com.hs.loan.collection.dto.AppLoanAcctDto;
import com.hs.loan.collection.dto.AppLoanCustInfoDto;
import com.hs.loan.collection.dto.LoanOvduCaseDto;
import com.hs.loan.collection.dto.LoanOvduCaseOutDto;
import com.hs.loan.collection.dto.LoanOveDualCaseBaseDto;
import com.hs.loan.collection.dto.LoanOveDualCaseDto;
import com.hs.loan.collection.dto.LoanOverdueDetailDto;
import com.hs.loan.collection.dto.LoanOverduePeriodDetileDto;
import com.hs.loan.collection.dto.OverdueAmtDetailDto;
import com.hs.loan.collection.dto.PlLoanOvduCaseRetAndFlowDto;
import com.hs.loan.collection.dto.PlLoanOvduHandDto;
import com.hs.loan.collection.dto.PubMessageModelDto;
import com.hs.loan.collection.entity.AccCustAndBankBo;
import com.hs.loan.collection.entity.AppLoanAcctBo;
import com.hs.loan.collection.entity.AppLoanCustInfoBo;
import com.hs.loan.collection.entity.PlLoanOvduCase;
import com.hs.loan.collection.entity.PlLoanOvduCaseBo;
import com.hs.loan.collection.entity.PlLoanOvduCaseRetAndFlowBo;
import com.hs.loan.collection.entity.PlLoanOvduHand;
import com.hs.loan.collection.entity.PlOverdueDetail;
import com.hs.loan.collection.service.PlLoanOvduCaseService;
import com.hs.loan.collection.service.PlLoanOvduHandService;
import com.hs.loan.collection.service.PlOverdueDetailService;
import com.hs.loan.finance.bo.SingleDkResultBo;
import com.hs.loan.finance.bo.SingleOtherBusiBo;
import com.hs.loan.finance.contant.FinanceConstant;
import com.hs.loan.finance.entity.AccCapWith;
import com.hs.loan.finance.entity.AccLoanAcctInst;
import com.hs.loan.finance.entity.AccLoanInst;
import com.hs.loan.finance.entity.AccLoanPlan;
import com.hs.loan.finance.entity.AccPayWith;
import com.hs.loan.finance.entity.AccRepayAdvanReg;
import com.hs.loan.finance.entity.AccRepayFlow;
import com.hs.loan.finance.entity.AccWithholdReg;
import com.hs.loan.finance.service.AccCapWithService;
import com.hs.loan.finance.service.AccLoanAcctInstService;
import com.hs.loan.finance.service.AccLoanInstService;
import com.hs.loan.finance.service.AccLoanPlanService;
import com.hs.loan.finance.service.AccPayWithService;
import com.hs.loan.finance.service.AccRepayAdvanRegService;
import com.hs.loan.finance.service.AccRepayFlowService;
import com.hs.loan.finance.service.AccWithholdRegService;
import com.hs.loan.finance.util.CompAmtUtil;
import com.hs.loan.finance.util.PayChanType;
import com.hs.loan.pub.hand.entity.AppLoanHand;
import com.hs.loan.pub.hand.entity.PubMessageModel;
import com.hs.loan.pub.hand.service.AppLoanHandService;
import com.hs.loan.pub.hand.service.PubMessageModelService;
import com.hs.utils.BeanUtils;
import com.hs.utils.DateUtils;
import com.hs.utils.HttpsInvokerUtil;
import com.hs.utils.ParamUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.SimpleCodeUtils;
import com.hs.utils.StringUtils;

/**
 * 逾期案件server
 * 
 * @author IT-009
 *
 */
@Service
@Transactional(readOnly = true)
public class LoanOvduCaseServer implements LoanOvduCaseApi {
	@Autowired
	private PlLoanOvduCaseService caseService;
	// @Autowired
	// private PubSysRegionalBelongService belongService;
	@Autowired
	private AccWithholdRegService accWithholdRegService;
	@Autowired
	private PlOverdueDetailService overdueDtlService;
	@Autowired
	private AccLoanPlanService accLoanPlanService;
	@Autowired
	private AppLoanHandService appLoanHandService;
	// @Autowired
	// private LoanAcctApi colLoanAcctService;
	@Autowired
	private AccLoanInstService accLoanInstService;
	@Autowired
	private AccLoanAcctInstService accLoanAcctInstService;
	@Autowired
	private AccCapWithService accCapWithService;
	@Autowired
	private AccRepayFlowService accRepayFlowService;
	@Autowired
	private PlLoanOvduHandService loanOvduHandService;
	@Autowired
	private AccPayWithService accPayWithService;
	@Autowired
	private PubMessageModelService pubMessageModelService;
	@Autowired
	private AccRepayAdvanRegService accRepayAdvanRegService;
	// @Autowired
	// private AccCapTranLogService accCapTranLogService;

	/**
	 * 根据主键获取逾期案件信息
	 * 
	 * @param primaryKey
	 *            逾期案件id(主键)
	 * @return LoanOvduCaseOutDto pl_逾期案件对象dto
	 */
	public LoanOvduCaseOutDto getByPrimaryKey(String primaryKey) {
		// pl_逾期案件对象
		PlLoanOvduCase byPrimaryKey = caseService.getByPrimaryKey(primaryKey);
		// pl_逾期案件对象dto
		LoanOvduCaseOutDto loanOvduCaseOutDto = JSON.parseObject(JSON.toJSONString(byPrimaryKey),
				LoanOvduCaseOutDto.class);
		return loanOvduCaseOutDto;
	}

	/**
	 * 查询预期案件列表分页查询（案件处理）
	 * 
	 * @param page
	 *            分页dto实体
	 * @param profile
	 *            用户信息
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	@Override
	public Page<LoanOvduCaseDto> queryLoanOvduCase(Page<LoanOvduCaseDto> page, UserProfile profile)
			throws ServiceException, AppException {
		// 逾期案件分页查询结果
		Page<PlLoanOvduCaseBo> pageRsl = caseService.queryForParam(page.toPage(PlLoanOvduCaseBo.class), profile);
		Page<LoanOvduCaseDto> caseOutDtoPage = pageRsl.toPage(LoanOvduCaseDto.class);
		return caseOutDtoPage;
	}

	/**
	 * 查询预期案件列表分页查询(案件分配)
	 * 
	 * @param page
	 *            分页dto实体
	 * @param profile
	 *            用户信息
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	@Override
	public Page<LoanOvduCaseDto> queryLoanOvduCaseAllot(Page<LoanOvduCaseDto> page, UserProfile profile)
			throws ServiceException, AppException {
		// 逾期案件分页查询结果
		Page<PlLoanOvduCaseBo> pageRsl = caseService.queryCaseAllotForParam(page.toPage(PlLoanOvduCaseBo.class),
				profile);
		Page<LoanOvduCaseDto> caseOutDtoPage = pageRsl.toPage(LoanOvduCaseDto.class);
		return caseOutDtoPage;

	}

	/**
	 * 查询预期案件列表分页查询(案件分配导出)
	 * 
	 * @param page
	 *            分页dto实体
	 * @param profile
	 *            用户信息
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<Map<String, Object>> queryLoanOvduCaseAllotForList(Map<String, Object> param, UserProfile profile)
			throws ServiceException {
		// 逾期案件分页查询结果
		List<PlLoanOvduCaseBo> dtoList = caseService.queryCaseAllotListForParam(param, profile);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		if (dtoList != null && dtoList.size() > 0) {
			String maxExropt = ParamUtils.getParam("maxExroptNum");
			if (StringUtils.isEmpty(maxExropt)) {
				maxExropt = "3000";
			}
			if (dtoList.size() > Integer.valueOf(maxExropt)) {
				throw new ServiceException("案件列表超过" + maxExropt + "条，请等待后台跑批处理...");
			} else {
//				Map<String, Object> handMap = null;
//				StringBuilder sb = null;
				for (PlLoanOvduCaseBo dto : dtoList) {
//					handMap = new HashMap<>();
//					handMap.put("handDetail", PubBusinessConstant.LOANHANDTYPE_COLLECT);
//					handMap.put("loanNo", dto.getLoanNo());
//					// 封装 对应的 备注信息
//					List<AppLoanHand> listhand = appLoanHandService.queryForList(handMap);
//					if (listhand != null && listhand.size() > 0) {
//						sb = new StringBuilder("");
//						for (int i = 0; i < listhand.size(); i++) {
//							sb.append(i + 1).append(".处理人:").append(listhand.get(i).getHandPersonName())
//									.append(";处理信息：").append(listhand.get(i).getRemark()).append(";");
//						}
//						dto.setDealDesc(sb.toString());
//					}
					String stat = dto.getDealStat();
					dto.setDealStat(stat == null || stat.equals(PubBusinessConstant.COLLECTION_WCL) ? "未处理"
							: stat.equals(PubBusinessConstant.COLLECTION_YCL) ? "已处理" : "失效");
					resultList.add(BeanUtils.bean2map(dto));
				}
			}
		}
		return resultList;
	}

	/**
	 * 查询预期案件详细信息-包含费用项,减免信息,预期信息
	 * 
	 * @param
	 * @param profile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	@Override
	public LoanOverdueDetailDto queryLoanOvduCaseDetail(String loanNo, String caseId, UserProfile profile)
			throws ServiceException, AppException {
		LoanOverdueDetailDto ret = new LoanOverdueDetailDto();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loanNo", loanNo);
		// List<AccLoanBreaksFlow> beaksFlowList =
		// breaksFlowService.queryForPermissionList(param,profile);
		// List<AccLoanBreaksFlowDto> accLoanBreaksFlowDtos =
		// BeanUtils.copyProperties(beaksFlowList, AccLoanBreaksFlowDto.class);
		// ret.setAccLoanBreaksFlowDtos(accLoanBreaksFlowDtos);
		param.put("caseId", caseId);
		List<PlOverdueDetail> overdueList = overdueDtlService.queryForPermissonList(param, profile);
		List<OverdueAmtDetailDto> overdueAmtDetailDtos = BeanUtils.copyProperties(overdueList,
				OverdueAmtDetailDto.class);
		ret.setOverdueAmtDetailDtos(overdueAmtDetailDtos);
		List<PlLoanOvduCase> caseBaseList = caseService.queryForPermissonList(param, profile);
		List<LoanOveDualCaseBaseDto> caseBaseDtos = BeanUtils.copyProperties(caseBaseList,
				LoanOveDualCaseBaseDto.class);
		ret.setCaseBaseDtos(caseBaseDtos);
		return ret;
	}

	/**
	 * 案件处理 （调用经办信息）
	 * 
	 * @param remark
	 * @param profile
	 * @throws ServiceException
	 * @throws AppException
	 **/
	@Transactional
	public void detailLoanCase(Map<String, Object> param, UserProfile profile) throws ServiceException, AppException {
		if (param != null) {
			String loanNo = param.get("loanNo") != null ? param.get("loanNo").toString() : "";
			String contName = param.get("contName") != null ? param.get("contName").toString() : "";
			String contRel = param.get("contRel") != null ? param.get("contRel").toString() : "";
			String contTel = param.get("contTel") != null ? param.get("contTel").toString() : "";
			String handOvduResn = param.get("handOvduResn") != null ? param.get("handOvduResn").toString() : "";
			String remark = param.get("remark") != null ? param.get("remark").toString() : "";
			String restCode = param.get("restCode") != null ? param.get("restCode").toString() : "";
			String restStat = param.get("restStat") != null ? param.get("restStat").toString() : "";
			String custNo = param.get("custNo") != null ? param.get("custNo").toString() : "";
			String custName = param.get("custName") != null ? param.get("custName").toString() : "";
			Date handDate = DateUtils.getCurrentDate();
			// 逾期经办
			PlLoanOvduHand hand = new PlLoanOvduHand();
			hand.setId(RandomUtil.getUUID());
			hand.setCustNo(custNo);
			hand.setCustName(custName);
			hand.setHandDate(handDate);
			hand.setHandPsnNo(profile.getStaffNo());
			hand.setHandPsnName(profile.getStaffName());
			hand.setInstDate(handDate);
			hand.setLoanNo(loanNo);
			hand.setTyp(PubBusinessConstant.LOANHANDMODEL_HAND);
			hand.setContName(contName);
			hand.setContRel(contRel);
			hand.setContTel(contTel);
			hand.setHandOvduResn(handOvduResn);
			hand.setRemark(remark);
			hand.setRestCode(restCode);
			hand.setRestStat(restStat);
			loanOvduHandService.insert(hand);
			// 保存系统经办
			String handDetailTyp = PubBusinessConstant.LOANHANDTYPE_COLLECT;
			String handPersonNo = profile.getStaffNo();
			String handPersonName = profile.getStaffName();
			appLoanHandService.saveAppLoanHand(loanNo, custNo, custName, handDetailTyp,
					PubBusinessConstant.LOANHANDMODEL_SYS, handPersonNo, handPersonName, handDate, remark,
					PubBusinessConstant.CUST_ZC);
		} else {
			throw new com.hs.base.exception.ServiceException("保存处理信息失败");
		}
	}

	/**
	 * 案件重新分配
	 * 
	 * @param caseId
	 * @param staffNo
	 * @param profile
	 * @throws ServiceException
	 * @throws AppException
	 */
	@Transactional
	public void distrbOveCse(List<String> caseIdList, String staffNo, String staffName, UserProfile profile)
			throws ServiceException, AppException {
		if (caseIdList != null && caseIdList.size() > 0) {
			for (String caseId : caseIdList) {
				PlLoanOvduCase caseBo = caseService.getByPrimaryKey(caseId);
				if (caseBo == null) {
					throw new ServiceException("分配失败,ID为[" + caseId + "]无相关案件信息！");
				} else {
					if (caseBo.getDealStat().equals(PubBusinessConstant.COLLECTION_YCL)) {
						throw new ServiceException("分配失败,案件编号为[" + caseBo.getLoanNo() + "]该条案件已处理！");
					} else if (caseBo.getDealStat().equals(PubBusinessConstant.COLLECTION_SX)) {
						throw new ServiceException("分配失败,案件编号为[" + caseBo.getLoanNo() + "]该条案件已失效！");
					} else {
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("id", caseId);
						param.put("staffNo", staffNo);
						param.put("staffName", staffName);
						param.put("updtDate", new Date());
						caseService.updateByPrimaryKeySelective(param);// 修改逾期案件处理结果
					}
				}
			}
		} else {
			throw new ServiceException("分配失败,未选择案件信息！");
		}
	}

	/**
	 * 案件标记为委外
	 * 
	 * @param caseId
	 * @param profile
	 * @throws ServiceException
	 * @throws AppException
	 */
	@Transactional
	@Override
	public void remarkOutSource(String loanNo, String caseId, UserProfile profile)
			throws ServiceException, AppException {
		PlLoanOvduCase caseBo = caseService.getByPrimaryKey(caseId);
		if (caseBo == null) {
			throw new ServiceException("标记委外失败，无相关案件信息！");
		} else {
			if (caseBo.getDealStat().equals(PubBusinessConstant.COLLECTION_YCL)) {
				throw new ServiceException("标记委外失败,该条案件已处理！");
			} else if (caseBo.getDealStat().equals(PubBusinessConstant.COLLECTION_SX)) {
				throw new ServiceException("标记委外失败,该条案件已失效！");
			} else if (caseBo.getOutFlag().equals(CommonConstant.COMMON_YES)) {
				throw new ServiceException("标记委外失败,该条案件已经是委外状态！");
			} else {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("id", caseId);
				param.put("outFlag", CommonConstant.COMMON_YES);
				param.put("updtDate", new Date());
				caseService.updateByPrimaryKeySelective(param);// 修改逾期案件处理结果
				// 新增经办登记
				String handDetailTyp = PubBusinessConstant.LOANHANDTYPE_OUTSOURCE;
				String handPersonNo = profile.getStaffNo();
				String handPersonName = profile.getStaffName();
				Date handDate = DateUtils.getCurrentDate();
				appLoanHandService.saveAppLoanHand(loanNo, caseBo.getCustNo(), caseBo.getCustName(), handDetailTyp,
						PubBusinessConstant.LOANHANDMODEL_SYS, handPersonNo, handPersonName, handDate, "标记委外",
						PubBusinessConstant.CUST_ZC);
			}
		}
	}

	/**
	 * 案件标记为委外批量
	 * 
	 * @param caseId
	 * @param profile
	 * @throws ServiceException
	 * @throws AppException
	 */
	@Transactional
	@Override
	public void remarkOutSourceList(List<Map<String, Object>> list, UserProfile profile)
			throws ServiceException, AppException {
		List<Map<String, Object>> listCase = new ArrayList<>();
		Map<String, Object> param = null;
		for (Map<String, Object> map : list) {
			String loanNo = map.get("loanNo").toString();
			String caseId = map.get("caseId").toString();
			PlLoanOvduCase caseBo = caseService.getByPrimaryKey(caseId);
			if (caseBo == null) {
				throw new ServiceException("标记委外失败，" + loanNo + "无相关案件信息！");
			} else {
				if (caseBo.getDealStat().equals(PubBusinessConstant.COLLECTION_YCL)) {
					throw new ServiceException("标记委外失败," + loanNo + "该条案件已处理！");
				} else if (caseBo.getDealStat().equals(PubBusinessConstant.COLLECTION_SX)) {
					throw new ServiceException("标记委外失败," + loanNo + "该条案件已失效！");
				} else if (caseBo.getOutFlag().equals(CommonConstant.COMMON_YES)) {
					throw new ServiceException("标记委外失败," + loanNo + "该条案件已经是委外状态！");
				} else {
					param = new HashMap<String, Object>();
					param.put("id", caseId);
					param.put("outFlag", CommonConstant.COMMON_YES);
					param.put("updtDate", new Date());
					listCase.add(param);
					// 新增经办登记
					appLoanHandService.saveAppLoanHand(loanNo, caseBo.getCustNo(), caseBo.getCustName(),
							PubBusinessConstant.LOANHANDTYPE_OUTSOURCE, PubBusinessConstant.LOANHANDMODEL_SYS,
							profile.getStaffNo(), profile.getStaffName(), DateUtils.getCurrentDate(), "标记委外",
							PubBusinessConstant.CUST_ZC);
				}
			}
		}
		if (listCase.size() == list.size()) {
			caseService.updateList(listCase);
		}
	}

	/**
	 * 取消案件标记为委外
	 * 
	 * @param caseId
	 * @param profile
	 * @throws ServiceException
	 * @throws AppException
	 */
	@Transactional
	@Override
	public void cancleRemarkOutSource(String caseId, UserProfile profile) throws ServiceException, AppException {
		PlLoanOvduCase caseBo = caseService.getByPrimaryKey(caseId);
		if (caseBo == null) {
			throw new ServiceException("取消标记委外失败，无相关案件信息！");
		} else {
			if (caseBo.getDealStat().equals(PubBusinessConstant.COLLECTION_YCL)) {
				throw new ServiceException("取消标记委外失败,该条案件已处理！");
			} else if (caseBo.getDealStat().equals(PubBusinessConstant.COLLECTION_SX)) {
				throw new ServiceException("取消标记委外失败,该条案件已失效！");
			} else if (caseBo.getOutFlag().equals(CommonConstant.COMMON_NO)) {
				throw new ServiceException("取消标记委外失败,该条案件并不是委外类型！");
			} else {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("id", caseId);
				param.put("outFlag", CommonConstant.COMMON_NO);
				param.put("updtDate", new Date());
				caseService.updateByPrimaryKeySelective(param);// 修改逾期案件处理结果
			}
		}
	}

	/**
	 * 取消案件标记为委外批量
	 * 
	 * @param caseId
	 * @param profile
	 * @throws ServiceException
	 * @throws AppException
	 */
	@Transactional
	@Override
	public void cancleRemarkOutSourceList(List<Map<String, Object>> list, UserProfile profile)
			throws ServiceException, AppException {
		List<Map<String, Object>> listCase = new ArrayList<>();
		Map<String, Object> param = null;
		for (Map<String, Object> map : list) {
			String caseId = map.get("caseId").toString();
			PlLoanOvduCase caseBo = caseService.getByPrimaryKey(caseId);
			if (caseBo == null) {
				throw new ServiceException("取消标记委外失败，" + map.get("loanNo") + "无相关案件信息！");
			} else {
				if (caseBo.getDealStat().equals(PubBusinessConstant.COLLECTION_YCL)) {
					throw new ServiceException("取消标记委外失败," + map.get("loanNo") + "该条案件已处理！");
				} else if (caseBo.getDealStat().equals(PubBusinessConstant.COLLECTION_SX)) {
					throw new ServiceException("取消标记委外失败," + map.get("loanNo") + "该条案件已失效！");
				} else if (caseBo.getOutFlag().equals(CommonConstant.COMMON_NO)) {
					throw new ServiceException("取消标记委外失败," + map.get("loanNo") + "该条案件并不是委外类型！");
				} else {
					param = new HashMap<String, Object>();
					param.put("id", caseId);
					param.put("outFlag", CommonConstant.COMMON_NO);
					param.put("updtDate", new Date());
					listCase.add(param);
				}
			}
		}
		if (listCase.size() == list.size()) {
			caseService.updateList(listCase);
		}
	}

	/**
	 * 逾期扣款
	 * 
	 * @param caseId
	 * @param loanNo
	 * @param type
	 * @param amt
	 * @param profile
	 */
	@Transactional
	@Override
	public void repayAmt(String caseId, String loanNo, String type, BigDecimal amt, UserProfile profile)
			throws ServiceException, AppException {

	}

	/**
	 * 查询逾期期数列表
	 */
	public List<LoanOverduePeriodDetileDto> getOverduePeriodList(Map<String, Object> param) {
		List<LoanOverduePeriodDetileDto> listdto = new ArrayList<LoanOverduePeriodDetileDto>();
		if (param != null) {
			// 贷款编号
			String loanNo = param.get("loanNo").toString();
			// 开始期数
			String bgRepay = param.get("bgRepayNum").toString();
			// 结束期数
			String enRepay = param.get("egRepayNum").toString();
			Map<String, Object> planParam = null;
			planParam = new HashMap<String, Object>();
			planParam.put("loanNo", loanNo);
			planParam.put("bgRepayNum", bgRepay);
			planParam.put("enRepayNum", enRepay);
			// 逾期计划列表
			List<AccLoanPlan> listplan = accLoanPlanService.queryListByParam(planParam);
			LoanOverduePeriodDetileDto dto = null;
			if (listplan != null && listplan.size() > 0) {
				for (AccLoanPlan plan : listplan) {
					dto = new LoanOverduePeriodDetileDto();
					dto.setPlanid(plan.getId());
					dto.setRepayNum(plan.getRepayNum().toString());
					dto.setRepayDate(plan.getRepayDate());
					dto.setRepayAmt(plan.getRepayAmt());
					dto.setOverdueAmt(plan.getOvduAmt());
					String repayDateStr = plan.getRepayDate();
					int nowDateStr = DateUtils.getDaysBetweenTwoDates(repayDateStr, DateUtils.getCurDate());
					dto.setOverdueNum(nowDateStr > 0 ? nowDateStr + "" : "0");
					listdto.add(dto);
				}
			}
		}
		return listdto;
	}

	/**
	 * 查询逾期案件信息
	 */
	public LoanOveDualCaseDto getOverduePeriodInfo(Map<String, Object> param) {
		LoanOveDualCaseDto dto = new LoanOveDualCaseDto();
		String caseId = param.get("caseId") + "";
		dto.setCaseId(caseId);
		// 逾期案件信息
		PlLoanOvduCase caseinfo = caseService.getByPrimaryKey(caseId);
		Map<String, Object> planParam = null;
		Map<String, Object> allplanParam = null;
		if (caseinfo != null) {
			AppLoanAcctBo acctBo = caseService.getAppLoanAcctBo(caseinfo.getLoanNo());
			if (acctBo != null) {
				AppLoanAcctDto accDto = new AppLoanAcctDto();
				BeanUtils.copyProperties(acctBo, accDto);
				dto.setAppLoanAcctDto(accDto);
			}
			// 计划参数拼装
			planParam = new HashMap<String, Object>();
			planParam.put("loanNo", caseinfo.getLoanNo());
			planParam.put("bgRepayNum", caseinfo.getBgnNum());
			planParam.put("enRepayNum", caseinfo.getEndNum());
			allplanParam = new HashMap<String, Object>();
			allplanParam.put("loanNo", caseinfo.getLoanNo());
			BeanUtils.copyProperties(caseinfo, dto);
			// 所有计划列表
			List<AccLoanPlan> allplan = accLoanPlanService.queryForList(allplanParam);
			// 逾期计划列表
			List<AccLoanPlan> listplan = accLoanPlanService.queryListByParam(planParam);
			// 实收金额列表
			List<AccLoanInst> listinst = accLoanInstService.queryForList(allplanParam);
			if (listplan != null && listplan.size() > 0) {
				for (AccLoanPlan plan : listplan) {
					// 管理费合计
					dto.setAccountMrgAmt((dto.getAccountMrgAmt() == null ? BigDecimal.ZERO : dto.getAccountMrgAmt())
							.add(plan.getMngAmt()));
					// 保险合计
					dto.setInsuranceAmt((dto.getInsuranceAmt() == null ? BigDecimal.ZERO : dto.getInsuranceAmt())
							.add(plan.getInsuAmt()));
					// 逾期本金
					dto.setPrinAmt(
							(dto.getPrinAmt() == null ? BigDecimal.ZERO : dto.getPrinAmt()).add(plan.getPrinAmt()));
					// 逾期利息
					dto.setInterAmt(
							(dto.getInterAmt() == null ? BigDecimal.ZERO : dto.getInterAmt()).add(plan.getIntAmt()));
					// 滞纳金合计
					dto.setOverdueAmt((dto.getOverdueAmt() == null ? BigDecimal.ZERO : dto.getOverdueAmt())
							.add(plan.getOvduAmt()));
					// 服务费合计
					dto.setServiceAmt((dto.getServiceAmt() == null ? BigDecimal.ZERO : dto.getServiceAmt())
							.add(plan.getSvcAmt()));
					// 随心包合计
					dto.setPackAmt(
							(dto.getPackAmt() == null ? BigDecimal.ZERO : dto.getPackAmt()).add(plan.getPackAmt()));
				}
				// 本息金额 = 本金+利息
				dto.setPrinIntAmt(dto.getPrinAmt().add(dto.getInterAmt()));
				// 逾期金额
				dto.setOvduLoanAmt(
						dto.getPrinAmt().add(dto.getInterAmt()).add(dto.getOverdueAmt()).add(dto.getServiceAmt())
								.add(dto.getPackAmt()).add(dto.getInsuranceAmt()).add(dto.getAccountMrgAmt()));
			}
			if (allplan != null && allplan.size() > 0) {
				BigDecimal packAmtTotal = BigDecimal.ZERO;// 随心包合计
				for (AccLoanPlan plan : allplan) {
					// 剩余账户管理费
					dto.setRemainAcc(
							(dto.getRemainAcc() == null ? BigDecimal.ZERO : dto.getRemainAcc()).add(plan.getMngAmt()));
					// 剩余保险费
					dto.setRemainIns(
							(dto.getRemainIns() == null ? BigDecimal.ZERO : dto.getRemainIns()).add(plan.getInsuAmt()));
					// 剩余本金
					dto.setRemainPrin((dto.getRemainPrin() == null ? BigDecimal.ZERO : dto.getRemainPrin())
							.add(plan.getPrinAmt()));
					// 剩余利息
					dto.setRemainInt(
							(dto.getRemainInt() == null ? BigDecimal.ZERO : dto.getRemainInt()).add(plan.getIntAmt()));
					// 剩余滞纳金
					dto.setRemainOver((dto.getRemainOver() == null ? BigDecimal.ZERO : dto.getRemainOver())
							.add(plan.getOvduAmt()));
					// 剩余服务费
					dto.setRemainSer(
							(dto.getRemainSer() == null ? BigDecimal.ZERO : dto.getRemainSer()).add(plan.getSvcAmt()));
					packAmtTotal.add(plan.getPackAmt());
				}
				// 剩余金额
				dto.setRemnAmt(dto.getRemainAcc().add(dto.getRemainIns()).add(dto.getRemainPrin())
						.add(dto.getRemainInt()).add(dto.getRemainOver()).add(dto.getRemainSer()).add(packAmtTotal));
			}
			// 已还金额
			BigDecimal instNum = new BigDecimal("0");
			if (listinst != null && listinst.size() > 0) {
				for (AccLoanInst inst : listinst) {
					instNum.add(inst.getActTotlAmt());
				}
			}
			dto.setPaidAmt(instNum);
		}
		return dto;
	}

	/**
	 * 查询实时代扣金额
	 */
	public String getOvduCaseDebit(Map<String, Object> param) throws ServiceException {
		String dvalRepayTyp = param.get("dvalRepayTyp").toString();
		String loanNo = param.get("loanNo").toString();
		// 逾期计划列表
		int bgRepayNum = Integer.parseInt(param.get("bgRepayNum").toString());
		if ("50001001".equals(dvalRepayTyp)) { // 实时代扣
			int enRepayNum = Integer.parseInt(param.get("enRepayNum").toString());
			BigDecimal result = new BigDecimal("0");
			for (int i = bgRepayNum; i <= enRepayNum; i++) {
				BigDecimal rs = CompAmtUtil.getDkTransBal(loanNo, i, "", "100310", FinanceConstant.SINGLE_REPAY_DK);
				if (rs.compareTo(BigDecimal.ZERO) != -1) {
					result = result.add(rs);
				}
			}
			return result + "";
		}
		if ("50001002".equals(dvalRepayTyp)) {// 提前结清
			BigDecimal rs = CompAmtUtil.getDgTransBal(loanNo, bgRepayNum, PubBusinessConstant.PLAN_REPAY_TYPE_OVER);
			return rs + "";
		}

		throw new ServiceException("数据获取失败");
	}

	/**
	 * 代扣金额
	 * 
	 * @param param:
	 *            loanNo,caseId,transAmtTotal,dvalRepayTyp,bgRepayNum,enRepayNum
	 * @param userProFile
	 * @throws Exception
	 */
	@Transactional
	public void ovduCaseRepayAmt(Map<String, Object> param, UserProfile userProFile) throws ServiceException {
		String exportTxtType = param.get("exportTxtType").toString();
		if (exportTxtType.equals(PayChanType.ALLINPAY.toString())) {
			// 通联
			allinpay(param, userProFile);
		} else if (exportTxtType.equals(PayChanType.LYCHPAY.toString())
				|| exportTxtType.equals(PayChanType.RYPAY.toString())) {
			// 快付通 &瑞银
			if (accCapWithService.capWithSize(2, param.get("loanNo").toString())) {
				throw new ServiceException("该笔贷款当日快付通扣款失败次数超出限制");
			}
			kftpay(param, userProFile,exportTxtType);
		} else if (exportTxtType.equals(PayChanType.ZJPAY.toString())) {
			// 中金支付
			if (accCapWithService.capWithSize(1, param.get("loanNo").toString())) {
				throw new ServiceException("该笔贷款当日中金扣款失败次数超出限制");
			}
			zjpay(param, userProFile);
		} else if (exportTxtType.equals(PayChanType.CHINAPAY.toString())) {
			// 银联
			chinaPay(param, userProFile);
		} else if (exportTxtType.equals(PayChanType.LIANLIANPAY.toString())) {
			if (accCapWithService.capWithSize(3, param.get("loanNo").toString())) {
				throw new ServiceException("该笔贷款当日连连支付扣款失败次数超出限制");
			}
			zjpay(param, userProFile);
		} else {
			throw new ServiceException("不存在的扣款渠道");
		}
	}

	/**
	 * 快付通&瑞银走间连付款
	 * 
	 * @param param
	 * @param userProFile
	 * @throws ServiceException
	 */
	@Transactional
	private void kftpay(Map<String, Object> param, UserProfile userProFile,String exportTxtType) {
		String loanNo = param.get("loanNo").toString();
		String caseId = param.get("caseId").toString();
		BigDecimal transAmtTotal = new BigDecimal(param.get("transAmtTotal").toString());
		String dvalRepayTyp = param.get("dvalRepayTyp").toString();
		int bgRepayNum = Integer.parseInt(param.get("bgRepayNum").toString());
		int enRepayNum = Integer.parseInt(param.get("enRepayNum").toString());
		String repayKind = PubBusinessConstant.REPAY_TYPE_DUE_OVER;
		// 核对金额
		Map<String, Object> planParam = new HashMap<String, Object>();
		planParam.put("loanNo", loanNo);
		planParam.put("bgRepayNum", bgRepayNum);
		planParam.put("lastStat", "20109003");
		if ("50001001".equals(dvalRepayTyp)) { // 单笔代扣
			planParam.put("enRepayNum", enRepayNum);
			repayKind = PubBusinessConstant.REPAY_TYPE_DUE;
		}
		// 预处理表
		List<AccLoanAcctInst> listinit = null;
		List<AccCapWith> listCap = null;
		Map<String, Object> caseparam = new HashMap<String, Object>();
		// 查询客户信息和银行信息
		AccCustAndBankBo cb = null;
		// 组装数据
		AccPayWith accPayWith = null;
		// 查询金额
		String transAmtTotalFlg = "";
		// 根据贷款编号查询交易方
		try {
			listinit = accLoanAcctInstService.queryForList(planParam);
			planParam.put("enRepayNum", enRepayNum);
			listCap = accCapWithService.queryForList(planParam);
			planParam.put("dvalRepayTyp", dvalRepayTyp);
			caseparam.put("id", caseId);
			caseparam.put("dealTime", new Date());
			caseparam.put("updtDate", new Date());
			cb = caseService.getCustAndBank(loanNo);
			if (cb!=null) {
				if (exportTxtType.equals(PayChanType.LYCHPAY.toString())&&!StringUtils.isBlank(cb.getNoAgree())) {
					throw new ServiceException("该客户没有进行快付通协议绑定!");
				}
				transAmtTotalFlg = getOvduCaseDebit(planParam);
				accPayWith = new AccPayWith();
				accPayWith.setId(RandomUtil.getUUID());
				accPayWith.setLoanNo(loanNo);
				accPayWith.setAcctNo(cb.getAcctNo());
				accPayWith.setAcctName(cb.getAcctName());
				accPayWith.setOpenBank(cb.getBankNo());
				accPayWith.setCertNo(cb.getCertNo());
				accPayWith.setCurRcvAmt(transAmtTotal);
				accPayWith.setBgnRepayNum(bgRepayNum);
				accPayWith.setEndRepayNum(enRepayNum);
				accPayWith.setWithAmt(new BigDecimal(transAmtTotalFlg));
				accPayWith.setStat(FinanceConstant.WITHSTAT_UNDK);
				accPayWith.setRepayType(repayKind);
				accPayWith.setInstDate(new Date());
				accPayWith.setCompanyType(
						param.get("exportTxtType").toString().equals(PayChanType.LYCHPAY.toString()) ? "0" : "1");
			}else {
				throw new ServiceException("该客户银行卡信息有误");
			}
		} catch (Exception e) {
			throw new ServiceException("查询扣款相关信息失败!==="+e.getMessage());
		}
		if (listinit == null) {
			throw new ServiceException("贷款预处理记录为空!");
		} else if (listCap == null) {
			throw new ServiceException("贷款银联代扣记录为空!");
		}
		// 金额小于等于
		if (transAmtTotal.compareTo(new BigDecimal(transAmtTotalFlg)) != 1) {
			if ("50001002".equals(dvalRepayTyp)) { // 提前结清类型
				// 如果状态为正常
				CompAmtUtil.getDgTransBal(loanNo, bgRepayNum, PubBusinessConstant.PLAN_REPAY_TYPE_OVER);
				// 锁表 锁预处理表
				String returnStatus = accLoanAcctInstService.updateAccLoanAcctStat(loanNo, -1,
						FinanceConstant.PRETREAT_STAT_LOCK);
				if (!returnStatus.equals(FinanceConstant.SUCC)) {
					throw new ServiceException("代扣数据［预处理信息ACC_LOAN_ACCT_INST］锁表失败");
				}
				// 改变代扣表状态 为正在扣款
				updateAccCapWith(FinanceConstant.WITHSTAT_DKING, listCap);
				try {
					for (int i = 0; i < listinit.size(); i++) {
						if (listinit.get(i).getCurRcvAmt().compareTo(BigDecimal.ZERO) == 1) {
							bgRepayNum = listinit.get(i).getRepayNum();
							break;
						}
					}
					accPayWith.setBgnRepayNum(bgRepayNum);
					enRepayNum = listinit.get(listinit.size() - 1).getRepayNum();
					accPayWith.setEndRepayNum(enRepayNum);
					// 扣款
					accPayWithService.accPayReg(accPayWith);
				} catch (Exception e) {
					accLoanAcctInstService.updateAccLoanAcctStat(loanNo, -1, FinanceConstant.PRETREAT_STAT_UNLOCK);
					throw new ServiceException(e.getMessage());
				}

			} else if ("50001001".equals(dvalRepayTyp)) { // 单笔
				for (AccLoanAcctInst plan : listinit) {
					// 检查是否已锁定
					CompAmtUtil.getDgTransBal(loanNo, plan.getRepayNum(), PubBusinessConstant.PLAN_REPAY_TYPE_PRE);
				}

				// 锁表 锁预处理表
				for (AccLoanAcctInst plan : listinit) {
					String returnStatus = accLoanAcctInstService.updateAccLoanAcctStatusLock(loanNo,
							plan.getRepayNum());
					if (!returnStatus.equals(FinanceConstant.SUCC)) {
						throw new ServiceException("代扣数据［预处理信息ACC_LOAN_ACCT_INST］锁表失败");
					}
				}
				// 改变代扣表状态 为正在扣款
				updateAccCapWith(FinanceConstant.WITHSTAT_DKING, listCap);
				try {
					// 扣款
					accPayWithService.accPayReg(accPayWith);
				} catch (Exception e) {
					for (AccLoanAcctInst plan : listinit) {
						accLoanAcctInstService.updateAccLoanAcctStatusUnLock(loanNo, plan.getRepayNum());
					}
					throw new ServiceException(e.getMessage());
				}
			}
		} else

		{
			throw new ServiceException("扣款失败，扣款金额核对不正确!");
		}

	}

	/**
	 * 中金实时付款
	 * 
	 * @param param
	 * @param userProFile
	 * @throws ServiceException
	 */
	@Transactional
	private void zjpay(Map<String, Object> param, UserProfile userProFile) throws ServiceException {
		String loanNo = param.get("loanNo").toString();
		String caseId = param.get("caseId").toString();
		String exportTxtType = param.get("exportTxtType").toString();
		String tranChan ="";
		String tranChanMsg ="";
		if (exportTxtType.equals(PayChanType.ZJPAY.toString())) {
			tranChan = PubBusinessConstant.ZJ_SIGLE;
			tranChanMsg="中金代扣";
		}else if (exportTxtType.equals(PayChanType.LIANLIANPAY.toString())) {
			tranChan = PubBusinessConstant.LL_SIGLE;
			tranChanMsg = "连连支付";
		}
		String chanNo = "";
		BigDecimal transAmtTotal = new BigDecimal(param.get("transAmtTotal").toString());
		String dvalRepayTyp = param.get("dvalRepayTyp").toString();
		int bgRepayNum = Integer.parseInt(param.get("bgRepayNum").toString());
		int enRepayNum = Integer.parseInt(param.get("enRepayNum").toString());
		// 核对金额
		Map<String, Object> planParam = new HashMap<String, Object>();
		planParam.put("loanNo", loanNo);
		planParam.put("bgRepayNum", bgRepayNum);
		if ("50001001".equals(dvalRepayTyp)) { // 单笔代扣
			planParam.put("enRepayNum", enRepayNum);
		}
		// 预处理表
		List<AccLoanAcctInst> listinit = null;
		List<AccCapWith> listCap = null;
		Map<String, Object> caseparam = new HashMap<String, Object>();
		// 查询客户信息和银行信息
		AccCustAndBankBo cb = null;
		// 组装数据
		SingleOtherBusiBo singleBo = null;
		// 查询金额
		String transAmtTotalFlg = "";
		// 根据贷款编号查询交易方
		String tranObjByLoanNo = "";
		AccWithholdReg reg = new AccWithholdReg();
		try {
			listinit = accLoanAcctInstService.queryForList(planParam);
			listCap = accCapWithService.queryForList(planParam);
			chanNo = accCapWithService.getChanNoByMap(planParam);
			tranObjByLoanNo = accCapWithService.selectTranObjByLoanNo(loanNo.trim());
			planParam.put("dvalRepayTyp", dvalRepayTyp);
			caseparam.put("id", caseId);
			caseparam.put("loanNo", loanNo);
			caseparam.put("dealTime", new Date());
			caseparam.put("updtDate", new Date());
			cb = caseService.getCustAndBank(loanNo);
			singleBo = new SingleOtherBusiBo();
			singleBo.setId(caseId);
			singleBo.setTranType(exportTxtType);
			singleBo.setLoanNo(loanNo);
			singleBo.setCertNo(cb.getCertNo());
			singleBo.setChanNo(chanNo);
			singleBo.setBankNo(cb.getBankNo());
			singleBo.setBankName(cb.getBankName());
			singleBo.setAcctName(cb.getAcctName());
			singleBo.setAcctNo(cb.getAcctNo());
			singleBo.setRemark(caseId);
			singleBo.setTransAmtTotal(transAmtTotal);
			singleBo.setRepayNum(bgRepayNum);
			singleBo.setRepayDate(listCap.get(0).getRepayDate());
			transAmtTotalFlg = getOvduCaseDebit(planParam);
			reg.setId(RandomUtil.getUUID());
			reg.setBgnNum(bgRepayNum);
			reg.setEndNum(enRepayNum);
			reg.setStaffNo(userProFile.getStaffNo());
			reg.setStaffName(userProFile.getStaffName());
			reg.setOrgNo(userProFile.getOrgNo());
			reg.setBankNo(cb.getBankNo());
			reg.setAcctNum(cb.getAcctNo());
			reg.setAcctName(cb.getAcctName());
			reg.setLoanNo(loanNo);
			reg.setTranAmt(transAmtTotal);
			reg.setRcvAmt(new BigDecimal(transAmtTotalFlg));
			reg.setRepayDate(listCap.get(0).getRepayDate());
			reg.setTranBgnDate(new Date());
			reg.setTranChan(tranChan);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("查询扣款相关信息失败!");
		}
		if (listinit == null) {
			throw new ServiceException("贷款预处理记录为空!");
		}
		// 金额小于等于
		if (transAmtTotal.compareTo(new BigDecimal(transAmtTotalFlg)) != 1) {
			if ("50001002".equals(dvalRepayTyp)) { // 提前结清类型
				reg.setTranTyp(PubBusinessConstant.REPAY_TYPE_DUE_OVER);
				// 如果状态为正常
				CompAmtUtil.getDgTransBal(loanNo, bgRepayNum, PubBusinessConstant.PLAN_REPAY_TYPE_OVER);
				// 锁表 锁预处理表
				String returnStatus = accLoanAcctInstService.updateAccLoanAcctStat(loanNo, -1,
						FinanceConstant.PRETREAT_STAT_LOCK);
				if (!returnStatus.equals(FinanceConstant.SUCC)) {
					throw new ServiceException("代扣数据［预处理信息ACC_LOAN_ACCT_INST］锁表失败");
				}
				// 改变代扣表状态 为正在扣款
				singleBo.setId(updateAccCapWith(FinanceConstant.WITHSTAT_DKING, listCap));
				// 扣款
				SingleDkResultBo result = null;
				try {
					result = accCapWithService.singleRepayOtherBusi(singleBo, userProFile);
				} catch (Exception e) {
					e.printStackTrace();
					try {
						// 修改逾期案件处理结果
						caseparam.put("withStat", PubBusinessConstant.REPAY_DG_STATUS_FAIL);
						caseService.updateByPrimaryKeySelective(caseparam);
						// 改变代扣表状态 为扣款失败
						updateAccCapWith(FinanceConstant.WITHSTAT_DKFIELD, listCap);
					} finally {
						// 解锁
						accLoanAcctInstService.updateAccLoanAcctStat(loanNo, -1, FinanceConstant.PRETREAT_STAT_UNLOCK);
					}
					throw new ServiceException("扣款失败，"+tranChanMsg+"接口调用失败!--" + e.getMessage());
				}
				if (result == null) {
					try {
						// 修改逾期案件处理结果
						caseparam.put("withStat", PubBusinessConstant.REPAY_DG_STATUS_FAIL);
						caseService.updateByPrimaryKeySelective(caseparam);
						// 改变代扣表状态 为扣款失败
						updateAccCapWith(FinanceConstant.WITHSTAT_DKFIELD, listCap);
					} finally {
						// 解锁
						accLoanAcctInstService.updateAccLoanAcctStat(loanNo, -1, FinanceConstant.PRETREAT_STAT_UNLOCK);
					}
					throw new ServiceException("扣款失败，"+tranChanMsg+"接口无返回值!");
				} else if (result.getRetItem() != null && result.getRetItem().getRetCode() != null
						&& result.getRetItem().getRetCode().equals(FinanceConstant.TRAN_ST_SUCC)) { // 代扣成功
					reg.setTxsn(result.getReqSn());
					reg.setWithStat(FinanceConstant.WITHSTAT_DKSUCC);
					reg.setTranEndDate(new Date());
					reg.setSettleStat("10001002");
					try {
						accWithholdRegService.insertAccWithholdReg(reg);
						// 提前结清登记表
						AccRepayAdvanReg accRepayAdvanReg = new AccRepayAdvanReg();
						accRepayAdvanReg.setId(RandomUtil.getUUID());
						accRepayAdvanReg.setLoanNo(loanNo);
						accRepayAdvanReg.setRepayDate(listinit.get(0).getRepayDate());
						accRepayAdvanReg.setRepayNum(listinit.get(0).getRepayNum());
						accRepayAdvanReg.setTranDate(new Date());
						accRepayAdvanReg.setTranAmt(transAmtTotal);
						accRepayAdvanReg.setCustName(cb.getAcctName());
						accRepayAdvanReg.setRepayChan(tranChan);
						accRepayAdvanReg.setConfNo(PubBusinessConstant.PLAN_REPAY_TYPE_OVER);
						accRepayAdvanReg.setCustAcct(cb.getAcctNo());
						accRepayAdvanReg.setStat(PubBusinessConstant.REPAY_DG_STATUS_PAIED);
						accRepayAdvanReg.setTranDesc("催收提前结清");
						accRepayAdvanReg.setTranStaff(userProFile.getStaffNo());
						accRepayAdvanReg.setTranOrg(userProFile.getOrgNo());
						accRepayAdvanReg.setInstDate(new Date());
						accRepayAdvanReg.setSetlType(FinanceConstant.CLEARN_TYPE_YQ);
						accRepayAdvanRegService.insert(accRepayAdvanReg);
						// 加流水
						AccRepayFlow accRepayFlow = new AccRepayFlow();
						accRepayFlow.setId(RandomUtil.getUUID());
						accRepayFlow.setLoanNo(loanNo);
						accRepayFlow.setRepayDate(listinit.get(0).getRepayDate());
						accRepayFlow.setTranType(PubBusinessConstant.TRANDTYPE_ZC);
						accRepayFlow.setTranChan(tranChan);
						accRepayFlow.setTranDate(DateUtils.getYear() + DateUtils.getMonth() + DateUtils.getDay());
						accRepayFlow.setTranAmt(transAmtTotal);
						accRepayFlow.setAcctNo(cb.getAcctNo());
						accRepayFlow.setAcctName(cb.getAcctName());
						accRepayFlow.setLoanStat("20101420");
						accRepayFlow.setSetlFlag(CommonConstant.COMMON_NO);
						accRepayFlow.setTranObj(tranObjByLoanNo);
						accRepayFlow.setInstDate(DateUtils.getCurrentTimestamp());
						accRepayFlow.setRepayNum(listinit.get(0).getRepayNum());
						accRepayFlow.setCntAcctNo(ParamUtils.getParam("compAcctNo"));
						accRepayFlow.setCntAcctName("深圳柠檬金融信息服务有限公司");
						accRepayFlow.setTranOrg(userProFile.getStaffNo());
						accRepayFlow.setTranStaff(userProFile.getStaffName());
						accRepayFlowService.insert(accRepayFlow);
						// 修改逾期案件处理结果
						caseparam.put("withStat", PubBusinessConstant.REPAY_DG_STATUS_PAIED);
						caseparam.put("dealStat", PubBusinessConstant.COLLECTION_YCL);
						caseService.updateByPrimaryKeySelective(caseparam);
						// 改变代扣表状态 为扣款成功
						updateAccCapWith(FinanceConstant.WITHSTAT_DKSUCC, listCap);
						if ("50001002".equals(dvalRepayTyp)) { // 还款类型为提前结清
							// 成功后修改预处理表为已结清
							accLoanAcctInstService.updateAccLoanAcctStat(loanNo, -1,
									FinanceConstant.PRETREAT_STAT_CLEARD);
						}
						// 新增经办登记
						String handDetailTyp = PubBusinessConstant.LOANHANDTYPE_DULREPAY;
						String handPersonNo = userProFile.getStaffNo();
						String handPersonName = userProFile.getStaffName();
						Date handDate = DateUtils.getCurrentDate();
						appLoanHandService.saveAppLoanHand(caseparam.get("loanNo").toString(), cb.getCustNo(),
								cb.getCustName(), handDetailTyp, PubBusinessConstant.LOANHANDMODEL_SYS, handPersonNo,
								handPersonName, handDate, "逾期案件处理还款[提前结清]", PubBusinessConstant.CUST_ZC);

					} catch (Exception e) {
						try {
							// 修改逾期案件处理结果
							caseparam.put("withStat", PubBusinessConstant.REPAY_DG_STATUS_FAIL);
							caseService.updateByPrimaryKeySelective(caseparam);
							// 改变代扣表状态 为扣款失败
							updateAccCapWith(FinanceConstant.WITHSTAT_DKFIELD, listCap);
						} finally {
							// 解锁
							accLoanAcctInstService.updateAccLoanAcctStat(loanNo, -1,
									FinanceConstant.PRETREAT_STAT_UNLOCK);
						}
						throw new ServiceException("添加扣款流水失败!" + e.getMessage());
					}
				} else if (result.getRetItem() != null && result.getRetItem().getRetCode() != null
						&& result.getRetItem().getRetCode().equals(FinanceConstant.TRAN_ST_DEALING)) {
					reg.setTxsn(result.getReqSn());
					reg.setWithStat(FinanceConstant.WITHSTAT_DKING);
					reg.setSettleStat("10001002");
					accWithholdRegService.insertAccWithholdReg(reg);

					throw new ServiceException("正在扣款中...." + result.getRetItem().getErrMsg());
				} else {
					reg.setTxsn(result.getReqSn());
					reg.setWithStat(FinanceConstant.WITHSTAT_DKFIELD);
					reg.setTranEndDate(new Date());
					reg.setSettleStat("10001002");
					accWithholdRegService.insertAccWithholdReg(reg);
					try {
						// 修改逾期案件处理结果
						caseparam.put("withStat", PubBusinessConstant.REPAY_DG_STATUS_FAIL);
						caseService.updateByPrimaryKeySelective(caseparam);
						// 改变代扣表状态 为扣款失败
						updateAccCapWith(FinanceConstant.WITHSTAT_DKFIELD, listCap);
					} finally {
						// 解锁
						accLoanAcctInstService.updateAccLoanAcctStat(loanNo, -1, FinanceConstant.PRETREAT_STAT_UNLOCK);
					}
					throw new ServiceException("扣款失败!" + result.getRetItem().getErrMsg());
				}

			} else if ("50001001".equals(dvalRepayTyp)) { // 单笔
				for (AccLoanAcctInst plan : listinit) {
					// 检查是否已锁定
					CompAmtUtil.getDgTransBal(loanNo, plan.getRepayNum(), PubBusinessConstant.PLAN_REPAY_TYPE_PRE);
				}
				reg.setTranTyp(PubBusinessConstant.REPAY_TYPE_DUE);
				// 锁表 锁预处理表
				for (AccLoanAcctInst plan : listinit) {
					String returnStatus = accLoanAcctInstService.updateAccLoanAcctStatusLock(loanNo,
							plan.getRepayNum());
					if (!returnStatus.equals(FinanceConstant.SUCC)) {
						throw new ServiceException("代扣数据［预处理信息ACC_LOAN_ACCT_INST］锁表失败");
					}
				}
				// 改变代扣表状态 为正在扣款
				singleBo.setId(updateAccCapWith(FinanceConstant.WITHSTAT_DKING, listCap));
				// 扣款
				SingleDkResultBo result = null;
				try {
					result = accCapWithService.singleRepayOtherBusi(singleBo, userProFile);
				} catch (Exception e) {
					try {
						// 修改逾期案件处理结果
						caseparam.put("withStat", PubBusinessConstant.REPAY_DG_STATUS_FAIL);
						caseService.updateByPrimaryKeySelective(caseparam);
						// 改变代扣表状态 为扣款失败
						updateAccCapWith(FinanceConstant.WITHSTAT_DKFIELD, listCap);
					} finally {
						// 解锁
						for (AccLoanAcctInst plan : listinit) {
							accLoanAcctInstService.updateAccLoanAcctStatusUnLock(loanNo, plan.getRepayNum());
						}
					}
					throw new ServiceException("扣款失败，"+tranChanMsg+"接口调用失败!--", e.getMessage());
				}
				if (result == null) {
					try {
						// 修改逾期案件处理结果
						caseparam.put("withStat", PubBusinessConstant.REPAY_DG_STATUS_FAIL);
						caseService.updateByPrimaryKeySelective(caseparam);
						// 改变代扣表状态 为扣款失败
						updateAccCapWith(FinanceConstant.WITHSTAT_DKFIELD, listCap);
					} finally {
						// 解锁
						for (AccLoanAcctInst plan : listinit) {
							accLoanAcctInstService.updateAccLoanAcctStatusUnLock(loanNo, plan.getRepayNum());
						}
					}
					throw new ServiceException("扣款失败，"+tranChanMsg+"接口无返回值!");
				} else if (result.getRetItem() != null && result.getRetItem().getRetCode() != null
						&& result.getRetItem().getRetCode().equals(FinanceConstant.TRAN_ST_SUCC)) { // 代扣成功
					reg.setTxsn(result.getReqSn());
					reg.setWithStat(FinanceConstant.WITHSTAT_DKSUCC);
					reg.setTranEndDate(new Date());
					reg.setSettleStat("10001002");
					accWithholdRegService.insertAccWithholdReg(reg);
					boolean successFlg = false;
					List<Map<String, Object>> acctList = new ArrayList<>();
					try {
						// 成功后修改预处理表金额 和加流水
						Map<String, Object> accAmtMap = null;
						AccRepayFlow accRepayFlow = null;
						BigDecimal forAmt = transAmtTotal;
						for (AccLoanAcctInst init : listinit) {
							BigDecimal inAmt = BigDecimal.ZERO;
							boolean amtFlg = false;
							// 还款金额大于某一期的金额
							if (forAmt.subtract(init.getCurRcvAmt()).compareTo(BigDecimal.ZERO) != -1) {
								forAmt = forAmt.subtract(init.getCurRcvAmt());
								inAmt = init.getCurRcvAmt();
								amtFlg = true;
							} else if (forAmt.compareTo(BigDecimal.ZERO) != -1) {
								inAmt = forAmt;
								amtFlg = true;
								forAmt = forAmt.subtract(init.getCurRcvAmt());
							}
							if (amtFlg) {
								accRepayFlow = new AccRepayFlow();
								accRepayFlow.setId(RandomUtil.getUUID());
								accRepayFlow.setLoanNo(loanNo);
								accRepayFlow.setRepayDate(init.getRepayDate());
								accRepayFlow.setTranType(PubBusinessConstant.TRANDTYPE_ZC);
								accRepayFlow.setTranChan(tranChan);
								accRepayFlow.setTranDate(DateUtils.getYear() + DateUtils.getMonth() + DateUtils.getDay());
								accRepayFlow.setTranObj(tranObjByLoanNo);
								accRepayFlow.setAcctNo(cb.getAcctNo());
								accRepayFlow.setAcctName(cb.getAcctName());
								accRepayFlow.setLoanStat("20101420");
								accRepayFlow.setSetlFlag(CommonConstant.COMMON_NO);
								accRepayFlow.setInstDate(DateUtils.getCurrentTimestamp());
								accRepayFlow.setRepayNum(init.getRepayNum());
								accRepayFlow.setCntAcctNo(ParamUtils.getParam("compAcctNo"));
								accRepayFlow.setCntAcctName("深圳柠檬金融信息服务有限公司");
								accRepayFlow.setTranOrg(userProFile.getOrgNo());
								accRepayFlow.setTranStaff(userProFile.getStaffName());
								accRepayFlow.setTranAmt(inAmt);
								// 更新预处理表
								accAmtMap = new HashMap<String, Object>();
								accAmtMap.put("loanNo", loanNo);
								accAmtMap.put("repayNum", init.getRepayNum());
								accAmtMap.put("updt", new Date());
								accAmtMap.put("curRcvAmt", inAmt);
								acctList.add(accAmtMap);
								accRepayFlowService.insert(accRepayFlow);
							}
						}
						PlLoanOvduCase caseinfo = caseService.getByPrimaryKey(caseId);
						// 判断是否所有逾期扣款完成
						if (caseinfo.getEndNum() == enRepayNum
								&& transAmtTotal.compareTo(new BigDecimal(transAmtTotalFlg)) == 0) {
							caseparam.put("dealStat", PubBusinessConstant.COLLECTION_YCL);
							// 修改逾期案件扣款结果
							caseparam.put("withStat", PubBusinessConstant.REPAY_DG_STATUS_PAIED);
							// 改变代扣表状态 为扣款成功
							updateAccCapWith(FinanceConstant.WITHSTAT_DKSUCC, listCap);
						} else {
							// 修改逾期案件扣款结果 未结束
							caseparam.put("withStat", PubBusinessConstant.REPAY_DG_STATUS_PART);
							updateAccCapWith(FinanceConstant.WITHSTAT_UNDK, listCap);
						}
						caseService.updateByPrimaryKeySelective(caseparam);
						// 新增经办登记
						String handDetailTyp = PubBusinessConstant.LOANHANDTYPE_DULREPAY;
						String handPersonNo = userProFile.getStaffNo();
						String handPersonName = userProFile.getStaffName();
						Date handDate = DateUtils.getCurrentDate();
						appLoanHandService.saveAppLoanHand(loanNo, cb.getCustNo(), cb.getCustName(), handDetailTyp,
								PubBusinessConstant.LOANHANDMODEL_SYS, handPersonNo, handPersonName, handDate,
								"逾期案件处理还款[正常还款" + bgRepayNum + "-" + enRepayNum + "期]", PubBusinessConstant.CUST_ZC);
						successFlg = true;
					} catch (Exception e) {
						successFlg = false;
						// 修改逾期案件处理结果
						caseparam.put("withStat", PubBusinessConstant.REPAY_DG_STATUS_FAIL);
						caseService.updateByPrimaryKeySelective(caseparam);
						// 改变代扣表状态 为扣款失败
						updateAccCapWith(FinanceConstant.WITHSTAT_DKFIELD, listCap);
						throw new ServiceException("添加扣款流水失败!");
					} finally {
						if (successFlg) {
							for (Map<String, Object> accAmtMap : acctList) {
								// 更新预处理表
								accLoanAcctInstService.updateAccLoanInstCurRcvAmtAndUnLock(accAmtMap);
							}
						} else {
							// 解锁
							for (AccLoanAcctInst plan : listinit) {
								accLoanAcctInstService.updateAccLoanAcctStatusUnLock(loanNo, plan.getRepayNum());
							}
						}
					}
				} else if (result.getRetItem() != null && result.getRetItem().getRetCode() != null
						&& result.getRetItem().getRetCode().equals(FinanceConstant.TRAN_ST_DEALING)) {
					reg.setTxsn(result.getReqSn());
					reg.setWithStat(FinanceConstant.WITHSTAT_DKING);
					reg.setSettleStat("10001002");
					accWithholdRegService.insertAccWithholdReg(reg);
					// 开启轮询
					throw new ServiceException("正在扣款中...." + result.getRetItem().getErrMsg());
				} else {
					try {
						reg.setTxsn(result.getReqSn());
						reg.setWithStat(FinanceConstant.WITHSTAT_DKFIELD);
						reg.setTranEndDate(new Date());
						reg.setSettleStat("10001002");
						accWithholdRegService.insertAccWithholdReg(reg);
						// 修改逾期案件处理结果
						caseparam.put("withStat", PubBusinessConstant.REPAY_DG_STATUS_FAIL);
						caseService.updateByPrimaryKeySelective(caseparam);
						// 改变代扣表状态 为扣款失败
						updateAccCapWith(FinanceConstant.WITHSTAT_DKFIELD, listCap);
					} finally {
						// 解锁
						for (AccLoanAcctInst plan : listinit) {
							accLoanAcctInstService.updateAccLoanAcctStatusUnLock(loanNo, plan.getRepayNum());
						}
					}
					throw new ServiceException("扣款失败!" + result.getRetItem().getErrMsg());
				}
			}
		} else {
			throw new ServiceException("扣款失败，扣款金额核对不正确!");
		}
	}
	
	/**
	 * 通联实时付款
	 * 
	 * @param param
	 * @param userProFile
	 * @throws ServiceException
	 */
	@Transactional
	private void allinpay(Map<String, Object> param, UserProfile userProFile) throws ServiceException {
	}

	/**
	 * 银联付款
	 * 
	 * @param param
	 * @param userProFile
	 * @throws ServiceException
	 */
	@Transactional
	private void chinaPay(Map<String, Object> param, UserProfile userProFile) throws ServiceException {
	}

	/**
	 * 通联付款 (走文件 已放弃)
	 * 
	 * @param param
	 * @param userProFile
	 * @throws ServiceException
	 */
	@Deprecated
	@Transactional
	private void lychPay(Map<String, Object> param, UserProfile userProFile) throws ServiceException {
	}

	/** 修改代扣表状态 */
	@Transactional
	private String updateAccCapWith(String withStat, List<AccCapWith> listCap) throws ServiceException {
		Map<String, Object> withMap = null;
		
		for (AccCapWith with : listCap) {
			withMap = new HashMap<String, Object>();
			withMap.put("id", with.getId());
			withMap.put("withStat", withStat);
			withMap.put("updtDate", new Date());
			accCapWithService.updateAccCapWithStatById(withMap);
		}
		String ids ="";
		if (listCap.size()>0) {
			 ids = listCap.get(0).getId();
		}
		return ids;
	}

	/**
	 * 获取客户联系人列表
	 * 
	 * @param param
	 *            客户编号：custNo
	 * @return
	 */
	public List<AppLoanCustInfoDto> queryLoanCustInfoForList(Map<String, Object> param) {
		List<AppLoanCustInfoBo> infoList = caseService.queryLoanCustInfoForList(param);
		return BeanUtils.copyProperties(infoList, AppLoanCustInfoDto.class);
	}

	/**
	 * 获取逾期案件结果登记信息
	 * 
	 * @param param
	 *            贷款编号:loanNo
	 * @return
	 */
	public List<PlLoanOvduHandDto> queryLoanOvduHandForList(Map<String, Object> param) {
		List<PlLoanOvduHand> handList = loanOvduHandService.queryLoanOvduHandForList(param);
		return BeanUtils.copyProperties(handList, PlLoanOvduHandDto.class);
	}

	/**
	 * 经理查询对应的还款信息
	 * 
	 * @param page
	 * @param profile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public Page<PlLoanOvduCaseRetAndFlowDto> queryLoanOvduCaseFlowPage(Page<PlLoanOvduCaseRetAndFlowDto> page,
			UserProfile profile) throws ServiceException, AppException {
		Set<String> roles = profile.getRoleNoSet();
		boolean flg = false;
		for (String role : roles) {
			if (PubBusinessConstant.ROLE_R_SYS_SUPER.equals(role)) {
				flg = true;
				break;
			} else if (PubBusinessConstant.ROLE_R_COLLEC_MGR.equals(role)) {
				flg = true;
				break;
			}
		}
		if (!flg) {
			throw new ServiceException("查询失败，本用户无查询权限!");
		}
		Page<PlLoanOvduCaseRetAndFlowBo> pageRsl = caseService
				.queryLoanOvduCaseFlowPage(page.toPage(PlLoanOvduCaseRetAndFlowBo.class));
		Page<PlLoanOvduCaseRetAndFlowDto> caseOutDtoPage = pageRsl.toPage(PlLoanOvduCaseRetAndFlowDto.class);
		return caseOutDtoPage;
	}

	/**
	 * 经理查询对应的还款信息导出
	 * 
	 * @param page
	 * @param profile
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public List<Map<String, Object>> queryLoanOvduCaseFlowList(Map<String, Object> param, UserProfile userProFile)
			throws ServiceException, AppException {
		Set<String> roles = userProFile.getRoleNoSet();
		boolean flg = false;
		for (String role : roles) {
			if (PubBusinessConstant.ROLE_R_SYS_SUPER.equals(role)) {
				flg = true;
				break;
			} else if (PubBusinessConstant.ROLE_R_COLLEC_MGR.equals(role)) {
				flg = true;
				break;
			}
		}
		if (!flg) {
			throw new ServiceException("查询失败，本用户无查询权限!");
		}

		// 逾期案件分页查询结果
		List<PlLoanOvduCaseRetAndFlowBo> pageRsl = caseService.queryLoanOvduCaseFlowList(param);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		if (pageRsl != null && pageRsl.size() > 0) {
			String maxExropt = ParamUtils.getParam("maxExroptNum");
			if (StringUtils.isEmpty(maxExropt)) {
				maxExropt = "3000";
			}
			if (pageRsl.size() > Integer.valueOf(maxExropt)) {
				throw new ServiceException("案件列表超过" + maxExropt + "条，请等待后台跑批处理...");
			} else {
				Map<String, Object> handMap = null;
				StringBuilder sb = null;
				for (PlLoanOvduCaseRetAndFlowBo dto : pageRsl) {
					handMap = new HashMap<>();
					handMap.put("handDetail", PubBusinessConstant.LOANHANDTYPE_COLLECT);
					handMap.put("loanNo", dto.getLoanNo());
					// 封装 对应的 备注信息
					List<AppLoanHand> listhand = appLoanHandService.queryForList(handMap);
					if (listhand != null && listhand.size() > 0) {
						sb = new StringBuilder("");
						for (int i = 0; i < listhand.size(); i++) {
							sb.append(i + 1).append(".处理人:").append(listhand.get(i).getHandPersonName())
									.append(";处理信息：").append(listhand.get(i).getRemark()).append(";");
						}
						dto.setDealDesc(sb.toString());
					}
					String stat = dto.getDealStat();
					dto.setDealStat(stat == null || stat.equals(PubBusinessConstant.COLLECTION_WCL) ? "未处理"
							: stat.equals(PubBusinessConstant.COLLECTION_YCL) ? "已处理" : "失效");
					resultList.add(BeanUtils.bean2map(dto));
				}
			}
		}
		return resultList;
	}

	/**
	 * 短信发送接口
	 */
	@Override
	public void sendMsg(Map<String, Object> param) throws ServiceException {
		if (!param.get("flag").equals("sensitive")) {
			param.remove("flag");
		}
		String url = ParamUtils.getParam("msgSendUrl");
		List<NameValuePair> parameters = new ArrayList<>();
		Set<String> keys = param.keySet();
		for (String key : keys) {
			parameters.add(new BasicNameValuePair(key, param.get(key).toString()));
		}
		try {
			HttpsInvokerUtil.executeHttpPost(url, parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取短信发送模版
	 */
	@Override
	public List<PubMessageModelDto> getMsg(Map<String, Object> param) throws ServiceException {
		List<PubMessageModel> listModel = new ArrayList<>();
		if (param.get("messageTyp").toString().equals("coll")) { // 催收
			listModel = collMsgModel(param);
		} else if (param.get("messageTyp").toString().equals("operate")) { // 运营
			listModel = operateMsgModel(param);
		} else if (param.get("messageTyp").toString().equals("customer")) {// 客服
			listModel = customerMsgModel(param);
		}
		return BeanUtils.copyProperties(listModel, PubMessageModelDto.class);
	}

	/**
	 * 客服短信模版获取
	 * 
	 * @param param
	 * @return
	 */
	private List<PubMessageModel> customerMsgModel(Map<String, Object> param) {
		String name = param.get("name") + "";
		String loanNo = param.get("loanNo") + "";
		String messageTyp = param.get("messageTyp") + "";
		Map<String, Object> modelparam = new HashMap<>();
		modelparam.put("msgTyp", messageTyp);
		modelparam.put("messageStat", "1");
		List<PubMessageModel> listModel = pubMessageModelService.queryForList(modelparam);
		Map<String, Object> acct = accRepayAdvanRegService.getAppLoanAcct(loanNo);
		Map<String, Object> caseParam = new HashMap<>();
		caseParam.put("dvalRepayTyp", "50001002");
		caseParam.put("loanNo", loanNo);
		caseParam.put("bgRepayNum", 1);
		String curRcvAmt = "0.00"; // 当前欠款
		try {
			curRcvAmt = getOvduCaseDebit(caseParam);
		} catch (Exception e) {
		}
		for (PubMessageModel model : listModel) {
			String msg = model.getMsg();
			msg = msg.replace("{name}", name);
			msg = msg.replace("{applyDate}", acct.get("applyDate").toString()).replace("{loanNo}", loanNo)
					.replace("{todayDate}", acct.get("todayDate").toString()).replace("{overAmt}", curRcvAmt);
			model.setMsg(msg);
			if (model.getMgbz() != null && !model.getMgbz().equals("0")) {
				model.setMgbz("sensitive");
			}
		}
		return listModel;
	}

	/**
	 * 运营短信模版获取
	 * 
	 * @param param
	 * @return
	 */
	private List<PubMessageModel> operateMsgModel(Map<String, Object> param) {
		String name = param.get("name") + "";
		String loanNo = param.get("loanNo") + "";
		String messageTyp = param.get("messageTyp") + "";
		Map<String, Object> modelparam = new HashMap<>();
		modelparam.put("msgTyp", messageTyp);
		modelparam.put("messageStat", "1");
		List<PubMessageModel> listModel = pubMessageModelService.queryForList(modelparam);
		Map<String, Object> acct = accRepayAdvanRegService.getAppLoanAcct(loanNo);
		for (PubMessageModel model : listModel) {
			String msg = model.getMsg();
			msg = msg.replace("{name}", name);
			msg = msg.replace("{applyDate}", acct.get("applyDate").toString()).replace("{loanNo}", loanNo)
					.replace("{todayDate}", acct.get("todayDate").toString());
			model.setMsg(msg);
			if (model.getMgbz() != null && !model.getMgbz().equals("0")) {
				model.setMgbz("sensitive");
			}
		}
		return listModel;
	}

	
	/**
	 * 催收短信模版获取
	 * 
	 * @param param
	 * @return
	 */
	private List<PubMessageModel> collMsgModel(Map<String, Object> param) {
		String name = param.get("name") + "";
		String loanNo = param.get("loanNo") + "";
		String caseId = param.get("caseId") + "";
		String messageTyp = param.get("messageTyp") + "";
		String contactRel = param.get("contactRel")+"";
		Map<String, Object> modelparam = new HashMap<>();
		PlLoanOvduCase caseinfo = caseService.getByPrimaryKey(caseId);
		modelparam.put("msgTyp", messageTyp);
		modelparam.put("messageStat", "1");
		List<PubMessageModel> listModel = pubMessageModelService.queryForList(modelparam);
		modelparam.put("msgTyp", "colloth");
		List<PubMessageModel> otherlistModel = pubMessageModelService.queryForList(modelparam);
		AccCustAndBankBo cb = caseService.getCustAndBank(loanNo);
		Map<String, Object> caseParam = new HashMap<>();
		caseParam.put("dvalRepayTyp", "50001001");
		caseParam.put("loanNo", loanNo);
		caseParam.put("bgRepayNum", caseinfo.getBgnNum());
		caseParam.put("enRepayNum", caseinfo.getEndNum());
		List<AccLoanInst> listInst = accLoanInstService.queryInstForList(caseParam);
		String custName = cb.getCustName();
		String curRcvAmt = getOvduCaseDebit(caseParam); // 当前欠款
		String totalAmt = curRcvAmt;// 总欠款
		String date = caseinfo.getRepayDate(); // 还款日期
		date = DateUtils.dateToStr(DateUtils.strToDate(date, "yyyyMMdd"), "yyyy年MM月dd日");
		String bankName = SimpleCodeUtils.getCodeByName("chargeOpenOrg", cb.getBankName()); // 还款银行名称
		String acctNoSub4 = cb.getAcctNo().substring(cb.getAcctNo().length() - 4, cb.getAcctNo().length()); // 银行卡后4位
		BigDecimal lateAmt = BigDecimal.ZERO; // 违约金
		for (AccLoanInst inst : listInst) {
			lateAmt.add(inst.getRcvPunAmt());
		}
		if (custName.equals(name)) { //给本人发
			for (PubMessageModel model : listModel) {
				String msg = model.getMsg();
				msg = msg.replace("{name}", name);
				if (model.getMessageCode().equals("coll_cust")) {// 家人/朋友
					msg = msg.replace("{cust}", custName);
				} else {
					msg = msg.replace("{lateAmt}", lateAmt.toString()).replace("{totalAmt}", totalAmt)
							.replace("{date}", date).replace("{curRcvAmt}", curRcvAmt).replace("{bankName}", bankName)
							.replace("{acctNoSub4}", acctNoSub4);
				}
				model.setMsg(msg);
				if (model.getMgbz() != null && !model.getMgbz().equals("0")) {
					model.setMgbz("sensitive");
				}
			}
			return listModel;
		}else { //给其他联系人发
			for (PubMessageModel model : otherlistModel) {
				String msg = model.getMsg();
				msg = msg.replace("{name}", name).replace("{custName}", custName).replace("{otherType}","");
				msg = msg.replace("{lateAmt}", lateAmt.toString()).replace("{totalAmt}", totalAmt).replace("{curRcvAmt}", curRcvAmt).replace("{bankName}", bankName)
						.replace("{acctNoSub4}", acctNoSub4);
				model.setMsg(msg);
				if (model.getMgbz() != null && !model.getMgbz().equals("0")) {
					model.setMgbz("sensitive");
				}
			}
			return otherlistModel;
		}
	}
	
	/**
	 * 催收短信模版获取
	 * 
	 * @param param
	 * @return
	 *//*
	private List<PubMessageModel> collMsgModel(Map<String, Object> param) {
		String name = param.get("name") + "";
		String loanNo = param.get("loanNo") + "";
		String caseId = param.get("caseId") + "";
		String messageTyp = param.get("messageTyp") + "";
		Map<String, Object> modelparam = new HashMap<>();
		PlLoanOvduCase caseinfo = caseService.getByPrimaryKey(caseId);
		modelparam.put("msgTyp", messageTyp);
		modelparam.put("messageStat", "1");
		List<PubMessageModel> listModel = pubMessageModelService.queryForList(modelparam);
		AccCustAndBankBo cb = caseService.getCustAndBank(loanNo);
		Map<String, Object> caseParam = new HashMap<>();
		caseParam.put("dvalRepayTyp", "50001001");
		caseParam.put("loanNo", loanNo);
		caseParam.put("bgRepayNum", caseinfo.getBgnNum());
		caseParam.put("enRepayNum", caseinfo.getEndNum());
		List<AccLoanInst> listInst = accLoanInstService.queryInstForList(caseParam);
		for (PubMessageModel model : listModel) {
			String msg = model.getMsg();
			msg = msg.replace("{name}", name);
			BigDecimal lateAmt = BigDecimal.ZERO; // 违约金
			for (AccLoanInst inst : listInst) {
				lateAmt.add(inst.getRcvPunAmt());
			}
			String custName = cb.getCustName();
			String curRcvAmt = getOvduCaseDebit(caseParam); // 当前欠款
			String totalAmt = curRcvAmt;// 总欠款
			String date = caseinfo.getRepayDate(); // 还款日期
			String bankName = SimpleCodeUtils.getCodeByName("chargeOpenOrg", cb.getBankName()); // 还款银行名称
			String acctNoSub4 = cb.getAcctNo().substring(cb.getAcctNo().length() - 4, cb.getAcctNo().length()); // 银行卡后4位
			if (model.getMessageCode().equals("coll_cust")) {// 家人/朋友
				msg = msg.replace("{cust}", custName);
			} else {
				date = DateUtils.dateToStr(DateUtils.strToDate(date, "yyyyMMdd"), "yyyy年MM月dd日");
				msg = msg.replace("{lateAmt}", lateAmt.toString()).replace("{totalAmt}", totalAmt)
						.replace("{date}", date).replace("{curRcvAmt}", curRcvAmt).replace("{bankName}", bankName)
						.replace("{acctNoSub4}", acctNoSub4);
			}
			model.setMsg(msg);
			if (model.getMgbz() != null && !model.getMgbz().equals("0")) {
				model.setMgbz("sensitive");
			}
		}
		return listModel;
	}
*/
	/**
	 * 手动更新回盘结果
	 */
	@Transactional
	public void updateInstStat(String loanNo) throws ServiceException, AppException {
		Map<String, Object> param = new HashMap<>();
		param.put("loanNo", loanNo);
		param.put("instStat", FinanceConstant.PRETREAT_STAT_LOCK);
		// 预处理表
		List<AccLoanAcctInst> listinit = accLoanAcctInstService.queryForList(param);
		if (listinit != null && listinit.size() > 0) {
			Map<String, Object> tranLog = accCapWithService.findTranLog(loanNo, listinit.get(0).getRepayDate());
			if (tranLog != null) {
				SingleDkResultBo result = accCapWithService.singleQueryStat(tranLog.get("FILE_NAME").toString());
				if (result != null) {
					Map<String, Object> planParam = new HashMap<String, Object>();
					planParam.put("loanNo", loanNo);
					planParam.put("bgRepayNum", listinit.get(0).getRepayNum());
					planParam.put("enRepayNum", listinit.get(listinit.size() - 1).getRepayNum());
					planParam.put("dvalRepayTyp", "50001001");
					planParam.put("dealStat", "50002001");
					planParam.put("bgnNum", listinit.get(0).getRepayNum());
					planParam.put("endNum", listinit.get(listinit.size() - 1).getRepayNum());
					List<AccCapWith> listCap = accCapWithService.queryForList(planParam);
					List<PlLoanOvduCase> caseList = caseService.queryForList(planParam);
					// 查询客户信息和银行信息
					AccCustAndBankBo cb = caseService.getCustAndBank(loanNo);
					// 单笔
					if (result.getRetItem() != null && result.getRetItem().getRetCode() != null
							&& result.getRetItem().getRetCode().equals(FinanceConstant.TRAN_ST_DEALING)) {
						// 依然还在代扣中 不做任何处理 继续轮询
						throw new ServiceException("该笔贷款正在扣款中,请稍后再试");
					} else if (result != null
							&& result.getRetItem().getRetCode().equals(FinanceConstant.TRAN_ST_SUCC)) { // 代扣成功
						boolean successFlg = false;
						List<Map<String, Object>> acctList = new ArrayList<>();
						if (caseList != null && caseList.size() > 0) { // 逾期
							Map<String, Object> caseparam = new HashMap<String, Object>();
							caseparam.put("id", caseList.get(0).getId());
							caseparam.put("loanNo", loanNo);
							caseparam.put("dealTime", new Date());
							caseparam.put("updtDate", new Date());
							try {
								// 成功后修改预处理表金额 和加流水
								Map<String, Object> accAmtMap = null;
								AccRepayFlow accRepayFlow = null;
								BigDecimal forAmt = new BigDecimal(result.getRetItem().getAmount());
								forAmt = forAmt.divide(new BigDecimal("100"));
								BigDecimal transAmtTotal = new BigDecimal("0");
								BigDecimal resultAmt = forAmt;
								BigDecimal inAmt = BigDecimal.ZERO;
								boolean amtFlg = false;
								for (AccLoanAcctInst init : listinit) {
									transAmtTotal = transAmtTotal.add(init.getCurRcvAmt());
									// 还款金额大于某一期的金额
									if (forAmt.subtract(init.getCurRcvAmt()).compareTo(BigDecimal.ZERO) != -1) {
										forAmt = forAmt.subtract(init.getCurRcvAmt());
										inAmt = init.getCurRcvAmt();
										amtFlg = true;
									} else if (forAmt.compareTo(BigDecimal.ZERO) != -1) {
										inAmt = forAmt;
										amtFlg = true;
										forAmt = forAmt.subtract(init.getCurRcvAmt());
									}
									// 更新预处理表
									accAmtMap = new HashMap<String, Object>();
									accAmtMap.put("loanNo", loanNo);
									accAmtMap.put("repayNum", init.getRepayNum());
									accAmtMap.put("updt", new Date());
									accAmtMap.put("curRcvAmt", inAmt);
									acctList.add(accAmtMap);
								}

								if (amtFlg) {
									accRepayFlow = new AccRepayFlow();
									accRepayFlow.setId(RandomUtil.getUUID());
									accRepayFlow.setLoanNo(loanNo);
									accRepayFlow.setRepayDate(listinit.get(0).getRepayDate());
									accRepayFlow.setTranType(PubBusinessConstant.TRANDTYPE_ZC);
									accRepayFlow.setTranChan(PubBusinessConstant.ZJ_SIGLE);
									accRepayFlow.setTranDate(
											DateUtils.getYear() + DateUtils.getMonth() + DateUtils.getDay());
									accRepayFlow.setTranObj("100310");
									accRepayFlow.setAcctNo(cb.getAcctNo());
									accRepayFlow.setAcctName(cb.getAcctName());
									accRepayFlow.setLoanStat("20101420");
									accRepayFlow.setSetlFlag(CommonConstant.COMMON_NO);
									accRepayFlow.setInstDate(DateUtils.getCurrentTimestamp());
									accRepayFlow.setRepayNum(listinit.get(0).getRepayNum());
									accRepayFlow.setCntAcctNo(ParamUtils.getParam("compAcctNo"));
									accRepayFlow.setCntAcctName("深圳柠檬金融信息服务有限公司");
									accRepayFlow.setTranOrg(caseList.get(0).getStaffNo());
									accRepayFlow.setTranStaff(caseList.get(0).getStaffName());
									accRepayFlow.setTranAmt(resultAmt);
									accRepayFlowService.insert(accRepayFlow);
								}

								// 判断是否所有逾期扣款完成
								if (caseList.get(0).getEndNum() == listinit.get(listinit.size() - 1).getRepayNum()
										&& transAmtTotal.compareTo(resultAmt) != -1) {
									caseparam.put("dealStat", PubBusinessConstant.COLLECTION_YCL);
									// 修改逾期案件扣款结果
									caseparam.put("withStat", PubBusinessConstant.REPAY_DG_STATUS_PAIED);
									// 改变代扣表状态 为扣款成功
									updateAccCapWith(FinanceConstant.WITHSTAT_DKSUCC, listCap);
								} else {
									// 修改逾期案件扣款结果 未结束
									caseparam.put("withStat", PubBusinessConstant.REPAY_DG_STATUS_PART);
									updateAccCapWith(FinanceConstant.WITHSTAT_UNDK, listCap);
								}
								caseService.updateByPrimaryKeySelective(caseparam);
								// 新增经办登记
								String handDetailTyp = PubBusinessConstant.LOANHANDTYPE_DULREPAY;
								String handPersonNo = caseList.get(0).getStaffNo();
								String handPersonName = caseList.get(0).getStaffName();
								Date handDate = DateUtils.getCurrentDate();
								appLoanHandService.saveAppLoanHand(caseparam.get("loanNo").toString(),
										caseList.get(0).getCustNo(), caseList.get(0).getCustName(), handDetailTyp,
										PubBusinessConstant.LOANHANDMODEL_SYS, handPersonNo, handPersonName, handDate,
										"逾期案件处理还款[正常还款" + listinit.get(0).getRepayNum() + "-"
												+ listinit.get(listinit.size() - 1).getRepayNum() + "期]",
										PubBusinessConstant.CUST_ZC);
								successFlg = true;
							} catch (Exception e) {
								e.printStackTrace();
								successFlg = false;
								// 修改逾期案件处理结果
								caseparam.put("withStat", PubBusinessConstant.REPAY_DG_STATUS_FAIL);
								caseService.updateByPrimaryKeySelective(caseparam);
								// 改变代扣表状态 为扣款失败
								updateAccCapWith(FinanceConstant.WITHSTAT_DKFIELD, listCap);
							} finally {
								if (successFlg) {
									for (Map<String, Object> accAmtMap : acctList) {
										// 更新预处理表
										accLoanAcctInstService.updateAccLoanInstCurRcvAmtAndUnLock(accAmtMap);
									}
								} else {
									// 解锁
									for (AccLoanAcctInst plan : listinit) {
										accLoanAcctInstService.updateAccLoanAcctStatusUnLock(
												caseparam.get("loanNo").toString(), plan.getRepayNum());
									}
								}
							}
						} else { // 非逾期的数据 直接加流水改预处理表就OK
							AccRepayFlow accRepayFlow = null;
							Map<String, Object> accAmtMap = null;
							for (AccLoanAcctInst init : listinit) {
								accRepayFlow = new AccRepayFlow();
								accRepayFlow.setId(RandomUtil.getUUID());
								accRepayFlow.setLoanNo(loanNo);
								accRepayFlow.setRepayDate(init.getRepayDate());
								accRepayFlow.setTranType(PubBusinessConstant.TRANDTYPE_ZC);
								accRepayFlow.setTranChan(PubBusinessConstant.ZJ_SIGLE);
								accRepayFlow
										.setTranDate(DateUtils.getYear() + DateUtils.getMonth() + DateUtils.getDay());
								accRepayFlow.setTranObj("100310");
								accRepayFlow.setAcctNo(cb.getAcctNo());
								accRepayFlow.setAcctName(cb.getAcctName());
								accRepayFlow.setLoanStat("20101420");
								accRepayFlow.setSetlFlag(CommonConstant.COMMON_NO);
								accRepayFlow.setInstDate(DateUtils.getCurrentTimestamp());
								accRepayFlow.setRepayNum(init.getRepayNum());
								accRepayFlow.setCntAcctNo(ParamUtils.getParam("compAcctNo"));
								accRepayFlow.setCntAcctName("深圳柠檬金融信息服务有限公司");
								accRepayFlow.setTranOrg(caseList.get(0).getStaffNo());
								accRepayFlow.setTranStaff(caseList.get(0).getStaffName());
								accRepayFlow.setTranAmt(new BigDecimal(result.getRetItem().getAmount()));
								// 更新预处理表
								accAmtMap = new HashMap<String, Object>();
								accAmtMap.put("loanNo", loanNo);
								accAmtMap.put("repayNum", init.getRepayNum());
								accAmtMap.put("updt", new Date());
								accAmtMap.put("curRcvAmt", result.getRetItem().getAmount());
								acctList.add(accAmtMap);
								accRepayFlowService.insert(accRepayFlow);
							}
							for (Map<String, Object> amtMap : acctList) {
								// 更新预处理表
								accLoanAcctInstService.updateAccLoanInstCurRcvAmtAndUnLock(amtMap);
							}
							// 改变代扣表状态 为扣款成功
							updateAccCapWith(FinanceConstant.WITHSTAT_DKSUCC, listCap);
						}
					} else {
						// 解锁
						for (AccLoanAcctInst plan : listinit) {
							accLoanAcctInstService.updateAccLoanAcctStatusUnLock(loanNo, plan.getRepayNum());
						}
						// 改变代扣表状态 为扣款失败
						updateAccCapWith(FinanceConstant.WITHSTAT_DKFIELD, listCap);
					}

				} else {
					// 解锁
					for (AccLoanAcctInst plan : listinit) {
						accLoanAcctInstService.updateAccLoanAcctStatusUnLock(loanNo, plan.getRepayNum());
					}
				}
			} else {
				throw new ServiceException("该笔贷款暂无中金扣款数据,请核实");
			}
		} else {
			throw new ServiceException("该笔贷款未被锁定");
		}
	}
}
