package com.hs.loan.cust.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.CommonConstant;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.loan.cust.api.CustBankAcctApi;
import com.hs.loan.cust.dto.CardBinDto;
import com.hs.loan.cust.dto.CustBankAcctDto;
import com.hs.loan.cust.entity.AppCustBankAcct;
import com.hs.loan.cust.service.AppCustBankAcctService;
import com.hs.loan.pub.hand.service.AppLoanHandService;
import com.hs.loan.sale.entity.AppLoanBankAcct;
import com.hs.loan.sale.service.AppLoanBankAcctService;
import com.hs.system.area.PubSysRegionalBelongService;
import com.hs.system.entity.PubCardBin;
import com.hs.utils.BeanUtils;
import com.hs.utils.DateUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;

/**
 * 客户银行账户 服务
 * 
 * @author zwr
 *
 */
@Service
@Transactional(readOnly = true)
public class CustBankAcctServer implements CustBankAcctApi {

	@Autowired
	private AppCustBankAcctService appCustBankAcctService;
	@Autowired
	private PubSysRegionalBelongService regionalBelongService; // 区县

	@Autowired
	private AppLoanBankAcctService appLoanBankAcctService;
	@Autowired
	private AppLoanHandService appLoanHandService;

	/**
	 * 获取客户有效的银行账户信息 按时间倒序排序
	 * 
	 * @param custNo
	 * @return
	 */
	@Override
	public List<CustBankAcctDto> getListByNo(String custNo) {
		List<CustBankAcctDto> lst = BeanUtils.copyProperties(appCustBankAcctService.getListByNo(custNo),
				CustBankAcctDto.class);
		for (CustBankAcctDto dto : lst) {
			setBankAddr(dto);
		}
		return lst;
	}

	/**
	 * 获取客户无效的银行账户信息 按时间倒序排序
	 * 
	 * @param custNo
	 * @return
	 */
	@Override
	public List<CustBankAcctDto> getInvalidListByNo(String custNo) {
		List<CustBankAcctDto> lst = BeanUtils.copyProperties(appCustBankAcctService.getInvalidListByNo(custNo),
				CustBankAcctDto.class);
		for (CustBankAcctDto dto : lst) {
			setBankAddr(dto);
		}
		return lst;
	}

	/**
	 * 获取客户的银行账户信息，按时间倒序，包括有效的和无效的
	 * 
	 * @param custNo
	 * @return
	 */
	public List<CustBankAcctDto> getListAll(String custNo) {
		List<CustBankAcctDto> lst = BeanUtils.copyProperties(appCustBankAcctService.getListAll(custNo),
				CustBankAcctDto.class);
		for (CustBankAcctDto dto : lst) {
			setBankAddr(dto);
		}
		return lst;
	}

	/**
	 * 通过主键id 获取 客户银行账户信息
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CustBankAcctDto getById(String id) {
		CustBankAcctDto dto = (CustBankAcctDto) BeanUtils.copyPropertiesNotNull(new CustBankAcctDto(),
				appCustBankAcctService.getById(id));
		setBankAddr(dto);
		return dto;
	}

	/**
	 * 获取cardBin信息
	 * 
	 * @param cardBin
	 * @return
	 */
	public CardBinDto getCardBinInfo(String cardBin) {
		PubCardBin carBin = appCustBankAcctService.getCardBinInfo(cardBin);
		CardBinDto dto = (CardBinDto) BeanUtils.copyPropertiesNotNull(new CardBinDto(), carBin);
		return dto;
	}

	/**
	 * 保存（insert）银行卡信息,银行卡不可修改
	 * 
	 * @param appCustBankAcct
	 */
	@Transactional
	public void save(CustBankAcctDto custBankAcctDto) {
		AppCustBankAcct appCustBankAcct = (AppCustBankAcct) BeanUtils.copyPropertiesNotNull(new AppCustBankAcct(),
				custBankAcctDto);
		appCustBankAcctService.save(appCustBankAcct);
	}
	
