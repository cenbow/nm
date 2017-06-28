package com.hs.loan.finance.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.finance.bo.BatchRepayCallBackBo;
import com.hs.loan.finance.bo.BatchRepayDKFileBo;
import com.hs.loan.finance.entity.AccCapTranDtl;

/**
 *  mapper
 * @author autocreate
 * @create 2016-02-03
 */
@MyBatisRepository
public interface  AccCapTranDtlMapper extends BaseMapper<AccCapTranDtl>{
	
	
	/**
	 * 删除交易明细 根据日志id
	 * @param params
	 */
	public Integer deleteByLogIdKey(Map<String, Object> params);
	/**
	 * 根据请求银联文件名查询交易明细纪录
	 * @param fileName
	 * @return
	 */
	@Deprecated
	public List<BatchRepayCallBackBo> queryBatchRepayCallBackInfo(String fileName);
	
	/**
	 * 根据代扣导出文件id查询
	 * @param logId
	 * @return
	 */
	public List<BatchRepayCallBackBo> queryBatchRepayDkFile(String logId);
	
	/**
	 * 根据代扣登记导出文件id查询
	 * @param logId
	 * @return
	 */
	public List<BatchRepayDKFileBo> queryBatchRepayDkFileForPay(String logId);
	
	
	public Integer batckInsertAccCapTranDtl(List<AccCapTranDtl> list);
	
}