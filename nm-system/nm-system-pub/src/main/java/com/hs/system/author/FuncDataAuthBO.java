package com.hs.system.author;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 功能权限BO
 */
public class FuncDataAuthBO {
	/** 当前用户角色机构path */
	private String orgPath;
	/** 当前用户角色机构匹配类型 */
	private String orgMatchType;
	/** 当前机构*/
	private String curOrg;
	/** 当前机构*/
	private String curOrgPath;
	/** 权限类型*/
	private String autTypeCode;
	/** 指定机构号（仅当 权限类型为指定时有效 ） */
	private List<String> appointOrgNo;
	/** 指定角色（仅当 权限类型为指定时有效 ） */
	private List<String> appointRoleNo;
	/** 指定人员（仅当 权限类型为指定时有效 ） */
	private List<String> appointEmpNo;
	/** 指定机构类别（仅当 权限类型为指定时有效 ） */
	private List<String> appointOrgClass;
	
	
	public String getCurOrg() {
		return curOrg;
	}
	public void setCurOrg(String curOrg) {
		this.curOrg = curOrg;
	}
	public String getAutTypeCode() {
		return autTypeCode;
	}
	public void setAutTypeCode(String autTypeCode) {
		this.autTypeCode = autTypeCode;
	}
	public List<String> getAppointOrgNo() {
		return appointOrgNo;
	}
	public void setAppointOrgNo(String appointOrgNo) {
		if(StringUtils.isEmpty(appointOrgNo)){
			this.appointOrgNo = null;
		}else{
			this.appointOrgNo = Arrays.asList(appointOrgNo.split(";"));
		}
	}
	public String getOrgPath() {
		return orgPath;
	}
	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
	}
	public String getOrgMatchType() {
		return orgMatchType;
	}
	public void setOrgMatchType(String orgMatchType) {
		this.orgMatchType = orgMatchType;
	}
	public String getCurOrgPath() {
		return curOrgPath;
	}
	public void setCurOrgPath(String curOrgPath) {
		this.curOrgPath = curOrgPath;
	}
	public List<String> getAppointRoleNo() {
		return appointRoleNo;
	}
	public void setAppointRoleNo(String appointRoleNo) {
		if(StringUtils.isEmpty(appointRoleNo)){
			this.appointRoleNo = null;
		}else{
			this.appointRoleNo = Arrays.asList(appointRoleNo.split(";"));
		}
	}
	public List<String> getAppointEmpNo() {
		return appointEmpNo;
	}
	public void setAppointEmpNo(String appointEmpNo) {
		if(StringUtils.isEmpty(appointEmpNo)){
			this.appointEmpNo = null;
		}else{
			this.appointEmpNo = Arrays.asList(appointEmpNo.split(";"));
		}
	}
	public List<String> getAppointOrgClass() {
		return appointOrgClass;
	}
	public void setAppointOrgClass(String appointOrgClass) {
		if(StringUtils.isEmpty(appointOrgClass)){
			this.appointOrgClass = null;
		}else{
			this.appointOrgClass = Arrays.asList(appointOrgClass.split(";"));
		}
	}
}
