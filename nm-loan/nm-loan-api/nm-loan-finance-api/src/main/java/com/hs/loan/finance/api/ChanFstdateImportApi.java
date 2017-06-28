package com.hs.loan.finance.api;

import java.util.List;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.finance.dto.AppChanFstdateImportDto;

/**
 * 接口
 * 
 * @author autocreate
 * @create 2016-09-26
 */
public interface ChanFstdateImportApi {
	/**
	 * 查询资方首次还款日数据
	 * 
	 * @param page
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public Page<AppChanFstdateImportDto> queryListForPage(Page<AppChanFstdateImportDto> page)
			throws ServiceException, AppException;

	/**
	 * 批量导入
	 * 
	 * @param list
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void insertList(List<AppChanFstdateImportDto> list) throws ServiceException, AppException;
}