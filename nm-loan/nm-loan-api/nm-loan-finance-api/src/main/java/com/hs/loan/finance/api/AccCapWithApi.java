package com.hs.loan.finance.api;

import java.util.List;
import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.finance.dto.BatchDkResultDto;
import com.hs.loan.finance.dto.QueryResultDto;
import com.hs.loan.finance.dto.RetItemDto;
import com.hs.loan.finance.dto.SingleOtherBusiDto;
import com.hs.loan.finance.dto.SingleRepayDto;
import com.hs.loan.finance.withpay.dto.LoanRepayWithDto;
import com.hs.loan.finance.withpay.dto.SingleDkResultDto;

/**
 *  代扣接口
 * @author autocreate
 * @create 2016-02-03
 */
public interface  AccCapWithApi{

	/**
	 * 查询分期代扣信息
	 * @param page
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	public Page<LoanRepayWithDto> queryLoanPayWithInfo(Page<LoanRepayWithDto> page)  throws ServiceException,AppException;
	 
	/**
	 * 查询单笔代扣信息
	 * @param singleRepayVO
	 * @return
	 * @throws ServiceException
	 */
	public SingleRepayDto querySingleRepayInfo(SingleRepayDto singleRepayVO) throws ServiceException; 
	
	/**
	 * 银联单笔代扣
	 * @param singleRepayVO
	 * @return 
	 * @throws ServiceException
	 */
	public SingleDkResultDto singleRepay(SingleRepayDto singleRepayDto,UserProfile userProFile) throws ServiceException;
	
	/**
	 * 银联单笔代扣 供催收 提前结清处使用
	 * @param singleRepayVO
	 * @return 
	 * @throws ServiceException
	 */
	public SingleDkResultDto singleRepayOtherBusi(SingleOtherBusiDto singleOtherBusiDto,UserProfile userProFile) throws ServiceException;
	
	
	/**
	 * 银联批量代扣
	 * @param params 查询条件
	 * @return
	 * @throws Exception
	 */
	public BatchDkResultDto batchRepay(Map<String, Object> params) throws ServiceException; 
	
	/**
	 * 批量代扣文件导出
	 * @param params 查询条件
	 * @return
	 * @throws Exception
	 */
	public String batchDkFileExport(Map<String, Object> params) throws ServiceException; 
	
	/**
	 * 回盘文件上传后的处理
	 * @param params
	 * @throws ServiceException
	 */
	public void executeBatchDkFileImport(String fileName,List<RetItemDto> lst,UserProfile user) throws ServiceException; 
	
	/**
	 * 前端查询组装后的代扣数据
	 * @param page
	 * @return
	 */
	public Page<SingleRepayDto> querySingleRepayListForPage(Page<SingleRepayDto> page) throws ServiceException;
	
	/**
	 * 批量回调接口
	 * @param reqSN
	 * @throws ServiceException
	 */
	public void executeBatchRepayCallback(String reqSN) throws ServiceException;
	
	/**
	 * 手动回盘查询
	 * @param reqSN
	 * @param chalCode 银联渠道
	 * @return
	 * @throws ServiceException
	 */
	public QueryResultDto queryBatchRepay(String reqSN,String chalCode) throws ServiceException;
	
	
	/**
	 * 中金单笔资方扣款
	 * @param singleRepayDto
	 * @param userProFile
	 * @return
	 * @throws ServiceException
	 */
	public SingleDkResultDto singleRepayZf(SingleRepayDto singleRepayDto,UserProfile userProFile) throws ServiceException;
	/**
	 * 中金批量资方扣款
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public BatchDkResultDto batchRepayZf(Map<String, Object> params) throws ServiceException; 
	/**
	 * 中金单笔平台扣款
	 * @param singleRepayDto
	 * @param userProFile
	 * @return
	 * @throws ServiceException
	 */
	public SingleDkResultDto singleRepayPt(SingleRepayDto singleRepayDto,UserProfile userProFile) throws ServiceException;
	/**
	 * 中金批量平台扣款
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public BatchDkResultDto batchRepayPt(Map<String, Object> params) throws ServiceException; 
}