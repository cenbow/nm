package com.hs.loan.sale.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.sale.entity.AppChanImport;
import com.hs.loan.sale.mapper.AppChanImportMapper;

/**
 *  业务处理
 * @author autocreate
 * @create 2016-08-30
 */
@Service
@Transactional(readOnly=true)
public class  AppChanImportService{
	@Autowired
	private AppChanImportMapper appChanImportMapper;
	
	/**
	 * 新增 
	 * @param appChanImport 新增对象
	 */
	@Transactional
	public void insert(AppChanImport appChanImport){
		appChanImportMapper.insert(appChanImport);
	}

	/**
	 * 通过主键修改 
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appChanImportMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appChanImportMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得  对象
	 * @param primaryKey 主键
	 * @return 对象
	 */
	public AppChanImport getByPrimaryKey(String primaryKey){
		return appChanImportMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询  列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppChanImport> queryForList(Map<String, Object> param){
		return appChanImportMapper.queryForList(param);
	}
	
	/**
	 * 查询  分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppChanImport> queryForPage(Page<AppChanImport> page){
		appChanImportMapper.queryForList(page.getPageParams());
		return (Page<AppChanImport>)page.getPageParams().get(Page.KEY);
	}
	
	/**
	 * 先删除老记录后添加新纪录
	 * @param appChanImport
	 */
	@Transactional
	public void deleteAndInsert(AppChanImport appChanImport) {
		appChanImportMapper.deleteByPrimaryKey(appChanImport.getLoanNo());
		appChanImportMapper.insert(appChanImport);
		
	}
}