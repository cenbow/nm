package com.hs.system.api;

import java.util.List;
import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.system.entity.PubSaleCrowd;
import com.hs.system.entity.SysStaff;

/**
 * PUB_销售群 接口
 * @author autocreate
 * @create 2015-10-15
 */
public interface  PubSaleCrowdApi{

	/**
	 * 保存或者更新 销售群
	 * @param pubSaleCrowd
	 */
	public void save(PubSaleCrowd pubSaleCrowd) throws ServiceException,AppException;
	
	/**
	 * 根据crowdNo 删除 销售群
	 * @param crowdNo
	 */
	public void deleteByNo(String crowdNo) throws ServiceException,AppException;
	
	/**
	 * 根据crowdNo 获取 销售群
	 * @param crowdNo
	 */
	public PubSaleCrowd getByNo(String crowdNo) throws ServiceException,AppException;
	
	/**
	 * 获取 销售群 list
	 * @param param
	 * @return
	 */
	public List<PubSaleCrowd> getList(Map<String,Object> param) throws ServiceException,AppException;
	
	/**
	 * 分页查询 销售群
	 * @param page
	 * @return
	 */
	public Page<PubSaleCrowd> querySaleCrowd(Page<PubSaleCrowd> page) throws ServiceException,AppException;
	
	/**
	 * 执行 销售群 规则 ，返回销售list
	 * @param CrowdNo
	 * @return
	 */
	public List<SysStaff> executeRule(String crowdNo) throws ServiceException,AppException;
	
	/**
	 * 分页执行 销售群 规则 ，返回销售list
	 * 必须的参数 crowdNo
	 * 可选传入的参数 staffName
	 * 
	 * @param page
	 * @return
	 */
	public Page<SysStaff> executeRulePage(Page<SysStaff> page) throws ServiceException,AppException;
	
	/**
	 * 分页查询销售组/销售群下的 员工
	 * @param page
	 * @return
	 */
	public Page<SysStaff> queryGrpCrowdStaff(Page<SysStaff> page)  throws ServiceException,AppException;
	
	/**
	 * 删除销售群/销售组
	 * @param param
	 */
	public void deleteGrpCrowd(Map<String,Object> param) throws ServiceException,AppException;
	
	/**
	 * 校验规则是否可用
	 * 
	 * @return
	 */
	public boolean validRule(String rule);
	
}