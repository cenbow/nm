package com.hs.loan.sale.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.loan.sale.entity.AppLoanRepayTyp;
import com.hs.loan.sale.mapper.AppLoanRepayTypMapper;

/**
 * APP_分期与还款类型的关系 业务处理
 * @author autocreate
 * @create 2015-10-30
 */
@Service
@Transactional(readOnly=true)
public class  AppLoanRepayTypService{
	@Autowired
	private AppLoanRepayTypMapper appLoanRepayTypMapper;
	
	/**
	 * 新增 APP_分期与还款类型的关系
	 * @param appLoanRepayTyp 新增对象
	 */
	@Transactional
	public void insert(AppLoanRepayTyp appLoanRepayTyp){
		appLoanRepayTypMapper.insert(appLoanRepayTyp);
	}

	/**
	 * 通过主键修改 APP_分期与还款类型的关系
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appLoanRepayTypMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_分期与还款类型的关系
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appLoanRepayTypMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_分期与还款类型的关系 对象
	 * @param primaryKey 主键
	 * @return APP_分期与还款类型的关系对象
	 */
	public AppLoanRepayTyp getByPrimaryKey(String primaryKey){
		return appLoanRepayTypMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_分期与还款类型的关系 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	private List<AppLoanRepayTyp> queryForList(Map<String, Object> param){
		return appLoanRepayTypMapper.queryForList(param);
	}
	
	
	/**
	 * 查询分期与还款类型列表
	 * @param loanNo 分期编号
	 * @return List<T>列表
	 */
	public List<AppLoanRepayTyp> queryByLoanNo(String loanNo){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("loanNo", loanNo);
		return this.queryForList(param);
	}
	/**
	 * 删除分期与还款类型列表
	 * @param loanNo 分期编号
	 * @return List<T>列表
	 */
	
	public void deleteByLoanNo(String loanNo) {
		appLoanRepayTypMapper.deleteByLoanNo(loanNo);
		
	}
}