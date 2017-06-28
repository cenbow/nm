package com.hs.loan.finance.server;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.finance.api.AccCapWithApi;
import com.hs.loan.finance.bo.BatchDkResultBo;
import com.hs.loan.finance.bo.QueryResultBo;
import com.hs.loan.finance.bo.RetItemBo;
import com.hs.loan.finance.bo.SingleDkResultBo;
import com.hs.loan.finance.bo.SingleOtherBusiBo;
import com.hs.loan.finance.bo.SingleRepayBo;
import com.hs.loan.finance.contant.FinanceConstant;
import com.hs.loan.finance.dto.BatchDkResultDto;
import com.hs.loan.finance.dto.QueryResultDto;
import com.hs.loan.finance.dto.RetItemDto;
import com.hs.loan.finance.dto.SingleOtherBusiDto;
import com.hs.loan.finance.dto.SingleRepayDto;
import com.hs.loan.finance.entity.AccCapWith;
import com.hs.loan.finance.service.AccCapWithService;
import com.hs.loan.finance.util.PayChanType;
import com.hs.loan.finance.withpay.dto.LoanRepayWithDto;
import com.hs.loan.finance.withpay.dto.RetItem;
import com.hs.loan.finance.withpay.dto.SingleDkResultDto;
import com.hs.utils.BeanUtils;

/**
 * 代扣服务实现
 * 
 * @author hwen
 *
 */
@Service
@Transactional(readOnly = true)
public class AccCapWithServer implements AccCapWithApi {

	@Autowired
	private AccCapWithService accCapWithService;

	/**
	 * 查询分期代扣信息
	 * 
	 * @param page
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	@Override
	public Page<LoanRepayWithDto> queryLoanPayWithInfo(Page<LoanRepayWithDto> page)
			throws ServiceException, AppException {
		return accCapWithService.queryForPage(page.toPage(AccCapWith.class)).toPage(LoanRepayWithDto.class);
	}

	/**
	 * 查询组装后的代扣还款数据
	 * 
	 * @param page
	 * @return
	 */
	public Page<SingleRepayDto> querySingleRepayListForPage(Page<SingleRepayDto> page) throws ServiceException {
		return accCapWithService.querySingleRepayBoForList(page.toPage(SingleRepayBo.class))
				.toPage(SingleRepayDto.class);
	}

	/**
	 * 查询单笔代扣信息
	 * 
	 * @param singleRepayVO
	 * @return
	 * @throws ServiceException
	 */
	@Override
	@Deprecated // 空方法
	public SingleRepayDto querySingleRepayInfo(SingleRepayDto singleRepayVO) throws ServiceException {
		return null;
	}

