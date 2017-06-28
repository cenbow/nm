package com.hs.loan.collection.dto;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * PL_逾期费用项明细 对象
 * @author autocreate
 * @create 2015-12-02
 */
public class LoanOutSourceDetailDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private List<OverdueAmtDetailDto> overdueAmtDetailDtos;
    
    private List<AccLoanBreaksFlowDto> accLoanBreaksFlowDtos;
    
    private  LoanOutsourceDto  loanOutsourceDto;
	//根据贷款编号查询还款计划的费用和
	private HashMap<String, Object> loanPlanSumByLoanNoMap;

	public HashMap<String, Object> getLoanPlanSumByLoanNoMap() {
		return loanPlanSumByLoanNoMap;
	}

	public void setLoanPlanSumByLoanNoMap(HashMap<String, Object> loanPlanSumByLoanNoMap) {
		this.loanPlanSumByLoanNoMap = loanPlanSumByLoanNoMap;
	}

	public List<OverdueAmtDetailDto> getOverdueAmtDetailDtos() {
		return overdueAmtDetailDtos;
	}

	public void setOverdueAmtDetailDtos(List<OverdueAmtDetailDto> overdueAmtDetailDtos) {
		this.overdueAmtDetailDtos = overdueAmtDetailDtos;
	}

	public List<AccLoanBreaksFlowDto> getAccLoanBreaksFlowDtos() {
		return accLoanBreaksFlowDtos;
	}

	public void setAccLoanBreaksFlowDtos(List<AccLoanBreaksFlowDto> accLoanBreaksFlowDtos) {
		this.accLoanBreaksFlowDtos = accLoanBreaksFlowDtos;
	}

	public LoanOutsourceDto getLoanOutsourceDto() {
		return loanOutsourceDto;
	}

	public void setLoanOutsourceDto(LoanOutsourceDto loanOutsourceDto) {
		this.loanOutsourceDto = loanOutsourceDto;
	}
 
}