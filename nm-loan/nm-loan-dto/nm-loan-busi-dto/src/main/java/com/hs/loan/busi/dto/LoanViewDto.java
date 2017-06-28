package com.hs.loan.busi.dto;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 分期视图 对象
 * @author jqiu
 * @create 2015-10-27
 */
public class LoanViewDto implements Serializable{
	private static final long serialVersionUID = 1L;
    
	 /*** 分期基本信息 */
  	private LoanAcctOutDto baseInfo ; 
  	
  	/*** 客户基本信息 */
  	private LoanCustBaseInfoDto custBaseInfo ; 
  	
  	/*** 商品信息列表 */
  	private List<LoanGoodsDto> goods ; 
  	
  	/*** 销售信息 */
  	private LoanSalerDto saler ; 
  	
  	/*** 分期产品信息 */
  	private LoanProdDto prod ; 
  	
  	/*** 分期网点信息 */
  	private LoanBranchDto branch ; 
  	
  	/*** 分期银行账户信息 */
  	private LoanBankAcctDto bankAcct ; 
  	
  	/*** 渠道资方信息 */
  	private LoanFundChanDto fundChan ;
  	
  	/*** 合同列表(合同附件ID，合同名) */
  	private Map<String,String> contracts ; 
  	
  	/*** 还款类型(类型编号，类型名) */
  	private Map<String,String> repayTyps ; 
  	
  	/*** 分期费用项列表  */
  	private List<LoanFeeDto> fees ; 
  	
  	/*** 分期汇总信息  */
  	private LoanRepayCollectDto repayCollect ; 
  	
	public LoanAcctOutDto getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(LoanAcctOutDto baseInfo) {
		this.baseInfo = baseInfo;
	}

	public List<LoanGoodsDto> getGoods() {
		return goods;
	}

	public void setGoods(List<LoanGoodsDto> goods) {
		this.goods = goods;
	}

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

	public LoanBankAcctDto getBankAcct() {
		return bankAcct;
	}

	public void setBankAcct(LoanBankAcctDto bankAcct) {
		this.bankAcct = bankAcct;
	}



	public List<LoanFeeDto> getFees() {
		return fees;
	}

	public void setFees(List<LoanFeeDto> fees) {
		this.fees = fees;
	}

	public LoanFundChanDto getFundChan() {
		return fundChan;
	}

	public void setFundChan(LoanFundChanDto fundChan) {
		this.fundChan = fundChan;
	}

	public LoanCustBaseInfoDto getCustBaseInfo() {
		return custBaseInfo;
	}

	public void setCustBaseInfo(LoanCustBaseInfoDto custBaseInfo) {
		this.custBaseInfo = custBaseInfo;
	}


	public Map<String, String> getContracts() {
		return contracts;
	}

	public void setContracts(Map<String, String> contracts) {
		this.contracts = contracts;
	}

	public Map<String, String> getRepayTyps() {
		return repayTyps;
	}

	public void setRepayTyps(Map<String, String> repayTyps) {
		this.repayTyps = repayTyps;
	}

	public LoanRepayCollectDto getRepayCollect() {
		return repayCollect;
	}

	public void setRepayCollect(LoanRepayCollectDto repayCollect) {
		this.repayCollect = repayCollect;
	} 
	
}