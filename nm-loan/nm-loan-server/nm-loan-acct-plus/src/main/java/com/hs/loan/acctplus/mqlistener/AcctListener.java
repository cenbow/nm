package com.hs.loan.acctplus.mqlistener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hs.base.exception.ServiceException;
import com.hs.loan.acct.dto.AppLoanAcctDto;
import com.hs.loan.acctplus.Config;
import com.hs.loan.acctplus.cache.AcctCache;
import com.hs.loan.acctplus.common.DBSTAT;
import com.hs.loan.acctplus.model.AppLoanAppr;
import com.hs.loan.acctplus.model.AppLoanDmScore;
import com.hs.loan.acctplus.model.DmOrderInfo;
import com.hs.loan.acctplus.service.AppLoanAcctService;
import com.hs.loan.acctplus.service.AppLoanApprService;
import com.hs.loan.acctplus.service.AppLoanDmScoreService;
import com.hs.loan.acctplus.service.DmOrderInfoService;
import com.hs.loan.acctplus.service.impl.AcctPlusServiceImpl;
import com.hs.loan.acctplus.util.AppLoanUtil;
import com.hs.loan.acctplus.util.BeanFactory;
import com.hs.loan.acctplus.util.HttpInvokerUtil;
import com.rabbitmq.client.Channel;

/**
 * <li>@ClassName: AcctListener
 * <li>@Description: 接收MQ消息
 * <li>@author zzy
 * <li>@date 2017年1月4日
 * <li>
 */
@Component
public class AcctListener implements ChannelAwareMessageListener {

	/**
	 * 推送审批
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void onMessage(Message msg, Channel channel) throws Exception {
		
		
		boolean flag = false;
		try {
			//等前面事务提交完毕
			Thread.sleep(500);
			
			AppLoanAcctDto acct = JSON.parseObject(msg.getBody(), AppLoanAcctDto.class);
			AppLoanAcctService appLoanAcctService = BeanFactory.getBean(AppLoanAcctService.class);
			//二次进件的
			if("mult".equals(acct.getEnterType())){
				Map<String,String> param = new HashMap<String, String>();
				param.put("loanNo", acct.getLoanNo());
				appLoanAcctService.multipleEnter(param);
				return;
			}
			//历史的app_loan_acct加入缓存，判断loan是否存在，且状态是否正确。如果信任客户端就不需要验证
			//提交待审批
			if(DBSTAT.APP_LOAN_ACCT_STAT_WAITING_APPROVAL.equals(acct.getStat())){
				
				int fileNo = Integer.parseInt(acct.getFileNo());
				if(fileNo%DBSTAT.FILE_REFUSE==0){//直接拒绝
					appLoanAcctService.handApprovalRefuse(acct,"手拒单-系统写入");
					flag = true;
				}else if(isBlacklistBranchNo(acct)){//验证商户黑名单
					appLoanAcctService.handApprovalRefuse(acct,"商户黑名单-系统写入");
					flag = true;
				}else{
					//一般订单消息在MQ中存在N分钟后 直接变为人工审核 不再走大数据
					if(acct.getEnterMqTime()+DBSTAT.EXPIRED_TIME_MILLISECOND<System.currentTimeMillis()){
						artificialHand(acct);
						flag = true;
						return;
					}
					flag = appLoanAcctService.sendBigData(acct);
				
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				// 确认消息，已经收到
				channel.basicAck(msg.getMessageProperties().getDeliveryTag(),false);
			} else {
				// 消息确认失败，消息重回队列,这里马上就可以重新收到该消息
				channel.basicNack(msg.getMessageProperties().getDeliveryTag(),false, true);
			}
		}
	}
	
	public boolean isBlacklistBranchNo(AppLoanAcctDto acct){
		String branchNo = acct.getBranchNo();
		Boolean flag = AcctCache.getBlackBranchCache(branchNo);
		if(flag!=null){
			return flag.booleanValue();
		}else{
			//看能拿到商户编号不
			Map<String,String> param = new HashMap<String, String>();
			param.put("branchNo", branchNo);
			AppLoanAcctService appLoanAcctService = BeanFactory.getBean(AppLoanAcctService.class);
			int num = appLoanAcctService.isBlacklistBranchNo(param);
			boolean f = num>0?true:false;
			AcctCache.addBlackBranchCache(branchNo,f);
			return f;
		}
		
	}
	public static void main(String[] args) {
		AcctPlusServiceImpl a = new AcctPlusServiceImpl();
		AppLoanAcctDto acct = new AppLoanAcctDto();
		a.check(acct);
	}
	
	public void artificialHand(AppLoanAcctDto acct){
		//插入贷款审批信息
		AppLoanAppr ap = AppLoanUtil.getAppLoanApprByDto(acct);
		ap.setInstNum(1);
		ap.setStat("40002005");//人工审核
		ap.setManuFlag("10001001");
		AppLoanApprService appLoanApprService = BeanFactory.getBean(AppLoanApprService.class);
		appLoanApprService.insert(ap);
	}
}
