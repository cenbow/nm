package com.hs.loan.finance.server;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.commons.attach.Attachment;
import com.hs.commons.attach.AttachmentApi;
import com.hs.loan.finance.api.SalescoreInfoApi;
import com.hs.loan.finance.bo.SaleScoreFlowBo;
import com.hs.loan.finance.dto.AccSalescoreFlowDto;
import com.hs.loan.finance.dto.SaleScoreFlowDto;
import com.hs.loan.finance.dto.SysGiftInfoDto;
import com.hs.loan.finance.dto.SysSalescoreInfoDto;
import com.hs.loan.finance.entity.AccSalescoreFlow;
import com.hs.loan.finance.entity.SysGiftInfo;
import com.hs.loan.finance.entity.SysSalescoreInfo;
import com.hs.loan.finance.service.AccSalescoreFlowService;
import com.hs.loan.finance.service.SysGiftInfoService;
import com.hs.loan.finance.service.SysSalescoreInfoService;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;

/**
 * 奖品兑换
 * 
 * @author zhangxiaoqiang
 *
 */
@Service
@Transactional(readOnly = true)
public class SalescoreInfoServer implements SalescoreInfoApi {

	@Autowired
	private AccSalescoreFlowService accSalescoreFlowService;
	@Autowired
	private SysGiftInfoService sysGiftInfoService;
	@Autowired
	private SysSalescoreInfoService sysSalescoreInfoService;
	@Autowired
	private AttachmentApi attachmentApi ;

	@Override
	public Page<SysSalescoreInfoDto> querySaleCorePage(Page<SysSalescoreInfoDto> page)
			throws ServiceException, AppException {
		// TODO Auto-generated method stub
		return sysSalescoreInfoService.queryForPage(page.toPage(SysSalescoreInfo.class))
				.toPage(SysSalescoreInfoDto.class);
	}

	@Override
	public Page<AccSalescoreFlowDto> querySaleCoreFlowPage(Page<AccSalescoreFlowDto> page)
			throws ServiceException, AppException {
		// TODO Auto-generated method stub
		return accSalescoreFlowService.queryForPage(page.toPage(AccSalescoreFlow.class))
				.toPage(AccSalescoreFlowDto.class);
	}

