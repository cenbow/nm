package com.hs.loan.acctplus.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.hs.base.exception.ServiceException;
import com.hs.loan.acct.dto.AppLoanAcctDto;
import com.hs.loan.acctplus.Config;
import com.hs.loan.acctplus.common.DBSTAT;
import com.hs.loan.acctplus.mapper.AppLoanAcctMapper;
import com.hs.loan.acctplus.model.AppLoanAcct;
import com.hs.loan.acctplus.model.AppLoanAppr;
import com.hs.loan.acctplus.model.AppLoanDmScore;
import com.hs.loan.acctplus.model.AppLoanHand;
import com.hs.loan.acctplus.model.DmOrderInfo;
import com.hs.loan.acctplus.service.AppLoanAcctService;
import com.hs.loan.acctplus.service.AppLoanApprService;
import com.hs.loan.acctplus.service.AppLoanDmScoreService;
import com.hs.loan.acctplus.service.AppLoanHandService;
import com.hs.loan.acctplus.service.DmOrderInfoService;
import com.hs.loan.acctplus.util.AppLoanUtil;
import com.hs.loan.acctplus.util.BeanFactory;
import com.hs.loan.acctplus.util.HttpInvokerUtil;

/**
 *
 * AppLoanAcct 表数据服务层接口实现类
 *
 */
@Service
public class AppLoanAcctServiceImpl extends CommonServiceImpl<AppLoanAcctMapper, AppLoanAcct> implements AppLoanAcctService {

	@Autowired 
	private AppLoanAcctMapper appLoanAcctMapper;
	
	@Autowired
	private DmOrderInfoService dmOrderInfoService;
	
	@Autowired
	private AppLoanDmScoreService appLoanDmScoreService;
	
	@Transactional()
	public void procFundMatch(Map<String,String> param){
		appLoanAcctMapper.procFundMatch(param);
	}

	public void multipleEnter(Map<String, String> param) {
		appLoanAcctMapper.multipleEnter(param);
	}

	@Transactional(rollbackFor=Exception.class)
	public void handApproval(AppLoanAcctDto acct, String acct_stat,
			String appr_stat, String hand_remark) {
			//修改app_loan_acct状态
			AppLoanAcct ac = new AppLoanAcct();
			ac.setLoanNo(acct.getLoanNo());
			ac.setStat(acct_stat);
			AppLoanAcctService appLoanAcctService = BeanFactory.getBean(AppLoanAcctService.class);
			boolean flag = appLoanAcctService.updateSelectiveById(ac);
			if(!flag){
				throw new ServiceException("update acct stat return false !");
			}
			//插入贷款审批信息
			AppLoanAppr ap = AppLoanUtil.getAppLoanApprByDto(acct);
			ap.setInstNum(1);
			ap.setStat(appr_stat);
			ap.setManuFlag("10001002");
			ap.setApprNo("AUTO");
			ap.setApprName("AUTO");
			AppLoanApprService appLoanApprService = BeanFactory.getBean(AppLoanApprService.class);
			appLoanApprService.insert(ap);
//			insertAppLoanApprCache(ap);
			
			//插入贷款经办信息
			AppLoanHand h = AppLoanUtil.getAppLoanHandByDto(acct);
			h.setRemark(hand_remark);
			AppLoanHandService appLoanHandService = BeanFactory.getBean(AppLoanHandService.class);
			appLoanHandService.insert(h);
		
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void handApprovalRefuse(AppLoanAcctDto acct,String hand_remark){
		String acct_stat = DBSTAT.APP_LOAN_ACCT_STAT_REFUSE;
		String appr_stat = DBSTAT.APP_LOAN_APPR_STAT_REFUSE;
		handApproval(acct, acct_stat, appr_stat, hand_remark);
	}

	@Transactional(rollbackFor=Exception.class)
	public void handApprovalPass(AppLoanAcctDto acct, String hand_remark) {
		String acct_stat = DBSTAT.APP_LOAN_ACCT_STAT_PASS;
		String appr_stat = DBSTAT.APP_LOAN_APPR_STAT_PASS;
		
		handApproval(acct, acct_stat, appr_stat, hand_remark);
		//调用存储过程 资金匹配
		Map<String,String> pm = new HashMap<String, String>();
		pm.put("loanNo", acct.getLoanNo());
		pm.put("stat", "1");
		procFundMatch(pm);//这里需要在同一个事务里处理
		
	}

	public int isBlacklistBranchNo(Map<String, String> param) {
		return appLoanAcctMapper.isBlacklistBranchNo(param);
	}

	public boolean sendBigData(AppLoanAcctDto acct) throws Exception {

		boolean flag = false;
		//根据loan_no去取到orderIds
		String orderId = getOrderIDs(acct.getLoanNo());
		//订单的来源组织机构
		String orgNo = acct.getOrgNo();
		String result = HttpInvokerUtil.executeHttpPost(Config.getBig_data_url(), preParams(orderId));
		
		JSONObject jsonObject = JSONObject.parseObject(result);
		String code = jsonObject.getString("errorCode");
		String data = jsonObject.getString("data");
		com.alibaba.fastjson.JSONArray jsonArr = JSONObject.parseArray(data);
		AppLoanAcctService appLoanAcctService = BeanFactory.getBean(AppLoanAcctService.class);
		if("200".equals(code)){
			for(int i=0;i<jsonArr.size();i++){
				//存分数
				AppLoanDmScore dmpo = saveAppLoanDmScore(jsonArr);
				//不等于数尊 && 不等于旅游
				if(!DBSTAT.ORG_NO_SHUZUN.equals(orgNo) && !DBSTAT.ORG_NO_LVYOU.equals(orgNo)){
					if("1".equals(dmpo.getResult())){//审批通过
						appLoanAcctService.handApprovalPass(acct,"大数据-系统写入");
					}else{//审批拒绝
						appLoanAcctService.handApprovalRefuse(acct, "大数据-系统写入");
					}
				}else if(DBSTAT.ORG_NO_SHUZUN.equals(orgNo) || DBSTAT.ORG_NO_LVYOU.equals(orgNo)){
					if("2".equals(dmpo.getResult())){//审批拒绝
						appLoanAcctService.handApprovalRefuse(acct, "大数据-系统写入");
					}
				}
				
				flag = true;
			}
		}
		return flag;
	}

	
	
	public AppLoanDmScore saveAppLoanDmScore(com.alibaba.fastjson.JSONArray jsonArr){
		JSONObject jsonData = jsonArr.getJSONObject(0);
		AppLoanDmScore dmpo = new AppLoanDmScore();
		dmpo.setOrderId(jsonData.getString("orderId"));
		dmpo.setCreateDate(new Date());
		dmpo.setScore(jsonData.getInteger("creditScore"));
		dmpo.setResult(jsonData.getString("checkResult"));
		appLoanDmScoreService.insertSelective(dmpo);
		return dmpo;
	}

	public String getOrderIDs(String loan){
		
		DmOrderInfo dmin = new DmOrderInfo();
		dmin.setLoanNo(loan);
		DmOrderInfo dmin2 = dmOrderInfoService.selectOne(dmin);
		if(dmin2==null){
			throw new ServiceException("not find orderid by loan:"+loan);
		}
		return dmin2.getOrderId();
	}
	
	public List<NameValuePair> preParams(String orderId){
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
    	parameters.add(new BasicNameValuePair("orderIds", orderId));
    	parameters.add(new BasicNameValuePair("appKey", "lemon"));
    	return parameters;
	}

}