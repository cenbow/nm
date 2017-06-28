package com.hs.loan.approve.server;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import com.alibaba.fastjson.TypeReference;
import com.hs.base.entity.CometData;
import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.CommonConstant;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.loan.approv.dto.AppLoanApprTermDto;
import com.hs.loan.approv.dto.LoanApprDto;
import com.hs.loan.approv.dto.LoanApprInDto;
import com.hs.loan.approv.dto.LoanCustRepeatCheckDto;
import com.hs.loan.approvcheck.dto.AppLoanApprCheckDto;
import com.hs.loan.approve.api.LoanApprApi;
import com.hs.loan.approve.bo.AppLoanApprBo;
import com.hs.loan.approve.bo.AppLoanCustRepeatCheckBo;
import com.hs.loan.approve.entity.AppLoanAppr;
import com.hs.loan.approve.entity.AppLoanApprTerm;
import com.hs.loan.approve.service.AppApprStaffGroupService;
import com.hs.loan.approve.service.AppApprStaffSignService;
import com.hs.loan.approve.service.AppLoanApprService;
import com.hs.loan.approve.service.AppLoanApprTermService;
import com.hs.loan.approve.service.AppLoanCustReCheckService;
import com.hs.loan.approve.service.AppLoanFundMatchService;
import com.hs.loan.approvecheck.entity.AppLoanApprCheck;
import com.hs.loan.approvecheck.entity.AppLoanApprCheckH;
import com.hs.loan.approvecheck.service.AppLoanApprCheckHService;
import com.hs.loan.approvecheck.service.AppLoanApprCheckService;
import com.hs.loan.busi.dto.AppLoanProdDto;
import com.hs.loan.busi.dto.LoanAcctOutDto;
import com.hs.loan.busi.dto.LoanProdCalcDto;
import com.hs.loan.contract.api.LoanContractSignApi;
import com.hs.loan.pub.hand.entity.AppLoanHand;
import com.hs.loan.pub.hand.service.AppLoanHandService;
import com.hs.loan.sale.api.LoanAcctApi;
import com.hs.utils.BeanUtils;
import com.hs.utils.DateUtils;
import com.hs.utils.HttpsInvokerUtil;
import com.hs.utils.ParamUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;

@Service
@Transactional(readOnly=true)
public class LoanApprServer implements LoanApprApi {

	@Autowired
	private AppLoanApprService appLoanApprService;
	@Autowired
	private AppApprStaffGroupService appApprStaffGroupService;
	@Autowired
	private AppLoanFundMatchService appLoanFundMatchService;
	@Autowired
	private LoanAcctApi approveLoanAcctService;
	@Autowired
	private LoanContractSignApi approveLoanContractSignService;
	@Autowired
	private AppLoanHandService appLoanHandService;
 
	@Autowired
	private AppLoanCustReCheckService appLoanCustCheckService;

	@Autowired
	private AppLoanApprCheckService appLoanApprCheckService;
	@Autowired
	private AppLoanApprCheckHService appLoanApprCheckHService;
	
	public static final String APPRSTAT_WAITAPPR_CHECK = "60021001";//未复核
	public static final String APPRSTAT_APPRPASS_CHECK = "60021002";//通过
	public static final String APPRSTAT_APPRUPASS_CHECK = "60021003";//拒绝
	private String threeNull(Object obj) {
		return (null == obj || "".equals(obj.toString().trim())) ? null : obj.toString().trim();
	}

	/**
	 * 根据贷款编号查询审批信息列表
	 * (判断上一次状态是否为预通过 null!=getAppLoanAppr&&"40002010".equals(getAppLoanAppr().get(0).getStat()))
	 * @param loanNo
	 * @return List<AppLoanAppr>
	 */
	public List<LoanApprDto> getAppLoanAppr(String loanNo)throws ServiceException, AppException{
		if(null==threeNull(loanNo))throw new ServiceException("贷款编号不能为空");
		Map map=new HashMap();
		map.put("loanNo",loanNo);
		return JSON.parseObject(JSON.toJSONString(appLoanApprService.getAppLoanAppr(map)),new TypeReference<List<LoanApprDto>>(){}) ;
	}

	/**
	 * 重复人员信息(查询是否以前带款的历史记录)
	 * @param custNo
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	@Override
	public List<LoanCustRepeatCheckDto> getList(String custNo) throws ServiceException, AppException {
		try{
		if(org.apache.commons.lang3.StringUtils.isBlank(custNo)){
			throw new ServiceException("客户编号为空");
		}
		 List<AppLoanCustRepeatCheckBo> list = appLoanCustCheckService.queryForList(custNo);
		return BeanUtils.copyProperties(list, LoanCustRepeatCheckDto.class);
		}catch(ServiceException e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	 /**
	 * 待审批分期查询
	 */ 
	@Override
	public Page<LoanApprDto> queryLoanApprInfo(Page<LoanApprDto> param, UserProfile userProfile)
			throws ServiceException, AppException {
		Page<AppLoanApprBo> page = param.toPage(AppLoanApprBo.class);
		// 权限过滤
//		page.getPageParams().put("apprNo", userProfile.getStaffNo());
//		page.getPageParams().put("waitAppr", PubBusinessConstant.APPRSTAT_WAITAPPR);
		Page<AppLoanApprBo> retpage = appLoanApprService.queryForPageTwo(page);
		return retpage.toPage(LoanApprDto.class);
	}

