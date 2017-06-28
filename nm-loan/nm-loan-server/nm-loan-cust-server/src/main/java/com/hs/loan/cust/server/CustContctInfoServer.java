package com.hs.loan.cust.server;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hs.loan.cust.api.CustContctInfoApi;
import com.hs.loan.cust.dto.CustContctInfoDto;
import com.hs.loan.cust.entity.AppCustContctInfo;
import com.hs.loan.cust.service.AppCustContctInfoService;
import com.hs.utils.BeanUtils;

/**
 * 客户联系信息 服务
 * @author zwr
 *
 */
@Service
@Transactional(readOnly=true)
public class CustContctInfoServer implements CustContctInfoApi {

	@Autowired
	private AppCustContctInfoService appCustContctInfoService;
	
	/**
	 * 通过客户号 获取客户现联系信息
	 * @return
	 */
	public CustContctInfoDto getCrtByNo(String custNo) {
		AppCustContctInfo appCustContctInfo = appCustContctInfoService.getCrtByNo(custNo);
		CustContctInfoDto custContctInfoDto = (CustContctInfoDto) BeanUtils.copyPropertiesNotNull(new CustContctInfoDto(), appCustContctInfo);
		return custContctInfoDto;
	}
	
	/**
	 * 保存或者更新 客户联系信息
	 * @param custNo
	 * @param contctLst
	 */
	@Transactional
	public void save(String custNo,CustContctInfoDto... contctLst){
		appCustContctInfoService.save(custNo, BeanUtils.copyProperties(Arrays.asList(contctLst), AppCustContctInfo.class));
	}
	
	/**
	 * 删除某个客户的 客户联系信息
	 * @param custNo
	 * @param ids
	 */
	@Transactional
	public void delete(String custNo,String... ids){
		appCustContctInfoService.delete(custNo, ids);
	}
	
	/**
	 * 通过id获取 客户联系信息
	 * @param id
	 * @return
	 */
	public CustContctInfoDto getById(String id){
		CustContctInfoDto custContctInfoDto =(CustContctInfoDto) BeanUtils.copyPropertiesNotNull(new CustContctInfoDto(),appCustContctInfoService.getById(id));
		return custContctInfoDto;
	}
	
	/**
	 * 根据客户号和有效时间段 获取 客户联系信息
	 * 
	 * @param custNo
	 * @param availableDate
	 * @return
	 */
	public List<CustContctInfoDto> getCustContctInfoListByDate(String custNo,Date availableDate){
		return BeanUtils.copyProperties(appCustContctInfoService.getCustContctInfoListByDate(custNo, availableDate),CustContctInfoDto.class);
	}
	
	/**
	 * 获取最新的 有效的  客户联系信息
	 * 
	 * @return
	 */
	public List<CustContctInfoDto> getCrtContctInfoLst(String custNo){
		return BeanUtils.copyProperties(appCustContctInfoService.getCrtContctInfoLst(custNo),CustContctInfoDto.class);
	}
	
	/**
	 * 获取客户全部的 客户联系信息
	 * @param custNo
	 * @return
	 */
	public List<CustContctInfoDto> getListByNo(String custNo){
		return BeanUtils.copyProperties(appCustContctInfoService.getListByNo(custNo), CustContctInfoDto.class);
	}
	

}
