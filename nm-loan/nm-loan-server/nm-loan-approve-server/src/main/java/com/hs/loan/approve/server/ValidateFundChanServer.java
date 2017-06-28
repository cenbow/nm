package com.hs.loan.approve.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.loan.approve.contant.ApproveContant;
import com.hs.loan.certification.api.jiufu.JfEnterApi;
import com.hs.loan.cust.dto.CustInfoDto;
import com.hs.loan.finance.dto.FundChanInfoDto;

/**
 * 验证资金渠道
 * 
 * @author zwr
 *
 */
@Service
@Transactional
public class ValidateFundChanServer {

	/*@Autowired
	private JfEnterApi approveJfEnterService;*/
	
	/**
	 * 检查渠道是否可用
	 * 
	 * @return true 可用，false 不可用
	 */
	/*public boolean checkChanIzAvailable(String loanNo,CustInfoDto custDto,FundChanInfoDto chanDto){
		String chanNo = chanDto.getChanNo();
		
		return isInJfBlackList(custDto.getCertNo(), custDto.getCustName());
	}
	
	public boolean isInJfBlackList(String certNo,String custName){
		return approveJfEnterService.validateCoustmor(certNo, custName);
	}
	*/
	
}
