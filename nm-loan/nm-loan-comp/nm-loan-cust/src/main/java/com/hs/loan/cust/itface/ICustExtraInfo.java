package com.hs.loan.cust.itface;

import java.util.Date;

/**
 * 客户的一些附加信息 实体 的 接口
 * @author zwr
 *
 */
public interface ICustExtraInfo {

	public String getId();
	public void setId(String id);
	
	public Date getBeginDate();
	public void setBeginDate(Date beginDate);
	
	public Date getEndDate();
	public void setEndDate(Date endDate);
	
}
