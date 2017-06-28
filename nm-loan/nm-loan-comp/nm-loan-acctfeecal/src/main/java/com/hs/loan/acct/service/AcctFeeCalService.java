package com.hs.loan.acct.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.CommonConstant;
import com.hs.loan.acct.entity.PubLoanProdCalc;
import com.hs.loan.acct.entity.PubProdFeeCalc;
import com.hs.loan.acct.mapper.AcctFeeCalMapper;
import com.hs.utils.StringUtils;

/**
 * 费用计算
 * @author IT-009
 *
 */
@Service
@Transactional(readOnly=true)
public class AcctFeeCalService {
	Logger log = LoggerFactory.getLogger(AcctFeeCalService.class);
		@Autowired
		private AcctFeeCalMapper acctFeeCalMapper;
	 
	/**
	 * 产品费用试算
	 * @param pubRepayFeeConf
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public  List<PubProdFeeCalc> calc(String prodNo,BigDecimal loanAmt,int proies){
		Map<String, Object> param = new HashMap<>();
			param.put("I_PROD_NO", prodNo);
			param.put("I_INST_NUM", proies);
			param.put("I_LOAN_AMT", loanAmt);
			List<PubProdFeeCalc> list= new ArrayList<>();
			try{		
			   list= acctFeeCalMapper.calFee(param);
			}catch(Exception e){
				log.error("产品试算失败",e);
			   throw new ServiceException("产品试算失败");
			}
		return list;
	}  
	/**
	 * 分期试算
	 * @param pubRepayFeeConf
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public  List<PubLoanProdCalc> loanCalc(String prodNo,BigDecimal loanAmt,int proies,String fees){
		Map<String, Object> param = new HashMap<>();
		param.put("I_PROD_NO", prodNo);
		param.put("I_INST_NUM", proies);
		param.put("I_LOAN_AMT", loanAmt);
		param.put("I_FEE_INFO", fees);
		List<PubLoanProdCalc> list= new ArrayList<>();
		try{		
		   list= acctFeeCalMapper.loanCalFee(param);
		}catch(Exception e){
			e.printStackTrace();
			log.error("分期试算失败",e);
		   throw new ServiceException("分期试算失败"+param.get("O_RET"));
		}
		return list;
	}  
}
