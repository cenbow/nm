package com.hs.loan.finance.service;

import com.hs.base.entity.Page;
import com.hs.base.exception.ServiceException;
import com.hs.loan.finance.bo.AccLoanAcctInstBo;
import com.hs.loan.finance.contant.FinanceConstant;
import com.hs.loan.finance.entity.AccLoanAcctInst;
import com.hs.loan.finance.enums.AcctType;
import com.hs.loan.finance.mapper.AccLoanAcctInstMapper;
import com.hs.loan.finance.util.FinanceUtil;
import com.hs.loan.finance.util.PayChanType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ACC_分期预处理 业务处理
 * @author autocreate
 * @create 2016-02-03
 */
@Service
@Transactional(readOnly=true)
public class  AccLoanAcctInstService{
	
	@Autowired
	private AccLoanAcctInstMapper accLoanAcctInstMapper;
	
	/**
	 * 根据回盘文件dkid更新预处理状态
	 * @param params
	 * @return
	 */
	public Integer batchUpdateALAIStatByBatckDk(Map<String, Object> params)throws ServiceException{
		return accLoanAcctInstMapper.batchUpdateALAIStatByBatckDk(params);
	}
	
	/**
	 * 根据回盘文件dkid更新金额
	 * @param params
	 * @return
	 */
	public Integer batchUpdateCurAmtByBatckDk(Map<String, Object> params)throws ServiceException{
		return accLoanAcctInstMapper.batchUpdateCurAmtByBatckDk(params);
	}
	
	/**
	 * 根据回盘文件dkid更新预处理状态和当日应还金额
	 * @param params
	 * @return
	 */
	public Integer batchUpdateALAIStatAndCurAmtByBatckDk(Map<String, Object> params)throws ServiceException{
		return accLoanAcctInstMapper.batchUpdateALAIStatAndCurAmtByBatckDk(params);
	}
	
	public Integer queryUnClearTotalByLoanNo(String loanNo)throws ServiceException{
		return accLoanAcctInstMapper.queryUnClearTotalByLoanNo(loanNo);
	}
	
	/**
	 * 
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public String updateAlaiCramtAndIns(Map<String, Object> params)throws ServiceException{
		return accLoanAcctInstMapper.updateAlaiCramtAndIns(params) > 0 ? FinanceConstant.SUCC : FinanceConstant.FILED;
	}
	
	
	/**
	 * 更新信托当日金额
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public String updateXtAlaiCramtAndIns(Map<String, Object> params)throws ServiceException{
		return accLoanAcctInstMapper.updateXtAlaiCramtAndIns(params) > 0 ? FinanceConstant.SUCC : FinanceConstant.FILED;
	}
	
	
	
	
	/**
	 * 新增 ACC_分期预处理
	 * @param accLoanAcctInst 新增对象
	 */
	@Transactional
	public void insert(AccLoanAcctInst accLoanAcctInst){
		accLoanAcctInstMapper.insert(accLoanAcctInst);
	}

