package com.hs.loan.prod.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.prod.dto.ProFeeCalResultDto;
import com.hs.loan.prod.dto.ProdBaseInfoDto;
import com.hs.loan.prod.dto.PubProdDto;

/**
 * PUB_产品信息后端 接口
 * @author autocreate
 * @create 2015-10-15
 */
public interface  ProdApi{

	/**
	 * 查询产品基本信息列表-
	 * @param map
	 * @return Page<PubProd>
	 */
	public Page<ProdBaseInfoDto> queryProd(Page<ProdBaseInfoDto> map) throws ServiceException,AppException;
	/**
	 * 查询产品信息列表 ---分期试算
	 * @param map
	 * @return Page<PubProd>
	 */
	public List<PubProdDto> queryProdForLoan(Map<String,Object> map) throws ServiceException,AppException;
	
	
	/**
	 * 产品上线
	 * @param prodNo 产品编号 
	 */
	public void onProd(String prodNo) throws ServiceException,AppException;
	
	/**
	 * 产品下线
	 * @param prodNo 产品编号 
	 */
	public void offProd(String prodNo) throws ServiceException,AppException;
	
	/**
	 * 保存产品基本信息
	 * @param baseInfoDto
	 */
	public void saveProdBaseInfo(ProdBaseInfoDto baseInfoDto) throws ServiceException,AppException;
	
	/**
	 * 获取产品基本信息
	 * @param baseInfoDto
	 */
	public ProdBaseInfoDto getProdBaseInfo(String prodNo) throws ServiceException,AppException;
	
	/**
	 * 产品试算
	 * @param baseInfoDto
	 */
	public ProFeeCalResultDto tryCalProdFee(String prodNo,BigDecimal goodsPrice,int perios) throws ServiceException,AppException;
	
	/**
	 * 检查产品跟还款类型关系
	 */
	
	public boolean queryProdRepayType(String repayTypeNo)  throws ServiceException,AppException;
	/**
	 * 检查产品跟还款方式关系
	 */
	public boolean queryProdRepayKind(String confNo) throws ServiceException,AppException;
	
	/**
	 * 通过费用号查询 PUB_产品与费用项关系
	 * 
	 * @param primaryKey
	 * @return 返回true可以删除 费用项 false则不可以删除 说明费用项和已有产品有关系
	 */
	public boolean queryExistRelaProdAndFee(String feeNo);

}