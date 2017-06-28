package com.nm.model;

import java.io.Serializable;
import java.util.Date;

public class LoanApprBookModel implements Serializable {
	private static final long serialVersionUID = 1L;
	 /*** ID */
  	private String id ;
    
    /*** 案件ID */
  	private String apprId ;
    
    /*** 分期编号 */
  	private String loanNo ;
    
    /*** 块ID */
  	private String blockId ;
  	private String blockIdName;
    
    /*** 审批批注 */
  	private String apprBook ;
    
    /*** 插入时间 */
  	private Date instDate ;

	public String getBlockIdName() {
		return blockIdName;
	}

	public void setBlockIdName(String blockIdName) {
		this.blockIdName = blockIdName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApprId() {
		return apprId;
	}

	public void setApprId(String apprId) {
		this.apprId = apprId;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

	public String getApprBook() {
		return apprBook;
	}

	public void setApprBook(String apprBook) {
		this.apprBook = apprBook;
	}

	public Date getInstDate() {
		return instDate;
	}

	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	} 

}
