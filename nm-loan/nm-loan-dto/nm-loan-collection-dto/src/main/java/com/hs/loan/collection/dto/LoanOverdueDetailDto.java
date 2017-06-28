package com.hs.loan.collection.dto;


import java.io.Serializable;
import java.util.List;

/**
 * PL_逾期费用项明细 对象
 * @author autocreate
 * @create 2015-12-02
 */
public class LoanOverdueDetailDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private List<OverdueAmtDetailDto> overdueAmtDetailDtos;
    
    private List<AccLoanBreaksFlowDto> accLoanBreaksFlowDtos;
    
    private List<LoanOveDualCaseBaseDto> caseBaseDtos;

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

	public List<LoanOveDualCaseBaseDto> getCaseBaseDtos() {
		return caseBaseDtos;
	}

	public void setCaseBaseDtos(List<LoanOveDualCaseBaseDto> caseBaseDtos) {
		this.caseBaseDtos = caseBaseDtos;
	}
    
}