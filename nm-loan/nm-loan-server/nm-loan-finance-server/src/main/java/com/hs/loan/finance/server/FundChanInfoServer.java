package com.hs.loan.finance.server;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.finance.api.FundChanInfoApi;
import com.hs.loan.finance.bo.SaleContractBO;
import com.hs.loan.finance.dto.FundChanInfoDto;
import com.hs.loan.finance.dto.SaleContractDto;
import com.hs.loan.finance.entity.PubFundChanInfo;
import com.hs.loan.finance.service.PubFundChanInfoService;
import com.hs.loan.prod.service.PubProdFundChanService;
import com.hs.utils.BeanUtils;

/**
 * 资金渠道信息 服务
 * @author zwr
 *
 */
@Service
@Transactional(readOnly = true)
public class FundChanInfoServer implements FundChanInfoApi {

	@Autowired
	private PubFundChanInfoService  pubFundChanInfoService;
	
	@Autowired
	private PubProdFundChanService pubProdFundChanService;
	
	/**
	 * 保存或者更新 资金渠道信息
	 * @param pubFundChanInfo
	 */
	@Transactional
	public void save(FundChanInfoDto fundChanInfoDto){
		PubFundChanInfo pubFundChanInfo = new PubFundChanInfo();
		BeanUtils.copyProperties(fundChanInfoDto, pubFundChanInfo);
		pubFundChanInfoService.save(pubFundChanInfo);
	}
	
	/**
	 * 通过渠道编号 删除 资金渠道信息
	 * @param chanNo
	 */
	@Transactional
	public void deleteByNo(String chanNo){
		if(pubProdFundChanService.queryExistRelaProdAndChan(chanNo)){
			pubFundChanInfoService.deleteByNo(chanNo);
			return;
		}
		throw new ServiceException("该资金渠道已被产品关联不可删除！");
	}
	
	/**
	 * 通过渠道编号 获取 资金渠道信息
	 * @param chanNo
	 */
	public FundChanInfoDto getByNo(String chanNo){
		PubFundChanInfo pubFundChanInfo = pubFundChanInfoService.getByNo(chanNo);
		FundChanInfoDto dto = new FundChanInfoDto();
		BeanUtils.copyProperties(pubFundChanInfo, dto);
		return dto;
	}
	
	/**
	 * 获取 资金渠道信息 列表
	 * @param param
	 * @return
	 */
	public List<FundChanInfoDto> getList(Map<String,Object> param){
		return BeanUtils.copyProperties(pubFundChanInfoService.getList(param), FundChanInfoDto.class);
	}
	
	/**
	 * 分页查询 资金渠道信息 
	 * @param page
	 * @return
	 */
	public Page<FundChanInfoDto> queryPubFundChanInfo(Page<FundChanInfoDto> page){
		return pubFundChanInfoService.queryPubFundChanInfo(page.toPage(PubFundChanInfo.class)).toPage(FundChanInfoDto.class);
	}

	@Override
	public Page<SaleContractDto> queryContractBill(Page<SaleContractDto> page) throws ServiceException, AppException {
		return pubFundChanInfoService.querySaleContractList(page.toPage(SaleContractBO.class)).toPage(SaleContractDto.class);
	}
}
