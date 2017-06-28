package com.hs.loan.produce.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.hs.loan.produce.common.AuditingStat;
import com.hs.loan.produce.common.MqCache;
import com.hs.loan.produce.dao.AppMqStatDao;

/** 
 * <li>ClassName:AppLoanApprDaoImpl <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年11月7日 <br/> 
 * <li>@author   zzy       
 */
@Repository
public class AppMqStatDaoImpl implements AppMqStatDao{

	@Autowired
	NamedParameterJdbcTemplate  jdbcTemplate;
	
	@Override
	public List<Map<String, Object>> getAll() {
		
		Map<String,Object> map = new HashMap<String, Object>();
		String sql = "SELECT BUSINESS_TYPE,BUSINESS_KEY,LOAN_STAT,INSERT_DATE FROM app_mq_stat;";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, map);
		return list;
	}

	@Override
	public void batchInsertAppr(List<String> result) {
		if(result.size()==0){
			return;
		}
		Date date = new Date();
		Map<String,Object>[] mm = new Map[result.size()];
		for(int i=0;i<result.size();i++){
			mm[i] = new HashMap<String, Object>();
			mm[i].put("id", UUID.randomUUID().toString().replaceAll("-", ""));
			mm[i].put("type", AuditingStat.DATA_TYPE_APPROVE);
			mm[i].put("key", result.get(i));
			mm[i].put("date", date);
		}
		String sql = "INSERT INTO app_mq_stat(id,BUSINESS_TYPE,BUSINESS_KEY,INSERT_DATE) VALUES(:id,:type,:key,:date);";
		jdbcTemplate.batchUpdate(sql,mm);
	}

	@Override
	public void batchInsertAcct(List<Map<String,Object>> result) {
		if(result.size()==0){
			return;
		}
		Date date = new Date();
		Map<String,Object>[] mm = new Map[result.size()];
		for(int i=0;i<result.size();i++){
			Map<String,Object> ap = result.get(i);
			mm[i] = new HashMap<String, Object>();
			mm[i].put("id", UUID.randomUUID().toString().replaceAll("-", ""));
			mm[i].put("type", AuditingStat.DATA_TYPE_PAYMENT);
			mm[i].put("key", ap.get("LOAN_NO"));
			mm[i].put("branchNo", ap.get("BRANCH_NO"));
			mm[i].put("amt", ap.get("LOAN_AMT"));
			mm[i].put("distrDate", ap.get("DISTR_DATE"));
			mm[i].put("loanStat", ap.get("LOAN_STAT"));
			
			mm[i].put("date", date);
		}
		String sql = "INSERT INTO app_mq_stat(id,BUSINESS_TYPE,BUSINESS_KEY,BRANCH_NO,LOAN_AMT,DISTR_DATE,LOAN_STAT,INSERT_DATE) VALUES(:id,:type,:key,:branchNo,:amt,:distrDate,:loanStat,:date);";
		jdbcTemplate.batchUpdate(sql,mm);
		
	}

	@Override
	public void clearHistoryDate() {
		String sql ="DELETE FROM app_mq_stat WHERE INSERT_DATE<:data;";
		Map<String,String> map = new HashMap<String, String>();
		Calendar ca = Calendar.getInstance(); 
		ca.add(Calendar.DAY_OF_MONTH, -MqCache.clearDay);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String data = sdf.format(ca.getTime());
		System.out.println("data=="+data);
		map.put("data", data);
		int num = jdbcTemplate.update(sql, map);
		System.out.println("del return "+num);
	}

	@Override
	public void batchInsertAccCap(List<Map<String, Object>> result) {
		
		if(result.size()==0){
			return;
		}
		Date date = new Date();
		Map<String,Object>[] mm = new Map[result.size()];
		for(int i=0;i<result.size();i++){
			Map<String,Object> ap = result.get(i);
			mm[i] = new HashMap<String, Object>();
			mm[i].put("id", UUID.randomUUID().toString().replaceAll("-", ""));
			mm[i].put("type", AuditingStat.DATA_TYPE_CUTPAYMENT);
			mm[i].put("key", ap.get("LOAN_NO"));
			mm[i].put("date", date);
			
			mm[i].put("repayDate", ap.get("repayDate"));
			mm[i].put("repayNumber", ap.get("RCV_TOTL_AMT"));
			mm[i].put("cutTime", ap.get("cutTime"));
			mm[i].put("cutNumber", ap.get("TRAN_AMT"));
		}
		String sql = "INSERT INTO app_mq_stat(id,BUSINESS_TYPE,BUSINESS_KEY,INSERT_DATE,REPAY_DATE,REPAY_NUMBER,CUT_TIME,CUT_NUMBER) VALUES(:id,:type,:key,:date,:repayDate,:repayNumber,:cutTime,:cutNumber);";
		jdbcTemplate.batchUpdate(sql,mm);
	}
}
