package com.hs.loan.cust.server;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.loan.cust.api.CustCarInfoApi;
import com.hs.loan.cust.dto.CustCarInfoDto;
import com.hs.loan.cust.entity.AppCustCarInfo;
import com.hs.loan.cust.service.AppCustCarInfoService;
import com.hs.utils.BeanUtils;

/**
 * 客户车辆信息 服务
 * @author zwr
 *
 */
@Service
@Transactional(readOnly = true)
public class CustCarInfoServer implements CustCarInfoApi {

	@Autowired
	private AppCustCarInfoService appCustCarInfoService;
	
	/**
	 * 通过客户号 获取 客户车辆信息
	 */
	@Override
	public List<CustCarInfoDto> getListByNo(String custNo) {
		return BeanUtils.copyProperties(appCustCarInfoService.getListByNo(custNo), CustCarInfoDto.class);
	}

	/**
	 * 保存或者更新 客户车辆信息
	 * @param custNo
	 * @param carLst
	 */
	@Transactional
	public void save(String custNo,CustCarInfoDto... carLst){
		appCustCarInfoService.save(custNo, BeanUtils.copyProperties(Arrays.asList(carLst), AppCustCarInfo.class));
	}
	
	/**
	 * 删除
	 * @param custNo
	 * @param ids
	 */
	@Transactional
	public void delete(String custNo,String... ids){
		appCustCarInfoService.delete(custNo, ids);
	}
	
	/**
	 * 通过id获取 客户车辆信息
	 * 
	 * @param id
	 * @return
	 */
	public CustCarInfoDto getById(String id){
		AppCustCarInfo appCustCarInfo = appCustCarInfoService.getById(id);
		CustCarInfoDto dto = (CustCarInfoDto) BeanUtils.copyPropertiesNotNull( new CustCarInfoDto(), appCustCarInfo);
		return dto;
	}
	
	/**
	 * 获取 客户车辆信息lst
	 * @param param
	 * @return
	 */
	public List<CustCarInfoDto> getList(Map<String,Object> param){
		return BeanUtils.copyProperties(appCustCarInfoService.getList(param),CustCarInfoDto.class);
	}
	
	/**
	 * 获取有效时间段里的 有效的 客户车辆信息
	 * @param custNo
	 * @param availableDate
	 * @return
	 */
	public List<CustCarInfoDto> getCustCarInfoLstByDate(String custNo,Date availableDate){
		return BeanUtils.copyProperties(appCustCarInfoService.getCustCarInfoLstByDate(custNo, availableDate), CustCarInfoDto.class);
	}
	
	/**
	 * 获取客户当前 有效的 客户车辆信息
	 * @param custNo
	 * @return
	 */
	public List<CustCarInfoDto> getCrtCustCarInfoLst(String custNo){
		return BeanUtils.copyProperties(appCustCarInfoService.getCrtCustCarInfoLst(custNo), CustCarInfoDto.class);
	}
	
	/**
	 * 获取刚刚修改过的车辆信息
	 * 
	 * @param appCustCarInfo
	 * @return
	 */
	public CustCarInfoDto getEditedCarInfo(CustCarInfoDto custCarInfoDto){
		AppCustCarInfo appCustCarInfo = appCustCarInfoService.getEditedCarInfo((AppCustCarInfo)BeanUtils.copyPropertiesNotNull(new AppCustCarInfo(), custCarInfoDto));
		return (CustCarInfoDto) BeanUtils.copyPropertiesNotNull(new CustCarInfoDto(), appCustCarInfo);
	}
	
	
}
