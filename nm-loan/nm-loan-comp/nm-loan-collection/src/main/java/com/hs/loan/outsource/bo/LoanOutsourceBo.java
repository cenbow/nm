package com.hs.loan.outsource.bo;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

import java.math.BigDecimal;
import java.util.Date;

import java.io.Serializable;

/**
 * PL_委外案件 对象
 * @author autocreate
 * @create 2015-12-02
 */
public class LoanOutsourceBo implements Serializable{
	private static final long serialVersionUID = 1L;
	
    
    /*** ID */
  	private String id ; 
    
    /*** 委托单位 */
  	private String companyName ; 
    
    /*** 案件编号 */
  	private String loanNo ; 
    
    /*** 债务人 */
  	private String debtName ; 
    
    /*** 性别 */
  	private String sex ; 
    
    /*** 身份证号 */
  	private String cardNo ; 
    
    /*** 生日 */
  	private String birthday ; 
    
    /*** 属性 */
  	private String type ; 
    
    /*** 逾期开始时间 */
  	private String exceptDay ; 
    
    /*** 合同号 */
  	private String contractNo ; 
    
    /*** 申请日 */
  	private String applyDay ; 
    
    /*** 首次还款月 */
  	private String firstMonth ; 
    
    /*** 商品价格 */
  	private java.math.BigDecimal goodPrice ; 
    
    /*** 首付金额 */
  	private java.math.BigDecimal firstPrice ; 
    
    /*** 已还期数 */
  	private Integer backNo ; 
    
    /*** 最后一次还款的时间 */
  	private String lastMonth ; 
    
    /*** 分期金额 */
  	private java.math.BigDecimal loanAmt ; 
    
    /*** 期数 */
  	private Integer period ; 
    
    /*** 期款 */
  	private java.math.BigDecimal periodPrice ; 
    
    /*** 分期类型 */
  	private String loanType ; 
    
    /*** 预付款 */
  	private java.math.BigDecimal rePay ; 
    
    /*** 总合同数 */
  	private Integer allContract ; 
    
    /*** 保险 */
  	private java.math.BigDecimal insure ; 
    
    /*** 期款未付 */
  	private java.math.BigDecimal periodUnpaid ; 
    
    /*** 滞纳金 */
  	private java.math.BigDecimal overdueAmt ; 
    
    /*** 欠款金额 */
  	private java.math.BigDecimal debtAmt ; 
    
    /*** 累计已还款 */
  	private java.math.BigDecimal sumDebt ; 
    
    /*** 账户 */
  	private String custAccount ; 
    
    /*** 委托日期 */
  	private Date entrustDate ; 
    
    /*** 批次 */
  	private Integer batch ; 
    
    /*** 逾期费率 */
  	private java.math.BigDecimal overdueRate ; 
    
    /*** 委托期限 */
  	private String entrustTime ; 
    
    /*** 手数 */
  	private Integer handNum ; 
    
    /*** 未逾期费率 */
  	private java.math.BigDecimal unoverdueRate ; 
    
    /*** 经办人员 */
  	private String handCust ; 
    
    /*** 利息 */
  	private java.math.BigDecimal interAmt ; 
    
    /*** CD值 */
  	private java.math.BigDecimal cdValue ; 
    
    /*** 自动状态 */
  	private String autoType ; 
    
    /*** 手工状态 */
  	private String handType ; 
    
    /*** 户籍地址 */
  	private String regAddr ; 
    
    /*** 移动电话 */
  	private String mobileTel ; 
    
    /*** 申请当时提供手机 */
  	private String applyTel ; 
    
    /*** 家电号码 */
  	private String homeTel ; 
    
    /*** 单位电话 */
  	private String unitTel ; 
    
    /*** 公司名称 */
  	private String unitName ; 
    
    /*** 部门 */
  	private String sector ; 
    
    /*** 单位地址 */
  	private String unitAddr ; 
    
    /*** 商户名称 */
  	private String marName ; 
    
