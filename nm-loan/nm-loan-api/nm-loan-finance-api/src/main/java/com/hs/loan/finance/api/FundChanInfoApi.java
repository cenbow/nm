package com.hs.loan.finance.api;

import java.util.List;
import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.finance.dto.FundChanInfoDto;
import com.hs.loan.finance.dto.SaleContractDto;

/**
 * 资金渠道信息 接口
 * @author zwr
 *
 */
public interface FundChanInfoApi {

	/**
	 * 保存或者更新 资金渠道信息
	 * @param pubFundChanInfo
	 */
	public void save(FundChanInfoDto fundChanInfoDto) throws ServiceException,AppException;
	
	/**
	 * 通过渠道编号 删除 资金渠道信息
	 * @param chanNo
	 */
	public void deleteByNo(String chanNo) throws ServiceException,AppException;
	
	/**
	 * 通过渠道编号 获取 资金渠道信息
	 * @param chanNo
	 */
	public FundChanInfoDto getByNo(String chanNo) throws ServiceException,AppException;
	
	/**
	 * 获取 资金渠道信息 列表
	 * @param param
	 * @return
	 */
	public List<FundChanInfoDto> getList(Map<String,Object> param) throws ServiceException,AppException;
	
	/**
	 * 分页查询 资金渠道信息 
	 * @param page
	 * @return
	 */
	public Page<FundChanInfoDto> queryPubFundChanInfo(Page<FundChanInfoDto> page) throws ServiceException,AppException;

	/**
	 * 分页查询 资金渠道信息 
	 * @param page
	 * @return
	 */
	public Page<SaleContractDto> queryContractBill(Page<SaleContractDto> page) throws ServiceException,AppException;
}
