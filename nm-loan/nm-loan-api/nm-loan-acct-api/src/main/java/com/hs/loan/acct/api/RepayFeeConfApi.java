package com.hs.loan.acct.api;

import java.util.List;
import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.acct.dto.RepayFeeConfDto;


/**
 * PUB_费用项配置 接口
 * @author autocreate
 * @create 2015-10-15
 */
public interface  RepayFeeConfApi{

	/**
	 * 保存或更新 费用配置项，
	 * 有id为更新，
	 * 无id为保存
	 * 
	 */
	public void save(RepayFeeConfDto repayFeeConfDto) throws ServiceException,AppException;
	
	/**
	 * 通过费用项编号 删除 费用项配置
	 */
	public void deleteByNo(String feeNo) throws ServiceException,AppException;
	
	/**
	 * 通过费用项编号 获取 费用项配置
	 * @param feeNo
	 */
	public RepayFeeConfDto getByNo(String feeNo) throws ServiceException,AppException;
	
	/**
	 * 获取 费用项配置 list
	 * @param param
	 * @return
	 */
	public List<RepayFeeConfDto> getFeeConfList(Map<String, Object> param) throws ServiceException,AppException;
	
	/**
	 * 分页查询 费用项配置
	 * @param page
	 * @return
	 */
	public Page<RepayFeeConfDto> queryFeeConf(Page<RepayFeeConfDto> page) throws ServiceException,AppException;
	
	/**
	 * 获取 启用的费用项配置 list
	 * @param param
	 * @return
	 */
	public List<RepayFeeConfDto> getEnabledFeeConfList(Map<String,Object> param) throws ServiceException,AppException;
}