package com.hs.loan.finance.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.finance.mapper.SysSalescoreInfoMapper;
import com.hs.loan.finance.entity.SysSalescoreInfo;
import com.hs.base.entity.Page;

/**
 *  员工积分业务处理
 * @author autocreate
 * @create 2016-10-11
 */
@Service
@Transactional(readOnly=true)
public class  SysSalescoreInfoService{
	@Autowired
	private SysSalescoreInfoMapper sysSalescoreInfoMapper;
	
	/**
	 * 新增 
	 * @param sysSalescoreInfo 新增对象
	 */
	@Transactional
	public void insert(SysSalescoreInfo sysSalescoreInfo){
		sysSalescoreInfoMapper.insert(sysSalescoreInfo);
	}

	/**
	 * 通过主键修改 
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		sysSalescoreInfoMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		sysSalescoreInfoMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得  对象
	 * @param primaryKey 主键
	 * @return 对象
	 */
	public SysSalescoreInfo getByPrimaryKey(String primaryKey){
		return sysSalescoreInfoMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询  列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<SysSalescoreInfo> queryForList(Map<String, Object> param){
		return sysSalescoreInfoMapper.queryForList(param);
	}
	
	/**
	 * 查询  分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<SysSalescoreInfo> queryForPage(Page<SysSalescoreInfo> page){
		sysSalescoreInfoMapper.queryForList(page.getPageParams());
		return (Page<SysSalescoreInfo>)page.getPageParams().get(Page.KEY);
	}
}