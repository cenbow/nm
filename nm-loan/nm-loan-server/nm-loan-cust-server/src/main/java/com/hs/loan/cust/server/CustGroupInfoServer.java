package com.hs.loan.cust.server;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.commons.constants.CommonConstant;
import com.hs.loan.cust.api.CustGroupInfoApi;
import com.hs.loan.cust.dto.CustGroupInfoDto;
import com.hs.loan.cust.dto.CustInfoDto;
import com.hs.loan.cust.entity.AppCustGroupInfo;
import com.hs.loan.cust.entity.AppCustInfo;
import com.hs.loan.cust.service.AppCustGroupInfoService;
import com.hs.utils.BeanUtils;
import com.hs.utils.DateUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;

/**
 * 客户分群 服务
 * @author zwr
 *
 */
@Service
@Transactional(readOnly=true)
public class CustGroupInfoServer implements CustGroupInfoApi {

	@Autowired
	private AppCustGroupInfoService appCustGroupInfoService;
	
	
	/**
	 * 保存或者更新 客户群
	 * @param appCustGroupInfo
	 */
	@Transactional
	public void save(CustGroupInfoDto custGroupInfoDto ){
		AppCustGroupInfo appCustGroupInfo = new AppCustGroupInfo();
		BeanUtils.copyPropertiesNotForce(appCustGroupInfo,custGroupInfoDto);
		appCustGroupInfoService.save(appCustGroupInfo);
	}
	
	/**
	 * 通过群编号 获取客户群
	 * @param custGroup
	 * @return
	 */
	public CustGroupInfoDto getByNo(String custGroup){
		AppCustGroupInfo appCustGroupInfo =appCustGroupInfoService.getByNo(custGroup);
		CustGroupInfoDto custGroupInfoDto =(CustGroupInfoDto) BeanUtils.copyPropertiesNotNull(new CustGroupInfoDto(), appCustGroupInfo);
		return custGroupInfoDto;
	}
	
	/**
	 * 通过群编号删除
	 * @param custGroup
	 */
	@Transactional
	public void deleteByNo(List<String> custGroupLst){
		appCustGroupInfoService.deleteByNo(custGroupLst);
	}
	
	/**
	 * 获取 客户群list
	 * @param param
	 * @return
	 */
	public List<CustGroupInfoDto>getList(Map<String,Object> param){
		return BeanUtils.copyProperties(appCustGroupInfoService.getList(param), CustGroupInfoDto.class);
	}
	
	/**
	 * 分页查询 客户群
	 * @return
	 */
	public Page<CustGroupInfoDto> queryCustGroup(Page<CustGroupInfoDto> page){
		return appCustGroupInfoService.queryCustGroup(page.toPage(AppCustGroupInfo.class)).toPage(CustGroupInfoDto.class);
	}
	
	/**
	 * 执行规则
	 * 必须的参数：custGroup
	 * 
	 * @param page
	 * @return
	 */
	public Page<CustInfoDto> executeRule(Page<CustInfoDto> page){
		return appCustGroupInfoService.executeRule(page.toPage(AppCustInfo.class)).toPage(CustInfoDto.class);
	}
	
	/**
	 * 校验规则是否可用
	 * 
	 * @param rule
	 * @return
	 */
	public boolean validRule(String rule){
		return appCustGroupInfoService.validRule(rule);
	}
	
}
