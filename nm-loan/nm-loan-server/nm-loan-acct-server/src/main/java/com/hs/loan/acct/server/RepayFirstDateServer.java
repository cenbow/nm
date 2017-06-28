package com.hs.loan.acct.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.acct.api.RepayFirstConfApi;
import com.hs.loan.acct.api.RepayKindConfApi;
import com.hs.loan.acct.dto.PubRepayFirstConfDto;
import com.hs.loan.acct.entity.PubRepayFirstConf;
import com.hs.loan.acct.entity.PubRepayKindConf;
import com.hs.loan.acct.service.PubRepayFirstConfService;
import com.hs.loan.acct.service.PubRepayKindConfService;
import com.hs.utils.BeanUtils;


/**
 *还款日规则
 * @author zym
 *
 */
@Service
@Transactional(readOnly = true)
public class RepayFirstDateServer implements RepayFirstConfApi{

	@Autowired
	private PubRepayFirstConfService pubRepayFirstConfService;
	
	/**
	 * 保存或更新 还款日规则
	 * @param pubRepayFirstConfDto
	 */
	@Transactional
	public void save(PubRepayFirstConfDto PubRepayFirstConfDto){
		PubRepayFirstConf  pubRepayFirstConf = new PubRepayFirstConf();
		BeanUtils.copyProperties(PubRepayFirstConfDto, pubRepayFirstConf);
		pubRepayFirstConfService.save(pubRepayFirstConf);
	}
	
	/**
	 * 通过还款日规则编号 删除 还款日规则
	 * @param repayNo
	 */
	@Transactional
	public void deleteByNo(String repayNo){
		pubRepayFirstConfService.deleteNo(repayNo);
	}
	
	/**
	 * 通过还款方式编号 获取 还款日规则
	 * @param repayNo
	 */
	public PubRepayFirstConfDto getByNo(String repayNo){
		PubRepayFirstConf pubRepayFirstConf = pubRepayFirstConfService.getByPrimaryKey(repayNo);
		PubRepayFirstConfDto PubRepayFirstConfDto = new PubRepayFirstConfDto();
		BeanUtils.copyProperties(pubRepayFirstConf, PubRepayFirstConfDto);
		return PubRepayFirstConfDto;
	}
	
	/**
	 * 获取 还款方式list
	 * @param param
	 * @return
	 */
	public List<PubRepayFirstConfDto> getList(Map<String, Object> param){
		List<PubRepayFirstConf> sLst = pubRepayFirstConfService.queryForList(param);
		return BeanUtils.copyProperties(sLst, PubRepayFirstConfDto.class);
	}
	
	/**
	 *计算还款日
	 * @param param
	 * @return
	 */
	@Transactional
	public String getFirstRepayDate(String prodNo,String applyDate){
		return pubRepayFirstConfService.calFirstDate(prodNo, applyDate);
	}
	
}