	 /**
	 * 分期审批
	 */
	@Override
	@Transactional
	public synchronized String loanAppr(LoanApprInDto inDto, UserProfile userProfile) throws ServiceException, AppException {
		String retMsg = "00";

		if(StringUtils.isEmpty(inDto.getApprId())){
			throw new ServiceException("审批编号为空");
		}
		if(StringUtils.isEmpty(inDto.getLoanNo())){
			throw new ServiceException("分期编号为空");
		}
		LoanAcctOutDto loanAcctOutDto = approveLoanAcctService.getLoanAcct(inDto.getLoanNo(), userProfile);
		if(loanAcctOutDto == null){
			throw new ServiceException("获取分期信息异常");
		}
		String loanStat = loanAcctOutDto.getStat();
		if(!PubBusinessConstant.LOANSTAT_WATEAPPROV.equals(loanStat)){
			throw new ServiceException("分期状态与当前请求不一致.");
		}
		String stat = inDto.getStat();
		Map<String,Object> map = new HashMap<>();
		map.put("apprId",inDto.getApprId());
		map.put("manuEndDate", DateUtils.getCurDateTime());
		map.put("apprDesc", inDto.getApprDesc());
		map.put("remark", inDto.getRemark());
		map.put("stat", stat);
		map.put("apprType", inDto.getApprTyp());
		map.put("codeRmark", inDto.getCodeRmark());
		appLoanApprService.updateByPrimaryKeySelective(map);
		
		map.put("loanNo",inDto.getLoanNo());
		map.put("aprvDate", DateUtils.getCurDateTime());
		//修改分期基本信息分期状态
		if(stat.equals(PubBusinessConstant.APPRSTAT_APPRUPASS)){
			map.put("stat",PubBusinessConstant.LOANSTAT_REFUSED);
			approveLoanAcctService.updateLoanByLoanNo(map);
			appLoanHandService.saveAppLoanHand(inDto.getLoanNo(),inDto.getCustNo(),inDto.getCustName(),
					PubBusinessConstant.LOANHANDTYPE_APPOV, PubBusinessConstant.LOANHANDMODEL_SYS, 
					userProfile.getStaffNo(), userProfile.getStaffName(),DateUtils.getCurrentTimestamp(), "分期审批不通过,意见:-"+inDto.getApprDesc(),PubBusinessConstant.CUST_ZC);
			CometData cometData = new CometData();
			cometData.setChannel("sale");
			cometData.setClient(inDto.getSaleNo());
			cometData.setContent("您有一笔分期被审批拒绝,客户名称:["+inDto.getCustName()+"]");
			String json = JSON.toJSONString(cometData); 
			HttpsInvokerUtil.postJson(ParamUtils.getParam("pubshMsg"), json);
		}else if(stat.equals(PubBusinessConstant.APPRSTAT_APPRREFUSE)){
			map.put("stat",PubBusinessConstant.LOANSTAT_REJECTED);
			approveLoanAcctService.updateLoanByLoanNo(map);
			appLoanHandService.saveAppLoanHand(inDto.getLoanNo(),inDto.getCustNo(),inDto.getCustName(),
					PubBusinessConstant.LOANHANDTYPE_MODI, PubBusinessConstant.LOANHANDMODEL_SYS, 
					userProfile.getStaffNo(), userProfile.getStaffName(),DateUtils.getCurrentTimestamp(), "分期审批返回修改,意见:"+inDto.getApprDesc(),PubBusinessConstant.CUST_ZC);
			/*CometData cometData = new CometData();ZYM暂时屏蔽
			cometData.setChannel("sale");
			cometData.setClient(inDto.getSaleNo());
			cometData.setContent("您有一笔分期被审批驳回修改,客户名称:["+inDto.getCustName()+"]");
			String json = JSON.toJSONString(cometData); 
			HttpsInvokerUtil.postJson(ParamUtils.getParam("pubshMsg"), json);*/
		}else if(stat.equals(PubBusinessConstant.APPRSTAT_APPRPASS)){
			map.put("stat",PubBusinessConstant.LOANSTAT_PASS);
			//调用资金渠道自动匹配
			String matchStat = appLoanFundMatchService.loanFundMatch(inDto.getLoanNo());
			appLoanHandService.saveAppLoanHand(inDto.getLoanNo(),inDto.getCustNo(),inDto.getCustName(),
					PubBusinessConstant.LOANHANDTYPE_APPOV, PubBusinessConstant.LOANHANDMODEL_SYS, 
					userProfile.getStaffNo(), userProfile.getStaffName(),DateUtils.getCurrentTimestamp(),"分期审批通过,意见:"+inDto.getApprDesc(),PubBusinessConstant.CUST_ZC);
			retMsg = matchStat;
			
			//匹配成功后
			if("00".equalsIgnoreCase(matchStat)){
				try{
					//生成合同
					//approveLoanContractSignService.buidContant(inDto.getLoanNo(), userProfile);
					appLoanHandService.saveAppLoanHand(inDto.getLoanNo(),inDto.getCustNo(),inDto.getCustName(),
							PubBusinessConstant.LOANHANDTYPE_APPOV, PubBusinessConstant.LOANHANDMODEL_SYS, 
							userProfile.getStaffNo(), userProfile.getStaffName(),DateUtils.getCurrentTimestamp(), "分期资金匹配-系统写入",PubBusinessConstant.CUST_ZC);
				}catch(Exception e){
					e.printStackTrace();
					/*retMsg="02"; //审批通过资金匹配成功合同生成失败
					return retMsg;*/
					throw new ServiceException("生成合同失败,请重新审批!");
				}
				
				/*CometData cometData = new CometData();
				cometData.setChannel("sale");
				cometData.setClient(inDto.getSaleNo());
				cometData.setContent("您有一笔分期审批通过,客户名称是:["+inDto.getCustName()+"]");
				String json = JSON.toJSONString(cometData); 
				HttpsInvokerUtil.postJson(ParamUtils.getParam("pubshMsg"), json);*/
				if(inDto.getCreditlimitAmt() != null &&  inDto.getCreditlimitAmt().compareTo(new BigDecimal(0)) != 0){
					Map<String,Object> retmap = chageCreditlimit(userProfile, inDto.getApprId(), inDto.getLoanNo(), inDto.getCreditlimitAmt());
					map.put("fstRepayAmt",retmap.get("fstRepayAmt"));         
			 		/*** 月还款金额 */                                   
			 		map.put("mthRepayAmt",retmap.get("mthRepayAmt"));  
			 		map.put("loanNo", retmap.get("loanNo"));
			 		map.put("loanAmt", retmap.get("loanAmt"));
			 		int ret =appLoanApprService.updateGoodsPric(inDto.getLoanNo(), inDto.getCreditlimitAmt());
			 		if(ret != 1 ){
			 			throw new ServiceException("调额更新产品信息失败");
			 		}
			 		//写入经办信息
			 		appLoanHandService.saveAppLoanHand(loanAcctOutDto.getLoanNo(), loanAcctOutDto.getCustNo(), loanAcctOutDto.getCustName(), PubBusinessConstant.LOANHANDTYPE_CREDTLIMT, PubBusinessConstant.LOANHANDMODEL_HAND,
			 				userProfile.getStaffNo(), userProfile.getStaffName(), DateUtils.getCurrentTimestamp(), "分期信息额度调整，申请额度"+loanAcctOutDto.getLoanAmt()+"月还款金额为"+loanAcctOutDto.getMthRepayAmt()+"*"+loanAcctOutDto.getInstNum()+"，综合授信额度为："+inDto.getCreditlimitAmt()+"元,月还款额度："+retmap.get("mthRepayAmt")+"*"+loanAcctOutDto.getInstNum()+",",PubBusinessConstant.CUST_ZC);
			 		
				}
		 		approveLoanAcctService.updateLoanByLoanNo(map);
		 		
			}else{//匹配不成功消息提醒
				/*CometData cometData = new CometData();
				cometData.setChannel("operate");
				cometData.setContent("您有一笔分期需要人工资金匹配,客户名称是:["+inDto.getCustName()+"]");
				String json = JSON.toJSONString(cometData); 
				HttpsInvokerUtil.postJson(ParamUtils.getParam("pubshMsg"), json);*/
			} 
		}
		//设置审批人员状态为空闲
		appApprStaffGroupService.updateStaffApprStat(userProfile.getStaffNo(),PubBusinessConstant.SIGNTYPE_WAITING);
		return retMsg;
	}
	
