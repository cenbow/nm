package com.hs.loan.cust.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.commons.constants.CommonConstant;
import com.hs.loan.cust.api.CustTeamInfoApi;
import com.hs.loan.cust.dto.CustInfoDto;
import com.hs.loan.cust.dto.CustTeamInfoDto;
import com.hs.loan.cust.entity.AppCustInfo;
import com.hs.loan.cust.entity.AppCustTeamInfo;
import com.hs.loan.cust.service.AppCustTeamInfoService;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;

/**
 * 客户分组 服务
 * @author zwr
 *
 */
@Service
@Transactional(readOnly = true)
public class CustTeamInfoServer implements CustTeamInfoApi {
	
	@Autowired
	private AppCustTeamInfoService appCustTeamInfoService;
	
	/**
	 * 新增或修改 客户分组信息
	 * @param appCustTeamInfo
	 */
	@Transactional
	public void save(CustTeamInfoDto custTeamInfoDto){
		AppCustTeamInfo appCustTeamInfo = new AppCustTeamInfo();
		appCustTeamInfo.setCustCalc(custTeamInfoDto.getCustCalc());
		appCustTeamInfo.setCustTeam(custTeamInfoDto.getCustTeam());
		appCustTeamInfo.setCustTeamName(custTeamInfoDto.getCustTeamName());
		appCustTeamInfo.setBeginDate(custTeamInfoDto.getBeginDate());
		appCustTeamInfo.setEndDate(custTeamInfoDto.getEndDate());
		appCustTeamInfo.setStat(custTeamInfoDto.getStat());
		appCustTeamInfo.setInstPerson(custTeamInfoDto.getInstPerson());
		//BeanUtils.copyPropertiesNotForce(appCustTeamInfo,custTeamInfoDto);
		appCustTeamInfoService.save(appCustTeamInfo);
		
	}
	
	/**
	 * 通过分组编号 删除分组
	 * @param custTeam
	 */
	@Transactional
	public void deleteByNo(String custTeam){
		appCustTeamInfoService.deleteByNo(custTeam);
	}
	
	/**
	 * 分页查询 客户分组
	 * @param page
	 * @return
	 */
	public Page<CustTeamInfoDto> queryCustTeam(Page<CustTeamInfoDto> page){
		return appCustTeamInfoService.queryCustTeam(page.toPage(AppCustTeamInfo.class)).toPage(CustTeamInfoDto.class);
	}
	
	/**
	 * 分页查询 客户分组下的 客户信息
	 * 
	 * 必须的参数 custTeam
	 * 
	 * @param page
	 * @return
	 */
	public Page<CustInfoDto> queryGrpCust(Page<CustInfoDto> page){
		return appCustTeamInfoService.queryGrpCust(page.toPage(AppCustInfo.class)).toPage(CustInfoDto.class);
	}
	
	/**
	 * 分页查询 不在当前客户组下的 客户信息
	 * 必须的参数 custTeam
	 * 
	 * @param page
	 * @return
	 */
	public Page<CustInfoDto> queryNotInGrpCust(Page<CustInfoDto> page){
		return appCustTeamInfoService.queryNotInGrpCust(page.toPage(AppCustInfo.class)).toPage(CustInfoDto.class);
	}
	
	/**
	 * 删除客户分组和客户的关系
	 * @param appCustTeam
	 */
	@Transactional
	public void deleteGrpCustRel(String custTeam ,List<String> custNoLst){
		appCustTeamInfoService.deleteGrpCustRel(custTeam, custNoLst);
	}
	
	/**
	 * 保存或者更新 客户组和客户的关系
	 * 
	 * @param custTeam
	 * @param custNoLst
	 */
	@Transactional
	public void saveGrpCustRel(String custTeam,List<String> custNoLst){
		appCustTeamInfoService.saveGrpCustRel(custTeam, custNoLst);
	}
	
	/**
	 * 同时保存或更新 客户组和其下的客户
	 * @param appCustTeamInfo
	 * @param custNoLst
	 */
	@Transactional
	public void saveGrpCust(CustTeamInfoDto custTeamInfoDto,List<String> custNoLst){
		AppCustTeamInfo appCustTeamInfo = new AppCustTeamInfo();
		BeanUtils.copyProperties(custTeamInfoDto, appCustTeamInfo);
		appCustTeamInfoService.saveGrpCust(appCustTeamInfo, custNoLst);
	}
	
}