	/**
	 * 通过主键修改 ACC_分期预处理
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		accLoanAcctInstMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 ACC_分期预处理
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		accLoanAcctInstMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 ACC_分期预处理 对象
	 * @param primaryKey 主键
	 * @return ACC_分期预处理对象
	 */
	public AccLoanAcctInst getByPrimaryKey(String primaryKey){
		return accLoanAcctInstMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 ACC_分期预处理 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AccLoanAcctInst> queryForList(Map<String, Object> param){
		return accLoanAcctInstMapper.queryForList(param);
	}
	
	/**
	 * 查询 ACC_分期预处理 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AccLoanAcctInst> queryForPage(Page<AccLoanAcctInst> page){
		accLoanAcctInstMapper.queryForList(page.getPageParams());
		return (Page<AccLoanAcctInst>)page.getPageParams().get(Page.KEY);
	}
	public int insertAppFeeAdjuReg(java.util.Map map){
		return accLoanAcctInstMapper.insertAppFeeAdjuReg(map);
	}
	public int isLock(java.util.Map map){
		return accLoanAcctInstMapper.isLock(map);
	}
	public int isOver(java.util.Map map){
		return accLoanAcctInstMapper.isOver(map);
	}
	/**
	 * 查询变更次数
	 * @param map
	 * @return HashMap<String,Object>
	 */
	public  HashMap<String,Object> changeCount(java.util.Map map){
		return accLoanAcctInstMapper.changeCount(map);
	}
	public Page<HashMap<String,Object>> flexibleNotYetDetail(Page<HashMap<String,Object>> page){
		Map<String, Object> pageParams = page.getPageParams();
		accLoanAcctInstMapper.flexibleNotYetDetail(pageParams);
		return (Page<HashMap<String,Object>>)page.getPageParams().get(Page.KEY);
	}
	/**
	 * 查询 ACC_分期预处理 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public List<AccLoanAcctInstBo> queryAccLoanAcctInstDg(Map<String,Object> map){
		return accLoanAcctInstMapper.queryAccLoanAcctInstDg(map);
	}
	
	
	
	/**
	 * 计算当日提前结清总金额
	 * -1数据不存在
	 * -2业务日期不对称
	 * -3锁表
	 * -4结清
	 * @param loanNo
	 * @param repayNum
	 * @return
	 * @throws ServiceException 
	 */
	public BigDecimal compCurrSetAmtAll(String loanNo, int repayNum) throws ServiceException{
		Map<String,Object> map = getAccInst(loanNo, repayNum);
		//当日提前结清总金额
		BigDecimal compSetlAmt = (BigDecimal) map.get(AcctType.SETL_AMT.toString());
		return compSetlAmt;
	}
	
	
	
	
	/**
	 * 根据代款编号还款期数查询预处理信息
	 * @param paramMap
	 * @return
	 * @throws ServiceException
	 */
	public Map<String,Object> getAccInst(String loanNo,int repayNum)throws ServiceException{
		Map <String, Object>paramMap = new HashMap<String, Object>();
		paramMap.put("loanNo", loanNo);
		paramMap.put("repayNum", repayNum);
		return accLoanAcctInstMapper.queryAccLoanAcctInst(paramMap);
	}
	
	/**
	 * 资方扣款更新预处理表
	 * @param loanNo
	 * @param list
	 * @param repayNum
	 * @param FUND_CURR_SHL_AMT
	 * @return
	 * @throws ServiceException
	 */
	public String updateFundcurrAmt(String loanNo, int repayNum, BigDecimal FUND_CUR_RCV_AMT) throws ServiceException{
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("loanNo", loanNo);
		paramMap.put("repayNum", repayNum);
		paramMap.put(AcctType.CUR_RCV_AMT.toString(), FUND_CUR_RCV_AMT);
		paramMap.put(AcctType.FUND_CUR_RCV_AMT.toString(), FUND_CUR_RCV_AMT);
		paramMap.put("updt", new Date());
		
		return accLoanAcctInstMapper.updateFundAccLoanInst(paramMap) > 0 ? FinanceConstant.SUCC : FinanceConstant.FILED;
	}
	
	/**
	 * 更新预处理表当日应还金额
	 * @param paramMap loanNo 贷款编号 repayNum 还款期数 curRcvAmt 金额
	 * @return
	 */
	@Transactional
	public String updateAccLoanInstCurRcvAmt(Map<String,Object> paramMap){
		return accLoanAcctInstMapper.updateAccLoanInstCurRcvAmt(paramMap) > 0 ? FinanceConstant.SUCC : FinanceConstant.FILED;
	}
	/**
	 * 更新预处理表当日应还金额
	 * @param paramMap loanNo 贷款编号 repayNum 还款期数 curRcvAmt 金额
	 * @return
	 */
	@Transactional
	public String updateAccLoanInstCurRcvAmtAndUnlock(Map<String,Object> paramMap){
		return accLoanAcctInstMapper.updateAccLoanInstCurRcvAmt(paramMap) > 0 ? FinanceConstant.SUCC : FinanceConstant.FILED;
	}
	
	
	/**
	 * 更新预处理表结清金额
	 * @param paramMap loanNo 贷款编 curRcvAmt 金额
	 * @return
	 */
	@Transactional
	public String updateAccLoanInstSetlAmt(Map<String,Object> paramMap){
		return accLoanAcctInstMapper.updateAccLoanInstSetlAmt(paramMap) > 0 ? FinanceConstant.SUCC : FinanceConstant.FILED;
	}
	
	
	
	
	/**
	 * 更改预处理表状态
	 * @param loanNo
	 * @param repayNum
	 * @param stat
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public String updateAccLoanAcctStat(String loanNo, int repayNum, String stat)throws ServiceException{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("loanNo", loanNo);
		paramMap.put("instStat", stat);
		paramMap.put("updt", new Date());
		if(repayNum > 0){
			paramMap.put("repayNum", repayNum);
		}
		return accLoanAcctInstMapper.updateAccLoanAccInstLockStatusByLoanNoAndRepayNum(paramMap) > 0 ? FinanceConstant.SUCC : FinanceConstant.FILED;
	}
	
	@Transactional
	public String updateAccLoanAcctStatNotREQUIRES_NEW(String loanNo, int repayNum, String stat)throws ServiceException{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("loanNo", loanNo);
		paramMap.put("instStat", stat);
		paramMap.put("updt", new Date());
		if(repayNum > 0){
			paramMap.put("repayNum", repayNum);
		}
		return accLoanAcctInstMapper.updateAccLoanAccInstLockStatusByLoanNoAndRepayNum(paramMap) > 0 ? FinanceConstant.SUCC : FinanceConstant.FILED;
	}
	
	/**
	 * 预处理表锁定
	 * @param loanNo
	 * @param repayNum
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public String updateAccLoanAcctStatusLock(String loanNo, int repayNum)throws ServiceException{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("loanNo", loanNo);
		paramMap.put("repayNum", repayNum);
		paramMap.put("updt", new Date());
		//锁定状态
		paramMap.put("instStat", FinanceConstant.PRETREAT_STAT_LOCK);
		return accLoanAcctInstMapper.updateAccLoanAccInstLockStatusByLoanNoAndRepayNum(paramMap) > 0 ? FinanceConstant.SUCC : FinanceConstant.FILED;
	}
	
	/**
	 * 预处理表解锁
	 * @param loanNo
	 * @param repayNum
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public String updateAccLoanAcctStatusUnLock(String loanNo, int repayNum)throws ServiceException{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("loanNo", loanNo);
		paramMap.put("repayNum", repayNum);
		paramMap.put("updt", new Date());
		//解锁状态
		paramMap.put("instStat", FinanceConstant.PRETREAT_STAT_UNLOCK);
		
		return accLoanAcctInstMapper.updateAccLoanAccInstLockStatusByLoanNoAndRepayNum(paramMap) > 0 ? FinanceConstant.SUCC : FinanceConstant.FILED;
	}
	
	/**
	 * 查询预处理表是否锁定
	 * @param loanNo
	 * @param repayNum
	 * @return  BigDecimal
	 *  		0正常
	 * 			-1数据不存在
	 * 			-2业务日期不对称
	 * 			-3锁表
	 * @throws ServiceException
	 */
	public BigDecimal queryAccLoanAcctStatus(String loanNo, int repayNum)throws ServiceException{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("loanNo", loanNo);
		paramMap.put("repayNum", repayNum);
		Map<String, Object> map = accLoanAcctInstMapper.queryAccLoanAccInstLockStatusByLoanNoAndRepayNum(paramMap);
		//校验结果
		BigDecimal checkReg = FinanceUtil.checkStatus(map);
		
		return checkReg;
	}
	
	/**
	 * 查询预处理表是否锁定
	 * @param loanNo
	 * @param repayNum
	 * @return  BigDecimal
	 *  		0正常
	 * 			-1数据不存在
	 * 			-2业务日期不对称
	 * 			-3锁表
	 * @throws ServiceException
	 */
	public BigDecimal queryAccLoanAcctStatus(String loanNo)throws ServiceException{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("loanNo", loanNo);
		Map<String, Object> map = accLoanAcctInstMapper.queryAccLoanAccInstLockStatusByLoanNoAndRepayNum(paramMap);
		//校验结果
		BigDecimal checkReg = FinanceUtil.checkStatus(map);
		
		return checkReg;
	}
	
	/**
	 * 计算当期时点应还金额
	 * -1数据不存在
	 * -2业务日期不对称
	 * -3锁表
	 * -4结清
	 * @param loanNo
	 * @param repayNum
	 * @return
	 * @throws ServiceException 
	 */
	public BigDecimal compCurrAmt(String loanNo, int repayNum) throws ServiceException{
		Map<String,Object> map = getAccInst(loanNo, repayNum);
		
		//校验数据
		BigDecimal checkReg = FinanceUtil.checkStatus(map);
		if(checkReg.compareTo(new BigDecimal(0)) == 0){
			return (BigDecimal) map.get(AcctType.CUR_RCV_AMT.toString());
		}else{
			return checkReg;
		}
		
	}
	
	/**
	 * 批量回调不验证表情况
	 * @param loanNo
	 * @param repayNum
	 * @return
	 * @throws ServiceException
	 */
	public BigDecimal batchCallBackCompCurrAmt(String loanNo, int repayNum) throws ServiceException{
		Map<String,Object> map = getAccInst(loanNo, repayNum);
		BigDecimal reg = new BigDecimal(-3);
		String stu = String.valueOf(map.get("INST_STAT"));
		//如果状态是锁定则返回 当日应还金额
		if(stu != null && stu.equals(FinanceConstant.PRETREAT_STAT_LOCK)){
			reg = (BigDecimal) map.get(AcctType.CUR_RCV_AMT.toString());
		}
		return reg;
	}
	
	/**
	 * 计算当期资方时点应还金额
	 * -1数据不存在
	 * -2业务日期不对称
	 * -3锁表
	 * -4结清
	 * @param loanNo
	 * @param repayNum
	 * @return
	 * @throws ServiceException 
	 */
	public BigDecimal compCurrFunAmt(String loanNo, int repayNum) throws ServiceException{
		Map<String,Object> map = getAccInst(loanNo, repayNum);

		//校验数据
		BigDecimal checkReg = FinanceUtil.checkStatus(map);
		if(checkReg.compareTo(new BigDecimal(0)) == 0){
			return (BigDecimal)map.get(AcctType.FUND_CUR_RCV_AMT.toString());
		}else{
			return checkReg;
		}
	}
	
	/**
	 * 根据批量代扣查询条件批量锁定预处理表并更新预处理表更新时间
	 * 批量代扣为解决事务问题，特将修改代扣表方法移动到此处解决事务没有提交的问题
	 * @param loanNo
	 * @param repayNum
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Integer[] batchUpdateAccLoanAcctInstByBatckDk(Map<String, Object> params)throws ServiceException{
		if ("1".equals(params.get("dkType"))) {
			params.put("ovduStatNo", "1");
			params.put("ovduStatYes","");
		}else if ("2".equals(params.get("dkType"))) {
			params.put("ovduStatNo", "");
			params.put("ovduStatYes","1");
		}else if ("3".equals(params.get("dkType"))) {
			params.put("ovduStatNo", "");
			params.put("ovduStatYes","2");
		}
		if (PayChanType.LIANLIANPAY.toString().equals(params.get("exportTxtType"))) {
			params.put("lianlianpay","1");
		}
		Integer[] obj = new Integer[2];
		obj[0] = accLoanAcctInstMapper.batchUpdateAccLoanAcctInstByBatckDk(params);
		obj[1] = accLoanAcctInstMapper.batchUpdateAccCapWithByBatckDk(params);
		return obj;
	}

	/**
	 *  对公还款，费用减免等
	 *  更新当前还款金额并且解锁
	 * @param accAmtMap
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Integer updateAccLoanInstCurRcvAmtAndUnLock(Map<String, Object> accAmtMap) {
		return accLoanAcctInstMapper.updateAccLoanInstCurRcvAmtAndUnLock(accAmtMap); 
		
	}
	@Transactional
	public Integer updateAccLoanInstCurRcvAmtAndUnLockNotREQUIRES_NEW(Map<String, Object> accAmtMap) {
		return accLoanAcctInstMapper.updateAccLoanInstCurRcvAmtAndUnLock(accAmtMap); 
		
	}
	
}