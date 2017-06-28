package com.hs.loan.produce.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import scala.collection.mutable.StringBuilder;

import com.hs.loan.produce.common.AuditingStat;
import com.hs.loan.produce.dao.AppLoanApprDao;
import com.hs.utils.DateUtils;

/** 
 * <li>ClassName:AppLoanApprDaoImpl <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年11月7日 <br/> 
 * <li>@author   zzy       
 */
@Repository
public class AppLoanApprDaoImpl implements AppLoanApprDao{

	@Autowired
	NamedParameterJdbcTemplate  jdbcTemplate;
	
	Logger log = Logger.getLogger(AppLoanApprDaoImpl.class);
	
	@Override
	public List<Map<String, Object>> getApprAll() {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		String stat = AuditingStat.apprStatelist.toString().replace("[", "").replace("]", "");
		map.put("saleNo", AuditingStat.STAFF_NO);
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT LOAN_NO,STAT,APPR_DESC FROM app_loan_appr  WHERE SALE_NO=:saleNo AND STAT IN(");
		sb.append(stat);
		sb.append(")");
		System.out.println("query sql="+sb.toString());
		
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sb.toString(), map);
		return list;
		
	}

	@Override
	public List<Map<String, Object>> getAcctAll() {
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("saleNo", AuditingStat.STAFF_NO);
		
		String stat = AuditingStat.acctStatelist.toString().replace("[", "").replace("]", "");
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT a.LOAN_NO,b.BRANCH_NO,a.LOAN_AMT,a.DISTR_DATE,a.LOAN_STAT FROM app_loan_acct a INNER JOIN app_loan_branch b "); 
		sb.append("INNER JOIN app_loan_saler c ON a.LOAN_NO=b.LOAN_NO AND c.LOAN_NO=a.LOAN_NO ");
		sb.append("WHERE a.LOAN_STAT in(");
		sb.append(stat);
		sb.append(") AND c.STAFF_NO=:saleNo ;");
		System.out.println("query sql="+sb.toString());
		
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sb.toString(), map);
		return list;
	}

	@Override
	public List<Map<String, Object>> getKouKuanAll() {
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -1);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("saleNo", AuditingStat.STAFF_NO);
		map.put("start1", sf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_YEAR,1);
		map.put("end1",sf.format(cal.getTime())+" 23:59:59");
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT A.LOAN_NO,B.REPAY_DATE AS repayDate, B.RCV_TOTL_AMT,A.TRAN_DATE AS cutTime,A.TRAN_AMT ");
		sb.append(" FROM (SELECT LOAN_NO,REPAY_DATE,TRAN_DATE,TRAN_AMT FROM ACC_REPAY_FLOW ");
		sb.append(" WHERE LOAN_NO IN (SELECT LOAN_NO FROM APP_LOAN_SALER WHERE STAFF_NO=:saleNo) ");
		sb.append(" AND INST_DATE >=:start1 AND INST_DATE <=:end1 ) A  ");
		sb.append(" INNER JOIN ACC_LOAN_INST B ON A.LOAN_NO = B.LOAN_NO AND A.REPAY_DATE= B.REPAY_DATE; ");
		System.out.println("query sql="+sb.toString());
		
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sb.toString(), map);
		return list;
		
	}

	@Override
	public List<Map<String, Object>> getAccCapWithAll2() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public static void main(String[] args) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -1);
		String date = sf.format(cal.getTime());
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("saleNo", AuditingStat.STAFF_NO);
		map.put("start1", date);
		cal.add(Calendar.DAY_OF_YEAR,1);
		String date2 = sf.format(cal.getTime());
		map.put("end1",date2+" 23:59:59");
		
		System.out.println(map.toString());
	}

}
