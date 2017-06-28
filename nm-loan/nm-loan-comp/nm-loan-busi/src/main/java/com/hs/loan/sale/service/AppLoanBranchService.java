package com.hs.loan.sale.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.loan.sale.mapper.AppLoanBranchMapper;
import com.hs.loan.sale.entity.AppLoanBranch;
import com.hs.loan.sale.entity.AppLoanSaler;
import com.hs.base.entity.Page;

/**
 * APP_分期与网点关系 业务处理
 * @author autocreate
 * @create 2015-10-27
 */
@Service
@Transactional(readOnly=true)
public class  AppLoanBranchService{
	@Autowired
	private AppLoanBranchMapper appLoanBranchMapper;
	public Map getPubBranchByLoanNo(Map map){
		return appLoanBranchMapper.getPubBranchByLoanNo(map);
	}
	
	/**
	 * 新增 APP_分期与网点关系
	 * @param appLoanBranch 新增对象
	 */
	@Transactional
	public void insert(AppLoanBranch appLoanBranch){
		appLoanBranchMapper.insert(appLoanBranch);
	}

	/**
	 * 通过主键修改 APP_分期与网点关系
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appLoanBranchMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_分期与网点关系
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appLoanBranchMapper.deleteByPrimaryKey(primaryKey);
	}
	/**
	 * 通过主键删除 APP_分期与网点关系
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByLoanNo(String loanNo){
		appLoanBranchMapper.deleteByLoanNo(loanNo);
	}

	

	/**
	 * 通过主键取得 APP_分期与网点关系 对象
	 * @param primaryKey 主键
	 * @return APP_分期与网点关系对象
	 */
	private AppLoanBranch getByPrimaryKey(String primaryKey){
		return appLoanBranchMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_分期与网点关系 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	private List<AppLoanBranch> queryForList(Map<String, Object> param){
		return appLoanBranchMapper.queryForList(param);
	}
	

	/**
	 * 查询分期网点信息
	 * @param loanNo 分期编号
	 * @return AppLoanBranch 网点信息
	 */
	public AppLoanBranch getByLoanNo(String loanNo){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("loanNo", loanNo);
		List<AppLoanBranch> list = this.queryForList(param);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
}