/**
 * 
 */
package com.hs.loan.finance.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class LoanBatchDKTaskThread extends Thread {
	
	private Map<String, Object>  params;			
	
	public LoanBatchDKTaskThread(Map<String, Object>  params) {
		this.params = params;
	}

	public void run() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<Boolean> scoreTask = executorService.submit(new ScoreTask(""));
		try {
			Boolean scoreBoolean = scoreTask.get();
			//待评分数据线程、...（以后可能还会扩展其他并发线程） 跑完之后，发起审批流程
			if(scoreBoolean){
				//发起审批流程
				FlowTask();
			}
			executorService.shutdown();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * 评分数据计算线程
	 * @author Administrator
	 *
	 */
	class ScoreTask implements Callable<Boolean> {
		private String loanNo;

	    public ScoreTask(String loanNo) {
	        this.loanNo = loanNo;
	    }

	    public Boolean call() {
//	    	ILoanApplyService applyService = (ILoanApplyService)ContextUtil.getBean(ILoanApplyService.BEAN_ID);
//			try {
//				//保存评分
//				applyService.saveScore(loanNo);
//			} catch (ServiceException e) {
//				e.printStackTrace();
//			}
			//不管评分数据是否跑错,线程跑完之后都返回true
			return true;
	    }
	}
	
	
	
	private void FlowTask(){

	}
	
}