	@Override
	public Page<SysGiftInfoDto> queryGiftInfoPage(Page<SysGiftInfoDto> page) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		return sysGiftInfoService.queryForPage(page.toPage(SysGiftInfo.class)).toPage(SysGiftInfoDto.class);
	}

	/**
	 * 查看具体员工积分
	 * 
	 * @param staffNo
	 * @return
	 */
	@Override
	public SysSalescoreInfoDto findSaleCoreByNo(String staffNo) {
		// TODO Auto-generated method stub
		SysSalescoreInfoDto dto = new SysSalescoreInfoDto();
		Map<String, Object> param = new HashMap<>();
		param.put("staffNo", staffNo);
		List<SysSalescoreInfo> infoList = sysSalescoreInfoService.queryForList(param);
		if (infoList != null && infoList.size() > 0) {
			BeanUtils.copyProperties(infoList.get(0), dto);
		}
		return dto;
	}

	/**
	 * 礼品兑换
	 * 
	 * @param staffNo
	 * @param giftNo
	 * @param num
	 * @throws ServiceException
	 * @throws AppException
	 */
	@Override
	@Transactional
	public void exChangeGift(String staffNo, String giftNo, String remainNum) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<>();
		param.put("staffNo", staffNo);
		param.put("giftNo", giftNo);
		if (changeFlg(param, remainNum)) { // 判断是否满足兑换条件
			// 员工
			List<SysSalescoreInfo> saleInfoList = sysSalescoreInfoService.queryForList(param);
			// 礼品列表
			List<SysGiftInfo> giftInfoList = sysGiftInfoService.queryForList(param);
			SysSalescoreInfo saleCore = saleInfoList.get(0);
			SysGiftInfo giftInfo = giftInfoList.get(0);
			AccSalescoreFlow flow = new AccSalescoreFlow();
			flow.setId(RandomUtil.getUUID());
			flow.setGiftNo(giftNo);
			flow.setStaffNo(staffNo);
			flow.setTranDate(new Date());
			flow.setTranScore(new BigDecimal(Integer.parseInt(remainNum) * giftInfo.getValueScore()));
			flow.setTranCnt(Integer.parseInt(remainNum));
			Map<String, Object> saleCoreMap = new HashMap<>();
			saleCoreMap.put("id", saleCore.getId());
			saleCoreMap.put("score", saleCore.getScore().subtract(flow.getTranScore()));
			Map<String, Object> giftInfoMap = new HashMap<>();
			giftInfoMap.put("id", giftInfo.getId());
			giftInfoMap.put("remainNum", giftInfo.getRemainNum() - flow.getTranCnt());
			accSalescoreFlowService.insertFlow(flow, saleCoreMap, giftInfoMap);
		}
	}

	/**
	 * 判断是否满足兑换条件
	 * 
	 * @param staffNo
	 * @param giftNo
	 * @param num
	 * @return
	 */
	private boolean changeFlg(Map<String, Object> param, String remainNum) throws ServiceException, AppException {
		// 员工
		List<SysSalescoreInfo> saleInfoList = sysSalescoreInfoService.queryForList(param);
		// 礼品列表
		List<SysGiftInfo> giftInfoList = sysGiftInfoService.queryForList(param);
		// 员工兑换记录
		List<AccSalescoreFlow> coreFlowList = accSalescoreFlowService.queryListByStaff(param);
		SysSalescoreInfo saleCore = null;
		SysGiftInfo giftInfo = null;
		if (saleInfoList != null && saleInfoList.size() > 0) {
			saleCore = saleInfoList.get(0);
			if (giftInfoList != null && giftInfoList.size() > 0) {
				giftInfo = giftInfoList.get(0);
				if (giftInfoList.get(0).getRemainNum() > 0) {
					if (coreFlowList != null
							&& (coreFlowList.size() + Integer.parseInt(remainNum)) > giftInfo.getSelfCnt()) {
						throw new ServiceException("本月内兑换该礼品的次数已使用完，请确认");
					} else {
						if (saleCore.getScore().compareTo(
								new BigDecimal(giftInfo.getValueScore() * Integer.parseInt(remainNum))) != -1) {
							return true;

						} else {
							throw new ServiceException("可用积分余额不足，请确认");
						}
					}
				} else {
					throw new ServiceException("该礼品剩余数量不足，请确认");
				}
			} else {
				throw new ServiceException("未查询到该礼品，请确认");
			}
		} else {
			throw new ServiceException("未查询到该员工，请确认");
		}
	}

	/**
	 * 礼品更新
	 */
	@Transactional
	public void updateGift(Map<String, Object> map) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		sysGiftInfoService.updateByPrimaryKeySelective(map);
	}

	/**
	 * 新增礼品
	 */
	@Transactional
	public void insertGift(SysGiftInfoDto giftInfo) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		if (giftInfo != null) {
			SysGiftInfo info = new SysGiftInfo();
			info.setId(RandomUtil.getUUID());
			info.setValueScore(giftInfo.getValueScore());
			info.setGiftNo(giftInfo.getGiftNo());
			info.setRemainNum(giftInfo.getRemainNum());
			info.setSelfCnt(giftInfo.getSelfCnt());
			info.setBgnDate(giftInfo.getBgnDate());
			info.setEndDate(giftInfo.getEndDate());
			info.setPotPath(giftInfo.getPotPath());
			info.setGiftSubject(giftInfo.getGiftSubject());
			info.setGiftRemark(giftInfo.getGiftRemark());
			sysGiftInfoService.insert(info);
		} else {
			throw new ServiceException("添加失败,礼品为空");
		}
	}

	/**
	 * 根据giftNo统计次数
	 * @param map
	 * @return
	 */
	public Integer getCountByGiftNo(Map<String,Object> map)
	{
		return sysGiftInfoService.getCountByGiftNo(map);
	}
	
	/**
	 * 删除礼品
	 */
	@Transactional
	public void deleteGift(String id) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		sysGiftInfoService.deleteByPrimaryKey(id);
	}

	@Override
	public SysGiftInfoDto queryGiftInfo(String id) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		SysGiftInfo info = sysGiftInfoService.getByPrimaryKey(id);
		SysGiftInfoDto infoDto = new SysGiftInfoDto();
		if (info != null)
			BeanUtils.copyProperties(info, infoDto);
		return infoDto;
	}

	@Override
	public Attachment findAttachment(String id) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		return attachmentApi.getById(id);
	}

	/**
	 * 查询积分流水列表
	 * @param page
	 * @return
	 */
	public Page<SaleScoreFlowDto> querySaleScoreFlowList(Page<SaleScoreFlowDto> page)
	{
		Page<SaleScoreFlowBo> pageBo = page.toPage(SaleScoreFlowBo.class);
				
		return accSalescoreFlowService.queryForPageTwo(pageBo).toPage(SaleScoreFlowDto.class);
	}
}
