package com.hs.loan.cust.server;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.exception.AppException;
import com.hs.loan.cust.api.CustContctOtherApi;
import com.hs.loan.cust.dto.CustContctInfoDto;
import com.hs.loan.cust.dto.CustContctOtherDto;
import com.hs.loan.cust.dto.CustContctPersonDto;
import com.hs.loan.cust.entity.AppCustContctOther;
import com.hs.loan.cust.service.AppCustContctOtherService;
import com.hs.utils.BeanUtils;
import com.hs.utils.StringUtils;

/**
 * 客户其他联系人信息 服务
 * @author zwr
 *
 */
@Service
@Transactional(readOnly = true)
public class CustContctOtherServer implements CustContctOtherApi {

	@Autowired
	private AppCustContctOtherService appCustContctOtherService;
	@Autowired
	private CustContctInfoServer custContctInfoServer;
	
	/**
	 * 通过客户号 获取 客户其他联系人信息
	 * @param custNo
	 * @return
	 */
	@Override
	public List<CustContctOtherDto> getListByNo(String custNo) {
		return BeanUtils.copyProperties(appCustContctOtherService.getListByNo(custNo),CustContctOtherDto.class);
	}

	/**
	 * 保存 客户其他联系人信息
	 * @param custNo
	 * @param otherLst
	 */
	@Transactional
	public void save(String custNo,CustContctOtherDto... otherLst){
		appCustContctOtherService.save(custNo, BeanUtils.copyProperties(Arrays.asList(otherLst), AppCustContctOther.class));
	}
	
	/**
	 * 删除 客户其他联系人信息
	 */
	@Transactional
	public void delete(String custNo,String... ids){
		appCustContctOtherService.delete(custNo, ids);
	}
	
	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	public CustContctOtherDto getById(String id){
		AppCustContctOther appCustContctOther = appCustContctOtherService.getById(id);
		CustContctOtherDto custContctOtherDto =(CustContctOtherDto) BeanUtils.copyPropertiesNotNull(new CustContctOtherDto(), appCustContctOther);
		return custContctOtherDto;
	}
	
	/**
	 * 通过客户号和有效时间段 获取客户其他联系人信息
	 * @param availableDate
	 * @return
	 */
	public List<CustContctOtherDto> getCustContctOtherLstByDate(String custNo,Date availableDate){
		return BeanUtils.copyProperties(appCustContctOtherService.getCustContctOtherLstByDate(custNo, availableDate), CustContctOtherDto.class);
	}
	
	/**
	 * 获取当前有效的 客户其他联系人联系信息
	 * 
	 * @param custNo
	 * @return
	 */
	public List<CustContctOtherDto> getCrtContctOtherLst(String custNo){
		return BeanUtils.copyProperties(appCustContctOtherService.getCrtContctOtherLst(custNo), CustContctOtherDto.class);
	}
	
	/**
	 * 获取 客户其他联系人联系信息
	 * @param param
	 * @return
	 */
	public List<CustContctOtherDto> getList(Map<String,Object> param){
		return BeanUtils.copyProperties(appCustContctOtherService.getList(param), CustContctOtherDto.class);
	}
	
	/**
	 * 保存客户的 联系人信息和其他联系人信息
	 * 
	 */
	@Transactional
	public void saveContctPerson(String custNo ,CustContctPersonDto dto){
		if(StringUtils.isEmpty(custNo)){
			throw new AppException("custNo 不可为空");
		}
		CustContctInfoDto contct = dto.getCustContctInfoDto();
		List<CustContctOtherDto> oLst = dto.getCustContctOtherDtoLst();
		//保存联系信息
		if(contct!=null){
			contct.setCustNo(custNo);
			custContctInfoServer.save(custNo,contct);
		}
		//保存其他联系人信息
		if(oLst != null && oLst.size() >0){
			CustContctOtherDto[] otherArray =oLst.toArray(new CustContctOtherDto[oLst.size()]) ;
			for(CustContctOtherDto other : otherArray){
				other.setCustNo(custNo);
			}
			save(custNo,otherArray);
		}
	}
	
	
}
