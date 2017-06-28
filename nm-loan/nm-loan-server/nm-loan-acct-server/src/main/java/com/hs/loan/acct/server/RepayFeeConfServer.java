package com.hs.loan.acct.server;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.acct.api.RepayFeeConfApi;
import com.hs.loan.acct.dto.RepayFeeConfDto;
import com.hs.loan.acct.entity.PubRepayFeeConf;
import com.hs.loan.acct.service.PubRepayFeeConfService;
import com.hs.loan.prod.api.ProdApi;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;

/**
 * 费用配置项服务
 * @author zwr
 *
 */
@Service
@Transactional(readOnly = true)
public class RepayFeeConfServer implements RepayFeeConfApi{

	@Autowired
	private PubRepayFeeConfService pubRepayFeeConfService;
	@Autowired
	private ProdApi acctProdService;
	
	/**
	 * 保存或更新 费用配置项，
	 * 有id为更新，
	 * 无id为保存
	 * 
	 */
	@Transactional
	public void save(RepayFeeConfDto repayFeeConfDto){
		PubRepayFeeConf pubRepayFeeConf = new PubRepayFeeConf();
		BeanUtils.copyProperties(repayFeeConfDto, pubRepayFeeConf);
		pubRepayFeeConfService.save(pubRepayFeeConf);
	}
	
	/**
	 * 通过费用项编号 删除 费用项配置
	 */
	@Transactional
	public void deleteByNo(String feeNo){
		if(acctProdService.queryExistRelaProdAndFee(feeNo)){
			pubRepayFeeConfService.deleteByNo(feeNo);
			return;
		}
		throw new ServiceException("该费用项已被产品关联不可删除！");
		
	}
	
	/**
	 * 通过费用项编号 获取 费用项配置
	 * @param feeNo
	 */
	public RepayFeeConfDto getByNo(String feeNo){
		PubRepayFeeConf prf = pubRepayFeeConfService.getByNo(feeNo);
		RepayFeeConfDto dto = new RepayFeeConfDto();
		BeanUtils.copyProperties(prf, dto);
		return dto;
	}
	
	/**
	 * 获取 费用项配置 list
	 * @param param
	 * @return
	 */
	public List<RepayFeeConfDto> getFeeConfList(Map<String, Object> param){
		List<PubRepayFeeConf> lst = pubRepayFeeConfService.getFeeConfList(param);
		List<RepayFeeConfDto> dtoLst  = BeanUtils.copyProperties(lst, RepayFeeConfDto.class);
		return dtoLst;
	}
	
	/**
	 * 获取 启用的费用项配置 list
	 * @param param
	 * @return
	 */
	public List<RepayFeeConfDto> getEnabledFeeConfList(Map<String,Object> param){
		List<PubRepayFeeConf> lst = pubRepayFeeConfService.getEnabledFeeConfList(param);
		List<RepayFeeConfDto> dtoLst  = BeanUtils.copyProperties(lst, RepayFeeConfDto.class);
		return dtoLst;
	}
	
	/**
	 * 分页查询 费用项配置
	 * @param page
	 * @return
	 */
	public Page<RepayFeeConfDto> queryFeeConf(Page<RepayFeeConfDto> page){
		return pubRepayFeeConfService.queryFeeConf(page.toPage(PubRepayFeeConf.class))
				.toPage(RepayFeeConfDto.class);
	}
	
}
