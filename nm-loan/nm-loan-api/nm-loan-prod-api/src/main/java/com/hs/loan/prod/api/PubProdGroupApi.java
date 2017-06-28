package com.hs.loan.prod.api;

import java.util.List;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.prod.dto.PubProdGroupDto;

/**
 * PUB_产品与销售群关系 接口
 * @author autocreate
 * @create 2015-10-21
 */
public interface  PubProdGroupApi{

	/**
	 * 查询组和群消息
	 * @param page
	 * @return
	 */
	public Page<PubProdGroupDto> queryPubGroup(Page<PubProdGroupDto> page) throws ServiceException,AppException;
	
	/**
	 * 查询组和群消息
	 * @param page
	 * @return
	 */
	public Page<PubProdGroupDto> queryPubProdGroup(Page<PubProdGroupDto> page) throws ServiceException,AppException;
	
	 /**
	  * 保存
	  * @param prodGroupDtos
	  */
	public void savePubProdGroup(List<PubProdGroupDto> prodGroupDtos) throws ServiceException,AppException;
	/**
	 * 删除
	 * @param prodGroupDtos
	 */
	public void removePubProdGroup(List<PubProdGroupDto> prodGroupDtos) throws ServiceException,AppException;
	
}