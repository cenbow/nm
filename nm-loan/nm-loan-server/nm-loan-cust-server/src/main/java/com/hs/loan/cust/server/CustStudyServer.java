package com.hs.loan.cust.server;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.loan.cust.api.CustStudyApi;
import com.hs.loan.cust.dto.CustStudyDto;
import com.hs.loan.cust.entity.AppCustStudy;
import com.hs.loan.cust.service.AppCustStudyService;
import com.hs.system.area.PubSysRegionalBelongService;
import com.hs.utils.BeanUtils;

/**
 * 客户学校信息 服务
 * @author zwr
 *
 */
@Service
@Transactional(readOnly=true)
public class CustStudyServer implements CustStudyApi {

	@Autowired
	private AppCustStudyService appCustStudyService;
	@Autowired
	private PubSysRegionalBelongService regionalBelongService;	//区县
	
	/**
	 * 保存或者更新 客户学校信息
	 * @param cust
	 * @param studyLst
	 */
	@Transactional
	public void save(String custNo,CustStudyDto... studyLst){
		appCustStudyService.save(custNo, BeanUtils.copyProperties(Arrays.asList(studyLst), AppCustStudy.class));
	}
	
	
	/**
	 * 删除 客户学校信息
	 * @param custNo
	 * @param ids
	 */
	@Transactional
	public void delete(String custNo,String... ids){
		appCustStudyService.delete(custNo, ids);
	}
	
	/**
	 * 通过id获取 客户学校信息
	 * 
	 * @param id
	 * @return
	 */
	public CustStudyDto getById(String id){
		AppCustStudy appCustStudy = appCustStudyService.getById(id);
		CustStudyDto custStudyDto =(CustStudyDto) BeanUtils.copyPropertiesNotNull(new CustStudyDto(), appCustStudy);
		setStudyAddr(custStudyDto);
		return custStudyDto;
	}
	
	/**
	 *获取 客户学校信息 list
	 * 
	 * @return
	 */
	public List<CustStudyDto> getList(Map<String,Object> param){
		List<CustStudyDto> lst =  BeanUtils.copyProperties(appCustStudyService.getList(param), CustStudyDto.class);
		for(CustStudyDto dto : lst){
			setStudyAddr(dto);
		}
		return lst;
	}
	
	/**
	 * 通过有效时间区间 获取这个区间的 客户学校信息 list
	 * 
	 * @param custNo
	 * @param availableDate
	 * @return
	 */
	public List<CustStudyDto> getCustStudyListByDate(String custNo,Date availableDate){
		List<CustStudyDto> lst =  BeanUtils.copyProperties(appCustStudyService.getCustStudyListByDate(custNo, availableDate),CustStudyDto.class);
		for(CustStudyDto dto : lst){
			setStudyAddr(dto);
		}
		return lst;
	}
	
	/**
	 * 获取客户当前的 有效的 客户学校信息 list
	 * 
	 * @return
	 */
	public List<CustStudyDto> getCrtCustStudyList(String custNo){
		List<CustStudyDto> lst = BeanUtils.copyProperties(appCustStudyService.getCrtCustStudyList(custNo), CustStudyDto.class);
		for(CustStudyDto dto : lst){
			setStudyAddr(dto);
		}
		return lst;
	}
	
	/**
	 * 设置学校信息
	 * 
	 * @param dto
	 */
	private void setStudyAddr(CustStudyDto dto){
		if(dto == null) return;
		dto.setSchoolProvName(regionalBelongService.getProvName(dto.getSchoolProv()));
		dto.setSchoolCityName(regionalBelongService.getCityName(dto.getSchoolCity()));
		dto.setSchoolAreaName(regionalBelongService.getCountName(dto.getSchoolArea()));
	}
	
}
