package com.hs.loan.finance.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.finance.bo.BatchRepayCallBackBo;
import com.hs.loan.finance.bo.BatchRepayDKFileBo;
import com.hs.loan.finance.entity.AccCapTranDtl;
import com.hs.loan.finance.mapper.AccCapTranDtlMapper;

/**
 *  业务处理
 * @author autocreate
 * @create 2016-02-03
 */
@Service
@Transactional(readOnly=true)
public class  AccCapTranDtlService{
	@Autowired
	private AccCapTranDtlMapper accCapTranDtlMapper;
	
	
	/**
	 * 删除交易明细 根据日志id
	 * @param params
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Integer deleteByLogIdKey(Map<String, Object> params){
		return accCapTranDtlMapper.deleteByLogIdKey(params);
	}
	
	/**
	 * 批量插入银联返回纪录
	 * @param list
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Integer batckInsertAccCapTranDtl(List<AccCapTranDtl> list){
		return accCapTranDtlMapper.batckInsertAccCapTranDtl(list);
	}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	@Deprecated
	public List<BatchRepayCallBackBo> queryBatchRepayCallBackInfo(String fileName){
		return accCapTranDtlMapper.queryBatchRepayCallBackInfo(fileName);
	}
	
	public List<BatchRepayCallBackBo> queryBatchRepayDkFile(String logId){
		return accCapTranDtlMapper.queryBatchRepayDkFile(logId);
	}
	
	public List<BatchRepayDKFileBo> queryBatchRepayDkFileForPay(String logId){
		return accCapTranDtlMapper.queryBatchRepayDkFileForPay(logId);
	}
	
	/**
	 * 新增 
	 * @param accCapTranDtl 新增对象
	 */
	@Transactional
	public void insert(AccCapTranDtl accCapTranDtl){
		accCapTranDtlMapper.insert(accCapTranDtl);
	}

	/**
	 * 通过主键修改 
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		accCapTranDtlMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		accCapTranDtlMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得  对象
	 * @param primaryKey 主键
	 * @return 对象
	 */
	public AccCapTranDtl getByPrimaryKey(String primaryKey){
		return accCapTranDtlMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询  列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AccCapTranDtl> queryForList(Map<String, Object> param){
		return accCapTranDtlMapper.queryForList(param);
	}
	
	/**
	 * 查询  分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AccCapTranDtl> queryForPage(Page<AccCapTranDtl> page){
		accCapTranDtlMapper.queryForList(page.getPageParams());
		return (Page<AccCapTranDtl>)page.getPageParams().get(Page.KEY);
	}
}