package com.hs.loan.prod.api;

import java.util.List;

import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.prod.dto.PubProdRepayTypDto;

/**
 * PUB_产品与还款类型的关系 接口
 * @author autocreate
 * @create 2015-10-16
 */
public interface  PubProdRepayTypApi{

	/**
	 * 保存产品与还款类型关系信息
	 * @param prodFees
	 */
	public void savePubProdRepayTyp(List<PubProdRepayTypDto> prodFees) throws ServiceException,AppException;
	
	
	/**
	 * 保存产品与还款类型信息
	 * @param prodFees
	 */
	public List<PubProdRepayTypDto> queryPubProdRepayTyp(String prodNo) throws ServiceException,AppException;
}