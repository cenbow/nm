package com.hs.loan.sale.api;

import java.util.List;

import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.busi.dto.AppChanImportDto;


/**
 *  接口
 * @author autocreate
 * @create 2016-08-30
 */
public interface  ChanImportApi{

	/**
	 * 导入资方分期信息
	 * @param chanImportDtos
	 * @param profile
	 * @return
	 */
	public void importAppChanImports(List<AppChanImportDto> chanImportDtos,UserProfile profile) throws ServiceException,AppException;
	
	
	
}