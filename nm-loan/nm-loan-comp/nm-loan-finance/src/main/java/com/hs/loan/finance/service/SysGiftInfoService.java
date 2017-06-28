package com.hs.loan.finance.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.finance.mapper.SysGiftInfoMapper;
import com.hs.loan.finance.entity.SysGiftInfo;
import com.hs.base.entity.Page;

/**
 * 礼品业务处理
 * 
 * @author autocreate
 * @create 2016-10-11
 */
@Service
@Transactional(readOnly = true)
public class SysGiftInfoService {
	@Autowired
	private SysGiftInfoMapper sysGiftInfoMapper;

	/**
	 * 新增
	 * 
	 * @param sysGiftInfo
	 *            新增对象
	 */
	@Transactional
	public void insert(SysGiftInfo sysGiftInfo) {
		sysGiftInfoMapper.insert(sysGiftInfo);
	}

	/**
	 * 通过主键修改
	 * 
	 * @param map
	 *            修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map) {
		sysGiftInfoMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除
	 * 
	 * @param primaryKey
	 *            主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey) {
		sysGiftInfoMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 对象
	 * 
	 * @param primaryKey
	 *            主键
	 * @return 对象
	 */
	public SysGiftInfo getByPrimaryKey(String primaryKey) {
		return sysGiftInfoMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 列表
	 * 
	 * @param param
	 *            查询参数map
	 * @return List<T>列表
	 */
	public List<SysGiftInfo> queryForList(Map<String, Object> param) {
		return sysGiftInfoMapper.queryForList(param);
	}

	/**
	 * 查询 分页列表
	 * 
	 * @param page
	 *            查询参数page
	 * @return List<T>列表
	 */
	public Page<SysGiftInfo> queryForPage(Page<SysGiftInfo> page) {
		sysGiftInfoMapper.queryForList(page.getPageParams());
		return (Page<SysGiftInfo>) page.getPageParams().get(Page.KEY);
	}
	
	/**
	 * 根据giftNo统计次数
	 * @param map
	 * @return
	 */
	public Integer getCountByGiftNo(Map<String,Object> map)
	{
		return sysGiftInfoMapper.getCountByGiftNo(map);
	}
}