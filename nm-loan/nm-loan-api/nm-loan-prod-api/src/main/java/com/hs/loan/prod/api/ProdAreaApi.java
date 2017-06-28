package com.hs.loan.prod.api;

import java.util.Set;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.prod.dto.PubProdAreaDto;

/**
 *  接口
 * @author autocreate
 * @create 2015-10-15
 */
public interface  ProdAreaApi{

	
	/**
	 * 查询产品区域关系信息
	 * @param prodNo
	 * @return Page<PubProdArea>
	 */
	public Page<PubProdAreaDto> queryProdArea(Page<PubProdAreaDto> page) throws ServiceException,AppException;
	
	/**
	 * 保存产品区域关系信息表
	 * @param prodArea
	 */
	public void saveProdArea(PubProdAreaDto pubProdAreaDto) throws ServiceException,AppException;
	
	/**
	 * 删除产品区域关系信息表  编号
	 * @param prodArea
	 */
	public void removeProdArea(Set<String> ids) throws ServiceException,AppException;
	
	
}