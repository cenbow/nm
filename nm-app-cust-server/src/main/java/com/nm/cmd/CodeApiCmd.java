package com.nm.cmd;

import java.io.Serializable;

public class CodeApiCmd implements Serializable {
	private static final long serialVersionUID = 1L;
	private String type;
	private String names;
	private String group;
	private Integer level = 1;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
}
