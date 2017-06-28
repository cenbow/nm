package com.hs.system.index.bo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class IndexOfApprBo  implements Serializable{
	
	/**
	 * 销售首页
	 */
	private static final long serialVersionUID = 1L;
	
	private String staffName;
	
	private String lineStat;
	
	private List<Map<String,String>> groupName;
	
	private List<ValBo> retLst;
	
	private List<String> dates;
	
	private List<String> pass;

	private List<String> unpass;
	
	private List<String> total;
	
	private Double risk;
	
	private Map<String,Object> cntBymuth;
	
	public List<String> getDates() {
		return dates;
	}

	public void setDates(List<String> dates) {
		this.dates = dates;
	}

	public List<String> getPass() {
		return pass;
	}

	public void setPass(List<String> pass) {
		this.pass = pass;
	}

	public List<String> getUnpass() {
		return unpass;
	}

	public void setUnpass(List<String> unpass) {
		this.unpass = unpass;
	}

	public List<String> getTotal() {
		return total;
	}

	public void setTotal(List<String> total) {
		this.total = total;
	}

	public Double getRisk() {
		return risk;
	}

	public void setRisk(Double risk) {
		this.risk = risk;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getLineStat() {
		return lineStat;
	}

	public void setLineStat(String lineStat) {
		this.lineStat = lineStat;
	}

	public List<Map<String, String>> getGroupName() {
		return groupName;
	}

	public void setGroupName(List<Map<String, String>> groupName) {
		this.groupName = groupName;
	}

	public List<ValBo> getRetLst() {
		return retLst;
	}

	public void setRetLst(List<ValBo> retLst) {
		this.retLst = retLst;
	}

	public Map<String, Object> getCntBymuth() {
		return cntBymuth;
	}

	public void setCntBymuth(Map<String, Object> cntBymuth) {
		this.cntBymuth = cntBymuth;
	}

}
