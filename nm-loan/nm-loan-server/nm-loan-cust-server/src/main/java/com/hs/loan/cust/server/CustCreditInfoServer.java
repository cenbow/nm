package com.hs.loan.cust.server;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.loan.cust.api.CustCreditInfoApi;
import com.hs.loan.cust.dto.CustCreditInfoDto;
import com.hs.loan.cust.entity.AppCustCreditInfo;
import com.hs.loan.cust.service.AppCustCreditInfoService;
import com.hs.utils.BeanUtils;


/**
 * 客户信用卡信息 服务
 * @author zwr
 *
 */
@Service
@Transactional(readOnly = true)
public class CustCreditInfoServer implements CustCreditInfoApi {

	@Autowired
	private AppCustCreditInfoService appCustCreditInfoService;
	
	/**
	 * 根据客户号后去 客户信用卡信息 list
	 * @param custNo
	 * @return
	 */
	public List<CustCreditInfoDto> getListByNo(String custNo) {
		return BeanUtils.copyProperties(appCustCreditInfoService.getListByNo(custNo), CustCreditInfoDto.class);
	}
	
	
	/**
	 * 保存或者更新 客户信用卡信息
	 * 必须的参数，custNo
	 * 
	 * @param appCustCreditInfo
	 */
	@Transactional
	public void save(String custNo,CustCreditInfoDto... creditLst){
		appCustCreditInfoService.save(custNo, BeanUtils.copyProperties(Arrays.asList(creditLst), AppCustCreditInfo.class));
	}
	
	/**
	 * 获取 客户 在有效时间段里的 信用卡信息 列表
	 * 
	 * @param availableDate 有效时间
	 * @return
	 */
	public List<CustCreditInfoDto> getCustCreditLstByDate(String custNo,Date availableDate){
		return BeanUtils.copyProperties(appCustCreditInfoService.getCustCreditLstByDate(custNo, availableDate),CustCreditInfoDto.class);
	}
	
	
	/**
	 * 获取客户当前 有效的 信用卡信息 列表
	 * 
	 * @return
	 */
	public  List<CustCreditInfoDto> getCrtCustCreditInfoLst(String custNo){
		return BeanUtils.copyProperties(appCustCreditInfoService.getCrtCustCreditInfoLst(custNo),CustCreditInfoDto.class);
	}
	
	/**
	 * 通过id 获取 客户信用卡信息
	 */
	public CustCreditInfoDto getById(String id){
		CustCreditInfoDto custCreditInfoDto =(CustCreditInfoDto) BeanUtils.copyPropertiesNotNull(new CustCreditInfoDto(), appCustCreditInfoService.getById(id));
		return custCreditInfoDto;
	}
	
	/**
	 * 删除 客户信用卡信息
	 * @param ids
	 */
	@Transactional
	public void delete(String custNo,String... ids){
		appCustCreditInfoService.delete(custNo, ids);
	}
	
	/**
	 * 获取 客户信用卡信息 list
	 * @param param
	 * @return
	 */
	public List<CustCreditInfoDto> getList(Map<String,Object> param){
		return BeanUtils.copyProperties(appCustCreditInfoService.getList(param), CustCreditInfoDto.class) ;
	}

	/**
	 * 获取编辑过的 信用卡信息
	 * 
	 * @param appCustCreditInfo
	 * @return
	 */
	public CustCreditInfoDto getEditedCreditInfo(CustCreditInfoDto custCreditInfoDto){
		AppCustCreditInfo appCustCreditInfo = appCustCreditInfoService.getEditedCreditInfo((AppCustCreditInfo)BeanUtils.copyPropertiesNotNull(new AppCustCreditInfo(), custCreditInfoDto));
		return (CustCreditInfoDto) BeanUtils.copyPropertiesNotNull(new CustCreditInfoDto() , appCustCreditInfo);
	}
	
}
