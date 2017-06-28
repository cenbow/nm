package com.hs.loan.finance.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.finance.mapper.AppChanFstdateImportMapper;
import com.hs.loan.finance.entity.AppChanFstdateImport;
import com.hs.base.entity.Page;
import com.hs.base.exception.ServiceException;

/**
 * 业务处理
 * 
 * @author autocreate
 * @create 2016-09-26
 */
@Service
@Transactional(readOnly = true)
public class AppChanFstdateImportService {
	@Autowired
	private AppChanFstdateImportMapper appChanFstdateImportMapper;

	/**
	 * 新增
	 * 
	 * @param appChanFstdateImport
	 *            新增对象
	 */
	@Transactional
	public void insert(AppChanFstdateImport appChanFstdateImport) {
		appChanFstdateImportMapper.insert(appChanFstdateImport);
	}

	/**
	 * 通过主键修改
	 * 
	 * @param map
	 *            修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map) {
		appChanFstdateImportMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除
	 * 
	 * @param primaryKey
	 *            主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey) {
		appChanFstdateImportMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 对象
	 * 
	 * @param primaryKey
	 *            主键
	 * @return 对象
	 */
	public AppChanFstdateImport getByPrimaryKey(String primaryKey) {
		return appChanFstdateImportMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 列表
	 * 
	 * @param param
	 *            查询参数map
	 * @return List<T>列表
	 */
	public List<AppChanFstdateImport> queryForList(Map<String, Object> param) {
		return appChanFstdateImportMapper.queryForList(param);
	}

	/**
	 * 查询 分页列表
	 * 
	 * @param page
	 *            查询参数page
	 * @return List<T>列表
	 */
	public Page<AppChanFstdateImport> queryForPage(Page<AppChanFstdateImport> page) {
		appChanFstdateImportMapper.queryForList(page.getPageParams());
		return (Page<AppChanFstdateImport>) page.getPageParams().get(Page.KEY);
	}

	@Transactional
	public void insertList(List<AppChanFstdateImport> importList) {
		for (AppChanFstdateImport imp : importList) {
			appChanFstdateImportMapper.insert(imp);
		}
		appChanFstdateImportMapper.callDcFlow();
	}
}