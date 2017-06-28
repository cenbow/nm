package com.hs.loan.finance.api;

import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.commons.attach.Attachment;
import com.hs.loan.finance.dto.AccSalescoreFlowDto;
import com.hs.loan.finance.dto.SaleScoreFlowDto;
import com.hs.loan.finance.dto.SysGiftInfoDto;
import com.hs.loan.finance.dto.SysSalescoreInfoDto;

/**
 * 奖品兑换接口
 * 
 * @author autocreate
 * @create 2016-10-11
 */
public interface SalescoreInfoApi {

	/**
	 * 获取员工积分
	 * 
	 * @param page
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public Page<SysSalescoreInfoDto> querySaleCorePage(Page<SysSalescoreInfoDto> page)
			throws ServiceException, AppException;

	/**
	 * 查看具体员工积分
	 * 
	 * @param staffNo
	 * @return
	 */
	public SysSalescoreInfoDto findSaleCoreByNo(String staffNo);

	/**
	 * 获取员工兑换记录
	 * 
	 * @param page
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public Page<AccSalescoreFlowDto> querySaleCoreFlowPage(Page<AccSalescoreFlowDto> page)
			throws ServiceException, AppException;

	/**
	 * 获取礼品Page
	 * 
	 * @param page
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public Page<SysGiftInfoDto> queryGiftInfoPage(Page<SysGiftInfoDto> page) throws ServiceException, AppException;

	/**
	 * 根据ID 查询礼品信息
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public SysGiftInfoDto queryGiftInfo(String id) throws ServiceException, AppException;

	/**
	 * 礼品兑换
	 * 
	 * @param staffNo
	 * @param giftNo
	 * @param num
	 * @throws ServiceException
	 * @throws AppException
	 */
	public void exChangeGift(String staffNo, String giftNo, String remainNum) throws ServiceException, AppException;

	/**
	 * 礼品维护
	 * 
	 * @param giftInfo
	 */
	public void updateGift(Map<String, Object> map) throws ServiceException, AppException;

	/**
	 * 礼品添加
	 * 
	 * @param giftInfo
	 */
	public void insertGift(SysGiftInfoDto giftInfo) throws ServiceException, AppException;

	/**
	 * 礼品删除
	 * 
	 * @param giftInfo
	 */
	public void deleteGift(String giftId) throws ServiceException, AppException;

	/**
	 * 获取附件
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public Attachment findAttachment(String id) throws ServiceException, AppException;
	
	/**
	 * 根据giftNo统计次数
	 * @param map
	 * @return
	 */
	public Integer getCountByGiftNo(Map<String,Object> map);

	/**
	 * 查询积分流水列表
	 * @param page
	 * @return
	 */
	public Page<SaleScoreFlowDto> querySaleScoreFlowList(Page<SaleScoreFlowDto> page);
}