package com.hs.loan.prod.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neo4j.cypher.internal.compiler.v2_1.prettifier.Comma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.CommonConstant;
import com.hs.loan.market.entity.PubBranch;
import com.hs.loan.market.service.PubBranchService;
import com.hs.loan.prod.bo.PubBranchBO;
import com.hs.loan.prod.entity.PubProdFee;
import com.hs.loan.prod.entity.PubProdStr;
import com.hs.loan.prod.mapper.PubProdStrMapper;
import com.hs.utils.BeanUtils;

/**
 * PUB_产品与网点的关系 业务处理
 * @author autocreate
 * @create 2015-10-15
 */
@Service
@Transactional(readOnly=true)
public class  PubProdStrService{
	@Autowired
	private PubProdStrMapper pubProdStrMapper;
	
	@Autowired
	private PubBranchService pubBranchService;
	/**
	 * 新增 PUB_产品与网点的关系
	 * @param pubProdStr
	 * @return
	 */
	@Transactional
	public void insert(PubProdStr pubProdStr){
		pubProdStrMapper.insert(pubProdStr);
	}

	/**
	 * 通过主键修改 PUB_产品与网点的关系
	 * @param map
	 * @return
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		pubProdStrMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PUB_产品与网点的关系
	 * @param primaryKey
	 * @return
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		pubProdStrMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 PUB_产品与网点的关系 对象
	 * @param primaryKey
	 * @return
	 */
	public PubProdStr getByPrimaryKey(String primaryKey){
		return pubProdStrMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PUB_产品与网点的关系 列表
	 * @param param
	 * @return List<T>
	 */
	public List<PubProdStr> queryForList(Map<String, Object> param){
		return pubProdStrMapper.queryForList(param);
	}
	
	/**
	 * 查询 PUB_产品与网点的关系 分页列表
	 * @param page
	 * @return List<T>
	 */
	public Page<PubProdStr> queryForPage(Page<PubProdStr> page){
		pubProdStrMapper.queryForList(page.getPageParams());
		return (Page<PubProdStr>)page.getPageParams().get(Page.KEY);
	}

	/**
	 * 查询产品和网点关系
	 * @param page
	 * @return
	 */
	public Page<PubBranchBO> queryProdStr(Page<PubBranchBO> page) {
		if(null == page.getPageParams().get("prodNo") || "".equals(page.getPageParams().get("prodNo"))){
			throw new ServiceException("产品编号为空");
		}
		List<PubProdStr> prodStrs = pubProdStrMapper.queryForList(page.getPageParams());
		List<PubBranchBO> retlist= new ArrayList<>();
		for (PubProdStr pubProdStr : prodStrs) {
			PubBranch pubBranch=pubBranchService.getByNo(pubProdStr.getBranchNo());
			if(pubBranch != null &&  CommonConstant.STAT_ENABLE.equals(pubBranch.getStat())){  //如果机构删除了报错到界面
				PubBranchBO bo = new PubBranchBO();
				BeanUtils.copyPropertiesNotForce(bo,pubBranch);
				bo.setId(pubProdStr.getId());
				retlist.add(bo);
			}
		}
		page.setList(retlist);
		return page;
	}

	 /**
	  * 根据产品编号删除关系信息
	  * @param prodNo
	  */
	public void deleteByProdNo(String prodNo) {
		pubProdStrMapper.deleteByProdNo(prodNo);
	}
	/**
	 * 通过业务渠道号查询 PUB_产品与业务渠道关系
	 * 
	 * @param primaryKey
	 * @return 返回true可以删除 业务渠道false则不可以删除 说明业务渠道和已有产品有关系
	 */
	public boolean queryExistRelaProdAndbranchNo(String branchNo) {
		Map<String, Object> param = new HashMap<>();
		param.put("branchNo", branchNo);
		List<PubProdStr> list= this.queryForList(param);
		if(list != null && list.size() > 0){
			return false;
		}
		return true;
	}
}