	/**
	 * 银联单笔代扣
	 * 
	 * @param singleRepayVO
	 * @return
	 * @throws ServiceException
	 */
	@Transactional
	public SingleDkResultDto singleRepay(SingleRepayDto singleRepayDto, UserProfile userProFile)
			throws ServiceException {
		SingleRepayBo bo = new SingleRepayBo();
		BeanUtils.copyProperties(singleRepayDto, bo);
		SingleDkResultBo bos = accCapWithService.singleRepay(bo, userProFile);
		SingleDkResultDto dto = new SingleDkResultDto();
		if (bos == null) {
			throw new ServiceException("单扣调用接口连接失败，返回数据为空");
		} else if (bos.getRetItem() == null && bos.getRetCode().equals(FinanceConstant.TRAN_ST_DEALING)) {
			RetItem re = new RetItem();
			re.setRetCode(FinanceConstant.TRAN_ST_DEALING);
			re.setErrMsg("代扣处理中......");
			dto.setRetItem(re);
		} else {
			RetItem re = new RetItem();
			try {
				BeanUtils.copyProperties(bos.getRetItem(), re);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			dto.setRetItem(re);
		}

		BeanUtils.copyProperties(bos, dto);
		return dto;
		/**
		 * 如果为结清且成功设置预处理状态为已结清 贷款分期状态:20101001-未加锁/20101002-已加锁/20101003-已结清
		 */
	}

	/**
	 * 银联批量代扣
	 * 
	 * @param params
	 *            查询条件
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public BatchDkResultDto batchRepay(Map<String, Object> params) throws ServiceException {
		BatchDkResultBo bo = accCapWithService.executeBatchRepay(params);
		BatchDkResultDto dto = new BatchDkResultDto();
		if (bo == null) {
			throw new ServiceException("银联批量调用接口连接失败，返回数据为空");
		}
		BeanUtils.copyProperties(dto, bo);
		return dto;

	}

	/**
	 * 批量回调接口
	 * 
	 * @param reqSN
	 * @throws ServiceException
	 */
	@Transactional
	@Deprecated // 不再使用回调处理批量代扣
	public void executeBatchRepayCallback(String reqSN) throws ServiceException {
		accCapWithService.executeBatchRepayCallback(reqSN);
	}

	/**
	 * 手动回盘查询
	 * 
	 * @param reqSN
	 * @param chalCode
	 *            银联渠道
	 * @return
	 * @throws ServiceException
	 */
	public QueryResultDto queryBatchRepay(String reqSN, String chalCode) throws ServiceException {
		QueryResultDto dto = new QueryResultDto();
		QueryResultBo bo = accCapWithService.queryBatchRepay(reqSN, chalCode);
		if (bo == null) {
			throw new ServiceException("银联手动回盘接口连接失败，返回数据为空");
		}
		BeanUtils.copyProperties(bo, dto);
		return dto;
	}

	/**
	 * 单笔 催收 提前结清代扣接口
	 */
	@Override
	public SingleDkResultDto singleRepayOtherBusi(SingleOtherBusiDto singleOtherBusiDto, UserProfile userProFile)
			throws ServiceException {
		SingleOtherBusiBo bo = new SingleOtherBusiBo();
		BeanUtils.copyProperties(singleOtherBusiDto, bo);
		SingleDkResultBo bos = accCapWithService.singleRepayOtherBusi(bo, userProFile);
		RetItem re = new RetItem();
		BeanUtils.copyProperties(bos.getRetItem(), re);
		SingleDkResultDto dto = new SingleDkResultDto();
		BeanUtils.copyProperties(bos, dto);
		dto.setRetItem(re);
		return dto;
	}

	/**
	 * 批量导出代扣文件 返回文件url地址
	 */
	@Override
	public String batchDkFileExport(Map<String, Object> params) throws ServiceException {
		String fileUrl = accCapWithService.executeBatchDkFileExport(params);
		if (StringUtils.isEmpty(fileUrl)) {
			throw new ServiceException("代扣文件生成失败");
		}
		return fileUrl;
	}

	/**
	 * 回盘文件导入
	 */
	@Transactional
	public void executeBatchDkFileImport(String fileName, List<RetItemDto> lst, UserProfile user)
			throws ServiceException {
		List<RetItemBo> boLst = BeanUtils.copyProperties(lst, RetItemBo.class);
		accCapWithService.executeBatchDkFileImport(fileName, boLst, user);
	}

	/**
	 * 中金资方单笔扣款
	 */
	@Transactional
	public SingleDkResultDto singleRepayZf(SingleRepayDto singleRepayDto, UserProfile userProFile)
			throws ServiceException {
		SingleRepayBo bo = new SingleRepayBo();
		singleRepayDto.setTranType(PayChanType.ZJPAY.toString());
		singleRepayDto.setChanNo("006");
		BeanUtils.copyProperties(singleRepayDto, bo);
		SingleDkResultBo bos = accCapWithService.singleRepay(bo, userProFile);
		SingleDkResultDto dto = new SingleDkResultDto();
		if (bos == null) {
			throw new ServiceException("单扣调用接口连接失败，返回数据为空");
		} else if (bos.getRetItem() == null && bos.getRetCode().equals(FinanceConstant.TRAN_ST_DEALING)) {
			RetItem re = new RetItem();
			re.setRetCode(FinanceConstant.TRAN_ST_DEALING);
			re.setErrMsg("代扣处理中......");
			dto.setRetItem(re);
		} else {
			RetItem re = new RetItem();
			try {
				BeanUtils.copyProperties(bos.getRetItem(), re);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			dto.setRetItem(re);
		}

		BeanUtils.copyProperties(bos, dto);
		return dto;
	}

	/**
	 * 中金批量资方扣款
	 */
	@Transactional
	public BatchDkResultDto batchRepayZf(Map<String, Object> params) throws ServiceException {
		params.put("exportTxtType", PayChanType.ZJPAY.toString());
		params.put("batchChanNo", "006");
		BatchDkResultBo bo = accCapWithService.executeBatchRepay(params);
		BatchDkResultDto dto = new BatchDkResultDto();
		if (bo == null) {
			throw new ServiceException("批量调用接口连接失败，返回数据为空");
		}
		BeanUtils.copyProperties(bo, dto);
		return dto;

	}

	/**
	 * 中金平台单笔扣款
	 */
	@Transactional
	public SingleDkResultDto singleRepayPt(SingleRepayDto singleRepayDto, UserProfile userProFile)
			throws ServiceException {
		SingleRepayBo bo = new SingleRepayBo();
		singleRepayDto.setTranType(PayChanType.ZJPAY.toString());
		singleRepayDto.setChanNo("999");
		BeanUtils.copyProperties(singleRepayDto, bo);
		SingleDkResultBo bos = accCapWithService.singleRepay(bo, userProFile);
		SingleDkResultDto dto = new SingleDkResultDto();
		if (bos == null) {
			throw new ServiceException("单扣调用接口连接失败，返回数据为空");
		} else if (bos.getRetItem() == null && bos.getRetCode().equals(FinanceConstant.TRAN_ST_DEALING)) {
			RetItem re = new RetItem();
			re.setRetCode(FinanceConstant.TRAN_ST_DEALING);
			re.setErrMsg("代扣处理中......");
			dto.setRetItem(re);
		} else {
			RetItem re = new RetItem();
			try {
				BeanUtils.copyProperties(bos.getRetItem(), re);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			dto.setRetItem(re);
		}

		BeanUtils.copyProperties(bos, dto);
		return dto;
	}

	/**
	 * 中金平台批量扣款
	 */
	@Transactional
	public BatchDkResultDto batchRepayPt(Map<String, Object> params) throws ServiceException {
		params.put("exportTxtType", PayChanType.ZJPAY.toString());
		params.put("batchChanNo", "999");
		BatchDkResultBo bo = accCapWithService.executeBatchRepay(params);
		BatchDkResultDto dto = new BatchDkResultDto();
		if (bo == null) {
			throw new ServiceException("批量扣款调用接口连接失败，返回数据为空");
		}
		BeanUtils.copyProperties(bo,dto);
		return dto;

	}
}