    /*** 销售地 */
  	private String saleAddrs ; 
    
    /*** 厂商 */
  	private String firm ; 
    
    /*** 品牌 */
  	private String modeName ; 
    
    /*** 产品 */
  	private String productName ; 
    
    /*** 现居住宅地址 */
  	private String liveAddress ; 
    
    /*** 申请时提供家电 */
  	private String applyHometel ; 
    
    /*** 申请时提供的办公室电话 */
  	private String applyWorktel ; 
    
    /*** 配偶姓名 */
  	private String spouseName ; 
    
    /*** 配偶电话 */
  	private String spouseTel ; 
    
    /*** 父母姓名 */
  	private String parentName ; 
    
    /*** 父母电话 */
  	private String parentPhone ; 
    
    /*** 父母地址 */
  	private String parentAddress ; 
    
    /*** 联系人1 */
  	private String contactName1 ; 
    
    /*** 联系人电话1 */
  	private String contactTel1 ; 
    
    /*** 联系人2 */
  	private String contactName2 ; 
    
    /*** 联系人电话2 */
  	private String contactTel2 ; 
    
    /*** 联系人3 */
  	private String contactName3 ; 
    
    /*** 联系人电话3 */
  	private String contactTel3 ; 
    
    /*** 联系人4 */
  	private String contactName4 ; 
    
    /*** 联系人电话4 */
  	private String contactTel4 ; 
    
    /*** 联系人5 */
  	private String contactName5 ; 
    
    /*** 联系人电话5 */
  	private String contactTel5 ; 
    
    /*** 销售备注 */
  	private String saleRemark ; 
    
    /*** 邮箱 */
  	private String mailBox ; 
    
    /*** 户名 */
  	private String userName ; 
    
    /*** 开户银行 */
  	private String openBank ; 
    
    /*** 邮政储蓄银行商户号 */
  	private String bankNo ; 
  	
  	/**应还总金额***/
  	private String yhRepayAmt;
  	/**回收总金额***/
  	private String hsRepayAmt;
  	/**渠道**/
  	private String chanleNo;
  	/**渠道**/
  	private String chanleName;
	/**是否结清**/
	private String isPayd;
	/**提前结清金额**/
	private BigDecimal repayAmt;
	/**是否分配**/
	private String isDistrbut;
	/*** 委外日期 */
	private Date pltime;
	/** 委托状态 */
	private String stat;

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public Date getPltime() {
		return pltime;
	}

	public void setPltime(Date pltime) {
		this.pltime = pltime;
	}

	public String getIsDistrbut() {
		return isDistrbut;
	}

	public void setIsDistrbut(String isDistrbut) {
		this.isDistrbut = isDistrbut;
	}

	public String getIsPayd() {
		return isPayd;
	}

	public void setIsPayd(String isPayd) {
		this.isPayd = isPayd;
	}

	public BigDecimal getRepayAmt() {
		return repayAmt;
	}

	public void setRepayAmt(BigDecimal repayAmt) {
		this.repayAmt = repayAmt;
	}

	//构造函数
    public LoanOutsourceBo(){}

    //getter和setter方法
    
    /**
	 * 获取 ID
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 ID
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

    
    /**
	 * 获取 委托单位
	 * @return String
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * 设置 委托单位
	 * @param companyName
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

    
    /**
	 * 获取 案件编号
	 * @return String
	 */
	public String getLoanNo() {
		return loanNo;
	}

	/**
	 * 设置 案件编号
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

    
    /**
	 * 获取 债务人
	 * @return String
	 */
	public String getDebtName() {
		return debtName;
	}

