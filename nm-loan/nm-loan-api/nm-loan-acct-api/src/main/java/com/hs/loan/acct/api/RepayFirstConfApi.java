package com.hs.loan.acct.api;

import java.util.List;
import java.util.Map;

import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.acct.dto.PubRepayFirstConfDto;

/**
 * PUB_首次还款日规则 接口
 * @author autocreate
 * @create 2015-10-29
 */
public interface  RepayFirstConfApi{

	
	/**
	 * 保存还款日规则
	 * @param pubRepayKindConf
	 */
	public void save(PubRepayFirstConfDto pubRepayFirstConfDto) throws ServiceException,AppException;
	
	/**
	 * 通过还款日规则编号 删除 还款日规则
	 * @param repayNo
	 */
	public void deleteByNo(String repayNo) throws ServiceException,AppException;
	
	/**
	 * 通过还款日规则编号 查询 还款日规则
	 * @param repayNo
	 */
	public PubRepayFirstConfDto getByNo(String firstNo) throws ServiceException,AppException;
	
	/**
	 * 获取 还款方式list
	 * @param param
	 * @return
	 */
	public List<PubRepayFirstConfDto> getList(Map<String, Object> param) throws ServiceException,AppException;
	
	/**
	 *计算还款日
	 * @param param
	 * @return
	 */
	public String getFirstRepayDate(String repayNo,String applyDate) throws ServiceException,AppException;
}