	/**
	 * 分期初审
	 * @param inDto
	 * @param userProfile
	 * @return
	 * @throws ServiceException
	 */
	@Transactional
	public synchronized Map<String,Object> loanApprFstCheck(LoanApprInDto inDto, UserProfile userProfile) throws ServiceException,AppException
	{
		if(StringUtils.isEmpty(inDto.getApprId()))
		{
			throw new ServiceException("审批编号为空");
		}
		if(StringUtils.isEmpty(inDto.getLoanNo()))
		{
			throw new ServiceException("分期编号为空");
		}
		
		LoanAcctOutDto loanAcctOutDto = approveLoanAcctService.getLoanAcct(inDto.getLoanNo(), userProfile);
		if(loanAcctOutDto == null)
		{
			throw new ServiceException("获取分期信息异常");
		}
		String loanStat = loanAcctOutDto.getStat();
		if(!PubBusinessConstant.LOANSTAT_WATEAPPROV.equals(loanStat))
		{
			throw new ServiceException("分期状态与当前请求不一致.");
		}
		Map<String, String> manageInfo = appLoanApprCheckService.getManagerNoByStaffNo(userProfile.getStaffNo());
		if(manageInfo == null)
		{
			throw new ServiceException("该员工组无主管，请先添加");
		}
		//主管status : PubBusinessConstant.SIGNTYPE_ONLINE(上线)	PubBusinessConstant.SIGNTYPE_OFFLINE(离线)
		String status = appLoanApprCheckService.getGroupTypeByStaffNo(manageInfo.get("staffNo"));
		boolean flag = true;
		if(!userProfile.getStaffNo().equals(manageInfo.get("staffNo")))
		{
			if(PubBusinessConstant.SIGNTYPE_ONLINE.equals(status))
			{
				if(inDto.getForceCheck())
				{
					flag = false;
				}
				else 
				{
					Integer apprStateByStaffNo = appLoanApprCheckService.getCountByManagerNo(manageInfo.get("staffNo"));
					flag = ((apprStateByStaffNo > 0) ? true : false);
				}
			}
		}
		String retMsg = "00";
		String stat = inDto.getStat();
		Map<String,Object> loanAppmap = new HashMap<String,Object>();
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("through", false);
		param.put("retMsg", retMsg);
		loanAppmap.put("apprId",inDto.getApprId());
		loanAppmap.put("manuEndDate", DateUtils.getCurDateTime());
		loanAppmap.put("apprDesc", inDto.getApprDesc());
		loanAppmap.put("remark", inDto.getRemark());
		loanAppmap.put("apprType", inDto.getApprTyp());
		loanAppmap.put("codeRmark", inDto.getCodeRmark());
		if( flag )
		{
			loanAppmap.put("stat", stat);
			appLoanApprService.updateByPrimaryKeySelective(loanAppmap);
			map.putAll(loanAppmap);
			map.put("loanNo",inDto.getLoanNo());
			map.put("aprvDate", DateUtils.getCurDateTime());

			//初审是预审批通过
			if(stat.equals(PubBusinessConstant.APPRSTAT_APPRPREPASS)){
				//设置贷款状态为预审批通过
				map.put("stat",PubBusinessConstant.LOANSTAT_PREPASS);
				appLoanHandService.saveAppLoanHand(inDto.getLoanNo(),inDto.getCustNo(),inDto.getCustName(),PubBusinessConstant.LOANHANDTYPE_APPOV, PubBusinessConstant.LOANHANDMODEL_SYS, userProfile.getStaffNo(), userProfile.getStaffName(),DateUtils.getCurrentTimestamp(), "初审分期审批预通过,意见:"+inDto.getApprDesc(),PubBusinessConstant.CUST_ZC);
				appLoanApprCheckService.updateAppLoanAcctByLoanNo(map);
			}

			//修改分期基本信息分期状态
			if(stat.equals(PubBusinessConstant.APPRSTAT_APPRUPASS))
			{
				map.put("stat",PubBusinessConstant.LOANSTAT_REFUSED);
				//approveLoanAcctService.updateLoanByLoanNo(map);
				appLoanApprCheckService.updateAppLoanAcctByLoanNo(map);
				appLoanHandService.saveAppLoanHand(inDto.getLoanNo(),inDto.getCustNo(),inDto.getCustName(), PubBusinessConstant.LOANHANDTYPE_APPOV, PubBusinessConstant.LOANHANDMODEL_SYS, userProfile.getStaffNo(), userProfile.getStaffName(),DateUtils.getCurrentTimestamp(), "分期审批不通过,意见:-"+inDto.getApprDesc(),PubBusinessConstant.CUST_ZC);
				/*CometData cometData = new CometData();
				cometData.setChannel("sale");
				cometData.setClient(inDto.getSaleNo());
				cometData.setContent("您有一笔分期被审批拒绝,客户名称:["+inDto.getCustName()+"]");
				String json = JSON.toJSONString(cometData); 
				HttpsInvokerUtil.postJson(ParamUtils.getParam("pubshMsg"), json);*/
			}
			else if(stat.equals(PubBusinessConstant.APPRSTAT_APPRREFUSE))
			{
				map.put("stat",PubBusinessConstant.LOANSTAT_REJECTED);
				//approveLoanAcctService.updateLoanByLoanNo(map);
				appLoanApprCheckService.updateAppLoanAcctByLoanNo(map);
				appLoanHandService.saveAppLoanHand(inDto.getLoanNo(),inDto.getCustNo(),inDto.getCustName(),PubBusinessConstant.LOANHANDTYPE_MODI, PubBusinessConstant.LOANHANDMODEL_SYS, userProfile.getStaffNo(), userProfile.getStaffName(),DateUtils.getCurrentTimestamp(), "分期审批返回修改,意见:"+inDto.getApprDesc(),PubBusinessConstant.CUST_ZC);
			}
			else if(stat.equals(PubBusinessConstant.APPRSTAT_APPRPASS))
			{
				map.put("stat",PubBusinessConstant.LOANSTAT_PASS);
				//调用资金渠道自动匹配
				String matchStat = appLoanFundMatchService.loanFundMatch(inDto.getLoanNo());
				appLoanHandService.saveAppLoanHand(inDto.getLoanNo(),inDto.getCustNo(),inDto.getCustName(),PubBusinessConstant.LOANHANDTYPE_APPOV, PubBusinessConstant.LOANHANDMODEL_SYS,userProfile.getStaffNo(), userProfile.getStaffName(),DateUtils.getCurrentTimestamp(),"分期审批通过,意见:"+inDto.getApprDesc(),PubBusinessConstant.CUST_ZC);
				retMsg = matchStat;
				param.put("retMsg", retMsg);
				//匹配成功后
				if("00".equalsIgnoreCase(matchStat))
				{
					appLoanHandService.saveAppLoanHand(inDto.getLoanNo(),inDto.getCustNo(),inDto.getCustName(),PubBusinessConstant.LOANHANDTYPE_APPOV, PubBusinessConstant.LOANHANDMODEL_SYS,userProfile.getStaffNo(), userProfile.getStaffName(),DateUtils.getCurrentTimestamp(), "分期资金匹配-系统写入",PubBusinessConstant.CUST_ZC);
					//appLoanApprCheckService.updateAppLoanAcctByLoanNo(map);
					/*CometData cometData = new CometData();
					cometData.setChannel("sale");
					cometData.setClient(inDto.getSaleNo());
					cometData.setContent("您有一笔分期审批通过,客户名称是:["+inDto.getCustName()+"]");
					String json = JSON.toJSONString(cometData); 
					HttpsInvokerUtil.postJson(ParamUtils.getParam("pubshMsg"), json);*/
					
					if(inDto.getCreditlimitAmt() != null &&  inDto.getCreditlimitAmt().compareTo(new BigDecimal(0)) != 0){
						Map<String,Object> retmap = chageCreditlimit(userProfile, inDto.getApprId(), inDto.getLoanNo(), inDto.getCreditlimitAmt());
						map.put("fstRepayAmt",retmap.get("fstRepayAmt"));         
				 		/*** 月还款金额 */                                   
				 		map.put("mthRepayAmt",retmap.get("mthRepayAmt"));  
				 		map.put("loanNo", retmap.get("loanNo"));
				 		map.put("loanAmt", retmap.get("loanAmt"));
				 		
				 		//现金贷调额更新商品信息
				 		int ret =appLoanApprService.updateGoodsPric(inDto.getLoanNo(), inDto.getCreditlimitAmt());
				 		if(ret != 1 ){
				 			throw new ServiceException("调额更新产品信息失败");
				 		}
				 		//写入经办信息
				 		appLoanHandService.saveAppLoanHand(loanAcctOutDto.getLoanNo(), loanAcctOutDto.getCustNo(), loanAcctOutDto.getCustName(), PubBusinessConstant.LOANHANDTYPE_CREDTLIMT, PubBusinessConstant.LOANHANDMODEL_HAND,
				 				userProfile.getStaffNo(), userProfile.getStaffName(), DateUtils.getCurrentTimestamp(), "分期信息额度调整，申请额度"+loanAcctOutDto.getLoanAmt()+"月还款金额为"+loanAcctOutDto.getMthRepayAmt()+"*"+loanAcctOutDto.getInstNum()+"，综合授信额度为："+inDto.getCreditlimitAmt()+"元,月还款额度："+retmap.get("mthRepayAmt")+"*"+loanAcctOutDto.getInstNum()+",",PubBusinessConstant.CUST_ZC);
					}
			 		approveLoanAcctService.updateLoanByLoanNo(map);
				}
				else
				{	//匹配不成功消息提醒
					/*CometData cometData = new CometData();
					cometData.setChannel("operate");
					cometData.setContent("您有一笔分期需要人工资金匹配,客户名称是:["+inDto.getCustName()+"]");
					String json = JSON.toJSONString(cometData); 
					HttpsInvokerUtil.postJson(ParamUtils.getParam("pubshMsg"), json);*/
				} 
				
			}
				try {
					//医美类型发送短信
					sendApproveMsg(loanAcctOutDto.getLoanNo(), loanAcctOutDto.getCustNo(), stat);
				} catch (Exception e) {
					e.printStackTrace();;
				}
			param.put("through", true);
		}
		else 
		{
			AppLoanAppr appLoanAppr = appLoanApprService.getByPrimaryKey(inDto.getApprId());
			Integer num = appLoanApprCheckService.getCheckCntByApprId(inDto.getApprId());
			if(num == null)
			{
				num = 0;
			}
			else 
			{
				appLoanApprCheckService.deleteByApprId(inDto.getApprId());
			}
			AppLoanApprCheck appLoanApprCheck = new AppLoanApprCheck();
			appLoanApprCheck.setIsForceCheck(CommonConstant.COMMON_NO);
			if(inDto.getForceCheck())
			{
				appLoanApprCheck.setIsForceCheck(CommonConstant.COMMON_YES);
			}
			AppLoanApprCheckH appLoanApprCheckH = new AppLoanApprCheckH();
			appLoanApprCheck.setId(RandomUtil.getUUID());
			appLoanApprCheck.setApprId(appLoanAppr.getApprId());
			appLoanApprCheck.setLoanNo(appLoanAppr.getLoanNo());
			appLoanApprCheck.setCustNo(appLoanAppr.getCustNo());
			appLoanApprCheck.setCustName(appLoanAppr.getCustName());
			appLoanApprCheck.setStaffNo(appLoanAppr.getSaleNo());
			appLoanApprCheck.setStaffName(appLoanAppr.getSaleName());
			appLoanApprCheck.setCheckCnt(num + 1); 
			appLoanApprCheck.setBegDate(new Date());
			appLoanApprCheck.setFstCheckNo(userProfile.getStaffNo());
			appLoanApprCheck.setFstCheckName(userProfile.getStaffName());
			appLoanApprCheck.setApprTyp(inDto.getApprTyp());
			appLoanApprCheck.setCheckNo(manageInfo.get("staffNo"));
			appLoanApprCheck.setCheckName(manageInfo.get("staffName"));
			appLoanApprCheck.setCheckResult(APPRSTAT_WAITAPPR_CHECK);
			appLoanApprCheck.setFstApprResult(inDto.getStat());
			appLoanApprCheck.setFstApprRemark(inDto.getApprDesc());
			appLoanApprCheck.setGroupId(manageInfo.get("groupId"));
			appLoanApprCheck.setGroupName(manageInfo.get("groupName"));
			BeanUtils.copyPropertiesNotForce(appLoanApprCheckH, appLoanApprCheck);
			appLoanApprCheckService.insert(appLoanApprCheck);
			appLoanApprCheckHService.insert(appLoanApprCheckH);
			appLoanApprService.updateByPrimaryKeySelective(loanAppmap);
			appLoanHandService.saveAppLoanHand(inDto.getLoanNo(),inDto.getCustNo(),inDto.getCustName(),PubBusinessConstant.LOANHANDTYPE_APPOV, PubBusinessConstant.LOANHANDMODEL_SYS,userProfile.getStaffNo(), userProfile.getStaffName(),DateUtils.getCurrentTimestamp(),"分期已初审核-待复核,意见:"+inDto.getApprDesc(),PubBusinessConstant.CUST_ZC);
			if(inDto.getCreditlimitAmt() != null &&  inDto.getCreditlimitAmt().compareTo(new BigDecimal(0)) != 0){
				Map<String,Object> retmap = chageCreditlimit(userProfile, inDto.getApprId(), inDto.getLoanNo(), inDto.getCreditlimitAmt());
				map.put("fstRepayAmt",retmap.get("fstRepayAmt"));         
		 		/*** 月还款金额 */                                   
		 		map.put("mthRepayAmt",retmap.get("mthRepayAmt"));  
		 		map.put("loanNo", retmap.get("loanNo"));
		 		map.put("loanAmt", retmap.get("loanAmt"));
		 		//现金贷调额更新商品信息
		 		int ret =appLoanApprService.updateGoodsPric(inDto.getLoanNo(), inDto.getCreditlimitAmt());
		 		if(ret != 1 ){
		 			throw new ServiceException("调额更新产品信息失败");
		 		}
		 		//写入经办信息
		 		appLoanHandService.saveAppLoanHand(loanAcctOutDto.getLoanNo(), loanAcctOutDto.getCustNo(), loanAcctOutDto.getCustName(), PubBusinessConstant.LOANHANDTYPE_CREDTLIMT, PubBusinessConstant.LOANHANDMODEL_HAND,
		 				userProfile.getStaffNo(), userProfile.getStaffName(), DateUtils.getCurrentTimestamp(), "分期信息额度调整，申请额度"+loanAcctOutDto.getLoanAmt()+"月还款金额为"+loanAcctOutDto.getMthRepayAmt()+"*"+loanAcctOutDto.getInstNum()+"，综合授信额度为："+inDto.getCreditlimitAmt()+"元,月还款额度："+retmap.get("mthRepayAmt")+"*"+loanAcctOutDto.getInstNum()+",",PubBusinessConstant.CUST_ZC);
			}
		}
		//设置审批人员状态为空闲
		appApprStaffGroupService.updateStaffApprStat(userProfile.getStaffNo(),PubBusinessConstant.SIGNTYPE_WAITING);
		return param;
	}
	/**
	 * 审批出结果后发送短信给客户
	 * @param loanNo
	 * @param custNo
	 * @param stat
	 * @throws Exception 
	 */
	private void sendApproveMsg(String _loanNo, String _custNo,String stat) throws Exception {
			Map<String, String> param = new HashMap<String, String>();
			Map<String, Object> sendFlag= appLoanApprService.querySendPhoneNo(_loanNo,_custNo);
			if (sendFlag!=null&&sendFlag.get("PHONE_NO")!=null) {
				param.put("mob", sendFlag.get("PHONE_NO").toString());
				StringBuilder msgBuilder = new StringBuilder();
				if(stat.equals(PubBusinessConstant.APPRSTAT_APPRPASS)){//通过
					msgBuilder.append("尊敬的客户，恭喜您，您的分期申请已经通过。请您前往“我的订单-订单状态-审批通过”，点击签约按钮完成合同签署！");
				}else if(stat.equals(PubBusinessConstant.APPRSTAT_APPRUPASS)){//拒绝
					msgBuilder.append("尊敬的客户，非常遗憾，由于综合评分不足，您的分期申请未能通过。若有疑问，可致电我司客服4006-028-011。");
				} else if(stat.equals(PubBusinessConstant.APPRSTAT_APPRREFUSE)){ //驳回
					msgBuilder.append("尊敬的客户，由于需要您进一步完善信息，您的分期订单已被驳回。请您前往“我的订单-订单状态-审批驳回”，根据提示内容进行相关修改！");
				}
				param.put("msg", msgBuilder.toString());
				String url = ParamUtils.getParam("msgSendUrl");
				List<NameValuePair> parameters = new ArrayList<>();
				Set<String> keys = param.keySet();
				for (String key : keys) {
					parameters.add(new BasicNameValuePair(key, param.get(key).toString()));
				}
				HttpsInvokerUtil.executeHttpPost(url, parameters);
			}
	}
	/**
	 * 分期复核
	 * @param inDto
	 * @param userProfile
	 * @return
	 * @throws ServiceException
	 */
	@Transactional
	public Map<String,Object> loanApprCheck(AppLoanApprCheckDto appLoanApprCheckDto,LoanApprInDto inDto,UserProfile userProfile) throws ServiceException,AppException
	{
		if(StringUtils.isEmpty(inDto.getLoanNo()))
		{
			throw new ServiceException("分期编号为空");
		}
		if(StringUtils.isBlank(appLoanApprCheckDto.getId()))
		{
			throw new ServiceException("复核ID为空");
		}
		if(StringUtils.isBlank(appLoanApprCheckDto.getCheckResult()))
		{
			throw new ServiceException("复核结果不能为空");
		}
		String state = appLoanApprCheckDto.getCheckResult();
		String retMsg = "00";
		AppLoanApprCheck appLoanApprCheck = appLoanApprCheckService.getByPrimaryKey(appLoanApprCheckDto.getId());
		//通过
		if(APPRSTAT_APPRPASS_CHECK.equals(state))
		{
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("apprId",appLoanApprCheck.getApprId());
			map.put("stat", appLoanApprCheck.getFstApprResult());
			appLoanApprService.updateByPrimaryKeySelective(map);

			//复审是预审批通过
			if(appLoanApprCheck.getFstApprResult().equals(PubBusinessConstant.APPRSTAT_APPRPREPASS)){
				map.put("stat",PubBusinessConstant.LOANSTAT_PREPASS);
				map.put("loanNo",appLoanApprCheck.getLoanNo());
				map.put("aprvDate", DateUtils.getCurDateTime());
				appLoanApprCheckService.updateAppLoanAcctByLoanNo(map);
				appLoanHandService.saveAppLoanHand(appLoanApprCheck.getLoanNo(),appLoanApprCheck.getCustNo(),appLoanApprCheck.getCustName(),PubBusinessConstant.LOANHANDTYPE_APPOV, PubBusinessConstant.LOANHANDMODEL_SYS, userProfile.getStaffNo(), userProfile.getStaffName(),DateUtils.getCurrentTimestamp(), "分期复核预通过,意见:"+appLoanApprCheckDto.getCheckRemark(),PubBusinessConstant.CUST_ZC);
			}

			//修改分期基本信息分期状态
			if(PubBusinessConstant.APPRSTAT_APPRUPASS.equals(appLoanApprCheck.getFstApprResult()))
			{
				
				map.put("stat",PubBusinessConstant.LOANSTAT_REFUSED);
				map.put("loanNo",appLoanApprCheck.getLoanNo());
				map.put("aprvDate", DateUtils.getCurDateTime());
				//approveLoanAcctService.updateLoanByLoanNo(map);
				appLoanApprCheckService.updateAppLoanAcctByLoanNo(map);
				appLoanHandService.saveAppLoanHand(appLoanApprCheck.getLoanNo(),appLoanApprCheck.getCustNo(),appLoanApprCheck.getCustName(), PubBusinessConstant.LOANHANDTYPE_APPOV, PubBusinessConstant.LOANHANDMODEL_SYS, userProfile.getStaffNo(), userProfile.getStaffName(),DateUtils.getCurrentTimestamp(), "分期复核审批不通过,意见:-"+appLoanApprCheckDto.getCheckRemark(),PubBusinessConstant.CUST_ZC);
				CometData cometData = new CometData();
				cometData.setChannel("sale");
				cometData.setClient(appLoanApprCheck.getStaffNo());
				cometData.setContent("您有一笔分期被审批拒绝,客户名称:["+appLoanApprCheck.getCustName()+"]");
				String json = JSON.toJSONString(cometData); 
				HttpsInvokerUtil.postJson(ParamUtils.getParam("pubshMsg"), json);
			}
			else if(PubBusinessConstant.APPRSTAT_APPRREFUSE.equals(appLoanApprCheck.getFstApprResult()))
			{
				map.put("stat",PubBusinessConstant.LOANSTAT_REJECTED);
				map.put("loanNo",appLoanApprCheck.getLoanNo());
				map.put("aprvDate", DateUtils.getCurDateTime());
				appLoanApprCheckService.updateAppLoanAcctByLoanNo(map);
				appLoanHandService.saveAppLoanHand(appLoanApprCheck.getLoanNo(),appLoanApprCheck.getCustNo(),appLoanApprCheck.getCustName(),PubBusinessConstant.LOANHANDTYPE_MODI, PubBusinessConstant.LOANHANDMODEL_SYS, userProfile.getStaffNo(), userProfile.getStaffName(),DateUtils.getCurrentTimestamp(), "分期复核审批返回修改,意见:"+appLoanApprCheckDto.getCheckRemark(),PubBusinessConstant.CUST_ZC);
			}
			else if(PubBusinessConstant.APPRSTAT_APPRPASS.equals(appLoanApprCheck.getFstApprResult()))
			{
				map.put("stat",PubBusinessConstant.LOANSTAT_PASS);
				map.put("loanNo",appLoanApprCheck.getLoanNo());
				map.put("aprvDate", DateUtils.getCurDateTime());
				//调用资金渠道自动匹配
				String matchStat = appLoanFundMatchService.loanFundMatch(appLoanApprCheck.getLoanNo());
				appLoanHandService.saveAppLoanHand(appLoanApprCheck.getLoanNo(),appLoanApprCheck.getCustNo(),appLoanApprCheck.getCustName(),PubBusinessConstant.LOANHANDTYPE_APPOV, PubBusinessConstant.LOANHANDMODEL_SYS,userProfile.getStaffNo(), userProfile.getStaffName(),DateUtils.getCurrentTimestamp(),"分期复核审批通过,意见:"+appLoanApprCheckDto.getCheckRemark(),PubBusinessConstant.CUST_ZC);
				//匹配成功后
				if("00".equalsIgnoreCase(matchStat))
				{
					appLoanHandService.saveAppLoanHand(appLoanApprCheck.getLoanNo(),appLoanApprCheck.getCustNo(),appLoanApprCheck.getCustName(),PubBusinessConstant.LOANHANDTYPE_APPOV, PubBusinessConstant.LOANHANDMODEL_SYS,userProfile.getStaffNo(), userProfile.getStaffName(),DateUtils.getCurrentTimestamp(), "分期资金匹配-系统写入",PubBusinessConstant.CUST_ZC);
					//approveLoanAcctService.updateLoanByLoanNo(map);
					appLoanApprCheckService.updateAppLoanAcctByLoanNo(map);
					CometData cometData = new CometData();
					cometData.setChannel("sale");
					cometData.setClient(appLoanApprCheck.getStaffNo());
					cometData.setContent("您有一笔分期审批通过,客户名称是:["+appLoanApprCheck.getCustName()+"]");
					String json = JSON.toJSONString(cometData); 
					HttpsInvokerUtil.postJson(ParamUtils.getParam("pubshMsg"), json);
				}
				else
				{	//匹配不成功消息提醒
					CometData cometData = new CometData();
					cometData.setChannel("operate");
					cometData.setContent("您有一笔分期需要人工资金匹配,客户名称是:["+appLoanApprCheck.getCustName()+"]");
					String json = JSON.toJSONString(cometData); 
					HttpsInvokerUtil.postJson(ParamUtils.getParam("pubshMsg"), json);
				}
			}
		}
		else
		{
			appLoanHandService.saveAppLoanHand(appLoanApprCheck.getLoanNo(),appLoanApprCheck.getCustNo(),appLoanApprCheck.getCustName(),PubBusinessConstant.LOANHANDTYPE_APPOV, PubBusinessConstant.LOANHANDMODEL_SYS,userProfile.getStaffNo(), userProfile.getStaffName(),DateUtils.getCurrentTimestamp(),"已经复核，但拒绝:"+appLoanApprCheckDto.getCheckRemark(),PubBusinessConstant.CUST_ZC);
		}
		Map<String,Object> param = BeanUtils.bean2map(appLoanApprCheckDto);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		param.put("endDate", sdf.format(new java.util.Date()));
		appLoanApprCheckService.updateByPrimaryKeySelective(param);
		appLoanApprCheckHService.updateByPrimaryKeySelective(param);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("retMsg",retMsg);
		map.put("state", appLoanApprCheck.getFstApprResult());
		map.put("custNo", appLoanApprCheck.getCustNo());
		map.put("custName", appLoanApprCheck.getCustName());
		map.put("saleNo", appLoanApprCheck.getStaffNo());
		return map;
	}
	
	
	/**
	 * //生成合同
	 * @param loanNo
	 * @param profile
	 * @return
	 */
	public String createContract(String loanNo,UserProfile profile){
		//生成合同
		try {
			return approveLoanContractSignService.buidContant(loanNo, profile,"pc").get("downUrl").toString();
		} catch (Exception e) {
			return null;
		}
	}
	
