package com.hs.loan.finance.api;

import java.util.List;
import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.finance.dto.AccPayWithDto;
import com.hs.loan.finance.dto.RetItemDto;

/**
 * ACC_三方支付代扣信息 接口
 * @author autocreate
 * @create 2016-04-20
 */
public interface  AccPayWithApi{
	
	/**
	 * 批量代扣文件导出
	 * @param params 查询条件
	 * @return
	 * @throws Exception
	 */
	public String batchDkFileExport(Map<String, Object> params) throws ServiceException; 
	
	/**
	 * 回盘文件上传后的处理
	 * @param params
	 * @throws ServiceException
	 */
	public void executeBatchDkFileImport(String fileName,List<RetItemDto> lst,UserProfile user) throws ServiceException; 
	
	/**
	 * 查询分期代扣信息
	 * @param page
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public Page<AccPayWithDto> queryLoanPayWithInfo(Page<AccPayWithDto> page)  throws ServiceException,AppException;
	
}