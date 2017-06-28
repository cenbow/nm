package com.hs.loan.prod.api;

import java.util.List;

import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.prod.dto.PubFundChanInfoDto;
import com.hs.loan.prod.dto.PubProdFundChanDto;

/**
 *  接口
 * @author autocreate
 * @create 2015-10-15
 */
public interface  ProdFundChanApi{

	
	/**
	 * 保存产品与资金渠道关系信息
	 * @param prodFees
	 */
	public void saveProdFundChan(List<PubProdFundChanDto> prodFees,String prodNo) throws ServiceException,AppException;
	/**
	 * 查询产品与资金渠道关系信息
	 * @param prodFees
	 */
	public List<PubProdFundChanDto> queryProdFundChan(String prodNo) throws ServiceException,AppException;
	/**
	 * 资金渠道信息 没再产品中的
	 * @param prodFees
	 */
	public List<PubFundChanInfoDto> queryPubFundChanInfo(String prodNo) throws ServiceException,AppException;
	/**
	 * 解除产品与资金渠道关系信息
	 * @param prodFees
	 */
	public void removeFunchanl(String id) throws ServiceException,AppException;
}