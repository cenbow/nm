package com.hs.loan.prod.api;

import java.util.List;
import java.util.Map;

import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.prod.dto.ProdFeeCalDto;
import com.hs.loan.prod.dto.ProdFeeDto;
import com.hs.loan.prod.dto.PubProdFeeDto;
import com.hs.loan.prod.dto.PubRepayFeeConfDto;

/**
 * PUB_产品与费用项关系 接口
 * @author autocreate
 * @create 2015-10-15
 */
public interface  ProdFeeApi{

	/**
	 * 保存产品与费用项关系信息
	 * @param prodFees
	 */
	public void saveProdFee(List<PubProdFeeDto> prodFees,String prodNo) throws ServiceException,AppException;
	
	/**
	 * 保存产品与费用项关系信息
	 * @param prodFees
	 */
	public void removeProdFee(String prodNo,String inum) throws ServiceException,AppException;
	
	
	/**
	 *查询产品与费用项关系信息- distinct 收费项目 和所有的期数值
	 * @param prodFees
	 */
	public ProdFeeDto queryProdFeeInfo(String prodNo) throws ServiceException,AppException;
	public List<PubProdFeeDto> queryProdFee2(Map<String,Object> map);
	/**
	 *查询产品与费用项关系信息
	 * @param map -prodNo产品编号，期数
	 */
	public List<PubProdFeeDto> queryProdFee(Map<String,Object> map) throws ServiceException,AppException;
	/**
	 *费用项信息
	 * @param prodFees
	 */
	public List<PubRepayFeeConfDto> queryPubRepayFeeConf(String prodNo) throws ServiceException,AppException;

	/**
	 *查询产品与费用项关系信息--客户可选则的费用项
	 * @param prodFees
	 */
	public List<PubProdFeeDto> queryProdFeeForCustSel(String prodNo) throws ServiceException,AppException;
	
	/**
	 *查询产品与费用项关系信息--客户可选则的费用项、和期数(分期试算)
	 * @param prodFees
	 */
	public ProdFeeCalDto queryProdFeeForCal(String prodNo) throws ServiceException,AppException;
	
}