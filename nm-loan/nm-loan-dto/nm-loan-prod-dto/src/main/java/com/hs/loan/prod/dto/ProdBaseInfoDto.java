package com.hs.loan.prod.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

/**
 * 产品基本信息数据传输对象
 * @author IT-009
 *
 */
public class ProdBaseInfoDto implements Serializable{

	private static final long serialVersionUID = 1L;

	/**产品基本信息**/
	@Valid
	private PubProdDto pubProd;
	
	/**还款类型**/
	@Valid
	 private List<PubRepayTypConfDto> pubRepayTypConfs;
    /**资金渠道**/
	@Valid
	 private List<PubFundChanInfoDto> pubFundChanInfos;
	 /**商品信息**/
	@Valid
	 private List<PubProdGoodsDto> pubProdGoodss;
	 /**区域关系**/
	@Valid
	 private List<PubProdAreaDto> prodAreaDtos;
	 /**机构关系**/
	@Valid
	 private List<PubProdSysOrgDto> pubProdSysOrgDtos;
	 /**网点关系**/
	@Valid
	 private List<PubProdStrDto> pubProdStrDtos ;
	 /**费用项关系**/
	@Valid
	 private List<PubProdFeeDto> pubProdFeeDtos  ;
	 /**销售群、组关系**/ 
	@Valid
	 private List<PubProdGroupDto> pubProdGroupDto  ;
	 
	public PubProdDto getPubProd() {
		return pubProd;
	}
	public void setPubProd(PubProdDto pubProd) {
		this.pubProd = pubProd;
	}
	public List<PubRepayTypConfDto> getPubRepayTypConfs() {
		return pubRepayTypConfs;
	}
	public void setPubRepayTypConfs(List<PubRepayTypConfDto> pubRepayTypConfs) {
		this.pubRepayTypConfs = pubRepayTypConfs;
	}
	public List<PubFundChanInfoDto> getPubFundChanInfos() {
		return pubFundChanInfos;
	}
	public void setPubFundChanInfos(List<PubFundChanInfoDto> pubFundChanInfos) {
		this.pubFundChanInfos = pubFundChanInfos;
	}
	public List<PubProdGoodsDto> getPubProdGoodss() {
		return pubProdGoodss;
	}
	public void setPubProdGoodss(List<PubProdGoodsDto> pubProdGoodss) {
		this.pubProdGoodss = pubProdGoodss;
	}
	public List<PubProdAreaDto> getProdAreaDtos() {
		return prodAreaDtos;
	}
	public void setProdAreaDtos(List<PubProdAreaDto> prodAreaDtos) {
		this.prodAreaDtos = prodAreaDtos;
	}
	public List<PubProdSysOrgDto> getPubProdSysOrgDtos() {
		return pubProdSysOrgDtos;
	}
	public void setPubProdSysOrgDtos(List<PubProdSysOrgDto> pubProdSysOrgDtos) {
		this.pubProdSysOrgDtos = pubProdSysOrgDtos;
	}
	public List<PubProdStrDto> getPubProdStrDtos() {
		return pubProdStrDtos;
	}
	public void setPubProdStrDtos(List<PubProdStrDto> pubProdStrDtos) {
		this.pubProdStrDtos = pubProdStrDtos;
	}
	public List<PubProdFeeDto> getPubProdFeeDtos() {
		return pubProdFeeDtos;
	}
	public void setPubProdFeeDtos(List<PubProdFeeDto> pubProdFeeDtos) {
		this.pubProdFeeDtos = pubProdFeeDtos;
	}
	public List<PubProdGroupDto> getPubProdGroupDto() {
		return pubProdGroupDto;
	}
	public void setPubProdGroupDto(List<PubProdGroupDto> pubProdGroupDto) {
		this.pubProdGroupDto = pubProdGroupDto;
	}
	 
}
