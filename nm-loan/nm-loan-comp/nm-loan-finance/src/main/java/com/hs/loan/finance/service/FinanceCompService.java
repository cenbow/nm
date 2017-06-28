package com.hs.loan.finance.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.exception.ServiceException;
import com.hs.loan.finance.enums.AcctType;
import com.hs.loan.finance.mapper.AccLoanAcctInstMapper;
import com.hs.loan.finance.util.FinanceUtil;

/**
 * 还款费用计算
 * @author ZYM
 *
 */
@Service
@Transactional(readOnly=true)
public class FinanceCompService{
	
	
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
		Map<String,Object> map = getAcctInst(loanNo, repayNum);
		
		//校验数据
		BigDecimal checkReg = FinanceUtil.checkStatus(map);
		if(checkReg.compareTo(new BigDecimal(0)) == 0){
			if(new BigDecimal(0).compareTo((BigDecimal) map.get(AcctType.CUR_RCV_AMT.toString())) == 1 || new BigDecimal(0).compareTo((BigDecimal) map.get(AcctType.CUR_RCV_AMT.toString()))==0){
				throw new ServiceException("本期金额已还清");
			}
			return (BigDecimal) map.get(AcctType.CUR_RCV_AMT.toString());
		}else{
			return checkReg;
		}
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
		Map<String,Object> map = getAcctInst(loanNo, repayNum);

