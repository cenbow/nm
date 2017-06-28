package com.hs.loan.cust.server;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.loan.cust.api.CustViewApi;
import com.hs.loan.cust.dto.CustAssetInfoDto;
import com.hs.loan.cust.dto.CustBankAcctDto;
import com.hs.loan.cust.dto.CustCallRegisterDto;
import com.hs.loan.cust.dto.CustCarInfoDto;
import com.hs.loan.cust.dto.CustContctInfoDto;
import com.hs.loan.cust.dto.CustContctOtherDto;
import com.hs.loan.cust.dto.CustCreditInfoDto;
import com.hs.loan.cust.dto.CustEstateInfoDto;
import com.hs.loan.cust.dto.CustInfoDto;
import com.hs.loan.cust.dto.CustLiveInfoDto;
import com.hs.loan.cust.dto.CustLoanTotalDto;
import com.hs.loan.cust.dto.CustOtherLoanDto;
import com.hs.loan.cust.dto.CustStarLevelBoDto;
import com.hs.loan.pub.hand.service.AppLoanHandService;
import com.hs.utils.DateUtils;
import com.hs.utils.RandomUtil;

/**
 * 客户视图 服务
 * @author zwr
 *
 */
@Service
@Transactional(readOnly= true)
public class CustViewServer implements CustViewApi {

	@Autowired
	private CustInfoServer custInfoServer;
	@Autowired
	private CustLevelServer custLevelServer;
	@Autowired
	private CustLiveInfoServer custLiveInfoServer;
	@Autowired
	private CustContctInfoServer custContctInfoServer;
	@Autowired
	private CustContctOtherServer custContctOtherServer;
	@Autowired
	private CustAssetInfoServer custAssetInfoServer;
	@Autowired
	private CustEstateInfoServer custEstateInfoServer;
	@Autowired
	private CustCarInfoServer custCarInfoServer;
	@Autowired
	private CustCreditInfoServer custCreditInfoServer;
	@Autowired
	private CustOtherLoanServer custOtherLoanServer;
//	@Autowired
//	private CustLoanInfoServer custLoanInfoServer;
	@Autowired
	private CustCallRegisterServer custCallRegisterServer;
	@Autowired
	private CustBankAcctServer custBankAcctServer;
	@Autowired
	private CustLoanTotalServer custLoanTotalServer;
	@Autowired
	private AppLoanHandService appLoanHandService;
	/**
	 * 通过客户号，获取客户基本信息（包含户籍信息）
	 * @param custNo
	 * @return
	 */
	public CustInfoDto getCustInfoByNo(String custNo) {
		CustInfoDto custInfoDto =  custInfoServer.getByNo(custNo);
		return custInfoDto;
	}

	/**
	 * 通过客户号 获取客户的星级评估信息
	 * @param custNo
	 * @return
	 */
	public CustStarLevelBoDto getCustStarLevel(String custNo) {
		CustStarLevelBoDto custStarLevelBoDto = custLevelServer.getStarEvaluate(custNo);
		if(custStarLevelBoDto==null){
			return null;
		}
		custStarLevelBoDto.setLevelDefine(custLevelServer.getLevelDefine());
		custStarLevelBoDto.setCustNo(custNo);
		return custStarLevelBoDto;
	}

	/**
	 * 通过客户号 获取 客户的现居住信息
	 * @param custNo
	 * @return
	 */
	public CustLiveInfoDto getCustCrtLiveInfoByNo(String custNo) {
		return custLiveInfoServer.getCrtByNo(custNo);
	}

	/**
	 * 通过客户号 获取客户现联系信息
	 * @return
	 */
	@Override
	public CustContctInfoDto getCustCrtContctInfoByNo(String custNo) {
		return custContctInfoServer.getCrtByNo(custNo);
	}

	/**
	 * 通过客户号 获取 客户其他联系人信息
	 * @param custNo
	 * @return
	 */
	@Override
	public List<CustContctOtherDto> getCustContctOtherLst(String custNo) {
		return custContctOtherServer.getListByNo(custNo);
	}

