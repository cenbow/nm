package com.hs.loan.cust.server;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.loan.cust.api.CustLiveInfoApi;
import com.hs.loan.cust.dto.CustLiveInfoDto;
import com.hs.loan.cust.entity.AppCustLiveInfo;
import com.hs.loan.cust.service.AppCustLiveInfoService;
import com.hs.system.area.PubSysRegionalBelongService;
import com.hs.utils.BeanUtils;

/**
 * 客户居住信息 服务
 * @author zwr
 *
 */
@Service
@Transactional(readOnly = true)
public class CustLiveInfoServer implements CustLiveInfoApi {

	@Autowired
	private AppCustLiveInfoService appCustLiveInfoService;

	@Autowired
	private PubSysRegionalBelongService regionalBelongService;	//区县
	
	/**
	 * 通过客户号 获取 客户的现居住信息
	 * @param custNo
	 * @return
	 */
	public CustLiveInfoDto getCrtByNo(String custNo){
		CustLiveInfoDto dto = null;
		AppCustLiveInfo appCustLiveInfo = appCustLiveInfoService.getCrtByNo(custNo);
		if(appCustLiveInfo == null){
			return null;
		}
		dto = new CustLiveInfoDto();
		BeanUtils.copyProperties(appCustLiveInfo, dto);
		setLiveAddr(dto);
		return dto;
	}
	
	/**
	 * 保存或者更新 客户居住信息
	 * 
	 * @param custNo
	 * @param liveLst
	 */
	@Transactional
	public void save(String custNo,CustLiveInfoDto... liveLst){
		appCustLiveInfoService.save(custNo, BeanUtils.copyProperties(Arrays.asList(liveLst), AppCustLiveInfo.class));
	}
	
	/**
	 * 删除
	 * 
	 * @param custNo
	 * @param ids
	 */
	@Transactional
	public void delete(String custNo,String... ids){
		appCustLiveInfoService.delete(custNo, ids);
	}
	
	/**
	 * 通过id 获取 客户居住信息
	 * @param id
	 * @return
	 */
	public CustLiveInfoDto getById(String id){
		AppCustLiveInfo appCustLiveInfo = appCustLiveInfoService.getById(id);
		CustLiveInfoDto custLiveInfoDto =(CustLiveInfoDto) BeanUtils.copyPropertiesNotNull(new CustLiveInfoDto(), appCustLiveInfo);
		setLiveAddr(custLiveInfoDto);
		return custLiveInfoDto;
	}
	
	/**
	 * 获取 客户居住信息 list
	 * @param param
	 * @return
	 */
	public List<CustLiveInfoDto> getList(Map<String,Object> param){
		List<CustLiveInfoDto> lst =  BeanUtils.copyProperties(appCustLiveInfoService.getList(param), CustLiveInfoDto.class);
		for(CustLiveInfoDto dto : lst){
			setLiveAddr(dto);
		}
		return lst;
	}
	
	/**
	 * 获取 有效时间段里的 有效的 客户居住信息
	 * @param custNo
	 * @param availableDate
	 * @return
	 */
	public List<CustLiveInfoDto> getCustLiveInfoLstByDate(String custNo,Date availableDate){
		List<CustLiveInfoDto> lst = BeanUtils.copyProperties(appCustLiveInfoService.getCustLiveInfoLstByDate(custNo, availableDate), CustLiveInfoDto.class);
		for(CustLiveInfoDto dto : lst){
			setLiveAddr(dto);
		}
		return lst;
	} 
	
	/**
	 * 获取 当前 有效的 客户居住信息 
	 * @param custNo
	 * @return
	 */
	public List<CustLiveInfoDto> getCrtCustLiveInfoLst(String custNo){
		List<CustLiveInfoDto> lst = BeanUtils.copyProperties(appCustLiveInfoService.getCrtCustLiveInfoLst(custNo), CustLiveInfoDto.class);
		for(CustLiveInfoDto dto : lst){
			setLiveAddr(dto);
		}
		return lst;
	}
	
	/**
	 * 设置居住地址名称
	 * 
	 * @param dto
	 */
	private void setLiveAddr(CustLiveInfoDto dto){
		if(dto==null) return;
		dto.setLiveProvName(regionalBelongService.getProvName(dto.getLiveProv()));
		dto.setLiveCityName(regionalBelongService.getCityName(dto.getLiveCity()));
		dto.setLiveAreaName(regionalBelongService.getCountName(dto.getLiveArea()));
	}
	

}