	  @Autowired
	    private AppApprStaffSignService staffSignService;//审批签到service
	    /**
	     * 获取审批任务
	     *
	     * @param profile 用户信息实体
		 * 返回审批列表list
	     */

	    @Transactional
	    public synchronized Page<LoanApprDto> getAppoTask(UserProfile profile) throws ServiceException, AppException {
	    	 
	        //基本数据判断
	        if (StringUtils.isBlank(profile.getStaffNo()) || StringUtils.isBlank(profile.getStaffName())) {
	            throw new ServiceException("审批人员编号或者姓名为空");
	        }

	         //获取审判员的签到状态
	           String status = staffSignService.getStaffStat(profile.getStaffNo());
				//假如审批员不在线则不分配任务
				if (!StringUtils.equals(PubBusinessConstant.SIGNTYPE_ONLINE, status)) {
					throw new ServiceException("审批员不在线,无法获取审批任务");
				}



	        //查询审批人员当前的审批状态
	       Integer apprStateByStaffNo = staffSignService.getApprStateByStaffNo(profile.getStaffNo().trim());
			//循环审批人员所在所有组的审批状态
	       if(apprStateByStaffNo !=null){
	    	   if(apprStateByStaffNo.intValue() != 0){
	    		   throw new ServiceException("审批人员正在审批中");
	    	   }
	       }

			//查询是否存在带分配的贷款审批案件参数map
			HashMap<String,Object> waitAllotParam=new HashMap<>();
			//审批人员编号
			waitAllotParam.put("apprNo",profile.getStaffNo().trim());
			//查询是否存在带分配的贷款审批案件(未分配和审批驳回的)
	        int selectWaitAllotResult = appLoanApprService.selectWaitAllot(waitAllotParam);
	        if (0 >= selectWaitAllotResult) {
	            throw new ServiceException("暂时没有贷款审批");
	        }

	        //参数map
	        HashMap<String, Object> map = new HashMap<>();
	        //人工审批开始时间
	        map.put("maunStartDate", DateUtils.getCurDateTime());
	        //审批人员编号
	        map.put("apprNo", profile.getStaffNo());
	        //审批人员姓名
	        map.put("apprName", profile.getStaffName());
	        //更新根据进入时间的第一条未被未被分配的任务
	        int updateFirstOrderByInstDate = appLoanApprService.updateFirstOrderByInstDate(map);
			//如果更新成功，说明抢单成功则更新审批状态。更新不成功则不更改审批人员状态
	        //根据人员编号更新审批人员状态(审批中)
			if(updateFirstOrderByInstDate==1){
				int updateApprStateByStaffNo = staffSignService.updateApprStateByStaffNo(profile.getStaffNo().trim());
				//sssPage<LoanApprDto> param=new Page<>();
				//查询返回抢到的列表
				return null;
			}else{
				throw new ServiceException("请重新抢单!");
			}
	    }

