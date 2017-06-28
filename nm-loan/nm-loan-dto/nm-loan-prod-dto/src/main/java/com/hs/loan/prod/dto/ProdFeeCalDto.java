package com.hs.loan.prod.dto;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

/**
 * PUB_产品与费用项关系 对象
 * @author autocreate
 * @create 2015-10-15
 */
public class ProdFeeCalDto implements Serializable{
	private static final long serialVersionUID = 1L;
	@Valid
	private List<PubProdFeeDto> seleFees;

	private List<String> instNum;

	public List<PubProdFeeDto> getSeleFees() {
		return seleFees;
	}

	public void setSeleFees(List<PubProdFeeDto> seleFees) {
		this.seleFees = seleFees;
	}

	public List<String> getInstNum() {
		return instNum;
	}

	public void setInstNum(List<String> instNum) {
		this.instNum = instNum;
	}
}