	/**
	 * 通过客户号 获取 客户资产信息(收入来源)
	 * @param custNo
	 * @return
	 */
	@Override
	public List<CustAssetInfoDto> getCustAssetInfoLst(String custNo) {
		return custAssetInfoServer.getListByNo(custNo);
	}

	/**
	 * 通过客户号 获取 客户房产信息
	 * @param custNo
	 * @return
	 */
	@Override
	public List<CustEstateInfoDto> getCustEstateInfoLst(String custNo) {
		return custEstateInfoServer.getListByNo(custNo);
	}

	/**
	 * 通过客户号，获取 客户车辆信息
	 * @param custNo
	 * @return
	 */
	@Override
	public List<CustCarInfoDto> getCustCarInfoLst(String custNo) {
		return custCarInfoServer.getListByNo(custNo);
	}

	/**
	 * 通过客户号 获取 客户信用卡信息
	 * @param custNo
	 * @return
	 */
	@Override
	public List<CustCreditInfoDto> getCustCreditInfoLst(String custNo) {
		return custCreditInfoServer.getListByNo(custNo);
	}

	/**
	 * 通过客户号 获取 客户其他分期信息
	 * @param custNo
	 * @return
	 */
	@Override
	public List<CustOtherLoanDto> getCustOtherLoanLst(String custNo) {
		return custOtherLoanServer.getListByNo(custNo);
	}

	/**
	 * 分页查询 客户分期信息 按时间倒序排
	 * 必须的参数 custNo
	 * @param page
	 * @return
	 */
//	@Override
//	public Page<CustLoanInfoDto> queryCustLoanInfo(Page<CustLoanInfoDto> page) {
//		return custLoanInfoServer.queryCustLoanInfo(page);
//	}

	/**
	 * 分页查询 客户来电记录 按时间倒序排序
	 * 必须的参数 custNo
	 * @param page
	 * @return
	 */
	@Override
	public Page<CustCallRegisterDto> queryCustCallRegister(Page<CustCallRegisterDto> page) {
		return custCallRegisterServer.queryCustCallRegister(page);
	}

	/**
	 * 获取客户有效的银行账户信息
	 * @param custNo
	 * @return
	 */
	@Override
	public List<CustBankAcctDto> getCustBankAcctLst(String custNo) {
		return custBankAcctServer.getListByNo(custNo);
	}

	/**
	 * 获取客户无效的银行账户信息
	 * @param custNo
	 * @return
	 */
	@Override
	public List<CustBankAcctDto> getInvalidCustBankAcctLst(String custNo) {
		return custBankAcctServer.getInvalidListByNo(custNo);
	}

	/**
	 * 通过客户号 获取客户分期汇总信息
	 * @param custNo
	 * @return
	 */
	@Override
	public CustLoanTotalDto getCustLoanTotal(String custNo) {
		return custLoanTotalServer.getByNo(custNo);
	}
	
	/**
	 *  新增客户来电信息
	 */
	@Transactional
	public void instCallRegister(CustCallRegisterDto custCallRegisterDto, UserProfile userProfile,String loanNo)
			throws ServiceException, AppException {
		// TODO Auto-generated method stub
		if (custCallRegisterDto!=null) {
			custCallRegisterDto.setId(RandomUtil.getUUID());
			custCallRegisterDto.setBeginDate(new Date());
			custCallRegisterDto.setEndDate(new Date());
		}
		custCallRegisterServer.insert(custCallRegisterDto);
		/* 添加经办信息 */
		appLoanHandService.saveAppLoanHand(loanNo, custCallRegisterDto.getCustNo(),
				custCallRegisterDto.getCustName(),
				PubBusinessConstant.LOANHANDTYPE_CUSTSERVICE,
				PubBusinessConstant.LOANHANDMODEL_HAND, userProfile.getStaffNo(),
				userProfile.getStaffName(), DateUtils.getCurrentTimestamp(),
				"添加来电记录 ："+custCallRegisterDto.getCallContent(),PubBusinessConstant.CUST_ZC);
		
	}

}
