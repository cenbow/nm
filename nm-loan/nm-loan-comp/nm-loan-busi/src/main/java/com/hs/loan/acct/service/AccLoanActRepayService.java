package com.hs.loan.acct.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.loan.acct.entity.AccLoanActRepay;
import com.hs.loan.acct.mapper.AccLoanActRepayMapper;

/**
 * ACC_实还明细信息 业务处理
 * @author autocreate
 * @create 2015-10-30
 */
@Service
@Transactional(readOnly=true)
public class  AccLoanActRepayService{
	@Autowired
	private AccLoanActRepayMapper accLoanActRepayMapper;
	
	/**
	 * 新增 ACC_实还明细信息
	 * @param accLoanActRepay 新增对象
	 */
	@Transactional
	public void insert(AccLoanActRepay accLoanActRepay){
		accLoanActRepayMapper.insert(accLoanActRepay);
	}

	/**
	 * 通过主键修改 ACC_实还明细信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		accLoanActRepayMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 ACC_实还明细信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		accLoanActRepayMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 ACC_实还明细信息 对象
	 * @param primaryKey 主键
	 * @return ACC_实还明细信息对象
	 */
	private AccLoanActRepay getByPrimaryKey(String primaryKey){
		return accLoanActRepayMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 ACC_实还明细信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	private List<AccLoanActRepay> queryForList(Map<String, Object> param){
		return accLoanActRepayMapper.queryForList(param);
	}
	

	/**
	 * 通过分期编号查询实还明细信息 列表
	 * @param loanNo 分期编号
	 * @return List<AccLoanActRepay>
	 */
	public List<AccLoanActRepay> queryByLoanNo(String loanNo){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loanNo", loanNo);
		List<AccLoanActRepay> list = this.queryForList(param);
		return list;
	}
}