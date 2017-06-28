package com.hs.loan.busi.dto;

import java.io.Serializable;

public class LoanOtherInfoDto implements Serializable{

 	/*** 销售信息 */
  	private LoanSalerDto saler ; 
  	
  	/*** 分期产品信息 */
  	private LoanProdDto prod ; 
  	
  	/*** 分期网点信息 */
  	private LoanBranchDto branch ;

	public LoanSalerDto getSaler() {
		return saler;
	}

	public void setSaler(LoanSalerDto saler) {
		this.saler = saler;
	}

	public LoanProdDto getProd() {
		return prod;
	}

	public void setProd(LoanProdDto prod) {
		this.prod = prod;
	}

	public LoanBranchDto getBranch() {
		return branch;
	}

	public void setBranch(LoanBranchDto branch) {
		this.branch = branch;
	} 
	
}
