package com.hs.loan.cust.server;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hs.loan.cust.api.CustOtherLoanApi;
import com.hs.loan.cust.dto.CustOtherLoanDto;
import com.hs.loan.cust.entity.AppCustOtherLoan;
import com.hs.loan.cust.service.AppCustOtherLoanService;
import com.hs.utils.BeanUtils;

/**
 * 客户其他分期信息 服务
 * @author zwr
 *
 */
@Service
@Transactional(readOnly = true)
public class CustOtherLoanServer implements CustOtherLoanApi {

	@Autowired
	private AppCustOtherLoanService appCustOtherLoanService;
	
	/**
	 * APP_客户其他分期信息 接口
	 * @author autocreate
	 * @create 2015-10-26
	 */
	@Override
	public List<CustOtherLoanDto> getListByNo(String custNo) {
		return BeanUtils.copyProperties(appCustOtherLoanService.getListByNo(custNo), CustOtherLoanDto.class);
	}

	/**
	 * 保存或者更新 客户其他分期信息
	 * 
	 * @param custNo
	 * @param loanLst
	 */
	@Transactional
	public void save(String custNo, CustOtherLoanDto... loanLst){
		appCustOtherLoanService.save(custNo, BeanUtils.copyProperties(Arrays.asList(loanLst), AppCustOtherLoan.class));
	}
	
	/**
	 * 删除
	 * 
	 * @param custNo
	 * @param ids
	 */
	@Transactional
	public void delete(String custNo,String... ids){
		appCustOtherLoanService.delete(custNo, ids);
	}
	
	/**
	 * 通过id获取 客户其他分期信息
	 * @param id
	 */
	public CustOtherLoanDto getById(String id){
		AppCustOtherLoan app = appCustOtherLoanService.getById(id);
		CustOtherLoanDto dto = (CustOtherLoanDto) BeanUtils.copyPropertiesNotNull( new CustOtherLoanDto(), app);
		return dto;
	}
	
	/**
	 * 获取 客户其他分期信息 list
	 * 
	 * @param param
	 * @return
	 */
	public List<CustOtherLoanDto> getList(Map<String,Object> param){
		return BeanUtils.copyProperties(appCustOtherLoanService.getList(param), CustOtherLoanDto.class);
	}
	
	/**
	 * 获取有效时间段的有效的 客户其他分期信息
	 * @param custNo
	 * @param availableDate
	 * @return
	 */
	public List<CustOtherLoanDto> getCustOtherLoanLstByDate(String custNo,Date availableDate){
		return BeanUtils.copyProperties(appCustOtherLoanService.getCustOtherLoanLstByDate(custNo, availableDate), CustOtherLoanDto.class);
	}
	
	/**
	 * 获取当前 有效的 客户其他分期信息
	 * 
	 * @param custNo
	 * @return
	 */
	public List<CustOtherLoanDto> getCrtCustOtherLoanLst(String custNo){
		return BeanUtils.copyProperties(appCustOtherLoanService.getCrtCustOtherLoanLst(custNo), CustOtherLoanDto.class); 
	}
	
	/**
	 * 获取刚刚编辑的其他分期信息
	 * 
	 * @return
	 */
	public CustOtherLoanDto getEditedOtherLoan(CustOtherLoanDto custOtherLoanDto){
		AppCustOtherLoan appCustOtherLoan = appCustOtherLoanService.getEditedOtherLoan((AppCustOtherLoan)BeanUtils.copyPropertiesNotNull(new AppCustOtherLoan(), custOtherLoanDto));
		return (CustOtherLoanDto) BeanUtils.copyPropertiesNotNull(new CustOtherLoanDto(), appCustOtherLoan);
	}
	
}