	/**
	 * 设置 债务人
	 * @param debtName
	 */
	public void setDebtName(String debtName) {
		this.debtName = debtName;
	}

    
    /**
	 * 获取 性别
	 * @return String
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 设置 性别
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

    
    /**
	 * 获取 身份证号
	 * @return String
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * 设置 身份证号
	 * @param cardNo
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

    
    /**
	 * 获取 生日
	 * @return String
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * 设置 生日
	 * @param birthday
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

    
    /**
	 * 获取 属性
	 * @return String
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置 属性
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

    
    /**
	 * 获取 逾期开始时间
	 * @return String
	 */
	public String getExceptDay() {
		return exceptDay;
	}

	/**
	 * 设置 逾期开始时间
	 * @param exceptDay
	 */
	public void setExceptDay(String exceptDay) {
		this.exceptDay = exceptDay;
	}

    
    /**
	 * 获取 合同号
	 * @return String
	 */
	public String getContractNo() {
		return contractNo;
	}

	/**
	 * 设置 合同号
	 * @param contractNo
	 */
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

    
    /**
	 * 获取 申请日
	 * @return String
	 */
	public String getApplyDay() {
		return applyDay;
	}

	/**
	 * 设置 申请日
	 * @param applyDay
	 */
	public void setApplyDay(String applyDay) {
		this.applyDay = applyDay;
	}

    
    /**
	 * 获取 首次还款月
	 * @return String
	 */
	public String getFirstMonth() {
		return firstMonth;
	}

	/**
	 * 设置 首次还款月
	 * @param firstMonth
	 */
	public void setFirstMonth(String firstMonth) {
		this.firstMonth = firstMonth;
	}

    
    /**
	 * 获取 商品价格
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getGoodPrice() {
		return goodPrice;
	}

	/**
	 * 设置 商品价格
	 * @param goodPrice
	 */
	public void setGoodPrice(java.math.BigDecimal goodPrice) {
		this.goodPrice = goodPrice;
	}

    
    /**
	 * 获取 首付金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getFirstPrice() {
		return firstPrice;
	}

	/**
	 * 设置 首付金额
	 * @param firstPrice
	 */
	public void setFirstPrice(java.math.BigDecimal firstPrice) {
		this.firstPrice = firstPrice;
	}

    
    /**
	 * 获取 已还期数
	 * @return Integer
	 */
	public Integer getBackNo() {
		return backNo;
	}

	/**
	 * 设置 已还期数
	 * @param backNo
	 */
	public void setBackNo(Integer backNo) {
		this.backNo = backNo;
	}

    
    /**
	 * 获取 最后一次还款的时间
	 * @return String
	 */
	public String getLastMonth() {
		return lastMonth;
	}

	/**
	 * 设置 最后一次还款的时间
	 * @param lastMonth
	 */
	public void setLastMonth(String lastMonth) {
		this.lastMonth = lastMonth;
	}

    
    /**
	 * 获取 分期金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getLoanAmt() {
		return loanAmt;
	}

	/**
	 * 设置 分期金额
	 * @param loanAmt
	 */
	public void setLoanAmt(java.math.BigDecimal loanAmt) {
		this.loanAmt = loanAmt;
	}

    
    /**
	 * 获取 期数
	 * @return Integer
	 */
	public Integer getPeriod() {
		return period;
	}

	/**
	 * 设置 期数
	 * @param period
	 */
	public void setPeriod(Integer period) {
		this.period = period;
	}

    
    /**
	 * 获取 期款
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getPeriodPrice() {
		return periodPrice;
	}

	/**
	 * 设置 期款
	 * @param periodPrice
	 */
	public void setPeriodPrice(java.math.BigDecimal periodPrice) {
		this.periodPrice = periodPrice;
	}

    
    /**
	 * 获取 分期类型
	 * @return String
	 */
	public String getLoanType() {
		return loanType;
	}

	/**
	 * 设置 分期类型
	 * @param loanType
	 */
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

    
    /**
	 * 获取 预付款
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getRePay() {
		return rePay;
	}

	/**
	 * 设置 预付款
	 * @param rePay
	 */
	public void setRePay(java.math.BigDecimal rePay) {
		this.rePay = rePay;
	}

    
    /**
	 * 获取 总合同数
	 * @return Integer
	 */
	public Integer getAllContract() {
		return allContract;
	}

