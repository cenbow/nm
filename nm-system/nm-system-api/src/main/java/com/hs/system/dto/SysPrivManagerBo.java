package com.hs.system.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.base.support.json.annotation.DateTimeJsonSerializer;

/**
 * 角色/人员权限设置bo
 * @author zwr
 *
 */
public class SysPrivManagerBo implements Serializable{
	private static final long serialVersionUID = 526715904909181768L;

	private String id ; 
  	private String busiTypCod ; //业务功能，码表
  	private String orgPath ; //机构路径
  	private String orgPathName; //机构名
  	private String orgMatchTyp ;//机构匹配方式，码表
  	private String roleNo ; 
  	private String roleName;//角色名称
  	private String staffNo ; 
  	private String staffName;//员工名
  	private String autTypCod ; //权限类型编号，码表
  	private String appointOrgNo ; //指定机构编号
  	private String appointOrgName;//指定机构的名称
  	private String appointRoleNo ; //指定角色编号
  	private String appointRoleName; //指定角色名称
  	private String appointStaffNo ; //指定员工编号
  	private String appointStaffName;//指定员工名称
  	private String appointOrgClass ; //指定机构类型
  	private Date instDate ; //privInfo创建时间
  	private Date updtDate ; //privInfo更新时间
  	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBusiTypCod() {
		return busiTypCod;
	}
	public void setBusiTypCod(String busiTypCod) {
		this.busiTypCod = busiTypCod;
	}
	public String getOrgPath() {
		return orgPath;
	}
	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
	}
	
	public String getOrgPathName() {
		return orgPathName;
	}
	public void setOrgPathName(String orgPathName) {
		this.orgPathName = orgPathName;
	}
	public String getOrgMatchTyp() {
		return orgMatchTyp;
	}
	public void setOrgMatchTyp(String orgMatchTyp) {
		this.orgMatchTyp = orgMatchTyp;
	}
	public String getRoleNo() {
		return roleNo;
	}
	public void setRoleNo(String roleNo) {
		this.roleNo = roleNo;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getAutTypCod() {
		return autTypCod;
	}
	public void setAutTypCod(String autTypCod) {
		this.autTypCod = autTypCod;
	}
	public String getAppointOrgNo() {
		return appointOrgNo;
	}
	public void setAppointOrgNo(String appointOrgNo) {
		this.appointOrgNo = appointOrgNo;
	}
	public String getAppointOrgName() {
		return appointOrgName;
	}
	public void setAppointOrgName(String appointOrgName) {
		this.appointOrgName = appointOrgName;
	}
	public String getAppointRoleNo() {
		return appointRoleNo;
	}
	public void setAppointRoleNo(String appointRoleNo) {
		this.appointRoleNo = appointRoleNo;
	}
	public String getAppointRoleName() {
		return appointRoleName;
	}
	public void setAppointRoleName(String appointRoleName) {
		this.appointRoleName = appointRoleName;
	}
	public String getAppointStaffNo() {
		return appointStaffNo;
	}
	public void setAppointStaffNo(String appointStaffNo) {
		this.appointStaffNo = appointStaffNo;
	}
	public String getAppointStaffName() {
		return appointStaffName;
	}
	public void setAppointStaffName(String appointStaffName) {
		this.appointStaffName = appointStaffName;
	}
	public String getAppointOrgClass() {
		return appointOrgClass;
	}
	public void setAppointOrgClass(String appointOrgClass) {
		this.appointOrgClass = appointOrgClass;
	}
	
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	public Date getInstDate() {
		return instDate;
	}
	public void setInstDate(Date instDate) {
		this.instDate = instDate;
	}
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	public Date getUpdtDate() {
		return updtDate;
	}
	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

  	
}
