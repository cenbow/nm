package com.hs.loan.cust.api;

import java.util.List;
import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.cust.dto.CustGroupInfoDto;
import com.hs.loan.cust.dto.CustInfoDto;


/**
 * APP_客户分群 接口
 * @author autocreate
 * @create 2015-10-26
 */
public interface  CustGroupInfoApi{

	/**
	 * 保存或者更新 客户群
	 * @param appCustGroupInfo
	 */
	public void save(CustGroupInfoDto custGroupInfoDto ) throws ServiceException,AppException;
	
	/**
	 * 通过群编号 获取客户群
	 * @param custGroup
	 * @return
	 */
	public CustGroupInfoDto getByNo(String custGroup) throws ServiceException,AppException;
	
	/**
	 * 通过群编号删除
	 * @param custGroup
	 */
	public void deleteByNo(List<String> custGroupLst) throws ServiceException,AppException;
	
	/**
	 * 获取 客户群list
	 * @param param
	 * @return
	 */
	public List<CustGroupInfoDto> getList(Map<String,Object> param) throws ServiceException,AppException;
	
	/**
	 * 分页查询 客户群
	 * @return
	 */
	public Page<CustGroupInfoDto> queryCustGroup(Page<CustGroupInfoDto> page) throws ServiceException,AppException;
	
	/**
	 * 执行规则
	 * 必须的参数：custGroup
	 * 
	 * @param page
	 * @return
	 */
	public Page<CustInfoDto> executeRule(Page<CustInfoDto> page) throws ServiceException,AppException;
	
	/**
	 * 校验规则是否可用
	 * 
	 * @param rule
	 * @return
	 */
	public boolean validRule(String rule);
	
}