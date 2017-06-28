package com.hs.loan.approv.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lenovo on 2016/5/24.
 */
public class LoanApprRemarkDto implements Serializable{
    private static final long serialVersionUID = 1L;


    /*** ID */
    private String id ;

    /*** 案件ID */
    @NotBlank(message="案件ID不能为空")
    @Size(max=40,message="案件ID超长")
    private String apprId ;

    /*** 分期编号 */
    @NotBlank(message="分期编号不能为空")
    @Size(max=40,message="分期编号超长")
    private String loanNo ;

    /*** 块ID */
    @NotBlank(message="块ID不能为空")
    @Size(max=40,message="块ID超长")
    private String blockId ;

    /*** 审批批注 */
    //@NotBlank(message="审批批注不能为空")
    @Size(max=400,message="审批批注超长")
    private String apprRemark ;

    /*** 插入时间 */
    private Date instDate ;

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

    public String getApprRemark() {
        return apprRemark;
    }

    public void setApprRemark(String apprRemark) {
        this.apprRemark = apprRemark;
    }

    public Date getInstDate() {
        return instDate;
    }

    public void setInstDate(Date instDate) {
        this.instDate = instDate;
    }
}
