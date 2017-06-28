package com.hs.loan.finance.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.finance.bo.SaleScoreFlowBo;
import com.hs.loan.finance.entity.AccSalescoreFlow;
import com.hs.loan.finance.mapper.AccSalescoreFlowMapper;
import com.hs.loan.finance.mapper.SysGiftInfoMapper;
import com.hs.loan.finance.mapper.SysSalescoreInfoMapper;

/**
 * 礼品兑换记录业务处理
 * 
 * @author autocreate
 * @create 2016-10-11
 */
@Service
@Transactional(readOnly = true)
public class AccSalescoreFlowService {
	@Autowired
	private AccSalescoreFlowMapper accSalescoreFlowMapper;
	@Autowired
	private SysSalescoreInfoMapper sysSalescoreInfoMapper;
	@Autowired
	private SysGiftInfoMapper sysGiftInfoMapper;

	/**
	 * 新增
	 * 
	 * @param accSalescoreFlow
	 *            新增对象
	 */
	@Transactional
	public void insert(AccSalescoreFlow accSalescoreFlow) {
		accSalescoreFlowMapper.insert(accSalescoreFlow);
	}

	/**
	 * 通过主键修改
	 * 
	 * @param map
	 *            修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map) {
		accSalescoreFlowMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除
	 * 
	 * @param primaryKey
	 *            主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey) {
		accSalescoreFlowMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 对象
	 * 
	 * @param primaryKey
	 *            主键
	 * @return 对象
	 */
	public AccSalescoreFlow getByPrimaryKey(String primaryKey) {
		return accSalescoreFlowMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 列表
	 * 
	 * @param param
	 *            查询参数map
	 * @return List<T>列表
	 */
	public List<AccSalescoreFlow> queryForList(Map<String, Object> param) {
		return accSalescoreFlowMapper.queryForList(param);
	}

	/**
	 * 查询 分页列表
	 * 
	 * @param page
	 *            查询参数page
	 * @return List<T>列表
	 */
	public Page<AccSalescoreFlow> queryForPage(Page<AccSalescoreFlow> page) {
		accSalescoreFlowMapper.queryForList(page.getPageParams());
		return (Page<AccSalescoreFlow>) page.getPageParams().get(Page.KEY);
	}

	public List<AccSalescoreFlow> queryListByStaff(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return accSalescoreFlowMapper.queryListByStaff(param);
	}

	@Transactional
	public void insertFlow(AccSalescoreFlow flow, Map<String, Object> saleCoreMap, Map<String, Object> giftInfoMap) {
		// TODO Auto-generated method stub
		accSalescoreFlowMapper.insert(flow);
		sysSalescoreInfoMapper.updateByPrimaryKeySelective(saleCoreMap);
		sysGiftInfoMapper.updateByPrimaryKeySelective(giftInfoMap);
	}
	
	/**
	 * 分页查询积分流水列表
	 * @param map
	 * @return
	 */
	public Page<SaleScoreFlowBo> queryForPageTwo(Page<SaleScoreFlowBo> page)
	{
		accSalescoreFlowMapper.queryForListTwo(page.getPageParams());
		return (Page<SaleScoreFlowBo>) page.getPageParams().get(Page.KEY);
	}
}