	/**
	 * 设置 总合同数
	 * @param allContract
	 */
	public void setAllContract(Integer allContract) {
		this.allContract = allContract;
	}

    
    /**
	 * 获取 保险
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getInsure() {
		return insure;
	}

	/**
	 * 设置 保险
	 * @param insure
	 */
	public void setInsure(java.math.BigDecimal insure) {
		this.insure = insure;
	}

    
    /**
	 * 获取 期款未付
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getPeriodUnpaid() {
		return periodUnpaid;
	}

	/**
	 * 设置 期款未付
	 * @param periodUnpaid
	 */
	public void setPeriodUnpaid(java.math.BigDecimal periodUnpaid) {
		this.periodUnpaid = periodUnpaid;
	}

    
    /**
	 * 获取 滞纳金
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getOverdueAmt() {
		return overdueAmt;
	}

	/**
	 * 设置 滞纳金
	 * @param overdueAmt
	 */
	public void setOverdueAmt(java.math.BigDecimal overdueAmt) {
		this.overdueAmt = overdueAmt;
	}

    
    /**
	 * 获取 欠款金额
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getDebtAmt() {
		return debtAmt;
	}

	/**
	 * 设置 欠款金额
	 * @param debtAmt
	 */
	public void setDebtAmt(java.math.BigDecimal debtAmt) {
		this.debtAmt = debtAmt;
	}

    
    /**
	 * 获取 累计已还款
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getSumDebt() {
		return sumDebt;
	}

	/**
	 * 设置 累计已还款
	 * @param sumDebt
	 */
	public void setSumDebt(java.math.BigDecimal sumDebt) {
		this.sumDebt = sumDebt;
	}

    
    /**
	 * 获取 账户
	 * @return String
	 */
	public String getCustAccount() {
		return custAccount;
	}

	/**
	 * 设置 账户
	 * @param custAccount
	 */
	public void setCustAccount(String custAccount) {
		this.custAccount = custAccount;
	}

    
    /**
	 * 获取 委托日期
	 * @return Date
	 */
	public Date getEntrustDate() {
		return entrustDate;
	}

	/**
	 * 设置 委托日期
	 * @param entrustDate
	 */
	public void setEntrustDate(Date entrustDate) {
		this.entrustDate = entrustDate;
	}

    
    /**
	 * 获取 批次
	 * @return Integer
	 */
	public Integer getBatch() {
		return batch;
	}

	/**
	 * 设置 批次
	 * @param batch
	 */
	public void setBatch(Integer batch) {
		this.batch = batch;
	}

    
    /**
	 * 获取 逾期费率
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getOverdueRate() {
		return overdueRate;
	}

	/**
	 * 设置 逾期费率
	 * @param overdueRate
	 */
	public void setOverdueRate(java.math.BigDecimal overdueRate) {
		this.overdueRate = overdueRate;
	}

    
    /**
	 * 获取 委托期限
	 * @return String
	 */
	public String getEntrustTime() {
		return entrustTime;
	}

	/**
	 * 设置 委托期限
	 * @param entrustTime
	 */
	public void setEntrustTime(String entrustTime) {
		this.entrustTime = entrustTime;
	}

    
    /**
	 * 获取 手数
	 * @return Integer
	 */
	public Integer getHandNum() {
		return handNum;
	}

	/**
	 * 设置 手数
	 * @param handNum
	 */
	public void setHandNum(Integer handNum) {
		this.handNum = handNum;
	}

    
    /**
	 * 获取 未逾期费率
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getUnoverdueRate() {
		return unoverdueRate;
	}

	/**
	 * 设置 未逾期费率
	 * @param unoverdueRate
	 */
	public void setUnoverdueRate(java.math.BigDecimal unoverdueRate) {
		this.unoverdueRate = unoverdueRate;
	}

    
    /**
	 * 获取 经办人员
	 * @return String
	 */
	public String getHandCust() {
		return handCust;
	}

