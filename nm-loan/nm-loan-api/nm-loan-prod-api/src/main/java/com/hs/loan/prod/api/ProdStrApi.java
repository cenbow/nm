package com.hs.loan.prod.api;

import java.util.List;
import java.util.Set;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.prod.dto.PubBranchDto;
import com.hs.loan.prod.dto.PubProdStrDto;

/**
 * PUB_产品与业务渠道的关系 接口
 * @author autocreate
 * @create 2015-10-15
 */
public interface  ProdStrApi{

	/**
	 * 查询产品和业务渠道关系信息
	 * @param prodNo
	 * @return
	 */
	public Page<PubBranchDto> queryProdStr(Page<PubBranchDto> page) throws ServiceException,AppException;
	
	/**
	 * 保存产品和业务渠道的关系信息（单个）
	 * @param prodStr
	 */
	public void saveProdStr(List<PubProdStrDto> pubProdStrDto,String prodNo) throws ServiceException,AppException;
	/**
	 * 删除产品网点关系信息表  编号
	 * @param prodArea
	 */
	public void removeProdStr(Set<String> ids) throws ServiceException,AppException;
	
}