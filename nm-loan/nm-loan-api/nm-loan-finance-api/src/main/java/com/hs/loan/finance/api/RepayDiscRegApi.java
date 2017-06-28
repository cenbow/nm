package com.hs.loan.finance.api;

import java.util.List;
import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.ServiceException;
import com.hs.loan.finance.dto.AccLoanPlanDto;
import com.hs.loan.finance.dto.AccRepayDiscRegDto;
import com.hs.loan.finance.dto.AccRepayDiscRegInfoDto;

/**
 * ACC_还款登记（费用减免） 接口
 * 
 * @author autocreate
 * @create 2016-02-03
 */
public interface RepayDiscRegApi {
	/**
	 * 新增 ACC_还款登记（费用减免）
	 * 
	 * @param accRepayDiscReg
	 *            新增对象
	 * @throws Exception
	 */
	public void insert(AccRepayDiscRegDto repayDiscRegDto, UserProfile user) throws ServiceException;

	/**
	 * 通过主键修改 ACC_还款登记（费用减免）
	 * 
	 * @param map
	 *            修改参数Map
	 */
	public void updateByPrimaryKeySelective(Map<String, Object> map);

	/**
	 * 通过主键删除 ACC_还款登记（费用减免）
	 * 
	 * @param primaryKey
	 *            主键
	 */
	public void deleteByPrimaryKey(String primaryKey);

	/**
	 * 通过主键取得 ACC_还款登记（费用减免） 对象
	 * 
	 * @param primaryKey
	 *            主键
	 * @return ACC_还款登记（费用减免）对象
	 */
	public AccRepayDiscRegDto getByPrimaryKey(String primaryKey);

	/**
	 * 查询 ACC_还款登记（费用减免） 列表
	 * 
	 * @param param
	 *            查询参数map
	 * @return List<T>列表
	 */
	public List<AccRepayDiscRegDto> queryForList(Map<String, Object> param);

	/**
	 * 查询 ACC_还款登记（费用减免） 分页列表
	 * 
	 * @param page
	 *            查询参数page
	 * @return List<T>列表
	 */
	public Page<AccRepayDiscRegDto> queryForPage(Page<AccRepayDiscRegDto> page);

	/**
	 * 根据时间查询 ACC_还款登记（费用减免） 分页列表
	 * 
	 * @param page
	 *            查询参数page
	 * @return List<T>列表
	 */
	public Page<AccRepayDiscRegInfoDto> queryForListByInstDate(Page<AccRepayDiscRegInfoDto> page);

	/**
	 * 查询还款计划
	 * 
	 * @param page
	 * @return
	 */
	public Page<AccLoanPlanDto> queryListOnNo(Page<AccLoanPlanDto> page) throws ServiceException;
}