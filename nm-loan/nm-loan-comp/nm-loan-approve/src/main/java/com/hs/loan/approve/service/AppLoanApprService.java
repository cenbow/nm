package com.hs.loan.approve.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.approve.bo.AppLoanApprBo;
import com.hs.loan.approve.entity.AppLoanAppr;
import com.hs.loan.approve.mapper.AppLoanApprMapper;

/**
 * APP_分期审批信息 业务处理
 * @author autocreate
 * @create 2015-11-23
 */
@Service
@Transactional(readOnly=true)
public class  AppLoanApprService{
	@Autowired
	private AppLoanApprMapper appLoanApprMapper;
	public List<AppLoanAppr> getAppLoanAppr(Map map){
		return appLoanApprMapper.getAppLoanAppr(map);
	}
	/**
	 * 更新根据进入时间的第一条未被未被分配的任务
	 * @param map (人工审批开始时间 MANU_START_DATE,审批人员编号 APPR_NO,审批人员姓名 apprName)
	 * @return
	 */
	@Transactional
	public int updateFirstOrderByInstDate(HashMap<String,Object> map){
		return appLoanApprMapper.updateFirstOrderByInstDate(map);
	}
	/**
	 * 查询是否存在带分配的贷款审批案件
	 * @return int
	 */
	public int selectWaitAllot(HashMap<String,Object> map){
		return appLoanApprMapper.selectWaitAllot(map);
	}
	/**
	 * 新增 APP_分期审批信息
	 * @param appLoanAppr 新增对象
	 */
	@Transactional
	public void insert(AppLoanAppr appLoanAppr){
		appLoanApprMapper.insert(appLoanAppr);
	}

	/**
	 * 通过主键修改 APP_分期审批信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appLoanApprMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_分期审批信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appLoanApprMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_分期审批信息 对象
	 * @param primaryKey 主键
	 * @return APP_分期审批信息对象
	 */
	public AppLoanAppr getByPrimaryKey(String primaryKey){
		return appLoanApprMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_分期审批信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppLoanAppr> queryForList(Map<String, Object> param){
		return appLoanApprMapper.queryForList(param);
	}
	
	/**
	 * 统计当前有多少待审批的
 	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public int queryForListcnt(Map<String, Object> param){
		return appLoanApprMapper.queryForListcnt(param);
	}
	
	/**
	 * 查询 APP_分期审批信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppLoanAppr> queryForPage(Page<AppLoanAppr> page){
		appLoanApprMapper.queryForList(page.getPageParams());
		return (Page<AppLoanAppr>)page.getPageParams().get(Page.KEY);
	}
	
	/**
	 * 查询 APP_分期审批信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppLoanApprBo> queryForPageTwo(Page<AppLoanApprBo> page){
		appLoanApprMapper.queryForListTwo(page.getPageParams());
		return (Page<AppLoanApprBo>)page.getPageParams().get(Page.KEY);
	}
	
/***
 * 	根据贷款编号修改现金贷金额（调额）
 * @param loanNo
 * @param pric
 * @return
 */
	public int updateGoodsPric(String loanNo,BigDecimal pric) {
		Map<String,Object> paramap = new HashMap<>();
		paramap.put("loanNo", loanNo);
		paramap.put("pric", pric);
		return appLoanApprMapper.updateGoodsPric(paramap);
	}
	/**获取审批发送短信的判断结果*/
	public Map<String, Object> querySendPhoneNo(String _loanNo, String _custNo) {
	// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<>();
		param.put("_loanNo", _loanNo);
		param.put("_custNo", _custNo);
		Map<String, Object> goodsType =	appLoanApprMapper.getGoodsType(param);
		if (goodsType!=null) {
			return appLoanApprMapper.querySendPhoneNo(param);
		}else {
			return null;
		}
	}
}