	    /**
	     * 抢单获取审批任务
	     *
	     * @param profile 用户信息实体
	     */
	    @Transactional
	    public synchronized Page<LoanApprDto> getGrabList(UserProfile profile) throws ServiceException
	    {
	    	
	    	//基本数据判断
	    	if(StringUtils.isBlank(profile.getStaffNo()) || StringUtils.isBlank(profile.getStaffName())) 
	    	{
	    		throw new ServiceException("审批人员编号或者姓名为空");
	    	}

	    	//获取审判员的签到状态
	    	String status = staffSignService.getStaffStat(profile.getStaffNo());
	    	//假如审批员不在线则不分配任务
			if(!StringUtils.equals(PubBusinessConstant.SIGNTYPE_ONLINE, status)) 
			{
				throw new ServiceException("审批员不在线,无法获取审批任务");
			}
			//根据审批编号查询主管编号
			Map<String,String> manageInfo = appLoanApprCheckService.getManagerNoByStaffNo(profile.getStaffNo());
			if(manageInfo == null)
			{
				throw new ServiceException("该员工组无主管，请先添加");
			}
			String staffNo = manageInfo.get("staffNo");
			int approveNum = 0;
			//主管
			if(profile.getStaffNo().equals(staffNo))
			{
				approveNum = appLoanApprCheckService.getCountByManagerNo(profile.getStaffNo());
			}
			else //非主管
			{
				approveNum = appLoanApprCheckService.getCountByStaffNo(profile.getStaffNo());
			}
			if(approveNum > 0)
			{
				throw new ServiceException("审批人员正在审批中，不能抢单");
			}
			//查询是否存在带分配的贷款审批案件参数map
			HashMap<String,Object> waitAllotParam=new HashMap<>();
			//审批人员编号
			waitAllotParam.put("apprNo",profile.getStaffNo().trim());
			//查询是否存在带分配的贷款审批案件(未分配和审批驳回的)
			int selectWaitAllotResult = appLoanApprService.selectWaitAllot(waitAllotParam);
			if (0 >= selectWaitAllotResult) 
			{
				throw new ServiceException("暂时没有贷款审批");
			}
			//参数map
			HashMap<String, Object> map = new HashMap<>();
			//人工审批开始时间
			map.put("maunStartDate", DateUtils.getCurDateTime());
			//审批人员编号
			map.put("apprNo", profile.getStaffNo());
			//审批人员姓名
			map.put("apprName", profile.getStaffName());
			//更新根据进入时间的第一条未被未被分配的任务
			int updateFirstOrderByInstDate = appLoanApprService.updateFirstOrderByInstDate(map);
			//如果更新成功，说明抢单成功则更新审批状态。更新不成功则不更改审批人员状态
			//根据人员编号更新审批人员状态(审批中)
			if(updateFirstOrderByInstDate==1)
			{
				int updateApprStateByStaffNo = staffSignService.updateApprStateByStaffNo(profile.getStaffNo().trim());
				//sssPage<LoanApprDto> param=new Page<>();
				//查询返回抢到的列表
				return null;
			}
			else
			{
				throw new ServiceException("请重新抢单!");
			}
	    }
	    
