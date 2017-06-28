package com.hs.loan.sale.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.sale.entity.AppLoanAtt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * APP_分期附件表 mapper
 * @author autocreate
 * @create 2015-11-11
 */
@MyBatisRepository
public interface  AppLoanAttMapper extends BaseMapper<AppLoanAtt>{
	/**
	 * 获取产品渠道
	 * @param map{loanNo:贷款编号}
	 * @return Map{SALE_CHAN:产品销售渠道}
	 */
	public Map getLoanProd(java.util.Map map);
	public List<HashMap<String,Object>> getAttByLoanNo(java.util.Map map);

	void deleteByLoanAndAttNo(Map<String,String> map);

	AppLoanAtt getByPrimaryKey(Map<String,String> map);

	/**
	 * 根据附件码组获取贷款附件
	 * @param map{loanNo: 贷款编号, codTyp: 码类, groupCod: 码组}
	 * @return
	 */
	List<AppLoanAtt> queryLoanAttByGroupCod(Map<String,Object> map);
}