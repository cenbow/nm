package com.hs.loan.prod.api;

import java.util.Set;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.prod.dto.PubProdSysOrgDto;

/**
 *  PUB_产品与机构的关系 接口
 * @author autocreate
 * @create 2015-10-15
 */
public interface  ProdOrgApi{

	/**
	 * 查询产品与机构关系 -返回
	 * @param prodNo
	 * @return 机构号
	 */
	public Set<String> queryProdOrg(String prodNo) throws ServiceException,AppException;
	/**
	 * 查询产品与机构关系 -返回机构
	 * @param prodNo
	 * @return 机构号
	 */
	public Page<PubProdSysOrgDto> queryProdOrgForPage(Page<PubProdSysOrgDto> page) throws ServiceException,AppException;
	

	/**
	 * 保存产品与机构关系 -返回
	 * @param prodNo
	 * @return 机构号
	 */
	public void saveProdOrg(Set<String> orgNos,String prodNo) throws ServiceException,AppException;
}