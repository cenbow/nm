package com.hs.loan.outsource.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.outsource.bo.LoanOutsourceBo;
import com.hs.loan.outsource.entity.PlLoanOutsource;
import com.hs.loan.outsource.mapper.PlLoanOutsourceMapper;
//import com.nm.cache.busi.service.OperateCacheServiceApi;

/**
 * PL_委外案件 业务处理 委外扣款
 * 
 * @author autocreate
 * @create 2015-12-02
 */
@Service
@Transactional(readOnly = true)
public class PlLoanOutsourceService {
	@Autowired
	private PlLoanOutsourceMapper plLoanOutsourceMapper;
	
//	@Autowired
//	private OperateCacheServiceApi operateCacheService;
	
    public Map<String,Object> getStaff(Map map){
//    	List<Map<String,Object>> lst =operateCacheService.queryCacheByCondition("SYS_STAFF", map);
//    	Map<String,Object> tmp = new HashMap<String,Object>();
//    	if(!lst.isEmpty())
//    	{
//    		tmp.put("STAFF_STAT", lst.get(0).get("staffStat"));
//    	}
//    	return tmp;
    	return plLoanOutsourceMapper.getStaff(map);
    }
	public Map<String,Object> outsourceOver(Map map){
	return plLoanOutsourceMapper.outsourceOver(map);
	}

	public List<Map<String,Object>> outsourceCur(Map map){
		return plLoanOutsourceMapper.outsourceCur(map);
	}

	/**
	 *
	 * @param map{loanNo:贷款编号}
	 * @return Map<String,Object>
	 */
	@Transactional
	public Map<String,Object> callBackCase(Map<String,Object> map){
	  Integer dealResult=plLoanOutsourceMapper.dealCallBackCase(map);
	  Map<String,Object> result=new HashMap<>();
	  if(null!=dealResult&&dealResult.intValue()>=1){
        result.put("result","success");
	  }else{
		  result.put("result","error");
	  }
      return result;
	}

	/**
	 * 查询销售中心
	 * @return Map{PROV_NO:省的编号,PROV_NAME:省份名称}
	 */
	public List<Map> getRegionalSale(){
		return plLoanOutsourceMapper.getRegionalSale();
	}

	/**
	 * 根据贷款编号查询应还多少钱(委外费用+实际还款金额)
	 * 
	 * @param loanNo
	 * @return HashMap
	 */
	public HashMap<String, Object> getcurRvcAmtByLoanNo(Map<String, Object> param) {
		return plLoanOutsourceMapper.getcurRvcAmtByLoanNo(param);
	}

	/**
	 * 根据员工编号查询员工角色
	 * 
	 * @param staffNo
	 * @return List<String>
	 */
	public List<String> selectRoleByStaffNo(String staffNo) {
		return plLoanOutsourceMapper.selectRoleByStaffNo(staffNo);
	}

	/**
	 * 根据贷款编号查询还款计划的费用和
	 * 
	 * @param loanNo
	 * @return HashMap<String,Object>
	 */
	public HashMap<String, Object> getLoanPlanSumByLoanNo(String loanNo) {
		return plLoanOutsourceMapper.getLoanPlanSumByLoanNo(loanNo);
	}

	/**
	 * 新增 PL_委外案件
	 * 
	 * @param plLoanOutsource
	 *            新增对象
	 */
	@Transactional
	public void insert(PlLoanOutsource plLoanOutsource) {
		plLoanOutsourceMapper.insert(plLoanOutsource);
	}

	/**
	 * 通过主键修改 PL_委外案件
	 * 
	 * @param map
	 *            修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map) {
		plLoanOutsourceMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PL_委外案件
	 * 
	 * @param primaryKey
	 *            主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey) {
		plLoanOutsourceMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 PL_委外案件 对象
	 * 
	 * @param primaryKey
	 *            主键
	 * @return PL_委外案件对象
	 */
	public PlLoanOutsource getByPrimaryKey(String primaryKey) {
		return plLoanOutsourceMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PL_委外案件 列表
	 * 
	 * @param param
	 *            查询参数map
	 * @return List<T>列表
	 */
	public List<PlLoanOutsource> queryForList(Map<String, Object> param) {
		return plLoanOutsourceMapper.queryForList(param);
	}

	/**
	 * 查询 PL_委外案件 分页列表
	 * 
	 * @param page
	 *            查询参数page
	 * @return List<T>列表
	 */
	public Page<PlLoanOutsource> queryForPage(Page<PlLoanOutsource> page) {
		plLoanOutsourceMapper.queryForList(page.getPageParams());
		return (Page<PlLoanOutsource>) page.getPageParams().get(Page.KEY);
	}

	/**
	 * 查询 PL_委外案件 分页列表
	 * 
	 * @param page
	 *            查询参数page
	 * @return List<T>列表
	 */
	public Page<LoanOutsourceBo> queryOutSourceForPage(Page<LoanOutsourceBo> page) {
		plLoanOutsourceMapper.queryOutSourceForList(page.getPageParams());
		return (Page<LoanOutsourceBo>) page.getPageParams().get(Page.KEY);
	}

	/**
	 * 查询 PL_委外案件 分页列表
	 * 
	 * @param page
	 *            查询参数page
	 * @return List<T>列表
	 */
	public List<LoanOutsourceBo> queryOutSourceForList(Map<String, Object> map) {
		return plLoanOutsourceMapper.queryOutSourceForList(map);
	}

	/**
	 * 查询 PL_委外案件 分页列表
	 * 
	 * @param page
	 *            查询参数page
	 * @return List<T>列表
	 */
	public List<LoanOutsourceBo> queryOutSourceForLst(Map<String, Object> map) {
		return plLoanOutsourceMapper.queryOutSourceForList(map);
	}

	public Integer updateIsSettle(Map<String, Object> outMap) {
		return plLoanOutsourceMapper.updateIsSettle(outMap);
	}
	
	/**
	 * 根据贷款编号查询还款计划的费用和
	 * 
	 * @param loanNo
	 * @return HashMap<String,Object>
	 */
	public int batchDeleteByLoanNoLst(List<String> list) 
	{
		return plLoanOutsourceMapper.batchDeleteByLoanNoLst(list);
	}
	
	/**
	 * 根据贷款编号查询还款计划的费用和
	 * 
	 * @param loanNo
	 * @return HashMap<String,Object>
	 */
	public int batchModifyByLoanNoLst(List<String> list) {
		return plLoanOutsourceMapper.batchModifyByLoanNoLst(list);
	}

}