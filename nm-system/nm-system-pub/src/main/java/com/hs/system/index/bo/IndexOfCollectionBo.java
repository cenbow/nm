package com.hs.system.index.bo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class IndexOfCollectionBo  implements Serializable{
	
	/**
	 * 催收首页
	 */
	private static final long serialVersionUID = 1L;
	
	private String staffName;
	 
	private List<Map<String,String>> groupName;
	
	private List<String> dates;
	
	private List<String> pass;

	private List<String> total;

	public List<Map<String, String>> getGroupName() {
		return groupName;
	}

	public void setGroupName(List<Map<String, String>> groupName) {
		this.groupName = groupName;
	}

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

	public List<String> getTotal() {
		return total;
	}

	public void setTotal(List<String> total) {
		this.total = total;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	
	
	
}
