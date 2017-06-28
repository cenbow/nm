package com.hs.loan.outsource.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.outsource.bo.LoanOutsourceBo;
import com.hs.loan.outsource.entity.PlLoanOutsource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PL_委外案件 mapper
 * @author autocreate
 * @create 2015-12-02
 */
@MyBatisRepository
public interface  PlLoanOutsourceMapper extends BaseMapper<PlLoanOutsource>{
    public Map<String,Object> getStaff(Map map);

	public Map<String,Object> outsourceOver(Map map);

	public List<Map<String,Object>> outsourceCur(Map map);

	/**
	 * 委外案件回收
	 * @param map{loanNo:贷款编号}
	 * @return Integer
	 */
	public Integer dealCallBackCase(Map map);

	/**
	 * 查询销售中心
	 * @return Map{PROV_NO:省的编号,PROV_NAME:省份名称}
	 */
	public List<Map> getRegionalSale();

	/**
	 *根据贷款编号查询应还多少钱(委外费用+实际还款金额)
	 * @param loanNo
	 * @return HashMap
	 */
	public HashMap<String,Object> getcurRvcAmtByLoanNo(Map<String,Object> param);
	/**
	 * 根据员工编号查询员工角色
	 * @param staffNo
	 * @return List<String>
	 */
	public List<String> selectRoleByStaffNo(String staffNo);
	List<LoanOutsourceBo> queryOutSourceForList(Map<String,Object> map);

	/**
	 * 根据贷款编号查询还款计划的费用和
	 * @param loanNo
	 * @return HashMap<String,Object>
	 */
	public HashMap<String,Object> getLoanPlanSumByLoanNo(String loanNo);
	public Integer updateIsSettle(Map<String, Object> outMap);
	
	
	/**
	 * 批量删除委外案件
	 * @param groupId
	 * @return
	 */
	public int batchDeleteByLoanNoLst(List<String> list);
	
	/**
	 * 批量更新逾期案件
	 * @param groupId
	 * @return
	 */
	public int batchModifyByLoanNoLst(List<String> list);
	
}