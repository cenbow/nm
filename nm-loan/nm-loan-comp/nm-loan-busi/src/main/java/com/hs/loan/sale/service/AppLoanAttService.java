package com.hs.loan.sale.service;

import com.hs.base.entity.Page;
import com.hs.loan.sale.entity.AppLoanAtt;
import com.hs.loan.sale.mapper.AppLoanAttMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * APP_分期附件表 业务处理
 * @author autocreate
 * @create 2015-11-11
 */
@Service
@Transactional(readOnly=true)
public class  AppLoanAttService{
	@Autowired
	private AppLoanAttMapper appLoanAttMapper;
	/**
	 * 获取产品渠道
	 * @param map{loanNo:贷款编号}
	 * @return Map{SALE_CHAN:产品销售渠道}
	 */
	public Map getLoanProd(java.util.Map map){
		return appLoanAttMapper.getLoanProd(map);
	}
	public List<HashMap<String,Object>> getAttByLoanNo(java.util.Map map){
		return appLoanAttMapper.getAttByLoanNo(map);
	}
	
	/**
	 * 新增 APP_分期附件表
	 * @param appLoanAtt 新增对象
	 */
	@Transactional
	public void insert(AppLoanAtt appLoanAtt){
		appLoanAttMapper.insert(appLoanAtt);
	}

	/**
	 * 通过主键修改 APP_分期附件表
	 * @param map 修改参数Map
	 */
	@Transactional
	private void updateByPrimaryKeySelective(Map<String, Object> map){
		appLoanAttMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_分期附件表
	 * @param primaryKey 主键
	 */
	@Transactional
	private void deleteByPrimaryKey(String primaryKey){
		appLoanAttMapper.deleteByPrimaryKey(primaryKey);
	}
	/**
	 * 通过主键删除 APP_分期附件表
	 * @param 分期编号和附件号
	 */
	@Transactional
	public void deleteByPrimaryKey(Map<String,String> map){
		appLoanAttMapper.deleteByLoanAndAttNo(map);
	}
	/**
	 * 通过主键取得 APP_分期附件表 对象
	 * @param primaryKey 主键
	 * @return APP_分期附件表对象
	 */
	public AppLoanAtt getByPrimaryKey(String primaryKey){
		return appLoanAttMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_分期附件表 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppLoanAtt> queryForList(Map<String, Object> param){
		return appLoanAttMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_分期附件表 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppLoanAtt> queryForPage(Page<AppLoanAtt> page){
		appLoanAttMapper.queryForList(page.getPageParams());
		return (Page<AppLoanAtt>)page.getPageParams().get(Page.KEY);
	}
	
	/**
	 * 根据附件码组获取贷款附件
	 * @param map{loanNo: 贷款编号, codTyp: 码类, groupCod: 码组}
	 * @return
	 */
	public List<AppLoanAtt> queryLoanAttByGroupCod(Map<String,Object> map) {
		return appLoanAttMapper.queryLoanAttByGroupCod(map);
	}
}