		//校验数据
		BigDecimal checkReg = FinanceUtil.checkStatus(map);
		if(checkReg.compareTo(new BigDecimal(0)) == 0){
			return (BigDecimal)map.get(AcctType.FUND_CUR_RCV_AMT.toString());
		}else{
			return checkReg;
		}
	}
	
	/**
	 * 计算当期平台时点应还金额
	 * -1数据不存在
	 * -2业务日期不对称
	 * -3锁表
	 * -4结清
	 * @param loanNo
	 * @param repayNum
	 * @return
	 * @throws ServiceException 
	 */
	public BigDecimal compCurrPloAmt(String loanNo, int repayNum) throws ServiceException{
		BigDecimal compCurrPloAmt = new BigDecimal(0);
		
		Map<String,Object> map = getAcctInst(loanNo, repayNum);
		
		//校验数据
		BigDecimal checkReg = FinanceUtil.checkStatus(map);
		if(checkReg.compareTo(new BigDecimal(0)) == 0){
			//当前应还总金额
			BigDecimal compCurrAmt = (BigDecimal)map.get(AcctType.CUR_RCV_AMT.toString());
			//当前资方应还总额
			BigDecimal compCurrFunAmt = (BigDecimal) map.get(AcctType.FUND_CUR_RCV_AMT.toString());
			
			compCurrPloAmt = compCurrAmt.subtract(compCurrFunAmt);
			
			return compCurrPloAmt;
		}else{
			return checkReg;
		}
	}
	
	/**
	 * 查询当日应还金额
	 * -1数据不存在
	 * -2业务日期不对称
	 * -3锁表
	 * -4结清
	 * @param loanNo
	 * @param repayNum
	 * @return
	 * @throws ServiceException 
	 */
	public BigDecimal compDayAmt(String loanNo, int repayNum) throws ServiceException{
		
		Map<String,Object> map = getAcctInst(loanNo, repayNum);
		
		//校验数据
		BigDecimal checkReg = FinanceUtil.checkStatus(map);
		//排除锁表的校验
		if(checkReg.compareTo(new BigDecimal(0)) == 0 || checkReg.compareTo(new BigDecimal(-3)) == 0){
			//当日应还总金额
			BigDecimal compDayAmt = (BigDecimal)map.get(AcctType.DAY_RCV_AMT.toString());
			
			return compDayAmt;
		}else{
			return checkReg;
		}
	}
	
	/**
	 * 计算提前结清时点应还金额
	 * -1数据不存在
	 * -2业务日期不对称
	 * -3锁表
	 * -4结清
	 * @param loanNo
	 * @param repayNum
	 * @return
	 * @throws ServiceException 
	 */
	public BigDecimal compCurrSetAmt(String loanNo, int repayNum) throws ServiceException{
		BigDecimal compCurrSetAmt = new BigDecimal(0);
		
		Map<String,Object> map = queryAccLoanAcctInstSelt(loanNo, repayNum);
		
		//校验数据
		BigDecimal checkReg = FinanceUtil.checkStatus(map);
		if(checkReg.compareTo(new BigDecimal(0)) == 0){
			//当前应还总金额
			BigDecimal compCurrAmt = (BigDecimal) map.get(AcctType.CUR_RCV_AMT.toString());
			//当日应还总金额
			BigDecimal compDayAmt = (BigDecimal) map.get(AcctType.DAY_RCV_AMT.toString());
			//当日提前结清总金额
			BigDecimal compSetlAmt = (BigDecimal) map.get(AcctType.SETL_AMT.toString());
			//当日已还金额
			BigDecimal compCurrRepayAmt = compDayAmt.subtract(compCurrAmt);
			compCurrSetAmt = compSetlAmt.subtract(compCurrRepayAmt);
			return compCurrSetAmt;
		}else{
			return checkReg;
		}
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
	/*@Deprecated
	public BigDecimal compCurrSetAmtAll(String loanNo, int repayNum) throws ServiceException{
		
		Map<String,Object> map = getAcctInst(loanNo, repayNum);
		
		//当日提前结清总金额
		BigDecimal compSetlAmt = (BigDecimal) map.get(AcctType.SETL_AMT.toString());
		return compSetlAmt;
	}*/
	
	@Autowired
	private AccLoanAcctInstMapper accLoanAcctInstMapper;

	
	/**
	 * 查询预处理信息
	 * @param loanNo
	 * @param repayNum
	 * @return
	 * @throws ServiceException
	 */
	private Map<String,Object> getAcctInst(String loanNo, int repayNum) throws ServiceException{
		Map<String,Object> map = new HashMap<String,Object>();
		
		Map paramMap = new HashMap();
		paramMap.put("loanNo", loanNo);
		paramMap.put("repayNum", repayNum);
		map = (Map<String, Object>)accLoanAcctInstMapper.queryAccLoanAcctInst(paramMap);
		return map;
	}
	/**
	 * 查询预处理信息 获取提前结清信息
	 * @param loanNo
	 * @param repayNum
	 * @return
	 * @throws ServiceException
	 */
	private Map<String,Object> queryAccLoanAcctInstSelt(String loanNo, int repayNum) throws ServiceException{
		Map<String,Object> map = new HashMap<String,Object>();
		
		Map paramMap = new HashMap();
		paramMap.put("loanNo", loanNo);
	//	paramMap.put("repayNum", repayNum);
		map = (Map<String, Object>)accLoanAcctInstMapper.queryAccLoanAcctInstSelt(paramMap);
		return map;
	}
	
	/**
	 * 查询预处理信息
	 * @param loanNo
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String,Object>> getAcctInstAll(String loanNo) throws ServiceException{
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map paramMap = new HashMap();
		paramMap.put("loanNo", loanNo);
		list = (List<Map<String,Object>>)accLoanAcctInstMapper.queryListAccLoanAcctInst(loanNo);
		return list;
	}

	/**
	 * 计算当期时点应还总金额
	 * -1数据不存在
	 * -2业务日期不对称
	 * -3锁表
	 * -4结清
	 * @param loanNo
	 * @param repayNum
	 * @return
	 * @throws ServiceException 
	 */
	public BigDecimal compCurrAmt(String loanNo, int bgRepayNum, int edRepayNum) throws ServiceException {
		Map paramMap = new HashMap();
		paramMap.put("loanNo", loanNo);
		paramMap.put("bgRepayNum", bgRepayNum);
		paramMap.put("edRepayNum", edRepayNum);
		Map map = (Map) accLoanAcctInstMapper.queryAccLoanAcctAll(paramMap);
		BigDecimal allAmt = (BigDecimal) map.get("allAmt");
		return allAmt;
	}
}
