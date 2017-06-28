package com.hs.loan.acct.api;

import java.util.List;
import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.acct.dto.RepayKindConfDto;


/**
 * PUB_还款方式配置表 接口
 * @author autocreate
 * @create 2015-10-15
 */
public interface  RepayKindConfApi{

	/**
	 * 保存或更新 还款方式
	 * @param pubRepayKindConf
	 */
	public void save(RepayKindConfDto repayKindConfDto) throws ServiceException,AppException;
	
	/**
	 * 通过还款方式编号 删除 还款方式
	 * @param repayNo
	 */
	public void deleteByNo(String repayNo) throws ServiceException,AppException;
	
	/**
	 * 通过还款方式编号 获取 还款方式
	 * @param repayNo
	 */
	public RepayKindConfDto getByNo(String repayNo) throws ServiceException,AppException;
	
	/**
	 * 获取 还款方式list
	 * @param param
	 * @return
	 */
	public List<RepayKindConfDto> getList(Map<String, Object> param) throws ServiceException,AppException;
	
	
	/**
	 * 分页查询 还款方式
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<RepayKindConfDto> queryRepayConf(Page<RepayKindConfDto> page) throws ServiceException,AppException;
	
}