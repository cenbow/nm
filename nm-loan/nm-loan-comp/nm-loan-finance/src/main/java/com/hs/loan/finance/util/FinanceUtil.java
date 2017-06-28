package com.hs.loan.finance.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.hs.loan.finance.contant.FinanceConstant;
import com.hs.utils.DateUtils;

/**
 * 财务公共算法类
 * @author YXS
 *
 */
public class FinanceUtil {
	
	/**
	 * 校验预处理表状态
	 * 0正常
	 * -1数据不存在
	 * -2业务日期不对称
	 * -3锁表
	 * @param map
	 * @return
	 */
	public static BigDecimal checkStatus(Map<String,Object> map){
		
		BigDecimal reg = new BigDecimal(0);
		
		//如果未查出数据
		if(map == null){
			reg = new BigDecimal(-1);
			return reg;
		}
		
		//业务日期
		String busDate = String.valueOf(map.get("BUSN_DATE"));
		//业务日期T-1，只能查询业务日期产生的数据
		String busDateCop = DateUtils.calendar(new Date(), -1,"yyyyMMdd");
		if(!busDateCop.equals(busDate)){
			reg = new BigDecimal(-2);
			return reg;
		}
		
		//状态
		String stu = String.valueOf(map.get("INST_STAT"));
		//如果状态是锁定则返回 -3
		if(stu != null && stu.equals(FinanceConstant.PRETREAT_STAT_LOCK)){
			reg = new BigDecimal(-3);
			return reg;
		}
		//如果状态是结清则返回 -4
		if(stu != null && stu.equals(FinanceConstant.PRETREAT_STAT_CLEARD)){
			reg = new BigDecimal(-4);
			return reg;
		}
		
		return reg;
	}
}
