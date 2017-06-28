package com.hs.loan.cust.server;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.loan.cust.api.CustWorkApi;
import com.hs.loan.cust.dto.CustWorkDto;
import com.hs.loan.cust.entity.AppCustWork;
import com.hs.loan.cust.service.AppCustWorkService;
import com.hs.system.area.PubSysRegionalBelongService;
import com.hs.utils.BeanUtils;

/**
 * 客户工作信息 服务
 * 
 * @author zwr
 *
 */
@Service
@Transactional(readOnly=true)
public class CustWorkServer implements CustWorkApi {

	@Autowired
	private AppCustWorkService appCustWorkService;
	
	@Autowired
	private PubSysRegionalBelongService regionalBelongService;	//区县
	
	/**
	 * 保存或更新 客户工作信息
	 * @param custNo
	 * @param workLst
	 */
	@Transactional
	public void save(String custNo,CustWorkDto... workLst){
		appCustWorkService.save(custNo, BeanUtils.copyProperties(Arrays.asList(workLst), AppCustWork.class));
	}

	/**
	 * 删除 客户工作信息
	 * @param custNo
	 * @param ids
	 */
	@Transactional
	public void delete(String custNo , String... ids){
		appCustWorkService.delete(custNo, ids);
	}
	
	/**
	 * 通过id 获取客户工作信息
	 * @param id
	 * @return
	 */
	public CustWorkDto getById(String id){
		AppCustWork appCustWork = appCustWorkService.getById(id);
		CustWorkDto dto = (CustWorkDto) BeanUtils.copyPropertiesNotNull(new CustWorkDto(), appCustWork);
		setWorkAddr(dto);
		return dto;
	}
	
	/**
	 * 获取 客户工作信息 list
	 * 
	 * @param param
	 * @return
	 */
	public List<CustWorkDto> getList(Map<String,Object> param){
		List<CustWorkDto> list =  BeanUtils.copyProperties(appCustWorkService.getList(param), CustWorkDto.class);
		for(CustWorkDto dto : list){
			setWorkAddr(dto);
		}
		return list;
	}
	
	/**
	 * 获取客户有效时间段里的 有效的 客户工作信息
	 * 
	 * @param custNo
	 * @param availableDate
	 * @return
	 */
	public List<CustWorkDto> getCustWorkLstByDate(String custNo,Date availableDate){
		List<CustWorkDto> list = BeanUtils.copyProperties(appCustWorkService.getCustWorkLstByDate(custNo, availableDate), CustWorkDto.class);
		for(CustWorkDto dto : list){
			setWorkAddr(dto);
		}
		return list;
	}
	
	/**
	 * 获取当前 有效的 客户工作信息
	 * @param custNo
	 * @return
	 */
	public List<CustWorkDto> getCrtCustWorkLst(String custNo){
		List<CustWorkDto> list = BeanUtils.copyProperties(appCustWorkService.getCrtCustWorkLst(custNo), CustWorkDto.class);
		for(CustWorkDto dto : list){
			setWorkAddr(dto);
		}
		return list;
	}
	
	/**
	 * 设置工作地的地址
	 */
	private void setWorkAddr(CustWorkDto dto){
		if(dto == null) return;
		
		dto.setUnitProvName(regionalBelongService.getProvName(dto.getUnitProv()));
		dto.setUnitCityName(regionalBelongService.getCityName(dto.getUnitCity()));
		dto.setUnitAreaName(regionalBelongService.getCountName(dto.getUnitArea()));
	}
	
}
