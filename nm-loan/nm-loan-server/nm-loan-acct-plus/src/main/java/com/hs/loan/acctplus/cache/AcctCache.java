package com.hs.loan.acctplus.cache;

import java.util.HashMap;
import java.util.Map;

/** 
 * <li>ClassName:AcctCache <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2017年1月17日 <br/> 
 * <li>@author   zzy       
 */
public class AcctCache {
	
	private static Map<String,Boolean> blackBranchNoMap = new HashMap<String, Boolean>();
	
	public static void addBlackBranchCache(String branchNo,boolean b){
		blackBranchNoMap.put(branchNo, b);
	}
	
	public static Boolean getBlackBranchCache(String branchNo){
		
		return blackBranchNoMap.get(branchNo);
	}
	
	
}