	    /**
	     * 获取待分配的单子
	     */
		@Override
		public int getWaitApprCnt(UserProfile profile) throws ServiceException, AppException {
			Map<String,Object> map = new  HashMap<>();
			map.put("apprNo",profile.getStaffNo());
			return appLoanApprService.queryForListcnt(map);
		}
		@Autowired
		private  AppLoanApprTermService apploanapprtermservice;

		@Override
		public List<AppLoanApprTermDto> getApprTermList(String jobtype) throws ServiceException, AppException {
			Map<String,Object> map =new HashMap<>();
			map.put("jobType", jobtype);
			String TermdefaultNum = ParamUtils.getParam("TERM_DEF_VAL");
			map.put("stat", CommonConstant.STAT_ENABLE);
			map.put("termdefaultNum", TermdefaultNum);
			List<AppLoanApprTerm> termList = apploanapprtermservice.queryForList(map);
			List<AppLoanApprTermDto> apploanapprtermdtos = BeanUtils.copyProperties(termList,
					AppLoanApprTermDto.class);
			return apploanapprtermdtos;
		}

		@Override
		@Transactional
		public void saveorupdateApprTermDto(AppLoanApprTermDto termdto, UserProfile userProFile) throws ServiceException, AppException {
			AppLoanApprTerm apploanapprterm = new AppLoanApprTerm();
			if(StringUtils.isEmpty(termdto.getTermId())){
			String termId = RandomUtil.getUUID();
			String redAddMan = userProFile.getStaffNo();
			apploanapprterm.setTermId(termId);
			apploanapprterm.setTermDesc(termdto.getTermDesc());
			apploanapprterm.setRedAddMan(redAddMan);
			apploanapprterm.setStat(CommonConstant.STAT_ENABLE);
			apploanapprterm.setJobTyp(termdto.getJobTyp());
			apploanapprterm.setInstDate(new Date());
			apploanapprterm.setUpdtDate(new Date());
			apploanapprtermservice.insert(apploanapprterm);
			}else{
			Map<String,Object> map =new HashMap<>();
			String redAddMan = userProFile.getStaffNo();
			map.put("termId",termdto.getTermId());
			map.put("jobTyp",termdto.getJobTyp());
			map.put("termDesc", termdto.getTermDesc());
			map.put("stat", termdto.getStat());
			map.put("redAddMan", redAddMan);
			map.put("instDate", termdto.getInstDate());
			map.put("updtDate", new Date());
			apploanapprtermservice.updateByPrimaryKeySelective(map);
			}
			
		}

