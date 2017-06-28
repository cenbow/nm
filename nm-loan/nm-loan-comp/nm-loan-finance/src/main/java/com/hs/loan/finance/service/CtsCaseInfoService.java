package com.hs.loan.finance.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.finance.contant.FinanceConstant;
import com.hs.loan.finance.entity.CtsCaseInfo;
import com.hs.loan.finance.mapper.CtsCaseInfoMapper;

/**
 * 业务处理
 * 
 * @author autocreate
 * @create 2016-10-13
 */
@Service
@Transactional(readOnly = true)
public class CtsCaseInfoService {
	@Autowired
	private CtsCaseInfoMapper ctsCaseInfoMapper;

	/**
	 * 新增
	 * 
	 * @param ctsCaseInfo
	 *            新增对象
	 */
	@Transactional
	public void insert(CtsCaseInfo ctsCaseInfo) {
		ctsCaseInfoMapper.insert(ctsCaseInfo);
	}

	/**
	 * 通过主键修改
	 * 
	 * @param map
	 *            修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map) {
		ctsCaseInfoMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除
	 * 
	 * @param primaryKey
	 *            主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey) {
		ctsCaseInfoMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 对象
	 * 
	 * @param primaryKey
	 *            主键
	 * @return 对象
	 */
	public CtsCaseInfo getByPrimaryKey(String primaryKey) {
		return ctsCaseInfoMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 列表
	 * 
	 * @param param
	 *            查询参数map
	 * @return List<T>列表
	 */
	public List<CtsCaseInfo> queryForList(Map<String, Object> param) {
		return ctsCaseInfoMapper.queryForList(param);
	}

	/**
	 * 查询 分页列表
	 * 
	 * @param page
	 *            查询参数page
	 * @return List<T>列表
	 */
	public Page<CtsCaseInfo> queryForPage(Page<CtsCaseInfo> page) {
		Map<String, Object> params = page.getPageParams();
		if (params.get("caseStat") == null) {
			params.put("caseStat", "60021001");
		}
		if (params.get("dealStat") == null) {
			params.put("dealStat", FinanceConstant.ALL_ZT_FALSE);
		}
		ctsCaseInfoMapper.queryForList(params);
		return (Page<CtsCaseInfo>) page.getPageParams().get(Page.KEY);
	}

	public void updateCaseList(List<Map<String, Object>> list) {
		// TODO Auto-generated method stub
		for (Map<String, Object> param : list) {
			param.put("updtDate", new Date());
			ctsCaseInfoMapper.updateByPrimaryKeySelective(param);
		}
	}

	public boolean queryParam(String loanNo) {
		// TODO Auto-generated method stub
		if (ctsCaseInfoMapper.queryParam(loanNo)!=null) {
			return false;
		}else{
			return true;
		}
	}
}