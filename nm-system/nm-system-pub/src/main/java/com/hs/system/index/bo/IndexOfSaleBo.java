package com.hs.system.index.bo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class IndexOfSaleBo  implements Serializable{
	
	/**
	 * 销售首页
	 */
	private static final long serialVersionUID = 1L;
	List<ValBo> retLst;
	
	public List<ValBo> getRetLst() {
		return retLst;
	}



	public void setRetLst(List<ValBo> retLst) {
		this.retLst = retLst;
	}
	
}