		@Override
		@Transactional
		public void updateApprTermStatDto(AppLoanApprTermDto termdto, UserProfile userProFile) throws ServiceException, AppException {
			Map<String,Object> map =new HashMap<>();
			String redAddMan = userProFile.getStaffNo();
			map.put("termId",termdto.getTermId());
			if(CommonConstant.STAT_ENABLE.equals(termdto.getStat())){
			map.put("stat", CommonConstant.STAT_DISABLE);
			}else if(CommonConstant.STAT_DISABLE.equals(termdto.getStat())){
			map.put("stat", CommonConstant.STAT_ENABLE);	
			}
			map.put("redAddMan",redAddMan);
			apploanapprtermservice.updateByPrimaryKeySelective(map);
		}

		@Override
		public Page<AppLoanApprTermDto> getApprTermPage(Page<AppLoanApprTermDto> page) throws ServiceException, AppException {
			Page<AppLoanApprTerm> pageRsl =  apploanapprtermservice.queryForPage(page.toPage(AppLoanApprTerm.class));
			return pageRsl.toPage(AppLoanApprTermDto.class);
		}

		
		/**
		 * 调整审批额度
		 */
		private Map<String,Object> chageCreditlimit(UserProfile userProfile, String apprNo,String loanNo, BigDecimal creditlimitAmt)
				throws ServiceException, AppException {
			if(null == creditlimitAmt || new BigDecimal(0).compareTo(creditlimitAmt)==0){
				return null;
			}
			
			AppLoanProdDto appLoanProdDto = 	approveLoanAcctService.getLoanProd(loanNo);
			
			BigDecimal minAmt = appLoanProdDto.getMinAmt();
			if(minAmt == null) throw new ServiceException("产品最小分期金额为空 ，请联系运营");
			BigDecimal maxAmt = appLoanProdDto.getMaxAmt();
			if(maxAmt == null) throw new ServiceException("产品最大分期金额为空 ，请联系运营");
			if(creditlimitAmt.compareTo(maxAmt) == 1 || creditlimitAmt.compareTo(minAmt) == -1){
				throw new ServiceException("调整的额度不在产品规定的范围内,该产品:"+appLoanProdDto.getProdName()+"的额度范围是["+minAmt +"---"+maxAmt+"]");
			}
			 
			/*if(StringUtils.isEmpty(apprNo)){
				throw new ServiceException("审批编号为空");
			}*/
			if(StringUtils.isEmpty(loanNo)){
				throw new ServiceException("分期编号为空");
			}
			LoanAcctOutDto loanAcctOutDto = approveLoanAcctService.getLoanAcct(loanNo, userProfile);
			if(loanAcctOutDto == null){
				throw new ServiceException("获取分期信息异常");
			}
			String loanStat = loanAcctOutDto.getStat();
			if(!PubBusinessConstant.LOANSTAT_WATEAPPROV.equals(loanStat)){
				throw new ServiceException("分期状态与当前请求不一致.");
			}
		  
			/*if(creditlimitAmt.compareTo(loanAcctOutDto.getLoanAmt()) == 1){
				throw new ServiceException("授信额度不能大于申请额度");
			}*/
			
			/*//写入经办信息
			
			appLoanHandService.saveAppLoanHand(loanNo, loanAcctOutDto.getCustNo(), loanAcctOutDto.getCustName(), PubBusinessConstant.LOANHANDTYPE_CREDTLIMT, PubBusinessConstant.LOANHANDMODEL_HAND,
					userProfile.getStaffNo(), userProfile.getStaffName(), DateUtils.getCurrentTimestamp(), "分期信息额度调整，申请额度"+loanAcctOutDto.getLoanAmt()+"综合授信额度为："+creditlimitAmt+"元",PubBusinessConstant.CUST_ZC);*/
			//试算重新写入贷款金额
			List feeslst = approveLoanAcctService.queryFeesByLoanNo(loanNo);
			String  othFees = org.apache.commons.lang3.StringUtils.join(feeslst, "|");
			List<LoanProdCalcDto> list =  approveLoanAcctService.loanTryCalc(loanAcctOutDto.getProdNo(), creditlimitAmt, loanAcctOutDto.getInstNum(), othFees);
			
		 	if(list != null && list.size() > 0){
		 		Map<String,Object> map = new HashMap<>();
		 		map.put("fstRepayAmt",list.get(0).getFeeAmt());         
		 		/*** 月还款金额 */                                   
		 		map.put("mthRepayAmt",list.get(0).getFeeAmt());  
		 		map.put("loanNo", loanNo);
		 		map.put("loanAmt", creditlimitAmt);
		 		return map;
		 	 	//approveLoanAcctService.updateLoanByLoanNo(map);
		 	}else{
		 		throw new ServiceException("提额后重新计算还款计划失败");
		 	}
		}

		/**
		 * 查询最后一次调额后的额度
		 */
		@Override
		public BigDecimal queryChageCreditlimitAmt(String loanNo,UserProfile userProfile) throws ServiceException, AppException {
			Map<String, Object> param = new HashMap<>();
			param.put("loanNo", loanNo);
			param.put("handDetail", PubBusinessConstant.LOANHANDTYPE_CREDTLIMT);
			param.put("typ", PubBusinessConstant.LOANHANDMODEL_SYS);
			List<AppLoanHand> list = appLoanHandService.queryForList(param);
			if(list == null || list.size() == 0){
				return new BigDecimal(0);
			}
			LoanAcctOutDto loanAcctOutDto = approveLoanAcctService.getLoanAcct(loanNo, userProfile);
			return loanAcctOutDto.getLoanAmt();
		}
}