	/**
	 * 设置 经办人员
	 * @param handCust
	 */
	public void setHandCust(String handCust) {
		this.handCust = handCust;
	}

    
    /**
	 * 获取 利息
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getInterAmt() {
		return interAmt;
	}

	/**
	 * 设置 利息
	 * @param interAmt
	 */
	public void setInterAmt(java.math.BigDecimal interAmt) {
		this.interAmt = interAmt;
	}

    
    /**
	 * 获取 CD值
	 * @return java.math.BigDecimal
	 */
	public java.math.BigDecimal getCdValue() {
		return cdValue;
	}

	/**
	 * 设置 CD值
	 * @param cdValue
	 */
	public void setCdValue(java.math.BigDecimal cdValue) {
		this.cdValue = cdValue;
	}

    
    /**
	 * 获取 自动状态
	 * @return String
	 */
	public String getAutoType() {
		return autoType;
	}

	/**
	 * 设置 自动状态
	 * @param autoType
	 */
	public void setAutoType(String autoType) {
		this.autoType = autoType;
	}

    
    /**
	 * 获取 手工状态
	 * @return String
	 */
	public String getHandType() {
		return handType;
	}

	/**
	 * 设置 手工状态
	 * @param handType
	 */
	public void setHandType(String handType) {
		this.handType = handType;
	}

    
    /**
	 * 获取 户籍地址
	 * @return String
	 */
	public String getRegAddr() {
		return regAddr;
	}

	/**
	 * 设置 户籍地址
	 * @param regAddr
	 */
	public void setRegAddr(String regAddr) {
		this.regAddr = regAddr;
	}

    
    /**
	 * 获取 移动电话
	 * @return String
	 */
	public String getMobileTel() {
		return mobileTel;
	}

