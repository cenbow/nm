package com.hs.loan.cust.server;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.loan.cust.api.CustAssetInfoApi;
import com.hs.loan.cust.dto.CustAssetInfoDto;
import com.hs.loan.cust.entity.AppCustAssetInfo;
import com.hs.loan.cust.service.AppCustAssetInfoService;
import com.hs.utils.BeanUtils;

/**
 * 客户资产信息 服务
 * @author zwr
 *
 */
@Service
@Transactional(readOnly=true)
public class CustAssetInfoServer implements CustAssetInfoApi {

	@Autowired
	private AppCustAssetInfoService appCustAssetInfoService;
	
	/**
	 * 通过客户号 获取 客户资产信息
	 * @param custNo
	 * @return
	 */
	public List<CustAssetInfoDto> getListByNo(String custNo) {
		List<AppCustAssetInfo> list = appCustAssetInfoService.getListByNo(custNo);
		List<CustAssetInfoDto> rs = BeanUtils.copyProperties(list, CustAssetInfoDto.class);
		return rs;
	}
	
	/**
	 * 保存或更新 客户资产信息 
	 */
	@Transactional
	public void save(String custNo,CustAssetInfoDto... assetLst){
		appCustAssetInfoService.save(custNo, BeanUtils.copyProperties(Arrays.asList(assetLst), AppCustAssetInfo.class));
	}
	
	/**
	 * 删除
	 * @param custNo
	 * @param ids
	 */
	@Transactional
	public void delete(String custNo,String... ids){
		appCustAssetInfoService.delete(custNo, ids);
	}
	
	/**
	 * 通过id获取 客户资产信息
	 * @param id
	 */
	public CustAssetInfoDto getById(String id){
		AppCustAssetInfo appCustAssetInfo = appCustAssetInfoService.getById(id);
		CustAssetInfoDto custAssetInfoDto = (CustAssetInfoDto) BeanUtils.copyPropertiesNotNull(new CustAssetInfoDto(), appCustAssetInfo);
		return custAssetInfoDto;
	}
	
	/**
	 * 通过客户号 获取 客户资产信息
	 * @param custNo
	 * @return
	 */
	public List<CustAssetInfoDto> getList(Map<String,Object> param ){
		return BeanUtils.copyProperties(appCustAssetInfoService.getList(param), CustAssetInfoDto.class);
	}
	
	/**
	 * 获取客户 有效时间段的有效的 资产信息
	 * @param custNo
	 * @param availableDate
	 * @return
	 */
	public List<CustAssetInfoDto> getCustAssetInfoLstByDate(String custNo,Date availableDate){
		return BeanUtils.copyProperties(appCustAssetInfoService.getCustAssetInfoLstByDate(custNo, availableDate), CustAssetInfoDto.class);
	}
	
	/**
	 * 获取客户当前有效的资产信息
	 * @param custNo
	 * @return
	 */
	public List<CustAssetInfoDto> getCrtCustAssetInfoLst(String custNo){
		return BeanUtils.copyProperties(appCustAssetInfoService.getCrtCustAssetInfoLst(custNo), CustAssetInfoDto.class);
	}
	
	/*
	public static void main(String[] args) {
		//CustAssetInfoDto dto = new CustAssetInfoDto();
		AppCustAssetInfo app = new AppCustAssetInfo();
		app.setBeginDate(new Date());
		app.setId("xxx");
		
		CustAssetInfoDto rs = (CustAssetInfoDto) BeanUtils.copyPropertiesNotNull(new CustAssetInfoDto(), app);
		
		System.out.println(rs);
	}
*/
}
