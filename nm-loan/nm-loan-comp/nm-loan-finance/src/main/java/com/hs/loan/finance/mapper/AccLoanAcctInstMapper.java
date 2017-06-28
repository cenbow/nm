package com.hs.loan.finance.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.finance.bo.AccLoanAcctInstBo;
import com.hs.loan.finance.entity.AccLoanAcctInst;
import com.hs.loan.finance.entity.AccRepayFlow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ACC_分期预处理 mapper
 * @author autocreate
 * @create 2016-02-03
 */
@MyBatisRepository
public interface  AccLoanAcctInstMapper extends BaseMapper<AccLoanAcctInst>{
	public int isLock(java.util.Map map);
	public int isOver(java.util.Map map);
	public int insertAppFeeAdjuReg(java.util.Map map);
	/**
	 * 查询变更次数
	 * @param map
	 * @return HashMap<String,Object>
	 */
	public  HashMap<String,Object> changeCount(java.util.Map map);
	
	public List<HashMap<String,Object>> flexibleNotYetDetail(java.util.Map map);
	/**
	 * 根据回盘文件dkid更新预处理状态
	 * @param params
	 * @return
	 */
	public Integer batchUpdateALAIStatByBatckDk(Map<String, Object> params);
	
	/**
	 * 根据贷款编号查询未结清的期数
	 * @param loanNo
	 * @return 
	 */
	public Integer queryUnClearTotalByLoanNo(String loanNo);
	
	/**
	 * 根据回盘文件dkid更新金额
	 * @param params
	 * @return
	 */
	public Integer batchUpdateCurAmtByBatckDk(Map<String, Object> params);
	
	/**
	 * 更新贷款预处理锁表状态 根据贷款编号和期数
	 * @param params
	 */
	public Integer updateAccLoanAccInstLockStatusByLoanNoAndRepayNum(Map<String, Object> params);
	
	/**
	 * 更新当日应还并更改当前预处理状态
	 * @param params
	 */
	public Integer updateAlaiCramtAndIns(Map<String, Object> params);
	
	
	/**
	 * 根据回盘文件dkid更新预处理状态和当日应还金额
	 * @param params
	 * @return
	 */
	public Integer batchUpdateALAIStatAndCurAmtByBatckDk(Map<String, Object> params);
	
	/**
	 * 批量插入流水 银联代扣回调
	 * @param list
	 * @return
	 */
	public Integer batckInsertAccLoanAcctInst(List<AccRepayFlow> list);
	
	/**
	 * 查询贷款预处理状态
	 * @param params
	 */
	public Map<String, Object> queryAccLoanAccInstLockStatusByLoanNoAndRepayNum(Map<String, Object> params);
	
	/**
	 * 资方扣款更新预处理表
	 * @param params
	 */
	public Integer updateFundAccLoanInst (Map<String, Object> params);
	
	
	
	/**
	 * 查询贷款预处理 根据贷款编号 还款期数
	 * @param params
	 */
	public Map<String,Object> queryAccLoanAcctInst(Map<String, Object> params);
	
	/**
	 * 提前结清获取全部金额
	 * @param params
	 */
	public Map<String,Object> queryAccLoanAcctInstSelt(Map<String, Object> params);
	
	/**
	 * 查询贷款预处理 根据贷款编号
	 * @param params
	 * @return list
	 */
	public List<Map<String,Object>> queryListAccLoanAcctInst(String loanNo);
	
	
	
	/**
	 * 查询贷款预处应还总金额 根据代扣编号 开始结束期数
	 * @param params
	 */
	public List<Map<String, Object>> queryAccLoanAcctAll(Map<String, Object> params);
	
	
	
	/**
	 * 更新预处理表金额为0
	 * @param params
	 */
	public Integer updateAccLoanInstAmtToZero (Map<String, Object> params);
	
	/**
	 * 更新预处理表金额为0
	 * @param params
	 */
	public Integer updateAccLoanInstCurRcvAmt (Map<String, Object> params);
	
	/**
	 * 根据贷款编号更新结清金额
	 * @param params
	 * @return
	 */
	public Integer updateAccLoanInstSetlAmt(Map<String, Object> params);

	/**
	 * 
	 * @param pageParams
	 */
	public List<AccLoanAcctInstBo> queryAccLoanAcctInstDg(Map<String, Object> pageParams);
	
	/**
	 * 批量更新 根据批量代扣查询条件批量锁定预处理表并更新预处理表更新时间
	 * @param params 
	 * @return
	 */
	public Integer batchUpdateAccLoanAcctInstByBatckDk (Map<String, Object> params);
	
	/**
	 * 批量更新 根据批量代扣查询条件批量更改代扣纪录为处理中并更新预处理表更新时间
	 * @param params 
	 * @return
	 */
	public Integer batchUpdateAccCapWithByBatckDk (Map<String, Object> params);
	
	/**更新当前应还金额并且解锁
	 */
	public Integer updateAccLoanInstCurRcvAmtAndUnLock (Map<String, Object> params);
	public Integer updateXtAlaiCramtAndIns(Map<String, Object> params);
}