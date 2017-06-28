package com.hs.loan.acct.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.loan.acct.entity.AccRepaySum;
import com.hs.loan.acct.mapper.AccRepaySumMapper;

/**
 * ACC_应收实收金额汇总 业务处理
 * @author autocreate
 * @create 2015-10-30
 */
@Service
@Transactional(readOnly=true)
public class  AccRepaySumService{
	@Autowired
	private AccRepaySumMapper accRepaySumMapper;
	
	/**
	 * 新增 ACC_应收实收金额汇总
	 * @param accRepaySum 新增对象
	 */
	@Transactional
	public void insert(AccRepaySum accRepaySum){
		accRepaySumMapper.insert(accRepaySum);
	}

	/**
	 * 通过主键修改 ACC_应收实收金额汇总
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		accRepaySumMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 ACC_应收实收金额汇总
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		accRepaySumMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 ACC_应收实收金额汇总 对象
	 * @param primaryKey 主键
	 * @return ACC_应收实收金额汇总对象
	 */
	private AccRepaySum getByPrimaryKey(String primaryKey){
		return accRepaySumMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 ACC_应收实收金额汇总 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	private List<AccRepaySum> queryForList(Map<String, Object> param){
		return accRepaySumMapper.queryForList(param);
	}
	
	/**
	 * 通过分期编号查询分期汇总信息
	 * @param loanNo 分期编号
	 * @return AccRepaySum
	 */
	public AccRepaySum getByLoanNo(String loanNo){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loanNo", loanNo);
		List<AccRepaySum> list = this.queryForList(param);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
}