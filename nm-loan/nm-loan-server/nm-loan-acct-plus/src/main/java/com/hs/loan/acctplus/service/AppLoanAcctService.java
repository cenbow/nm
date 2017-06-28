package com.hs.loan.acctplus.service;

import java.util.Map;

import com.hs.loan.acct.dto.AppLoanAcctDto;
import com.hs.loan.acctplus.model.AppLoanAcct;
import com.hs.loan.acctplus.model.AppLoanDmScore;
import com.baomidou.framework.service.ICommonService;

/**
 *
 * AppLoanAcct 表数据服务层接口
 *
 */
public interface AppLoanAcctService extends ICommonService<AppLoanAcct> {

	//资金匹配存储过程
	public void procFundMatch(Map<String,String> param);
	
	//多次进件
	public void multipleEnter(Map<String,String> param);
	
	//处理进件数据库
	public void handApproval(AppLoanAcctDto acct,String acct_stat,String appr_stat,String hand_remark);
	
	//处理进件数据库成功并处理资金匹配
	public void handApprovalPass(AppLoanAcctDto acct,String hand_remark);
	
	//审批拒绝
	public void handApprovalRefuse(AppLoanAcctDto acct,String hand_remark);

	//查询商户黑名单
	public int isBlacklistBranchNo(Map<String,String> param);
	
	//走大数据获取分数
	public boolean sendBigData(AppLoanAcctDto acct) throws Exception;
	
	//获取订单id
	public String getOrderIDs(String loan);
	
	//保存大数据分数
	public AppLoanDmScore saveAppLoanDmScore(com.alibaba.fastjson.JSONArray jsonArr);
}