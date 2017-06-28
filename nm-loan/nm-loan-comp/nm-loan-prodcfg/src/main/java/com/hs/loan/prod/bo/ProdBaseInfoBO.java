package com.hs.loan.prod.bo;

import java.io.Serializable;
import java.util.List;

import com.hs.loan.acct.entity.PubRepayFirstConf;
import com.hs.loan.acct.entity.PubRepayTypConf;
import com.hs.loan.finance.entity.PubFundChanInfo;
import com.hs.loan.prod.entity.PubProd;
import com.hs.loan.prod.entity.PubProdArea;
import com.hs.loan.prod.entity.PubProdFee;
import com.hs.loan.prod.entity.PubProdGoods;
import com.hs.loan.prod.entity.PubProdGroup;
import com.hs.loan.prod.entity.PubProdOrg;
import com.hs.loan.prod.entity.PubProdStr;

/**
 * 产品基本信息数据传输对象
 * @author IT-009
 *
 */
public class ProdBaseInfoBO implements Serializable{

	private static final long serialVersionUID = 1L;

	/**产品基本信息**/
	private PubProd pubProd;
	
	/**还款类型**/
	 private List<PubRepayTypConf> pubRepayTypConfs;

    /**资金渠道**/
	 private List<PubFundChanInfo> pubFundChanInfos;
	 /**商品信息**/
	 private List<PubProdGoods> pubProdGoodss;
	 /**区域关系**/
	 private List<PubProdArea> prodAreaDtos;
	 /**机构关系**/
	 private List<PubProdOrg> pubProdSysOrgDtos;
	 /**网点关系**/
	 private List<PubProdStr> pubProdStrDtos ;
	 /**费用项关系**/
	 private List<PubProdFee> pubProdFeeDtos  ;
	 /**销售群、组关系**/ 
	 private List<PubProdGroup> pubProdGroups;
	 
	public List<PubRepayTypConf> getPubRepayTypConfs() {
		return pubRepayTypConfs;
	}
	public void setPubRepayTypConfs(List<PubRepayTypConf> pubRepayTypConfs) {
		this.pubRepayTypConfs = pubRepayTypConfs;
	}
	public List<PubFundChanInfo> getPubFundChanInfos() {
		return pubFundChanInfos;
	}
	public void setPubFundChanInfos(List<PubFundChanInfo> pubFundChanInfos) {
		this.pubFundChanInfos = pubFundChanInfos;
	}
	public List<PubProdGoods> getPubProdGoodss() {
		return pubProdGoodss;
	}
	public void setPubProdGoodss(List<PubProdGoods> pubProdGoodss) {
		this.pubProdGoodss = pubProdGoodss;
	}
	public PubProd getPubProd() {
		return pubProd;
	}
	public void setPubProd(PubProd pubProd) {
		this.pubProd = pubProd;
	}
	public List<PubProdArea> getProdAreaDtos() {
		return prodAreaDtos;
	}
	public void setProdAreaDtos(List<PubProdArea> prodAreaDtos) {
		this.prodAreaDtos = prodAreaDtos;
	}
	public List<PubProdOrg> getPubProdSysOrgDtos() {
		return pubProdSysOrgDtos;
	}
	public void setPubProdSysOrgDtos(List<PubProdOrg> pubProdSysOrgDtos) {
		this.pubProdSysOrgDtos = pubProdSysOrgDtos;
	}
	public List<PubProdStr> getPubProdStrDtos() {
		return pubProdStrDtos;
	}
	public void setPubProdStrDtos(List<PubProdStr> pubProdStrDtos) {
		this.pubProdStrDtos = pubProdStrDtos;
	}
	public List<PubProdFee> getPubProdFeeDtos() {
		return pubProdFeeDtos;
	}
	public void setPubProdFeeDtos(List<PubProdFee> pubProdFeeDtos) {
		this.pubProdFeeDtos = pubProdFeeDtos;
	}
	public List<PubProdGroup> getPubProdGroups() {
		return pubProdGroups;
	}
	public void setPubProdGroups(List<PubProdGroup> pubProdGroups) {
		this.pubProdGroups = pubProdGroups;
	}
}
