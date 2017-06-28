package com.hs.loan.produce.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hs.loan.produce.dao.AppMqStatDao;

/** 
 * <li>ClassName:MqCache <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年11月8日 <br/> 
 * <li>@author   zzy       
 */
@Component
public class MqCache {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired AppMqStatDao appMqStatDao;
	
	private static ConcurrentHashMap<String,Long> apprMap = new ConcurrentHashMap<String, Long>();
	private static ConcurrentHashMap<String,Long> acctMap = new ConcurrentHashMap<String, Long>();
	private static ConcurrentHashMap<String,Long> accCapMap = new ConcurrentHashMap<String, Long>();
	
	static MqCache mqCache = null;
	
	public static final int clearDay = 2;
	
	private MqCache() {}
	
	@PostConstruct
	private void init(){
		mqCache = this;
	}
	
	public static MqCache getInstance(){
		return mqCache;
	}
	
	private void clear(){
		apprMap.clear();
		acctMap.clear();
		accCapMap.clear();
	}
	
	/** 
	 * <li>@Description:去除重复的数据
	 * <li>@param list
	 * <li>@return
	 * <li>创建人：曾志远
	 * <li>创建时间：2016年11月8日
	 * <li>修改人：
	 * <li>修改时间：
	 */  
	public static List<Map<String, Object>> distinctApprMsg(List<Map<String, Object>> list){
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, Object> map = (Map<String, Object>) iterator.next();
			if(apprMap.containsKey(map.get("LOAN_NO").toString())){
				iterator.remove();
			}
		}
		
		return list;
	}
	
	public static List<Map<String, Object>> distinctAcctMsg(List<Map<String, Object>> list){
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, Object> map = (Map<String, Object>) iterator.next();
			String loan = map.get("LOAN_NO").toString();
			String stat = map.get("LOAN_STAT").toString();
			if(acctMap.containsKey(loan+"_"+stat)){
				//LOAN_NO+状态才能判断
				iterator.remove();
			}
		}
		
		return list;
	}
	
	public static List<Map<String, Object>> distinctAccCapMsg(List<Map<String, Object>> list){
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, Object> map = (Map<String, Object>) iterator.next();
			String loan = map.get("LOAN_NO").toString();
			if(accCapMap.containsKey(loan)){
				iterator.remove();
			}
		}
		
		return list;
	}
	
	
	public static void addSendedAppr(String key,long insertTimeMillis){
		apprMap.put(key, insertTimeMillis);
	}
	
	public static void addSendedAcct(String key,long insertTimeMillis){
		acctMap.put(key, insertTimeMillis);
	}
	
	public static void addSendedAccCap(String key,long insertTimeMillis){
		accCapMap.put(key, insertTimeMillis);
	}
	
	/** 
	 * <li>@Description:初始化缓存
	 * <li>
	 * <li>创建人：曾志远
	 * <li>创建时间：2016年11月15日
	 * <li>修改人：
	 * <li>修改时间：
	 */  
	public void initCache(){
		log.info("MqCache.init.......");
		System.out.println("MqCache.init.......");
		//清除缓存
		clear();
		List<Map<String, Object>> list = appMqStatDao.getAll();
		for(Map<String, Object> m:list){
			String type = m.get("BUSINESS_TYPE").toString();
			String key = m.get("BUSINESS_KEY").toString();
			Object stat = m.get("LOAN_STAT");
			Date d = (Date) m.get("INSERT_DATE");
			if(AuditingStat.DATA_TYPE_APPROVE.equals(type)){
				addSendedAppr(key,d.getTime());
			}else if(AuditingStat.DATA_TYPE_PAYMENT.equals(type)){
				if(stat!=null){
					addSendedAcct(key+"_"+stat.toString(),d.getTime());
				}
				
			}else if(AuditingStat.DATA_TYPE_CUTPAYMENT.equals(type)){
				addSendedAccCap(key,d.getTime());
			}
		}
		log.info("MqCache.init.apprMap.size="+apprMap.size());
		log.info("MqCache.init.acctMap.size="+acctMap.size());
		System.out.println("MqCache.init.apprMap.size="+apprMap.size());
		System.out.println("MqCache.init.acctMap.size="+acctMap.size());
		System.out.println("MqCache.init.accCapMap.size="+accCapMap.size());
	}
	
	public static void cleanHistoryCache(){
		Calendar ca = Calendar.getInstance(); 
		ca.add(Calendar.DAY_OF_MONTH, -clearDay);
		long history = ca.getTimeInMillis();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String data = sdf.format(history);
		System.out.println("data=="+data);
		
		
		List<String> apprlist = new ArrayList<String>();
		List<String> acctlist = new ArrayList<String>();
		List<String> accCaplist = new ArrayList<String>();
		for(Entry<String, Long> en:apprMap.entrySet()){
			if(en.getValue().longValue()<history){
				apprlist.add(en.getKey());
			}
		}
		for(Entry<String, Long> en:acctMap.entrySet()){
			if(en.getValue().longValue()<history){
				acctlist.add(en.getKey());
			}
		}
		for(Entry<String, Long> en:accCapMap.entrySet()){
			if(en.getValue().longValue()<history){
				accCaplist.add(en.getKey());
			}
		}
		System.out.println("apprMap del before"+apprMap.size());
		for(String s:apprlist){
			apprMap.remove(s);
		}
		System.out.println("apprMap del after"+apprMap.size());
		
		System.out.println("acctMap del before"+acctMap.size());
		for(String s:acctlist){
			acctMap.remove(s);
		}
		System.out.println("acctMap del after"+acctMap.size());
		for(String s:accCaplist){
			accCapMap.remove(s);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		for(int i=0;i<100;i++){
			apprMap.put(i+"", System.currentTimeMillis()-(2*24*60*60*1000)+1000);
			acctMap.put(i+"-", System.currentTimeMillis()-(2*24*60*60*1000)+1000);
		}
//		Thread.sleep(1);
		MqCache.cleanHistoryCache();
		System.out.println(apprMap.size());
		System.out.println(acctMap.size());
		
	}
}