	/**
	 * 设置 移动电话
	 * @param mobileTel
	 */
	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}

    
    /**
	 * 获取 申请当时提供手机
	 * @return String
	 */
	public String getApplyTel() {
		return applyTel;
	}

	/**
	 * 设置 申请当时提供手机
	 * @param applyTel
	 */
	public void setApplyTel(String applyTel) {
		this.applyTel = applyTel;
	}

    
    /**
	 * 获取 家电号码
	 * @return String
	 */
	public String getHomeTel() {
		return homeTel;
	}

	/**
	 * 设置 家电号码
	 * @param homeTel
	 */
	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}

    
    /**
	 * 获取 单位电话
	 * @return String
	 */
	public String getUnitTel() {
		return unitTel;
	}

	/**
	 * 设置 单位电话
	 * @param unitTel
	 */
	public void setUnitTel(String unitTel) {
		this.unitTel = unitTel;
	}

    
    /**
	 * 获取 公司名称
	 * @return String
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * 设置 公司名称
	 * @param unitName
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

    
    /**
	 * 获取 部门
	 * @return String
	 */
	public String getSector() {
		return sector;
	}

	/**
	 * 设置 部门
	 * @param sector
	 */
	public void setSector(String sector) {
		this.sector = sector;
	}

    
    /**
	 * 获取 单位地址
	 * @return String
	 */
	public String getUnitAddr() {
		return unitAddr;
	}

	/**
	 * 设置 单位地址
	 * @param unitAddr
	 */
	public void setUnitAddr(String unitAddr) {
		this.unitAddr = unitAddr;
	}

    
    /**
	 * 获取 商户名称
	 * @return String
	 */
	public String getMarName() {
		return marName;
	}

	/**
	 * 设置 商户名称
	 * @param marName
	 */
	public void setMarName(String marName) {
		this.marName = marName;
	}

    
    /**
	 * 获取 销售地
	 * @return String
	 */
	public String getSaleAddrs() {
		return saleAddrs;
	}

	/**
	 * 设置 销售地
	 * @param saleAddrs
	 */
	public void setSaleAddrs(String saleAddrs) {
		this.saleAddrs = saleAddrs;
	}

    
    /**
	 * 获取 厂商
	 * @return String
	 */
	public String getFirm() {
		return firm;
	}

	/**
	 * 设置 厂商
	 * @param firm
	 */
	public void setFirm(String firm) {
		this.firm = firm;
	}

    
    /**
	 * 获取 品牌
	 * @return String
	 */
	public String getModeName() {
		return modeName;
	}

	/**
	 * 设置 品牌
	 * @param modeName
	 */
	public void setModeName(String modeName) {
		this.modeName = modeName;
	}

    
    /**
	 * 获取 产品
	 * @return String
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * 设置 产品
	 * @param productName
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

    
    /**
	 * 获取 现居住宅地址
	 * @return String
	 */
	public String getLiveAddress() {
		return liveAddress;
	}

	/**
	 * 设置 现居住宅地址
	 * @param liveAddress
	 */
	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}

    
    /**
	 * 获取 申请时提供家电
	 * @return String
	 */
	public String getApplyHometel() {
		return applyHometel;
	}

	/**
	 * 设置 申请时提供家电
	 * @param applyHometel
	 */
	public void setApplyHometel(String applyHometel) {
		this.applyHometel = applyHometel;
	}

    
    /**
	 * 获取 申请时提供的办公室电话
	 * @return String
	 */
	public String getApplyWorktel() {
		return applyWorktel;
	}

	/**
	 * 设置 申请时提供的办公室电话
	 * @param applyWorktel
	 */
	public void setApplyWorktel(String applyWorktel) {
		this.applyWorktel = applyWorktel;
	}

    
    /**
	 * 获取 配偶姓名
	 * @return String
	 */
	public String getSpouseName() {
		return spouseName;
	}

	/**
	 * 设置 配偶姓名
	 * @param spouseName
	 */
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

    
    /**
	 * 获取 配偶电话
	 * @return String
	 */
	public String getSpouseTel() {
		return spouseTel;
	}

	/**
	 * 设置 配偶电话
	 * @param spouseTel
	 */
	public void setSpouseTel(String spouseTel) {
		this.spouseTel = spouseTel;
	}

    
    /**
	 * 获取 父母姓名
	 * @return String
	 */
	public String getParentName() {
		return parentName;
	}

	/**
	 * 设置 父母姓名
	 * @param parentName
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

    
    /**
	 * 获取 父母电话
	 * @return String
	 */
	public String getParentPhone() {
		return parentPhone;
	}

	/**
	 * 设置 父母电话
	 * @param parentPhone
	 */
	public void setParentPhone(String parentPhone) {
		this.parentPhone = parentPhone;
	}

    
    /**
	 * 获取 父母地址
	 * @return String
	 */
	public String getParentAddress() {
		return parentAddress;
	}

	/**
	 * 设置 父母地址
	 * @param parentAddress
	 */
	public void setParentAddress(String parentAddress) {
		this.parentAddress = parentAddress;
	}

    
    /**
	 * 获取 联系人1
	 * @return String
	 */
	public String getContactName1() {
		return contactName1;
	}

	/**
	 * 设置 联系人1
	 * @param contactName1
	 */
	public void setContactName1(String contactName1) {
		this.contactName1 = contactName1;
	}

    
    /**
	 * 获取 联系人电话1
	 * @return String
	 */
	public String getContactTel1() {
		return contactTel1;
	}

	/**
	 * 设置 联系人电话1
	 * @param contactTel1
	 */
	public void setContactTel1(String contactTel1) {
		this.contactTel1 = contactTel1;
	}

    
    /**
	 * 获取 联系人2
	 * @return String
	 */
	public String getContactName2() {
		return contactName2;
	}

	/**
	 * 设置 联系人2
	 * @param contactName2
	 */
	public void setContactName2(String contactName2) {
		this.contactName2 = contactName2;
	}

    
    /**
	 * 获取 联系人电话2
	 * @return String
	 */
	public String getContactTel2() {
		return contactTel2;
	}

	/**
	 * 设置 联系人电话2
	 * @param contactTel2
	 */
	public void setContactTel2(String contactTel2) {
		this.contactTel2 = contactTel2;
	}

    
    /**
	 * 获取 联系人3
	 * @return String
	 */
	public String getContactName3() {
		return contactName3;
	}

	/**
	 * 设置 联系人3
	 * @param contactName3
	 */
	public void setContactName3(String contactName3) {
		this.contactName3 = contactName3;
	}

    
    /**
	 * 获取 联系人电话3
	 * @return String
	 */
	public String getContactTel3() {
		return contactTel3;
	}

	/**
	 * 设置 联系人电话3
	 * @param contactTel3
	 */
	public void setContactTel3(String contactTel3) {
		this.contactTel3 = contactTel3;
	}

    
    /**
	 * 获取 联系人4
	 * @return String
	 */
	public String getContactName4() {
		return contactName4;
	}

	/**
	 * 设置 联系人4
	 * @param contactName4
	 */
	public void setContactName4(String contactName4) {
		this.contactName4 = contactName4;
	}

    
    /**
	 * 获取 联系人电话4
	 * @return String
	 */
	public String getContactTel4() {
		return contactTel4;
	}

	/**
	 * 设置 联系人电话4
	 * @param contactTel4
	 */
	public void setContactTel4(String contactTel4) {
		this.contactTel4 = contactTel4;
	}

    
    /**
	 * 获取 联系人5
	 * @return String
	 */
	public String getContactName5() {
		return contactName5;
	}

	/**
	 * 设置 联系人5
	 * @param contactName5
	 */
	public void setContactName5(String contactName5) {
		this.contactName5 = contactName5;
	}

    
    /**
	 * 获取 联系人电话5
	 * @return String
	 */
	public String getContactTel5() {
		return contactTel5;
	}

	/**
	 * 设置 联系人电话5
	 * @param contactTel5
	 */
	public void setContactTel5(String contactTel5) {
		this.contactTel5 = contactTel5;
	}

    
    /**
	 * 获取 销售备注
	 * @return String
	 */
	public String getSaleRemark() {
		return saleRemark;
	}

	/**
	 * 设置 销售备注
	 * @param saleRemark
	 */
	public void setSaleRemark(String saleRemark) {
		this.saleRemark = saleRemark;
	}

    
    /**
	 * 获取 邮箱
	 * @return String
	 */
	public String getMailBox() {
		return mailBox;
	}

	/**
	 * 设置 邮箱
	 * @param mailBox
	 */
	public void setMailBox(String mailBox) {
		this.mailBox = mailBox;
	}

    
    /**
	 * 获取 户名
	 * @return String
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置 户名
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

    
    /**
	 * 获取 开户银行
	 * @return String
	 */
	public String getOpenBank() {
		return openBank;
	}

	/**
	 * 设置 开户银行
	 * @param openBank
	 */
	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

    
    /**
	 * 获取 邮政储蓄银行商户号
	 * @return String
	 */
	public String getBankNo() {
		return bankNo;
	}

	/**
	 * 设置 邮政储蓄银行商户号
	 * @param bankNo
	 */
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getYhRepayAmt() {
		return yhRepayAmt;
	}

	public void setYhRepayAmt(String yhRepayAmt) {
		this.yhRepayAmt = yhRepayAmt;
	}

	public String getHsRepayAmt() {
		return hsRepayAmt;
	}

	public void setHsRepayAmt(String hsRepayAmt) {
		this.hsRepayAmt = hsRepayAmt;
	}

	public String getChanleNo() {
		return chanleNo;
	}

	public void setChanleNo(String chanleNo) {
		this.chanleNo = chanleNo;
	}

	public String getChanleName() {
		return chanleName;
	}

	public void setChanleName(String chanleName) {
		this.chanleName = chanleName;
	}
}