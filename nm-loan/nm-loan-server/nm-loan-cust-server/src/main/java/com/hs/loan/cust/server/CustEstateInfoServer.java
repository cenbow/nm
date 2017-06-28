package com.hs.loan.cust.server;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hs.loan.cust.api.CustEstateInfoApi;
import com.hs.loan.cust.dto.CustEstateInfoDto;
import com.hs.loan.cust.entity.AppCustEstateInfo;
import com.hs.loan.cust.service.AppCustEstateInfoService;
import com.hs.system.area.PubSysRegionalBelongService;
import com.hs.utils.BeanUtils;

/**
 * 客户房产信息 服务
 * @author zwr
 *
 */
@Service
@Transactional(readOnly = true)
public class CustEstateInfoServer implements CustEstateInfoApi {

	@Autowired
	private AppCustEstateInfoService appCustEstateInfoService;
	@Autowired
	private PubSysRegionalBelongService regionalBelongService;	//区县
	
	/**
	 * 通过客户号 获取 客户房产信息
	 * @param custNo
	 * @return
	 */
	@Override
	public List<CustEstateInfoDto> getListByNo(String custNo) {
		List<CustEstateInfoDto> lst =  BeanUtils.copyProperties(appCustEstateInfoService.getListByNo(custNo), CustEstateInfoDto.class);
		for(CustEstateInfoDto dto : lst){
			setEstateAddr(dto);
		}
		return lst;
	}
	
	/**
	 * 保存或者更新 客户的房产信息
	 * 
	 * @param custNo
	 * @param estateLst
	 */
	@Transactional
	public void save(String custNo,CustEstateInfoDto... estateLst){
		appCustEstateInfoService.save(custNo, BeanUtils.copyProperties(Arrays.asList(estateLst), AppCustEstateInfo.class));
	}
	
	/**
	 * 删除 客户的房产信息
	 * 
	 * @param custNo
	 * @param ids
	 */
	@Transactional
	public void delete(String custNo,String... ids){
		appCustEstateInfoService.delete(custNo, ids);
	}
	
	/**
	 * 根据id 获取客户房产信息
	 * 
	 * @param id
	 */
	public CustEstateInfoDto getById(String id){
		AppCustEstateInfo appCustEstateInfo = appCustEstateInfoService.getById(id);
		CustEstateInfoDto dto = (CustEstateInfoDto) BeanUtils.copyPropertiesNotNull( new CustEstateInfoDto(), appCustEstateInfo);
		if(dto!=null){
			setEstateAddr(dto);
		}
		return dto;
	}
	
	/**
	 * 获取 客户房产信息list
	 * 
	 * @param param
	 * @return
	 */
	public List<CustEstateInfoDto> getList(Map<String,Object> param){
		List<CustEstateInfoDto> dtoLst =  BeanUtils.copyProperties(appCustEstateInfoService.getList(param), CustEstateInfoDto.class);
		for(CustEstateInfoDto dto : dtoLst){
			setEstateAddr(dto);
		}
		return dtoLst;
	}
	
	/**
	 * 获取有效时间段的 有效的 客户房产信息
	 * @param custNo
	 * @param availableDate
	 * @return
	 */
	public List<CustEstateInfoDto> getCustEstateInfoLstByDate(String custNo,Date availableDate){
		List<CustEstateInfoDto> dtoLst =  BeanUtils.copyProperties(appCustEstateInfoService.getCustEstateInfoLstByDate(custNo, availableDate), CustEstateInfoDto.class);
		for(CustEstateInfoDto dto : dtoLst){
			setEstateAddr(dto);
		}
		return dtoLst;
	}
	
	/**
	 * 获取当前有效的 房产信息
	 * 
	 * @param cust
	 * @return
	 */
	public List<CustEstateInfoDto> getCrtEstateInfoLst(String custNo){
		List<CustEstateInfoDto> dtoLst =  BeanUtils.copyProperties(appCustEstateInfoService.getCrtEstateInfoLst(custNo), CustEstateInfoDto.class);
		for(CustEstateInfoDto dto : dtoLst){
			setEstateAddr(dto);
		}
		return dtoLst;
	}
	
	/**
	 * 设置房产地址
	 * 
	 * @param dto
	 */
	private void setEstateAddr(CustEstateInfoDto dto){
		if(dto==null) return;
		dto.setEstateProvName(regionalBelongService.getProvName(dto.getEstateProv()));
		dto.setEstateCityName(regionalBelongService.getCityName(dto.getEstateCity()));
		dto.setEstateAreaName(regionalBelongService.getCountName(dto.getEstateArea()));
	}

	/**
	 * 获取保存或修改过的那条房产信息
	 * 
	 * @param id
	 * @param custNo
	 * @return
	 */
	/*public CustEstateInfoDto getSavedEstateInfo(String id,String custNo){
		if(StringUtils.isEmpty(custNo)){
			throw new AppException("custNo为必须");
		}
		List<CustEstateInfoDto> lst =  getCrtEstateInfoLst(custNo);
		if(lst==null || lst.size() == 0) return null;
		
		if(StringUtils.isEmpty(id)) return lst.get(lst.size()-1);
		CustEstateInfoDto rs = null;
		for(CustEstateInfoDto dto: lst){
			if(dto.getId().equals(id.trim())){
				rs = dto;
				break;
			}
		}
		return rs;
	}*/
	
	/**
	 * 获取刚刚保存的或者修改的 房产信息;
	 * 
	 * @param dto
	 * @return
	 */
	public CustEstateInfoDto getEditedEstateInfo(CustEstateInfoDto dto){
		AppCustEstateInfo appCustEstateInfo = appCustEstateInfoService.getEditedEstateInfo((AppCustEstateInfo)BeanUtils.copyPropertiesNotNull(new AppCustEstateInfo(), dto));
		CustEstateInfoDto rs = (CustEstateInfoDto) BeanUtils.copyPropertiesNotNull(new CustEstateInfoDto(), appCustEstateInfo);
		setEstateAddr(rs);
		return rs;
	}
	
	
	/*public static void main(String[] args) {
		List<CustEstateInfoDto> b = new ArrayList<CustEstateInfoDto>();
		new CustEstateInfoServer().save("hh", b.toArray(new CustEstateInfoDto[b.size()] ));
	}*/
	
}
