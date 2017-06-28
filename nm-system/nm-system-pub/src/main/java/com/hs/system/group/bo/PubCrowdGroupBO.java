package com.hs.system.group.bo;


import java.io.Serializable;

/**
 * PUB_产品与销售群关系 对象
 * @author autocreate
 * @create 2015-10-21
 */
public class PubCrowdGroupBO implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private String id;
    /*** 销售群/组编号 */
  	private String groupNo ; 
    
    /*** 销售群/组名称 */
  	private String groupName ; 
  	
  	 /***属性  销售群/组 */
  	private String property ;

    //构造函数
    public PubCrowdGroupBO(){}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

    //getter和setter方法
    
}