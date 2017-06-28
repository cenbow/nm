package com.hs.loan.busi.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 分期基本信息查看 对象
 * @author jqiu
 * @create 2015-10-27
 */
public class LoanAcctOutDto extends LoanAcctInDto implements Serializable{
	private static final long serialVersionUID = 1L;
    
    /*** 利率 */
	@NotNull(message="利率不能为空")
  	@Size(max=20,message="利率超长")
  	private java.math.BigDecimal interRate ; 
    
    /*** 首次还款额 */
	@NotNull(message="首次还款额不能为空")
  	@Size(max=20,message="首次还款额超长")
  	private java.math.BigDecimal fstRepayAmt ; 
    
    /*** 首次还款日 */
	@NotBlank(message="首次还款日不能为空")
  	@Size(max=20,message="首次还款日超长")
  	private String fstRepayDate ; 
    
    /*** 每月还款日 */
	@NotBlank(message="每月还款日不能为空")
  	@Size(max=10,message="每月还款日超长")
  	private String mthRepayDate ; 
    
    /*** 月还款金额 */
	@NotNull(message="月还款金额不能为空")
  	@Size(max=20,message="月还款金额超长")
  	private java.math.BigDecimal mthRepayAmt ; 
    
    /*** 申请日期 */
  	@Future(message="申请日期必须晚于当前时间")
  	private Date applyDate ; 
    
    /*** 审批日期 */
  	@Future(message="审批日期必须晚于当前时间")
  	private Date aprvDate ; 
    
    /*** 注册日期 */
  	@Future(message="注册日期必须晚于当前时间")
  	private Date regDate ; 
    
    /*** 放款日期 */
  	@Future(message="放款日期必须晚于当前时间")
  	private Date distrDate ; 
    
    /*** 分期状态 */
  	@NotBlank(message="分期状态不能为空")
  	@Size(max=10,message="分期状态超长")
  	private String stat ; 
  	
  	/*** 分期状态名 */
  	@NotBlank(message="分期状态名不能为空")
  	@Size(max=10,message="分期状态名超长")
  	private String statName ; 
    
    /*** 五级分类 */
  	@NotBlank(message="五级分类不能为空")
  	@Size(max=10,message="五级分类超长")
  	private String fivCls ;
  	
    /*** 办理所在省名称 */
  	@NotBlank(message="办理所在省名称不能为空")
  	@Size(max=128,message="办理所在省名称超长")
  	private String applyProvName ; 
    
    /*** 办理所在区/县名称 */
  	@NotBlank(message="办理所在区/县名称不能为空")
  	@Size(max=128,message="办理所在区/县名称超长")
  	private String applyAreaName ; 
    
    /*** 办理所在市名称 */
  	@NotBlank(message="办理所在市名称不能为空")
  	@Size(max=128,message="办理所在市名称超长")
  	private String applyCityName ; 
  	
 	 /*商铺等级*/
 	private String shopLevel;
	//定位
    private String location;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getShopLevel() {
		return shopLevel;
	}

	public void setShopLevel(String shopLevel) {
		this.shopLevel = shopLevel;
	}

	public java.math.BigDecimal getInterRate() {
		return interRate;
	}

	public void setInterRate(java.math.BigDecimal interRate) {
		this.interRate = interRate;
	}

	public java.math.BigDecimal getFstRepayAmt() {
		return fstRepayAmt;
	}

	public void setFstRepayAmt(java.math.BigDecimal fstRepayAmt) {
		this.fstRepayAmt = fstRepayAmt;
	}

	public String getFstRepayDate() {
		return fstRepayDate;
	}

	public void setFstRepayDate(String fstRepayDate) {
		this.fstRepayDate = fstRepayDate;
	}

	public String getMthRepayDate() {
		return mthRepayDate;
	}

	public void setMthRepayDate(String mthRepayDate) {
		this.mthRepayDate = mthRepayDate;
	}

	public java.math.BigDecimal getMthRepayAmt() {
		return mthRepayAmt;
	}

	public void setMthRepayAmt(java.math.BigDecimal mthRepayAmt) {
		this.mthRepayAmt = mthRepayAmt;
	}

	@JsonSerialize(using = DateTimeJsonSerializer.class) 
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	@JsonSerialize(using = DateTimeJsonSerializer.class) 
	public Date getAprvDate() {
		return aprvDate;
	}

	public void setAprvDate(Date aprvDate) {
		this.aprvDate = aprvDate;
	}

	@JsonSerialize(using = DateTimeJsonSerializer.class) 
	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@JsonSerialize(using = DateTimeJsonSerializer.class) 
	public Date getDistrDate() {
		return distrDate;
	}

	public void setDistrDate(Date distrDate) {
		this.distrDate = distrDate;
	}

	public String getFivCls() {
		return fivCls;
	}

	public void setFivCls(String fivCls) {
		this.fivCls = fivCls;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getStatName() {
		return statName;
	}

	public void setStatName(String statName) {
		this.statName = statName;
	}

	public String getApplyProvName() {
		return applyProvName;
	}

	public void setApplyProvName(String applyProvName) {
		this.applyProvName = applyProvName;
	}

	public String getApplyAreaName() {
		return applyAreaName;
	}

	public void setApplyAreaName(String applyAreaName) {
		this.applyAreaName = applyAreaName;
	}

	public String getApplyCityName() {
		return applyCityName;
	}

	public void setApplyCityName(String applyCityName) {
		this.applyCityName = applyCityName;
	}
}