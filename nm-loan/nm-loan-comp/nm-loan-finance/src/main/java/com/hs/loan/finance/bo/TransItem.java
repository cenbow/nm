package com.hs.loan.finance.bo;

import java.io.Serializable;

/**
 * 交易明细
 * @author zwr
 *
 */
public class TransItem implements Serializable{
	private static final long serialVersionUID = -8696183456214564577L;
	
	/*** 银行代码	非空 */
	private String bankCode;
	/*** 账号	非空 */
	private String accountNo;
	/*** 账号名	非空 */
	private String accountName;
	/*** 开户行所在省		可空 */
	private String province;
	/*** 开户行所在市		可空 */
	private String city;
	/*** 金额，单位分		非空 */
	private String amount;
	/*** 证件类型	可空 */
	private String idType;
	/*** 证件号	可空 */
	private String id;
	/*** 自定义用户号	可空 */
	private String custUserid;
	/*** 备注	可空 */
	private String remark;//代扣表id
	/*** 保留域1 		可空 */
	private String reserve1;//渠道编号
	/*** 保留域2 		可空 */
	private String reserve2;//贷款编号
	/*** 保留域3 		可空 */
	private String reserve3;
	
	private Integer repayNum;
	
	private String repayDate;
	
	public Integer getRepayNum() {
		return repayNum;
	}
	public void setRepayNum(Integer repayNum) {
		this.repayNum = repayNum;
	}
	public String getRepayDate() {
		return repayDate;
	}
	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}
	
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustUserid() {
		return custUserid;
	}
	public void setCustUserid(String custUserid) {
		this.custUserid = custUserid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getReserve1() {
		return reserve1;
	}
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}
	public String getReserve2() {
		return reserve2;
	}
	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}
	public String getReserve3() {
		return reserve3;
	}
	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}
	
}
