package com.hs.loan.busi.dto;

import java.io.Serializable;

public class LoanAttInDto implements Serializable{
	
	
private static final long serialVersionUID = 1L;
	
    /*** 附件编号 */
  	private String attNo ; 
    
    /*** 分期编号 */
  	private String loanNo ; 
    
    /*** 客户号 */
  	private String custNo ; 
    
    /*** 附件名称 */
  	private String attName ; 
    
    /*** 附件类型 */
  	private String attTyp ; 
    
    /*** 附件文件格式 */
  	private String attFile ;

	public String getAttNo() {
		return attNo;
	}

	public void setAttNo(String attNo) {
		this.attNo = attNo;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getAttName() {
		return attName;
	}

	public void setAttName(String attName) {
		this.attName = attName;
	}

	public String getAttTyp() {
		return attTyp;
	}

	public void setAttTyp(String attTyp) {
		this.attTyp = attTyp;
	}

	public String getAttFile() {
		return attFile;
	}

	public void setAttFile(String attFile) {
		this.attFile = attFile;
	} 
    

}
