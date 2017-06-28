package com.hs.loan.cust.api;

import java.util.List;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.cust.dto.CustInfoDto;
import com.hs.loan.cust.dto.CustTeamInfoDto;


/**
 * APP_客户分组 接口
 * @author autocreate
 * @create 2015-10-26
 */
public interface  CustTeamInfoApi{

	/**
	 * 新增或修改 客户分组信息
	 * @param appCustTeamInfo
	 */
	public void save(CustTeamInfoDto custTeamInfoDto) throws ServiceException,AppException;
	
	/**
	 * 通过分组编号 删除分组
	 * @param custTeam
	 */
	public void deleteByNo(String custTeam) throws ServiceException,AppException;
	
	/**
	 * 分页查询 客户分组
	 * @param page
	 * @return
	 */
	public Page<CustTeamInfoDto> queryCustTeam(Page<CustTeamInfoDto> page) throws ServiceException,AppException;
	
	/**
	 * 分页查询 客户分组下的 客户信息
	 * 
	 * 必须的参数 custTeam
	 * 
	 * @param page
	 * @return
	 */
	public Page<CustInfoDto> queryGrpCust(Page<CustInfoDto> page) throws ServiceException,AppException;
	
	/**
	 * 分页查询 不在当前客户组下的 客户信息
	 * 必须的参数 custTeam
	 * 
	 * @param page
	 * @return
	 */
	public Page<CustInfoDto> queryNotInGrpCust(Page<CustInfoDto> page) throws ServiceException,AppException;
	
	/**
	 * 删除客户分组和客户的关系
	 * @param appCustTeam
	 */
	public void deleteGrpCustRel(String custTeam ,List<String> custNoLst) throws ServiceException,AppException;
	
	/**
	 * 保存或者更新 客户组和客户的关系
	 * 
	 * @param custTeam
	 * @param custNoLst
	 */
	public void saveGrpCustRel(String custTeam,List<String> custNoLst) throws ServiceException,AppException;
	
	/**
	 * 同时保存或更新 客户组和其下的客户
	 * @param appCustTeamInfo
	 * @param custNoLst
	 */
	public void saveGrpCust(CustTeamInfoDto custTeamInfoDto,List<String> custNoLst) throws ServiceException,AppException;
	
}