	/**
	 * 保存（insert）银行卡信息,银行卡不可修改
	 * 
	 * @param appCustBankAcct
	 */
	@Transactional
	public void save(CustBankAcctDto custBankAcctDto, UserProfile userProfile, String loanNo) {
		AppCustBankAcct appCustBankAcct = (AppCustBankAcct) BeanUtils.copyPropertiesNotNull(new AppCustBankAcct(),
				custBankAcctDto);
		appCustBankAcctService.save(appCustBankAcct);
		saveLoanHand(userProfile, custBankAcctDto.getAcctNo(), custBankAcctDto.getCustNo(), loanNo,
				"保存银行卡信息新卡[" + custBankAcctDto.getAcctNo() + "]，开户行：[" + custBankAcctDto.getOpenOrg() + "]");
	}


	/**
	 * 加银行卡操作日志
	 * 
	 * @param userProfile
	 * @param acctNo
	 * @param custNo
	 * @param openOrg
	 */
	@Transactional
	private void saveLoanHand(UserProfile userProfile, String acctNo, String custNo, String loanNo, String remark) {
		// TODO Auto-generated method stub
		appLoanHandService.saveAppLoanHand(loanNo, custNo, "", PubBusinessConstant.LOANHANDTYPE_UPDATECUSTINFO,
				PubBusinessConstant.LOANHANDMODEL_HAND, userProfile.getStaffNo(), userProfile.getStaffName(),
				DateUtils.getCurrentTimestamp(), remark, PubBusinessConstant.CUST_ZC);
	}

	/**
	 * 删除银行卡信息
	 * 
	 * @param id
	 */
	@Transactional
	public void removeById(String id) {
		 appCustBankAcctService.removeById(id);
	}

	// 设置开户地址
	private void setBankAddr(CustBankAcctDto dto) {
		if (dto == null)
			return;
		dto.setOpenProvName(regionalBelongService.getProvName(dto.getOpenProv()));
		dto.setOpenCityName(regionalBelongService.getCityName(dto.getOpenCity()));
	}

	/**
	 * 获取客户的银行卡信息
	 * 
	 * @param custNo
	 *            客户号
	 * @param acctNo
	 *            银行账户号
	 */
	public CustBankAcctDto getCustBankCard(String custNo, String acctNo) {
		CustBankAcctDto dto = (CustBankAcctDto) BeanUtils.copyPropertiesNotNull(new CustBankAcctDto(),
				appCustBankAcctService.getCustBankCard(custNo, acctNo));
		setBankAddr(dto);
		return dto;
	}

	@Transactional
	public void saveCustLoanBank(CustBankAcctDto custBankAcctDto, String loanNo)
			throws ServiceException, AppException {
		if (StringUtils.isEmpty(custBankAcctDto.getCustNo())) {
			throw new AppException("银行账户信息中custNo不可为空");
		}
		appCustBankAcctService.removeByCustNo(custBankAcctDto.getCustNo());
		/* 保存 */
		AppCustBankAcct appCustBankAcct = (AppCustBankAcct) BeanUtils.copyPropertiesNotNull(new AppCustBankAcct(),
				custBankAcctDto);
		appCustBankAcctService.saveBankLoanNo(appCustBankAcct);

		/* 删除贷款银行卡信息 */
		appLoanBankAcctService.deleteByLoanNo(loanNo);

		/* 保存 */
		AppLoanBankAcct appLoanBankAcct = new AppLoanBankAcct();
		appLoanBankAcct.setId(RandomUtil.getUUID());
		appLoanBankAcct.setLoanNo(loanNo);
		appLoanBankAcct.setBankAcctId(appCustBankAcct.getId());
		appLoanBankAcct.setStat(CommonConstant.STAT_ENABLE);
		appLoanBankAcct.setInstDate(DateUtils.getCurrentDate());
		appLoanBankAcctService.insert(appLoanBankAcct);
	}
}
